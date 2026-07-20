<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="AR-DWL-Notice" >
	<fo:block text-align="left"  space-after="1.5cm">				  	
						<fo:external-graphic src="../LawyersIns/image/CFLogo.png"/>           	
					</fo:block>
	
		<fo:block text-align="center" font-weight="bold" font-size="16px">
							ARKANSAS NOTICE
							</fo:block>
		<fo:block text-align="center" font-weight="bold" font-size="16px" space-after="1cm">
				DEFENSE EXPENSES WITHIN THE LIMIT OF LIABILITY

		</fo:block>
		
		<fo:block margin-top="3mm">
			<fo:table border="2pt solid black" text-align="center">
				<fo:table-column column-width="190mm" />
				<!-- <fo:table-column column-width="70mm" />			 -->	
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Named Insured:</fo:block>
						</fo:table-cell>
						<!-- <fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Endorsement Number:</fo:block>
						</fo:table-cell> -->
					</fo:table-row>
				</fo:table-body>	
			</fo:table>
			<fo:table border="2pt solid black" text-align="center">
				<fo:table-column column-width="120mm" />
				<!-- <fo:table-column column-width="60mm" /> -->
				<fo:table-column column-width="70mm" />				
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell border="2pt solid black" padding-left="4pt" 	padding-bottom="4mm">
							<fo:block font-size="10px" text-align="left">Policy Number: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
						</fo:table-cell>
						<!-- <fo:table-cell border="2pt solid black" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Policy Period:</fo:block>
							<fo:block font-size="10px" padding-left="8pt">to</fo:block>
						</fo:table-cell> -->
						<fo:table-cell border="2pt solid black" padding-left="4pt" 	padding-bottom="4mm">
							<fo:block font-size="10px" text-align="left">Effective Date:</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
					
			<fo:table border="2pt solid black" text-align="center">
				<fo:table-column column-width="190mm" />
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Insurer:</fo:block>
						</fo:table-cell>					
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
		<fo:block margin-top="5mm">
		</fo:block>
		<fo:block margin-top="5mm"></fo:block>
		<fo:block  font-size="11px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">The undersigned acknowledges that the subject policy has limits of liability which may be reduced or completely eliminated by payments for legal defense costs and claims expenses.</fo:block>
		
		<fo:block font-size="11px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" padding-top="140px" position="absolute">
            <fo:table border="0.2pt solid white">
              <fo:table-column column-width="1.8in"/>
              <fo:table-column column-width="2.0in"/>
              <fo:table-column column-width="1.7in"/>
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
                    <fo:block font-size="10px" font-family="Arial" text-align="left">
                      &#160;
                    </fo:block>
                    
                  </fo:table-cell>
                  
                </fo:table-row>
                
                <fo:table-row>
                  <fo:table-cell display-align="after" number-columns-spanned="4">
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="5px" margin-bottom="10px">
                      *MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE <fo:inline font-weight="bold" >NAMED INSURED</fo:inline> ON BEHALF OF ALL <fo:inline font-weight="bold" >INSUREDS</fo:inline>.
                    </fo:block>
                  </fo:table-cell>           
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after" padding-bottom="5px">
                    <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify" space-after="4mm" margin-top="5px" margin-bottom="10px">
                    
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
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell  display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                      &#160;
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="after">
                    <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
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
          <fo:block margin-top="8cm"></fo:block>
         <fo:block  font-size="10px" color="grey" text-align="left">MO 42 003 04 17</fo:block> 
		 <fo:block page-break-before="auto"></fo:block>		 
		
	</xsl:template>
</xsl:stylesheet>