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
					<fo:region-body margin-top="0.5in"/>
					<fo:region-before extent="1.0in"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>			

			<fo:page-sequence master-reference="A4-portrait">			
				<fo:static-content flow-name="xsl-region-before">
					<fo:block text-align="right" color="grey">				  	
				  	<!--  	<fo:external-graphic src="../LawyersIns/image/crum_logo.png" content-height="4em" content-width="12em"/>-->
				  		
				  		<fo:external-graphic src="../LawyersIns/image/crum_logo.png" content-height="3em" content-width="10em"/>            	
				  	</fo:block>	
				</fo:static-content>
			
				<fo:static-content flow-name="xsl-region-after">
				<fo:table>
				         <fo:table-column column-width = "60mm" />
				         <fo:table-column column-width = "60mm" />
				         <fo:table-body>
					           <fo:table-row>
					           <fo:table-cell><fo:block text-align="left" font-size="8px" font-family="Arial" color="grey">	
				<xsl:choose>
				<xsl:when test="response/policy_freeform_01/data/StateCode= 'FL'">
					<fo:block text-align="left" font-size="8px" font-family="Arial" color="grey">  LPL FL – 101 (09/10) </fo:block>	
				</xsl:when>
				<xsl:when test="response/policy_freeform_01/data/StateCode= 'CT'">
					<fo:block text-align="left" font-size="8px" font-family="Arial" color="grey">  LPL - CT - 101 (09/10)</fo:block>	
				</xsl:when>
				<xsl:when test="response/policy_freeform_01/data/StateCode= 'NH'">
					<fo:block text-align="left" font-size="8px" font-family="Arial" color="grey">  LPL – NH - 101 (09/10) </fo:block>	
				</xsl:when>
				<xsl:when test="response/policy_freeform_01/data/StateCode= 'IA'">
					<fo:block text-align="left" font-size="8px" font-family="Arial" color="grey">  LPL – IA - 101 (09/10) </fo:block>	
				</xsl:when>
				<xsl:otherwise>
					<fo:block text-align="left" font-size="8px" font-family="Arial" color="grey"> LPL – 101 (08/11)</fo:block>	
				</xsl:otherwise>
				</xsl:choose>
				</fo:block></fo:table-cell>
					            <fo:table-cell><fo:block text-align="center" font-size="8px" font-family="Arial" color="grey">Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block></fo:table-cell>
					           </fo:table-row>
					           </fo:table-body>
					           </fo:table>
				</fo:static-content>
				
			<fo:flow flow-name="xsl-region-body">
				<fo:block margin-top="2mm">
				<fo:table>
					<fo:table-column column-width = "70mm" />
					<fo:table-column column-width = "50mm" />
					<fo:table-body>
						<fo:table-row>
								<fo:table-cell >
									<fo:block font-size="12px" font-weight="bold" font-family="Arial Narrow" >
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
									</fo:block>
								</fo:table-cell>
								<fo:table-cell ><fo:block font-size="9px" font-family="Arial" >
								<fo:table>
				         <fo:table-column column-width = "20mm" />
				         <fo:table-column column-width = "20mm" />
				         <fo:table-column column-width = "80mm" />
				         <fo:table-body>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" font-weight="bold">UNITED STATES FIRE INSURANCE COMPANY</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" font-weight="bold">THE NORTH RIVER INSURANCE COMPANY</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" font-weight="bold">305 MADISON AVENUE, MORRISTOWN, NJ 07962</fo:block></fo:table-cell>
					           </fo:table-row>
					  </fo:table-body>
					</fo:table>
								
								</fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
					<!-- 
					<fo:table>
				         <fo:table-column column-width = "60mm" />
				         <fo:table-column column-width = "50mm" />
				         <fo:table-column column-width = "80mm" />
				         <fo:table-body>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" font-weight="bold">UNITED STATES FIRE INSURANCE COMPANY</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" font-weight="bold">THE NORTH RIVER INSURANCE COMPANY</fo:block></fo:table-cell>
					           </fo:table-row>
					           <fo:table-row>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					             <fo:table-cell ><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					           	 <fo:table-cell ><fo:block font-size="9px" font-family="Arial" font-weight="bold">305 MADISON AVENUE, MORRISTOWN, NJ 07962</fo:block></fo:table-cell>
					           </fo:table-row>
					  </fo:table-body>
					</fo:table>
				
				-->
					<!-- <fo:external-graphic src="../LawyersIns/image/header_bottom.png" content-width="44em"/> --> 
				
				
				</fo:block>	
				<fo:block>
					<fo:table margin-top="3mm">
						<fo:table-column column-width = "200mm"/>
						<fo:table-body>
							<fo:table-row>
					        	<fo:table-cell><fo:block></fo:block></fo:table-cell>
					        </fo:table-row>
					    	<fo:table-row>
					        	<fo:table-cell><fo:block  font-size="12px" font-family="Arial Black" font-weight="bold" text-align="center">LAWYERS PROFESSIONAL LIABILITY APPLICATION</fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
				</fo:block>	
				<fo:block>
					<fo:table margin-top="3mm">
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Tahoma">NOTICE: COVERAGE FOR WHICH THIS APPLICATION IS MADE IS WRITTEN ON A CLAIMS MADE AND REPORTED BASIS MEANING, EXCEPT AS OTHERWISE</fo:block></fo:table-cell>
					        </fo:table-row>
					     </fo:table-body>
					</fo:table>
					<fo:table margin-top="0.5mm" border="0.0pt solid black">
						<fo:table-column column-width="49mm"/>
						<fo:table-column column-width="11mm"/>
						<fo:table-column column-width="32mm"/>
						<fo:table-column column-width="12mm"/>
						<fo:table-column column-width="29mm"/>
						<fo:table-column column-width="12mm"/>
						<fo:table-column column-width="16mm"/>						
						<fo:table-column/>						
						<fo:table-body>   
					        <fo:table-row>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="7px" font-family="Tahoma">PROVIDED, COVERAGE APPLIES ONLY TO</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-style="italic" font-family="Tahoma" font-weight="bold" font-size="7px">&#160;CLAIMS</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="7px" font-family="Tahoma"> FIRST MADE AGAINST THE </fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-style="italic" font-family="Tahoma" font-weight="bold" font-size="7px">INSURED</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="7px" font-family="Tahoma"> AND REPORTED TO THE</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-style="italic" font-family="Tahoma" font-weight="bold" font-size="7px">INSURER</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="7px" font-family="Tahoma"> DURING THE </fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-style="italic" font-family="Tahoma" font-weight="bold" font-size="7px">POLICY PERIOD</fo:block>
					        	
					        	</fo:table-cell>
					        </fo:table-row>
					        
					     </fo:table-body>
					</fo:table>
					<fo:table>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="0.0pt solid black"><fo:block font-family="Tahoma" font-size="7px">
							<xsl:choose>							
					        		<xsl:when test="response/policy_freeform_01/data/StateCode= 'CT'">					        			
					        				OR DURING ANY APPLICABLE EXTENDED REPORTING PERIOD.					        					        			
					        		</xsl:when>
					        		<xsl:otherwise>					        		
					        		</xsl:otherwise>
					        	</xsl:choose>
					        	</fo:block></fo:table-cell>
					        	</fo:table-row>		
					     </fo:table-body>
					</fo:table>
					
					<fo:table margin-top="3mm">  
						<fo:table-column column-width="151mm"/>
						<fo:table-column/>
						<fo:table-body>      
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial">CAREFULLY READ THE ENTIRE POLICY FOR WHICH THIS APPLICATION IS MADE. WORDS AND PHRASES WHICH ARE PRINTED IN</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-style="italic" font-family="Arial" font-weight="bold" font-size="7px"> BOLD ITALIC </fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="0.5mm">  
						<fo:table-column column-width="13mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-style="italic" font-family="Arial" font-weight="bold" font-size="7px">TYPEFACE</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial">&#160;HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV OF THE POLICY.</fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					
					<fo:table margin-top="3mm">  
						<fo:table-column/>    
					    <fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial">THE APPLICATION, ITS ATTACHMENTS AND ALL PREVIOUS APPLICATIONS AND THEIR ATTACHMENTS SHALL SERVE AS THE BASIS OF THE POLICY, AND </fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="0.5mm">  
						<fo:table-column column-width="126mm"/>
						<fo:table-column column-width="12mm"/>
						<fo:table-column/>    
					    <fo:table-body>    
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial">SHALL BECOME PART OF SUCH POLICY SHOULD A POLICY BE ISSUED, AS IF PHYSICALLY ATTACHED. THE</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-style="italic" font-family="Arial" font-weight="bold" font-size="7px">INSURER</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial">RELIES UPON THE APPLICATION IN</fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="0.5mm">  
						<fo:table-column/>    
					    <fo:table-body>     
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial">ISSUING THE POLICY. COMPLETION OF THIS APPLICATION DOES NOT IN ANY WAY IMPLY SUCH COVERAGE UNDER THE POLICY. COVERAGE IS </fo:block></fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial">AFFORDED ONLY IF AND TO THE EXTENT INDICATED BY THE TERMS AND CONDITIONS OF THE POLICY IF ISSUED.</fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
				</fo:block>	
				
				<fo:block font-size="9px" font-family="Arial" font-weight="bold" margin-top="2mm">
					Wherever the word "Applicant" is used, it will be deemed to include all attorneys within the firm and any predecessor firms. 
				</fo:block>
				<fo:block>
					<fo:table margin-top="1mm" border="0.1pt solid black">
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>    
					</fo:table>
				</fo:block>
				
				<fo:block>
				
				<fo:table margin-top="3mm">
				<fo:table-column column-width="5mm"></fo:table-column>
				<fo:table-column column-width="30mm"></fo:table-column>
				<fo:table-column column-width="60mm"></fo:table-column>
				<fo:table-column column-width="55mm"></fo:table-column>
				<fo:table-column column-width="35mm"></fo:table-column>
					<fo:table-body>
						<fo:table-row>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">1.</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">Applicant Firm Name:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/AccountName"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">County:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/CountyDesc"/></fo:block> </fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">Address:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/Address1"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/Address2"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">City:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/City"/></fo:block> </fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">Zip:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/Zip"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/Zip4"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">State:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/StateDesc"/></fo:block> </fo:table-cell>
						</fo:table-row>
						
						<fo:table-row>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">Firm Phone Number:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/WorkPhone"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/WorkExt"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">Email Address:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/AccountEmail"/></fo:block></fo:table-cell>
						</fo:table-row>
						
						<fo:table-row>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">Contact:</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial">Contact Phone:&#160;(if different than above)&#160;</fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/ContactPhone"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/ContactPhoneExt"/></fo:block></fo:table-cell>
						</fo:table-row>
							
					</fo:table-body>
				</fo:table>
				
				<!-- 
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">1. </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Applicant Firm Name:&#160;<xsl:value-of select="response/policy_freeform_01/data/AccountName"/></fo:block></fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" ></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="7px" font-family="Arial" font-style="italic" text-indent="3cm">Legal name of the Applicant to be insured</fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="2mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="90mm"/>
						<fo:table-column column-width="40mm"/>
						<fo:table-column column-width="30mm"/>
						<fo:table-column/>
						<fo:table-body>       
					       <fo:table-row>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Address:&#160;<fo:inline ><xsl:value-of select="response/policy_freeform_01/data/Address1"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/Address2"/></fo:inline></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">City:&#160;<xsl:value-of select="response/policy_freeform_01/data/City"/></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">State:&#160;<xsl:value-of select="response/policy_freeform_01/data/StateDesc"/></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Zip:&#160;<xsl:value-of select="response/policy_freeform_01/data/Zip"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/Zip4"/></fo:block></fo:table-cell>
					        </fo:table-row>
					         <fo:table-row>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="7px" font-family="Arial" font-style="italic">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Principal location</fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        </fo:table-row>
					        
					        
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="2mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="60mm"/>
						<fo:table-column column-width="90mm"/>
						<fo:table-column/>
						<fo:table-body>       
					       <fo:table-row>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Firm Phone Number:&#160;<xsl:value-of select="response/policy_freeform_01/data/WorkPhone"/>&#160;
					        	<xsl:value-of select="response/policy_freeform_01/data/WorkExt"/></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Email Address:&#160;<xsl:value-of select="response/policy_freeform_01/data/AccountEmail"/></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">County:&#160;<xsl:value-of select="response/policy_freeform_01/data/CountyDesc"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="2mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="90mm"/>
						<fo:table-column/>
						<fo:table-body>       
					       <fo:table-row>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Contact:&#160;<xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/></fo:block></fo:table-cell>
					        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Contact Phone:&#160;(if different than above)&#160;<xsl:value-of select="response/policy_freeform_01/data/ContactPhone"/>&#160;&#160;<xsl:value-of select="response/policy_freeform_01/data/ContactPhoneExt"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					  -->
				</fo:block>
				<!-- 
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="30mm"/>
						<fo:table-column column-width="120mm"/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">1. </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Applicant Name: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/AccountName"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>  
					<fo:table margin-top="2mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="30mm"/>
						<fo:table-column column-width="140mm"/>
						<fo:table-body>       
					       <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Address: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/Address1"/><xsl:value-of select="response/policy_freeform_01/data/Address2"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="2mm" border="0.0pt solid black">	
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="30mm"/>
						<fo:table-column column-width="35mm"/>
						<fo:table-column column-width="13mm"/>
						<fo:table-column column-width="35mm"/>
						<fo:table-column column-width="10mm"/>
						<fo:table-column column-width="35mm"/>
						<fo:table-body>	
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">State: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/StateDesc"/></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">County: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/CountyDesc"/></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">City: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/City"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="2mm" border="0.0pt solid black">	
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="30mm"/>
						<fo:table-column column-width="35mm"/>
						<fo:table-column column-width="25mm"/>
						<fo:table-column column-width="25mm"/>
						<fo:table-body>	
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Firm Phone Number: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/WorkPhone"/></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Email Address: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/AccountEmail"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="2mm" border="0.0pt solid black">	
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="30mm"/>
						<fo:table-column column-width="35mm"/>
						<fo:table-column column-width="25mm"/>
						<fo:table-column column-width="25mm"/>
						<fo:table-body>	
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Zip: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/Zip"/></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Contact Phone: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/ContactPhone"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
					<fo:table margin-top="2mm" border="0.0pt solid black">	
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="30mm"/>
						<fo:table-column/>
						<fo:table-body>	
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Contact: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
				</fo:block>
				 -->
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="31mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">2. </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Desired Effective Date: </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate"/></fo:block></fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">      </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">              </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="5px" font-family="Arial" font-style="italic">        MM  /  DD  /  YYYY</fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>  
				</fo:block>	
				
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="65mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">3. </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">What year was the Applicant firm established?</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/firm_freeform_01/data/YearOfFirmEstablished"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>  
				</fo:block>
							
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="105mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">4. </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a. List all of the Applicant's attorneys. </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table> 
					
					<fo:table margin-top="1mm">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell>
					        		<fo:block>
					        			<fo:table margin-top="2mm">
									         <fo:table-column/>	    		         
									         <fo:table-column/>
									         <fo:table-column/>
									         <fo:table-column/>
									         <fo:table-column/>
									         <fo:table-column/>
									        
											 <fo:table-body>
											 	<fo:table-row>							
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Attorney Name</fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">*Attorney Designation</fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">States Licensed to Practice Law</fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Annual Hours Worked</fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Date Joined the Firm</fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold"># of Years in Practice</fo:block></fo:table-cell>
									         	<!--  	
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Yrs. of Continuous Prof. Liab. Cov.</fo:block></fo:table-cell>
									         	-->
									         	</fo:table-row>
											 	<xsl:for-each select="response/attorneys_firm_list_01/data">
									         		<fo:table-row>							
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="AttorneyName"/></fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="AttorneyDesg"/></fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="LicensedStates"/></fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="AnnualWorkedHours"/></fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="AttorneyPriorActDate"/></fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NumberOfYearsInPractice"/></fo:block></fo:table-cell>
									         		<!-- 
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NumberOfYearsMalpracticeCov"/></fo:block></fo:table-cell>
									         		-->
									         	</fo:table-row>								
												</xsl:for-each>           
											</fo:table-body>
										</fo:table>
					        		</fo:block>
					        	</fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table> 
					 
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" font-weight="bold">*Attorney Designations:</fo:block></fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
					        	<fo:table-cell><fo:block></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">A = Associate, E = Employee, IC = Independent Contractor, OC = Of Counsel, P = Partner, RP = Retired Partner</fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>        	
					
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsIndependentContractor = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If the Applicant is a solo practitioner, are you practicing law as an independent contractor working for another law firm?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png" /></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>	    
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsIndependentContractor = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If the Applicant is a solo practitioner, are you practicing law as an independent contractor working for another law firm?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>	    
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="153mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If the Applicant is a solo practitioner, are you practicing law as an independent contractor working for another law firm?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>	  
						</xsl:otherwise>
					</xsl:choose>
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="10mm"/>
						<fo:table-column column-width="154mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", what percentage of gross billings is derived from working under contract with another law firm?</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right"><xsl:value-of select="response/firm_freeform_01/data/PerOfGrossBillingsUnderContract"/>%</fo:block></fo:table-cell>
					        </fo:table-row>
						</fo:table-body>
					</fo:table>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmHaveBackupAttorney = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">
								<fo:table-column column-width="5mm"/>  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If you are a solo practitioner, do you have a backup attorney in case you are unable or unavailable to work for periods of time?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>        
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmHaveBackupAttorney = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">
								<fo:table-column column-width="5mm"/>  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If you are a solo practitioner, do you have a backup attorney in case you are unable or unavailable to work for periods of time?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>        
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">
								<fo:table-column column-width="5mm"/>  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="153mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If you are a solo practitioner, do you have a backup attorney in case you are unable or unavailable to work for periods of time?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>    
						</xsl:otherwise>
					</xsl:choose>
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						
						<fo:table-column column-width="40mm"/>
						<fo:table-column column-width="40mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-indent="10mm">If yes :</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
								
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Name of backup attorney:</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/firm_freeform_01/data/BackupAttorneyName"/></fo:block></fo:table-cell>
					        </fo:table-row>  
					        <fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Address:</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/firm_freeform_01/data/BackupAttorneyAddress"/>&#160;<xsl:value-of select="response/firm_freeform_01/data/BackupAttorneyAddress2"/></fo:block></fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Phone Number:</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/firm_freeform_01/data/BackupAttorneyPhoneNumber"/></fo:block></fo:table-cell>
					        </fo:table-row>
						</fo:table-body>
					</fo:table>	
				</fo:block>	
				
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="105mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">5. </fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">What is the number of non attorney support staff (full and part time combined)?</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="response/firm_freeform_01/data/TotalNumOfNonAttorneyStaff"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>  
				</fo:block>				
					
				<fo:block>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmPracticeInOtherState = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">6.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have offices other than the principal location indicated in question 1?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
						        </fo:table-body>
						    </fo:table>     
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmPracticeInOtherState = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">6.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have offices other than the principal location indicated in question 1?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
						        </fo:table-body>
						    </fo:table>     
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="158mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">6.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have offices other than the principal location indicated in question 1?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
						        </fo:table-body>
						    </fo:table>  
						</xsl:otherwise>
					</xsl:choose>
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>
							
							    <fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", provide the following information for these additional offices from where you practice:</fo:block></fo:table-cell>
						        </fo:table-row>
						   
						</fo:table-body>
					</fo:table>
					<fo:table margin-top="1mm">
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
								<fo:table-cell>
									<fo:block>
										<fo:table margin-top="3mm" border="0.5pt solid black">
											<fo:table-column column-width="35mm"/>
											<fo:table-column column-width="25mm"/>
											<fo:table-column column-width="25mm"/>
											<fo:table-column column-width="25mm"/>
											<fo:table-column column-width="25mm"/>
											<fo:table-column column-width="20mm"/>
											<fo:table-column column-width="20mm"/>
											<fo:table-body>
											    <fo:table-row>
											    	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Address</fo:block></fo:table-cell>
											    	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">City</fo:block></fo:table-cell>
										        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">State</fo:block></fo:table-cell>
										        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Zip</fo:block></fo:table-cell>
										        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold"># of Attorneys</fo:block></fo:table-cell>
										        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold"># of other Employees</fo:block></fo:table-cell>
										        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% of Firm's Billable Hours</fo:block></fo:table-cell>
										        </fo:table-row>
										        <xsl:for-each select="response/primaryloc_firm_list_01/data">
									         		<fo:table-row>							
										         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FPLAddress"/></fo:block></fo:table-cell>
										         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FPLCity"/></fo:block></fo:table-cell>
										         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FPLStateCode"/></fo:block></fo:table-cell>
										         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FPLZip"/></fo:block></fo:table-cell>
										         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NumberOfAttorneys"/></fo:block></fo:table-cell>
										         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NumberOfOtherEmployees"/></fo:block></fo:table-cell>
										         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NumberOfBillabeHours"/>%</fo:block></fo:table-cell>
										         	</fo:table-row>								
												</xsl:for-each>
											</fo:table-body>
										</fo:table>
									</fo:block>
								</fo:table-cell>	
							</fo:table-row>
						</fo:table-body>
					</fo:table>	
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsApplFirmWithDifferentLegalName = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row> 
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Do any of these additional offices listed in 6.a. above operate under a different legal name than provided in Question 1?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						
						<!-- New Add Start 6.b -->
						<xsl:when test="response/firm_freeform_01/data/IsApplFirmWithDifferentLegalName = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Do any of these additional offices listed in 6.a. above operate under a different legal name than provided in Question 1?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="153mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Do any of these additional offices listed in 6.a. above operate under a different legal name than provided in Question 1?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="10mm"/>
						<fo:table-column/>
						<fo:table-body>
						    <fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", provide the different legal name and address associated with it: &#160; <xsl:value-of select="response/firm_freeform_01/data/ApplLegalNameAddressDesc"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
						<!-- New Add End -->
						
						<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsApplRestWithManagement = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row> 
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the responsibility for the Applicant's other offices rest with management at the principal location?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsApplRestWithManagement = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the responsibility for the Applicant's other offices rest with management at the principal location?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="153mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the responsibility for the Applicant's other offices rest with management at the principal location?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="10mm"/>
						<fo:table-column/>
						<fo:table-body>
						    <fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">No</fo:inline>", please describe how the branch office operates and is managed: &#160; <xsl:value-of select="response/firm_freeform_01/data/ApplRestWithManagementDesc"/></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
				</fo:block>
				
				
				<fo:block>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsApplicantProvidesLegalServices = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">7.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Are there any other legal entities under which the Applicant provides legal services? </fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsApplicantProvidesLegalServices = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">7.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Are there any other legal entities under which the Applicant provides legal services? </fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">7.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Are there any other legal entities under which the Applicant provides legal services? </fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", please list: &#160; <xsl:value-of select="response/firm_freeform_01/data/ApplicantProvidesLegalServicesDesc"></xsl:value-of> </fo:block></fo:table-cell>
					        </fo:table-row>
						</fo:table-body>
					</fo:table>  
				</fo:block>	
				
				
				
				
				
				<fo:block>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsAppOfficeSharedWithAttorney = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">8.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Is the Applicant's office or suite shared with attorneys who are not members of the Applicant's firm?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsAppOfficeSharedWithAttorney = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">8.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Is the Applicant's office or suite shared with attorneys who are not members of the Applicant's firm?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">8.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Is the Applicant's office or suite shared with attorneys who are not members of the Applicant's firm?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
					    
				
					
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">9.</fo:block></fo:table-cell>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">Gross fees billed by the Applicant for the last 2 years:</fo:block></fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
					<fo:table margin-top="1mm">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>       
					        <fo:table-row>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell>
					        		<fo:block>
					        			<fo:table margin-top="2mm">
									         <fo:table-column/>	    		         
									         <fo:table-column/>
									         <fo:table-body>
											 	<fo:table-row>							
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Most Current Completed Year</fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Prior Year</fo:block></fo:table-cell>
									         	</fo:table-row>
									         	<fo:table-row>							
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center">FYE:&#160;&#160;<xsl:value-of select="response/YearEndDate_0"/></fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center">FYE:&#160;&#160;<xsl:value-of select="response/YearEndDate_1"/></fo:block></fo:table-cell>
									         		
									         	</fo:table-row>
									         	<fo:table-row>							
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/Amount_0"/></fo:block></fo:table-cell>
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/Amount_1"/></fo:block></fo:table-cell>
									         	</fo:table-row> 
									        </fo:table-body>
										</fo:table>
					        		</fo:block>
					        	</fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
				</fo:block>			
				
				
				
				<!--  
				<fo:block>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmInvolvedInClassActionCase = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">10.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Has the Applicant rendered professional services in any mass tort/class action cases within the past five years?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmInvolvedInClassActionCase = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">10.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Has the Applicant rendered professional services in any mass tort/class action cases within the past five years?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">10.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Has the Applicant rendered professional services in any mass tort/class action cases within the past five years?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
				-->
				
				
				<fo:block>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientInEntertainmentInd = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">10.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have public figures as clients (e.g. Entertainment, Politics or Sports)?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientInEntertainmentInd = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">10.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have public figures as clients (e.g. Entertainment, Politics or Sports)?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">10.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have public figures as clients (e.g. Entertainment, Politics or Sports)?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					
					
					
					
					<!--  
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="154mm"/>
						<fo:table-body>
						    <fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>" please complete the <fo:inline font-weight="bold" font-style="italic">Public Figure Supplement.</fo:inline></fo:block></fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>
				-->
				
				</fo:block>
				
				
				
				
				<!-- Start -->
				
				<fo:block>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalService = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
						    				<fo:table-cell><fo:block font-size="9px" font-family="Arial">11.</fo:block></fo:table-cell>
					        				<fo:table-cell><fo:block font-size="9px" font-family="Arial">At any time in the past five years, has the Applicant or any attorney of the Applicant (regardless of what firm the attorney was employed by at such time) provided legal services related to mass tort/class action, securities transactions, investment vehicles or ventures, hedge or other investment funds, money or investment management or investments with clients?</fo:block></fo:table-cell>
					        				<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
					       			 </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalService = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">11.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">At any time in the past five years, has the Applicant or any attorney of the Applicant (regardless of what firm the attorney was employed by at such time) provided legal services related to mass tort/class action, securities transactions, investment vehicles or ventures, hedge or other investment funds, money or investment management or investments with clients?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">11.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">At any time in the past five years, has the Applicant or any attorney of the Applicant (regardless of what firm the attorney was employed by at such time) provided legal services related to mass tort/class action, securities transactions, investment vehicles or ventures, hedge or other investment funds, money or investment management or investments with clients?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", please indentify and explain the relevant legal services &#160; <xsl:value-of select="response/firm_freeform_01/data/IsFirmProvidedLegalServiceDesc"></xsl:value-of> </fo:block></fo:table-cell>
					        </fo:table-row>
						</fo:table-body>
					</fo:table>  
				
				</fo:block>
				
				
				
				<!-- End -->
				
				<!--  
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column column-width="159mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
						    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">11.</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">At any time in the past five years, has the Applicant or any attorney of the Applicant (regardless of what firm the attorney was employed by at such time) provided legal services related to mass tort/class action, securities transactions, investment vehicles or ventures, hedge or other investment funds, money or investment management or investments with clients?</fo:block></fo:table-cell>
					        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right"></fo:block></fo:table-cell>
					        </fo:table-row>
						</fo:table-body>
					</fo:table>
					
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToSecurity = 'Y'">
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">securities transactions?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToSecurity = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">securities transactions?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">securities transactions?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>	
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToInvestment = 'Y'">
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">investment vehicles or ventures?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToInvestment = 'N'">
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">investment vehicles or ventures?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">investment vehicles or ventures?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>	
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToMoney = 'Y'">
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">money or investment management?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToMoney = 'N'">
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">money or investment management?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">money or investment management?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToInvClient = 'Y'">
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">d.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">investments with clients?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceToInvClient = 'N'">
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">d.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">investments with clients?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="2mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="159mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">d.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">investments with clients?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					</fo:block>
					-->
					
				<fo:block>
					<xsl:choose>
						<xsl:when test="response/firm_freeform_01/data/IsFirmEquityInterestInTitleInsurance = 'Y'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
						    				<fo:table-cell><fo:block font-size="9px" font-family="Arial">12.</fo:block></fo:table-cell>
					        				<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does anyone affiliated with the Applicant maintain any equity interest in a Title Insurance Agency?</fo:block></fo:table-cell>
					        				<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
					       			 </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:when test="response/firm_freeform_01/data/IsFirmEquityInterestInTitleInsurance = 'N'">
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">12.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does anyone affiliated with the Applicant maintain any equity interest in a Title Insurance Agency?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:when>
						<xsl:otherwise>
							<fo:table margin-top="3mm" border="0.0pt solid black">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="154mm"/>
								<fo:table-column/>
								<fo:table-body>
								    <fo:table-row>
								    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">12.</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does anyone affiliated with the Applicant maintain any equity interest in a Title Insurance Agency?</fo:block></fo:table-cell>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>
						</xsl:otherwise>
					</xsl:choose>
					
				
				</fo:block>
					
					
						
				
				<fo:block>
					<fo:table margin-top="3mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">13.</fo:block></fo:table-cell>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">Enter the percentage of billable hours for each area below in the past fiscal year. Indicate percentages in whole numbers next to the type of law the Applicant practices:</fo:block></fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
					<fo:table margin-top="1mm">
							<fo:table-column column-width="70mm"/>
							<fo:table-column column-width="25mm"/>
							<fo:table-column column-width="65mm"/>
							<fo:table-column column-width="25mm"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block></fo:table-cell>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block></fo:table-cell>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
								</fo:table-row>
								<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;A. Anti-trust/Trade Regulation</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_0"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;N. Immigration</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_13"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;B. Arbitration</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_1"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;O. Investment Counseling/Money Mgt.</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_14"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;C. Bankruptcy</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_2"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;P. Natural Resource</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_15"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;D. Civil Rights/Employment</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_3"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Q. Personal Contracts</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_16"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;E. Corporate / Commercial </fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_4"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;R. Plaintiff Litigation (1)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_17"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;F. Copyright / Trademark /Patent </fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_5"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;S. Public Utilities</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_18"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;G. Defense / Criminal Litigation</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_6"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;T. Real Estate Commercial (2)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_19"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;H. Domestic Relations </fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_7"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;U. Real Estate Residential (3)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_25"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;I. Elder Law</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_8"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;V. Securities / Bonds</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_20"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;J. Environmental</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_9"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;W. Tax</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_21"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;K. ERISA / Pensions</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_10"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;X. Title Opinions</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_22"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;L. Financial Institution</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_11"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Y. Wills, Trusts, Estates (4)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_23"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;M. Government</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_12"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;Z. Other (please describe)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_24"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
					         		
					         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;<xsl:value-of select="response/AOP_CommentDesc_24"/></fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         	
					         	
					         	
					         		
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-weight="bold" font-family="Arial">&#160;&#160;Total (must equal 100%)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aop_total"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>							
							</fo:table-body>
						</fo:table>
					
					
					<fo:table margin-top="1mm" border="0.0pt solid black">  
						<fo:table-column column-width="5mm"/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">If the Applicant practices in any area(s) above with a numerical notation(s), complete the associated <fo:inline font-weight="bold">Supplement</fo:inline> as follows:</fo:block></fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
					<fo:table margin-top="1mm" border="0.0pt solid black">
						<fo:table-column column-width="5mm"/>  
						<fo:table-column column-width="60mm"/>
						<fo:table-column/>
						<fo:table-column/>
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">(1) Plaintiff Litigation</fo:block></fo:table-cell>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">(2) Real Estate Commercial</fo:block></fo:table-cell>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">(3) Real Estate Residential</fo:block></fo:table-cell>
								<fo:table-cell><fo:block font-size="9px" font-family="Arial">(4) Wills, Trusts, Estates</fo:block></fo:table-cell>
							</fo:table-row>
							
						</fo:table-body>	
					</fo:table>       		
				</fo:block>
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientMoreThan25PercentOfBilling = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">14.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">At any time during the last 5 years has any client of
																		the Applicant represented more than 30% of the
																		Applicant's annual revenue?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
							<xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientMoreThan25PercentOfBilling = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">14.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">At any time during the last 5 years has any client of
																		the Applicant represented more than 30% of the
																		Applicant's annual revenue?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">14.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">At any time during the last 5 years has any client of
																		the Applicant represented more than 30% of the
																		Applicant's annual revenue?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:otherwise>
						</xsl:choose>
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If Yes, please indicate percentage of revenue from largest revenue client (include client’s affiliated entities):<xsl:value-of select="response/firm_freeform_01/data/PercentFromFirstLargestRevenueClient"/>%</fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>					
						<fo:table>
							<fo:table-column column-width="5mm"></fo:table-column>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block></fo:block></fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<fo:table margin-top="1mm" border="0.5pt solid black">  
												<fo:table-column/>												
												<fo:table-column/>
												<fo:table-body>       
											        <fo:table-row>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Client Name</fo:block></fo:table-cell>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/firm_freeform_01/data/ClientNameFirstLargestRevenueClient"/></fo:block></fo:table-cell>
											         </fo:table-row>					         	 					
										         	<fo:table-row>							
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Services Rendered</fo:block></fo:table-cell>
														<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/firm_freeform_01/data/ServicesRenderedFirstLargestRevenueClient"/></fo:block></fo:table-cell>
											        </fo:table-row>	
											        <fo:table-row>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Year Rendered</fo:block></fo:table-cell>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/firm_freeform_01/data/DateRenderedFirstLargestRevenueClient"/></fo:block></fo:table-cell>
											        </fo:table-row>
												</fo:table-body>
								  			</fo:table>	
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>	
						
						
						
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Please indicate percentage of revenue from second largest revenue client (include client’s affiliated entities):<xsl:value-of select="response/firm_freeform_01/data/PercentFromSecondLargestRevenueClient"/>%</fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>					
						<fo:table>
							<fo:table-column column-width="5mm"></fo:table-column>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block></fo:block></fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<fo:table margin-top="1mm" border="0.5pt solid black">  
												<fo:table-column/>												
												<fo:table-column/>
												<fo:table-body>       
											        <fo:table-row>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Client Name</fo:block></fo:table-cell>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/firm_freeform_01/data/ClientNameSecondLargestRevenueClient"/></fo:block></fo:table-cell>
											         </fo:table-row>					         	 					
										         	<fo:table-row>							
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Services Rendered</fo:block></fo:table-cell>
														<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/firm_freeform_01/data/ServicesRenderedSecondLargestRevenueClient"/></fo:block></fo:table-cell>
											        </fo:table-row>	
											        <fo:table-row>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Year Rendered</fo:block></fo:table-cell>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/firm_freeform_01/data/DateRenderedSecondLargestRevenueClient"/></fo:block></fo:table-cell>
											        </fo:table-row>
												</fo:table-body>
								  			</fo:table>	
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>		
						
						
						
							
					</fo:block>
					
					
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsPolicyProhibitAttorneyWithInvestment = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="155mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">15.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have a policy prohibiting an attorney
																with an investment in a client from working on
																transactions of such clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsPolicyProhibitAttorneyWithInvestment = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="155mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">15.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have a policy prohibiting an attorney
																with an investment in a client from working on
																transactions of such clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="154mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">15.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have a policy prohibiting an attorney
																with an investment in a client from working on
																transactions of such clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	    
					
					
					
					
					
					
					
					
					
					
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmProvideInvestmentAdvice = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">16.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant or any member of the firm provide investment advice or make discretionary investments?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsFirmProvideInvestmentAdvice = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">16.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant or any member of the firm provide investment advice or make discretionary investments?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">16.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant or any member of the firm provide investment advice or make discretionary investments?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	 
					
					   
					<!-- 
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmPersServedAsOfficerInJointVenture = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="159mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">16.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">In the past 5 years, has the Applicant or its
																			personnel served as an officer, director, partner,
																			manager, employee, committee member of any client?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsFirmPersServedAsOfficerInJointVenture = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="159mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">16.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">In the past 5 years, has the Applicant or its
																			personnel served as an officer, director, partner,
																			manager, employee, committee member of any client?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="159mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">16.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">In the past 5 years, has the Applicant or its
																			personnel served as an officer, director, partner,
																			manager, employee, committee member of any client?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:otherwise>
						</xsl:choose>
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>" to question 15 or 16 please complete the information below:</fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:table>
							<fo:table-column column-width="5mm"></fo:table-column>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block></fo:block></fo:table-cell>
									<fo:table-cell>
										<fo:block>
											<fo:table margin-top="1mm" border="0.5pt solid black">  
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-body>       
											        <fo:table-row>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Attorney Name</fo:block></fo:table-cell>
											        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Entity Name</fo:block></fo:table-cell>
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Nature of Client Business</fo:block></fo:table-cell>
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Position Held</fo:block></fo:table-cell>
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Percent Equity Interest</fo:block></fo:table-cell>
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Service Provided by Firm</fo:block></fo:table-cell>
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Does the individual listed perform these services?</fo:block></fo:table-cell>
											         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Is Director/Officer Insurance in force?</fo:block></fo:table-cell>
											         </fo:table-row>
					         	 					<xsl:for-each select="response/personnel_practice_list_01/data">
										         		<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FirmMember"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="EntityName"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NatureOfClientBusiness"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="PositionHeld"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="PercentEquityInterest"/>%</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FirmServices"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:choose>
														<xsl:when test="IsIndividualPerformServices = 'Y'">
														<fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png" /></fo:block></xsl:when>
														<xsl:when test="IsIndividualPerformServices = 'N'"><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png" /></fo:block></xsl:when>
														</xsl:choose></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center">
											         		<xsl:choose>
											         		<xsl:when test="IsDirOffInsInForce = 'Y'"><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png" /></fo:block></xsl:when>
														<xsl:when test="IsDirOffInsInForce = 'N'"><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png" /></fo:block></xsl:when>
														</xsl:choose></fo:block></fo:table-cell>
											         	</fo:table-row>								
													</xsl:for-each>
												</fo:table-body>
								  			</fo:table>	
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>	    
					-->
					
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmHaveProcForFormersClients = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">17.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Do all attorneys follow established procedures for
																identifying and resolving potential or actual conflicts
																of interest including cross - checking of former,
																existing or potential clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsFirmHaveProcForFormersClients = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">17.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Do all attorneys follow established procedures for
																identifying and resolving potential or actual conflicts
																of interest including cross - checking of former,
																existing or potential clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">17.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Do all attorneys follow established procedures for
																identifying and resolving potential or actual conflicts
																of interest including cross - checking of former,
																existing or potential clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	 
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmHaveIndepDockets = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">18.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant maintain a central docket control or diary system?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsFirmHaveIndepDockets = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">18.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant maintain a central docket control or diary system?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">18.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant maintain a central docket control or diary system?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	     
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmRequireEngagementLetter = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">19.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant use engagement letters including fee
																agreements on all new matters undertaken by the firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						  	<xsl:when test="response/firm_freeform_01/data/IsFirmRequireEngagementLetter = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">19.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant use engagement letters including fee
																agreements on all new matters undertaken by the firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">19.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant use engagement letters including fee
																agreements on all new matters undertaken by the firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	    
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsNonEngagementLetterIssueToFirm = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">20.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Are declinations or non-engagement letters, which
																include time sensitive dates, issued on all matters
																declined by the Applicant?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsNonEngagementLetterIssueToFirm = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">20.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Are declinations or non-engagement letters, which
																include time sensitive dates, issued on all matters
																declined by the Applicant?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">20.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Are declinations or non-engagement letters, which
																include time sensitive dates, issued on all matters
																declined by the Applicant?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	      
					
					
					<!--  
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsPolicyProhibitAttorneyWithInvestment = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="155mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">21.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have a policy prohibiting an attorney
																with an investment in a client from working on
																transactions of such clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsPolicyProhibitAttorneyWithInvestment = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="155mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">21.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have a policy prohibiting an attorney
																with an investment in a client from working on
																transactions of such clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="154mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">21.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the Applicant have a policy prohibiting an attorney
																with an investment in a client from working on
																transactions of such clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	    
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsEmplRelativeToConfInfomation = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="158mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">22.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does Applicant have procedures which address the conduct
																of employees relative to the handling confidential information of clients?
																</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsEmplRelativeToConfInfomation = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="159mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">22.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does Applicant have procedures which address the conduct
																of employees relative to the handling confidential information of clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="158mm"/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">22.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does Applicant have procedures which address the conduct
																of employees relative to the handling confidential information of clients?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	    
					-->
					
					
					<fo:block>
						<fo:table margin-top="3mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column column-width="159mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">21.</fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">What percentage of Applicant's accounts receivable is
															outstanding more than 90 days?</fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right"><xsl:value-of select="response/firm_freeform_01/data/PercentofApplAcctRcbl"></xsl:value-of>%</fo:block></fo:table-cell>
						        </fo:table-row>
						    </fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsApplInitiatedLawsuitForFirm = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">22.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">In the last 3 years has the Applicant initiated lawsuits or arbitration procedures to enforce the collection of unpaid fees of any client?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsApplInitiatedLawsuitForFirm = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">22.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">In the last 3 years has the Applicant initiated lawsuits or arbitration procedures to enforce the collection of unpaid fees of any client?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">22.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">In the last 3 years has the Applicant initiated lawsuits or arbitration procedures to enforce the collection of unpaid fees of any client?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", please complete the <fo:inline font-weight="bold" font-style="italic">Fee Suit Supplement</fo:inline></fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>	    
					
					<fo:block>
						<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;CLAIM / INCIDENT INFORMATION </fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block margin-top="1mm"></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
						
					<fo:block>
						<fo:table margin-top="3mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">23.</fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">After inquiry of all attorneys and staff of the
																			Applicant, within the past 5 years have any past or
																			present personnel:</fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsPersonnelBeSubOfAnyInvest = 'Y'">
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">been the subject of any regulatory investigation
																							or inquiry; suspended from practice; or charged,
																							indicted, plead no contest (&quot;nolo
																							contendere&quot;), plead guilty or been convicted
																							of any felony charge?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:when test="response/firm_freeform_01/data/IsPersonnelBeSubOfAnyInvest = 'N'">
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">been the subject of any regulatory investigation
																							or inquiry; suspended from practice; or charged,
																							indicted, plead no contest (&quot;nolo
																							contendere&quot;), plead guilty or been convicted
																							of any felony charge?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:otherwise>
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">been the subject of any regulatory investigation
																							or inquiry; suspended from practice; or charged,
																							indicted, plead no contest (&quot;nolo
																							contendere&quot;), plead guilty or been convicted
																							of any felony charge?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	  
							</xsl:otherwise>
						</xsl:choose>
						<fo:table margin-top="2mm" border="0.0pt solid black">  
							<fo:table-column column-width="10mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", please provide details and dates: &#160;&#160;&#160;<xsl:value-of select="response/firm_freeform_01/data/PersonnelBeSubOfAnyInvestDetails"></xsl:value-of>&#160;<xsl:value-of select="response/firm_freeform_01/data/PersonnelBeSubOfAnyInvestDate"></xsl:value-of></fo:block></fo:table-cell>
						        </fo:table-row>
						      <!--   <fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", provide details:&#160;<xsl:value-of select="response/firm_freeform_01/data/PersonnelBeSubOfAnyInvestDetails"></xsl:value-of></fo:block></fo:table-cell>
						        </fo:table-row>--> 
							</fo:table-body>
						</fo:table>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsLawyerProfLiabClaimAgntAppl = 'Y'">
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">know of any professional liability claims made
																			against the Applicant, its affiliates or its
																			personnel?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:when test="response/firm_freeform_01/data/IsLawyerProfLiabClaimAgntAppl = 'N'">
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">know of any professional liability claims made
																			against the Applicant, its affiliates or its
																			personnel?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:otherwise>
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">know of any professional liability claims made
																			against the Applicant, its affiliates or its
																			personnel?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>
							</xsl:otherwise>
						</xsl:choose>	
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsAnyActOmmBecomeClaimAgntFirm = 'Y'">
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">become aware of any act, error or omission or fee
																			dispute which might become the basis of a claim
																			against the Applicant or its personnel?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:when test="response/firm_freeform_01/data/IsAnyActOmmBecomeClaimAgntFirm = 'N'">
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">become aware of any act, error or omission or fee
																			dispute which might become the basis of a claim
																			against the Applicant or its personnel?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:otherwise>
								<fo:table margin-top="2mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">c.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">become aware of any act, error or omission or fee
																			dispute which might become the basis of a claim
																			against the Applicant or its personnel?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	 
							</xsl:otherwise>
						</xsl:choose>	
						<fo:table margin-top="3mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" font-weight="bold">NOTE: THE POLICY FOR WHICH THIS APPLICATION IS BEING MADE SHALL NOT APPLY TO ANY INCIDENTS OR CLAIMS DETAILED OR WHICH SHOULD HAVE BEEN DETAILED IN THE QUESTION 23 a, b or c ABOVE.</fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:table margin-top="3mm" border="0.0pt solid black">  
							<fo:table-column column-width="10mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>" to 23 b or c above, complete the <fo:inline font-weight="bold" font-style="italic">CLAIM SUPPLEMENT</fo:inline> for each claim or potential claim</fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>	
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsAttorneyDeclineForProfLiability = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">24.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Within the past five years has the Applicant or its
																			attorneys been declined, canceled, or non-renewed for
																			professional liability insurance for any reason? (Not
																			applicable to Missouri)</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsAttorneyDeclineForProfLiability = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">24.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Within the past five years has the Applicant or its
																			attorneys been declined, canceled, or non-renewed for
																			professional liability insurance for any reason? (Not
																			applicable to Missouri)</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">24.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Within the past five years has the Applicant or its
																			attorneys been declined, canceled, or non-renewed for
																			professional liability insurance for any reason? (Not
																			applicable to Missouri)</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", please provide dates and reasons: <xsl:value-of select="response/firm_freeform_01/data/AttorneyDeclineForProfLiabilityDates"></xsl:value-of>&#160;<xsl:value-of select="response/firm_freeform_01/data/AttorneyDeclineForProfLiabilityReasons"></xsl:value-of></fo:block></fo:table-cell>
						        </fo:table-row>
						    </fo:table-body>
						</fo:table>
					</fo:block>	 
					
					<fo:block>
						<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;PRIOR INSURANCE INFORMATION </fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block margin-top="1mm"></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'Y'">
								<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial">25.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does Applicant currently carry professional liability
																			insurance?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'N'">
								<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial">25.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does Applicant currently carry professional liability
																			insurance?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	        
							</xsl:when>
							<xsl:otherwise>
								<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial">25.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does Applicant currently carry professional liability insurance?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
									</fo:table-body>
								</fo:table>	
							</xsl:otherwise>
						</xsl:choose>	
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", provide the information requested below:</fo:block></fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="10mm"/>
							<fo:table-column column-width="80mm"/>
							<fo:table-column/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Insurance Company(not broker/agent):&#160;<xsl:value-of select="response/coverage_freeform_1/data/InsuranceCompanyNamePross"/></fo:block></fo:table-cell>
						        
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial">Policy Expiration Date:&#160;<xsl:value-of select="response/coverage_freeform_1/data/PolicyExpirationDatePross"/></fo:block></fo:table-cell>
						        </fo:table-row>
						        
						        <fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">  </fo:block></fo:table-cell>
						        
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell><fo:table-cell><fo:block font-size="5px" font-family="Arial" text-align="right"> MM / DD / YYYY </fo:block></fo:table-cell>
						        </fo:table-row>
						    </fo:table-body>    
						</fo:table>	
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="10mm"/>
							<fo:table-column column-width="55mm"/>
							<fo:table-column column-width="5mm"/>
							<fo:table-column column-width="40mm"/>
							<fo:table-column column-width="8mm"/>
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Limits of Liability: $<xsl:value-of select="response/coverage_freeform_1/data/LimitAmount"/></fo:block></fo:table-cell>
						        	<fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial">
							        		<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_1/data/IsClaimExpLiability='DR'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>								        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>
							        			</xsl:otherwise>
							        		</xsl:choose>
							        	</fo:block>
							        </fo:table-cell>
							        <fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial">Defense expenses <fo:inline font-weight="bold">reduce</fo:inline> limits of liability</fo:block>
							        </fo:table-cell>
							        <fo:table-cell>
							        	<fo:block font-size="9px" font-weight="bold" font-family="Arial">OR</fo:block>
							        </fo:table-cell>
						        	<fo:table-cell>
						        		<fo:block font-size="9px" font-family="Arial" text-align="left">
						        			<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_1/data/IsClaimExpLiability='DA'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>								        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>	
							        			</xsl:otherwise>
							        		</xsl:choose>
						        		</fo:block>
						        	</fo:table-cell>
						        	<fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial">Defense expenses are paid in <fo:inline font-weight="bold">addition to</fo:inline> limits of liability</fo:block>
							        </fo:table-cell>
						        </fo:table-row>			        
							</fo:table-body>
						</fo:table>	
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="10mm"/>
							<fo:table-column column-width="40mm"/>
							<fo:table-column column-width="25mm"/>
							<fo:table-column column-width="8mm"/>
							<fo:table-column column-width="32mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Deductible: $<xsl:value-of select="response/coverage_freeform_1/data/DeductibleAmount"/></fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial">
							        		<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_1/data/IsPerClaim='PC'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>	Per Claim 							        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>	Per Claim 
							        			</xsl:otherwise>
							        		</xsl:choose>	
							        	</fo:block>
							        </fo:table-cell>
							        <fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial" font-weight="bold">OR</fo:block>
							        </fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
						        		<fo:block font-size="9px" font-family="Arial" text-align="left">
						        			<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_1/data/IsPerClaim='AA'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>	Annual Aggregate							        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>	Annual Aggregate
							        			</xsl:otherwise>
							        		</xsl:choose>
						        		</fo:block>
						        	</fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
						        		<fo:block font-size="9px" font-family="Arial" text-align="left">
						        			<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_1/data/IsProfDefenceExpense='Y'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>	Deductible does not apply to defense expenses							        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>	Deductible does not apply to defense expenses
							        			</xsl:otherwise>
							        		</xsl:choose>
						        		</fo:block>
						        	</fo:table-cell>
						        </fo:table-row>
						        <fo:table-row>
									<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial">
							        			
							        	</fo:block>
							        </fo:table-cell>
							        <fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial" font-weight="bold"></fo:block>
							        </fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
						        		<fo:block font-size="9px" font-family="Arial" text-align="left">
						        			
						        		</fo:block>
						        	</fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
						        		<fo:block font-size="9px" font-family="Arial" font-style="italic">
						        			&#160;&#160;&#160;&#160;(first dollar defense)
						        		</fo:block>
						        	</fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>	
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="10mm"/>
							<fo:table-column/>
							<fo:table-body>								
						        <fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"><xsl:choose>
							        			<xsl:when test="response/coverage_freeform_1/data/IsPriorActDateFull='Y'">
														How many years has your Firm had continuous insurance coverage? <xsl:value-of select="response/coverage_freeform_1/data/FirmYear"/>	Premium:<xsl:value-of select="response/coverage_freeform_1/data/ProInsPremium"/>					        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				Indicate the prior acts date (also known as retroactive date) for your policy: Prior Acts Date <xsl:value-of select="response/coverage_freeform_1/data/PriorActDatePross"/> &#160;Premium:<xsl:value-of select="response/coverage_freeform_1/data/ProInsPremium"/>
							        			</xsl:otherwise>
							        		</xsl:choose> 
						        	
						        	</fo:block></fo:table-cell>
						        </fo:table-row>
						         <fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-indent="12cm" ><xsl:choose>
							        			<xsl:when test="response/coverage_freeform_1/data/IsPriorActDateFull='Y'">
																			        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				       <fo:inline font-size="5px" font-style="italic"> MM / DD / YYYY</fo:inline>                                                          
							        			</xsl:otherwise>
							        		</xsl:choose> 
						        	
						        	</fo:block></fo:table-cell>
						        </fo:table-row>
						        
						        
						        
							</fo:table-body>
						</fo:table>		
					</fo:block>
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsPolicyIncludeLateralHireCov = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">26.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the policy above include lateral hire coverage for
																any of the Applicant's current attorneys?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsPolicyIncludeLateralHireCov = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">26.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the policy above include lateral hire coverage for
																any of the Applicant's current attorneys?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">26.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the policy above include lateral hire coverage for
																any of the Applicant's current attorneys?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
					</fo:block>	
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsPolicyExcludesCoverage = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">27.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the policy above, exclude coverage for any
																			attorney, predecessor firms, firm affiliates,
																			clients, specific engagements or other circumstances?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsPolicyExcludesCoverage = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">27.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the policy above, exclude coverage for any
																			attorney, predecessor firms, firm affiliates,
																			clients, specific engagements or other circumstances?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">27.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Does the policy above, exclude coverage for any
																			attorney, predecessor firms, firm affiliates,
																			clients, specific engagements or other circumstances?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", please describe:&#160;<xsl:value-of select="response/firm_freeform_01/data/PolicyExcludeCoverageForAffiliatesDesc"></xsl:value-of></fo:block></fo:table-cell>
						        </fo:table-row>
						    </fo:table-body>
						</fo:table>
					</fo:block>	
					
					<fo:block>
						<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;COVERAGE SELECTION </fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block margin-top="1mm"></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:table margin-top="1mm">
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block font-size="8px" font-weight="Arial">Indicate your desired coverage selection:</fo:block></fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column column-width="55mm"/>
							<fo:table-column column-width="5mm"/>
							<fo:table-column column-width="40mm"/>
							<fo:table-column column-width="8mm"/>
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">28.</fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Limits of Liability: $<xsl:value-of select="response/coverage_freeform_2/data/LimitAmount"/></fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial">
							        		<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_2/data/IsClaimExpensesType='N'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>								        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>
							        			</xsl:otherwise>
							        		</xsl:choose>	
							        	</fo:block>
							        </fo:table-cell>
							        <fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial">Defense expenses <fo:inline font-weight="bold">reduce</fo:inline> limits of liability</fo:block>
							        </fo:table-cell>
							        <fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial" font-weight="bold">&#160;OR</fo:block>
							        </fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
						        		<fo:block font-size="9px" font-family="Arial" text-align="left">
						        			<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_2/data/IsClaimExpensesType='Y'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>								        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>
							        			</xsl:otherwise>
							        		</xsl:choose>
						        		</fo:block>
						        	</fo:table-cell>
						        	<fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial">Defense expenses are paid in <fo:inline font-weight="bold">addition to</fo:inline> limits of liability</fo:block>
							        </fo:table-cell>
						        </fo:table-row>
						        
						        <fo:table-row>
									<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="7px" font-family="Arial" text-indent="23mm" font-style="italic">per claim / aggregate</fo:block></fo:table-cell>
						        	<fo:table-cell >
							        	<fo:block font-size="9px" font-family="Arial">
							        		
							        	</fo:block>
							        </fo:table-cell>
							        <fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial"></fo:block>
							        </fo:table-cell>
							        <fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial" ></fo:block>
							        </fo:table-cell>
						        	<fo:table-cell>
						        		<fo:block font-size="9px" font-family="Arial" >
						        			
						        		</fo:block>
						        	</fo:table-cell>
						        	<fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial"></fo:block>
							        </fo:table-cell>
						        </fo:table-row>
						        
						        
						        
						        
						        
						        
						        
							</fo:table-body>
						</fo:table>	
					</fo:block>
					<fo:block>	
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column column-width="40mm"/>
							<fo:table-column column-width="22mm"/>
							<fo:table-column column-width="8mm"/>
							<fo:table-column column-width="35mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">29.</fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black"><fo:block font-size="9px" font-family="Arial">Deductible: $<xsl:value-of select="response/coverage_freeform_2/data/DeductibleAmount"/></fo:block></fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial">
							        		<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_2/data/IsClaimOptionType='N'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>	Per Claim 							        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>	Per Claim 
							        			</xsl:otherwise>
							        		</xsl:choose>	
							        	</fo:block>
							        </fo:table-cell>
							        <fo:table-cell border="0.0pt solid black">
							        	<fo:block font-size="9px" font-family="Arial" font-weight="bold">OR</fo:block>
							        </fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
						        		<fo:block font-size="9px" font-family="Arial" text-align="left">
						        			<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_2/data/IsClaimOptionType='Y'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>	Annual Aggregate							        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>	Annual Aggregate
							        			</xsl:otherwise>
							        		</xsl:choose>
						        		</fo:block>
						        	</fo:table-cell>
						        	<fo:table-cell border="0.0pt solid black">
						        		<fo:block font-size="9px" font-family="Arial" text-align="left">
						        			<xsl:choose>
							        			<xsl:when test="response/coverage_freeform_2/data/IsDollarDefense='Y'">
													<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>	Deductible does not apply to defense expenses							        			
							        			</xsl:when>
							        			<xsl:otherwise>
							        				<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>	Deductible does not apply to defense expenses
							        			</xsl:otherwise>
							        		</xsl:choose>
						        		</fo:block>
						        	</fo:table-cell>
						        </fo:table-row>
						        <fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
						        	<fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial"></fo:block>
							        </fo:table-cell>
							        <fo:table-cell>
							        	<fo:block font-size="9px" font-family="Arial"></fo:block>
							        </fo:table-cell>
						        	<fo:table-cell>
						        		<fo:block font-size="9px" font-family="Arial"></fo:block>
						        	</fo:table-cell>
						        	<fo:table-cell>
						        		<fo:block font-size="9px" font-family="Arial" font-style="italic">&#160;&#160;&#160;&#160;(first dollar defense)</fo:block>
						        	</fo:table-cell>
						        </fo:table-row>
							</fo:table-body>
						</fo:table>	
					</fo:block>	
					
					<fo:block>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmMergedWithOtherFirm = 'Y'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="155mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">30.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Have you acquired or merged with another firm in the
																			past 10 years?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsFirmMergedWithOtherFirm = 'N'">
								<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">30.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Have you acquired or merged with another firm in the
																			past 10 years?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="3mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="150mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">30.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Have you acquired or merged with another firm in the
																			past 10 years?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:otherwise>
						</xsl:choose>
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsApplIntToFinanAssests = 'Y'">
								<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", was the Applicant the majority successor in 
																										interest to the financial assets and
																										liabilities of the acquired or merged firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsApplIntToFinanAssests = 'N'">
								<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", was the Applicant the majority successor in
																										interest to the financial assets and
																										liabilities of the acquired or merged firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">a.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", was the Applicant the majority successor in
																										interest to the financial assets and
																										liabilities of the acquired or merged firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:otherwise>
						</xsl:choose>    
						<xsl:choose>
							<xsl:when test="response/firm_freeform_01/data/IsFirmCoverageForPreceedorFirms = 'Y'">
								<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant desire coverage for this
																										entity as a predecessor firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:when test="response/firm_freeform_01/data/IsFirmCoverageForPreceedorFirms = 'N'">
								<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant desire coverage for this
																										entity as a predecessor firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table> 
						    </xsl:when>
						    <xsl:otherwise>
						    	<fo:table margin-top="1mm" border="0.0pt solid black">  
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="5mm"/>
									<fo:table-column column-width="145mm"/>
									<fo:table-column column-width="30mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
									    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">b.</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant desire coverage for this
																										entity as a predecessor firm?</fo:block></fo:table-cell>
								        	<fo:table-cell><fo:block font-size="9px" font-family="Arial" text-align="right">Yes&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
								        </fo:table-row>
								    </fo:table-body>
								</fo:table>
						    </xsl:otherwise>
						</xsl:choose>    
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="10mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>" to b. above complete the <fo:inline font-weight="bold" font-style="italic">Predecessor Firm Supplement.</fo:inline></fo:block></fo:table-cell>
						        </fo:table-row>
						    </fo:table-body>
						</fo:table>
					</fo:block>	    
					
					<fo:block margin-top="3mm">
						<xsl:if test="response/policy_freeform_01/data/StateCode='AK'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "A PERSON WHO KNOWINGLY AND WITH INTENT TO INJURE, DEFRAUD, OR DECEIVE AN INSURANCE COMPANY FILES A <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> CONTAINING FALSE, INCOMPLETE, OR MISLEADING
									INFORMATION MAY BE PROSECUTED UNDER STATE LAW."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='AR'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> FOR PAYMENT OF A <fo:inline font-weight="bold" font-style="italic">LOSS</fo:inline> OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT IN PRISON."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='AZ'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> FOR PAYMENT OF A <fo:inline font-weight="bold" font-style="italic">LOSS</fo:inline> IS SUBJECT TO CRIMINAL AND CIVIL PENALTIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='CA'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> FOR THE PAYMENT OF A <fo:inline font-weight="bold" font-style="italic">LOSS</fo:inline> IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT IN STATE PRISON."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='CO'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "IT IS UNLAWFUL TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING FACTS OR INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE COMPANY.  PENALTIES MAY INCLUDE IMPRISONMENT, FINES, DENIAL OF INSURANCE AND CIVIL DAMAGES.  ANY INSURANCE COMPANY OR AGENT OF AN INSURANCE COMPANY WHO KNOWINGLY PROVIDES FALSE, INCOMPLETE OR MISLEADING FACTS OR INFORMATION TO A POLICYHOLDER OR CLAIMANT FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE POLICYHOLDER OR CLAIMANT WITH REGARD TO A SETTLEMENT OR AWARD PAYABLE FROM INSURANCE PROCEEDS SHALL BE REPORTED TO THE COLORADO DIVISION OF INSURANCE WITHIN THE DEPARTMENT OF REGULATORY AGENCIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='DC'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "WARNING: IT IS A CRIME TO PROVIDE FALSE OR MISLEADING INFORMATION TO AN <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline> FOR THE PURPOSE OF DEFRAUDING THE <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline> OR ANY OTHER PERSON.  PENALTIES INCLUDE IMPRISONMENT AND/OR FINES.  IN ADDITION, AN INSURER MAY DENY INSURANCE BENEFITS IF FALSE INFORMATION MATERIALLY RELATED TO A <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> WAS PROVIDED BY THE APPLICANT."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='FL'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO INJURE, DEFRAUD OR DECEIVE ANY <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline>, FILES A STATEMENT OF <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> OR AN APPLICATION CONTAINING ANY FALSE, INCOMPLETE, OR MISLEADING INFORMATION IS GUILTY OF A FELONY OF THE THIRD DEGREE."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='HI'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "FOR OUR PROTECTION, HAWAII LAW REQUIRES YOU TO BE INFORMED THAT PRESENTING A FRAUDULENT <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> FOR PAYMENT OF A <fo:inline font-weight="bold" font-style="italic">LOSS</fo:inline> OR BENEFIT IS A CRIME PUNISHABLE BY FINES OR IMPRISONMENT OR BOTH."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='ID'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD OR DECEIVE ANY INSURANCE COMPANY, FILES A STATEMENT CONTAINING ANY FALSE, INCOMPLETE OR MISLEADING INFORMATION IS GUILTY OF A FELONY."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='IN'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD AN <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline> FILES A STATEMENT OF <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> CONTAINING ANY FALSE, INCOMPLETE OR MISLEADING INFORMATION COMMITS A FELONY."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='KY'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS, FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT ACT, WHICH IS A CRIME."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='LA'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT CLAIM FOR PAYMENT OF A LOSS OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT IN PRISON."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='ME'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE, OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY. PENALTIES MAY INCLUDE IMPRISONMENT, FINES, OR DENIAL OF INSURANCE BENEFITS."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='MD'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WILLFULLY PRESENTS A FALSE OR FRAUDULENT CLAIM FOR PAYMENT OF A LOSS OR BENEFIT OR WHO KNOWINGLY AND WILLFULLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT TO PRISON."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='MA'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR ANTOHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND MAY SUBJECT THE PERSON TO CRIMINAL AND CIVIL PENALTIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='MN'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "A PERSON WHO SUBMITS AN APPLICATION OR FILES A CLAIM WITH INTENT TO DEFRAUD OR HELPS COMMIT A FRAUD AGAINST AN <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline> IS GUILTY OF A CRIME."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='MO'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "<fo:inline font-weight="bold" font-style="italic">DEFENSE COSTS</fo:inline> PAID UNDER THE POLICY PROVISIONS WILL REDUCE THE AVAILABLE LIMIT OF LIABILITY AND MAY EXHAUST THEM COMPLETELY."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='NE'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR ANOTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND MAY SUBJECT THE PERSON TO CRIMINAL AND CIVIL PENALTIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='NJ'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO INCLUDES ANY INFORMATION ON AN APPLICATION FOR AN INSURANCE POLICY IS SUBJECT TO CRIMINAL AND CIVIL PENALTIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='NM'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT CLAIM FOR PAYMENT OF A LOSS OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MY BE SUBJECT TO CIVIL FINES AND CRIMINAL PENALTIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='NY'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME, AND SHALL ALSO BE SUBJECT TO A CIVIL PENALTY NOT TO EXCEED FIVE THOUSAND DOLLARS AND THE STATED VALUE OF THE <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> FOR EACH SUCH VIOLATION."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='OH'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO WITH INTENT TO DEFRAUD OR KNOWING HE IS FACILITATING A FRAUD AGAINST AN INSURER, SUBMITS AN APPLICATION OR FILES A <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> CONTAINING A FALSE OR DECEPTIVE STATEMENT IS GUILTY OF INSURANCE FRAUD."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='OK'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "WARNING: ANY PERSON WHO KNOWINGLY, AND WITH INTENT TO INJURE, DEFRAUD OR DECEIVE ANY <fo:inline font-weight="bold" font-style="italic">INSURER</fo:inline>, MAKES ANY <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> FOR THE PROCEEDS OF AN INSURANCE POLICY CONTAINING ANY FALSE, INCOMPLETE OR MISLEADING INFORMATION IS GUILTY OF A FELONY."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='OR'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR ANTOHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND MAY SUBJECT THE PERSON TO CRIMINAL AND CIVIL PENALTIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='PA'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON TO CRIMINAL AND CIVIL PENALTIES."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='TN'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES AND DENIAL OF INSURANCE BENEFITS."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='VT'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY PRESENTS A FALSE STATEMENT IN AN APPLICATION FOR INSURANCE MAY BE GUILTY OF A CRIMINAL OFFENSE AND SUBJECT TO PENALTIES UNDER STATE LAW."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='VA'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES AND DENIAL OF INSURANCE BENEFITS."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='WA'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES AND DENIAL OF INSURANCE BENEFITS."
							</fo:block>	
						</xsl:if>
						<xsl:if test="response/policy_freeform_01/data/StateCode='WV'">
							<fo:block font-family="Arial" font-size="9px">
								NOTICE: "ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT <fo:inline font-weight="bold" font-style="italic">CLAIM</fo:inline> FOR PAYMENT OF A <fo:inline font-weight="bold" font-style="italic">LOSS</fo:inline> OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT TO PRISON."
							</fo:block>	
						</xsl:if>
					</fo:block>
					
					<fo:block margin-top="3mm" font-family="Arial" font-size="8px">
						Completion and/or signing of this application does not bind the Applicant to purchase, nor the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> to provide, any insurance policy; however, no policy can be issued unless the application is properly completed, signed and dated.
					</fo:block>
					
					<fo:block margin-top="3mm" font-family="Arial" font-size="8px">
						The signatory declares that (s)he is authorized by the Applicant to sign this application on behalf of all prospective <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> and that to the best of his/her knowledge the statements herein are true.  The signatory agrees that if the information supplied in this application and the materials submitted therewith should change between the date this application is signed and the effective date of the proposed insurance, the signatory shall immediately notify the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> of such and shall provide the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> with information that would complete, update or correct the application or materials submitted therewith. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may withdraw or modify any of the terms or conditions of coverage accordingly.
					</fo:block>
					
					<fo:block margin-top="3mm" font-family="Arial" font-size="8px" font-weight="bold">
						ALL WRITTEN STATEMENTS, SUPPLEMENTAL APPLICATION AND MATERIALS FURNISHED TO THE INSURER IN CONJUNCTION WITH THIS APPLICATION ARE INCORPORATED BY REFERENCE INTO THIS APPLICATION AND MADE A PART THEREOF, AND DEEMED ATTACHED HERETO.
					</fo:block>
					
					<fo:block margin-top="3mm" font-family="Arial" font-size="8px" font-weight="bold">
						ANY PERSON WHO, WITH INTENT TO DEFRAUD OR KNOWING THAT (S)HE IS FACILITATING A FRAUD AGAINST AN INSURER, SUBMITS AN APPLICATION OR FILES A CLAIM CONTAINING A FALSE OR DECEPTIVE STATEMENT IS GUILTY OF INSURANCE FRAUD.
					</fo:block>
					
					<fo:block margin-top="3mm" font-family="Arial" font-size="9px">
						<fo:table>
							<fo:table-column></fo:table-column>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block>SIGNATURE*___________________________________</fo:block></fo:table-cell>
									<fo:table-cell><fo:block>PRINTED NAME*___________________________________</fo:block></fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						*MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE APPLICANT ON BEHALF OF ALL INSUREDS.
					</fo:block>
					
					<fo:block margin-top="3mm" font-family="Arial" font-size="9px">
						<fo:table>
							<fo:table-column></fo:table-column>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block>TITLE OF SIGNATORY: ___________________________________</fo:block></fo:table-cell>
									<fo:table-cell><fo:block>DATE SIGNED:</fo:block></fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:table>
							<fo:table-column column-width="115mm"></fo:table-column>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell><fo:block></fo:block></fo:table-cell>
									<fo:table-cell><fo:block text-align="left"><fo:inline font-style="italic">MM / DD / YYYY</fo:inline></fo:block></fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>		
					</fo:block>
					
					<xsl:if test="response/policy_freeform_01/data/StateCode= 'NH'">
						<fo:block margin-top="10mm" font-family="Arial" font-size="9px" >						
							AGENT:Protexure; NH LICENSE # 2064151 : INDIVIDUAL AGENT NAME : Kyle Nieman
							AGENT SIGNATURE: <fo:external-graphic src="../LawyersIns/image/kyle.png"/>

						</fo:block>
					</xsl:if>
					
					<xsl:if test="response/policy_freeform_01/data/StateCode= 'IA'">
						<fo:block margin-top="10mm" font-family="Arial" font-size="9px" >						
							PROGRAM AGENT: Protexure; IOWA LICENSE # 14397323
						</fo:block>
					</xsl:if>
					
					
					<xsl:if test="response/policy_freeform_01/data/StateCode= 'FL'">
						<fo:block margin-top="10mm" font-family="Arial" font-size="9px" >						
							PROGRAM AGENT: Protexure; FLORIDA LICENSE # P227965
						</fo:block>
					</xsl:if>
					
					<fo:block break-after="page"/>
					
					<xsl:if test="response/AOP_PercentageValue_19 > 0">  
					
						<fo:block>
							<fo:table margin-top="3mm" border="0.5pt solid grey" background-color="grey">
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;COMMERCIAL REAL ESTATE SUPPLEMENT (As required in Question 13.T.)</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm"></fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">1.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Enter the percentage of commercial real estate practice gross fees billed which were generated from the following areas:</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="1mm">
								<fo:table-column column-width="70mm"/>
								<fo:table-column column-width="25mm"/>
								<fo:table-column column-width="65mm"/>
								<fo:table-column column-width="25mm"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">AREA OF SPECIALITY</fo:block></fo:table-cell>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">AREA OF SPECIALITY</fo:block></fo:table-cell>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
									</fo:table-row>
									<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;A.	Commercial Purchase and Sale </fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_2"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;H.	Speculative Real Estate</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_13"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;B.	Property Valuation/Real Estate Tax Abatement</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_7"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;I.	Limited Partnerships</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_14"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;C.	Foreclosures</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_8"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;J.	Real Estate Syndications</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_15"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;D.	Construction Work and or Mechanics Liens</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_9"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;K.	Real Estate Trusts</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_16"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;E.	Loan Workouts</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_10"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;L.	Other  (please describe) </fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_17"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;F.	Land Use/Development</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_11"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;<xsl:value-of select="response/AOPRE_CommentDesc_17"/></fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;G.	Eminent Domain</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_12"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
						         	</fo:table-row>
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-weight="bold" font-family="Arial">&#160;&#160;Total (must equal 100%)</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aoprec_total"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>			
								</fo:table-body>
							</fo:table>
						</fo:block>
						
					</xsl:if>
						
						
				<xsl:if test="response/AOP_PercentageValue_25 > 0">  		
				
						<fo:block margin-top="5mm">
							<fo:table margin-top="3mm" border="0.5pt solid grey" background-color="grey">
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;RESIDENTIAL REAL ESTATE SUPPLEMENT (As required in Question 13.U.)</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm"></fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">1.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Enter the percentage of residential real estate practice gross fees billed which were generated from the following areas:</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="1mm">
								<fo:table-column column-width="70mm"/>
								<fo:table-column column-width="25mm"/>
								<fo:table-column column-width="65mm"/>
								<fo:table-column column-width="25mm"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">AREA OF SPECIALITY</fo:block></fo:table-cell>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">AREA OF SPECIALITY</fo:block></fo:table-cell>
										<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
									</fo:table-row>
									<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;A.	Residential Purchase and Sale </fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_1"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;D.  Loan Workouts</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_21"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;B.	Condominiums, Cooperative or Homeowner Associations</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_4"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;E.	Landlord/Tenant</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_6"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;C.	Foreclosures</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_19"/> %</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;F.	Other  (please describe)</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/AOPRE_PercentageValue_20"/> %</fo:block></fo:table-cell>
						         		
						         	</fo:table-row>
						         	
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;<xsl:value-of select="response/AOPRE_CommentDesc_20"/></fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
						         	</fo:table-row>
						         		
						         	<fo:table-row>							
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"></fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-weight="bold" font-family="Arial">&#160;&#160;Total (must equal 100%)</fo:block></fo:table-cell>
						         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aopre_total"/> %</fo:block></fo:table-cell>
						         	</fo:table-row>							         		
								</fo:table-body>
							</fo:table>
						</fo:block>
						
					
					
					
					
					</xsl:if>
					
					 <xsl:if test="response/AOP_PercentageValue_17 > 0"> 
					
						<fo:block margin-top="5mm">
							
							<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;PLAINTIFF LITIGATION SUPPLEMENT (As required in Question 13.R.)</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm"></fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">1.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Please complete the following chart,
											providing a breakdown of the Applicant's practice based on
											gross billable income and case information:</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:table margin-top="1mm" border="0.5pt solid black">
										    	<fo:table-column column-width="65mm"/>
										        <fo:table-column/>
										        <fo:table-column/>
										        <fo:table-column/>
										        <fo:table-body>
												 	<fo:table-row>
												 		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">AREA OF LITIGATION</fo:block></fo:table-cell>
						         						<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% of Revenue</fo:block></fo:table-cell>
						         						<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Average Case Size</fo:block></fo:table-cell>
						         						<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Largest Case Size</fo:block></fo:table-cell>
						         					</fo:table-row>
					         						
										         		<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" >&#160;&#160;a.	Commercial</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_4"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_4"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_4"/></fo:block></fo:table-cell>
											         	</fo:table-row>
											         	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" >&#160;&#160;b.	Medical Malpractice </fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_5"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_5"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_5"/></fo:block></fo:table-cell>
											         	</fo:table-row>		
											         	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;c.	Other Personal Injury / Property Damage</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_6"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_6"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_6"/></fo:block></fo:table-cell>
											         	</fo:table-row>		
											         	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;d.	Products Liability  </fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_8"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_8"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_8"/></fo:block></fo:table-cell>
											         	</fo:table-row>		
											         	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;e.	Professional Liability (non medical)</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_9"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_9"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_9"/></fo:block></fo:table-cell>
											         	</fo:table-row>		
											         	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;f.	Toxic Tort </fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_11"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_11"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_11"/></fo:block></fo:table-cell>
											         	</fo:table-row>		
											         	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;g.	Workers’ Compensation</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_12"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_12"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_12"/></fo:block></fo:table-cell>
											         	</fo:table-row>
											         	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial">&#160;&#160;h.	Other (please describe)<xsl:value-of select="response/aol_plaintiffMap/data/CommentDesc_13"/> </fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_13"/> %</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/AverageCaseSize_13"/></fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_13"/></fo:block></fo:table-cell>
											         	</fo:table-row>												
													
													<fo:table-row>
												 		<fo:table-cell border="0.5pt solid black"><fo:block text-align="center" font-family="Arial" font-size="9px" font-weight="bold">Total (must equal 100%)</fo:block></fo:table-cell>
						         						<fo:table-cell border="0.5pt solid black"><fo:block text-align="center" font-family="Arial" font-size="9px" font-weight="bold"><xsl:value-of select="response/aol_percentageOfRevenue"/>%</fo:block></fo:table-cell>
						         						<fo:table-cell border="0.5pt solid black"><fo:block text-align="center" font-family="Arial" font-size="9px" font-weight="bold"><xsl:value-of select="response/aol_averageCaseSize"/></fo:block></fo:table-cell>
						         						<fo:table-cell border="0.5pt solid black"><fo:block text-align="center" font-family="Arial" font-size="9px" font-weight="bold"><xsl:value-of select="response/aol_largestCaseSize"/></fo:block></fo:table-cell>
						         					</fo:table-row>
												</fo:table-body>
						         			</fo:table>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>			
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">2.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Please provide the following for each attorney performing
														plaintiff work:</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="1mm">  
								<fo:table-column column-width="5mm"/>
								<fo:table-column/>
								<fo:table-body>       
							        <fo:table-row>
							        	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							        	<fo:table-cell>
							        		<fo:block>
							        			<fo:table margin-top="1mm">
											         <fo:table-column/>	    		         
											         <fo:table-column/>
											         <!-- 
											         <fo:table-column/>
											          -->
											         <fo:table-body>
													 	<fo:table-row>							
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Name of Attorney</fo:block></fo:table-cell>
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Years of Plaintiff Experience</fo:block></fo:table-cell>
											         	<!--  
											         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Percentage of Time Devoted to Specialization in Plaintiff Work</fo:block></fo:table-cell>
											         	-->
											         	</fo:table-row>
													 	<xsl:for-each select="response/attorneys_plaintiff_list_01/data">
											         		<fo:table-row>							
												         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="PlaintiffAttorneyName"/></fo:block></fo:table-cell>
												         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="PlaintiffYrsExp"/></fo:block></fo:table-cell>
												         	<!-- 	
												         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="PlaintiffPecOfTimeDev"/>%</fo:block></fo:table-cell>
												         	 -->
												         	</fo:table-row>								
														</xsl:for-each>           
													</fo:table-body>
												</fo:table>
							        		</fo:block>
							        	</fo:table-cell>
							        </fo:table-row>
							    </fo:table-body>
							</fo:table>  
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">3.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Number of support staff devoted to plaintiff work?</fo:block>
										</fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"><xsl:value-of select="response/plaintiff_freeform_01/data/NumberOfSuppotedStaffToPlantiff"></xsl:value-of></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">4.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Total number of plaintiff cases during the past 12
											months:</fo:block>
										</fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"><xsl:value-of select="response/plaintiff_freeform_01/data/NumberOfInjuryCasesIn12Month"></xsl:value-of></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">5.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Average number of plaintiff cases each attorney of the
											Applicant handles per year: &#160;&#160;<xsl:value-of select="response/plaintiff_freeform_01/data/NumberOfInjuryHandleByAttorney"></xsl:value-of></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">6.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Of the number of plaintiff cases handled by the firm, in the past 5 years, what percent of them were settled prior to trial: &#160;&#160;<xsl:value-of select="response/plaintiff_freeform_01/data/PerOfCasesSettledBeforeTrail"></xsl:value-of>%</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							<!--  
							
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"/>
								<fo:table-column column-width="5mm"/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Settled before trial? &#160;<xsl:value-of select="response/plaintiff_freeform_01/data/PerOfCasesBeforeTrail"></xsl:value-of>%</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">b.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Tried to conclusion? &#160;<xsl:value-of select="response/plaintiff_freeform_01/data/PerOfCasesTriedToConclusion"></xsl:value-of>%</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">c.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Referred to the Applicant by other law firms? &#160;<xsl:value-of select="response/plaintiff_freeform_01/data/PerOfCasesReferToApplicant"></xsl:value-of>%</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							-->
							
							<xsl:choose>
								<xsl:when test="response/plaintiff_freeform_01/data/IsApplAcceptCasesToStatuteOfLim='Y'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">7.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant accept cases with less than six months to
													the Statute of Limitation?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/plaintiff_freeform_01/data/IsApplAcceptCasesToStatuteOfLim='N'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">7.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant accept cases with less than six months to
													the Statute of Limitation?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="158mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">7.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant accept cases with less than six months to
													the Statute of Limitation?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
						<fo:table margin-top="1mm" border="0.0pt solid black">  
							<fo:table-column column-width="5mm"/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial"></fo:block></fo:table-cell>
							    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", how does the applicant handle potential statute of limitation issues: <xsl:value-of select="response/plaintiff_freeform_01/data/IsApplAcceptCasesToStatuteOfLimDesc"></xsl:value-of></fo:block></fo:table-cell>
						        </fo:table-row>
						    </fo:table-body>
						</fo:table>
							
							<!--  
							
							
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">8.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">Has the Applicant advertised during the past 12 months through
											any of the following (check all that apply):</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							
							
							
							<fo:table>
								<fo:table-column></fo:table-column>
								<fo:table-column></fo:table-column>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<xsl:choose>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByTelevision='Y'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Television</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByTelevision='N'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Television</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:otherwise>
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Television</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:otherwise>
											</xsl:choose>	
										</fo:table-cell>
										<fo:table-cell>
											<xsl:choose>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByNewspaper='Y'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">c.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Newspaper</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByNewspaper='N'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">c.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Newspaper</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:otherwise>
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">c.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Newspaper</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:otherwise>
											</xsl:choose>
										</fo:table-cell>
										<fo:table-cell>
											<xsl:choose>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByInternet='Y'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">e.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Internet</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByInternet='N'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">e.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Internet</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:otherwise>
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">e.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Internet</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:otherwise>
											</xsl:choose>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<xsl:choose>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByRadio='Y'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">b.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Radio</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByRadio='N'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">b.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Radio</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:otherwise>
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">b.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Radio</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:otherwise>
											</xsl:choose>	
										</fo:table-cell>
										<fo:table-cell>
											<xsl:choose>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByYellowpages='Y'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">d.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Yellow pages</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByYellowpages='N'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">d.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Yellow pages</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:otherwise>
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">d.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Yellow pages</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:otherwise>
											</xsl:choose>
										</fo:table-cell>
										<fo:table-cell>
											<xsl:choose>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByMagazine='Y'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">f.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Magazines</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:when test="response/plaintiff_freeform_01/data/IsApplAdvertiseByMagazine='N'">
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">f.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Magazines</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:when>
												<xsl:otherwise>
													<fo:table margin-top="1mm">
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="5mm"/>
														<fo:table-column column-width="30mm"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px">f.</fo:block></fo:table-cell>
																<fo:table-cell>
																	<fo:block font-family="Arial" font-size="9px">Magazines</fo:block>
																</fo:table-cell>
																<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</xsl:otherwise>
											</xsl:choose>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							
							-->
							
							
							<xsl:choose>
								<xsl:when test="response/plaintiff_freeform_01/data/IsAppAcceptRefersFromOtherFirms='Y'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">8.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant accept referrals from other firms?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/plaintiff_freeform_01/data/IsAppAcceptRefersFromOtherFirms='N'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">8.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant accept referrals from other firms?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="158mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">8.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant accept referrals from other firms?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
							
							<xsl:choose>
								<xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToAppl='Y'">
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", is a written referral agreement used?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToAppl='N'">
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", is a written referral agreement used?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="153mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", is a written referral agreement used?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
							
							<xsl:choose>
								<xsl:when test="response/plaintiff_freeform_01/data/IsAppReferToOtherLawFirms='Y'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">9.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant refer cases to other law firms?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/plaintiff_freeform_01/data/IsAppReferToOtherLawFirms='N'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">9.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant refer cases to other law firms?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="158mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">9.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant refer cases to other law firms?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
							
							
							
							
							
							<xsl:choose>
								<xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToApplRefersOut='Y'">
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", are written referral agreements used?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToApplRefersOut='N'">
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", are written referral agreements used?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="153mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">a.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", are written referral agreements used?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
							
							<xsl:choose>
								<xsl:when test="response/plaintiff_freeform_01/data/IsAppConfirmCarryProfLiabIns='Y'">
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">b.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant confirm that firms to which
																		referrals are made carry professional liability
																		insurance?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/plaintiff_freeform_01/data/IsAppConfirmCarryProfLiabIns='N'">
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">b.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant confirm that firms to which
																		referrals are made carry professional liability
																		insurance?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="1mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="153mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">b.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant confirm that firms to which
																		referrals are made carry professional liability
																		insurance?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
							
							<xsl:choose>
								<xsl:when test="response/plaintiff_freeform_01/data/IsSettlementAggrementsUsed='Y'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">10.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Are written authorizations for settlement always obtained from clients when settlements are reached?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/plaintiff_freeform_01/data/IsSettlementAggrementsUsed='N'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">10.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Are written authorizations for settlement always obtained from clients when settlements are reached?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="158mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">10.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Are written authorizations for settlement always obtained from clients when settlements are reached?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>						
				
					</xsl:if>
					
					
					
					<xsl:if test="response/AOP_PercentageValue_23 > 0">
						
						<fo:block margin-top="5mm">
							
							
							
							<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;WILLS, TRUSTS AND ESTATES SUPPLEMENT (As required in Question 13.Y.)</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block margin-top="1mm"></fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							
							<xsl:choose>
								<xsl:when test="response/willsEstate_freeform_01/data/IsAttorneyServeAsExecutorTrustee='Y'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">1.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does any attorney currently serve as Executor/Person
											Representative/Administrator or Trustee?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/willsEstate_freeform_01/data/IsAttorneyServeAsExecutorTrustee='N'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">1.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does any attorney currently serve as Executor/Person
											Representative/Administrator or Trustee?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">1.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does any attorney currently serve as Executor/Person
											Representative/Administrator or Trustee?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
							
							
							
							
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">What services does the Applicant provided for clients? (Check all that apply)</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							  
							
							
							
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block></fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:table margin-top="1mm">  
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-body>       
											        <fo:table-row>
											        	<fo:table-cell border="0.0pt solid black">
											        		<fo:block>
											        			<fo:table>
															    	<fo:table-column column-width="5mm"/>
															        <fo:table-column/>
															        <fo:table-body>
																	 	<xsl:for-each select="response/services_willsEstate_list_01/data">
															         		<fo:table-row>
																					<fo:table-cell border="0.0pt solid black" width="5mm">
																					<fo:block>		
																	         			<xsl:choose>
																	         				<xsl:when test="CheckedValue='Y'">
																	         					<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;
																	         				</xsl:when>
																         				<xsl:otherwise>
																         					<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;
																         				</xsl:otherwise>
																         			</xsl:choose>
															         			</fo:block>
														         			</fo:table-cell>				
														         			<fo:table-cell border="0.0pt solid black" width="30mm"><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="WESDesc"/></fo:block></fo:table-cell>														         			
															         	</fo:table-row>								
																	</xsl:for-each>
																</fo:table-body>
										         			</fo:table>
										        		</fo:block>
										        	</fo:table-cell>
										        	
										        	<fo:table-cell border="0.0pt solid black">
										        		<fo:block>
										        			<fo:table>
														    	<fo:table-column column-width="5mm"/>
														        <fo:table-column/>
														        <fo:table-body>
																 	<xsl:for-each select="response/services_willsEstate_list_02/data">
														         		<fo:table-row>
																			<fo:table-cell border="0.0pt solid black" width="5mm">
																				<fo:block>		
																         			<xsl:choose>
																         				<xsl:when test="CheckedValue='Y'">
																         					<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;
																         				</xsl:when>
																         				<xsl:otherwise>
																         					<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;
																         				</xsl:otherwise>
																         			</xsl:choose>
															         			</fo:block>
														         			</fo:table-cell>				
														         			<fo:table-cell border="0.0pt solid black" width="30mm"><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="WESDesc"/></fo:block></fo:table-cell>
															         	</fo:table-row>								
																	</xsl:for-each>
																</fo:table-body>
										         			</fo:table>
										        		</fo:block>
										        	</fo:table-cell>
										        	
										        	
										        	<fo:table-cell border="0.0pt solid black">
										        		<fo:block>
										        			<fo:table>
														    	<fo:table-column column-width="5mm"/>
														        <fo:table-column/>
														        <fo:table-body>
																 	<xsl:for-each select="response/services_willsEstate_list_03/data">
														         		<fo:table-row>
																			<fo:table-cell border="0.0pt solid black" width="5mm">
																				<fo:block>		
																         			<xsl:choose>
																         				<xsl:when test="CheckedValue='Y'">
																         					<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;
																         				</xsl:when>
																         				<xsl:otherwise>
																         					<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;
																         				</xsl:otherwise>
																         			</xsl:choose>
															         			</fo:block>
														         			</fo:table-cell>				
														         			<fo:table-cell border="0.0pt solid black" width="30mm"><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="WESDesc"/></fo:block></fo:table-cell>
															         	</fo:table-row>								
																	</xsl:for-each>
																</fo:table-body>
										         			</fo:table>
										        		</fo:block>
										        	</fo:table-cell>
										        	
										        	
										        	<fo:table-cell border="0.0pt solid black">
										        		<fo:block>
										        			<fo:table>
														    	<fo:table-column column-width="5mm"/>
														        <fo:table-column/>
														        <fo:table-body>
																 	<xsl:for-each select="response/services_willsEstate_list_04/data">
														         		<fo:table-row>
																			<fo:table-cell border="0.0pt solid black" width="5mm">
																				<fo:block>		
																         			<xsl:choose>
																         				<xsl:when test="CheckedValue='Y'">
																         					<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;
																         				</xsl:when>
																         				<xsl:otherwise>
																         					<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;
																         				</xsl:otherwise>
																         			</xsl:choose>
															         			</fo:block>
														         			</fo:table-cell>				
														         			<fo:table-cell border="0.0pt solid black" width="30mm"><fo:block font-size="9px" font-family="Arial"><xsl:value-of select="WESDesc"/> &#160;</fo:block>
														         			<fo:block font-size="9px" font-family="Arial">
														         			<xsl:if test="WESCommentDesc != ''">
														         				(Please Describe): <xsl:value-of select="WESCommentDesc"/>
														         			</xsl:if>
														         			</fo:block>
														         			</fo:table-cell>
															         	</fo:table-row>								
																	</xsl:for-each>
																</fo:table-body>
										         			</fo:table>
										        		</fo:block>
										        	</fo:table-cell>
										       
										       
										        </fo:table-row>
										    </fo:table-body>
										</fo:table>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							
							
							
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">2.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px">How many client estates or trusts are valued over:</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							
							
							<fo:table margin-top="3mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="20mm"/>
								<fo:table-column column-width="20mm"/>
								<fo:table-column column-width="20mm"/>
								<fo:table-column column-width="20mm"/>
								<fo:table-column column-width="20mm"/>
								<fo:table-column column-width="20mm"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">$1 million?</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"><xsl:value-of select="response/willsEstate_freeform_01/data/NumberOfEstateOver1Million"/></fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">$5 million?</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"><xsl:value-of select="response/willsEstate_freeform_01/data/NumberOfEstateOver5Million"/></fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px">$10 million?</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"><xsl:value-of select="response/willsEstate_freeform_01/data/NumberOfEstateOver10Million"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							
							
								
							
							
							
							
							
							
							
							
							
							<xsl:choose>
								<xsl:when test="response/willsEstate_freeform_01/data/IsApplHaveControlOverFunds='Y'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">3.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant or any member of the firm have discretionary control of funds for clients?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/willsEstate_freeform_01/data/IsApplHaveControlOverFunds='N'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">3.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant or any member of the firm have discretionary control of funds for clients?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="154mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">3.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Does the Applicant or any member of the firm have discretionary control of funds for clients?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>								
							</xsl:choose>
							
							<xsl:choose>
								<xsl:when test="response/willsEstate_freeform_01/data/IsCounterSignatureRequired='Y'">
							
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-indent="5mm">a.</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", is a countersignature required on all client checks issued by personnel of the Firm?</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:when>
							<xsl:when test="response/willsEstate_freeform_01/data/IsCounterSignatureRequired='N'">
							
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="5mm">a.</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", is a countersignature required on all client checks issued by personnel of the Firm?</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:when>
							
							<xsl:otherwise>
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="5mm">a.</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", is a countersignature required on all client checks issued by personnel of the Firm?</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:otherwise>
							
							
							</xsl:choose>
							
							<xsl:choose>
							<xsl:when test="response/willsEstate_freeform_01/data/IsApplAuthorizeToWithdrawFromBank='Y'">
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="5mm">b.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", are all client bank accounts reconciled by someone other than Firm personnel authorized to deposit or withdraw there from?
																		</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:when>
							
							<xsl:when test="response/willsEstate_freeform_01/data/IsApplAuthorizeToWithdrawFromBank='N'">
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="5mm">b.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", are all client bank accounts reconciled by someone other than Firm personnel authorized to deposit or withdraw there from?
																		</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:when>
							
							
							<xsl:otherwise>
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="4mm">b.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", are all client bank accounts reconciled by someone other than Firm personnel authorized to deposit or withdraw there from?
																		</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:otherwise>
							
							
							</xsl:choose>
							
							<xsl:choose>
							<xsl:when test="response/willsEstate_freeform_01/data/IsApplCarryEmployeeDishonestyCoverage='Y'">
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="5mm">c.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant carry Employee Dishonesty Coverage?
																		</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:when>
							
							<xsl:when test="response/willsEstate_freeform_01/data/IsApplCarryEmployeeDishonestyCoverage='N'">
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="5mm">c.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant carry Employee Dishonesty Coverage?
																		</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							
							</xsl:when>
							
							<xsl:otherwise>
							<fo:table margin-top="1mm">
								<fo:table-column column-width="5mm"></fo:table-column>
								<fo:table-column column-width="154mm"/>
								<fo:table-column></fo:table-column>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px"  text-indent="5mm">c.</fo:block></fo:table-cell>
										<fo:table-cell>
											<fo:block font-family="Arial" font-size="9px"  text-indent="7mm">If "<fo:inline font-weight="bold" font-style="italic">Yes</fo:inline>", does the Applicant carry Employee Dishonesty Coverage?
																		</fo:block></fo:table-cell>
										<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							</xsl:otherwise>
							
							</xsl:choose>
							
							
							
							
							
							
							
							
							<xsl:choose>
								<xsl:when test="response/willsEstate_freeform_01/data/IsApplPolicyReviewBySecondAttorney='Y'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">4.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Is it the Applicant's policy to include a review and sign off
											by a second attorney when drafting all new wills and trusts?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:when test="response/willsEstate_freeform_01/data/IsApplPolicyReviewBySecondAttorney='N'">
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="159mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">4.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Is it the Applicant's policy to include a review and sign off
											by a second attorney when drafting all new wills and trusts?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:when>
								<xsl:otherwise>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column column-width="158mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px">4.</fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Arial" font-size="9px">Is it the Applicant's policy to include a review and sign off
											by a second attorney when drafting all new wills and trusts?</fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</xsl:otherwise>
							</xsl:choose>
							
							
							
							
							
						
							
							
							
							
						
						
						
						</fo:block>
				
				
					
				
				</xsl:if>
					
					
					
					
					
					
					
						<fo:block margin-top="10mm">	
							<xsl:if test="response/firmlawsuit_practice_list_01/data/*">
								<fo:block>
									<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
										<fo:table-column></fo:table-column>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;FEE SUIT SUPPLEMENT (As required in Question 22)</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell>
													<fo:block margin-top="1mm"></fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									<fo:table margin-top="1mm" border="0.0pt solid black">  
										<fo:table-column column-width="5mm"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
										    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">1.</fo:block></fo:table-cell>
										    	<fo:table-cell><fo:block font-size="9px" font-family="Arial">Please complete the following for each suit Applicant has filed against a client for collection of fees due Applicant.</fo:block></fo:table-cell>
									        </fo:table-row>
										</fo:table-body>
									</fo:table>
									<fo:table>
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column></fo:table-column>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block>
														<fo:table margin-top="1mm" border="0.5pt solid black">  
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-body>       
														        <fo:table-row>
														        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Amount of Fees Sued For</fo:block></fo:table-cell>
														        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Date Fees were Due</fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Date Fee Suit Filed</fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Area of Practice</fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Was there a Counter Claim or Allegation of Legal Malpractice</fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Disposition of Fee Suit*</fo:block></fo:table-cell>
														        </fo:table-row>
								         	 					<xsl:for-each select="response/firmlawsuit_practice_list_01/data">
													         		<fo:table-row>							
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="AmountOfFeesSued"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="DueDateFees"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="SuitFilesDateFees"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FeesAreaOfPractice"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:choose>
															         		<xsl:when test="IsAllegOfLegalMalPrac = 'Y'"><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png" /></fo:block></xsl:when>
																				<xsl:when test="IsAllegOfLegalMalPrac = 'N'"><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png" /></fo:block></xsl:when>
																				</xsl:choose>
														         		</fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FeeSuitDesc"/></fo:block></fo:table-cell>
														         	</fo:table-row>								
																</xsl:for-each>
															</fo:table-body>
											  			</fo:table>	
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									<fo:table margin-top="1mm" border="0.0pt solid black">  
										<fo:table-column column-width="5mm"/>
										<fo:table-column/>
										<fo:table-body>       
									        <fo:table-row>
									        	<fo:table-cell><fo:block></fo:block></fo:table-cell>
									        	<fo:table-cell><fo:block font-size="9px" font-family="Arial">*P = Fees paid in full, NS = Negotiated Settlement, JP = Judgment Plaintiff, JD = Judgment Defense, O = Open</fo:block></fo:table-cell>
									        </fo:table-row>
								    	</fo:table-body>
									</fo:table> 
								</fo:block>	
							</xsl:if>	
						</fo:block>
						
						
						
						
						
						
						<fo:block margin-top="5mm">
							<xsl:if test="response/prodecessor_list_1/data/*">
								<fo:block>
									<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
										<fo:table-column></fo:table-column>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;PREDECESSOR FIRM SUPPLEMENT (As required in Question 30)</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell>
													<fo:block margin-top="1mm"></fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									<fo:table margin-top="3mm">
										<fo:table-column column-width="5mm"></fo:table-column>
										<fo:table-column></fo:table-column>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell>
													<fo:block>
														<fo:table margin-top="1mm" border="0.5pt solid black">  
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-column/>
															<fo:table-body>       
														        <fo:table-row>
														        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Firm Name</fo:block></fo:table-cell>
														        	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Date of Acquisition or Merger </fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Type of Legal Entity </fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold"># of Attys at Firm at Dissolution </fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold"># of Attys for whom coverage is sought</fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Insurer at Dissolution</fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">Was ERP Purchased</fo:block></fo:table-cell>
														         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">ERP Expiration Date</fo:block></fo:table-cell>
														        </fo:table-row>
								         	 					<xsl:for-each select="response/prodecessor_list_1/data">
													         		<fo:table-row>							
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="FirmName"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="DateOfAcquisation"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="TypeOfEntity"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NumberOfAttorneyAtDiss"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="NumberOfAttorneyAtApplFirm"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="InsurerAtDissolution"/></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:choose>
												         		<xsl:when test="IsERPPurchased = 'Y'"><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png" /></fo:block></xsl:when>
															<xsl:when test="IsERPPurchased = 'N'"><fo:block font-size="9px" font-family="Arial">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png" />&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png" /></fo:block></xsl:when>
															</xsl:choose></fo:block></fo:table-cell>
														         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial" text-align="center"><xsl:value-of select="ERPExpDate"/></fo:block></fo:table-cell>
														         	</fo:table-row>								
																</xsl:for-each>
															</fo:table-body>
											  			</fo:table>	
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>	
							</xsl:if>
						</fo:block>
						
						
						
						
						<fo:block margin-top="5mm">
							<xsl:if test="response/claims_list_1/data/*">	
								<fo:block>
									<fo:table margin-top="3mm" border="0.5pt solid black" background-color="grey">
										<fo:table-column></fo:table-column>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block margin-top="1mm" font-family="Arial" font-weight="bold" font-size="9px">&#160;&#160;CLAIM SUPPLEMENT (As required in Question 23)</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell>
													<fo:block margin-top="1mm"></fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									<fo:table margin-top="3mm">
										<fo:table-column></fo:table-column>
										<fo:table-body>
											<xsl:for-each select="response/claims_list_1/data">
												<fo:table-row>
													<fo:table-cell>
														<fo:block>
															<fo:table margin-top="1mm">
																<fo:table-column column-width="5mm"/>
																<fo:table-column column-width="145mm"/>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">1.</fo:block></fo:table-cell>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">Full name of claimant or potential claimant:&#160;<xsl:value-of select="NameOfClaimant"></xsl:value-of></fo:block></fo:table-cell>
																		<fo:table-cell>
																			<xsl:choose>
																				<xsl:when test="IsClient='Y'">
																					<fo:block font-family="Arial" font-size="9px"><fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;Client&#160;&#160;&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;Non-Client</fo:block>
																				</xsl:when>
																				<xsl:when test="IsClient='N'">
																					<fo:block font-family="Arial" font-size="9px"><fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;Client&#160;&#160;&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;Non-Client</fo:block>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:block font-family="Arial" font-size="9px"><fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;Client&#160;&#160;&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;Non-Client</fo:block>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:table-cell>	
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
															
															
															<fo:table margin-top="1mm">
																<fo:table-column column-width="5mm"/>
																<fo:table-column column-width="145mm"/>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">2.</fo:block></fo:table-cell>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">Is the claim in suit?</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<xsl:choose>
																				<xsl:when test="IsClaimInSuit='Y'">
																					<fo:block font-family="Arial" font-size="9px"><fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;Yes&#160;&#160;&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;No</fo:block>
																				</xsl:when>
																				<xsl:when test="IsClaimInSuit='N'">
																					<fo:block font-family="Arial" font-size="9px"><fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;Yes&#160;&#160;&#160;<fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;No</fo:block>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:block font-family="Arial" font-size="9px"><fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;Yes&#160;&#160;&#160;<fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;No</fo:block>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:table-cell>	
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
															
															
															
															
															
															
															
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column column-width="110mm"/>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">3.</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Date Applicant was notified or became aware of error: &#160;<xsl:value-of select="ClaimNotifiedDate"></xsl:value-of></fo:block>
																		</fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Date of alleged error:&#160;<xsl:value-of select="DateOfAllegedError"></xsl:value-of></fo:block>
																		</fo:table-cell>	
																	</fo:table-row>
																	
																	
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="5px"  font-style="italic" text-indent="7.2cm"> MM / DD / YYYY</fo:block>
																		</fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="5px" text-indent="3cm" font-style="italic"> MM / DD / YYYY</fo:block>
																		</fo:table-cell>	
																	</fo:table-row>
																	
																	
																	
																	
																</fo:table-body>
															</fo:table>
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">4.</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Names of Applicant personnel involved in the claim or potential claim:&#160;<xsl:value-of select="NameOfPersonnelINClaim"/></fo:block>
																		</fo:table-cell>
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">5.</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Description of claim or potential claim:&#160;<xsl:value-of select="DescOfClaim"/></fo:block>
																		</fo:table-cell>
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
															<xsl:choose>
																<xsl:when test="IsClaimReportedToInsComp='Y'">
																	<fo:table margin-top="3mm">
																		<fo:table-column column-width="5mm"></fo:table-column>
																		<fo:table-column column-width="154mm"/>
																		<fo:table-column/>
																		<fo:table-body>
																			<fo:table-row>
																				<fo:table-cell><fo:block font-family="Arial" font-size="9px">6.</fo:block></fo:table-cell>
																				<fo:table-cell>
																					<fo:block font-family="Arial" font-size="9px">Has this claim or potential claim been reported
																																to the Applicants professional liability
																																insurer?</fo:block>
																				</fo:table-cell>
																				<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</xsl:when>
																<xsl:when test="IsClaimReportedToInsComp='N'">
																	<fo:table margin-top="3mm">
																		<fo:table-column column-width="5mm"></fo:table-column>
																		<fo:table-column column-width="154mm"/>
																		<fo:table-column/>
																		<fo:table-body>
																			<fo:table-row>
																				<fo:table-cell><fo:block font-family="Arial" font-size="9px">6.</fo:block></fo:table-cell>
																				<fo:table-cell>
																					<fo:block font-family="Arial" font-size="9px">Has this claim or potential claim been reported
																																to the Applicants professional liability
																																insurer?</fo:block>
																				</fo:table-cell>
																				<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn6.png"/></fo:block></fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</xsl:when>
																<xsl:otherwise>
																	<fo:table margin-top="3mm">
																		<fo:table-column column-width="5mm"></fo:table-column>
																		<fo:table-column column-width="153mm"/>
																		<fo:table-column/>
																		<fo:table-body>
																			<fo:table-row>
																				<fo:table-cell><fo:block font-family="Arial" font-size="9px">6.</fo:block></fo:table-cell>
																				<fo:table-cell>
																					<fo:block font-family="Arial" font-size="9px">Has this claim or potential claim been reported
																																to the Applicants professional liability
																																insurer?</fo:block>
																				</fo:table-cell>
																				<fo:table-cell><fo:block font-family="Arial" font-size="9px" text-align="right">Yes <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/>&#160;&#160;&#160;No <fo:external-graphic src="../LawyersIns/image/check-btn4.png"/></fo:block></fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</xsl:otherwise>
															</xsl:choose>
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column column-width="110mm"/>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">7.</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Name of insurance company:&#160;<xsl:value-of select="NameOfInsComp"></xsl:value-of></fo:block>
																		</fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Date reported to insurance company:&#160;<xsl:value-of select="DateReportedToInsComp"></xsl:value-of></fo:block>
																		</fo:table-cell>	
																	</fo:table-row>
																	
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px"></fo:block>
																		</fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="5px" text-indent="5cm" font-style="italic"> MM / DD / YYYY</fo:block>
																		</fo:table-cell>	
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">8.</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">If claim is now open, provide: Insurer's loss
																																reserve($):&#160;<xsl:value-of select="InsurerLoss"/>
																			</fo:block>
																		</fo:table-cell>
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column column-width="65mm"/>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Claimant's last demand($):&#160;<xsl:value-of select="ClaimantLastDemand"></xsl:value-of></fo:block>
																		</fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">Current Status:&#160;<xsl:value-of select="CurrentStatus"></xsl:value-of></fo:block>
																		</fo:table-cell>	
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column column-width="60mm"/>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">9.</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">If closed, provide: date closed:&#160;<xsl:value-of select="ClaimClosingDate"></xsl:value-of></fo:block>
																		</fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px" text-align="right">Total amount paid (including damages and defense
																																expenses):&#160;<xsl:value-of select="TotalAmountPaid"></xsl:value-of></fo:block>
																		</fo:table-cell>	
																	</fo:table-row>
																	
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px"></fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="5px" text-indent="4cm" font-style="italic"> MM / DD / YYYY</fo:block>
																		</fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px" text-align="right"></fo:block>
																		</fo:table-cell>	
																	</fo:table-row>
																	
																	
																	
																</fo:table-body>
															</fo:table>
															<fo:table margin-top="3mm">
																<fo:table-column column-width="5mm"></fo:table-column>
																<fo:table-column/>
																<fo:table-body>
																	<fo:table-row>
																		<fo:table-cell><fo:block font-family="Arial" font-size="9px">10.</fo:block></fo:table-cell>
																		<fo:table-cell>
																			<fo:block font-family="Arial" font-size="9px">What steps have been taken by Applicant to prevent similar allegations from being made in future claims? &#160;<xsl:value-of select="StepsTakenToPreventClaims"/></fo:block>
																		</fo:table-cell>
																	</fo:table-row>
																</fo:table-body>
															</fo:table>
														</fo:block>
														<fo:block>
															<fo:table margin-top="1mm" border="0.5pt solid black">
																<fo:table-column/>
																<fo:table-body>       
															        <fo:table-row>
															        	<fo:table-cell><fo:block></fo:block></fo:table-cell>
															        </fo:table-row>
															    </fo:table-body>    
															</fo:table>
														</fo:block>
													</fo:table-cell>		
												</fo:table-row>
											</xsl:for-each>
										</fo:table-body>
									</fo:table>
								</fo:block>	
							</xsl:if>		
						</fo:block>
							
							
							
					
					
					  
				
				
				
					
					<fo:block margin-top="10mm">
						<fo:table>
							<fo:table-column></fo:table-column>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-family="Arial" font-size="9px" text-align="left">Comments by Insured : <xsl:value-of select="response/firm_freeform_01/data/CommentsByInsured"></xsl:value-of> </fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>	
					
					
					
					
					
					
					<fo:block id="last-page" font-size="11px"/>
											
				</fo:flow>
			</fo:page-sequence>

		</fo:root>


	</xsl:template>

</xsl:stylesheet>
