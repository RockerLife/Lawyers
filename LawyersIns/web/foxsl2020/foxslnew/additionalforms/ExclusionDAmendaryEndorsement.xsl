<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="ExclusionDAmendaryEndorsement">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
				   
				   
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">EXCLUSION D AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     
				     <fo:block text-align="left" font-size="10px" >In consideration of: (check one box only)</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of $ <xsl:value-of select="response/PremiumValue"/></fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
				    
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >it is agreed that Section V., Exclusion D., paragraph 2., is deleted and replaced with the following:</fo:block>
				     <fo:block margin-top="4mm"/>
				     
				     <fo:block space-after="1cm">
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label>
                        <fo:block>
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="5mm">
				    <fo:block  text-align="left" font-size="10px" > such <fo:inline font-weight="bold" >Insured</fo:inline> or their <fo:inline font-weight="bold" >Immediate Family Member</fo:inline> controlled or owned more than   <xsl:value-of select="response/test"/>   % equity interest, operated or managed such entity; or</fo:block>
				     </fo:list-item-body>
				     
				     
				     </fo:list-item>
				     
				     </fo:list-block>
				     </fo:block>
				     
				      <fo:block space-after="15cm" text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     
				    
				     <xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F012 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	</xsl:if>
			 	 
     </xsl:template>
</xsl:stylesheet>




					
				    	