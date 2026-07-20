package com.manage.process;


import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.fop.servlet.ServletContextURIResolver;
import org.apache.log4j.PropertyConfigurator;
import org.apache.jcs.engine.control.CompositeCacheManager;

import com.manage.managecomponent.processflow.PagecomponentImpl;
import com.manage.managecomponent.processflow.ProcessFlowResources;
import com.manage.managemetadata.functions.commonfunctions.DataUtils;
import com.manage.managemetadata.util.LookupResourceLoader;
import com.manage.managereporting.ReportingConstants;
import com.manage.process.reporting.ReportProcessor;
import com.manage.util.ErrorUtils;
import com.manage.util.PageUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.CacheManager;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IOUtils;
import com.util.InetLogger;
import com.util.LoadXML;
import com.util.ResourceLoader;
import com.util.SystemProperties;

public abstract class ComponentProcessServlet extends HttpServlet{
	private static InetLogger logger = InetLogger.getInetLogger(ComponentProcessServlet.class);
	private static int requestCount = 0;
	protected ServletContextURIResolver uriResolver;
	protected ServletContext servletContext;
	protected PIMAjaxProcessorInterface ajaxProcessorInterface;
	protected PIMRequestProcessorInterface requestProcessorInterface;
	public String ERROR_FORWARD_URL = "appl.error.forward.url";
	//private String SSO_FORWARD_URL = "sso.logout.url";
	public String ZohoAccessToken = "";
	
	public void init() throws ServletException{
		String project_resource = getServletConfig().getInitParameter(Constants.INET_PROJECT_ID);
		if(project_resource == null || project_resource.trim().length() == 0)
			throw new ServletException("Project name is not defined in web.xml");

		try {
			SystemProperties.setPropertyFileName(getServletConfig().getInitParameter(Constants.APP_PROPERTIES));
			String encProps = initLocalResources();
			SystemProperties.setEncryptedProperties(encProps);
			
			//Change log properties from server properties file instead of log4j.properties located at src folder
			PropertyConfigurator.configure(getServletConfig().getInitParameter(Constants.APP_PROPERTIES));
			String realPath = this.getServletContext().getRealPath("/");
			Configuration conf = SystemProperties.getInstance(getServletContext());
			
			logger.debug("At the Startup set the Application Path to : "+ realPath);
			
			conf.setProperty("appl.home.dir", realPath);			
			
			loadProjects(project_resource);

			uriResolver = new ServletContextURIResolver(getServletContext());
			servletContext = getServletContext();

			ajaxProcessorInterface = new PIMAjaxProcessorImpl();
			requestProcessorInterface = new PIMRequestProcessorImpl();

			if(getServletConfig().getInitParameter("ajaxOverrideClass") != null){
				String className = getServletConfig().getInitParameter("ajaxOverrideClass");
				try{
					Class claz = Class.forName(className);
					ajaxProcessorInterface = (PIMAjaxProcessorInterface)claz.newInstance();
				}catch(ClassNotFoundException e) {
					logger.info("PIMAjaxProcessorInterface does not found any implementation class.");
				}
			}
			if(getServletConfig().getInitParameter("requestOverrideClass") != null){
				String className = getServletConfig().getInitParameter("requestOverrideClass");
				try{
					Class claz = Class.forName(className);
					requestProcessorInterface = (PIMRequestProcessorInterface)claz.newInstance();
				}catch(ClassNotFoundException e) {
					logger.info("PIMRequestProcessorInterface does not found any implementation class.");
				}
			}
			//RuleConfiguration.init();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Project resources are not initialized properly due to exception : " + e.getMessage());
		}
	}

