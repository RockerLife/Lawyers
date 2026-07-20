<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
  <xsl:template match="/" name="LPL-117-NJ">
          <fo:block>				  	
			<xsl:call-template name="CommonHeader" />						           	
		  </fo:block>


          <fo:block text-align="center" margin-left="1.3in" margin-right="1.3in" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-before="5mm" space-after="5mm" font-weight="bold">

            AGGREGATE DEDUCTIBLE FOR ALL CLAIMS - NEW JERSEY
            DOES NOT APPLY TO DEFENSE EXPENSES


          </fo:block>
          <fo:block padding-left="5pt" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="justify">

            <fo:block space-after="4mm">
              In consideration of: (check one box only)
            </fo:block>
            <fo:block space-after="4mm">

              <fo:list-block provisional-label-separation="0.15cm">
                <fo:list-item space-after="4mm">
                  <fo:list-item-label start-indent="7mm">
                    <fo:block>
                      <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="12mm">
                    <fo:block>
                      <fo:inline>an additional premium of $__________
                    </fo:inline>                      
                    </fo:block>
                  </fo:list-item-body>
                </fo:list-item>
                <fo:list-item>
                  <fo:list-item-label start-indent="7mm">
                    <fo:block>
                      <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="12mm">
                    <fo:block>
                      the premium charged
                    </fo:block>
                  </fo:list-item-body>
                </fo:list-item>
              </fo:list-block>
              
            </fo:block>
            <fo:block space-after="4mm">
              and notwithstanding anything to the contrary in the New Jersey Amendatory Endorsement, LPL-AMEND-NJ, it is agreed that:
            </fo:block>

            <fo:block space-after="4mm">

              <fo:list-block>
                <fo:list-item space-after="4mm">
                  <fo:list-item-label>
                    <fo:block>
                      <fo:inline>1.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    
                    <fo:block space-after="3mm" start-indent="7mm">
                     Item 4. of the Declarations, Deductible, is deleted and replaced with the following:
                    </fo:block>
                      <fo:block>

                      <fo:table border="0.2pt solid white" width="100%">
                        <fo:table-column column-width="0.3in"/>
                        <fo:table-column column-width="0.7in"/>
                        <fo:table-column column-width="6.6in"/>

                        <fo:table-body>
                          
                          <fo:table-row>
                            <fo:table-cell border-right="0.2pt solid white">
                              <fo:block text-align="left">
                                &#160;
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.2pt solid black" padding="2pt">
                              <fo:block text-align="left" text-indent="2mm">
                                Item 4.
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="0.2pt solid black"  padding="2pt">
                              <fo:block text-align="left" text-indent="2mm">
                                <fo:inline font-weight="bold">Deductible</fo:inline>:
                              </fo:block>
                              <fo:block text-align="left" text-indent="2mm">
                                <fo:inline>$</fo:inline>
                                <fo:inline>.....</fo:inline> 
                                <!--<fo:table>
                                <fo:table-column column-width="0.4in"/>
                                <fo:table-body>
                                  <fo:table-row>
                                    <fo:table-cell background-color="#D0CDCD">
                                      <fo:block text-align="left">&#160;</fo:block>
                                    </fo:table-cell>
                                  </fo:table-row>
                                </fo:table-body>
                              </fo:table>-->
                                <fo:inline>Aggregate Deductible for all</fo:inline>
                                 <fo:inline font-weight="bold">Claims</fo:inline>
                              </fo:block>
                              <fo:block text-align="left" text-indent="2mm">
                                This amount does not apply to <fo:inline font-weight="bold">Defense Expenses</fo:inline>
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
                    <fo:block>
                      <fo:inline>2.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body start-indent="15mm">
                    <fo:block space-after="3mm" start-indent="7mm">
                      <fo:block space-after="3mm">
                        Section II. of the policy, Limits of Liability and Deductible, subsection B., Deductible, is deleted and replaced with the following:
                      </fo:block>
                      <fo:block space-after="3mm">

                        <fo:list-block>
                          <fo:list-item>
                            <fo:list-item-label>
                              <fo:block>
                                <fo:inline font-weight="bold">B.</fo:inline>
                              </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body>

                              <fo:block start-indent="13mm">
                                <fo:inline font-weight="bold">Deductible</fo:inline>
                              </fo:block>

                            </fo:list-item-body>
                          </fo:list-item>
                        </fo:list-block>
                      </fo:block>
                      <fo:block start-indent="13mm">
                        <fo:block>
                          The <fo:inline font-weight="bold">Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-weight="bold">Damages</fo:inline> which are in excess of the Deductible amount shown in Item 4. of the Declarations.  This Deductible amount shall:
                        </fo:block>
                        <fo:block>
                          <fo:list-block>
                            <fo:list-item>
                              <fo:list-item-label>
                                <fo:block>
                                  <fo:inline>1.</fo:inline>
                                </fo:block>
                              </fo:list-item-label>
                              <fo:list-item-body>

                                <fo:block start-indent="20mm">
                                  be borne by the <fo:inline font-weight="bold">Insured</fo:inline>;
                                </fo:block>
                                

                              </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                              <fo:list-item-label>
                                <fo:block>
                                  <fo:inline>2.</fo:inline>
                                </fo:block>
                              </fo:list-item-label>
                              <fo:list-item-body>

                                <fo:block start-indent="20mm">
                                  remain uninsured; and
                                </fo:block>


                              </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                              <fo:list-item-label>
                                <fo:block>
                                  <fo:inline>3.</fo:inline>
                                </fo:block>
                              </fo:list-item-label>
                              <fo:list-item-body>

                                <fo:block start-indent="20mm">
                                  be the maximum amount borne by the <fo:inline font-weight="bold">Insured</fo:inline> for all <fo:inline font-weight="bold">Claims</fo:inline> under this policy.
                                </fo:block>


                              </fo:list-item-body>
                            </fo:list-item>
                          </fo:list-block>
                        </fo:block>
                        <fo:block>
                          If the <fo:inline font-weight="bold">Insured</fo:inline> has paid this amount, no further Deductibles shall apply to <fo:inline font-weight="bold">Claims</fo:inline>.  The Deductible amount applies to the payment of <fo:inline font-weight="bold">Damages</fo:inline> only and 
                          shall not apply to <fo:inline font-weight="bold">Defense Expenses</fo:inline>.  If the <fo:inline font-weight="bold">Insurer</fo:inline> advances any amounts within the Deductible, the <fo:inline font-weight="bold">Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold">Insurer</fo:inline> within
                          30 days of the <fo:inline font-weight="bold">Insurer’s</fo:inline> request to do so.  In the event of <fo:inline font-weight="bold">Related Claims</fo:inline>, a single Deductible amount will apply.
                        </fo:block>
                      </fo:block>
                    </fo:block>
                  </fo:list-item-body>
                </fo:list-item>
              </fo:list-block>

            </fo:block>
            
             <fo:block>
               All other terms, conditions and limitations of the policy remain unaltered.
             </fo:block>
            <fo:block margin-top="7cm"/>
			<fo:block  font-size="10px" color="grey" text-align="left">LPL - 117 (04/17) NJ<!--<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1170417NJ"/> --></fo:block>
				
          </fo:block>
          
  </xsl:template>
  
</xsl:stylesheet>
