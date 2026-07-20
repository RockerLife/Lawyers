package com.manage.docmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.tempuri.AuthHeader;
import org.tempuri.UploadDocument;
import org.tempuri.UploadDocumentSoap;

import com.util.InetLogger;
import com.util.SharePointUtils;
import com.util.SystemProperties;

//import com.sun.xml.ws.api.message.Headers;
//import com.sun.xml.ws.developer.WSBindingProvider;

public class DocManagementUtil {	
	 private static InetLogger logger = InetLogger.getInetLogger(DocManagementUtil.class);
	 
	 public String getworkStation() throws Exception {
		return SystemProperties.getInstance().getString("sharepoint.workstation");
	 }
	 
	 public String getPort() throws Exception {
		return SystemProperties.getInstance().getString("sharepoint.port");
	 }
	 
	 public String getSharePointBaseDirectory() throws Exception {
		return SystemProperties.getInstance().getString("sharepoint.basedir");
	}
	 
	public String uploadDocToSharePoint(String docLibPathName,String folderName,String baseDir,
			String docName, String srcFileUrl, String userName,String password,String domain, String spUrl) throws Exception{		
		
		String workStation = getworkStation();
		int port = Integer.parseInt(getPort());
		return new SharePointUtils().uploadDocToSharePoint(docLibPathName, folderName, baseDir, docName, srcFileUrl, userName, password, domain, workStation, port, spUrl);
	}
	
	public String uploadDocToSharePoint(String docLibPathName,String folderName,String baseDir,
			String docName,byte[] docContent,String userName,String password,String domain) throws Exception{
		
		String result = null;		
		UploadDocumentSoap docService = null;
		
		
		try{
			docService = new UploadDocument().getUploadDocumentSoap();
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
			
		AuthHeader authInfo = new AuthHeader();
		
		authInfo.setUsername(userName);
		authInfo.setPassword(password);
		authInfo.setDomain(domain);
		/*if(true)
			throw new PolicyNumberException();
		*/
		String res = docService.uploadFilewithSoap(folderName,baseDir,docName, docContent, docLibPathName,authInfo);
		return res;		
	}
	
	/*public byte[] downloadDocFromSharePoint(String docUrl, String userName, String password, String domain) throws Exception{
        
        byte[] retDoc = null;        
        UploadDocumentSoap docService = null;        
        
        try{
              docService = new UploadDocument().getUploadDocumentSoap();
      
              
        AuthHeader authHeader = new AuthHeader();        
        authHeader.setUsername(userName);
        authHeader.setPassword(password);
        authHeader.setDomain(domain);
        
        retDoc = docService.downloadDocument(docUrl, authHeader);   
		}catch(Exception e){
	        e.printStackTrace();
	  }
        finally{
        	docService=null;
        }
        return retDoc;
        
	}*/
	
	public byte[] downloadDocFromSharePoint(String docUrl, String userName, String password, String domain) throws Exception{
        
        byte[] retDoc = null;        
       String baseDir = getSharePointBaseDirectory();
		String workStation = getworkStation();
		int port = Integer.parseInt(getPort());
        return new SharePointUtils().downloadDocFromSharePoint(docUrl, baseDir, userName, password, domain, workStation, port);
       
	}

public byte[] downloadDocFromSharePoint(String docUrl, String userName, String password, String domain,String baseDir) throws Exception{
        
        byte[] retDoc = null;        
       // String baseDir = getSharePointBaseDirectory();
		String workStation = getworkStation();
		int port = Integer.parseInt(getPort());
        return new SharePointUtils().downloadDocFromSharePoint(docUrl, baseDir, userName, password, domain, workStation, port);
       
	}

	
	public void downloadDocFromSharePoint(){
		
		OutputStreamWriter writer=null;
		BufferedReader in =null;
		
		try{
		URL u = new URL("http://wss3lp:8081/TestJava/QN-0000410/AmitSQL.txt");
		URLConnection uc = u.openConnection();
		uc.setDoOutput(true);

		uc.setRequestProperty("Content-Type","application/pdf");

//		uc.setRequestProperty("Username","sanjayr");
//		uc.setRequestProperty("Password","Sanr55.bgp");
//		uc.setRequestProperty("Domain","in");	
		writer=new OutputStreamWriter(uc.getOutputStream());
		writer.flush();
		
		in = new BufferedReader(
		new InputStreamReader(uc.getInputStream()));
		}catch(Exception ex){
			logger.error("Unexpected error", ex);
			logger.error("error in downloadDocFromSharePoint method:::"+ex.getMessage());
		}
		finally{
			/*code by sukhi 26/09/2018*/
			if(writer != null)
				try {
					writer.close();
				} catch (Exception e) {					
					logger.error("Unexpected error", e);
				}
			if(in != null)
				try {
					in.close();
				} catch (Exception e) {					
					logger.error("Unexpected error", e);
				}
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		String docName = "AccountantPolicy_10_08_2010.pdf";
		String docLibPathName = "http://wss3lp:8081/TestJava";
		String folderName = "QN-0000101";
		String userName ="sanjayr";
		String password = "Sanr55.bgp";
		String domain = "in";
		String baseDir = "TestJava";
		
		byte[] docContent = getBytesFromFile(new File("d:\\Accountant_Policy.pdf"));
		
		
		//http://wss3lp:8081/TestJava/QN-0000102/AccountantPolicy_10_05_2010.pdf
		

		new DocManagementUtil().downloadDocFromSharePoint();
		
	}

	public static byte[] getBytesFromFile(File file) {
		java.io.InputStream is = null;
		try { 
			is = new java.io.FileInputStream(file); 
			} catch (FileNotFoundException e) {
				logger.error("Unexpected error", e); 
				} 
		// Get the size of the file 
		long length = file.length(); 
		// Create the byte array to hold the data 
		byte[] bytes = new byte[(int)length]; 
		// Read in the bytes 
		int offset = 0; 
		int numRead = 0; 
		try { 
			while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
				offset += numRead; 
				} 
			} catch (IOException e) {
				logger.error("Unexpected error", e); 
			} 
		// Close the input stream and return bytes 
		try { 
			is.close(); 
			} catch (IOException e) {
				logger.error("Unexpected error", e); 
			}
	
		return bytes;
	
	}
}
