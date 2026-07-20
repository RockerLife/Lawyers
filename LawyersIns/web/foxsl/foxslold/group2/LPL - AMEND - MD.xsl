<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-MD">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDMD0910" />
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
						font-size="10px">MARYLAND AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						1. Section VI.,
						General Conditions, D. Cancellation and Non-Renewal, paragraphs #2
						and #3 are deleted and replaced with the
						following paragraphs #2
						and #3:

					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm"  font-size="10px">
						2. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may only cancel this policy for nonpayment of
						premium. This policy may be canceled by or on
						behalf of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at the address
						shown in Item 1. of
						the Declarations,
						written notice of cancellation at least 10 days
						before the effective date
						of cancellation if the reason is for
						nonpayment of premium. The certificate of mailing of such notice
						shall be
						sufficient proof of notice and the
						effective date of
						cancellation stated in such notice shall become the
						expiration date
						of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. 
					</fo:block>

					<fo:block margin-top="4mm" />
					
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels this policy for nonpayment of premium, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						shall credit the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> the unearned
						portion of the premium.
						Payment or tender of any unearned premium by the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not
						be a condition
						precedent to the effectiveness of cancellation, but
						such payment shall be
						made as soon as practicable.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						3. This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by mailing to the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known address,
						written notice of such
						nonrenewal at least forty five days prior to the
						expiration of the
						<fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The
						certificate of mailing such notice shall be
						sufficient proof of notice.
						Failure of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> to send notice
						of
						non renewal in conformance with the applicable Maryland
						Regulations
						will result in the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> being
						subject to penalties.
						If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> fails to comply with the above
						paragraph, this
						policy will expire at the end
						of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> if the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline> fails to pay the premium
						for the policy after a reasonable
						demand
						for the premium has been made.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						2. Section VI.,
						General Conditions, paragraph C. Action against
						the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, is
						amended by addition of the following sentence:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						A civil action at law shall be filed within three years from the
						date it accrues unless another provision of the
						Code provides a different period of time within which an action shall
						be commenced.
					</fo:block>
					
					<fo:block margin-top="40mm" />

					<fo:block text-align="center" font-size="10px">All other
						terms,
						conditions and limitations of the policy remain unaltered.
					</fo:block>
					<fo:block margin-top="10mm" />
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
					<fo:block margin-top="15mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDMD0910" />
						
					</fo:block>


	</xsl:template>
</xsl:stylesheet>
