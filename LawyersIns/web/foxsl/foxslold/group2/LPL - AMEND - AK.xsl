<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-AK" >
	
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLAMENDAK0910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    
   <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				   
				      <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">ALASKA AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="10mm"/>
				    
				    
				   
				
				    <fo:block text-align="left" font-size="10px" >1.	It is agreed that Section III, Extensions of Coverage, D. Extended Reporting Period Extensions, Paragraph 2., Optional Extended Reporting Period, is amended by replacing the Options as they appear in a) through d) with the following:</fo:block>
					<fo:block margin-top="2mm"/>
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">(a) 	Optional Extended Reporting Period of 12 months for a premium charge of 100% of the annual policy premium;</fo:block>
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">(b) 	Optional Extended Reporting Period of 36 months for a premium charge of 175% of the annual policy premium;</fo:block>
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">(c)  	Optional Extended Reporting Period of 60 months for a premium charge of 185% of the annual policy premium;</fo:block>
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">(d)  	Optional Extended Reporting Period of 72 months for a premium charge of 200% of the annual policy premium.</fo:block>
					
					<fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px">2. 	Section III., Extensions, D. Extended Reporting Periods, is amended as follows:</fo:block>
					
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">- 	The phrase "… or for nonpayment of Deductible due hereunder…" is deleted from the first sentence of Paragraphs 1. and 2.</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">-  	The phrase "… or Deductible …" where it appears in (i)  in the first sentence of 3. (a) is deleted;</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">- 	The last sentence in 1. is deleted and replaced with the following sentences:</fo:block>
						
					<fo:block margin-top="2mm"/>					
				 	<fo:block text-align="left" font-size="10px" start-indent="2cm">This Automatic Extended Reporting Period shall not be applicable, however, in the event the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> has obtained another policy of insurance of Lawyers Professional Liability insurance (hereinafter referred to as " the subsequent policy")  with an inception date as of the termination date of this policy and which includes the same <fo:inline font-weight="bold" font-style="italic">Prior Act Date</fo:inline> shown in Item 6. of the Declaration Page of this policy. If such subsequent policy incorporates a <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> later than as that shown in Item 6. of the Declarations, then this Automatic Extended Reporting Period shall only apply to <fo:inline font-weight="bold" font-style="italic">Wrongful Acts</fo:inline> committed between the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> shown in Item 6 of this policy's Declarations and the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> of the subsequent policy and which are not otherwise excluded under other term, conditions and exclusions of this policy.</fo:block>
				    
				    <fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm"><fo:inline text-decoration="underline">Paragraph 3</fo:inline > (a)  is amended as follows:</fo:block>
					
					<fo:block margin-top="3mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">-	 Where the number "…275%.." appears within the parentheses, such number is deleted and replaced with "…200%..."</fo:block>
					
				    <fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px">3. 	Section VI., General Conditions, B., Defense and Settlement, paragraph 1, is amended by deletion in its entirety of the last sentence of the paragraph.</fo:block>
					
					<fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px">4. 	Section VI, General Conditions. D., Cancellation and Nonrenewal, is amended as follows:</fo:block>
					
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">-	paragraph 1 is amended by deleting the phrase  " … shall return 90 percent … " where it appears in the second sentence and replacing it  with the phrase "… shall return 92.5 percent…".</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">- 	paragraph 2 is amended by:</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">- 	deletion of the phrase "… or Deductible…" in the first sentence.</fo:block>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">- 	deletion of the phrase "… delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or…" where it appears in the second sentence.</fo:block>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">- 	deletion of the phrase "…at least 10 days before…" where it appears in the second sentence and replacing it with the phrase  "… at least 20 days before…".</fo:block>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">- 	deletion of the last two sentences and replacing them with the following sentence:  " If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels this policy for non payment of premium,  the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall credit the pro rata portion of the unearned premium  within 45 days after notice of such cancelation was given to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>."</fo:block>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">- 	paragraph 3 is amended by deletion of the phrase "… by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or…".</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">Further, wherever the phrase "…by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>…" appears in paragraphs 2 and 3, such phrase shall be deleted and replaced with the phrase "… by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> by first class mail…"</fo:block>
					
					
					<fo:block margin-top="5mm"/>
			  		<fo:block  font-size="10px"  start-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDAK0910" /></fo:block>
		  
					<fo:block break-after="page"></fo:block>
					
					<fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
					 <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule" />
   						</fo:block>
					
					<fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px">5. 	Section VI., General Conditions, H. Other Insurance, is deleted and replaced with the following:</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">H. 	All <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> payable under this policy shall contribute with any other insurance applicable to any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>, except with respect to other insurance written specifically to be excess of this policy. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall contribute <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> in the proportion equal to the percent its Limit of Liability bears to the total Limit of Liability of this policy plus such other insurance. This policy shall not be subject to the terms or conditions of such other insurance. </fo:block>
					
					<fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px">6. 	Section VI., General Conditions, item C. Action Against <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> is amended by the addition of the following sentence at the end of paragraph 1.:</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">Commencing upon the date a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is denied by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>  has three years to bring an action against the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.</fo:block>
					
					<fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px">7. 	<fo:inline font-weight="bold">NOTICE REGARDING ATTORNEY FEES:</fo:inline></fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">In any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> that the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> defends in Alaska, its obligation to pay all costs taxed against any <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> in any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is amended by the following:</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will pay that portion of the attorney fees awarded as costs which do not exceed the amount allowed for a contested case in the schedule of attorney’s fees contained in Alaska Civil Rule 82 for a judgment equal to the applicable Limit of Liability.</fo:block>
												
					
					
					
				     
				    <fo:block margin-top="70mm"/>
				    
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
			  <fo:block margin-top="40mm"/>
			  <fo:block  font-size="10px"  start-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDAK0910" /></fo:block>
		  
     </xsl:template>
</xsl:stylesheet>
