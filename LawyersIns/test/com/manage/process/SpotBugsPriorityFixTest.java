package com.manage.process;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;

import com.util.Context;

public class SpotBugsPriorityFixTest {
	public static void main(String[] args) throws Exception {
		HTMLGenerator generator = new HTMLGenerator("test");
		if(generator.getDocument(null, "missing") != null)
			throw new AssertionError("Invalid HTML unexpectedly produced a document");
		if(generator.parseDocument(new Context(), null, "missing", null) != null)
			throw new AssertionError("Null document unexpectedly produced output");

		Document documentWithoutBody = new Document(new Element("html"));
		if(generator.parseDocument(new Context(), documentWithoutBody, "missing-body", null) != documentWithoutBody)
			throw new AssertionError("Document without BODY was not returned safely");

		try {
			new TestServlet().init(missingProjectConfig());
			throw new AssertionError("Missing project configuration was accepted");
		} catch (ServletException expected) {
			if(!expected.getMessage().contains("Project name"))
				throw new AssertionError("Unexpected configuration error: " + expected.getMessage());
		}
	}

	private static ServletConfig missingProjectConfig() {
		return new ServletConfig() {
			public String getServletName() { return "test"; }
			public ServletContext getServletContext() { return null; }
			public String getInitParameter(String name) { return null; }
			public Enumeration getInitParameterNames() { return Collections.enumeration(Collections.EMPTY_LIST); }
		};
	}

	private static class TestServlet extends ComponentProcessServlet {
		public boolean validateApplication(HttpServletRequest request, HttpServletResponse response, Context ctx) { return true; }
		public String initLocalResources() { return null; }
		public boolean validateApplicationForSession(HttpServletRequest request, HttpServletResponse response, String action) { return true; }
		public void logOut(Context ctx, HttpServletRequest request, HttpServletResponse response) { }
	}
}
