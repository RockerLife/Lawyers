/*
 * Created on May 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manage.process;

import com.util.InetLogger;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.util.SystemProperties;




 /* @author Amit Jain
 *
 * TODO don't forget to add the javadoc!
 */
public class FileUploadServlet extends HttpServlet 
{
	private static final InetLogger logger = InetLogger.getInetLogger(FileUploadServlet.class);
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            doPost(request, response);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
       
    	String dynaKeys = request.getParameter("dynaKeys");
        String dynaValues = request.getParameter("dynaValues");
        String inet_method = request.getParameter("inet_method");
        String inet_page = request.getParameter("inet_page");
        String QN = request.getSession().getAttribute("QuoteNumber") != null ? request.getSession().getAttribute("QuoteNumber").toString():"";
        
        try {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		
			// maximum size that will be stored in memory
			factory.setSizeThreshold(25*1024*1024);
		
			// the location for saving data that is larger than getSizeThreshold()
			String tempDirectory = SystemProperties.getInstance().getString(
					"fileupload.tempdirectory");
			factory.setRepository(new File(tempDirectory));
			
			
			ServletFileUpload upload = new ServletFileUpload(factory);
		
			// maximum size before a FileUploadException will be thrown
			Long size = new Long(25*1024*1024);
			upload.setSizeMax(size.longValue());	
		
			List fileItems = upload.parseRequest(request);
		
			// assume we know there are two files. The first file is a small
			// text file, the second is unknown and is written to a file on
			// the server
			Iterator i = fileItems.iterator();
		
			String fileName = "";
			String DocTitle = "";
			String Comment = "";
			String DocFileName = "";
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
		
				if (fi.isFormField()) {
				   String name = fi.getFieldName();
				   if(name.equalsIgnoreCase("DocTitle")){
					   DocTitle = fi.getString();
				   }else if(name.equalsIgnoreCase("Comment")){
					   Comment = fi.getString();
				   }
				 //  String value = fi.getString();
				} 
				
				// filename on the client
				fileName = fi.getName();
		
				if (fileName == null) {
					continue;
				}
				int lastIndex = fileName.lastIndexOf(".");
				if (lastIndex == -1) {
		            continue;
		        }          
		        //String ext = fileName.substring(lastIndex+1); 
		        DocFileName = fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length());
		        DocFileName = QN + "_" + DocFileName;
		        /*if(ext == null)
		        	ext = "xls";*/
		              
				//String newFileName = "Account_"+QN+"."+ ext;			
				// save comment and filename to database
				// write the file
				String uploaddirectory = SystemProperties.getInstance().getString(
						"fileupload.uploaddirectory");
				
				fi.write(new File(uploaddirectory, DocFileName));
				
				fi=null;
				
				//break;
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/upload.do?dynaKeys="+dynaKeys+"&dynaValues="+dynaValues+"&inet_method="+inet_method+"&inet_page="+inet_page+"&uploadSuccess=Y&DocTitle="+DocTitle+"&Comment="+Comment+"&DocFileName="+DocFileName);
		    dispatcher.forward(request,response);
		} catch (Exception e) {
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/upload.do?dynaKeys="+dynaKeys+"&dynaValues="+dynaValues+"&inet_method="+inet_method+"&inet_page="+inet_page+"&uploadSuccess=N");
		    try {
				dispatcher.forward(request,response);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				logger.error("Unexpected error", e1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				logger.error("Unexpected error", e1);
			}
		    logger.error("Unexpected error", e);
		}
    }

    
   
}
