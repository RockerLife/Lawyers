<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-State-IL" >
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLAMENDIL0910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>

						
					    		 
					     </fo:table-body>
				       </fo:table>	   
				      </fo:block>
				    
   					 <fo:block space-after=".1in" text-align-last="justify">
   						<fo:leader leader-pattern="rule" />
   					 </fo:block>				   
				     <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px" >ILLINOIS AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="10mm"/>			    
				
				     <fo:block text-align="left" font-size="10px" >1.  Section <fo:inline font-weight="bold">III. – Extensions Of Coverage,  D. – Extended Reporting Periods Extensions</fo:inline>, Paragraphs <fo:inline font-weight="bold">1</fo:inline>.  and <fo:inline font-weight="bold">2</fo:inline>. are amended as follows:</fo:block>
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">-	Paragraph<fo:inline font-weight="bold"> 1. – Automatic Extended Reporting Period</fo:inline> is amended by deletion of the following words in the first sentence:</fo:block>
					 <fo:block margin-top="8mm"/>
				 	 <fo:block text-align="left" font-size="10px" start-indent="2cm"> “… other than for cancellation for nonpayment of premium or for nonpayment of Deductible due hereunder… “</fo:block>
				     <fo:block margin-top="10mm"/>
				    
                         
                       
                     <fo:block text-align="left" font-size="10px" start-indent="2cm">-	Paragraph <fo:inline font-weight="bold">2. – Optional Extended Reporting Period</fo:inline> is deleted in its entirety and replaced by the following paragraph:</fo:block>
                        
                 	 <fo:block margin-top="10mm"/>  
                     <fo:block text-align="left" font-size="10px" start-indent="2cm"><fo:inline font-weight="bold">2.	Optional Extended Reporting Period</fo:inline></fo:block>
                     
                     <fo:block margin-top="8mm"/>
			         <fo:block text-align="left" font-size="10px" start-indent="2cm">Upon the expiration of cancellation of this policy for any reason, the<fo:inline font-weight="bold" font-style="italic"> Named Insured </fo:inline>shall have the right, upon payment of the additional premium solely designated in option (a) below for the designated length of time shown solely in option (a) below, commencing on the expiration date of the Automatic Extended Reporting Period, to report <fo:inline font-weight="bold" font-style="italic">Claims </fo:inline>pursuant to Section VI. A. of General Conditions, Notice of <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>or Circumstance; however, upon the expiration or cancellation of this policy for any reason other than for cancellation for non payment of premium or for non payment of Deductible due hereunder, the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall have the right upon payment of the additional premium designated in options (b), (c) or (d) below for the designated length of time shown, commencing upon the expiration date of the Automatic Extended Reporting period, to report <fo:inline font-weight="bold" font-style="italic">Claims </fo:inline> pursuant to Section VI. A. of General Conditions, Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance: </fo:block>
			         
			         <fo:block margin-top="8mm"/>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">(a) Optional Extended Reporting Period of 12 months for a premium charge of 100% of the annual policy premium;</fo:block>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">(b) Optional Extended Reporting Period of 36 months for a premium charge of 185% of the annual policy premium;</fo:block>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">(c) Optional Extended Reporting Period of 60 months for a premium charge of 225% of the annual policy premium;</fo:block>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">(d) Optional Extended Reporting Period of 72 months for a premium charge of 250% of the annual policy premium.</fo:block>			
                 	 
                 	 <fo:block margin-top="8mm"/>
			 		 <fo:block text-align="left" font-size="10px" start-indent="2cm">Coverage under such extension of time to report a <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>(hereinafter referred to as the Optional Extended Reporting Period) shall apply solely to negligent acts, errors or omissions in rendering <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> committed or attempted prior to the effective date of nonrenewal or cancellation, whichever occurs first, and which are not otherwise excluded by any terms, conditions or exclusions of this policy.
					 </fo:block>

			         <fo:block margin-top="8mm"/>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">Except with respect to Optional Extended Reporting Period (a), as a condition precedent to purchase Optional Extended Reporting Periods (b), (c) or (d):</fo:block>
					 
					 <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDIL0910" /></fo:block>
					 
					 <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="right">Page 1 of 3</fo:block>
					 
					 
					 
					 <fo:block break-after="page"/>
					 
					 <fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
					 <fo:block margin-top="6mm"/>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">(a)  the total premium and Deductible amounts  for this policy must have been paid, and</fo:block>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">(b)  all <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline>  must be  in compliance with the terms and conditions of the policy, and</fo:block>
               		 <fo:block text-align="left" font-size="10px" start-indent="2cm">(c)  the<fo:inline font-weight="bold" font-style="italic"> Named Insured’s</fo:inline> right to practice law has not been revoked, suspended or surrendered at the request of any regulatory authority for reasons other than death, disability or retirement,  and</fo:block>
               		 <fo:block text-align="left" font-size="10px" start-indent="2cm">(d)  the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> provides the Insurer with written notice of its selection  and pays the premium charge for the selected Optional Reporting Period in full within sixty days of the expiration date of  the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.</fo:block>
 				
 					 
 					 
 					 <fo:block margin-top="6mm"/>  
 					 <fo:block text-align="left" font-size="10px" start-indent="2cm">To effect Optional Extended Reporting Period option (a) the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> must receive a written request to effect such Extended Reporting Period and the premium due for such Extended Reporting Period, as well as any other premiums due and owing to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, within sixty days after the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. All premium paid to the Insurer to effect any Optional Extended Reporting Period shall be fully earned by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> and the Optional Extended Reporting Period cannot be cancelled by the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> or the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.</fo:block>


               	     <fo:block margin-top="6mm"/> 
				     <fo:block text-align="left" font-size="10px" >2.  Section IV, Definitions, definition of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> is amended as follows:</fo:block>
				      <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">- the first two sentences of the definition of<fo:inline font-weight="bold" font-style="italic"> Damages</fo:inline> are deleted and replaced with the following:</fo:block>

            	     <fo:block margin-top="6mm"/> 

				     <fo:block text-align="left" font-size="10px" start-indent="2cm" >“<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> means any monetary judgment, monetary award or monetary settlement negotiated with the <fo:inline font-weight="bold" font-style="italic">Insurer’s </fo:inline> written consent.  Pre- and post- judgment interest awarded against the <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>shall be deemed an element of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> which are payable in addition to the Limit of Liability as a supplementary payment.” </fo:block>
				     <fo:block margin-top="4mm"/> 
				     	
				     <fo:block text-align="left" font-size="10px" start-indent="2cm" >- paragraph (c) under “ <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> shall not include: …” is deleted and replaced with the following (c):</fo:block>

				     <fo:block margin-top="6mm"/> 
				
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">(c) 	punitive or exemplary damages, awards or judgments or any amounts of compensatory damages/awards or judgments if awarded as a result on the <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> own misconduct; however,  the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall have a  right and duty to defend and pay <fo:inline font-weight="bold" font-style="italic">Defense Costs</fo:inline> for any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>  which includes a demand for both punitive and covered compensatory <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> under this policy.</fo:block>

				     <fo:block margin-top="6mm"/> 

			         <fo:block text-align="left" font-size="10px" >3.  	Section VI, General Conditions, D. Cancellation and Non Renewal:</fo:block>
					 <fo:block margin-top="4mm"/>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">- 	paragraph #2, is deleted and replaced with the following 2.:</fo:block>
					 <fo:block margin-top="4mm"/>

					 <fo:block text-align="left" font-size="10px" start-indent="2cm">“The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may only cancel this policy for nonpayment of premium. This policy may be canceled by or on behalf of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known mailing address, as well as to the <fo:inline font-weight="bold" font-style="italic">Named Insured’s</fo:inline> insurance agent or broker, if known,  written notice of cancellation at least 10 days before the effective date of cancellation. The <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline> must retain proof of mailing on a form acceptable to the U. S. Post office or other commercial mail delivery service.  Mailing of a cancellation notice by any other means shall not be deemed a valid notice of cancellation. If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels this policy for  non payment of premium, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall credit the<fo:inline font-weight="bold" font-style="italic"> Insured </fo:inline>90% of the unearned portion of the premium. Payment or tender of any unearned premium by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not be a condition precedent to the effectiveness of cancellation, but such payment shall be as soon as practicable.”</fo:block>


					 <fo:block text-align="left" font-size="10px" start-indent="2cm">- 	paragraph #3 is deleted and replaced with the following #3.:</fo:block>
					 <fo:block margin-top="4mm"/>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">“This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured </fo:inline>at its last known address, as well as to the <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> insurance broker or agent , if known,  written notice of such nonrenewal at least sixty days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The Insurer must maintain proof of mailing on a form acceptable to the U.S. Post  Office or other commercial mail delivery service. Mailing of non renewal notices by any other means shall not be deemed valid non  renewal notice.”</fo:block>
					 <fo:block margin-top="4mm"/>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">Even if the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> fails to comply with the paragraph above, the policy will expire at the end of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> if (a) the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>  fails to pay any premium or installment premium due the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> whether payable direct to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> or to an agent of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, or indirectly under any premium finance plan or extension of credit; (b) the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> has indicated its willingness to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or to its representative, to renew the policy, or (c) the Named Insured has notified the Insurer or the agent of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> that the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> does not wish to renew the policy.</fo:block>
					 
					 <fo:block margin-top="4mm"/>
					 
					 <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDIL0910" /></fo:block>
					 
					 <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="right">Page 2 of 3</fo:block>
					 
					 <fo:block break-after="page"></fo:block>
					 <fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
					 <fo:block margin-top="10mm"/>
					 <fo:block text-align="left" font-size="10px" >4.  Section VI., General Conditions, H. Other Insurance, is deleted and replaced with the following</fo:block>
					 <fo:block margin-top="6mm"/>
					 <fo:block text-align="left" font-size="10px" start-indent="2cm">All <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> payable under this policy shall be shared proportionately with any other insurance, except insurance written to be specifically excess  above this policy, applicable to any <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>made under this policy. This policy shall not be the subject of any terms or conditions of such other insurance</fo:block>
			
			
					 
					
			
               

				      <fo:block margin-top="180mm"/>
				    
				      <fo:block text-align="center" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				      <fo:block margin-top="15mm"/>
				       <fo:block>
				       <fo:table  >
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
			  <fo:block margin-top="15mm"/>
			  <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDIL0910" /></fo:block>
		  	  <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="right">Page 3 of 3</fo:block>
		   <!--  
		    </fo:flow>
		  </fo:page-sequence>
		</fo:root>	  
			-->
     </xsl:template>
</xsl:stylesheet>
