package com.userbo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mail.MailSender;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;


public class EPICDataUpdate {
	
	private static InetLogger logger = InetLogger.getInetLogger(EPICDataUpdate.class);
	
	public static void main(String[] args) {
		try {
			Context ctx = new Context();
			insertEPICData(ctx);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Unexpected error", e);
		}
	}
	
	public static void insertEPICData(Context ctx){
		getEPICData(ctx);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public static void getEPICData(Context ctx){
		Context newCtx = null;
		String epicType = "";
		String epicData = "";
		try {
			logger.debug("Starting getEPICData.....");
			
			newCtx = new Context();

			newCtx.setProject(ctx.getProject());
			newCtx.putAll(ctx);
			newCtx.put("MarketableProductKey", 2);

			List policyAddressList = (List)SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetInsuredEPICData", newCtx);
			Map policyAddressData = (Map)policyAddressList.get(0);
			
			HashMap insertData = new HashMap();
			insertData.putAll(policyAddressData);
			
			if(insertData.get("TransactionTypeKey") != null && !"".equals(insertData.get("TransactionTypeKey")) && Integer.parseInt(insertData.get("TransactionTypeKey").toString()) > 6){
				if(insertData.get("isFinancialEndirsement") == null || "".equals(insertData.get("isFinancialEndirsement")) || "N".equals(insertData.get("isFinancialEndirsement"))){
					return;
				}
			}
			
			int insuredNameLength = SystemProperties.getInstance().getInt("appl.LawyersIns.epic.insuredNameLength");
			int insuredAddress1Length = SystemProperties.getInstance().getInt("appl.LawyersIns.epic.insuredAddress1Length");
			int insuredAddress2Length = SystemProperties.getInstance().getInt("appl.LawyersIns.epic.insuredAddress2Length");
			int insuredCityLength = SystemProperties.getInstance().getInt("appl.LawyersIns.epic.insuredCityLength");
			int insuredCountyLength = SystemProperties.getInstance().getInt("appl.LawyersIns.epic.insuredCountyLength");
			
			String insuredName = insertData.get("AccountName").toString();
			if(insuredName.length() > insuredNameLength){
				insuredName = insuredName.substring(0, insuredName.substring(0,insuredNameLength).lastIndexOf(" "));
			}
			insertData.put("AccountName", insuredName);
			
			String insuredAddress1 = (insertData.get("Street") == null || insertData.get("Street").toString().isEmpty()) ? insertData.get("Address1").toString() : insertData.get("Street").toString();
			if(insuredAddress1.length() > insuredAddress1Length){
				insuredAddress1 = insuredAddress1.substring(0, insuredAddress1.substring(0,insuredAddress1Length).lastIndexOf(" "));
			}
			insertData.put("Address1", insuredAddress1);
			
			String insuredAddress2 = (insertData.get("Address2") == null || insertData.get("Address2").toString().isEmpty()) ? "" : insertData.get("Address2").toString();
			if(insuredAddress2.length() > insuredAddress2Length){
				insuredAddress2 = insuredAddress2.substring(0, insuredAddress2.substring(0,insuredAddress2Length).lastIndexOf(" "));
			}
			insertData.put("Address2", insuredAddress2);
			
			String insuredCity = (insertData.get("City") == null || insertData.get("City").toString().isEmpty()) ? "" : insertData.get("City").toString();
			if(insuredCity.length() > insuredCityLength){
				insuredCity = insuredCity.substring(0, insuredCity.substring(0,insuredCityLength).lastIndexOf(" "));
			}
			insertData.put("City", insuredCity);
			
			String insuredCounty = (insertData.get("CountyDesc") == null || insertData.get("CountyDesc").toString().isEmpty()) ? "" : insertData.get("CountyDesc").toString();
			if(insuredCounty.length() > insuredCountyLength){
				insuredCounty = insuredCounty.substring(0, insuredCounty.substring(0,insuredCountyLength).lastIndexOf(" "));
			}
			insertData.put("County", insuredCounty);
			
			String insuredContactPerson = (insertData.get("ContactPerson") == null || insertData.get("ContactPerson").toString().isEmpty()) ? "" : insertData.get("ContactPerson").toString();
			if(!insuredContactPerson.isEmpty() && insuredContactPerson.contains(" ")){
				insertData.put("ContactPersonFirst", insuredContactPerson.substring(0, insuredContactPerson.indexOf(" ")));
				insertData.put("ContactPersonLast", insuredContactPerson.substring(insuredContactPerson.indexOf(" ") + 1, insuredContactPerson.length()));
			} else {
				insertData.put("ContactPersonFirst", insuredContactPerson);
				insertData.put("ContactPersonLast", insuredContactPerson);
			}
			
			String policyEffectiveDate = insertData.get("PolicyEffectiveDate").toString();
			policyEffectiveDate = policyEffectiveDate.substring(0,10);
			insertData.put("PolicyEffectiveDate", policyEffectiveDate);
			
			String transactionDate = "";
			try {
				Calendar calendarG = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Date dateG = new SimpleDateFormat("MM/dd/yyyy").parse(insertData.get("PolicyExpirationDate").toString());
			    calendarG.setTime(dateG);
				insertData.put("PolicyExpirationDate", sdf.format(calendarG.getTime()));
				
				if(insertData.get("TransactionTypeKey") != null && !"".equals(insertData.get("TransactionTypeKey")) && Integer.parseInt(insertData.get("TransactionTypeKey").toString()) > 6){
					transactionDate = insertData.get("EndorsementIssuedDate").toString();
					dateG = new SimpleDateFormat("yyyy-MM-dd").parse(transactionDate);
				} else {
					transactionDate = insertData.get("IssuedDate").toString();
					dateG = new SimpleDateFormat("MM/dd/yyyy").parse(transactionDate);
				}
			    
			    calendarG.setTime(dateG);
				insertData.put("TransactionDate", sdf.format(calendarG.getTime()));

				Calendar calendarEff = Calendar.getInstance();
				Date dateEff = new SimpleDateFormat("yyyy-MM-dd").parse(policyEffectiveDate);
				calendarEff.setTime(dateEff);
				
				String transactionMonth = "";
				if(calendarG.after(calendarEff)){
					insertData.put("ARDueDate", sdf.format(calendarG.getTime()));
					transactionMonth = (1900+dateG.getYear()) + String.format("%02d", (dateG.getMonth()+1));
				} else {
					insertData.put("ARDueDate", sdf.format(calendarEff.getTime()));
					transactionMonth = (1900+dateEff.getYear()) + String.format("%02d", (dateEff.getMonth()+1));
				}
				insertData.put("TransactionMonth", transactionMonth);
				
				
			} catch(Exception e) {
				logger.error("Unexpected error", e);
			}

			insertData.put("OldAccountId", (insertData.get("oldAccountId") == null ? "" : insertData.get("oldAccountId").toString()));
			insertData.put("AccountEmail", insertData.get("AccountEmail").toString());
			insertData.put("Zip", insertData.get("Zip").toString().replaceAll("\\D+",""));
			insertData.put("WorkPhone", insertData.get("WorkPhone").toString().replaceAll("\\D+",""));
			
//Premium TAX start

			String invoicedPremium = insertData.get("InvoicedPremium").toString();
			invoicedPremium = invoicedPremium.substring(0, invoicedPremium.indexOf("."));
			
			if(insertData.get("TotalPolicyTaxAmount") != null && !"".equals(insertData.get("TotalPolicyTaxAmount"))) {
				
				String totalPolicyTaxAmount = insertData.get("TotalPolicyTaxAmount").toString();
				totalPolicyTaxAmount = totalPolicyTaxAmount.substring(0, totalPolicyTaxAmount.indexOf("."));
				if(Integer.parseInt(totalPolicyTaxAmount) > 0) {
					insertData.put("EpicTaxTransactionCode", "SSTX");
					insertData.put("TotalPolicyTaxAmount", Integer.parseInt(totalPolicyTaxAmount));
					invoicedPremium = "" + (Integer.parseInt(invoicedPremium) - Integer.parseInt(totalPolicyTaxAmount));
				}
			}
			insertData.put("EstimatedPremium", Integer.parseInt(invoicedPremium));
			insertData.put("LineEstimatedPremium", Integer.parseInt(invoicedPremium));
			insertData.put("LineEstimatedCommission", Math.round(Integer.parseInt(invoicedPremium) * (SystemProperties.getInstance().getFloat("appl.LawyersIns.epic.policy.defaultvalue.agencyCommissionPercent")/100)));

			if(insertData.get("PolicyStatusDesc") != null && !"".equals(insertData.get("PolicyStatusDesc")) && "New".equals(insertData.get("PolicyStatusDesc"))){
				insertData.put("PolicyStatusCode", "NEW");
			} else if(insertData.get("PolicyStatusDesc") != null && !"".equals(insertData.get("PolicyStatusDesc")) && "Renew".equals(insertData.get("PolicyStatusDesc"))){
				insertData.put("PolicyStatusCode", "REN");
			}
			
			if(insertData.get("TransactionTypeKey") != null && !"".equals(insertData.get("TransactionTypeKey")) && Integer.parseInt(insertData.get("TransactionTypeKey").toString()) < 6) {
				
				insertData.put("TransactionAmount", Integer.parseInt(invoicedPremium));
				
				if(insertData.get("PolicyStatusDesc") != null && !"".equals(insertData.get("PolicyStatusDesc")) && "New".equals(insertData.get("PolicyStatusDesc"))){
					insertData.put("EpicTransactionCode", "NEWB");
				} else if(insertData.get("PolicyStatusDesc") != null && !"".equals(insertData.get("PolicyStatusDesc")) && "Renew".equals(insertData.get("PolicyStatusDesc"))){
					insertData.put("EpicTransactionCode", "RENB");
				}
			} else if(insertData.get("TransactionTypeKey") != null && !"".equals(insertData.get("TransactionTypeKey")) && Integer.parseInt(insertData.get("TransactionTypeKey").toString()) > 6){
//Endorsement Start

				String premiumVariation = insertData.get("PremiumVariation").toString();
				premiumVariation = premiumVariation.substring(0, premiumVariation.indexOf("."));
				
				if(insertData.get("TaxVariation") != null && !"".equals(insertData.get("TaxVariation"))) {
					
					String taxVariation = insertData.get("TaxVariation").toString();
					taxVariation = taxVariation.substring(0, taxVariation.indexOf("."));
					if(Integer.parseInt(taxVariation) > 0) {
						insertData.put("EpicTaxTransactionCode", "SSTX");
						insertData.put("TotalPolicyTaxAmount", Integer.parseInt(taxVariation));
						premiumVariation = "" + (Integer.parseInt(premiumVariation) - Integer.parseInt(taxVariation));
					}
				}

				insertData.put("TransactionAmount", Integer.parseInt(premiumVariation));
				
				if(insertData.get("isFinancialEndirsement") != null && !"".equals(insertData.get("isFinancialEndirsement")) && "Y".equals(insertData.get("isFinancialEndirsement"))){
					
					if(insertData.get("TransactionTypeDesc") != null && !"".equals(insertData.get("TransactionTypeDesc")) && "Cancellation_Endorsement".equals(insertData.get("TransactionTypeDesc"))){
						insertData.put("EpicTransactionCode", "CANC");
					} else if(insertData.get("TransactionTypeDesc") != null && !"".equals(insertData.get("TransactionTypeDesc")) && "Reinstatement_Endorsement".equals(insertData.get("TransactionTypeDesc"))){
						insertData.put("EpicTransactionCode", "REIN");
					} else {
						if(Integer.parseInt(premiumVariation) > 0) {
							insertData.put("EpicTransactionCode", "+END");
						} else {
							insertData.put("EpicTransactionCode", "-END");
						}
					}
				} else{
					insertData.put("EpicTransactionCode", "END");
				}

//Endorsement End
			}
			
//SubProducer Start
			if(insertData.get("ProducerCode") != null && !"".equals(insertData.get("ProducerCode")) && !"Y".equals(insertData.get("IsDriect"))){
				if(insertData.get("PolicyStatusDesc") != null && !"".equals(insertData.get("PolicyStatusDesc")) && "Renew".equals(insertData.get("PolicyStatusDesc"))){
					insertData.put("SubProducerCommission", Float.parseFloat(insertData.get("RenewalCommission").toString()));
				} else {
					insertData.put("SubProducerCommission", Float.parseFloat(insertData.get("NewCommission").toString()));
				}
				insertData.put("ProducerCode_McGowan", (insertData.get("ProducerCode_McGowan") == null || insertData.get("ProducerCode_McGowan").toString().isEmpty()) ? "Test" : insertData.get("ProducerCode_McGowan").toString());
				insertData.put("BillToEntityType", "Broker");
				insertData.put("BillingMode", "AgencyBilled");
				insertData.put("BillingModeValue", 0);
			} else {
				insertData.put("BillToEntityType", "Client");
				insertData.put("BillingMode", "DirectBilled");
				insertData.put("BillingModeValue", 1);
			}
//SubProducer end
			
			
			String webServiceURL = SystemProperties.getInstance().getString("appl.LawyersIns.epic.webserviceurl");
			String userAgentKey = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
			String policyKey = ""+newCtx.get("PolicyKey");

///ClientData
			epicType = "Client API";
			
			logger.debug("Insured " + epicType + " Data is going to set in request");
	
			JSONObject messageBodyJsonObject = new JSONObject();
			String subURL = new EPICDataUpdate().setClientData(messageBodyJsonObject, insertData);
			
			
			epicData = messageBodyJsonObject.toString();
			String result = authorizeTransaction(messageBodyJsonObject, webServiceURL,  subURL, userAgentKey);
			

			if(result.startsWith("#Sucesss#")) {
				result = result.replace("#Sucesss#", "");
				String resultList[] = result.replace("\"", "").replace("\"", "").replace("(", "").replace(")", "").split(",");
				int epicAccountId = Integer.parseInt(resultList[0].trim());
				String epicLookupCode = resultList[1].trim();
				insertData.put("EPICAccountId", epicAccountId);
				insertData.put("EPICLookupCode", epicLookupCode);
				
				setSucesssLogsData(newCtx, epicType, epicData, epicAccountId, epicLookupCode);
///PolicyData
				if(insertData.get("TransactionTypeKey") != null && !"".equals(insertData.get("TransactionTypeKey")) && Integer.parseInt(insertData.get("TransactionTypeKey").toString()) < 6){
					epicType = "Policy API";
					
					logger.debug("Insured " + epicType + " Data is going to set in request");
					
					messageBodyJsonObject = new JSONObject();
					subURL = new EPICDataUpdate().setPolicyData(messageBodyJsonObject, insertData);
		
					
					epicData = messageBodyJsonObject.toString();
					result = authorizeTransaction(messageBodyJsonObject, webServiceURL,  subURL, userAgentKey);
					
				} else {
					result = "#Sucesss#";
				}
				
				if(result.startsWith("#Sucesss#")) {
					if(insertData.get("TransactionTypeKey") != null && !"".equals(insertData.get("TransactionTypeKey")) && Integer.parseInt(insertData.get("TransactionTypeKey").toString()) < 6){
						result = result.replace("#Sucesss#", "");
						epicAccountId = Integer.parseInt(result.trim());
						epicLookupCode = "";
						
						setSucesssLogsData(newCtx, epicType, epicData, epicAccountId, epicLookupCode);
					}
///TransactionData
					if(insertData.get("BillingMode") != null && !"".equals(insertData.get("BillingMode")) && "DirectBilled".equals(insertData.get("BillingMode"))) {
						epicType = "Transaction API";
						
						logger.debug("Insured " + epicType + " Data is going to set in request");
						
						messageBodyJsonObject = new JSONObject();
						subURL = new EPICDataUpdate().setTransactionData(messageBodyJsonObject, insertData);
			
						
						epicData = messageBodyJsonObject.toString();
						result = authorizeTransaction(messageBodyJsonObject, webServiceURL,  subURL, userAgentKey);
						
						
						if(result.startsWith("#Sucesss#")) {
							result = result.replace("#Sucesss#", "").replace("[", "").replace("]", "");
							if(result.contains(",")) {
								result = result.split(",")[0];
							}
							epicAccountId = Integer.parseInt(result.trim());
							epicLookupCode = "";
							
							setSucesssLogsData(newCtx, epicType, epicData, epicAccountId, epicLookupCode);
						} else {
							result = result.replace("#Failed#", "");
							setErrorLogsData(result, policyKey, newCtx, epicType, epicData);
						}
					}
				} else {
					result = result.replace("#Failed#", "");
					setErrorLogsData(result, policyKey, newCtx, epicType, epicData);
				}
			} else {
				result = result.replace("#Failed#", "");
				setErrorLogsData(result, policyKey, newCtx, epicType, epicData);
			}
		}catch (Exception e) {
			// TODO: handle exception
			newCtx.put("EPICType", epicType);
			newCtx.put("EPICData", epicData);
			newCtx.put("EPICComment", e.getMessage());
			try {
				sendEPICError((Context)newCtx);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("Exception occured while sendEPICError" + e.getMessage());
				logger.error("Unexpected error", e1);
			}
			logger.error("Unexpected error", e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public String setClientData(JSONObject messageBodyJsonObject, HashMap insertData) throws Exception {

		JSONObject emptyJsonObject = new JSONObject();
		
		JSONObject addressJsonObject = new JSONObject();
		addressJsonObject.put("city", insertData.get("City"));//City
		addressJsonObject.put("countryCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.client.defaultvalue.countryCode"));//Default Value
		addressJsonObject.put("county", insertData.get("County"));//CountyDesc
		addressJsonObject.put("stateOrProvinceCode", insertData.get("StateCode"));//StateCode
		addressJsonObject.put("street1", insertData.get("Address1"));//Address1
		addressJsonObject.put("street2", insertData.get("Address2"));//Address2
		addressJsonObject.put("zipOrPostalCode", insertData.get("Zip"));//Zip
		
		JSONObject structureJsonObject = new JSONObject();
		structureJsonObject.put("agencyCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.client.defaultvalue.agencyCode"));//Default Value
		structureJsonObject.put("branchCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.client.defaultvalue.branchCode"));//Default Value
		
		JSONArray structureJsonArray = new JSONArray();
		structureJsonArray.put(structureJsonObject);
		
		JSONObject accountValueJsonObject = new JSONObject();
		accountValueJsonObject.put("address", addressJsonObject);
		accountValueJsonObject.put("structure", structureJsonArray);
		accountValueJsonObject.put("accountEmail", insertData.get("AccountEmail"));//AccountEmail
		accountValueJsonObject.put("number", insertData.get("WorkPhone"));//WorkPhone
		
		JSONObject formatOptionJsonObject = new JSONObject();
		formatOptionJsonObject.put("optionName", "Business");//Default Value Business
		formatOptionJsonObject.put("value", 1);//Default Value 
		formatOptionJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject

		JSONArray servicingContactsJsonArray = new JSONArray();
		
		JSONObject servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "MORFR1");//Default Value 
		servicingContactsJsonObject.put("role", "Account Manager");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "NIEKY1");//Default Value 
		servicingContactsJsonObject.put("role", "Producer");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "");//Default Value 
		servicingContactsJsonObject.put("role", "Customer Service Rep");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "");//Default Value 
		servicingContactsJsonObject.put("role", "Underwriter");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "");//Default Value 
		servicingContactsJsonObject.put("role", "Renewal Underwriter");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "");//Default Value 
		servicingContactsJsonObject.put("role", "Associate Undrwrtr");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "");//Default Value 
		servicingContactsJsonObject.put("role", "Undrwrtng Assistant");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "");//Default Value 
		servicingContactsJsonObject.put("role", "Admin Assistant");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		servicingContactsJsonObject = new JSONObject();
		servicingContactsJsonObject.put("employeeLookupCode", "");//Default Value 
		servicingContactsJsonObject.put("role", "Regional Sales Manager");//Default Value 
		servicingContactsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		servicingContactsJsonArray.put(servicingContactsJsonObject);
		
		JSONObject billingValueJsonObject = new JSONObject();
		if(insertData.get("BillingMode") != null && !"".equals(insertData.get("BillingMode")) && "AgencyBilled".equals(insertData.get("BillingMode"))) {
			billingValueJsonObject.put("brokerLookupCode", insertData.get("ProducerCode_McGowan"));//ProducerCode_McGowan
		} else {
			billingValueJsonObject.put("brokerLookupCode", "");//Default Value
		}
		billingValueJsonObject.put("invoiceLayout", "Protexure MPA Broker Invoice");//Default Value
		billingValueJsonObject.put("invoicePageBreak", "Policy");//Default Value
		billingValueJsonObject.put("serviceChargeFlag", false);//Default Value
		billingValueJsonObject.put("billBrokerNet", false);//Default Value
		billingValueJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		messageBodyJsonObject.put("billingValue", billingValueJsonObject);
		
		if(insertData.get("OldAccountId") != null && !"".equals(insertData.get("OldAccountId"))){
			JSONObject categoriesHistoryValueJsonObject = new JSONObject();
			categoriesHistoryValueJsonObject.put("priorAccountId", insertData.get("OldAccountId"));//OldAccountID
			messageBodyJsonObject.put("categoriesHistoryValue", categoriesHistoryValueJsonObject);
		}

		messageBodyJsonObject.put("accountName", insertData.get("AccountName"));//AccountName
		messageBodyJsonObject.put("businessName", insertData.get("AccountName"));//AccountName
		messageBodyJsonObject.put("primaryContactFirst", insertData.get("ContactPersonFirst"));//ContactPerson
		messageBodyJsonObject.put("primaryContactLast", insertData.get("ContactPersonLast"));//ContactPerson
		messageBodyJsonObject.put("primaryContactEmail", insertData.get("AccountEmail"));//AccountEmail
		messageBodyJsonObject.put("primaryContactNumber", insertData.get("WorkPhone"));//WorkPhone
		messageBodyJsonObject.put("accountValue", accountValueJsonObject);
		messageBodyJsonObject.put("servicingContacts", servicingContactsJsonArray);
		messageBodyJsonObject.put("formatOption", formatOptionJsonObject);
		
		return SystemProperties.getInstance().getString("appl.LawyersIns.epic.suburl.insertclient");
	}
	
	@SuppressWarnings("rawtypes")
	public String setPolicyData(JSONObject messageBodyJsonObject, HashMap insertData) throws Exception {

		JSONObject emptyJsonObject = new JSONObject();
		
		JSONObject commissionsJsonObject = new JSONObject();
		commissionsJsonObject.put("commissionPercent", SystemProperties.getInstance().getInt("appl.LawyersIns.epic.policy.defaultvalue.commissionPercent"));//Default Value
		commissionsJsonObject.put("commissionType", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.commissionType"));//Default Value
		commissionsJsonObject.put("flag", SystemProperties.getInstance().getInt("appl.LawyersIns.epic.policy.defaultvalue.flag"));//Default Value
		commissionsJsonObject.put("lookupCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.lookupCode"));//Default Value
		commissionsJsonObject.put("producerBrokerCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.producerBrokerCode"));//Default Value
		commissionsJsonObject.put("productionCredit", SystemProperties.getInstance().getInt("appl.LawyersIns.epic.policy.defaultvalue.productionCredit"));//Default Value
		commissionsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		JSONArray commissionsJsonArray = new JSONArray();
		commissionsJsonArray.put(commissionsJsonObject);
		
		if(insertData.get("BillToEntityType") != null && !"".equals(insertData.get("BillToEntityType")) && "Broker".equals(insertData.get("BillToEntityType"))) {
			commissionsJsonObject = new JSONObject();
			commissionsJsonObject.put("commissionPercent", insertData.get("SubProducerCommission"));//NewCommission/RenewalCommission
			commissionsJsonObject.put("commissionType", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.subProducerCommissionType"));//Default Value
			commissionsJsonObject.put("producerBrokerCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.subProducerBrokerCode"));//Default Value
			commissionsJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
			commissionsJsonArray.put(commissionsJsonObject);
		}
		
		JSONObject producerBrokerCommissionTermOptionJsonObject = new JSONObject();
		producerBrokerCommissionTermOptionJsonObject.put("optionName", "SingleTermCommission");//?
		producerBrokerCommissionTermOptionJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject

		JSONArray producerBrokerScheduleTermItemsJsonArray = new JSONArray();
		
		JSONObject producerBrokerCommissionsValueJsonObject = new JSONObject();
		producerBrokerCommissionsValueJsonObject.put("commissions", commissionsJsonArray);
		producerBrokerCommissionsValueJsonObject.put("producerBrokerCommissionTermOption", producerBrokerCommissionTermOptionJsonObject);
		producerBrokerCommissionsValueJsonObject.put("producerBrokerScheduleTermItems", producerBrokerScheduleTermItemsJsonArray);
		producerBrokerCommissionsValueJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		
		JSONObject billingModeOptionJsonObject = new JSONObject();
		billingModeOptionJsonObject.put("optionName", "AgencyBilled");//Default Value AgencyBilled  insertData.get("BillingMode"));//ProducerCode
		billingModeOptionJsonObject.put("value", 0);//Default Value AgencyBilled   insertData.get("BillingModeValue"));//ProducerCode
		billingModeOptionJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject

		messageBodyJsonObject.put("accountId", insertData.get("EPICAccountId"));//EPICAccountId
		messageBodyJsonObject.put("agencyCommissionPercent", SystemProperties.getInstance().getInt("appl.LawyersIns.epic.policy.defaultvalue.agencyCommissionPercent"));//Default Value
		messageBodyJsonObject.put("agencyCommissionType", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.agencyCommissionType"));//Default Value
		messageBodyJsonObject.put("agencyCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.agencyCode"));//Default Value
		messageBodyJsonObject.put("branchCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.branchCode"));//Default Value
		messageBodyJsonObject.put("departmentCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.departmentCode"));//Default Value
		messageBodyJsonObject.put("description", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.description"));//Default Value
		messageBodyJsonObject.put("effectiveDate", insertData.get("PolicyEffectiveDate"));//EffectiveDate
		messageBodyJsonObject.put("expirationDate", insertData.get("PolicyExpirationDate"));//ExpirationDate
		messageBodyJsonObject.put("estimatedPremium", insertData.get("EstimatedPremium"));//InvoicedPremium
		messageBodyJsonObject.put("lineEstimatedPremium", insertData.get("LineEstimatedPremium"));//InvoicedPremium
		messageBodyJsonObject.put("lineEstimatedCommission", insertData.get("LineEstimatedCommission"));//InvoicedPremium
		messageBodyJsonObject.put("statusCode", insertData.get("PolicyStatusCode"));//Policy Status
		messageBodyJsonObject.put("policyNumber", insertData.get("PolicyNumber")); //PolicyNumber
		messageBodyJsonObject.put("issuingLocationCode", insertData.get("StateCode")); //State code
		messageBodyJsonObject.put("issuingCompanyLookupCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.issuingCompanyLookupCode"));//Default Value
		messageBodyJsonObject.put("lineTypeCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.lineTypeCode"));//Default Value
		messageBodyJsonObject.put("policyTypeCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.policyTypeCode"));//Default Value
		messageBodyJsonObject.put("profitCenterCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.profitCenterCode"));//Default Value
		messageBodyJsonObject.put("premiumPayableLookupCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.premiumPayableLookupCode"));//Default Value
		messageBodyJsonObject.put("premiumPayableTypeCode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.premiumPayableTypeCode"));//Default Value
		messageBodyJsonObject.put("extensionData", emptyJsonObject);//emptyJsonObject
		messageBodyJsonObject.put("producerBrokerCommissionsValue", producerBrokerCommissionsValueJsonObject);
		messageBodyJsonObject.put("billingModeOption", billingModeOptionJsonObject);
		
		return SystemProperties.getInstance().getString("appl.LawyersIns.epic.suburl.insertpolicy");
	}
	
	@SuppressWarnings("rawtypes")
	public String setTransactionData(JSONObject messageBodyJsonObject, HashMap insertData) throws Exception {
		
		if(insertData.get("BillToEntityType") != null && !"".equals(insertData.get("BillToEntityType")) && "Broker".equals(insertData.get("BillToEntityType"))) {
			messageBodyJsonObject.put("billToEntity", insertData.get("ProducerCode_McGowan"));//ProducerCode_McGowan
		} else {
			messageBodyJsonObject.put("billToEntity", insertData.get("EPICLookupCode"));//EPICLookupCode
		}
		messageBodyJsonObject.put("billFromClient", insertData.get("EPICLookupCode"));//EPICLookupCode
		messageBodyJsonObject.put("epicAccountId", insertData.get("EPICAccountId"));//EPICAccountId
		messageBodyJsonObject.put("billingMode", SystemProperties.getInstance().getString("appl.LawyersIns.epic.transaction.defaultvalue.billingMode"));//Default Value
		messageBodyJsonObject.put("billToEntityType", insertData.get("BillToEntityType"));//Client-Broker
		messageBodyJsonObject.put("epicTransactionCode", insertData.get("EpicTransactionCode"));//PolicyTransactionType
		messageBodyJsonObject.put("billAtPolicy", true);//Default Value
		messageBodyJsonObject.put("transactiondate", insertData.get("TransactionDate"));//TransactionDate
		messageBodyJsonObject.put("aRduedate", insertData.get("TransactionDate"));//TransactionDate
		messageBodyJsonObject.put("generateInvoice", false);//Default Value
		messageBodyJsonObject.put("amount", insertData.get("TransactionAmount"));//InvoicedPremium or PremiumVariation
		
		if(insertData.get("EpicTaxTransactionCode") != null && !"".equals(insertData.get("EpicTaxTransactionCode")) && "SSTX".equals(insertData.get("EpicTaxTransactionCode"))) {
			messageBodyJsonObject.put("epicTaxTransactionCode", insertData.get("EpicTaxTransactionCode"));//EpicTaxTransactionCode
			messageBodyJsonObject.put("taxAmount", insertData.get("TotalPolicyTaxAmount"));//TotalPolicyTaxAmount
//			messageBodyJsonObject.put("description", "State Surcharge");//State Surcharge  Default Value
		} else {
			messageBodyJsonObject.put("description", SystemProperties.getInstance().getString("appl.LawyersIns.epic.policy.defaultvalue.description"));//Default Value
		}
		messageBodyJsonObject.put("accountingMonth", insertData.get("TransactionMonth"));//TransactionMonth
		messageBodyJsonObject.put("productionMonth", insertData.get("TransactionMonth"));//TransactionMonth
		messageBodyJsonObject.put("policyNumberToApply", insertData.get("PolicyNumber")); //PolicyNumber
		
		return SystemProperties.getInstance().getString("appl.LawyersIns.epic.suburl.inserttransaction");
	}
	
	@SuppressWarnings("deprecation")
	public static String authorizeTransaction(JSONObject authRequest, String webServiceURL,  String subURL, String userAgentKey) {
		PostMethod post = null; // Execute http request
		try {
    	
		    post = new PostMethod(webServiceURL + subURL);
		    post.setRequestHeader("Content-Type", MediaType.APPLICATION_JSON);
		    post.setRequestHeader("User-Agent", userAgentKey);
		    post.setRequestBody(authRequest.toString());

		    HttpClient httpclient = new HttpClient();

		    int result = httpclient.executeMethod(post);
		    
    		logger.debug(result);
		logger.debug("EPIC service response received");
		    
    		if (result == 201 || result == 200) {
		    	return "#Sucesss#" + post.getResponseBodyAsString();
			} else {
		    	return "#Failed#" + post.getResponseBodyAsString();
			}
		    
		} catch (Exception e) {
		    logger.error("Unexpected error", e);
		} finally {
		    post.releaseConnection();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void setSucesssLogsData(IContext newCtx, String epicType, String epicData, int epicAccountId, String epicLookupCode)  throws Exception {
	    newCtx.put("CreatedBy", (newCtx.get("LastUpdateUserID") == null ? 0 : newCtx.get("LastUpdateUserID")));
		newCtx.put("EPICType", epicType);
		newCtx.put("EPICData", epicData);
		newCtx.put("EPICAccountId", epicAccountId);
		newCtx.put("EPICLookupCode", epicLookupCode);
		newCtx.put("EPICStatus", "Sucesss");
		newCtx.put("EPICComment", epicType + " Data Sucesssfully Sent");
		
		insertEPICLogs(newCtx);
	}
	
	@SuppressWarnings("unchecked")
	public static void setErrorLogsData(String result, String policyKey, IContext newCtx, String epicType, String epicData) throws Exception {
    	String error = "";
    	if(isValidJSON(result)) {
	    	JSONObject json = new JSONObject(result);
			json = json.getJSONObject("errors");
			error = json.toString();
    	} else {
			error = result;
    	}
    	

	    newCtx.put("CreatedBy", (newCtx.get("LastUpdateUserID") == null ? 0 : newCtx.get("LastUpdateUserID")));
		newCtx.put("EPICType", epicType);
		newCtx.put("EPICData", epicData);
		newCtx.put("EPICAccountId", null);
		newCtx.put("EPICLookupCode", null);
		newCtx.put("EPICStatus", "Failed");
		newCtx.put("EPICComment", error);
		
		insertEPICLogs(newCtx);
		sendEPICError((Context)newCtx);
	}
	
	public static boolean isValidJSON(String json) {
	    try {
	        new JSONObject(json);
	    } catch (JSONException e) {
	        return false;
	    }
	    return true;
	}
	
	public static void insertEPICLogs(IContext newCtx) throws Exception {
		SqlResources.getSqlMapProcessor(newCtx).insert("EPICLogs.insert", newCtx);
	}
	
	public static void sendEPICError(Context newCtx) throws Exception {
		String toAddress = SystemProperties.getInstance().getString("appl.LawyersIns.epic.error.to.email");
        String ccAddress = SystemProperties.getInstance().getString("appl.LawyersIns.epic.error.cc.email");
        
        logger.debug("Epic Error Mail is going---- ");
        MailSender mail = new MailSender();
        mail.setToAdd(toAddress);
        mail.setCcAdd(ccAddress);
        mail.setSubject("Epic Error");
        mail.setContentType("text/html");
        mail.setBody(generateEPICErrorBody(newCtx));
        mail.sendMail(newCtx);
        logger.debug("Mail has sent---- ");
	}
	
	private static String generateEPICErrorBody(Context newCtx) throws Exception {

		return new StringBuilder(512)
				.append("<table cellpadding=\"5\" cellspacing=\"5\">")
				.append("<tr><td colspan=\"2\">Hi<br/><br/></td></tr>")
				.append("<tr><td colspan=\"2\">The following error are getting in EPIC<br/><br/></td></tr>")
				.append("<tr><td style=\"width:30%\">Law firm Name </td><td><b>:</b>&nbsp;&nbsp;").append(newCtx.get("AccountName")).append("</td></tr>")
				.append("<tr><td style=\"width:30%\">Quote </td><td><b>:</b>&nbsp;&nbsp;").append(newCtx.get("QuoteNumber")).append("</td></tr>")
				.append("<tr><td style=\"width:30%\">Error </td><td><b>:</b>&nbsp;&nbsp;").append(newCtx.get("EPICComment")).append("</td></tr>")
				.append("<tr><td style=\"width:30%\">EPICType </td><td><b>:</b>&nbsp;&nbsp;").append(newCtx.get("EPICType")).append("</td></tr>")
				.append("<tr><td style=\"width:30%\">EPICData </td><td>").append(newCtx.get("EPICData")).append("</td></tr>")
				.append("</table>")
				.toString();

	}

}
