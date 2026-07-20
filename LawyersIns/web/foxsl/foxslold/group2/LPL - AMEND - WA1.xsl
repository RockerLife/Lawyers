<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-WA1">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDWA0910" />
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
						font-size="10px">WASHINGTON AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="10mm" />


					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						1. This policy may
						not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except
						for non payment of premium or deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.
						However, VI., General Conditions, D. Cancellation and Nonrenewal,
						paragraph #2 is amended to state that such notice shall also be
						sent to any other person shown by the policy to have an interest
						in any loss which may occur under the policy; and paragraph #3 is
						deleted and replaced with the following:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						3. This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by
						delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline> at its last known address, to the <fo:inline font-weight="bold" font-style="italic">Insured’s</fo:inline> agent or
						broker and to any other person shown in the policy to have an
						interest in any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> which may be made under this policy at their
						last known mailing address, written notice of such nonrenewal at
						least forty five days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy
						Period</fo:inline>., or prior to the anniversary date of the policy if the
						policy has been written for a term of more than one year. The
						proof of mailing such notice shall be sufficient proof of notice.
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will otherwise renew this policy unless:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						- the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> fails to pay the renewal premium
						after the Insurer has made a renewal offer including a statement
						of the renewal premium to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> , or its agent or
						broker, at least 20 days before the expiration date of the policy;
						or,
					</fo:block>
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						- other coverage acceptable to the Named Insured has
						been procured
						prior to the expiration date of this policy; or,
					</fo:block>
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						- the this policy clearly states that it is non
						renewable, and for
						a specific line, subclassification, or type of
						coverage that is
						not offered on a renewable basis.
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px" >
						2. Section II. Limits of Liability and Deductible, A,. Limit of
						Liability, sub paragraph 7. is deleted in its entirety and
						replaced with the following:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						7. After the
						applicable Limit of Liability has been
						exhausted by payment of
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and/or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> or by
						tendering the remaining
						Limit of Liability into court, the
						<fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> duty to defend the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall continue until a
						judgment or settlement has
						been made with the injured party, or
						until the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> obtains
						written permission of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>
						to cease defending the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.
					</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						3. Section VI.,
						General Conditions, B., Defense and Settlement, paragraph 1. is
						amended by the deletion of the following sentence in its entirety
						where it appears in paragraph 1.:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						“The <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> right and duty to defend any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> and
						pay <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> shall terminate upon the exhaustion of
						the Limit of Liability, where upon the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall have no further
						obligation or liability to defend the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or to pay
						<fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>, judgments or settlements.”

					</fo:block>






					<fo:block margin-top="20mm" />

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
					<fo:block margin-top="10mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDWA0910" />

					</fo:block>

	</xsl:template>
</xsl:stylesheet>
