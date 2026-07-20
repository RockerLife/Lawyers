package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.userbo.LawyersUtils;


public class SubproducerMailer {  

	private static InetLogger logger = InetLogger.getInetLogger(SubproducerMailer.class);	

    public String toAdd = "";
    public String fromAdd = "";
    public String subject = "";
    public String body = "";
    public String contentType = "text/plain";
    public String ccAdd= "";
    public String bccAdd= "";
    public String pdfFile= "";
    public String name= "";
    public String isSentToCC= "";
    public String imgPath = "";
    public List attachments;
    protected List<String> toAddList;
	protected List<String> ccAddList;
	protected List<String> bccAddList;
	protected List<String> attachFilesList;
	protected String attachmentContent;
	
    private static String MAIL_HOST_SERVER;
    private static String MAIL_DEBUG;
    private static String MAIL_PORT;
    private static String MAIL_ADMIN_ADDRESS;
    private static String MAIL_ADMIN_USERID;
    private static String MAIL_ADMIN_PASSWORD;
    private static String MAIL_AUTHENTICATION_REQUIRED;
    private static String MAIL_ERROR_NOTIFICATION_ADDRESS;
    private static String MAIL_TO_ADDRESS;
    private static String MAIL_CC_ADDRESS;  
    private static String MAIL_BCC_ADDRESS;  
    private static Properties properties;
    private static FileInputStream in;	
	
	
        public static void send(Context ctx){   
        	//Get properties object 
      	  Properties props = new Properties();         
            
            props.put("mail.smtp.user", MAIL_ADMIN_USERID);
            props.put("mail.smtp.host", MAIL_HOST_SERVER);
            props.put("mail.host", MAIL_HOST_SERVER);
            props.put("mail.smtp.auth", MAIL_AUTHENTICATION_REQUIRED);
            props.put("mail.smtp.port", MAIL_PORT);
            props.put("mail.smtp.starttls.enable", "true");
            if("465".equals(MAIL_PORT)) {
    	        props.put("mail.smtp.socketFactory.port", MAIL_PORT);
    	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	        props.put("mail.smtp.socketFactory.fallback", "false");
            } else {
            	props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            	props.put("mail.smtp.ssl.trust", "*");
            }
            
          	Multipart mpart=(Multipart)ctx.get("mpart");
          	String toAddress=ctx.get("toAddress").toString();
          	String bccAdd=ctx.get("bccAddress").toString();
          	String subject=ctx.get("subject").toString();
          	String emailMessage=ctx.get("body").toString();
          	String displayID=ctx.get("displayID").toString();
          	String displayName=ctx.get("displayName").toString();
          	
    		try {
    			if ("Y".equals(SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production"))){
    				if(!"law.mail@protexure.com".equalsIgnoreCase(bccAdd)){
    					bccAdd=bccAdd+",law.mail@protexure.com";
    				}
    			}
    		} catch (Exception e)  {
    			logger.error("error in geeting production environment");
    		}
    		logger.debug("toAddress:::"+toAddress+"::::ccAdd::::"+bccAdd+":::displayID:::"+displayID+"::::displayName:::"+displayName);
          	Session session = Session.getInstance(props,  new javax.mail.Authenticator() {    
                  protected PasswordAuthentication getPasswordAuthentication() {    
                  return new PasswordAuthentication(MAIL_ADMIN_USERID, MAIL_ADMIN_PASSWORD);  
                  }    
                 });
               
              //Prepare message    
              try
              {    
                   MimeMessage message = new MimeMessage(session);
                   
                   message.setFrom(new InternetAddress(displayID, displayName));             
                   
                   message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));  
                   message.addRecipients(Message.RecipientType.BCC,  InternetAddress.parse(bccAdd));
                   message.setSubject(subject);   
                   message.setSentDate(new Date()); 
                   message.setText(emailMessage);    
                   message.setContent(mpart);
                   logger.debug("going to send subproducer email");
                   Transport.send(message);    
                   session = null;
                   message = null;
                   props = null;
                   logger.debug("message sent successfully");    
              } 
              catch (Exception e)
              {
          		// TODO Auto-generated catch block
            	  logger.error("error in SubproducerMailer method - "+e.getMessage()); 
          		logger.error("Unexpected error", e);
      			try{
          		
  					StringBuffer errorBody = new StringBuffer();
  					errorBody.append("A failed attempt has been made to send mail because following exception \n\r ");
  					errorBody.append("Exception :- \n\r ");
  					errorBody.append(e.getMessage());
  					errorBody.append("\n\r\n\r\n\r ");
  					errorBody.append("Original Subject : "+ subject +" \n\r ");
  					errorBody.append("Original Content : "+ emailMessage +"  \n\r ");
  					
  					MimeMessage message = new MimeMessage(session);    
					message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(MAIL_ERROR_NOTIFICATION_ADDRESS));  
					message.setFrom(new InternetAddress(MAIL_ADMIN_ADDRESS));
					message.setSubject("Mail error Notification");   
					message.setSentDate(new Date()); 
					message.setText(errorBody.toString());    
					message.setContent(mpart);
					logger.debug("going to send subproducer error email");
					Transport.send(message);    
					session = null;
					message = null;
					props = null;
				}catch(Exception e1){
					logger.error("Unexpected error", e1);
				}
          	}  
               
        }  
     
        public static void sendEmailAsSubProducer(Context oldCtx) {  
        	
        	DataSource ds1 = null;
        	DataSource ds2 = null;
        	DataSource ds3 = null;
        	File file = null;
        	FileDataSource fds = null;
        	
        	try
        	{
        		logger.debug("preparing subproducer email content");
        		Context ctx=new Context();
    			ctx.setProject("LawyersIns");
            	ArrayList attachments= oldCtx.get("emailAttachment")==null?null:(ArrayList)oldCtx.get("emailAttachment");
            	
            	/*String from=SystemProperties.getInstance().getString("mail.adminsub.userid");
        		String password=SystemProperties.getInstance().getString("mail.adminsub.password");
        		String displayID=oldCtx.get("Producer_email")!=null?oldCtx.get("Producer_email").toString():SystemProperties.getInstance().getString("mail.admin.cc.address");
            	String displayName=	oldCtx.get("ProducerName")!=null?oldCtx.get("ProducerName").toString():"Test";	
            	String toAddress=oldCtx.get("toAddress")!=null?oldCtx.get("toAddress").toString():SystemProperties.getInstance().getString("mail.admin.cc.address");
            	String ccAddress=oldCtx.get("ccAddress")!=null?oldCtx.get("ccAddress").toString():SystemProperties.getInstance().getString("mail.admin.cc.address");
            	String subject=oldCtx.get("subject")!=null?oldCtx.get("subject").toString():"Testing";
            	String body=oldCtx.get("body")!=null?oldCtx.get("body").toString():"Test";        	
            	String from=SystemProperties.getInstance().getString("mail.adminsub.userid");
        		String password=SystemProperties.getInstance().getString("mail.adminsub.password");
        		ctx.put("smtpHost",SystemProperties.getInstance().getString("mail.hostsub.server"));
        		*/
            	
        		String displayID=LawyersUtils.getProducerdisplayEmail(oldCtx);
            	String displayName=oldCtx.get("ProducerName") != null?oldCtx.get("ProducerName").toString() : "Test";	
            	String toAddress=oldCtx.get("toAddress") != null ? oldCtx.get("toAddress").toString() : MAIL_TO_ADDRESS;
            	String bccAddress=oldCtx.get("bccAddress") != null ? oldCtx.get("bccAddress").toString() : MAIL_BCC_ADDRESS;
            	String subject=oldCtx.get("subject") != null ? oldCtx.get("subject").toString() : "Testing";
            	String body=oldCtx.get("body") != null ? oldCtx.get("body").toString() : "Test";
            	
            	ctx.put("toAddress",toAddress);
            	ctx.put("bccAddress",bccAddress);
            	ctx.put("subject", subject);
            	ctx.put("body", body);
            	ctx.put("displayID",displayID);
        		ctx.put("displayName",displayName); 
        		
        		String contentType = "text/html";
	        	String imgPath="";
	        	String imgPath1="";
	        	String imgPath2="";
	        	String path=oldCtx.get("Signature")!=null?oldCtx.get("Signature").toString():"";
	        	logger.debug("preparing subproducer image data");
	        	
	        	String subProducerCode = SystemProperties.getInstance().getString("appl.LawyersIns.subproducer.signaturedisplay");
		     	String[] subProducerCodeList = subProducerCode.split("~");
		     	for(int subProducerCodeCount = 0; subProducerCodeCount < subProducerCodeList.length; subProducerCodeCount++){
		     		if(subProducerCodeList[subProducerCodeCount].equalsIgnoreCase(oldCtx.get("ProducerCode").toString())){
		     			path = "";
		     		}
		     	}
	        	if(!"".equals(path))
	        	 imgPath=new DocumentGenerationBO().imageDownload(path);
	        	 
	        	 
	        	 if(oldCtx.get("EmailSignature")!=null && !"".equals(oldCtx.get("EmailSignature"))){
	        	 imgPath1=SystemProperties.getInstance().getString("appl.LawyersIns.subproducerlogopath");
	        	 imgPath1=imgPath1+"subproducerlogo/"+oldCtx.get("EmailSignature");
	        	 }
	        	 if(oldCtx.get("SignatureText3")!=null && !"".equals(oldCtx.get("SignatureText3"))){
		        	 imgPath2=SystemProperties.getInstance().getString("appl.LawyersIns.subproducerlogopath");
		        	 imgPath2=imgPath2+"subproducerlogo/"+oldCtx.get("SignatureText3");
		        	 }
	        	 
	        	 BodyPart bpart = new MimeBodyPart();
	        	    bpart.setContent(body, "text/html");
	        	    
	        	    Multipart mpart = new MimeMultipart("mixed");        
	        	    BodyPart attachPart = null;
	
	        	    if(attachments != null && attachments.size()>0)
	        		{
	        	    	for(int i=0; i<attachments.size(); i++)
	        	    	{
	        	    		String filePath = attachments.get(i).toString();
	        	    		String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
	        	    		file = new File(filePath);		
	        	    		fds = new FileDataSource(file);
	        	    		attachPart = new MimeBodyPart();
	        	    		attachPart.setDataHandler(new DataHandler(fds));	    		
	        	    		attachPart.setFileName(fileName);
	        	    		attachPart.setDisposition(Part.ATTACHMENT);     		
	        	            mpart.addBodyPart(attachPart);	
	        	            	
	        	    	}
	        		}
	        		
	        		if(body != null )
	        		{
	        			BodyPart bodyPart = new MimeBodyPart();
	        			if(contentType != null && !"".equals(contentType))		    
	        				bodyPart.setContent(body,contentType);
	        			else
	        				bodyPart.setContent(body,"text/plain");
	        			mpart.addBodyPart(bodyPart);
	        			if(imgPath != null && !"".equals(imgPath))
	        			{
	        				bodyPart = new MimeBodyPart();
	        				ds1 = new FileDataSource(imgPath);
	        				bodyPart.setDataHandler(new DataHandler(ds1));
	        				bodyPart.setHeader("Content-ID","<image>");	
	        				bodyPart.setHeader("Content-Disposition", "inline");
	        				mpart.addBodyPart(bodyPart);
	        				
	        			}	
	        			if(imgPath1 != null && !"".equals(imgPath1))
	        			{
	        				bodyPart = new MimeBodyPart();
	        				ds2 = new FileDataSource(imgPath1);
	        				bodyPart.setDataHandler(new DataHandler(ds2));
	        				bodyPart.setHeader("Content-ID","<image1>");	
	        				bodyPart.setHeader("Content-Disposition", "inline");
	        				mpart.addBodyPart(bodyPart);
	        				
	        			}	
	        			
	        			if(imgPath2 != null && !"".equals(imgPath2))
	        			{
	        				bodyPart = new MimeBodyPart();
	        				ds3 = new FileDataSource(imgPath2);
	        				bodyPart.setDataHandler(new DataHandler(ds3));
	        				bodyPart.setHeader("Content-ID","<image2>");	
	        				bodyPart.setHeader("Content-Disposition", "inline");
	        				mpart.addBodyPart(bodyPart);
	        				
	        			}	
	        			
	        		}
	        		
	        		ctx.put("mpart", mpart);	        		
	        		send(ctx);
	        		
		        	}catch(Exception e) {
		        		logger.error("error occured while preparing subproducer email content :"+e);
		        		logger.error("Unexpected error", e);
		        	}finally {
		        		/*code by sukhi 26/09/2018*/
        				fds = null;
        				ds1 = null;
        				ds2 = null;
        				ds3 = null;
        				file = null;
		        	}
		            
} 
   
        static
        {
            try {
//            	PropertiesLoader.loadProperties();
            	MAIL_HOST_SERVER = SystemProperties.getInstance().getString("mail.hostsub.server");
                MAIL_DEBUG = SystemProperties.getInstance().getString("mail.sub.debug");
                MAIL_PORT = SystemProperties.getInstance().getString("mail.sub.port");
                MAIL_ADMIN_ADDRESS = SystemProperties.getInstance().getString("mail.adminsub.from.address");
                MAIL_ADMIN_USERID =SystemProperties.getInstance().getString("mail.adminsub.userid");
                MAIL_ADMIN_PASSWORD = SystemProperties.getInstance().getString("mail.adminsub.password");        	
                
                MAIL_AUTHENTICATION_REQUIRED = SystemProperties.getInstance().getString("mail.sub.authetication.required");
                MAIL_ERROR_NOTIFICATION_ADDRESS = SystemProperties.getInstance().getString("mail.adminsub.address");
                MAIL_TO_ADDRESS = SystemProperties.getInstance().getString("mail.adminsub.to.address");
                MAIL_CC_ADDRESS = SystemProperties.getInstance().getString("mail.adminsub.cc.address");
                MAIL_BCC_ADDRESS = SystemProperties.getInstance().getString("mail.adminsub.cc.address");
            } catch (Exception e) {
                logger.error(" <*|*> Not able to initialize Mail Properties, Exception : " + e.getMessage());
            }
        }      
        
}
