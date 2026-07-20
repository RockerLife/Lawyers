package com.userbo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.osi.ws.client.objects.Privileges;
import com.osi.ws.client.objects.QuestionAnswers;
import com.osi.ws.client.objects.SecureOneWSSoap11BindingStub;
import com.osi.ws.client.objects.UserDetails;
import com.osi.ws.client.objects.UserRole;
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;

public class SecureUtils {

	private static final String ERROR_STRING = "Error:Old Password is not correct";
	private static final String STRING_TRUE = "true";
	private static InetLogger logger = InetLogger.getInetLogger(SecureUtils.class);

	public void saveRegistrationInfo(IContext ctx) throws Exception {
		try {

			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			String ProducerCode = ctx.get("ProducerCode") != null ? ctx.get("ProducerCode").toString() : "";
			logger.debug("Creating user for producer code " + ProducerCode);

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			String result = bindingStub.createUser(ctx.get("AccountEmail")
					.toString().trim(), null, null, ctx.get("firstname")
					.toString().trim(), null, ctx.get("lastname").toString()
					.trim(), null, ctx.get("AccountEmail").toString().trim(),
					ctx.get("WorkPhone").toString().trim(), null, null, null,
					null, null, null, null, null, null, 3, 5,
					"Insured Organization", ctx.get("PassWord").toString().trim(), ProducerCode);

			logger.debug("Create user request succeeded: " + "true".equalsIgnoreCase(result));

			if ("true".equalsIgnoreCase(result)) {
				Boolean isUserUpdated = bindingStub.updateUserProfile(ctx.get(
						"AccountEmail").toString().trim(), null, null, ctx.get(
						"firstname").toString().trim(), null, ctx.get(
						"lastname").toString().trim(), "", ctx.get(
						"AccountEmail").toString().trim(), ctx.get("WorkPhone")
						.toString().trim(), null, null, "Y", Integer
						.parseInt(ctx.get("ChallengeQuesID").toString()), 0, 0,
						ctx.get("ChallengeAns1").toString().trim(), null, null,
						null, null, null, null, null, null, null, true, ctx
								.get("AccountEmail").toString().trim());

				if (isUserUpdated) {
					ctx.put("userSaved", "Y");
					logger.debug("Is User updated -- " + isUserUpdated);
					logger.debug("User has been created and Updated---- ");
				} else {
					ctx.put("userSaved", "N");
					logger.debug("Is User updated -- " + isUserUpdated);
					logger.debug("User has not been Updated---- ");
				}
			} else {
				if (result.startsWith("An error occurred while adding a new user"))
					result = "You have not been registered, please contact 1-877-569-4111 for any assistance";

				LawyersUtils.populateError(ctx, "Registration", result);
				ctx.put("userSaved", "N");
				logger.debug("User has not been created ---- ");
			}

		} catch (Exception e) {
			LawyersUtils.populateError(ctx, "AccountEmail",
					"Please contact 1-877-569-4111 for assistance");
			ctx.put("userSaved", "N");
			logger.error("Unable to create user", e);
		}
	}

