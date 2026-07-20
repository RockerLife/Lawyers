package com.manage.process;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ormapping.ibatis.SqlResources;
import com.userbo.LawyersConstants;
import com.userbo.LawyersValidationUtils;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.SystemProperties;



public class LawyersInsServlet extends ComponentProcessServlet{
	
	private String SSO_FORWARD_URL = "sso.logout.url";
	private static InetLogger logger = InetLogger.getInetLogger(LawyersInsServlet.class);
	public void init() throws ServletException {
		super.init();
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		//new TabsConfiguration().getTabsConf();	
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.process(request, response);
	}

	public boolean validateApplication(HttpServletRequest req,
			HttpServletResponse resp,Context ctx) throws Exception {	
		//To set Cut-Off Date 
		ctx.put("CutOffDate", LawyersConstants.CUT_OFF_DATE);
		ctx.put("CutOffDateGroup2", LawyersConstants.CUT_OFF_DATE_GROUP2);
		ctx.put("NewAppFlowCutOffDate", LawyersConstants.NEWAPPFLOW_CUT_OFF_DATE);
		ctx.put("CutOffDateGroup3", LawyersConstants.CUT_OFF_DATE_GROUP3);
		ctx.put("CutOffDateGroup4", LawyersConstants.CUT_OFF_DATE_GROUP4);
		ctx.put("CutOffDateGroup5", LawyersConstants.CUT_OFF_DATE_GROUP5);
		ctx.put("CutOffDateGroup6", LawyersConstants.CUT_OFF_DATE_GROUP6);
		ctx.put("CutOffDateGroup7", LawyersConstants.CUT_OFF_DATE_GROUP7);
		ctx.put("CutOffDateGroup8", LawyersConstants.CUT_OFF_DATE_GROUP8);
		ctx.put("CutOffDateCCBSupp", LawyersConstants.CUT_OFF_DATE_CCBSupp);
		ctx.put("CutOffDate2020", LawyersConstants.CUT_OFF_DATE_2020);
		ctx.put("CutOffDateCannibsSupp", LawyersConstants.CUT_OFF_DATE_CANNIBSSUPP);
		ctx.put("CFPolicyIssuanceEndDate", LawyersConstants.END_DATE_CF_POLICYISSUANCE);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Date parsedDate = dateFormat.parse(LawyersConstants.ELECTRONIC_INSURANCE_IMPL_DATE);
		ctx.put("ElectronicInsuranceImplDate", new Timestamp(parsedDate.getTime()));
		
		parsedDate = dateFormat.parse(LawyersConstants.BANKRUPTCY_INSURANCE_IMPL_DATE);
		ctx.put("BankruptcySuppImplDate", new Timestamp(parsedDate.getTime()));
		
		parsedDate = dateFormat.parse(LawyersConstants.NEVADA_DOUBLEQUOTE_IMPL_DATE);
		ctx.put("NevadaDoubleQuoteImplDate", new Timestamp(parsedDate.getTime()));
		
		//going to filter fields based on showRightClickPageId flag
		if(ctx.get("showRightClickPageId") != null && ctx.get("showRightClickPageFields") != null){
			/*String showRightClickPageId = ctx.get("showRightClickPageId").toString();
			String showRightClickPageFields = ctx.get("showRightClickPageFields").toString();
			
			StringTokenizer tokens = new StringTokenizer(showRightClickPageFields, ",");
			while(tokens.hasMoreTokens()){
				String token = tokens.nextToken();
				
				ctx.put(token, ctx.get(token+"_"+ctx.get(showRightClickPageId)));
				ctx.remove(token+"_"+ctx.get(showRightClickPageId));
			}*/
			
			ctx.put("Comment", ctx.get("SuggestedAnswer"));
		}
		
		
		if(ctx.get("inet_page")!= null && ctx.get("inet_page").equals("indexrenew")
				&& ctx.get("EncryptedPolicyKey")!= null){
			String policyKey = ctx.get("EncryptedPolicyKey").toString();
			
			
			
			
			if(policyKey != null && !HtmlConstants.EMPTY.equals(policyKey)){
				
				 LawyersValidationUtils.decryptyPolicyKey(ctx);
				
				 
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementdroolQueriesgetPolicyData", ctx);
				if(obj != null && obj instanceof Map )
				{
					Map map = (Map)obj;
					if(map != null && map.get("AppCreatedDate")!= null && !HtmlConstants.EMPTY.equals(map.get("AppCreatedDate").toString())){
						ctx.put("AppCreatedDate",map.get("AppCreatedDate").toString());
					}
				}
			
				 List policyList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPolicyTransaction",ctx);
					if(policyList != null )
					{
						Map map = (Map) policyList.get(0);
						
						if(map != null && map.get("StatusKey").toString().equals("6")){
							ctx.put("AppCreatedDate",null);
						}
					}
			}
			
		}
		
		// TODO Auto-generated method stub
		if(ctx.get("inet_page") != null && (ctx.get("inet_page").equals("login")
				||  ctx.get("inet_page").equals("indexinsured") ||  ctx.get("inet_page").equals("insured") || ctx.get("inet_page").equals("indexrenew")
				||  ctx.get("inet_page").equals("registration") ||  ctx.get("inet_page").equals("indexsignandpay") || ctx.get("inet_page").equals("MANAGE_TESTHARNESS")
				||  ctx.get("inet_page").equals("forgotpassword") || ctx.get("inet_page").equals("forgotpasswordquestion1") || ctx.get("inet_page").equals("forgotidconfirmation")
				 || ctx.get("inet_page").equals("protexureEstimate")|| ctx.get("inet_page").equals("ProtexureEstimateForm")))
			return true;
		
		
		
		if(ctx.get("role_id") == null || ctx.get("role_id").equals("") || ctx.get("role_id").equals("null")){
			String url = "lawyersrep.jsp";
			//if(ctx.get("inet_page") != null && ctx.get("inet_page").equals("login"))
				url = url + "?pageError=Please select one Role";
				req.getRequestDispatcher(url).forward(req, resp);
				return false;	
		}
		
		return true;
	}

