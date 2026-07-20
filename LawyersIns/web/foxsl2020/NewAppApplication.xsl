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
                               page-height="29.7cm" margin-top="0.1in" margin-bottom="0.5in" margin-left="0.3in" margin-right="0.3in">
          <fo:region-body margin-top="0.3in"/>
          <fo:region-before extent="1.0in"/>
          <fo:region-after />
        </fo:simple-page-master>
      </fo:layout-master-set>

      <fo:page-sequence master-reference="A4-portrait" >
       <fo:static-content flow-name="xsl-region-before">
	   <fo:block>
            <fo:table margin-top="3mm">
              <fo:table-column column-width = "200mm"/>
              <fo:table-body>
                  <fo:table-row>
                  <fo:table-cell>
                     <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				
				<fo:table-row>
                  <fo:table-cell>
                     <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				<fo:table-row>
                  <fo:table-cell>
                     <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				<fo:table-row>
                  <fo:table-cell>
                     <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
		  
		
          <fo:block margin-left="340px" color="grey">
            <!--  	<fo:external-graphic src="../LawyersIns/image/crum_logo.png" content-height="4em" content-width="12em"/>-->

           

          </fo:block>
        </fo:static-content>
		

        <fo:static-content flow-name="xsl-region-after">
          <fo:table>
            <fo:table-column column-width = "85mm" />
            <fo:table-column column-width = "100mm" />
            <fo:table-body>
              <fo:table-row>
                <fo:table-cell>
                  <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">
                    <xsl:choose>
                      <xsl:when test="response/policy_freeform_01/data/CompanyKey= 1">
                            <xsl:choose>
		                      <xsl:when test="response/policy_freeform_01/data/StateCode= 'FL'">
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">  LPL – 101 (02/20) FL </fo:block>
		                      </xsl:when>
		                      <!-- <xsl:when test="response/policy_freeform_01/data/StateCode= 'CT'">
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">  LPL - CT - 101 (09/10)</fo:block>
		                      </xsl:when> -->
		                      <xsl:when test="response/policy_freeform_01/data/StateCode= 'NH'">
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">  LPL – NH - 101 (01/20) </fo:block>
		                      </xsl:when>
		                      <xsl:when test="response/policy_freeform_01/data/StateCode= 'IA'">
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">   LPL – IA - 101 (01/20) </fo:block>
		                      </xsl:when>
		                      <xsl:otherwise>
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey"> LPL – 101 (01/21)</fo:block>
		                      </xsl:otherwise>
		                    </xsl:choose>
                    </xsl:when>
                           <xsl:when test="response/policy_freeform_01/data/CompanyKey= 3">
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">  ISMIE ALA-04-S001 (09/01/2021) </fo:block>
		                      </xsl:when> 
		                       <xsl:when test="response/policy_freeform_01/data/CompanyKey= 4">
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">  LPL- Pro 100 (10/21) </fo:block>
		                      </xsl:when>                   
                      <xsl:otherwise>
                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey"> &#160;</fo:block>
                      </xsl:otherwise>
                    </xsl:choose>
                  </fo:block>
                </fo:table-cell>
                
                <fo:table-cell>
                  <fo:block text-align="right" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">
                   Page <fo:page-number/> of 
						<fo:page-number-citation 
						ref-id="TheVeryLastPage"/>
					</fo:block>
                </fo:table-cell>
              </fo:table-row>
            </fo:table-body>
          </fo:table>
        </fo:static-content>

        <fo:flow flow-name="xsl-region-body" >
        
       <xsl:if test="response/policy_freeform_01/data/CompanyKey= 1">
          <fo:block >
            <fo:table>
              <fo:table-column column-width = "70mm" />
              <fo:table-column column-width = "50mm" />
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell >
                    <fo:block font-size="12px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
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
                  <fo:table-cell >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                      <fo:table>
                        <fo:table-column column-width = "4.7in" />


                        <fo:table-body>
						<fo:table-row>
                  <fo:table-cell>
                     <fo:block  color="grey"  text-align="right">
					 <xsl:choose>
								<xsl:when
									test="response/policy_freeform_01/data/CompanyKey= 1">
									<fo:external-graphic
										src="../LawyersIns/image/crum2_logo.png" content-width="200px" />
										
								</xsl:when>
								
							</xsl:choose>
					 </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <xsl:if test="response/policy_freeform_01/data/CompanyKey= 1">
						<fo:table-row>
							<fo:table-cell>
									<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
							</fo:table-cell>
				  		</fo:table-row>
                          <fo:table-row>
                            <fo:table-cell >
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="right">UNITED STATES FIRE INSURANCE COMPANY</fo:block>
                		 </fo:table-cell>
                          </fo:table-row>
                          <fo:table-row>
                            
                            <fo:table-cell >
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="right">305 MADISON AVENUE, MORRISTOWN, NJ 07962</fo:block>


                            </fo:table-cell>
                          </fo:table-row>
                          
                          
                          </xsl:if>
                        </fo:table-body>
                      </fo:table>

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          </xsl:if>
          
            <xsl:if test="response/policy_freeform_01/data/CompanyKey= 3">
           <fo:block  color="grey" text-align="left">
			<fo:external-graphic
			src="../LawyersIns/image/ISMIE_logo.png" content-width="200px" />
			 </fo:block>
          </xsl:if>
          
            <xsl:if test="response/policy_freeform_01/data/CompanyKey= 4">
          <fo:block >
            <fo:table>
              <fo:table-column column-width = "70mm" />
              <fo:table-column column-width = "50mm" />
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell >
                    <fo:block font-size="12px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                      <fo:table>
                        <fo:table-column column-width = "4.7in" />


                        <fo:table-body>
						<fo:table-row>
                  <fo:table-cell>
                     <fo:block  color="grey"  text-align="right">
					 <xsl:choose>
								<xsl:when
									test="response/policy_freeform_01/data/CompanyKey= 4">
									<fo:external-graphic
										src="../LawyersIns/image/Protexure.png" content-width="200px" />
										
								</xsl:when>
								
							</xsl:choose>
					 </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <xsl:if test="response/policy_freeform_01/data/CompanyKey= 4">
						<fo:table-row>
							<fo:table-cell>
									<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
							</fo:table-cell>
				  		</fo:table-row>
                          <fo:table-row>
                            <fo:table-cell >
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="right">4200 Commerce Court, Suite 102 Lisle, IL 60532</fo:block>
                		 </fo:table-cell>
                          </fo:table-row>
                          <fo:table-row>
                            
                            <fo:table-cell >
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="right"><fo:inline font-weight="bold">Phone:</fo:inline> 877-569-4111 / <fo:inline font-weight="bold">Fax:</fo:inline> 630-799-1796 / <fo:inline font-weight="bold">Email:</fo:inline> info@protexure.com</fo:block>


                            </fo:table-cell>
                          </fo:table-row>
                          
                          
                          </xsl:if>
                        </fo:table-body>
                      </fo:table>

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          </xsl:if>
          
          <fo:block>
            <fo:table margin-top="3mm">
              <fo:table-column column-width = "200mm"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block></fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell>
                        <fo:block  font-size="14px" font-family="Arial Black" font-weight="bold" text-align="center">LAWYERS PROFESSIONAL LIABILITY APPLICATION</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block>
            <fo:table margin-top="3mm" border="0.0pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row >
                  <fo:table-cell >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">NOTICE: COVERAGE FOR WHICH THIS APPLICATION IS MADE IS WRITTEN ON A CLAIMS MADE AND REPORTED BASIS MEANING, EXCEPT AS OTHERWISE PROVIDED, COVERAGE APPLIES ONLY TO <fo:inline font-weight="bold">CLAIMS</fo:inline> FIRST MADE AGAINST THE <fo:inline font-weight="bold">INSURED</fo:inline> AND REPORTED TO THE <fo:inline font-weight="bold">INSURER</fo:inline> DURING THE <fo:inline font-weight="bold">POLICY PERIOD</fo:inline> OR DURING ANY APPLICABLE EXTENDED REPORTING PERIOD.</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				 <fo:table-row >
                  <fo:table-cell >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				 <fo:table-row >
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">CAREFULLY READ THE ENTIRE POLICY FOR WHICH THIS APPLICATION IS MADE.  WORDS AND PHRASES WHICH ARE PRINTED IN <fo:inline font-weight="bold">BOLD TYPEFACE</fo:inline> HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OF THE POLICY.</fo:block>
                  </fo:table-cell>
                </fo:table-row >
				<fo:table-row >
                  <fo:table-cell >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				 <fo:table-row >
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">THE APPLICATION, ITS ATTACHMENTS AND ALL PREVIOUS APPLICATIONS AND THEIR ATTACHMENTS SHALL SERVE AS THE BASIS OF THE POLICY, AND SHALL BECOME PART OF SUCH POLICY SHOULD A POLICY BE ISSUED, AS IF PHYSICALLY ATTACHED.  THE <fo:inline font-weight="bold">INSURER</fo:inline> RELIES UPON THE APPLICATION IN ISSUING THE POLICY.  COMPLETION OF THIS APPLICATION DOES NOT IN ANY WAY IMPLY SUCH COVERAGE UNDER THE POLICY.  COVERAGE IS AFFORDED ONLY IF AND TO THE EXTENT INDICATED BY THE TERMS AND CONDITIONS OF THE POLICY IF ISSUED.</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				<fo:table-row >
                  <fo:table-cell >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				<fo:table-row >
                  <fo:table-cell>
                    <fo:block font-size="11px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold"> Wherever the word "You, Your or Firm" is used, it will be deemed to include all attorneys within the firm and any predecessor firms.</fo:block>
                  </fo:table-cell>
                </fo:table-row>
				<fo:table-row >
                  <fo:table-cell >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;</fo:block>

                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
			<!--
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
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">PROVIDED, COVERAGE APPLIES ONLY TO</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-style="italic" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="7px">&#160;CLAIMS</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> FIRST MADE AGAINST THE </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-style="italic" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="7px">INSURED</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> AND REPORTED TO THE</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-style="italic" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="7px">INSURER</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> DURING THE </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-style="italic" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="7px">POLICY PERIOD</fo:block>

                  </fo:table-cell>
                </fo:table-row>

              </fo:table-body>
            </fo:table>
            <fo:table>
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="7px">
                      <xsl:choose>
                        <xsl:when test="response/policy_freeform_01/data/StateCode= 'CT'">
                          OR DURING ANY APPLICABLE EXTENDED REPORTING PERIOD.
                        </xsl:when>
                        <xsl:otherwise>
                        </xsl:otherwise>
                      </xsl:choose>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>

            <fo:table margin-top="3mm">
              <fo:table-column column-width="151mm"/>
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">CAREFULLY READ THE ENTIRE POLICY FOR WHICH THIS APPLICATION IS MADE. WORDS AND PHRASES WHICH ARE PRINTED IN</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-style="italic" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="7px"> BOLD ITALIC </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
            <fo:table margin-top="0.5mm">
              <fo:table-column column-width="13mm"/>
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-style="italic" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="7px">TYPEFACE</fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV OF THE POLICY.</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>

            <fo:table margin-top="3mm">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">THE APPLICATION, ITS ATTACHMENTS AND ALL PREVIOUS APPLICATIONS AND THEIR ATTACHMENTS SHALL SERVE AS THE BASIS OF THE POLICY, AND </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
            <fo:table margin-top="0.5mm">
              <fo:table-column column-width="126mm"/>
              <fo:table-column column-width="12mm"/>
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">SHALL BECOME PART OF SUCH POLICY SHOULD A POLICY BE ISSUED, AS IF PHYSICALLY ATTACHED. THE</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.0pt solid black">
                    <fo:block font-style="italic" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="7px">INSURER</fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">RELIES UPON THE APPLICATION IN</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
            <fo:table margin-top="0.5mm">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">ISSUING THE POLICY. COMPLETION OF THIS APPLICATION DOES NOT IN ANY WAY IMPLY SUCH COVERAGE UNDER THE POLICY. COVERAGE IS </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">AFFORDED ONLY IF AND TO THE EXTENT INDICATED BY THE TERMS AND CONDITIONS OF THE POLICY IF ISSUED.</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" margin-top="2mm">
           

          </fo:block>
          <fo:block>-->
            <fo:table margin-top="1mm" border="0.1pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block></fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block>
            <fo:table margin-top="1mm" border="0.5pt solid black" background-color="LightGray ">
              <fo:table-column></fo:table-column>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">&#160;&#160;FIRM PROFILE </fo:block>
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
            <fo:table>
              <fo:table-column column-width="1in" />
              <fo:table-column column-width="4in"/>
              <fo:table-column  />
              <fo:table-column column-width="1.6in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="18pt">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"
											>
                      1.&#160;&#160;Firm Name:

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="18pt"  number-columns-spanned="3">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
                      <xsl:value-of select="response/policy_freeform_01/data/AccountName"></xsl:value-of>

                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center"
											>
                      Legal name of the Firm to be insured

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell  padding-top="10px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right"
											>
                      Address:&#160;&#160;

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
                        <xsl:value-of select="response/policy_freeform_01/data/Street"></xsl:value-of>

                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center"
											>
                   Physical location of Principal office

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell  padding-top="10px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      County:&#160;&#160;</fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
		                   
					<xsl:choose>
						<xsl:when test="response/policy_freeform_01/data/CountyDesc != '' ">
							<xsl:value-of select="response/policy_freeform_01/data/CountyDesc"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<fo:block>&#160;</fo:block>
						</xsl:otherwise>
					</xsl:choose>
		                       
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>
                
                
                
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px"  font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
											
                      Address Line 2:

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" start-indent="0.3cm">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black" >
                       
                       
                       
                       <xsl:choose>
         <xsl:when test="response/policy_freeform_01/data/Address2 != '' ">
             <xsl:value-of select="response/policy_freeform_01/data/Address2"></xsl:value-of>
         </xsl:when>
         <xsl:otherwise>
          <fo:block>&#160;</fo:block>
         </xsl:otherwise>
       </xsl:choose>
                       
                       <!--  <xsl:value-of select="response/policy_freeform_01/data/Address2"></xsl:value-of> -->
          </fo:block>
                  
                  
                  
   
                  </fo:table-cell>

                 

                </fo:table-row>
                
                
                
                
                
                
                
              </fo:table-body>
            </fo:table>
          </fo:block>


          <fo:block>
            <fo:table>
              <fo:table-column column-width="1in" />
              <fo:table-column column-width="2in"/>
              <fo:table-column column-width="0.5in" />
              <fo:table-column column-width="2in"/>
              <fo:table-column column-width="0.8in" />
              <fo:table-column column-width="1.0in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block start-indent="0.7in" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      City:&#160;&#160;
					</fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" start-indent="2mm" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
                      <xsl:value-of select="response/policy_freeform_01/data/City"></xsl:value-of>

                    </fo:block>

                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											>
                      &#160;&#160;&#160;&#160;State:&#160;&#160;

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" start-indent="2mm" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
                      <xsl:value-of select="response/policy_freeform_01/data/StateDesc"></xsl:value-of>

                    </fo:block>

                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      Zip:&#160;&#160;

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px"  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
                      <xsl:value-of select="response/policy_freeform_01/data/Zip"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/Zip4"/>

                    </fo:block>

                  </fo:table-cell>
                </fo:table-row>
                
              </fo:table-body>
            </fo:table>
          </fo:block>
		  <!-- <xsl:if test="(response/subproducernumber_freeform_02/data/SubProducerNumber != '')">
		  <fo:block>
            <fo:table>
              <fo:table-column column-width="1.5in" />
              <fo:table-column column-width="4in"/>
              <fo:table-body>
              <fo:table-row>
              <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Sub Producer Number:
					</fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" start-indent="2mm" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
											
			<xsl:choose>
         <xsl:when test="(response/subproducernumber_freeform_02/data/SubProducerNumber != '') and 
		         (response/subproducernumber_freeform_02/data/SubProducerNumber != 'P0000001') and 
		         (response/subproducernumber_freeform_02/data/SubProducerNumber != 'P0000004') and 
		         (response/subproducernumber_freeform_02/data/SubProducerNumber != 'P0000007') and 
		         (response/subproducernumber_freeform_02/data/SubProducerNumber != 'P0000031') and 
		         (response/subproducernumber_freeform_02/data/SubProducerNumber != 'P0000040') and 
		         (response/subproducernumber_freeform_02/data/SubProducerNumber != 'P0000041') and 
		         (response/subproducernumber_freeform_02/data/SubProducerNumber != 'P0000042')">
             <xsl:value-of select="response/subproducernumber_freeform_02/data/SubProducerNumber"></xsl:value-of>
         </xsl:when>
         <xsl:otherwise>
          <fo:block>&#160;</fo:block>
         </xsl:otherwise>
       </xsl:choose>
											
                      
                    </fo:block>

                  </fo:table-cell>
                
              </fo:table-row>
              </fo:table-body>
              </fo:table>
              </fo:block>
		  </xsl:if> -->
          <fo:block>
            <fo:table>
              <fo:table-column column-width="3in" />
              <fo:table-column column-width="1in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      2.&#160;&#160;When would you like your insurance to start?

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px"  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
                      <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate"></xsl:value-of>

                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center"
											>
                      mm/dd/yyyy

                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>


          <fo:block>
            <fo:table margin-top="3mm" border="0.5pt solid black" background-color="LightGray ">
              <fo:table-column></fo:table-column>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      &#160;&#160;INSURANCE
                      HISTORY
                    </fo:block>
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
            <fo:table>
              <fo:table-column column-width="6.5in" />
              <fo:table-column column-width="1.0in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      3.&#160;&#160;Do you currently carry professional liability insurance?

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px"  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right" >
                      	<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                           </xsl:choose>
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
		  <xsl:if test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'Y'">
          <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.2in" padding-top="10px" padding-bottom="10px">
            If <fo:inline font-weight="bold" font-style="italic">"Yes"</fo:inline>, provide the information requested below:

          </fo:block>

          <fo:block>
            <fo:table>
              <fo:table-column column-width="2.7in" />
              <fo:table-column column-width="1.5in"/>
              <fo:table-column column-width="2.0in" />
              <fo:table-column column-width="1.0in"/>
              <fo:table-body>
                <fo:table-row >
                  <fo:table-cell >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.2in">
                      Insurance Company (not broker/agent):
						
                    </fo:block>
                  </fo:table-cell>
				  <fo:table-cell  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black" >
                      <xsl:value-of select="response/coverage_freeform_1/data/InsuranceCompanyNamePross"/>
                    </fo:block>
                  </fo:table-cell>
				  <!--
                  <fo:table-cell  padding-top="14pt" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid" start-indent="0.1in"
											border-bottom-color="black">
                      "Policy Effective date"
						<xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate"/>
                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center"
											>
                      mm/dd/yyyy

                    </fo:block>
                  </fo:table-cell>
				  -->
                  <fo:table-cell  start-indent="0.3in">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Policy Expiration Date:
                     

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell   >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                   <xsl:value-of select="response/coverage_freeform_1/data/PolicyExpirationDatePross"/>

                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center"
											>
                      mm/dd/yyyy

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

  <!--         <fo:block>
            <fo:table>
              <fo:table-column column-width="1.5in" />
              <fo:table-column column-width="1.4in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="1.7in" />
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="2in" />

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.2in" padding-top="10px">
                      Limits of Liability: $

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
					<xsl:value-of select="response/coverage_freeform_1/data/LimitAmount"/>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block padding-top="10px" start-indent="0.1in">
                          <xsl:choose>
                    	 <xsl:when test="response/coverage_freeform_1/data/IsClaimExpLiability = 'DR'">
                   
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

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                  
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  padding-top="10px" start-indent="0.1in">
                      Defense expenses reduce limits of liability
                    </fo:block>
                   
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="10px" >
                      <fo:inline font-weight="bold">OR</fo:inline>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="10px" >
                     <xsl:choose>
                    	 <xsl:when test="response/coverage_freeform_1/data/IsClaimExpLiability = 'DA'">
                   
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


                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="10px" >
                      Defense expenses are paid in addition to limits of liability
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
 -->
 
 <fo:block>
            <fo:table>
              <fo:table-column column-width="1.5in" />
              <fo:table-column column-width="1.5in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="1.7in" />
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="2in" />

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.3in">
                      Limits of Liability: $

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
					<xsl:value-of select="response/coverage_freeform_1/data/LimitAmount"/>
                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center">
                      per claim / aggregate

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block padding-top="14pt" start-indent="0.1in">
                      <xsl:choose>
                    	 <xsl:when test="response/coverage_freeform_1/data/IsClaimExpLiability ='DR'">
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

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  padding-top="14pt" start-indent="0.1in">
                      Defense expenses reduce limits of liability
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="14pt" >
                      <fo:inline font-weight="bold">OR</fo:inline>

                    </fo:block>
                   
                    
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="14pt" start-indent="3mm">
                      <xsl:choose>
                    	 <xsl:when test="response/coverage_freeform_1/data/IsClaimExpLiability ='DA'">
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


                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell start-indent="3mm">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="14pt" >
                      Defense expenses are paid in addition to limits of liability
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <!-- <fo:block>
            <fo:table>
              <fo:table-column column-width="1.1in" />
              <fo:table-column column-width="1.1in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="0.9in" />
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="1.2in" />
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="2.6in" />

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.2in">
                      Deductible:$

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
						<xsl:value-of select="response/coverage_freeform_1/data/DeductibleAmount"/>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block padding-top="10px" start-indent="0.1in">
                      <xsl:choose>
                       <xsl:when test="response/coverage_freeform_1/data/IsPerClaim='PC'">
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

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  padding-top="13px" start-indent="0.1in">
                      Per Claim
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="15pt" >
                      <fo:inline font-weight="bold">OR</fo:inline>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="10px" >
                    <xsl:choose>
                       <xsl:when test="response/coverage_freeform_1/data/IsPerClaim='AA'">
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


                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="13px" >
                      Annual Aggregate
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="10px" >
                  <xsl:choose>
                       <xsl:when test="response/coverage_freeform_1/data/IsProfDefenceExpense='Y'">
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


                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="10px" >
                      Deductible does not apply to defense expenses (first dollar defense)
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block> -->
          <fo:block>
            <fo:table>
              <fo:table-column column-width="1.04in" />
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="0.9in" />
              <fo:table-column column-width="0.2in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="1.2in" />
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="2.6in" />

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.2in">
                     Deductible: $

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
						<xsl:value-of select="response/coverage_freeform_1/data/DeductibleAmount"/>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block padding-top="14pt" start-indent="0.1in">
                      <xsl:choose>
                       <xsl:when test="response/coverage_freeform_1/data/IsPerClaim ='PC'">
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

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  padding-top="16px" start-indent="0.1in">
                      Per Claim
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                  <fo:block margin-top="2mm"></fo:block>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="10px" >
                      <fo:inline font-weight="bold">OR</fo:inline>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="14pt" start-indent="4mm" >
                      <xsl:choose>
                       <xsl:when test="response/coverage_freeform_1/data/IsPerClaim ='AA'">
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


                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="16px" start-indent="4mm" >
                      Annual Aggregate
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="14pt" >
                      <xsl:choose>
                       <xsl:when test="response/coverage_freeform_1/data/IsProfDefenceExpense ='Y'">
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

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                  <fo:block margin-top="2mm"></fo:block>
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="10px" >
                      Deductible does not apply to defense expenses (first dollar defense)
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block>
            <fo:table>
              <fo:table-column column-width="4.84in" />
              <fo:table-column column-width="1in"/>
              <fo:table-column column-width="0.8in" />
              <fo:table-column column-width="0.8in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.2in">
                      Indicate the prior acts date (also known as retroactive date) for your policy:

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                     
                     
                     <xsl:choose>
						<xsl:when test="response/coverage_freeform_1/data/PriorActDatePross != '' ">
							<xsl:value-of select="response/coverage_freeform_1/data/PriorActDatePross"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<fo:block>&#160;</fo:block>
						</xsl:otherwise>
					</xsl:choose>
		                       
                     
                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center">
                      MM/DD/YYYY </fo:block>
                  </fo:table-cell>
                  <fo:table-cell   padding-top="10px">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Premium: $

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px"  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
				<xsl:choose>
         			<xsl:when test="response/coverage_freeform_1/data/ProInsPremium != '' ">
                      	<xsl:value-of select="response/coverage_freeform_1/data/ProInsPremium"/>
					</xsl:when>
         			<xsl:otherwise>
          				<fo:block>&#160;</fo:block>
         			</xsl:otherwise>
       			</xsl:choose>
                    </fo:block>

                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block>
            <fo:table>
              <fo:table-column column-width="6.427in" />
              <fo:table-column column-width="1in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.2in">
                      If your current policy has Full prior acts, please provide the number of years of continuous insurance:

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid" start-indent="0.1in"
														border-bottom-color="black" text-align="center">
			
				<xsl:choose>
					<xsl:when test="response/coverage_freeform_1/data/FirmYear != '' ">
						<xsl:value-of select="response/coverage_freeform_1/data/FirmYear"/>
					</xsl:when>
					<xsl:otherwise>
						<fo:block>&#160;</fo:block>
					</xsl:otherwise>
				</xsl:choose>
                     
                    

                    </fo:block>

                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
