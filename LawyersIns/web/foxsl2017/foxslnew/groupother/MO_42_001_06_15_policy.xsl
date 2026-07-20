<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="ND-Defense-Inside">
		
					<fo:block text-align="left">				  	
				  	<fo:external-graphic src="../LawyersIns/image/cf.png" content-height="6em" content-width="20em"/>           	
				  </fo:block>
				  <fo:block margin-top="10mm"></fo:block>
					<fo:block text-align="center" font-weight="bold"
						font-size="16px">
						DEFENSE EXPENSES WITHIN LIMITS OF LIABILITY
					</fo:block>
					<fo:block text-align="center" font-weight="bold"
						font-size="16px">

						DISCLOSURE ACKNOWLEDGMENT
					</fo:block>
					<fo:block margin-top="10mm" />




					<fo:block margin-top="12mm" />
					<fo:block text-align="left" font-size="11px">
						The undersigned acknowledges that the subject policy has limits
						of
						liability which may be reduced or completely eliminated by
						payments for legal defense costs and claims expenses.
						<fo:block margin-top="8mm" />
						This acknowledgment shall remain in effect for the term of the
						policy and
						for each renewal, reinstatement, substitute, modified,
						replacement or
						amended policy.

					</fo:block>
					<fo:block margin-top="4mm" />


					<fo:block margin-top="25mm" />
					<fo:block>

						<fo:table text-align="center">
							<fo:table-column column-width="80mm" />
							<fo:table-column column-width="80mm" />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding-left="4pt">
										<fo:block font-size="10px" text-align="center">
											<xsl:value-of select="response/freeform/data/authorized_signature" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt">
										<fo:block font-size="10px" text-align="center">
											<xsl:value-of select="response/freeform/data/date" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-size="10px" text-align="center">___________________________
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-size="10px" text-align="center">___________________________
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-size="10px" text-align="center">
											<fo:inline font-weight="bold">
												Signature of
												Applicant/Named
												Insured
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-size="10px" text-align="center">
											<fo:inline font-weight="bold">
												Date Signed
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block margin-top="90mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						MO 42 001 06 15
					</fo:block>


	</xsl:template>
</xsl:stylesheet>
