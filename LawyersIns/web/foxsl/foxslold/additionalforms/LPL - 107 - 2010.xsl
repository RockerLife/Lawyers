<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="LPL1072010">
		
				    <fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1070910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">PREDECESSOR FIRM PRIOR ACTS EXCLUSION ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				    <fo:block  font-size="10px"> It is agreed that Item 6. of the Declarations, <fo:inline font-weight="bold" font-style="italic">Prior Acts Date(s)</fo:inline>  is/are amended to the date shown in Column B below with respect to the <fo:inline font-weight="bold" font-style="italic">Predecessor Firm(s)</fo:inline> shown in Column A below</fo:block>
				 	 <fo:block margin-top="10mm"/>
				 <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"   text-align="center"> (A) </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"> (B) </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center"><fo:inline font-weight="bold" font-style="italic"> Predecessor Firm</fo:inline> Name </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"> Prior Acts Date: </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"> </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"> </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"  text-align="center" margin-top="1cm"><xsl:if test="response/additionaldata_freeform_01/data/PredecessorFirmName_1 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PredecessorFirmName_1"></xsl:value-of></xsl:if>  </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center" margin-top="1cm"><xsl:if test="response/additionaldata_freeform_01/data/PriorActsDate_1 != '' "> <xsl:value-of select="response/additionaldata_freeform_01/data/PriorActsDate_1"></xsl:value-of></xsl:if>  </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"  text-align="center" margin-top="1cm"><xsl:if test="response/additionaldata_freeform_01/data/PredecessorFirmName_2 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PredecessorFirmName_2"></xsl:value-of></xsl:if>  </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center" margin-top="1cm"><xsl:if test="response/additionaldata_freeform_01/data/PriorActsDate_2 != '' "> <xsl:value-of select="response/additionaldata_freeform_01/data/PriorActsDate_2"></xsl:value-of></xsl:if>  </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"  text-align="center" margin-top="1cm"><xsl:if test="response/additionaldata_freeform_01/data/PredecessorFirmName_3 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PredecessorFirmName_3"></xsl:value-of></xsl:if>  </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center" margin-top="1cm"><xsl:if test="response/additionaldata_freeform_01/data/PriorActsDate_3 != '' "> <xsl:value-of select="response/additionaldata_freeform_01/data/PriorActsDate_3"></xsl:value-of></xsl:if>  </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				     
				     
				     <fo:block margin-top="5cm"/>
				     <fo:block text-align="left" start-indent="4cm" font-size="10px">All other terms, conditions and limitations of the policy remain unchanged.</fo:block>
				     <fo:block margin-top="8mm"/>
				     <fo:block>
				       <fo:table  text-align="center">
					   <fo:table-column column-width = "80mm"/>
				       <fo:table-column column-width = "80mm"/>
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/authorized_signature"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/date"/></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 <fo:table-row>
					    		  		<fo:table-cell padding-left="4pt" padding-top="4pt">
					               			<fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					             </fo:table-row>
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center">Authorized Representative</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">Date</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>				       
	         	<fo:block margin-top="50mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1070910"/></fo:block>
				 
     </xsl:template>
</xsl:stylesheet>




					
				    	