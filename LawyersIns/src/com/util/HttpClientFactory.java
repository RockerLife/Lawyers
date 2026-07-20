package com.util;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpClientFactory {
	private static InetLogger logger = InetLogger.getInetLogger(HttpClientFactory.class);
	
	private String SiteUrl;
	private CredentialsProvider credsProvider;
	private CloseableHttpClient httpclient;

	public HttpClientFactory(String LoginName, String Passwd, String SiteUrl, String DomainName){
		this.SiteUrl = SiteUrl;
		this.credsProvider = new BasicCredentialsProvider();
		this.credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new NTCredentials(LoginName, Passwd, SiteUrl, DomainName));
		this.httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
	}

	public CloseableHttpClient getHttpClient(){
		return this.httpclient;
	}

	public void executeCreateFolder(String documentLibrary, String folderName) throws Exception{
		StringBuilder postMethodLocation = new StringBuilder();

		postMethodLocation.append(this.SiteUrl);
		postMethodLocation.append("/_api/Web/Folders/add('" + documentLibrary + "/" + folderName + "')");

		logger.debug("SharePointTestClient.uploadFileMethodLocation " + postMethodLocation );
		//System.out.println("SharePointTestClient.uploadFileMethodLocation " + postMethodLocation );

		HttpPost httppost = new HttpPost(postMethodLocation.toString());

		httppost.addHeader("Accept", "application/json;odata=verbose");
		httppost.addHeader("X-HTTP-Method", "POST");
		httppost.addHeader("X-ClientService-ClientTag", "SDK-JAVA");
		httppost.addHeader("X-RequestDigest", this.getRequestDigest());

		CloseableHttpResponse response = this.httpclient.execute(httppost);

		String restfulresponse = EntityUtils.toString(response.getEntity(), "UTF-8");

		logger.debug(restfulresponse);
		//System.out.println(restfulresponse);		
		//System.out.println("RequestLine " + httppost.getRequestLine());
		//System.out.println("ProtocolVersion " +  response.getProtocolVersion());
		//System.out.println("StatusLine " + response.getStatusLine());

		response.close();
	}

	public String getRequestDigest() throws Exception {
		HttpPost httppost = new HttpPost(this.SiteUrl + "/_api/contextinfo");

		httppost.addHeader("Accept", "application/json;odata=verbose");
		httppost.addHeader("content-type", "application/json;odata=verbose");
		httppost.addHeader("X-ClientService-ClientTag", "SDK-JAVA");

		CloseableHttpResponse response = this.httpclient.execute(httppost);

		String restfulresponse = EntityUtils.toString(response.getEntity(), "UTF-8");

		return this.parseJsonDigestValue(restfulresponse);
	}

	private String parseJsonDigestValue(String json) throws Exception{
		JSONObject jsonObject = new JSONObject(json);

		jsonObject = (JSONObject)jsonObject.get("d");
		jsonObject = (JSONObject)jsonObject.get("GetContextWebInformation");

		return jsonObject.getString("FormDigestValue");
	}

	public static void main(String args[]) {	  
		String loginname = "administrator";
		String password = "Yahoo.com1";
		String sharepointsiteurl = "http://adc-secureone:9696/";
		String windomainname = "secureone";

		HttpClientFactory factory = new HttpClientFactory(loginname, password, sharepointsiteurl, windomainname);

		try {
			factory.executeCreateFolder("LawyersDocs", "TestFolder");
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		} finally { 

		}
	}

}