	public void validateUser(IContext ctx) throws Exception {

		try {

			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			logger.debug("Going to validate user ---");

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			// SecureOneWSSoap11BindingStub bindingStub = null;
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			String result = bindingStub.isUserAuthenticate(ctx.get(
					"AccountEmail").toString(), ctx.get("PassWord").toString());

			logger.debug("Result -- " + result);
			//result="true";
			boolean isPrimary = false;
			UserRole userRole = null;

			if ("true".equalsIgnoreCase(result)) {
				
				HttpServletRequest req = (HttpServletRequest) ctx.get("DocumentRequest");
				HttpSession sess = req.getSession();
				String userLoginKey = LawyersUtilities.generateUniqueId() + "-" + LawyersUtilities.generateRandomString();
				ctx.put("UserLoginKey", userLoginKey);
				ctx.put("UserEmail", ctx.get("AccountEmail").toString());
				ctx.put("RecordType", "I");
				ctx.put("LoginTimeStamp", new Timestamp(new Date().getTime()));
				
				LawyersUtils.updateLoginDetalis(ctx);

				UserRole[] roles = bindingStub.getUserRole(ctx.get("AccountEmail").toString());
				UserDetails userDetail = bindingStub.getUserDetail(ctx.get("AccountEmail").toString());
				
				if (roles != null) {
					for (int i = 0; i < roles.length; i++) {
						userRole = roles[i];
						if ("Y".equals(userRole.getIsprimary())) {
							isPrimary = true;
							break;
						}
					}
				}

				if (isPrimary) {
					// String email = ctx.get("AccountEmail").toString();
					//					
					// if(email.indexOf('@') >0)
					// email = email.substring(0,email.indexOf('@'));
					ctx.put("UserRoleName", userRole.getBus_role_nme());
					String fstName = userDetail.getFst_nme().trim();
					String lstName = userDetail.getLst_nme().trim();
					String agentName = fstName + " " + lstName;
					
					if ("AccountRep".equalsIgnoreCase(userRole.getBus_role_nme())) {
						// ctx.put("LastUpdateUserID", email);
						ctx.put("User", "Agent");
						ctx.put("role_id", SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".accountrep"));
						ctx.put("AgentName",agentName);
					} else if ("Mgt".equalsIgnoreCase(userRole.getBus_role_nme())) {
						// ctx.put("LastUpdateUserID", email);
						ctx.put("User", "Agent");
						ctx.put("role_id", SystemProperties.getInstance().getString(
										"appl." + ctx.getProject()+ ".management"));
						
							ctx.put("AgentName",agentName);
					} else if ("Crum&Foster".equalsIgnoreCase(userRole
							.getBus_role_nme())) {
						// ctx.put("LastUpdateUserID", email);
						ctx.put("User", "Agent");
						ctx.put("role_id", SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".crumfoster"));
						ctx.put("AgentName",agentName);
					} else if ("OtherMgt".equalsIgnoreCase(userRole.getBus_role_nme())) {				// ctx.put("LastUpdateUserID", email);
						ctx.put("User", "Agent");
						ctx.put("role_id", SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".othermanagement"));
						ctx.put("AgentName",agentName);
					} else if ("Agent".equalsIgnoreCase(userRole.getBus_role_nme())) {
						// ctx.put("LastUpdateUserID", email);
						ctx.put("User", "Agent");
						ctx.put("role_id", SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".accountrep"));
						ctx.put("AgentName",agentName);
					} 
					else if ("UnderWriter".equalsIgnoreCase(userRole
							.getBus_role_nme())) {
						ctx.put("User", "Agent");
						ctx.put("role_id",SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".underwriter"));
						ctx.put("AgentName",agentName);
					}
					else if ("Subproducer".equalsIgnoreCase(userRole
							.getBus_role_nme())) {
						ctx.put("User", "Agent");
						ctx.put("role_id",SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".Subproducer"));
						
