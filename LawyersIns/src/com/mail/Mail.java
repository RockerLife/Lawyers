/*
 * Amit Jain
 * Created on Sep 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mail;

import com.util.InetLogger;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.util.Context;
import com.util.SystemProperties;

 

// Send a simple, single part, text/plain e-mail
public class Mail {
	private static final InetLogger logger = InetLogger.getInetLogger(Mail.class);


    public String toAdd = "";
    public String fromAdd = "";
    public String subject = "";
    public String body = "";
    public String contentType = "text/plain";
    public String ccAdd= "";

    private static String MAIL_HOST_SERVER;
    private static String MAIL_DEBUG;
    private static String MAIL_ADMIN_ADDRESS;
    private static String MAIL_ADMIN_USERID;
    private static String MAIL_ADMIN_PASSWORD;
    private static String MAIL_AUTHENTICATION_REQUIRED;
    private static String MAIL_ERROR_NOTIFICATION_ADDRESS;



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

    public boolean sendMail(Context ctx) throws Exception
    {

        try {
            sendMail();
            return true;
        }
        catch (Exception mex)
        {
            if(MAIL_ERROR_NOTIFICATION_ADDRESS == null
                    || "".equals(MAIL_ERROR_NOTIFICATION_ADDRESS)
                    || MAIL_ERROR_NOTIFICATION_ADDRESS.equals(toAdd))
                return false;

            Mail errorMail = new Mail();
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

//            mex.printStackTrace();
            return false;
        }
    }


    private boolean sendMail() throws Exception
    {

        // Create properties, get Session
        Properties props = new Properties();

        // If using static Transport.send(),
        // need to specify which host to send it to
        props.put("mail.smtp.host", MAIL_HOST_SERVER);
        props.put("mail.host", MAIL_HOST_SERVER);
        props.put("mail.smtp.auth", MAIL_AUTHENTICATION_REQUIRED);

        // To see what is going on behind the scene
        props.put("mail.debug", MAIL_DEBUG);

        Session session = Session.getDefaultInstance(props);

            // Instantiatee a message
        	Message msg = new MimeMessage(session);

            if(fromAdd == null || "".equals(fromAdd))
                fromAdd = MAIL_ADMIN_ADDRESS;

          // Set message attributes
            msg.setFrom(new InternetAddress(fromAdd));
            InternetAddress[] address = {new InternetAddress(toAdd)};
          
            msg.setRecipients(Message.RecipientType.TO, address);
            InternetAddress[] ccaddress = {new InternetAddress(ccAdd)};
            msg.setRecipients(Message.RecipientType.CC, ccaddress );
            
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            //msg.setText(body);
            msg.setContent(body, getContentType());

            //Send the message
//            Transport.send(msg);

            InternetAddress[] fromAddress = {new InternetAddress(MAIL_ADMIN_ADDRESS)};
            //An abstract class that models a message transport
            Transport oTransport = session.getTransport(fromAddress[0]);
            oTransport.connect(MAIL_HOST_SERVER, MAIL_ADMIN_USERID, MAIL_ADMIN_PASSWORD);
            //To send the recipients in TO
            oTransport.sendMessage(msg, address);


//            System.out.println("------------------------------------------------------------------");

            return true;
    }

    static
    {
        try {
            MAIL_HOST_SERVER = SystemProperties.getInstance().getString("mail.host.server");
            MAIL_DEBUG = SystemProperties.getInstance().getString("mail.debug");
            MAIL_ADMIN_ADDRESS = SystemProperties.getInstance().getString("mail.admin.address");
            MAIL_ADMIN_USERID = SystemProperties.getInstance().getString("mail.admin.userid");
            MAIL_ADMIN_PASSWORD = SystemProperties.getInstance().getString("mail.admin.password");
            MAIL_AUTHENTICATION_REQUIRED = SystemProperties.getInstance().getString("mail.authetication.required");
            MAIL_ERROR_NOTIFICATION_ADDRESS = SystemProperties.getInstance().getString("mail.error.notification.address");

        } catch (Exception e) {
            logger.error(" <*|*> Not able to initialize Mail Properties, Exception : " + e.getMessage());
        }

    }

	




}
