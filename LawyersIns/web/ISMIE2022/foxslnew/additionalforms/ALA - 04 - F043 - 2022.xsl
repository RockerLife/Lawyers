<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="ProfessionalServicesExclusionEndorsement">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					
  					 
				     <fo:block margin-top="3mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="12px">SPECIFIC PROFESSIONAL SERVICES EXCLUSION ENDORSEMENT </fo:block>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block  font-size="10px">In consideration of the premium charged, it is agreed that his policy does not apply to any <fo:inline font-weight="bold" >Claim </fo:inline>alleging, based   
						upon, arising out of or attributable to the rendering of, or failure to render services in the following areas:</fo:block>
				 	  
				 	 <fo:block margin-top="5mm"/>
				 	  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
                      <xsl:value-of select="response/additionaldata_freeform_01/data/SpecificProfessionalServices"></xsl:value-of>
					 </fo:block>
				 	
					<fo:block margin-top="1cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="18.2cm"/>
				    	       
	         	
	         	
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F043 (03/01/2022)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	