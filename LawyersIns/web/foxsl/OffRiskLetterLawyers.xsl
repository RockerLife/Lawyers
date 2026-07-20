<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">


	<xsl:template match="/">

		<fo:root>

			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait"
					page-width="21cm" page-height="29.7cm" margin-top="0.9cm"
					margin-bottom="0.7in" margin-left="0.5in" margin-right="0.5in">
					<fo:region-body />
					<fo:region-before extent="3.8in" />
					<fo:region-after />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">

					<fo:block text-align="center">
						<fo:external-graphic
							
							content-height="6em" content-width="20em" />
					</fo:block>

					<fo:block>
						<fo:table>
							<fo:table-column column-width="130mm" />
							<fo:table-column column-width="100mm" />
							
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="8px" font-family="Arial">
										<fo:external-graphic src="../LawyersIns/image/NewLawyersLogo.png"
							content-width="20em" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
									<fo:table>
							<fo:table-column column-width="100mm" />
									<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="8px" font-family="Arial">4200
											Commerce
											Court, Ste 102
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="8px" font-family="Arial">Lisle,
											IL 60532
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="8px" font-family="Arial">Phone
											1-877-569-4111
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-size="8px" font-family="Arial">Fax
											(630)
											799-1796
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						</fo:table-cell>
						</fo:table-row>
							</fo:table-body>
						</fo:table>
						<fo:external-graphic src="../LawyersIns/image/header_bottom.png"
							content-width="44em" />
					</fo:block>
					<fo:block font-size="10px" color="grey">
						<xsl:value-of select="response/policy_freeform_01/data/PresentDate" />
					</fo:block>
					
					<fo:block margin-top="10mm" font-size="12px" font-family="Arial">
						Re: Lawyers Professional Liability
					</fo:block>
					<fo:block font-size="12px" font-family="Arial">
						Policy Number:
						<xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" />
					</fo:block>
					<fo:block font-size="12px" font-family="Arial">
						Policy term:
						<xsl:value-of select="response/policy_freeform_01/data/PolicyTerm" /></fo:block>
					
					<fo:block margin-top="5mm" font-size="12px" font-family="Arial">
						<xsl:value-of select="response/policy_freeform_01/data/AccountName" />
					</fo:block>
					<!-- <fo:block font-size="12px" font-family="Arial">
						<xsl:value-of select="response/policy_freeform_01/data/ContactPerson" />
					</fo:block> -->
					<fo:block font-size="12px" font-family="Arial">
						<xsl:value-of select="response/policy_freeform_01/data/Address" />
						<!-- <xsl:value-of select="response/policy_freeform_01/data/Address2" /> -->
					</fo:block>
					<fo:block font-size="12px" font-family="Arial">
						<xsl:value-of select="response/policy_freeform_01/data/City" /> <xsl:value-of select="response/policy_freeform_01/data/StateDesc" /> <xsl:value-of select="response/policy_freeform_01/data/Zip" />
					</fo:block>
					
					<fo:block margin-top="10mm" font-size="12px" font-family="Arial">
						Dear <xsl:value-of select="response/policy_freeform_01/data/ContactPerson" />
					</fo:block>
					<fo:block margin-top="5mm" font-size="12px" font-family="Arial">
						We have not received your request to renew the captioned policy.
						This is to inform you that your Professional Liability coverage
						will lapse on <xsl:value-of select="response/policy_freeform_01/data/PolicyExpirationDate" />
					</fo:block>

					<fo:block margin-top="5mm" font-size="12px" font-family="Arial">
						If you have not replaced this coverage, please contact us upon
						receipt of this letter to discuss extended reporting period
						options under your expiring policy.
					</fo:block>

					<fo:block margin-top="5mm" font-size="12px" font-family="Arial">
						In order to renew your policy please contact the Protexure Renewal
						team immediately at 877-569-4111.
					</fo:block>

					<fo:block margin-top="10mm" font-size="12px" font-family="Arial">
						Sincerely,
					</fo:block>
					<fo:block font-size="12px" font-family="Arial">
						The Protexure
						Renewal Team
					</fo:block>
					<fo:block font-size="12px" font-family="Arial">
						877-569-4111
					</fo:block>

					<!-- <fo:block margin-top="8cm" font-size="8px" color="grey"
						font-family="Arial">
						Protexure. does business as
						AmerInst
						Professional Insurance Services, Inc. in California,
						Florida,
						Indiana, New Jersey and Tennessee, and as AmerInst
						Professional
						Liability Services Ltd. Inc. in Alabama.
					</fo:block> -->


				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>
</xsl:stylesheet>	
				