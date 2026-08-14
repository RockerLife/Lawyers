package com.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.UUID;

/**
 * Compatibility facade for the old SharePoint call sites. Documents are now
 * stored directly under document.storage.root.
 */
public class SharePointUtils {
    public String uploadDocToSharePoint(String docLibPathName, String folderName, String baseDir,
            String docName, String srcFileUrl, String userName, String password, String domain,
            String workStation, int port, String spUrl) throws Exception {
        return store(configuredRoot(), folderName, baseDir, docName, Paths.get(srcFileUrl));
    }

    public byte[] downloadDocFromSharePoint(String docUrl, String baseDir, String userName,
            String password, String domain, String workStation, int port) throws Exception {
        String alternateDir = SystemProperties.getInstance().getProperty("document.storage.alternate.directory");
        return load(configuredRoot(), docUrl, baseDir, alternateDir);
    }

    /*
     * LEGACY SHAREPOINT REFERENCE ONLY
     *
     * This was the previous HTTP upload/download implementation. The active
     * methods above use direct UUID storage. Restoring this code would also
     * require the former Apache HttpClient and HttpClientFactory imports.
     *
     * public String uploadDocToSharePoint(String docLibPathName, String folderName,
     *         String baseDir, String docName, String srcFileUrl, String userName,
     *         String password, String domain, String workStation, int port,
     *         String spUrl) throws Exception {
     *     try (CloseableHttpClient httpclient = HttpClients.custom()
     *             .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
     *             .build()) {
     *         logger.debug("Document goin to upload details : doc lib path=" + docLibPathName
     *                 + " folderName=" + folderName + " base dir" + baseDir
     *                 + " doc Name=" + docName);
     *
     *         CredentialsProvider credsProvider = new BasicCredentialsProvider();
     *         credsProvider.setCredentials(AuthScope.ANY,
     *                 new NTCredentials(userName, password, "", ""));
     *         HttpHost target = new HttpHost(workStation, port, "http");
     *         HttpClientContext context = HttpClientContext.create();
     *         context.setCredentialsProvider(credsProvider);
     *
     *         try (CloseableHttpResponse response =
     *                 httpclient.execute(target, new HttpHead("/"), context)) {
     *             EntityUtils.consume(response.getEntity());
     *             logger.debug("Share point connecting status : "
     *                     + response.getStatusLine().getStatusCode());
     *         }
     *
     *         String uri = "/" + baseDir + "/" + folderName + "/" + docName;
     *         File file = new File(srcFileUrl);
     *         HttpPut request = new HttpPut(uri);
     *         request.setEntity(new FileEntity(file));
     *
     *         try (CloseableHttpResponse response = httpclient.execute(target, request, context)) {
     *             EntityUtils.consume(response.getEntity());
     *             if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED
     *                     || response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
     *                 logger.debug(file.getName() + " is copied");
     *                 return "File added successfully!";
     *             }
     *         }
     *
     *         logger.debug(uri + " is going to be created");
     *         HttpClientFactory factory = new HttpClientFactory(userName, password, spUrl, domain);
     *         try {
     *             factory.executeCreateFolder(baseDir, folderName);
     *         } catch (Exception ex) {
     *             logger.error("There is some problem in creating " + uri + " " + ex.getMessage());
     *         } finally {
     *             factory.getHttpClient().close();
     *         }
     *
     *         try (CloseableHttpResponse response = httpclient.execute(target, request, context)) {
     *             EntityUtils.consume(response.getEntity());
     *             int status = response.getStatusLine().getStatusCode();
     *             if (status == HttpStatus.SC_CREATED || status == HttpStatus.SC_OK) {
     *                 logger.debug(file.getName() + " is copied");
     *                 return "File added successfully!";
     *             }
     *             String message = "Problem in copying on share point " + file.getName()
     *                     + " reason " + response.getStatusLine().getReasonPhrase()
     *                     + " httpcode : " + status;
     *             logger.debug(message);
     *             throw new IOException(message);
     *         }
     *     }
     * }
     *
     * public byte[] downloadDocFromSharePoint(String docUrl, String baseDir,
     *         String userName, String password, String domain, String workStation,
     *         int port) throws Exception {
     *     String accountantDir = SystemProperties.getInstance()
     *             .getString("sharepoint.basedirAccountant");
     *     try (CloseableHttpClient httpclient = HttpClients.custom()
     *             .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
     *             .build()) {
     *         CredentialsProvider credsProvider = new BasicCredentialsProvider();
     *         credsProvider.setCredentials(AuthScope.ANY,
     *                 new NTCredentials(userName, password, "", ""));
     *         HttpHost target = new HttpHost(workStation, port, "http");
     *         HttpClientContext context = HttpClientContext.create();
     *         context.setCredentialsProvider(credsProvider);
     *
     *         String file = getServerRelativePath(docUrl, baseDir, accountantDir);
     *         HttpGet request = new HttpGet("/_api/web/GetFileByServerRelativeUrl('"
     *                 + file.replace("'", "''") + "')/$value");
     *         try (CloseableHttpResponse response = httpclient.execute(target, request, context)) {
     *             HttpEntity entity = response.getEntity();
     *             int status = response.getStatusLine().getStatusCode();
     *             if (status == HttpStatus.SC_OK) {
     *                 logger.debug(file + " has been found from share point");
     *                 return EntityUtils.toByteArray(entity);
     *             }
     *             EntityUtils.consume(entity);
     *             String message = "Problem while receiving " + file + " reason : "
     *                     + response.getStatusLine().getReasonPhrase()
     *                     + " httpcode : " + status;
     *             logger.error(message);
     *             throw new IOException(message);
     *         }
     *     }
     * }
     */

