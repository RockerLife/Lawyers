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
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">  ISMIE ALA-04-S013 (05/01/2025)  </fo:block>
		                      </xsl:when>                
                      <xsl:otherwise>
                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey"> &#160;</fo:block>
                      </xsl:otherwise>
                    </xsl:choose>
                     <xsl:if test="response/policy_freeform_01/data/CompanyKey= 4">
		                        <fo:block text-align="left" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey">  LPL- Pro 100 (10/21) </fo:block>
		                     </xsl:if>  
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
              <fo:table-row>
	              <fo:table-cell number-columns-spanned="2">
	              
	              	<xsl:choose>
	              		<xsl:when test="response/policy_freeform_01/data/CompanyKey= 3">
	              			<fo:block text-align="center" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="grey"> 
			                         ISMIE Mutual Insurance Company </fo:block>
	              		</xsl:when>
	              	<xsl:otherwise>
	              			<fo:block />
	              	</xsl:otherwise>
	              	</xsl:choose>
	              		    
	                 	
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
                              <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="right"><fo:inline font-weight="bold">Phone:</fo:inline> 877-569-4111 / <fo:inline font-weight="bold">Fax:</fo:inline> (440) 333-3214 / <fo:inline font-weight="bold">Email:</fo:inline> info@protexure.com</fo:block>


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
                    <fo:block  font-size="14px" font-family="Arial Black" font-weight="bold" text-align="center">LAWYERS PROFESSIONAL LIABILITY BRIDGE APPLICATION</fo:block>
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
                
                
                
                <!-- <fo:table-row>
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
                       
                        <xsl:value-of select="response/policy_freeform_01/data/Address2"></xsl:value-of>
          </fo:block>
                  
                  
                  
   
                  </fo:table-cell>

                 

                </fo:table-row>
                 -->
                
                
                
                
                
                
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
		                
                  <fo:block >
                    <fo:table>
                      <fo:table-column/>
                      <fo:table-body>
                        <fo:table-row>
                          <fo:table-cell>
                            <fo:block margin-top="3mm" margin-left="5mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="" font-size="10px">
                             Contact Information:
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
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"></fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="3mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
                    <fo:table border="0.2pt solid white">
                      <fo:table-column column-width="1.6in" />
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
                  <fo:table-cell border-bottom="0.9pt solid black"  display-align="after" text-align="left">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                     <xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Position Held at the Firm:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.9pt solid black" display-align="after" text-align="left">
                    <fo:block text-align="left" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                    <!--  <xsl:value-of select="response/firm_freeform_01/data/secondayContactPerson"></xsl:value-of> --> &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                        <fo:table-row>
                 
                  <!-- <fo:table-cell display-align="after">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="center">
                      Secondary Phone Number:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.9pt solid black" display-align="after">
                    <fo:block text-align="left" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                       <xsl:value-of select="response/policy_freeform_01/data/otherPhone"></xsl:value-of>&#160;
                    </fo:block>
                  </fo:table-cell> -->
                  <fo:table-cell display-align="after" padding-top="4mm" padding-right="1mm">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  text-align="right">
                      Email:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after" >
                    <fo:block   font-size="9px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="left"	border-bottom-width="0.9pt" border-bottom-style="solid"	border-bottom-color="black">
                     <xsl:value-of select="response/policy_freeform_01/data/AccountEmail"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                   <fo:table-cell display-align="after" padding-top="4mm" padding-right="1mm">
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
                      Phone Number:
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-bottom="0.9pt solid black" display-align="after" text-align="left">
                    <fo:block text-align="left" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                     <xsl:value-of select="response/policy_freeform_01/data/WorkPhone"/>&#160;<xsl:value-of select="response/policy_freeform_01/data/WorkExt"/> &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                </fo:table-row>

                   <!-- <fo:table-row>
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
                
                </fo:table-row> -->
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
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
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
          <fo:block>
            <fo:table>
              <fo:table-column column-width="3.4in" />
              <fo:table-column column-width="4.2in"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block  font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                      2.&#160;&#160;Description of the attached application for review:

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="18px"  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
                     <!--  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate"></xsl:value-of> -->

                    </fo:block>
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-style="italic" text-align="center"
					>
					Completed Alternate Carrier Application Submitted for Review

                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell  padding-top="10px" >
                    <fo:block start-indent="3.7cm" font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      Date Application was signed:

                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  padding-top="18px"  >
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											border-bottom-width="0.9pt" border-bottom-style="solid"
											border-bottom-color="black">
                     <!--  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate"></xsl:value-of> -->

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
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
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
                  <xsl:if test="response/firm_freeform_01/data/IsPersonnelBeSubOfAnyInvest != 'N' and (response/StatusDesc != 'Issued' or response/firm_freeform_01/data/IsPersonnelBeSubOfAnyInvest = 'Y')">
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
                    NOTE: THE POLICY FOR WHICH THIS APPLICATION IS BEING MADE SHALL NOT APPLY TO ANY INCIDENTS OR CLAIMS DETAILED OR WHICH SHOULD HAVE BEEN DETAILED IN QUESTION a, b or c ABOVE.
                  </fo:block>
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-after="4mm">
                  <fo:inline font-weight="bold" font-style="italic">  If  "Yes"  to b or c above, complete the  CLAIM SUPPLEMENT for each claim or potential claim.</fo:inline>
                  </fo:block>
                  
                </fo:list-item-body>
              </fo:list-item>

              
              
              
              </fo:list-block>
              </fo:block>
              
                 
                  
              
		   <fo:block padding-top="20px"></fo:block>
		   <!-- <fo:block page-break-before="always"></fo:block> -->
          <xsl:choose>
			
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'CO'">
        
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">COLORADO FRAUD WARNING: </fo:inline> IT IS UNLAWFUL TO KNOWINGLY PROVIDE FALSE, INCOMPLETE, OR MISLEADING FACTS OR INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE COMPANY.  PENALTIES MAY INCLUDE IMPRISONMENT, FINES, DENIAL OF INSURANCE, AND CIVIL DAMAGES.  ANY INSURANCE COMPANY OR AGENT OF AN INSURANCE COMPANY WHO KNOWINGLY PROVIDES FALSE, INCOMPLETE, OR MISLEADING FACTS OR INFORMATION TO A POLICYHOLDER OR CLAIMANT FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE POLICYHOLDER OR CLAIMANT WITH REGARD TO A SETTLEMENT OR AWARD PAYABLE FROM INSURANCE PROCEEDS SHALL BE REPORTED TO THE COLORADO DIVISION OF INSURANCE WITHIN THE DEPARTMENT OF REGULATORY AGENCIES.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'FL'">
         
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">FLORIDA FRAUD WARNING: </fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO INJURE, DEFRAUD OR DECEIVE ANY INSURER FILES A STATEMENT OF CLAIM OR AN APPLICATION CONTAINING ANY FALSE, INCOMPLETE, OR MISLEADING INFORMATION IS GUILTY OF A FELONY OF THE THIRD DEGREE.
          </fo:block>
            </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'KS'">
        
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">KANSAS FRAUD WARNING: </fo:inline>ANY PERSON WHO, KNOWINGLY AND WITH INTENT TO DEFRAUD, PRESENTS, CAUSES TO BE PRESENTED OR PREPARES WITH KNOWLEDGE OR BELIEF THAT IT WILL BE PRESENTED TO OR BY AN INSURER, PURPORTED INSURER, BROKER OR ANY AGENT THEREOF, ANY WRITTEN STATEMENT AS PART OF, OR IN SUPPORT OF, AN APPLICATION FOR THE ISSUANCE OF, OR THE RATING OF AN INSURANCE POLICY FOR PERSONAL OR COMMERCIAL INSURANCE WHICH SUCH PERSON KNOWS TO CONTAIN MATERIALLY FALSE INFORMATION CONCERNING ANY FACT MATERIAL THERETO; OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRADULENT INSURANCE ACT.
          </fo:block>
       </xsl:when>
          <xsl:when test="response/policy_freeform_01/data/StateCode= 'KY'">
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="30px" margin-bottom="10px">
            <fo:inline font-weight="bold">KENTUCKY FRAUD WARNING: </fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT ACT, WHICH IS A CRIME.
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
          </xsl:when>
          <xsl:otherwise>
					<fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            <fo:inline font-weight="bold"></fo:inline>ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON
				FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE
				INFORMATION OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT
				MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON
				TO CRIMINAL AND CIVIL PENALTIES. (Not applicable in AL, AR, CO, DC, FL, KS, KY, LA, MD, ME, NJ, NM, NY, OH, OK, OR,
				RI, TN, VA, VT, WA or WV – see Additional Fraud Notices for these States below).
          </fo:block>
          
				</xsl:otherwise>
          
          </xsl:choose>
          
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            Completion and/or signing of this application does not bind the firm to purchase, nor the <fo:inline font-weight="bold">Insurer</fo:inline> to provide, any insurance policy;
			however, no policy can be issued unless the application is properly completed, signed and dated.
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm">
            The signatory declares that (s)he is authorized by the firm to sign this application on behalf of all prospective <fo:inline font-weight="bold">Insureds</fo:inline> and that to
			the best of his/her knowledge the statements herein are true. The signatory agrees that if the information supplied in this
			application and the materials submitted therewith should change between the date this application is signed and the effective date
			of the proposed insurance, the signatory shall immediately notify the <fo:inline font-weight="bold">Insurer</fo:inline> of such and shall provide the <fo:inline font-weight="bold">Insurer</fo:inline> with
			information that would complete, update or correct the application or materials submitted therewith. The <fo:inline font-weight="bold">Insurer</fo:inline> may withdraw or
			modify any of the terms or conditions of coverage accordingly.
          </fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="1cm">
            <fo:inline font-weight="bold">ALL WRITTEN STATEMENTS, SUPPLEMENTAL APPLICATION AND MATERIALS FURNISHED TO THE INSURER IN
			CONJUNCTION WITH THIS APPLICATION ARE INCORPORATED BY REFERENCE INTO THIS APPLICATION AND MADE
			A PART THEREOF, AND DEEMED ATTACHED HERETO.</fo:inline>
          </fo:block>
           
         
      	
           
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


