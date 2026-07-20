<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	<xsl:template match="/" name="LPLFORM129">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait"
					page-width="21cm" page-height="29.7cm" margin-top="10mm"
					margin-bottom="1.0mm" margin-left="10mm" margin-right="10mm">
					<fo:region-body margin-top="2.0mm" />
					<fo:region-before extent="1.0mm" />
					<fo:region-after />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
					<fo:block text-align="center">
						<fo:external-graphic src="../LawyersIns/image/NewLawyersLogo.PNG"
							content-height="4em" content-width="16em" />
					</fo:block>
					<fo:block margin-top="10mm" />
					<fo:block text-align="center" font-weight="bold">
						4200 Commerce Court, Suite 102
					</fo:block>
					<fo:block text-align="center" font-weight="bold">
						Lisle, IL 60532
					</fo:block>
					<fo:block margin-top="20mm" />
					<fo:block text-align="center" font-weight="bold">
						INVOICE
					</fo:block>
					<fo:block margin-top="20mm" />
					<fo:block>
						<fo:table text-align="center">
							<fo:table-column column-width="130mm" />
							<fo:table-column column-width="90mm" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding-left="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">
											
											<xsl:value-of select="response/InvoiceDataLW/data/AccountName" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">Invoice Date: <xsl:value-of select="response/InvoiceDataLW/data/InvoicedDate" /></fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left"><xsl:value-of select="response/InvoiceDataLW/data/Address1" /> </fo:block>
											<fo:block font-weight="bold" font-size="10px"
											text-align="left"> <xsl:value-of select="response/InvoiceDataLW/data/Address2" /> </fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">Invoice Number: <xsl:value-of select="response/InvoiceDataLW/data/TransactionSequence" /> </fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left"><xsl:value-of select="response/InvoiceDataLW/data/City" />, <xsl:value-of select="response/InvoiceDataLW/data/StateDesc" />, <xsl:value-of select="response/InvoiceDataLW/data/Zip" />  </fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">Amount Invoiced: <xsl:value-of select="response/InvoiceDataLW/data/InvoicedPremium" /> </fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left"></fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">Remitted Amount: _____________</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block margin-top="20mm" />
					<fo:block>
						Make checks payable to: Protexure.
					</fo:block>
					<fo:block margin-top="20mm" />
					<fo:block font-size="8px" text-align="center">
						------------------------------ Please remit this portion with your
						payment -----------------------------
					</fo:block>
					<fo:block margin-top="10mm" />
					<fo:block font-weight="bold" text-align="center">Named Insured :
						<xsl:value-of select="response/InvoiceDataLW/data/AccountName" />
					</fo:block>
					<fo:block margin-top="20mm" />
					<fo:table text-align="center">
						<fo:table-column column-width="100mm" />
						<fo:table-column column-width="100mm" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">
										Quote Number: 
										<xsl:value-of select="response/InvoiceDataLW/data/QuoteNumber" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">Transaction: Renewal Policy </fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell padding-left="4pt" padding-top="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">Carrier: United States Fire Insurance Company
									</fo:block>
								</fo:table-cell>
								<fo:table-cell padding-left="4pt" padding-top="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">Transaction Effective Date: <xsl:value-of select="response/InvoiceDataLW/data/TransactionEffectiveDate" /></fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell padding-left="4pt" padding-top="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">Billing Term: <xsl:value-of select="response/InvoiceDataLW/data/PolicyTerm" /></fo:block>
								</fo:table-cell>
								<fo:table-cell padding-left="4pt" padding-top="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left"></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block margin-top="10mm" />
					<fo:table text-align="center">
						<fo:table-column column-width="70mm" />
						<fo:table-column column-width="70mm" />
						<fo:table-column column-width="40mm" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="0.5pt solid black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">
										Coverage										
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt solid black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">Premium </fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt solid black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">Amount Due </fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">
										Professional Liability
										
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left"><xsl:value-of select="response/InvoiceDataLW/data/InvoicedPremium" /> </fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="right"><xsl:value-of select="response/InvoiceDataLW/data/InvoicedPremium" /> </fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt dashed black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border="0.5pt solid black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="left">&#160;&#160;</fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt solid black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="right">Total Due: </fo:block>
								</fo:table-cell>
								<fo:table-cell border="0.5pt solid black"
									padding-left="4pt">
									<fo:block font-weight="bold" font-size="10px"
										text-align="right"><xsl:value-of select="response/InvoiceDataLW/data/InvoicedPremium" /> </fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block margin-top="25mm" />
					<fo:block font-size="10px" text-indent="1.8cm" color="grey"
						text-align="center">Payment Due – <xsl:value-of select="response/InvoiceDataLW/data/CurrentDate" /> </fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>				    	