</xsl:if>
          <fo:block page-break-before="always"/>

          <fo:block>
            <fo:table margin-top="2mm" border="0.5pt solid black" background-color="LightGray ">
              <fo:table-column></fo:table-column>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      &#160;&#160;
                      COVERAGE
                    </fo:block>
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
            <fo:table>
              <fo:table-column column-width="3.3in" />
              <fo:table-column column-width="3.93in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      4.&#160;&#160;Indicate your desired coverage selection :

                    </fo:block>
                  </fo:table-cell>


                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block>
            <fo:table>
              <fo:table-column column-width="1.5in" />
              <fo:table-column column-width="1.5in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="1.7in" />
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="2in" />

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.3in">
                      Limits of Liability: $

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
					<xsl:value-of select="response/coverage_freeform_2/data/LimitAmount"/>
                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center">
                      per claim / aggregate

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block padding-top="14pt" start-indent="0.1in">
                      <xsl:choose>
                    	 <xsl:when test="response/coverage_freeform_2/data/IsClaimExpensesType ='N'">
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

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  padding-top="14pt" start-indent="0.1in">
                      Defense expenses reduce limits of liability
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="14pt" >
                      <fo:inline font-weight="bold">OR</fo:inline>

                    </fo:block>
                   
                    
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="14pt" start-indent="3mm">
                      <xsl:choose>
                    	 <xsl:when test="response/coverage_freeform_2/data/IsClaimExpensesType ='Y'">
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


                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell start-indent="3mm">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="14pt" >
                      Defense expenses are paid in addition to limits of liability
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block>
            <fo:table>
              <fo:table-column column-width="1.15in" />
              <fo:table-column column-width="1.1in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="0.9in" />
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="1.2in" />
              <fo:table-column column-width="0.2in" />
              <fo:table-column column-width="2.6in" />

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="0.3in">
                     Deductible: $

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
						<xsl:value-of select="response/coverage_freeform_2/data/DeductibleAmount"/>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block padding-top="14pt" start-indent="0.1in">
                      <xsl:choose>
                       <xsl:when test="response/coverage_freeform_2/data/IsClaimOptionType='N'">
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

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  padding-top="16px" start-indent="0.1in">
                      Per Claim
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                  <fo:block margin-top="2mm"></fo:block>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="10px" >
                      <fo:inline font-weight="bold">OR</fo:inline>

                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="14pt" start-indent="4mm" >
                      <xsl:choose>
                       <xsl:when test="response/coverage_freeform_2/data/IsClaimOptionType='Y'">
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


                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="16px" start-indent="4mm" >
                      Annual Aggregate
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell   >
                    <fo:block padding-top="14pt" >
                      <xsl:choose>
                       <xsl:when test="response/coverage_freeform_2/data/IsDollarDefense='Y'">
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

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell >
                  <fo:block margin-top="2mm"></fo:block>
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="10px" >
                      Deductible does not apply to defense expenses (first dollar defense)
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block>
            <fo:table margin-top="3mm" border="0.5pt solid black" background-color="LightGray ">
              <fo:table-column></fo:table-column>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      &#160;&#160;
                      ATTORNEYS
                    </fo:block>
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
            <fo:table>
              <fo:table-column column-width="3.3in" />


              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      5.&#160;&#160;List all of your attorneys :
						</fo:block>
                  </fo:table-cell>


                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:table margin-top="1mm" start-indent="12pt">

            <fo:table-column/>
            <fo:table-body>
              <fo:table-row>

                <fo:table-cell>
                  <fo:block>
                    <fo:table margin-top="2mm">
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-column column-width="1.4in"/>
                      <fo:table-column column-width="1.4in"/>
                      <fo:table-column column-width="0.8in"/>
                      <fo:table-column column-width="0.9in"/>
                      <fo:table-column column-width="0.9in"/>
                      <fo:table-column column-width="0.7in"/>

                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">Attorney Name</fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">*Attorney Designation</fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">States Licensed to Practice Law</fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  start-indent="4mm" text-align="center" font-weight="bold">Annual Hours Worked</fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  start-indent="4mm" text-align="center" font-weight="bold">Date Joined the Firm</fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">Prior Acts Date</fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block text-align="center" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="2mm" font-weight="bold"># of Years in Practice</fo:block>
                          </fo:table-cell>
                        <!--  	
							<fo:table-cell border="0.5pt solid black"><fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">Yrs. of Continuous Prof. Liab. Cov.</fo:block></fo:table-cell>
						-->
                        </fo:table-row>
                        <xsl:for-each select="response/attorneys_firm_list_01/data">
                          <fo:table-row>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                                <xsl:value-of select="AttorneyName"/>
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                                <xsl:value-of select="AttorneyDesg"/>
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                                <xsl:value-of select="LicensedStates"/>
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                                <xsl:value-of select="AnnualWorkedHours"/>
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                                <xsl:value-of select="AttorneyPriorActDate"/>
                              </fo:block>
                            </fo:table-cell>
                            <!-- <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                                <xsl:value-of select="PriorActDateAttorneysNew"/>
                              </fo:block>
                            </fo:table-cell> -->
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                                <xsl:value-of select="NumberOfYearsInPractice"/>
                              </fo:block>
                            </fo:table-cell>
                            <!-- 
									         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="NumberOfYearsMalpracticeCov"/></fo:block></fo:table-cell>
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
          <fo:block>
            <fo:table>
              <fo:table-column/>


              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="8pt" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="24pt">
                      *Attorney Designations :

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell  padding-top="8pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="24pt">
                      A=Associate, E=Employee, IC=Independent Contractor, OC=Of Counsel, P=Partner, RP=Retired Partner, O=Owner, S=Solo Practitioner

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block page-break-before="always"/>
          <fo:block >
            <fo:table margin-top="1mm" border="0.5pt solid black" background-color="LightGray ">
              <fo:table-column></fo:table-column>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      &#160;&#160;
                      AREA OF PRACTICE
                    </fo:block>
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
          <fo:block >
            <fo:table>
              <fo:table-column  />
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      6.&#160;&#160;Enter the percentage of revenue for each area below in the past fiscal year. Indicate percentages in whole numbers next 
                    </fo:block>
                  </fo:table-cell>
					</fo:table-row>
				<fo:table-row>
                  <fo:table-cell >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                     &#160;&#160;&#160;&#160;&#160;&#160;to the type of law the firm practices :

                    </fo:block>
                  </fo:table-cell>


                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>



 <xsl:if test="response/isBeforeLitigation='Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    Please complete the following chart for the past fiscal year, providing a breakdown of the firm’s litigation practice based on case information:
                  

          <fo:table margin-top="1mm"   start-indent="12pt">
            <fo:table-column column-width="3.7in"/>
            <fo:table-column column-width="3.7in"/>
            <fo:table-body>
              <fo:table-row>

               <fo:table-cell>
                  <fo:block>
                    <fo:table margin-top="2mm">
                      <fo:table-column column-width="2.9in"/>
                      <fo:table-column column-width="0.8in" />
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  start-indent="center" font-weight="bold">% OF REVENUE</fo:block>
                          </fo:table-cell>

                        </fo:table-row>
                        <xsl:for-each select="response/aopData_list_01/data">
                          <fo:table-row>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left">
                                <xsl:value-of select="DisplaySequence"/>. <xsl:value-of select="AOPDesc"/>
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center">
                                <xsl:value-of select="percentage"/>
                              </fo:block>
                            </fo:table-cell>

                          </fo:table-row>
                        </xsl:for-each>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                  <fo:block >
                    <fo:table margin-top="2mm">
                      <fo:table-column column-width="2.9in"/>
                      <fo:table-column column-width="0.8in"/>


                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.5pt solid black">
                            <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="center" font-weight="bold">% OF REVENUE</fo:block>
                          </fo:table-cell>

                        </fo:table-row>
                        <xsl:for-each select="response/aopData_list_02/data">
                          <fo:table-row>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif" text-align="left">
                               <xsl:value-of select="DisplaySequence"/>. <xsl:value-of select="AOPDesc"/>
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.5pt solid black">
                              <fo:block font-size="10px" font-family="Arial, Helvetica, sans-serif" text-align="center">
                                <xsl:value-of select="percentage"/>
                              </fo:block>
                            </fo:table-cell>

                          </fo:table-row>
                        </xsl:for-each>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                   <fo:block  start-indent="12pt">
		            <fo:table border="0.5pt solid black">
		              <fo:table-column column-width="2.9in"/>
                      <fo:table-column column-width="0.8in"/>
                      
		                <fo:table-body>
		                <fo:table-row>
		                  <fo:table-cell  border="0.5pt solid black" >
		                  <fo:block  font-size="10px" font-family="Arial, Helvetica, sans-serif"  text-align="left">
		                      
							Other Desc: 
		                    </fo:block>
		                    
		                  </fo:table-cell>
		                  <fo:table-cell  border="0.5pt solid black">
		                  <fo:block  font-size="10px" font-family="Arial, Helvetica, sans-serif" >
		                      
							<xsl:value-of select="response/AOP_CommentDesc_24"/>
		                    </fo:block>
		                    
		                  </fo:table-cell>
						
		
		                </fo:table-row>
		              </fo:table-body>
		             
		               <fo:table-body>
		                <fo:table-row>
		                  <fo:table-cell  border="0.5pt solid black">
		                  <fo:block  font-size="10px" font-family="Arial, Helvetica, sans-serif" >
		                      
							Total:
		                    </fo:block>
		                    
		                  </fo:table-cell>
		                  <fo:table-cell  border="0.5pt solid black">
		                  <fo:block  font-size="10px" font-family="Arial, Helvetica, sans-serif" text-align="center">
		                      
							<xsl:value-of select="response/aop_total"/> %
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
  
</fo:block>
  
