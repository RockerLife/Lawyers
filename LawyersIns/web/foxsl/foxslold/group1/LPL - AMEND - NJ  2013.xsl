<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="LPL-State-NJ">
		
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLNJAmendatory0313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">NEW JERSEY AMENDATORY ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="5mm"/>
				    
				     <fo:block text-align="left"  font-size="10px">1. Section III. EXTENSIONS OF COVERAGE, Paragraph D. Extended Reporting Period Extensions, is amended by deletion of the words "… other than for cancellation for nonpayment of premium or for nonpayment of Deductible due hereunder…" where they appear in the first sentence of sub paragraphs 1. and 2.</fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">2. Section III. EXTENSIONS OF COVERAGE,  paragraph 2., under the paragraph beginning " As a condition precedent to</fo:block>
				     <fo:block text-align="left" font-size="10px">the right…",	              -  letter (a) is deleted in its entirety.</fo:block>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">-  letter (c) is deleted and replaced with the following:</fo:block>
			    
			   		 <fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="4cm"> (c) if the <fo:inline font-style="italic" font-weight="bold">Named Insured's</fo:inline> right to practice law has been revoked, suspended or surrendered at the request of any regulatory authority for reasons other than death, disability or retirement this Optional Extended Reporting Period shall be applicable solely to Professional Services rendered prior to such revocation, suspension or surrender, and</fo:block>
			    
			    	 <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">3. Section III. EXTENSIONS OF COVERAGE, Paragraph D. Extended Reporting Period Extensions, is amended by deletion of  (i) in paragraph (a) of sub section 3.</fo:block>
			    
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">4. Section VI. GENERAL CONDITIONS, Paragraph D. Cancellation and Nonrenewal is amended as follows:</fo:block>
				     <fo:block text-align="left" font-size="10px"  start-indent="2cm">- sub paragraph 2: the third sentence is deleted and replaced with the following sentence. "The mailing of such notice shall be by certified mail or by proof of mailing."</fo:block>
				     <fo:block text-align="left" font-size="10px"  start-indent="2cm">- sub paragraph 3:  the last sentence is deleted and replaced with the following sentence. "The mailing of such notice shall be by certified mail or by proof of mailing."</fo:block>
			    
			    	 <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" >5. Item 4. of the Declarations, Deductible, is amended by the deletion of the following sentence;</fo:block>
			    		<fo:block text-align="left" font-size="10px" start-indent="2cm" >"This amount applies to <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline>, unless this Section is amended by specific endorsement of this policy."</fo:block>
			    	
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">6. Section II. of the Policy, Limits of Liability and Deductible, paragraph B. Deductible, is deleted and replaced with the following:</fo:block>
			    	
			    	
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="3cm">B.   Deductible</fo:block>
			    	
			    	<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="3cm">The <fo:inline font-style="italic" font-weight="bold">Insurer</fo:inline> shall be liable for amounts payable under this policy for <fo:inline font-style="italic" font-weight="bold">Damages or Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4 of the Declarations. The Deductible amount shall apply separately to each and every <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>, unless amended by specific endorsement of this policy, and shall be borne by the <fo:inline font-style="italic" font-weight="bold">Insured</fo:inline> and shall remain uninsured. The Deductible amount shall not apply to <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline>. In the event of <fo:inline font-style="italic" font-weight="bold">Related Claims</fo:inline> that are deemed a single <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline> pursuant to Section II C. below, a single Deductible amount will apply.</fo:block>
			    			    	
			    	<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px">7.  Section VI. General Conditions, Paragraph B. Defense and Settlement, sub-paragraph 1. is amended by deletion of this the third sentence:   </fo:block>
				     <fo:block text-align="left" font-size="10px" start-indent="3cm">"The <fo:inline font-style="italic" font-weight="bold">Insureds</fo:inline> shall pay any <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> within the applicable Deductible shown in Item 4 of the Declarations.”</fo:block>
			    	
			    		<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">8. If: 	(a) the Limit of Liability shown in Item 3 of the Declarations as to "each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>" is $1,000,000 or higher, and</fo:block>
			    	
			    	<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">(b) the policy is not endorsed so that <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> are paid in addition to the Limit of Liability:</fo:block>
			    	
			    		
			    		<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px">then:</fo:block>
						
							<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">(a) <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> shall not begin to erode or to reduce the Limit of Liability to pay <fo:inline font-style="italic" font-weight="bold">Damages</fo:inline> for "each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>" until  <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> incurred for "each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>" total 50% of the Limit of Liability for  "each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>";</fo:block>
		
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">(b)  the portion of the Limit of Liability that remains available to pay <fo:inline font-style="italic" font-weight="bold">Claims</fo:inline> may be reduced only by the portion of incurred <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> greater than 50% of the policy Limit of Liability;</fo:block>
						
					<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">(c)  the portion of the Limit of Liability available to pay <fo:inline font-style="italic" font-weight="bold">Claims</fo:inline> shall not be reduced to an amount less than 50% of the policy Limit of Liability, regardless of the amount of <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> incurred.  </fo:block>
		
			    
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" start-indent="4cm" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
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
	         	<fo:block margin-top="1mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLNJAmendatory0313"/></fo:block>
				    
     </xsl:template>
</xsl:stylesheet>




					
				    	