<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
  
  <xsl:template match="/" name="ACORD-25">
   <fo:root>
      <fo:layout-master-set>
        <fo:simple-page-master master-name="A4-portrait" page-width="21cm" 
                               page-height="29.7cm" margin-top="0.1in" margin-bottom="0.5in" margin-left="0.3in" margin-right="0.3in">
          <fo:region-body margin-top="0.3in"/>
          <fo:region-before extent="1.0in"/>
          <fo:region-after />
        </fo:simple-page-master>
      </fo:layout-master-set>

      <fo:page-sequence master-reference="A4-portrait" >
       <fo:static-content flow-name="xsl-region-before">
	     
		
          <fo:block margin-left="340px" color="grey">
		</fo:block>
        </fo:static-content>
		<fo:flow flow-name="xsl-region-body" >
          <fo:block>
          <fo:table>
          	<fo:table-column column-width="30mm" />
		  	<fo:table-column column-width="60mm" />
		  	<fo:table-column column-width="60mm" />	
		  	<fo:table-column column-width="40mm" />
		  	<fo:table-body>
			<fo:table-row>
			<fo:table-cell padding-left="2mm">
			<fo:block> 
			 <xsl:choose>
				<xsl:when test="response/environmentproduction = 'Y'">
					<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/acord.png" content-height="3em" content-width="5em" />
				</xsl:when>
				<xsl:otherwise>
					<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/acord.png" content-height="3em" content-width="5em" />
				</xsl:otherwise>
			</xsl:choose>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell number-columns-spanned="2" text-align="center" padding-top="6mm">
			<fo:block font-size="13px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">
			CERTIFICATE OF LIABILITY INSURANCE
			</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell border="1pt solid" text-align="center">
			<fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			DATE (MM/DD/YYYY)
			</fo:block>
			<fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			&#160;
			</fo:block>
			<fo:block font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="7px" font-weight="bold">
			<xsl:value-of select="response/CreationDate"/>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid"  number-columns-spanned="4" padding-left="3mm">
			<fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">
			THIS CERTIFICATE IS ISSUED AS A MATTER OF INFORMATION ONLY AND CONFERS NO RIGHTS UPON THE 
			CERTIFICATE HOLDER. THIS CERTIFICATE DOES NOT AFFIRMATIVELY OR NEGATIVELY AMEND, EXTEND OR ALTER 
			THE COVERAGE AFFORDED BY THE POLICIES BELOW.  THIS CERTIFICATE OF INSURANCE DOES NOT CONSTITUTE A 
			CONTRACT BETWEEN THE ISSUING INSURER(S), AUTHORIZED REPRESENTATIVE OR PRODUCER, AND THE CERTIFICATE 
			HOLDER.
			</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" number-columns-spanned="4" padding-left="3mm">
			<fo:block font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">
			IMPORTANT:  If the certificate holder is an ADDITIONAL INSURED, the 
			policy(ies) must have ADDITIONAL INSURED provisions or be endorsed. If 
			SUBROGATION IS WAIVED, subject to the terms and conditions of the policy, certain 
			policies may require an endorsement.  A statement on this certificate does not confer 
			rights to the certificate holder in lieu of such endorsement(s).
			</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" number-columns-spanned="2" padding-left="2mm">
			<fo:block   space-after="2mm" font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			PRODUCER
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="1mm">
			Protexure
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="1mm">
			4200 Commerce Ct. 
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			Suite 102
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			Lisle, IL 60532
			</fo:block>
			</fo:table-cell>
			<fo:table-cell>
			<fo:table border="1pt solid">
			<fo:table-column column-width="60mm" />
			<fo:table-column column-width="20mm" />
			<fo:table-column column-width="19.7mm" />
			<fo:table-body>
			<fo:table-row>
			<fo:table-cell border="1pt solid" number-columns-spanned="3" padding-left="2mm">
			<fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			CONTACT
			</fo:block>
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">NAME:</fo:inline>&#160; &#160;&#160; &#160; <fo:inline font-size="8px">Kyle Nieman</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" padding-left="2mm">
			<fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			PHONE
			</fo:block>
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">(A/C, No, Ext):</fo:inline>&#160; <fo:inline font-size="8px">630-799-2000</fo:inline> ext <fo:inline font-size="8px">7011</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell border="1pt solid" number-columns-spanned="2" padding-left="2mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">FAX</fo:inline>
			</fo:block>
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">(A/C, No):</fo:inline>&#160; <fo:inline font-size="8px">(440) 333-3214</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" number-columns-spanned="3" padding-left="2mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">EMAIL</fo:inline>
			</fo:block>
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">ADDRESS:</fo:inline>&#160; <fo:inline font-size="8px">kneiman@protexure.com</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell text-align="center" number-columns-spanned="2" padding-bottom="1mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">INSURER(S) AFFORDING COVERAGE</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell padding-bottom="1mm" border="1pt solid" text-align="center">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">NAIC #</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
             <fo:table-cell border="1pt solid" padding-top="1mm" number-columns-spanned="2" padding-bottom="1mm" padding-left="2mm">
             <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
             <fo:inline font-weight="bold">INSURER A :</fo:inline>&#160; <fo:inline font-size="8px">ISMIE Mutual Insurance Company</fo:inline>
             </fo:block>
             </fo:table-cell>
             <fo:table-cell padding-top="1mm" padding-bottom="2mm" border="1pt solid" text-align="center">
             <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
             <fo:inline font-weight="bold">32921</fo:inline>
             </fo:block>
             </fo:table-cell>
             </fo:table-row>
			</fo:table-body>
			</fo:table>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" number-columns-spanned="2" padding-left="2mm">
			<fo:block  font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			INSURED
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			&#160;<xsl:value-of select="response/AccountName"/>
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			&#160;<xsl:value-of select="response/Address1"/>&#160;
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			&#160;<xsl:value-of select="response/Address2"/>
			</fo:block>
			<fo:block  font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			&#160;<xsl:value-of select="response/City"/>,&#160;&#160;<xsl:value-of select="response/StateCode"/>&#160;&#160;
			<xsl:value-of select="response/Zip"/>&#160;
			<xsl:choose>
						<xsl:when test="response/Zip4 != '' ">
							-&#160;<xsl:value-of select="response/Zip4"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<fo:block>&#160;</fo:block>
						</xsl:otherwise>
					</xsl:choose>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell>
			<fo:table border="1pt solid">
			<fo:table-column column-width="60mm" />
			<fo:table-column column-width="20mm" />
			<fo:table-column column-width="19.7mm" />
			<fo:table-body>
			<fo:table-row>
			<fo:table-cell border="1pt solid" number-columns-spanned="2" padding-bottom="1mm" padding-left="2mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">INSURER B :</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell padding-bottom="2mm" border="1pt solid" text-align="center">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold"> &#160;</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" padding-left="2mm" number-columns-spanned="2" padding-bottom="1mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">INSURER C :</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell padding-bottom="2mm" border="1pt solid" text-align="center">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold"> &#160;</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" padding-left="2mm" number-columns-spanned="2" padding-bottom="1mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">INSURER D :</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell padding-bottom="2mm" border="1pt solid" text-align="center">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold"> &#160;</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" padding-left="2mm" number-columns-spanned="2" padding-bottom="1mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">INSURER E :</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell padding-bottom="2mm" border="1pt solid" text-align="center">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold"> &#160;</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			<fo:table-row>
			<fo:table-cell border="1pt solid" number-columns-spanned="2" padding-bottom="1mm" padding-left="2mm">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold">INSURER F :</fo:inline>
			</fo:block>
			</fo:table-cell>
			<fo:table-cell padding-bottom="2mm" border="1pt solid" text-align="center">
			<fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
			<fo:inline font-weight="bold"> &#160;</fo:inline>
			</fo:block>
			</fo:table-cell>
			</fo:table-row>
			</fo:table-body>
			</fo:table>
			</fo:table-cell>
			</fo:table-row>
			</fo:table-body>
          </fo:table>
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          COVERAGES:&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
          CERTIFICATE NUMBER: &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;
          REVISION NUMBER:
          </fo:block>
          <fo:table>
          <fo:table-column column-width="8mm"/>
          <fo:table-column column-width="46mm"/>
          <fo:table-column column-width="8mm"/>
          <fo:table-column column-width="8mm"/>
          <fo:table-column column-width="26mm"/>
          <fo:table-column column-width="21mm"/>
          <fo:table-column column-width="21mm"/>
          <fo:table-column column-width="53mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid" number-columns-spanned="8" padding-left="3mm">
          <fo:block font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          THIS IS TO CERTIFY THAT THE POLICIES OF INSURANCE LISTED BELOW HAVE BEEN ISSUED TO THE INSURED NAMED 
          ABOVE FOR THE POLICY PERIOD INDICATED. NOTWITHSTANDING ANY REQUIREMENT, TERM OR 
          CONDITION OF ANY CONTRACT OR OTHER DOCUMENT WITH RESPECT TO WHICH THIS CERTIFICATE MAY BE 
          ISSUED OR MAY PERTAIN, THE INSURANCE AFFORDED BY THE POLICIES DESCRIBED HEREIN IS SUBJECT TO 
          ALL THE TERMS, EXCLUSIONS AND CONDITIONS OF SUCH POLICIES. LIMITS SHOWN MAY HAVE BEEN REDUCED BY 
          PAID CLAIMS.
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" text-align="center">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          INSR
          </fo:block>
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          LTR
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center" padding-top="2mm">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          TYPE OF INSURANCE
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          ADDL
          </fo:block>
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          INSD
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          SUBR
          </fo:block>
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          WVD
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center" padding-top="2mm">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          POLICY NUMBER
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          POLICY EFF
          </fo:block>
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          (MM/DD/YYYY)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          POLICY EXP
          </fo:block>
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          (MM/DD/YYYY)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center" padding-top="2mm">
          <fo:block font-size="8px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          LIMITS
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" margin-bottom="-6px">
          <fo:table>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="5mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="7mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="9.5mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm" number-columns-spanned="6" padding-top="2mm">
          <fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          COMMERCIAL GENERAL LIABILITY
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm" padding-top="2mm" number-columns-spanned="3">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          CLAIMS-MADE
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm" padding-top="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          OCCUR
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-top="2mm" padding-left="1mm" number-columns-spanned="6">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          _________________________
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-top="2mm" padding-left="1mm" number-columns-spanned="6">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          _________________________
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell padding-top="2mm" padding-left="1mm" number-columns-spanned="7">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          GEN'L AGGREGATE LIMIT APPLIES PER:
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm" padding-top="2mm" number-columns-spanned="2">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          POLICY
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          PRO-
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          JECT
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm" padding-top="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          LOC
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm" padding-top="2mm" number-columns-spanned="6">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          OTHER:
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block></fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="34mm"/>
          <fo:table-column column-width="18.9mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.5mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          EACH OCCURRENCE
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.5mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
           <fo:table-cell border="1pt solid" padding-left="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          DAMAGE TO RENTED
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          PREMISES(Ea occurrence)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          MED EXP(Any one person)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          PERSONAL &#38; ADV INJURY
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          GENERAL AGGREGATE
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          PRODUCTS-COMP/OP AGG
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          &#160;
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          &#160;
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-top="2mm">
          <fo:block font-size="6px"  padding-bottom="0.5mm" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold">
          &#160; AUTOMOBILE LIABILITY
          </fo:block>
          <fo:table>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="15mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="15mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell number-columns-spanned="3" padding-top="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          ANY AUTO
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          OWNED
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          AUTOS ONLY
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          SCHEDULED
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          AUTOS
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          HIRED
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          AUTOS ONLY
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          NON-OWNED
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          AUTOS ONLY
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          &#160;
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          &#160;
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block></fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="34mm"/>
          <fo:table-column column-width="18.9mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          COMBINED SINGLE LIMIT
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          (Ea accident)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          BODILY INJURY (Per person)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          BODILY INJURY (Per accident)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" >
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          PROPERTY DAMAGE
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          (Per accident)
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="7mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="7mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="13.9mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell number-columns-spanned="3" padding-top="2mm" padding-left="1mm">
          <fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          UMBRELLA LIAB
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-top="2mm" padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          OCCUR
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell number-columns-spanned="3" padding-top="2mm" padding-left="1mm">
          <fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          EXCESS LIAB
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-top="2mm" padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          CLAIMS-MADE
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-top="2mm" padding-left="1mm" border="1pt solid">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          DED
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block> &#160;</fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-top="2mm" number-columns-spanned="3" padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"> 
          RETENTION $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block></fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="34mm"/>
          <fo:table-column column-width="18.9mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="3mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          EACH OCCURRENCE
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="3mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="3mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          AGGREGATE
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="3mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="3mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="3.5mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="36mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell padding-top="1mm" number-columns-spanned="2" padding-left="1mm">
          <fo:block font-size="6px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          WORKERS COMPENSATION
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell padding-left="1mm">
          <fo:block font-size="6px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          AND EMPLOYERS' LIABILITY
          </fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-size="6px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          Y/N
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          ANYPROPRIETOR/PARTNER/EXECUTIVE
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          OFFICER/MEMBER EXCLUDED?
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell number-columns-spanned="2" padding-left="1mm">
          <fo:block font-size="6px" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          (Mandatory in NH)
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell number-columns-spanned="2" padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          If yes, describe under
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell number-columns-spanned="2" padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          DESCRIPTION OF OPERATIONS below
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" text-align="center">
          <fo:block>&#160;</fo:block>
          <fo:block>&#160;</fo:block>
          <fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          N/A
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block></fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="34mm"/>
          <fo:table-column column-width="18.9mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="12mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-column column-width="6mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid">
          <fo:block></fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          PER
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          STATUTE
          </fo:block>
          </fo:table-cell>
           <fo:table-cell border="1pt solid">
          <fo:block></fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="1mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          OTH-
          </fo:block>
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          ER
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          E.L. EACH ACCIDENT
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          E.L. DISEASE - EA EMPLOYEE
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.2mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          E.L. DISEASE - POLICY LIMIT
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="2.4mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-top="2mm" padding-left="3mm">
          <fo:block font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          X
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-top="1mm" padding-left="1mm">
          <fo:block font-size="7px"  font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          Lawyers Professional Liability Insurance
          </fo:block>
          </fo:table-cell>
          
          <fo:table-cell border="1pt solid">
          <fo:block>
          &#160;
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:block>
          &#160;
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="1mm" padding-top="2mm">
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          <xsl:value-of select="response/PolicyNumber"/>&#160;
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="1mm" padding-top="2mm">
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          <xsl:value-of select="response/PolicyEffectivedate"/>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="1mm" padding-top="2mm">
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          <xsl:value-of select="response/PolicyExpirationDate"/>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid" padding-left="1mm" padding-top="2mm">
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          $<xsl:value-of select="response/AggregateLimit"/> per Claim/$<xsl:value-of select="response/OccuranceLimit"/> Aggregate with a 
          $<xsl:value-of select="response/OccuranceDeductible"/> Deductible
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" number-columns-spanned="8" padding-left="1mm" padding-top="1mm" padding-bottom="4mm"> 
          <fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          DESCRIPTION OF OPERATIONS/LOCATIONS/VEHICLES (Accord 101, Additional Remarks Schedule, may be attached if more space is required)
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          <fo:table>
          <fo:table-column column-width="95mm"/>
          <fo:table-column column-width="96mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell>
          <fo:block font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          CERTIFICATE HOLDER
          </fo:block>
          </fo:table-cell>
          <fo:table-cell>
          <fo:block font-weight="bold" font-size="8px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          CANCELLATION
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-top="3mm">
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          <xsl:value-of select="response/CertificateHolderName"/>
          </fo:block>
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          <xsl:value-of select="response/CertificateHolderAddress"/>
          </fo:block>
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          <xsl:value-of select="response/CertificateHolderCity"/>&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; 
          <xsl:value-of select="response/CertificateHolderState"/>&#160; &#160; &#160; &#160; <xsl:value-of select="response/CertificateHolderZip"/>
          </fo:block>
          </fo:table-cell>
          <fo:table-cell border="1pt solid">
          <fo:table>
          <fo:table-column column-width="96mm"/>
          <fo:table-body>
          <fo:table-row>
          <fo:table-cell border="1pt solid" padding-left="2mm" padding-bottom="1mm" padding-top="1mm">
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          SHOULD ANY OF THE ABOVE DESCRIBED POLICIES BE CANCELLED BEFORE THE EXPIRATION DATE THEREOF, NOTICE WILL BE DELIVERED IN ACCORDANCE WITH THE POLICY PROVISIONS.
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          <fo:table-row>
          <fo:table-cell padding-left="1mm" padding-top="1mm">
          <fo:block font-weight="bold" font-size="6px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          AUTHORIZED REPRESENTATIVE *********************
          </fo:block>
          <fo:block>  &#160;    &#160; &#160; &#160; &#160; &#160; 
          
           <xsl:choose>
				<xsl:when test="response/environmentproduction = 'Y'">
				
				<xsl:choose>
					<xsl:when test="response/isShowSignature = 'Y'">
						<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/kyle.png" content-height="2em" content-width="6em" />
					</xsl:when>
					<xsl:otherwise>
						<fo:external-graphic src="" content-height="2em" content-width="6em" />
					</xsl:otherwise>
				</xsl:choose>
				
					
				</xsl:when>
				<xsl:otherwise>
					<xsl:choose>
						<xsl:when test="response/isShowSignature = 'Y'">
							<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/kyle.png" content-height="2em" content-width="6em" />
						</xsl:when>
						<xsl:otherwise>
							<fo:external-graphic src="" content-height="2em" content-width="6em" />
						</xsl:otherwise>
					</xsl:choose>
				</xsl:otherwise>
			</xsl:choose>
          </fo:block>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          </fo:table-cell>
          </fo:table-row>
          </fo:table-body>
          </fo:table>
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="right">
          © 1988-2015 ACORD CORPORATION.  All rights reserved. 
          </fo:block>
          <fo:block font-weight="bold" font-size="7px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
          ACORD 25 (2016/03)  &#160;    &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160;The ACORD name and logo are registered marks of ACORD 
          </fo:block>
          </fo:block>
          </fo:flow>
          </fo:page-sequence>
          </fo:root>
  </xsl:template>
 
</xsl:stylesheet>