</xsl:if>


        <xsl:if test="response/isBeforeLitigation='N'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                   <!--  Please complete the following plaintiff litigation on providing the approximate largest case size for the past fiscal year  for each area of the firm's
           		 		plaintiff litigation practice:   -->
         
                  <fo:table margin-top="1mm">
							<fo:table-column column-width="70mm"/>
							<fo:table-column column-width="25mm"/>
							<fo:table-column column-width="65mm"/>
							<fo:table-column column-width="25mm"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block></fo:table-cell>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block></fo:table-cell>
									<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-weight="bold">% OF REVENUE</fo:block></fo:table-cell>
								</fo:table-row>
								<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;1 . Administrative or Regulatory</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_54"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;36 . International</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_42"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;2 . Admiralty and Maritime</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_28"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;37 . Investment Counseling / Money Management</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_15"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;3 . Alternate Dispute Resolution</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_2"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;38 . Juvenile or Guardianship</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_43"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         <fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;4 . Anti-trust</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_1"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;39 . Labor Management</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_44"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;5 . Appellate Practice </fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_29"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;40 . Labor Union</fo:block></fo:table-cell>
					         		<fo:table-cell border="0	.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_45"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;6 . Aviation </fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_30"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;41 . Litigation - Other - Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_58"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;7 . Bankruptcy/Insolvency and Reorganization(1)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_3"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;Desc</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/AOP_CommentDesc_56"/> </fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;8 . Civil Litigation - Defense </fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_64"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;42 . Litigation Other - Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_67"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;9 . Civil Litigation - Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_55"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;Desc</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/AOP_CommentDesc_65"/> </fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;10 . Civil Rights or Discrimination</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_4"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;43 . Mass Tort Litigation / Class Actions -Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_68"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;11 . Collections / Repossession</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_31"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;44 . Mass Tort Litigation / Class Actions -Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_59"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;12 . Communications / Media</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_32"/> %</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;45 . Medical Malpractice - Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_69"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;13 . Construction</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_33"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;46 . Medical Malpractice - Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_60"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;14 . Construction Litigation - Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_65"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;47 . Mergers and Acquisitions</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_46"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;15 . Construction Litigation - Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_56"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;48 . Natural Resources</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_16"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;16 . Copyright / Trademark (3)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_6"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;49 . Non-Medical Malpractice - Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_70"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;17 . Corporate / Commercial / Business Litigation- Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_66"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;50 . Non-Medical Malpractice - Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_61"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;18 . Corporate / Commercial / Business Litigation- Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_57"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;51 . Non-Profit / Charities</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_47"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;19 . Corporate / Commercial / Business (11)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_5"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;52 . Patent</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_48"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;20 . Creditor Debtor Rights</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_34"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;53 . Personal Injury Litigation - Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_71"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;21 . Criminal Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_35"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;54 . Personal Injury Litigation - Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_62"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         	 	<fo:table-row>							
					         		<!--<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;22 . Criminal Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_7"/> %</fo:block></fo:table-cell>
					         		-->
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;23 . Education</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_36"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;55 . Product Liability Litigation - Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_72"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	 
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;24 . Elder Law</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_9"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;56 . Product Liability Litigation - Plaintiff (7)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_63"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;25 . Employee Benefits (ERISA)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_11"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;57 . Real Estate Commercial (8)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_20"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;26 . Employment</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_37"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;58 . Real Estate Residential (8)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_27"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;27 . Entertainment / Sports</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_38"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;59 . Securities</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_21"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;28 . Environmental</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_10"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;60 . Social Security / Disability</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_49"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;29 . Family Law (4)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_8"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;61 . Tax (9)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_22"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;30 . Financial Institution/Banking/Finance (5)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_12"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;62 . Traffic or DUI/DWI Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_50"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;31 . Government / Municipal / Zoning (6)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_13"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;63 . Trusts / Estates / Wills / Probate (10)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_24"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;32 . Healthcare</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_39"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;64 . Utilities</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_51"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;34 . Immigration</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_14"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;65 . Workers Compensation Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_52"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<!-- <fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;33 . Immigration</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_40"/> %</fo:block></fo:table-cell>
					         		 -->
					         		 
					         		 <fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;35 . Insurance Defense</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_41"/> %</fo:block></fo:table-cell>
					         		 
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;66 . Workers Compensation Plaintiff</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_53"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">&#160;&#160;</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;67 . Other</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_25"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	
					         		<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">&#160;&#160;</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;Other Desc:</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/AOP_CommentDesc_24"/> </fo:block></fo:table-cell>
					         		
					         	</fo:table-row>	
					         		<!-- <fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;13 . Construction</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aopPercentage_33"/> %</fo:block></fo:table-cell>
					         		
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;Z. Other (please describe)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/AOP_PercentageValue_24"/> %</fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row> -->
					         	
					         	
					         	
					         	<!-- <fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"></fo:block></fo:table-cell>
					         		
					         	<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;<xsl:value-of select="response/AOP_CommentDesc_24"/></fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"></fo:block></fo:table-cell>
					         		
					         		
					         		
					         	</fo:table-row>	 -->
					         	
					         	
					         	
					         		
					         	<fo:table-row>							
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"></fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"></fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">&#160;&#160;Total (must equal 100%)</fo:block></fo:table-cell>
					         		<fo:table-cell border="0.5pt solid black"><fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"><xsl:value-of select="response/aop_total"/> %</fo:block></fo:table-cell>
					         	</fo:table-row>							
							</fo:table-body>
						</fo:table>
                </fo:block>
                </xsl:if>
    
    
    
    
          <fo:block  start-indent="12pt">
            <fo:table>
              <fo:table-column  /> 
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      If you practice in any area(s) above with a numerical notation(s), complete the associated Supplement as follows :

                    </fo:block>
                  </fo:table-cell>


                </fo:table-row>
              </fo:table-body>
            </fo:table>

          </fo:block>
          <fo:block   border="0.2pt solid black"  start-indent="16pt" background-color="LightGray">
            <fo:table margin-top="1mm">
              <fo:table-column column-width="2in" />
              <fo:table-column column-width="2in"  />
              <fo:table-column column-width="1.5in"  />
              <fo:table-column  column-width="2.1in" />
              <fo:table-body  >
                <fo:table-row>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Bankruptcy(1)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Family Law(4)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Plaintiff Litigation(7)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Trusts, Wills, Estate, Probate(10)
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Collections / Repossession(2)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Financial Institution(5)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Real Estate(8)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
						Corporate / Commercial / Business(11)
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell  padding-top="5pt"  padding-bottom="5pt">
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Copyright / Trademark(3)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt"  padding-bottom="5pt">
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Government(6)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt"   padding-bottom="5pt">
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       Tax(9)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="5pt" padding-bottom="5pt">
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">

                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
       
          <fo:block  break-after="page"/>
          <fo:block>
            <fo:table margin-top="3mm" border="0.5pt solid black" background-color="LightGray ">
              <fo:table-column></fo:table-column>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block  font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      &#160;&#160;
                      ABOUT YOUR FIRM
                    </fo:block>
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

          <fo:block >
            <fo:table>
              <fo:table-column column-width="5.9in" />
              <fo:table-column column-width="1.3in" />
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      7.&#160;&#160;What year was your firm established?

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black" text-align="center">
							<xsl:value-of select="response/firm_freeform_01/data/YearOfFirmEstablished"/>

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block >
            <fo:table>
              <fo:table-column  />

              <fo:table-body>
			 
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      8.&#160;&#160;What was your estimated revenue for last year (most current completed year)?

                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block   border="0.2pt solid black"  start-indent="16pt" background-color="LightGray">
            <fo:table margin-top="1mm">
              <fo:table-column column-width="2.5in"/>
              <fo:table-column column-width="2.5in" />
              <fo:table-column  column-width="2.5in" />

              <fo:table-body  >
                <fo:table-row>
                  <fo:table-cell  padding-top="2pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      
                        <fo:block>
                          
                        <xsl:choose>
                       <xsl:when test="response/firm_freeform_01/data/AnnualRevenueSequence=1">
                        <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#x2714;</fo:inline>&#160;  $0-$100,000
                        </fo:block>
                       </xsl:when>
                       <xsl:otherwise>
                       <fo:block>
                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; $0-$100,000
                        </fo:block>
                       </xsl:otherwise>
                       </xsl:choose>
                            
                           
						</fo:block>
                      
                    </fo:block>
                  </fo:table-cell>
                                    <fo:table-cell  padding-top="2pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      
                        <fo:block>
                        
	                        <xsl:choose>
	                       <xsl:when test="response/firm_freeform_01/data/AnnualRevenueSequence=2">
	                        <fo:block>
	                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
	                            border="1pt black solid">&#x2714;</fo:inline> &#160;  $100,000-$250,000
	                        </fo:block>
	                       </xsl:when>
	                       <xsl:otherwise>
	                       <fo:block>
	                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
	                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; $100,000-$250,000
	                        </fo:block>
	                       </xsl:otherwise>
	                       </xsl:choose>
                        
						</fo:block>
						
						
						
						
						
                      
                    </fo:block>
                  </fo:table-cell>
				                    <fo:table-cell  padding-top="2pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      
                        <fo:block>
                        
                        <xsl:choose>
	                       <xsl:when test="response/firm_freeform_01/data/AnnualRevenueSequence=3">
	                        <fo:block>
	                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#x2714;</fo:inline> &#160; $250,000-$500,000
	                        </fo:block>
	                       </xsl:when>
	                       <xsl:otherwise>
	                       <fo:block>
	                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
	                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; $250,000-$500,000
	                        </fo:block>
	                       </xsl:otherwise>
	                       </xsl:choose>
                        
                        
                        
                        
                        
                        
                          
						</fo:block>
                      
                    </fo:block>
                  </fo:table-cell>


                </fo:table-row>
				<fo:table-row>
                  <fo:table-cell  padding-top="2pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      
                        <fo:block>
                        
                          <xsl:choose>
	                       <xsl:when test="response/firm_freeform_01/data/AnnualRevenueSequence=4">
	                        <fo:block>
	                         <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#x2714;</fo:inline> &#160; $500,000-$750,000
	                        </fo:block>
	                       </xsl:when>
	                       <xsl:otherwise>
	                       <fo:block>
	                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
	                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; $500,000-$750,000
	                        </fo:block>
	                       </xsl:otherwise>
	                       </xsl:choose>
                        
                        
                          
						</fo:block>
                      
                    </fo:block>
                  </fo:table-cell>
                                    <fo:table-cell  padding-top="2pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      
                        <fo:block>
                        
                         <xsl:choose>
	                       <xsl:when test="response/firm_freeform_01/data/AnnualRevenueSequence=5">
	                        <fo:block>
	                         <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#x2714;</fo:inline> &#160;   $750,000-$1,000,000
	                        </fo:block>
	                       </xsl:when>
	                       <xsl:otherwise>
	                       <fo:block>
	                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
	                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; $750,000-$1,000,000
	                        </fo:block>
	                       </xsl:otherwise>
	                       </xsl:choose>
                        
                          
						</fo:block>
                      
                    </fo:block>
                  </fo:table-cell>
				                    <fo:table-cell  padding-top="2pt" >
                    <fo:block  font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      
                        <fo:block>
                        
                        
                          <xsl:choose>
	                       <xsl:when test="response/firm_freeform_01/data/AnnualRevenueSequence=6">
	                        <fo:block>
	                         <fo:inline font-family="ZapfDingbats" font-size="8pt"
                            border="1pt black solid">&#x2714;</fo:inline> &#160;  $1,000,000+
	                        </fo:block>
	                       </xsl:when>
	                       <xsl:otherwise>
	                       <fo:block>
	                          <fo:inline font-family="ZapfDingbats" font-size="8pt"
	                            border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; $1,000,000+
	                        </fo:block>
	                       </xsl:otherwise>
	                       </xsl:choose>
                          
						</fo:block>
                      
                    </fo:block>
                  </fo:table-cell>


                </fo:table-row>
				
                
              </fo:table-body>
            </fo:table>
          </fo:block>
          
          <!--<Created by chandra>-->
          
          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">

              <fo:list-item margin-top="10px">
                <fo:list-item-label>
                  <fo:block margin-bottom="5px"  padding-top="-1.5px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">9.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" >
                              What is the number of non attorney support staff (full and part time combined)?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right"  >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" space-after="4mm" border-bottom="0.2pt solid black">
                             <xsl:value-of select="response/firm_freeform_01/data/TotalNumOfNonAttorneyStaff"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>



              <fo:list-item margin-top="10px">
                <fo:list-item-label>
                  <fo:block margin-bottom="5px" padding-top="-1.5px" >
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">10.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" >
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" >
                              Are there any other entities under which you provide professional services?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" >
                            <xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsApplicantProvidesLegalServices = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsApplicantProvidesLegalServices = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                              
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <xsl:if test="response/firm_freeform_01/data/IsApplicantProvidesLegalServices = 'Y'">
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="7mm" padding-bottom="10px">
                              If "Yes", please list the name of the entity and the services provided:
                            </fo:block>
                          </fo:table-cell>
                          
                        </fo:table-row>
						<fo:table-row>
                          
                          <fo:table-cell text-align="right" border-bottom="0.2pt solid black" >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="left">
                             <xsl:value-of select="response/firm_freeform_01/data/ApplicantProvidesLegalServicesDesc"></xsl:value-of>  
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        </xsl:if>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-bottom="5px" margin-top="11px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">11.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="3mm" margin-top="12px">
                              If you are a solo practitioner, do you have a backup attorney in case you are unable or unavailable to work for periods of time?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                       
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="3mm" margin-top="10px">
                            
                               <xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveBackupAttorney = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveBackupAttorney = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            
                            
                            
                            
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>


              <fo:list-item margin-top="10px">
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px" >
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">12.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="3mm" >
                              Have you acquired or merged with another firm in the past 10 years?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="3mm">

   							<xsl:choose>
   							
                            <xsl:when test="response/firm_freeform_01/data/IsFirmMergedWithOtherFirm = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmMergedWithOtherFirm = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                             <xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance ='N'">
                             N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> 
                            </xsl:when>
                            <xsl:otherwise>
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>



                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <xsl:if test="response/firm_freeform_01/data/IsFirmMergedWithOtherFirm = 'Y'">
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                              a. &#160; &#160; If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline>, were you the majority successor in interest to the financial assets and liabilities of the
                            </fo:block>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" start-indent="14mm">
                              acquired or merged firm?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
   							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsApplIntToFinanAssests = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsApplIntToFinanAssests = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                            
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              b. &#160; &#160; If<fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline>, do you desire coverage for this entity as a predecessor firm?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
   							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmCoverageForPreceedorFirms = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmCoverageForPreceedorFirms = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                                                        </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block start-indent="0.4in" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              If <fo:inline font-weight="bold" font-style="bold"> "Yes" </fo:inline> to a or b above, complete the <fo:inline font-weight="bold" font-style="italic"> Predecessor Firm Supplement. </fo:inline>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                              
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        </xsl:if>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="15px" >
                    <fo:table border="0.2pt solid black">
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                            <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                              PRACTICE MANAGEMENT
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px" >
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">13.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-bottom="10px" >
                              Do you maintain a central docket control or diary system?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="10px" >
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveIndepDockets = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveIndepDockets = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px" >
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">14.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-bottom="10px" >
                              Does the firm have procedures for identifying and resolving potential or actual conflicts of interest including cross-checking of former, existing or potential clients?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="10px" >
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveProcForFormersClients = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveProcForFormersClients = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                            
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">15.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-bottom="10px">
                              Do you use engagement letters including fee agreements on all new matters undertaken by the firm?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmRequireEngagementLetter = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmRequireEngagementLetter = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                           
                             </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">16.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-bottom="10px">
                              Are declinations or non-engagement letters, which include time sensitive dates, issued when declining or ceasing representation?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsNonEngagementLetterIssueToFirm = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsNonEngagementLetterIssueToFirm = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                           
                             </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">17.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-bottom="10px">
                              Have you moved to withdraw or been disengaged at the request of a client during the past two years?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/withdrawOrDisengaged = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/withdrawOrDisengaged = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                              </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">18.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" >
                              In the past three years have you initiated lawsuits or arbitration procedures to enforce the collection of unpaid fees of any client?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm" >
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsApplInitiatedLawsuitForFirm = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsApplInitiatedLawsuitForFirm = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid" start-indent="0.2in">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" margin-bottom="10px" start-indent="0.25in">
                    If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline> please complete the <fo:inline font-weight="bold" font-style="italic"> Fee Suit Supplement. </fo:inline>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block padding-top="-1.5px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">19.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                              Do you provide investment advice, make discretionary investments or have discretionary control of client funds?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm" margin-bottom="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmProvideInvestmentAdvice = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmProvideInvestmentAdvice = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
				   <xsl:if test="response/firm_freeform_01/data/IsFirmProvideInvestmentAdvice = 'Y'"> 
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" margin-bottom="10px" start-indent="0.25in" >
                    If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline> please explain.
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="7.0in"/>
                      <fo:table-body>
                        <fo:table-row>                          
                          <fo:table-cell border-bottom="0.9pt solid black" margin-bottom="10px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              <xsl:value-of select="response/firm_freeform_01/data/FirmInvestmentAdviceDesc"/>  
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        
                      </fo:table-body>
                    </fo:table>
                  </fo:block>  
			</xsl:if>			  
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="10px" >
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">20.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.0pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="12px">
                              At any time during the past three years has any client of the firm represented more than 50% of the firm’s annual revenue?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm" margin-top="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientMoreThan25PercentOfBilling = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientMoreThan25PercentOfBilling = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>   
                  <xsl:if test="response/firm_freeform_01/data/IsFirmHaveClientMoreThan25PercentOfBilling = 'Y'">               
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="0.1in"/>
                      <fo:table-column column-width="0.4in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="0.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              Client Name:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
								<xsl:value-of select="response/firm_freeform_01/data/ClientNameFirstLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              Services Provided:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
								<xsl:value-of select="response/firm_freeform_01/data/ServicesRenderedFirstLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
						<xsl:value-of select="response/firm_freeform_01/data/PercentFromFirstLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              % &#160; &#160; Year:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                             <xsl:value-of select="response/firm_freeform_01/data/DateRenderedFirstLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              Client Name:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
							<xsl:value-of select="response/firm_freeform_01/data/ClientNameSecondLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              Services Provided:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
							<xsl:value-of select="response/firm_freeform_01/data/ServicesRenderedSecondLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
							
								<xsl:value-of select="response/firm_freeform_01/data/PercentFromSecondLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              % &#160; &#160; Year:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell display-align="after" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
								<xsl:value-of select="response/firm_freeform_01/data/DateRenderedSecondLargestRevenueClient"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  </xsl:if>

                </fo:list-item-body>
              </fo:list-item>
             <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="12px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">21.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="1mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="1mm" margin-top="13px">
                              Do you have public figures as clients? (e.g. Entertainment, Politics or Sports)
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="1mm" margin-top="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientInEntertainmentInd = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveClientInEntertainmentInd = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  
                  <xsl:if test="response/firm_freeform_01/data/IsFirmHaveClientInEntertainmentInd = 'Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="5.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                              If <fo:inline font-weight="bold" font-style="italic">"Yes"</fo:inline>:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="justify" space-after="4mm">
                              a. &#160; Are you acting as an agent for such clients?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/actAgentForPublicFigures = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/actAgentForPublicFigures = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="justify">
                              b. &#160; Are you providing business management or negotiation of performance or 
                            </fo:block>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" start-indent="9mm">
                              professional contracts for such clients?
                            </fo:block>

                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/provideProfessionalContract = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/provideProfessionalContract = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                              c. &#160; Are you providing money management or investment advice services to such 
                            </fo:block>
                           
                           <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="9mm">
                            clients?</fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/provideMoneyManagementServices = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/provideMoneyManagementServices = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                    
                  </fo:block>
                  
                  </xsl:if>
					<fo:block page-break-after="always"/>  <!-- page break added by sukhi-->
                </fo:list-item-body>
              </fo:list-item>
              
              
              
              
              <fo:list-item>
                <fo:list-item-label>
                 
                  <fo:block margin-top="8px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">22.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="1mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="1mm" margin-top="10px">
                              At any time in the past five years, have you provided legal services related to publically traded securities transactions or securities that are not exempt from registration, investment vehicles or ventures, hedge or other investment funds?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="1mm" margin-top="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalService = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalService = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <xsl:if test="response/firm_freeform_01/data/IsFirmProvidedLegalService = 'Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="3mm" start-indent="0.25in">
                    If <fo:inline font-weight="bold" font-style="italic">"Yes"</fo:inline>, please identify and explain the relevant legal services.
                  </fo:block>
                  <fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                  <xsl:value-of select="response/firm_freeform_01/data/IsFirmProvidedLegalServiceDesc"></xsl:value-of>
                   
                    
                  </fo:block>
                 
                  </xsl:if>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="9px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">23.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="10px">
                              In the past five years have you served as general counsel, CEO, chairman, president, officer, director or member of an internal committee for a publically traded company or any financial institution?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/isServedAsCEOChairmanPresident = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/isServedAsCEOChairmanPresident = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="11px" >
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">24.</fo:inline>
                  </fo:block>
                  
                </fo:list-item-label>
                  
          
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="13px" margin-bottom="10px">
                              Is your office or suite shared with attorneys who are not members of your firm?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px" margin-bottom="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsAppOfficeSharedWithAttorney = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsAppOfficeSharedWithAttorney = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                 <!--  <fo:block page-break-before="always"/> -->
                 
                <!--   <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="15px" >
                    <fo:table border="0.2pt solid black">
                    
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                            <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                              UNDERWRITING INFORMATION
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block> -->

                </fo:list-item-body>
              </fo:list-item>
              </fo:list-block>
              </fo:block>
              <fo:block page-break-before="always"/>
                 
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="15px" >
                    <fo:table border="0.2pt solid black">
                    
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                            <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                              UNDERWRITING INFORMATION
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
              <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block  padding-top="-1.5px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">25.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              Does your current policy exclude coverage for any attorney, predecessor firms, firm affiliates, clients, specific engagements or other circumstances?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/isPolicyExcludeCoverage = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/isPolicyExcludeCoverage = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'N'">
                           N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> 
                            </xsl:when>
                            <xsl:otherwise>
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
				   <xsl:if test="response/firm_freeform_01/data/isPolicyExcludeCoverage = 'Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table >
                      <fo:table-column />
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-bottom="10px">
                              If <fo:inline font-style="italic" font-weight="bold"> "Yes" </fo:inline>, please describe:
                            </fo:block>
                          </fo:table-cell>
                          
                        </fo:table-row>
						<fo:table-row>
                          <fo:table-cell border-bottom="0.9pt solid black" margin-bottom="10px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                              <xsl:value-of select="response/firm_freeform_01/data/isPolicyExcludeCoverageDescription"></xsl:value-of>
                            </fo:block>
							 </fo:table-cell>
						  </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
				   </xsl:if>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="8px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">26.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="10px">
                              Does your current policy include lateral hire coverage for any of the firm’s current attorneys?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsPolicyIncludeLateralHireCov = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsPolicyIncludeLateralHireCov = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'N'">
                           N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> 
                            </xsl:when>
                            <xsl:otherwise>
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
							                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="8px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">27.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="10px">
                              Do you have care, custody or control over any funds or accounts of any of your clients, including but not limited to escrow or trust accounts?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/custodyOrControlFunds = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/custodyOrControlFunds = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'N'">
                            N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> 
                            </xsl:when>
                            
                            
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
				  
		<xsl:if test="response/firm_freeform_01/data/custodyOrControlFunds = 'Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="0.7in"/>
                      <fo:table-column column-width="5.8in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                               If <fo:inline font-weight="bold" font-style="italic">"Yes"</fo:inline>:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="justify">
                              a. &#160; &#160; In the past year, approximately how many disbursement transactions have you executed 
                            </fo:block>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="justify" space-after="4mm"  start-indent="10mm">
                              from your client funds account?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" margin-bottom="30px" space-after="14mm" >
                           <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                             <xsl:value-of select="response/firm_freeform_01/data/disbursementTransaction"></xsl:value-of>
                              
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="justify">
                              b. &#160; &#160; How many people at your firm have the authority to disburse funds out of your client
                            </fo:block>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" start-indent="10mm">
                              accounts, or to change payment instructions, payment accounts or addresses?
                            </fo:block>
								 
                          </fo:table-cell>
                          <fo:table-cell text-align="right" >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black" 	>