	private void loadHtmls(String project_resource) throws Exception{
		Context ctx = new Context();
		ctx.setProject(project_resource);
		List pagecomponentList = ProcessFlowResources.getInstance(ctx).getPagecomponentList();

		if (pagecomponentList != null && !pagecomponentList.isEmpty()) {
			for (int i = 0; i < pagecomponentList.size(); i++){
				PagecomponentImpl pcImpl = (PagecomponentImpl) pagecomponentList.get(i);
				Map map = null;
				try {
					map = new PageUtils().convertHTMLToXMl(pcImpl, ctx);
				} catch (Exception e) {
					if(SystemProperties.getInstance().getProperty("appl.logs.required") != null && 
							SystemProperties.getInstance().getProperty("appl.logs.required").toString().equalsIgnoreCase("Y")){
						
						logger.error("Error in reading Html - " + pcImpl.getName());
						logger.error("Unexpected error", e);
					}
				}
				
				CacheManager.put(pcImpl.getName(), map);
				logger.debug("Done Caching of Html - " + pcImpl.getName());
			}
		}
	}

	protected void loadProjects(String project_resource) throws Exception{
		String[] projects = null;

		if (project_resource.contains(","))
			projects = project_resource.split(",");

		if (projects != null) {
			for(int i = 0; i < projects.length; i++) {
				String isLoadHTMLS = SystemProperties.getInstance().getString("appl." + projects[i] + ".LoadHTMLS");
				loadResources(projects[i]);
				if(isLoadHTMLS != null && "Y".equalsIgnoreCase(isLoadHTMLS))
					loadHtmls(projects[i]);
				
				try{
					if(SystemProperties.getInstance().getString("appl." + projects[i] + ".xslt.processor").equals("Y")){
						try{
							String isLoadXSLTS = SystemProperties.getInstance().getString("appl." + projects[i] + ".LoadXSLTS");
							if(isLoadXSLTS != null && "Y".equalsIgnoreCase(isLoadXSLTS))
								loadXslts(projects[i]);
						}catch (Exception e) {
							logger.error("Error in loading xslts in cache : " + e.getMessage());
						}
					}
				}catch (Exception e) {
					//Nothing to display
				}
			}
		} else {
			// project = project_resource;
			loadResources(project_resource);

			String isLoadHTMLS = SystemProperties.getInstance().getString("appl." + project_resource + ".LoadHTMLS");
			if(isLoadHTMLS != null && "Y".equalsIgnoreCase(isLoadHTMLS))
				loadHtmls(project_resource);
			
			try{
				if(SystemProperties.getInstance().getString("appl." + project_resource + ".xslt.processor").equals("Y")){
					try{
						String isLoadXSLTS = SystemProperties.getInstance().getString("appl." + project_resource + ".LoadXSLTS");
						if(isLoadXSLTS != null && "Y".equalsIgnoreCase(isLoadXSLTS))
							loadXslts(project_resource);
					}catch (Exception e) {
						logger.error("Error in loading xslts in cache : " + e.getMessage());
					}
				}
			}catch (Exception e) {
				//Nothing to display
			}
		}
     }

	protected void loadResources(String projectName) throws Exception{
		loadResource(projectName, "metadata");
		loadResource(projectName, "ibatisconfig");
		loadResource(projectName, "security");
		loadResource(projectName, "rules");
		loadResource(projectName, "components");
		loadResource(projectName, "processflow");
		loadResource(projectName, "messages");
		loadResource(projectName, "functions");
		loadResource(projectName, "reports");
		loadResource(projectName, "applicationworkflow");
		loadResource(projectName, "graphs");
		loadResource(projectName, "fields");
		//loadResource(projectName, "tabsconfiguration");
		loadResource(projectName, "labelconf");
		
        new LabelConfiguration().getLabelConfiguration(projectName);
        LoadXML.loadXML("tabsconfiguration", projectName);
        
        LookupResourceLoader.loadLookupResources(projectName);
        LoadCommonResources.loadCommonResources(projectName);
    }