<xsl:choose>
	<xsl:when test="response/policy_freeform_01/data/StateCode='FL'">	
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
</xsl:when>
<xsl:when test="response/policy_freeform_01/data/StateCode='IA'">	
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
    </xsl:when> 
    <xsl:when test="response/policy_freeform_01/data/StateCode='NH'">			             
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
          </xsl:when>
         <xsl:otherwise>
				<fo:block>
	  
	         </fo:block>
			</xsl:otherwise>          
</xsl:choose>    
       
		
  <!--  <fo:block break-after="page"/> -->
          <!-- <fo:block page-break-before="always"></fo:block> -->
          
		

        

<!--  <xsl:if test="response/firm_freeform_01/data/FlagForClaim != 'N' and (response/StatusDesc != 'Issued' or response/firm_freeform_01/data/FlagForClaim= 'Y')">
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
                      &#160; &#160; <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#x2714;</fo:inline> Client &#160; &#160; <fo:inline font-family="ZapfDingbats" font-size="8pt" border="1pt black solid">&#xA0;&#xA0;&#xA0;</fo:inline> Non-Client
                    </fo:block>
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

          <fo:block space-after="4mm">
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
          </fo:block>
          

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
         
         
  -->        
         


<fo:block id="TheVeryLastPage"> </fo:block>


        </fo:flow>
      </fo:page-sequence>
    </fo:root>


  </xsl:template>

</xsl:stylesheet>