<xsl:value-of select="response/firm_freeform_01/data/peopleToDisburseFunds"></xsl:value-of>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                              c. &#160; &#160; Do you authenticate all changes to payment instructions, account numbers and addresses
                            </fo:block>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" start-indent="10mm">
                              via outbound phone call to the documented receiving party?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" >
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/instructionsDocumentedReceivingParty = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/instructionsDocumentedReceivingParty = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                              d. &#160; &#160; Do you have documented protocols and authority level sign-off for all changes to payment
                            </fo:block>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" start-indent="10mm">
                              instructions, account numbers and addresses of clients, vendors and third parties?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" >
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/doucumentedProtocolsAndAuthority = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/doucumentedProtocolsAndAuthority = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
				   </xsl:if>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="11px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">28.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="13px">
                              Do you have a fraud security awareness program that all employees are briefed on?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px">

							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/securityAwareness = 'Y'">
                            Yes<fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/securityAwareness = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                          
                           
                          <xsl:otherwise>
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="8px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">29.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px">
                    If your firm has three or more attorneys, please provide your professional liability insurance history for the past three years:
                  </fo:block>
                   <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
                              
                              
                              <xsl:choose>
                            
                            <xsl:when test="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance = 'N'">
                         &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
                          &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
                           &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
                           &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
                           &#160; &#160; &#160; &#160; &#160; &#160; &#160;  &#160; &#160; &#160; &#160; &#160; &#160;
                           
                               N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                              <fo:inline >&#160;</fo:inline> 
                            </xsl:otherwise>
                            </xsl:choose>
                              
                              
                               <!-- <xsl:value-of select="response/firm_freeform_01/data/IsFirmHaveLawyersLiabilityInsurance"></xsl:value-of> -->
                            </fo:block>
                  
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column column-width="2.5in"/>
                      <fo:table-column column-width="1.2in"/>
                      <fo:table-column column-width="1.3in"/>
                      <fo:table-column column-width="1.2in"/>
                      <fo:table-column column-width="1.3in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <!-- <fo:table-cell border="0.9pt solid black">
                            <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm" >
                              Insurance Carrier
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm" >
                              Policy Period
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm" start-indent="1.5cm">
                              Insurance Carrier
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm" start-indent="5mm">
                               Policy Period
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm">
                              Limit / Deductible
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm">
                              Number of Attorneys
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm">
                              Premium
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
                               <xsl:value-of select="response/AttorneysInsurance_list_01/data/insuranceCarrierFirstAttoryneyInsurance"></xsl:value-of>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
                              <xsl:value-of select="response/AttorneysInsurance_list_01/data/policyPeriodFirstAttoryneyInsurance"></xsl:value-of>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
								<xsl:value-of select="response/AttorneysInsurance_list_01/data/limitDeductibleFirstAttoryneyInsurance"></xsl:value-of>                 
          					 </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
							<xsl:value-of select="response/AttorneysInsurance_list_01/data/numberOfAttorneysFirstAttoryneyInsurance"></xsl:value-of>    
							                        </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                              &#160;
						<xsl:value-of select="response/AttorneysInsurance_list_01/data/premiumFirstAttoryneyInsurance"></xsl:value-of>        
					                    </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <!-- <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
								<xsl:value-of select="response/AttorneysInsurance_list_01/data/insuranceCarrierSecondAttorneyInsurance"></xsl:value-of>    
							 </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
								<xsl:value-of select="response/AttorneysInsurance_list_01/data/policyPeriodSecondAttorneyInsurance"></xsl:value-of>    
								  </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
									<xsl:value-of select="response/AttorneysInsurance_list_01/data/limitDeductibleSecondAttorneyInsurance"></xsl:value-of>        
						 </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                              &#160;
								<xsl:value-of select="response/AttorneysInsurance_list_01/data/numberOfAttorneysSecondAttorneyInsurance"></xsl:value-of>  
								     </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                              &#160;
								<xsl:value-of select="response/AttorneysInsurance_list_01/data/premiumSecondAttorneyInsurance"></xsl:value-of>         
								  </fo:block>
                          </fo:table-cell>
                        </fo:table-row> -->

                      </fo:table-body>
                    </fo:table>
                  </fo:block>
              <!--     <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="30px" margin-bottom="10px">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                            <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px" >
                              PAST CLAIMS
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block> -->

                </fo:list-item-body>
              </fo:list-item>
               </fo:list-block>
              </fo:block>
              <fo:block page-break-before="always"/>
                 
                <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="30px" margin-bottom="10px">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                            <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px" >
                              PAST CLAIMS
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block> 
              <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="8px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">30.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="10px">
                    After inquiry of all attorneys and staff of the firm, within the past five years have any past or present personnel:
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>                        
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" >
                             a. &#160; &#160; been the subject of any regulatory investigation or inquiry; suspended or disbarred from 
                            </fo:block>
                            <fo:block start-indent="1.5cm" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">practice; or charged, indicted or been convicted of any criminal charge?</fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >

							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsPersonnelBeSubOfAnyInvest = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsPersonnelBeSubOfAnyInvest = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <xsl:if test="response/firm_freeform_01/data/IsPersonnelBeSubOfAnyInvest = 'Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="3.in"/>
                      <fo:table-column column-width="4.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-bottom="7px">
                              If <fo:inline font-style="italic" font-weight="bold"> "Yes"</fo:inline>, please provide details and dates:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border-bottom="0.2pt solid white">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                              
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>                         
                          <fo:table-cell number-columns-spanned="2" text-align="left" border-bottom="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                             <xsl:value-of select="response/firm_freeform_01/data/PersonnelBeSubOfAnyInvestDate"></xsl:value-of> &#160; - &#160;
                             <xsl:value-of select="response/firm_freeform_01/data/PersonnelBeSubOfAnyInvestDetails"></xsl:value-of>
                                                           
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  </xsl:if>

                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              b. &#160; &#160; know of any professional liability claims made against the firm, its affiliates or its personnel?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsLawyerProfLiabClaimAgntAppl = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsLawyerProfLiabClaimAgntAppl = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="10px">
                              c. &#160; &#160; become aware of any act, error or omission or fee dispute which might become the basis 
                            </fo:block>
                            <fo:block start-indent="1.4cm" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="10px">of a claim  against  the firm or its personnel?</fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px">

							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsAnyActOmmBecomeClaimAgntFirm = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsAnyActOmmBecomeClaimAgntFirm = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" space-after="4mm">
                    NOTE: THE POLICY FOR WHICH THIS APPLICATION IS BEING MADE SHALL NOT APPLY TO ANY INCIDENTS OR CLAIMS DETAILED OR WHICH SHOULD HAVE BEEN DETAILED IN QUESTION 30 a, b or c ABOVE.
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm">
                    If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline> to 30 b or c above, complete the <fo:inline font-weight="bold"> CLAIM SUPPLEMENT </fo:inline> for each claim or potential claim.
                  </fo:block>
                  
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="8px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">31.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">                  
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                          
								<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="10px">
	                              Within the past five years has the firm or its attorneys been declined, canceled, or non-renewed for professional liability insurance for any reason (Not applicable to Missouri firms)
	                            </fo:block>
                           
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="10px">


 
 							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsAttorneyDeclineForProfLiability = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsAttorneyDeclineForProfLiability = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <xsl:if test="response/firm_freeform_01/data/IsAttorneyDeclineForProfLiability = 'Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="7.7in"/>
                      
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	space-after="4mm" margin-bottom="7px">
                              If <fo:inline font-style="italic" font-weight="bold">"Yes"</fo:inline>, please provide dates and reasons:
                            </fo:block>
                          </fo:table-cell>
                          
                        </fo:table-row>  
						<fo:table-row>
                          
                          <fo:table-cell text-align="left" >
                           <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black" >
                             <xsl:value-of select="response/firm_freeform_01/data/AttorneyDeclineForProfLiabilityDates"></xsl:value-of>&#160; - &#160;
                              <xsl:value-of select="response/firm_freeform_01/data/AttorneyDeclineForProfLiabilityReasons"></xsl:value-of>


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>   						
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  </xsl:if>

                 <!--  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="30px" margin-bottom="20px">

                    <fo:table border="0.2pt solid black">
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                            <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                              CONTACT INFORMATION
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block> -->

                  
                </fo:list-item-body>
              </fo:list-item>
              
              
              </fo:list-block>
              </fo:block>
              <fo:block page-break-before="always"/>
                 
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="30px" margin-bottom="20px">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                            <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                              CONTACT INFORMATION
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
              <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              

              <fo:list-item>
                <fo:list-item-label>
                  <fo:block margin-top="8px">
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">32.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="1.5in" />
		              <fo:table-column column-width="2.0in"/>
		              <fo:table-column column-width="1.8in" />
		              <fo:table-column column-width="1.7in"/>
                      <fo:table-body>
                        <fo:table-row>
                  <fo:table-cell display-align="after" padding-top="4mm">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Primary Contact Name: 
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.9pt solid black"  display-align="after">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                     <xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Secondary Contact Name:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.9pt solid black" display-align="after">
                    <fo:block text-align="left" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                     <xsl:value-of select="response/firm_freeform_01/data/secondayContactPerson"></xsl:value-of> &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                        <fo:table-row>
                  <fo:table-cell display-align="after" padding-top="4mm">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Phone Number:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.9pt solid black" display-align="after">
                    <fo:block text-align="left" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                     <xsl:value-of select="response/policy_freeform_01/data/WorkPhone"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/WorkExt"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Secondary Phone Number:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.9pt solid black" display-align="after">
                    <fo:block text-align="left" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       <xsl:value-of select="response/policy_freeform_01/data/otherPhone"></xsl:value-of>&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                        <fo:table-row>
                  <fo:table-cell display-align="after" padding-top="4mm">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Email:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                     <xsl:value-of select="response/policy_freeform_01/data/AccountEmail"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160; Secondary Email:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                     <xsl:value-of select="response/policy_freeform_01/data/secondaryEmail"></xsl:value-of> &#160;
                    </fo:block>
                  </fo:table-cell>
                
                </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                   <fo:block space-after="4mm">
            <fo:table border="0.9pt solid white">
              <fo:table-column column-width="1.5in" />
              <fo:table-column column-width="4in"/>             
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Website Address:&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                     <xsl:value-of select="response/policy_freeform_01/data/Website"></xsl:value-of> 
                    </fo:block>
                  </fo:table-cell>                 
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              
            </fo:list-block>
          </fo:block>
		   <fo:block padding-top="20px"></fo:block>
		   
			
         <!--  <xsl:when test="response/policy_freeform_01/data/StateCode= 'CO'">
        
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">NOTICE TO COLORADO APPLICANTS: </fo:inline> IT IS UNLAWFUL TO KNOWINGLY PROVIDE FALSE, INCOMPLETE, OR MISLEADING FACTS OR INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE COMPANY.  PENALTIES MAY INCLUDE IMPRISONMENT, FINES, DENIAL OF INSURANCE, AND CIVIL DAMAGES.  ANY INSURANCE COMPANY OR AGENT OF AN INSURANCE COMPANY WHO KNOWINGLY PROVIDES FALSE, INCOMPLETE, OR MISLEADING FACTS OR INFORMATION TO A POLICYHOLDER OR CLAIMANT FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE POLICYHOLDER OR CLAIMANT WITH REGARD TO A SETTLEMENT OR AWARD PAYABLE FROM INSURANCE PROCEEDS SHALL BE REPORTED TO THE COLORADO DIVISION OF INSURANCE WITHIN THE DEPARTMENT OF REGULATORY AGENCIES.
          </fo:block>
            </xsl:when> -->
          
        <!--   <xsl:when test="response/policy_freeform_01/data/StateCode= 'KS'">
        
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">KANSAS FRAUD WARNING: </fo:inline>ANY PERSON WHO, KNOWINGLY AND WITH INTENT TO DEFRAUD, PRESENTS, CAUSES TO BE PRESENTED OR PREPARES WITH KNOWLEDGE OR BELIEF THAT IT WILL BE PRESENTED TO OR BY AN INSURER, PURPORTED INSURER, BROKER OR ANY AGENT THEREOF, ANY WRITTEN STATEMENT AS PART OF, OR IN SUPPORT OF, AN APPLICATION FOR THE ISSUANCE OF, OR THE RATING OF AN INSURANCE POLICY FOR PERSONAL OR COMMERCIAL INSURANCE WHICH SUCH PERSON KNOWS TO CONTAIN MATERIALLY FALSE INFORMATION CONCERNING ANY FACT MATERIAL THERETO; OR CONCEALS, FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRADULENT INSURANCE ACT.
          </fo:block>
       </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'KY'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">KENTUCKY FRAUD WARNING: </fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS, FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT ACT, WHICH IS A CRIME.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'ME'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">MAINE FRAUD WARNING: </fo:inline>IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES MAY INCLUDE IMPRISONMENT, FINES OR A DENIAL OF INSURANCE BENEFITS.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'MD'">
          
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">MARYLAND FRAUD WARNING: </fo:inline> ANY PERSON WHO KNOWINGLY OR WILLFULLY PRESENTS A FALSE OR FRAUDULENT CLAIM FOR PAYMENT OF A LOSS OR BENEFIT OR WHO KNOWINGLY OR WILLFULLY PRESENTS FALSE INFORMATION IN AN APPLICATON FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT IN PRISON.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'NJ'">
         
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">NEW JERSEY FRAUD WARNING:  </fo:inline>ANY PERSON WHO INCLUDES ANY FALSE OR MISLEADING INFORMATION ON AN APPLICATION FOR AN INSURANCE POLICY IS SUBJECT TO CRIMINAL AND CIVIL PENALTIES.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'NY'">
         
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">NEW YORK FRAUD WARNING: </fo:inline> ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SHALL ALSO BE SUBJECT TO A CIVIL PENALTY NOT TO EXCEED FIVE THOUSAND DOLLARS AND THE STATED VALUE OF THE CLAIM FOR EACH SUCH VIOLATION.
          </fo:block>
           </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'OH'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">OHIO FRAUD WARNING:  </fo:inline>ANY PERSON WHO, WITH INTENT TO DEFRAUD OR KNOWING THAT HE IS FACILITATING A FRAUD AGAINST AN INSURER, SUBMITS AN APPLICATION OR FILES A CLAIM CONTAINING A FALSE OR DECEPTIVE STATEMENT IS GUILTY OF INSURANCE FRAUD.
          </fo:block>
           </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">OKLAHOMA FRAUD WARNING:</fo:inline> ANY PERSON WHO KNOWINGLY, AND WITH INTENT TO INJURE, DEFRAUD OR DECEIVE ANY INSURER, MAKES ANY CLAIM FOR THE PROCEEDS OF AN INSURANCE POLICY CONTAINING ANY FALSE, INCOMPLETE OR MISLEADING INFORMATION IS GUILTY OF A FELONY.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'OR'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">OREGON FRAUD WARNING: </fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD OR SOLICIT ANOTHER TO DEFRAUD THE INSURER BY SUBMITTING AN APPLICATION CONTAINING A FALSE STATEMENT AS TO ANY MATERIAL FACT MAY BE VIOLATING STATE LAW.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'PA'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">PENNSYLVANIA FRAUD WARNING: </fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON TO CRIMINAL AND CIVIL PENALTIES.
          </fo:block>
          </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'TN'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">TENNESSEE FRAUD WARNING: </fo:inline> IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES AND DENIAL OF INSURANCE BENEFITS.
          </fo:block>
           </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'VT'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">VERMONT FRAUD WARNING: </fo:inline>ANY PERSON WHO KNOWINGLY PRESENTS A FALSE STATEMENT IN AN APPLICATION FOR INSURANCE MAY BE GUILTY OF A CRIMINAL OFFENSE AND SUBJECT TO PENALTIES UNDER STATE LAW.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'VA'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">VIRGINIA FRAUD WARNING: </fo:inline> IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES, AND DENIAL OF INSURANCE BENEFITS.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'WA'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">WASHINGTON FRAUD WARNING:</fo:inline> IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE, OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES, AND DENIAL OF INSURANCE BENEFITS.
          </fo:block>
          </xsl:when> -->
       <!-- 
          <fo:block page-break-before="always"></fo:block>
					<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold"></fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON
				FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE
				INFORMATION OR CONCEALS, FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT
				MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON
				TO CRIMINAL AND CIVIL PENALTIES. (Not applicable in AL, AR, CO, DC, FL, KS, KY, LA, MD, ME, NJ, NM, NY, OH, OK, OR,
				RI, TN, VA, VT, WA or WV – see Additional Fraud Notices for these States below).
          </fo:block>
          
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" space-after="4mm" font-weight="bold">
            ADDITIONAL FRAUD NOTICES
            </fo:block>
          
         <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO ALABAMA, ARKANSAS, LOUISIANA, NEW MEXICO, RHODE ISLAND AND WEST VIRGINIA APPLICANTS:</fo:inline>
				Any person who knowingly presents a false or fraudulent claim for payment of a loss or benefit or knowingly presents false
				information in an application for insurance is guilty of a crime and may be subject to fines and confinement in prison.</fo:block>
				
				
				
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO COLORADO APPLICANTS:</fo:inline> It is unlawful to knowingly provide false, incomplete, or misleading facts or information
			to an insurance company for the purpose of defrauding or attempting to defraud the company. Penalties may include
			imprisonment, fines, denial of insurance, and civil damages. Any insurance company or agent of an insurance company who
			knowingly provides false, incomplete, or misleading facts or information to a policyholder or claimant for the purpose of defrauding
			or attempting to defraud the policyholder or claimant with regard to a settlement or award payable from insurance proceeds shall
			be reported to the Colorado Division of Insurance within the Department of Regulatory Agencies.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO DISTRICT OF COLUMBIA APPLICANTS: WARNING:</fo:inline> It is a crime to provide false or misleading information to an
			insurer for the purpose of defrauding the insurer or any other person. Penalties include imprisonment and/or fines. In addition, an
			insurer may deny insurance benefits if false information materially related to a claim was provided by the applicant.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO FLORIDA APPLICANTS:</fo:inline> Any person who knowingly and with intent to injure, defraud, or deceive any insurer files a
			statement of claim or an application containing any false, incomplete, or misleading information is guilty of a felony of the third
			degree.</fo:block>
			            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO KANSAS APPLICANTS:</fo:inline> Any person who, knowingly and with intent to defraud, presents, causes to be presented or
			prepares with knowledge or belief that it will be presented to or by an insurer, purported insurer, broker or any agent thereof, any
			written, electronic, electronic impulse, facsimile, magnetic, oral, or telephonic communication or statement as part of, or in support
			of, an application for the issuance of, or the rating of an insurance policy for personal or commercial insurance, or a claim for
			payment or other benefit pursuant to an insurance policy for commercial or personal insurance which such person knows to
			contain materially false information concerning any fact material thereto; or conceals, for the purpose of misleading, information
			concerning any fact material thereto commits a fraudulent insurance act.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO KANSAS APPLICANTS:</fo:inline> Any person who, knowingly and with intent to defraud, presents, causes to be presented or
			prepares with knowledge or belief that it will be presented to or by an insurer, purported insurer, broker or any agent thereof, any
			written, electronic, electronic impulse, facsimile, magnetic, oral, or telephonic communication or statement as part of, or in support
			of, an application for the issuance of, or the rating of an insurance policy for personal or commercial insurance, or a claim for
			payment or other benefit pursuant to an insurance policy for commercial or personal insurance which such person knows to
			contain materially false information concerning any fact material thereto; or conceals, for the purpose of misleading, information
			concerning any fact material thereto commits a fraudulent insurance act.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO KENTUCKY APPLICANTS:</fo:inline> Any person who knowingly and with intent to defraud any insurance company or other
			person files an application for insurance containing any materially false information or conceals, for the purpose of misleading,
			information concerning any fact material thereto commits a fraudulent insurance act, which is a crime.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO MAINE APPLICANTS:</fo:inline> It is a crime to knowingly provide false, incomplete or misleading information to an insurance
				company for the purpose of defrauding the company. Penalties may include imprisonment, fines or denial of insurance benefits.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO MARYLAND APPLICANTS:</fo:inline> Any person who knowingly or willfully presents a false or fraudulent claim for payment
				of a loss or benefit or who knowingly or willfully presents false information in an application for insurance is guilty of a crime and
				may be subject to fines and confinement in prison.
            </fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO NEW JERSEY APPLICANTS:</fo:inline> Any person who includes any false or misleading information on an application for an
			insurance policy is subject to criminal and civil penalties.</fo:block>
				
				
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO NEW YORK APPLICANTS:</fo:inline> Any person who knowingly and with intent to defraud any insurance company or other
				person files an application for insurance or statement of claim containing any materially false information, or conceals for the
				purpose of misleading, information concerning any fact material thereto, commits a fraudulent insurance act, which is a crime, and
				shall also be subject to a civil penalty not to exceed five thousand dollars and the stated value of the claim for each such violation.</fo:block>
            
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO OHIO APPLICANTS:</fo:inline> Any person who, with intent to defraud or knowing that he is facilitating a fraud against an
			insurer, submits an application or files a claim containing a false or deceptive statement is guilty of insurance fraud.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO OKLAHOMA APPLICANTS: WARNING:</fo:inline> Any person who knowingly, and with intent to injure, defraud or deceive
			any insurer, makes any claim for the proceeds of an insurance policy containing any false, incomplete or misleading information is
			guilty of a felony.
            </fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO OREGON APPLICANTS:</fo:inline> Any person who knowingly and with intent to defraud or solicit another to defraud the
			insurer by submitting an application containing a false statement as to any material fact may be violating state law</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO TENNESSEE, VIRGINIA AND WASHINGTON APPLICANTS:</fo:inline> It is a crime to knowingly provide false, incomplete, or
				misleading information to an insurance company for the purpose of defrauding the company. Penalties include imprisonment,
				fines, and denial of insurance benefits.</fo:block>
            
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO VERMONT APPLICANTS:</fo:inline> Any person who knowingly presents a false statement in an application for insurance may
			be guilty of a criminal offense and subject to penalties under state law.</fo:block>
            
          
          <fo:block break-after="page"/> -->
  			
            <fo:block page-break-before="always"></fo:block>
				
		<xsl:choose>
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'AL' or response/policy_freeform_01/data/StateCode= 'AR' or response/policy_freeform_01/data/StateCode= 'LA' or response/policy_freeform_01/data/StateCode= 'NM' or response/policy_freeform_01/data/StateCode= 'RI' or response/policy_freeform_01/data/StateCode= 'WV'">
          
			<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO ALABAMA, ARKANSAS, LOUISIANA, NEW MEXICO, RHODE ISLAND AND WEST VIRGINIA APPLICANTS:</fo:inline>
				Any person who knowingly presents a false or fraudulent claim for payment of a loss or benefit or knowingly presents false
				information in an application for insurance is guilty of a crime and may be subject to fines and confinement in prison.</fo:block>
			</xsl:when>	
				
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'CO'">	
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO COLORADO APPLICANTS:</fo:inline> It is unlawful to knowingly provide false, incomplete, or misleading facts or information
			to an insurance company for the purpose of defrauding or attempting to defraud the company. Penalties may include
			imprisonment, fines, denial of insurance, and civil damages. Any insurance company or agent of an insurance company who
			knowingly provides false, incomplete, or misleading facts or information to a policyholder or claimant for the purpose of defrauding
			or attempting to defraud the policyholder or claimant with regard to a settlement or award payable from insurance proceeds shall
			be reported to the Colorado Division of Insurance within the Department of Regulatory Agencies.</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'DC'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO DISTRICT OF COLUMBIA APPLICANTS: WARNING:</fo:inline> It is a crime to provide false or misleading information to an
			insurer for the purpose of defrauding the insurer or any other person. Penalties include imprisonment and/or fines. In addition, an
			insurer may deny insurance benefits if false information materially related to a claim was provided by the applicant.</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'FL'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO FLORIDA APPLICANTS:</fo:inline> Any person who knowingly and with intent to injure, defraud, or deceive any insurer files a
			statement of claim or an application containing any false, incomplete, or misleading information is guilty of a felony of the third
			degree.</fo:block>
			 </xsl:when>	           
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'KS'">			
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO KANSAS APPLICANTS:</fo:inline> Any person who, knowingly and with intent to defraud, presents, causes to be presented or
			prepares with knowledge or belief that it will be presented to or by an insurer, purported insurer, broker or any agent thereof, any
			written, electronic, electronic impulse, facsimile, magnetic, oral, or telephonic communication or statement as part of, or in support
			of, an application for the issuance of, or the rating of an insurance policy for personal or commercial insurance, or a claim for
			payment or other benefit pursuant to an insurance policy for commercial or personal insurance which such person knows to
			contain materially false information concerning any fact material thereto; or conceals, for the purpose of misleading, information
			concerning any fact material thereto commits a fraudulent insurance act.</fo:block>
            </xsl:when>	
			
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'KY'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO KENTUCKY APPLICANTS:</fo:inline> Any person who knowingly and with intent to defraud any insurance company or other
			person files an application for insurance containing any materially false information or conceals, for the purpose of misleading,
			information concerning any fact material thereto commits a fraudulent insurance act, which is a crime.</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'ME'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO MAINE APPLICANTS:</fo:inline> It is a crime to knowingly provide false, incomplete or misleading information to an insurance
				company for the purpose of defrauding the company. Penalties may include imprisonment, fines or denial of insurance benefits.</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'MD'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
				<fo:inline font-weight="bold">NOTICE TO MARYLAND APPLICANTS:</fo:inline> Any person who knowingly or willfully presents a false or fraudulent claim for payment
				of a loss or benefit or who knowingly or willfully presents false information in an application for insurance is guilty of a crime and
				may be subject to fines and confinement in prison.
				</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'NJ'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
				<fo:inline font-weight="bold">NOTICE TO NEW JERSEY APPLICANTS:</fo:inline> Any person who includes any false or misleading information on an application for an
				insurance policy is subject to criminal and civil penalties.</fo:block>
			</xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'NY'">	
				
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
				<fo:inline font-weight="bold">NOTICE TO NEW YORK APPLICANTS:</fo:inline> Any person who knowingly and with intent to defraud any insurance company or other
					person files an application for insurance or statement of claim containing any materially false information, or conceals for the
					purpose of misleading, information concerning any fact material thereto, commits a fraudulent insurance act, which is a crime, and
					shall also be subject to a civil penalty not to exceed five thousand dollars and the stated value of the claim for each such violation.</fo:block>
            </xsl:when>	
            <xsl:when test="response/policy_freeform_01/data/StateCode= 'OH'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO OHIO APPLICANTS:</fo:inline> Any person who, with intent to defraud or knowing that he is facilitating a fraud against an
			insurer, submits an application or files a claim containing a false or deceptive statement is guilty of insurance fraud.</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
				<fo:inline font-weight="bold">NOTICE TO OKLAHOMA APPLICANTS: WARNING:</fo:inline> Any person who knowingly, and with intent to injure, defraud or deceive
				any insurer, makes any claim for the proceeds of an insurance policy containing any false, incomplete or misleading information is
				guilty of a felony.
				</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'OR'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold">NOTICE TO OREGON APPLICANTS:</fo:inline> Any person who knowingly and with intent to defraud or solicit another to defraud the
			insurer by submitting an application containing a false statement as to any material fact may be violating state law</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'TN' or response/policy_freeform_01/data/StateCode= 'VA' or response/policy_freeform_01/data/StateCode= 'WA'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
				<fo:inline font-weight="bold">NOTICE TO TENNESSEE, VIRGINIA AND WASHINGTON APPLICANTS:</fo:inline> It is a crime to knowingly provide false, incomplete, or
				misleading information to an insurance company for the purpose of defrauding the company. Penalties include imprisonment,
				fines, and denial of insurance benefits.</fo:block>
            </xsl:when>	
			<xsl:when test="response/policy_freeform_01/data/StateCode= 'VT'">
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
				<fo:inline font-weight="bold">NOTICE TO VERMONT APPLICANTS:</fo:inline> Any person who knowingly presents a false statement in an application for insurance may
				be guilty of a criminal offense and subject to penalties under state law.</fo:block>
            	</xsl:when>	
			<xsl:otherwise>		
				<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
					<fo:inline font-weight="bold"></fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON
				FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE
				INFORMATION OR CONCEALS, FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT
				MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON
				TO CRIMINAL AND CIVIL PENALTIES. (Not applicable in AL, AR, CO, DC, FL, KS, KY, LA, MD, ME, NJ, NM, NY, OH, OK, OR,
				RI, TN, VA, VT, WA or WV).
				</fo:block>
          </xsl:otherwise>
	</xsl:choose>
          
         
		 
		  
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            Completion and/or signing of this application does not bind the firm to purchase, nor the Insurer to provide, any insurance policy;
			however, no policy can be issued unless the application is properly completed, signed and dated.
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            The signatory declares that (s)he is authorized by the firm to sign this application on behalf of all prospective Insureds and that to
			the best of his/her knowledge the statements herein are true. The signatory agrees that if the information supplied in this
			application and the materials submitted therewith should change between the date this application is signed and the effective date
			of the proposed insurance, the signatory shall immediately notify the Insurer of such and shall provide the Insurer with
			information that would complete, update or correct the application or materials submitted therewith. The Insurer may withdraw or
			modify any of the terms or conditions of coverage accordingly.
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="1cm">
            <fo:inline font-weight="bold">ALL WRITTEN STATEMENTS, SUPPLEMENTAL APPLICATION AND MATERIALS FURNISHED TO THE INSURER IN
			CONJUNCTION WITH THIS APPLICATION ARE INCORPORATED BY REFERENCE INTO THIS APPLICATION AND MADE
			A PART THEREOF, AND DEEMED ATTACHED HERETO.</fo:inline>
          </fo:block>
           
      	 <!-- <fo:block page-break-before="always"/>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="100px">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="1.8in"/>
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="1.7in"/>
              <fo:table-column column-width="2.0in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      SIGNATURE*
                    </fo:block>
                  </fo:table-cell>
                <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      PRINTED NAME*
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after" number-columns-spanned="4">
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="5px" margin-bottom="10px">
                      *MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE FIRM ON BEHALF OF ALL INSUREDS
                    </fo:block>
                  </fo:table-cell>           
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      TITLE OF SIGNATORY:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      DATE SIGNED:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      MM / DD / YYYY
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="2in"/>
              <fo:table-column column-width="2.4in"/>
              <fo:table-column column-width="0.2in"/>
              <fo:table-column column-width="2.4in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      FLORIDA FIRMS ONLY:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>                
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">                      
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Producer’s Name

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      Producer’s Florida License Number
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="3.0in"/>
              <fo:table-column column-width="4.0in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      IOWA FIRMS ONLY: &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; Producer’s Name:
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="2.3in"/>
              <fo:table-column column-width="2.2in"/>
              <fo:table-column column-width="0.2in"/>
              <fo:table-column column-width="2.2in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after" >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      NEW HAMPSHIRE FIRMS ONLY:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" border-bottom="0.2pt solid black">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Producer’s Name

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      Producer’s Signature
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block page-break-before="always"/>
          
           -->
           
            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="15px">
            <fo:table border="0.9pt solid white">
              <fo:table-column column-width="1.5in"/>
              <fo:table-column column-width="2.5in"/>
              <fo:table-column column-width="1.5in"/>
              <fo:table-column column-width="2.0in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      SIGNATURE*
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      PRINTED NAME*
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after" number-columns-spanned="4" padding-top="7px" padding-bottom="15px">
                    <fo:block font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                      *MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE FIRM ON BEHALF OF ALL INSUREDS
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      TITLE OF SIGNATORY:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      DATE SIGNED:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      MM / DD / YYYY
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>




          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="5mm">
            <fo:table border="0.9pt solid white">
              <fo:table-column column-width="2in"/>
              <fo:table-column column-width="2.4in"/>
              <fo:table-column column-width="0.2in"/>
              <fo:table-column column-width="2.4in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      FLORIDA FIRMS ONLY:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <xsl:choose>
			        	<xsl:when test="response/policy_freeform_01/data/StateCode='FL'">			        
				          	<fo:block text-align="center" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
		                      Kyle Nieman
		                    </fo:block>
            			</xsl:when>
            			<xsl:otherwise>
            				<fo:block>
		                      &#160;
		                    </fo:block>
            			</xsl:otherwise>          
          			</xsl:choose>                    
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <xsl:choose>
			        	<xsl:when test="response/policy_freeform_01/data/StateCode='FL'">			        
				          	<fo:block text-align="center" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
		                      P227965
		                    </fo:block>
            			</xsl:when>
            			<xsl:otherwise>
            				<fo:block>
		                      &#160;
		                    </fo:block>
            			</xsl:otherwise>          
          			</xsl:choose>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Producer's Name
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      Producer's Florida License Number
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

	<xsl:if test="response/policy_freeform_01/data/StateCode !='FL'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="7mm">
            <fo:table border="0.9pt solid white">
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="4.0in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      IOWA FIRMS ONLY:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                  	<xsl:choose>
			        	<xsl:when test="response/policy_freeform_01/data/StateCode='IA'">			        
				          	<fo:block text-align="center" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
		                      Kyle Nieman
		                    </fo:block>
            			</xsl:when>
            			<xsl:otherwise>
            				<fo:block text-align="center" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
		                    </fo:block>
            			</xsl:otherwise>          
          			</xsl:choose>                   
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                <fo:table-cell>
                	<fo:block>
                      &#160;
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                	<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Producer's Name
                    </fo:block>
                </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          </xsl:if>
          <xsl:if test="response/policy_freeform_01/data/StateCode='NH'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            <fo:table border="0.9pt solid white">
              <fo:table-column column-width="2.4in"/>
              <fo:table-column column-width="2.4in"/>
              <fo:table-column column-width="0.2in"/>
              <fo:table-column column-width="2.4in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      NEW HAMPSHIRE FIRMS ONLY:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <xsl:choose>
			        	<xsl:when test="response/policy_freeform_01/data/StateCode='NH'">			        
				          	<fo:block text-align="center" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
		                      Kyle Nieman
		                    </fo:block>
            			</xsl:when>
            			<xsl:otherwise>
            				<fo:block>
		                      &#160;
		                    </fo:block>
            			</xsl:otherwise>          
          			</xsl:choose>                     
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                  	<xsl:choose>
			        	<xsl:when test="response/policy_freeform_01/data/StateCode='NH'">			        
				          	<fo:block text-align="center">
								<fo:external-graphic  height=".60in"  content-width=".80in"  src="../LawyersIns/image/kyle.png" />
							</fo:block>
            			</xsl:when>
            			<xsl:otherwise>
            				<fo:block>
		                      &#160;
		                    </fo:block>
            			</xsl:otherwise>          
          			</xsl:choose>                    
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Producer's Name
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      Producer's Signature
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          </xsl:if>
          
        <!--  <fo:block break-after="page"/> -->
          <!-- <fo:block page-break-before="always"></fo:block> -->
          
		<xsl:if test="response/firm_freeform_01/data/IsFirmCoverageForPreceedorFirms = 'Y' or response/firm_freeform_01/data/IsApplIntToFinanAssests = 'Y'">
		<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px">
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      PREDECESSOR FIRM SUPPLEMENT <fo:inline font-style="italic">(as required in Question 12)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="1.1in"/>
              <fo:table-column column-width="0.7in"/>
              <fo:table-column column-width="0.7in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="0.9in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Firm Name
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Date of Acquisition or Merger
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Type of Legal Entity
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      # of Attys at Firm at Dissolution
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      # of Attys for Whom Coverage is Sought
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Insurer at Dissolution
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Was ERP Purchased
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      ERP Expiration Date
                    </fo:block>
                  </fo:table-cell>                  
                </fo:table-row>
				<xsl:for-each select="response/prodecessor_list_1/data">
                <fo:table-row>
                  <fo:table-cell display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="FirmName"/>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" display-align="after" >
                      <xsl:value-of select="DateOfAcquisation"/>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="TypeOfEntity"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="NumberOfAttorneyAtDiss"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="NumberOfAttorneyAtApplFirm"/>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="InsurerAtDissolution"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:choose>
                            <xsl:when test="IsERPPurchased = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="IsERPPurchased = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="ERPExpDate"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                </xsl:for-each>

                
                
              </fo:table-body>
            </fo:table>
          </fo:block>
