<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-KS" >
	
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLAMENDKS0910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    
   <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				   
				      <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">KANSAS AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="10mm"/>
				
				    <fo:block text-align="left" font-size="10px">1) 	This policy may be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> only for these reasons:</fo:block>
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">(a)  	for nonpayment of the premium or of the deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>;</fo:block>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">(b) 	if a determination by the Commissioner of the Kansas Department of Insurance that continuation of coverage could place the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> in a hazardous financial condition or in violation of the laws of the state of Kansas; or</fo:block>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">(c)  	a determination by the Commissioner of the Kansas Department of Insurance that the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> no longer has adequate reinsurance to meet the <fo:inline font-weight="bold" font-style="italic">Insurer's</fo:inline> needs</fo:block>
				 	
				 	<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">However, VI., General Conditions, D. Cancellation and Nonrenewal, paragraph #3 is deleted and replaced with the following:</fo:block>
				 	<fo:block margin-top="4mm"/>				 	
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">3. 	This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, at its last known address,  written notice of  the <fo:inline font-weight="bold" font-style="italic">Insurer's</fo:inline> intent not to renew the policy at least sixty days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.  Said notice must state the specific reasons why the policy is being non-renewed. The proof of mailing of such notice shall be sufficient proof of notice. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may satisfy this obligation by causing such notice to be given by a licensed agent.</fo:block>
				 	
				 	<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" font-size="10px">2) 	Section VI., General Conditions, N.,  Authorization and Sole Agent, is deleted and replaced with the following:</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">N. 	<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> as Sole Agent</fo:block>
					
				 	<fo:block margin-top="2mm"/>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">The <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> will be the sole agent and will be authorized to act on behalf of all <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> for the purpose of giving or receiving any notices, any amendments to or cancellation of this policy, for the completing of any applications and the making of any statements or representations for the policy, for the payment of the deductible and the exercising or declining to exercise any right under this policy, including the purchase of any Extended Reporting Period.</fo:block>
					
				 	 <!--
				 	 
				 	<fo:block margin-top="4mm"/>				 	
				 	<fo:block text-align="left" font-size="10px">2. 	Section III., Extensions of Coverage, Paragraph D Extended Reporting Extensions, paragraph 1 is amended by deletion of the first sentence and the replacement of the  sentence with the following:</fo:block>
				 	<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">Upon the expiration, cancellation by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> or, if upon renewal the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> decreases the Limit of Liability, or increases the Deductible, or adds an exclusion,  advances the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline>, or otherwise offers the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> less favorable coverage, the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall be provided with an automatic and non-cancelable period of 30 days,  commencing upon the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>, or if the policy is cancelled  prior to the expiration date, then commencing on the cancellation date,  to report <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to Section VI. A. of General Condition, Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance.</fo:block>
	
				 
				 	<fo:block margin-top="4mm"/>				 	
				 	<fo:block text-align="left" font-size="10px">3. 	Section III., Extensions of Coverage, D. Extended Reporting Extensions, paragraph 2. is amended:</fo:block>
				 	
				 	<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent=".5cm" font-size="10px">(A) 	by deletion of  the first paragraph and deletion of the options (a), (b), (c) and (d) which follow the first paragraph, and is replaced with the following: </fo:block>
				 	
				 	<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent="3cm" font-size="10px">Upon the expiration, cancellation by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> or, if upon renewal the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> decreases the Limit of Liability, or increases the Deductible, or adds an exclusion,  advances the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline>, or otherwise offers the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> less favorable coverage,  the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall have the right upon payment of an additional premium of 200% of the total policy premium to purchase an unlimited period of time, commencing on the expiration date of the Automatic Extended Reporting Period, to report <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> pursuant to Section VI. General Conditions, A. Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance.</fo:block>
					
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent=".5cm" font-size="10px">(B) 	by deleting the phrase “… in full within sixty days…”  where it appears in (d) of the penultimate paragraph and replacing  such phrase with  “… in full within 30 days…” </fo:block>
					
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" font-size="10px">4.  	Section VI. General Conditions, D., Cancellation and Nonrenewal, paragraph 2 is amended by deletion of the first two sentences and replacement with the following two sentences:</fo:block>
					
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">This policy may not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except for non payment of premium or deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.  Such cancellation will be advised by delivering to, or mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known address, written notice of such cancellation at least 10 days prior to the cancellation date and shall contain (a) a written statement specifying the reason for such cancellation (b) advice of the Automatic Extended Reporting Period (c) the options for and premium charges for any Optional Extended Reporting Periods and (d) the importance to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> of purchasing such Extended Reporting .</fo:block>
					 <fo:block margin-top="10mm"/>
			 		 <fo:block  font-size="10px"  start-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/APLAMENDDC2009" />LPL - AMEND - WI (09/10)</fo:block>
		  
					<fo:block break-after="page" />
												
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" font-size="10px">5. 	Section VI., General Conditions, D. Cancellation and Nonrenewal, paragraph 3 is deleted and replaced with the following paragraph:</fo:block>
				 		<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">3. 	This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known address,  written notice of the intent to non-renew at least ninety days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. Such notice of intent not to renew shall contain (a) a written statement specifying the reason for such non renewal (b) advice of the Automatic Extended Reporting Period (c) the options for and premium charges for any Optional Extended Reporting Periods and (d) the importance to  the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> of purchasing such Extended Reporting Period. The proof of mailing such notice shall be sufficient proof of notice.</fo:block>
					 -->
				     
				    <fo:block margin-top="60mm"/>
				    
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
			  <fo:block  font-size="10px"  start-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDKS0910" /></fo:block>
		  
     </xsl:template>
</xsl:stylesheet>
