package com.manage.docmanagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.util.SharePointUtils;
import com.util.SystemProperties;

/** Keeps the legacy method signatures while routing documents to local storage. */
public class DocManagementUtil {
    public static byte[] getBytesFromFile(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    public String uploadDocToSharePoint(String docLibPathName, String folderName, String baseDir,
            String docName, String srcFileUrl, String userName, String password, String domain,
            String spUrl) throws Exception {
        return new SharePointUtils().uploadDocToSharePoint(docLibPathName, folderName, baseDir,
                docName, srcFileUrl, null, null, null, null, 0, null);
    }

    public byte[] downloadDocFromSharePoint(String docUrl, String userName, String password,
            String domain) throws Exception {
        String baseDir = SystemProperties.getInstance().getString("document.storage.directory");
        return downloadDocFromSharePoint(docUrl, null, null, null, baseDir);
    }

    public byte[] downloadDocFromSharePoint(String docUrl, String userName, String password,
            String domain, String baseDir) throws Exception {
        return downloadDocFromSharePoint(docUrl, userName, password, domain, baseDir, null);
    }

    public byte[] downloadDocFromSharePoint(String docUrl, String userName, String password,
            String domain, String baseDir, String folderName) throws Exception {
        return new SharePointUtils().downloadDocFromSharePoint(docUrl, baseDir,
                null, null, null, null, 0, folderName);
    }

    /*
     * LEGACY SHAREPOINT WRAPPERS - REFERENCE ONLY
     *
     * public String uploadDocToSharePoint(String docLibPathName, String folderName,
     *         String baseDir, String docName, String srcFileUrl, String userName,
     *         String password, String domain, String spUrl) throws Exception {
     *     String workStation = getworkStation();
     *     int port = Integer.parseInt(getPort());
     *     return new SharePointUtils().uploadDocToSharePoint(docLibPathName, folderName,
     *             baseDir, docName, srcFileUrl, userName, password, domain,
     *             workStation, port, spUrl);
     * }
     *
     * public byte[] downloadDocFromSharePoint(String docUrl, String userName,
     *         String password, String domain) throws Exception {
     *     String baseDir = getSharePointBaseDirectory();
     *     String workStation = getworkStation();
     *     int port = Integer.parseInt(getPort());
     *     return new SharePointUtils().downloadDocFromSharePoint(docUrl, baseDir,
     *             userName, password, domain, workStation, port);
     * }
     *
     * public byte[] downloadDocFromSharePoint(String docUrl, String userName,
     *         String password, String domain, String baseDir) throws Exception {
     *     String workStation = getworkStation();
     *     int port = Integer.parseInt(getPort());
     *     return new SharePointUtils().downloadDocFromSharePoint(docUrl, baseDir,
     *             userName, password, domain, workStation, port);
     * }
     */
}
