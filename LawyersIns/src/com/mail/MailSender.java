/*
 * Amit Jain
 * Created on Sep 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mail;

import java.io.File;
import java.io.FileInputStream;
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
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;




// Send a simple, single part, text/plain e-mail
public class MailSender {

	private static InetLogger logger = InetLogger.getInetLogger(MailSender.class);

    public String toAdd = "";
    public String fromAdd = "";
    public String subject = "";
    public String body = "";
    public String contentType = "text/plain";
    public String ccAdd= "",bccAdd= "";
    public String pdfFile= "";
    public String name= "";
    public String isSentToCC= "";
    public String imgPath = "";
    public String imgPath1 = "";
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
   
    
    private static Properties properties;
    private static FileInputStream in;

    /**
	 * @return the pdfFile
	 */
	public String getPdfFile() {
		return pdfFile;
	}
	/**
	 * @param pdfFile the pdfFile to set
	 */
	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}
	/**
     * @return Returns the body.
     */
    public String getBody() {
        return body;
    }
    /**
     * @param body The body to set.
     */
    public void setBody(String body) {
        this.body = body;
    }
    /**
     * @return Returns the fromAdd.
     */
    public String getFromAdd() {
        return fromAdd;
    }
    /**
     * @param fromAdd The fromAdd to set.
     */
    public void setFromAdd(String fromAdd) {
        this.fromAdd = fromAdd;
    }
    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @param subject The subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * @return Returns the toAdd.
     */
    public String getToAdd() {
        return toAdd;
    }
    /**
     * @param toAdd The toAdd to set.
     */
    public void setToAdd(String toAdd) {
        this.toAdd = toAdd;
    }

    /**
	 * @return Returns the contentType.
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType The contentType to set.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
    
	/**
	 *  @return  Returns the ccAdd
	 */
	public String getCcAdd() {
		return ccAdd;
	}
	
	public void setCcAdd(String ccAdd) {
		this.ccAdd = ccAdd;
	}

	public String getBccAdd() {
		return bccAdd;
	}
	
	public void setBccAdd(String bccAdd) {
		this.bccAdd = bccAdd;
	}

	/**
	 * @return the isSentToCC
	 */
	public String getIsSentToCC() {
		return isSentToCC;
	}
	/**
	 * @param isSentToCC the isSentToCC to set
	 */
	public void setIsSentToCC(String isSentToCC) {
		this.isSentToCC = isSentToCC;
	}
	
	public void setToAddList(List<String> toAddList) {
		this.toAddList = toAddList;
	}
	
	public void setCcAddList(List<String> ccAddList) {
		this.ccAddList = ccAddList;
	}
	
	public void setAttachFilesList(List<String> attachFilesList) {
		this.attachFilesList = attachFilesList;
	}
	
	public void setAttachmentContent(String attachmentContent) {
		this.attachmentContent = attachmentContent;
	}
	
	public boolean sendMail(Context ctx) throws Exception
    {
        try {
            return sendMail();
        }
        catch (Exception mex)
        {
            if(MAIL_ERROR_NOTIFICATION_ADDRESS == null 
            		|| "".equals(MAIL_ERROR_NOTIFICATION_ADDRESS)
                    || MAIL_ERROR_NOTIFICATION_ADDRESS.equals(toAdd))
                return false;

            MailSender errorMail = new MailSender();
            errorMail.setToAdd(MAIL_ERROR_NOTIFICATION_ADDRESS);
            errorMail.setSubject("Mail error Notification");
            StringBuffer errorBody = new StringBuffer();
            errorBody.append("A failed attempt has been made to send mail because following exception \n\r ");
            errorBody.append("Exception :- \n\r ");
            errorBody.append(mex.getMessage());
            errorBody.append("\n\r\n\r\n\r ");
            errorBody.append("Original Subject : "+ subject +" \n\r ");
            errorBody.append("Original Content : "+ body +"  \n\r ");
            errorMail.setBody(errorBody.toString());
            errorMail.sendMail(ctx);
            
            return false;
        }
    }

    public boolean sendMail()
    {
        // Create properties, get Session
    	//setEmailProperties();
    	
    	File file=null;
    	FileDataSource fds=null;
    	DataSource ds=null;
    	
        Properties props = new Properties();
      
        props.put("mail.smtp.user", MAIL_ADMIN_USERID);
        props.put("mail.smtp.host", MAIL_HOST_SERVER);
        props.put("mail.host", MAIL_HOST_SERVER);
        props.put("mail.smtp.auth", MAIL_AUTHENTICATION_REQUIRED);
        props.put("mail.smtp.port", MAIL_PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", MAIL_DEBUG);
        if("465".equals(MAIL_PORT)) {
	        props.put("mail.smtp.socketFactory.port", MAIL_PORT);
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
        } else {
        	props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        	props.put("mail.smtp.ssl.trust", "*");
        }
        // To see what is going on behind the scene
        
      //get Session   
        Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator() {    
        protected PasswordAuthentication getPasswordAuthentication() {    
        return new PasswordAuthentication(MAIL_ADMIN_USERID, MAIL_ADMIN_PASSWORD);  
        }    
       });    

		try{
        // Instantiate a message
    	Message msg = new MimeMessage(session);

        if(fromAdd == null || "".equals(fromAdd))
            fromAdd = MAIL_ADMIN_ADDRESS;
        
        if(toAdd == null || "".equals(toAdd))
        	toAdd = MAIL_TO_ADDRESS;

        // Set message attributes
        msg.setFrom(new InternetAddress(fromAdd));
        
        // Destination Address
        //InternetAddress[] address = {new InternetAddress(toAdd)};          
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAdd));
        
        // CC Address
		/*
		 * if(ccAdd == null || ccAdd.equals("")) ccAdd = MAIL_CC_ADDRESS;
		 * 
		 * if(ccAdd != null && !"".equals(ccAdd)){
		 * 
		 * InternetAddress[] ccaddress = {new InternetAddress(ccAdd)};
		 * msg.setRecipients(Message.RecipientType.CC, ccaddress ); }
		 */   
        if(ccAdd != null && !"".equals(ccAdd)){       	
        	
        	InternetAddress[] ccaddress = {new InternetAddress(ccAdd)};
            msg.setRecipients(Message.RecipientType.CC, ccaddress );
        }
        if(bccAdd != null && !"".equals(bccAdd)){       	
        	
        	InternetAddress[] bccaddress = {new InternetAddress(bccAdd)};
            msg.setRecipients(Message.RecipientType.BCC, bccaddress );
        }    
        BodyPart bpart = new MimeBodyPart();
        bpart.setContent(body, "text/html");
        
        //bpart.setText(body);
        //File file = new File(getPdfFile());		
		//FileDataSource fds = new FileDataSource(file);
		
		//bpart.setDataHandler(new DataHandler(fds));
		//bpart.setFileName(getName());
		//bpart.setDisposition(Part.ATTACHMENT); 
        
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
				ds = new FileDataSource(getImgPath());
				bodyPart.setDataHandler(new DataHandler(ds));				
				bodyPart.setHeader("Content-Type", "image/jpeg;name=image.jpg");
				bodyPart.setHeader("Content-ID","<memememe>");	
				bodyPart.setHeader("Content-Disposition", "inline");
				mpart.addBodyPart(bodyPart);
			}
			
			if(imgPath1 != null && !"".equals(imgPath1))
			{
				bodyPart = new MimeBodyPart();
				ds = new FileDataSource(getImgPath1());
				bodyPart.setDataHandler(new DataHandler(ds));				
				bodyPart.setHeader("Content-Type", "image/jpeg;name=image.jpg");
				bodyPart.setHeader("Content-ID","<memememe1>");	
				bodyPart.setHeader("Content-Disposition", "inline");
				mpart.addBodyPart(bodyPart);
			}
		}
        
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        msg.setText(body);
        msg.setContent(mpart);
        
        logger.debug("Going to transport mail . . ");
        Transport.send(msg);
        session = null;
        msg = null;
        props = null;
        logger.debug("Mail Transported . . ");
        
		}catch(Exception e){
			logger.error("Error while sending mail: " + e.getMessage());
			return false;
		}
		finally{
			/*code by sukhi 26/09/2018*/
			ds=null;
			fds=null;
			file=null;
		}
		return true;
    }

    public boolean sendProducerMail()
    {
    	File file=null;
    	FileDataSource fds=null;
    	DataSource ds=null;
    	
        // Create properties, get Session
        Properties props = new Properties();
        //setEmailProperties();
        props.put("mail.smtp.user", MAIL_ADMIN_USERID);
        props.put("mail.smtp.host", MAIL_HOST_SERVER);
        props.put("mail.host", MAIL_HOST_SERVER);
        props.put("mail.smtp.auth", MAIL_AUTHENTICATION_REQUIRED);
        props.put("mail.smtp.port", MAIL_PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", MAIL_DEBUG);
        if("465".equals(MAIL_PORT)) {
	        props.put("mail.smtp.socketFactory.port", MAIL_PORT);
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
        } else {
        	props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        	props.put("mail.smtp.ssl.trust", "*");
        }
        // To see what is going on behind the scene
        
      //get Session   
        Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator() {    
        protected PasswordAuthentication getPasswordAuthentication() {    
        return new PasswordAuthentication(MAIL_ADMIN_USERID, MAIL_ADMIN_PASSWORD);  
        }    
       });    
        
        try{
        // Instantiate a message
    	Message msg = new MimeMessage(session);

        if(fromAdd == null || "".equals(fromAdd))
            fromAdd = MAIL_ADMIN_ADDRESS;
        
        if(toAdd == null || "".equals(toAdd))
        	toAdd = MAIL_TO_ADDRESS;

        // Set message attributes
        msg.setFrom(new InternetAddress(fromAdd));
        
        // Destination Address
        InternetAddress[] address = {new InternetAddress(toAdd)};          
        msg.setRecipients(Message.RecipientType.TO, address);
        
        // CC Address
        if(ccAdd == null || ccAdd.equals(""))
        	ccAdd = MAIL_CC_ADDRESS;
        
        if(ccAdd != null && !"".equals(ccAdd)){
        	String[] internetAdd = ccAdd.split(",");
        	 InternetAddress[] ccaddress = new InternetAddress[internetAdd.length];
        		for(int i = 0 ; i < internetAdd.length ; i++){
        			ccaddress[i] = new InternetAddress(internetAdd[i]);        			
        	}
        		msg.setRecipients(Message.RecipientType.CC, ccaddress );
        }     
        
        BodyPart bpart = new MimeBodyPart();
        bpart.setContent(body, "text/html");
        
        //bpart.setText(body);
        //File file = new File(getPdfFile());		
		//FileDataSource fds = new FileDataSource(file);
		
		//bpart.setDataHandler(new DataHandler(fds));
		//bpart.setFileName(getName());
		//bpart.setDisposition(Part.ATTACHMENT); 
        
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
				ds = new FileDataSource(getImgPath());
				bodyPart.setDataHandler(new DataHandler(ds));				
				bodyPart.setHeader("Content-Type", "image/jpeg;name=image.jpg");
				bodyPart.setHeader("Content-ID","<memememe>");	
				bodyPart.setHeader("Content-Disposition", "inline");
				mpart.addBodyPart(bodyPart);
			}			
		}
        
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        msg.setText(body);
        msg.setContent(mpart);
        logger.debug("Going to transport mail . . ");
        Transport.send(msg);
        session = null;
        msg = null;
        props = null;

        logger.debug("Mail Transported . . ");
        }catch(Exception ex){
        	logger.error("Error in mail::::::"+ex.getMessage());
        	return false;
        }
        finally{
			/*code by sukhi 26/09/2018*/
			ds=null;
			fds=null;
			file=null;
		}
        return true;
    }
    
  static
    {
        try {
//        	PropertiesLoader.loadProperties();
        	MAIL_HOST_SERVER = SystemProperties.getInstance().getString("mail.host.server");
            MAIL_DEBUG = SystemProperties.getInstance().getString("mail.debug");
            MAIL_PORT = SystemProperties.getInstance().getString("mail.port");
            MAIL_ADMIN_ADDRESS = SystemProperties.getInstance().getString("mail.admin.from.address");
            MAIL_ADMIN_USERID = SystemProperties.getInstance().getString("mail.admin.userid");
            MAIL_ADMIN_PASSWORD = SystemProperties.getInstance().getString("mail.admin.password");
           
            MAIL_AUTHENTICATION_REQUIRED = SystemProperties.getInstance().getString("mail.authetication.required");
            MAIL_ERROR_NOTIFICATION_ADDRESS = SystemProperties.getInstance().getString("mail.admin.address");
            MAIL_TO_ADDRESS = SystemProperties.getInstance().getString("mail.admin.to.address");
            MAIL_CC_ADDRESS = SystemProperties.getInstance().getString("mail.admin.cc.address");
        } catch (Exception e) {
            logger.error(" <*|*> Not able to initialize Mail Properties, Exception : " + e.getMessage());
        }
    }
  
    /*public  void setEmailProperties()
    {
        try {
        	PropertiesLoader.loadProperties();
        	MAIL_HOST_SERVER = SystemProperties.getInstance().getString("mail.host.server");
            MAIL_DEBUG = SystemProperties.getInstance().getString("mail.debug");
            MAIL_ADMIN_ADDRESS = SystemProperties.getInstance().getString("mail.admin.from.address");
            MAIL_ADMIN_USERID = SystemProperties.getInstance().getString("mail.admin.userid");
            MAIL_ADMIN_PASSWORD = SystemProperties.getInstance().getString("mail.admin.password");
           
            MAIL_AUTHENTICATION_REQUIRED = SystemProperties.getInstance().getString("mail.authetication.required");
            MAIL_ERROR_NOTIFICATION_ADDRESS = SystemProperties.getInstance().getString("mail.admin.address");
            MAIL_TO_ADDRESS = SystemProperties.getInstance().getString("mail.admin.to.address");
            MAIL_CC_ADDRESS = SystemProperties.getInstance().getString("mail.admin.cc.address");
        } catch (Exception e) {
            System.err.println(" <*|*> Not able to initialize Mail Properties, Exception : " + e.getMessage());
        }

    }*/
	
  public static void main(String args[]) throws Exception
  {
  	MailSender mail = new MailSender();
		mail.setToAdd("vikask@outlinesys.com");
		//mail.setCcAdd("amitj@outlinesys.com"); 
		mail.setSubject("Test mail");
		mail.setBody("Test Mail,." );
		mail.setName("quoteletter.pdf");
		//mail.setPdfFile("C:\\Tomcat\\webapps\\AccountantIns\\data\\quoteletter.pdf");
		//mail.sendMail();
  }


  /**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */
	/*private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = MAIL_ADMIN_USERID;
			String password = MAIL_ADMIN_PASSWORD;
			
			return new PasswordAuthentication(username, password);
		}
	}*/


