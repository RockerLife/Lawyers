package com.manage.process;

import com.util.InetLogger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;

import com.util.Context;
import com.util.SystemProperties;

/**
 * Servlet implementation class ZohoServlet
 */
public class ZohoAccessTokenServlet extends HttpServlet {
	private static final InetLogger logger = InetLogger.getInetLogger(ZohoAccessTokenServlet.class);
	private static final long serialVersionUID = 1L;
	private ZohoTimer zohoTimer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZohoAccessTokenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		generateZohoCode(request, response);
	}
	
	public void generateZohoCode(HttpServletRequest request, HttpServletResponse response){
		
//		GetMethod get = null;
		try{
			String code = String.valueOf(request.getParameter("code"));
			boolean erorr = generateZohoAccessToken(code);
			PrintWriter pwr = response.getWriter();
			if(erorr){
				pwr.print("Token is invalid.");
			} else {
				pwr.print("Access Token Created Successfully.");
			}
			
//			String authCodeURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.authcodeurl");
//			String scope = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.auth.scope");
//			String client_id = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.clientid");
//			String response_type = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.auth.responsetype");
//			String access_type = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.auth.accesstype");
//			String prompt = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.auth.prompt");
//			String redirect_uri = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.redirecturl");
//			
//			//----------------------------Fetch ZOHO Data ----------------------
//			
//			authCodeURL += "?scope=" + scope + "&client_id=" + client_id + "&response_type=" + response_type + "&access_type=" + access_type 
//					+ "&prompt=" + prompt + "&redirect_uri=" + redirect_uri;
//  			get = new GetMethod(authCodeURL);
//			
//			HttpClient httpclient = new HttpClient();
//			int result = httpclient.executeMethod(get);
//			
//			System.out.println(result);
//			System.out.println("status     " + get.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Unexpected error", e);
//		} finally {
//			get.releaseConnection();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean generateZohoAccessToken(String code) {
    	PostMethod post = null;
    	try{

    		String refreshToken = "";
    		boolean erorr = false;
	    	String accessTokenURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.accesstokenurl");
			String redirect_uri = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.redirecturl");
			String client_id = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.clientid");
			String client_secret = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.clientsecret");
			String grant_type = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.auth.granttype");
			String username = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.username");
			String password = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.password");
			String refreshtime = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.refreshtime");
			
			//----------------------------Fetch ZOHO Data ----------------------
			
			post = new PostMethod(accessTokenURL);
			post.setParameter("code", code);
			post.setParameter("redirect_uri",redirect_uri);
			post.setParameter("client_id",client_id);
			post.setParameter("client_secret",client_secret);
			post.setParameter("grant_type",grant_type);
			post.setParameter("username", username);
			post.setParameter("password", password);
			
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(post);
			
			String responseBody = post.getResponseBodyAsString();
			JSONObject captureZohoAccessToken = new JSONObject(responseBody);
			Iterator<String> captureKeys = captureZohoAccessToken.keys();
			ServletContext context=getServletContext();
			while (captureKeys.hasNext()) {
				String captureKey = (String)captureKeys.next();
		        String captureKeyValue = captureZohoAccessToken.getString(captureKey);
		        
		        if("access_token".equals(captureKey)){
		        	context.setAttribute("ZohoAccessToken", captureKeyValue);
		        } else if("refresh_token".equals(captureKey)){
		        	refreshToken =  captureKeyValue;
		        } else if("error".equals(captureKey)){
		        	erorr = true;
		        }
			}
			if(!"".equals(refreshToken)){
				startZohoTimer(refreshToken, Long.parseLong(refreshtime)*60000L);
			}
			
			return erorr;
    	} catch(Exception e){
    		logger.error("Unexpected error", e);
    	} finally {
			if(post != null)
				post.releaseConnection();
		}
    	return true;
	}

	private synchronized void startZohoTimer(String refreshToken, long refreshIntervalMs){
		if(zohoTimer != null)
			zohoTimer.cancel();
		zohoTimer = new ZohoTimer();
		zohoTimer.setRefreshToken(refreshToken);
		zohoTimer.setContext(getServletContext());
		zohoTimer.createZohoTimer(refreshIntervalMs);
	}

	@Override
	public synchronized void destroy(){
		if(zohoTimer != null){
			zohoTimer.cancel();
			zohoTimer = null;
		}
		super.destroy();
	}
	
}

class ZohoTimer {
	private static final InetLogger logger = InetLogger.getInetLogger(ZohoTimer.class);
	private Timer timer;
    String refreshToken = "";
    ServletContext context = null;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
    public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void createZohoTimer(long refreshIntervalMs) {
		if(refreshIntervalMs <= 0)
			throw new IllegalArgumentException("Zoho refresh interval must be greater than zero.");
		cancel();
		timer = new Timer("ZohoAccessTokenRefresh", true);
		timer.scheduleAtFixedRate(new RemindTask(), refreshIntervalMs, refreshIntervalMs);
    }

	public void cancel(){
		if(timer != null){
			timer.cancel();
			timer.purge();
			timer = null;
		}
	}

	boolean isRunning(){
		return timer != null;
	}

    class RemindTask extends TimerTask {
        public void run() {
            generateZohoRefreshAccessToken();
        }
    }
	
	@SuppressWarnings("unchecked")
	public void generateZohoRefreshAccessToken() {
		PostMethod post = null;
    	try{
			String accessTokenURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.accesstokenurl");
			String client_id = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.clientid");
			String client_secret = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.clientsecret");
			String grant_type = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.refresh.granttype");
			String username = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.username");
			String password = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.password");
			
			//----------------------------Fetch ZOHO Data ----------------------
			
			post = new PostMethod(accessTokenURL);
			post.setParameter("refresh_token", getRefreshToken());
			post.setParameter("client_id",client_id);
			post.setParameter("client_secret",client_secret);
			post.setParameter("grant_type",grant_type);
			post.setParameter("username", username);
			post.setParameter("password", password);
			
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(post);
			
			String responseBody = post.getResponseBodyAsString();
			JSONObject captureZohoAccessToken = new JSONObject(responseBody);
			Iterator<String> captureKeys = captureZohoAccessToken.keys();
			while (captureKeys.hasNext()) {
				String captureKey = (String)captureKeys.next();
		        String captureKeyValue = captureZohoAccessToken.getString(captureKey);
		        
		        if("access_token".equals(captureKey)){
		        	getContext().setAttribute("ZohoAccessToken", captureKeyValue);
		        	updateZohoAccessToken(captureKeyValue);
		        }
			}
    	} catch(Exception e){
    		logger.error("Unexpected error", e);
    	} finally {
			if(post != null)
				post.releaseConnection();
		}
	}
	
	public void updateZohoAccessToken(String captureKeyValue) throws Exception{
		Context ctx = new Context();
		String project = "LawyersIns";
		ctx.setProject(project);
		ctx.put("LastUpdateTimeStamp", new Timestamp(new Date().getTime()));
		ctx.put("isZohoAccessToken", captureKeyValue);
		com.userbo.LawyersUtils.updateZohoToken(ctx);
	}
}
