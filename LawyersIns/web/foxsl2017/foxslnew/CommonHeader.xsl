<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template name="CommonHeader" match="/">
		<fo:block text-align="center" font-weight="bold" font-size="11px">
			THIS ENDORSEMENT CHANGES THE POLICY. PLEASE READ IT CAREFULLY.
		</fo:block>
		
		<fo:block margin-top="3mm">
			<fo:table border="2pt solid black" text-align="center">
				<fo:table-column column-width="120mm" />
				<fo:table-column column-width="70mm" />				
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Named Insured:</fo:block>
						</fo:table-cell>
						<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Endorsement Number:</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>	
			</fo:table>
			<fo:table border="2pt solid black" text-align="center">
				<fo:table-column column-width="60mm" />
				<fo:table-column column-width="60mm" />
				<fo:table-column column-width="70mm" />				
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell border="2pt solid black" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Policy Number: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
						</fo:table-cell>
						<fo:table-cell border="2pt solid black" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Policy Period:</fo:block>
							<fo:block font-size="10px" padding-left="8pt">to</fo:block>
						</fo:table-cell>
						<fo:table-cell border="2pt solid black" padding-left="4pt">
							<fo:block font-size="10px" text-align="left">Effective Date of Endorsement:</fo:block>
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
		<fo:block font-size="7px" padding-left="20pt" text-indent="0.5cm">Insert the policy number. The remainder of the information is to be completed only when this endorsement is issued subsequent to the preparation of the policy.</fo:block>
		<fo:block margin-top="2mm">
		</fo:block>
		
	</xsl:template>
</xsl:stylesheet>




					
				    	