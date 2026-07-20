package com.util;

public class SharePointUtilsPathTest {
	public static void main(String[] args) {
		assertPath("/LawyersDocs/QN-1/form.pdf",
				SharePointUtils.getServerRelativePath("http://sharepoint:8080/LawyersDocs/QN-1/form.pdf", "LawyersDocs", "AccountantDocs"));
		assertPath("/LawyersDocs/QN-2/form.pdf",
				SharePointUtils.getServerRelativePath("LawyersDocs\\QN-2\\form.pdf?download=true", "/LawyersDocs/", "AccountantDocs"));
		assertPath("/AccountantDocs/QN-3/form.pdf",
				SharePointUtils.getServerRelativePath("http://sharepoint/AccountantDocs/QN-3/form.pdf", "LawyersDocs", "AccountantDocs"));

		try {
			SharePointUtils.getServerRelativePath("http://sharepoint/OtherDocs/form.pdf", "LawyersDocs", "AccountantDocs");
			throw new AssertionError("Invalid base directory was accepted");
		} catch (IllegalArgumentException expected) {
			if(!expected.getMessage().contains("LawyersDocs"))
				throw new AssertionError("Validation error did not identify the expected base directory");
		}
	}

	private static void assertPath(String expected, String actual) {
		if(!expected.equals(actual))
			throw new AssertionError("Expected " + expected + " but got " + actual);
	}
}