	protected void loadResource(String projectName, String resourceType)throws Exception {
		String resourcePath = null;

		if (!resourceType.equals("ibatisconfig")) {
			if(resourceType.equalsIgnoreCase("components")){
				resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + File.separator + "components" + File.separator + resourceType + ".xml";
			}else if(resourceType.equalsIgnoreCase("metadata")){
				resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + File.separator + "metadata" + File.separator + resourceType + ".xml";
			}/*else if(resourceType.equalsIgnoreCase("rules")){
				resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + File.separator + "rules" + File.separator + resourceType + ".xml";
			}*/else if(resourceType.equalsIgnoreCase("messages") || resourceType.equalsIgnoreCase("reports") || resourceType.equalsIgnoreCase("security")){
				resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + File.separator + resourceType + ".xml";
				
				if(SystemProperties.getInstance().getProperty("appl.logs.required") != null && 
						SystemProperties.getInstance().getProperty("appl.logs.required").toString().equalsIgnoreCase("Y")){
					try {
						String resourcePathFromProperties = SystemProperties.getInstance().getString("appl." + projectName + "." + resourceType + ".path");
						if(null != resourcePathFromProperties && !HtmlConstants.EMPTY.equals(resourcePathFromProperties)){
							resourcePath = resourcePathFromProperties;
						}
					} catch (Exception e) {
						logger.error("Unable to load "+resourceType+" for "+projectName+" due to error : " + e.getMessage());
					}
				}else{
					if(SystemProperties.getInstance().getProperty("appl." + projectName + "." + resourceType + ".path") != null){
						try {
							String resourcePathFromProperties = SystemProperties.getInstance().getString("appl." + projectName + "." + resourceType + ".path");
							if(null != resourcePathFromProperties && !HtmlConstants.EMPTY.equals(resourcePathFromProperties)){
								resourcePath = resourcePathFromProperties;
							}
						} catch (Exception e) {
							logger.error("Unable to load "+resourceType+" for "+projectName+" due to error : " + e.getMessage());
						}
					}
				}
			}else{
				resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + File.separator + resourceType + ".xml";
			}
			
			resourcePath = resourcePath.replace("//", File.separator);
			resourcePath = resourcePath.replace("\\", File.separator);
			ResourceLoader.load(resourcePath, resourceType, projectName);
		}else{
			resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + "//ibatis//maps//SqlMapConfig.xml";
			resourcePath = resourcePath.replace("//", File.separator);
			resourcePath = resourcePath.replace("\\", File.separator);
			
			SqlResources.load(projectName, resourcePath);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		//RuleConfiguration.init();
		Context ctx = new Context();
		ctx.put("DocumentServletContext", servletContext);
		ctx.put("DocumentUriResolver", uriResolver);
		ctx.put("DocumentRequest", request);
		ctx.put("DocumentResponse", response);
		ctx.put("LastUpdateTimeStamp", new Timestamp(new Date().getTime()));
		
		ServletContext context=getServletContext();
		if(context.getAttribute("ZohoAccessToken") != null && !"".equals(context.getAttribute("ZohoAccessToken"))){
			ctx.put("isZohoAccessToken", context.getAttribute("ZohoAccessToken"));
		}
		try{
			if (requestCount == 2000){
				requestCount = 0;
				System.gc();
			}

			requestCount = requestCount + 1;

			HttpSession session = request.getSession();

			logger.info("Session is New :" + session.isNew());
			logger.info("Request Session Id : " + request.getRequestedSessionId());
			logger.info("Session Id : " + session.getId());

			if(session.getAttribute("UserNo") != null && !"".equals(session.getAttribute("UserNo"))){
				ctx.put("LastUpdateUserID", session.getAttribute("UserNo"));
				ctx.put("LastUpdatedBy", session.getAttribute("UserNo"));
				
			}
			if(session.isNew()
					&& !request.isRequestedSessionIdValid()) {
				String action = request.getParameter("inet_page") != null ? request.getParameter("inet_page").toString() : null;
				if(!validateApplicationForSession(request, response, action)){
					response.sendRedirect("sessiontimeout.jsp");
					return;
				}
				
//				  if("lawyersrep".equals(action)){ ctx.put("IS_SESSION_NEW", "Y"); }
				 
				/*if(action == null || (!"ManageIndex".equals(action) && !"userRoles".equals(action) && !"TestWebService".equals(action)
						&& !"JitWSTestHarness".equals(action)  && !"payment".equals(action) && !"bgOneWSTestHarness".equals(action))){
					response.sendRedirect("sessiontimeout.jsp?loginPage="+getErrorForwardUrl());
					return;
				}*/
			}else if(!ServletFileUpload.isMultipartContent(request) && ((request.getParameter("inet_page") == null || request.getParameter("inet_page").equals(HtmlConstants.EMPTY))
					&& request.getParameter(Constants.INET_AJAX_FORM) == null 
					&& request.getParameter(HtmlConstants.INET_AJAX_PAGE) == null
					&& request.getParameter(HtmlConstants.INET_AJAX_SUBMIT_FORM) == null)){ //if jsessionid coming but inet_page not coming
				response.sendRedirect("sessiontimeout.jsp");
				return;
			}
			
			//Added code to put session attributes in DocumentSessionMap - 12/10/2015 vikas
			if(session.getAttributeNames() != null){
				Map documentSessionMap = new HashMap();
				
				Enumeration<String> enm = session.getAttributeNames();
				while(enm.hasMoreElements()){
					String attr = enm.nextElement();
					
					if(!attr.equals("page_list_stack") && !attr.equals("REQUEST_AUTH_TOKEN"))
						documentSessionMap.put(attr, session.getAttribute(attr));
				}
				
				ctx.put("DocumentSessionMap", documentSessionMap);
			}
			//Ended code
			
			//To Log Out application and kill bounded session
			// Populate Context from Request
			PageUtils utils = new PageUtils();
            String method_name = request.getParameter(HtmlConstants.INET_METHOD) != null ? request.getParameter(HtmlConstants.INET_METHOD).toString() : null;
            if(method_name != null && "breadCrumbLink".equalsIgnoreCase(method_name)){
            	ctx = utils.getOldRequest(request);
                ctx.put(PageUtils.PAGE_LIST_STACK, request.getSession().getAttribute(PageUtils.PAGE_LIST_STACK));
                ctx.put(RequestAuthToken.REQUEST_AUTH_TOKEN, request.getParameter(RequestAuthToken.REQUEST_AUTH_TOKEN));
                ctx.put(HtmlConstants.INET_METHOD, method_name);
            }else
            	utils.populateContext(request, ctx);
                        
			if(ctx.get("inet_method") != null &&
					(ctx.get("inet_method").equals("logout") ||
							ctx.get("inet_method").equals("homepage"))){
				session.invalidate();
				logOut(ctx, request, response);
				
				/*if(ctx.get("roles") != null && ctx.get("roles").toString().equals("AgencyAdmin"))
					response.sendRedirect("logout.jsp");
				else	
					response.sendRedirect(getErrorForwardUrl(ctx, true));*/
				
				return;
			}

			String project = getServletConfig().getInitParameter(Constants.INET_PROJECT_ID);
			String[] projects = null;

			if(project.contains(",")){
				projects = project.split(",");
				project = projects[0];
			}
			
			ctx.setProject(project);
			if(!ZohoAccessToken.equals(ctx.get("isZohoAccessToken")) && ctx.get("isZohoAccessToken") != null){
				com.userbo.LawyersUtils.updateZohoToken(ctx);
				ZohoAccessToken = ctx.get("isZohoAccessToken") == null ? "" : ctx.get("isZohoAccessToken").toString();
			}
			com.userbo.LawyersValidationUtils.ValidateStatus(ctx);
			com.userbo.LawyersValidationUtils.validateUserLogin(ctx);
			//Checking validity of coming URL
			if(!validateApplication(request, response, ctx))
				return;

			//Adding jsessionid in Context to maintain session management
			ctx.put("jsessionid", session.getId());
			ctx.remove("NameClearanceIndicationFlag");
			
			if(ctx.get(Constants.INET_PAGE) != null &&
					(ctx.get(Constants.INET_PAGE).equals(ReportingConstants.MANAGE_REPORTING_PAGE) ||
							ctx.get(Constants.INET_PAGE).equals(ReportingConstants.MANAGE_GRAPH_PAGE))){
				if(ctx.get(ReportingConstants.PROJECTID) == null || ctx.get(ReportingConstants.PROJECTID).toString().equals(HtmlConstants.EMPTY))
					ctx.put(ReportingConstants.PROJECTID, ctx.getProject());
				
				if(!(ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("download") || ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("export")
						|| ctx.get(HtmlConstants.INET_METHOD).toString().equals(ReportingConstants.INET_SHOW_FILTER_DATA)
						|| ctx.get(HtmlConstants.INET_METHOD).toString().equals(ReportingConstants.INET_SHOW_GRAPH_POPUP)
						|| ctx.get(HtmlConstants.INET_METHOD).toString().equals(ReportingConstants.INET_CLOSE_GRAPH_POPUP))){
					String isRequestAuthTokenOn = null;
                	try {
						isRequestAuthTokenOn = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".request.auth.token");
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					
					//Bypass requestauthtoken check F5 button click for managereportdatatemplate page events
					if(ctx.get(ReportingConstants.ISSKIPREQUESTAUTHTOKEN) == null || !ctx.get(ReportingConstants.ISSKIPREQUESTAUTHTOKEN).toString().equals("Y")){
	                	if((isRequestAuthTokenOn != null && "Y".equalsIgnoreCase(isRequestAuthTokenOn)) && !RequestAuthToken.verifyRequest(ctx, request)){
	            			String pageContent = utils.getOldPageRequest(request);
	            			if(pageContent != null){
	                    		RequestAuthToken.resetToken(ctx, request);
	        					if(pageContent.contains("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\"") && pageContent.contains("</form>")) {
	            					String oldTokenTag = pageContent.substring(pageContent.indexOf("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\""),pageContent.indexOf("</form>"));
	            					String newTokenTag = "<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\" value=\""+ ctx.get("REQUEST_AUTH_TOKEN")+"\">";
	            					pageContent = pageContent.replace(oldTokenTag, newTokenTag);
	            					
	            					utils.writeResponse(response, pageContent, "text/html");
	            					return;
	        					}
	            			}
	                		
	        				if(pageContent == null){
	        					response.sendRedirect("sessiontimeout.jsp");
	        					return;
	        				} 
	        			}
					}
            	}
				
				new ReportProcessor().processReport(ctx, request, response);
				return;
			} else if(ctx.get(Constants.INET_PAGE) != null &&
					(ctx.get(Constants.INET_PAGE).equals(HtmlConstants.MANAGE_TESTHARNESS))){ //Added code for manage testharness
				
				new PIMRequestProcessorImpl().processManageTestHarness(ctx, utils, request, response);
				return;
			}else  if(ctx.get(Constants.INET_PAGE) != null &&
					(ctx.get(Constants.INET_PAGE).equals(HtmlConstants.MANAGE_TESTHARNESS))){ //Added code for manage testharness
				
				new PIMRequestProcessorImpl().processManageTestHarness(ctx, utils, request, response);
				return;
			}else {
				//Added code to write old response for uploadDocument action - 19/7/2012
				if(ctx.get(HtmlConstants.INET_METHOD) != null && !ctx.get(HtmlConstants.INET_METHOD).equals("uploadAttachment")
						&& !ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("download")){
					ctx.remove("htmlSource");
				}
				//Ended code
				
				if(request.getParameter(Constants.INET_AJAX_FORM) != null){
	                String inet_ajax_form = (String) request.getParameter(Constants.INET_AJAX_FORM);
	                if("Y".equals(inet_ajax_form)){
	                	//Doing Ajax Validation
	                	ajaxProcessorInterface.validateAjaxComponent(ctx, utils, request, response);
	                }
				}else {
                    if(null != request.getParameter(HtmlConstants.INET_AJAX_PAGE)){
                        //Processing Ajax Page view
                        ajaxProcessorInterface.populateNextComponentpage(ctx, utils, request, response);
                    }else if(null != request.getParameter(HtmlConstants.INET_AJAX_SUBMIT_FORM)){
                        //Processing Ajax Page view while submitting form
                        ajaxProcessorInterface.populateNextComponentpage(ctx, utils, request, response);
                    }else{
                    	String isRequestAuthTokenOn = null;
                    	try {
							isRequestAuthTokenOn = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".request.auth.token");
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
                    	if(!(ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("download") || ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("export") ||
                    			ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("showSessionList") ||
                    			ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("viewAll"))){
                        	if((isRequestAuthTokenOn != null && "Y".equalsIgnoreCase(isRequestAuthTokenOn)) && !RequestAuthToken.verifyRequest(ctx, request)){
                        		String pageContent = null;
                        		/*if(ctx.get(HtmlConstants.INET_METHOD).toString().equals("breadCrumbLink")){
                        			ctx.put("inet_method1", oldMethodName);
                        			pageContent = requestProcessorInterface.populateBreadCrumbPage(ctx, utils, request, response); 
                        		}else{*/
                        			pageContent = utils.getOldPageRequest(request);
                        			if(pageContent != null){
		                        		RequestAuthToken.resetToken(ctx, request);
		            					if(pageContent.contains("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\"") && pageContent.contains("</form>")) {
		                					String oldTokenTag = pageContent.substring(pageContent.indexOf("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\""),pageContent.indexOf("</form>"));
		                					String newTokenTag = "<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\" value=\""+ ctx.get("REQUEST_AUTH_TOKEN")+"\">";
		                					pageContent = pageContent.replace(oldTokenTag, newTokenTag);
		                					
		                					utils.writeResponse(response, pageContent, "text/html");
		                					return;
		            					}
                        			}
                        		//}
                				if(pageContent == null){
                					response.sendRedirect("sessiontimeout.jsp");
                					return;
                				} /*else {
                					RequestAuthToken.resetToken(ctx, request);
                					if(pageContent.contains("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\"") && pageContent.contains("</form>")) {
                    					String oldTokenTag = pageContent.substring(pageContent.indexOf("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\""),pageContent.indexOf("</form>"));
                    					String newTokenTag = "<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\" value=\""+ ctx.get("REQUEST_AUTH_TOKEN")+"\">";
                    					pageContent = pageContent.replace(oldTokenTag, newTokenTag);
                					}
                					utils.writeResponse(response, pageContent, "text/html");
                					return;
                				}*/
                			}
                    	}
                    	if(ctx.get(HtmlConstants.INET_METHOD).toString().equals("breadCrumbLink")){
                			String pageContent = requestProcessorInterface.populateBreadCrumbPage(ctx, utils, request, response);
                			utils.writeResponse(response, pageContent, "text/html");
                			return;
                		}
                        //Processing html page
                        requestProcessorInterface.populateNextComponentpage(ctx, utils, request, response);
                        return;
                    }
				}
			}
		}catch(Exception e){
			logger.error("Unexpected error", e);
			String stackTrace = e.getMessage();			
			stackTrace = DataUtils.getExceptionStackTrace(e);
	        logger.fatal(stackTrace);
	        
			String projectName = ctx.getProject();
			
			String session = "";
			HttpSession httpsession = request.getSession();
			if(httpsession != null){
				Enumeration attributes = httpsession.getAttributeNames();
				while(attributes.hasMoreElements()){
					String attr = attributes.nextElement().toString();
					if("page_list_stack".equalsIgnoreCase(attr))
						continue;
					session = session + attr + "=" + httpsession.getAttribute(attr) + ";";
				}
			}
			
			session = session + " #INET_PAGE#" + " = " + ctx.get(Constants.INET_PAGE) + ";";
			
			session = session + " #INET_METHOD#" + " = " + ctx.get(HtmlConstants.INET_METHOD) + ";";
			
			session = session + " #NEXT_PAGE#" + " = " + ctx.get("next_page_for_view") + ";";
			
			String errorMsg = e.getMessage();
			if(errorMsg != null)
				errorMsg = errorMsg.replaceAll("'", "");

			int errorId = 0;
			//errorId =logger.ineterror(errorMsg, stackTrace, projectName, session);
			errorId = new ErrorUtils().logError(errorMsg, stackTrace, projectName, session);
			
			request.setAttribute("errorId", errorId);
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("loginPage", getErrorForwardUrl(ctx, false));
			if(!forwardErrorPage(request, response))
				logger.error("Error page not forwarded because the response was already committed.");
		}
	}

	@Override
	public void destroy(){
		try {
			SqlResources.releaseResources();
		} catch (Exception e) {
			logger.error("Unable to release iBATIS resources: " + e.getMessage());
		}
		try {
			CompositeCacheManager.getInstance().shutDown();
		} catch (Exception e) {
			logger.error("Unable to shut down JCS: " + e.getMessage());
		}
		deregisterJdbcDrivers(getClass().getClassLoader());
		super.destroy();
	}

	static int deregisterJdbcDrivers(ClassLoader applicationClassLoader){
		int deregistered = 0;
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()){
			Driver driver = drivers.nextElement();
			if(driver.getClass().getClassLoader() != applicationClassLoader)
				continue;
			try {
				DriverManager.deregisterDriver(driver);
				deregistered++;
			} catch (SQLException e) {
				logger.error("Unable to deregister JDBC driver " + driver.getClass().getName() + ": " + e.getMessage());
			}
		}
		return deregistered;
	}

