<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	 <xsl:output method="xml" indent="no" />
	<xsl:template match="/" name="Policycoverletter9">
      
					<fo:block text-align="center" font-weight="bold" font-size="11px">
						THIS ENDORSEMENT CHANGES THE POLICY. PLEASE READ IT CAREFULLY.
					</fo:block>
					
					<fo:block margin-top="3mm">
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="120mm" />
							<fo:table-column column-width="70mm" />				
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Named Insured: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Endorsement Number: LPL-109 (04/17)</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>	
						</fo:table>
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="60mm" />
							<fo:table-column column-width="60mm" />
							<fo:table-column column-width="70mm" />				
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Policy Number: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Policy Period:</fo:block>
										<fo:block font-size="10px" padding-left="8pt"><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /> to <xsl:value-of select="response/policycoverage_freeform_01/data/PolicyExpirationDate" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policycoverage_freeform_01/data/TransactionEffectiveDate" /></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
								
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="190mm" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Insurer: United States Fire Insurance Company</fo:block>
									</fo:table-cell>					
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="7px" padding-left="20pt" text-indent="0.5cm">Insert the policy number. The remainder of the information is to be completed only when this endorsement is issued subsequent to the preparation of the policy.</fo:block>
					<fo:block margin-top="2mm">
					</fo:block>
					
					
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="center" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="12px" >GENERAL CHANGE ENDORSEMENT TO DECLARATIONS PAGE</fo:block>
				     
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px"  > In consideration of: (check one box only)</fo:block>				     
				    <fo:block margin-top="3mm"/>
				    <fo:block start-indent="7mm">
				     <xsl:choose>
                    	<xsl:when test="response/PremiumCharged= 'Y'">
                    	 <fo:block margin-top="1mm"/>				     
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     an additional premium of <xsl:value-of select="response/PremiumValue"/></fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     a return premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     the premium charged </fo:block>
                      </xsl:when>
                      <xsl:when test="response/PremiumCharged= 'N'">
                         <fo:block margin-top="1mm"/>				     
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     an additional premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     a return premium of <xsl:value-of select="response/PremiumValue"/></fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     the premium charged </fo:block>
                      </xsl:when>
                      <xsl:when test="response/PremiumCharged= 'S'">
                         <fo:block margin-top="1mm"/>				     
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     an additional premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     a return premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     the premium charged </fo:block>
                      </xsl:when>
                      <xsl:when test="response/TransactionTypeKey= '11'">
                         <fo:block margin-top="1mm"/>				     
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     an additional premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     a return premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     the premium charged </fo:block>
                      </xsl:when>
                      <xsl:otherwise>
                         <fo:block margin-top="1mm"/>				     
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     an additional premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     a return premium of $</fo:block>
					     <fo:block margin-top="1mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     the premium charged </fo:block>
                      </xsl:otherwise>
                     </xsl:choose>
                     	</fo:block>			     
				     <fo:block margin-top="3mm"/>
				     <fo:block text-align="left" font-size="10px" >it is agreed that:(check appropriate boxes)</fo:block>
				     
				     <fo:block margin-top="3mm"/>
				     <xsl:choose>
                    	<xsl:when test="response/TransactionTypeKey= '11'">
                        <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     Item 1. of the Declarations, <fo:inline font-weight="bold">Named Insured</fo:inline> <fo:inline>&amp;</fo:inline>Street Address, is amended to read:</fo:block>
					     
					     <fo:block  text-align="left" font-size="9px" text-indent="1.5cm"><xsl:value-of select="response/policy_freeform_01/data/AccountName"/></fo:block>
					     <fo:block  text-align="left" font-size="9px" text-indent="1.5cm"><xsl:value-of select="response/policy_freeform_01/data/Address1"/></fo:block>
					     <fo:block  text-align="left" font-size="9px" text-indent="1.5cm"><xsl:value-of select="response/policy_freeform_01/data/Address2"/></fo:block>
					     <fo:block  text-align="left" font-size="9px" text-indent="1.5cm">
		                    <xsl:if test="response/policy_freeform_01/data/City!= ''"> 
						        <xsl:value-of select="response/policy_freeform_01/data/City" />
								<xsl:if test="response/policy_freeform_01/data/StateDesc!= ''">, 
						        </xsl:if>
						    </xsl:if>
						    <xsl:if test="response/policy_freeform_01/data/StateDesc!= ''"> 					    	
						        <xsl:value-of select="response/policy_freeform_01/data/StateDesc" /> 
						        <xsl:if test="response/policy_freeform_01/data/Zip!= ''"><fo:external-graphic src="../LawyersIns/image/spacer.png"/>					        	
						    	</xsl:if>
						    </xsl:if>	
						    <xsl:if test="response/policy_freeform_01/data/Zip!= ''"> 
						        <xsl:value-of select="response/policy_freeform_01/data/Zip" /> 
						    </xsl:if>						    
		                 </fo:block>
                      </xsl:when>
                      <xsl:otherwise>
                        <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     Item 1. of the Declarations, <fo:inline font-weight="bold">Named Insured </fo:inline> <fo:inline>&amp; </fo:inline>Street Address, is amended to read:</fo:block>
					     					     
                      </xsl:otherwise>
                     </xsl:choose>
				     
			    	 
				     <fo:block margin-top="6mm"/>
				     <xsl:choose>
                    	<xsl:when test="response/TransactionTypeKey= '13'">
                         <fo:block text-align="left" font-size="10px">
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     Item 2. of the Declarations, <fo:inline font-weight="bold">Policy Period</fo:inline>, is amended to read:</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">From:  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /><fo:inline>(Effective)</fo:inline> &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160; To:  <xsl:value-of select="response/policycoverage_freeform_01/data/PolicyExpirationDate" />  (Expiration)  </fo:block>
						 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">(12:01 a.m. local time at the address shown in Item 1.)</fo:block>
					  </xsl:when>
                      <xsl:otherwise>
                        <fo:block text-align="left" font-size="10px">
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     Item 2. of the Declarations, <fo:inline font-weight="bold">Policy Period</fo:inline>, is amended to read:</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">From:   <fo:inline>(Effective)</fo:inline>&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160; To:  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /> (Expiration) </fo:block>
						 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">(12:01 a.m. local time at the address shown in Item 1.)</fo:block>
					  </xsl:otherwise>
                     </xsl:choose>
				     
				     <fo:block margin-top="6mm"/>
				     <xsl:choose>
                    	<xsl:when test="response/TransactionTypeKey= '9'">
	                    	 <fo:block text-align="left" font-size="10px" >
						     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
						     Item 3. of the Declarations, Limit of Liability, is amended to read:</fo:block>
						     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm"><fo:inline></fo:inline><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText" />&#160;&#160;&#160;&#160;&#160;&#160;  each <fo:inline font-weight="bold">Claim</fo:inline></fo:block>							    		  		       
							 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm"><fo:inline></fo:inline><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText" />&#160;&#160;&#160;&#160;&#160;&#160;  <fo:inline font-weight="bold">Policy Aggregate</fo:inline></fo:block>
							 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">These amounts include <fo:inline font-weight="bold">Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
							 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">policy.</fo:block>
					  	</xsl:when>
	                  	<xsl:otherwise>
	                         <fo:block text-align="left" font-size="10px" >
						     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
						     Item 3. of the Declarations, Limit of Liability, is amended to read:</fo:block>
						     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm"><fo:inline></fo:inline>  &#160;&#160;&#160;&#160;&#160;&#160;  each <fo:inline font-weight="bold">Claim</fo:inline></fo:block>							    		  		       
							 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm"><fo:inline></fo:inline> &#160;&#160;&#160;&#160;&#160;&#160;  <fo:inline font-weight="bold">Policy Aggregate</fo:inline></fo:block>
							 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">These amounts include <fo:inline font-weight="bold">Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
							 <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">policy.</fo:block>
	                    </xsl:otherwise>
                     </xsl:choose>
					  
				     <fo:block margin-top="6mm"/>
				     <xsl:choose>
                    	<xsl:when test="response/TransactionTypeKey= '10'">
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     Item 4. of the Declarations, Deductible, is amended to read:</fo:block>
					     <fo:block font-size="10px" text-align="left" text-indent="1.5cm"><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount" />&#160;&#160;&#160;&#160;&#160;&#160;  each<fo:inline font-weight="bold" > Claim</fo:inline></fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">This amount applies to <fo:inline font-weight="bold">Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">policy.</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">This amount applies to each <fo:inline font-weight="bold">Claim</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">policy.</fo:block>
				      </xsl:when>
                      <xsl:otherwise>
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     Item 4. of the Declarations, Deductible, is amended to read:</fo:block>
					     <fo:block font-size="10px" text-align="left" text-indent="1.5cm">&#160;&#160;&#160;&#160;&#160;&#160;  each<fo:inline font-weight="bold" > Claim</fo:inline></fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">This amount applies to <fo:inline font-weight="bold" >Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">policy.</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">This amount applies to each <fo:inline font-weight="bold" >Claim</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
					     <fo:block font-size="10px"   text-align="left" text-indent="1.5cm">policy.</fo:block>
				      </xsl:otherwise>
                     </xsl:choose>
				     
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px">
				     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
				     Item 5. of the Declarations, Premium, is amended to read: <fo:inline start-indent="10mm">&#160;&#160;&#160;&#160;&#160;&#160;</fo:inline>
				     	<!--<xsl:value-of select="response/policycoverage_freeform_01/data/TotalPremium" />
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
		             	</xsl:if>  -->
				     </fo:block>
				     
				     <fo:block margin-top="6mm"/>
				     <xsl:choose>
                    	<xsl:when test="response/TransactionTypeKey= '12'">
                         <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     Item 6. of the Declarations, <fo:inline font-weight="bold" >Prior Acts Date</fo:inline>, is amended to read:
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
                      </xsl:when>
                      <xsl:otherwise>
                       <fo:block text-align="left" font-size="10px" >
                        <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     Item 6. of the Declarations, <fo:inline font-weight="bold">Prior Acts Date</fo:inline>, is amended to read:					      
					     </fo:block>
                      </xsl:otherwise>
                     </xsl:choose>
				     
				     <fo:block margin-top="6mm"/>
				     
				      <fo:block text-align="left" font-size="10px" >
                        <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     Item 7. of the Declarations is amended as follows:					      
					     </fo:block>
					     
				     <fo:block margin-top="7mm"/>
				     <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
			       	<fo:block margin-top="8mm"/>
		            <fo:block>
			       	<fo:table>
					<fo:table-column column-width="80mm"/>
					<fo:table-column column-width="40mm"/>
					<fo:table-column column-width="60mm"/>
					<fo:table-body>
						<fo:table-row>
						<fo:table-cell>
						
						 <xsl:choose>
	                    	<xsl:when test="response/isShowSignature='Y'">
		                    	<fo:block font-size="9px" color="black" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black" text-align="center">
									<fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="10em" content-width="8em"/>
								</fo:block> 
						  	</xsl:when>
		                  	<xsl:otherwise>
		                        <fo:block font-size="9px" color="black" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black" text-align="center">
									<fo:external-graphic src="../LawyersIns/image/BlankSingnature.png" content-height="10em" content-width="8em"/>
								</fo:block>
		                    </xsl:otherwise>
                   		  </xsl:choose>
						<fo:block font-size="9px" color="black" text-align="center">
						&#160;&#160;Authorized Representative
						</fo:block>
						</fo:table-cell>
						<fo:table-cell>
						<fo:block> &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center">
						<fo:block>
						&#160;
						</fo:block>
						<fo:block>
						</fo:block>
						<fo:block margin-top="3mm" font-size="9px" color="black"  border-bottom-style="solid" border-bottom-color="black" text-align="center">
						<xsl:value-of select="response/general_freeform_01/data/CurrentDate"/>
						</fo:block>
						<fo:block font-size="9px" color="black">
						Date
						</fo:block>
						</fo:table-cell>
						</fo:table-row>
						</fo:table-body>
					</fo:table>	   
				</fo:block>	
				<fo:block margin-top="1cm"/> 
		        <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey" text-align="left">LPL-109 (04/17)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        <fo:inline text-align="right" width="50%">Page 1 of 1</fo:inline></fo:block>	

		        
     </xsl:template>
</xsl:stylesheet>




					
				    	