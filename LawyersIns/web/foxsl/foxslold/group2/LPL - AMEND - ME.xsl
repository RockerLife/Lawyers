<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-ME">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDME0910" />
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
						font-size="10px">MAINE AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						1. This policy may
						not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except
						for non payment of premium or deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.
						However, VI., General Conditions, D. Cancellation and Nonrenewal,
						paragraph #3 is deleted and replaced with the following:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						3. This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to
						the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or by mailing to the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last
						known address, written notice of such
						nonrenewal at least thirty
						three days prior to the
						expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The proof
						of mailing such notice shall be
						conclusive proof of receipt of
						notice on the
						third calendar day after mailing.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						2. Section IV.,
						Defintions, definition of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> is deleted and replaced with the
						following:
					</fo:block>
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> means any monetary judgment (including pre-judgment
						interest awarded against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, monetary
						award or monetary
						settlement negotiated with the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> written
						consent. If the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> makes an offer to pay the
						applicable Limit of Liability, it
						will not pay any pre-judgment interest
						based upon the period of
						time after such offer is
						made.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						3. Section III.,
						Extensions of Coverage, is amended by the addition of the
						following paragraph K.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						K. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be obligated to pay any post judgment
						interest awarded the against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> in any settlement
						negotiated with the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> consent. Such post judgment interest
						shall not be deemed to be part of or a reduction
						of the Limit of Liability under this policy, but rather, shall be
						paid by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> in addition to the Limit of Liability.
					</fo:block>


					





					<fo:block margin-top="70mm" />

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
							select="response/policyformfooter_freeform_01/data/LPLAMENDME0910" />
					</fo:block>

	</xsl:template>
</xsl:stylesheet>
