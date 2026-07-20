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
					page-height="29.7cm" margin-top="0.2in" margin-bottom="1.0in" margin-left="0.5in" 
					margin-right="0.5in">
					<fo:region-body margin-top="2.0in"/>
					<fo:region-before extent="1.0in"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4-portrait">
			
			<fo:static-content flow-name="xsl-region-before">
				 <fo:block text-align="left">				  	
				  	<fo:external-graphic src="../LawyersIns/image/logo_protexureaccountants1.png" content-height="6em" content-width="20em"/>           	
				  </fo:block>	
				  
				  <fo:block>
					<fo:table>
				         <fo:table-column column-width = "80mm" />
				         <fo:table-column column-width = "50mm" />
				         <fo:table-column column-width = "70mm" />
				         <fo:table-body>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" >4200 Commerce Court, Ste 102</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" >Lisle, IL 60532</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" >Phone 1-877-569-4111</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" >Fax (630) 799-1796</fo:block></fo:table-cell>
					           </fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:external-graphic src="../LawyersIns/image/header_bottom.png" content-width="44em"/> 
				</fo:block>	
				
				<fo:table>
			         <fo:table-column column-width = "120mm" />
			         <fo:table-column column-width = "70mm" />
			         <fo:table-body>
			         	 <fo:table-row>
				             <fo:table-cell><fo:block font-size="10px" font-family="Arial" color="grey"><fo:inline font-weight="bold">Quick Quotation for Lawyers Professional Liability Insurance</fo:inline></fo:block></fo:table-cell>
				             <fo:table-cell><fo:block  font-size="10px" text-indent="1cm" color="grey">Date:<xsl:value-of select="response/date_freeform_01/data/QuoteDate" />&#160;&#160;<xsl:value-of select="response/time_freeform_01/data/QuoteTime" /></fo:block></fo:table-cell>
				           </fo:table-row>				         
			         </fo:table-body>				         
				</fo:table>
				
				
			</fo:static-content>
								  				
				<fo:static-content flow-name="xsl-region-after">
				  <fo:block text-align="left">Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block>
				</fo:static-content>
				
				<fo:flow flow-name="xsl-region-body">					
					<fo:block margin-top="3mm">
					<fo:table border="0.5pt solid black">
				         <fo:table-column column-width = "50mm" />
				         <fo:table-column column-width = "135mm" />
				         <fo:table-body>
					           <fo:table-row>
					             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="10px" font-family="Arial" >Insured:</fo:block></fo:table-cell>
					             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="10px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="10px" font-family="Arial" >Policy Period:</fo:block></fo:table-cell>
					             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="10px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  -  <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /></fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="10px" font-family="Arial" >Quotation #</fo:block></fo:table-cell>
					             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="10px" font-family="Arial" ><xsl:value-of select="response/policy_freeform_01/data/QuoteNumber" /></fo:block></fo:table-cell>
					           </fo:table-row>	           
						</fo:table-body>
					</fo:table>
					</fo:block>
					
					<fo:block>
						<fo:table margin-top="3mm" border="0.0pt solid black">  
							<fo:table-column column-width="100mm"/>
							<fo:table-column column-width="100mm"/>							
							<fo:table-body>								
						        <fo:table-row>							        		
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">							        		
											If you have coverage, what is your current effective date? &#160;&#160;<xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate"/>&#160;&#160;&#160;&#160;&#160;						        			
								       	</fo:block></fo:table-cell>	
								       	<fo:table-cell><fo:block font-size="9px" font-family="Arial" >							        		
											In what state does you firm reside? &#160;<xsl:value-of select="response/address_freeform_01/data/StateDesc"/>&#160;&#160;&#160;&#160;&#160;						        			
								       	</fo:block></fo:table-cell>									       							        	
						        </fo:table-row>	
						        
						   <!-- "CA", "KY", "FL", "NJ", "NY", "PA", "MI", "TX", "IL" -->
						        
						     
						        <xsl:if test="(response/address_freeform_01/data/StateCode = 'CA') or (response/address_freeform_01/data/StateCode = 'KY')
						        or (response/address_freeform_01/data/StateCode = 'FL')or (response/address_freeform_01/data/StateCode = 'NJ')
						        or (response/address_freeform_01/data/StateCode ='NY')or (response/address_freeform_01/data/StateCode ='PA')
						        or (response/address_freeform_01/data/StateCode = 'MI')or (response/address_freeform_01/data/StateCode = 'TX')
						        or (response/address_freeform_01/data/StateCode = 'IL')">
						         <fo:table-row>				        	
								       	<fo:table-cell><fo:block font-size="9px" font-family="Arial" margin-top="3mm">							        		
											County : &#160;<xsl:value-of select="response/address_freeform_01/data/CountyDesc"/>&#160;&#160;&#160;&#160;&#160;						        			
								       	</fo:block></fo:table-cell>	
								       	<fo:table-cell><fo:block font-size="9px" font-family="Arial" margin-top="3mm">							        		
											City :&#160;<xsl:value-of select="response/address_freeform_01/data/City"/>&#160;&#160;&#160;&#160;&#160;						        			
								       	</fo:block></fo:table-cell>								        	
						        </fo:table-row>	
						        </xsl:if>					        						        
						         <xsl:if test="(response/IsFirmHaveLawyersLiabilityInsurance = 'Y') ">
						         <fo:table-row>				        	
								       	<fo:table-cell><fo:block font-size="9px" font-family="Arial" margin-top="3mm">							        		
											Expiring carrier :  &#160;<xsl:value-of select="response/priorPolicyData_freeform_01/data/InsuranceCompanyNamePross"/>&#160;&#160;&#160;&#160;&#160;						        			
								       	</fo:block></fo:table-cell>	
								       	<fo:table-cell><fo:block font-size="9px" font-family="Arial" margin-top="3mm">							        		
											Current premium :&#160;<xsl:value-of select="response/priorPolicyData_freeform_01/data/ProInsPremium"/>&#160;&#160;&#160;&#160;&#160;						        			
								       	</fo:block></fo:table-cell>								        	
						        </fo:table-row>	
						        </xsl:if>	
						        	
						        <fo:table-row>
							        	 <fo:table-cell><fo:block font-size="9px" font-family="Arial" margin-top="3mm" >							        		
											Email : &#160;<xsl:value-of select="response/policy_freeform_01/data/AccountEmail"/>&#160;&#160;&#160;&#160;&#160;						        			
								       	</fo:block></fo:table-cell>						         									
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" margin-top="3mm" >
								        	 Phone : &#160;<xsl:value-of select="response/policy_freeform_01/data/WorkPhone"/>&#160;&#160;&#160;&#160;&#160;
									    </fo:block></fo:table-cell>
						        </fo:table-row>	
						        
						      </fo:table-body>
						</fo:table>
					</fo:block>
					
					
					
					<fo:block font-size="9px" font-family="Arial" margin-top="4mm" >What percentage of gross annual revenue comes from each Area of Practice listed below?</fo:block>
					
					<fo:block>
						<fo:table margin-top="2mm">									
									<fo:table-column column-width="140mm"/>
									<fo:table-column column-width="30mm"/>
							<fo:table-body>
							<fo:table-row>	
																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" text-align="center" font-family="Arial">AOPDesc</fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center">Percentage</fo:block></fo:table-cell>
									</fo:table-row>
									<xsl:for-each select="response/aoplist/data">		
									<fo:table-row>	
																	
							         		<fo:table-cell border="0.5pt solid black" padding-left="2mm"><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="AOPDescNew"/></fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="PercentageValue"/>%</fo:block></fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
								<fo:table-row>	
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" text-align="center" font-weight="bold" font-family="Arial">&#160;&#160;Total Percentage </fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aop_total"/>%</fo:block></fo:table-cell>
						         	</fo:table-row>	
							</fo:table-body>		
						</fo:table>
					</fo:block>
					
					<fo:block>
						<fo:table margin-top="5mm">									
									<fo:table-column column-width="70mm"/>
									<fo:table-column column-width="70mm"/>
							<fo:table-body>	
										<xsl:if test="(response/NumberOfYearsWithFirm_1 != '') or (response/NumberOfYearsWithFirm_1 !=NUll)">					
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 1 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_1"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_2 != '') or (response/NumberOfYearsWithFirm_2 !=NUll)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 2 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_2"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_3 != '') or (response/NumberOfYearsWithFirm_3 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 3 : Number of years with firm&#160;&#160;</fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">
							         		<xsl:value-of select="response/NumberOfYearsWithFirm_3"/>								        
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_4 != '') or (response/NumberOfYearsWithFirm_4 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 4 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"> <fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_4"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_5 != '') or (response/NumberOfYearsWithFirm_5 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 5 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">	
							         		<xsl:value-of select="response/NumberOfYearsWithFirm_5"/>							        
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_6 != '') or (response/NumberOfYearsWithFirm_6 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 6 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_6"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_7 != '') or (response/NumberOfYearsWithFirm_7 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 7 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_7"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_8 != '') or (response/NumberOfYearsWithFirm_8 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 8 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_8"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_9 != '') or (response/NumberOfYearsWithFirm_9 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 9 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_9"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_10 != '') or (response/NumberOfYearsWithFirm_10 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 10 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_10"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_11 != '') or (response/NumberOfYearsWithFirm_11 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 11 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_11"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_12 != '') or (response/NumberOfYearsWithFirm_12 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 12 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_12"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_13 != '') or (response/NumberOfYearsWithFirm_13 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 13 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_13"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_14 != '') or (response/NumberOfYearsWithFirm_14 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 14 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_14"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_15 != '') or (response/NumberOfYearsWithFirm_15 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 15 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_15"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_16 != '') or (response/NumberOfYearsWithFirm_16 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 16 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_16"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_17 != '') or (response/NumberOfYearsWithFirm_17 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 17 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_17"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_18 != '') or (response/NumberOfYearsWithFirm_18 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 18 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_18"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_19 != '') or (response/NumberOfYearsWithFirm_19 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 19 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_19"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_20 != '') or (response/NumberOfYearsWithFirm_20 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 20 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_20"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_21 != '') or (response/NumberOfYearsWithFirm_21 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 21 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_21"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_22 != '') or (response/NumberOfYearsWithFirm_22 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 22 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_22"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_23 != '') or (response/NumberOfYearsWithFirm_23 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 23 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_23"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_24 != '') or (response/NumberOfYearsWithFirm_24 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 24 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_24"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									<xsl:if test="(response/NumberOfYearsWithFirm_25 != '') or (response/NumberOfYearsWithFirm_25 !=NULL)">
									<fo:table-row>																	
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Attorney 25 : Number of years with firm&#160;&#160; </fo:block></fo:table-cell>
							         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">								        
									        <xsl:value-of select="response/NumberOfYearsWithFirm_25"/>
									        </fo:block></fo:table-cell>
									</fo:table-row>
									</xsl:if>
									
									
							</fo:table-body>		
						</fo:table>
					</fo:block>
						
							
				
					<fo:block>	
					
					
									
					  <xsl:if test="response/quoteOpted_list_01/* or response/quoteOpted_list_01/text()">
					  	
					 
					  
					  	
					  
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
												             <fo:table-cell number-columns-spanned="2" padding-left="4pt">												            
												            	<fo:block font-size="11px" start-indent="1cm" font-weight="bold">													            												            		
												            	   <fo:external-graphic src="../LawyersIns/image/check-btn4.png" /> Option <xsl:value-of select="ROWID" />:												        								   
																	Limit of Liability /
																<xsl:choose>
													                    <xsl:when test="IsClaimExpensesType='Yes'">
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
												             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Limit of Liability:</fo:block></fo:table-cell>
												             <fo:table-cell border="0.5pt solid black" padding-left="4pt">
												             	<fo:block font-size="11px" font-family="Arial" >
												             		<xsl:value-of select="AggregateLimit" /> Each <fo:inline font-weight="bold">Claim</fo:inline> / <xsl:value-of select="OccuranceLimit" /> Annual Aggregate
												             		
												             	</fo:block>
												             </fo:table-cell>
												           </fo:table-row>
												           <fo:table-row>
												             <fo:table-cell border="0.5pt solid black" padding-left="4pt"><fo:block font-size="11px" font-family="Arial" >Deductible:</fo:block></fo:table-cell>
												             <fo:table-cell border="0.5pt solid black" padding-left="4pt">
												             	<fo:block font-size="11px" font-family="Arial" >
												             		<xsl:value-of select="DeductibleAmount" /> 
												             		<xsl:choose>
													                    <xsl:when test="IsClaimOptionType='Yes'">
													                        Annual Aggregate
						        								        </xsl:when>
													                    <xsl:otherwise>
													                        Each <fo:inline font-weight="bold">Claim</fo:inline> 
						        								        </xsl:otherwise>
													                </xsl:choose>
													               
												             	</fo:block>
												             </fo:table-cell>
												           </fo:table-row>
												         
												           <fo:table-row>
												             <fo:table-cell border="0.5pt solid black" padding-left="4pt">
												             	<fo:block font-size="9px" font-family="Arial" >
												             	 
												             		<xsl:choose>
													                    <xsl:when test="IsMinimumPremium='Y'">
													                        <fo:inline font-weight="bold">Total Premium:</fo:inline>
						        								        </xsl:when>
													                    <xsl:otherwise>
													                        <fo:inline font-weight="bold">Premium Range:</fo:inline> 
						        								        </xsl:otherwise>
													                </xsl:choose>
												             	 
												             	</fo:block>
												             </fo:table-cell>
												             <fo:table-cell border="0.5pt solid black" padding-left="4pt">
												             	<fo:block font-size="9px" font-family="Arial" >
												             	<xsl:choose>
													                    <xsl:when test="IsMinimumPremium='Y'">
													                        <xsl:value-of select="InvoicedPremium" />
						        								        </xsl:when>
													                    <xsl:otherwise>
													                       <xsl:value-of select="EstimatePremiumRange" /> 
						        								        </xsl:otherwise>
													                </xsl:choose>
												             	</fo:block>
												             </fo:table-cell>
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
					<!--  
					<fo:block margin-top="5mm" font-size="11px" font-family="Arial">
					<xsl:if test="response/policy_freeform_01/data/IsFirmReferred != 'Y' ">
						Please note: The total premiums shown above include any applicable taxes.
					</xsl:if>
					</fo:block>					
					-->
					  
					<fo:block id="last-page" font-size="11px"/>						
															
				</fo:flow>
			</fo:page-sequence>
	</fo:root>
 
	</xsl:template>

</xsl:stylesheet>
