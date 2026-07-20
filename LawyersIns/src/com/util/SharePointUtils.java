package com.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SharePointUtils {
    private static final InetLogger logger = InetLogger.getInetLogger(SharePointUtils.class);

    public String uploadDocToSharePoint(String docLibPathName, String folderName, String baseDir,
            String docName, String srcFileUrl, String userName, String password, String domain,
            String workStation, int port, String spUrl) throws Exception {
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build()) {
            logger.debug("Document goin to upload details : doc lib path=" + docLibPathName
                    + " folderName=" + folderName + " base dir" + baseDir + " doc Name=" + docName);

            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(AuthScope.ANY,
                    new NTCredentials(userName, password, "", ""));
            HttpHost target = new HttpHost(workStation, port, "http");
            HttpClientContext context = HttpClientContext.create();
            context.setCredentialsProvider(credsProvider);

            try (CloseableHttpResponse response = httpclient.execute(target, new HttpHead("/"), context)) {
                EntityUtils.consume(response.getEntity());
                logger.debug("Share point connecting status : " + response.getStatusLine().getStatusCode());
            }

            String uri = "/" + baseDir + "/" + folderName + "/" + docName;
            File file = new File(srcFileUrl);
            HttpPut request = new HttpPut(uri);
            request.setEntity(new FileEntity(file));

            try (CloseableHttpResponse response = httpclient.execute(target, request, context)) {
                EntityUtils.consume(response.getEntity());
                int status = response.getStatusLine().getStatusCode();
                if (isUploadSuccess(status)) {
                    logger.debug(file.getName() + " is copied");
                    return "File added successfully!";
                }
            }

            logger.debug(uri + " is going to be created");
            HttpClientFactory factory = new HttpClientFactory(userName, password, spUrl, domain);
            try {
                factory.executeCreateFolder(baseDir, folderName);
            } catch (Exception ex) {
                logger.error("There is some problem in creating " + uri + " " + ex.getMessage());
            } finally {
                factory.getHttpClient().close();
            }

            try (CloseableHttpResponse response = httpclient.execute(target, request, context)) {
                EntityUtils.consume(response.getEntity());
                int status = response.getStatusLine().getStatusCode();
                if (isUploadSuccess(status)) {
                    logger.debug(file.getName() + " is copied");
                    return "File added successfully!";
                }
                String reason = response.getStatusLine().getReasonPhrase();
                String message = "Problem in copying on share point " + file.getName()
                        + " reason " + reason + " httpcode : " + status;
                logger.debug(message);
                throw new IOException(message);
            }
        }
    }

    public byte[] downloadDocFromSharePoint(String docUrl, String baseDir, String userName,
            String password, String domain, String workStation, int port) throws Exception {
        String accountantDir = SystemProperties.getInstance().getString("sharepoint.basedirAccountant");
        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build()) {
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(AuthScope.ANY,
                    new NTCredentials(userName, password, "", ""));
            HttpHost target = new HttpHost(workStation, port, "http");
            HttpClientContext context = HttpClientContext.create();
            context.setCredentialsProvider(credsProvider);

            String file = getServerRelativePath(docUrl, baseDir, accountantDir);
            HttpGet request = new HttpGet("/_api/web/GetFileByServerRelativeUrl('"
                    + file.replace("'", "''") + "')/$value");
            try (CloseableHttpResponse response = httpclient.execute(target, request, context)) {
                HttpEntity entity = response.getEntity();
                int status = response.getStatusLine().getStatusCode();
                if (status == HttpStatus.SC_OK) {
                    logger.debug(file + " has been found from share point");
                    return EntityUtils.toByteArray(entity);
                }
                EntityUtils.consume(entity);
                String message = "Problem while receiving " + file + " reason : "
                        + response.getStatusLine().getReasonPhrase() + " httpcode : " + status;
                logger.error(message);
                throw new IOException(message);
            }
        }
    }

    static String getServerRelativePath(String docUrl, String baseDir, String accountantDir) {
        if (docUrl == null || docUrl.trim().length() == 0) {
            throw new IllegalArgumentException("SharePoint document URL is missing.");
        }

        String normalizedUrl = docUrl.trim().replace('\\', '/');
        int queryIndex = normalizedUrl.indexOf('?');
        int fragmentIndex = normalizedUrl.indexOf('#');
        int suffixIndex = queryIndex < 0 ? fragmentIndex
                : fragmentIndex < 0 ? queryIndex : Math.min(queryIndex, fragmentIndex);
        if (suffixIndex >= 0) {
            normalizedUrl = normalizedUrl.substring(0, suffixIndex);
        }
        if (!normalizedUrl.startsWith("/") && normalizedUrl.indexOf("://") < 0) {
            normalizedUrl = "/" + normalizedUrl;
        }

        String normalizedBaseDir = normalizeBaseDir(baseDir);
        String normalizedAccountantDir = normalizeBaseDir(accountantDir);
        if (findBaseDir(normalizedUrl, normalizedAccountantDir) >= 0) {
            normalizedBaseDir = normalizedAccountantDir;
        }

        int baseDirIndex = findBaseDir(normalizedUrl, normalizedBaseDir);
        if (baseDirIndex < 0) {
            throw new IllegalArgumentException("SharePoint document URL does not contain base directory '"
                    + normalizedBaseDir + "'.");
        }
        return normalizedUrl.substring(baseDirIndex);
    }

    private static boolean isUploadSuccess(int status) {
        return status == HttpStatus.SC_CREATED || status == HttpStatus.SC_OK;
    }

    private static int findBaseDir(String normalizedUrl, String baseDir) {
        if (baseDir.length() == 0) {
            return -1;
        }
        String lowerUrl = normalizedUrl.toLowerCase(Locale.ENGLISH);
        String marker = "/" + baseDir.toLowerCase(Locale.ENGLISH);
        int index = lowerUrl.indexOf(marker + "/");
        return index < 0 && lowerUrl.endsWith(marker) ? lowerUrl.length() - marker.length() : index;
    }

    private static String normalizeBaseDir(String baseDir) {
        if (baseDir == null) {
            return "";
        }
        String normalized = baseDir.trim().replace('\\', '/');
        while (normalized.startsWith("/")) {
            normalized = normalized.substring(1);
        }
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }
}
