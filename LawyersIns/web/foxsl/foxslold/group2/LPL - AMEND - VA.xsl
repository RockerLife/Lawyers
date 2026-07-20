<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-VA">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDVA0910" />
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
						font-size="10px">VIRGINIA AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="5mm" />

					<fo:block text-align="left" font-weight="bold" font-size="10px">
					<fo:inline text-decoration="underline" font-weight="bold" font-size="10pt">NOTICE
						TO VIRGINIA
						APPLICANTS:</fo:inline> YOU HAVE PURCHASED A 'CLAIMS-MADE'
						LIABILITY INSURANCE
						POLICY. PLEASE READ THE ENTIRE POLICY CAREFULLY
						TO UNDERSTAND THE
						COVERAGE GRANTED. THERE ARE CERTAIN CIRCUMSTANCES
						IN WHICH YOU
						MUST BE PROVIDED THE OPPORTUNITY TO PURCHASE AN
						EXTENDED REPORTING
						PERIOD TO REPORT CLAIMS. THESE CIRCUMSTANCES ARE
						EXPLAINED IN THE
						POLICY AS AMENDED BY THIS AND OTHER ENDORSEMENTS
						ATTACHED TO THE
						POLICY. IF YOU HAVE ANY QUESTIONS REGARDING THE
						COST OF AN
						EXTENDED REPORTING PERIOD OR, THE AVAILABLE OPTIONS
						UNDER THE
						EXTENDED REPORTING PROVISIONS OF THIS POLICY, CONTACT
						YOUR
						INSURANCE AGENT OR THE INSURANCE COMPANY.
					</fo:block>
					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">1. Section III,
						Extensions of Coverage, D. Extended Reporting Period, paragraphs 1
						and 2 are deleted and replaced with the following:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">D.
						Extended Reporting Period
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						Except as provided in the third paragraph of this section D., if
						the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> (a) advances the <fo:inline font-weight="bold" font-style="italic">Prior Acts Date</fo:inline> (b) renews coverage
						on an other- than– claims- made basis (c) offers renewal excluding
						coverage previously provided (d) non-renews or cancels the policy
						for reasons other than for non payment of premium, failure to
						comply with the terms and conditions of this policy, or for fraud,
						or (e) deletes a portion of coverage available under the policy,
						then all <fo:inline font-weight="bold" font-style="italic">Named Insureds</fo:inline> shall be entitled to purchase an Optional
						Extended Reporting Period from the choices following this
						paragrah. The <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> shall have up to sixty days subsequent to
						the effective date of (a), (b), (c), (d) or (e) to opt for such an
						Optional Extended Reporting Period, said sixty day period deemed
						an Automatic Extended Reporting Period and free of charge.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">Choices
						of Optional Extended Reporting Periods:
					</fo:block>

					<fo:block text-align="left" start-indent="4cm" font-size="10px">
						A) 12 months length at a premium charge of 100% of the annual
						premium, or
					</fo:block>
					<fo:block text-align="left" start-indent="4cm" font-size="10px">
						B) 24 months length at a premium charge of 200% of the annual
						premium, or
					</fo:block>
					<fo:block text-align="left" start-indent="4cm" font-size="10px">
						C) 36 months length at a premium charge of 215% of the annual
						premium, or
					</fo:block>
					<fo:block text-align="left" start-indent="4cm" font-size="10px">
						D) 60 months length at a premium charge of 225% of the annual
						premium, or
					</fo:block>
					<fo:block text-align="left" start-indent="4cm" font-size="10px">
						E) 72 months length at a premium charge of 250% of the annual
						premium.
					</fo:block>
					
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">The
						Automatic Extended Reporting Period and the Optional Extended
						Reporting Period (if purchased) are extensions of time to report
						<fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> pursuant to Section VI A. of General Conditions, Notice of
						a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or Circumstance. Coverage under such extension of time to
						report a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> shall apply solely to <fo:inline font-weight="bold" font-style="italic">Wrongful Acts</fo:inline> committed,
						attempted or allegedly committed or attempted, in whole, prior to
						the effective date of (a), (b), (c), (d) or (e) in the foregoing
						paragraph, but which are not otherwise excluded by any terms,
						conditions or exclusions of this policy.
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">The
						foregoing Automatic and Optional Extended Reporting Periods shall
						not apply, however, if the policy is canceled or non- renewed as a
						result of (a) failure of the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> to pay premium due under the
						policy by due date, or (b) failure of the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> to comply with
						conditions required under the policy, or (c) fraud by any Insured.
						The right to purchase the Optional Extended Reporting Period shall
						end on the last day of the Automatic Extended Reporting Period
						unless prior thereto the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> has delivered written
						notice of the election of the Optional Extended Reporting period
						together with the appropriate additional premium due to the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>. In such case, the Optional Extended Reporting Period
						shall incept on the last day of the Automatic Extended Reporting
						Period.
					</fo:block>
					
					<fo:block margin-top="5mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDVA0910" />
					</fo:block>
					<fo:block break-after="page"></fo:block>
					
					<fo:block text-align="center" font-size="12px">THIS
						ENDORSEMENT
						CHANGES THE POLICY, READ IT CAREFULLY.
					</fo:block>
					<fo:block space-after=".1in" text-align-last="justify">
						<fo:leader leader-pattern="rule" />
					</fo:block>
					<fo:block margin-top="6mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">The
						Automatic Extended Reporting Period shall not serve to increase
						the Limit of Liability as stated in the Declarations and such
						Limit of Liability during the Automatic Extended Reporting Period
						shall be the remaining Limit of Liability of the policy at the
						inception of the Automatic Extended Reporting Period. However, if
						the Optional Extended Reporting Period is purchased, the Limit of
						Liability available for the entire Optional Extended Reporting
						Period shall be equal to the Limit of Liability as stated in the
						Declarations. Once in effect, the Optional Extended Reporting
						Period cannot be cancelled except that the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may cancel the
						Optional Extended Reporting Period for non- payment of premium or
						for fraud by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>. The Optional Extended Reporting Period
						shall apply as excess over any other valid insurance applicable to
						a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>, but the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not void the coverage available to
						the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> under the Optional Extended Reporting Period
						owing to the existence of such other valid insurance.
					</fo:block>


					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">2. Section II,
						Limits of Liability and Deductible, paragraph A., Limit of
						Liability, (1), (2), (6) and (7) are deleted and replaced with the
						following (1), (2, (6) and (7):
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						(1) Subject to (2) that follows, the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> Limit of Liability
						for <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> combined for all each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> first
						made during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> , including the Automatic Extended
						Reporting Period shall not exceed the Amount shown in Item 3. of
						the Declarations as "each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>".
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						(2) Subject to (1) above, the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> Limit of Liability for
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> combined for all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> first made
						during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>, including the Automatic Extended
						Reporting Period, shall not exceed the amount shown in Item 3. of
						the Declarations as “<fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline>”.
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						(6) The Limit of Liability available for all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> first made
						against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during any Length
						Optional Extended Reporting Period purchased by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> shall
						be equal to the Limits of Liability as shown in Item 3. of the
						Declarations for “each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>” and for <fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline>.
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						(7) If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> has exhausted the applicable Limit of Liability
						by payment of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and/or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> it shall have no
						other duties to the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> under this policy except for (a)
						sending proper notice of cancellation or non renewal of the policy
						to the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and (b) offering the Optional Extended Reporting
						Period when conditions require.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">3. (a) Section IV.,
						Definitions, definition of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>, first two sentences are
						deleted and amended to read:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> means any monetary judgment (including pre-judgment
						interest) monetary award or monetary settlement negotiated with
						the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> written consent. Post- judgment interest is not part
						of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and if awarded against the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, are payable in
						addition to the Limit of Liability as a supplementary payment.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">
						(b) paragraph (c) under “<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> shall not include…” is deleted.
					</fo:block>

					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">4. Section VI,
						General Conditions, paragraph G. Representations, is deleted and
						replaced with the following paragraph:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						G. Representations
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						In granting coverage to the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> it is agreed that the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						has relied on the representations contained in the application for
						this policy (and all such previous applications submitted, or made
						part of any previous policy which this policy may succeed in
						time). Statements in the application including materials submitted
						therewith are only material to the risk assumed, or can invalidate
						coverage under this policy, if the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> can clearly prove that
						such statements in the application or materials submitted
						therewith or in any affidavit before or after a Claim was material
						to the risk assumed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> and was untrue.
					</fo:block>

					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">5. Section IV,
						Definitions, Definition of <fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline> is deleted and
						replaced with the following:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						<fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline> means the amount identified in Item 3. of the
						Declarations and represents the amount of the <fo:inline font-weight="bold" font-style="italic">Insurer’s</fo:inline> liability
						for all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline>, all <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and all <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> combined
						under this policy, inclusive of the Automatic Extended Reporting
						Period. The <fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline> shall be reinstated in full for the
						Optional Extended Reporting Period if purchased by the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline> even if the <fo:inline font-weight="bold" font-style="italic">Policy Aggregate</fo:inline> has been eroded or exhausted
						by reason of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> paid during the <fo:inline font-weight="bold" font-style="italic">Policy
						Period</fo:inline> and/or the Automatic Extended Reporting Period.
					</fo:block>
					
					<fo:block margin-top="5mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDVA0910" />
					</fo:block>
					<fo:block break-after="page"/>
					
					<fo:block text-align="center" font-size="12px">THIS
						ENDORSEMENT
						CHANGES THE POLICY, READ IT CAREFULLY.
					</fo:block>
					<fo:block space-after=".1in" text-align-last="justify">
						<fo:leader leader-pattern="rule" />
					</fo:block>
					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">6. Section VI,
						General Conditions, D. Cancellation and Non Renewal, paragraphs 2
						and 3 are deleted and replaced with the following:
					</fo:block>
					
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						2. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> may only cancel this policy for nonpayment of
						premium or Deductible. This policy may be canceled by or on behalf
						of the Insurer for nonpayment of premium, or non payment of
						renewal premium by delivering to Named Insured, or by mailing to
						all <fo:inline font-weight="bold" font-style="italic">Named Insureds</fo:inline>, at the address shown in Item 1. of the
						Declarations, written notice of cancellation at least 15 days
						before the effective date of cancellation, or in the case of non
						payment of Deductible, at least 45 days before the effective date
						of cancellation. The mailing of such notice shall be sufficient
						proof of notice and the effective date of cancellation stated in
						such notice shall become the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.
						If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels this policy for nonpayment of premium, the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall credit the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> the pro rata unearned portion of
						the premium. Payment or tender of any unearned premium by the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall not be a condition precedent to the effectiveness of
						cancellation, but such payment shall be made as soon as
						practicable.
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						3. This policy may be non renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to
						all <fo:inline font-weight="bold" font-style="italic">Named Insureds</fo:inline> or by mailing to all <fo:inline font-weight="bold" font-style="italic">Named Insureds</fo:inline> at the
						address shown in Item 1 of the Declarations written notice of
						nonrenewal at least 45 (forty-five) days prior to the expiration
						date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The mailing of such notice shall be
						sufficient proof of such notice.
					</fo:block>
					
					
					
					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">7. Section VI.,
						General Conditions, paragraph C., Action Against the Insurer is
						amended by the addition of item 3, as follows:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						3. However, notwithstanding 1. and 2. above, an action may be
						maintained against he Insurer if a judgment against an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> or
						its representative is returned unsatisfied.
					</fo:block>

					<fo:block margin-top="6mm" />
					<fo:block text-align="left" font-size="10px">8. Section VI.,
						General Conditions, paragraph N. Authorization and Sole Agent, is
						amended by deletion of the phrase "… and warranties …" where it
						appears in the sentence.
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
							select="response/policyformfooter_freeform_01/data/LPLAMENDVA0910" />
					</fo:block>


	</xsl:template>
</xsl:stylesheet>
