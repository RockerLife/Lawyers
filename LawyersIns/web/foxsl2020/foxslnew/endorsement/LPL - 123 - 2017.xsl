<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
  
  <xsl:template match="/" name="LPL1232017">
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
										
										<xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
										<fo:block font-size="10px" text-align="left">Endorsement Number: LPL-123 (04/17)</fo:block>
										</xsl:if>
										<xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
										<fo:block font-size="10px" text-align="left">Endorsement Number: ISMIE ALA-04-F038 (09/01/2021)</fo:block>
										</xsl:if>
										
										<fo:block>&#160;</fo:block>
										
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
										<fo:block font-size="10px" text-align="left">Policy Number:  <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Policy Period:</fo:block>
										<fo:block font-size="10px" padding-left="8pt">
										<xsl:choose>
										<xsl:when test="response/cancelexpirydate !='' " >
										<xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /> to <xsl:value-of select="response/cancelexpirydate" />
										</xsl:when>
										<xsl:otherwise>
										<fo:block>
										<xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /> to <xsl:value-of select="response/policy_freeform_01/data/PolicyExpirationDate" />
										</fo:block>
								         </xsl:otherwise>
								       </xsl:choose>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/TransactionEffectiveDate" /></fo:block>
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

          <fo:block text-align="center" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-before="5mm" space-after="7mm" font-weight="bold">

       OPTIONAL EXTENDED REPORTING PERIOD ENDORSEMENT
   </fo:block>
          <fo:block padding-left="5pt" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="justify">
          <fo:block space-after="8mm">
          <fo:list-block>
                <fo:list-item space-after="4mm">
                  <fo:list-item-label>
                    <fo:block>
                      
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="4mm">
                 <fo:block space-after="4mm" >
					In consideration of an additional premium of <xsl:value-of select="response/endorsementPremium" />, it is agreed that the
					Optional Extended Reporting Period as detailed in paragraph D.2. of
					Section III. of this policy is in full force:
                  </fo:block>
                  </fo:list-item-body>
                  </fo:list-item>
                  
                  <fo:list-item space-after="2mm">
                  <fo:list-item-label start-indent="7mm">
                  <xsl:choose>
                      <xsl:when test="response/isUnlimitedYearsERP='N'">
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
                  from <xsl:value-of select="response/ERPEffective" /> to <xsl:value-of select="response/ErpExpirationDate" /> or      
                  </fo:block>
                  </fo:list-item-body>
                  </fo:list-item>

                  <fo:list-item space-after="4mm">
                  <fo:list-item-label start-indent="7mm">
                  <xsl:choose>
                       <xsl:when test="response/isUnlimitedYearsERP='Y'">
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
                  for an unlimited period.       
                  </fo:block>
                  </fo:list-item-body>
                  </fo:list-item>
                  
                  </fo:list-block>
                  
                  </fo:block>
                  
                  <fo:block start-indent="4mm">
                  All other terms, conditions and limitations of the policy remain unaltered.
                  </fo:block>
                  <fo:block margin-top="6.1in"/>
                    <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
                  <fo:block space-after="0.7cm">
                  <fo:table>
					<fo:table-column column-width="80mm"/>
					<fo:table-column column-width="120mm"/>
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
						<fo:table-cell text-align="center">
						<fo:block>
						&#160;
						</fo:block>
						<fo:block>
						&#160;&#160;&#160;&#160;&#160;<xsl:value-of select="response/general_freeform_01/data/CurrentDate"/>
						</fo:block>
						<fo:block font-size="9px" color="grey">
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;_________________________________________
						</fo:block>
						<fo:block font-size="9px" color="grey">
						&#160; &#160;  &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; Date
						</fo:block>
						</fo:table-cell>
						</fo:table-row>
						</fo:table-body>
					</fo:table>
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
                 <fo:block font-size="9px" color="grey" text-align="left">
						LPL-123 (04/17)&#160; &#160; &#160; &#160; &#160; &#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
						&#160;&#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
						&#160;&#160;&#160; &#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;
						&#160;&#160; &#160;&#160; &#160; &#160; &#160; &#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160; &#160; Page 1 of 1
					</fo:block>
					</xsl:if>
					<xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
					<fo:block font-size="9px" color="grey" text-align="left" margin-top="0.7cm">
						ISMIE ALA-04-F038 (09/01/2021)
						  &#160; &#160;
						&#160;&#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
						&#160;&#160;&#160; &#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;&#160; &#160;&#160; &#160;
						&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160;
						&#160;&#160; &#160;&#160; &#160; &#160; &#160; &#160;&#160; &#160;&#160;&#160;&#160; &#160;&#160; &#160; Page 1 of 1
					</fo:block>
					</xsl:if>
                  </fo:block>
            

         
  </xsl:template>
 
</xsl:stylesheet>
