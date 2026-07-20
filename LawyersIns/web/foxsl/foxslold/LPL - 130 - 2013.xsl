<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="coverageendorsement">
		
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1300813" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">AMEND SECTION III. EXTENSIONS OF COVERAGE ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="5mm"/>
				    
				     <fo:block text-align="left"  font-size="10px">In consideration of premium charged, it is understood and agreed that <fo:inline font-weight="bold">Section III. Extensions of Coverage, G. is deleted and replaced with the following:</fo:inline></fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" font-weight="bold" start-indent="2cm">G.	Regulatory Inquiry Extension</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">If a regulatory body, state licensing board, public oversight board or government agency having regulatory authority over the <fo:inline font-weight="bold" font-style="italic">Insured's Professional Services</fo:inline>, first initiates an investigation of any <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> which arises from <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> rendered subsequent the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> and, such regulatory inquiry is reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to Section VI. General Conditions, A., Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> for attorney fees, court and regulatory body costs incurred in responding to such inquiry, up to a maximum reimbursement of $25,000. This is the maximum amount the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse, regardless of the number of such inquiries or of the number of <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> involved in such inquiries, for all inquiries first initiated against the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> during the policy and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to Section VI. General Conditions, A. Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance.</fo:block>
				     
				     
			   		 <fo:block margin-top="8mm"/>
				     <fo:block text-align="left" font-size="10px" >It is further agreed that no Deductible shall apply to <fo:inline font-weight="bold">Section III. Extensions of Coverage:</fo:inline></fo:block>
			    
			    	 <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" font-weight="bold" start-indent="2cm">F.	Defendant Reimbursement Extension</fo:block>
			    	 <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" font-weight="bold" start-indent="2cm">G.	Regulatory Inquiry Extension</fo:block>	
			    	 <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" font-weight="bold" start-indent="2cm">H.	Subpoena Assistance Extension</fo:block>	
			    	 <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" font-weight="bold" start-indent="2cm">I.	Client Discrimination Extension</fo:block>	
			    	 <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" font-weight="bold" start-indent="2cm">J. Disciplinary Proceedings</fo:block>	
			    	
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
	         	<fo:block margin-top="20mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1300813"/></fo:block>
				  
     </xsl:template>
</xsl:stylesheet>




					
				    	