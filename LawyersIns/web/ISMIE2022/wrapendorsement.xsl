<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	 
	  <xsl:include  href="foxslnew/endorsement/CommonHeaderFilled.xsl" />
	<!--   <xsl:include  href="foxslnew/CommonDetailFilled.xsl" /> -->
     
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - S005 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F015 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F041 - 2022.xsl"/>
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F011 - 2021.xsl"/>
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F004 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F005 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F006 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F018 - F004 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F018 - F005 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F018 - F006 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F018 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F016 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F045 - 2022.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F045 - MA - 2022.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F007 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F020 - 2022.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F038 - 2021.xsl" />
      <xsl:include  href="foxslnew/endorsement/ALA - 04 - F007 - VA - 2022.xsl" />
      <!-- <xsl:include  href="foxslnew/endorsement/LPL - 105 - 2017.xsl" />
      
      <xsl:include  href="foxslnew/endorsement/LPL - 109 - 2010.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 110 - 2017.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 134 - 2017.xsl" />    
      <xsl:include  href="foxslnew/endorsement/LPL - 123 - 2017.xsl" />   
      <xsl:include  href="foxslnew/endorsement/LPL - 114 - 2017.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 136 - 2017.xsl" />  
      <xsl:include  href="foxslnew/endorsement/LPL - 136 - 102 - 2017.xsl" />      
      <xsl:include  href="foxslnew/endorsement/LPL - 136 - 103 - 2017.xsl" /> 
      <xsl:include  href="foxslnew/endorsement/LPL - 136 - 104 - 2017.xsl" /> -->
     
      
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
					
						<xsl:if test="response/ALA_S005_ENDORSEMENT= 'Y'">
							<xsl:call-template name="S005" />						
							<fo:block break-after="page" />
						</xsl:if> 					
						<xsl:if test="response/ALA_F015_ENDORSEMENT= 'Y'">
							<xsl:call-template name="F015" />						
							<fo:block break-after="page" />
						</xsl:if>
						<!-- <xsl:if test="response/ALA_F041_ENDORSEMENT= 'Y'">
							<xsl:call-template name="ALAF041" />						
							<fo:block break-after="page" />
						</xsl:if> -->
						<xsl:if test="response/ALA_F011_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter9" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/ALA_F004_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter2" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/ALA_F005_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter3" />	
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/ALA_F006_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter4" />	
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/ALA_F018_F004_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter18004" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/ALA_F018_F005_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter36103" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/ALA_F018_F006_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter18006" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/ALA_F016_ENDORSEMENT= 'Y'">
							<xsl:call-template name="LPL-General" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/ALA_F045_ENDORSEMENT= 'Y'">
							<xsl:if test="response/StateCode != 'MA'">
								<xsl:call-template name="Policycoverletter10" />						
							<fo:block break-after="page" />
							</xsl:if>
							<xsl:if test="response/StateCode = 'MA'">
								<xsl:call-template name="Policycoverletter10_MA" />						
							<fo:block break-after="page" />
							</xsl:if>												
						</xsl:if>
						<!-- <xsl:if test="response/ALA_F045_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter10" />						
							<fo:block break-after="page" />
						</xsl:if>  -->
						<xsl:if test="response/ALA_F007_ENDORSEMENT= 'Y'">
							<xsl:if test="response/StateCode != 'VA'">
								<xsl:call-template name="ALA04F007" />						
							<fo:block break-after="page" />
							</xsl:if>
							<xsl:if test="response/StateCode = 'VA'">
								<xsl:call-template name="ALA04F007_VA" />						
							<fo:block break-after="page" />
							</xsl:if>												
						</xsl:if>
						<!-- <xsl:if test="response/ALA_F007_ENDORSEMENT= 'Y'">
							<xsl:call-template name="ALA04F007" />						
							<fo:block break-after="page" />
						</xsl:if>  -->
						<xsl:if test="response/ALA_F020_ENDORSEMENT= 'Y'">
							<xsl:call-template name="ALA04F020" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/ALA_F038_ENDORSEMENT= 'Y'">
							<xsl:call-template name="ALA04F038" />						
							<fo:block break-after="page" />
						</xsl:if> 
				   	</fo:block>
				   	
				</fo:flow>
					
			</fo:page-sequence>
		
		</fo:root>
     </xsl:template>    
</xsl:stylesheet>
		    	