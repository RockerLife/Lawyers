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
				 <fo:block text-align="right">		  	
				  	<fo:external-graphic src="../LawyersIns/image/ProtexureLogo_New.png" content-height="4em" content-width="21em"/>           	
				  </fo:block>	
				
				
				</fo:static-content>
				
			
				<!-- <fo:static-content flow-name="xsl-region-after">
				<fo:block text-align="right" >Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block>
				</fo:static-content> -->
				
				<fo:flow flow-name="xsl-region-body" margin-top="0.1cm">
				
				<fo:table>
			         
			         <fo:table-body>
			       
			            		 <fo:table-row>
								
								<fo:table-cell>
									<fo:block text-align="left">
										<xsl:value-of select="response/policy_freeform_01/data/CurrentDate" />
										
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	
							
							
			         	 <fo:table-row>
								
								<fo:table-cell>
									<fo:block text-align="right">
										Via Electronic Mail
										
									</fo:block>
								</fo:table-cell>
								</fo:table-row>
								
							 <fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>
							 <fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>
							 
							 <fo:table-row>
								
								<fo:table-cell>
									<fo:block text-align="left" >
										<xsl:value-of select="response/policy_freeform_01/data/AccountName" />
										
									</fo:block>
								</fo:table-cell>
							</fo:table-row>			
							 <fo:table-row>
								
								<fo:table-cell>
									<fo:block text-align="left" >
										<xsl:value-of select="response/policy_freeform_01/data/Address1" />
										
									</fo:block>
								</fo:table-cell>
							</fo:table-row>			
							 <fo:table-row>
								
								<fo:table-cell>
									<fo:block text-align="left" >
										<xsl:value-of select="response/policy_freeform_01/data/Address2" />
										
									</fo:block>
								</fo:table-cell>
							</fo:table-row>		
							<fo:table-row>
					                    <fo:table-cell >
						                    <fo:block  text-align="left" >
							                    <xsl:if test="response/address_freeform_01/data/City!= ''"> 
											        <xsl:value-of select="response/address_freeform_01/data/City" /> 
											        <xsl:if test="response/address_freeform_01/data/StateDesc!= ''">, 
											        </xsl:if>
											    </xsl:if>
											    <xsl:if test="response/address_freeform_01/data/StateDesc!= ''">					    	
											        <xsl:value-of select="response/address_freeform_01/data/StateDesc" /> 
											        <xsl:if test="response/address_freeform_01/data/Zip!= ''">,&#160;			        	
											    	</xsl:if>
											    </xsl:if>	
											    <xsl:if test="response/address_freeform_01/data/Zip!= ''"> 
											        <xsl:value-of select="response/address_freeform_01/data/Zip" /> 
											    </xsl:if>						    
							                </fo:block>
					                    </fo:table-cell>
					    		  </fo:table-row>
							 <!-- <fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>
							 <fo:table-row>
								
								<fo:table-cell>
									<fo:block text-align="left" >
										<xsl:value-of select="response/policy_freeform_01/data/AccountName" />
										
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	 -->
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>
							 <fo:table-row>
								<fo:table-cell>
									<fo:block text-align="left" >
										Dear&#160;<xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/>:
									</fo:block>
								</fo:table-cell>
							</fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" >
										Thank you for allowing Protexure Insurance Agency, Inc. the opportunity to provide a quote for your
									firm’s consideration of lawyers Professional Liability options.

									</fo:block>
								</fo:table-cell>
							</fo:table-row>	      
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" >
										Attached, you will find a comprehensive proposal with terms and conditions offered for your firm’s upcoming annual Professional Liability policy.
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" >
										Protexure Insurance Agency, Inc. is a full-service insurance agency specializing in the insurance needs of solo attorneys and small law firms. Our agency is built on establishing long-term relationships with our clients and providing comprehensive professional liability insurance solutions in a timely fashion. We look forward to continuing our business relationship.
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	  
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" >
										To bind coverage, please follow the instructions on the quote acceptance page.
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	  
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="justify" >
										If you have any additional questions, please contact us at our toll-free number 877-569-4111 and one of our licensed Professional Liability Specialists will be happy to assist you.
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	 
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>	
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="left" >
										Regards,
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	 
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row>
								<fo:table-cell>
									<fo:block text-align="left" >
										The Protexure Lawyers Team
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	  
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							
							<fo:table-row>
								<fo:table-cell>
								<fo:block>
									
									</fo:block>
								</fo:table-cell>
							</fo:table-row>	  
			         </fo:table-body>				         
				</fo:table>
				<fo:table margin-top="20mm">
				         <fo:table-column /> <fo:table-column />
				         <fo:table-body>
				         	<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							<fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>		
							
							
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" text-align="left">PO Box 773197</fo:block></fo:table-cell>
					              <fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">(877) 569-4111 / T</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" text-align="left">Detroit, Michigan 48277-3197</fo:block></fo:table-cell>
					              <fo:table-cell ><fo:block font-size="9px" font-family="Arial" text-align="right">(440) 333-3214 / F</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" text-align="left">&#160;&#160;</fo:block></fo:table-cell>
					              <fo:table-cell ><fo:block font-size="9px" font-family="Arial" text-align="right">LAW.MAIL@PROTEXURE.COM / E</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block></fo:block></fo:table-cell>
					              <fo:table-cell text-align="right"><fo:block font-size="9px" font-family="Arial" >PROTEXURELAWYERS.COM / W</fo:block></fo:table-cell>
					           </fo:table-row>
					          </fo:table-body>
					          </fo:table>	
					 <fo:block break-after="page" />
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
					 <xsl:choose>
						        <xsl:when test="response/address_freeform_01/data/StateCode = 'FL' or response/address_freeform_01/data/StateCode = 'NJ'">
						                     	
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
		                 					<xsl:value-of select="TotalPremium"/>
	      							  </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                        </xsl:for-each>
		                      </fo:table-body>
		                    </fo:table>
						</xsl:if>	
					</xsl:when>
					<xsl:otherwise>
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
		                                      	 <xsl:value-of select="InvoicedPremium"/> B
	      							  </fo:block>
		                            </fo:table-cell>
		                          </fo:table-row>
		                        </xsl:for-each>
		                      </fo:table-body>
		                    </fo:table>
						</xsl:if>
					 </xsl:otherwise>
	                </xsl:choose>
					
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
		                                                  Extension Limits   </fo:block>
		                             <!-- <fo:block line-height="12pt" text-decoration="underline" font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left" >                     
		                                               Each Claim/Aggregate
		                                                   </fo:block> -->
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
		                                $50,000 / $100,000
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
		                               Loss of Earnings ($1,000 per Day)
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
		                               Crisis Event Expenses
		                              </fo:block>
		                            </fo:table-cell>
		                            <fo:table-cell border="0.5pt solid black" padding-left="10pt">
		                              <fo:block line-height="12pt"  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left"   padding="5pt">
		                                $50,000 / $50,000
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
									3.&#160;&#160;Please remit payment <fo:inline text-decoration="underline"> prior </fo:inline> to effective date of your policy to Protexure Insurance Agency
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
					             <fo:table-cell padding-top="8pt" padding-left="4pt" text-align="justify"><fo:block font-size="11px" font-family="Arial">I, on behalf of the <xsl:value-of select="response/policy_freeform_01/data/AccountName" />, hereby accept the following terms offered by <fo:inline font-weight="bold"> ISMIE Mutual Insurance Company</fo:inline> and authorize <fo:inline font-weight="bold">Protexure Insurance Agency, Inc.</fo:inline> to order coverage, as noted below, on the firm’s behalf.</fo:block></fo:table-cell>
					             
					           </fo:table-row>
					            
						</fo:table-body>
					</fo:table>
					<fo:block >		
						<!-- <xsl:if test="response/quoteOpted_list_01/* or response/quoteOpted_list_01/text()"> -->	
					<xsl:if test="response/quoteOpted_list_01/data !=''">
						<xsl:choose>
							 <xsl:when test="response/address_freeform_01/data/StateCode = 'FL' or response/address_freeform_01/data/StateCode = 'NJ'">
							  <fo:table margin-top="2mm">
					         <fo:table-column column-width = "185mm" />	    		         
							 
							  <fo:table-body>
					         	   	<fo:table-row>				
										<fo:table-cell >
											<fo:block >
												<fo:table>
										         <fo:table-column column-width = "100mm" />
										         <fo:table-column column-width = "30mm" />
										          <fo:table-column  column-width = "20mm" />
										          <fo:table-column  column-width = "20mm" />
										          <fo:table-column  column-width = "20mm" />
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
											             	  <fo:table-cell border="0.5pt solid black" text-align="center" padding="3pt">	
											             	<fo:block font-size="11px" font-family="Arial" text-decoration="underline" font-weight="bold" >
											           		  <xsl:if test="response/address_freeform_01/data/StateCode='FL'">
											             			FLPLIGA
											             		</xsl:if>
											             		<xsl:if test="response/address_freeform_01/data/StateCode='NJ'">
											             			NJPLIGA 
											             		</xsl:if>
											             	</fo:block>
											             </fo:table-cell>
											             <fo:table-cell border="0.5pt solid black" text-align="center" padding="3pt">	
											             	<fo:block font-size="11px" font-family="Arial" text-decoration="underline" font-weight="bold" >
											             
											             	Total Premium
											             		
											             	</fo:block>
											             </fo:table-cell>	
											          </fo:table-row>
											          <xsl:for-each select="response/quoteOpted_list_01/data">		
											           <fo:table-row  border="0.5pt solid black">
											             
											             <fo:table-cell  padding="4pt" text-align="center" >
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
											             <fo:table-cell   border="0.5pt solid black" padding="4pt" text-align="center" >
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
											             <fo:table-cell  padding="4pt" border="0.5pt solid black" text-align="center" ><fo:block font-size="11px" font-family="Arial" >$<xsl:value-of select="TotalPremium" /></fo:block></fo:table-cell>
											              <fo:table-cell  padding="4pt" border="0.5pt solid black" text-align="center" ><fo:block font-size="11px" font-family="Arial" >$<xsl:value-of select="TotalPolicyTaxAmount" /></fo:block></fo:table-cell>
											              <fo:table-cell  padding="4pt" border="0.5pt solid black" text-align="center" ><fo:block font-size="11px" font-family="Arial" >$<xsl:value-of select="InvoicedPremium" /></fo:block></fo:table-cell>
											           </fo:table-row>
											           </xsl:for-each> 
											           <fo:table-row>
										             <fo:table-cell padding-top="4mm" padding-left="4pt" number-columns-spanned="5" text-align="justify">
										             <fo:block font-size="11px" font-family="Arial" >
										             As part of this quotation acceptance, I acknowledge that, after inquiry of each lawyer in the firm, there is no awareness of any claims, suit for fees, bar complaints or disciplinary actions and/or circumstances, acts, errors or omissions that might reasonably be expected to be the basis of a claim or suit which have not already been disclosed. I understand that a knowing misrepresentation in connection with such awareness could give rise to grounds for rescission of the policy being obtained.  I further understand that any such   matter would be excluded from coverage under the policy being obtained, and therefore should be immediately reported to my current carrier before expiration of that policy, to secure coverage for such potential claim under my former policy. I acknowledge that I have disclosed all predecessor firms to Protexure Insurance Agency and that I will hold the agency harmless for not disclosing all predecessor firms.
										             </fo:block>
										             </fo:table-cell>
										           </fo:table-row>
										          <!--  <fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>  -->
											        <fo:table-row>
										             <fo:table-cell padding-top="3mm" padding-left="4pt" number-columns-spanned="5" text-align="justify"><fo:block font-size="11px" font-family="Arial" >
										            	I am aware that coverage is not bound until confirmation of coverage is received from the carrier, and an insurance policy is provided by Protexure Insurance Agency, Inc. In authorizing Protexure Insurance Agency, Inc. to order coverage, I agree to be responsible for any minimum premium payable to the insurance company once coverage is bound.
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
					
							
							 
   				        </xsl:when>
		                    <xsl:otherwise>
					        <fo:table margin-top="2mm">
					         <fo:table-column column-width = "185mm" />	    		         
							  <fo:table-body>
							 	   	<fo:table-row>				
										<fo:table-cell >
											<fo:block >
												<fo:table>
										         <fo:table-column column-width = "110mm" />
										         <fo:table-column column-width = "30mm" />
										          <fo:table-column  column-width = "20mm" />
										         
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
										             As part of this quotation acceptance, I acknowledge that, after inquiry of each lawyer in the firm, there is no awareness of any claims, suit for fees, bar complaints or disciplinary actions and/or circumstances, acts, errors or omissions that might reasonably be expected to be the basis of a claim or suit which have not already been disclosed. I understand that a knowing misrepresentation in connection with such awareness could give rise to grounds for rescission of the policy being obtained.  I further understand that any such   matter would be excluded from coverage under the policy being obtained, and therefore should be immediately reported to my current carrier before expiration of that policy, to secure coverage for such potential claim under my former policy. I acknowledge that I have disclosed all predecessor firms to Protexure Insurance Agency and that I will hold the agency harmless for not disclosing all predecessor firms.
										             </fo:block>
										             </fo:table-cell>
										           </fo:table-row>
										          <!--  <fo:table-row><fo:table-cell><fo:block>  &#160;&#160;</fo:block></fo:table-cell></fo:table-row>  -->
											        <fo:table-row>
										             <fo:table-cell padding-top="3mm" padding-left="4pt" number-columns-spanned="3" text-align="justify"><fo:block font-size="11px" font-family="Arial" >
										            	I am aware that coverage is not bound until confirmation of coverage is received from the carrier, and an insurance policy is provided by Protexure Insurance Agency, Inc. In authorizing Protexure Insurance Agency, Inc. to order coverage, I agree to be responsible for any minimum premium payable to the insurance company once coverage is bound.
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
					
				        </xsl:otherwise>
		                </xsl:choose>
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
						<xsl:if test="response/subproducerFormData_freeform_01 = '' ">
							<fo:table-row>
					           		<fo:table-cell number-columns-spanned="3" padding-top="5pt">
						           		
						                    <fo:block font-size="12px" text-decoration="underline" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"
																	>
						                     Form of Payment (Please check one)
						
						                    </fo:block>
					           		</fo:table-cell>
					           		
					           </fo:table-row>
				           </xsl:if>
						</fo:table-body>
					</fo:table>
					</fo:block>
						<xsl:if test="response/subproducerFormData_freeform_01 = '' ">
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
												<fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;Check made payable to:
											</fo:block>
									</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
									<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell padding-top="2pt" text-align="left" padding-left="50pt">
												<fo:block font-size="11px" font-family="Arial" >
													Protexure Insurance/MPA. 
												</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell  text-align="left" padding-left="50pt">
												<fo:block font-size="11px" font-family="Arial" >
													Mail to:
												</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell  text-align="left" padding-left="50pt">
												<fo:block font-size="11px" font-family="Arial" >
													PO Box 773197
												</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell  text-align="left" padding-left="50pt">
												<fo:block font-size="11px" font-family="Arial" >
													Detroit, Michigan 48277-3197
												</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
										<fo:block>  &#160;&#160;</fo:block>
									</fo:table-cell>
										<fo:table-cell  text-align="left" padding-left="50pt">
												<fo:block font-size="11px" font-family="Arial" >
													&#160;&#160;
												</fo:block>
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
						</xsl:if>
						<fo:block break-after="page" />
					
					 <fo:block text-align="left">				  	
				 	 	<fo:external-graphic src="../LawyersIns/image/ProtexureAgencyLogo.PNG" content-height="6em" content-width="30em"/>           	
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
						            Protexure Insurance Agency, Inc. (Protexure) specializes in the insurance needs of smaller law firms. Established in 2010, Protexure is your comprehensive small law firm professional liability coverage solution. 
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
						          For over 10 years Protexure Insurance Agency, Inc. has provided professional liability insurance to over 5,000 firms. Protexure Insurance Agency, Inc. and its founder, Kyle Nieman, have developed strong partnerships with quality insurance carriers.  Our program is designed to provide attorneys with quality coverage at an affordable price. 
						             </fo:block>
						            </fo:table-cell>
		   		 		</fo:table-row><fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 			<fo:table-cell><fo:block>&#160;</fo:block></fo:table-cell>
		   		 		</fo:table-row>
		   		 		<fo:table-row>
		   		 		<fo:table-cell>
   		 						<fo:block font-size="11px" font-family="Arial" text-align="justify">
						            At Protexure, we provide competitive and sustainable policy rates with the utmost professionalism that is among the best in the industry. Customer satisfaction begins with earning your trust. Our licensed professionals are here to partner with you every step of the way. 
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
						            For the latest in news and information, visit us on the web at www.protexurelawyers.com 
						             </fo:block>
						             </fo:table-cell>
		   		 		</fo:table-row>
		   		 	</fo:table-body>
		   		 </fo:table>  		 
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
				