	@Override
	public String initLocalResources() throws Exception {
		//String encProps = "appl.ProducerOne.db.password";
		//encProps = encProps + "";
		return "";
	}

	@Override
	public boolean validateApplicationForSession(HttpServletRequest request,
			HttpServletResponse response, String action) throws Exception {
		if(action == null || (!"ManageIndex".equals(action) && !"userRoles".equals(action) 
				&& !"firm".equals(action) && !"indexinsured".equals(action) && !"payment".equals(action)
				&& !"qqinsured".equals(action) && !"signandpay".equals(action) && !"paypalThanks".equals(action)
				&& !"indexsignandpay".equals(action) && !"indexrenew".equals(action)
				&& !"login".equals(action)  && !"insured".equals(action) && !"lawyersrep".equals(action) 
				&& !"completeapplink".equals(action) && !"paymentmethod".equals(action) && !"IPFSThankYouPage".equals(action) && !"certificateInsurance".equals(action)  && !"MANAGE_TESTHARNESS".equals(action) 
				&& !"qqlogin".equals(action) && !"indicationInsured".equals(action) && !"IPFSCancelPage".equals(action) && !"RequotePolicy".equals(action)
				&& !"brokerageConversion".equals(action) && !"protexureEstimate".equals(action) && !"ProtexureEstimateForm".equals(action)
				)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public void logOut(Context ctx, HttpServletRequest request, HttpServletResponse response) {
		  try{
				response.sendRedirect(getErrorForwardUrl(ctx, true));
		  }catch (Exception e) {
				// TODO: handle exception
		  }
	}

	private String getErrorForwardUrl(Context ctx, boolean isLogOut){
		try{
			return SystemProperties.getInstance().getString("appl.error.forward.url");
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
		
		return null; 
	}
}
