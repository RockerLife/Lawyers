<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="SecurityIncident">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
				    <!-- <fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1270313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule" />
  					 </fo:block> -->
  					 
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">SECURITY INCIDENT AND IDENTIFICATION THEFT EXTENSION ENDORSEMENT</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     <fo:block text-align="left"  font-size="10px"  >In consideration of the premium charged, it is agreed that Section III. Extension of Coverage is amended by addition of the following:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" ><fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.4em" content-width="0.4em"/>&#160;<fo:inline font-weight="bold" >Security Incident Extension</fo:inline></fo:block>
				      <fo:block margin-top="2mm"/>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm">The <fo:inline font-weight="bold" >Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold" >Named Insured</fo:inline>, for each <fo:inline font-weight="bold" >Security Incident</fo:inline> first occurring and reported in writing to </fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm">the <fo:inline font-weight="bold" >Insurer</fo:inline> during the <fo:inline font-weight="bold" >Policy Period</fo:inline> pursuant to Section VI., General Condition A., Notice of <fo:inline font-weight="bold" >Claim</fo:inline> or Circumstance, for </fo:block>
                     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm"> expenses incurred to:</fo:block>

				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm">1. hire cyber forensic analysts to determine the extent of an actual security breach that has occurred; or </fo:block>
				     <fo:block margin-top="1mm"/>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm">2. provide notification and credit monitoring services to individuals when the security, confidentiality, or integrity of their</fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.5cm">personal information has been compromised, as required by state or local privacy laws;</fo:block>
				   
				     <fo:block text-align="justify" font-size="10px" text-indent="0.5cm">up to a maximum reimbursable amount of $25,000.  This is the maximum amount the <fo:inline font-weight="bold" >Insurer</fo:inline> will reimburse, regardless</fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.5cm">of the number of</fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.5cm" >(a)&#160;such <fo:inline font-weight="bold" >Security Incidents;</fo:inline> or </fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.5cm">(b)&#160;of the number of <fo:inline font-weight="bold" >Insureds</fo:inline> involved in such <fo:inline font-weight="bold" >Security Incidents</fo:inline>,</fo:block>
				     
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm" > for all<fo:inline font-weight="bold" > Security Incidents</fo:inline> reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> during the <fo:inline font-weight="bold" >Policy Period</fo:inline>. Any payments made regarding such</fo:block>
				     
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm" ><fo:inline font-weight="bold" >Security Incidents</fo:inline> will be in addition to the Limit of Liability and are not subject to the Deductible.</fo:block>
				     
				     <fo:block margin-top="5mm"/>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm">Any notice the <fo:inline font-weight="bold" >Insured</fo:inline> gives the <fo:inline font-weight="bold" >Insurer</fo:inline> of such <fo:inline font-weight="bold" >Security Incident</fo:inline> shall be deemed notification of a potential <fo:inline font-weight="bold" >Claim</fo:inline> </fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm">under Section VI., General Condition A., Notice of Claim or Circumstance.</fo:block>
				     
				      <fo:block margin-top="5mm"/>
				     
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm"><fo:inline font-weight="bold" >Security Incident</fo:inline> means the unauthorized access, destruction, use, modification or disclosure of confidential or proprietary</fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm"> data or of personally identifiable information or data containing private or confidential information in connection with the</fo:block>
				     <fo:block text-align="justify" font-size="10px" text-indent="0.2cm">performance of <fo:inline font-weight="bold" >Professional Services</fo:inline>.</fo:block>   
				     <fo:block margin-top="20mm"/>
				     <fo:block text-align="justify" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="15mm"/>
				     <!-- 
				     <fo:block>
				       <fo:table  text-align="center">
					    <fo:table-column column-width = "80mm" />
				        <fo:table-column column-width = "80mm" />
				       
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
				</fo:block>		 -->		       
	         	<fo:block margin-top="6.5cm"/>
	         	<!--<fo:block  font-size="10px" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1270417"/></fo:block>
				--><fo:block font-size="9px" color="grey" text-align="left">
				     
				      <xsl:choose>
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM2'">
						     		 LPL-127 (04/19)&#160; &#160; &#160; &#160; &#160; &#160; &#160;
						     	</xsl:when> 
						     	<xsl:otherwise>
		                         	 LPL-127 (04/17)&#160; &#160; &#160; &#160; &#160; &#160; &#160;
		                      </xsl:otherwise>
		                    </xsl:choose>
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
						&#160;&#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
						&#160;&#160;&#160; &#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;
						&#160;&#160; &#160;&#160; &#160; &#160; &#160; &#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160; &#160; Page 1 of 1
				  </fo:block>
				  
     </xsl:template>
</xsl:stylesheet>




					
				    	