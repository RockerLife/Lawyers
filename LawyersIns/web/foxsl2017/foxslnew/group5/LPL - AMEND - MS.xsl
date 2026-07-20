<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
  
  <xsl:template match="/" name="LPL-AMEND-MS">
         <fo:block>				  	
			<xsl:call-template name="CommonHeader" />						           	
		 </fo:block>

          <fo:block text-align="center" margin-left="1.3in" margin-right="1.3in" font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"  space-before="5mm"  space-after="5mm" font-weight="bold">

           MISSISSIPPI AMENDATORY ENDORSEMENT
		
		 </fo:block>
          <fo:block padding-left="5pt" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="justify">

            <fo:block space-after="4mm">
             In consideration of the premium charged, it is agreed that Section VI., General Condition D., Cancellation and Nonrenewal, is amended by addition of the following:
            </fo:block>
           
            <fo:block space-after="4mm">
                      <fo:list-block>
                        <fo:list-item>
                          <fo:list-item-label>
                            <fo:block >
                              <fo:inline font-weight="bold">•</fo:inline>
                            </fo:block>
                          </fo:list-item-label>
                          <fo:list-item-body>
								<fo:block space-after="1cm" start-indent="5mm">A reduction
									in coverage is not effective unless notice is mailed or delivered to
									the <fo:inline font-weight="bold">Named Insured</fo:inline> by the <fo:inline font-weight="bold">Insurer</fo:inline> not less than 30 days prior to the
									effective date of such reduction in coverage.
								</fo:block>
                          </fo:list-item-body>
                        </fo:list-item>
                      </fo:list-block>
                    </fo:block>
			  
            <fo:block margin-top="9mm">
              All other terms, conditions and limitations of the policy remain unaltered.
            </fo:block>

         </fo:block>
             <fo:block margin-top="18cm"/>
           <fo:block  font-size="10px" color="grey" text-align="left">LPL-AMEND-MS (04/17) &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
           &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Page 1 of 1</fo:block>
           
  </xsl:template>
</xsl:stylesheet>
