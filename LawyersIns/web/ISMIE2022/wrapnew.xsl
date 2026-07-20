<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	 
	  <xsl:include  href="foxslnew/Policycoverletter.xsl" />
	  <xsl:include  href="foxslnew/PolicyHolderNotice.xsl" />
	  <xsl:include  href="foxslnew/ALA - 04 - F004 - 2021.xsl" />
	  <xsl:include  href="foxslnew/ALA - 04 - F005 - 2021.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - F006 - 2021.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - F008 - 2021.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - F014 - 2021.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - F014 - NJ - 2021.xsl" />        
      <xsl:include  href="foxslnew/ALA - 04 - F034 - 2021.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - P001 - 2021.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - P002 - 2021.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - P002 - PA - 2021.xsl" />
   	  <xsl:include  href="foxslnew/HotlineNoticeLW.xsl" />
      <xsl:include  href="foxslnew/ofac_notice.xsl" />
      <xsl:include  href="foxslnew/CommonHeader.xsl" />
         <!-- Group 1 States ('AL','AR','AZ','DC','DE','ID','IN','KY','MI','NJ','PA')-->      
      <xsl:include  href="foxslnew/group1/ALA - 04 - F001 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F002 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F003 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F021 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - F022 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F024 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - F025 - 2021.xsl" />      
      <xsl:include  href="foxslnew/group1/ALA - 04 - F026 - 2021.xsl" /> 
      <xsl:include  href="foxslnew/group1/ALA - 04 - F026 - 2025.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - F027 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F028 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F029 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F030 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - F031 - 2021.xsl" />
      <xsl:include  href="foxslnew/group1/ALA - 04 - F032 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - F033 - 2021.xsl" />      
      <xsl:include  href="foxslnew/group1/ALA - 04 - F036 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - F040 - 2021.xsl" />  
      <!-- <xsl:include  href="foxslnew/group1/ALA - 04 - S003 - FL - 2021.xsl" /> -->  
      <xsl:include  href="foxslnew/group1/ALA - 04 - F023 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - S011 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/ALA - 04 - S008 - 2021.xsl" />  
      <xsl:include  href="foxslnew/group1/MI-40-001-12-15-TX.xsl" />  
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F012 - 2021.xsl" />  
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F013 - 2021.xsl" />  
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F019 - 2021.xsl" />
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F043 - 2022.xsl" />  
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F035 - 2021.xsl" />  
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F039 - 2021.xsl" />  
	  <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F009 - 2021.xsl" />
	  <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F009 - MA - 2022.xsl" />
	  <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F010 - 2021.xsl" />
	  <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F037 - 2021.xsl" />
	  <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F017 - 2021.xsl" />
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F046 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F047 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F048 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F049 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F050 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F051 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F052 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F053 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F054 - 2022.xsl" />  
	  <xsl:include  href="foxslnew/group2/ALA - 04 - F055 - 2022.xsl" />  
      <xsl:include  href="foxslnew/group2/ALA - 04 - S012 - 2021.xsl" />
      <xsl:include  href="foxslnew/group2/ALA - 04 - F060 - 2022.xsl" />   
      <xsl:include  href="foxslnew/group2/ALA - 04 - F062 - 2022.xsl" />  
      <xsl:include  href="foxslnew/ALA - 04 - P001 - GA - 2022.xsl" />
      <xsl:include  href="foxslnew/group2/ALA - 04 - F057 - 2022.xsl" />
      <xsl:include  href="foxslnew/group2/ALA - 04 - F063 - 2022.xsl" />
      <xsl:include  href="foxslnew/group2/ALA - 04 - F064 - 2022.xsl" />
      <xsl:include  href="foxslnew/group2/ALA - 04 - F061 - 2022.xsl" />
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F0060 - 2022.xsl" />  
      <xsl:include  href="foxslnew/group3/ALA - 04 - F065 - 2023.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - P001 - CA - 2024.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - P001 - 2024.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - P002 - 2024.xsl" />
      <xsl:include  href="foxslnew/ALA - 04 - P002 - PA - 2024.xsl" />
      <xsl:include  href="foxslnew/additionalforms/ALA - 04 - F066 - 2022.xsl" />
   <!--  
      <xsl:include  href="foxslnew/additionalforms/TitleAgentExclusionEndorsement.xsl" />
        <xsl:include  href="foxslnew/additionalforms/SpecifiedAttorneyOrEntityPriorActEndorsement.xsl" />
        <xsl:include  href="foxslnew/additionalforms/ExclusionDAmendaryEndorsement.xsl" />
        <xsl:include  href="foxslnew/endorsement/LPL - 105 - 2017.xsl" />
       <xsl:include  href="foxslnew/additionalforms/CareerCoverageEndorsement.xsl" />
       -->
     
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
								
					<fo:block>
															
						<xsl:call-template name="Policycoverletter" />
						<fo:block break-after="page" />
						
						<xsl:call-template name="PolicyHolderNotice" />
						<fo:block break-after="page" />
					
					<xsl:if test="response/templates/template_id_ALA_P002= 'ALA_P002'">

						

							<xsl:choose>
							 <xsl:when test="response/address_freeform_01/data/StateCode = 'PA'">
								 <xsl:call-template name="Policycoverletter0_PA_2024" />
								<fo:block break-after="page" />	
							 </xsl:when>
							  <xsl:when test="response/address_freeform_01/data/StateCode = 'AZ' or response/address_freeform_01/data/StateCode = 'GA'
							 or response/address_freeform_01/data/StateCode = 'IN' or response/address_freeform_01/data/StateCode = 'MI'
							 or response/address_freeform_01/data/StateCode = 'MN' or response/address_freeform_01/data/StateCode = 'NC'
							 or response/address_freeform_01/data/StateCode = 'TX' or response/address_freeform_01/data/StateCode = 'UT'
							 or response/address_freeform_01/data/StateCode = 'NV' or response/address_freeform_01/data/StateCode = 'WI'
							 or response/address_freeform_01/data/StateCode = 'CT' or response/address_freeform_01/data/StateCode = 'MA'
							 or response/address_freeform_01/data/StateCode = 'FL' or response/address_freeform_01/data/StateCode = 'ID'
							 or response/address_freeform_01/data/StateCode = 'KS' or response/address_freeform_01/data/StateCode = 'SC'
							 or response/address_freeform_01/data/StateCode = 'NJ' or response/address_freeform_01/data/StateCode = 'MO'
							 or response/address_freeform_01/data/StateCode = 'VA' or response/address_freeform_01/data/StateCode = 'CO'
							 or response/address_freeform_01/data/StateCode = 'OH'">
							   <xsl:call-template name="Policycoverletter0_group5" />		
								<fo:block break-after="page" />	
							 </xsl:when>
							 <xsl:otherwise>
							  <xsl:call-template name="Policycoverletter0" />
								<fo:block break-after="page" />
							 </xsl:otherwise>
						   </xsl:choose>
							
						</xsl:if>
						<!-- <xsl:if test="response/templates/template_id_ALA_P002= 'ALA_P002'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'PA'">
								<xsl:call-template name="Policycoverletter0" />
								<fo:block break-after="page" />
							</xsl:if>
							<xsl:if test="response/address_freeform_01/data/StateCode = 'PA'">
								<xsl:call-template name="Policycoverletter0_PA" />
								<fo:block break-after="page" />	
							</xsl:if>												
						</xsl:if>	 -->					
						
						<!-- <xsl:if test="response/templates/template_id_ALA_P001= 'ALA_P001'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'GA' and response/address_freeform_01/data/StateCode != 'IL' and response/address_freeform_01/data/StateCode != 'CA'">
								<xsl:call-template name="Policycoverletter1" />
								<fo:block break-after="page" />
							</xsl:if>
							<xsl:if test="response/address_freeform_01/data/StateCode = 'GA' or response/address_freeform_01/data/StateCode = 'IL'">
								<xsl:call-template name="Policycoverletter1_GA" />		
								<fo:block break-after="page" />	
							</xsl:if>
							<xsl:if test="response/address_freeform_01/data/StateCode = 'CA'">
								<xsl:call-template name="Policycoverletter1_CA" />		
								<fo:block break-after="page" />	
							</xsl:if>						
							
						</xsl:if> -->
						
						<xsl:if test="response/templates/template_id_ALA_P001= 'ALA_P001'">

							<xsl:choose>
								 <xsl:when test="response/address_freeform_01/data/StateCode = 'CA'">
									  <xsl:call-template name="Policycoverletter1_CA" />		
										<fo:block break-after="page" />	
								 </xsl:when>
								 <xsl:when test="response/address_freeform_01/data/StateCode = 'IL'">
								   <xsl:call-template name="Policycoverletter1_GA" />		
									<fo:block break-after="page" />	
								 </xsl:when>
								 <xsl:when test="response/address_freeform_01/data/StateCode = 'AZ' or response/address_freeform_01/data/StateCode = 'GA'
								 or response/address_freeform_01/data/StateCode = 'IN' or response/address_freeform_01/data/StateCode = 'MI'
								 or response/address_freeform_01/data/StateCode = 'MN' or response/address_freeform_01/data/StateCode = 'NC'
								 or response/address_freeform_01/data/StateCode = 'TX' or response/address_freeform_01/data/StateCode = 'UT'
								 or response/address_freeform_01/data/StateCode = 'NV' or response/address_freeform_01/data/StateCode = 'WI'
								 or response/address_freeform_01/data/StateCode = 'CT' or response/address_freeform_01/data/StateCode = 'MA'
								 or response/address_freeform_01/data/StateCode = 'PA' or response/address_freeform_01/data/StateCode = 'NJ'
								 or response/address_freeform_01/data/StateCode = 'FL' or response/address_freeform_01/data/StateCode = 'ID'
							 	 or response/address_freeform_01/data/StateCode = 'KS' or response/address_freeform_01/data/StateCode = 'SC'
								 or response/address_freeform_01/data/StateCode = 'MO' or response/address_freeform_01/data/StateCode = 'VA'
								 or response/address_freeform_01/data/StateCode = 'CO' or response/address_freeform_01/data/StateCode = 'OH'
								 ">
								   <xsl:call-template name="Policycoverletter_group5" />		
									<fo:block break-after="page" />	
								 </xsl:when>
								 <xsl:otherwise>
								  <xsl:call-template name="Policycoverletter1" />
									<fo:block break-after="page" />
								 </xsl:otherwise>
							   </xsl:choose>
							
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F021= 'ALA_F021'">
							<xsl:call-template name="ALA04F021" />						
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_ALA_F004= 'ALA_F004'">
							<xsl:call-template name="Policycoverletter2" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F005= 'ALA_F005'">
							<xsl:call-template name="Policycoverletter3" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F006= 'ALA_F006'">
							<xsl:call-template name="Policycoverletter4" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F008= 'ALA_F008'">
							<xsl:call-template name="Policycoverletter6" />						
							<fo:block break-after="page" />
						</xsl:if>
						<!-- <xsl:if test="response/templates/template_id_ALA_F009= 'ALA_F009'">
							<xsl:call-template name="ALA04F009" />						
							<fo:block break-after="page" />
						</xsl:if> -->
						<xsl:if test="response/templates/template_id_ALA_F009= 'ALA_F009'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'MA'">
								<xsl:call-template name="ALA04F009" />						
								<fo:block break-after="page" />
							</xsl:if>
							<xsl:if test="response/address_freeform_01/data/StateCode = 'MA'">
								<xsl:call-template name="ALA04F009_MA" />						
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F010= 'ALA_F010'">
							<xsl:call-template name="ALA04F010" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F014= 'ALA_F014'">
							<xsl:if test="response/address_freeform_01/data/StateCode != 'NJ'">
								<xsl:call-template name="ALA04F014" />						
								<fo:block break-after="page" />
							</xsl:if>
							<xsl:if test="response/address_freeform_01/data/StateCode = 'NJ'">
								<xsl:call-template name="ALA04F014_NJ" />						
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F025= 'ALA_F025'">
							<xsl:call-template name="ALA04F025" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F003= 'ALA_F003'">
							<xsl:call-template name="ALA04F003" />						
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_ALA_F026= 'ALA_F026'">
							<xsl:call-template name="ALA04F026_2025" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F037= 'ALA_F037'">
							<xsl:call-template name="ALA04F037" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F034= 'ALA_F034'">
							<xsl:call-template name="SecurityIncident" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F033= 'ALA_F033'">
							<xsl:call-template name="ALA04F033" />						
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_ALA_F032= 'ALA_F032'">
							<xsl:call-template name="ALA04F032" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F023= 'ALA_F023'">
							<xsl:call-template name="ALA04F023" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F017= 'ALA_F017'">
							<xsl:call-template name="ALA04F017" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F001= 'ALA_F001'">
							<xsl:call-template name="ALA04F001" />						
							<fo:block break-after="page" />
						</xsl:if>
						<!-- <xsl:if test="response/templates/template_id_ALA_S003= 'ALA_S003'">
						
							<xsl:if test="response/address_freeform_01/data/StateCode = 'FL'">
									<xsl:call-template name="ALA04S003_FL" />						
								<fo:block break-after="page" />
							</xsl:if>
						</xsl:if> -->
						<xsl:if test="response/templates/template_id_ALA_S012= 'ALA_S012'">
							<xsl:call-template name="ALA04S012" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F022= 'ALA_F022'">
							<xsl:call-template name="ALA04F022" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F002= 'ALA_F002'">
							<xsl:call-template name="CODisclosureForm" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F024= 'ALA_F024'">
							<xsl:call-template name="ALA04F024" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F027= 'ALA_F027'">
							<xsl:call-template name="ALA04F027" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F028= 'ALA_F028'">
							<xsl:call-template name="ALA04F028" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F029= 'ALA_F029'">
							<xsl:call-template name="ALA04F029" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F030= 'ALA_F030'">
							<xsl:call-template name="ALA04F030" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F031= 'ALA_F031'">
							<xsl:call-template name="ALA04F031" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F036= 'ALA_F036'">
							<xsl:call-template name="ALA04F036" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F050= 'ALA_F050'">
							<xsl:call-template name="ALA04F050" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F051= 'ALA_F051'">
							<xsl:call-template name="ALA04F051" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F046= 'ALA_F046'">
							<xsl:call-template name="ALA04F046" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F052= 'ALA_F052'">
							<xsl:call-template name="ALA04F052" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F053= 'ALA_F053'">
							<xsl:call-template name="ALA04F053" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F054= 'ALA_F054'">
							<xsl:call-template name="ALA04F054" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F047= 'ALA_F047'">
							<xsl:call-template name="ALA04F047" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F048= 'ALA_F048'">
							<xsl:call-template name="ALA04F048" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F055= 'ALA_F055'">
							<xsl:call-template name="ALA04F055" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_S008= 'ALA_S008'">
							<xsl:call-template name="ALA04S008" />						
							<fo:block break-after="page" />	
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_ALA_S011= 'ALA_S011'">
							<xsl:call-template name="ALA04S011" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F049= 'ALA_F049'">
							<xsl:call-template name="ALA04F049" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F060= 'ALA_F060'">
							<xsl:call-template name="ALA04F060" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F062= 'ALA_F062'">
							<xsl:call-template name="ALA04F062" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F063= 'ALA_F063'">
							<xsl:call-template name="ALA04F063" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F064= 'ALA_F064'">
							<xsl:call-template name="ALA04F064" />						
							<fo:block break-after="page" />
						</xsl:if>
							<xsl:if test="response/templates/template_id_ALA_F061= 'ALA_F061'">
							<xsl:call-template name="ALA04F061" />						
							<fo:block break-after="page" />
						</xsl:if>
						<!-- <xsl:if test="response/templates/template_id_ALA_F0060= 'ALA_F0060'">
							<xsl:call-template name="ALA04F0060" />						
							<fo:block break-after="page" />
						</xsl:if> -->
						<xsl:if test="response/templates/template_id_ALA_F066= 'ALA_F066'">
							<xsl:call-template name="ALA04F066" />						
							<fo:block break-after="page" />
						</xsl:if>
						
						
						<xsl:if test="response/templates/template_id_ALA_F065= 'ALA_F065'">
							<xsl:call-template name="ALA04F065" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F040= 'ALA_F040'">
							<xsl:call-template name="ALA04F040" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F039= 'ALA_F039'">
							<xsl:call-template name="CareerCoverageEndorsement" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F019= 'ALA_F019'">
							<xsl:call-template name="TitleAgency" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/templates/template_id_ALA_F043= 'ALA_F043'">
							<xsl:call-template name="ProfessionalServicesExclusionEndorsement" />						
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:if test="response/templates/template_id_ALA_F057= 'ALA_F057'">
							<xsl:call-template name="ALA04F057" />						
							<fo:block break-after="page" />
						</xsl:if>
						
						<xsl:call-template name="Ofac" />
						<fo:block break-after="page" />						
				   	</fo:block>
		
				</fo:flow>
					
			</fo:page-sequence>
		
		</fo:root>
     </xsl:template>    
</xsl:stylesheet>
		    	