    static String store(Path configuredRoot, String folderName, String baseDir,
            String docName, Path source) throws IOException {
        if (source == null || !Files.isRegularFile(source)) {
            throw new IOException("Document upload source does not exist: " + source);
        }
        safeSegment(folderName, "folder name");
        safeSegment(docName, "document name");

        Path root = secureRoot(configuredRoot);
        String documentId = UUID.randomUUID().toString();
        Path target = safePath(root, storedRelativePath(baseDir, documentId));
        Path parent = target.getParent();
        Files.createDirectories(parent);
        if (!parent.toRealPath().startsWith(root)) {
            throw new IOException("Document path resolves outside document.storage.root");
        }

        Path temporary = Files.createTempFile(parent, ".upload-", ".tmp");
        try {
            Files.copy(source, temporary, StandardCopyOption.REPLACE_EXISTING);
            try {
                Files.move(temporary, target, StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException ex) {
                Files.move(temporary, target, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temporary);
        }
        return documentId;
    }

    static byte[] load(Path configuredRoot, String docUrl, String baseDir,
            String alternateDir) throws IOException {
        Path root = secureRoot(configuredRoot);
        String documentId = documentId(docUrl);
        if (documentId != null) {
            Path file = safePath(root, storedRelativePath(baseDir, documentId));
            if (!Files.isRegularFile(file) && normalizeOptionalBaseDir(alternateDir).length() > 0) {
                file = safePath(root, storedRelativePath(alternateDir, documentId));
            }
            return read(root, file, docUrl);
        }

        String relativePath = getServerRelativePath(docUrl, baseDir, alternateDir).substring(1);
        relativePath = URLDecoder.decode(relativePath.replace("+", "%2B"), "UTF-8");
        return read(root, safePath(root, relativePath), docUrl);
    }

    private static byte[] read(Path root, Path file, String reference) throws IOException {
        if (!Files.isRegularFile(file)) {
            throw new IOException("Document was not found: " + reference);
        }
        Path realFile = file.toRealPath();
        if (!realFile.startsWith(root)) {
            throw new IOException("Document path resolves outside document.storage.root");
        }
        return Files.readAllBytes(realFile);
    }

    private static String documentId(String reference) {
        if (reference == null) {
            return null;
        }
        String value = reference.trim();
        try {
            return UUID.fromString(value).toString();
        } catch (IllegalArgumentException ex) {
            if (value.indexOf('/') >= 0 || value.indexOf('\\') >= 0) {
                return null;
            }
            throw new IllegalArgumentException("Invalid document UUID", ex);
        }
    }

    static String getServerRelativePath(String docUrl, String baseDir, String alternateDir) {
        if (docUrl == null || docUrl.trim().length() == 0) {
            throw new IllegalArgumentException("Document URL is missing.");
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
        String normalizedAlternateDir = normalizeOptionalBaseDir(alternateDir);
        if (normalizedAlternateDir.length() > 0
                && findBaseDir(normalizedUrl, normalizedAlternateDir) >= 0) {
            normalizedBaseDir = normalizedAlternateDir;
        }

        int baseDirIndex = findBaseDir(normalizedUrl, normalizedBaseDir);
        if (baseDirIndex < 0) {
            throw new IllegalArgumentException("Document URL does not contain storage directory '"
                    + normalizedBaseDir + "'.");
        }
        return normalizedUrl.substring(baseDirIndex);
    }

    private static Path configuredRoot() throws Exception {
        return Paths.get(SystemProperties.getInstance().getString("document.storage.root"));
    }

    private static Path secureRoot(Path configuredRoot) throws IOException {
        if (configuredRoot == null) {
            throw new IllegalArgumentException("document.storage.root is missing");
        }
        Path root = configuredRoot.toAbsolutePath().normalize();
        Files.createDirectories(root);
        return root.toRealPath();
    }

    private static Path safePath(Path root, String relativePath) throws IOException {
        Path path = root.resolve(relativePath).normalize();
        if (!path.startsWith(root)) {
            throw new IOException("Document path is outside document.storage.root");
        }
        return path;
    }

    private static String storedRelativePath(String baseDir, String documentId) {
        return normalizeBaseDir(baseDir) + "/" + documentId.substring(0, 2)
                + "/" + documentId.substring(2, 4) + "/" + documentId;
    }

    private static String safeSegment(String value, String label) {
        String segment = value == null ? "" : value.trim();
        if (segment.length() == 0 || ".".equals(segment) || "..".equals(segment)
                || segment.indexOf('/') >= 0 || segment.indexOf('\\') >= 0
                || segment.indexOf(':') >= 0) {
            throw new IllegalArgumentException("Invalid " + label);
        }
        for (int i = 0; i < segment.length(); i++) {
            if (segment.charAt(i) < 32 || segment.charAt(i) == 127) {
                throw new IllegalArgumentException("Invalid " + label);
            }
        }
        return segment;
    }

    private static int findBaseDir(String normalizedUrl, String baseDir) {
        String lowerUrl = normalizedUrl.toLowerCase(Locale.ENGLISH);
        String marker = "/" + baseDir.toLowerCase(Locale.ENGLISH);
        int index = lowerUrl.indexOf(marker + "/");
        return index < 0 && lowerUrl.endsWith(marker) ? lowerUrl.length() - marker.length() : index;
    }

    private static String normalizeBaseDir(String baseDir) {
        String normalized = normalizeOptionalBaseDir(baseDir);
        if (normalized.length() == 0) {
            throw new IllegalArgumentException("Invalid document storage directory");
        }
        return normalized;
    }

    private static String normalizeOptionalBaseDir(String baseDir) {
        String normalized = baseDir == null ? "" : baseDir.trim().replace('\\', '/');
        while (normalized.startsWith("/")) {
            normalized = normalized.substring(1);
        }
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        if (normalized.indexOf(':') >= 0) {
            throw new IllegalArgumentException("Invalid document storage directory");
        }
        if (normalized.length() > 0) {
            String[] segments = normalized.split("/");
            for (String segment : segments) {
                safeSegment(segment, "document storage directory");
            }
        }
        return normalized;
    }
}
