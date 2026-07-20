<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	
	  
	  <xsl:template match="/">
		
		<fo:root>
		
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait" page-width="21cm"   
					page-height="29.7cm" margin-top="0.9cm" margin-bottom="0.7in" margin-left="0.5in" 
					margin-right="0.5in">
					<fo:region-body margin-top="1.5in"/>
					<fo:region-before extent="3.8in"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>	
	
		     <fo:page-sequence master-reference="A4-portrait">			
				
				<fo:static-content flow-name="xsl-region-before">
				 <fo:block text-align="left">				  	
				  	<fo:external-graphic src="../LawyersIns/image/LogoImage.png" content-height="6em" content-width="20em"/>           	
				  </fo:block>	
				  
				  <fo:block>
					<fo:table>
				         <fo:table-column column-width = "80mm" />
				         <fo:table-column column-width = "50mm" />
				         <fo:table-column column-width = "70mm" />
				         <fo:table-body>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ><xsl:value-of select="response/subproducerFormData_freeform_01/data/StreetAddress" />,</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ><xsl:value-of select="response/subproducerFormData_freeform_01/data/SPCity" />, &#160; <xsl:value-of select="response/subproducerFormData_freeform_01/data/State" />,&#160;<xsl:value-of select="response/SPZip" /></fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="8px" font-family="Arial" >Phone 	<xsl:value-of select="response/subproducerFormData_freeform_01/data/SPPhoneNumber" /></fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="8px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="8px" font-family="Arial" >Fax 	<xsl:value-of select="response/subproducerFormData_freeform_01/data/Fax" /></fo:block></fo:table-cell>
					           </fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:external-graphic src="../LawyersIns/image/header_bottom.png" content-width="44em"/> 
				</fo:block>	
				
				<fo:table>
			         <fo:table-column column-width = "120mm" />
			         <fo:table-column column-width = "65mm" />
			         <fo:table-body>
			         	 <fo:table-row>
				             <fo:table-cell><fo:block font-size="10pt" font-family="Arial" color="grey"><fo:inline font-weight="bold">Quotation for Lawyers Professional Liability Insurance</fo:inline></fo:block></fo:table-cell>
				             <fo:table-cell><fo:block text-align="right" font-size="10px" text-indent="1cm" color="grey"><xsl:if test="response/policy_freeform_01/data/CurrentDate != ''"> </xsl:if><xsl:value-of select="response/policy_freeform_01/data/CurrentDate" />&#160;&#160;<xsl:value-of select="response/time_freeform_01/data/QuoteTime" /></fo:block></fo:table-cell>
				           </fo:table-row>				         
			         </fo:table-body>				         
				</fo:table>
				
				
				</fo:static-content>
				
				<fo:static-content flow-name="xsl-region-after">
				<fo:block text-align="left" >Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block>
				</fo:static-content>
				
				<fo:flow flow-name="xsl-region-body">
				
					<fo:block margin-top="10mm">
					<fo:table>
				         <fo:table-column />
				         <fo:table-body>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Insured: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					             <!-- <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell> -->
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Policy Period: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  -  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /></fo:block></fo:table-cell>
					             <!-- <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  -  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /></fo:block></fo:table-cell> -->
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Quotation # <xsl:value-of select="response/policy_freeform_01/data/QuoteNumber" /></fo:block></fo:table-cell>
					             <!-- <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/QuoteNumber" /></fo:block></fo:table-cell> -->
					           </fo:table-row>
					           
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt">
					             	<fo:block font-size="11px" font-family="Arial" >					             				
					             		Prior Acts<xsl:if test="response/professionalliabilityinsdetail_freeform_01/data/IsPriorActDateFull= 'N'"> Date</xsl:if><xsl:if test="response/policy_freeform_01/data/IsFirmCarryingProfLiabilityIns != 'Y'"> Date</xsl:if>:&#160;
					             		<xsl:if test="response/policy_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance= 'Y'">
						             		<xsl:if test="response/professionalliabilityinsdetail_freeform_01/data/IsPriorActDateFull= 'Y'"> 
										       No Limitation										       
										    </xsl:if>
										    <xsl:if test="response/professionalliabilityinsdetail_freeform_01/data/IsPriorActDateFull= 'N'"> 
										        <xsl:value-of select="response/professionalliabilityinsdetail_freeform_01/data/PriorActDatePross" /> 
										    </xsl:if>	
									    </xsl:if>	
									    <xsl:if test="response/policy_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance != 'Y'">
									    	<xsl:value-of select="response/professionalliabilityinsdetail_freeform_01/data/PriorActDatePross" />
									    </xsl:if>					   					             	
					             	</fo:block>
					             </fo:table-cell>
					            			             
					           </fo:table-row>
					           
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >This quotation expires on: <xsl:value-of select="response/policy_freeform_01/data/QuoteExpiryDate" /></fo:block></fo:table-cell>
					             <!-- <fo:table-cell  padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/QuoteExpiryDate" /></fo:block></fo:table-cell> -->
					           </fo:table-row>
					           
					           
						</fo:table-body>
					</fo:table>
					</fo:block>
					
					
					<fo:block >		
						<!-- <xsl:if test="response/quoteOpted_list_01/* or response/quoteOpted_list_01/text()"> -->	
					<xsl:if test="response/quoteOpted_list_01/data !=''">
				
						<fo:table margin-top="5mm">
					         <fo:table-column column-width = "185mm" />	    		         
							 
							  <fo:table-body>
							 							  
					         	<xsl:for-each select="response/quoteOpted_list_01/data">			         		
					         		<fo:table-row>
					         			<fo:table-cell >
						         			<fo:block>					         			
							         			<xsl:if test="position()mod 6=0">
												   <fo:block break-after="page" />
												</xsl:if>
											</fo:block>
										</fo:table-cell>
					         		</fo:table-row>
					         	   	<fo:table-row>				
										<fo:table-cell >
											<fo:block margin-top="5mm">
												<fo:table border="0.5pt solid black">
											         <fo:table-column column-width = "50mm" />
											         <fo:table-column column-width = "135mm" />
											         <fo:table-body>
											         	
											         		 <fo:table-row>
												             <fo:table-cell padding-top="2mm" number-columns-spanned="2" padding-left="4pt">
												             <fo:block text-align="left" font-size="11px" font-weight="bold">ACCEPT</fo:block>  
															</fo:table-cell>
												           </fo:table-row>
											         	
												           <fo:table-row>
												             <fo:table-cell number-columns-spanned="2" padding-left="4pt">												            
												            	<fo:block font-size="11px" start-indent="1cm" font-weight="bold">													            												            		
												            	   <fo:external-graphic src="../LawyersIns/image/check-btn4.png" /> Option <xsl:value-of select="ROWID" />:												        								   
																	Limit of Liability /
																<xsl:choose>
													                    <xsl:when test="IsClaimExpensesType='Y'">
													                        Claim Expenses Outside the Limit of Liability
						        								        </xsl:when>
													                    <xsl:otherwise>
													                         Claim Expenses Inside the Limit of Liability
						        								        </xsl:otherwise>
													                </xsl:choose>
																</fo:block>
																
															</fo:table-cell>
												           </fo:table-row>
												         
												           <fo:table-row>
												             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Limit of Liability:</fo:block></fo:table-cell>
												             <fo:table-cell padding-top="2mm" padding-left="4pt">
												             	<fo:block font-size="11px" font-family="Arial" >
												             		$<xsl:value-of select="AggregateLimit" /> Each <fo:inline font-weight="bold">Claim</fo:inline> / $<xsl:value-of select="OccuranceLimit" /> Annual Aggregate
												             		
												             	</fo:block>
												             </fo:table-cell>
												           </fo:table-row>
												           <fo:table-row>
												             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Deductible:</fo:block></fo:table-cell>
												             <fo:table-cell padding-top="2mm" padding-left="4pt">
												             	<fo:block font-size="11px" font-family="Arial" >
												             		$<xsl:value-of select="DeductibleAmount" /> 
												             		<xsl:choose>
													                    <xsl:when test="IsClaimOptionType='Y'">
													                        Annual Aggregate
						        								        </xsl:when>
													                    <xsl:otherwise>
													                        Each <fo:inline font-weight="bold">Claim</fo:inline> 
						        								        </xsl:otherwise>
													                </xsl:choose>
													                <xsl:choose>
													                    <xsl:when test="IsDollarDefense='Y'">
													      					<fo:block font-size="11px" font-family="Arial">  Deductible does not apply to Defense Expenses</fo:block>                  
						        								        </xsl:when>
													                    <xsl:otherwise>
													                      
						        								        </xsl:otherwise>
													                </xsl:choose>
												             	</fo:block>
												             </fo:table-cell>
												           </fo:table-row>
												         
												            
												           <fo:table-row>
												             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><fo:inline font-weight="bold">Total Premium:</fo:inline></fo:block></fo:table-cell>
												             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >$<xsl:value-of select="InvoicedPremium" /></fo:block></fo:table-cell>
												           </fo:table-row>	
												           	
												          				           
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>																		
									</fo:table-row>	
								</xsl:for-each>           
							</fo:table-body>
							
						</fo:table>	
					</xsl:if>	
					</fo:block>					
								
					
					<fo:block margin-top="5mm" font-size="11px" font-family="Arial">
						Please note: The total premiums shown above include any applicable taxes.
					</fo:block>
					
					<fo:block margin-top="5mm" font-size="11px" font-family="Arial">  
					  
					<fo:table>
				         <fo:table-column column-width = "185mm" />
				          <fo:table-body>	
						 	<fo:table-row>
							    <fo:table-cell >
							    	<fo:block font-size="11px" font-family="Arial" ><fo:inline font-weight="bold">This quotation is subject to the following conditions:</fo:inline></fo:block>
							    </fo:table-cell>
							</fo:table-row>
							<fo:table-row>
							    <fo:table-cell >
							    	<fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/QuoteComment" /></fo:block>
							    </fo:table-cell>
							</fo:table-row>
						  </fo:table-body>
					</fo:table>
					
					</fo:block>
						<!--	  
						 
					<fo:block margin-top="5mm" font-size="11px" font-family="Arial">
					If between the date of this quotation and the effective date of the policy, there is a significant change in the condition of the applicant, or an event which could substantially change our underwriting evaluation of the applicant, then, at our discretion, this quotation may be withdrawn by written notice to the proposed <fo:inline font-weight="bold">applicant</fo:inline>.   
					</fo:block>	
					    -->
					<fo:block margin-top="10mm" font-size="11px" font-family="Arial">
						<fo:inline font-weight="bold">If you would like to receive additional quote options, please contact one of our Professional Liability Specialists toll-free at &#160;<xsl:value-of select="response/subproducerFormData_freeform_01/data/SPPhoneNumber" /></fo:inline>
					</fo:block>
					
					 <fo:block break-after="page" /> 
					<fo:block margin-top="10mm" font-size="11px" font-family="Arial"  >
						<fo:inline font-weight="bold">Endorsements</fo:inline>
					</fo:block>
					
					   
					<fo:table border="0.5pt solid black">
				         <fo:table-column column-width = "59mm" />
				         <fo:table-column column-width = "126mm" />
				        <!--  <fo:table-column column-width = "67mm" /> -->			         
						 
						 <fo:table-body>	
						 	<fo:table-row>
							    <fo:table-cell padding-top="2mm">
							      <fo:block font-size="11px" font-style="italic" font-weight="bold">
							      	<fo:inline padding-left="4pt" text-decoration="underline">Form ID Number</fo:inline></fo:block>
							    </fo:table-cell>
							    <fo:table-cell padding-top="2mm">
							      <fo:block font-size="11px" font-style="italic" font-weight="bold">
							      	<fo:inline text-decoration="underline" padding-left="4pt">Form Name</fo:inline></fo:block>
							    </fo:table-cell>
							    <!-- <fo:table-cell border="0.5pt solid black">
							      <fo:block font-size="11px" border="0.5pt solid black" font-style="italic" font-weight="bold">
							      	<fo:inline text-decoration="underline" padding-left="4pt">Brief Description</fo:inline></fo:block>
							    </fo:table-cell> -->
							  </fo:table-row>
							  
				         	<xsl:for-each select="response/stateform_list_01/data">				         	
								<fo:table-row>								
									<fo:table-cell padding-top="2mm" padding-left="4pt">
										<fo:block font-size="11px"  font-family="Arial">
											<xsl:value-of select="FormID" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-top="2mm" padding-left="4pt">
										<fo:block font-size="11px"  font-family="Arial">
											<xsl:value-of select="FormName" />
										</fo:block>
									</fo:table-cell>
									<!-- <fo:table-cell border="0.5pt solid black" padding-left="4pt">
										<fo:block font-size="11px"  font-family="Arial">
											<xsl:value-of select="FormDesc" />
										</fo:block>
									</fo:table-cell>	 -->								
								</fo:table-row>								
							</xsl:for-each>           
						</fo:table-body>
						
					</fo:table>
					
					<fo:block margin-top="5mm" font-size="11px"  font-family="Arial" font-style="italic">  
						Please note that the title and brief description for each form listed in the section above may not fully describe the scope or intent of such form.  Please read each form carefully.
					</fo:block>
					
					
					
					
					<fo:block break-after="page" />
					<fo:block margin-top="10mm" font-size="11px"  font-family="Arial">
						<fo:inline font-weight="bold">Instructions for Binding Coverage</fo:inline>
					</fo:block>
					
					<fo:block margin-top="5mm">
					<fo:table>
						 <fo:table-column column-width = "10mm" />
				         <fo:table-column column-width = "175mm" />				         
				         <fo:table-body>				         	   
					           <fo:table-row>
					           	 <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left" >1.</fo:block></fo:table-cell>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left"  >Please review all parts of this quotation.</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					           	 <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left" >2.</fo:block></fo:table-cell>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left" >Complete, sign and date the attached United States Fire Insurance Company Lawyers Professional Liability Application.</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					           	 <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left" >3.</fo:block></fo:table-cell>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left" >Check the coverage option desired on the previous page.</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					           	 <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left"  >4.</fo:block></fo:table-cell>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" text-align="left" >Sign and date this form below, and return it along with your completed Application and your premium payment, prior to the quotation expiration date.</fo:block></fo:table-cell>
					           </fo:table-row>	
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="12px"  font-family="Arial" text-align="left" >5.</fo:block></fo:table-cell>
					             <fo:table-cell  padding-top="2mm" padding-left="4pt"><fo:block font-size="12px"  font-family="Arial" text-align="left" >Make checks payable to: <fo:inline font-weight="bold">&#160; <xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName" /> &#160;</fo:inline></fo:block></fo:table-cell>
					           </fo:table-row>			           
						</fo:table-body>
					</fo:table>
					</fo:block>
					
					<fo:block margin-top="5mm"/>
						<fo:table>
				         <fo:table-column column-width = "185mm" />	
				          <fo:table-body>				         	   
					           <fo:table-row>
					             <fo:table-cell padding-left="4pt" >
									<fo:block font-size="12px" font-family="Arial">
										If between the date of this quotation and the effective date of the policy, there is a significant change in the condition of the applicant, or an event which could substantially change our underwriting evaluation of the applicant, then, at our discretion, this quotation may be withdrawn by written notice to the proposed <fo:inline font-weight="bold">applicant</fo:inline>.   
									</fo:block>	
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
						</fo:table>
					
				
					<fo:block margin-top="5mm">
					<fo:table>
				         <fo:table-column column-width = "105mm" />	
				         <fo:table-column column-width = "80mm" />			         
				         <fo:table-body>				         	   
					           <fo:table-row>
					            <fo:table-cell number-columns-spanned="2" padding-left="4pt"><fo:block font-size="11px"  font-family="Arial" >My signature below constitutes my authorization for &#160; <xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName" /> &#160;to bind the referred coverage with United States Fire Insurance Company as indicated.<fo:external-graphic src="../LawyersIns/image/spacer.png" /></fo:block></fo:table-cell>
					          </fo:table-row>
					           
					         
					       
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm"  ><fo:block font-size="11px"  font-family="Arial" text-align="left" margin-top="8mm" padding-left="4pt"><fo:inline >Signature:_____________________</fo:inline></fo:block></fo:table-cell>
					          	 <fo:table-cell padding-top="2mm"  padding-left="70pt"><fo:block font-size="11px"  font-family="Arial" text-align="left" margin-top="8mm"><fo:inline >Date:________________</fo:inline></fo:block></fo:table-cell> 
					           </fo:table-row>					           			           
						</fo:table-body>
					</fo:table>
					</fo:block>		
		   		 
		   		 	<!-- <xsl:if test="response/policycoverage_freeform_01/data/IsClaimExpensesType != 'Y'">
		   		 		<fo:block break-after="page" />
		     			<xsl:call-template name="MO_42_005_04_17_WY" />						
		     		</xsl:if>		   		 
		   		  -->
		   		<fo:block id="last-page" font-size="11px"/>	
				</fo:flow>
					
			</fo:page-sequence>
		
		</fo:root>
     </xsl:template> 
</xsl:stylesheet>	
				