</xsl:if>
<xsl:if test="response/AOP_PercentageValueCheck_3 > 35">
<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px">
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      BANKRUPTCY SUPPLEMENT  <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" space-after="4mm">
            NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR BANKRUPTCY IN THE AREA OF PRACTICE TABLE IS GREATER THAN 35%.
          </fo:block>

          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm" padding-top="2px">
                    Please indicate the percentage of bankruptcy cases which are:
                  </fo:block>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="2mm">
                      <fo:table border="0.2pt solid white">
                        <fo:table-column column-width="1.7in"/>
                        <fo:table-column column-width="0.8in"/>
                        <fo:table-column column-width="1.7in"/>
                        <fo:table-column column-width="0.8in"/>
                        <fo:table-column column-width="1.7in"/>
                        <fo:table-column column-width="0.8in"/>
                        <fo:table-body>
                          <fo:table-row>
                            <fo:table-cell display-align="after">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                                Personal Bankruptcies
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell  border-bottom="0.2pt solid black"  display-align="after">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                               <xsl:value-of select="response/Bankruptcy_list_01/data/personalBankrupties"></xsl:value-of> %
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell  display-align="after">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                                Commercial Bankruptcies
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                               <xsl:value-of select="response/Bankruptcy_list_01/data/commercialBankruptcies"></xsl:value-of> %
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell  display-align="after">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                                Total (must equal 100%)
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              100  %
                              </fo:block>
                            </fo:table-cell>
                          </fo:table-row>
                        </fo:table-body>
                      </fo:table>
                    </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              
               <xsl:if test="response/isAfterbankruptcySupply='N'">
              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="2px">
                    In the past three years how many bankruptcy cases has the firm handled each year in the following categories:
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column column-width="1.8in"/>
                      <fo:table-column column-width="1.8in"/>
                      <fo:table-column column-width="1.8in"/>
                      <fo:table-column column-width="1.8in"/>
                      
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border-bottom="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              Debtor
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              Creditor
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              Trustee
                            </fo:block>
                          </fo:table-cell>                          
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell  border="0.2pt solid black" display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              Last Completed Year:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_list_01/data/Debtor"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_list_01/data/Creditor"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/bankruptcyCasesDetails_list_01/data/Trustee"></xsl:value-of>&#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              One Year Prior:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                           <xsl:value-of select="response/bankruptcyCasesDetails_list_02/data/Debtor"></xsl:value-of>  &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_list_02/data/Creditor"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_list_02/data/Trustee"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              Two Years Prior:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                           <xsl:value-of select="response/bankruptcyCasesDetails_list_03/data/Debtor"></xsl:value-of>  &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                            <xsl:value-of select="response/bankruptcyCasesDetails_list_03/data/Creditor"></xsl:value-of>  &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_list_03/data/Trustee"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        
                        
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
               </xsl:if>
              <xsl:if test="response/isAfterbankruptcySupply='Y'">
              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block >
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="2px">
                    How much of your bankruptcy practice involves the following:
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column column-width="3in"/>
                      <fo:table-column column-width="2.1in"/>
                      <fo:table-column column-width="2.1in"/>
                      <!-- <fo:table-column column-width="1.8in"/> -->
                      
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border-bottom="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              	Percentage
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border-bottom="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              Average Case Value
                            </fo:block>
                          </fo:table-cell>                        
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell  border="0.2pt solid black" display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              Bankruptcy representation - Consumer:
							</fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_new_list_01/data/Percentage"></xsl:value-of>% &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_new_list_01/data/AverageCaseValue"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                          
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              Bankruptcy representation - Commercial:
							</fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                           <xsl:value-of select="response/bankruptcyCasesDetails_new_list_02/data/Percentage"></xsl:value-of>%  &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/bankruptcyCasesDetails_new_list_02/data/AverageCaseValue"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                             Bankruptcy Trustee - Consumer:
							 </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                           <xsl:value-of select="response/bankruptcyCasesDetails_new_list_03/data/Percentage"></xsl:value-of>%  &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                            <xsl:value-of select="response/bankruptcyCasesDetails_new_list_03/data/AverageCaseValue"></xsl:value-of>  &#160;
                            </fo:block>
                          </fo:table-cell>
                         </fo:table-row>
                         
                         
                         <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after" padding-left="3px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                            Bankruptcy Trustee - Commercial:
							</fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                           <xsl:value-of select="response/bankruptcyCasesDetails_new_list_03/data/Percentage"></xsl:value-of>%  &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                            <xsl:value-of select="response/bankruptcyCasesDetails_new_list_03/data/AverageCaseValue"></xsl:value-of>  &#160;
                            </fo:block>
                          </fo:table-cell>
                         </fo:table-row>
                        
                        
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              
              <fo:list-item padding-top="10px" >
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you use any third-party or outside company to finance bankruptcy litigation?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Bankruptcy_list_01/data/companyFinance = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Bankruptcy_list_01/data/companyFinance = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              </xsl:if>
              
              
              
              
              
              
              

              <fo:list-item padding-top="10px" >
                <fo:list-item-label>
                   <xsl:if test="response/isAfterbankruptcySupply='Y'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">4.</fo:inline>
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isAfterbankruptcySupply='N'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                  </xsl:if>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Have you ever represented debtors in a bankruptcy proceeding where total debt exceeded $10 million?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Bankruptcy_list_01/data/representedDebtors = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Bankruptcy_list_01/data/representedDebtors = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <xsl:if test="response/isAfterbankruptcySupply='Y'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">5.</fo:inline>
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isAfterbankruptcySupply='N'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">4.</fo:inline>
                  </fo:block>
                  </xsl:if>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you have any affiliations, or referral arrangements with third party entities or other attorneys that offer any pre-bankruptcy services in the area of debt settlement, debt resolution, debt consolidation, or debt relief?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Bankruptcy_list_01/data/preBankruptcyServices = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Bankruptcy_list_01/data/preBankruptcyServices = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                 <xsl:if test="response/isAfterbankruptcySupply='Y'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">6.</fo:inline>
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isAfterbankruptcySupply='N'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">5.</fo:inline>
                  </fo:block>
                  </xsl:if>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you have a due diligence process for certifying the truthfulness and accuracy of the debtors bankruptcy schedule?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Bankruptcy_list_01/data/dueDiligenceProcess = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Bankruptcy_list_01/data/dueDiligenceProcess = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                                                        </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <xsl:if test="response/isAfterbankruptcySupply='Y'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">7.</fo:inline>
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isAfterbankruptcySupply='N'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">6.</fo:inline>
                  </fo:block>
                  </xsl:if>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you use a review procedure for certification of the debtor’s ability to pay?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Bankruptcy_list_01/data/reviewProcedureForCertification = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Bankruptcy_list_01/data/reviewProcedureForCertification = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                <xsl:if test="response/isAfterbankruptcySupply='Y'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">8.</fo:inline>
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isAfterbankruptcySupply='N'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">7.</fo:inline>
                  </fo:block>
                  </xsl:if>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you use a uniform disclosure statement explaining the duties of the debtor in bankruptcy which is disseminated to all clients?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Bankruptcy_list_01/data/disclosureStatementExplaining = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Bankruptcy_list_01/data/disclosureStatementExplaining = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <xsl:if test="response/isAfterbankruptcySupply='Y'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">9.</fo:inline>
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isAfterbankruptcySupply='N'">
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">8.</fo:inline>
                  </fo:block>
                  </xsl:if>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you include a conspicuous statement in all advertising stating that the firm is acting as a debt relief agency and containing all required disclosures?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Bankruptcy_list_01/data/conspicuousStatement = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Bankruptcy_list_01/data/conspicuousStatement = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>
