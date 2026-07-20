<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	 
	  <xsl:include  href="foxslold/Policycoverletter.xsl" />
	  <xsl:include  href="foxslold/LPL - 100 - 2009.xsl" />
      <xsl:include  href="foxslold/LPL - 102 - 2009.xsl" />
      <xsl:include  href="foxslold/LPL - 103 - 2009.xsl" />
      <xsl:include  href="foxslold/LPL - 104 - 2009.xsl" />     
      <xsl:include  href="foxslold/LPL - 106 - 2010.xsl" />
      <xsl:include  href="foxslold/LPL - 125 - 2012.xsl" />
      <xsl:include  href="foxslold/LPL - 126 - 2013.xsl" /> 
      <xsl:include  href="foxslold/LPL - 127 - 2013.xsl" />
      <xsl:include  href="foxslold/LPL - 129 - 2013.xsl" />
      <xsl:include  href="foxslold/LPL - 130 - 2013.xsl" />         
      <xsl:include  href="foxslold/ofac_notice.xsl" />
      
      
      <xsl:include  href="foxslold/group1/LPL - AMEND - PA.xsl" />
      <xsl:include  href="foxslold/group1/LPL - NOTICE - PA1.xsl" />      
      <xsl:include  href="foxslold/group1/LPL - NOTICE - PA2.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - GA.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - IL.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - IN.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - MO.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - NJ.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - TN .xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - WI.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - KY.xsl" />
      <xsl:include  href="foxslold/group1/LPL-KY-TAX.xsl" />
      
      <xsl:include  href="foxslold/group1/MA - LPL - 102.xsl" />
      <xsl:include  href="foxslold/group1/MA - LPL - 103.xsl" />
      <xsl:include  href="foxslold/group1/MA - LPL - 104.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - NM.xsl" />
      <xsl:include  href="foxslold/group1/LPL - AMEND - NJ  2013.xsl" />
      <xsl:include  href="foxslold/group1/LPL-AMEND-WA.xsl" />
      
      
      <!-- Group 2 States -->
      <xsl:include  href="foxslold/group2/LPL - AMEND - AK.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - AR.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - AZ.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - DC.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - DE.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - HI.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - IA.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - KS.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - MD.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - ME.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - MN.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - MT.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - NC.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - ND.xsl" /> 
      <xsl:include  href="foxslold/group2/LPL - AMEND - NH.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - OH.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - OR.xsl" /> 
      <xsl:include  href="foxslold/group2/LPL - AMEND - RI.xsl" /> 
      <xsl:include  href="foxslold/group2/LPL - AMEND - SC.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - SD.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - VA.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - WV.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - WY.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - CT.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - LA.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - WA1.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - NV.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - TX.xsl" />
      <xsl:include  href="foxslold/group2/LPL - NOTICE - TX.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - WY2.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - FL.xsl" />
      <xsl:include  href="foxslold/group2/LPL - AMEND - VT.xsl" />
      <xsl:include  href="foxslold/group2/FM303-0-26-02-14.xsl" />
      <xsl:include  href="foxslold/HotlineNoticeLW.xsl" />
      
      <xsl:include  href="foxslold/additionalforms/LPL - 107 - 2010.xsl" />
      <xsl:include  href="foxslold/additionalforms/LPL - 108 - 2010.xsl" />
      <xsl:include  href="foxslold/additionalforms/LPL - 120 - 2011.xsl" />
      <xsl:include  href="foxslold/additionalforms/LPL - 121 - 2012.xsl" />
      <xsl:include  href="foxslold/additionalforms/LPL - 135 - 2014.xsl" />
      
      <xsl:include  href="foxslold/group3/FM_2_0_1033_03_12.xsl" />
      <xsl:include  href="foxslold/group3/MI_43_002_06_15.xsl" />
      <xsl:include  href="foxslold/group3/CODisclosureForm.xsl" />
      <xsl:include  href="foxslold/group3/LPL-AMEND-CO.xsl" />
      <xsl:include  href="foxslold/Signature.xsl" />
       <xsl:include href="foxslold/group3/MO_42_001_06_15_policy.xsl" />
      
	  <xsl:template match="/">
		
		<fo:root>
		
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait" page-width="21cm"   
					page-height="29.7cm" margin-top="10mm" margin-bottom="1.0mm" margin-left="10mm" 
					margin-right="10mm">
					<fo:region-body margin-top="2.0mm"/>
					<fo:region-before extent="1.0mm"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>		
	
		     <fo:page-sequence master-reference="A4-portrait">			
				
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:call-template name="Policycoverletter" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="HOTLINENOTICELW" />
						<fo:block break-after="page" />
						
						<xsl:if test="response/templates/template_id_LPL_100= 'LPL_100'">
						<xsl:call-template name="Policycoverletter1" />
						
						<xsl:call-template name="Signature" />
						<fo:block break-after="page" />
						</xsl:if>
						
							
						<xsl:if test="response/templates/template_id_LPL_102= 'LPL_102'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'MA'">
								<xsl:call-template name="Policycoverletter2" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>	
														
						<xsl:if test="response/templates/template_id_LPL_103= 'LPL_103'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'MA'">
								<xsl:call-template name="Policycoverletter3" />
								<fo:block break-after="page" />
							</xsl:if>
						
						</xsl:if>						
						
						<xsl:if test="response/templates/template_id_LPL_104= 'LPL_104'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'MA'">
								<xsl:call-template name="Policycoverletter4" />
								<fo:block break-after="page" />
							</xsl:if>
								
						</xsl:if>			
						
						 <!-- 
						<xsl:if test="response/templates/template_id_LPL_105= 'LPL_105'">
							<xsl:call-template name="Policycoverletter5" />
							<fo:block break-after="page" />
						</xsl:if>						
						 -->
						
						<xsl:if test="response/templates/template_id_LPL_106= 'LPL_106'">
							<xsl:call-template name="Policycoverletter6" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<xsl:if test="response/templates/template_id_LPL_107= 'LPL_107'">
							<xsl:if test="response/additionaldata_freeform_01/data/IsPredecessorFormSelected= 'Y'">
								<xsl:call-template name="LPL1072010" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_108= 'LPL_108'">
							<xsl:if test="response/additionaldata_freeform_01/data/IsSpecifiedAttorneySelected= 'Y'">
								<xsl:call-template name="LPL1082010" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_120= 'LPL_120'">
							<xsl:if test="response/additionaldata_freeform_01/data/IsSpecificProfServicesSelected= 'Y'">
								<xsl:call-template name="LPL1202011" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_121= 'LPL_121'">
							<xsl:if test="response/additionaldata_freeform_01/data/IsPreviouFirmFormSelected= 'Y'">
								<xsl:call-template name="LPL1212012" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						
						<!-- 
						<xsl:if test="response/templates/template_id_LPL_108= 'LPL_108'">
							<xsl:call-template name="Policycoverletter8" />
							<fo:block break-after="page" />
						</xsl:if>
						 -->
						<xsl:if test="response/templates/template_id_LPL_125= 'LPL_125'">
							<xsl:call-template name="ProfessionalServiceEndorsement" />
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_LPL_126= 'LPL_126'">
							<xsl:call-template name="PredecessorFirmDefinition" />
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_LPL_127= 'LPL_127'">
							<xsl:call-template name="SecurityIncident" />
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_LPL_129= 'LPL_129'">
							<xsl:call-template name="LPLFORM129" />
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_LPL_130= 'LPL_130'">
							<xsl:call-template name="coverageendorsement" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_135= 'LPL_135'">
							<xsl:if test="response/additionaldata_freeform_01/data/IsSuitForFeesAction='Y'">
								<xsl:call-template name="LPL1352014" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:call-template name="Ofac" />
						<fo:block break-after="page" />
						
						
						<!-- State Specific Forms Code goes below -->
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'PA'">
							<xsl:call-template name="LPL-State-PA-1" />
							<fo:block break-after="page" />
							 
								<xsl:if test="response/ClaimExpensesTypeFlag = 'N'">							
									<xsl:call-template name="LPL-State-PA-2" />
									<fo:block break-after="page" />
									<xsl:call-template name="LPL-State-PA-3" />
									<fo:block break-after="page" />						
							  </xsl:if>
							
						</xsl:if>
						
						
						
						
						
						
						  
						<xsl:if test="response/address_freeform_01/data/StateCode= 'KY'">
							<xsl:call-template name="LPL-State-KY" />
							<fo:block break-after="page" />
							
							<xsl:if test="response/firm_freeform_01/data/IsTaxCalculation !='Y'">  							 
							<xsl:if test="response/AttachKentuckyTaxFlag = 'Y'">
							

							  <xsl:call-template name="LPL-KY-TAX" />
									<fo:block break-after="page" />
							  </xsl:if>
							
							</xsl:if>
												
						</xsl:if>	
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'GA'">
							<xsl:call-template name="LPL-State-GA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'IL'">
							<xsl:call-template name="LPL-State-IL" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'IN'">
							<xsl:call-template name="LPL-State-IN" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'TN'">
							<xsl:call-template name="LPL-State-TN" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WI'">
							<xsl:call-template name="LPL-State-WI" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MO'">
							<xsl:call-template name="LPL-State-MO" />
							<fo:block break-after="page" />
							<!--  
							 <xsl:call-template name="LPL-State-MO-2" />
							<fo:block break-after="page" />
							-->
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NJ'">
							<xsl:call-template name="LPL-State-NJ" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MA'">
								<xsl:if test="response/ClaimExpensesTypeFlag= 'Y'">							
									<xsl:call-template name="MADefenceExpensesInAddition" />
									<fo:block break-after="page" />												
								</xsl:if>	
								<xsl:if test="response/DollarDefenseFlag= 'Y'">							
									<xsl:call-template name="MADefenceExpenses" />
									<fo:block break-after="page" />												
								</xsl:if>
								<xsl:if test="response/ClaimOptionTypeFlag= 'Y'">							
									<xsl:call-template name="MAAggregateDeductible" />
									<fo:block break-after="page" />												
								</xsl:if>
						</xsl:if>
						 
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NM'">
							<xsl:call-template name="NMAmendatory" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WA'">
							<xsl:call-template name="LPL-AMEND-WA1" />
								<fo:block break-after="page" />
							<xsl:if test="response/endorsements_freeform_01/data/LPLAMENDWA20513 != ''">
								<xsl:call-template name="LPL-AMEND-WA-2" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						
						<!-- Group 2 States -->
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'AK'">
							<xsl:call-template name="LPL-AMEND-AK" />
							<fo:block break-after="page" />
							<xsl:if test="response/ClaimExpensesTypeFlag ='Y' ">	
								<xsl:call-template name="Form_FM_201033_12" />
								<fo:block break-after="page" />
							</xsl:if>
							<xsl:if test="response/ClaimExpensesTypeFlag ='N' ">	
								<xsl:call-template name="MI_43_002_06_15" />
								<fo:block break-after="page" />
							</xsl:if>
							
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'AR'">
							<xsl:call-template name="LPL-AMEND-AR" />
							<fo:block break-after="page" />
						</xsl:if>
						
							<xsl:if test="response/address_freeform_01/data/StateCode= 'AZ'">
							<xsl:call-template name="LPL-AMEND-AZ" />
							<fo:block break-after="page" />
						</xsl:if>						
							
						<xsl:if test="response/address_freeform_01/data/StateCode= 'DC'">
							<xsl:call-template name="LPL-AMEND-DC" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'DE'">
							<xsl:call-template name="LPL-AMEND-DE" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'HI'">
							<xsl:call-template name="LPL-AMEND-HI" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'IA'">
							<xsl:call-template name="LPL-AMEND-IA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'KS'">
							<xsl:call-template name="LPL-AMEND-KS" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MD'">
							<xsl:call-template name="LPL-AMEND-MD" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'ME'">
							<xsl:call-template name="LPL-AMEND-ME" />
							<fo:block break-after="page" />
						</xsl:if>	
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MN'">
							<xsl:call-template name="LPL-AMEND-MN" />
							<fo:block break-after="page" />
						</xsl:if>	
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MT'">
							<xsl:call-template name="LPL-AMEND-MT" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NC'">
							<xsl:call-template name="LPL-AMEND-NC" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'ND'">
							<xsl:call-template name="LPL-AMEND-ND" />
							<fo:block break-after="page" />
							<xsl:if test="response/IsDefensePaidWithinRuleND ='Y' ">
								  <xsl:call-template name="ND-Defense-Inside" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NH'">
							<xsl:call-template name="LPL-AMEND-NH" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'OH'">
							<xsl:call-template name="LPL-AMEND-OH" />
							<fo:block break-after="page" />							
						</xsl:if>		
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'OR'">
							<xsl:call-template name="LPL-AMEND-OR" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'RI'">
							<xsl:call-template name="LPL-AMEND-RI" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'SC'">
							<xsl:call-template name="LPL-AMEND-SC" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'SD'">
							<xsl:call-template name="LPL-AMEND-SD" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'VA'">
							<xsl:call-template name="LPL-AMEND-VA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WV'">
							<xsl:call-template name="LPL-AMEND-WV" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WY'">
							<xsl:call-template name="LPL-AMEND-WY" />
							<fo:block break-after="page" />
							<xsl:if test="response/ClaimExpensesTypeFlag != 'Y'">
								<xsl:call-template name="LPL-AMEND-WY2" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'CT'">
							<xsl:call-template name="LPL-AMEND-CT" />
							<fo:block break-after="page" />							 
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'LA'">
							<xsl:call-template name="LPL-AMEND-LA" />
							<fo:block break-after="page" />							 
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NV'">
							<xsl:call-template name="LPL-AMEND-NV" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'TX'">
							<xsl:call-template name="LPL-AMEND-TX" />
							<fo:block break-after="page" />
							  <xsl:call-template name="LPL-State-TX-Notice" />
							<fo:block break-after="page" /> 
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'FL'">
							<xsl:call-template name="LPL-AMEND-FL" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'VT'">
							<xsl:call-template name="LPL-AMEND-VT" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MI'">
							<xsl:call-template name="FM3030260214" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'CO'">
							<xsl:call-template name="CODisclosureForm" />
							<fo:block break-after="page" />
							<xsl:call-template name="LPL-AMEND-CO" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<!--
						<xsl:call-template name="Policycoverletter10" />
						<fo:block break-after="page" />
						
						
						<xsl:call-template name="Policycoverletter11" />
						<fo:block break-after="page" />
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'OK'">	
								<xsl:if test="response/ClaimExpensesTypeFlag= 'Y'">							
									<xsl:call-template name="LPL-State-OK-2" />
									<fo:block break-after="page" />												
								</xsl:if>
							</xsl:if>
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NV'">
							<xsl:call-template name="LPL-State-NV" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						
						
						
						
						
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WA'">
							<xsl:call-template name="LPL-State-WA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'OK'">
							<xsl:call-template name="LPL-State-OK-1" />
							<fo:block break-after="page" />							 
						</xsl:if>
						
						
						
						
						
						
						-->
						
						
						
						
				   </fo:block>
				</fo:flow>
					
			</fo:page-sequence>
		
		</fo:root>
     </xsl:template>    
</xsl:stylesheet>
		    	