<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-State-NJold" >
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
				    <fo:block margin-top="4mm"/>
				    
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLNJAmendatory0313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>

						
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    
   <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				   
				      <fo:block margin-top="4mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px" >NEW JERSEY AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="4mm"/>
				    
				
				     <fo:block text-align="left" font-size="10px" >1. Section III., EXTENSIONS OF COVERAGE, Paragraph D. Extended Reporting Period Extensions, is amended by deletion
     						of the words “… other than for cancellation for non payment of premium or for non payment of Deductible due hereunder…”
    						 where they appear in the first sentence of sub paragraphs 1. and 2.
					</fo:block>
				     <fo:block margin-top="4mm"/>
				     
				     <fo:block text-align="left" font-size="10px" >2. Section III., EXTENSIONS OF COVERAGE,  paragraph 2., under the paragraph beginning “ As a condition precedent to the right…”,</fo:block>

					 <fo:block margin-top="2mm"/>
				 	<fo:block text-align="left" font-size="10px" start-indent="2cm">-  letter (a) is deleted in its entirety.</fo:block>
				    <fo:block margin-top="2mm"/>                     
                       
                    <fo:block text-align="left" font-size="10px" start-indent="2cm">-  letter (c) is deleted and replaced with the following:</fo:block>
                        
                    <fo:block margin-top="2mm"/>  
                    <fo:block text-align="left" font-size="10px" start-indent="3cm">(c) if the <fo:inline font-weight="bold" font-style="italic">Named Insured’s</fo:inline> right to practice law has been revoked, suspended or surrendered at
                                                        the request of any regulatory authority for reasons other than death, disability or retirement 
                                                        this Optional Extended Reporting Period shall be applicable solely to <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> 
                                                        rendered prior to such revocation, suspension or surrender, and
					</fo:block>
			        <fo:block margin-top="4mm"/>
			        <fo:block text-align="left" font-size="10px">3. Section III., EXTENSIONS OF COVERAGE, Paragraph D. Extended Reporting Period Extensions, is amended by deletion of  (i) in paragraph (a) of sub section 3</fo:block>
					<fo:block margin-top="4mm"/>
					<fo:block text-align="left" font-size="10px">4. Section VI. GENERAL CONDITIONS, Paragraph D, Cancellation and Nonrenewal is amended as follows:	- sub paragraph 2: the third sentence is deleted and replaced with the following sentence. “ The mailing of such notice  shall be by certified mail or by proof of mailing.”	- sub paragraph 3:  the last sentence is deleted and replaced with the following sentence. “ The mailing of such notice  shall be by certified mail or by proof of mailing.”</fo:block>			
                 	<fo:block margin-top="4mm"/>
			 		<fo:block text-align="left" font-size="10px">5. Item 4. of the Declarations, Deductible, is amended by the deletion of the following sentence; “ This amount applies to <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>,  unless this Section is amended by specific endorsement of this policy.”</fo:block>
					<fo:block margin-top="4mm"/>		
					<fo:block text-align="left" font-size="10px">6. Section II., LIMITS OF LIABILITY AND DEDUCTIBLE, is amended by deletion of B. Deductible and replacing it with the following:</fo:block>
					<fo:block text-align="left" font-size="10px" start-indent="2cm">  B. Deductible</fo:block>
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="2.5cm">The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> which are in excess of the amount shown in Item 4. of the Declarations. The Deductible amount shall apply separately to each and every  <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> and shall be borne by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and remain uninsured. The Deductible amount applies to the payment of
                     <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> but, not to the payment of <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>. If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> advances any amounts within the
                      Deductible, the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> within thirty days of the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> request to do so. In 
                      the event of <fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline>, a single Deductible amount shall apply.
				</fo:block>
                <fo:block margin-top="4mm"/>
                <fo:block text-align="left" font-size="10px">7. If:</fo:block> 				
 				 
 				<fo:block text-align="left" font-size="10px" start-indent="2cm"> (a) the Limit of Liability shown in Item 3 of the Declarations as to “each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>” is $1,000,000 or higher, and</fo:block>
                <fo:block margin-top="2mm"/> 
                
				<fo:block text-align="left" font-size="10px" start-indent="2cm">(b) the policy is not endorsed so that <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> are paid in addition to the Limit of Liability,	then:</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block text-align="left" font-size="10px" start-indent="2.5cm">(a) <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> shall not begin to erode or to reduce the Limit of Liability to pay <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> for “each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>” 
                      until  <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> incurred for “each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>” total 50% of the Limit of Liability for  “each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>”;
				</fo:block>
				
				<fo:block margin-top="2mm"/>
				<fo:block text-align="left" font-size="10px"  start-indent="2.5cm">(b)  the portion of the Limit of Liability that remains available to pay <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> may be reduced only by the portion of 
                      incurred <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> greater than 50% of the policy Limit of Liability;
				</fo:block>

				<fo:block margin-top="2mm"/> 
				
				<fo:block text-align="left" font-size="10px" start-indent="2.5cm">(c)  the portion of the Limit of Liability available to pay <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> shall not be reduced to an amount less than 50% of the
                      policy <fo:inline font-weight="bold" font-style="italic">Limit of Liability</fo:inline>, regardless of the amount of Defense Expenses incurred.
				</fo:block>
				
				<!--  
				<fo:block text-align="center" font-size="10px">The North River Insurance Company of</fo:block>
				<fo:block text-align="center" font-size="10px">Crum &amp; Forster</fo:block>
				<fo:block text-align="center" font-size="10px">305 Madison Avenue</fo:block>
				<fo:block text-align="center" font-size="10px">Morristown, NJ 07962-1973</fo:block>
				<fo:block text-align="center" font-size="10px">Telephone: 973 490 6600</fo:block>

				<fo:block margin-top="4mm"/> 

			<fo:block text-align="left" font-size="10px">V.	Missouri requirements provide that a Date must be entered in Item 6. of the Declarations, Prior Acts Date. If the word “NONE” appears in Item 6. of the Declarations, however, it means that the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> is providing full, unknown prior acts coverage.</fo:block>
			-->
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

				      
				       <fo:block margin-top="2mm"/>
				      <fo:block text-align="center" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				      <fo:block margin-top="2mm"/>
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
			  <fo:block margin-top="3mm"/>			 
			  <fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLNJAmendatory0313"/></fo:block>
		  <!--  
		    </fo:flow>
		  </fo:page-sequence>
		</fo:root>	  
			-->
     </xsl:template>
</xsl:stylesheet>
