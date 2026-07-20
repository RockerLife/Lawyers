<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-LA">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDLA1010" />
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
						font-size="10px">LOUISIANA AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="10mm" />


					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						(1) Section III.,
						Extensions, B., Spousal And Domestic Partner Extension, is deleted
						and replaced with the following substitute B.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						B. Spousal Extension
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						If a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is made
						against the lawful spouse of an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> which
						includes a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> for
						a negligent act, error or omission made
						against an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, then
						such <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> shall be deemed a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> made
						against such <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>,
						provided his or her lawful spouse accepts
						the same legal counsel as
						the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and that such <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is made
						solely by reason of such
						lawful spouse’s status as such. This
						extension, however, shall not
						apply to any <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> alleging any
						negligent act, error or omission
						committed or alleged to have been
						committed by the lawful spouse or
						lawful domestic partner of an
						<fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						(2) Section III.,
						Extensions of Coverage, E., Deductible Credit for Mediation
						Extension, is amended by the addition of the following sentence:
					</fo:block>

					<fo:block margin-top="2mm" />
					<fo:block text-align="left" font-size="10px" start-indent="2cm">
						Such mediation is voluntary and non binding upon the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						(3) Section VI.,
						General Conditions, Paragraph C., Action Against
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, is
						amended by deletion of its paragraph 2 and replacing
						it with the
						following paragraph 2.:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						2. A person or organization may bring suit against the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						including, but not limited to a suit to recover on an
						“agreed
						settlement” or on a final judgment against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> ; but the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will not be liable for <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> that
						are not payable under
						the terms of this policy or which are in excess
						of the Limit of
						Liability. For the purpose of this
						paragraph, an “agreed
						settlement” means a settlement and release of
						liability signed by
						the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>
						and the claimant or its legal
						representative

					</fo:block>
					
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						(4) Section VI.,
						General Conditions, paragraph D. Cancellation and
						Non Renewal is
						amended as follows:
					</fo:block>

					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						- Item 1 is amended
						by deletion of the last sentence and
						replacement with the
						following sentence:
					</fo:block>

					<fo:block text-align="left" start-indent="4cm" font-size="10px">
						If the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall cancel this policy, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall
						return the portion of unearned premium on
						a pro rata basis.
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						- the following, new Item, 4. is added:
					</fo:block>
					<fo:block text-align="left" start-indent="4cm" font-size="10px">
						4. In the event a
						Deductible payment is advanced by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> shall
						reimburse the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> in
						accordance with Paragraph B. Deductible, of Section II, Limits of Liability
						and Deductible. If the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> fails to comply with this policy
						provision, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall have the right to cancel the policy
						owing to a breach of policy provisions and Notice of Cancelation
						shall be delivered to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or mailed to the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline> at the address shown in Item1. of the Declarations written
						notice of such cancellation at least sixty days before the
						effective date of cancellation.

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
							select="response/policyformfooter_freeform_01/data/LPLAMENDLA1010" />
					</fo:block>
	</xsl:template>
</xsl:stylesheet>
