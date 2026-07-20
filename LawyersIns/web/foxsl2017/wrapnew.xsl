<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	 
	  <xsl:include  href="foxslnew/Policycoverletter.xsl" />
	  <xsl:include  href="foxslnew/LPL - 099 - 2009.xsl" />
	  <xsl:include  href="foxslnew/LPL - 100 - 2009.xsl" />
      <xsl:include  href="foxslnew/LPL - 102 - 2009.xsl" />
      <xsl:include  href="foxslnew/LPL - 103 - 2009.xsl" />
      <xsl:include  href="foxslnew/LPL - 104 - 2009.xsl" />     
      <xsl:include  href="foxslnew/LPL - 106 - 2010.xsl" />
      <xsl:include  href="foxslnew/LPL - 117 - 2017.xsl" />
      <xsl:include  href="foxslnew/LPL - 127 - 2013.xsl" />
      
      <xsl:include  href="foxslnew/HotlineNoticeLW.xsl" />
      <xsl:include  href="foxslnew/ofac_notice.xsl" />
      <xsl:include  href="foxslnew/CommonHeader.xsl" />
      <xsl:include  href="foxslnew/Signature.xsl" />
      
      <!-- Group 1 States ('AL','AR','AZ','DC','DE','ID','IN','KY','MI','NJ','PA')-->      
      <xsl:include  href="foxslnew/group1/LPL - AMEND - AL.xsl" />
      <xsl:include  href="foxslnew/group1/LPL - AMEND - AR.xsl" />
      <xsl:include  href="foxslnew/group1/LPL - AMEND - AZ.xsl" />
      <xsl:include  href="foxslnew/group1/LPL - AMEND - DC.xsl" />
      <xsl:include  href="foxslnew/group1/LPL - AMEND - DE.xsl" />
      <xsl:include  href="foxslnew/group1/LPL - AMEND - ID.xsl" />
      <xsl:include  href="foxslnew/group1/LPL - AMEND - IN.xsl" />  
      <xsl:include  href="foxslnew/group1/LPL - AMEND - KY.xsl" />
      <xsl:include  href="foxslnew/group1/LPL - AMEND - MI.xsl" />  
      <xsl:include  href="foxslnew/group1/LPL - AMEND - NJ.xsl" />      
      <xsl:include  href="foxslnew/group1/LPL - AMEND - PA.xsl" />  
      
      <xsl:include  href="foxslnew/group1/LPL - AMEND - AR - CF.xsl" />  
      <xsl:include  href="foxslnew/group1/MO-42-003-04-17-AR.xsl" />    
      <xsl:include  href="foxslnew/group1/LPL - KY - TAX.xsl" />      
      <xsl:include  href="foxslnew/group1/LPL - 117 - NJ.xsl" /> 
      <xsl:include  href="foxslnew/group1/FM303-0-26-02-14.xsl" />     
    	 
      <!-- Group 2 States ('MO','MT','NE','ND','NV','RI','SD','TN','TX','UT') -->
      <xsl:include  href="foxslnew/group2/LPL - AMEND - MO.xsl" />
      <xsl:include  href="foxslnew/group2/LPL - AMEND - MT.xsl" />      
      <xsl:include  href="foxslnew/group2/LPL - AMEND - NE.xsl" />      
      <xsl:include  href="foxslnew/group2/LPL - AMEND - ND.xsl" /> 
      <xsl:include  href="foxslnew/group2/LPL - AMEND - NV.xsl" />      
      <xsl:include  href="foxslnew/group2/LPL - AMEND - RI.xsl" />
      <xsl:include  href="foxslnew/group2/LPL - AMEND - SD.xsl" />      
      <xsl:include  href="foxslnew/group2/LPL - AMEND - TN.xsl" />
      <xsl:include  href="foxslnew/group2/LPL - AMEND - TX.xsl" />
      <xsl:include  href="foxslnew/group2/LPL - AMEND - UT.xsl" />
      
                   
      <xsl:include  href="foxslnew/group2/MI-40-002-09-16-CF-Notice.xsl" />  
      <xsl:include  href="foxslnew/group2/MO-42-004-04-17-MT.xsl" />    
      <xsl:include  href="foxslnew/group2/MO-42-001-04-17-ND.xsl" />        
      <xsl:include  href="foxslnew/group2/MI-40-001-12-15-TX.xsl" />
      <xsl:include  href="foxslnew/group2/FM-101-0-1347-02-13-TX.xsl" />
      <xsl:include  href="foxslnew/group2/MI-40-009-02-18-TX.xsl" />
      
      <!-- Group 3 States ('NM')-->
      <xsl:include  href="foxslnew/group3/LPL - 102 - NM1.xsl" />
      <xsl:include  href="foxslnew/group3/LPL - 102 - NM2.xsl" />
      <xsl:include  href="foxslnew/group3/LPL - 117 - NM.xsl" />  
      <xsl:include  href="foxslnew/group3/MO-42-002-04-17-NM.xsl" /> 
     
      <!-- Group 4 States ('CO','GA','IA','IL','MA','ME','NH','OH','OR','WA','WI','WV') -->
      <xsl:include  href="foxslnew/group4/LPL - AMEND - CO.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - GA.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - IA.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - IL.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - MA.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - ME.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - NH.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - OH.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - OR.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - WA.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - WI.xsl" />
      <xsl:include  href="foxslnew/group4/LPL - AMEND - WV.xsl" />
      <xsl:include  href="foxslnew/group4/MA - LPL - 102.xsl" />
      <xsl:include  href="foxslnew/group4/MA - LPL - 103.xsl" />
      <xsl:include  href="foxslnew/group4/MA - LPL - 104.xsl" />      
      <xsl:include  href="foxslnew/group4/FM-2-0-1057-03-13.xsl" />
      <xsl:include  href="foxslnew/group4/FM-2-0-648-09-10.xsl" />
      <xsl:include  href="foxslnew/group4/LPL-EXCEPTION-WA.xsl" />
      <xsl:include  href="foxslnew/group4/MI-40-010-03-19.xsl" />
      
      
      <!-- Group 5 States ('AK','CT','HI','KS','LA','MD','MN','MS','NC','SC','VA','VT','WY') -->
      <xsl:include  href="foxslnew/group5/LPL - AMEND - AK.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - CT.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - HI.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - KS.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - LA.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - MD.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - MN.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - MS.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - NC.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - SC.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - VA.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - AMEND - VT.xsl" />   
      <xsl:include  href="foxslnew/group5/LPL - AMEND - WY.xsl" /> 
         
      <xsl:include  href="foxslnew/group5/FM_2_0_1033_03_12_AK.xsl" />
      <xsl:include  href="foxslnew/group5/MI_43_002_06_15_AK.xsl" />
      <xsl:include  href="foxslnew/group5/FM_2_0_1019_10_12_MN.xsl" />
      <xsl:include  href="foxslnew/group5/MI_40_002_09_16_SC.xsl" />
      <xsl:include  href="foxslnew/group5/FM_2_0_1029_01_12_VA.xsl" />
      <xsl:include  href="foxslnew/group5/MO_43_001_04_18_VA.xsl" />
      <xsl:include  href="foxslnew/group5/LPL - EXCEPTION - VT.xsl" />
      <xsl:include  href="foxslnew/group5/MO_42_005_04_17_WY.xsl" />
      
      <!-- Group 6 States  ('OK') -->
      <xsl:include  href="foxslnew/group6/LPL - AMEND - OK.xsl" />
      <xsl:include  href="foxslnew/group6/LPL - EXCEPTION - OK.xsl" />
      
      <!-- Group 7 States  ('CA','FL') -->
      <xsl:include  href="foxslnew/group7/LPL - AMEND - CA.xsl" />
      <xsl:include  href="foxslnew/group7/LPL - AMEND - FL.xsl" />
      <xsl:include  href="foxslnew/group7/MI_40_002_09_16_FL.xsl" />
           
      <!-- Group Other States -->                
      <xsl:include  href="foxslnew/groupother/MO_42_001_06_15_policy.xsl" />
      
      <!-- Additional Form -->
      <xsl:include  href="foxslnew/additionalforms/LPL - 107 - 2010.xsl" />
      <xsl:include  href="foxslnew/additionalforms/LPL - 108 - 2010.xsl" />
      <xsl:include  href="foxslnew/additionalforms/LPL - 120 - 2011.xsl" />
      <xsl:include  href="foxslnew/additionalforms/LPL - 121 - 2012.xsl" />
      <xsl:include  href="foxslnew/additionalforms/LPL - 135 - 2014.xsl" />
      <xsl:include  href="foxslnew/additionalforms/LPL - 181 - 2020.xsl" />
      <xsl:include  href="foxslnew/additionalforms/LPL - 182 - 2020.xsl" />
      <!-- Group 8 States  -->
      <xsl:include  href="foxslnew/group8/LPL-AMEND-NM1.xsl" />
      <xsl:include  href="foxslnew/group8/LPL-AMEND-NM2.xsl" />
      <xsl:include  href="foxslnew/group8/LPL-AMEND-NM3.xsl" />
      <xsl:include  href="foxslnew/group8/LPL-AMEND-NM4.xsl" />
      <xsl:include  href="foxslnew/group8/MO-42-002-04-19-NM.xsl" />
      <xsl:include  href="foxslnew/group8/MO-42-006-04-19-NM.xsl" />
      <xsl:include  href="foxslnew/group8/MO-42-007-04-19-NM.xsl" />
      <xsl:include  href="foxslnew/group8/LPL-117-NMNew.xsl" />
     
	  <xsl:template match="/">
		
		<fo:root>		
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait" page-width="21cm"   
					page-height="29.7cm" margin-top="10mm" margin-bottom="8mm" margin-left="10mm" 
					margin-right="10mm">
					<fo:region-body margin-top="2.0mm"/>
					<fo:region-before extent="1.0mm"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>		
	
		    <fo:page-sequence master-reference="A4-portrait">		
	
				<fo:flow flow-name="xsl-region-body">
					<!--
					<fo:block>					
						<xsl:call-template name="Policycoverletter" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="HOTLINENOTICELW" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Policycoverletter0" />						
						<xsl:call-template name="Signature" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Policycoverletter1" />						
						<xsl:call-template name="Signature" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Policycoverletter2" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Policycoverletter3" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Policycoverletter4" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Policycoverletter17" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Policycoverletter6" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL1072010" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL1082010" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL1202011" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="SecurityIncident" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL1352014" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="Ofac" />
						<fo:block break-after="page" /> 
						 
						<xsl:call-template name="LPL-AMEND-SD" />
						<fo:block break-after="page" />	
						
						<xsl:call-template name="LPL-AMEND-MO" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-AMEND-MT" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-AMEND-NE" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-AMEND-ND" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-102-NM1" />
						<fo:block break-after="page" />
						<xsl:call-template name="LPL-102-NM2" />
						<fo:block break-after="page" />
						<xsl:call-template name="LPL-117-NM" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-AMEND-NV" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-AMEND-RI" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-AMEND-SD" />
						<fo:block break-after="page" />	
						
						<xsl:call-template name="LPL-AMEND-TN" />
						<fo:block break-after="page" />	
						
						<xsl:call-template name="LPL-AMEND-TX" />
						<fo:block break-after="page" />	
						
						<xsl:call-template name="LPL-AMEND-UT" />
						<fo:block break-after="page" />	
						
						<xsl:call-template name="LPL-AMEND-TX-COMPLAINT" />
						<fo:block break-after="page" />
						<xsl:call-template name="LPL-AMEND-TX-LOSS" />
						<fo:block break-after="page" />
						<xsl:call-template name="LPL-CF-NOTICE" />
						<fo:block break-after="page" />
						<xsl:call-template name="ND-DWL-Notice" />
						<fo:block break-after="page" />
						<xsl:call-template name="NM-DWL-Notice" />
						<fo:block break-after="page" />	
						
						<xsl:call-template name="LPL-AMEND-CO" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-GA" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-IA" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-IL" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-MA" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-ME" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-NH" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-OH" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-OR" />
						<fo:block break-after="page" />	
						<xsl:call-template name="LPL-AMEND-WA" />
						<fo:block break-after="page" />
						<xsl:call-template name="LPL-AMEND-WI" />
						<fo:block break-after="page" />
						<xsl:call-template name="LPL-AMEND-WV" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="LPL-CF-NOTICE" />
						<fo:block break-after="page" />
						<xsl:call-template name="CODisclosureForm" />
						<fo:block break-after="page" />
						<xsl:call-template name="FM206480910" />
						<fo:block break-after="page" />
						
					-->	 				
					<fo:block>
															
						<xsl:call-template name="Policycoverletter" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="HOTLINENOTICELW" />
						<fo:block break-after="page" />
						
						<xsl:if test="response/templates/template_id_LPL_099= 'LPL_099'">
							<xsl:call-template name="Policycoverletter0" />
							<fo:block break-after="page" />													
						</xsl:if>						
						
						<xsl:if test="response/templates/template_id_LPL_100= 'LPL_100'">
							<xsl:call-template name="Policycoverletter1" />						
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:call-template name="Signature" />
						<fo:block break-after="page" />	
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'AR'">
							<xsl:call-template name="LPL-AMEND-AR-CF" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_102= 'LPL_102'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'NM'">
								
								<xsl:if test="response/address_freeform_01/data/StateCode = 'VA' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
									<xsl:call-template name="Policycoverletter2" />
									<fo:block break-after="page" />
								</xsl:if>
								<xsl:if test="response/address_freeform_01/data/StateCode != 'VA'">
									<xsl:call-template name="Policycoverletter2" />
									<fo:block break-after="page" />
								</xsl:if>
								
							</xsl:if>
						</xsl:if>
						<!-- <xsl:if test="response/address_freeform_01/data/StateCode = 'NM'">
							<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
					     			<xsl:call-template name="LPL-102-NM1" />
					     		</xsl:when>
					     		<xsl:otherwise>							     		
					     			<xsl:call-template name="LPL-102-NM2" />
					     		</xsl:otherwise>
				     		</xsl:choose>
							<fo:block break-after="page" />
						</xsl:if> -->
															<!-- Group 3 State ('NM') -->
										
						<xsl:if test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM1'">
							<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
					     			<xsl:call-template name="LPL-102-NM1" />
					     		</xsl:when>
					     		<xsl:otherwise>							     		
					     			<xsl:call-template name="LPL-102-NM2" />
					     		</xsl:otherwise>
				     		</xsl:choose>
							<fo:block break-after="page" />
						</xsl:if>	
						
						<xsl:if test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM2'">
							
							<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/IsDollarDefense='Y' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
					     			<xsl:call-template name="LPL-AMEND-NM1" />
					     		</xsl:when>
					    	</xsl:choose>
						<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/LimitSequence &gt;=28 and response/policycoverage_freeform_01/data/IsDollarDefense!='Y' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'N'">
					     			<xsl:call-template name="LPL-AMEND-NM2" />
					     		</xsl:when>
					    	</xsl:choose>
					    	
					    	<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/LimitSequence &gt;=28 and response/policycoverage_freeform_01/data/IsDollarDefense='Y' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'N'">
					     			<xsl:call-template name="LPL-AMEND-NM3" />
					     		</xsl:when>
					    	</xsl:choose>
							<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/LimitSequence &gt;=28 and response/policycoverage_freeform_01/data/IsDollarDefense!='Y' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
					     			<xsl:call-template name="LPL-AMEND-NM4" />
					     		</xsl:when>
					    	</xsl:choose>
							<fo:block break-after="page" />
					
						</xsl:if>					
						
						<!-- <xsl:if test="response/templates/template_id_LPL_102= 'LPL_102'">
							<xsl:choose>
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NM'">
						     		<xsl:choose>
							     		<xsl:when test="response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
							     			<xsl:call-template name="LPL-102-NM1" />
							     		</xsl:when>
							     		<xsl:otherwise>							     		
							     			<xsl:call-template name="LPL-102-NM2" />
							     		</xsl:otherwise>
						     		</xsl:choose>
									<fo:block break-after="page" />
						     	</xsl:when> 						     	   
		                      	<xsl:otherwise>
		                         	<xsl:call-template name="Policycoverletter2" />
									<fo:block break-after="page" />
		                      </xsl:otherwise>
		                     </xsl:choose>
						</xsl:if> -->	
														
						<xsl:if test="response/templates/template_id_LPL_103= 'LPL_103'">
							<xsl:call-template name="Policycoverletter3" />
							<fo:block break-after="page" />						
						</xsl:if>						
						
						<xsl:if test="response/templates/template_id_LPL_104= 'LPL_104'">
							<xsl:call-template name="Policycoverletter4" />
							<fo:block break-after="page" />								
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_117= 'LPL_117'">
							<xsl:choose>
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NJ'">
						     		<xsl:call-template name="LPL-117-NJ" />
									<fo:block break-after="page" />
						     	</xsl:when> 
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM1'">
						     		<xsl:call-template name="LPL-117-NM" />
									<fo:block break-after="page" />
						     	</xsl:when> 
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM2' and response/policycoverage_freeform_01/data/IsClaimOptionType='Y'">
						     		<xsl:call-template name="LPL-117-NMNew" />
									<fo:block break-after="page" />
						     	</xsl:when>    
		                      	<xsl:otherwise>
		                         	<xsl:call-template name="Policycoverletter17" />
									<fo:block break-after="page" />
		                      </xsl:otherwise>
		                     </xsl:choose>														
						</xsl:if>								
						
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
						
						<xsl:if test="response/templates/template_id_LPL_127= 'LPL_127'">
							<xsl:call-template name="SecurityIncident" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_135= 'LPL_135'">
							<xsl:if test="response/additionaldata_freeform_01/data/IsSuitForFeesAction='Y'">
								<xsl:call-template name="LPL1352014" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						 	
											
						<!--Commented
						<xsl:if test="response/templates/template_id_LPL_105= 'LPL_105'">
							<xsl:call-template name="Policycoverletter5" />
							<fo:block break-after="page" />
						</xsl:if>						
						
						<xsl:if test="response/templates/template_id_LPL_121= 'LPL_121'">
							<xsl:if test="response/additionaldata_freeform_01/data/IsPreviouFirmFormSelected= 'Y'">
								<xsl:call-template name="LPL1212012" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>	
						
						<xsl:if test="response/templates/template_id_LPL_108= 'LPL_108'">
							<xsl:call-template name="Policycoverletter8" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_125= 'LPL_125'">
							<xsl:call-template name="ProfessionalServiceEndorsement" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_LPL_126= 'LPL_126'">
							<xsl:call-template name="PredecessorFirmDefinition" />
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
						-->					
						
						<!-- Group 1 States	('AL','AR','AZ','DC','DE','ID','IN','KY','PA','MI','NJ')-->
						<xsl:if test="response/address_freeform_01/data/StateCode= 'AL'">
							<xsl:call-template name="LPL-AMEND-AL" />
							<fo:block break-after="page" />
						</xsl:if>
							
						<xsl:if test="response/address_freeform_01/data/StateCode= 'AR'">
							<xsl:call-template name="LPL-AMEND-AR" />
							<fo:block break-after="page" />
							<xsl:if test="response/policycoverage_freeform_01/data/IsClaimExpensesType != 'Y'">
								  <xsl:call-template name="AR-DWL-Notice" />
								<fo:block break-after="page" />
							</xsl:if>							
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
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'ID'">
							<xsl:call-template name="LPL-AMEND-ID" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'IN'">
							<xsl:call-template name="LPL-AMEND-IN" />
							<fo:block break-after="page" />
						</xsl:if>						
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NJ'">
							<xsl:call-template name="LPL-AMEND-NJ" />
							<fo:block break-after="page" />
						</xsl:if>	
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MI'">
							<xsl:call-template name="LPL-AMEND-MI" />
							<fo:block break-after="page" />
							<xsl:call-template name="FM3030260214" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'PA'">
							<xsl:call-template name="LPL-AMEND-PA" />
							<fo:block break-after="page" />								
						</xsl:if>
						  
						<xsl:if test="response/address_freeform_01/data/StateCode= 'KY'">
							<xsl:call-template name="LPL-AMEND-KY" />
							<fo:block break-after="page" />							
						</xsl:if>
						
						<!-- Group 2 States ('MO','MT','NE','ND','NV','RI','SD','TN','TX','UT') -->
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MO'">
							<xsl:call-template name="LPL-AMEND-MO" />
							<fo:block break-after="page" />
							<xsl:call-template name="LPL-CF-NOTICE" />
							<fo:block break-after="page" />							
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MT'">
							<xsl:call-template name="LPL-AMEND-MT" />
							<fo:block break-after="page" />
							<xsl:if test="response/policycoverage_freeform_01/data/IsClaimExpensesType != 'Y'">
								  <xsl:call-template name="MT-DWL-Notice" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NE'">
							<xsl:call-template name="LPL-AMEND-NE" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'ND'">
							<xsl:call-template name="LPL-AMEND-ND" />
							<fo:block break-after="page" />
							<xsl:if test="response/policycoverage_freeform_01/data/IsClaimExpensesType != 'Y'">
								  <xsl:call-template name="ND-DWL-Notice" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NV'">
							<xsl:call-template name="LPL-AMEND-NV" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'RI'">
							<xsl:call-template name="LPL-AMEND-RI" />
							<fo:block break-after="page" />	
							<xsl:call-template name="LPL-CF-NOTICE" />
							<fo:block break-after="page" />						
						</xsl:if>	
											
						<xsl:if test="response/address_freeform_01/data/StateCode= 'SD'">
							<xsl:call-template name="LPL-AMEND-SD" />
							<fo:block break-after="page" />
						</xsl:if>	
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'TN'">
							<xsl:call-template name="LPL-AMEND-TN" />
							<fo:block break-after="page" />		
							<xsl:call-template name="LPL-CF-NOTICE" />
							<fo:block break-after="page" />					
						</xsl:if>
												
						<xsl:if test="response/address_freeform_01/data/StateCode= 'TX'">
							<xsl:call-template name="LPL-AMEND-TX" />
							<fo:block break-after="page" />
							<xsl:call-template name="LPL-AMEND-TX-LOSS" />
							<fo:block break-after="page" /> 
							<xsl:call-template name="LPL-AMEND-TX-COMPLAINT-NEW" />
							<fo:block break-after="page" /> 
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'UT'">
							<xsl:call-template name="LPL-AMEND-UT" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<!-- Group 3 State ('NM') -->
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NM' and response/NMRatingVersion = 'NM1'">
							<xsl:if test="response/policycoverage_freeform_01/data/IsClaimExpensesType != 'Y'">
								  <xsl:call-template name="NM-DWL-Notice" />
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>						
						<xsl:if test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM2'">
							<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/LimitSequence &gt;=28 and response/policycoverage_freeform_01/data/IsDollarDefense!='Y' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'N'">
					     			<xsl:call-template name="NM-DWL-Notice002" />
					     		</xsl:when>
					    	</xsl:choose>
					    	
					    	<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/LimitSequence &gt;=28 and response/policycoverage_freeform_01/data/IsDollarDefense='Y' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'N'">
					     			<xsl:call-template name="NM-DWL-Notice006" />
					     			
					     		</xsl:when>
					    	</xsl:choose>
							<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/LimitSequence &gt;=28 and response/policycoverage_freeform_01/data/IsDollarDefense!='Y' and response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
					     				<xsl:call-template name="NM-DWL007-Notice" />
					     		
					     		</xsl:when>
					    	</xsl:choose>
							
					
						</xsl:if>	
						<!-- Group 4 States ('CO','GA','IA','IL','MA','ME','NH','OH','OR','WA','WI','WV') -->									
						<xsl:if test="response/address_freeform_01/data/StateCode= 'CO'">							
							<xsl:call-template name="LPL-AMEND-CO" />
							<fo:block break-after="page" />
							<xsl:call-template name="CODisclosureForm" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'GA'">
							<xsl:call-template name="LPL-AMEND-GA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'IA'">
							<xsl:call-template name="LPL-AMEND-IA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'IL'">
							<xsl:call-template name="LPL-AMEND-IL" />
							<fo:block break-after="page" />
							<xsl:call-template name="FM206480910" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MA'">
							<xsl:call-template name="LPL-AMEND-MA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'ME'">
							<xsl:call-template name="LPL-AMEND-ME" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NH'">
							<xsl:call-template name="LPL-AMEND-NH" />
							<fo:block break-after="page" />
							<xsl:call-template name="LPL-CF-NOTICE" />
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
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WA'">
							<xsl:call-template name="LPL-AMEND-WA" />
							<fo:block break-after="page" />
							<xsl:call-template name="LPL-EXCEPTION-WA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WI'">
							<xsl:call-template name="LPL-AMEND-WI" />
							<fo:block break-after="page" />
							<xsl:call-template name="LPL-CF-NOTICE_NEW" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WV'">
							<xsl:call-template name="LPL-AMEND-WV" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<!-- Group 5 States ('AK','CT','HI','KS','LA','MD','MN','MS','NC','SC','VA','VT','WY') -->						
				     	<xsl:if test="response/address_freeform_01/data/StateCode= 'AK'">							
							<xsl:call-template name="LPL-AMEND-AK" />
							<fo:block break-after="page" />
							<xsl:choose>
					     		<xsl:when test="response/policycoverage_freeform_01/data/IsClaimExpensesType = 'Y'">
					     			<xsl:call-template name="FM_2_0_1033_03_12_AK" />
					     		</xsl:when>
					     		<xsl:otherwise>							     		
					     			<xsl:call-template name="MI_43_002_06_15_AK" />
					     		</xsl:otherwise>
				     		</xsl:choose>							
						</xsl:if>	
						<xsl:if test="response/address_freeform_01/data/StateCode= 'CT'">							
							<xsl:call-template name="LPL-AMEND-CT" />
							<fo:block break-after="page" />							
						</xsl:if>
														
						<xsl:if test="response/address_freeform_01/data/StateCode= 'HI'">							
							<xsl:call-template name="LPL-AMEND-HI" />
							<fo:block break-after="page" />							
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'KS'">
							<xsl:call-template name="LPL-AMEND-KS" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'LA'">							
							<xsl:call-template name="LPL-AMEND-LA" />
							<fo:block break-after="page" />							
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MD'">
							<xsl:call-template name="LPL-AMEND-MD" />
							<fo:block break-after="page" />
						</xsl:if>
						      
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MN'">
							<xsl:call-template name="LPL-AMEND-MN" />
							<fo:block break-after="page" />
							<xsl:call-template name="FM_2_0_1019_10_12_MN" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'MS'">
							<xsl:call-template name="LPL-AMEND-MS" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'NC'">
							<xsl:call-template name="LPL-AMEND-NC" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'SC'">
							<xsl:call-template name="LPL-AMEND-SC" />
							<fo:block break-after="page" />
							<xsl:call-template name="MI_40_002_09_16_SC" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'VA'">
							<xsl:call-template name="LPL-AMEND-VA" />
							<fo:block break-after="page" />
							<xsl:call-template name="FM_2_0_1029_01_12_VA" />
							<fo:block break-after="page" />
							<xsl:call-template name="MO_43_001_04_18_VA" />
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'VT'">
							<xsl:call-template name="LPL-AMEND-VT" />
							<fo:block break-after="page" />
							<!-- <xsl:call-template name="LPL-EXCEPTION-VT" />
							<fo:block break-after="page" /> -->
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'WY'">
							<xsl:call-template name="LPL-AMEND-WY" />
							<fo:block break-after="page" />							
				     		<xsl:if test="response/policycoverage_freeform_01/data/IsClaimExpensesType != 'Y'">
				     			<xsl:call-template name="MO_42_005_04_17_WY" />
								<fo:block break-after="page" />
				     		</xsl:if>							
						</xsl:if>
						
						<!-- Group 6 States ('OK') -->						
				     	<xsl:if test="response/address_freeform_01/data/StateCode= 'OK'">
							<xsl:call-template name="LPL-AMEND-OK" />
							<fo:block break-after="page" />
							<!-- <xsl:call-template name="LPL-EXCEPTION-OK" />
							<fo:block break-after="page" /> -->
						</xsl:if>
						
						<!-- Group 7 States ('CA','FL') -->						
				     	<xsl:if test="response/address_freeform_01/data/StateCode= 'CA'">
							<xsl:call-template name="LPL-AMEND-CA" />
							<fo:block break-after="page" />							
						</xsl:if>
						
						<xsl:if test="response/address_freeform_01/data/StateCode= 'FL'">
							<xsl:call-template name="LPL-AMEND-FL" />
							<fo:block break-after="page" />
							<xsl:call-template name="MI_40_002_09_16_FL" />
							<fo:block break-after="page" />
						</xsl:if>
							<xsl:if test="response/templates/template_id_LPL_181= 'LPL_181'">
							<xsl:call-template name="LPL1812020" />
							<fo:block break-after="page" />								
						</xsl:if>
						<xsl:if test="response/templates/template_id_LPL_182= 'LPL_182'">
							<xsl:call-template name="LPL1822020" />
							<fo:block break-after="page" />								
						</xsl:if>
						<xsl:call-template name="Ofac" />
						<fo:block break-after="page" />						
				   	</fo:block>
		
					<!-- <fo:block id="TheVeryLastPage"> </fo:block> -->
				</fo:flow>
					
			</fo:page-sequence>
		
		</fo:root>
     </xsl:template>    
</xsl:stylesheet>
		    	