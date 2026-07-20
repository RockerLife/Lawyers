package com.fop;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.fop.servlet.ServletContextURIResolver;
import org.jdom.Element;

import com.manage.managemetadata.functions.commonfunctions.Math;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.SystemProperties;
import com.util.XMLUtils;

public class DownloadRating {
	
private static InetLogger logger = InetLogger.getInetLogger(DownloadRating.class);
	
	public void makeRatingPdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {
		
		String xmlFile = "";
		String foFile = "";		
		
		try{	
			
			xmlFile = SystemProperties.getInstance().getString("html.basedir") + "data//rating.xml";
			foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//rating.xsl";
			
			fetchDataForXml(ctx);
			populateDataXml(ctx, xmlFile);
			
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);			
		}
		catch(Exception e){
			logger.error("Problem in generating rating for Quote Number : " +ctx.get("QuoteNumber"));
		}
	}
	
	public void makeInvoicePdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {
		
		String xmlFile = "";
		String foFile = "";		
		
		try{			
			
			xmlFile = SystemProperties.getInstance().getString("html.basedir") + "data//quoteletter.xml";
			
			if(!"3".equals(ctx.get("CompanyKey").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//InvoiceLW.xsl";
			if("3".equals(ctx.get("CompanyKey").toString()))
				foFile = SystemProperties.getInstance().getString("html.basedir") + "ISMIE2022//ISMIEInvoiceLW.xsl";
			fetchDataForInvoiceXml(ctx);
			populateDataXml(ctx, xmlFile);
			
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);			
		}
		catch(Exception e){
			logger.error("Problem in generating rating for Quote Number : " +ctx.get("QuoteNumber"));
		}
	}
	
	public void fetchDataForInvoiceXml(Context ctx) {		
		
		try{
			
			Object objInvoiceData = SqlResources.getSqlMapProcessor(ctx).select("Account.getInvoicedData", ctx);
			if(objInvoiceData !=null)
			{				
				ctx.put("InvoiceDataLW", objInvoiceData);
			}			
		}
		catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in fetching Invoiced  data for Quote Number : " +ctx.get("QuoteNumber"));
		}
		
	}

	public void fetchDataForXml(Context ctx) {		
		
		try{
			
			Object objRatingData = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgetRatingData", ctx);
			if(objRatingData !=null)
			{
				
				ctx.put("RatingData", objRatingData);
			}
			
			Object objFirmLW = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);
			if(objFirmLW != null){
				ctx.put("FirmLW", objFirmLW);
			}
			
			
			Object objRatingTrace = SqlResources.getSqlMapProcessor(ctx).findByKey("RatingTrace.findByKey", ctx);
			if(objRatingTrace != null)
			{
				Map mapRatingTrace = (Map)objRatingTrace;
				if(mapRatingTrace.get("XmlOutputDatafromRating") != null)
				{
					
					String outputString = mapRatingTrace.get("XmlOutputDatafromRating").toString();
					Element root = XMLUtils.parseXMLRootElement(outputString);					
					Element outputElement = root.getChild("PremiumInfoLW");
					Map out = populateOutput( outputElement);
					logger.debug("MTTaxAmmount-------"+out.get("MTTaxAmmount"));
					
					Map outData = calculateTax(out, ctx);
					
					ctx.put("PremiumInfo", outData);
					
				}
				
			}
				
		}
		catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in fetching data for Quote Number : " +ctx.get("QuoteNumber"));
		}
		
	}
	
	public Map calculateTax(Map out, Context ctx) throws Exception {
		
		
		Object obj = RuleUtils.executeRule(ctx, "LawyersRule.doNotCalculateTax");			
			boolean flag = true;
			if(obj != null && obj instanceof Boolean )
				flag =(Boolean) obj;
			
			double mTCountyTax = 0.0;		
			double finalBaseLimitPrem = 0.0;
			
			
			
			// Skip No
			if (!flag) {
                if (out.get("MTTaxAmmount") != null
                          && !"".equals(out.get("MTTaxAmmount")
                                   .toString())) {
                     mTCountyTax = Double.parseDouble(out.get(
                               "MTTaxAmmount").toString());
                     
                    
                     
                }
           }else
           {
        	   
        	   out.put("MTTaxAmmount",0.0);
           }
			
           if (out.get("CountyTaxAmmount") != null
                     && !"".equals(out.get("CountyTaxAmmount")
                               .toString())) {
                if (mTCountyTax <= 0) {
                     mTCountyTax = mTCountyTax
                               + Double.parseDouble(out.get(
                                        "CountyTaxAmmount").toString());
                     logger.debug("countrytaxamount------"+Double.parseDouble(out.get("CountyTaxAmmount").toString()));
                     
                     out.put("CountyTaxAmmount", Double.parseDouble(out.get("CountyTaxAmmount").toString()));
                }else{
             	   out.put("CountyTaxAmmount", 0.0);
                }
           }

			
			if(out.get("State1TaxAmmount") !=null && !"".equals(out.get("State1TaxAmmount").toString()))
			{
				mTCountyTax = mTCountyTax + Double.parseDouble(out.get("State1TaxAmmount").toString());
				out.put("State1TaxAmmount", Math.round(out.get("State1TaxAmmount")));
			}
			if(out.get("State2TaxAmmount") !=null && !"".equals(out.get("State2TaxAmmount").toString()))
			{
				mTCountyTax = mTCountyTax + Double.parseDouble(out.get("State2TaxAmmount").toString());
				out.put("State2TaxAmmount", Double.parseDouble(out.get("State2TaxAmmount").toString()));
			}
		
			
			out.put("Total_tax", Math.round(mTCountyTax));
			out.put("TotalCoverageTaxAmount", Math.round(mTCountyTax));
			
			double premiumWithoutTax = Double.parseDouble(out.get(
			"ActualFinalBaseLimitPremium").toString());
				double totalTax =  Double.parseDouble(out.get("Total_tax").toString()) ;
				double premiumWithTax = premiumWithoutTax + totalTax;
				
				out.put("FinalBaseLimitPremiumWithTax", premiumWithTax);
						
			
		
		
		return out;
	}

	public void populateDataXml(Context ctx, String xmlFile) {
		
		StringBuffer buffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		
		try{			
			// Data writer
			//IOUtils.writeToFile(xmlFile, buffer.append(new RequestProcessor().generateResponseXml(ctx)));
		}
		catch(Exception e){
			logger.error("Problem in generating data xml for Quote Number : " +ctx.get("QuoteNumber"));
		}
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
			out.put(name, str);      
	    }  	
		
		return out;
	}

}
