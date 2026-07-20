<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	 
	  <xsl:include  href="foxslnew/CommonHeaderFilled.xsl" />
	  <xsl:include  href="foxslnew/CommonDetailFilled.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 102 - 2010.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 103 - 2010.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 104 - 2010.xsl" />
        <xsl:include  href="foxslnew/endorsement/LPL - 105 - 2017.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 109 - 2010.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 110 - 2017.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 134 - 2017.xsl" />    
      <xsl:include  href="foxslnew/endorsement/LPL - 123 - 2017.xsl" />   
      <xsl:include  href="foxslnew/endorsement/LPL - 114 - 2017.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 132 - 2017.xsl" />
      <xsl:include  href="foxslnew/endorsement/LPL - 136 - 102 - 2017.xsl" />      
      <xsl:include  href="foxslnew/endorsement/LPL - 136 - 103 - 2017.xsl" /> 
      <xsl:include  href="foxslnew/endorsement/LPL - 136 - 104 - 2017.xsl" />
       
       
      
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
						<xsl:if test="response/LPL_109_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter9" />	
							<fo:block break-after="page" />	
						</xsl:if>
						<xsl:if test="response/LPL_102_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter2" />	
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_103_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter3" />	
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_104_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter4" />	
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/LPL_114_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter14" />						
							<fo:block break-after="page" />
						</xsl:if> 					
						<xsl:if test="response/LPL_132_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter32" />						
							<fo:block break-after="page" />
						</xsl:if>
						<xsl:if test="response/LPL_136_102_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter36102" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_136_103_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter36103" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_136_104_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter36104" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_110_ENDORSEMENT= 'Y'">
							<xsl:call-template name="Policycoverletter10" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_134_ENDORSEMENT= 'Y'">
							<xsl:call-template name="LPL-General" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_105_ENDORSEMENT= 'Y'">
							<xsl:call-template name="LPL1052017" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<xsl:if test="response/LPL_123_ENDORSEMENT= 'Y'">
							<xsl:call-template name="LPL1232017" />						
							<fo:block break-after="page" />
						</xsl:if> 
						<!--<xsl:call-template name="Policycoverletter2" />	
							<fo:block break-after="page" />
						  <xsl:call-template name="Policycoverletter3" />	
							<fo:block break-after="page" /> 
						<xsl:call-template name="Policycoverletter4" />	
							<fo:block break-after="page" />
						<xsl:call-template name="Policycoverletter36" />						
							<fo:block break-after="page" />
						-->
				   	</fo:block>
				   	
				</fo:flow>
					
			</fo:page-sequence>
		
		</fo:root>
     </xsl:template>    
</xsl:stylesheet>
		    	