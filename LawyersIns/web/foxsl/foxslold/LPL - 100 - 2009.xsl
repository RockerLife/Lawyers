<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template name="Policycoverletter1" match="/" >
				 	 <fo:block text-align="right">				  	
					 	<fo:external-graphic src="../LawyersIns/image/crum_logo.png" content-height="4em" content-width="16em"/>           	
					 </fo:block>
				    <fo:block>
				    <fo:table>
				    	 <fo:table-column column-width = "70mm" />
				        <fo:table-column column-width = "90mm" />
				        <fo:table-body>
				        
				        		 <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"   text-align="left">Policy Number: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"   text-align="left"></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  <fo:table-row>
					                    <fo:table-cell padding-top="4pt" padding-left="4pt">
					                    <fo:block font-weight="bold" font-family="Arial Narrow"  font-size="12px"   text-align="left">
					                    <xsl:choose>
										<xsl:when test="response/policy_freeform_01/data/StateCode= 'MT'">
											Note : Except as may be amended by endorsement
											to this policy Defense Expenses shall erode
											the each Claim and Policy Aggregate Limit of
											Liability available to pay Damages unless the
											Policy Aggregate is less than $1,000,000, in
											which case Defense Expenses do not begin to
											erode the Policy Aggregate until they exceed
											$50,000.									
										</xsl:when>
										<xsl:otherwise>
											
										</xsl:otherwise>
									</xsl:choose></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold"  text-align="left">
					                    <fo:table  text-align="center">
					    <fo:table-column column-width = "20mm" />
				        <fo:table-column column-width = "100mm" />				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="12px"   text-align="left"></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold"  font-size="11px"   text-align="left">INSURER:<fo:external-graphic src="../LawyersIns/img/space.gif"/> <fo:external-graphic src="../LawyersIns/image/boxcross.png"/><fo:external-graphic src="../LawyersIns/img/space.gif"/>  United States Fire Insurance Company</fo:block></fo:table-cell>
					    		  </fo:table-row>			    		 
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold"  font-size="11px"   text-align="left" text-indent="1.9cm"><fo:external-graphic src="../LawyersIns/image/nocross.jpg"/> <fo:external-graphic src="../LawyersIns/img/space.gif"/> The North River Insurance Company</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold"  font-size="11px"   text-align="left" text-indent="2.6cm">305 Madison Avenue</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold"  font-size="11px"   text-align="left" text-indent="2.6cm">Morristown, NJ  07962-1973</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>
				     </fo:block></fo:table-cell>
					    		  </fo:table-row>
				        
				        </fo:table-body>
				    </fo:table>
				    	   
				</fo:block>
				    
				    
				    
				  
                <fo:block margin-top="5mm" />
                 <xsl:choose>
                 	<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
                 	<fo:block font-weight="bold"  font-size="14px" text-align="center" >LAWYERS PROFESSIONAL LIABILITY - NORTH DAKOTA</fo:block>
                 </xsl:when>
                 <xsl:otherwise>
                 	<fo:block font-weight="bold"  font-size="14px" text-align="center" >LAWYERS PROFESSIONAL LIABILITY</fo:block>
                 </xsl:otherwise>
                 </xsl:choose>
                 
                 <fo:block font-weight="bold"  font-size="14px" text-align="center" id="1.1">PLATINUM PROTECTION</fo:block>
                
                
                <xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						<fo:block font-weight="normal" margin-top="5mm"   font-size="11px" text-align="left">
                 
                 NOTICE: THIS IS A CLAIMS MADE AND REPORTED POLICY. EXCEPT AS OTHERWISE PROVIDED HEREIN, THIS POLICY PROVIDES COVERAGE FOR <fo:inline font-weight="bold" font-style="italic">CLAIMS</fo:inline> FIRST MADE AGAINST THE <fo:inline font-weight="bold" font-style="italic">INSUREDS</fo:inline> AND REPORTED TO THE <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline> DURING THE <fo:inline font-weight="bold" font-style="italic">POLICY PERIOD</fo:inline>. <fo:inline font-size="14px" font-family="Arial Narrow" font-style="italic">PLEASE READ THIS POLICY CAREFULLY</fo:inline>. WORDS AND PHRASES WHICH ARE PRINTED IN <fo:inline font-weight="bold" font-style="italic">BOLD ITALIC TYPEFACE</fo:inline> HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OFTHE POLICY.  <fo:inline font-size="14px" font-family="Arial Narrow" font-style="italic">UNLESS STATED OTHERWISE BY SPECIFIC ENDORSEMENT</fo:inline>, <fo:inline font-size="14px" font-family="Arial Narrow" font-weight="bold" font-style="italic">DEFENSE EXPENSES</fo:inline> <fo:inline font-size="14px" font-family="Arial Narrow" font-style="italic">ARE INCLUDED IN THE LIMIT OF LIABILITY AND REDUCE THE LIMIT OF LIABILITY AVAILABLE TO PAY </fo:inline> <fo:inline font-family="Arial Narrow" font-size="14px" font-weight="bold" font-style="italic">DAMAGES</fo:inline>.</fo:block>
					</xsl:when>
                 	<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
                 		<fo:block font-weight="bold" margin-top="5mm"   font-size="12px" text-align="left">
                 		NOTICE: THIS IS A CLAIMS MADE AND REPORTED POLICY. EXCEPT AS OTHERWISE PROVIDED HEREIN, THIS POLICY PROVIDES COVERAGE FOR CLAIMS FIRST MADE AGAINST THE INSUREDS AND REPORTED TO THE INSURER DURING THE POLICY PERIOD. PLEASE READ THIS POLICY CAREFULLY. WORDS AND PHRASES WHICH ARE PRINTED IN BOLD ITALIC TYPEFACE HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OFTHE POLICY.  UNLESS STATED OTHERWISE BY SPECIFIC ENDORSEMENT, DEFENSE EXPENSES ARE INCLUDED IN THE LIMIT OF LIABILITY AND REDUCE THE LIMIT OF LIABILITY AVAILABLE TO PAY DAMAGES.</fo:block>
					</xsl:when>
					<xsl:otherwise>
						<fo:block font-weight="normal" margin-top="5mm"   font-size="11px" text-align="left">
                 
                 NOTICE: THIS IS A CLAIMS MADE AND REPORTED POLICY. EXCEPT AS OTHERWISE PROVIDED HEREIN, THIS POLICY PROVIDES COVERAGE FOR <fo:inline font-weight="bold" font-style="italic">CLAIMS</fo:inline> FIRST MADE AGAINST THE <fo:inline font-weight="bold" font-style="italic">INSUREDS</fo:inline> AND REPORTED TO THE <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline> DURING THE <fo:inline font-weight="bold" font-style="italic">POLICY PERIOD</fo:inline>. PLEASE READ THIS POLICY CAREFULLY. WORDS AND PHRASES WHICH ARE PRINTED IN <fo:inline font-weight="bold" font-style="italic">BOLD ITALIC TYPEFACE</fo:inline> HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OFTHE POLICY.  UNLESS STATED OTHERWISE BY SPECIFIC ENDORSEMENT, <fo:inline font-weight="bold" font-style="italic">DEFENSE EXPENSES</fo:inline> ARE INCLUDED IN THE LIMIT OF LIABILITY AND REDUCE THE LIMIT OF LIABILITY AVAILABLE TO PAY <fo:inline font-weight="bold" font-style="italic">DAMAGES</fo:inline>.</fo:block>
					</xsl:otherwise>
				</xsl:choose>
				
                
                
                
                 	
					
                <fo:block >
					<fo:table border="2pt solid black" text-align="center">
					    <fo:table-column column-width = "20mm" />
				        <fo:table-column column-width = "150mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt"><fo:block font-size="10px"   text-align="left">Item 1</fo:block></fo:table-cell>
					                    <fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt">
					                    	<fo:block font-weight="bold"  font-size="10px"   text-align="left">Named  Insured  &amp; Street Address: </fo:block>
					                    	<fo:block font-size="10px"   text-align="left">
					                    		<fo:table text-align="left">
					    							<fo:table-column column-width = "150mm" />
					    								<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-size="10px"   text-align="left"><xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               					    	</fo:table-row>
					               					    	<fo:table-row>
																<fo:table-cell>
																	<fo:block font-size="10px"   text-align="left"><xsl:value-of select="response/address_freeform_01/data/Address1" />
																		<xsl:if test="response/address_freeform_01/data/Address2!= ''"> 					    	
																	    	<fo:external-graphic src="../LawyersIns/image/spacer.png"/><xsl:value-of select="response/address_freeform_01/data/Address2" />  
																	    </xsl:if>
																	    
																	</fo:block>
																</fo:table-cell>
					               					    	</fo:table-row>
					               					    	
					               					    	<fo:table-row>
																<fo:table-cell>
																	<fo:block font-size="10px"   text-align="left">
																		<xsl:if test="response/address_freeform_01/data/City!= ''">
																			<xsl:value-of select="response/address_freeform_01/data/City"/>, 
																		</xsl:if>
																		<xsl:if test="response/address_freeform_01/data/StateDesc!= ''">
																			<xsl:value-of select="response/address_freeform_01/data/StateDesc"/><fo:external-graphic src="../LawyersIns/image/spacer.png"/>
																		</xsl:if>
																		<xsl:value-of select="response/address_freeform_01/data/Zip" />															
																	</fo:block>
																</fo:table-cell>
					               					    	</fo:table-row>
					               					    	
					               					    	<!--
					               					    	<fo:table-row>
																<fo:table-cell><fo:block font-size="10px"   text-align="left"><xsl:value-of select="response/address_freeform_01/data/City" /></fo:block></fo:table-cell>
					               					    	</fo:table-row>
					               					    	<fo:table-row>
																<fo:table-cell><fo:block font-size="10px"   text-align="left"><xsl:value-of select="response/address_freeform_01/data/StateDesc" /></fo:block></fo:table-cell>
					               					    	</fo:table-row>
					               					    	<fo:table-row>
																<fo:table-cell><fo:block font-size="10px"   text-align="left"><xsl:value-of select="response/address_freeform_01/data/Zip" /></fo:block></fo:table-cell>
					               					    	</fo:table-row>	
					               					    	 -->				               					    	
					    		  						</fo:table-body>
					    						</fo:table>					    						 
					                    	</fo:block>
					                    </fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt"><fo:block font-size="10px"   text-align="left">Item 2</fo:block></fo:table-cell>
					               		<fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt">
					               			 <fo:block font-size="10px"   text-align="left"><fo:inline font-weight="bold" font-style="italic">Policy Period:</fo:inline>  From  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  (Effective)    To  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" />  (Expiration)  </fo:block>
					               			 <fo:block font-size="10px"   text-align="center">(12:01 a.m. local time at the address shown in Item 1)</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		 
					    		 <fo:table-row>
					               		<fo:table-cell padding-bottom="4mm" border="2pt solid black" padding-left="4pt"><fo:block font-size="10px"   text-align="left">Item 3</fo:block></fo:table-cell>
					               		<fo:table-cell padding-bottom="4mm" border="2pt solid black" padding-left="4pt">
					               		<fo:block font-size="10px"   >
					               		<fo:block font-weight="bold" font-size="10px"   text-align="left">Limit of Liability</fo:block>  
					               		<fo:block margin-top="2mm"/> 
					         			<fo:table  text-align="center">
				    						<fo:table-column column-width = "30mm" />
			        		                <fo:table-column column-width = "31mm" />
			        		                 <fo:table-column column-width = "78mm" />
						                    <fo:table-body>
							    		        <fo:table-row>
						                    		<fo:table-cell  padding-left="4pt">
							                    		<fo:block font-size="10px"   text-align="center">
								                    		<fo:block text-align="left"><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText" /></fo:block>
								                    		<fo:block text-align="left"><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText" /></fo:block>
							                    		</fo:block>
						                    		</fo:table-cell>						                    		
						                    		<fo:table-cell padding-left="4pt">
							    		  		         <fo:block font-size="10px"   text-align="left">Each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>						    		  		       	
							    		  		       					    		  		       
							    		  		     	  </fo:block>							    		  		       
							    		  		         <fo:block font-size="10px"   text-align="left"><fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline></fo:block>
						    		  		        </fo:table-cell>  
						    		  		       <!--  
						    		  		        <fo:table-cell padding-left="4pt">
							    		  		         <fo:block font-size="10px"  text-align="left" >						    		  		       	
							    		  		       		<xsl:choose>
											                    <xsl:when test="response/policycoverage_freeform_01/data/IsClaimExpensesType='Y'">
											                     (Claim Expenses capped at the Limit of Liability)
				        								        </xsl:when>											                   
											                </xsl:choose>			    		  		       
							    		  		     	  </fo:block>	
						    		  		        </fo:table-cell> 
						    		  		        -->
					    		  				</fo:table-row>
					    		  			</fo:table-body>
				   						</fo:table>
					               		<fo:block text-align="left">These amounts include <fo:inline font-weight="bold" font-style="italic">Defense Expenses </fo:inline>unless this Section is amended by specific endorsement of this policy.</fo:block>
					               		</fo:block>
					              	    </fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-size="10px"   text-align="left">Item 4</fo:block></fo:table-cell>
					               		<fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm">
						               		
						               		<fo:block font-size="10px"   >
					               		<fo:block font-weight="bold" font-size="10px"   text-align="left">Deductible:</fo:block>  
					               		<fo:block margin-top="2mm"/> 
					         			<fo:table  text-align="center">
				    						<fo:table-column column-width = "30mm" />
			        		                <fo:table-column column-width = "31mm" />
						                    <fo:table-body>
							    		        <fo:table-row>
						                    		<fo:table-cell  padding-left="4pt">
							                    		<fo:block font-size="10px"   text-align="center">
								                    		<fo:block text-align="left"><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount" /></fo:block>
								                    		
							                    		</fo:block>
						                    		</fo:table-cell>
						                    		<fo:table-cell padding-left="4pt">
						                    			<fo:block font-size="10px"   text-align="left">Each<fo:inline font-weight="bold" font-style="italic"> Claim</fo:inline></fo:block>
						                    		<!--  
							    		  		         <fo:block font-size="10px"   text-align="left">
							    		  		         	<xsl:choose>
											                    <xsl:when test="response/policycoverage_freeform_01/data/IsClaimOptionType='Y'">
											                        <fo:inline font-weight="bold" font-style="italic">Annual Aggregate</fo:inline>
				        								        </xsl:when>
											                    <xsl:otherwise>
											                        <fo:inline font-weight="bold" font-style="italic">Per Claim</fo:inline>
				        								        </xsl:otherwise>
											                </xsl:choose>
							    		  		         </fo:block>	
							    		  		         -->						    		  		         
						    		  		        </fo:table-cell>  
					    		  				</fo:table-row>
					    		  			</fo:table-body>
				   						</fo:table>
				   						<fo:block-container position="absolute" top="48pt" left="30pt" width="100%">
							              	   	<fo:block>
							              	   		<xsl:if test="response/policy_freeform_01/data/StateCode= 'NY'">
														<fo:external-graphic src="../LawyersIns/image/free_trade_zone_stamp.png"/>
							  				  		</xsl:if>
							  				  	</fo:block>
						  				  	</fo:block-container>
					               		<fo:block text-align="left">This amount applies to <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this policy.
	 											This amount applies to each <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>unless this Section is amended by specific endorsement of this policy.</fo:block>
					               		</fo:block>
						               		
						               		
						               						              	    
					              	    </fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-size="10px"   text-align="left">Item 5</fo:block></fo:table-cell>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm">
					               	<fo:block font-weight="bold"  font-size="10px"   text-align="left">Premium:
					               		<xsl:value-of select="response/policycoverage_freeform_01/data/TotalPremium" /> 
					               		
					               		<xsl:if test="response/firm_freeform_01/data/IsTaxCalculation !='Y'">
					               				
					               					
					               		
					               						<xsl:if test="response/policycoverage_freeform_01/data/MTTaxAmmount != '0'">
						             							+ <xsl:value-of select="response/policycoverage_freeform_01/data/MTTaxAmmount" /> (<xsl:value-of select="response/policycoverage_freeform_01/data/MTTaxPercentage" /> %, Municipal Tax) 
						             					</xsl:if>
						             	
						             				
						             	
						             	</xsl:if>
						             	
						             	<xsl:if test="response/policycoverage_freeform_01/data/CountyTaxAmmount != '0'">
						             		 + <xsl:value-of select="response/policycoverage_freeform_01/data/CountyTaxAmmount" /> (<xsl:value-of select="response/policycoverage_freeform_01/data/CountyTaxPercentage" /> %, County Tax) 
						             	</xsl:if>
						             	<xsl:if test="response/policycoverage_freeform_01/data/State1TaxAmmount != '0'">
						             		+ <xsl:value-of select="response/policycoverage_freeform_01/data/State1TaxAmmount" /> (<xsl:value-of select="response/policycoverage_freeform_01/data/State1Percentage" /> %, State1 Tax) 
						             	</xsl:if>
						             	<xsl:if test="response/policycoverage_freeform_01/data/State2TaxAmmount != '0'">
						             		+ <xsl:value-of select="response/policycoverage_freeform_01/data/State2TaxAmmount" /> (<xsl:value-of select="response/policycoverage_freeform_01/data/State2Percentage" /> %, State2 Tax) 
						             	</xsl:if>						             				               		
					               	</fo:block>
					               	<fo:block font-size="10px"  text-align="left">
					               	 for the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>
					               	</fo:block>
					               </fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		  <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-size="10px"   text-align="left"></fo:block></fo:table-cell>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-weight="bold"  font-size="10px"   text-align="left">Total: <xsl:value-of select="response/policycoverage_freeform_01/data/InvoicedPremium" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="6mm"><fo:block font-size="10px"   text-align="left">Item 6</fo:block></fo:table-cell>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="6mm"><fo:block font-weight="bold"  font-size="10px"   text-align="left">Prior Acts<xsl:if test="response/professionalliabilityinsdetail_freeform_01/data/IsPriorActDateFull= 'N'"> Date</xsl:if><xsl:if test="response/policy_freeform_01/data/IsFirmCarryingProfLiabilityIns != 'Y'"> Date</xsl:if>: 
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
									    </xsl:if></fo:block></fo:table-cell>
					    		 </fo:table-row>
					    		 <!--  
					    		 <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-size="10px"   text-align="left">Item 7</fo:block></fo:table-cell>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-weight="bold"  font-size="10px"   text-align="left">Optional Extended Reporting Period:</fo:block>
					                 <fo:block  font-size="10px"   text-align="left">(a) 12  Months                   (b) Additional Premium: 100 % of the annual premium</fo:block>
					               </fo:table-cell>
					    		 </fo:table-row>
					    		 -->
					    		 <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm">
					               <fo:block font-size="10px"   text-align="left" padding-bottom="4mm">Item 7</fo:block>
					               </fo:table-cell>
					               
					               <fo:table-cell border="2pt solid black" padding-left="4pt">
					                    <fo:block font-size="10px"   text-align="left">The following endorsements, if any, are made a part of this policy at issuance:</fo:block>
					                    <fo:block font-weight="bold"  font-size="10px"   text-align="left"><xsl:value-of select="response/Endorsements"/>
					                    , MI 07 001 01 15
					                   <!--  <xsl:if test="response/policy_freeform_01/data/StateCode= 'MI'">
														, FM 303.0.26 02 14
										</xsl:if>	 -->				                    
					                    </fo:block>
					               </fo:table-cell>
					    		 </fo:table-row>
					    		 
					    		
					    </fo:table-body>
				   </fo:table>
				</fo:block>	    	
					 
			 	<fo:block margin-top="1mm" font-size="10px" text-align="left">These Declarations, the application, and the policy with endorsements attached thereto, constitute the entire agreement between the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> and the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>.</fo:block>
				<fo:block  margin-top="1mm"> 
					<fo:table>
					    <fo:table-column column-width = "120mm" />
				        <fo:table-column column-width = "70mm" />				       
				        <fo:table-body>
			    		  <fo:table-row>
			                    <fo:table-cell padding-bottom="4mm" ><fo:block font-size="10px"   text-align="left">Countersigned (if required by law):__________________</fo:block></fo:table-cell>
			                    <fo:table-cell padding-bottom="4mm" ><fo:block font-size="10px"   text-align="left">Date:__________________</fo:block></fo:table-cell>
			              </fo:table-row>
					    </fo:table-body>
				</fo:table>
				</fo:block>				
				
				
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
										
					
				
				<fo:block break-after="page" />
				
				<fo:block margin-top="4mm"/>
				<fo:block font-weight="bold" font-size="10px" text-align="center" >LAWYERS PROFESSIONAL LIABILITY</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" >In consideration of the payment of premium, the undertaking of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> to pay the Deductible herein, and in reliance (attached hereto) and supplements (attached hereto) upon the application and all the information provided to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, and subject to the Declarations, definitions, terms, conditions, limitations, representations, exclusions and endorsements herein and/or attached hereto, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> and the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> agree as follows:</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" border="2pt solid black" font-size="12px" text-align="left" ><fo:external-graphic src="../LawyersIns/image/spacer.png" />I.		INSURING AGREEMENT </fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >A. Coverage</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >Subject to the Limit of Liability stated in Item 3. of the Declarations, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall pay on behalf of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> all <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> in excess of the Deductible as stated in Item 4. of the Declarations that the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> becomes legally obligated to pay as a result of a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> first made against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and reported in writing to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> or during any applicable Extended Reporting Period, by reason of a negligent act, error or omission in the performance of <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or by someone for whom the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> is legally responsible.</fo:block>
				  
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >B. Defense</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >Subject to the terms, conditions and exclusions appearing in other Sections of this policy, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> has the right and duty to defend any covered <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> including, but not limited to, the appointment of legal counsel, even if any of the allegations of the <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> are groundless, false or fraudulent.</fo:block>
				
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" border="2pt solid black" font-size="12px" text-align="left" ><fo:external-graphic src="../LawyersIns/image/spacer.png" />II.		LIMITS OF LIABILITY AND DEDUCTIBLE</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >A.	Limits of Liability</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.	Subject to 2 that follows, the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> Limit of Liability for <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> combined, for each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> first made and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amount shown in Item 3 of the Declarations as applicable to “each <fo:inline font-weight="bold" font-style="italic">Claim”</fo:inline>.</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.	Subject to 1 above, the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> Limit of Liability for <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> combined, for all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> first made and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amount shown in Item 3 of the Declarations as applicable to <fo:inline font-weight="bold" font-style="italic">“Policy Aggregate”</fo:inline>.</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.	<fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> are included within, and shall reduce, the applicable Limit of Liability available to pay <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>.</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm">4.	The Limit of Liability shall apply excess of the Deductible amount.</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm">5.	All <fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline> shall be deemed a single <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> and such single <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> shall have been deemed to have been made on the date the earliest of such <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> was first made against an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, regardless of whether such date is before or during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> and shall be subject to the Limit of Liability as shown in Item 3 of the Declarations as applicable to “each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>”.</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm">6.	The Limit of Liability available for <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> first made against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during any applicable  Extended Reporting Period, is part of, and not in addition to the Limit of Liability stated in Item 3 of the Declarations. Applicable Extended Reporting Periods shall not provide a new, additional or renewed Limit of Liability. </fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm">7.	If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> has exhausted the applicable Limit of Liability by payment of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and/or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> or by tender of the remaining Limit of Liability into court,  it shall have no further duties to the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> under this policy.</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >B.	Deductible</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4 in the Declarations. This Deductible amount shall apply separately to each and every <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> and shall be borne by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and shall remain uninsured. The Deductible amount applies to the payment of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>. If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> advances any amounts within the Deductible, the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> within thirty days of the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> request to do so.  In the event of <fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline>, a single Deductible amount will apply.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >C.	Multiple Insureds, Claims and Claimants</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >Regardless of the number of <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline>, <fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline>, <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> or claimants, the Limit of Liability as shown in Item 3 of the Declarations as applicable to “each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> “ and to “<fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline>” shall be subject to  Paragraph A of this Section II.</fo:block>
				
				
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>		
				
				
				
				<fo:block break-after="page" />
			
			     <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" border="2pt solid black" font-size="12px" text-align="left" ><fo:external-graphic src="../LawyersIns/image/spacer.png" />III.		EXTENSIONS OF COVERAGE</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block  font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >A.	Estates, Heirs, Bankruptcy Extension</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >In the event of the death or incapacity of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, or the bankruptcy of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> made against any heir, executor, administrator, assignee or legal representative of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or against any trustee in bankruptcy of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, which arises from any real or alleged negligent act, error or omission of such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, shall be deemed to be a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> made against such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> for the purposes of this policy. Bankruptcy or insolvency of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or of the <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> estate will not relieve the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> of any of its obligations hereunder.</fo:block>
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >B.	Spousal And Domestic Partner Extension </fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >If a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is made against the lawful spouse or lawful domestic partner of an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> which includes a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> for a negligent act, error or omission made against an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> rendering Professional Services, then such <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> shall be deemed a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> made against such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, provided his or her lawful spouse or lawful domestic partner accepts the same legal counsel as the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and that such <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is made solely by reason of such lawful spouse’s or lawful domestic partner’s status as such. This extension, however, shall not apply to any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> alleging any negligent act, error or omission committed or alleged to have been committed by the lawful spouse or lawful domestic partner of an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>.</fo:block>
				
				
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >C.	Personal Injury and Advertising Liability Extensions</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >Subject to all other terms, conditions and exclusions, this policy covers <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> becomes legally obligated to pay resulting from <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> arising out of <fo:inline font-weight="bold" font-style="italic">Personal Injury</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Advertising Liability</fo:inline>.</fo:block>
				
				
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >D.	Extended Reporting Period Extensions</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1.5cm" font-size="12px" text-align="left" >1.	Automatic Extended Reporting Period</fo:block>
				
				 <fo:block font-size="10px" start-indent="2cm" text-align="left" >Upon the expiration of this policy for any reason other than for cancellation for nonpayment of premium or for nonpayment of Deductible due hereunder, the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall be provided with an automatic and non-cancelable period of sixty days, commencing on the policy expiration date, to report <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to Section VI. A. of General Conditions, Notice of <fo:inline font-weight="bold" font-style="italic">Claim or Circumstance</fo:inline>.  Coverage under this extension of time to report a <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>(hereinafter referred to as the Automatic Extended Reporting Period) shall apply solely to negligent acts, errors or omissions in rendering <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> committed or attempted prior to the effective date of nonrenewal or cancellation, whichever occurs first, and which are not otherwise excluded by any terms, conditions or exclusions of this policy. This Automatic Extended Reporting Period shall not be applicable, however, in the event the<fo:inline font-weight="bold" font-style="italic"> Named Insured</fo:inline> has obtained another policy of Lawyers Professional Liability insurance with an inception date as of the termination date of this policy.</fo:block>
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1.5cm" font-size="12px" text-align="left" >2.	Optional Extended Reporting Period</fo:block>
				 <fo:block margin-top="2mm"/>				 
				 <fo:block font-size="10px" start-indent="2cm" text-align="left" >Upon the expiration or cancellation of this policy for any reason other than for nonpayment of premium or for the nonpayment of Deductible due hereunder, the <fo:inline font-weight="bold" font-style="italic">Named Insured </fo:inline>shall have the right, upon payment of the additional premium designated in one of the options below for the designated length of time shown, commencing on the expiration date of the Automatic Extended Reporting Period, to report<fo:inline font-weight="bold" font-style="italic"> Claims</fo:inline> pursuant to Section VI.A. of General Conditions, Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance:
				 </fo:block>
				 <fo:block margin-top="1mm"/>
				 
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(a) Optional Extended Reporting Period of 12 months for a premium charge of 100% of the annual policy premium;
				 </fo:block>
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(b) Optional Extended Reporting Period of 36 months for a premium charge of 185% of the annual policy premium;
				 </fo:block>
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(c) Optional Extended Reporting Period of 60 months for a premium charge of 225% of the annual policy premium;
				 </fo:block>
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(d) Optional Extended Reporting Period of 72 months for a premium charge of 250% of the annual policy premium
				 </fo:block>				 
				 
				 <fo:block margin-top="1mm"/>
				 <fo:block font-size="10px" start-indent="2cm" text-align="left" >Coverage under such extension of time to report a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> (hereinafter referred to as the Optional Extended Reporting Period) shall apply solely to negligent acts, errors or omissions in rendering <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> committed or attempted prior to the effective date of nonrenewal or cancellation, whichever occurs first, and which are not otherwise excluded by any terms, conditions or exclusions of this policy.
				 </fo:block>
				 
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				 <fo:block break-after="page"></fo:block>
				 
				 <fo:block margin-top="2mm"/>				 
				 <fo:block font-size="10px" start-indent="2cm" text-align="left" >As a condition precedent to the right to purchase this Optional Extended Reporting Period:
				 </fo:block>
				 <fo:block margin-top="1mm"/>
				 
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(a)  the total premium and Deductible amounts  for this policy must have been paid, and
				 </fo:block>
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(b)  all<fo:inline font-weight="bold" font-style="italic"> Insureds </fo:inline> must be  in compliance with the terms and conditions of the policy, and
				 </fo:block>
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(c)  the <fo:inline font-weight="bold" font-style="italic">Named Insured’s</fo:inline> right to practice law has not been revoked, suspended or surrendered at the request of any regulatory authority for reasons other than death, disability or retirement,  and

				 </fo:block>
				 <fo:block font-size="10px" start-indent="4cm" text-align="left" >(d)  the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> provides the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> with written notice of its selection  and pays the premium charge for the  selected Optional Reporting Period in full within sixty days of the expiration date of  the <fo:inline font-weight="bold" font-style="italic">Policy Period. </fo:inline>
				 </fo:block>				 
				 
				 <fo:block margin-top="1mm"/>
				 <fo:block font-size="10px" start-indent="2cm" text-align="left" >If the Optional Extended Reporting Period requested by the<fo:inline font-weight="bold" font-style="italic"> Named Insured </fo:inline>is effected by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, the additional premium shall be fully earned by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> and the Optional Extended Reporting Period cannot be cancelled by the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> or the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>. This right to purchase the Optional Extended Reporting Period shall lapse unless the provisions of (a) through (d) in the preceding paragraph are fully met.
				 </fo:block>
				 
				 				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1.5cm" font-size="12px" text-align="left" >3.	Non – Practicing Insured  Extended Reporting Period</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="2cm" text-align="left" >(a)	Provided this policy (i) has not been cancelled for nonpayment of premium or Deductible and (ii) no other Extended Reporting Period is in effect and (iii) this policy remains in force as to all other <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline>, except in the case where all <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> are <fo:inline font-weight="bold" font-style="italic">Non – Practicing Insureds</fo:inline>, a <fo:inline font-weight="bold" font-style="italic">Non - Practicing  Insured</fo:inline> shall have the right to purchase an unlimited extension of time to report <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> that are first made against such <fo:inline font-weight="bold" font-style="italic">Non – Practicing  Insured</fo:inline> and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> after the effective date of cessation of the practice of law by reason of negligent acts, errors or omissions alleged to have been committed subsequent to the applicable <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline>, but before the effective date of cessation of the practice of law and not otherwise excluded by the agreements, conditions and exclusions of this policy. The additional premium for this <fo:inline font-weight="bold" font-style="italic">Non-Practicing Insured</fo:inline>  Extended Reporting Period shall be 125% of the <fo:inline font-weight="bold" font-style="italic">Non - Practicing Insured’s</fo:inline>  proportionate share of the annual premium (or, 275% of the annual premium in the case where all <fo:inline font-weight="bold" font-style="italic">Insureds </fo:inline>are <fo:inline font-weight="bold" font-style="italic">Non – Practicing Insureds)</fo:inline> and is due and payable to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> no later than thirty days after the effective date of cessation of the practice of law. </fo:block>
				  <fo:block margin-top="1mm"/>
				  <fo:block font-size="10px" start-indent="2cm" text-align="left" >(b) 	The additional premium charges  in (a) above shall be waived for<fo:inline font-weight="bold" font-style="italic"> Non – Practicing  Insureds </fo:inline>who have been insured for their Professional Services by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for at least three consecutive annual Policy Periods prior to becoming a<fo:inline font-weight="bold" font-style="italic"> Non- Practicing  Insured</fo:inline>. </fo:block>
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >E.	Deductible Credit for Mediation Extension</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reduce the applicable Deductible amount by 50% in respect of any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>  resolved in its entirety by mediation.</fo:block>
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >F.	Defendant Reimbursement Extension</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> $100 per hour for the time such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> attends a trial, court hearing, mediation or  arbitration proceeding in connection with a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>, when such attendance is at the at the request of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.</fo:block>
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >G.	Regulatory Inquiry Extension</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >If a regulatory body, state licensing board, public oversight board or government agency having regulatory authority over the <fo:inline font-weight="bold" font-style="italic">Insured’s Professional Services</fo:inline>, first initiates an investigation of any <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> which arises from <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> of such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> rendered subsequent the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> and, such regulatory inquiry is reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to Section VI. General Conditions, A., Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstances, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> for attorney fees, court and regulatory body costs incurred in responding to such inquiry, up to a maximum reimbursement of $12,500. This is the maximum amount the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse, regardless of the number of such inquiries or of the number of <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> involved in such inquiries, for all inquiries first initiated against the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> during the policy and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to Section VI. General Conditions, A. Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance.</fo:block>
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >H.	Subpoena Assistance Extension</fo:block>
				 <fo:block margin-top="2mm"/>
				 
				
				 
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >If during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>, an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> first receives a subpoena for documents or testimony as a fact witness arising from <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> rendered by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> in whole subsequent to the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> and such receipt of a subpoena is reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to Section VI. General Conditions A.. Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance, then provided that (a) said subpoena arises out of a matter or lawsuit to which an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> is not a party and (b) provided no <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> has been engaged to provide professional advice or testimony in connection with the matter or lawsuit at any previous time, then the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will retain an attorney to provide advice to the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> regarding the production of documents, to prepare the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> for sworn testimony and to represent the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> at depositions.</fo:block>
				  
				  
				 <fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				 
				 
				  <fo:block break-after="page"></fo:block>
				
				
				
				
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >I.	Client Discrimination Extension</fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" >If during the<fo:inline font-weight="bold" font-style="italic"> Policy Period</fo:inline>, allegations are made against an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> by a client, or potential client, that any <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>refused to 
                           perform<fo:inline font-weight="bold" font-style="italic"> Professional Services</fo:inline> for said client or potential client due to discrimination, and such allegations are reported to the
                          <fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> pursuant to Section VI. General Conditions, A. Notice of Claim or Circumstance, then provided the allegations did not 
                           arise out of such<fo:inline font-weight="bold" font-style="italic"> Insured’s</fo:inline> intentional disregard or willful failure to comply with any state or federal laws or regulations governing 
                           discriminatory practices, the Insurer will reimburse the Named Insured  solely<fo:inline font-weight="bold" font-style="italic"> Defense  Expenses</fo:inline> incurred by the<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> up to
                           a maximum reimbursable  amount of $15,000 for for the entire <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>  for all such allegations, regardless of the number 
                           of clients or potential clients making such allegations.   The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall provide its consent, not to be unreasonably withheld, to 
                           the<fo:inline font-weight="bold" font-style="italic"> Named Insured’s</fo:inline> choice of counsel.  This policy shall not provide coverage for Damages resulting from such allegations.
						</fo:block>
						
				
				 <fo:block margin-top="2mm"/>
			<fo:block font-size="10px" start-indent="1cm" text-align="left" > <fo:inline font-weight="bold" font-size="12px" start-indent="1.5cm">	 J.  </fo:inline>  <fo:inline font-weight="bold" font-style="italic">   Disciplinary Proceedings</fo:inline> are not <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> under this policy. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not have the right and </fo:block>
			<fo:block font-size="10px" start-indent="1.5cm" text-align="left"> shall not have the 
            duty to defend a <fo:inline font-weight="bold" font-style="italic">Disciplinary Proceeding</fo:inline> against any <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>.  It shall be the right and duty of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> to respond to or 
            defend such <fo:inline font-weight="bold" font-style="italic">Disciplinary Proceedings</fo:inline>.   In the event violations of disciplinary rules or other professional misconduct alleged in a 
            <fo:inline font-weight="bold" font-style="italic">Disciplinary Proceeding</fo:inline> are not proven by a final and enforceable determination by a tribunal of competent jurisdiction adverse in 
            whole to an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>  and, such violations or misconduct is not admitted by an<fo:inline font-weight="bold" font-style="italic"> Insured </fo:inline>, then the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse the 
           <fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> for reasonable fees, costs and expenses incurred by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> in the defense of such <fo:inline font-weight="bold" font-style="italic">Disciplinary Proceeding</fo:inline> 
            up to a maximum reimbursement of $25,000 in total for all  <fo:inline font-weight="bold" font-style="italic">Disciplinary Proceedings </fo:inline>reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during the<fo:inline font-weight="bold" font-style="italic"> Policy 
            Period </fo:inline>pursuant to Section VI., General Conditions, A., Notice of<fo:inline font-weight="bold" font-style="italic"> Claim </fo:inline>or Circumstance.</fo:block>

						
				 
				 
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-weight="bold" border="2pt solid black" font-size="12px" text-align="left" ><fo:external-graphic src="../LawyersIns/image/spacer.png" />IV.		DEFINITIONS </fo:block>
				 <fo:block margin-top="2mm"/>
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Advertising Liability</fo:inline> means legal obligations the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> incurs arising out of the marketing and promotion of the <fo:inline font-weight="bold" font-style="italic">Insured’s Professional Services</fo:inline> by reason of (a) the oral or written publication of material which slanders or libels an individual or entity, or which disparages its goods, services or products (b) the misappropriation of marketing or promotion ideas or styles of business of others, or (c) the infringement of titles or slogans of others. </fo:block>
				  <fo:block margin-top="2mm"/>
				  <fo:block font-size="10px" text-indent="1.5cm" text-align="left" ><fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>means: </fo:block>
				  <fo:block font-size="10px" text-indent="2.5cm" text-align="left" >(a)	a demand or civil proceeding seeking <fo:inline font-weight="bold" font-style="italic">Damages,</fo:inline> or</fo:block>
 				   <fo:block font-size="10px" text-indent="2.5cm" text-align="left" >(b)  	service of suit seeking<fo:inline font-weight="bold" font-style="italic"> Damages</fo:inline>, or</fo:block>
 				  <fo:block font-size="10px" text-indent="2.5cm" text-align="left">(c)  	institution of alternative dispute proceedings seeking<fo:inline font-weight="bold" font-style="italic"> Damages</fo:inline>, or</fo:block>
 				  <fo:block font-size="10px" text-indent="2.5cm" text-align="left">(d)	a demand for services.</fo:block>
				 
				 
				 
				 <fo:block margin-top="2mm"/>
				 <fo:block font-size="10px" start-indent="1.5cm" text-align="left" ><fo:inline font-weight="bold" font-style="italic">Damages </fo:inline>means a monetary judgment (including pre- and post- judgment interest awarded against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>), monetary award or monetary settlement negotiated with the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> written consent. If the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> makes an offer to pay the applicable Limit of Liability, it will not pay any prejudgment interest based on the period of time after such offer is made.</fo:block>
				  <fo:block margin-top="2mm"/>
				 <fo:block font-size="10px" text-align="left" text-indent="1.8cm"><fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> shall not include: </fo:block>
				 
				 <fo:block font-size="10px" text-align="left" start-indent="2.5cm">(a)	any restitution, disgorgement, unjust enrichment or illegal profits by an <fo:inline font-weight="bold" font-style="italic">Insured;</fo:inline> </fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="2.5cm">(b)	return or offset of fees or over-charges or amounts which are the subject of fee disputes;</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="2.5cm">(c)	punitive or exemplary damages, awards or judgments or any amounts which are a multiple of compensatory damages, awards or judgments, except to the extent insurance for such damages, awards or judgments is insurable under applicable law and is not otherwise excluded by the provisions of this policy. For the purposes of determining whether such damages are insurable, the law of the state of incorporation or principal place of business of the<fo:inline font-weight="bold" font-style="italic"> Insured </fo:inline>or the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline>, the state where the negligent act, error or omission took place, or the state where the damages are awarded or imposed, whichever is most favorable to the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, shall be deemed applicable law; </fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="2.5cm">(d)	civil or criminal fines, sanctions or penalties;</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="2.5cm">(e)	any amounts for which the Insured  is not  financially liable or for which there is no legal recourse against the Insured;</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="2.5cm">(f)	subject to (c) above,  amounts deemed uninsurable under the law pursuant to which this policy shall be construed;</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(g)	amounts paid to comply with any injunctive order or other non–monetary or declaratory relief or award, including amounts ordered to be paid to comply with specific performance or any agreement to provide such relief. </fo:block>
				
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				
				<fo:block break-after="page"></fo:block>
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Defense Expenses </fo:inline>mean reasonable and necessary fees charged by attorneys designated or approved by the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> and all other reasonable and necessary fees, costs and expenses resulting from the adjustment, negotiation, arbitration, mediation, defense or appeal of a<fo:inline font-weight="bold" font-style="italic"> Claim</fo:inline>, including premiums on appeal, attachment or similar bonds; provided, however, that this provision does not obligate the Insurer to apply for or furnish any such bond.<fo:inline font-weight="bold" font-style="italic"> Defense Expenses </fo:inline>include amounts payable by the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> under Section III., Extensions of Coverage, paragraphs F. through J., inclusive.<fo:inline font-weight="bold" font-style="italic"> Defense Expenses </fo:inline>do not include salaries, charges, wages, loss of wages or expenses of any partner, principal, director, officer, member or employee of the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or the <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline>other than as provided in Section III Extensions of Coverage, paragraphs F. through J. inclusive.</fo:block>
				<fo:block margin-top="2mm"/>
				 
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Disciplinary Proceeding</fo:inline> means a forum in which a complaint alleging violation of any professional rule or professional misconduct is brought before a tribunal of competent jurisdiction which shall make a determination subject to appeal or other review and/or a final and enforceable determination as to whether such alleged rules or misconduct is to be the subject of discipline.</fo:block>
				<fo:block margin-top="2mm"/>
				 
				 
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Immediate Family</fo:inline> means the parents, children, grandchildren, brothers, sisters or past or present spouse of any past or present Insured.</fo:block>
				<fo:block margin-top="2mm"/>
				 
				 
				 
				 <fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> means the <fo:inline font-weight="bold" font-style="italic">Named  Insured</fo:inline>,  Predecessor Firm, and:</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(a)	any present or future principal, partner, director, officer, member or employee of the<fo:inline font-weight="bold" font-style="italic"> Named Insured</fo:inline> ;</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(b)	any former principal, partner, director, officer, member or employee of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or <fo:inline font-weight="bold" font-style="italic">of a Predecessor Firm; </fo:inline>       
				</fo:block>				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(c)	the estate, heirs, executors, administrators, assigns and legal representatives of an<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline>  but only in the event of such 
	Insured’s death, incapacity, insolvency or bankruptcy, and only to the extent that such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> would otherwise have been 
	provided coverage under the terms, conditions and exclusions of this policy;</fo:block>				
	<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(d)	any contract or temporary employee of a Named Insured under the direct supervision of an Insured;</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(e)	any lawyer acting as “of counsel”;</fo:block>
				 
				 <fo:block margin-top="2mm"/>
				
			 	<fo:block font-size="10px" text-align="left" start-indent="1.5cm">but only with respect to <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> performed within the scope of their duties on behalf of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline>.</fo:block>
				<fo:block margin-top="2mm"/>
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> means the insurance company named in the Declarations.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">Named Insured means the entity(ies), individual, partnership or corporation stated in Item 1. of the Declarations.</fo:block>
				<fo:block margin-top="2mm"/>
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Non-Practicing Insured </fo:inline>means an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> who on a specific date becomes disabled, has died or has otherwise ceased the practice of law for reasons other than a revocation, suspension or surrender of license at the request or demand of any judicial or regulatory authority.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Personal Injury</fo:inline> means the following which arise from the<fo:inline font-weight="bold" font-style="italic"> Insured’s Professional Services:</fo:inline> (a) false arrest, detention or imprisonment; malicious prosecution (b) the publication or utterance of a libel or slander or other defamatory or disparaging statement or disparaging material (c) a publication or utterance in violation of a person’s right of privacy (d) the wrongful eviction of a person from a residence (e) wrongful entry into, or invasion of the right of private occupancy.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Policy Period </fo:inline>means the length of time between the effective date shown in Item 2. of the Declarations and the earlier of (a) the expiration date shown in Item 2. of the Declarations, or (b) the cancellation date of this policy.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline> means the amount identified in Item 3. of the Declarations which represents the maximum amount of the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> liability for all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline>, all <fo:inline font-weight="bold" font-style="italic">Damages </fo:inline>and all<fo:inline font-weight="bold" font-style="italic"> Defense Expenses </fo:inline>combined under this policy, inclusive of any applicable Extended Reported Period, if purchased.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline> means an individual, partnership, professional corporation, professional association, limited liability corporation or partnership which was at all times engaged in<fo:inline font-weight="bold" font-style="italic"> Professional Services</fo:inline> and to whose financial assets and liabilities the<fo:inline font-weight="bold" font-style="italic"> Named  Insured</fo:inline>  became  the majority successor in interest prior to the effective date as stated in Item 2. of  the Declarations and which is named as such by specific endorsement to this policy.  <fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline> does not include any individual, partnership, professional corporation, professional association, limited liability corporation or partnership which was at all times engaged in <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> and to whose financial assets and liabilities the<fo:inline font-weight="bold" font-style="italic"> Named Insured</fo:inline> becomes the majority successor in interest subsequent to the effective date of this policy as stated in Item 2. of the Declarations unless the <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline>at its sole discretion agrees to include such entity.  Should the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> agree to include such entity it may do so for an additional premium and/or with amended policy terms and conditions.</fo:block>
				<fo:block margin-top="2mm"/>
				
				
				
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> means the date shown in Item 6. of the Declarations.</fo:block>
				<fo:block margin-top="2mm"/>
				
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				
				<fo:block break-after="page"></fo:block>
				
				
				
				<fo:block font-size="10px" text-align="left" text-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> means</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(a)	those service performed  for a client in the<fo:inline font-weight="bold" font-style="italic"> Insured’s</fo:inline> capacity as a lawyer for a monetary fee, and</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(b)	those services as an arbitrator, mediator or notary public for a monetary fee, and </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(c)	those services performed as a title agent for a client which are incidental to services performed as a lawyer for the client for a monetary fee, and</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(d)	pro bono services of an <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>if at the time such services were rendered, they were approved by a partner, director or officer of the <fo:inline font-weight="bold" font-style="italic">Named Insured </fo:inline> to perform such services without compensation, and</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(e)	those services as a member of a formal accreditation, standards review or similar professional board or committee solely related to the profession of the practice of law, but only when such formal accreditation, standards review or similar professional board or committee solely related to the profession of the practice of law does not indemnify the <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>or have insurance coverage applicable to the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> in respect of such services.</fo:block>
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline> means all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> arising from negligent acts, errors, omissions that have as a common nexus any fact, circumstance, situation, transaction, event or cause or series of casually connected facts, circumstances, situations, transactions, events or causes.</fo:block>
				<fo:block margin-top="2mm"/>
				
								
				<fo:block font-weight="bold" font-size="12px" border="2pt solid black" text-align="left" ><fo:external-graphic src="../LawyersIns/image/spacer.png" />V.		EXCLUSIONS </fo:block>
				<fo:block margin-top="4mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">This policy does not apply to any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> based upon or arising out of:</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"> <fo:inline font-size="12px" font-weight="bold">  A.</fo:inline>  	a dishonest, intentional, fraudulent, criminal or malicious act or omission or any intentional or knowing violation of law by any <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>.  However, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will provide the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> with a defense of such <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> and pay <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> for any such <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>until there is a judgment, final adjudication or adverse admission by an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or a finding of fact against an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> as to such conduct;. However, this exclusion shall not apply to an Insured who, in fact, did not personally commit, direct or participate in committing a dishonest, intentional, fraudulent, criminal or malicious act or omission or any intentional or knowing violation of law.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"> <fo:inline font-size="12px" font-weight="bold">B.</fo:inline>	(a)  	physical injury, sickness, disease or the death of any person including mental anguish or</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="3cm"> emotional distress resulting therefrom, or</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="3cm">	    (b) 	physical injury to, or destruction of any tangible property, including any resulting loss of use thereof; however, this exclusion shall not apply to accounting  records of clients of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>;</fo:block>
				
				
				<fo:block margin-top="2mm"/>				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">C.</fo:inline>	any actual or alleged violation of:</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(a)	the Employee Retirement Income Security Act of 1974 and any amendments thereto;  however, this exclusion shall not apply if a court of competent jurisdiction deems the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> is a fiduciary under such Act solely by virtue of  <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline>  an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>  rendered to any employee benefit plan;(ii) if an Insured is appointed as a Receiver, Trustee  or  Custodian of an employee benefit plan by a court of law ; </fo:block>

				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(b)	the Racketeer Influenced and Corrupt Organizations Act;</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">D.</fo:inline>	any actual or alleged false, deceptive or unfair trade practice, violation of consumer protection laws or false, deceptive or  misleading trade practices;</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">E.</fo:inline>  <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> performed for any person or entity by an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, if at the time of any negligent act, error or omission giving rise to the<fo:inline font-weight="bold" font-style="italic"> Claim:</fo:inline></fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(a)	such Professional Services were rendered to an Immediate Family Member; or </fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(b)	such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or his/her Immediate Family Member controlled or owned more than 10% equity interest, operated or managed such entity; or,</fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(c)  	such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or his/her Immediate Family Member was a  partner, member, director, officer or employee of such entity; </fo:block>
				<fo:block margin-top="2mm"/>
				
				
				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">F.</fo:inline>        (a)	liability of others assumed by an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> under any contract or agreement, or</fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="3cm">			(b)	the breach of any express warranty of any contract unless such liability would have attached to the Insured even in the absence of such contract or agreement; </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">G. </fo:inline>   	actual or alleged negligent acts, errors or omissions asserted by or on behalf of an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>  against any other <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>;</fo:block>
				<fo:block margin-top="2mm"/>
				
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				 	
				 				
				 	<fo:block break-after="page"></fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">H.</fo:inline>   an<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> acting in the capacity as:</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(a)	an executor, administrator or personal representative of an estate or as a trustee if the<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> or a member of the <fo:inline font-weight="bold" font-style="italic">Insured’s Immediate Family</fo:inline> is or was a beneficiary or distributee of said estate or trust; or,</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(b)	an officer, director, trustee, partner or other member of a governing body of any entity other than the <fo:inline font-weight="bold" font-style="italic">Named Insured </fo:inline>and other than an accreditation or standards entity within the scope of Section IV, Definitions, paragraph (e) of the definition of <fo:inline font-weight="bold" font-style="italic">Professional Services;</fo:inline> or </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="3.5cm">(c)	a public official or employee of a governmental body, agency or subdivision thereof, unless such capacity is deemed as a matter of law to be a public official, employee or representative of such entity solely by virtue of an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> rendering<fo:inline font-weight="bold" font-style="italic"> Professional Services</fo:inline>. </fo:block>
				<fo:block margin-top="2mm"/>
				
				
				
				
			    <fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">I. </fo:inline> (a)	defects in title of which the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> had knowledge as of the date of issuance of any title policy, </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="3cm">and</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="3cm">(b)	breach of underwriting authority granted an<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> by a title insurance company or its delegate, and</fo:block>
				 <fo:block font-size="10px" text-align="left" start-indent="3cm">(c)	liability assumed under contract with any title insurance company to fund a loss payment, participate in a loss payment  or fund or reimburse any legal expenses under any title insurance policy. </fo:block>
				 	
				 	
				 <fo:block margin-top="2mm"/>					 
				 <fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">J. </fo:inline>	<fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> performed on or prior to the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> stated in Item 6. of the Declarations;</fo:block>
				  
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">K.	</fo:inline>negligent acts, errors or omissions or<fo:inline font-weight="bold" font-style="italic"> Related Claims</fo:inline> which have been the subject of any notice given under any prior policy of 
           			which this policy is a renewal or replacement;
					</fo:block>
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">L.</fo:inline>	any facts or circumstances of which any<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> had knowledge as of the effective date of this policy and which could reasonably have been expected to give rise to a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>;</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">M.	</fo:inline>(a)   any misuse or unauthorized use or disclosure of confidential or proprietary data or of personally</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="3cm">identifiable information; or</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="3cm">	(b)	any actual or alleged failure to inform customers or clients of any security breach which has impacted or may impact the confidential, proprietary or personally identifiable information of a customer or client;</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">N. </fo:inline>	<fo:inline font-weight="bold" font-style="italic">Professional Services </fo:inline> rendered by an<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline>  while the<fo:inline font-weight="bold" font-style="italic"> Insured’s</fo:inline> license to practice law was suspended, revoked, surrendered,  lapsed or otherwise not recognized as a bona fide license in the state where such services were rendered;</fo:block>
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">O.</fo:inline>	any loss sustained by any<fo:inline font-weight="bold" font-style="italic"> Insured </fo:inline>as a beneficiary or distributee of any estate or trust;</fo:block>
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm"><fo:inline font-size="12px" font-weight="bold">P.</fo:inline>	a notarized certification or acknowledgment of signature without an identity check and physical appearance before the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> performing such notarization of the person whose signature was notarized or acknowledged;  </fo:block>
				
				
				
				
				
				<fo:block margin-top="4mm"/>
				
				
			
				<fo:block font-weight="bold" font-size="12px" border="2pt solid black" text-align="left" ><fo:external-graphic src="../LawyersIns/image/spacer.png" />VI.			GENERAL CONDITIONS </fo:block>
				<fo:block margin-top="4mm"/>
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >A.	Notice of Claim or Circumstance </fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.	The <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> shall, as a condition precedent to the obligations of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> under this policy, give written notice of a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> as soon as practicable, but in no event later than sixty days after the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period.</fo:inline> In the event a<fo:inline font-weight="bold" font-style="italic"> Claim </fo:inline>is made during the Extended Reporting Period, if purchased, the <fo:inline font-weight="bold" font-style="italic">Insureds </fo:inline>shall, as a condition precedent to the obligations of the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> under this policy, give written notice of such <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>as soon as practicable, but in no event later than the expiration of the Extended Reporting Period. Such written notice shall include:</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(a)	all written correspondence between the claiming party and any <fo:inline font-weight="bold" font-style="italic">Insured,</fo:inline> and</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(b)	a written summary of  the facts and circumstances of the allegation of the negligent act, error, omission, <fo:inline font-weight="bold" font-style="italic">Personal Injury </fo:inline>or <fo:inline font-weight="bold" font-style="italic">Advertisers Liability</fo:inline></fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(c)		dates and details of the parties involved</fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(d)	possible<fo:inline font-weight="bold" font-style="italic"> Damages.</fo:inline></fo:block>
				
				
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2. If during the <fo:inline font-weight="bold" font-style="italic">Policy Period </fo:inline>or the Extended Reporting Period, if purchased, the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> becomes aware of any facts or circumstances that may reasonably be expected to give rise to a<fo:inline font-weight="bold" font-style="italic"> Claim </fo:inline>and, written notice is given to the<fo:inline font-weight="bold" font-style="italic"> Insurer </fo:inline>in accordance with paragraphs 1. and 3. of this Section A. of:</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(a)	such facts or circumstances, as well as the reasons for anticipating such a<fo:inline font-weight="bold" font-style="italic"> Claim</fo:inline>,  and</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(b)	specific information as to the expected negligent act, error, omission, <fo:inline font-weight="bold" font-style="italic">Personal Injury</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Advertisers Liability</fo:inline>,</fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(c)	dates and details of the parties involved, and</fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">(d)	the possible <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>, </fo:block>
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				
				<fo:block break-after="page"></fo:block>	
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="2.5cm">then any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> arising out of such specific facts or circumstances that is subsequently made against the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be deemed first made during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> as of the date of such notice.</fo:block>
				
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.    Notice of a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or circumstances to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be given in writing to:</fo:block>
				<fo:block margin-top="2mm"/>
				
				
				<fo:block font-size="10px" text-align="left" text-indent="2.5cm">Crum &amp; Forster</fo:block>
				<fo:block font-size="10px" text-align="left" text-indent="2.5cm">Claims Department</fo:block>
				<fo:block font-size="10px" text-align="left" text-indent="2.5cm">305 Madison Avenue</fo:block>
				<fo:block font-size="10px" text-align="left" text-indent="2.5cm">Morristown, New Jersey 07962</fo:block>
				<fo:block font-size="10px" text-align="left" text-indent="2.5cm">305_Liability@cfins.com</fo:block>
				
				<fo:block margin-top="2mm"/>
				
					
				
				<fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >B.	Defense and Settlement  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.	The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall have the right and the duty to defend any<fo:inline font-weight="bold" font-style="italic"> Claim </fo:inline>regardless of whether the allegations are groundless, false, or fraudulent. In undertaking this right and duty, the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> expressly retains the right to select defense counsel even when the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> reserves its rights on issues concerning the applicability of coverage under this policy. The<fo:inline font-weight="bold" font-style="italic"> Insureds </fo:inline>shall pay any <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> within the applicable Deductible shown in Item 4. of the Declarations. The<fo:inline font-weight="bold" font-style="italic"> Insurer’s</fo:inline> right and duty to defend any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> and pay <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> shall terminate upon the exhaustion of the Limit of Liability, whereupon the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall have no further obligation or liability to defend the<fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> or to pay <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>, judgments or settlements.  The <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline>may make any investigation it deems necessary and may, with the <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> consent, such consent not to be unreasonably withheld, make any settlement of any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> it deems expedient. If the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> withholds consent of such settlement, the <fo:inline font-weight="bold" font-style="italic">Insurer’s </fo:inline>liability for all <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> on account of such <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> shall not exceed the amount for which the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> could have settled such<fo:inline font-weight="bold" font-style="italic"> Claim</fo:inline>, inclusive of <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>, incurred as of the date such settlement was proposed to the<fo:inline font-weight="bold" font-style="italic"> Insured.</fo:inline></fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.	The <fo:inline font-weight="bold" font-style="italic">Insureds </fo:inline>and those acting on their behalf shall not admit liability, consent to any judgment, incur any <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> or agree to any settlement without the<fo:inline font-weight="bold" font-style="italic"> Insurer’s</fo:inline> written consent, such consent not to be unreasonably withheld.  The <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> agree that they shall not knowingly take any action that in any way increases<fo:inline font-weight="bold" font-style="italic"> Damages</fo:inline> or<fo:inline font-weight="bold" font-style="italic"> Defense Expenses</fo:inline> under this policy.  Coverage afforded by this policy shall not apply to any <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> sustained as a result of any admission of liability or consent to any judgment or agreement to settle, without or prior to the<fo:inline font-weight="bold" font-style="italic"> Insurer’s</fo:inline> written consent.</fo:block>
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.	The<fo:inline font-weight="bold" font-style="italic"> Insureds</fo:inline> shall provide the<fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> with such information, assistance, and cooperation as the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> and its counsel may reasonably request with respect to the defense and settlement of any<fo:inline font-weight="bold" font-style="italic"> Claim.</fo:inline> </fo:block>
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >C.	Action Against Insurer </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.	No action shall be taken against the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> unless, as a condition precedent thereto, the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> shall have fully complied with all of the terms and conditions of this policy, nor until the amount of the <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> obligation to pay <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> for any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> shall have been fully and finally determined either by judgment against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or by written agreement between the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline>, the claimant, and the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.	Nothing contained herein shall give any person or entity any right to join the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> as a party to any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> against the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> to determine their liability, nor shall the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> be impleaded by the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> or their legal representative in any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >D.	Cancellation and Non-renewal </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.	The <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> may cancel this policy at any time prior to the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> by mailing prior written notice to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> or by surrender of this policy to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> or its authorized agent. If the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall cancel this policy, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall return 90% of the unearned portion of the premium.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.	The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may only cancel this policy for nonpayment of premium or deductible. This policy may be canceled by or on behalf of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, at the address shown in Item 1. of the Declarations, written notice of cancellation at least 10 days before the effective date of cancellation. The mailing of such notice shall be sufficient proof of notice and the effective date of cancellation stated in such notice shall become the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels this policy for nonpayment of premium, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall credit the Insured 90% of the unearned portion of the premium. Payment or tender of any unearned premium by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not be a condition precedent to the effectiveness of cancellation, but such payment shall be made as soon as practicable.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.	This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>  at the address shown in Item 1. of the Declarations, written notice of nonrenewal at least thirty days prior to the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.  The mailing of such notice shall be sufficient proof of notice.</fo:block>
				<fo:block margin-top="2mm"/>
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				<fo:block break-after="page"></fo:block>
				
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >E. 	Changes in Exposures </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.	If the number of attorneys employed by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> increases more than 25% from the amount of attorneys shown in the application attached to this policy at its inception date, the <fo:inline font-weight="bold" font-style="italic">Named Insured </fo:inline>shall give the <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline>notice of such hiring, merger or acquisition as soon as practicable, but in no event more than 30 days after the effective date of hiring, merger or acquisition and the<fo:inline font-weight="bold" font-style="italic"> Insurer </fo:inline>shall then have the right to amend any terms of this policy. There shall be no coverage under this policy for any <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> subsequent to the date of hiring, merger or acquisition .  This paragraph shall not be applicable if the original   number of attorneys insured on the effective date of this policy was less than six attorneys. </fo:block>
				<fo:block margin-top="1mm"/>
				
				
				
				
				
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.	If the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> merges or consolidates with another entity in a manner such that the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> is not the surviving entity, coverage under this policy for<fo:inline font-weight="bold" font-style="italic"> Professional Services</fo:inline> rendered, or which should have been rendered subsequent to the effective date of such transaction shall be excluded for the remainder of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. Coverage shall then solely apply to <fo:inline font-weight="bold" font-style="italic">Professional Services </fo:inline>rendered, or which should have been rendered, between the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> stated in Item 6. of the Declarations and the effective date of the transaction whereby the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> is not the surviving entity, subject to all the  other terms, conditions and exclusions of this policy.</fo:block>
				
				
				
				
				
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >F. 	Subrogation </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">In the event of any payment by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> under this policy, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be subrogated to the extent of such payment to all <fo:inline font-weight="bold" font-style="italic">Insureds'</fo:inline> rights of recovery therefrom against any person or entity, and the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> shall execute all papers required and shall do everything that may be necessary to secure and preserve such rights to enable the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> to effectively bring suit in their name, and shall provide all other assistance and cooperation which the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may reasonably require.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >G. 	Representations  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">In granting coverage to the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline>, it is agreed that the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> has relied upon the representations and statements contained in the application for this policy (and all such previous applications submitted, or made part of any previous policy which this policy may succeed in time) including materials submitted therewith, as being accurate and complete and shall be the basis of the contract and shall become part of such policy as if physically attached. Such representations and statements are deemed to be material to the risk assumed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">H.	Other Insurance  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">All <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> payable under this policy shall be in excess of and shall not contribute with other existing insurance including, but not limited to, any insurance under which there is a duty to defend, regardless of whether any <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> are collectible or recoverable under such other insurance, unless such other insurance is written specifically excess of this policy.  This policy shall not be subject to the terms or conditions of any other insurance.</fo:block>
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">I. 	Authorization </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">The <fo:inline font-weight="bold" font-style="italic"> Named Insured</fo:inline> shall act on behalf of the  <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> with respect to the receiving of notices and return premiums from the  <fo:inline font-weight="bold" font-style="italic">Insurer.</fo:inline></fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">J.	Headings and Titles  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">The headings, sub-headings, and titles of this policy are for descriptive and reference purposes only and are not to be deemed in any way to limit, modify, or affect the terms and conditions of this policy.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">K.	Assignment of Interest  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" start-indent="1.5cm" text-align="left" >
					This policy and any and all rights hereunder are not assignable without the written consent of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.
				</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">L.	Changes  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">Notice to any agent or knowledge possessed by any agent or other person acting on behalf of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not effect a waiver or a change in any part of this policy or estop the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> from asserting any right under the terms and conditions of this policy, nor shall any terms or conditions be waived or changed except by written endorsement issued to form a part of this policy.</fo:block>
				<fo:block margin-top="2mm"/>
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				
				<fo:block break-after="page"></fo:block>
				
				
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">M.	Territory  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">This policy applies to a <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> taking place anywhere in the world provided that suit is brought and maintained against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>  within the United States of America, its territories or possessions, Puerto Rico or Canada.</fo:block>
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">N.	Named Insured Sole Agent  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">The <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> will be the sole agent and will be authorized to act on behalf of all<fo:inline font-weight="bold" font-style="italic"> Insured's</fo:inline> for the purpose of giving or   receiving any notices, any amendments to or cancellation of this policy, for the completing of any applications and the making of any statements, representations and warranties for the policy, for the payment of the deductible and the exercising or declining to exercise any right under this policy, including the purchase of any Extended Reporting Period.</fo:block>
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">O.	Liberalization    </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">If during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>,   the <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline>adopts any provision that would broaden the coverage under this policy without an additional premium charge, the broadened coverage shall automatically apply to this policy.</fo:block>
				
				<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				
				<fo:block break-after="page"></fo:block>
				
				
				<!--  
				<fo:block margin-top="10mm"/>
				
				<fo:block margin-top="10mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="center" >United States Fire Insurance Company</fo:block>
				<fo:block font-weight="bold,underline" font-size="12px" text-align="center" ><fo:inline font-weight="bold" font-style="underlined">A Delaware Corporation</fo:inline></fo:block>
				<fo:block font-size="10px" text-align="center" >Main Administrative Office:  Morristown, NJ</fo:block>
				<fo:block margin-top="5mm"/>
				<fo:block font-size="10px" text-align="center" >(A Capital Stock Company)</fo:block>
				<fo:block margin-top="30mm"/>
				<fo:table border="0" text-align="center">
					<fo:table-column column-width = "100mm" />
					<fo:table-column column-width = "100mm" />
	        		<fo:table-body>
	        			<fo:table-row>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block font-size="10px"   text-align="center">SIGNATURE</fo:block></fo:table-cell>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block   font-size="10px"   text-align="center">SIGNATURE</fo:block></fo:table-cell>
		    		  	</fo:table-row>	
	        			<fo:table-row>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block text-align="center"><fo:external-graphic src="../LawyersIns/image/douglas.png" /></fo:block></fo:table-cell>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block text-align="center"><fo:external-graphic src="../LawyersIns/image/james.png" /></fo:block></fo:table-cell>
		    		  	</fo:table-row>		    				    		   	
		    		   	<fo:table-row>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block font-size="10px"   text-align="center">Douglas M. Libby</fo:block></fo:table-cell>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block   font-size="10px"   text-align="center">James Kraus</fo:block></fo:table-cell>
		    		  	</fo:table-row>
		    		   	<fo:table-row>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block font-size="10px"   text-align="center">Chairman and CEO</fo:block></fo:table-cell>
		                    <fo:table-cell border="0" padding-left="4pt"><fo:block  font-size="10px"   text-align="center">Secretary</fo:block></fo:table-cell>
		    		  	</fo:table-row>
					</fo:table-body>
			</fo:table>
			
			<fo:block margin-top="2mm"  font-size="10px"    text-indent="2cm" color="grey" text-align="left">
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose>
				</fo:block>	
				
				-->
				
			 
     </xsl:template>
</xsl:stylesheet>




					
				    	