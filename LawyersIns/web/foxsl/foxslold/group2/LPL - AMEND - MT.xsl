<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-MT">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDMT0313" />
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
						font-size="10px">MONTANA AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						1. This policy may
						not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except
						for non payment of premium or deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.
						However, VI. General Conditions, D. Cancellation and Nonrenewal,
						paragraph #3 is deleted and replaced with the following:
					</fo:block>

					<fo:block margin-top="2mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						3. This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to
						the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or by mailing to the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last
						known address written notice of such non
						renewal at least forty
						five days prior to the
						expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The proof
						of mailing such notice shall be
						sufficient proof of notice. The
						Insurer
						is not required to deliver of mail such notice if the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline>
						has purchased coverage elsewhere, accepted
						replacement
						coverage, requested or agreed to non-renewal or if this policy
						is
						expressly designated as non-renewable.
					</fo:block>

					<fo:block margin-top="2mm" />
					<fo:block text-align="left" font-size="10px">
						2. Section VI.
						General Conditions, is amended by the addition of the following
						paragraph Q:
					</fo:block>

					<fo:block margin-top="2mm" />
					<fo:block font-weight="bold" text-align="left" start-indent="2cm" font-size="10px">
						Q. Conformity with Montana Statutes
					</fo:block>


					<fo:block margin-top="2mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						The provisions of this policy conform to the minimum requirements
						of Montana law and control over any
						conflicting statutes of any
						state in which the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> resides on or after
						the effective date
						of this policy.
					</fo:block>


					<fo:block margin-top="2mm" />
					<fo:block text-align="left" font-size="10px">
						3. It is agreed that
						notwithstanding anything contained herein to the contrary, the
						coverage provisions of this policy, inclusive of
						coverage
						provisions afforded within any of the Extended Reporting Periods,
						shall not be subject to any restriction in respect of
						fully earned
						premium charges. Wherever such provisions appear, The
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>
						may request cancellation and the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will then cancel the
						applicable coverage and return 90% of the
						unearned premium.

					</fo:block>

					<fo:block margin-top="2mm" />
					<fo:block text-align="left" font-size="10px">
						4. Section II. of
						the Policy, Limits of Liability and Deductible,
						paragraph B.
						Deductible, is deleted and replaced with the
						following:
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						B. Deductible
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be liable for amounts payable under this policy
						for <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> which are in excess of the
						Deductible amount shown in Item 4 of the Declarations. The
						Deductible amount shall apply separately to each and every <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>,
						unless amended by specific endorsement of this policy, and shall
						be borne by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and shall remain uninsured. In the event
						of <fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline> that are deemed a single <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> pursuant to
						Section II C. below, a single Deductible amount will apply.
					</fo:block>


					<fo:block margin-top="2mm" />
					<fo:block text-align="left" font-size="10px">
						5.If: &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;(a) the Limit
						of Liability shown in Item 3 of the Declarations as to “each
						<fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>” is less than $1,000,000,
					</fo:block>
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						and
					</fo:block>



					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(b) the policy is not endorsed so that <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> are paid
						in addition to the Limit of Liability,
					</fo:block>
					<fo:block text-align="left" font-size="10px">
						then
					</fo:block>

					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(a) <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> shall not begin to erode or to reduce the
						Limit of Liability to pay <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> until
						<fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> incurred total $50,000 for all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> for the
						<fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>;
					</fo:block>
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(b) the portion of the Limit of Liability that remains available
						to pay <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> may be reduced only by the portion of
						incurred
						<fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> greater than $50,000;
					</fo:block>
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(c) the portion of the Limit of Liability available to pay <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline>
						shall not be reduced to an amount less than $50,000
						regardless of the amount of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> incurred.

					</fo:block>

					


					<fo:block margin-top="15mm" />

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
					<fo:block margin-top="5mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDMT0313" />
					</fo:block>

	</xsl:template>
</xsl:stylesheet>