	static boolean forwardErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(response.isCommitted())
			return false;
		response.reset();
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		request.getRequestDispatcher("error.jsp").forward(request, response);
		return true;
	}

	//Method to validate application with coming URL
	public abstract boolean validateApplication(HttpServletRequest request, HttpServletResponse response, Context ctx)throws Exception;

	private String getErrorForwardUrl(Context ctx, boolean isLogOut){
		try{
			/*if(isLogOut && (SystemProperties.getInstance().getProperty("appl."+ ctx.getProject()+".issso.enabled") != null && 
					SystemProperties.getInstance().getProperty("appl."+ ctx.getProject()+".issso.enabled").equals("Y"))){
				
				if(SystemProperties.getInstance().getProperty(SSO_FORWARD_URL) != null)
					return SystemProperties.getInstance().getProperty(SSO_FORWARD_URL).toString();
				
				if(SystemProperties.getInstance().getProperty(ERROR_FORWARD_URL) != null)
					return SystemProperties.getInstance().getProperty(ERROR_FORWARD_URL).toString();
			}else{
				if(SystemProperties.getInstance().getProperty(ERROR_FORWARD_URL) != null)
					return SystemProperties.getInstance().getProperty(ERROR_FORWARD_URL).toString();
			}*/
			
			if(SystemProperties.getInstance().getProperty(ERROR_FORWARD_URL) != null)
				return SystemProperties.getInstance().getProperty(ERROR_FORWARD_URL).toString();
		}catch (Exception e) {
			logger.error("Error In Getting error.jsp Forward Page");
		}
		
		return "";
	}
	
		
	public abstract String initLocalResources() throws Exception ;
	
	private void loadXslts(String project_resource) throws Exception{
		Context ctx = new Context();
		ctx.setProject(project_resource);
		
		List pagecomponentList = ProcessFlowResources.getInstance(ctx).getPagecomponentList();
		if(pagecomponentList != null && !pagecomponentList.isEmpty()){
			for(int i=0; i<pagecomponentList.size(); i++){
				PagecomponentImpl pcImpl = (PagecomponentImpl)pagecomponentList.get(i);
				
				if(!pcImpl.getPath().endsWith(".html") || pcImpl.getName().equals("compensationIncentiveMgmt"))
					continue;
				
				String htmlDir = SystemProperties.getInstance().getString("html.basedir");
				String xsltFilePath = htmlDir + "//xslt//";
				
				String processFlowPath = ProcessFlowResources.getInstance(ctx).getPagecomponent(pcImpl.getName()).getPath();
				processFlowPath = processFlowPath.replace(".html", ".xslt");
				xsltFilePath = xsltFilePath +  processFlowPath + "//";
				
				String xsltFile = xsltFilePath;
				String xsltFileContent = IOUtils.readFile(xsltFile);
				
				CacheManager.put(pcImpl.getName(), xsltFileContent);
			}
		}
	}
	
	//Method to validate application with coming URL for Session Timeout
	public abstract boolean validateApplicationForSession(HttpServletRequest request, HttpServletResponse response, String action)throws Exception;
	
	//Method created to logout application
	public abstract void logOut(Context ctx, HttpServletRequest request, HttpServletResponse response);
}
