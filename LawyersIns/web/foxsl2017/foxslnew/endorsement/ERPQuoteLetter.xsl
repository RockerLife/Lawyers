<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">


	<xsl:template match="/">
	<fo:root>

			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait"
					page-width="21cm" page-height="29.7cm" margin-top="0.9cm"
					margin-bottom="0.7in" margin-left="0.5in" margin-right="0.5in">
					<fo:region-body />
					<fo:region-before extent="3.8in" />
					<fo:region-after />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
			<!-- <fo:block text-align="center">
						<fo:external-graphic
								src="../LawyersIns/image/NewLawyersLogo.PNG"
							content-height="6em" content-width="20em" />
					</fo:block> -->

					<fo:block>
						<fo:table>
							<fo:table-column column-width="110mm" />
							<fo:table-column column-width="90mm" />
							
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="11px" font-family="Arial">
										<fo:external-graphic src="../LawyersIns/image/logo_protexureaccountants1.png"
							content-width="20em" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
									<fo:table>
							<fo:table-column column-width="100mm" />
									<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="11px" font-family="Arial">4200
											Commerce
											Court, Ste 102
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="11px" font-family="Arial">Lisle,
											IL 60532
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="11px" font-family="Arial">Phone
											(877) 569-4111
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="11px" font-family="Arial">Fax
											(630) 799-1796
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						</fo:table-cell>
						</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block space-after="5mm">
					<fo:table>
					<fo:table-column column-width="50mm"/>
					<fo:table-column column-width="110mm"/>
					<fo:table-body>
					<fo:table-row>
					<fo:table-cell number-columns-spanned="2" margin-left="2mm">
					<fo:block font-size="12px" font-family="Arial">
						This quotation expires on: <xsl:value-of select="response/ERPQuoteExpirationDate" />
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell>
					<fo:block>
					&#160;
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					Named Insured:
					</fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					&#160; <xsl:value-of select="response/InsuredName" />
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					Policy Number:
					</fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					&#160;<xsl:value-of select="response/PolicyNumber" />
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell number-columns-spanned="2">
					<fo:block>
					&#160;
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell number-columns-spanned="2" border="1pt solid black" margin-left="1cm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
						Optional Extended Reporting Period (ERP): 
					</fo:block>
					
					
					<xsl:choose>
                      <xsl:when test="response/IsClaimExpensesType='N'">
                        <fo:block font-family="Arial" font-size="12px" font-weight="bold">
						Limit of Liability / Claim Expenses Inside the Limit of Liability 
					</fo:block>
                      </xsl:when>
                      <xsl:otherwise>
                        <fo:block font-family="Arial" font-size="12px" font-weight="bold">
						Limit of Liability / Claim Expenses outside the Limit of Liability 
					</fo:block>
                      </xsl:otherwise>
                      </xsl:choose>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="1cm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					Limit of Liability:
					</fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					&#160;$<xsl:value-of select="response/AggregateLimit"/> each Claim/$<xsl:value-of select="response/OccuranceLimit"/> Annual Aggregate 
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="1cm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					Deductible:
					</fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-size="12px" font-family="Arial">
					&#160;$<xsl:value-of select="response/OccuranceDeductible"/> Each Claim
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					</fo:table-body>
					</fo:table>
					</fo:block>
					
					<fo:block space-after="1cm">
					<fo:table>
					<fo:table-column column-width="60mm"/>
					<fo:table-column column-width="100mm"/>
					<fo:table-body>
					<fo:table-row border="1pt solid black">
					<fo:table-cell margin-left="2mm" padding-top="2mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					Length of ERP
					</fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" padding-top="2mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					Premium
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<xsl:choose>
                       <xsl:when test="response/isAttorneyRetiring= 'Y'">
					<fo:table-row>
						<fo:table-cell margin-left="8mm" border="1pt solid black" padding-top="2mm">
							<fo:block font-family="Arial" font-size="12px" font-weight="bold">
							 
		                        <fo:block>
		                          <fo:inline font-family="ZapfDingbats" font-weight="bold" font-size="12pt"
		                            border="1pt black solid">&#x2714;</fo:inline>&#160; Unlimited:
		                        </fo:block>
		                      
							</fo:block>
						</fo:table-cell>
						<fo:table-cell margin-left="2mm" border="1pt solid black" padding-top="2mm" padding-bottom="2mm"  >
							<fo:block font-family="Arial" font-size="12px" font-weight="bold">
							&#160;<xsl:value-of select="response/UnlimitedERPForAttorney"/> 
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					</xsl:when>
                      
                      </xsl:choose> 
                      <xsl:choose>
                       <xsl:when test="response/oneYear= '1'">
						<fo:table-row>
							<fo:table-cell margin-left="8mm" border="1pt solid black" padding-top=".5mm">
							<fo:block font-family="Arial" font-size="12px" font-weight="bold">
							 
		                        <fo:block>
		                          <fo:inline font-family="ZapfDingbats" font-weight="bold" font-size="12pt"
		                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; 12 months:
		                        </fo:block>
		                      
							</fo:block>
							</fo:table-cell>
							<fo:table-cell margin-left="2mm" border="1pt solid black" padding-top=".5mm">
							<fo:block font-family="Arial" font-size="12px" font-weight="bold">
							&#160;<xsl:value-of select="response/oneYearERP"/> 
							</fo:block>
							</fo:table-cell>
						</fo:table-row>
						</xsl:when>
                      
                      </xsl:choose> 
                       <xsl:choose>
                       <xsl:when test="response/threeYear= '3'">
					<fo:table-row>
					<fo:table-cell margin-left="8mm" border="1pt solid black" padding-top=".5mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					
                        <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-weight="bold" font-size="12pt"
                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; 36 months:
                        </fo:block>
                    
					</fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" border="1pt solid black" padding-top=".5mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					&#160;<xsl:value-of select="response/threeYearsERP"/> 
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					  </xsl:when>
                     
                      </xsl:choose>
                        <xsl:choose>
                       <xsl:when test="response/fiveYear= '5'">
					<fo:table-row>
					<fo:table-cell margin-left="8mm" border="1pt solid black" padding-top=".5mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					 <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-weight="bold" font-size="12pt"
                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; 60 months:
                        </fo:block>
                    </fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" border="1pt solid black" padding-top=".5mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					&#160;<xsl:value-of select="response/fiveYearsERP"/> 
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					 </xsl:when>
                      
                      </xsl:choose>
					<xsl:choose>
                      <xsl:when test="response/sixYear= '6'">
						<fo:table-row>
						<fo:table-cell margin-left="8mm" border="1pt solid black" padding-top=".5mm">
							<fo:block font-family="Arial" font-size="12px" font-weight="bold">
							 
		                        <fo:block>
		                          <fo:inline font-family="ZapfDingbats" font-weight="bold" font-size="12pt"
		                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; 72 months:
		                        </fo:block>
		                     
							</fo:block>
						</fo:table-cell>
						<fo:table-cell margin-left="2mm" border="1pt solid black" padding-top=".5mm">
						<fo:block font-family="Arial" font-size="12px" font-weight="bold">
						&#160;<xsl:value-of select="response/sixYearsERP"/> 
						</fo:block>
						</fo:table-cell>
						</fo:table-row>
					 </xsl:when>
                      
                      </xsl:choose>
                      <xsl:choose>
                       <xsl:when test="response/unlimitedYears= '10'">
					<fo:table-row>
					<fo:table-cell margin-left="8mm" border="1pt solid black" padding-top=".5mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					 
                        <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-weight="bold" font-size="12pt"
                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; Unlimited:
                        </fo:block>
                      
					</fo:block>
					</fo:table-cell>
					<fo:table-cell margin-left="2mm" border="1pt solid black" padding-top=".5mm">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					&#160;<xsl:value-of select="response/unlimitedYearsERP"/> 
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					</xsl:when>
                      
                      </xsl:choose>
                      
					</fo:table-body>
					</fo:table>
					</fo:block>
					
					<fo:block>
					<fo:table>
					<fo:table-column column-width="100mm"/>
					<fo:table-column column-width="60mm"/>
					<fo:table-body>
					<fo:table-row>
					<fo:table-cell margin-left="2mm" number-columns-spanned="2">
					<fo:block font-family="Arial" font-size="12px" font-weight="bold">
					Instructions for Binding Coverage
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="8mm" border="1pt solid black" number-columns-spanned="2">
					<fo:block font-family="Arial" font-size="12px">
          			<fo:list-block>
                	<fo:list-item>
                  	<fo:list-item-label>
                    <fo:block>
                      1.
                    </fo:block>
                 	</fo:list-item-label>
                  	<fo:list-item-body start-indent="12mm">
                  	<fo:block font-family="Arial" font-size="12px">
                  	Please review all parts of this quotation.
                  	</fo:block>
                  	</fo:list-item-body>
                  	</fo:list-item>
                  	</fo:list-block>
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="8mm" border="1pt solid black" number-columns-spanned="2">
					<fo:block font-family="Arial" font-size="12px">
          			<fo:list-block>
                	<fo:list-item>
                  	<fo:list-item-label>
                    <fo:block>
                      2.
                    </fo:block>
                 	</fo:list-item-label>
                  	<fo:list-item-body start-indent="12mm">
                  	<fo:block font-family="Arial" font-size="12px">
                  	Check the coverage option desired above.
                  	</fo:block>
                  	</fo:list-item-body>
                  	</fo:list-item>
                  	</fo:list-block>
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="8mm" border="1pt solid black" number-columns-spanned="2">
					<fo:block font-family="Arial" font-size="12px">
          			<fo:list-block>
                	<fo:list-item>
                  	<fo:list-item-label>
                    <fo:block>
                      3.
                    </fo:block>
                 	</fo:list-item-label>
                  	<fo:list-item-body start-indent="12mm">
                  	<fo:block font-family="Arial" font-size="12px">
                  	Sign and date this form below and return it along with your premium payment prior to the quote expiration date.
                  	</fo:block>
                  	</fo:list-item-body>
                  	</fo:list-item>
                  	</fo:list-block>
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="8mm" border="1pt solid black" number-columns-spanned="2">
					<fo:block font-family="Arial" font-size="12px">
          			<fo:list-block>
                	<fo:list-item>
                  	<fo:list-item-label>
                    <fo:block>
                      4.
                    </fo:block>
                 	</fo:list-item-label>
                  	<fo:list-item-body start-indent="12mm">
                  	<fo:block font-family="Arial" font-size="12px">
                  	Make checks payable to: <fo:inline border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">Protexure Insurance Agency, Inc.</fo:inline> and send to: 
					4200 Commerce Ct., Suite 102, Lisle, IL 60532
                  	</fo:block>
                  	</fo:list-item-body>
                  	</fo:list-item>
                  	</fo:list-block>
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell number-columns-spanned="2">
					<fo:block>
					&#160;
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="2mm" number-columns-spanned="2" border="1pt solid black">
					<fo:block font-family="Arial" font-size="12px">
					My signature below constitutes my authorization for Protexure Insurance Agency, Inc. to bind the selected Extended Reporting Period coverage with United States Fire Insurance Company.
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-family="Arial" font-size="12px">
					Signature:
					</fo:block>
					<!-- <fo:block font-family="Arial" font-size="12px">
					Signature:<fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="12em" content-width="8em" />
					</fo:block> -->
					</fo:table-cell>
					<!-- <fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block>
					&#160;
					</fo:block>
					<fo:block>
					&#160;
					</fo:block>
					<fo:block font-family="Arial" font-size="12px">
					Date:&#160;<xsl:value-of select="response/currentdate"/> 
					</fo:block>
					<fo:block font-family="Arial" font-size="12px">
					Date:&#160;
					</fo:block>
					</fo:table-cell> -->
					
					
					<fo:table-cell margin-left="2mm" border="1pt solid black">
					<fo:block font-family="Arial" font-size="12px">
					Date:
					</fo:block>
					<fo:block>
					&#160;
					</fo:block>
					<fo:block>
					&#160;
					</fo:block>
					</fo:table-cell>
					
					</fo:table-row>
					</fo:table-body>
					</fo:table>
					</fo:block>
					
		</fo:flow>
		</fo:page-sequence>
		</fo:root>		
	</xsl:template>
</xsl:stylesheet>