<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	
	<xsl:template match="/" name="LPL-AMEND-VT">
								<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
				 	
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="12px" >VERMONT AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="8mm"/>
				 
				         <fo:block font-size="10px">In consideration of the premium charged, it is agreed that:</fo:block>
				     <fo:block margin-top="4mm"/>
				     
				     <fo:block text-align="left" font-size="10px" >1.&#160;&#160;&#160;&#160;Item 3. of the Declarations, Limit of Liability,  is deleted and replaced with the following:</fo:block>
				     
				     <fo:block margin-top="5mm"/>
                     
                     <fo:table border="2pt solid black" start-indent="7mm">
						<fo:table-column column-width="15mm" />
						<fo:table-column column-width="150mm" />				
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left" start-indent="0mm">Item3.</fo:block>
								</fo:table-cell>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block text-align="left" font-size="10px" start-indent="0mm"><fo:inline font-weight="bold" >Limits of Liability:</fo:inline></fo:block>
								   
									<fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Damages</fo:inline>, each <fo:inline font-weight="bold">Claim</fo:inline></fo:block>
								    <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, each <fo:inline font-weight="bold">Claim</fo:inline></fo:block>
								    <fo:block margin-top="4mm"/>
								    <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Damages Aggregate</fo:inline></fo:block>
								    <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline></fo:block>
				      		</fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
					
				        <fo:block margin-top="4mm"/>
				        <fo:block text-align="left" font-size="10px" >2.&#160;&#160;&#160;Section II. of the policy, Limits of Liability and Deductible,  subsection A., Limits of Liability, is deleted and replaced</fo:block> 
				        <fo:block text-align="left" font-size="10px" >&#160;&#160;&#160;&#160;&#160;&#160;with the following:</fo:block>
				        <fo:block margin-top="4mm"/>
				        
				      <fo:block text-align="left" font-size="10px" text-indent="0.7cm" font-weight="bold" >A.&#160;&#160;&#160;&#160;Limits of Liability</fo:block>
				       <fo:block margin-top="2mm"/>
				      
				        
				            <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">1.&#160;&#160;&#160;Subject to 2. that follows, the <fo:inline font-weight="bold" >Insurer's</fo:inline> Limits of Liability for <fo:inline font-weight="bold" >Damages</fo:inline> and <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, for each <fo:inline font-weight="bold" >Claim</fo:inline></fo:block>
				     		<fo:block text-align="justify" font-size="10px" start-indent="2cm">first made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> during the <fo:inline font-weight="bold" >Policy Period</fo:inline>, including the Automatic and the Non-Practicing Extended Reporting Periods, shall not exceed the amounts shown in Item 3. of the Declarations as applicable to "<fo:inline font-weight="bold" >Damages</fo:inline>, each Claim” and <fo:inline font-weight="bold" >"Defense Expenses</fo:inline>, each <fo:inline font-weight="bold" >Claim</fo:inline>", respectively.</fo:block>
				        
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm" >2.&#160;&#160;&#160;Subject to 1. above, the <fo:inline font-weight="bold" >Insurer's</fo:inline> Limits of Liability for <fo:inline font-weight="bold" >Damages</fo:inline> and <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, for all <fo:inline font-weight="bold" >Claims</fo:inline> first </fo:block>
				     
				          <fo:block text-align="justify" font-size="10px" start-indent="2cm">made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> during the <fo:inline font-weight="bold" >Policy Period</fo:inline>, including the Automatic and the Non-Practicing Extended Reporting Periods, shall not exceed the amounts shown in Item 3. of the Declarations as applicable to "<fo:inline font-weight="bold" >Damages Aggregate</fo:inline>" and "<fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline>", respectively.</fo:block>
				        
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm" >3.&#160;&#160;&#160;The Limits of Liability shall apply excess of the Deductible amount.</fo:block>
				     
				        
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm" >4.&#160;&#160;&#160;The Limits of Liability available for <fo:inline font-weight="bold" >Claims</fo:inline> first made against the <fo:inline font-weight="bold" >Insured</fo:inline> and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> during</fo:block>
				           <fo:block text-align="justify" font-size="10px" start-indent="2cm">the Optional Extended Reporting Period, are part of, and not in addition to the Limits of Liability shown in Item 3. of the Declarations; however, to the extent that the <fo:inline font-weight="bold" >Damages Aggregate</fo:inline> or <fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline> has been reduced by <fo:inline font-weight="bold" >Damages</fo:inline> or <fo:inline font-weight="bold" >Defense Expenses</fo:inline> arising from <fo:inline font-weight="bold" >Claims</fo:inline> reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> prior to the inception of the Optional Extended Reporting Period, such <fo:inline font-weight="bold" >Damages Aggregate</fo:inline> or <fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline> shall be reinstated to its full amount at the inception of the Optional Extended Reporting Period.</fo:block>
				     
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm" >5.&#160;&#160;&#160;If the <fo:inline font-weight="bold" >Insurer</fo:inline> has exhausted the "<fo:inline font-weight="bold" >Damages</fo:inline>, each <fo:inline font-weight="bold" >Claim</fo:inline>" Limit of Liability by payment of <fo:inline font-weight="bold" >Damages</fo:inline> or by </fo:block>
				           <fo:block text-align="justify" font-size="10px" start-indent="2cm">tender of the remaining "<fo:inline font-weight="bold" >Damages</fo:inline>, each <fo:inline font-weight="bold" >Claim</fo:inline>" Limit of Liability into court, it shall have no further duties to the <fo:inline font-weight="bold" >Insured</fo:inline> under this policy with respect to the same <fo:inline font-weight="bold" >Claim</fo:inline>.</fo:block>
				     
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm" >6.&#160;&#160;&#160;If the <fo:inline font-weight="bold" >Insurer</fo:inline> has exhausted the "<fo:inline font-weight="bold" >Damages Aggregate</fo:inline>" Limit of Liability by payment of <fo:inline font-weight="bold" >Damages</fo:inline> or by </fo:block>
				           <fo:block text-align="justify" font-size="10px" start-indent="2cm">tender of the remaining "<fo:inline font-weight="bold" >Damages Aggregate</fo:inline>" Limit of Liability into court, it shall have no further duties to the <fo:inline font-weight="bold" >Insured</fo:inline> under this policy with respect to all <fo:inline font-weight="bold" >Claims</fo:inline>.</fo:block>
				     
				      <fo:block margin-top="4mm"/>
				     
				        <fo:block text-align="justify" font-size="10px" start-indent="1.4cm" >In the event the amount shown in this endorsement for <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, each <fo:inline font-weight="bold" >Claim</fo:inline> is exhausted, then 
						<fo:inline font-weight="bold" >Defense Expenses</fo:inline> incurred thereafter, shall be part of and reduce the Limit of Liability as shown for <fo:inline font-weight="bold" >Damages</fo:inline>, 
						each <fo:inline font-weight="bold" >Claim</fo:inline>. In the event the amount shown above for <fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline> is exhausted, then <fo:inline font-weight="bold" >Defense Expenses</fo:inline> incurred thereafter, shall be part of and reduce the Limit of Liability as shown for <fo:inline font-weight="bold" >Damages Aggregate</fo:inline>.
				       </fo:block>
				       
				      <fo:block margin-top="3cm"/>
				   <fo:block  font-size="10px" color="grey" text-align="left">LPL-AMEND-VT (04/17)&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Page 1 of 3</fo:block>
				      <fo:block break-after="page" />
				  
				 
				        
				        <fo:block text-align="left" font-size="10px">3.&#160;&#160;&#160;Section II. of the policy, Limits of Liability and Deductible, subsection C., Multiple <fo:inline font-weight="bold" >Insureds, Claims </fo:inline>and Claimants, is </fo:block> 
				        <fo:block text-align="left" font-size="10px" start-indent="0.5cm" >deleted and replaced with the following;</fo:block>
				        <fo:block margin-top="2mm"/>
				        
				        
				        <fo:block text-align="left" font-size="10px" text-indent="0.6cm" ><fo:inline font-weight="bold" >C.&#160;&#160;&#160;&#160;Multiple Insureds, Claims and Claimants</fo:inline></fo:block>
				       <fo:block margin-top="2mm"/>
				      
				        <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">Regardless of the number of <fo:inline font-weight="bold" >Claims, Insureds</fo:inline> or  claimants, the  Limit of Liability shown in Item 3. of the </fo:block>
				     		
				      <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">Declarations as applicable to "each <fo:inline font-weight="bold" >Claim</fo:inline>", to "<fo:inline font-weight="bold" >Damages Aggregate</fo:inline>" and to "<fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline>" shall be subject to paragraph A. of this Section II. If <fo:inline font-weight="bold" >Related Claims</fo:inline> are subsequently made against the <fo:inline font-weight="bold" >Insured</fo:inline> and reported to the <fo:inline font-weight="bold" >Insurer,</fo:inline> all such Related <fo:inline font-weight="bold" >Claims</fo:inline>, whenever made, shall be considered a single <fo:inline font-weight="bold" >Claim</fo:inline> first made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> within the policy period in which the earliest of the <fo:inline font-weight="bold" >Related Claims</fo:inline> was first made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline>.</fo:block>
				        
				        <fo:block margin-top="4mm"/>
				        
				         <fo:block text-align="left" font-size="10px"  ><fo:inline>4.&#160;&#160;&#160;Section III., Extension of Coverage B., Spousal and Domestic Partner Extension, is amended by deleting the phrase </fo:inline></fo:block>
				         <fo:block text-align="left" font-size="10px" text-indent="0.6cm" >"lawful spouse or lawful domestic partner" and replacing it with "lawful spouse, lawful domestic partner</fo:block>
				         <fo:block text-align="left" font-size="10px" text-indent="0.6cm" >or party to a civil union recognized under Vermont law".</fo:block>
				       <fo:block margin-top="2mm"/>
				        
				        
				         <fo:block text-align="left" font-size="10px" >5.&#160;&#160;&#160;Section IV. of the policy, Definitions, is amended as follows:</fo:block> 
				        <fo:block margin-top="4mm"/>
				       
				       <fo:block text-align="left" font-size="10px" text-indent="0.6cm" >a.&#160;&#160;&#160;&#160;&#160;Definition H., <fo:inline font-weight="bold">Immediate Family Member</fo:inline>, paragraph 6., is deleted and replaced with the following:</fo:block>
				       <fo:block margin-top="3mm"/>
				       <fo:block text-align="left" font-size="10px" text-indent="14mm" >6.	past or present spouse or party to a civil union recognized under Vermont law,</fo:block>
				       <fo:block margin-top="3mm"/>
				      <fo:block text-align="left" font-size="10px" text-indent="0.6cm" >b.&#160;&#160;&#160;&#160;&#160;Definition M., <fo:inline font-weight="bold">Policy Aggregate</fo:inline>, is deleted.</fo:block>
				       <fo:block margin-top="2mm"/>
				        
				        <fo:block text-align="left" font-size="10px" text-indent="0.6cm" >c.&#160;&#160;&#160;&#160;&#160;The following definitions are added:</fo:block>
				       <fo:block margin-top="2mm"/>
				        
				        <fo:block text-align="justify" font-size="10px" start-indent="1.4cm"><fo:inline font-weight="bold" >•&#160;&#160;&#160;&#160;&#160;Damages Aggregate</fo:inline> means the applicable amount shown in Item 3. of the Declarations which represents </fo:block>
				        <fo:block text-align="justify" font-size="10px" start-indent="2cm">the maximum amount of the <fo:inline font-weight="bold" >Insurer's</fo:inline> liability for all <fo:inline font-weight="bold" >Damages</fo:inline> for all <fo:inline font-weight="bold" >Claims</fo:inline> under this policy, inclusive of any applicable Extended Reporting Period.</fo:block>
				       <fo:block margin-top="2mm"/>
				        <fo:block text-align="justify" font-size="10px" start-indent="1.4cm"><fo:inline font-weight="bold" >•&#160;&#160;&#160;&#160;&#160;Defense Expenses Aggregate</fo:inline> means the applicable amount shown in Item 3. of the Declarations which </fo:block>
				        <fo:block text-align="justify" font-size="10px" start-indent="2cm">represents the maximum amount of the <fo:inline font-weight="bold" >Insurer's</fo:inline> liability for all <fo:inline font-weight="bold" >Defense Expenses</fo:inline> for all <fo:inline font-weight="bold" >Claims</fo:inline> under this policy, inclusive of any applicable Extended Reporting Period.</fo:block>
				      
				     <fo:block padding-left="5pt" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="justify">

            <fo:block margin-top="3mm"></fo:block>
            <fo:block space-after="4mm">

              <fo:list-block>
                <fo:list-item space-after="4mm">
                  <fo:list-item-label>
                    <fo:block>
                      <fo:inline>6.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>

                    <fo:block space-after="3mm" start-indent="6mm">
                      Section VI. of the policy, General Condition D., Cancellation and Nonrenewal, is deleted and replaced with the following:
                    </fo:block>

                    <fo:block>

                      <fo:list-block>
                        <fo:list-item>
                          <fo:list-item-label>
                            <fo:block start-indent="6mm">
                              <fo:inline font-weight="bold">D.</fo:inline>
                            </fo:block>
                          </fo:list-item-label>
                          <fo:list-item-body start-indent="13mm">

                            <fo:block space-after="3mm">
                              <fo:inline font-weight="bold">
                                Cancellation, Nonrenewal, Renewal and Conditional Renewal
                              </fo:inline>
                            </fo:block>
                            <fo:block>
                              <fo:list-block space-after="3mm">

                                <fo:list-item>
                                  <fo:list-item-label start-indent="13mm">
                                    <fo:block>
                                      <fo:inline font-weight="bold">1.</fo:inline>
                                    </fo:block>
                                  </fo:list-item-label>
                                  <fo:list-item-body start-indent="20mm">
                                    <fo:block space-after="3mm">
                                      <fo:inline font-weight="bold">Cancellation</fo:inline>
                                    </fo:block>

                                    <fo:block>

                                      <fo:list-block>

                                        <fo:list-item space-after="3mm">
                                          <fo:list-item-label start-indent="20mm">
                                            <fo:block>
                                              <fo:inline>(a)</fo:inline>
                                            </fo:block>
                                          </fo:list-item-label>
                                          <fo:list-item-body start-indent="27mm">
                                            <fo:block>
                                             
                                              The <fo:inline font-weight="bold">Named Insured</fo:inline> may cancel this policy at any time prior to the expiration date of the 
                                              <fo:inline font-weight="bold">Policy Period</fo:inline> by providing prior written 
                                              notice to the <fo:inline font-weight="bold">Insurer</fo:inline> or by surrender of this policy to the <fo:inline font-weight="bold">Insurer</fo:inline>
                                              or its authorized agent.  If the <fo:inline font-weight="bold">Named Insured</fo:inline> cancels this policy, the <fo:inline font-weight="bold">
                                                Insurer</fo:inline> shall return 90% of the unearned portion of the premium.

                                            </fo:block>
                                          </fo:list-item-body>
                                        </fo:list-item>

                                        <fo:list-item space-after="3mm">
                                          <fo:list-item-label start-indent="20mm">
                                            <fo:block>
                                              <fo:inline>(b)</fo:inline>
                                            </fo:block>
                                          </fo:list-item-label>
                                          <fo:list-item-body start-indent="27mm">
                                            
                                            <fo:block>

									The <fo:inline font-weight="bold">Insurer</fo:inline> may only cancel this policy for nonpayment of premium or
									Deductible. This policy may be canceled by the <fo:inline font-weight="bold">Insurer</fo:inline> by mailing or
									delivering to the <fo:inline font-weight="bold">Named Insured</fo:inline>, at the address shown in Item 1. of the
									Declarations, written notice of cancellation at least 15 days before
									the effective date of cancellation. The reason or reasons for
									cancellation shall accompany or be included in the notice of
									cancellation and the effective date of cancellation stated in such
									notice shall become the expiration date of the <fo:inline font-weight="bold">Policy Period</fo:inline>. When
									notice is provided by mail, such notice shall be by certified mail,
									except that in the case of cancellation for nonpayment of premium,
									notice shall be by certified mail or certificate of mailing. The
									effective date of cancellation stated in such notice shall become the
									expiration date of the <fo:inline font-weight="bold">Policy Period</fo:inline>. If the <fo:inline font-weight="bold">Insurer</fo:inline> cancels this
									policy, the <fo:inline font-weight="bold">Insurer</fo:inline> shall credit the <fo:inline font-weight="bold">Named Insured</fo:inline> the pro rata
									unearned portion of the premium. Payment or tender of any unearned
									premium by the <fo:inline font-weight="bold">Insurer</fo:inline> shall not be a condition precedent to the
									effectiveness of cancellation, but such payment shall be made as soon
									as practicable.</fo:block>
                                            <fo:block margin-top="3mm"></fo:block>
                                            
                                          </fo:list-item-body>
                                        </fo:list-item>

                                      </fo:list-block>

                                    </fo:block>

                                  </fo:list-item-body>
                                </fo:list-item>

                                <fo:list-item>
                                  <fo:list-item-label start-indent="13mm">
                                    <fo:block>
                                      <fo:inline font-weight="bold">2.</fo:inline>
                                    </fo:block>
                                  </fo:list-item-label>
                                  <fo:list-item-body start-indent="20mm">
                                    <fo:block space-after="3mm">
                                      <fo:inline font-weight="bold">Nonrenewal</fo:inline>
                                    </fo:block>

                                    <fo:block>

									This policy may be nonrenewed by the <fo:inline font-weight="bold">Insurer</fo:inline> by mailing or delivering
									to the <fo:inline font-weight="bold">Named Insured</fo:inline>, at the address shown in Item 1. of the
									Declarations, written notice of nonrenewal at least 45 days prior to
									the expiration date of the <fo:inline font-weight="bold">Policy Period</fo:inline>. When notice is provided by
									mail, such notice shall be by certified mail.
								                                   
                                    </fo:block>
                                    
                                    <fo:block margin-top="3mm">
								This subsection shall not apply if the Insurer has manifested its
								willingness to renew, or in case of nonpayment of premium, or if the
								Named Insured fails to pay any advance premium required by the Insurer
								for renewal. However, notwithstanding the failure of the Insurer to
								comply with this subsection, the policy shall terminate on the
								effective date of any other policy with respect to property designated
								in both policies.
                                    </fo:block>
                                    
                                    <fo:block margin-top="3mm"></fo:block>

                                  </fo:list-item-body>
                                </fo:list-item>
                                 </fo:list-block>
				      			</fo:block>
				      			<fo:block  margin-top="9mm" start-indent="0mm" font-size="10px" color="grey" text-align="left">LPL-AMEND-VT (04/17)&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Page 2 of 3</fo:block>
			 </fo:list-item-body>
				      </fo:list-item>
				      </fo:list-block>
				      </fo:block>
				       </fo:list-item-body>
				     </fo:list-item>
				      </fo:list-block>
				      </fo:block>
				</fo:block>
				
               <fo:block padding-left="5pt" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="justify">
                            
                               <fo:block>
                               <fo:list-block>
                                <fo:list-item>
                                <fo:list-item-label>
                                <fo:block start-indent="13mm"><fo:inline font-weight="bold">3.</fo:inline></fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                <fo:block  start-indent="20mm"><fo:inline font-weight="bold">Renewal</fo:inline></fo:block>
                                <fo:block margin-top="3mm"></fo:block>
                                <fo:block start-indent="20mm">
						If the <fo:inline font-weight="bold">Insurer</fo:inline> has the necessary information to issue the renewal
						policy, the <fo:inline font-weight="bold">Insurer</fo:inline> shall confirm in writing at least 45 days prior to
						expiration its intention to renew the policy and the premium at which
						the policy is to be renewed. The <fo:inline font-weight="bold">Named Insured</fo:inline> shall have the right to
						renew the policy at this premium.
								</fo:block>
								
						 <fo:block start-indent="20mm" margin-top="3mm">
							If the <fo:inline font-weight="bold">Insurer</fo:inline> does not comply with this subsection, it shall grant the
							<fo:inline font-weight="bold">Named Insured</fo:inline> renewal coverage at the rate or premium in effect under
							the expiring or expired policy or at rates lawfully in effect on the
							expiration date. This shall be done on a pro rata basis and shall
							continue for 45 days after the <fo:inline font-weight="bold">Insurer</fo:inline> confirms renewal coverage and
							premium. This subsection shall not apply if the <fo:inline font-weight="bold">Named Insured</fo:inline> accepts
							the renewal policy.
                              </fo:block>  
                              
                              <fo:block start-indent="20mm" margin-top="3mm">
                              The <fo:inline font-weight="bold">Insurer</fo:inline> may transfer this policy to an affiliate upon expiration of the policy without providing notice of nonrenewal, provided that:
                              </fo:block>
                              </fo:list-item-body>
                                </fo:list-item>
                                <fo:list-item>
                                <fo:list-item-label>
                                <fo:block start-indent="20mm">(a)</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                <fo:block start-indent="27mm">the rating by A.M. Best or a similarly qualified rating service of the affiliate is equal to or better than the transferring Insurer;</fo:block>
                                </fo:list-item-body>
                                </fo:list-item>
                                
                                  <fo:list-item>
                                <fo:list-item-label>
                                <fo:block start-indent="20mm">(b)</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                <fo:block start-indent="27mm">there is no diminution in the terms and conditions of coverage; and</fo:block>
                                </fo:list-item-body>
                                </fo:list-item>
                                
                                 <fo:list-item>
                                <fo:list-item-label>
                                <fo:block start-indent="20mm">(c)</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                <fo:block start-indent="27mm">notice of the transfer is provided to the Named Insured at least 45 days prior to the transfer by first class mail, and in connection with such notice the Insurer:</fo:block>
                                </fo:list-item-body>
                                </fo:list-item>
                                
                                <fo:list-item>
                                <fo:list-item-label>
                                <fo:block start-indent="27mm">(1)</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                <fo:block start-indent="34mm">includes in the notice of transfer a telephone number of the Insurer, or the producer, if any, where the Named Insured can learn additional information concerning the transfer and the reasons for the transfer; and</fo:block>
                                </fo:list-item-body>
                                </fo:list-item>
                               
                                <fo:list-item>
                                <fo:list-item-label>
                                <fo:block start-indent="27mm">(2)</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                <fo:block start-indent="34mm">complies with the other provisions of this subsection relating to renewal policies.</fo:block>
                                </fo:list-item-body>
                                </fo:list-item>
                                
                                
                                <fo:list-item>
                                <fo:list-item-label>
                                <fo:block start-indent="13mm" margin-top="3mm"><fo:inline font-weight="bold">4.</fo:inline></fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                 <fo:block  start-indent="20mm" margin-top="3mm"><fo:inline font-weight="bold">Conditional Renewal</fo:inline></fo:block>
                                 <fo:block margin-top="3mm"></fo:block>
									<fo:block start-indent="20mm">In the event the <fo:inline font-weight="bold">Insurer</fo:inline> eliminates or modifies coverage,
										conditions or definitions in any policy in force or at renewal that
										results in a diminution of benefits, the <fo:inline font-weight="bold">Insurer</fo:inline> shall provide the
										<fo:inline font-weight="bold">Named Insured</fo:inline> a printed notice summarizing what coverages, conditions
										or definitions have been eliminated or modified. The notice shall be
										mailed or delivered by the <fo:inline font-weight="bold">Insurer</fo:inline> or its authorized representative on
										or before the time the <fo:inline font-weight="bold">Named Insured</fo:inline> receives the new policy or policy
										form. This subsection does not apply to changes in the policy which
										have been agreed to in writing or requested by the <fo:inline font-weight="bold">Named Insured</fo:inline>.
									</fo:block>
                                </fo:list-item-body>
                                </fo:list-item>
                                
							</fo:list-block>
                            </fo:block>
				     
				      </fo:block>
				     <fo:block margin-top="10mm"/>
				        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				<fo:block margin-top="12.5cm"/>
	         	
			 	<fo:block  font-size="10px" color="grey" text-align="left">LPL-AMEND-VT (04/17)&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Page 3 of 3</fo:block>
				 
     </xsl:template>
</xsl:stylesheet>




					
				    	




					
				    	