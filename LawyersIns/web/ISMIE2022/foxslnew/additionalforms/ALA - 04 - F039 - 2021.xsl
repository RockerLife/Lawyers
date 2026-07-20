<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="CareerCoverageEndorsement">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					
				     <fo:block margin-top="3mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">CAREER COVERAGE ENDORSEMENT – SPECIFIED ATTORNEY</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block space-after="5mm"  font-size="10px">In consideration of the premium charged, and solely for the purposes of coverage provided by this endorsement, it is agreed that:</fo:block>
				 	 
				 	 
				 	  <fo:block space-after="1cm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="5mm">
				    <fo:block  text-align="left" font-size="10px" >Item 6. of the Declarations, <fo:inline font-weight="bold" >Prior Acts Date</fo:inline>, is amended to include the date shown in Column B below with respect to the <fo:inline font-weight="bold" >Specified Attorney</fo:inline> shown in Column A below:</fo:block>
				     </fo:list-item-body>
				     </fo:list-item>
				    </fo:list-block>
				     </fo:block>
				     
					<fo:block>
					 <fo:block margin-top="10mm"/>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "100mm" />
					     <fo:table-column column-width = "100mm" />
				        <fo:table-body>
				         <fo:table-row>
					                    <fo:table-cell padding-left="4pt">
					                    <fo:block   font-size="10px"   text-align="center"> A </fo:block>
					                    </fo:table-cell>
					               <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"   text-align="center"> B</fo:block></fo:table-cell>
					              
					              </fo:table-row>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt">
					                    <fo:block text-decoration="underline" font-size="10px" font-weight="bold"   text-align="center"> Specified Attorney </fo:block>
					                    </fo:table-cell>
					               <fo:table-cell padding-left="4pt"><fo:block  text-decoration="underline" font-weight="bold" font-size="10px"   text-align="center"> Prior Acts Date</fo:block></fo:table-cell>
					              
					              </fo:table-row>
					             	    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                   &#160; </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                    &#160;</fo:block></fo:table-cell>
					              </fo:table-row>				    		  
					    		  <xsl:for-each select="response/CarrearCoverageAttorneyDetails_list_01/data">
					                <fo:table-row>
					                    <fo:table-cell padding-left="4pt">
					                    <fo:block font-size="10px"  text-align="center" >
					                    	 <xsl:value-of select="AttorneyName"/>
					                     </fo:block></fo:table-cell>
					                     <fo:table-cell padding-left="4pt">
					                     <fo:block font-size="10px"  text-align="center" >
					                    	 <xsl:value-of select="AttorneyPriorActDate"/>
					                    </fo:block></fo:table-cell>	
					              </fo:table-row>
					                </xsl:for-each>
					              
					               <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                   &#160; </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                    &#160;</fo:block></fo:table-cell>
					              </fo:table-row>	
					             			    		  
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				
				
				 <fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="5mm">
				    <fo:block font-size="10px" >Section IV., Definitions, is amended as follows:</fo:block>
				     </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				
				
					<fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block start-indent="5mm" margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">a.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="10mm">
				    <fo:block  text-align="left" font-size="10px" >Definition I., <fo:inline font-weight="bold" >Insured</fo:inline>, is amended by addition of the following:</fo:block>
				    <fo:block  text-align="left" font-size="10px" ><fo:inline font-weight="bold" >Insured</fo:inline> also means any current principal, partner, director, officer, member or employee of the <fo:inline font-weight="bold" >Named Insured</fo:inline> that is listed as a <fo:inline font-weight="bold" >Specified Attorney</fo:inline> in Column A of item 1. above, but only with respect to <fo:inline font-weight="bold" >Professional Services</fo:inline> performed by such <fo:inline font-weight="bold" >Specified Attorney</fo:inline> on behalf of any other prior legal firm that does not qualify as a <fo:inline font-weight="bold" >Predecessor Firm</fo:inline>.</fo:block>
				    </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				     
				     <fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block start-indent="5mm" margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">b.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="10mm">
				    <fo:block  text-align="left" font-size="10px" >Definition Q., <fo:inline font-weight="bold" >Professional Services</fo:inline>, paragraph 4., is deleted and replaced with the following:</fo:block>
				    </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				     
				      <fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block start-indent="10mm" margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">4.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="15mm">
				    <fo:block  text-align="left" font-size="10px" >pro bono services performed by an <fo:inline font-weight="bold" >Insured</fo:inline> if at the time such services were rendered, they were approved by a partner, director or officer of the prior legal firm to be performed without compensation;</fo:block>
				    </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				     
				      <fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block start-indent="5mm" margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">c.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="10mm">
				    <fo:block  text-align="left" font-size="10px" >The following new definition is added:</fo:block>
				    </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				     
				     <fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block start-indent="5mm" margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"></fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="10mm">
				    <fo:block  text-align="left" font-size="10px" >Specified Attorney means the attorney listed in Column A of item 1. above.</fo:block>
				    </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				     
				     <fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="5mm">
				    <fo:block  text-align="left" font-size="10px" >Section VI., General Condition H., Other Insurance, is amended by addition of the following:</fo:block>
				     </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				     
				      <fo:block space-after="4mm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block margin-top="-0.8mm">
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"></fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="5mm">
						<fo:block text-align="left" font-size="10px">However, this
							policy does not apply to any <fo:inline font-weight="bold" >Claim</fo:inline> made against a <fo:inline font-weight="bold" >Specified Attorney</fo:inline>
							based upon or arising out of such <fo:inline font-weight="bold" >Specified Attorney’s</fo:inline> negligent acts,
							errors or omissions within the scope of their duties on behalf of any
							person or entity other than the <fo:inline font-weight="bold" >Named Insured</fo:inline>, if other insurance for
							such <fo:inline font-weight="bold" >Claim</fo:inline> is available to such <fo:inline font-weight="bold" >Specified Attorney</fo:inline>. This policy shall
							not be subject to the terms or conditions of any other insurance.
						</fo:block>
				     </fo:list-item-body>
				     </fo:list-item>
				     </fo:list-block>
				     </fo:block>
				     
				 
				     
				     
					 
				     <fo:block margin-top="2cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="3.7cm"/>
				    	       
	         	
	         	 
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F039 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	