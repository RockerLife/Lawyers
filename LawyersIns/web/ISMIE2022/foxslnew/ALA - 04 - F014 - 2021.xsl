<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="ALA04F014">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
				    <!--<fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block >
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1040313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>  -->
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >AGGREGATE DEDUCTIBLE FOR ALL CLAIMS</fo:block>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >DOES NOT APPLY TO DEFENSE EXPENSES</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     <fo:block text-align="left" font-size="10px" >In consideration of: (check one box only)</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of $__________</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
				    
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >it is agreed that:</fo:block> 
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >1.  Item 4 of the Declarations, Deductible, is deleted and replaced with the following:</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:table border="2pt solid black">
						<fo:table-column column-width="20mm" />
						<fo:table-column column-width="150mm" />				
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left">Item 4.</fo:block>
								</fo:table-cell>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block text-align="left" font-size="10px" ><fo:inline font-weight="bold" >Deductible</fo:inline></fo:block>
								   
									<fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/> Aggregate Deductible for all <fo:inline font-weight="bold" >Claims</fo:inline></fo:block>
								    <fo:block text-align="left" font-size="10px" >This amount does not apply to <fo:inline font-weight="bold" >Defense Expenses</fo:inline></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
				     
				      <!-- 
				      <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >Item 4. Deductible</fo:block>
				      <fo:block text-align="left" text-indent="2cm" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/>, Aggregate Deductible for all <fo:inline font-weight="bold" >Claims</fo:inline></fo:block>
				      <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >This amount includes <fo:inline font-weight="bold" >Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
				      <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >policy.</fo:block>
				       -->
				       <fo:block margin-top="3mm"/>
				        <fo:block text-align="left" font-size="10px" >2.	Section I. of the policy, Insuring Agreement. subsection A., Coverage, is deleted and replaced with the following:</fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" text-indent="0.5cm" font-weight="bold" >A. Coverage</fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm" >Subject to the Limit of Liability shown in Item 3. of the Declarations and as limited in Section II., the <fo:inline font-weight="bold" >Insurer</fo:inline> shall pay on behalf of the <fo:inline font-weight="bold" >Insured</fo:inline> all <fo:inline font-weight="bold" >Damages</fo:inline> in excess of the Deductible shown in Item 4. of the Declarations and as limited in Section II., and all <fo:inline font-weight="bold" >Defense Expenses</fo:inline>. that the <fo:inline font-weight="bold" >Insured</fo:inline> becomes legally obligated to pay as a result of a <fo:inline font-weight="bold" >Claim</fo:inline> first made against the <fo:inline font-weight="bold" >Insured</fo:inline> and reported in writing to the <fo:inline font-weight="bold" >Insurer</fo:inline> during:</fo:block> 
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">1.	the <fo:inline font-weight="bold" >Policy Period</fo:inline>: or</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">2.    any applicable Extended Reporting Period,</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">by reason of a negligent act, error or omission in the performance of <fo:inline font-weight="bold" >Professional Services</fo:inline> by the <fo:inline font-weight="bold" >Insured</fo:inline> or by someone for whom the <fo:inline font-weight="bold" >Insured</fo:inline> is legally responsible, provided that such negligent act, error or omission began on or after the <fo:inline font-weight="bold" >Prior Acts Date</fo:inline> shown in Item 6. of the Declarations.</fo:block>
				      <fo:block margin-top="2mm"/>
				      
				      <fo:block text-align="left" font-size="10px" >3. Section II. of the policy, Limits of Liability and Deductible, subsection B., Deductible, is deleted and replaced with the</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="0.4cm">following: </fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" text-indent="0.5cm" font-weight="bold" >B. Deductible</fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm" >The <fo:inline font-weight="bold" >Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-weight="bold" >Damages</fo:inline> which are in excess of the Deductible amount shown in Item 4. of the Declarations. This Deductible amount shall: </fo:block> 
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">1.&#160;&#160;&#160;&#160;be borne by the <fo:inline font-weight="bold" >Insured</fo:inline>; </fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">2.&#160;&#160;&#160;&#160;remain uninsured; and</fo:block>
				      
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">3.&#160;&#160;&#160;&#160;be the maximum amount borne by the <fo:inline font-weight="bold" >Insured</fo:inline> for all <fo:inline font-weight="bold" >Claims</fo:inline> under this policy.</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm"> If the <fo:inline font-weight="bold" >Insured</fo:inline> has paid this amount, no further Deductibles shall apply to <fo:inline font-weight="bold" >Claims</fo:inline>. The Deductible amount applies to the payment of <fo:inline font-weight="bold" >Damages</fo:inline> only and shall not apply to <fo:inline font-weight="bold" >Defense Expenses</fo:inline>.  If the <fo:inline font-weight="bold" >Insurer</fo:inline> advances any amounts within the Deductible, the <fo:inline font-weight="bold" >Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold" >Insurer</fo:inline> within 30 days of the <fo:inline font-weight="bold" >Insurer's</fo:inline> request to do so.  In the event of <fo:inline font-weight="bold" >Related Claims</fo:inline>, a single Deductible amount will apply.</fo:block>
				      
				      <fo:block margin-top="4mm"/>
				      <fo:block text-align="left" font-size="10px">4.  Section VI. of the policy, General Conditions B., Defense and Settlement, paragraph 1., is deleted and replaced</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="0.4cm"> with the following: </fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" start-indent="0.5cm" text-align-last="justify">1. The <fo:inline font-weight="bold" >Insurer</fo:inline> shall have the right and the duty to defend any <fo:inline font-weight="bold" >Claim</fo:inline> regardless of whether the allegations are 
						      groundless, false or fraudulant. In undertaking this right and duty, the <fo:inline font-weight="bold" >Insurer</fo:inline> expressly retains the right to select defense counsel even when the <fo:inline font-weight="bold" >Insurer</fo:inline> reserves its rights 
						      on issues concerning the applicability of coverage under this policy. The <fo:inline font-weight="bold" >Insurer's</fo:inline> right and duty to defend any <fo:inline font-weight="bold" >Claim</fo:inline> and pay <fo:inline font-weight="bold" >Defense Expenses</fo:inline> shall terminate upon the 
				      </fo:block>
			         <fo:block margin-top="5mm"/>
				         
				        
				         
				        
				<fo:block  font-size="10px" color="grey" text-align="left">
				<xsl:choose>
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM2'">
						         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F014 (09/01/2021)
							       		 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 2</fo:block>
							       	</fo:block>
						     	</xsl:when> 
						     	<xsl:otherwise>
						         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F014 (09/01/2021)
							       		 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 2</fo:block>
							       	</fo:block>
		                      </xsl:otherwise>
                    </xsl:choose>	
				
				</fo:block>
				
				         
				         
				         
				    <fo:block text-align="left" font-size="10px" start-indent="0.5cm">exhaustion of the Limit of Liability, whereupon the <fo:inline font-weight="bold" >Insurer</fo:inline> shall have no further obligation or liability to defend the <fo:inline font-weight="bold" >Insured</fo:inline> or to pay <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, judgments or settlements. The <fo:inline font-weight="bold" >Insurer</fo:inline> may make any investigation it deems necessary and may, with <fo:inline font-weight="bold" >Insured's</fo:inline> consent, such consent not to be unreasonably withheld, make any settlement of any <fo:inline font-weight="bold" >Claim</fo:inline> it deems expedient. If the <fo:inline font-weight="bold" >Insured</fo:inline> withholds consent of such settlement, the <fo:inline font-weight="bold" >Insurer's</fo:inline> liability for all <fo:inline font-weight="bold" >Damages</fo:inline> on account of such <fo:inline font-weight="bold" >Claim</fo:inline> shall not exceed the amount for which <fo:inline font-weight="bold" >Insurer</fo:inline> could have settled such <fo:inline font-weight="bold" >Claim</fo:inline>, inclusive of <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, incurred as of the date such settlement was proposed to the <fo:inline font-weight="bold" >Insured</fo:inline>.</fo:block> 
				       
				     
				     
				     
				     
				        <fo:block margin-top="20mm"/>
				        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				       
				        
		       <fo:block margin-top="22cm"/>
		      <!--  
		      <fo:block  font-size="10px" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1170417" /></fo:block>
				--> 
				 
				 
				<fo:block  font-size="10px" color="grey" text-align="left">
				<xsl:choose>
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM2'">
						     		<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F014 (09/01/2021)
						         		 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 2 of 2</fo:block>
						         	</fo:block>
						     	</xsl:when> 
						     	<xsl:otherwise>
		                         <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F014 (09/01/2021)
					         		 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 2 of 2</fo:block>
					         	</fo:block>
		                      </xsl:otherwise>
                    </xsl:choose>	
				
				</fo:block>
				
				
     </xsl:template>
</xsl:stylesheet>




					
				    	