/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
public String getImgPath() {
	return imgPath;
}
/**
 * @param imgPath the imgPath to set
 */
public void setImgPath(String imgPath) {
	this.imgPath = imgPath;
}


public String getImgPath1() {
	return imgPath1;
}
/**
 * @param imgPath1 the imgPath1 to set
 */
public void setImgPath1(String imgPath1) {
	this.imgPath1 = imgPath1;
}

/**
 * @return the attachments
 */
public List getAttachments() {
	return attachments;
}
/**
 * @param attachments the attachments to set
 */
public void setAttachments(List attachments) {
	this.attachments = attachments;
}

public void send(String smtpHost,final String from,final String password,String to,String ccAdd,String sub,String msg,String displayID,String displayName,Multipart mpart){  
    //Get properties object    
    Properties props = new Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.host", smtpHost);
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
    
    //get Session   
    Session session = Session.getDefaultInstance(props,    
     new javax.mail.Authenticator() {    
     protected PasswordAuthentication getPasswordAuthentication() {    
     return new PasswordAuthentication(from,password);  
     }    
    });    
    
    //compose message    
    try {    
     MimeMessage message = new MimeMessage(session);    
     
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     
     InternetAddress me = new InternetAddress(displayID);
//     me.setPersonal(displayName);
     
     InternetAddress ccaddress = new InternetAddress(ccAdd);
     message.addRecipient(Message.RecipientType.CC, ccaddress );
     
     message.setFrom(me);
     message.setSubject(sub);   
     message.setSentDate(new Date()); 
     message.setText(msg);    
     message.setContent(mpart);
     //send message  
     Transport.send(message);    
     logger.debug("message sent successfully");    
    } catch (Exception e){
		// TODO Auto-generated catch block
		logger.error("Unexpected error", e);
	}    
       
}  

