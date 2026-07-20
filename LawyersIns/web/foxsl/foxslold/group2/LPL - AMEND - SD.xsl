<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-SD">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDSD0910" />
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
						font-size="10px">SOUTH DAKOTA AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="5mm" />

					<fo:block text-align="left" font-size="10px">1. Section III.,
						EXTENSIONS, D. Extended Reporting Period Extensions, 3.,<fo:inline font-weight="bold" font-style="italic"> Non
						–Practicing Insured</fo:inline> Extended Reporting Period is amended by
						replacing the parenthetical words in the last sentence to read as
						follows: “ (200% of the annual premium in the case where all
						<fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> are <fo:inline font-weight="bold" font-style="italic">Non-Practicing Insureds</fo:inline>)”.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						2. Section VI.
						GENERAL CONDITIONS, D. Cancellation and Nonrenewal,
						paragraph 2.,
						is amended by deletion of the phrase
						“… at least 10 days before the
						effective date of cancellation…”
						where it appears in the second
						sentence, and replacing it with the
						phrase “… at least 20 days
						before the effective date of
						cancellation…”.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						3. Section VI.
						GENERAL CONDITIONS, D. Cancellation and Nonrenewal, paragraph 2.,
						is amended by deletion of the last two sentences and replacing
						them with the following two sentences:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">
						If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels this policy for nonpayment of premium or
						Deductible, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall credit the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> the
						pro-rata, unearned portion of the premium. Payment or tender of
						any unearned premium by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not be a condition
						precedent to the effectiveness of cancellation, but such payment
						shall be made within twenty days of the effective date of
						cancellation.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						4. This policy may
						not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except
						for non payment of premium or deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.
						However, VI., General Conditions, D. Cancellation and Nonrenewal,
						paragraph #3 is deleted and replaced with the following:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">
						3. This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to
						the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last
						known address written notice of such nonrenewal at least sixty
						days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> or the
						anniversary date if this is a continuous policy. The proof of
						mailing of such notice shall be sufficient proof of such notice.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						5. Section VI.,
						GENERAL CONDITIONS, L., Changes, is deleted and replaced with the
						following:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">
						L. Notices of changes given by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> to its agent,
						absent of fraud by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or its agent, shall be
						binding upon the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.
					</fo:block>

					<fo:block margin-top="10mm" />

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
					<fo:block margin-top="50mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDSD0910" />
					</fo:block>

	</xsl:template>
</xsl:stylesheet>
