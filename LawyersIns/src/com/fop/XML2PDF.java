package com.fop;

//Java
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

//JAXP 
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

//FOP
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.servlet.ServletContextURIResolver;

import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;


public class XML2PDF {
	
	private String xmlFile = "data.xml";
    private String xslFile = "C://pdfGen.xsl";
    private String outFile = "C://result.pdf";
    private String foFile = "quoteletter.xml";
    private static InetLogger logger = InetLogger.getInetLogger(XML2PDF.class);
    
    public void convertXMLtoStream(String foFile, StringBuffer xmlContent, List listFormID, String out) throws Exception
    {
    	
    	try {
		
    		String strStruct = "</response>"; 
            int iIndex = xmlContent.indexOf(strStruct);

            xmlContent.delete(iIndex, iIndex+strStruct.length());
            xmlContent.insert(iIndex, "<templates>");
            int k = 0;
        	
            for(int i=0; i<listFormID.size(); i++)
            {
            	Map map = (Map) listFormID.get(i);
            	String formID = map.get("FormID").toString();
            	if(!"".equals(formID))
            	{
            		String TempID = "";
                	if(formID.contains("099"))
                		TempID ="LPL_099";
                	else if(formID.contains("100"))
                		TempID ="LPL_100";
                	else if(formID.contains("102"))
                		TempID ="LPL_102";
                	else if(formID.contains("103") && !formID.contains("FM 2.0.1033"))
               		 	TempID ="LPL_103";
                	else if(formID.contains("104"))
               		 	TempID ="LPL_104";
                	else if(formID.contains("105"))
               		 	TempID ="LPL_105";
                	else if(formID.contains("106"))
               		 	TempID ="LPL_106";
                	else if(formID.contains("125"))
                  		 TempID ="LPL_125";
                	else if(formID.contains("126"))
                 		 TempID ="LPL_126";
                	else if(formID.contains("127"))
                		 TempID ="LPL_127";
                	else if(formID.contains("129"))
                		TempID ="LPL_129";
                	else if(formID.contains("130"))
                		TempID ="LPL_130";
                	else if(formID.contains("107"))
                		TempID ="LPL_107";
                	else if(formID.contains("108"))
                		TempID ="LPL_108";
                	else if(formID.contains("120"))
                		TempID ="LPL_120";
                	else if(formID.contains("121"))
                		TempID ="LPL_121";
                	else if(formID.contains("135"))
                		TempID ="LPL_135";
                	else if(formID.contains("117"))
                		TempID ="LPL_117";
                	else if(formID.contains("114"))
                		TempID ="LPL_114";
                	else if(formID.contains("132"))
                		TempID ="LPL_132";
                	else if(formID.contains("delendrs"))
                		TempID ="LPL_delendrs";
                	else if(formID.contains("109"))
                		TempID ="LPL_109";
                	xmlContent.append("<template_id_" + TempID +">" + TempID.trim() +"</template_id_" + TempID +">\n");
                	
            	}  
            }
            
            /*xmlContent.append("<template_id_LPL_Policycoverletter>LPL_Policycoverletter</template_id_LPL_Policycoverletter>\n");
            xmlContent.append("<template_id_LPL_HOTLINENOTICELW>LPL_HOTLINENOTICELW</template_id_LPL_HOTLINENOTICELW>\n");
            xmlContent.append("<template_id_LPL_OfacNotice>LPL_OfacNotice</template_id_LPL_OfacNotice>\n");
            */
            xmlContent.append("</templates>\n"); 
            xmlContent.append("</response>");
            //System.out.println("xmlContent.." + xmlContent);

	    	convertPOToPDF(foFile, xmlContent, out);
    	}
    	catch (Exception e) {			
			logger.error("Unexpected error", e);
			logger.error("Exception convertXMLtoStream " + e.getMessage());
			throw e;
		}    
    }
    
