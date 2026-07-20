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
				<fo:simple-page-master master-name="A4-portrait" page-width="30cm"   
					page-height="29.7cm" margin-top="0.2in" margin-bottom="1.0in" margin-left="0.5in" 
					margin-right="0.5in">
					<fo:region-body margin-top="2.0in"/>
					<fo:region-before extent="1.0in"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4-portrait">
			
			<fo:static-content flow-name="xsl-region-before">
				<fo:block text-align="left">				  	
				  	<fo:external-graphic src="../LawyersIns/image/logo_protexureaccountants1.png" content-height="6em" content-width="20em"/>           	
				</fo:block>							
			</fo:static-content>
								  				
				<fo:static-content flow-name="xsl-region-after">
				  <fo:block text-align="left">Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block>
				</fo:static-content>
				
				<fo:flow flow-name="xsl-region-body">				
					
				<fo:block white-space-collapse="false" white-space-treatment="preserve" linefeed-treatment="preserve" font-family="monospace">
					<xsl:value-of select="response/XmlOutputDatafromRating" />					
				</fo:block>
				
				<fo:block id="last-page" font-size="11px"/>		
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

</xsl:stylesheet>




































