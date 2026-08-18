package com.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class SharePointUtilsRegressionTest {
    public static void main(String[] args) throws Exception {
        Path temporaryRoot = Files.createTempDirectory("document-storage-test");
        try {
            Path source = temporaryRoot.resolve("source.txt");
            Files.write(source, "stored document".getBytes(StandardCharsets.UTF_8));

            Path documentRoot = temporaryRoot.resolve("documents");
            String reference = SharePointUtils.store(documentRoot, "QN-1",
                    "LawyersDocs", "file.txt", source);
            assertTrue(reference.matches("[0-9a-f-]{36}"),
                    "Upload did not return an opaque UUID reference: " + reference);
            assertEquals("stored document", new String(
                    SharePointUtils.load(documentRoot, reference,
                            "AccountantDocs", "LawyersDocs", "QN-1"), StandardCharsets.UTF_8));
            Path storedDocument = documentRoot.resolve("LawyersDocs")
                    .resolve("QN-1").resolve("file.txt");
            assertTrue(Files.isRegularFile(storedDocument),
                    "Document was not stored using the SharePoint directory convention");
            Path metadata = documentRoot.resolve("LawyersDocs")
                    .resolve("QN-1")
                    .resolve(reference + ".metadata.properties");
            assertTrue(Files.isRegularFile(metadata), "Document UUID mapping was not stored");

            String documentId = "8e4a2942-d793-46d6-9fe7-625ef760e048";
            Path uuidDocument = documentRoot.resolve("LawyersDocs")
                    .resolve(documentId.substring(0, 2)).resolve(documentId.substring(2, 4))
                    .resolve(documentId);
            Files.createDirectories(uuidDocument.getParent());
            Files.write(uuidDocument, "UUID document".getBytes(StandardCharsets.UTF_8));
            assertEquals("UUID document", new String(
                    SharePointUtils.load(documentRoot, documentId,
                            "LawyersDocs", null), StandardCharsets.UTF_8));

            Path legacy = documentRoot.resolve("LawyersDocs/QN-2/legacy.txt");
            Files.createDirectories(legacy.getParent());
            Files.write(legacy, "legacy document".getBytes(StandardCharsets.UTF_8));

            String mappedDocumentId = "c9a71a72-c043-4f32-88cc-df102b1c904e";
            Path oldMetadata = documentRoot.resolve("LawyersDocs")
                    .resolve(mappedDocumentId.substring(0, 2))
                    .resolve(mappedDocumentId.substring(2, 4))
                    .resolve(mappedDocumentId + ".metadata.properties");
            Files.createDirectories(oldMetadata.getParent());
            Files.write(oldMetadata,
                    "document.storageLocation=LawyersDocs/QN-2/legacy.txt\n"
                            .getBytes(StandardCharsets.ISO_8859_1));
            assertEquals("legacy document", new String(
                    SharePointUtils.load(documentRoot, mappedDocumentId,
                            "LawyersDocs", null, "QN-2"), StandardCharsets.UTF_8));

            assertEquals("legacy document", new String(
                    SharePointUtils.load(documentRoot,
                            "http://old-sharepoint/LawyersDocs/QN-2/legacy.txt?download=1",
                            "AccountantDocs", "LawyersDocs"), StandardCharsets.UTF_8));

            expectFailure(new CheckedAction() {
                public void run() throws Exception {
                    SharePointUtils.store(documentRoot, "../outside",
                            "LawyersDocs", "file.txt", source);
                }
            });
            expectFailure(new CheckedAction() {
                public void run() throws Exception {
                    SharePointUtils.load(documentRoot,
                            "/LawyersDocs/../../source.txt", "LawyersDocs", null);
                }
            });
            expectFailure(new CheckedAction() {
                public void run() throws Exception {
                    SharePointUtils.load(documentRoot, "not-a-uuid", "LawyersDocs", null);
                }
            });
            expectFailure(new CheckedAction() {
                public void run() throws Exception {
                    SharePointUtils.load(documentRoot, " ", "LawyersDocs", null);
                }
            });

            System.out.println("SharePointUtilsRegressionTest passed");
        } finally {
            try (Stream<Path> paths = Files.walk(temporaryRoot)) {
                paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
    }

    private static void expectFailure(CheckedAction action) throws Exception {
        try {
            action.run();
            throw new AssertionError("Unsafe document path was accepted");
        } catch (IOException expected) {
            // expected
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private interface CheckedAction {
        void run() throws Exception;
    }
}