public  boolean sendMailtoSub(IContext ctx) throws Exception
{
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
    		
        	File file = new File(filePath);		
    		FileDataSource fds = new FileDataSource(file);
    		
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
			DataSource fds = new FileDataSource(getImgPath());
			bodyPart.setDataHandler(new DataHandler(fds));				
			bodyPart.setHeader("Content-Type", "image/jpeg;name=image.jpg");
			bodyPart.setHeader("Content-ID","<memememe>");	
			bodyPart.setHeader("Content-Disposition", "inline");
			mpart.addBodyPart(bodyPart);
		}			
	}
	String ccAdd = "";
	
	String smtpHost =SystemProperties.getInstance().getString("mail.hostsub.server");
	String from = SystemProperties.getInstance().getString("mail.adminsub.userid");
	String password = SystemProperties.getInstance().getString("mail.adminsub.password");
	String displayID = LawyersUtils.getProducerEmail(ctx);
	String displayName = ctx.get("ProducerName").toString(); 
	String to = LawyersUtils.getEmailID(ctx);
	
	String productionEnv = "Y";
	try {
		productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
	} 
	catch (Exception e) 
	{
		logger.error("error in geeting production environment");
	}
	if ("N".equals(productionEnv))
		ccAdd=SystemProperties.getInstance().getString("mail.admin.cc.address");
	else 
		ccAdd = SystemProperties.getInstance().getString("mail.adminsub.cc.address");

	send(smtpHost, from,password,to,ccAdd,subject,body,displayID,displayName,mpart);
    return true;
}


}
