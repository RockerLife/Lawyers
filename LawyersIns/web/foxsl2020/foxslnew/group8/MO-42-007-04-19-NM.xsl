<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="NM-DWL007-Notice">
		
		<fo:block margin-top="0.5in" margin-left="0.5in" margin-right="0.5in">
		
		<fo:block text-align="left" start-indent="1cm" space-after="1.5cm" >				  	
						<fo:external-graphic src="../LawyersIns/image/CFLogo.png" content-height="17em" content-width="17em" />           	
					</fo:block>
					<fo:block font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center"
						font-size="16px">NEW MEXICO NOTICE
					</fo:block>
					<fo:block font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center"
						font-size="14px" space-after="6mm">(DEFENSE EXPENSES WITHIN THE DEDUCTIBLE)

					</fo:block>
					
					
					<fo:block space-after="10mm">
					<fo:table>
						 <fo:table-column column-width ="106mm" />
						 <fo:table-column column-width="59mm"/>
						 <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="1mm" number-columns-spanned="2" border="1pt solid black">
					                    <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    	Named Insured:
					                    </fo:block>
					                    <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    &#160;
					                    </fo:block>
					                    </fo:table-cell>
					               </fo:table-row>
					               <fo:table-row>
					               <fo:table-cell padding-left="1mm" border="1pt solid black">
					               <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    	Policy Number:
					                    </fo:block>
					                    <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    &#160;
					                    </fo:block>
					               </fo:table-cell>
					               <fo:table-cell padding-left="1mm" border="1pt solid black">
					               <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    	Effective Date:
					                    </fo:block>
					                    <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    &#160;
					                    </fo:block>
					               </fo:table-cell>
					               </fo:table-row>
					               <fo:table-row>
					                    <fo:table-cell padding-left="1mm" number-columns-spanned="2" border="1pt solid black">
					                    <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    	Insurer:
					                    </fo:block>
					                    <fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8px" text-align="left">
					                    &#160;
					                    </fo:block>
					                    </fo:table-cell>
					               </fo:table-row>
					     </fo:table-body>
					</fo:table>
					</fo:block>
					
						<fo:block font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify"
						font-size="11px" space-after="4mm">
							The undersigned acknowledges that:
						</fo:block>
						<fo:block font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify"
						font-size="11px" space-after="15mm">
							Legal defense costs that are incurred shall be applied against the deductible up to 50% and, in such event, the insurer shall be liable for legal defense costs exceeding that percentage.
						</fo:block>
						
					<fo:block space-after="7mm">
					<fo:table>
					<fo:table-column column-width="1.02in"/>
					<fo:table-column column-width="2.5in"/>
					<fo:table-column column-width="1.2in"/>
					<fo:table-column column-width="2.5in"/>
					<fo:table-body>
					<fo:table-row>
					<fo:table-cell>
					<fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="left">
						SIGNATURE*
					</fo:block>
					</fo:table-cell>
					<fo:table-cell padding-left="1mm" padding-right="1mm">
					<fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="center" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
						&#160;
					</fo:block>
					</fo:table-cell>
					<fo:table-cell>
					<fo:block margin-left="-1in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="left">
						PRINTED NAME*
					</fo:block>
					</fo:table-cell>
					<fo:table-cell padding-left="1mm" padding-right="1mm">
					<fo:block margin-left="-1in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="center" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
						&#160;
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
					<fo:table-cell number-columns-spanned="4" >
					<fo:block margin-top="2mm" margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="8.5px" text-align="left">
					*MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE <fo:inline font-weight="bold">NAMED INSURED</fo:inline> ON BEHALF OF ALL <fo:inline font-weight="bold">INSUREDS</fo:inline>.
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					</fo:table-body>
					</fo:table>
					</fo:block>
					
					<fo:block space-after="4mm">
					<fo:table>
					<fo:table-column column-width="2.1in"/>
					<fo:table-column column-width="1.9in"/>
					<fo:table-column column-width="1.1in"/>
					<fo:table-column column-width="2in"/>
					<fo:table-body>
					<fo:table-row>
					<fo:table-cell>
					<fo:block margin-left="-0.5in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="left">
						TITLE OF SIGNATORY:
					</fo:block>
					</fo:table-cell>
					<fo:table-cell padding-left="1mm" padding-right="1mm">
					<fo:block margin-left="-0.9in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="center" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
						&#160;
					</fo:block>
					</fo:table-cell>
					<fo:table-cell>
					<fo:block margin-left="-0.9in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="left">
						DATE SIGNED:
					</fo:block>
					</fo:table-cell>
					<fo:table-cell padding-left="1mm" padding-right="1mm" text-align="center">
					<fo:block margin-left="-0.9in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="center" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black">
						&#160;
					</fo:block>
					<fo:block margin-left="-0.9in" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="center"  font-style="italic">
					MM / DD / YYYY
					</fo:block>
					</fo:table-cell>
					</fo:table-row>
					</fo:table-body>
					</fo:table>
					</fo:block>
						
					<fo:block margin-top="11cm" />
					 <fo:block  start-indent="1cm" font-size="10px" color="grey" text-align="left">MO 42 007 04 19</fo:block>
           			 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block> 
         
</fo:block>

	</xsl:template>
</xsl:stylesheet>
