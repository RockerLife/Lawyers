<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-VT">
		
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
												select="response/endorsements_freeform_01/data/LPLAMENDVT0910" />
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
						font-size="10px">VERMONT AMENDATORY ENDORSEMENT</fo:block>
					<fo:block margin-top="10mm" />
					<fo:block text-align="center" font-weight="bold"
						font-size="10px">Part A:</fo:block>
					<fo:block margin-top="10mm" />
					<fo:block text-align="center" font-weight="bold"
						font-size="10px">DEFENSE EXPENSES IN ADDITION TO THE LIMIT OF LIABILITY
					</fo:block>

					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px">
						This Part A amends
						the limit of liability so that Defense Expenses are paid in
						addition to and equal to the Limit of Liability available to pay
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						In consideration of
						the premium charged it is agreed that:
					</fo:block>


					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						1- The last sentence
						of the Preamble appearing above Item 1 of the Declarations is
						deleted.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						2. Item 3 of the
						Declarations, Limit of Liability, is deleted and amended to read:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						<xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;       <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>, each
						<fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>;
					</fo:block>
					<fo:block text-align="left" font-size="10px">
						<xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;       <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>,
						each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>;
					</fo:block>
					<fo:block margin-top="2mm" />
					 <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" font-style="italic">Damages Policy Aggregate</fo:inline>;</fo:block>
				      <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" font-style="italic">Defense Expenses Policy Aggregate</fo:inline>.</fo:block>
				      

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						3. Section II of the
						Policy, Limits of Liability and Deductible, Paragraph A, Limits of
						Liability, is amended as follows:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						- the word “combined” is deleted where it appears in 1. and 2.;
					</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						- 3. is deleted in its entirety;
					</fo:block>




					<fo:block margin-top="10mm" />
					<fo:block text-align="center" font-weight="bold"
						font-size="10px">Part B:</fo:block>
					<fo:block margin-top="5mm" />
					<fo:block text-align="center" font-weight="bold"
						font-size="10px">OTHER POLICY CHANGES
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						1. Section III,
						Extensions, Paragraph B., Spousal and Domestic Partner Extension,
						is amended by the addition of the following
						sentence:

					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						The term spouse and the term domestic partner shall include
						parties to a civil union under Vermont law.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						2. Section VI.,
						General Conditions, Paragraph D. Cancellation and Non Renewal is
						amended as follows:
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						- Paragraph 2 is amended by deletion of the phrase in the second
						sentence “… at least 10 days before the effective
						date of
						cancellation…”, and replacing it with the phrase “… at least
						15
						days before the effective date of
						cancellation…”.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						- Paragraph 3 is deleted in its entirety and replaced with the
						following Paragraph 3.:
					</fo:block>
					<fo:block start-indent="3cm" text-align="left" font-size="10px">
						This policy may be nonrenewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by certified mailing
						to the address shown in Item. 1 of the
						Declarations notice of such
						non renewal, including the reasons for such
						nonrenewal, at least 45
						days prior to
						the expiration date of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.

					</fo:block>
					
					<fo:block margin-top="2mm" font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDVT0910" />
					</fo:block>
						
					<fo:block break-after="page"></fo:block>	
					
					<fo:block text-align="center" font-size="12px">THIS
						ENDORSEMENT
						CHANGES THE POLICY, READ IT CAREFULLY.
					</fo:block>
					<fo:block space-after=".1in" text-align-last="justify">
						<fo:leader leader-pattern="rule" />
					</fo:block>		

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						The following
						Paragraph 4. is added:
					</fo:block>
		
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						(a) If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> (i) elects to renew this policy and (ii) has
						the required information to issue a renewal policy.
						the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>
						will advise the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, in writing at least 45
						days before
						the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy
						Period</fo:inline> , of its intention to renew the
						policy and the premium at which
						it will renew the policy.

					</fo:block>
					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						(b) If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> does not comply with paragraph (a) above the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> will be issued a renewal
						policy. In such case the
						premium for the policy will be the lesser of (i)
						the rates in effect under the expiring 					
						<fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> or, the rates in effect and approved by the
						Commissioner on the expiration date of the
						<fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.
					</fo:block>

					<fo:block text-align="left" start-indent="3cm" font-size="10px">
						(c) The renewal policy will be issued on a pro-rata basis and will
						remain effective until of 45 days after the
						<fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> provides
						written notice of the renewal coverage and premium to
						the Named
						<fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>.. If the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> accepts the renewal coverage and
						premium, paragraph (b)
						above shall not apply.
					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						3. Section II.,
						Limits of Liability and Deductible, Part A., Limits of Liability,
						Item 6. is deleted, and replaced with the following:
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" start-indent="2cm" font-size="10px">
						6. Not withstanding Items 1 and 2 of the Section II A., the Limit
						of Liability granted under Section III, Extensions,
						Paragraph D.
						2., Optional Extended Reporting Period, shall be equal to the
						limit of Liability shown in Item 3. of the
						Declarations.

					</fo:block>

					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px">
						4. Section II.,
						Limits of Liability and Deductible, Paragraph B., Deductible, is
						amended by deletion of the second to last sentence and replacing
						it with the following sentence:
					</fo:block>
					
					<fo:block margin-top="4mm" />
					<fo:block start-indent="2cm" text-align="left" font-size="10px">
						If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> advances any amounts within the Deductible, the
						<fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> within
						thirty days if such advancement was with respect of <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>; if
						such advancement is with respect to
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>, such reimbursement shall be due the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> upon payment of such
						<fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>.

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
							select="response/policyformfooter_freeform_01/data/LPLAMENDVT0910" />
					</fo:block>

	</xsl:template>
</xsl:stylesheet>