    public void convertPOToPDF( String foFile, StringBuffer xmlData, String outFile)
	{
    	File fofile=null;
    	File pdffile=null;
    	FopFactory fopFactory =null;
    	FOUserAgent foUserAgent=null;
    	Fop fop =null;
    	TransformerFactory factory=null;
    	Transformer transformer =null;
    	Source src =null;
    	Result res =null;
    	
		try{
			fofile = new File(foFile);
			pdffile = new File(outFile);
			
			// configure fopFactory as desired
			fopFactory = FopFactory.newInstance();
			foUserAgent = fopFactory.newFOUserAgent();
			 
			// configure foUserAgent as desired
			// Setup output		 
			OutputStream out = new java.io.FileOutputStream(pdffile);
			out = new java.io.BufferedOutputStream(out);
			 
			try {
				// Construct fop with desired output format
				fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
				
				// Setup XSLT
				factory = TransformerFactory.newInstance();
				transformer = factory.newTransformer(new StreamSource(fofile));
				
				// Set the value of a <param> in the stylesheet
				//transformer.setParameter("versionParam", "2.0");
				
				// Setup input for XSLT transformation
				src = new StreamSource(new ByteArrayInputStream(xmlData.toString().getBytes()));
				 
				// Resulting SAX events (the generated FO) must be piped through to FOP
				res = new SAXResult(fop.getDefaultHandler());
				
				// Start XSLT transformation and FOP processing
				transformer.transform(src, res);
				
				} 
				finally {			 
					out.close();
					
				}
				
				 logger.debug("Success!");
				 fofile=null;
				 pdffile=null;
			} 
			catch (Exception e) {
				 logger.error("Unexpected error", e);
				 			  
			}
		finally {
			/*code by sukhi 26/09/2018*/
			res =null;
			src =null;
			transformer =null;
			factory=null;
			fop =null;
			foUserAgent=null;
			fopFactory =null;
			fofile=null;
	    	pdffile=null;
	    	
		}
	}

    
    public void convertPOToPDFApplication(String foFile, String xmlFile, Context ctx, HttpServletRequest req, HttpServletResponse res, ServletContextURIResolver uriResolver, ServletContext servletContext)
	{
    	ServletOutputStream out = null;
    	uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);   
    	File xmlfile=null;
    	File fofile=null;
		try
		{
			xmlfile = new File(xmlFile);
			fofile = new File(foFile);
			
			// configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();
			//tell the FOPFactory object where to look for resources
		    fopFactory.setURIResolver(uriResolver);

			//fopFactory.setUserConfig(new File("cfg.xml"));
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			foUserAgent.setBaseURL("file:///" + servletContext.getRealPath("/") );

			// configure foUserAgent as desired
			// Setup output		 
			out = res.getOutputStream();
			 
			try 
			{
				if(res != null)
				{
					// setting some response headers
					res.setHeader("Expires", "0");
					res.setHeader("Content-disposition","attachment; filename=\"" + "Lawyers_Application_" + ctx.get("QuoteNumber").toString() + ".pdf\"");				
					res.setHeader("Pragma", "public");															 
				}	
				
				// Construct fop with desired output format
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
								
				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				
				//tell the TransformerFactory where to find resources  
			    factory.setURIResolver(uriResolver);
			    
			    Transformer transformer = factory.newTransformer(new StreamSource(fofile));				
			    
			    //tell the Transformer object where to find resources 
			    transformer.setURIResolver(uriResolver);

				// Setup input for XSLT transformation
				Source src = new StreamSource(xmlfile);
				 
				// Resulting SAX events (the generated FO) must be piped through to FOP
				Result result = new SAXResult(fop.getDefaultHandler());
				
				// Start XSLT transformation and FOP processing
				transformer.transform(src, result);			
				
				out = res.getOutputStream();
				out.flush();
							
			} 
			catch(Exception e) {			 
				logger.error("Unexpected error", e);
			}			
				 
		} 
		catch (Exception e) {
			 logger.error("Unexpected error", e);					  
		}
		finally{
			if(out != null)
				try {
					out.close();
				} catch (Exception e) {					
					logger.error("Unexpected error", e);
				}
			/*code by sukhi 26/09/2018*/
			fofile=null;
			xmlfile=null;
		}
	}
    
    public void convertPOToPDF( String foFile,String xmlFile, String outFile)
	{
    	File xmlfile=null;
    	File fofile=null;
    	File pdffile=null;
    	
		try{
			xmlfile = new File(xmlFile);
			fofile = new File(foFile);
			pdffile = new File(outFile);
			
			// configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			 
			// configure foUserAgent as desired
			// Setup output		 
			OutputStream out = new java.io.FileOutputStream(pdffile);
			out = new java.io.BufferedOutputStream(out);
			 
			try {
				// Construct fop with desired output format
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
				
				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory.newTransformer(new StreamSource(fofile));
				
				// Set the value of a <param> in the stylesheet
				//transformer.setParameter("versionParam", "2.0");
				
				// Setup input for XSLT transformation
				Source src = new StreamSource(xmlfile);
				 
				// Resulting SAX events (the generated FO) must be piped through to FOP
				Result res = new SAXResult(fop.getDefaultHandler());
				
				// Start XSLT transformation and FOP processing
				transformer.transform(src, res);
				
				} 
				finally {			 
					out.close();
				}
				
				 logger.debug("Success!");
				 
			} 
			catch (Exception e) {
				 logger.error("Unexpected error", e);
				 			  
			}
		
		/*code by sukhi 26/09/2018*/
		
		finally{
			pdffile=null;
			fofile=null;
			xmlfile=null;
		}
	}
    
    
    
    public void convertPOToPDF(String foFile, String xmlFile, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver,Context ctx)
	{
    	//ServletOutputStream out = null;
    	uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);  
    	File xmlfile=null;
    	File fofile=null;
    	
		try
		{
			logger.debug("Fetch data for XML convertPOToPDFconvertPOToPDF: 300 "); 
			xmlfile = new File(xmlFile);
			fofile = new File(foFile);		
			
			// configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();
			//tell the FOPFactory object where to look for resources
		    fopFactory.setURIResolver(uriResolver);

			//fopFactory.setUserConfig(new File("cfg.xml"));
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			foUserAgent.setBaseURL(baseUrl);

			// configure foUserAgent as desired
			// Setup output		 
			//out = res.getOutputStream();
			 
			try 
			{
//				if(res != null)
//				{
//					// setting some response headers
//					res.setHeader("Expires", "0");
//					res.setHeader("Content-disposition","attachment; filename=\"" + "Accountant_Quote_Letter_" + ctx.get("QuoteNumber").toString() + ".pdf\"");				
//					res.setHeader("Pragma", "public");															 
//				}	
				
				// Construct fop with desired output format
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
								
				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				
				//tell the TransformerFactory where to find resources  
			    factory.setURIResolver(uriResolver);
			    
			    Transformer transformer = factory.newTransformer(new StreamSource(fofile));				
			    
			    //tell the Transformer object where to find resources 
			    transformer.setURIResolver(uriResolver);

				// Setup input for XSLT transformation
				Source src = new StreamSource(xmlfile);
				 
				// Resulting SAX events (the generated FO) must be piped through to FOP
				Result result = new SAXResult(fop.getDefaultHandler());
				
				// Start XSLT transformation and FOP processing
				transformer.transform(src, result);					
			} 
			catch(Exception e) {			 
				logger.error("Unexpected error", e);
			}			
				 
		} 
		catch (Exception e) {
			 logger.error("Unexpected error", e);					  
		}	
		finally{
			/*code by sukhi 26/09/2018*/
			fofile=null;
			xmlfile=null;
		}
	}
    
    public void convertPOToPDFQuoteLetter(String foFile, String xmlFile, Context ctx, HttpServletRequest req, HttpServletResponse res, ServletContextURIResolver uriResolver, ServletContext servletContext)
	{
    	ServletOutputStream out = null;
    	uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);   
    	File xmlfile=null;
    	File fofile=null;
    	
		try
		{
			xmlfile = new File(xmlFile);
			fofile = new File(foFile);
			
			// configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();
			//tell the FOPFactory object where to look for resources
		    fopFactory.setURIResolver(uriResolver);

			//fopFactory.setUserConfig(new File("cfg.xml"));
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			foUserAgent.setBaseURL("file:///" + servletContext.getRealPath("/") );

			// configure foUserAgent as desired
			// Setup output		 
			out = res.getOutputStream();
			 
			try 
			{
				if(res != null)
				{
					// setting some response headers
					res.setHeader("Expires", "0");
					res.setHeader("Content-disposition","attachment; filename=\"" + "Lawyers_Quote_Letter_" + ctx.get("QuoteNumber").toString() + ".pdf\"");				
					res.setHeader("Pragma", "public");															 
				}	
				
				// Construct fop with desired output format
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
								
				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				
				//tell the TransformerFactory where to find resources  
			    factory.setURIResolver(uriResolver);
			    
			    Transformer transformer = factory.newTransformer(new StreamSource(fofile));				
			    
			    //tell the Transformer object where to find resources 
			    transformer.setURIResolver(uriResolver);

				// Setup input for XSLT transformation
				Source src = new StreamSource(xmlfile);
				 
				// Resulting SAX events (the generated FO) must be piped through to FOP
				Result result = new SAXResult(fop.getDefaultHandler());
				
				// Start XSLT transformation and FOP processing
				transformer.transform(src, result);			
				
				out = res.getOutputStream();
				out.flush();
							
			} 
			catch(Exception e) {			 
				logger.error("Unexpected error", e);
			}			
				 
		} 
		catch (Exception e) {
			 logger.error("Unexpected error", e);					  
		}
		finally{
			if(out != null)
				try {
					out.close();
				} catch (Exception e) {					
					logger.error("Unexpected error", e);
				}
			/*code by sukhi 26/09/2018*/
			fofile=null;
			xmlfile=null;
		}
	}
    
    /*
     * This code is used during Policy form attachment
     */
    public void convertXMLtoStream(String foFile, StringBuffer xmlContent, List listFormID, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver,Context ctx) throws Exception
    {
    	uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
    	try {
		
    		String strStruct = "</response>"; 
            int iIndex = xmlContent.indexOf(strStruct);

            xmlContent.delete(iIndex, iIndex+strStruct.length());
            xmlContent.insert(iIndex, "<templates>");
            int k = 0;
        	if("1".equals(ctx.get("CompanyKey").toString()))
        	{
	            for(int i=0; i<listFormID.size(); i++)
	            {
	            	Map map = (Map) listFormID.get(i);
	            	String formID = map.get("FormID").toString();
	            	if(!"".equals(formID))
	            	{
	            		String TempID = "";
	                	if(formID.contains("099"))
	                		TempID ="LPL_099";
	                	else if(formID.contains("100"))
	               		 	TempID ="LPL_100";
	                	else if(formID.contains("102"))
	               		 	TempID ="LPL_102";
	                	else if(formID.contains("103") && !formID.contains("FM 2.0.1033"))
	               		 	TempID ="LPL_103";
	                	else if(formID.contains("104"))
	               		 	TempID ="LPL_104";
	                	else if(formID.contains("105"))
	               		 	TempID ="LPL_105";
	                	else if(formID.contains("106"))
	               		 	TempID ="LPL_106";
	                	else if(formID.contains("125"))
	                  		 TempID ="LPL_125";
	                	else if(formID.contains("126"))
	                 		 TempID ="LPL_126";
	                	else if(formID.contains("127"))
	                		 TempID ="LPL_127";
	                	else if(formID.contains("129"))
	                		TempID ="LPL_129";
	                	else if(formID.contains("130"))
	                		TempID ="LPL_130";
	                	else if(formID.contains("107"))
	                		TempID ="LPL_107";
	                	else if(formID.contains("108"))
	                		TempID ="LPL_108";
	                	else if(formID.contains("120"))
	                		TempID ="LPL_120";
	                	else if(formID.contains("121"))
	                		TempID ="LPL_121";
	                	else if(formID.contains("135"))
	                		TempID ="LPL_135";
	                	else if(formID.contains("117"))
	                		TempID ="LPL_117";
	                	else if(formID.contains("114"))
	                		TempID ="LPL_114";
	                	else if(formID.contains("132"))
	                		TempID ="LPL_132";
	                	else if(formID.contains("109"))
	                		TempID ="LPL_109";
	                	else if(formID.contains("delendrs"))
	                		TempID ="LPL_delendrs";
	                	else if(formID.contains("181"))
	                		TempID ="LPL_181";
	                	else if(formID.contains("182"))
	                		TempID ="LPL_182";
	                	
	                	xmlContent.append("<template_id_" + TempID +">" + TempID.trim() +"</template_id_" + TempID +">\n");
	                	
	            	}        	
	       	
	            }
        	}
        	if("3".equals(ctx.get("CompanyKey").toString()))
        	{
	            for(int i=0; i<listFormID.size(); i++)
	            {
	            	Map map = (Map) listFormID.get(i);
	            	String formID = map.get("FormID").toString();
	            	if(!"".equals(formID))
	            	{
	            		String TempID = "";
	            		if(formID.contains(" "))
	            			formID=formID.replace(" ","");
	            		if(formID.contains("-"))
	            			formID=formID.replace("-","");
	            		
	            		if(formID.contains("ALA04"))
	            			formID=formID.replace("ALA04","");
	            		
	            		if(formID.length()>16)
	            		{
		            		formID=formID.substring(0 ,5);
		                	TempID ="ALA_"+formID;
	            		}
	            		else
	            		{
	            			formID=formID.substring(0 ,4);
		                	TempID ="ALA_"+formID;
	            		}
	                	xmlContent.append("<template_id_" + TempID +">" + TempID.trim() +"</template_id_" + TempID +">\n");
	                	
	            	}        	
       	
	            }
        	}
            xmlContent.append("</templates>\n"); 
            xmlContent.append("</response>");

	    	convertPOToPDF(foFile, xmlContent, out, baseUrl, uriResolver);
    	}
    	catch (Exception e) {			
			logger.error("Unexpected error", e);
			logger.error("Exception convertXMLtoStream " + e.getMessage());
			throw e;
		}    	
    	
    }
    
    public void convertPOToPDF(String foFile, StringBuffer xmlData, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver)
	{
    	File fofile=null;
    	
		try{
			
			fofile = new File(foFile);
			//File pdffile = new File(outFile);			
		
			// configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();
			fopFactory.setURIResolver(uriResolver);
			
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			foUserAgent.setBaseURL(baseUrl);
			 
			// configure foUserAgent as desired
			// Setup output					
			//OutputStream out = new java.io.FileOutputStream(pdffile);
			//out = new java.io.BufferedOutputStream(out);		
			
			
			try {
				// Construct fop with desired output format
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
				
				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory.newTransformer(new StreamSource(fofile));
				
				// Set the value of a <param> in the stylesheet
				//transformer.setParameter("versionParam", "2.0");
				
				// Setup input for XSLT transformation
			
				Source src = new StreamSource(new ByteArrayInputStream(xmlData.toString().getBytes()));
				 
				// Resulting SAX events (the generated FO) must be piped through to FOP
				Result res = new SAXResult(fop.getDefaultHandler());
				
				// Start XSLT transformation and FOP processing
				transformer.transform(src, res);
				
			} catch (Exception e) {
				logger.error("Unexpected error", e);	
			} finally {			 
				out.close();
			}
			
			logger.debug("Success!");
		} 
		catch (Exception e) {
			 logger.error("Unexpected error", e);				 
			 //System.exit(-1);			  
		}
		finally{
			/*code by sukhi 26/09/2018*/
			fofile=null;
		}
	}
    
    public OutputStream convertPOToPDF(String foFile, String xmlFile,
			OutputStream out,ServletContextURIResolver uriResolver, ServletContext servletContext,Context ctx) throws Exception {
    	uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
    	
    	File xmlfile=null;
    	File fofile=null;
    	
    	try {
			xmlfile = new File(xmlFile);
			fofile = new File(foFile);

			//configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			foUserAgent
			.setBaseURL("file:///" + servletContext.getRealPath("/"));
			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent,
					out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			factory.setURIResolver(uriResolver);
			Transformer transformer = factory.newTransformer(new StreamSource(
					fofile));
			transformer.setURIResolver(uriResolver);
			// Set the value of a <param> in the stylesheet
			// transformer.setParameter("versionParam", "2.0");

			// Setup input for XSLT transformation
			Source src = new StreamSource(xmlfile);

			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			transformer.transform(src, res);

			logger.debug("Success!");
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			throw e;
		}
    	finally{
    		/*code by sukhi 26/09/2018*/
			fofile=null;
			xmlfile=null;
    	}
		return out;
	}

}
