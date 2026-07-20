package com.formgenerator;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.fop.servlet.ServletContextURIResolver;
import org.jdom.Element;

import com.fop.DownloadFOP;
import com.fop.XML2PDF;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.processor.RequestProcessor;
import com.userbo.EndorsementUtilities;
import com.userbo.LawyersConstants;
import com.userbo.LawyersDateUtils;
import com.userbo.LawyersUtilities;
import com.userbo.LawyersUtils;
import com.userbo.LawyersValidationUtils;
import com.userbo.Rating;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;
import com.util.XMLUtils;

public class DownloadForm {
	
	private static InetLogger logger = InetLogger.getInetLogger(DownloadForm.class);
	
	public void testForm(Context ctx, List listFormID, String out) throws Exception {
		String foFile = "";		
		try{
			if(ctx.get("IsNewFiling2020") != null && "Y".equalsIgnoreCase(ctx.get("IsNewFiling2020").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2020//wrapnew.xsl";
			else if(ctx.get("IsNewFiling") != null && "Y".equalsIgnoreCase(ctx.get("IsNewFiling").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//wrapnew.xsl";
            else
            	foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//wrap.xsl";
			
			populateEndorsement(ctx, listFormID);
			fetchDataForXml(ctx);
			new XML2PDF().convertXMLtoStream(foFile, new StringBuffer(generateDataXml(ctx)), listFormID, out);			
			
			/*foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//wrapendorsement.xsl";	
			fetchDataForXmlEndorsement(ctx);			
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(generateDataXml(ctx)), out);*/
		}
		catch(Exception e){
			logger.error("Problem in generating forms for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			throw e;
		}
	}

	public void generateForm(Context ctx, List listFormID, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) throws Exception {
		
		String foFile = "";		
		uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
		try{
			/*boolean isRatingNew = false;
			if(ctx.get("IsNewFiling") != null && "Y".equalsIgnoreCase(ctx.get("IsNewFiling").toString()))
				isRatingNew = true;			
			
            if(isRatingNew)
            	foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//wrapnew.xsl";
            else
            	foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//wrap.xsl";
            */
			
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPolicydatalist", ctx);
			ctx.put("policy_freeform_05", objPoilcy);
			
		
			RuleUtils.executeRule(ctx,"LawyersRule.setNewFilingForPolicyForm");
			
			if("1".equals(ctx.get("CompanyKey").toString()))
			{
            if(ctx.get("IsNewFiling2020") != null && "Y".equalsIgnoreCase(ctx.get("IsNewFiling2020").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2020//wrapnew.xsl";
			else if(ctx.get("IsNewFiling") != null && "Y".equalsIgnoreCase(ctx.get("IsNewFiling").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//wrapnew.xsl";
            else
            	foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//wrap.xsl";
			}
			if("3".equals(ctx.get("CompanyKey").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "ISMIE2022//wrapnew.xsl";
			logger.debug("generateForm--------"+foFile);
			populateEndorsement(ctx, listFormID);
			fetchDataForXml(ctx);
			
			new XML2PDF().convertXMLtoStream(foFile, new StringBuffer(generateDataXml(ctx)), listFormID, out, baseUrl, uriResolver, ctx);
		}
		catch(Exception e){
			logger.error("Problem in generating forms for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			throw e;
		}
	}
	
	public void generateEndorsement(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) throws Exception {
		
		String foFile = "";		
		uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
		try{	
			if("3".equals(ctx.get("CompanyKey").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "ISMIE2022//wrapendorsement.xsl";
			else
				foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//wrapendorsement.xsl";
			logger.debug("generateForm--------"+foFile);	
			//populateEndorsement(ctx, listFormID);
			//new XML2PDF().convertXMLtoStream(foFile, new StringBuffer(generateDataXml(ctx)), listFormID, out, baseUrl, uriResolver, ctx);
			/*fetchDataForXmlEndorsement(ctx);
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(generateDataXml(ctx)), out, baseUrl, uriResolver);	
			fetchDataForXmlEndorsement(ctx);*/
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(generateDataXml(ctx)), out, baseUrl, uriResolver);
		}
		catch(Exception e){
			logger.error("Problem in generating endorsement for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			throw e;
		}
	}
	
	public void generateEndorsement(Context ctx, String out) throws Exception {		
		String foFile = "";		
		try{	
			foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//wrapendorsement.xsl";            
			logger.debug("generateForm--------"+foFile);	
			//populateEndorsement(ctx, listFormID);
			//new XML2PDF().convertXMLtoStream(foFile, new StringBuffer(generateDataXml(ctx)), listFormID, out, baseUrl, uriResolver, ctx);
			//fetchDataForXmlEndorsement(ctx);
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(generateDataXml(ctx)), out);
		}
		catch(Exception e){
			logger.error("Problem in generating endorsement for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			throw e;
		}
	}
	
	public void populateEndorsement(Context ctx, List listFormID) {		
		
		String formIDs = "";
		Map mapEndorsment = new HashMap();
		for(int i=0; i<listFormID.size(); i++)
		{
			Map map = (Map) listFormID.get(i);
			String formID = (String) map.get("FormID");	
			
			//String formID = (String) listFormID.get(i);			
			if("LPL 100-106-2010".equals(formID))
				mapEndorsment.put("LPL1062010", formID);
//			else if("APL - 104 (12/09)".equals(formID))
//				mapEndorsment.put("APL1042009", formID);
//			 if("APL - 105 (12/09)".equals(formID))
//				mapEndorsment.put("APL1052009", formID);
//			else if("APL - AMEND - MT (12/09)".equals(formID))
//				mapEndorsment.put("APLAMENDMT2009", formID);
//			else if("APL - AMEND - ND (12/09)".equals(formID))
//				mapEndorsment.put("APLAMENDND2009", formID);
//			else if("APL-AMEND-SC (12/09)".equals(formID))
//				mapEndorsment.put("APLAMENDSC2009", formID);
//			else if(" APL - AMEND - NH (01/10)".equals(formID))
//				mapEndorsment.put("APLAMENDNH2010", formID);
//			else if("OR - AMEND - 2010".equals(formID))
//				mapEndorsment.put("ORAMEND2010", formID);			
			else if(formID.indexOf("(") < 0)
				mapEndorsment.put(formID.replace("-", "").replace(" ", "").replace("#", ""), formID);
			else if(formID.indexOf("(") > 0)
				mapEndorsment.put(formID.replace("-", "").replace(" ", "").replace("(", "").replace("/", "").replace(")","").replace("#", ""), formID);
			
			formIDs = formIDs + ", " + formID;			
			logger.debug("populateEndorsement......"+formIDs);
		}
		
		if(formIDs.length() > 0)
			ctx.put("Endorsements", formIDs.substring(1, formIDs.length()));
		else
			ctx.put("Endorsements", formIDs);
		
		ctx.put("endorsements_freeform_01", mapEndorsment);
	}
	
	/*public void fetchDataForXmlEnorsment(Context ctx){
		try{
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetPolicyEndorsment", ctx);
			ctx.put("policy_freeform_01", objPoilcy);
			
			Object objUserDetail = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetEndorsment", ctx);
			ctx.put("endorsment_freeform_01",objUserDetail );
			
			fillGeneralData(ctx);
			
		} catch(Exception e){
			logger.error("Problem in fetching data for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			
		}
	}
	
	public void fillGeneralData(Context ctx){
		try{
			Map map = new HashMap();
			List list = new ArrayList();
			map.put("CurrentDate", LawyersDateUtils.getCurrentDate());
			list.add(map);
			ctx.put("general_freeform_01",list);
			
		} catch(Exception e){
			logger.error("Problem in fetching data for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			
		}
	}*/
	
	public void fetchDataForXml(Context ctx) {		
		
		try{
			
			
		Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetPriorPolicyInfoForPolicyForm", ctx);
		ctx.put("policy_freeform_01", objPoilcy);
		
		Object objUserDetail = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetAgentDetail", ctx);
		ctx.put("userdetail_freeform_01",objUserDetail );
		
		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetAddressdetails", ctx);
		ctx.put("address_freeform_01", objAddress);
		
		Map mapFooter =null;
		Object objPolicyFormFooter = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementformgetFormFooter", ctx);
		mapFooter = DownloadFOP.populateFooter(objPolicyFormFooter);
		mapFooter.put("FormInclude", "Y");	
		if(mapFooter != null){
			ctx.put("policyformfooter_freeform_01", mapFooter);
		}
		
		Object objCoveragePoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetQuoteDetail", ctx);
		Map map = null;
		
		if(objCoveragePoilcy != null && objCoveragePoilcy instanceof Map){
			map = (Map)objCoveragePoilcy;
			if(map.get("XmlOutputDatafromRating") != null){
				String outputString = map.get("XmlOutputDatafromRating").toString();
				Element root = XMLUtils.parseXMLRootElement(outputString);					
				Element outputElement = root.getChild("PremiumInfoLW");
				Map out = populateOutput(outputElement);
				map.putAll(out);
				map.remove("XmlOutputDatafromRating");
				
				//To remove cents from all taxes
				map.put("MTTaxAmmount",LawyersUtilities.amountFormatLawyers(LawyersUtils.round(Double.parseDouble(map.get("MTTaxAmmount").toString()))));
				map.put("CountyTaxAmmount",LawyersUtilities.amountFormatLawyers(LawyersUtils.round(Double.parseDouble(map.get("CountyTaxAmmount").toString()))));
				map.put("State1TaxAmmount",LawyersUtilities.amountFormatLawyers(LawyersUtils.round(Double.parseDouble(map.get("State1TaxAmmount").toString()))));
				map.put("State2TaxAmmount",LawyersUtilities.amountFormatLawyers(LawyersUtils.round(Double.parseDouble(map.get("State2TaxAmmount").toString()))));
				
				//To set 1.8% Fire Fighter Surcharge for KY 
				if(ctx.get("address_freeform_01") != null){
					Map mapAdd = (Map) ctx.get("address_freeform_01");
					if(!mapAdd.isEmpty() && mapAdd.get("StateCode") != null && "KY".equals(mapAdd.get("StateCode").toString())){
						double premium = Double.parseDouble(map.get("ActualFinalBaseLimitPremium").toString());
						double fireFighterCharges = (premium * 1.8)/100;
						map.put("FireFighterCharges",LawyersUtilities.amountFormatLawyers(LawyersUtils.round(fireFighterCharges)));	
					}
				}
			}
		}		
		ctx.put("policycoverage_freeform_01", map);	
		
		Object objProLiabIns = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail", ctx);
		ctx.put("professionalliabilityinsdetail_freeform_01", objProLiabIns);
		
		Object listQuoteOption = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementformgetQuoteDetail", ctx);
		String IsClaimExpensesTypeFlag = "N";
		String IsClaimOptionTypeFlag = "N";
		String IsDollarDefenseFlag = "N";
		
		List quoteList = new ArrayList();
		if(listQuoteOption != null && listQuoteOption instanceof List){
			List list = (List)listQuoteOption;
			// For RatingTrace 
			for(int i=0; i<list.size(); i++){
				Map mapQuoteOption = (Map)list.get(i);				
				if(mapQuoteOption.get("IsClaimExpensesType") != null && "Y".equals(mapQuoteOption.get("IsClaimExpensesType").toString()))
					IsClaimExpensesTypeFlag = "Y";	
				if(mapQuoteOption.get("IsClaimOptionType") != null && "Y".equals(mapQuoteOption.get("IsClaimOptionType").toString()))
					IsClaimOptionTypeFlag = "Y";
				if(mapQuoteOption.get("IsDollarDefense") != null && "Y".equals(mapQuoteOption.get("IsDollarDefense").toString()))
					IsDollarDefenseFlag = "Y";
			}
		}	
		
		ctx.put("ClaimExpensesTypeFlag", IsClaimExpensesTypeFlag);	
		ctx.put("ClaimOptionTypeFlag", IsClaimOptionTypeFlag);	
		ctx.put("DollarDefenseFlag", IsDollarDefenseFlag);
		
		Object objFirmData = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetFirmLWFullDetails", ctx);
		ctx.put("firm_freeform_01", objFirmData);
		
		Object objAdditionFormsData = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetAdditionalFormDataLW", ctx);
		ctx.put("additionaldata_freeform_01", objAdditionFormsData);
		
		String skipTax = "";
		if(objFirmData instanceof Map){
			Map firmMap = (Map)objFirmData;
			//Map firmMap = (Map)objListdata.get(0);
			if(firmMap != null && firmMap.size() > 0){				
				skipTax =firmMap.get("IsTaxCalculation") != null ? firmMap.get("IsTaxCalculation").toString() : "";
				firmMap.put("IsTaxCalculation", skipTax) ;
			}
			ctx.put("firm_freeform_01", firmMap) ;
		}
		
		if(ctx.get("StateCode") != null && "ND".equals(ctx.get("StateCode").toString())){
			logger.debug("This is ND state | Check if any  quote is defense inside.");
			ctx.put("RuleRequestType","NDRuleForFormAttachmentPolicyForm");
			ctx.put("PolicyKey_rule",ctx.get("PolicyKey").toString());
			ctx.put("StateCode_rule",ctx.get("StateCode"));
			Object ruleMapObject = SqlResources.getSqlMapProcessor(ctx).findByKey("Account.StateSpecificRulesLawyers_proc", ctx); 
			if(ruleMapObject != null && ruleMapObject instanceof Map){						
				Map ruleMap = (Map)ruleMapObject;
				String IsDefensePaidWithinRuleND = ruleMap.get("IsDefensePaidWithinRuleND") != null ? ruleMap.get("IsDefensePaidWithinRuleND").toString() : "";
				logger.debug("Is IsDefensePaidWithinRuleND ? " + IsDefensePaidWithinRuleND);						
				ctx.put("IsDefensePaidWithinRuleND", IsDefensePaidWithinRuleND);
			}
		}
		
		List CarrearCoverageAttorneyDetails = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetCarrearCoverageAttorneyDetails", ctx);
		ctx.put("CarrearCoverageAttorneyDetails_list_01", CarrearCoverageAttorneyDetails);	
		
		
		getAgentData(ctx);
		
		boolean flag = false ;
		
		if("Y".equals(skipTax)){
			flag = false ;
		}else {
		
			flag = new LawyersValidationUtils().checkForKYMuncipalityTax(ctx);
		}
		
		
		if(flag){
			ctx.put("AttachKentuckyTaxFlag", "Y");
		}else{
			ctx.put("AttachKentuckyTaxFlag", "N");
		}
		
		ctx.remove("loggerbuffer");
		
		}
		catch(Exception e){
			logger.error("Problem in fetching data for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			
		}
		
	}

	
	private void getAgentData(IContext ctx) throws Exception
	{
		String agentNo = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".AgentNo");
		Context newCtx = new Context();
		newCtx.putAll(ctx);
		newCtx.put("AgentNo", agentNo);
		
		Object objUserDetails = SqlResources.getSqlMapProcessor(newCtx).findByKey("SqlStmts.UserStatementManualBoQueriesgetAgentData", newCtx);
		ctx.put("UserDetails_freeform_1", objUserDetails);
		
		
	}

	
	public String generateDataXml(Context ctx) {
		
		String dataXML = "";
		
		try{			
			dataXML = new RequestProcessor().generateResponseXml(ctx,false);			
		}
		catch(Exception e){
			logger.error("Problem in generating data xml for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			logger.error("Reason : " +e.getMessage());
		}
		
		//System.out.println(dataXML);
		return dataXML;
		//return "<response><address_freeform_01><data><AccountName>TestPolicy</AccountName><ContactPerson>Raghraj Singh</ContactPerson><Address1>No 224</Address1><Address2>NT</Address2><City>East Delhi</City><StateDesc>Delhi</StateDesc><Zip>110093</Zip></data></address_freeform_01><catalog><cd><title>Empire Burlesque</title><artist>Bob Dylan</artist><country>USA</country><company>Columbia</company><price>10.90</price><year>1985</year></cd></catalog></response>";  
		
	}
	
	public Map populateOutput(Element element)
	{
    	StringBuffer buffer = new StringBuffer();    	
		List children = element.getChildren();
		if(children == null)
			return new HashMap();
		Map out = new HashMap();
		for(int i=0; i<children.size(); i++)
	    {
			Element child = (Element) children.get(i);
			String name = child.getName();
			String str = child.getTextTrim();						
			if("MTTaxAmmount".equals(name) || "CountyTaxAmmount".equals(name) || "State1TaxAmmount".equals(name) || "State2TaxAmmount".equals(name)) {
				str = String.valueOf(Math.round(Float.parseFloat(str)));
			}
			out.put(name, str);      
	    }  	
		
		return out;
	}
	
	public void fetchDataForXmlEndorsement(Context ctx){
		try{
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetEndorsementDetail", ctx);
			ctx.put("policy_freeform_01", objPoilcy);
			
			Object objCancellationDetail = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetEndorsement", ctx);
			ctx.put("endorsement_freeform_01",objCancellationDetail);
			
			Object objCoveragePoilcyNew = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetEndorsementCoverage", ctx);
			ctx.put("policycoverage_freeform_01", objCoveragePoilcyNew);	
			
			Object objPriorActDetail = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail", ctx);
			ctx.put("professionalliabilityinsdetail_freeform_01", objPriorActDetail);
			
			/*Object objQuote = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetFinalisedCoveragePolicy", ctx);
			ctx.put("quote_freeform_01", objQuote);*/
			
			Object objQuote = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetCalculateLimitDeductiblePremium", ctx);
			ctx.put("quote_freeform_01", objQuote);
				
			if(objQuote != null){
				Map mapQuote = (Map) objQuote;
				ctx.put("PreviousfinilazedTransactionSequence", mapQuote.get("PreviousfinilazedTransactionSequence"));
				ctx.put("ProrataPremium",  mapQuote.get("ProrataPremium"));
				ctx.put("PremiumVariation",  mapQuote.get("PremiumVariation"));
				ctx.put("TaxVariation",  mapQuote.get("TaxVariation"));
			}
			Object objCoveragePoilcyPreExisting = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetEndorsementCoveragePreExisting", ctx);
			ctx.put("pre_existing_policycoverage_freeform_01", objCoveragePoilcyPreExisting);
			int transactionTypeKey=ctx.get("TransactionTypeKey")!= null && !ctx.get("TransactionTypeKey").equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("TransactionTypeKey").toString()):0;
			if(transactionTypeKey==14)
			{
				ctx.put("ListOfAttorneys_list_01",new LawyersUtils().manageAttoneyEndorsementChanges(ctx));
			}		 
			if(transactionTypeKey==15)
			{
				
				List data= (List) SqlResources.getSqlMapProcessor(ctx).select("AttorneyDetailsLW.GetDeletedAttorneysDetailsLW_p",ctx);
				if(data!=null)
				{
				Map map1=new HashMap();
				for(int i=0;i<data.size();i++)
				{
					map1=(HashMap)data.get(i);
					map1.put("TransactionEffectiveDate",ctx.get("endorsementEffectiveDate"));
					map1.put("EffectiveDateOfPolicy",ctx.get("PolicyEffectiveDate"));
					
				}
				}
				ctx.put("ListOfAttorneys_list_01",data);
				
		
			}
			
			
			processEndorsementData(ctx);
			
		} catch(Exception e){
			logger.error("Problem in fetching data for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			
		}
	}
	public void processEndorsementData(Context ctx){
		try{
			//To fill current date
			DecimalFormat  formatter = new DecimalFormat("#0.00");   
			NumberFormat numberFormat =
				    NumberFormat.getCurrencyInstance(Locale.US);
			Map mapGen = new HashMap();
			List listGen = new ArrayList();
			LawyersUtils.setCompanyForPolicy(ctx);
			mapGen.put("CurrentDate", LawyersDateUtils.getCurrentDate());
			listGen.add(mapGen);
			ctx.put("general_freeform_01",listGen);
			Map newPC = null;
			
			
			//Check difference between new premium and existing premium
			Object objQuote1 = ctx.get("quote_freeform_01");
			Object objCoveragePoilcyPreExisting = ctx.get("pre_existing_policycoverage_freeform_01");
			double totalUnearnedPremium=EndorsementUtilities.getEndorsementPremium(ctx);
			double newPremium = 0;
			Map preExistPC = null;
			Map objQuote = null;
			if(ctx.get("TransactionTypeKey") != null && (LawyersConstants.LIMIT_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString()) 
					|| LawyersConstants.DEDUCTIBLE_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())
					||LawyersConstants.PRIORACTDATE_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())
					||LawyersConstants.POLICYEXTENSION_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())
					||LawyersConstants.ADDCHANGEATTORNEY_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())
					||LawyersConstants.DELETEATTORNEY_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())
					
					))
			{
				if(objQuote1 == null) {
					 objQuote1 = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetFinalisedCoveragePolicy", ctx);
					 ctx.put("quote_freeform_01", objQuote1);			
				}
				
				if(objCoveragePoilcyPreExisting == null) {					
					objCoveragePoilcyPreExisting = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetEndorsementCoveragePreExistingFinilized", ctx);
					ctx.put("pre_existing_policycoverage_freeform_01", objCoveragePoilcyPreExisting);			
				}
			}
			if(("Y".equals(ctx.get("isEnforcedPolicy").toString()) && !"Y".equals(ctx.get("anyDecendentPolicy").toString()))
					|| ("Y".equals(ctx.get("isEnforcedPolicy").toString()) && LawyersConstants.POLICYEXTENSION_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString()))
					)
				{
				if(objQuote1 != null){
					objQuote = (Map)objQuote1;
					
					if(ctx.get("NewInvoicedPremium") == null && objQuote.get("ProrataPremium") != null)
						newPremium = Double.parseDouble(objQuote.get("ProrataPremium").toString());
					else if(ctx.get("NewInvoicedPremium") != null)
						newPremium = Double.parseDouble(ctx.get("NewInvoicedPremium").toString());			
					
					if(objQuote.get("Premium") != null){				
						double existingPremium = Double.parseDouble(objQuote.get("Premium").toString());					
						double diffPremium = 0;
						if(existingPremium > newPremium){ // Return Premium
							diffPremium = existingPremium - newPremium;
							ctx.put("PremiumCharged","N");					
						} else if(existingPremium < newPremium){ // Charge Increased Premium
							diffPremium = newPremium - existingPremium;
							ctx.put("PremiumCharged","Y");
						} else if(existingPremium == newPremium){ // No Premium
							diffPremium = newPremium - existingPremium;
							ctx.put("PremiumCharged","S");
						}
	
						ctx.put("PremiumValue","$"+(formatter.format(totalUnearnedPremium)));				
					} else{
						ctx.put("PremiumValue",LawyersUtilities.amountFormatLawyers(newPremium));
						ctx.put("PremiumCharged","Y");
					}
					
				} else if(ctx.get("NewInvoicedPremium") != null){
					newPremium = Double.parseDouble(ctx.get("NewInvoicedPremium").toString());
					ctx.put("PremiumValue",LawyersUtilities.amountFormatLawyers(newPremium));
					ctx.put("PremiumCharged","Y");
				}
			
			}
			else
			{
				ctx.put("PremiumValue","0");
				ctx.put("PremiumCharged","Y");
			}
			//End Check difference between new premium and existing premium
			
			//Check  difference in Limit, Deductible, IsClaimExpensesType, IsClaimOptionType, IsDollarDefense
			//Object objCoveragePoilcyPreExisting = ctx.get("pre_existing_policycoverage_freeform_01");
			Object objCoveragePoilcyNew = ctx.get("policycoverage_freeform_01");
			/*
			 * if(objCoveragePoilcyNew == null) { objCoveragePoilcyNew =
			 * SqlResources.getSqlMapProcessor(ctx).findByKey(
			 * "SqlStmts.UserStatementManualBoQueriesgetEndorsementCoverageNew", ctx);
			 * ctx.put("policycoverage_freeform_01", objCoveragePoilcyNew); }
			 */
			if(objCoveragePoilcyPreExisting == null && objQuote1 != null)
				objCoveragePoilcyPreExisting = objQuote1;
			
			if(objCoveragePoilcyPreExisting != null  && objCoveragePoilcyNew != null){
				preExistPC = (Map) objCoveragePoilcyPreExisting;
				
				if(objCoveragePoilcyNew instanceof List)
					newPC = (Map) ((List) objCoveragePoilcyNew).get(0);
				else
					newPC = (Map) objCoveragePoilcyNew;
				 
				
			
		} 
			if("3".equals(ctx.get("CompanyKey").toString()))
				setEndorsementForIsmiePolicy(ctx,newPC,preExistPC);
			else
				setEndorsementForNonIsmiePolicy(ctx,newPC,preExistPC);
			
		}catch(Exception e){
			logger.error("Problem in fetching data for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			
		}
	}	
	
	public void processIndicationForm(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) throws Exception {
		
		String foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//foxslnew//indication.xsl";	
		//String foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//ratingoutput.xsl";
		try{			
			
			fetchDataForIndication(ctx);
			//fetchDataForRatingOutput(ctx);
			/*StringBuffer xmlData = new StringBuffer(generateDataXml(ctx));
			new XML2PDF().convertPOToPDF(foFile, xmlData, out);*/
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(generateDataXml(ctx)), out, baseUrl, uriResolver);
		}
		catch(Exception e){
			logger.error("Problem in generating forms for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
			throw e;
		}
	}
	
	public static void fetchDataForRatingOutput(Context ctx) throws Exception {
		try{			
			ctx.put("FinalisedQuote_TransactionSequence", ctx.get("TransactionSequence"));
			Object objCoveragePoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetQuoteDetail", ctx);
			ctx.put("policycoverage_freeform_01", objCoveragePoilcy);
			
			if(objCoveragePoilcy != null){
				Map mapCov = (Map) objCoveragePoilcy;
				if(mapCov.get("XmlOutputDatafromRating") != null){
					String xml = (String) mapCov.get("XmlOutputDatafromRating");
					String out = XMLUtils.beautifyXML(xml);
					ctx.put("XmlOutputDatafromRating", out);					
				}
			}			
					
		} catch (Exception e) {
			
		}
	}
	
	
	public static void fetchDataForIndication(Context ctx) throws Exception {
		try{
			
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetPriorPolicyInfoForPolicyForm", ctx);
			ctx.put("policy_freeform_01", objPoilcy);
			
			Object objUserDetail = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetAgentDetail", ctx);
			ctx.put("userdetail_freeform_01",objUserDetail );
			
			Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetAddressdetails", ctx);
			ctx.put("address_freeform_01", objAddress);
			
			Object objFirmData = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetFirmLWFullDetails", ctx);
			ctx.put("firm_freeform_01", objFirmData);
			
			ctx.put("FinalisedQuote_TransactionSequence", ctx.get("TransactionSequence"));
			Object objCoveragePoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetQuoteDetail", ctx);
			ctx.put("policycoverage_freeform_01", objCoveragePoilcy);
			
			Object objPriorPolicyData = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail",ctx);
			ctx.put("priorPolicyData_freeform_01", objPriorPolicyData);
			
			List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementremoveAOPselectAreaOfPracticeLW", ctx);
			
			if(aoplist != null && !aoplist.isEmpty()){
				int totalAopPer = 0;
				int value = 0;
				int div = aoplist.size()/2;
				int i=0;
				Map map = null;
				List fstlist = new ArrayList();
				for(i=0; i<div; i++){
					map = (Map)aoplist.get(i);
					value = Integer.parseInt(map.get("PercentageValue").toString());
					totalAopPer += value;
					if(value == 0)
						map.put("PercentageValue", "");
					fstlist.add(aoplist.get(i));
				}
				List sndlist = new ArrayList();
				for(; i<aoplist.size(); i++){
					map = (Map)aoplist.get(i);
					value = Integer.parseInt(map.get("PercentageValue").toString());
					totalAopPer += value;
					if(value == 0)
						map.put("PercentageValue", "");
					sndlist.add(aoplist.get(i));
				}
				ctx.put("aop_total", totalAopPer);
				ctx.put("aopData_list_01", fstlist);
				ctx.put("aopData_list_02", sndlist);
			}
			
			List claimAgelist = SqlResources.getSqlMapProcessor(ctx).select("ClaimAgeLW.findAllByPartialKey", ctx);
			if(claimAgelist != null){
				Map mapClaimAge= null;
				for(int i=0; i<claimAgelist.size(); i++){
					mapClaimAge = (Map)claimAgelist.get(i);
					ctx.put("ClaimAge_" + (i+1), mapClaimAge.get("Year"));
				}
			}
			
			boolean isRatingNew = false;        
			Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
	        if (objRatingRule != null && objRatingRule instanceof Boolean) {
	        	isRatingNew = (Boolean) objRatingRule;
	        }	   
	        //Fill FTE for new filing only
			if(isRatingNew) {
				if(ctx.get("firm_freeform_01") != null){
					Map mapFirm = (Map) ctx.get("firm_freeform_01");
					ctx.put("NumberOfLawyers", mapFirm.get("NumberOfLawyers"));
					ctx.put("NumberOfLawyers500", mapFirm.get("NumberOfLawyers500"));
					ctx.put("NumberOfLawyers1000", mapFirm.get("NumberOfLawyers1000"));
				}
	        	Rating.populateFTE(ctx);
			}
			
		} catch (Exception e) {
			
		}
	}
	
	public void setEndorsementForNonIsmiePolicy(Context ctx,Map newPC,Map preExistPC)
	{	
		List <String>listFormID = new ArrayList <String> () ;
		try
		{
			DecimalFormat  formatter = new DecimalFormat("#0.00");   
			NumberFormat numberFormat =
				    NumberFormat.getCurrencyInstance(Locale.US);
			// To Check Changes in Limit or Deductible 
			if(newPC!=null && preExistPC!=null)
			{
				if(newPC.get("LimitSequence") != null && preExistPC.get("LimitSequence") != null
						&& !(newPC.get("LimitSequence").toString().equals(preExistPC.get("LimitSequence").toString()))	 						
				  ){
					ctx.put("LPL_109_ENDORSEMENT","Y");
					listFormID.add("109");
				} else if(newPC.get("DeductibleSequence") != null && preExistPC.get("DeductibleSequence") != null
						&& !(newPC.get("DeductibleSequence").toString().equals(preExistPC.get("DeductibleSequence").toString())) 						
				  ){
					ctx.put("LPL_109_ENDORSEMENT","Y");
					listFormID.add("109");
				}
				
				if(ctx.get("TransactionTypeKey") != null && LawyersConstants.LIMIT_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
					// For Defense Expense Inside -> Outside
					if(newPC.get("IsClaimExpensesType") != null && "Y".equals(newPC.get("IsClaimExpensesType").toString())
							&& (preExistPC.get("IsClaimExpensesType") == null  
							|| ((preExistPC.get("IsClaimExpensesType") != null && "N".equals(preExistPC.get("IsClaimExpensesType").toString()))))						
					  ){
						ctx.put("LPL_102_ENDORSEMENT","Y");
						listFormID.add("102");
					}	
					// For Defense Expense Outside -> Inside
					if(newPC.get("IsClaimExpensesType") != null && ("N".equals(newPC.get("IsClaimExpensesType").toString()) || "".equals(newPC.get("IsClaimExpensesType").toString())	)					 
							&& (preExistPC.get("IsClaimExpensesType") != null && "Y".equals(preExistPC.get("IsClaimExpensesType").toString()))						
					  ){
						ctx.put("LPL_136_102_ENDORSEMENT","Y");
						listFormID.add("136");
					}				
					// For Dollar Defense N -> Y
					if(newPC.get("IsDollarDefense") != null && "Y".equals(newPC.get("IsDollarDefense").toString())
							&& (preExistPC.get("IsDollarDefense") == null  
							|| ((preExistPC.get("IsDollarDefense") != null && "N".equals(preExistPC.get("IsDollarDefense").toString()))))					
					  ){
						ctx.put("LPL_103_ENDORSEMENT","Y");
						listFormID.add("103");
					}
					// For Dollar Defense Y -> N
					if((newPC.get("IsDollarDefense") == null || (newPC.get("IsDollarDefense") != null && ("N".equals(newPC.get("IsDollarDefense").toString()) || "".equals(newPC.get("IsDollarDefense").toString()))))
							&& (preExistPC.get("IsDollarDefense") != null && "Y".equals(preExistPC.get("IsDollarDefense").toString()))					
					  ){
						ctx.put("LPL_136_103_ENDORSEMENT","Y");
						listFormID.add("136");
					}
				}
					
				if(ctx.get("TransactionTypeKey") != null && LawyersConstants.DEDUCTIBLE_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
					// For Defense Option Per Claim -> Aggregate
					if(newPC.get("IsClaimOptionType") != null && "Y".equals(newPC.get("IsClaimOptionType").toString())
							&& (preExistPC.get("IsClaimOptionType") == null  
							|| ((preExistPC.get("IsClaimOptionType") != null && "N".equals(preExistPC.get("IsClaimOptionType").toString()))))					
					  ){
						ctx.put("LPL_104_ENDORSEMENT","Y");
						listFormID.add("104");
					}
					// For Defense Option Aggregate -> Per Claim
					if((newPC.get("IsClaimOptionType") == null || (newPC.get("IsClaimOptionType") != null && ("N".equals(newPC.get("IsClaimOptionType").toString()) || "".equals(newPC.get("IsClaimOptionType").toString()) )))
							&& (preExistPC.get("IsClaimOptionType") != null && "Y".equals(preExistPC.get("IsClaimOptionType").toString()))					
					  ){
						ctx.put("LPL_136_104_ENDORSEMENT","Y");
						listFormID.add("136");
					}
				}
				
			}
		//End Check  difference in Limit, Deductible, IsClaimExpensesType, IsClaimOptionType, IsDollarDefense

		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.NAMEADDRESS_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			// For Defense Option Per Claim -> Aggregate
				
				ctx.put("LPL_109_ENDORSEMENT","Y");
				listFormID.add("109");
			
			//ctx.put("PremiumCharged","S");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.PRIORACTDATE_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			// For Defense Option Per Claim -> Aggregate
				
			String isPremiumCalculated=ctx.get("premiumCalulated")!=null && !ctx.get("premiumCalulated").equals(HtmlConstants.EMPTY) ?ctx.get("premiumCalulated").toString():"N";
			if("Y".equals(ctx.get("isEnforcedPolicy").toString()) && !"Y".equals(ctx.get("anyDecendentPolicy").toString()))
			{	
				if("N".equals(isPremiumCalculated))
					{
					ctx.remove("PremiumCharged");
					ctx.remove("PremiumValue");
					ctx.remove("ProrataPremium");
					}
			}
			else
			{
				ctx.put("PremiumValue","0");
				ctx.put("PremiumCharged","Y");
			}
				ctx.put("LPL_109_ENDORSEMENT","Y");
				listFormID.add("109");
			
			//ctx.put("PremiumCharged","S");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.POLICYEXTENSION_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			// For Defense Option Per Claim -> Aggregate
			
				ctx.put("LPL_109_ENDORSEMENT","Y");
				listFormID.add("109");
			
			//ctx.put("PremiumCharged","S");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.CANCELLATION_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("LPL_114_ENDORSEMENT","Y");
			listFormID.add("114");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.REINSTATEMENT_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("LPL_132_ENDORSEMENT","Y");
			listFormID.add("132");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.ADDCHANGEATTORNEY_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("LPL_110_ENDORSEMENT","Y");
			listFormID.add("110");
			if("Y".equals(ctx.get("isNonFinancialEndorsement")))
				ctx.put("PremiumValue", "$");
			else
			{
				Double endorsementTotalPremium=Double.parseDouble(ctx.get("endorsementTotalPremium").toString());
				String endorsementTotalPremium2=numberFormat.format(Math.round(endorsementTotalPremium));
				ctx.put("PremiumValue", endorsementTotalPremium2.split("\\.")[0]);
			}
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.DELETEATTORNEY_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("LPL_134_ENDORSEMENT","Y");
			listFormID.add("134");
			if("Y".equals(ctx.get("isNonFinancialEndorsement")))
			{
				ctx.put("PremiumValue", "$");
			}
			else
			{
				Double PremiumValue=Double.parseDouble(ctx.get("endorsementTotalPremium").toString());
				String PremiumValue2=numberFormat.format(Math.round(PremiumValue));
				PremiumValue2=PremiumValue2.substring(1, PremiumValue2.length());
				ctx.put("PremiumValue", PremiumValue2.split("\\.")[0]);
				
			}
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.ERP_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("TransactionEffectiveDate", ctx.get("endorsementEffectiveDate"));
			
			String isAttorneyRetiring=ctx.get("isAttorneyRetiring")!=null && !ctx.get("isAttorneyRetiring").equals(HtmlConstants.EMPTY)?ctx.get("isAttorneyRetiring").toString():"A";
			String isInsuredWithUsFromPast=ctx.get("isInsuredWithUsFromPast")!=null && !ctx.get("isInsuredWithUsFromPast").equals(HtmlConstants.EMPTY)?ctx.get("isInsuredWithUsFromPast").toString():"A";
			String isDeathDisabilityERP=ctx.get("isDeathDisabilityERP")!=null && !ctx.get("isDeathDisabilityERP").equals(HtmlConstants.EMPTY)?ctx.get("isDeathDisabilityERP").toString():"A";
			String isFirmERP=ctx.get("isFirmERP")!=null && !ctx.get("isFirmERP").equals(HtmlConstants.EMPTY)?ctx.get("isFirmERP").toString():"A";
			
			if("Y".equals(isAttorneyRetiring) )
			{
				ctx.put("LPL_105_ENDORSEMENT","Y");
				listFormID.add("105");
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetAttornerDetailsData", ctx);
				if(obj != null && obj instanceof Map)
				{
					ctx.putAll((Map)obj);
				}
				ctx.put("AttorneyName",ctx.get("AttorneyName"));
				ctx.put("CeasedDate",ctx.get("ceasedDate"));
				if("Y".equals(isInsuredWithUsFromPast))
						ctx.put("isThereAdditionalPremium", "N");
				else
					ctx.put("isThereAdditionalPremium", "Y");
			}
			if("Y".equals(isDeathDisabilityERP) )
			{
				
				ctx.put("LPL_105_ENDORSEMENT","Y");
				listFormID.add("105");
				ctx.put("AttorneyName",ctx.get("deathDisabilityFirmOrAttorneyName"));
				ctx.put("CeasedDate",ctx.get("deathDiablityCeasedDate"));
				
				ctx.put("isThereAdditionalPremium", "N");
			}
			/*if("N".equals(isAttorneyRetiring) && "N".equals(isDeathDisabilityERP) )*/
			if("Y".equals(isFirmERP))
			{
				
				ctx.put("LPL_123_ENDORSEMENT","Y");
				listFormID.add("123");
				if("Y".equals(isFirmERP))
				{	
					int erpYears=ctx.get("yearsOfERP")!=null && !ctx.get("yearsOfERP").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("yearsOfERP").toString()):0;
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,StateCode,yearsOfERP,endorsementEffectiveDate");
					List data= (List) SqlResources.getSqlMapProcessor(ctx).select("PolicyTransaction.GetERPExpirationDate_p",ctx);
					ctx.putAll((Map) data.get(0));
					if(erpYears==10)
					ctx.put("isUnlimitedYearsERP", "Y");
					else
						ctx.put("isUnlimitedYearsERP", "N");
					
				}	
				//RuleUtils.executeRule(ctx,"LawyersRule.setErpExpirationDate");
				}
			
		}
		ctx.put("listFormID", listFormID);
		}
		
		catch(Exception e)
		{logger.error("Unexpected error", e);}
	}
	public void setEndorsementForIsmiePolicy(Context ctx,Map newPC,Map preExistPC)
	{	
		List <String>listFormID = new ArrayList <String> () ;
		try
		{
			DecimalFormat  formatter = new DecimalFormat("#0.00");   
			NumberFormat numberFormat =NumberFormat.getCurrencyInstance(Locale.US);
			// To Check Changes in Limit or Deductible 
			if(newPC!=null && preExistPC!=null)
			{
				if(newPC.get("LimitSequence") != null && preExistPC.get("LimitSequence") != null
						&& !(newPC.get("LimitSequence").toString().equals(preExistPC.get("LimitSequence").toString()))	 						
				  ){
					ctx.put("ALA_F011_ENDORSEMENT","Y");
					listFormID.add("F011");
				} else if(newPC.get("DeductibleSequence") != null && preExistPC.get("DeductibleSequence") != null
						&& !(newPC.get("DeductibleSequence").toString().equals(preExistPC.get("DeductibleSequence").toString())) 						
				  ){
					ctx.put("ALA_F011_ENDORSEMENT","Y");
					listFormID.add("F011");
				}
				 
				
				if(ctx.get("TransactionTypeKey") != null && LawyersConstants.LIMIT_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
					// For Defense Expense Inside -> Outside
					if(newPC.get("IsClaimExpensesType") != null && "Y".equals(newPC.get("IsClaimExpensesType").toString())
							&& (preExistPC.get("IsClaimExpensesType") == null  
							|| ((preExistPC.get("IsClaimExpensesType") != null && "N".equals(preExistPC.get("IsClaimExpensesType").toString()))))						
					  ){
						ctx.put("ALA_F004_ENDORSEMENT","Y");
						listFormID.add("F004");
					}	
					// For Defense Expense Outside -> Inside
					if(newPC.get("IsClaimExpensesType") != null && ("N".equals(newPC.get("IsClaimExpensesType").toString()) || "".equals(newPC.get("IsClaimExpensesType").toString())	)					 
							&& (preExistPC.get("IsClaimExpensesType") != null && "Y".equals(preExistPC.get("IsClaimExpensesType").toString()))						
					  ){
						//ctx.put("LPL_136_102_ENDORSEMENT","Y");
						//listFormID.add("136");
						ctx.put("ALA_F018_F004_ENDORSEMENT","Y");
						listFormID.add("F018");
					}				
					// For Dollar Defense N -> Y
					if(newPC.get("IsDollarDefense") != null && "Y".equals(newPC.get("IsDollarDefense").toString())
							&& (preExistPC.get("IsDollarDefense") == null  
							|| ((preExistPC.get("IsDollarDefense") != null && "N".equals(preExistPC.get("IsDollarDefense").toString()))))					
					  ){
						ctx.put("ALA_F005_ENDORSEMENT","Y");
						listFormID.add("F005");
					}
					// For Dollar Defense Y -> N
					if((newPC.get("IsDollarDefense") == null || (newPC.get("IsDollarDefense") != null && ("N".equals(newPC.get("IsDollarDefense").toString()) || "".equals(newPC.get("IsDollarDefense").toString()))))
							&& (preExistPC.get("IsDollarDefense") != null && "Y".equals(preExistPC.get("IsDollarDefense").toString()))					
					  ){
						//ctx.put("LPL_136_103_ENDORSEMENT","Y");
						//listFormID.add("136");
						ctx.put("ALA_F018_F005_ENDORSEMENT","Y");
						listFormID.add("F018");
					}
				}
					
				if(ctx.get("TransactionTypeKey") != null && LawyersConstants.DEDUCTIBLE_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
					// For Defense Option Per Claim -> Aggregate
					if(newPC.get("IsClaimOptionType") != null && "Y".equals(newPC.get("IsClaimOptionType").toString())
							&& (preExistPC.get("IsClaimOptionType") == null  
							|| ((preExistPC.get("IsClaimOptionType") != null && "N".equals(preExistPC.get("IsClaimOptionType").toString()))))					
					  ){
						//ctx.put("LPL_104_ENDORSEMENT","Y");
						//listFormID.add("104");
						ctx.put("ALA_F006_ENDORSEMENT","Y");
						listFormID.add("F006");
					}
					// For Defense Option Aggregate -> Per Claim
					if((newPC.get("IsClaimOptionType") == null || (newPC.get("IsClaimOptionType") != null && ("N".equals(newPC.get("IsClaimOptionType").toString()) || "".equals(newPC.get("IsClaimOptionType").toString()) )))
							&& (preExistPC.get("IsClaimOptionType") != null && "Y".equals(preExistPC.get("IsClaimOptionType").toString()))					
					  ){
						//ctx.put("LPL_136_104_ENDORSEMENT","Y");
						//listFormID.add("136");
						ctx.put("ALA_F018_F006_ENDORSEMENT","Y");
						listFormID.add("F018");
					}
				}
				
			}
		//End Check  difference in Limit, Deductible, IsClaimExpensesType, IsClaimOptionType, IsDollarDefense

		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.NAMEADDRESS_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			// For Defense Option Per Claim -> Aggregate
				
				//ctx.put("LPL_109_ENDORSEMENT","Y");
				//listFormID.add("109");
			ctx.put("ALA_F011_ENDORSEMENT","Y");
			listFormID.add("F011");
			//ctx.put("PremiumCharged","S");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.PRIORACTDATE_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			// For Defense Option Per Claim -> Aggregate
				
			String isPremiumCalculated=ctx.get("premiumCalulated")!=null && !ctx.get("premiumCalulated").equals(HtmlConstants.EMPTY) ?ctx.get("premiumCalulated").toString():"N";
			if("Y".equals(ctx.get("isEnforcedPolicy").toString()) && !"Y".equals(ctx.get("anyDecendentPolicy").toString()))
			{	
				if("N".equals(isPremiumCalculated))
					{
					ctx.remove("PremiumCharged");
					ctx.remove("PremiumValue");
					ctx.remove("ProrataPremium");
					}
			}
			else
			{
				ctx.put("PremiumValue","0");
				ctx.put("PremiumCharged","Y");
			}
				//ctx.put("LPL_109_ENDORSEMENT","Y");
				//listFormID.add("109");
				ctx.put("ALA_F011_ENDORSEMENT","Y");
				listFormID.add("F011");
			//ctx.put("PremiumCharged","S");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.POLICYEXTENSION_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			// For Defense Option Per Claim -> Aggregate
			
				//ctx.put("LPL_109_ENDORSEMENT","Y");
				//listFormID.add("109");
				ctx.put("ALA_F011_ENDORSEMENT","Y");
				listFormID.add("F011");
			//ctx.put("PremiumCharged","S");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.CANCELLATION_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("ALA_S005_ENDORSEMENT","Y");
			listFormID.add("S005");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.REINSTATEMENT_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("ALA_F015_ENDORSEMENT","Y");
			listFormID.add("F015");
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.ADDCHANGEATTORNEY_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("ALA_F045_ENDORSEMENT","Y");
			listFormID.add("F045");
			if("Y".equals(ctx.get("isNonFinancialEndorsement")))
				ctx.put("PremiumValue", "$");
			else
			{
				Double endorsementTotalPremium=Double.parseDouble(ctx.get("endorsementTotalPremium").toString());
				String endorsementTotalPremium2=numberFormat.format(Math.round(endorsementTotalPremium));
				ctx.put("PremiumValue", endorsementTotalPremium2.split("\\.")[0]);
			}
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.DELETEATTORNEY_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("ALA_F016_ENDORSEMENT","Y");
			listFormID.add("F016");
			if("Y".equals(ctx.get("isNonFinancialEndorsement")))
			{
				ctx.put("PremiumValue", "$");
			}
			else
			{
				Double PremiumValue=Double.parseDouble(ctx.get("endorsementTotalPremium").toString());
				String PremiumValue2=numberFormat.format(Math.round(PremiumValue));
				PremiumValue2=PremiumValue2.substring(1, PremiumValue2.length());
				ctx.put("PremiumValue", PremiumValue2.split("\\.")[0]);
				
			}
		}
		if(ctx.get("TransactionTypeKey") != null && LawyersConstants.ERP_ENDORSEMENT.equals(ctx.get("TransactionTypeKey").toString())){
			ctx.put("TransactionEffectiveDate", ctx.get("endorsementEffectiveDate"));
			
			String isAttorneyRetiring=ctx.get("isAttorneyRetiring")!=null && !ctx.get("isAttorneyRetiring").equals(HtmlConstants.EMPTY)?ctx.get("isAttorneyRetiring").toString():"A";
			String isInsuredWithUsFromPast=ctx.get("isInsuredWithUsFromPast")!=null && !ctx.get("isInsuredWithUsFromPast").equals(HtmlConstants.EMPTY)?ctx.get("isInsuredWithUsFromPast").toString():"A";
			String isDeathDisabilityERP=ctx.get("isDeathDisabilityERP")!=null && !ctx.get("isDeathDisabilityERP").equals(HtmlConstants.EMPTY)?ctx.get("isDeathDisabilityERP").toString():"A";
			String isFirmERP=ctx.get("isFirmERP")!=null && !ctx.get("isFirmERP").equals(HtmlConstants.EMPTY)?ctx.get("isFirmERP").toString():"A";
			
			if("Y".equals(isAttorneyRetiring) )
			{
				//ctx.put("LPL_105_ENDORSEMENT","Y");
				//listFormID.add("105");
				ctx.put("ALA_F007_ENDORSEMENT","Y");
				listFormID.add("F007");
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetAttornerDetailsData", ctx);
				if(obj != null && obj instanceof Map)
				{
					ctx.putAll((Map)obj);
				}
				ctx.put("AttorneyName",ctx.get("AttorneyName"));
				ctx.put("CeasedDate",ctx.get("ceasedDate"));
				if("Y".equals(isInsuredWithUsFromPast))
						ctx.put("isThereAdditionalPremium", "N");
				else
					ctx.put("isThereAdditionalPremium", "Y");
			}
			if("Y".equals(isDeathDisabilityERP) )
			{
				
				//ctx.put("LPL_105_ENDORSEMENT","Y");
				//listFormID.add("105");
				ctx.put("ALA_F007_ENDORSEMENT","Y");
				listFormID.add("F007");
				ctx.put("AttorneyName",ctx.get("deathDisabilityFirmOrAttorneyName"));
				ctx.put("CeasedDate",ctx.get("deathDiablityCeasedDate"));
				
				ctx.put("isThereAdditionalPremium", "N");
			}
			/*if("N".equals(isAttorneyRetiring) && "N".equals(isDeathDisabilityERP) )*/
			if("Y".equals(isFirmERP))
			{
				
				//ctx.put("LPL_123_ENDORSEMENT","Y");
				//listFormID.add("123");
				ctx.put("ALA_F038_ENDORSEMENT","Y");
				listFormID.add("F038");
				if("Y".equals(isFirmERP))
				{	
					int erpYears=ctx.get("yearsOfERP")!=null && !ctx.get("yearsOfERP").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("yearsOfERP").toString()):0;
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,StateCode,yearsOfERP,endorsementEffectiveDate");
					List data= (List) SqlResources.getSqlMapProcessor(ctx).select("PolicyTransaction.GetERPExpirationDate_p",ctx);
					ctx.putAll((Map) data.get(0));
					if(erpYears==10)
					ctx.put("isUnlimitedYearsERP", "Y");
					else
						ctx.put("isUnlimitedYearsERP", "N");
					
				}	
				//RuleUtils.executeRule(ctx,"LawyersRule.setErpExpirationDate");
				}
			
		}
		ctx.put("listFormID", listFormID);
		}
		
		catch(Exception e)
		{logger.error("Unexpected error", e);}
	}
	
}
