<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-CO">


		

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
												select="response/endorsements_freeform_01/data/LPLAMENDCO0315" />
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
						font-size="10px">COLORADO AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="10mm" />


					<fo:block margin-top="6mm" />

					<fo:block text-align="left" font-size="10px">
						In consideration of
						the premium charged, it is agreed that:
					</fo:block>

					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">
						A. Section
						<fo:inline font-weight="bold" font-style="italic">III. Extensions
							of Coverage
						</fo:inline>
						is amended as
						follows:

					</fo:block>


					<fo:block margin-top="8mm" />
					<fo:block start-indent="1cm" text-align="left" font-size="10px">
						1. Paragraph
						<fo:inline font-weight="bold" font-style="italic">B.
							Spousal And
							Domestic Partner Extension
						</fo:inline>
						is amended by deleting the
						phrase "lawful domestic partner" and
						replacing with the phrase
						"party to a civil union recognized under
						Colorado law".

					</fo:block>
					<fo:block margin-top="6mm" />
					<fo:block start-indent="1cm" text-align="left" font-size="10px">
						2. Paragraph
						<fo:inline font-weight="bold" font-style="italic">D.
							Extended
							Reporting Period Extensions
						</fo:inline>
						is amended as follows:
					</fo:block>
					<fo:block margin-top="6mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						a. Subparagraph
						<fo:inline font-weight="bold" font-style="italic">2.
							Optional
							Extended Reporting Period
						</fo:inline>
						is deleted in its entirety and
						replaced by the following:
					</fo:block>
					<fo:block margin-top="6mm" />

					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						Upon expiration or
						cancellation of this policy, the
						<fo:inline font-weight="bold" font-style="italic">Named Insured
						</fo:inline>
						shall have the
						right, upon payment of the additional premium
						designated in one of
						the options below for the designated length of
						time shown,
						commencing on the expiration date of the Automatic
						Extended
						Reporting Period, to report
						<fo:inline font-weight="bold" font-style="italic">Claims
						</fo:inline>
						pursuant to Paragraph
						<fo:inline font-weight="bold" font-style="italic">A. Notice
							of
							Claim or Circumstance
						</fo:inline>
						of Section
						<fo:inline font-weight="bold" font-style="italic">VI. General
							Conditions:
						</fo:inline>
					</fo:block>
					<fo:block margin-top="6mm" />

					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(a) Optional
						Extended Reporting Period of 12 months for a premium
						charge of
						100% of the annual policy premium;
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(b) Optional
						Extended Reporting Period of 36 months for a premium
						charge of
						185% of the annual policy premium.
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(c) Optional Extended Reporting Period of 60 months for a premium
						charge of 225% of the annual policy premium;
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						(d) Optional Extended Reporting Period of 72 months for a premium
						charge of 250% of the annual policy premium.
					</fo:block>






					<fo:block margin-top="6mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						Coverage under such
						extension of time to report a
						<fo:inline font-weight="bold" font-style="italic">Claim
						</fo:inline>
						(hereinafter referred to as
						the Optional Extended Reporting Period)
						shall apply solely to
						negligent acts, errors or omissions in
						rendering
						<fo:inline font-weight="bold" font-style="italic">Professional
							Services
						</fo:inline>
						committed or attempted prior to the effective date of
						nonrenewal or
						cancellation, whichever occurs first, and which are
						not otherwise
						excluded by any terms, conditions or exclusions of
						this policy.
					</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						As a condition
						precedent to the right to purchase this Optional
						Extended
						Reporting Period, the
						<fo:inline font-weight="bold" font-style="italic">Named Insured
						</fo:inline>
						shall provide the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						with
						written notice of its selection and pay the premium charge for
						the
						selected Optional Reporting Period (and any other premium due
						under the policy) in full within 60 days of the expiration date of
						the
						<fo:inline font-weight="bold" font-style="italic">Policy Period
						</fo:inline>
						.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						If the Optional
						Extended Reporting Period requested by the
						<fo:inline font-weight="bold" font-style="italic">Named Insured
						</fo:inline>
						is
						effected by the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						, the additional premium shall be fully
						earned by the Insurer and
						the Optional Extended Reporting Period
						cannot be canceled by the
						<fo:inline font-weight="bold" font-style="italic">Insureds
						</fo:inline>
						or the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						.
					</fo:block>

					<fo:block margin-top="4mm" />

					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						b. Subparagraph
						3.(a) of the
						<fo:inline font-weight="bold" font-style="italic">Non-Practicing
							Insured Extended Reporting Period
						</fo:inline>
						provision is deleted in its entirety and replaced by the
						following:
					</fo:block>

					<fo:block font-size="10px" start-indent="1cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/endorsements_freeform_01/data/LPLAMENDCO0315" />

					</fo:block>
					<fo:block color="grey" text-align="right" font-size="10px">
						Page 1
						of 2
					</fo:block>


					<fo:block break-after="page"></fo:block>


					<fo:block margin-top="4mm" />

					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						Provided no other
						Extended Reporting Period is in effect and
						provided this policy
						remains in force as to all other
						<fo:inline font-weight="bold" font-style="italic">Insureds
						</fo:inline>
						, except in the case
						where all
						<fo:inline font-weight="bold" font-style="italic">Insureds
						</fo:inline>
						are
						<fo:inline font-weight="bold" font-style="italic">Non –
							Practicing Insureds
						</fo:inline>
						, a
						<fo:inline font-weight="bold" font-style="italic">Non -
							Practicing Insured
						</fo:inline>
						shall have the right to purchase an unlimited
						extension of time to
						report
						<fo:inline font-weight="bold" font-style="italic"> Claims
						</fo:inline>
						that are first made against
						such
						<fo:inline font-weight="bold" font-style="italic">Non –
							Practicing Insured
						</fo:inline>
						and reported to the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						after
						the effective date of cessation of the practice of law by
						reason
						of negligent acts, errors or omissions

						alleged to have been
						committed subsequent to the applicable
						<fo:inline font-weight="bold" font-style="italic">Prior Acts Date
						</fo:inline>
						, but before
						the effective date of cessation of the practice of law
						and not
						otherwise excluded by the agreements, conditions and
						exclusions of
						this policy. The additional premium for this
						<fo:inline font-weight="bold" font-style="italic">Non-Practicing
							Insured
						</fo:inline>
						Extended Reporting Period shall be 125% of the
						<fo:inline font-weight="bold" font-style="italic">Non-Practicing
							Insured’s
						</fo:inline>
						proportionate share of the annual premium
						(or, 275% of the annual
						premium in the case where all
						<fo:inline font-weight="bold" font-style="italic">Insureds
						</fo:inline>
						are
						<fo:inline font-weight="bold" font-style="italic">Non-Practicing
							Insureds
						</fo:inline>
						). As a condition precedent to the right to
						purchase this
						<fo:inline font-weight="bold" font-style="italic">Non-Practicing
							Insured
						</fo:inline>
						Extended Reporting Period,
						the
						<fo:inline font-weight="bold" font-style="italic">Non-Practicing
							Insured
						</fo:inline>
						shall provide the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						with written
						notice of its election, together with payment in full
						of the
						additional premium due (and any other premium due under the
						policy) no later than 60 days following the effective date of
						cessation of the practice of law.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						B. Under Section
						<fo:inline font-weight="bold" font-style="italic">IV.
							Definitions
						</fo:inline>
						, the definition of
						<fo:inline font-weight="bold" font-style="italic">Immediate
							Family
						</fo:inline>
						is amended by
						deleting the phrase "spouse" and replacing with the
						phrase "spouse
						or party to a civil union recognized under Colorado
						law".
					</fo:block>

					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						C. Section
						<fo:inline font-weight="bold" font-style="italic">VI.
							General
							Conditions
						</fo:inline>
						is amended as follows:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						1. Paragraph
						<fo:inline font-weight="bold" font-style="italic">D.
							Cancellation
							and Nonrenewal
						</fo:inline>
						is revised as follows:
					</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						a. Subparagraph 2.
						Is deleted in its entirety and replaced by the
						following:
					</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						The
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						may only
						cancel this policy for nonpayment of premium. This policy
						may be
						canceled by or on behalf of the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						by mailing through
						first-class mail to the first
						<fo:inline font-weight="bold" font-style="italic">Named Insured
						</fo:inline>
						, at the last address
						shown in the
						<fo:inline font-weight="bold" font-style="italic">Insurer’s
						</fo:inline>
						records,
						<fo:inline font-weight="bold" font-style="italic">written
						</fo:inline>
						notice of cancellation,
						including the actual reason, at least 10
						days before the effective
						date of cancellation, if the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						cancels for nonpayment of
						premium. The mailing of such notice shall
						be sufficient proof of
						notice. The effective date of cancellation
						stated in such notice
						shall become the expiration date of the
						<fo:inline font-weight="bold" font-style="italic">Policy Period
						</fo:inline>
						. If the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						cancels this policy for nonpayment of premium, the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						shall credit the
						<fo:inline font-weight="bold" font-style="italic">Insured
						</fo:inline>
						90% of the unearned portion of the
						premium. Payment or tender of
						any unearned premium by the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						shall not be a condition precedent to the effectiveness of
						cancellation, but such payment shall be made as soon as
						practicable.
					</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						b. Subparagraph 3.
						is deleted in its entirety and replaced by the
						following:
					</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						The
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						may
						decide not to renew this policy by mailing through first class
						mail to the first
						<fo:inline font-weight="bold" font-style="italic">Named Insured
						</fo:inline>
						, at the last address shown in the
						<fo:inline font-weight="bold" font-style="italic">Insurer’s
						</fo:inline>
						records, written notice of the nonrenewal at least 45
						days before
						the expiration date of the
						<fo:inline font-weight="bold" font-style="italic">Policy Period
						</fo:inline>
						. The mailing
						of such notice shall be sufficient proof of notice.
					</fo:block>
					<fo:block margin-top="4mm" />

					<fo:block start-indent="1.5cm" text-align="left"
						font-size="10px">
						2. The following
						condition is added:
					</fo:block>
					<fo:block margin-top="2mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						<fo:inline font-weight="bold">Your Right To Claim
							Information
						</fo:inline>
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						The
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						shall
						furnish the
						<fo:inline font-weight="bold" font-style="italic">Named Insured
						</fo:inline>
						, upon their request and within 30 days
						thereafter, sufficient
						information about closed or paid
						<fo:inline font-weight="bold" font-style="italic">Claims
						</fo:inline>
						,
						<fo:inline font-weight="bold" font-style="italic">Claims
						</fo:inline>
						for which the Insurer has established reserves, and
						<fo:inline font-weight="bold" font-style="italic">Claims
						</fo:inline>
						for which the
						<fo:inline font-weight="bold" font-style="italic">Insurer
						</fo:inline>
						has received notices of facts or
						circumstances which could give
						rise to
						<fo:inline font-weight="bold" font-style="italic">Claims
						</fo:inline>
						to allow the
						<fo:inline font-weight="bold" font-style="italic">Insureds
						</fo:inline>
						to determine how much of their aggregate coverage remains
						available under the policy.
					</fo:block>
					<fo:block margin-top="20mm" />

					<fo:block text-align="center" font-size="10px">All other
						terms,
						conditions and limitations of the policy remain unchanged.
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
					<fo:block font-size="10px" start-indent="1cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/endorsements_freeform_01/data/LPLAMENDCO0315"  />

					</fo:block>
					<fo:block color="grey" text-align="right" font-size="10px">
						Page 2
						of 2
					</fo:block>


			

	</xsl:template>
</xsl:stylesheet>
