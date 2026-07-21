package com.userbo;

import com.util.InetLogger;
import com.util.SystemProperties;

public class LawyersConstants {
	private static InetLogger logger = InetLogger.getInetLogger(LawyersConstants.class);
    

	public static final String INSUREDSIGNATURE = "INSUREDSIGNATURE";
	
	public static double FACTROR500 = 0.0;
	public static double FACTROR1000 = 0.0;
	public static double FACTRORFULL = 0.0;
	
	public static final String SOUTH_DAKHOTA = "SD" ;
	public static final String ARKANSAS = "AR" ;
	public static final String VERMONT = "VT" ;
	public static final String OKLAHOMA = "OK" ;
	public static final String PHONENUMBER = "1-877-569-4111";
	
	public static final String CANCELLATION_ENDORSEMENT = "7";
	public static final String REINSTATEMENT_ENDORSEMENT = "8";
	public static final String LIMIT_ENDORSEMENT = "9";
	public static final String DEDUCTIBLE_ENDORSEMENT = "10";
	public static final String NAMEADDRESS_ENDORSEMENT = "11";
	public static final String PRIORACTDATE_ENDORSEMENT = "12";
	public static final String POLICYEXTENSION_ENDORSEMENT = "13";
	public static final String ADDCHANGEATTORNEY_ENDORSEMENT = "14";
	public static final String DELETEATTORNEY_ENDORSEMENT = "15";
	public static final String ERP_ENDORSEMENT = "16";
	public static final String INSURANCE_CERTIFICATE = "17";
	public static final String ENDORSEMENT_EMAIL = "EndorsementEmail";
	public static final String CANCELLATIONENDORSEMENT_EMAIL = "CancellationEndorsement";
	public static final String REINSTATEMENTENDORSEMENT_EMAIL = "ReinstatementEndorsement";
	public static final String INSURANCECERTIFICATE_EMAIL = "InsuranceCertificate";
	public static final String QUOTELETTER_EMAIL = "ERPQuoteLetter";
	
	
	public static String CUT_OFF_DATE = "01/01/2050";
	public static String CUT_OFF_DATE_GROUP2 = "01/01/2050";	
	public static String NEWAPPFLOW_CUT_OFF_DATE = "01/01/2050";
	public static int CLAIM_AGE_COUNT = 25;
	public static String CUT_OFF_DATE_GROUP3 = "01/01/2050";
	public static String CUT_OFF_DATE_GROUP4 = "01/01/2050";
	public static String CUT_OFF_DATE_GROUP5 = "01/01/2050";
	public static String CUT_OFF_DATE_GROUP6 = "01/01/2050";
	public static String CUT_OFF_DATE_GROUP7 = "01/01/2050";
	public static String CUT_OFF_DATE_GROUP8 = "01/01/2050";
	public static String CUT_OFF_DATE_CCBSupp = "01/01/2050";
	public static String CUT_OFF_DATE_2020 = "01/01/2050";
	public static String CUT_OFF_DATE_CANNIBSSUPP = "01/01/2050";
	public static String END_DATE_CF_POLICYISSUANCE = "03/31/2022";
	public static String ELECTRONIC_INSURANCE_IMPL_DATE = "05/24/2022";
	public static String BANKRUPTCY_INSURANCE_IMPL_DATE = "01/24/2022";
	public static String NEVADA_DOUBLEQUOTE_IMPL_DATE = "11/20/2023";
	public static String AZ_IN_MI_MN_TX_UT_QUOTELETTER_UPDATEDATE = "10/01/2024";
	public static String NC_QUOTELETTER_UPDATEDATE = "10/25/2024";
	public static String NJ_QUOTELETTER_UPDATEDATE ="11/01/2024";
	public static String FL_ID_KS_QUOTELETTER_UPDATEDATE="11/10/2024";
	public static String MO_VA_QUOTELETTER_UPDATEDATE="12/01/2024";
	public static String CO_OH_QUOTELETTER_UPDATEDATE="01/15/2025";
	static {
		try{
			FACTROR500 = Double.parseDouble(SystemProperties.getInstance().getString("appl.LawyersIns.factor500"));
			FACTROR1000 = Double.parseDouble(SystemProperties.getInstance().getString("appl.LawyersIns.factor1000"));
			FACTRORFULL = Double.parseDouble(SystemProperties.getInstance().getString("appl.LawyersIns.factorfull"));
		} catch (Exception e) {
			logger.error("Unable to load full-time equivalent factors", e);
		}
		
		try {
			NEWAPPFLOW_CUT_OFF_DATE = SystemProperties.getInstance().getString("appl.LawyersIns.newAppFlow.cutOffDate");
			CUT_OFF_DATE = SystemProperties.getInstance().getString("appl.LawyersIns.cutOffDate");
			CUT_OFF_DATE_GROUP2 = SystemProperties.getInstance().getString("appl.LawyersIns.Group2.cutOffDate");
			CUT_OFF_DATE_GROUP3 = SystemProperties.getInstance().getString("appl.LawyersIns.Group3.cutOffDate");
			CUT_OFF_DATE_GROUP4 = SystemProperties.getInstance().getString("appl.LawyersIns.Group4.cutOffDate");
			CUT_OFF_DATE_GROUP5 = SystemProperties.getInstance().getString("appl.LawyersIns.Group5.cutOffDate");
			CUT_OFF_DATE_GROUP6 = SystemProperties.getInstance().getString("appl.LawyersIns.Group6.cutOffDate");
			CUT_OFF_DATE_GROUP7 = SystemProperties.getInstance().getString("appl.LawyersIns.Group7.cutOffDate");
			CUT_OFF_DATE_GROUP8 = SystemProperties.getInstance().getString("appl.LawyersIns.Group8.cutOffDate");
			CUT_OFF_DATE_CCBSupp = SystemProperties.getInstance().getString("appl.LawyersIns.CCBSupp.cutOffDate");
			CUT_OFF_DATE_2020 = SystemProperties.getInstance().getString("appl.LawyersIns.Group1.cutOffDate.2020");
			CUT_OFF_DATE_CANNIBSSUPP = SystemProperties.getInstance().getString("appl.LawyersIns.CannibsSupp.cutOffDate");
			END_DATE_CF_POLICYISSUANCE= SystemProperties.getInstance().getString("appl.LawyersIns.CF.policyissuance");
			ELECTRONIC_INSURANCE_IMPL_DATE= SystemProperties.getInstance().getString("appl.LawyersIns.electronicInsuranceImplDate");
			BANKRUPTCY_INSURANCE_IMPL_DATE= SystemProperties.getInstance().getString("appl.LawyersIns.bankruptcySupplementDateNew");
			NEVADA_DOUBLEQUOTE_IMPL_DATE= SystemProperties.getInstance().getString("appl.LawyersIns.nevadaDoubleQuoteImplDate");
			AZ_IN_MI_MN_TX_UT_QUOTELETTER_UPDATEDATE= SystemProperties.getInstance().getString("appl.LawyersIns.ISMIE.AZ_IN_MI_MN_TX_UT.quoteLetter.updateDate");
			NC_QUOTELETTER_UPDATEDATE= SystemProperties.getInstance().getString("appl.LawyersIns.ISMIE.NC.quoteLetter.updateDate");
			NJ_QUOTELETTER_UPDATEDATE= SystemProperties.getInstance().getString("appl.LawyersIns.ISMIE.NJ.quoteLetter.updateDate");
			MO_VA_QUOTELETTER_UPDATEDATE= SystemProperties.getInstance().getString("appl.LawyersIns.ISMIE.MO_VA.quoteLetter.updateDate");
			CO_OH_QUOTELETTER_UPDATEDATE= SystemProperties.getInstance().getString("appl.LawyersIns.ISMIE.CO_OH.quoteLetter.updateDate");
		} catch (Exception e) {
			logger.error("Unable to load rating cut-off dates", e);
		}
	}
	
	
}