</xsl:if>



<xsl:if test="response/AOP_PercentageValueCheck_31 > 0">
<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" >
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      COLLECTIONS SUPPLEMENT <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>                      
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              
              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" padding-top="2px">
                              Over the past three years what is the average number of cases the firm has handled per year?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border-bottom="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                               <xsl:value-of select="response/Collections_list_01/data/averageCases"></xsl:value-of>&#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="2px">
                    &#160;&#160;&#160;What percentage of your collections practice are:
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="1.7in"/>
                      <fo:table-column column-width="0.8in"/>
                      <fo:table-column column-width="1.7in"/>
                      <fo:table-column column-width="0.8in"/>
                      <fo:table-column column-width="1.7in"/>
                      <fo:table-column column-width="0.8in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              Consumer Collections?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border-bottom="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/Collections_list_01/data/consumerCollections"></xsl:value-of> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              Commercial Collections?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/Collections_list_01/data/commercialollections"></xsl:value-of> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                              Total (must equal 100%)
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             100 %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>


              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              What is the average debt amount for individual collections accounts?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/collectionAmount_list_01/data/individualCollectionsAmount"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">4.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you use non-lawyer personnel to collect debts?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Collections_list_01/data/personnelToCollectDebts = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Collections_list_01/data/personnelToCollectDebts = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">5.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you own or provide any services to purchasers of debt or debt consolidators?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Collections_list_01/data/servicesToPurchasers = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Collections_list_01/data/servicesToPurchasers = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">6.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Within the past three years have you been a party to any claims or suits under the FDCPA?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Collections_list_01/data/claimsOrSuitsFDCPA = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Collections_list_01/data/claimsOrSuitsFDCPA = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>
	</xsl:if>
		<xsl:if test="((response/AOP_PercentageValueCheck_6 > 0) and(response/AOP_PercentageValueCheck_6 &lt;= 6))">
		<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px" >
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      COPYRIGHT / TRADEMARK SUPPLEMENT  <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Do you provide services other than searches and filings?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/CopyRightTrademark_list_01/data/otherServices = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/CopyRightTrademark_list_01/data/otherServices = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                              If <fo:inline font-style="italic" font-weight="bold"> "Yes" </fo:inline>, please describe:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell  border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                            <xsl:value-of select="response/CopyRightTrademark_list_01/data/otherServicesDesc"></xsl:value-of>
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>
          </xsl:if>
          <xsl:if test="response/isCCB='Y'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px">
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      CORPORATE / COMMERCIAL / BUSINESS SUPPLEMENT  <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" start-indent="4mm">
            NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR CORPORATE/COMMERCIAL/BUSINESS IN THE AREA OF PRACTICE TABLE IS GREATER THAN 10%.
          </fo:block>

          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Does your firm provide legal services related to growing, sale, or manufacture of cannabis product or set up and development of such companies?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceCCB = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/firm_freeform_01/data/IsFirmProvidedLegalServiceCCB = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                            
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>
          </xsl:if>
