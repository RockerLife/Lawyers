<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-AR" >
	
				    <fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLAMENDAR0910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    
   <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				   
				      <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">ARKANSAS AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="10mm"/>
				    
				    
				   
				
				    <fo:block text-align="left" font-size="10px" >1)  Section II, Limits of Liability and Deductible, Paragraph A, Limits of Liability, is amended as follows:</fo:block>
					
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">- the phrase “… including any applicable Extended Reporting Period…” is deleted where it appears in 1 and 2.</fo:block>
				 	
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">- 6. is deleted and replaced with the following 6.:</fo:block>
				 	<fo:block margin-top="4mm"/>
				 	
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">6. The Limit of Liability available for <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> first made against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during any applicable Extended Reporting Period shall be equal to the higher amount of either (a) the remaining Limit of Liability on the policy at the effective date of the applicable Extended Reporting Period or (b) 50% of the <fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline>.</fo:block>
				 	
				 	<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" font-size="10px">2) Section III., Extensions, D.,  Extended Reporting Period Extensions, is amended as follows:</fo:block>
				 	
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">- the phrase “…  other than for cancellation for nonpayment of premium of for nonpayment of Deductible due hereunder…” is deleted from the first sentence in 1.</fo:block>
					
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">- the phrase “… for reasons other than the nonpayment of premium or for the nonpayment of Deductible due hereunder… “ is deleted where it appears in the first sentence of 2.</fo:block>
					
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">- the first sentence of the second paragraph in 2. is deleted in its entirety.</fo:block>
					
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">- the phrase “ … (a) has not been cancelled for nonpayment of premium or Deductible…” is deleted from the first sentence in 3.</fo:block>
												
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" font-size="10px">3)  Section VI., General Conditions, D. Cancellation and Nonrenewal, is amended as follows:</fo:block>
				 	
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">-  the first sentence in paragraph #2 is deleted and replaced with the following first sentence:</fo:block>
					
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">The Insurer may only cancel this policy for nonpayment of premium.</fo:block>
					
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">- paragraph 3 is deleted in its entirety and replaced with the following paragraph 3.:</fo:block>
					
					<fo:block margin-top="3mm"/>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">3. This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known address written notice of such nonrenewal at least sixty days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. Or prior to the anniversary date if this policy is written for a term of more than one year and with no fixed expiration date. The proof of mailing of such notice shall be sufficient proof of notice. Said notice is not required if non renewal is due to the failure of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> to pay the premium due for the renewal. The notice will advise the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> and its Agent of the availability, premium charge and importance of  purchasing the Optional Extended Reporting Period.</fo:block>
					
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left"  font-size="10px">4)  Section VI., General Conditions, paragraph F.. Subrogation, is amended by deletion of the last sentence and replacing it with the following last sentence:</fo:block>
					
				 	<fo:block text-align="left" start-indent="1cm"  font-size="10px">Any recovery shall first go to the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, to the extent the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> is  fully compensated for the <fo:inline font-weight="bold" font-style="italic">Loss</fo:inline> sustained, and any remainder shall inure to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.</fo:block>
					
					
				     
				    <fo:block margin-top="20mm"/>
				    
				      <fo:block text-align="center" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				      <fo:block margin-top="15mm"/>
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
				</fo:block>
			  <fo:block margin-top="10mm"/>
			  <fo:block  font-size="10px"  start-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDAR0910" /></fo:block>
		  
		   
			
     </xsl:template>
</xsl:stylesheet>
