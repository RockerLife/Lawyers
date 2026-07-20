<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-OH">
		
			
					<fo:block text-align="center" font-size="12px">THIS
						ENDORSEMENT
						CHANGES THE POLICY, READ IT CAREFULLY.
					</fo:block>
					<fo:block margin-top="10mm" />

					<fo:block>
						<fo:table text-align="center">
							<fo:table-column column-width="90mm" />
							<fo:table-column column-width="90mm" />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding-left="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">
											To be attached to and form part of Policy No:
											<xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">
											Effective Date of Endorsement:
											<xsl:value-of
												select="response/policy_freeform_01/data/PolicyEffectiveDate" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">
											Issued to:
											<xsl:value-of select="response/policy_freeform_01/data/AccountName" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-weight="bold" font-size="10px"
											text-align="left">
											Endorsement No:
											<xsl:value-of
												select="response/endorsements_freeform_01/data/LPLAMENDOH0910" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
					</fo:block>

					<fo:block space-after=".1in" text-align-last="justify">
						<fo:leader leader-pattern="rule" />
					</fo:block>

					<fo:block margin-top="12mm" />
					<fo:block text-align="center" font-weight="bold"
						font-size="10px">OHIO AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="5mm" />

					<fo:block text-align="left" font-size="10px">1. Section III.,
						Extensions of Coverage, section D. Extended Reporting Periods, is
						amended as follows:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">-
						the words “… or for nonpayment of Deductible…” are deleted where
						they appear in the first sentence of paragraphs 1 and 2.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">-
						the words “…or Deductible…” are deleted where they appear in
						paragraph 3. (a) i)
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">2. Section IV.,
						Definitions, definition of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> is amended by deletion of (c)
						under “<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> shall not include..:” and replacing (c) with the
						following:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">(c)
						punitive or exemplary damages, awards or judgments or any amounts
						which are a multiple of compensatory damages, awards or judgments;
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">3.
						Section VI.,
						General Conditions, Paragraph D. Cancellation and
						Nonrenewal,
						subsection 2., is amended by deletion of solely the
						first sentence
						and replacing it with the following two sentences:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may only
						cancel this policy within the first 90 days of its inception date
						for non payment of premium or Deductible. If this policy has been
						in effect for 90 days or longer, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may only cancel this
						policy for non payment of premium.
					</fo:block>


					<fo:block margin-top="70mm" />

					<fo:block text-align="center" font-size="10px">All other
						terms,
						conditions and limitations of the policy remain unaltered.
					</fo:block>
					<fo:block margin-top="15mm" />
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
										<fo:block font-size="10px" text-align="center">________________________
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-size="10px" text-align="center">________________________
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-size="10px" text-align="center">Authorized
											Representative
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt" padding-top="4pt">
										<fo:block font-size="10px" text-align="center">Date</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block margin-top="10mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDOH0910" />
						
					</fo:block>

	</xsl:template>
</xsl:stylesheet>
