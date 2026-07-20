<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-NH">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDNH0910" />
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
						font-size="10px">NEW HAMPSHIRE AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="5mm" />

					<fo:block text-align="left" font-size="10px">THE FOLLOWING IS
						ADDED TO THE POLICY: No policies issued to New Hampshire
						policyholders where <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> are included within the Limit
						of Liability, shall be issued with a Limit of Liability below
						$100,000 each <fo:inline font-weight="bold" font-style="italic">Claim/ Policy Aggregate</fo:inline>.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">1. Section VI.,
						General Conditions, Paragraph D Cancellation and Non Renewal, is
						amended by deletion of 2. and 3. and
						replacing them with the
						following 2. and 3. :

					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">2.
						This policy may not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> if in effect for
						less than 60 days. Thereafter, this policy may be
						canceled by the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for solely, one or more of the following reasons:

					</fo:block>


					<fo:block text-align="left" start-indent="2cm" font-size="10px">-a-
						non payment of premium due hereunder
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">-b-
						fraud or material misrepresentation affecting the policy or in
						presentation of a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> hereunder, or
						violation of any of the terms
						or conditions of the policy

					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">-c-
						substantial increase in the hazard, provided that cancellation for
						this reason shall be effective only after
						approval of the Office of
						the Commissioner of the Insurance department of
						New Hampshire.
					</fo:block>

					<fo:block text-align="left" start-indent="1cm" font-size="10px">If
						this policy is cancelled for reason -a- or reason -c- above, the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall provide the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> with
						notice of the
						cancellation no less than 10 days prior to the
						effectiveness of
						such cancellation. If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> cancels
						the policy for reason -b-
						above, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall provide the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline> with notice of
						the cancellation no less
						than 60 days prior to the effectiveness of
						such cancellation. Such
						notices shall be in writing, state the
						reasons for
						cancellation and be mailed to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at the
						<fo:inline font-weight="bold" font-style="italic">Named Insured’s</fo:inline> last
						known address by certified mail,
						except with
						respect to cancellation for reason -a- when such notice will
						be
						sent with a certificate of mailing
						obtained by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>. If this
						policy is cancelled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for
						reason -a-, the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						shall credit the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>
						90% of the unearned portion of the
						premium. Payment or tender of any
						unearned premium by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						shall not
						be a condition precedent to the effectiveness of the
						cancellation,
						but such payment shall be made within thirty (30)
						days of the effective date of cancelation.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">3.This
						policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, or by mailing to the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known
						address, written notice of such
						nonrenewal at least 60 days prior
						to the
						expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> must send a non
						renewal notice
						60 days prior to the expiration of
						the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>
						if its intent is to renew the policy for a premium
						exceeding 25% of
						the expiring premium.
						The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> must send a non renewal notice at
						least 30 days prior to
						the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>
						if
						its intent is to renew the policy for a premium not exceeding 25%
						of the expiring premium. The proof of mailing
						such notice shall be
						sufficient proof of notice.
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						2. Section III.,
						Extensions, D. Extended Reporting Periods, paragraphs 1, 2 and 3
						are deleted and replaced with the following 1.,
						2. and 3.:

					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-weight="bold" start-indent="1cm" font-size="10px">1.
						Automatic Extended Reporting Period
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">
						Upon the expiration of this policy for any reason, the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline> shall be provided with an automatic and non cancelable
						period of sixty days, at no cost, commencing on the policy
						expiration date, to report <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> pursuant to
						Section VI. A. of General Conditions, Notice of <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> or
						<fo:inline font-weight="bold" font-style="italic">Circumstance</fo:inline>. Coverage under this extension of time to report a
						<fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> (hereinafter referred to as the Automatic Extended Reporting
						Period) shall apply solely to negligent acts, errors or omissions
						committed, attempted or allegedly committed or attempted, in
						whole, prior to the effective date of nonrenewal or cancellation,
						whichever occurs first, and which are not otherwise excluded by
						any terms, conditions or exclusions of this policy. This Automatic
						Extended Reporting Period shall not be applicable, however, in the
						event the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> has obtained another policy of Lawyers
						Professional Liability insurance with an inception date as of the
						termination date of this policy.
					</fo:block>
					
					<fo:block margin-top="4mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDNH0910" /></fo:block>
					<fo:block break-after="page"></fo:block>
					
					<fo:block text-align="center" font-size="12px">THIS
						ENDORSEMENT
						CHANGES THE POLICY, READ IT CAREFULLY.
					</fo:block>
					<fo:block space-after=".1in" text-align-last="justify">
						<fo:leader leader-pattern="rule" />
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-weight="bold" start-indent="1cm" font-size="10px">
						2. Optional Extended Reporting Period
					</fo:block>
					<fo:block margin-top="2mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">
						Upon the expiration or cancellation of this policy, the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline> shall have the right, upon payment of the additional
						premium stated in Item 7. (b) of the Declarations and solely for
						the length of time stated in the Options below commencing on the
						expiration date of the Automatic Extended Reporting Period, to
						report <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> pursuant to Section VI.A. of General Conditions,
						Notice of <fo:inline font-weight="bold" font-style="italic">Claim or Circumstance</fo:inline>. Coverage under such extension of
						time to report a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> (hereinafter referred to as the Optional
						Extended Reporting Period) shall apply solely to negligent acts,
						errors or omissions committed, attempted or allegedly committed or
						attempted, in whole, prior to the effective date of nonrenewal or
						cancellation, whichever occurs first, and which are not otherwise
						excluded by any terms, conditions or exclusions of this policy.</fo:block>
						<fo:block text-align="left"  start-indent="1cm" font-size="10px">a)
						Optional Extended reporting Period of 12 months for a premium
						charge of 100% on the annual premium charge;</fo:block>
						<fo:block text-align="left"  start-indent="1cm" font-size="10px">b) Optional Extended
						reporting Period of 36 months for a premium
						charge of 185% on the
						annual premium charge;</fo:block>
						<fo:block text-align="left"  start-indent="1cm" font-size="10px">c) Optional Extended reporting Period of 60
						months for a premium
						charge of 225% on the annual premium charge;</fo:block>
						<fo:block text-align="left"  start-indent="1cm" font-size="10px">d) Optional Extended reporting Period of 72 months for a premium
						charge of 250% on the annual premium charge.</fo:block>
					<fo:block margin-top="2mm" />
					<fo:block text-align="left" start-indent="1cm" font-size="10px">
						If the Optional
						Extended Reporting Period is requested by the <fo:inline font-weight="bold" font-style="italic">Named
						Insured</fo:inline>, the
						additional premium shall be fully
						earned, and the Optional Extended
						Reporting Period cannot be cancelled by
						the <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> or the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>. This right
					
						to
					
						purchase the Optional Extended Reporting Period shall lapse unless
						written notice of such election, together with
						payment of the
						additional premium due, is received by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> no
						later than
						sixty days following the effective date
					
						of
					
						cancellation or nonrenewal, whichever occurs first.
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-weight="bold" start-indent="1cm" font-size="10px">
						3. Non-Practicing Insured Extended Reporting Period
					</fo:block>

					<fo:block margin-top="2mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						Provided this policy (a) has not been cancelled (b) no other
						Extended Reporting Period is in effect and (c) this policy remains
						in force as to all other <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline>, except in the case where all
						<fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> are <fo:inline font-weight="bold" font-style="italic">Non-Practicing Insureds</fo:inline>, a <fo:inline font-weight="bold" font-style="italic">Non–Practicing Insured</fo:inline>
						shall have the right to purchase an unlimited extension of time to
						report <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> that are first made against such <fo:inline font-weight="bold" font-style="italic">Non-Practicing
						Insured</fo:inline> and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> after the effective date of
						retirement by reason of negligent acts, errors or omissions
						alleged to have been committed subsequent to the applicable Prior
						Acts Date, but before the effective date of retirement and not
						otherwise excluded by the agreements, conditions and exclusions of
						this policy. The additional premium for this <fo:inline font-weight="bold" font-style="italic">Non-Practicing
						Insured</fo:inline> Extended Reporting Period shall be 125% of the <fo:inline font-weight="bold" font-style="italic">Non-
						Practicing Insured’s</fo:inline> proportionate share of the annual premium
						(275% of the annual premium in the case where all <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> are
						<fo:inline font-weight="bold" font-style="italic">Non-Practicing Insureds</fo:inline>) and is due and payable to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> no
						later than thirty days after the effective date of retirement, or
						cessation of the practice of accountancy.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						3. Section VI.,
						General Conditions, paragraph G., Representations, is deleted and
						replaced with the following G.:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-weight="bold" start-indent="3cm" font-size="10px">
						G. Representations
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						The <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> represents that all information and statements
						contained in the Declarations are true,
						accurate and complete. All
						such information and statements are the basis
						for the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						issuing this policy.
						Any intentional misrepresentation, omission,
						concealment or
						misstatement of a material fact in the
						Declarations
						or otherwise, which relates to a particular <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> shall be
						grounds
						to deny coverage under this
						Policy.

					</fo:block>
					<fo:block margin-top="15mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDNH0910" /></fo:block>
					
					<fo:block break-after="page"></fo:block>
					<fo:block text-align="center" font-size="12px">THIS
						ENDORSEMENT
						CHANGES THE POLICY, READ IT CAREFULLY.
					</fo:block>
					<fo:block space-after=".1in" text-align-last="justify">
						<fo:leader leader-pattern="rule" />
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						4. Section VI.,
						General Conditions, subsection E., Changes in Exposures, Paragraph
						1. is deleted and replaced with the following:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						1. If the number of attorneys employed by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>
						increases more than 25% from the amount of attorneys shown in the
						application attached to this policy at its inception date, the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall give the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> notice of such hiring, merger
						or acquisition as soon as practicable, but in no event more than
						30 days after the effective date of hiring, merger or acquisition
						and the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall then have the right to amend any terms of
						this policy. Absent such timely notice, there shall be no coverage
						under this policy for any <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> subsequent to
						thirty days from the date of hiring, merger or acquisition;
						however, the coverage provided under this policy shall remain in
						force with respect to <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> provided prior to the
						thirtieth day subsequent to such hiring, merger or acquisition.
						This paragraph shall not be applicable if the original number of
						attorneys insured on the effective date of this policy was less
						than six attorneys.
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
					<fo:block margin-top="100mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDNH0910" /></fo:block>


	</xsl:template>
</xsl:stylesheet>