<xsl:if test="response/AOP_PercentageValueCheck_8 > 35">
<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px" >
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray" >
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      FAMILY LAW SUPPLEMENT <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" start-indent="4mm">
            NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR FAMILY LAW IN THE AREA OF PRACTICE TABLE IS GREATER THAN 35%.
          </fo:block>

          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                    Please complete the chart below for the past fiscal year for the Family Law services that you provide:
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column column-width="2.50in"/>
                      <fo:table-column column-width="1.25in"/>
                      <fo:table-column column-width="2.50in"/>
                      <fo:table-column column-width="1.25in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="center">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center" margin-left="-4mm" margin-right="2mm">
                              % of Revenue
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center" margin-left="-4mm" margin-right="2mm">
                              % of Revenue
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="left">
                              a.	Adoption
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                             <xsl:value-of select="response/FLAOP_PercentageValue_1"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              e.	Divorce – assets under $5M
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                             <xsl:value-of select="response/FLAOP_PercentageValue_4"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="left">
                              b.	Assisted Reproductive Technology
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                             <xsl:value-of select="response/FLAOP_PercentageValue_2"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              f.	Divorce – assets over $5M
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                             <xsl:value-of select="response/FLAOP_PercentageValue_5"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="left">
                              c.	Child Support
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              <xsl:value-of select="response/FLAOP_PercentageValue_3"/>%
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              g.	Surrogacy
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                             <xsl:value-of select="response/FLAOP_PercentageValue_6"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="left">
                              d.	Custody
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                             <xsl:value-of select="response/FLAOP_PercentageValue_8"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              h.	Other (please describe):
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                             <xsl:value-of select="response/FLAOP_PercentageValue_7"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                         
                          <fo:table-cell border="0.2pt solid black"  display-align="after"  number-columns-spanned="4">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              <xsl:value-of select="response/FLAO_CommentDesc_7"/>  &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="left">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="right">
                             &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="left">
                              TOTAL (must equal 100%)
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell border="0.2pt solid black"  display-align="after">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                            <xsl:value-of select="response/FLAOP_total"/> %  &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>
          </xsl:if>
           <xsl:if test="response/AOP_PercentageValueCheck_12 > 0">
           <fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px">
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      FINANCIAL INSTITUTION SUPPLEMENT <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="2px">
                    With regard to any financial institution client(s) within the past five years, has any current or former attorney of the firm:
                  </fo:block>
                  <fo:block>
                    <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block>
                            <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">a.</fo:inline>
                          </fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="7mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                            <fo:table border="0.2pt solid white">
                              <fo:table-column column-width="5.8in"/>
                              <fo:table-column column-width="1.5in"/>
                              <fo:table-body>
                                <fo:table-row>
                                  <fo:table-cell>
                                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                                      had any equity interest in or a loan commitment in or from said financial institution other than a mortgage on a personal residence?
                                    </fo:block>
                                  </fo:table-cell>
                                  <fo:table-cell text-align="right">
                                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">


							<xsl:choose>
                            <xsl:when test="response/FinancialInstitution_list_01/data/equityInterest = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/FinancialInstitution_list_01/data/equityInterest = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                                    
                            </fo:block>
                                  </fo:table-cell>
                                </fo:table-row>
                              </fo:table-body>
                            </fo:table>
                          </fo:block>
                        </fo:list-item-body>
                      </fo:list-item>

                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block>
                            <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">b.</fo:inline>
                          </fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="7mm">                          
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                            <fo:table border="0.2pt solid white">
                              <fo:table-column column-width="5.8in"/>
                              <fo:table-column column-width="1.5in"/>
                              <fo:table-body>
                                <fo:table-row>
                                  <fo:table-cell>
                                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                                      performed services related to regulatory compliance, opinion letters, preferred loan documentation, foreclosure or loan workout?
                                    </fo:block>
                                  </fo:table-cell>
                                  <fo:table-cell text-align="right">
                                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/FinancialInstitution_list_01/data/servicesRelatedRegulatory = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/FinancialInstitution_list_01/data/servicesRelatedRegulatory = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>  


                                    </fo:block>
                                  </fo:table-cell>
                                </fo:table-row>
                              </fo:table-body>
                            </fo:table>
                          </fo:block>
                          
                        </fo:list-item-body>
                      </fo:list-item>
                      
                    </fo:list-block>                    
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              
              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              Is any institution which you have represented within the past five years currently or been previously under regulatory review by any state or government agency or had action taken against them?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/FinancialInstitution_list_01/data/underRegulatoryReview = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/FinancialInstitution_list_01/data/underRegulatoryReview = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>  


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              Has any financial institution for which you have done work in the past five years become bankrupt or insolvent?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/FinancialInstitution_list_01/data/haveBecomeBankruptInsolvent = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/FinancialInstitution_list_01/data/haveBecomeBankruptInsolvent = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>  


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              
              
              
            </fo:list-block>
          </fo:block>
</xsl:if>
<xsl:if test="response/AOP_PercentageValueCheck_13 > 0"> 
<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px">
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      GOVERNMENT SUPPLEMENT  <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="10mm">
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm" padding-top="10px">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="2px">
                    Please complete the information below for each city, town, county or municipality to whom you provide services:
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column column-width="3.5in"/>
                      <fo:table-column column-width="3.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center" space-after="4mm">
                              Name
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black" >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center" space-after="4mm">
                              Services Provided
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                       
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                             <xsl:value-of select="response/governmentServices_list_01/data/ServiceName"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                             <xsl:value-of select="response/governmentServices_list_01/data/ServicesProvided"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                             <xsl:value-of select="response/governmentServices_list_02/data/ServiceName"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                            <xsl:value-of select="response/governmentServices_list_02/data/ServicesProvided"></xsl:value-of>  &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                             <xsl:value-of select="response/governmentServices_list_03/data/ServiceName"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" space-after="4mm">
                             <xsl:value-of select="response/governmentServices_list_03/data/ServicesProvided"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Are you providing bond work as part of your services?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">



							<xsl:choose>
                            <xsl:when test="response/Government_list_01/data/providingBondWork = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Government_list_01/data/providingBondWork = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                            
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="2px">
                              Are you providing services related to eminent domain?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/Government_list_01/data/eminentDomainServices = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/Government_list_01/data/eminentDomainServices = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>  

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
             
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>
</xsl:if>
<xsl:if test="response/isShowLitigationSupplement='Y'">
<fo:block page-break-after="always"></fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px" >
            <fo:table border="0.2pt solid black" >
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      PLAINTIFF LITIGATION SUPPLEMENT <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block >
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                   <xsl:if test="response/isBeforeLitigation='Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    Please complete the following chart for the past fiscal year, providing a breakdown of the firm’s litigation practice based on case information:
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isBeforeLitigation='N'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    Please complete the following plaintiff litigation on providing the approximate largest case size for the past fiscal year  for each area of the firm's
           		 		plaintiff litigation practice:
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isBeforeLitigation='Y'">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column column-width="3.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell  border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              AREA OF LITIGATION
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              % of Plaintiff Litigation
                            </fo:block>
                          </fo:table-cell>

                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              % of Defense Litigation
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              Largest Case Size
                            </fo:block>
                          </fo:table-cell>

                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              a.	Civil
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_14"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOLKey_PercentageValue_14"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_14"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              b.	Construction
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_15"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOLKey_PercentageValue_15"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_15"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              c.	Corporate / Commercial / Business
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_4"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOLKey_PercentageValue_4"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_4"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              d.	Personal Injury / Property Damage
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_6"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOLKey_PercentageValue_6"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_6"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              e.	Medical Malpractice
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_5"/>%
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOLKey_PercentageValue_5"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_5"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              f.	Products Liability
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_8"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOLKey_PercentageValue_8"/>%
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_8"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              g.	Professional Liability (non-medical)
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_9"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              
 							<xsl:value-of select="response/AOLKey_PercentageValue_9"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_9"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              h.	Mass Tort Litigation / Class Action / Toxic Tort
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_11"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOLKey_PercentageValue_11"/> %

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_11"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              i.	Other (please describe) :
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/PercentageOfRevenue_13"/> %
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOLKey_PercentageValue_13"/> %

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_13"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                             Description :
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aol_plaintiffMap/data/CommentDesc_13"/> 
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOLKey_CommentDesc_13"/> 
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                            
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              TOTAL (both columns combined must equal 100%)
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/aol_percentageOfRevenue"/> %

                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.2pt solid black" number-columns-spanned="2">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOLKey_total"/>  %

                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                    If there is any percentage in the Plaintiff column above please answer the questions below:
                  </fo:block>
                  </xsl:if>
                  <xsl:if test="response/isBeforeLitigation='N'">
                   <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid black">
                      <fo:table-column column-width="3.5in"/>
                    
                      <fo:table-column column-width="2.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell  border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              AREA OF LITIGATION
                            </fo:block>
                          </fo:table-cell>
                         
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              Largest Plaintiff Case Size
                            </fo:block>
                          </fo:table-cell>

                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              a.	Civil
                            </fo:block>
                          </fo:table-cell>
                        
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_14"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              b.	Construction
                            </fo:block>
                          </fo:table-cell>
                       
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_15"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              c.	Corporate / Commercial / Business
                            </fo:block>
                          </fo:table-cell>
                        
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_4"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              d.	Personal Injury / Property Damage
                            </fo:block>
                          </fo:table-cell>
                        
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_6"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              e.	Medical Malpractice
                            </fo:block>
                          </fo:table-cell>
                     
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_5"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              f.	Products Liability
                            </fo:block>
                          </fo:table-cell>
                        
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_8"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              g.	Professional Liability (non-medical)
                            </fo:block>
                          </fo:table-cell>
                          
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_9"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              h.	Mass Tort Litigation / Class Action / Toxic Tort
                            </fo:block>
                          </fo:table-cell>
                        
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                             <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_11"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                    <fo:table-row>
                          <fo:table-cell border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              i.	Other  :
                            </fo:block>
                          </fo:table-cell>
                        
                          <fo:table-cell text-align="right" border="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              <xsl:value-of select="response/aol_plaintiffMap/data/LargestCaseSize_13"/>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row> 
                        
                      
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                 
                  </xsl:if>
                  
                  
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" >
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" margin-top="2px">
                              Number of support staff devoted to plaintiff work:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                            <xsl:value-of select="response/plaintiff_freeform_01/data/NumberOfSuppotedStaffToPlantiff"></xsl:value-of>
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" margin-top="2px">
                              Total number of plaintiff cases during the past 12 months:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                            <xsl:value-of select="response/plaintiff_freeform_01/data/NumberOfInjuryCasesIn12Month"></xsl:value-of>
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">4.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.5in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                              Of the number of plaintiff cases handled by the firm, in the past five years, what percentage of them were settled prior to trial:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  border-bottom="0.2pt solid black">
                              <xsl:value-of select="response/plaintiff_freeform_01/data/PerOfCasesSettledBeforeTrail"></xsl:value-of>%
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">5.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" margin-top="2px">
                              Do you accept referrals from other firms?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
							<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppAcceptRefersFromOtherFirms = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppAcceptRefersFromOtherFirms = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                            
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
						 <xsl:if test="response/plaintiff_freeform_01/data/IsAppAcceptRefersFromOtherFirms = 'Y'">
                        <fo:table-row >
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" padding-top="5px">
                              If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline>, are written referral agreements used?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="5px">
							<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToAppl = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToAppl = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
						</xsl:if>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">6.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" margin-top="2px">
                              Do you refer cases to other law firms?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppReferToOtherLawFirms = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppReferToOtherLawFirms = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <xsl:if test="response/plaintiff_freeform_01/data/IsAppReferToOtherLawFirms = 'Y'">
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="5px">
                              a. &#160; &#160; &#160; If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline>, are written referral agreements used?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="5px">

							<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToApplRefersOut = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsRefAggrementReferToApplRefersOut = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        </xsl:if>
                        <xsl:if test="response/plaintiff_freeform_01/data/IsAppReferToOtherLawFirms = 'Y'">

                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" padding-top="5px">
                              b. &#160; &#160; &#160; If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline>, do you confirm that firms to which referrals are made carry professional liability insurance?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="5px">

							<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppConfirmCarryProfLiabIns = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppConfirmCarryProfLiabIns = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        </xsl:if>

                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">7.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="2px">
                              Are written authorizations for settlement always obtained from clients when settlements are reached?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
	<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsSettlementAggrementsUsed = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsSettlementAggrementsUsed = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">8.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="2px">
                              In the past five years have you been lead counsel or co-lead counsel on any mass tort or class action matter?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
	<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/massTortOrClassAction = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/massTortOrClassAction = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 


                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>


</xsl:if>



