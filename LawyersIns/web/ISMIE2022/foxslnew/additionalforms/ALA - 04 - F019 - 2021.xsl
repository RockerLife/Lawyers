<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="TitleAgency">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					
  					 
				     <fo:block margin-top="3mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="12px">TITLE AGENCY ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block  font-size="10px">In consideration of the premium charged, it is agreed that Section IV., Definition I., <fo:inline font-weight="bold" >Insured</fo:inline>, is amended to include:</fo:block>
				 	  <fo:table>
              <fo:table-column column-width="5in" />
               <fo:table-body>
                <fo:table-row>
                 <fo:table-cell  padding-top="18pt">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
                      <xsl:value-of select="response/additionaldata_freeform_01/data/TitleAgencyName"></xsl:value-of>
					 </fo:block>
                    </fo:table-cell></fo:table-row></fo:table-body>
              </fo:table>
				 	 <fo:block margin-top="5mm"/>
				 	
				 	<fo:block text-align="left" font-size="10px">for those services performed as a title agent for a client of the <fo:inline font-weight="bold" >Named Insured</fo:inline> which are incidental to services performed as a lawyer for the client for a monetary fee. </fo:block>
					
					
              
					<fo:block margin-top="1cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="16.5cm"/>
				    	       
	         	
	         	
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F019 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	