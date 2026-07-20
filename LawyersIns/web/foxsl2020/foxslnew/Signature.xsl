<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="Signature">
		

					<fo:block margin-top="10mm" />

					<fo:block margin-top="10mm" />
					<fo:block font-weight="bold" font-size="12px" text-align="center">
						United States Fire Insurance Company
					</fo:block>
					<fo:block font-weight="bold" font-size="12px" text-align="center">
						<fo:inline font-weight="bold">A Delaware
							Corporation
						</fo:inline>
					</fo:block>
					<fo:block font-weight="bold" font-size="12px" text-align="center">Home
						Office: Wilmington, DE</fo:block>
					<fo:block margin-top="5mm" />
					<fo:block font-size="10px" text-align="center">(A Capital
						Stock
						Company)
					</fo:block>
					<fo:block margin-top="15cm" />
					<fo:table border="0" text-align="center">
						<fo:table-column column-width="100mm" />
						<fo:table-column column-width="100mm" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block font-size="10px" text-align="center">SIGNATURE
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block font-size="10px" text-align="center">SIGNATURE
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block text-align="center">
										<fo:external-graphic  height=".60in"  content-width=".80in"  src="../LawyersIns/image/sign1.png" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block text-align="center">
										<fo:external-graphic height=".60in"  content-width=".80in"  src="../LawyersIns/image/sign2.png" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block font-size="10px" text-align="center">Marc J. Adee
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block font-size="10px" text-align="center">James Kraus
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block font-size="10px" text-align="center">Chairman and CEO
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0" padding-left="4pt">
									<fo:block font-size="10px" text-align="center">Secretary
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>

					<fo:block margin-top="40mm" font-size="10px" color="grey" text-align="left">
						MI 07 001 01 15
					</fo:block>

				

	</xsl:template>
</xsl:stylesheet>