//						HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
//				        HttpSession sess = req.getSession();
				        /*sess.setAttribute("ProducerCode","P0000001");
				        sess.setAttribute("ProducerCodefilter","P0000001");*/
						String ProducerCode = userDetail.getPrdcr_cde();	
						ctx.put("AgentName",agentName);
						
						ctx.put("ProducerCode",ProducerCode);
						sess.setAttribute("ProducerCode",ProducerCode);
						logger.debug("Producer Code for SubProducer ---" + ProducerCode);
						
					}
					else if ("ISMIE".equalsIgnoreCase(userRole
							.getBus_role_nme())) {
						ctx.put("User", "Agent");
						ctx.put("role_id",SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".ISMIE"));
						ctx.put("AgentName",agentName);
					}
					else if ("hanover".equalsIgnoreCase(userRole
							.getBus_role_nme())) {
						ctx.put("User", "Agent");
						ctx.put("role_id",SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".hanover"));
						ctx.put("AgentName",agentName);
					}
					else if ("Insured".equalsIgnoreCase(userRole.getBus_role_nme())) {
						ctx.put("User", "insured");
						ctx.put("role_id",SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".insured"));
					/*	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
				        HttpSession sess = req.getSession();
				     sess.setAttribute("SubProducerLogoDisplay", "Y");
					*/	
					}
					

					else
						LawyersUtils.populateError(ctx, "AccountEmail","Role is not defined for email id");
					
					//if(!"Insured".equalsIgnoreCase(userRole.getBus_role_nme()) || !"Crum&Foster".equalsIgnoreCase(userRole.getBus_role_nme())){
						if(!"Insured".equalsIgnoreCase(userRole.getBus_role_nme())){
						try	{
							new SetParametersForStoredProcedures().setParametersInContext(ctx, "ProducerCode,AccountEmail,AgentName");
						SqlResources.getSqlMapProcessor(ctx).update("FirmLW.ManageUserDetails_p", ctx);
						}
						catch(Exception e)
						{
			logger.error("Unable to call ManageUserdetails procedure", e);
						}
					}
					
				} else {
					ctx.put("User", "insured");
					ctx.put("role_id", SystemProperties.getInstance().getString("appl." + ctx.getProject()+ ".insured"));
				}
				ctx.put("SecureLoginId", ctx.get("AccountEmail").toString());
				logger.debug("User has been validated---");

				// System.out.println("Cookie is going to create-- ");
				//				
				// UserDetails userDetails =
				// bindingStub.getUserDetail(ctx.get("AccountEmail").toString());
				//				
				// long[] dateLong =
				// SecureDetails.getInstance().getCreationExpirationDate();
				// SecureOneUserDetails details = new
				// SecureOneUserDetails(userDetails.getUuid(), dateLong[0],
				// dateLong[1], null);
				//                
				// new SimpleSSOSecureValve(true,
				// details).invoke((HttpServletRequest)
				// ctx.get("httprequest"),(HttpServletResponse)
				// ctx.get("response"));
				//                
				// System.out.println("Cookie has been created-- ");
			} else {
				String result1 = "";
				String result2 = "";

				if (result.startsWith("Invalid User ID. Please try again")) {
					result1 = "This User ID does not exist.";
					result2 = "If you need assistance please call us at 1-877-569-4111";
				} else if (result
						.startsWith("user is currently disabled. Contact the System Administrator.")) {
					result1 = "User ID is disabled,";
					result2 = "Please contact 1-877-569-4111 for any assistance";
				} else if (result
						.startsWith("Invalid Credentials. 3 attempts left.")) {
					result1 = "Password is incorrect, 3 attempts left,";
					result2 = "Please contact 1-877-569-4111 for any assistance";
				} else if (result
						.startsWith("Invalid Credentials. 2 attempts left.")) {
					result1 = "Password is incorrect, 2 attempts left,";
					result2 = "Please contact 1-877-569-4111 for any assistance";
				} else if (result
						.startsWith("Invalid Credentials. 1 attempts left.")) {
					result1 = "Password is incorrect, 1 attempts left,";
					result2 = "Please contact 1-877-569-4111 for any assistance";
				} else if (result
						.startsWith("Your account is locked, please contact Administrator.")) {
					result1 = "User is locked,";
					result2 = "Please contact 1-877-569-4111 for any assistance";
				} else {
					result2 = "Please contact 1-877-569-4111 for any assistance";
				}

				LawyersUtils.populateError(ctx, "AccountEmail1", result1);
				LawyersUtils.populateError(ctx, "AccountEmail2", result2);
				logger.debug("User has not been validated---");
			}

			// ctx.put("User", "Agent");
			// ctx.put("role_id",
			// SystemProperties.getInstance().getString("appl."+ctx.getProject()+".management"));

		} catch (Exception e) {

			// This code when Secure one not working
			
//			  ctx.put("User", "Agent"); ctx.put("role_id",
//			  SystemProperties.getInstance().getString( "appl." +
//			  ctx.getProject() + ".management"));
			  /*ctx.getProject() + ".insured"));*/
			 
			 

			logger.error("Unexpected error", e);

			LawyersUtils.populateError(ctx, "AccountEmail",
					"Please contact 1-877-569-4111 for assistance");

		}
	}

	public void getPrivilagesList(IContext ctx) throws Exception {

		try {

			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			logger.debug("Going to get privilage ---");

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			// SecureOneWSSoap11BindingStub bindingStub = null;

			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			Privileges[] privileges = bindingStub.getPrivileges(ctx.get(
					"AccountEmail").toString());
			if (privileges == null)
				return;

			List privilagesList = new ArrayList();
			Privileges privilege = null;
			for (int i = 0; i < privileges.length; i++) {				
				privilege = privileges[i];
				Map mapPrivilege = new HashMap();
				mapPrivilege.put("rescr_url", privilege.getRescr_url()
						+ "?User=" + ctx.get("User").toString() + "&role_id="
						+ ctx.get("role_id").toString() + "&AccountEmail="
						+ ctx.get("AccountEmail").toString() + "&UserLoginKey="
						+ ctx.get("UserLoginKey").toString() + "&jsessionid="
						+ ctx.get("jsessionid"));
				mapPrivilege.put("rescr_shrt_nme", privilege
						.getRescr_shrt_nme());
				privilagesList.add(mapPrivilege);
			}

			ctx.put("privilages_list_01", privilagesList);

			logger.debug("Privilage has been got---");

		} catch (Exception e) {

			/**
			 * This code is to be used when secure one is down
			 */

			
			  List privilagesList = new ArrayList();
			  /*management*/
//			  Map mapPrivilege = new HashMap(); mapPrivilege.put("rescr_url",
//			  "http://localhost:8080/LawyersIns/lawyersrep.jsp" + "?User=" +
//			  ctx.get("User").toString() + "&role_id=" + "3" + "&AccountEmail=" +
//			  ctx.get("AccountEmail").toString() + "&jsessionid="
//						+ ctx.get("jsessionid"));
//			  mapPrivilege.put("rescr_shrt_nme", "lawyersLink");
//			  privilagesList.add(mapPrivilege); ctx.put("privilages_list_01",
//			  privilagesList);
			 
			 /* //for Insured
				Map mapPrivilege = new HashMap();
				mapPrivilege.put(
						"rescr_url",
						"http://localhost:8080/LawyersIns/lawyersrep.jsp"
								+ "?User=" + ctx.get("User").toString()
								+ "&role_id=" + "1" + "&AccountEmail="
								+ ctx.get("AccountEmail").toString()
								+ "&jsessionid=" + ctx.get("jsessionid"));
				mapPrivilege.put("rescr_shrt_nme", "lawyersLink");
				privilagesList.add(mapPrivilege);
				ctx.put("privilages_list_01", privilagesList);*/

			LawyersUtils.populateError(ctx, "AccountEmail",
					"Please contact  1-877-569-4111 for assistance");
			logger.debug("Privilage  has not been got---");

		}

	}

	public void validateChangePassword(IContext ctx) throws Exception {

		boolean isNotEmtpyPassword = false;
		if (ctx.get("old_pwd") == null)
			LawyersUtils.populateError(ctx, "old_pwd",
					"Old password is required.");
		else if (ctx.get("old_pwd") != null
				&& "".equals(ctx.get("old_pwd").toString().trim()))
			LawyersUtils.populateError(ctx, "old_pwd",
					"Old password is required.");

		if (ctx.get("new_pwd") == null)
			LawyersUtils.populateError(ctx, "new_pwd",
					"New password is required.");
		else if (ctx.get("new_pwd") != null
				&& "".equals(ctx.get("new_pwd").toString().trim()))
			LawyersUtils.populateError(ctx, "new_pwd",
					"New password is required.");
		else
			isNotEmtpyPassword = true;

		if (ctx.get("re_new_pwd") == null)
			LawyersUtils.populateError(ctx, "re_new_pwd",
					"Confirm password is required.");
		else if (ctx.get("re_new_pwd") != null
				&& "".equals(ctx.get("re_new_pwd").toString().trim()))
			LawyersUtils.populateError(ctx, "re_new_pwd",
					"Confirm password is required.");

		if (ctx.get("new_pwd") != null && ctx.get("re_new_pwd") != null) {
			if (!(ctx.get("new_pwd").toString().equals(ctx.get("re_new_pwd")
					.toString())))
				LawyersUtils.populateError(ctx, "new_pwd",
						"New password and re-enter password do not match.");
		}

		if (isNotEmtpyPassword) {
			ctx.put("PassWord", ctx.get("new_pwd"));
			Object obj = RuleUtils.executeRule(ctx,
					"LawyersRule.validatePassword");
			if (obj != null && obj instanceof Boolean) {
				boolean flag = (Boolean) obj;
				if (!flag)
					LawyersUtils.populateError(ctx, "new_pwd",
							"New password is not in correct format.");
			}
		}

	}

	public void changePassword(IContext ctx) throws Exception {

		try {

			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			logger.debug("Going to change password ---");

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			String loginId = LawyersUtils.getLoginID(ctx);

			// String pwdChanged = bindingStub.changePassword(ctx.get(
			// "AccountEmail").toString(), ctx.get("old_pwd").toString(),
			// ctx.get("new_pwd").toString(), ctx.get("re_new_pwd")
			// .toString());

			String pwdChanged = bindingStub.changePassword(ctx.get(
					"SecureLoginId").toString(), ctx.get(
					"old_pwd").toString(), ctx.get("new_pwd").toString(), ctx
					.get("re_new_pwd").toString());

			logger.debug("Result from web service - " + pwdChanged);

			// Error:Old Password is not correct true

			if (pwdChanged.indexOf("Error") != -1
					|| pwdChanged.equals(ERROR_STRING)) {
				LawyersUtils.populateError(ctx, "old_pwd",
						"Old Password is not correct");
				logger.debug("Password has not been changed---");
			}
			if (pwdChanged.indexOf("Error") == -1
					|| pwdChanged.equals(STRING_TRUE)) {
				logger.debug("Password has  been changed---");
			}

		} catch (Exception e) {

			LawyersUtils.populateError(ctx, "old_pwd",
					"Please contact 1-877-569-4111 for assistance");
			logger.debug("Password has not been changed---");
		}

	}

	public void getSecurityData(IContext ctx) throws Exception {

		try {

			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			logger.debug("Going to get security data ---");

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			QuestionAnswers qesans = bindingStub.getChanllengingQuesAns(ctx
					.get("AccountEmail").toString());

			if (qesans == null)
				LawyersUtils.populateError(ctx, "old_pwd",
						"Security question is not defined.");

			ctx.put("chlng_qns1", qesans.getQues1());
			ctx.put("db_chlng_ans1", qesans.getAns1());

			logger.debug("Security data has been got---");

		} catch (Exception e) {

			LawyersUtils.populateError(ctx, "old_pwd",
					"Please contact 1-877-569-4111 for assistance");
			logger.debug("Security data has not been got---");
		}

	}

	public void validateUserID(IContext ctx) throws Exception {

		if (ctx.get("AccountEmail") == null) {
			LawyersUtils.populateError(ctx, "AccountEmail",
					"User ID is required.");
			return;
		} else if (ctx.get("AccountEmail") != null
				&& "".equals(ctx.get("AccountEmail").toString().trim())) {
			LawyersUtils.populateError(ctx, "AccountEmail",
					"User ID is required.");
			return;
		}

		try {

			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			logger.debug("Going to validate AccountEmail ---");

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			// UserDetails userDetails = bindingStub.getUserDetail(ctx.get(
			// "AccountEmail").toString());

			UserDetails userDetails = bindingStub.getUserDetail(ctx.get(
					"AccountEmail").toString());

			if (userDetails == null) {
				LawyersUtils
						.populateError(ctx, "AccountEmail",
								"User ID does not exist. Please contact 1-877-569-4111 for assistance.");
				return;
			}

			if (userDetails != null) {
				QuestionAnswers qesans = bindingStub.getChanllengingQuesAns(ctx
						.get("AccountEmail").toString());

				if (qesans == null)
					LawyersUtils.populateError(ctx, "AccountEmail",
							"Security question is not defined.");
			}

			logger.debug("AccountEmail has been validated---");

		} catch (Exception e) {

			LawyersUtils.populateError(ctx, "AccountEmail",
					"Please contact 1-877-569-4111 for assistance");
			logger.debug("AccountEmail has not been validated---");
		}
	}

	public void validateSecurityData(IContext ctx) throws Exception {

		if (ctx.get("chlng_ans1") == null)
			LawyersUtils.populateError(ctx, "chlng_ans1",
					"Challenge answer is required.");
		else if (ctx.get("chlng_ans1") != null
				&& "".equals(ctx.get("chlng_ans1").toString().trim()))
			LawyersUtils.populateError(ctx, "chlng_ans1",
					"Challenge answer is required.");

		if (ctx.get("chlng_ans1") != null && !"".equals(ctx.get("chlng_ans1"))) {
			if (ctx.get("chlng_ans1").toString().equals(
					ctx.get("db_chlng_ans1")))
				ctx.put("chlng_ans1_matched", "Y");
			else {
				ctx.put("chlng_ans1_matched", "N");
				LawyersUtils.populateError(ctx, "chlng_ans1",
						"Challenge answer is not matched.");
			}
		}

	}

	public void resetSecurityData(IContext ctx) throws Exception {

		try {

			String endPointUrl = null;
			String username = null;
			String password = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			logger.debug("Going to reset password ---");

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			username = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.UserName");

			password = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.PassWord");

			// Boolean pwdChanged = bindingStub.resetPassword(ctx.get(
			// "AccountEmail").toString());

//			String pwdChanged = bindingStub.resetPasswordSecured(username,
//					password, ctx.get("AccountEmail").toString());
			boolean pwdChanged = bindingStub.resetPassword(ctx.get("AccountEmail").toString());

			logger.debug("Password has been reset---");

		} catch (Exception e) {

			LawyersUtils.populateError(ctx, "old_pwd",
					"Please contact 1-877-569-4111 for assistance");
			logger.debug("Password has not been reset---");
		}

	}

	public void updateRegistartionInfo(IContext ctx) throws Exception {
		try {

			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);

			if (ctx.get("LoginID") != null
					&& !"".equals(ctx.get("LoginID").toString())) {

				QuestionAnswers qas = bindingStub.getChanllengingQuesAns(ctx
						.get("LoginID").toString().trim());

				UserDetails userDetails = bindingStub.getUserDetail(ctx.get(
						"LoginID").toString());

				Context newCtx = new Context();
				newCtx.put("LoginID", ctx.get("LoginID"));
				newCtx.put("firstname", userDetails.getFst_nme());
				newCtx.put("lastname", userDetails.getLst_nme());
				newCtx.put("AccountEmail", ctx.get("AccountEmail"));
				newCtx.put("WorkPhone", userDetails.getBus_ph_nbr());

				List questionList = new QuestionDropdown().getDropDownData(ctx,
						"");
				int questionId = getQuestionId(questionList, qas.getQues1());
				if (questionId > 0) {

					Boolean isUserUpdated = bindingStub.updateUserProfile(
							newCtx.get("LoginID").toString().trim(), null,
							null, newCtx.get("firstname").toString().trim(),
							null, newCtx.get("lastname").toString().trim(), "",
							newCtx.get("AccountEmail").toString().trim(),
							newCtx.get("WorkPhone").toString().trim(), null,
							null, "Y", questionId, 0, 0, qas.getAns1().trim(),
							null, null, null, null, null, null, null, null,
							null, true, ctx.get("LoginID").toString().trim());

					if (isUserUpdated) {
						logger.debug("User has been updated");
					} else {
						logger.debug("Cannot update user Info");
					}
				}
			}
		} catch (Exception e) {
			LawyersUtils.populateError(ctx, "AccountEmail",
					"Please contact 1-877-569-4111 for assistance");
			logger.debug("User cannot be updated due to exception");

		}

	}

	private int getQuestionId(List questionList, String question) {

		int questionid = 0;
		if (questionList != null && question != null) {
			for (int i = 0; i < questionList.size(); i++) {

				Map questMap = (Map) questionList.get(i);

				if (questMap.get("ChallengeQuesDesc").toString()
						.equalsIgnoreCase(question)) {

					questionid = Integer.parseInt(questMap.get(
							"ChallengeQuesID").toString());
					return questionid;
				}
			}
		}
		return questionid;
	}
	
	public void resetPasswordInSecureOne(IContext ctx) throws Exception{
		logger.debug("Goint to reset password");
		try {
			ctx.remove("IsResetPassword");
			ctx.remove("AccountEmail_Secure");
			String endPointUrl = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,
					endPointUrl);
			
			String loginID = ctx.get("LoginID") != null ? ctx.get("LoginID").toString() : "";
			logger.debug("Resetting password for requested login");
			
			if (!"".equals(ctx.get("LoginID").toString())) {
				
				boolean resetPassword= bindingStub.resetPassword(loginID);
				logger.debug("Reset Password --- > " + resetPassword);
				if(resetPassword){
					 logger.debug("Going to get User Details");
					 UserDetails ud= bindingStub.getUserDetail(loginID);
					 String accountEmail = ud.getEmail_add();
					 ctx.put("AccountEmail_Secure", accountEmail);
					 ctx.put("IsResetPassword", "Yes");
					 logger.debug("User Details has been got");
				}
			}
			
		} catch (Exception e) {
			LawyersUtils.populateError(ctx, "AccountEmail",
					"Please contact 1-877-569-4111 for assistance");
			logger.error("Unable to reset password", e);
		}
		
	}
	
	public static void updateSubproducerStatus(Context ctx)
	{	
		try	
		{
			String ProducerCode="";
			String SubProducerAccess="";
			String endPointUrl = null;
			endPointUrl = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".webserviceurl.SecureOne");

			if (endPointUrl == null)
				return;

			SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
			bindingStub._setProperty(bindingStub.ENDPOINT_ADDRESS_PROPERTY,endPointUrl);

			ProducerCode=(String) ctx.get("ProducerCode");
			SubProducerAccess=(String) ctx.get("SubProducerAccess");
			
				String result = bindingStub.updateSubProduerStatus(ProducerCode, SubProducerAccess);
			
			if(result.equalsIgnoreCase("true")){
				logger.debug("Subproducer status updated");
			}
			else{
				logger.warn("Subproducer status was not updated");
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}	
	}

}
