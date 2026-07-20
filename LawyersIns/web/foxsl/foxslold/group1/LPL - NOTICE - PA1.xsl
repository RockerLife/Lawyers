<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-State-PA-2" >
	<!--  
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
	-->	
				    <fo:block text-align="center" font-size="16px"><fo:inline font-weight="bold">NOTICE TO PENNSYLVANIA POLICYHOLDERS</fo:inline></fo:block>
				    <fo:block margin-top="12mm"/>
				    
				   
				     <fo:block margin-top="15mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="16px" >DEFENSE WITHIN LIMITS NOTIFICATION</fo:block>
				     <fo:block margin-top="15mm"/>
				    
				
				     <fo:block text-align="left" font-size="16px" font-weight="bold">ANY “DEFENSE EXPENSES” PAID UNDER THIS POLICY WILL REDUCE THE LIMIT OF INSURANCE AND MAY EXHAUST THE LIMIT COMPLETELY</fo:block>
				     <fo:block margin-top="15mm"/>
				     
				     <fo:block text-align="left" font-size="16px" font-weight="bold">“DEFENSE EXPENSES” MEAN REASONABLE AND NECESSARY FEES, COSTS AND EXPENSES CONSENTED TO BY THE INSURED RESULTING SOLELY FROM THE INVESTIGATION, LEGAL DEFENSE AND LEGAL APPEAL OF A CLAIM AGAINST THE INSURED, BUT EXCLUDING SALARIES OF OFFICERS AND EMPLOYERS OF THE INSURER.
					</fo:block>

					
					
					
			  <fo:block margin-top="130mm"/>
			  <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLNOTICEPA10910"/></fo:block>
			  
		  <!--  
		    </fo:flow>
		  </fo:page-sequence>
		</fo:root>	  
			-->
     </xsl:template>
</xsl:stylesheet>
