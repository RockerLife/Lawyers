<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<!-- <xsl:include  href="foxslnew/group5/MO_42_005_04_17_WY.xsl" /> 
	 -->  
	  <xsl:template match="/">
		
		<fo:root>
		
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait" page-width="21cm"   
					page-height="29.7cm" margin-top="0.9cm" margin-bottom="0.7in" margin-left="0.5in" 
					margin-right="0.5in">
					<fo:region-body margin-top="0.7in"/>
					<fo:region-before extent="3.8in"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>	
	
		     <fo:page-sequence master-reference="A4-portrait">			
				
				<fo:static-content flow-name="xsl-region-before">
				<fo:table>
					<fo:table-column column-width="150mm"/>
					<fo:table-column column-width="50mm"/>
					<fo:table-body>
						<fo:table-row>
							<fo:table-cell>
								<fo:block text-align="right">
				             		<fo:external-graphic src="../LawyersIns/image/LogoImage.png" content-height="4em" content-width="21em"/>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell>
								<fo:block font-size="8px" font-family="Arial"><xsl:value-of select="response/subproducerFormData_freeform_01/data/StreetAddress"/>,</fo:block>
								<fo:block font-size="8px" font-family="Arial"><xsl:value-of select="response/subproducerFormData_freeform_01/data/SPCity"/>, &#160; <xsl:value-of select="response/subproducerFormData_freeform_01/data/State"/>,&#160;<xsl:value-of select="response/subproducerFormData_freeform_01/data/SPZip"/></fo:block>
								<fo:block font-size="8px" font-family="Arial">Phone 	<xsl:value-of select="response/subproducerFormData_freeform_01/data/SPPhoneNumber"/></fo:block>
								<fo:block font-size="8px" font-family="Arial">Fax 	<xsl:value-of select="response/subproducerFormData_freeform_01/data/Fax"/></fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
				 	
				
				
				</fo:static-content>
				
			
				<!-- <fo:static-content flow-name="xsl-region-after">
				<fo:block text-align="right" >Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block>
				</fo:static-content> -->
				
				<fo:flow flow-name="xsl-region-body" margin-top="1cm">
				
					<fo:block margin-top="2mm">
					
				 <fo:block text-align="left">				  	
				  	<fo:external-graphic src="../LawyersIns/image/ISMIE_Quote_logo.PNG" content-height="6em" content-width="30em"/>           	
				  </fo:block>	
						
						 
					<fo:table>
				         <fo:table-column />
				         <fo:table-body>
				         			<fo:table-row>
					             	<fo:table-cell padding-top="2mm" padding-left="4pt">
					             		 <fo:block> &#160;&#160;</fo:block>
						    
					             	</fo:table-cell>
					            
					           </fo:table-row>
				         		 <fo:table-row>
					             	<fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="12px" font-family="Arial" font-weight="bold" >LAWYERS PROFESSIONAL LIABILITY INSURANCE PREMIUM QUOTATION </fo:block></fo:table-cell>
					             <!-- <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell> -->
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="12px" font-family="Arial" font-weight="bold" >- Applicant:  <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					             <!-- <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell> -->
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="12px" font-family="Arial" font-weight="bold">- Effective Dates of Coverage from  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  -  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /></fo:block></fo:table-cell>
					             <!-- <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  -  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /></fo:block></fo:table-cell> -->
					           </fo:table-row>
					          <!--  <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Quotation # <xsl:value-of select="response/policy_freeform_01/data/QuoteNumber" /></fo:block></fo:table-cell>
					             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/QuoteNumber" /></fo:block></fo:table-cell>
					           </fo:table-row> -->
					           
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt">
					             	<fo:block font-size="11px" font-family="Arial" >					             				
					             		- Prior Acts is provided from <xsl:if test="response/professionalliabilityinsdetail_freeform_01/data/IsPriorActDateFull= 'N'"> Date</xsl:if><xsl:if test="response/policy_freeform_01/data/IsFirmCarryingProfLiabilityIns != 'Y'"> Date</xsl:if>:&#160;
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
									    for the Named Insured (see policy definition to ensure proper coverage and to confirm all predecessor firms are adequately covered). Prior Acts is limited to hire dates for the individual attorneys. 					   					             	
					             	</fo:block>
					             </fo:table-cell>
					            				             
					           </fo:table-row>
					           
					           
					           <fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt">
					             <fo:block font-size="11px" font-family="Arial" font-weight="bold">Coverage is provided for 
					             <xsl:value-of select="response/NumberOfLawyers"/>
					             <xsl:choose>
					             	<xsl:when test="response/NumberOfLawyers = '1'">
					             		&#160;attorney 
					             	</xsl:when>
					             	<xsl:otherwise>
					             		&#160;attorneys
					             	</xsl:otherwise>
					             </xsl:choose>
					            (see Coverage Summary).
					             
					             
					             </fo:block>
					             
					             </fo:table-cell>
					             
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" font-weight="bold">Coverage Summary</fo:block></fo:table-cell>
					             
					           </fo:table-row>
						</fo:table-body>
					</fo:table>
					</fo:block>
					<fo:block> &#160;&#160;</fo:block>
						<fo:block >		
						<!-- <xsl:if test="response/quoteOpted_list_01/* or response/quoteOpted_list_01/text()"> -->	
					<xsl:if test="response/quoteOptedUpdated_list_01/data !=''">
							<fo:table>
		                      <fo:table-column />
		                      <fo:table-column />
		                      <fo:table-column />
		                      <fo:table-column />
		                      <fo:table-body>
		                        <fo:table-row>
		                          <fo:table-cell  >
		                            <fo:block  line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt">
		                                                  Limits of Liability  </fo:block>
		                             <fo:block line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" >                     
		                                                Each Claim/Annual Aggregate
		                                                   </fo:block>
		                               <fo:block line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" >                        
		                                                                         (*CEIL/**CEOL)
		                                                                         </fo:block>
		                          </fo:table-cell>
		                          <fo:table-cell >
		                            <fo:block text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt">Deductible</fo:block>
		                          </fo:table-cell>
		                          <fo:table-cell >
		                            <fo:block text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt">Type</fo:block>
		                          </fo:table-cell>
								<fo:table-cell >
		                            <fo:block text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt"> Annual Premium</fo:block>
		                          </fo:table-cell>
		                        </fo:table-row>
		                        <xsl:for-each select="response/quoteOptedUpdated_list_01/data">
		                          <fo:table-row>
		                            <fo:table-cell  >
		                              <fo:block line-height="12pt" font-style="italic" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt">
		                                <xsl:value-of select="LimitAmount"/>
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell >
		                              <fo:block line-height="12pt" font-style="italic" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt">
		                                <xsl:value-of select="DeductibleAmount"/>
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell >
		                              <fo:block line-height="12pt" font-style="italic" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt">
		                                <xsl:value-of select="QuoteType"/>
		                              </fo:block>
		                            </fo:table-cell>
									<fo:table-cell >
		                              <fo:block line-height="12pt" font-style="italic" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold" padding-left="3pt" padding-top="7pt" >
		                                <xsl:value-of select="InvoicedPremium"/>
		                              </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                        </xsl:for-each>
		                      </fo:table-body>
		                    </fo:table>
						
					</xsl:if>	
					</fo:block>					
						 <fo:block> &#160;&#160;</fo:block>
				<fo:block >		
						
					
							<fo:table margin-top="2mm" >
		                      <fo:table-column />
		                      <fo:table-column />
		                      <fo:table-column />
		                      <fo:table-body>
		                        <fo:table-row>
		                          <fo:table-cell border="0.5pt solid black" padding-left="10pt" padding-top="8pt">
		                            <fo:block  line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   >
		                                                  Extensions of Coverage   </fo:block>
		                             <fo:block line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left" >                     
		                                               Included with Policy
		                                                   </fo:block>
		                                                   <fo:block> &#160;&#160;</fo:block>
		                          </fo:table-cell>
		                          <fo:table-cell border="0.5pt solid black" padding-left="10pt" padding-top="8pt">
		                             <fo:block  line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  >
		                                                  Limits of Liability   </fo:block>
		                             <fo:block line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left" >                     
		                                              Extension Limits
		                                                   </fo:block>
		                                                   <fo:block> &#160;&#160;</fo:block>
		                          </fo:table-cell>
								<fo:table-cell border="0.5pt solid black" padding-left="10pt" padding-top="8pt">
		                            <fo:block  line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  >
		                                                  Deductible   </fo:block>
		                             <!-- <fo:block line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left" >                     
		                                               Each Claim
		                                                   </fo:block> -->
		                                                   <fo:block> &#160;&#160;</fo:block>
		                          </fo:table-cell>
		                        </fo:table-row>
		                       
		                          <fo:table-row>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  padding="5pt">
		                                Subpoena Assistance Sublimit
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt">
		                                No Limit
		                              </fo:block>
		                            </fo:table-cell>
									<fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt" >
		                                $0
		                              </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                      	<fo:table-row>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  padding="5pt">
		                                Disciplinary Proceedings
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt">
		                                $25,000 / $25,000
		                              </fo:block>
		                            </fo:table-cell>
									<fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt" >
		                                $0
		                              </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                          <fo:table-row>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  padding="5pt">
		                               Loss of Earnings 
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt">
		                               $100 per day
		                              </fo:block>
		                            </fo:table-cell>
									<fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt" >
		                                $0
		                              </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                          <fo:table-row>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  padding="5pt">
		                               Crisis Event Expenses
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt">
		                                $20,000 / $20,000
		                              </fo:block>
		                            </fo:table-cell>
									<fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt" >
		                                $0
		                              </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                          <fo:table-row>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  padding="5pt">
		                               Client Discrimination 
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt">
		                                $15,000 / $15,000
		                              </fo:block>
		                            </fo:table-cell>
									<fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt" >
		                                $0
		                              </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                          <fo:table-row>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"  padding="5pt">
		                               Regulatory Inquiry Extension
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt">
		                                $25,000 / $25,000
		                              </fo:block>
		                            </fo:table-cell>
									<fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt" >
		                                $0
		                              </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                      </fo:table-body>
		                    </fo:table>
						
					
					</fo:block>	
					 <fo:block> &#160;&#160;</fo:block>
					<fo:block>
						<fo:table >
							<fo:table-column />
		                      <fo:table-body>
		                      
		                        <fo:table-row>
		                        	<fo:table-cell padding="4pt">
		                        			<fo:block font-style="italic" font-size="8px"> Additional Higher Limits may be available upon request.</fo:block>
		                        	</fo:table-cell>
		                        </fo:table-row>
		                        <fo:table-row>
		                        	<fo:table-cell padding="4pt">
		                        		<fo:block font-style="italic" font-size="8px">*CEIL - Claim Expenses Inside the Limit of Liability - Claim Expenses will erode the available limit for Indemnity </fo:block>
		                        	</fo:table-cell>
		                        </fo:table-row>
		                        <fo:table-row>
		                        	<fo:table-cell padding="4pt">
		                        		<fo:block font-style="italic" font-size="8px">	**CEOL - Claim Expenses Outside the Limit of Liability - Claim Expense limits are separate from Indemnity limits </fo:block>
		                        	</fo:table-cell>
		                        </fo:table-row>
		                        <fo:table-row>
		                        	<fo:table-cell padding="4pt">
		                        		<fo:block font-style="italic" font-size="8px">	Each Claim - Deductible feature which applies to both defense expenses and damages for each claim made and reported during the policy period. </fo:block>
		                        	</fo:table-cell>
		                        </fo:table-row>
		                        <fo:table-row>
		                        	<fo:table-cell padding="4pt">
		                        		<fo:block font-style="italic" font-size="8px">	AGG- Aggregate Deductible - Limits your contribution to the deductible amount shown on your declarations page, regardless of the number of claims reported during the policy period </fo:block>
		                        	</fo:table-cell>
		                        </fo:table-row>
		                        <fo:table-row>
		                        	<fo:table-cell padding="4pt">
		                        		<fo:block font-style="italic" font-size="8px">	FDD- First Dollar Defense - Deductible feature that converts the deductible to only apply in the event damages are paid to resolve a claim against your firm </fo:block>
		                        	</fo:table-cell>
		                        </fo:table-row>
		                       </fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block break-after="page" />
					
					
					<fo:block margin-top="5mm" font-size="11px" font-family="Arial"  >
						<fo:inline font-weight="bold" text-decoration="underline">Included Endorsements</fo:inline>
					</fo:block>
					<fo:block>  &#160;&#160;</fo:block>
					<fo:block>  &#160;&#160;</fo:block>
					   
					<fo:table>
				         <fo:table-column />
				         <fo:table-column />
				        <!--  <fo:table-column column-width = "67mm" /> -->			         
						 
						 <fo:table-body>	
						 	<fo:table-row>
							    <fo:table-cell padding-top="2mm">
							      <fo:block font-size="11px" font-weight="bold">
							      	<fo:inline padding-left="4pt" text-decoration="underline">Form ID Number</fo:inline></fo:block>
							    </fo:table-cell>
							    <fo:table-cell padding-top="2mm">
							      <fo:block font-size="11px"  font-weight="bold">
							      	<fo:inline text-decoration="underline" padding-left="4pt">Form Name</fo:inline></fo:block>
							    </fo:table-cell>
							   
							  </fo:table-row>
							  
				         	<xsl:for-each select="response/stateform_list_01/data">				         	
								<fo:table-row>								
									<fo:table-cell padding-top="2mm" padding-left="4pt">
										<fo:block font-size="11px"  font-family="Arial">
											<xsl:value-of select="ROWID"/>.&#160;&#160; <xsl:value-of select="FormID" />
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
						<fo:block>  &#160;&#160;</fo:block>		 
							<fo:block>  &#160;&#160;</fo:block>		 
								<fo:block>  &#160;&#160;</fo:block>		 
					 <fo:block font-size="10px" font-weight="bold" text-decoration="underline">Subject To
					</fo:block>
					<fo:block>  &#160;&#160;</fo:block>		  
					<fo:table>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" font-size="10px" font-weight="bold" line-height="25pt">The binding of the above quoted coverage is subject to our receipt of and/or favorable review by the Company of:</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" font-size="10px" line-height="25pt">
									1.&#160;&#160;Completed, signed and currently dated Application (attached).
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" font-size="10px" line-height="25pt">
									2.&#160;&#160;Quotation Acceptance Form signed by a principal of the firm (attached).
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" font-size="10px" font-weight="bold" line-height="25pt">
									3.&#160;&#160;Please remit payment <fo:inline text-decoration="underline"> prior </fo:inline> to effective date of your policy to <xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" font-size="10px" line-height="25pt">
									4.&#160;&#160;Quotation is valid until <xsl:value-of select="response/policy_freeform_01/data/QuoteExpiryDate" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" font-size="10px" line-height="25pt">
									<xsl:if test="response/QuoteComment!=''">
										5.&#160;&#160;<xsl:value-of select="response/QuoteComment" />
									</xsl:if>
									
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					
					
					<fo:block break-after="page" />
					
					 <fo:block text-align="left">				  	
				 	 	<fo:external-graphic src="../LawyersIns/image/QuoteAcceptanceLogo.PNG" content-height="3em" content-width="20em"/>           	
				  </fo:block>	
					<fo:table>
				         <fo:table-column />
				         <fo:table-body>
				         		
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="12px" font-family="Arial" font-weight="bold" >Applicant:  <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					           
					           </fo:table-row>
					          <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="12px" font-family="Arial" font-weight="bold">Effective Dates of Coverage from  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  -  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /></fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell padding-top="2mm" padding-left="4pt">
					             	<fo:block font-size="12px" font-family="Arial" font-weight="bold">					             				
					             		Prior Acts Date:
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
					             <fo:table-cell padding-top="2mm" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" font-weight="bold">Line of Coverage: Lawyers Professional Liability Insurance</fo:block></fo:table-cell>
					             
					           </fo:table-row>
					            					           
					           <fo:table-row >
					             <fo:table-cell padding-top="8pt" padding-left="4pt" text-align="justify"><fo:block font-size="11px" font-family="Arial">I, on behalf of the <xsl:value-of select="response/policy_freeform_01/data/AccountName" />, hereby accept the following terms offered by <fo:inline font-weight="bold"> ISMIE Mutual Insurance Company</fo:inline> and authorize <fo:inline font-weight="bold"><xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName"/></fo:inline> to order coverage, as noted below, on the firm’s behalf.</fo:block></fo:table-cell>
					             
					           </fo:table-row>
					            
						</fo:table-body>
					</fo:table>
					<fo:block >		
						<!-- <xsl:if test="response/quoteOpted_list_01/* or response/quoteOpted_list_01/text()"> -->	
					<xsl:if test="response/quoteOpted_list_01/data !=''">
				
						<fo:table margin-top="2mm">
					         <fo:table-column column-width = "185mm" />	    		         
							 
							  <fo:table-body>
							 							  
					         		         		
					         		
					         	   	<fo:table-row>				
										<fo:table-cell >
											<fo:block >
												<fo:table>
											          <fo:table-column column-width = "110mm" />
											         <fo:table-column column-width = "30mm" />
											          <fo:table-column  column-width = "30mm" />
											         
											          
											         <fo:table-body>
											         	<fo:table-row>
											             <fo:table-cell number-columns-spanned="3"><fo:block font-size="11px" font-family="Arial" font-weight="bold">Please insert your desired limits, deductible and premium.</fo:block><fo:block>  &#160;&#160;</fo:block></fo:table-cell>
											             
											           </fo:table-row>
												          <fo:table-row  border="0.5pt solid black">
												          <fo:table-cell border="0.5pt solid black" text-align="center" padding="3pt">
												          		<fo:block font-size="11px" font-family="Arial" text-decoration="underline" font-weight="bold">
												             	Limits of Liability
												             		
												             	</fo:block>
												             	 <fo:block font-size="11px" font-family="Arial" text-decoration="underline" font-weight="bold" >
												             	Each Claim/Annual Aggregate
												             		
												             	</fo:block>
												             </fo:table-cell>	
												             <fo:table-cell border="0.5pt solid black" text-align="center" padding="3pt">
												             <fo:block font-size="11px" font-family="Arial" text-decoration="underline" font-weight="bold">
												             	Deductible
												             		
												             	</fo:block>
												             	 
												             </fo:table-cell>
												              <fo:table-cell border="0.5pt solid black" text-align="center" padding="3pt">	
												             <fo:block font-size="11px" font-family="Arial" text-decoration="underline" font-weight="bold" >
												             
												             	Annual Premium
												             		
												             	</fo:block>
												             	
												             </fo:table-cell>	
												          </fo:table-row>
												          <xsl:for-each select="response/quoteOpted_list_01/data">		
												           <fo:table-row  border="0.5pt solid black">
												             
												             <fo:table-cell  padding="4pt">
												             	<fo:block font-size="11px" font-family="Arial" >
												             	<fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;$<xsl:value-of select="AggregateLimit" /> Each <fo:inline font-weight="bold">Claim</fo:inline> / $<xsl:value-of select="OccuranceLimit" /> Annual Aggregate
												             	<xsl:choose>
													                    <xsl:when test="IsClaimExpensesType='Y'">
													                        CEOL
						        								        </xsl:when>
													                    <xsl:otherwise>
													                        CEIL
						        								        </xsl:otherwise>
													                </xsl:choose>
												             		
												             	</fo:block>
												             </fo:table-cell>
												             <fo:table-cell   border="0.5pt solid black" padding="4pt">
												             	<fo:block font-size="11px" font-family="Arial" >
												             		$<xsl:value-of select="DeductibleAmount" /> 
												             		<xsl:choose>
													                    <xsl:when test="IsClaimOptionType='Y'">
													                        &#160;AGG,
						        								        </xsl:when>
													                    <xsl:otherwise>
													                        &#160;Each Claim, 
						        								        </xsl:otherwise>
													                </xsl:choose>
													                <xsl:choose>
													                    <xsl:when test="IsDollarDefense='Y'">
													      					&#160;FDD
						        								        </xsl:when>
													                 <xsl:otherwise>
													                 </xsl:otherwise>
													                </xsl:choose>
												             	</fo:block>
												             </fo:table-cell>
												             <fo:table-cell  padding="4pt" border="0.5pt solid black"><fo:block font-size="11px" font-family="Arial" >$<xsl:value-of select="InvoicedPremium" /></fo:block></fo:table-cell>
												            
												           </fo:table-row>
												           </xsl:for-each> 
												           <fo:table-row>
											             <fo:table-cell padding-top="4mm" padding-left="4pt" number-columns-spanned="3" text-align="justify">
											             <fo:block font-size="11px" font-family="Arial" >
											             As part of this quotation acceptance, I acknowledge that, after inquiry of each lawyer in the firm, there is no awareness of any claims, suit for fees, bar complaints or disciplinary actions and/or circumstances, acts, errors or omissions that might reasonably be expected to be the basis of a claim or suit which have not already been disclosed. I understand that a knowing misrepresentation in connection with such awareness could give rise to grounds for rescission of the policy being obtained.  I further understand that any such   matter would be excluded from coverage under the policy being obtained, and therefore should be immediately reported to my current carrier before expiration of that policy, to secure coverage for such potential claim under my former policy.  I also confirm that I have received and reviewed a copy of the specimen policy (ISMIE ALA-04-P001 (09/01/2021)) and endorsements.  I acknowledge and have read all of the exclusions in the policy as well as the endorsements to the policy. I acknowledge that I have disclosed all predecessor firms to <xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName"/> and that I will hold the agency harmless for not disclosing all predecessor firms.
											             </fo:block>
											             </fo:table-cell>
											           </fo:table-row>
											          <!--  <fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>  -->
												        <fo:table-row>
											             <fo:table-cell padding-top="3mm" padding-left="4pt" number-columns-spanned="3" text-align="justify"><fo:block font-size="11px" font-family="Arial" >
											            	I am aware that coverage is not bound until confirmation of coverage is received from the carrier, and an insurance policy is provided by <xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName"/>. In authorizing <xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName"/> to order coverage, I agree to be responsible for any minimum premium payable to the insurance company once coverage is bound.
											             </fo:block>
											             </fo:table-cell>
											             
											           </fo:table-row>
											          	
											          			           
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>																		
									</fo:table-row>	
								 
							</fo:table-body>
							
						</fo:table>	
					</xsl:if>	
					</fo:block>	
					
					<fo:block>			
					<fo:table>
						<fo:table-column column-width="75mm"></fo:table-column>
						<fo:table-column column-width="55mm"></fo:table-column>
						<fo:table-column column-width="60mm"> </fo:table-column>
						<fo:table-body>
							 <fo:table-row>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
				           	</fo:table-row>
							 <fo:table-row>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
				           	</fo:table-row>
							 <fo:table-row>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
							 <fo:table-cell><fo:block> &#160;&#160;</fo:block> </fo:table-cell>
				           	</fo:table-row>
							 <fo:table-row>
				           		<fo:table-cell>
					           		<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">&#160; </fo:block>
					            </fo:table-cell>
				           		<fo:table-cell>
				           			<fo:block> &#160;&#160;</fo:block>
				           		</fo:table-cell>
					           	<fo:table-cell>
						           	<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">&#160; </fo:block>
					            </fo:table-cell>
				           	</fo:table-row>
			            <fo:table-row>
			           		<fo:table-cell>
				           		
				                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"
															>
				                      Signature of Owner, Partner or Officer
				
				                    </fo:block>
			           		</fo:table-cell>
			           		<fo:table-cell>
			           			<fo:block> &#160;</fo:block>
			           		</fo:table-cell>
			           	<fo:table-cell >
				           		
				                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="left"
															>
				                     Date
				
				                    </fo:block>
			           		</fo:table-cell>
			           </fo:table-row> 
											         
						<fo:table-row>
					           		<fo:table-cell number-columns-spanned="3" padding-top="5pt">
						           		
						                    <fo:block font-size="12px" text-decoration="underline" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"
																	>
						                     Form of Payment (Please check one)
						
						                    </fo:block>
					           		</fo:table-cell>
					           		
					           </fo:table-row> 
						</fo:table-body>
					</fo:table>
					</fo:block>
										
						<fo:table>
							<fo:table-column></fo:table-column>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding-top="5pt">
											<fo:block font-size="11px" font-family="Arial" >
												<fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;Credit Card, ACH, or Premium Financing
											</fo:block>
									</fo:table-cell>
									
									<fo:table-cell padding-top="5pt">
										<fo:block font-size="11px" font-family="Arial" >
												<fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;Check made payable to <xsl:value-of select="response/subproducerFormData_freeform_01/data/ProducerName"/>
											</fo:block>
									</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
									<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell padding-top="2pt" text-align="left" padding-left="50pt">
												<fo:block font-size="8px" font-family="Arial"><xsl:value-of select="response/subproducerFormData_freeform_01/data/StreetAddress"/>,</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell  text-align="left" padding-left="50pt">
												<fo:block font-size="8px" font-family="Arial"><xsl:value-of select="response/subproducerFormData_freeform_01/data/SPCity"/>, &#160; <xsl:value-of select="response/subproducerFormData_freeform_01/data/State"/>,&#160;<xsl:value-of select="response/subproducerFormData_freeform_01/data/SPZip"/></fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell  text-align="left" padding-left="50pt">
												<fo:block font-size="8px" font-family="Arial">Phone 	<xsl:value-of select="response/subproducerFormData_freeform_01/data/SPPhoneNumber"/></fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell  text-align="left" padding-left="50pt">
												<fo:block font-size="8px" font-family="Arial">Fax 	<xsl:value-of select="response/subproducerFormData_freeform_01/data/Fax"/></fo:block>
										</fo:table-cell>
									</fo:table-row>
									
							</fo:table-body>
						</fo:table>
						<fo:block>
						<fo:table>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
										<fo:table-cell border-bottom-color="black" border-bottom-style="dashed" text-align="justify" padding="4pt" >
												<fo:block   >
												                     </fo:block>
										</fo:table-cell>
									</fo:table-row>
								<fo:table-row>
										<fo:table-cell  text-align="left" padding-top="4pt">
												 <fo:block font-size="12px" text-decoration="underline" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
													Please contact me to discuss the following Insurance Coverage (Please check)
												 </fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block font-size="11px" font-family="Arial" padding="4pt">
												<fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;Business Owners Policy &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;	
												
												<fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;Cyber Protection &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
												
												<fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;Worker’s Compensation &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
											</fo:block>
										</fo:table-cell>
									
									</fo:table-row>
							</fo:table-body>
						</fo:table>
						</fo:block>
						
		   		 <fo:block break-after="page" />
		   		 
		   		<fo:block text-align="left">				  	
				 	 	<fo:external-graphic src="../LawyersIns/image/ISMIE_Quote_logo.PNG" content-height="6em" content-width="30em"/>           	
				  </fo:block>	
						
		   		 <fo:table>
		   		 	<fo:table-column/>
		   		 	<fo:table-body>
		   		 	<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell>
   		 						<fo:block font-size="11px" font-family="Arial" text-align="justify">
						            Founded in 1976, ISMIE has for more than 45 years led the Illinois medical professional liability (MPL) market and is recognized across the country for its integrity and performance.  
						             </fo:block>
						             </fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 		<fo:table-cell>
   		 						<fo:block font-size="11px" font-family="Arial" text-align="justify">
						          ISMIE has launched new affinity programs by expanding its offering of professional liability product lines. Following a very deliberate process of several years, ISMIE seized on an opportunity to expand its business to develop programs for Non-Medical Professional Liability (PL) customer groups. 
						             </fo:block>
						            </fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 		<fo:table-cell>
   		 						<fo:block font-size="11px" font-family="Arial" text-align="justify">
						           A core tenet of ISMIE is to serve policyholders by not only offering innovative and high-quality products and services, but also by providing an unyielding claims defense with proven litigation strategies tailored for each case. 
						             </fo:block>
						             </fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell>
		   		 				<fo:block text-align="left">				  	
								 	 	<fo:external-graphic src="../LawyersIns/image/FinancialStandings.PNG" content-height="3em" content-width="15em"/>           	
								  </fo:block>	
						
		   		 			</fo:table-cell>
		   		 		</fo:table-row>
		   		 		
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 		<fo:table-cell>
   		 						<fo:block font-size="11px" font-family="Arial" text-align="justify">
						           ISMIE has total assets greater than $1.35 billion, holds a policyholder surplus of $663 million, and is rated A- (Excellent) and Financial Size Category Class X according to A.M. Best. 
						             </fo:block>
						             </fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 		<fo:table-cell>
   		 						<fo:block font-size="11px" font-family="Arial" text-align="justify">
						           For more information about this company, log on to their web site: www.ISMIE.com.
						             </fo:block>
						             </fo:table-cell>
		   		 		</fo:table-row>
		   		 	</fo:table-body>
		   		 </fo:table>
		   		<fo:block id="last-page" font-size="11px"/>	
				</fo:flow>
					
			</fo:page-sequence>
		
		</fo:root>
     </xsl:template> 
</xsl:stylesheet>	
				