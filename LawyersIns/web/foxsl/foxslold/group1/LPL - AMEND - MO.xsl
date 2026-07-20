<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-State-MO" >
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
				    <fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block >
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLAMENDMO0910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>

						
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    
   <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				   
				      <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px" >MISSOURI AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="10mm"/>
				    
				
				     <fo:block text-align="left" font-size="10px" >I. 	This policy may not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except for non payment of premium or deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.  However, VI., General Conditions, D. Cancellation and Nonrenewal, paragraph #3 is deleted and replaced with the following:</fo:block>
				     <fo:block margin-top="4mm"/>


				     <fo:block text-align="left" font-size="10px" start-indent="2cm">3. 	This policy may be non-renewed by the<fo:inline font-weight="bold" font-style="italic"> Insurer </fo:inline>by delivering to the<fo:inline font-weight="bold" font-style="italic"> Named Insured</fo:inline>, or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known address written notice of such non renewal, including the reason for non-renewal, at least  sixty days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The proof of mailing such notice shall be sufficient proof of notice.</fo:block>

					 <fo:block margin-top="6mm"/>
				 	<fo:block text-align="left" font-size="10px">II. 	Section IV., Definitions, definition of<fo:inline font-weight="bold" font-style="italic"> Damages</fo:inline>, is amended by deletion of (c) under the section beginning with “<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> shall not include…” and replacement with the following (c):</fo:block>
				    <fo:block margin-top="4mm"/>
				    
                         
                       
                    <fo:block text-align="left" font-size="10px" start-indent="2cm">(c) 	punitive or exemplary damages, awards or judgments or any amounts which are a multiple of compensatory, awards or judgments;</fo:block>
                        
                    <fo:block margin-top="6mm"/>  
                    <fo:block text-align="left" font-size="10px" >III. 	The following supersedes any other policy provisions to the contrary:</fo:block>
			        <fo:block margin-top="4mm"/>
			        <fo:block text-align="left" font-size="10px" start-indent="2cm"><fo:inline font-weight="bold">MISSOURI PROPERTY AND CASUALTY INSURANCE GUARANTY ASSOCIATION COVERAGE LIMITATIONS</fo:inline></fo:block>
					<fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">1. 	Subject to the provisions of the Missouri Property and Casualty Insurance Guaranty Association Act (to be referred to as the “Act”) if the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> is a member of the Missouri property and Casualty Insurance Guaranty Association (to be referred to as the “Association”) the Association will pay <fo:inline font-weight="bold" font-style="italic">Claims </fo:inline>covered under the Act if the <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline>becomes insolvent.</fo:block>			
                 	<fo:block margin-top="4mm"/>
			 		<fo:block text-align="left" font-size="10px" start-indent="2cm">2. 	The Act contains various exclusions, conditions and limitations that govern a claimant’s eligibility to collect payment from the Association and affect the amount of any payment. The following limitations apply subject to all other provisions of the Act:</fo:block>
					<fo:block margin-top="4mm"/>

			
				<fo:block text-align="left" font-size="10px" start-indent="2.5cm">(a) 	<fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> covered by the Association do not include a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> by or against an <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>of an insolvent <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> in the <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>has a net worth of more than $25 million on the later of the end of the <fo:inline font-weight="bold" font-style="italic">Insured’s </fo:inline>most recent fiscal year or the December 31st of the year next preceding the date the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> becomes insolvent; provided that an <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> net worth on such date shall be deemed to include the aggregate net worth of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and of all its affiliates as calculated on a consolidated basis.</fo:block>
				<fo:block text-align="left" font-size="10px" start-indent="2.5cm">(b) 	Payments made by the Association for covered <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> will include only that amount of each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> which is less than $300,000</fo:block>
				<fo:block margin-top="4mm"/>
				<fo:block text-align="left" font-size="10px" start-indent="2cm">However, the Association will not:</fo:block>
                <fo:block margin-top="4mm"/>
                <fo:block text-align="left" font-size="10px" start-indent="2cm">(1) 	pay an amount excess of the applicable limit of insurance of the policy from which the <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> arises; or,</fo:block> 				
 				 
 				<fo:block text-align="left" font-size="10px" start-indent="2cm"> (2) 	return to an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> any unearned premium in excess of $25,000.</fo:block>


                <fo:block margin-top="4mm"/> 
                
				<fo:block text-align="left" font-size="10px" start-indent="2cm">These limitations have no effect on the coverage the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> provides under this policy.</fo:block>
				<fo:block margin-top="15mm"/>
				<fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/APLAMENDAK2009" />LPL - AMEND – MO (09/10)</fo:block>
			    <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="right">Page 1 of 2</fo:block>
				<fo:block break-after="page"></fo:block>
				

				<fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				<fo:block margin-top="8mm"/>
				<fo:block text-align="left" font-size="10px"  >IV. 	This policy of insurance is underwritten by either United States Fire Insurance Company or The North River Insurance Company.  Both these insurance companies can be contacted at their principal place of business, at:</fo:block>

				<fo:block margin-top="8mm"/> 
				
				<fo:block text-align="center" font-size="10px">United States Fire Insurance Company of, and</fo:block>
				<fo:block text-align="center" font-size="10px">The North River Insurance Company of</fo:block>
				<fo:block text-align="center" font-size="10px">Crum &amp; Forster</fo:block>
				<fo:block text-align="center" font-size="10px">305 Madison Avenue</fo:block>
				<fo:block text-align="center" font-size="10px">Morristown, NJ 07962-1973</fo:block>
				<fo:block text-align="center" font-size="10px">Telephone: 973 490 6600</fo:block>

				<fo:block margin-top="4mm"/> 

			<fo:block text-align="left" font-size="10px">V.	Missouri requirements provide that a Date must be entered in Item 6. of the Declarations, Prior Acts Date. If the word “NONE” appears in Item 6. of the Declarations, however, it means that the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> is providing full, unknown prior acts coverage.</fo:block>
			
			<!--  
			<fo:block text-align="left" font-size="10px" start-indent="2.3cm">second sentence and replacing it  with the phrase “… shall return 92.5 percent…”.</fo:block>


			<fo:block text-align="left" font-size="10px" start-indent="2cm">- paragraph 2 is amended by:</fo:block>


			<fo:block text-align="left" font-size="10px" start-indent="3.5cm">- deletion of the phrase “… or Deductible…” in the first sentence.</fo:block>
			<fo:block text-align="left" font-size="10px" start-indent="3.5cm">- deletion of the phrase “… delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or…” where it appears in the second</fo:block>
			<fo:block text-align="left" font-size="10px" start-indent="3.9cm">sentence.</fo:block>
			
			<fo:block text-align="left" font-size="10px" start-indent="3.5cm">- deletion of the phrase “…at least 10 days before…” where it appears in the second sentence</fo:block>
			<fo:block text-align="left" font-size="10px" start-indent="3.9cm">and replacing it with the phrase  “… at least 20 days before…”.</fo:block>
			
			<fo:block margin-top="10mm"/>
			<fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/APLAMENDNC2009" />fgfg</fo:block>
									
			<fo:block break-after="page" /> 
			
			
			<fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
			<fo:block margin-top="10mm"/>
			
			
			
			
			
			
			<fo:block text-align="left" font-size="10px" start-indent="3.5cm">-deletion of the last two sentences and replacing them with the following sentence</fo:block>
			<fo:block text-align="left" font-size="10px" start-indent="4cm">“If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels this policy for non payment of premium,  the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall credit the pro rata 
                                         portion of the unearned premium  within 45 days after notice of such cancelation was given to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.”</fo:block>


			<fo:block text-align="left" font-size="10px" start-indent="2cm">- paragraph 3 is amended by deletion of the phrase “… by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or…”.</fo:block>
			
				<fo:block margin-top="6mm"/> 
			<fo:block text-align="left" font-size="10px" start-indent="2cm">Further, wherever the phrase “…by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>…” appears in paragraphs 2 and 3, such phrase 
              		  shall be deleted and replaced with the phrase “… by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> by first class mail…”</fo:block>
			<fo:block margin-top="10mm"/> 
			<fo:block text-align="left" font-size="10px" >6. Section VI., General Conditions, G. Other Insurance, is deleted and replaced with the following:</fo:block>

			<fo:block text-align="left" font-size="10px" start-indent="2cm">G. All <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> payable under this policy shall contribute with any other insurance</fo:block>
			<fo:block text-align="left" font-size="10px" start-indent="2.4cm">applicable to any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>,  except with respect to other insurance written specifically to be excess of this policy. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall contribute <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> in the proportion equal to the percent its Limit of Liability bears to
                   		 the total Limit of Liability of this policy plus such other insurance. This policy shall not be subject to the terms or conditions of such other insurance.</fo:block>

			<fo:block margin-top="12mm"/> 


			<fo:block text-align="left" font-size="10px">7.<fo:inline font-weight="bold" font-style="italic">NOTICE REGARDING ATTORNEY FEES:</fo:inline></fo:block>

			<fo:block margin-top="8mm"/> 
			<fo:block text-align="left" font-size="10px" start-indent="2cm">In any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> that the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> defends in Alaska, its obligation to pay all costs taxed against any Insured in any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is amended by the following:</fo:block>
			<fo:block margin-top="4mm"/> 
			<fo:block text-align="left" font-size="10px" start-indent="2cm">The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>  will pay that portion of the attorney fees awarded as costs which do not exceed the amount allowed for a 
  			      contested case in the schedule of attorney’s fees contained in Alaska Civil Rule 82 for a judgment equal to the applicable Limit of Liability.</fo:block>

               -->

				       <fo:block margin-top="110mm"/>
				    
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
			  <fo:block margin-top="4cm"/>
			  <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDMO0910" /></fo:block>
			  <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="right">Page 2 of 2</fo:block>
		  <!--  
		    </fo:flow>
		  </fo:page-sequence>
		</fo:root>	  
		-->	
     </xsl:template>
</xsl:stylesheet>
