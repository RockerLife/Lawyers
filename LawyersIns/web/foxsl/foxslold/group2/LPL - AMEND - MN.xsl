<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-MN">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDMN0910" />
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
						font-size="10px">MINNESOTA AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						1. Section II.,
						Limits of Liability and Deductible, A., Limits of Liability, is
						amended by the addition of item 8. as follows:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						8. Pre-judgment interest is provided by this policy and is payable
						in addition to the Limit of Liability
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						2. Section III.,
						Extensions, paragraph A., Estates, Heirs, Bankruptcy Extension, is
						amended by deletion of the last sentence and replacement with the
						following sentence: “ Bankruptcy, insolvency or dissolution of the
						<fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or of the <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> estate will not relive the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> of
						any of its obligations hereunder.”
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						3. Section IV.,
						Definitions, definition of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> is amended by
						deletion of the
						first sentence and replacement with the following
						sentence: “
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> means a monetary judgment (including
						post-judgment interest
						awarded against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>), monetary
						award or monetary settlement
						negotiated with the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline>
						consent.”
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						4. This policy may
						not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except
						for non payment of premium by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>. However, VI.,
						General Conditions, D. Cancellation and Nonrenewal, paragraph 2.
						is amended by deletion of the second and third and fourth
						sentences and replacing said sentences with the following: “ This
						policy may be cancelled by or on behalf of the Insurer by
						delivering or mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> , at the address shown
						in Item 1. of the Declarations, written notice of cancellation at
						least 15 days before the effective date of cancellation. The proof
						of mailing such notice shall be sufficient proof of notice and the
						effective date of cancellation stated in such notice shall be the
						expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels the
						policy for non payment of premium the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall credit the
						<fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> the unearned portion of the premium.”
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						5. Section, VI.,
						General Conditions, D. Cancellation and Nonrenewal, paragraph #3
						is deleted and replaced with the following:

					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						3. This policy may
						be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to
						the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>,
						or by first class mailing to the<fo:inline font-weight="bold" font-style="italic"> Named Insured</fo:inline>
						at its last known
						address, and to the agent of broker of the
						<fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, written notice
						of such nonrenewal. Such notice will be
						provided at least sixty
						days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy
						Period</fo:inline>. The proof of
						mailing such notice shall be sufficient proof
						of notice. The
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> need not deliver or mail such notice if the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>
						has replaced coverage with another insurance company,
						insured
						elsewhere or has agreed not to renew this policy.
					</fo:block>

					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						6. Section VI.,
						General Conditions, A. Notice of A <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance,
						paragraph 1. is amended by deletion of the first sentence and
						replacing it with the following sentence:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						The <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> shall, as a condition precedent to the obligations of
						the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> under this policy, give written or oral
						notice of a
						<fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> as soon as practicable, but in no event later than
						sixty days
						after the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>;
						such oral or written
						notice given to an agent of the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be
						deemed notice
						given to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>.

					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						7. Section VI.
						General Conditions, A. Notice of a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or
						Circumstance, paragraph
						3., is deleted and replaced with the
						following 3.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						3. Notice of a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstances to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be
						given in writing or orally to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> or, to the agent of the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>. If to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, then as follows:
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						Crum &amp; Forster
					</fo:block>
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						Claims Department
					</fo:block>
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						305 Madison Avenue
					</fo:block>
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						Morristown, NJ 07962
					</fo:block>
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						Telephone: 973 490 6600
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDMN0910" />
						
					</fo:block>
					<fo:block break-after="page" />
					<fo:block text-align="center" font-size="12px">THIS
						ENDORSEMENT
						CHANGES THE POLICY, READ IT CAREFULLY.
					</fo:block>
					<fo:block margin-top="10mm" />
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						8. Section VI.,
						General Conditions, Paragraph F., Subrogation, is a mended by the
						addition of the following sentence:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						In a subrogation action, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not proceed against any
						<fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> if <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> were caused by the non- intentional negligent
						acts, errors or omissions of any <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> under this policy, or
						under another policy issued by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> covering the same
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and the same <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline>.
					</fo:block>
					




					<fo:block margin-top="100mm" />

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
					<fo:block margin-top="80mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDMN0910" />
						
					</fo:block>

	</xsl:template>
</xsl:stylesheet>
