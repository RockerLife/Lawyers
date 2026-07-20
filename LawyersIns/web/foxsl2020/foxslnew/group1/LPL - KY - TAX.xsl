<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
 
	<xsl:template match="/" name="LPL-KY-TAX" >
		
				<fo:block  border="2pt solid black">
				    <fo:block text-align="left" font-size="12px" start-indent= "13cm" >Policy Number: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
				    <fo:block margin-top="10mm"/>
				   
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >SCHEDULE OF TAXES, SURCHARGES OR FEES</fo:block>
				     <fo:block text-align="center" font-weight="bold" font-size="12px"  >KENTUCKY MUNICIPAL TAX SCHEDULE</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				    
				    <fo:block >
					<fo:table  text-align="center">
					    <fo:table-column column-width = "110mm" />
				        <fo:table-column column-width = "110mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "1cm" >Named Insured: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px" text-align="left" start-indent= "2cm"  >Effective Date: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					                    
					    		  </fo:table-row>
					    		  
					    		 <fo:table-row>
					    		 		<fo:table-cell><fo:block> &#160; </fo:block></fo:table-cell>
					    		 		<fo:table-cell><fo:block  font-size="10px" text-align="left" start-indent="2cm" >12:01 A.M., Standard Time</fo:block></fo:table-cell>
					    		 </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" start-indent="1cm" >Agent Name: <xsl:value-of select="response/UserDetails_freeform_1/data/AgentName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent="2cm" >Agent No. <xsl:value-of select="response/UserDetails_freeform_1/data/LicenseNumber" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    
   				<fo:block space-after=".1in" text-align-last="justify">
   					 <fo:leader leader-pattern="rule" />
  				 </fo:block>
				   
				      <fo:table  text-align="center">
					    <fo:table-column column-width = "110mm" />
				        <fo:table-column column-width = "110mm" />
				        
				       
				       
				        	<fo:table-body>	
				        				<fo:table-row >
								              	<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent= "1cm">TAX JURISDICTION </fo:block></fo:table-cell>
								             	<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent= "2cm">TAX AMOUNT </fo:block></fo:table-cell>
								    	</fo:table-row>	
								    	<fo:table-row >
								              	<fo:table-cell padding-left="4pt" padding-top="-1pt" number-columns-spanned="2"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent= "1cm">__________________________________________________________________________ </fo:block></fo:table-cell>
								       </fo:table-row>	
					    	
				        			  <fo:table-row >
					               		 <fo:table-cell padding-left="4pt" padding-top="4pt">
					               			<fo:block  font-size="10px"  text-align="left" start-indent= "1cm">
					               				<xsl:if test="response/policycoverage_freeform_01/data/MTTaxAmmount != '0'">
								             		Municipal Tax 
								             	</xsl:if>								             	
					               			</fo:block>
					               		</fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			<fo:block font-size="10px"  text-align="left" start-indent= "2cm">
					               				 <xsl:if test="response/policycoverage_freeform_01/data/MTTaxAmmount != '0'">
								             		$<xsl:value-of select="response/policycoverage_freeform_01/data/MTTaxAmmount" />
								             	</xsl:if>
								             	
					               			</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		   
					    		  <fo:table-row >
					               		<fo:table-cell padding-left="4pt" padding-top="4pt">
					               			<fo:block font-size="10px"  text-align="left" start-indent= "1cm">
					               				<xsl:if test="response/policycoverage_freeform_01/data/CountyTaxAmmount != '0'">
								             		County Tax
								             	</xsl:if>
					               			</fo:block>
					               		</fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			<fo:block  font-size="10px"  text-align="left" start-indent= "2cm">
					               				
								             	<xsl:if test="response/policycoverage_freeform_01/data/CountyTaxAmmount != '0'">
								             		$0.00<!--  <xsl:value-of select="response/policycoverage_freeform_01/data/CountyTaxAmmount" />-->
								             	</xsl:if>
					               			</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>	
					    		
					    		  
					    		 
					    		  				    		  
					    	</fo:table-body>
					      </fo:table>
					    
					  
					    		  
					    	   		  
				       <fo:block margin-top="200mm"/>
			 
			  </fo:block>
			<fo:block  font-size="10px"  font-weight="bold" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/KYTaxForm1208" /></fo:block> 
     </xsl:template>
</xsl:stylesheet>
