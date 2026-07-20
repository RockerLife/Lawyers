<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
  
  <xsl:template match="/" name="Policycoverletter10_MA">
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
										<fo:block font-size="10px" text-align="left">Endorsement Number: ISMIE ALA-04-F045 (03/01/2022)</fo:block>
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
										<fo:block font-size="10px" padding-left="8pt"><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /> to <xsl:value-of select="response/policy_freeform_01/data/PolicyExpirationDate" />
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
										<fo:block font-size="10px" text-align="left">Insurer: ISMIE Mutual Insurance Company</fo:block>
									</fo:table-cell>					
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="7px" padding-left="20pt" text-indent="0.5cm">Insert the policy number. The remainder of the information is to be completed only when this endorsement is issued subsequent to the preparation of the policy.</fo:block>
					<fo:block margin-top="2mm">
					</fo:block>

          <fo:block text-align="center" margin-left="1in" margin-right="1in" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-before="5mm" space-after="7mm" font-weight="bold">

        MIDTERM ADDITION OF ATTORNEY ENDORSEMENT
   </fo:block>
          <fo:block padding-left="5pt" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="justify">
          <fo:block>
          <fo:list-block>
                <!-- <fo:list-item space-after="4mm">
                  <fo:list-item-label>
                    <fo:block>
                      
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="4mm">
                 <fo:block space-after="4mm" >
               In consideration of: (check one box only)
                    </fo:block>
                  </fo:list-item-body>
                  </fo:list-item> -->
                  
                  <!-- <fo:list-item space-after="2mm">
                  <fo:list-item-label start-indent="7mm">
                  <xsl:choose>
                       <xsl:when test="response/isNonFinancialEndorsement= 'N'">
                        <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#x2714;</fo:inline> &#160;
                        </fo:block>
                      </xsl:when>
                      <xsl:otherwise>
                        <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                        </fo:block>
                      </xsl:otherwise>
                      </xsl:choose>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="14mm">
                  <fo:block>
                  an additional premium of  <xsl:value-of select="response/PremiumValue"/>   
                  </fo:block>
                  </fo:list-item-body>
                  </fo:list-item> -->

                  <!-- <fo:list-item space-after="4mm">
                  <fo:list-item-label start-indent="7mm">
                  <xsl:choose>
                       <xsl:when test="response/isNonFinancialEndorsement= 'Y'">
                        <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#x2714;</fo:inline> &#160;
                        </fo:block>
                      </xsl:when>
                      <xsl:otherwise>
                        <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                        </fo:block>
                      </xsl:otherwise>
                      </xsl:choose>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="14mm">
                  <fo:block>
                  the premium charged        
                  </fo:block>
                  </fo:list-item-body>
                  </fo:list-item> -->
                  
                  <fo:list-item space-after="4mm">
                  <fo:list-item-label>
                    <fo:block>
                      
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="4mm">
                  <fo:block>In consideration of an additional premium of <xsl:value-of select="response/PremiumValue"/>   </fo:block>
                 <fo:block space-after="4mm" >
               it is agreed that the attorney whose name is listed below in Column A shall be deemed an 
               <fo:inline font-weight="bold">Insured</fo:inline> under this policy, effective the date shown in Column B below.  If a date 
               appears in Column C below, then Item 6. of the Declarations, <fo:inline font-weight="bold">Prior Acts Date</fo:inline>, is 
               amended to the date shown in Column C for that attorney:
                    </fo:block>
                  </fo:list-item-body>
                  </fo:list-item>
                  </fo:list-block>
                  
                  <fo:block space-after="8mm">
                  <fo:table>
                  <fo:table-column column-width="65mm"/>
                  <fo:table-column column-width="65mm"/>
                  <fo:table-column column-width="60mm"/>
                  <fo:table-body>
                  <fo:table-row>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  A
                  </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  B
                  </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  C
                  </fo:block>
                  </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  <fo:inline text-decoration="underline">
                  Attorney Name
                  </fo:inline>
                  </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  <fo:inline text-decoration="underline">
                  Effective Date of Addition
                  </fo:inline>
                  </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  <fo:inline font-weight="bold" text-decoration="underline">
                  Prior Acts Date
                  </fo:inline>
                  </fo:block>
                  </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>&#160;</fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>&#160;</fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>&#160;</fo:block>
                  </fo:table-cell>
                  </fo:table-row>
                   <xsl:for-each select="response/ListOfAttorneys_list_01/data">
                          <fo:table-row>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left">
                                <xsl:value-of select="AttorneyName"/>
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center">
                                <xsl:value-of select="TransactionEffectiveDate"/>
                              </fo:block>
                            </fo:table-cell>
							<fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center">
                                <xsl:value-of select="AttorneyPriorActDate"/>
                              </fo:block>
                            </fo:table-cell>
                          </fo:table-row>
                        </xsl:for-each>
                  <fo:table-row>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  &#160;
                  </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  &#160;
                  </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-bottom="1mm" padding-top="1mm" text-align="center">
                  <fo:block>
                  &#160;
                  </fo:block>
                  </fo:table-cell>
                  </fo:table-row>
                 
                
                  </fo:table-body>
                  </fo:table>
                  </fo:block>
                  
                  </fo:block>
                  <fo:block start-indent="4mm">
                  All other terms, conditions and limitations of the policy remain unaltered.
                  </fo:block>
                  <fo:block margin-top="8cm"/>
                  <fo:block space-after="0.7cm">
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

                 <fo:block margin-top="4cm"/>
				    <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F045 MA (09/01/2022)
		        	 	<fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
        	 		</fo:block>
                  </fo:block>
            

         
  </xsl:template>
 
</xsl:stylesheet>
