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
				<fo:simple-page-master master-name="FirstPage" page-width="21cm"   
					page-height="29.7cm" margin-top="0.2in" margin-bottom="1.0in" margin-left=".25in" 
					margin-right=".25in">
					<fo:region-body margin-top=".5in"/>
					<fo:region-before extent=".5in"  region-name="first-page-header"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
				
				<fo:simple-page-master master-name="OtherPage" page-width="21cm"   
					page-height="29.7cm" margin-top="0.2in" margin-bottom="1.0in" margin-left=".25in" 
					margin-right=".25in">
					<fo:region-body margin-top=".5in"/>
					<fo:region-before extent=".5in"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
				
				 <fo:page-sequence-master master-name="pages">
          <fo:repeatable-page-master-alternatives>
            <!-- if page-position="first" choose the simple-page-master with master-name="first-page" -->
            <fo:conditional-page-master-reference page-position="first" master-reference="FirstPage"/>

            <!--otherwise choose the simple-page-master with master-name="other-page" -->
            <fo:conditional-page-master-reference master-reference="OtherPage"/>
          </fo:repeatable-page-master-alternatives>
        </fo:page-sequence-master>
        
				
			</fo:layout-master-set>

			<fo:page-sequence master-reference="pages">
			
			<fo:static-content flow-name="first-page-header">
				<fo:block text-align="left">				  	
				  	<fo:external-graphic src="../LawyersIns/image/logo_protexureaccountants1.png" content-height="6em" content-width="20em"/>           	
				</fo:block>	
				<!-- <fo:block text-align="right">				  	
						<fo:external-graphic src="../LawyersIns/image/CFLogo.png"/>           	
					</fo:block>  -->
				<fo:block border-bottom="1pt solid blue"></fo:block>					
			</fo:static-content>
								  				
				<fo:static-content flow-name="xsl-region-after">
				  <fo:block text-align="left">Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block>
				</fo:static-content>
				
				<fo:flow flow-name="xsl-region-body">


          <fo:block font-size="10px" font-family="Arial" margin-top="5mm">

           <fo:table>
              <fo:table-column column-width="44mm"/>

              <fo:table-column column-width="5mm"/>

              <fo:table-column column-width="5mm"/>

              <fo:table-column column-width="44mm"/>

              <fo:table-column column-width="5mm"/>

              <fo:table-column column-width="44mm"/>

              <fo:table-column column-width="5mm"/>

              <fo:table-column column-width="44mm"/>
              

              <fo:table-body>
                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" font-weight="bold">
                      Indication Date
                    </fo:block>
                    
                  </fo:table-cell>
                  
                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policycoverage_freeform_01/data/RatingDate" />
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell number-columns-spanned="7">
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                </fo:table-row>
                
                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Insured Name
                    </fo:block>
                  </fo:table-cell>

                   <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Insured email
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                  <fo:table-cell>
                   <fo:block text-align="left" font-weight="bold">
                      Insured Phone number
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Sub producer Number
                    </fo:block>

                  </fo:table-cell>
                  
                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policy_freeform_01/data/AccountName"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policy_freeform_01/data/AccountEmail"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policy_freeform_01/data/WorkPhone"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policycoverage_freeform_01/data/ProducerCode"/>
                    </fo:block>
                  </fo:table-cell>
                  
                </fo:table-row>
                
                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  
                </fo:table-row>
                
                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Street Address
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                   <fo:block text-align="left" font-weight="bold">
                      Zip
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>

				<fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/address_freeform_01/data/Street"/>
                    </fo:block>
                  </fo:table-cell>
                  
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/address_freeform_01/data/Zip"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      State
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                   <fo:block text-align="left" font-weight="bold">
                      County
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                   <fo:block text-align="left" font-weight="bold">
                      City
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/address_freeform_01/data/StateDesc"/>
                    </fo:block>
                  </fo:table-cell>
                  
                    
                  
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/address_freeform_01/data/CountyDesc"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/address_freeform_01/data/City"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Effective Date
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Limit
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Deductible
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>
                
                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policycoverage_freeform_01/data/LimitAmount"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell>
                   <fo:block text-align="left" font-weight="bold">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                  <fo:block text-align="left" font-weight="bold">
                     Defense Option
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                     <fo:block text-align="left" font-weight="bold">
                      Deductible Option
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell padding-bottom="2mm">
                   <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                   <fo:block text-align="left">
				   <xsl:choose>
                        <xsl:when test="response/policycoverage_freeform_01/data/IsClaimExpensesType='Y'">
                          Defense Outside
                        </xsl:when>
                        <xsl:otherwise>
                          Defense Inside
                        </xsl:otherwise>
                      </xsl:choose>                      
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                 <fo:table-cell>
                    <fo:block border=".5pt solid gray" text-align="left"  padding-bottom="2mm">
                      <xsl:choose>
                        <xsl:when test="response/policycoverage_freeform_01/data/IsClaimOptionType='Y'">
                          Aggregate
                        </xsl:when>
                        <xsl:otherwise>
                          Per Claim
                        </xsl:otherwise>
                      </xsl:choose>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      <xsl:choose>
                        <xsl:when test="response/policycoverage_freeform_01/data/IsDollarDefense='Y'">
                          First Dollar Defense <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>
                        </xsl:when>
                        <xsl:otherwise>
                          First Dollar Defense <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>
                        </xsl:otherwise>
                      </xsl:choose>
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>
                
                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Number of Full time attorneys
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Age Of Lawyers
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                   <fo:block text-align="left" font-weight="bold">
                      Full Time Equivalent(FTE)
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/firm_freeform_01/data/NumberOfLawyers" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell number-columns-spanned="3">
                    <fo:block text-align="left">

                      <fo:table>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>

                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>

                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-body>
                          <fo:table-row>
                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                               	&#160;<xsl:value-of select="response/ClaimAge_1" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                              
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                              	&#160;<xsl:value-of select="response/ClaimAge_2" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                              
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                                &#160;<xsl:value-of select="response/ClaimAge_3" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                               
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                                &#160;<xsl:value-of select="response/ClaimAge_4" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                              
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                                &#160;<xsl:value-of select="response/ClaimAge_5" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left">                               
                               &#160;
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                              &#160; <xsl:value-of select="response/ClaimAge_6" />
                              </fo:block>
                            </fo:table-cell>
                            
                          </fo:table-row>

                        </fo:table-body>
                      </fo:table>
                            
                    </fo:block>
                  </fo:table-cell>
                  

                  
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      &#160;<xsl:value-of select="response/FullTimeEquivalent" />
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="1mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

           
<fo:table-cell number-columns-spanned="3">
                    <fo:block text-align="left">

                      <fo:table>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>

                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>

                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-column column-width="1mm"/>
                        <fo:table-column column-width="13mm"/>

                        <fo:table-body>
                          <fo:table-row>
                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                               	&#160;<xsl:value-of select="response/ClaimAge_7" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                              
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                              	&#160;<xsl:value-of select="response/ClaimAge_8" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                              
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                                &#160;<xsl:value-of select="response/ClaimAge_9" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                               
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                                &#160;<xsl:value-of select="response/ClaimAge_10" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left"> 
                               &#160;                              
                              </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                                &#160;<xsl:value-of select="response/ClaimAge_11" />
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left">                               
                               &#160;
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                              <fo:block text-align="left">
                              &#160; <xsl:value-of select="response/ClaimAge_12" />
                              </fo:block>
                            </fo:table-cell>
                            
                          </fo:table-row>

                        </fo:table-body>
                      </fo:table>
                            
                    </fo:block>
                  </fo:table-cell>
                  


                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
	<fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                     Expiring carrier
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                    Expiring premium
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>
                
                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/priorPolicyData_freeform_01/data/InsuranceCompanyNamePross"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/priorPolicyData_freeform_01/data/ProInsPremium"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                   <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
               
                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left">

                      <fo:table margin-top="1mm">
                        <fo:table-column column-width="96mm"/>
                        <fo:table-column column-width="2mm"/>
                        <fo:table-column column-width="96mm"/>
                        <fo:table-body>
                          <fo:table-row>

                            <fo:table-cell>
                              <fo:block>
                                <fo:table margin-top="2mm">
                                  <fo:table-column column-width="72mm"/>
                                  <fo:table-column column-width="24mm" />
                                  <fo:table-body>
                                    <fo:table-row>
                                      <fo:table-cell border="0.5pt solid gray">
                                        <fo:block font-size="10px" font-family="Arial" text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block>
                                      </fo:table-cell>
                                      <fo:table-cell border="0.5pt solid gray">
                                        <fo:block font-size="9px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block>
                                      </fo:table-cell>
                                    </fo:table-row>
                                    <xsl:for-each select="response/aopData_list_01/data">
                                      <fo:table-row>
                                        <fo:table-cell border="0.5pt solid gray">
                                          <fo:block font-size="10px" font-family="Arial" text-align="left">
                                            <xsl:value-of select="AOPDescNew"/>
                                          </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border="0.5pt solid gray">
                                          <fo:block font-size="10px" font-family="Arial" text-align="center">
                                          	<xsl:value-of select="PercentageValue"/>
                                          </fo:block>
                                        </fo:table-cell>
                                      </fo:table-row>
                                    </xsl:for-each>
                                  </fo:table-body>
                                </fo:table>
                              </fo:block>
                            </fo:table-cell>

                            <fo:table-cell>
                              <fo:block text-align="left" space-after="2mm">
                                &#160;
                              </fo:block>
                            </fo:table-cell>
                            
                            <fo:table-cell>
                              <fo:block >
                                <fo:table margin-top="2mm">
                                  <fo:table-column column-width="72mm"/>
                                  <fo:table-column column-width="24mm" />
                                  <fo:table-body>
                                    <fo:table-row>
                                      <fo:table-cell border="0.5pt solid black">
                                        <fo:block font-size="10px" font-family="Arial" text-align="center" font-weight="bold">AREA OF PRACTICE</fo:block>
                                      </fo:table-cell>
                                      <fo:table-cell border="0.5pt solid black">
                                        <fo:block font-size="10px" font-family="Arial" text-align="center" font-weight="bold">% OF REVENUE</fo:block>
                                      </fo:table-cell>
                                    </fo:table-row>
                                    <xsl:for-each select="response/aopData_list_02/data">
                                    	<xsl:choose>
                    						<xsl:when test="AOPKey = '18'">
                    						</xsl:when>
                    						<xsl:otherwise>
                    						  <fo:table-row>
		                                        <fo:table-cell border="0.5pt solid black">
		                                          <fo:block font-size="10px" font-family="Arial" text-align="left">
		                                            <xsl:value-of select="AOPDescNew"/>
		                                          </fo:block>
		                                        </fo:table-cell>
		                                        <fo:table-cell border="0.5pt solid black">
		                                          <fo:block font-size="10px" font-family="Arial" text-align="center">
		                                           	<xsl:value-of select="PercentageValue"/>                                           
		                                          </fo:block>
		                                        </fo:table-cell>
		                                      </fo:table-row>
                    						</xsl:otherwise>
                                      	</xsl:choose>
                                      
                                    </xsl:for-each>

                                    <fo:table-row>
                                      <fo:table-cell number-columns-spanned="2">
                                        <fo:block font-size="10px" font-family="Arial" text-align="center" font-weight="bold" start-indent="20pt">
                                          Total Percentage: <xsl:value-of select="response/aop_total"/> %

                                      </fo:block>
                                      </fo:table-cell>
                                     
                                    </fo:table-row>
                                    
                                  </fo:table-body>
                                </fo:table>
                              </fo:block>

                              <!--<fo:block  start-indent="12pt">
                                <fo:table border="0.5pt solid black">
                                  <fo:table-column column-width="3.0in"/>
                                  <fo:table-column column-width="0.7in"/>

                                  <fo:table-body>
                                    <fo:table-row>
                                      <fo:table-cell  border="0.5pt solid black">
                                        <fo:block  font-size="10px" font-family="Arial">
                                          Total:
                                        </fo:block>
                                      </fo:table-cell>
                                      <fo:table-cell  border="0.5pt solid black">
                                        <fo:block  font-size="10px" font-family="Arial">
                                          <xsl:value-of select="response/aop_total"/> %
                                        </fo:block>
                                      </fo:table-cell>
                                    </fo:table-row>
                                  </fo:table-body>
                                </fo:table>
                              </fo:block>-->
                          
						  </fo:table-cell>
						  
                          </fo:table-row>
                        </fo:table-body>
                      </fo:table>
                      
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" font-weight="bold">
                      Modifier
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                      <xsl:value-of select="response/policycoverage_freeform_01/data/TotalPolicyModifierPercentage" />
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell number-columns-spanned="7">
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
                
                <fo:table-row>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Total Premium
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left" font-weight="bold">
                      Premium with Taxes
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>

                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                     <xsl:value-of select="response/policycoverage_freeform_01/data/TotalPremium" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell border=".5pt solid gray" padding-bottom="2mm">
                    <fo:block text-align="left">
                     
                       <xsl:value-of select="response/policycoverage_freeform_01/data/InvoicedPremium" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                  <fo:table-cell >
                    <fo:block text-align="left">
                      &#160;
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>

                <fo:table-row>

                  <fo:table-cell number-columns-spanned="8">
                    <fo:block text-align="left" space-after="2mm">
                      &#160;
                    </fo:block>
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