<xsl:if test="response/FlagForRealEstate='Y'">
    <fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px">
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      REAL ESTATE SUPPLEMENT  <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>


          <fo:block space-after="4mm">
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                    Please complete the following chart, providing a breakdown of the firm’s real estate practice based
                  </fo:block>
                  <fo:block start-indent="7mm" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm"> on revenue for the past fiscal year:</fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.9pt solid black">
                      <fo:table-column column-width="5in"/>
                      <fo:table-column column-width="2.3in"/>
                    
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell  border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                            RESIDENTIAL REAL ESTATE AREA OF SPECIALTY
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              % of Residential Revenue
                            </fo:block>
                          </fo:table-cell>
                        <!--   <fo:table-cell  border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              % of Commercial Revenue
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              a.	Purchase and Sale
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  start-indent="center">
                             <xsl:value-of select="response/AOPRE_PercentageValue_1"/> %
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValueCom_1"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              b.	Title Opinions
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueRes_22"/>%
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueCom_22"/>%
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black" >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              c.	Foreclosures / Loan Workouts
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValue_19"/> %
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_19"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              d.	Property Valuation / Real Estate Tax Abatement
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_7"/> %
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValueCom_7"/>  %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              e.	Eminent Domain
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_12"/> %
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_12"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              f.	Landlord Tenant
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_6"/>%
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_6"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              g.	Condominiums, Cooperatives or Homeowner Associations
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValue_4"/> %
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right"  border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_4"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              h.	Leases
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueRes_23"/> %
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueCom_23"/>%
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              i.	Land Use / Development
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_11"/> %
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueCom_11"/>%
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              j.	Speculative Real Estate, Limited Partnerships, Real Estate Syndications
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_13"/> %
                            </fo:block>
                          </fo:table-cell>
                        <!--   <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_13"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              k.	Real Estate Trusts
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueRes_16"/> %
                            </fo:block>
                          </fo:table-cell>
                        <!--   <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_16"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              l.	Other (please describe):
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValue_20"/>%
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_20"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>
                        
                         <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              	Other Desc:
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_CommentDesc_20"/>
                            </fo:block>
                          </fo:table-cell>
                        <!--   <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_CommentDescCom_20"/> 
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black" >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              TOTAL (if applicable, each column separately must equal 100%)
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                            <xsl:value-of select="response/aopreRes_total"/>  %
                            </fo:block>
                          </fo:table-cell>
                        <!--   <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aoprecCom_total"/> %
                            </fo:block>
                          </fo:table-cell> -->
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  
                   <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    Please complete the following chart, providing a breakdown of the firm’s real estate practice based on revenue for the past fiscal year:
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.9pt solid black">
                      <fo:table-column column-width="5in"/>
                      <fo:table-column column-width="2.3in"/>
                    
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell  border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                           COMMERCIAL REAL ESTATE AREA OF SPECIALTY
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              % of Residential Revenue
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell  border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                              % of Commercial Revenue
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              a.	Purchase and Sale
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValue_1"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValueCom_1"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              b.	Title Opinions
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueRes_22"/>%
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueCom_22"/>%
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              c.	Foreclosures / Loan Workouts
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValue_19"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_19"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              d.	Property Valuation / Real Estate Tax Abatement
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_7"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValueCom_7"/>  %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              e.	Eminent Domain
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_12"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_12"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              f.	Landlord Tenant
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_6"/>%
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_6"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              g.	Condominiums, Cooperatives or Homeowner Associations
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/AOPRE_PercentageValue_4"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right"  border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_4"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              h.	Leases
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueRes_23"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueCom_23"/>%
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              i.	Land Use / Development
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_11"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValueCom_11"/>%
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              j.	Speculative Real Estate, Limited Partnerships, Real Estate Syndications
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValue_13"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_13"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              k.	Real Estate Trusts
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueRes_16"/> %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_16"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              l.	Other (please describe):
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_PercentageValue_20"/>%
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_PercentageValueCom_20"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                        
                         <fo:table-row>
                          <fo:table-cell border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                              	Other Desc:
                            </fo:block>
                          </fo:table-cell>
                          <!-- <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/AOPRE_CommentDesc_20"/>
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                              <xsl:value-of select="response/AOPRE_CommentDescCom_20"/> 
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>

                        <fo:table-row>
                          <fo:table-cell border="0.9pt solid black" >
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                              TOTAL (if applicable, each column separately must equal 100%)
                            </fo:block>
                          </fo:table-cell>
                         <!--  <fo:table-cell text-align="right" border="0.9pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                            <xsl:value-of select="response/aopreRes_total"/>  %
                            </fo:block>
                          </fo:table-cell> -->
                          <fo:table-cell text-align="right" border="0.9pt solid black" padding-right="5px">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                             <xsl:value-of select="response/aoprecCom_total"/> %
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  
                  
                  
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="5.1in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="0.4in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" >
                              Residential
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="center" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" >
                              Commercial
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="5.3in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="0.2in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" margin-top="2px">
                              What is the approximate number of transactions handled in the past 12 months?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                               <xsl:value-of select="response/RealEastate_list_01/data/transactionsHandled12MonthsResi"></xsl:value-of>&#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                              <xsl:value-of select="response/RealEastate_list_01/data/transactionsHandled12MonthsComm"></xsl:value-of> &#160;
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="5.3in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-column column-width="0.2in"/>
                      <fo:table-column column-width="1.0in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" margin-top="2px">
                              what was the largest real estate transaction in the past 12 months?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/amountForRealEstate_list_01/data/largestTransaction12MonthsResi"></xsl:value-of>
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                              &#160;
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right" border-bottom="0.2pt solid black">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                               <xsl:value-of select="response/amountForRealEstate_list_01/data/largestTransaction12MonthsComm"></xsl:value-of>
                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">4.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="2px">
                              Do non-attorney staff members ever attend closings on behalf of the firm in lieu of attorneys?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/RealEastate_list_01/data/attendClosings = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/RealEastate_list_01/data/attendClosings = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">5.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="2px">
                              Does the firm’s practice include securing financing for its clients?
                            </fo:block>
                          </fo:table-cell>
                           <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/RealEastate_list_01/data/includeSecuringFinancing = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/RealEastate_list_01/data/includeSecuringFinancing = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                 <!--        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              If <fo:inline font-weight="bold" font-style="italic"> "Yes" </fo:inline>, are written referral agreements used?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

							<xsl:choose>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppConfirmCarryProfLiabIns = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/plaintiff_freeform_01/data/IsAppConfirmCarryProfLiabIns = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row> -->
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>

              <fo:list-item padding-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">6.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              If you act in a dual capacity in the same real estate transaction, do you always use a disclosure form signed by both parties?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/RealEastate_list_01/data/useDisclosureForm = 'Y'">
                            N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/RealEastate_list_01/data/useDisclosureForm = 'N'">
                            N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160;  Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/RealEastate_list_01/data/useDisclosureForm = 'A'">
                            N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                            N/A <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160;    Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
            </fo:list-block>
          </fo:block>
          </xsl:if>

		<xsl:if test="response/AOP_PercentageValueCheck_22 > 25">
		<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" padding-top="20px" padding-bottom="10px">
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      TAX SUPPLEMENT  <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" start-indent="7mm">
            NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR TAX IN THE AREA OF PRACTICE TABLE IS GREATER THAN 25%.
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
          1. &#160; &#160; Please complete the following chart, providing a breakdown of the firm’s tax practice based on revenue for the past fiscal year:
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="3.1in"/>
              <fo:table-column column-width="0.9in"/>
              <fo:table-body>
                <fo:table-row padding-left="2px">
                  <fo:table-cell  border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      AREA OF TAX PRACTICE
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      % of Revenue
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      AREA OF TAX PRACTICE
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      % of Revenue
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell  border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      a.	Personal
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_PercentageValue_1"/>%
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      f.	Liquidation of Corporations
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_PercentageValue_6"/> %
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell  border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      b.	Corporate
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_PercentageValue_2"/> %
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      g.	Opinions on Tax Shelters
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_PercentageValue_7"/> %
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell  border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      c.	Estate Tax Returns
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_PercentageValue_3"/> %
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      h.	Opinions Involving Private Placement Memoranda
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_PercentageValue_8"/> %
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell  border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      d.	Investment Counselor Services
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_PercentageValue_4"/> %
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      i.	Other (please describe):
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                     <xsl:value-of select="response/Tax_PercentageValue_9"/>  %
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell  border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      e.	Subchapter S Elections
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                     <xsl:value-of select="response/Tax_PercentageValue_5"/>  %
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                     <xsl:value-of select="response/Tax_CommentDesc_9"/>  &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell  border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      TOTAL (must equal 100%)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      <xsl:value-of select="response/Tax_total"/> %
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          
</xsl:if>
<xsl:if test="response/AOP_PercentageValueCheck_24 > 0">
<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="20px" margin-bottom="10px" >
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      TRUSTS, WILLS, ESTATE, PROBATE SUPPLEMENT <fo:inline font-style="italic">(as required in Question 6, Area of Practice Table)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
          1.&#160;&#160; Please complete the following chart, providing a breakdown of the firm’s trusts, wills, estate and probate practice based
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" start-indent="6mm">
            on revenue for the past fiscal year:
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="3.0in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      % of Revenue
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      % of Revenue
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      a.	Preparation of Wills
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_1"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      h.	Corporation Formation
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                   <xsl:value-of select="response/WESKey_PercentageValue_5"/>   %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      b.	Estate Planning
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_2"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      i.	Taxation
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_7"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      c.	Probate
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                    <xsl:value-of select="response/WESKey_PercentageValue_3"/>  %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      j.	Tax Opinions
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_6"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      d.	Trust Administration
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_4"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      k.	Asset Protection
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                    <xsl:value-of select="response/WESKey_PercentageValue_8"/>  %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      e.	Guardianship
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="response/WESKey_PercentageValue_9"/>%&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      l.	Litigation
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="response/WESKey_PercentageValue_11"/>%&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      f.	Medical Planning
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_10"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      m.	Other (please describe):
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_12"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      g.	Trust Creation
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="response/WESKey_PercentageValue_13"/> %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;Description:  <xsl:value-of select="response/WESKey_CommentDesc_12"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                     &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      TOTAL (must equal 100%)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                    <xsl:value-of select="response/WESKey_total"/>  %&#160;&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="2px">
                              Does any attorney currently serve as Executor/Personal Representative/Administrator or Trustee?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">

							<xsl:choose>
                            <xsl:when test="response/willsEstate_freeform_01/data/IsAttorneyServeAsExecutorTrustee = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/willsEstate_freeform_01/data/IsAttorneyServeAsExecutorTrustee = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  
                  <xsl:if test="response/willsEstate_freeform_01/data/IsAttorneyServeAsExecutorTrustee = 'Y'">
                  
                  
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="6.0in"/>
                      <fo:table-column column-width="1.5in"/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
                              If <fo:inline font-style="italic" font-weight="bold"> "Yes" </fo:inline> , do your activities as a trustee include authority to make decisions resulting in the purchase or sale of securities, real estate or other investments?
                            </fo:block>
                          </fo:table-cell>
                          <fo:table-cell text-align="right">
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
							<xsl:choose>
                            <xsl:when test="response/willsEstate_freeform_01/data/inPurchaseSaleSecurities = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/willsEstate_freeform_01/data/inPurchaseSaleSecurities = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>

                            </fo:block>
                          </fo:table-cell>
                        </fo:table-row>
                      </fo:table-body>
                    </fo:table>
                  </fo:block>
                  </xsl:if>
                  
                </fo:list-item-body>
              </fo:list-item>
  		
             <!--  <fo:list-item margin-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" margin-top="2px">
                    Please complete below for the 3 largest trusts for which you provide services:
                  </fo:block>
                  
                </fo:list-item-body>
              </fo:list-item> -->
              
            </fo:list-block>
          </fo:block>
         
         
          <xsl:if test="((response/WESKey_PercentageValue_4 > 0) or (response/WESKey_PercentageValue_13 > 0))">
           <fo:block>
           <fo:list-block>
            <fo:list-item margin-top="10px">
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" margin-top="2px">
                    Please complete below for the 3 largest trusts for which you provide services:
                  </fo:block>
                  
                </fo:list-item-body>
              </fo:list-item>
           </fo:list-block>
         </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="1mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="3.5in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="1.5in"/>
              <fo:table-column column-width="1.3in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      Name of Trust
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      *Type
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      Value of Assets
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      **Beneficiary Interest
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                     &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      <xsl:value-of select="response/largestTrust_list_01/data/TrustName"></xsl:value-of>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      <xsl:value-of select="response/largestTrust_list_01/data/TypeofTrustDesc"></xsl:value-of> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                     <xsl:value-of select="response/largestTrust_list_01/data/AssetsValue"></xsl:value-of> $
                    </fo:block>
                  </fo:table-cell>                  
                  <fo:table-cell text-align="right" border="0.2pt solid black"  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
							<xsl:choose>
                            <xsl:when test="response/largestTrust_list_01/data/BeneficiaryInterest = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/largestTrust_list_01/data/BeneficiaryInterest = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                
                
               <xsl:if test="((response/largestTrust_list_02/data/TrustName != '') or (response/largestTrust_list_02/data/TypeofTrustDesc != '') or ((response/largestTrust_list_02/data/AssetsValue != '') and (response/largestTrust_list_02/data/AssetsValue != 0)))">
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      <xsl:value-of select="response/largestTrust_list_02/data/TrustName"></xsl:value-of>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                     <xsl:value-of select="response/largestTrust_list_02/data/TypeofTrustDesc"></xsl:value-of> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      <xsl:value-of select="response/largestTrust_list_02/data/AssetsValue"></xsl:value-of> $
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
							<xsl:choose>
                            <xsl:when test="response/largestTrust_list_02/data/BeneficiaryInterest = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/largestTrust_list_02/data/BeneficiaryInterest = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
               </xsl:if>
               
                <xsl:if test="((response/largestTrust_list_03/data/TrustName != '') or (response/largestTrust_list_03/data/TypeofTrustDesc != '') or ((response/largestTrust_list_03/data/AssetsValue != '') and (response/largestTrust_list_03/data/AssetsValue != 0)))">
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      <xsl:value-of select="response/largestTrust_list_03/data/TrustName"></xsl:value-of>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                     <xsl:value-of select="response/largestTrust_list_03/data/TypeofTrustDesc"></xsl:value-of>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      <xsl:value-of select="response/largestTrust_list_03/data/AssetsValue"></xsl:value-of> $
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
								<xsl:choose>
                            <xsl:when test="response/largestTrust_list_03/data/BeneficiaryInterest = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="response/largestTrust_list_03/data/BeneficiaryInterest = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose> 

                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                </xsl:if>
              </fo:table-body>
            </fo:table>
          </fo:block>
          
         
          

          <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left" start-indent="7mm">
            *E = Estate, P = Personal/Family, B = Business trusts, F = Foundations, C = Charities, R = Real Estate, I = Irrevocable Life Insurance trust
          </fo:block>
          <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" start-indent="7mm" space-after="4mm">
            **Beneficiary Interest means any personal interest you or your relatives might have as heir or beneficiary of the trust funds, other than customary fees as trustee, administrator, executor or personal representative to which you are entitled.
          </fo:block>
          </xsl:if>
          
		 <xsl:if test="((response/WESKey_PercentageValue_1 > 0) or (response/WESKey_PercentageValue_2 > 0) or (response/WESKey_PercentageValue_3 > 0))">
          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="6.0in"/>
              <fo:table-column column-width="1.3in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" margin-top="2px">
                      4. &#160; &#160; &#160; If applicable, how many wills or estates did you handle in the past year?
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after" border-bottom="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                    <xsl:value-of select="response/willsEstate_freeform_01/data/willEstatesHandledPastYear"></xsl:value-of>
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          
          </xsl:if>
           <fo:block page-break-before="auto"></fo:block> 
           </xsl:if>
          
<xsl:if test="response/firm_freeform_01/data/IsApplInitiatedLawsuitForFirm = 'Y'">
<fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" margin-top="20px" margin-bottom="10px" >
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      FEE SUIT SUPPLEMENT <fo:inline font-style="italic">(as required in Question 18)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            1. &#160; &#160; Please complete the following for each suit the firm has filed within the past three years against a client for collection of fees due:
          </fo:block>

          <fo:block space-after="1mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="0.3in"/>
              <fo:table-column column-width="1.8in"/>
              <fo:table-column column-width="1.8in"/>
              <fo:table-column column-width="1.8in"/>
              <fo:table-column column-width="1.8in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      Amount of Fees Sued For
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      Date Fee Suit Filed
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      Was There a Counter Claim or Allegation of Legal Malpractice?
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
                      Disposition of Fee Suit *
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <xsl:for-each select="response/firmlawsuit_practice_list_01/data">
                <fo:table-row>
                  <fo:table-cell border-right="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="center">
                      <xsl:value-of select="AmountOfFeesSued"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2px">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="SuitFilesDateFees"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
						<xsl:choose>
                            <xsl:when test="IsAllegOfLegalMalPrac = 'Y'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="IsAllegOfLegalMalPrac = 'N'">
                            Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>


                    </fo:block>
                  </fo:table-cell>                  
                  <fo:table-cell text-align="right" border="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="FeeSuitDesc"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                </xsl:for-each>
                

                
              </fo:table-body>
            </fo:table>
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" start-indent="7mm" space-after="15mm">
            *P = Fees paid in full, NS = Negotiated Settlement, JP = Judgment Plaintiff, JD = Judgment Defense, O = Open
          </fo:block>
		 <fo:block page-break-before="auto"></fo:block> 
		
</xsl:if>
 <xsl:if test="response/firm_freeform_01/data/FlagForClaim= 'Y'">
  <fo:block page-break-after="always"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" >
            <fo:table border="0.2pt solid black">
              <fo:table-column/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border="0.2pt solid black" background-color="LightGray">
                    <fo:block margin-top="1mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" font-size="10px">
                      CLAIM SUPPLEMENT <fo:inline font-style="italic">(as required in Question 30)</fo:inline>
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          
          <xsl:for-each select="response/claims_list_1/data">
          
         <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="2.9in"/>
              <fo:table-column column-width="3.2in"/>
              <fo:table-column column-width="1.5in"/>
              
              <fo:table-body text-align="justify">
                <fo:table-row >
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      1.&#160; Full name of claimant or potential claimant:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="NameOfClaimant"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                    <xsl:choose>
                            <xsl:when test="IsClient = 'Y'">
                            Client <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; Non-Client <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="IsClient = 'N'">
                            Client <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Non-Client <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             Client <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Non-Client <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
<!--                       &#160; &#160; <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> Client &#160; &#160; <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> Non-Client
 -->                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="6.1in"/>
              <fo:table-column column-width="1.5in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      2.&#160; Is the claim in suit?
                    </fo:block>
                  </fo:table-cell>                  
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                   			 <xsl:choose>
                            <xsl:when test="IsClaimInSuit='X'">
                            NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="IsClaimInSuit='Y'">
                            NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="IsClaimInSuit='N'">
                            NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>
                    
                    
                    
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="4.3in"/>
              <fo:table-column column-width="1.1in"/>
              <fo:table-column column-width="1.2in"/>
              <fo:table-column column-width="1.0in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      3.&#160;Date you were notified of claim or became aware of error/incident:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="center">
                      <xsl:value-of select="ClaimNotifiedDate"/>&#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Date of alleged error:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="center">
                     <xsl:value-of select="DateOfAllegedError"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-style="italic">
                      MM / DD / YY
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-style="italic">
                      MM / DD / YY
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="4.2in"/>
              <fo:table-column column-width="3.41in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      4.&#160;Names of firm personnel involved in the claim or potential claim:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black"  padding="2pt">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                    <xsl:value-of select="NameOfPersonnelINClaim"/>  &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="2.6in"/>
              <fo:table-column column-width="5.0in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      5.&#160;Description of claim or potential claim:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border="0.2pt solid black" padding="2pt">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                    <xsl:value-of select="DescOfClaim"/>  &#160;
                    </fo:block>
                  </fo:table-cell>                  
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <!-- <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="7.5in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border-bottom="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block> -->
          

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="6.0in"/>
              <fo:table-column column-width="1.5in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      6.&#160; Has this claim or potential claim been reported to the firm’s professional liability insurer?
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
 						<xsl:choose>
                            <xsl:when test="IsClaimReportedToInsComp='X'">
                            NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="IsClaimReportedToInsComp='Y'">
                            NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            </xsl:when>
                            <xsl:when test="IsClaimReportedToInsComp='N'">
                            NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                             NA <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> &#160; Yes <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>&#160; No <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline>
                            
                            </xsl:otherwise>
                            </xsl:choose>                 
                               </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="2.3in"/>
              <fo:table-column column-width="1.2in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      7.&#160;Name of insurance company:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="NameOfInsComp"/>  &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Date reported to insurance company:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                    <xsl:value-of select="DateReportedToInsComp"/>    &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-style="italic">
                      MM / DD / YY
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="3.5in"/>
              <fo:table-column column-width="1.2in"/>
              <fo:table-column column-width="1.8in"/>
              <fo:table-column column-width="1.1in"/>
              <fo:table-column column-width="0.1in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      8.&#160;If claim is now open, provide: Insurer’s loss reserve 
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="InsurerLoss"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Claimant’s last demand 
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="ClaimantLastDemand"/>  &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      ;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>
          
          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="1.3in"/>
              <fo:table-column column-width="6.5in"/>
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      Current Status:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="CurrentStatus"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block space-after="4mm">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="1.0in"/>
              <fo:table-column column-width="3.8in"/>
              <fo:table-column column-width="0.7in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left">
                      9.&#160;If closed, provide: date closed
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                     <xsl:value-of select="ClaimClosingDate"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Total amount paid (including damages and defense expenses): 
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.2pt solid black" display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      <xsl:value-of select="TotalAmountPaid"/>&#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center" font-style="italic">
                      MM / DD / YY
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="2mm">
            10. &#160; &#160; What steps have you taken to prevent similar allegations from being made in future claims/incidents?
          </fo:block>
          
         <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
                      <xsl:value-of select="StepsTakenToPreventClaims"/> &#160;
            </fo:block>
          </xsl:for-each>
          </xsl:if>
         






<fo:block id="TheVeryLastPage"> </fo:block>


        </fo:flow>
      </fo:page-sequence>
    </fo:root>


  </xsl:template>

</xsl:stylesheet>
