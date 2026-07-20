<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
		
	<xsl:template name="Policycoverletter0" match="/" >
	
	 <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
					<fo:block text-align="right">				  	
						<fo:external-graphic src="../LawyersIns/image/CFLogo.png"/>           	
					</fo:block>
					</xsl:if>	
					
					 <xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
			           <fo:block  color="grey" text-align="left">
						<fo:external-graphic
						src="../LawyersIns/image/ISMIE_logo.png" content-width="200px" />
						 </fo:block>
			          </xsl:if>
									
				    <fo:block margin-top="4mm">
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
					                    <fo:block font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  font-size="12px"   text-align="left">
					                    <!-- <xsl:choose>
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
									</xsl:choose> --></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt">
					                    
					                    <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
					                    <fo:block font-weight="bold"  text-align="left">
					                    <fo:table  text-align="center">
					    <fo:table-column column-width = "20mm" />
				        <fo:table-column column-width = "100mm" />				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="12px"   text-align="left"></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold"  font-size="11px"   text-align="right">Insurer:<fo:external-graphic src="../LawyersIns/img/space.gif"/> <!-- <fo:external-graphic src="../LawyersIns/image/boxcross.png"/><fo:external-graphic src="../LawyersIns/image/space.gif"/> -->  United States Fire Insurance Company</fo:block></fo:table-cell>
					    		  </fo:table-row>			    		 
					    		 <!-- 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold"  font-size="11px"   text-align="right" text-indent="1.9cm"><fo:external-graphic src="../LawyersIns/image/nocross.jpg"/> <fo:external-graphic src="../LawyersIns/img/space.gif"/> The North River Insurance Company</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row> -->
					    		  
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" ><fo:block  font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  >
					               			 <fo:block font-weight="bold"  font-size="11px"   text-align="right" text-indent="2.6cm">305 Madison Avenue</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" ><fo:block  font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" >
					               			 <fo:block font-weight="bold"  font-size="11px"   text-align="right" text-indent="2.6cm">Morristown, NJ 07962</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>
				     </fo:block>
				     </xsl:if>
				   
				      <fo:block>&#160;</fo:block>
				     
				     </fo:table-cell>
					    		  </fo:table-row>
				        
				        </fo:table-body>
				    </fo:table>				    	   
				</fo:block>			    
				    
				<!-- <fo:block margin-top="3mm" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  font-size="10px"   text-align="left">
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
					</xsl:choose>
				</fo:block> -->
				  
                <fo:block margin-top="3mm" />
                 <xsl:choose>
                 	<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
	                 	<fo:block font-weight="bold"  font-size="14px" text-align="center" >LAWYERS PROFESSIONAL LIABILITY POLICY- NORTH DAKOTA</fo:block>
	                 </xsl:when>
	                 <xsl:otherwise>
	                 	<fo:block font-weight="bold"  font-size="14px" text-align="center" >LAWYERS PROFESSIONAL LIABILITY POLICY</fo:block>
	                 </xsl:otherwise>
                 </xsl:choose>
                 
                 <fo:block font-weight="bold"  font-size="14px" text-align="center" id="1.1">DECLARATIONS</fo:block>
                
                
                <xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						<fo:block font-weight="normal" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" margin-top="5mm" font-size="10px" text-align="left">                 
                 		NOTICE: THIS IS A CLAIMS MADE AND REPORTED POLICY. EXCEPT AS OTHERWISE PROVIDED HEREIN, THIS POLICY PROVIDES COVERAGE FOR <fo:inline font-weight="bold" >CLAIMS</fo:inline> FIRST MADE AGAINST THE <fo:inline font-weight="bold" >INSUREDS</fo:inline> AND REPORTED TO THE <fo:inline font-weight="bold" >INSURER</fo:inline> DURING THE <fo:inline font-weight="bold" >POLICY PERIOD</fo:inline> OR DURING ANY APPLICABLE EXTENDED REPORTING PERIOD. <fo:inline font-size="14px" >PLEASE READ THIS POLICY CAREFULLY</fo:inline>. WORDS AND PHRASES WHICH ARE PRINTED IN <fo:inline font-weight="bold" >BOLD TYPEFACE</fo:inline> HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OF THE POLICY.  <fo:inline font-size="14px" >UNLESS STATED OTHERWISE BY SPECIFIC ENDORSEMENT</fo:inline>, <fo:inline font-size="14px" font-weight="bold" >DEFENSE EXPENSES</fo:inline> <fo:inline font-size="14px" font-style="italic">ARE INCLUDED IN THE LIMIT OF LIABILITY AND REDUCE THE LIMIT OF LIABILITY AVAILABLE TO PAY </fo:inline> <fo:inline font-size="14px" font-weight="bold" >DAMAGES</fo:inline>.</fo:block>
					</xsl:when>
                 	<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
                 		<fo:block font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" margin-top="5mm" font-size="10px" text-align="left">
                 		NOTICE: THIS IS A CLAIMS MADE AND REPORTED POLICY. EXCEPT AS OTHERWISE PROVIDED HEREIN, THIS POLICY PROVIDES COVERAGE FOR CLAIMS FIRST MADE AGAINST THE INSUREDS AND REPORTED TO THE INSURER DURING THE POLICY PERIOD OR DURING ANY APPLICABLE EXTENDED REPORTING PERIOD. PLEASE READ THIS POLICY CAREFULLY. WORDS AND PHRASES WHICH ARE PRINTED IN BOLD ITALIC TYPEFACE HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OF THE POLICY.  UNLESS STATED OTHERWISE BY SPECIFIC ENDORSEMENT, DEFENSE EXPENSES ARE INCLUDED IN THE LIMIT OF LIABILITY AND REDUCE THE LIMIT OF LIABILITY AVAILABLE TO PAY DAMAGES.</fo:block>
					</xsl:when>
					<xsl:otherwise>
						<fo:block font-weight="normal" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" margin-top="5mm" font-size="11px" text-align="left">                 
                 		NOTICE: THIS IS A CLAIMS MADE AND REPORTED POLICY. EXCEPT AS OTHERWISE PROVIDED HEREIN, THIS POLICY PROVIDES COVERAGE FOR <fo:inline font-weight="bold" >CLAIMS</fo:inline> FIRST MADE AGAINST THE <fo:inline font-weight="bold" >INSUREDS</fo:inline> AND REPORTED TO THE <fo:inline font-weight="bold" >INSURER</fo:inline> DURING THE <fo:inline font-weight="bold" >POLICY PERIOD</fo:inline> OR DURING ANY APPLICABLE EXTENDED REPORTING PERIOD. PLEASE READ THIS POLICY CAREFULLY. WORDS AND PHRASES WHICH ARE PRINTED IN <fo:inline font-weight="bold" >BOLD TYPEFACE</fo:inline> HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OF THE POLICY.  UNLESS STATED OTHERWISE BY SPECIFIC ENDORSEMENT, <fo:inline font-weight="bold" >DEFENSE EXPENSES</fo:inline> ARE INCLUDED IN THE LIMIT OF LIABILITY AND REDUCE THE LIMIT OF LIABILITY AVAILABLE TO PAY <fo:inline font-weight="bold" >DAMAGES</fo:inline>.</fo:block>
					</xsl:otherwise>
				</xsl:choose> 	
					
                <fo:block margin-top="3mm">
					<fo:table border="2pt solid black" text-align="center">
					    <fo:table-column column-width = "20mm" />
				        <fo:table-column column-width = "170mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt"><fo:block font-size="10px"   text-align="left">Item 1.</fo:block></fo:table-cell>
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
																	<fo:block font-size="10px"   text-align="left"><xsl:value-of select="response/address_freeform_01/data/Address1" />, 
																		<xsl:if test="response/address_freeform_01/data/Address2!= ''"> 					    	
																	    	<!-- <fo:external-graphic src="../LawyersIns/image/spacer.png"/> --><xsl:value-of select="response/address_freeform_01/data/Address2" />  
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
																			<xsl:value-of select="response/address_freeform_01/data/StateDesc"/>, <!--<fo:external-graphic src="../LawyersIns/image/spacer.png"/> -->
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
					               		<fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt"><fo:block font-size="10px"   text-align="left">Item 2.</fo:block></fo:table-cell>
					               		<fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt">
					               			 <fo:block font-size="10px"   text-align="left"><fo:inline font-weight="bold" >Policy Period:</fo:inline>  From  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  (Effective)  &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160; To  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" />  (Expiration)  </fo:block>
					               			 <fo:block font-size="10px"   text-align="left" text-indent="2.4cm">(12:01 a.m. local time at the address shown in Item 1.)</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		 
					    		 <fo:table-row>
					               		<fo:table-cell padding-bottom="4mm" border="2pt solid black" padding-left="4pt"><fo:block font-size="10px"   text-align="left">Item 3.</fo:block></fo:table-cell>
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
						                    		<fo:table-cell >
							                    		<fo:block font-size="10px"   text-align="center">
								                    		<fo:block text-align="left"><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText" /></fo:block>
								                    		<fo:block text-align="left"><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText" /></fo:block>
							                    		</fo:block>
						                    		</fo:table-cell>						                    		
						                    		<fo:table-cell padding-left="4pt">
							    		  		         <fo:block font-size="10px"   text-align="left">each <fo:inline font-weight="bold" >Claim</fo:inline>						    		  		       	
							    		  		       					    		  		       
							    		  		     	  </fo:block>							    		  		       
							    		  		         <fo:block font-size="10px"   text-align="left"><fo:inline font-weight="bold" >Policy Aggregate</fo:inline></fo:block>
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
					               		<fo:block text-align="left">These amounts include <fo:inline font-weight="bold" >Defense Expenses </fo:inline>unless this Section is amended by specific endorsement of this policy.</fo:block>
					               		</fo:block>
					              	    </fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-size="10px"   text-align="left">Item 4.</fo:block></fo:table-cell>
					               		<fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm">
						               		
						               		<fo:block font-size="10px"   >
					               		<fo:block font-weight="bold" font-size="10px"   text-align="left">Deductible:</fo:block>  
					               		<fo:block margin-top="2mm"/> 
					         			<fo:table  text-align="center">
				    						<fo:table-column column-width = "30mm" />
			        		                <fo:table-column column-width = "31mm" />
						                    <fo:table-body>
							    		        <fo:table-row>
						                    		<fo:table-cell >
							                    		<fo:block font-size="10px"   text-align="center">
								                    		<fo:block text-align="left"><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount" /></fo:block>
								                    		
							                    		</fo:block>
						                    		</fo:table-cell>
						                    		<fo:table-cell padding-left="4pt">
						                    			<fo:block font-size="10px"   text-align="left">each<fo:inline font-weight="bold" > Claim</fo:inline></fo:block>
						                    		<!--  
							    		  		         <fo:block font-size="10px"   text-align="left">
							    		  		         	<xsl:choose>
											                    <xsl:when test="response/policycoverage_freeform_01/data/IsClaimOptionType='Y'">
											                        <fo:inline font-weight="bold" >Annual Aggregate</fo:inline>
				        								        </xsl:when>
											                    <xsl:otherwise>
											                        <fo:inline font-weight="bold" >Per Claim</fo:inline>
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
					               		<fo:block text-align="left">This amount applies to <fo:inline font-weight="bold" >Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this policy.
	 											This amount applies to each <fo:inline font-weight="bold" >Claim </fo:inline>unless this Section is amended by specific endorsement of this policy.</fo:block>
					               		</fo:block>
						               		
						               		
						               						              	    
					              	    </fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-size="10px"   text-align="left">Item 5.</fo:block></fo:table-cell>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm">
					               	<fo:block font-weight="bold"  font-size="10px"   text-align="left">Premium:
					               		<xsl:value-of select="response/policycoverage_freeform_01/data/TotalPremium" /> 
					               		<xsl:if test="response/address_freeform_01/data/StateCode !='KY'">					               		
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
						             	</xsl:if>
					               		
						             	<xsl:if test="response/address_freeform_01/data/StateCode ='KY'">
						             		<fo:block> KY Premium Surcharges: <xsl:value-of select="response/policycoverage_freeform_01/data/FireFighterCharges" /></fo:block>
							             	<fo:block>	KY Local Government Premium Taxes: <xsl:value-of select="response/policycoverage_freeform_01/data/MTTaxAmmount" /></fo:block>
							             	{<xsl:value-of select="response/address_freeform_01/data/CountyDesc" />}
						             	</xsl:if>			             				               		
					               	</fo:block>
					               	
					               </fo:table-cell>
					    		  </fo:table-row>
					    		  <!-- 
					    		  <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-size="10px"   text-align="left"></fo:block></fo:table-cell>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="4mm"><fo:block font-weight="bold"  font-size="10px"   text-align="left">Total: <xsl:value-of select="response/policycoverage_freeform_01/data/InvoicedPremium" /></fo:block></fo:table-cell>
					    		  </fo:table-row> -->
					    		 
					    		  <fo:table-row>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="6mm"><fo:block font-size="10px"   text-align="left">Item 6.</fo:block></fo:table-cell>
					               <fo:table-cell border="2pt solid black" padding-left="4pt" padding-bottom="6mm"><fo:block font-weight="bold"  font-size="10px"   text-align="left">Prior Acts Date:<xsl:if test="response/professionalliabilityinsdetail_freeform_01/data/IsPriorActDateFull= 'N'"> Date :</xsl:if><xsl:if test="response/policy_freeform_01/data/IsFirmCarryingProfLiabilityIns != 'Y'"> Date :</xsl:if>  
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
					               <fo:block font-size="10px"   text-align="left" padding-bottom="4mm">Item 7.</fo:block>
					               </fo:table-cell>
					               
					               <fo:table-cell border="2pt solid black" padding-left="4pt">
					                    <fo:block font-size="10px"   text-align="left">The following endorsements, if any, are made a part of this policy at issuance:</fo:block>
					                    <fo:block font-weight="bold"  font-size="10px"   text-align="left"><xsl:value-of select="response/Endorsements"/>
					                    				                    
					                    </fo:block>
					               </fo:table-cell>
					    		 </fo:table-row>
					    		 
					    		
					    </fo:table-body>
				   </fo:table>
				</fo:block>	    	
				<fo:block margin-top="3mm"/> 
			 	<fo:block margin-top="1mm" font-size="10px" text-align="left">These Declarations, the application, and the policy with endorsements attached thereto, constitute the entire agreement between the <fo:inline font-weight="bold" >Insurer</fo:inline> and the <fo:inline font-weight="bold" >Insured</fo:inline>.</fo:block>
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
				
				 <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
				<fo:block margin-top="5mm" font-size="10px" color="grey" text-align="left">
						<xsl:choose>
						     	<xsl:when test="response/address_freeform_01/data/StateCode = 'NM' and response/NMRatingVersion = 'NM2'">
						     		LPL - 099 (04/19)
						     	</xsl:when> 
						     	<xsl:otherwise>
		                         	LPL - 099 (04/17)
		                      </xsl:otherwise>
		                    </xsl:choose>							  
				</fo:block>	
				</xsl:if>
				 <xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
				 <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P002 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 2 of 2</fo:block>
        	 </fo:block>
			 
				</xsl:if>
				
				<!-- <xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'MT'">
						<fo:block font-size="10px" color="grey" text-align="left">
							LPL - 099 (04/17)
						</fo:block>
					</xsl:when>
					<xsl:otherwise>
						<fo:block margin-top="5mm" font-size="10px" color="grey" text-align="left">
							LPL - 099 (04/17)
						</fo:block>
					</xsl:otherwise>
				</xsl:choose> -->
				<!-- <fo:block color="grey" text-align="left">				
				<xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						<fo:block font-size="10px" color="grey" text-align="left">
							LPL - 099 (04/17)
						</fo:block>
					</xsl:when>
					<xsl:otherwise>
						<fo:block margin-top="3mm" font-size="10px" color="grey" text-align="left">
							LPL - 099 (04/17)
						</fo:block>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL0990417" />
				</fo:block>	 -->			
	  
     </xsl:template>
</xsl:stylesheet>




					
				    	