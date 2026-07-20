<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-State-GA">
	<!--  
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait"
					page-width="21cm" page-height="29.7cm" margin-top="10mm"
					margin-bottom="1.0mm" margin-left="10mm" margin-right="10mm">
					<fo:region-body margin-top="2.0mm" />
					<fo:region-before extent="1.0mm" />
					<fo:region-after />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">

				

				<fo:flow flow-name="xsl-region-body">
	-->
					<fo:block text-align="center" font-size="12px">
						THIS ENDORSEMENT CHANGES THE POLICY, READ IT
						CAREFULLY.
					</fo:block>
					<fo:block margin-top="10mm" />

					<fo:block>
						<fo:table text-align="center">
							<fo:table-column column-width="90mm" />
							<fo:table-column column-width="90mm" />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding-left="4pt">
										<fo:block font-weight="bold"
											font-size="10px" text-align="left">
											To be attached to and form
											part of Policy No: 
											<xsl:value-of
												select="response/policy_freeform_01/data/PolicyNumber" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt">
										<fo:block font-weight="bold"
											font-size="10px" text-align="left">
											Effective Date of
											Endorsement: 
											<xsl:value-of
												select="response/policy_freeform_01/data/PolicyEffectiveDate" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell padding-left="4pt"
										padding-top="4pt">
										<fo:block font-weight="bold"
											font-size="10px" text-align="left">
											Issued to: 
											<xsl:value-of
												select="response/policy_freeform_01/data/AccountName" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt"
										padding-top="4pt">
										<fo:block font-weight="bold"
											font-size="10px" text-align="left">
											Endorsement No: 
											<xsl:value-of
												select="response/endorsements_freeform_01/data/LPLAMENDGA0910" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
					</fo:block>

					<fo:block space-after=".1in"
						text-align-last="justify">
						<fo:leader leader-pattern="rule" />
					</fo:block>

					<fo:block margin-top="12mm" />
					<fo:block text-align="center" font-weight="bold"
						font-size="10px">
						GEORGIA AMENDATORY ENDORSEMENT
					</fo:block>
					<fo:block margin-top="4mm" />


					<fo:block text-align="left" font-size="10px">
						(1) This policy may not be canceled by the
						<fo:inline font-weight="bold"
							font-style="italic">
							Insurer
						</fo:inline>
						for any reason at any time except for non
						payment of premium or deductible by the
						<fo:inline font-weight="bold"
							font-style="italic">
							Named Insured
						</fo:inline>
						. However, VI., General Conditions, D.
						Cancellation and Nonrenewal, paragraph #3 is
						deleted and replaced with the following:
					</fo:block>
					<fo:block margin-top="4mm" />


					<fo:block text-align="left" font-size="10px"
						start-indent="2cm">
						3. This policy may be non-renewed by the
						<fo:inline font-weight="bold"
							font-style="italic">
							Insurer
						</fo:inline>
						by delivering to the
						<fo:inline font-weight="bold"
							font-style="italic">
							Named Insured
						</fo:inline>
						, or by mailing to
					</fo:block>
					<fo:block text-align="left" font-size="10px"
						start-indent="2.4cm">the
						<fo:inline font-weight="bold"
							font-style="italic">Named Insured</fo:inline> at its last known address, written
						notice of such nonrenewal at least forty five
						days prior to the expiration of the<fo:inline font-weight="bold"
							font-style="italic"> Policy
						Period</fo:inline>. If the<fo:inline font-weight="bold"
							font-style="italic"> Insurer</fo:inline> offers renewal coverage
						at an increased premium or a new limitation or
						restriction of coverage, the<fo:inline font-weight="bold"
							font-style="italic"> Insurer</fo:inline> will also
						provide forty five days notice prior to the
						expiration of the <fo:inline font-weight="bold"
							font-style="italic">Policy Period </fo:inline>to the <fo:inline font-weight="bold"
							font-style="italic">Named
						Insured </fo:inline>in the same manner in the foregoing
						sentence. The proof of mailing such notice shall
						be sufficient proof of notice.

					</fo:block>
					<fo:block margin-top="10mm" />
					<!--  
						<fo:block margin-top="4mm"/>  
						<fo:block text-align="left" font-size="10px" start-indent="2cm">The<fo:inline font-weight="bold" font-style="italic"> Insurer </fo:inline> will otherwise renew this policy unless:</fo:block>
						
						<fo:block text-align="left" font-size="10px" start-indent="4cm">- the<fo:inline font-weight="bold" font-style="italic"> Named Insured </fo:inline>fails to pay the renewal  premium after the<fo:inline font-weight="bold" font-style="italic"> Insurer </fo:inline> has made a renewal</fo:block>
						<fo:block text-align="left" font-size="10px" start-indent="4.3cm">offer including a statement of the renewal premium to the<fo:inline font-weight="bold" font-style="italic"> Named Insured </fo:inline> , or its agent or broker, at least 20 days before the expiration date of the policy; or,</fo:block>
						
						<fo:block text-align="left" font-size="10px" start-indent="4cm">- other coverage acceptable to the Named Insured has been procured prior to the expiration </fo:block>						
						<fo:block text-align="left" font-size="10px" start-indent="4.3cm">date of this policy; or,</fo:block>
						
						<fo:block text-align="left" font-size="10px" start-indent="4cm">- the this policy clearly states that it is non renewable, and for a specific line, subclassification,</fo:block>
						<fo:block text-align="left" font-size="10px" start-indent="4.3cm">or type of coverage that is not offered on a renewable basis.</fo:block>
						
						<fo:block margin-top="4mm"/> 
					-->
					<fo:block text-align="left" font-size="10px">
						(2) It is agreed that VI. General Conditions,
						Paragraph I., Other Insurance, is deleted and
						replaced with the following:
					</fo:block>

					<!--  
						<fo:block text-align="left" font-size="10px" start-indent="2cm">7. After the applicable Limit of Liability has been exhausted by payment of<fo:inline font-weight="bold" font-style="italic"> Damages </fo:inline>and/or<fo:inline font-weight="bold" font-style="italic"> Defense </fo:inline></fo:block>
						<fo:block text-align="left" font-size="10px" start-indent="2.4cm"><fo:inline font-weight="bold" font-style="italic"> Expenses </fo:inline>or by tendering the remaining Limit of Liability into court, the <fo:inline font-weight="bold" font-style="italic"> Insurer’s </fo:inline>duty to defend the<fo:inline font-weight="bold" font-style="italic"> Named Insured </fo:inline>shall continue until a judgment or settlement has been made with the injured party, or until the<fo:inline font-weight="bold" font-style="italic"> Insurer </fo:inline>obtains written permission of the<fo:inline font-weight="bold" font-style="italic"> Named Insured </fo:inline>to cease defending the<fo:inline font-weight="bold" font-style="italic"> Named Insured </fo:inline>.</fo:block>
					-->
					<fo:block margin-top="4mm" />

					<fo:block text-align="left" font-size="10px" start-indent="2cm">
						H. Other Insurance
					</fo:block>
					<fo:block margin-top="4mm" />
					<fo:block text-align="left" font-size="10px"
						start-indent="2.4cm">
						If other valid and collectible insurance is
						applicable to pay<fo:inline font-weight="bold"	font-style="italic"> Damages </fo:inline>or <fo:inline font-weight="bold"
							font-style="italic">Defense Expenses</fo:inline>
						arising from a <fo:inline font-weight="bold"
							font-style="italic">Claim</fo:inline> to which this policy
						applies, the<fo:inline font-weight="bold"
							font-style="italic"> Insurer</fo:inline> will pay its proportional
						share of such <fo:inline font-weight="bold"
							font-style="italic">Damages </fo:inline>or <fo:inline font-weight="bold"
							font-style="italic">Defense Expenses </fo:inline>as its
						Limit of Liability relates to the total Limits
						of Liability applicable to such <fo:inline font-weight="bold"
							font-style="italic">Claim </fo:inline>from this
						policy plus other said insurance. (The foregoing
						shall not apply, however, if such other
						insurance was written specifically to be in
						excess of this policy.) This policy, however,
						shall not be subject to the terms or conditions
						of such other insurance.
					</fo:block>



					<fo:block margin-top="70mm" />

					<fo:block text-align="left" font-size="10px" start-indent="17mm">
						All other terms, conditions and limitations of
						the policy remain unaltered.
					</fo:block>
					<fo:block margin-top="15mm" />
					<fo:block>
						<fo:table text-align="center">
							<fo:table-column column-width="80mm" />
							<fo:table-column column-width="80mm" />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding-left="4pt">
										<fo:block font-size="10px"
											text-align="center">
											<xsl:value-of
												select="response/freeform/data/authorized_signature" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt">
										<fo:block font-size="10px"
											text-align="center">
											<xsl:value-of
												select="response/freeform/data/date" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt"
										padding-top="4pt">
										<fo:block font-size="10px"
											text-align="center">
											________________________
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt"
										padding-top="4pt">
										<fo:block font-size="10px"
											text-align="center">
											________________________
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-left="4pt"
										padding-top="4pt">
										<fo:block font-size="10px"
											text-align="center">
											Authorized Representative
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding-left="4pt"
										padding-top="4pt">
										<fo:block font-size="10px"
											text-align="center">
											Date
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block margin-top="30mm" />
					  
						<fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDGA0910" /></fo:block>
					<!--  
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
-->
	</xsl:template>
</xsl:stylesheet>
