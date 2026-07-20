<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait"
					page-width="21cm" page-height="29.7cm" margin-top="0.5in"
					margin-bottom="0.5in" margin-left="0.3in" margin-right="0.2in">
					<fo:region-body margin-top="0.5in" />
					<fo:region-before extent="1.0in" />
					<fo:region-after />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:static-content flow-name="xsl-region-before">
					<fo:block></fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="35px"
						font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
						color="maroon">
						<fo:block color="black">
							<fo:inline font-weight="bold">
								RISK MANAGEMENT
							</fo:inline>
						</fo:block>
						<fo:block color="black">
							<fo:inline font-weight="bold">
								ASSESSMENT
							</fo:inline>
						</fo:block>
						<fo:block>
							<xsl:value-of select="response/AccountName"></xsl:value-of>
						</fo:block>
						<fo:block>
						<xsl:choose>
							<xsl:when test="response/environmentproduction = 'Y'">
								<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/1.PNG" content-height="8in" />
							</xsl:when>
							<xsl:otherwise>
								<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/1.PNG" content-height="8in" />
							</xsl:otherwise>
						</xsl:choose>
						</fo:block>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="A4-portrait" >
				<fo:static-content flow-name="xsl-region-before">
					<fo:table>
						<fo:table-column column-width="85mm" />
						<fo:table-column column-width="100mm" />
						<fo:table-body>
							<fo:table-row space-after="3mm">
								<fo:table-cell>
									<fo:block text-align="left" font-size="8px"
										font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
										color="grey">
										<fo:block text-align="left" font-size="8px"
											font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
											color="black">Protexure Accountants</fo:block>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block text-align="right" font-size="8px"
										font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
										color="grey">
										<fo:page-number />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block margin-top="3mm">
						<xsl:choose>
							<xsl:when test="response/environmentproduction = 'Y'">
								<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/line1.PNG" />
							</xsl:when>
							<xsl:otherwise>
								<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/line1.PNG" />
							</xsl:otherwise>
						</xsl:choose>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<xsl:if
						test="(response/AOP_Percentage_3 != '' and response/AOP_Percentage_3 != '0')">
						<fo:block margin-top="8mm">
							<fo:block font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									INDIVIDUAL TAX RETURN
									PREPARATION 
								</fo:inline>
							</fo:block>
							<fo:block space-after="4mm" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									CLAIM SCENARIO
								</fo:inline>
							</fo:block>
							<fo:table>
								<fo:table-column column-width="60mm" />
								<fo:table-column column-width="120mm" />
								<fo:table-body>
									<fo:table-row space-before="3mm">
										<fo:table-cell>
											<fo:block>
												<xsl:choose>
													<xsl:when test="response/environmentproduction = 'Y'">
														<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/indvidual.PNG"
															content-height="12in" content-width="2in" />
													</xsl:when>
													<xsl:otherwise>
														<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/indvidual.PNG"
															content-height="12in" content-width="2in" />
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block space-after="10mm" font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												An accountant took on a new individual client, who had
												been on
												extension and not filed her federal returns for a few
												years.
												 The
												client made the accountant aware of the fact that
												she was due a
												refund from the IRS for the overpayment of
												estimated taxes a
												few years back, but she was not precise
												about exactly the
												year
												involved or the circumstances of the
												amount due back to the
												client.  Once the accountant was able
												to straighten out the
												situation and bring the client’s tax
												filings up to date, he
												applied
												for a refund of the previous
												overpayment, telling the client
												that
												the overpayment would be
												refunded by the IRS because he was
												making the request within
												three years of the due date of the
												relevant tax return.  Such
												overpayment was confiscated by the
												IRS, however, because the
												relevant tax return was filed more
												than two years after the
												overpayment was withheld.  As such
												two-year deadline was
												within the period of retention of the
												accountant, and expired
												while the accountant was otherwise
												bringing the client up to
												date, the client made a claim
												against
												the accountant for the
												amount that was confiscated by the IRS.
												 The claim was
												settled
												by payment of the amount of the
												confiscated
												overpayment. 
											</fo:block>
											<fo:block space-after="5mm" font-size="25px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="maroon">
												<fo:inline font-weight="bold">
													DISCUSSION
												</fo:inline>
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												Claims involving tax services are the most frequent
												type of claim
												brought against Accountants.
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												Typical circumstances that could give rise to a claim
												include:
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;Untimely tax preparation and filing
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;Improper computation of tax liability
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;Improper tax shelter advice
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;General erroneous tax advice
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												Interest and penalties may be assessed by the IRS
												and/or state
												authority, and potential damages can be
												significant under tax
												law.
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:block>
						<fo:block page-break-before="always" />
					</xsl:if>
					<xsl:if
						test="(response/AOP_Percentage_1 != '' and response/AOP_Percentage_1 != '0')">
						<fo:block margin-top="8mm">
							<fo:block font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									CORPORATE TAX PREPARATION 
								</fo:inline>
							</fo:block>
							<fo:block space-after="4mm" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									CLAIM SCENARIO
								</fo:inline>
							</fo:block>
							<fo:block space-after="0.5in" font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								The accountant was retained in 2000 to prepare corporate
								income and
								payroll tax filings
								for a New Jersey corporation, with
								all services performed in New
								Jersey and a small
								percentage of its
								product being sold in New York.  In 2000, the client
								asked the
								accountant randomly by email whether it owed any sales tax to
								the
								state of
								New York.
								 While the accountant was not involved in
								any of
								the sales tax
								preparation by the client,
								which handled all
								of its
								own sales tax issues through its internal
								accounting
								department,
								the accountant responded in writing that the client
								owed no sales
								tax to New York.  After
								2004, the client began to
								increase its
								sales in the state of New York,
								opening a small New
								York satellite
								office.  In 2014, the client advised the
								accountant
								that the state
								of New York
								had just assessed back sales
								tax to the year 2004,
								along with
								substantial penalties and
								interest, because the client
								had never paid any sales tax to the
								state of
								New York.  The
								accountant took the position that it had
								no involvement in the
								client’s
								sales tax issues, and
								that it was a
								complete surprise that
								the client had not paid sales
								tax to New
								York.  The
								client,
								however, filed suit against the accountant,
								claiming that the
								single email in 2000
								from the accountant to the
								client constituted
								sales tax advice, and
								created a continuing
								duty
								by the accountant
								to advise the client about whether it would
								become liable for New
								York sales tax, particularly once the
								accountant became aware of
								the
								opening of the
								client’s New York
								office.
							</fo:block>
							<fo:block space-after="0.3in" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									DISCUSSION
								</fo:inline>
							</fo:block>
							<fo:block font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								This claim demonstrates the importance of an annual
								retention letter
								clearly outlining the
								precise scope of a
								retention, and the importance of not deviating from
								same. The
								most
								important reason to use a retention letter is to avoid
								misunderstandings
								about the
								services to be performed. In essence,
								the retention letter serves to
								document the
								parameters of the
								duties agreed to by the Accountant and the client and
								acts as a
								contract
								between the parties. This document provides protective
								wording and
								serves as a basis of
								defense for an Accountant. The
								retention letter can protect the
								Accountant from legal
								liability
								and can also help to maintain smooth client relations.
							</fo:block>
							<fo:block font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								In
								preparing tax returns, an accountant must be mindful of Internal
								Revenue Code §§6662
								and 6694, which allow for accuracy-related
								penalties and
								return-preparer penalties,
								respectively. It should
								be
								noted that such tax preparer penalties are not
								typically
								covered
								under an accountant’s professional liability policy.
							</fo:block>
						</fo:block>
						<fo:block page-break-before="always" />
					</xsl:if>
					<xsl:if
						test="(response/AOP_Percentage_4 != '' and response/AOP_Percentage_4 != '0')">
						<fo:block margin-top="8mm">
							<fo:block space-after="0.5in" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									BOOKKEEPING CLAIM SCENARIOS 
								</fo:inline>
							</fo:block>
							<fo:block space-after="5mm" font-size="16px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								<fo:inline font-weight="bold">
									BANK ACCOUNT RECONCILIATIONS
								</fo:inline>
							</fo:block>
							<fo:block space-after="0.5in" font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								The client retained the insured to reconcile the operating
								account
								of the client on a
								monthly basis, in order to determine if
								checks paid to vendors were
								clearing on a timely
								basis. The
								insured
								was actually performing the reconciliation every six
								months, and
								during
								one such time period a vendor's check were
								outstanding for
								five
								months of same. The
								checks had been sent to a
								wrong address,
								the situation was not
								addressed and the
								business
								relationship was
								terminated by the vendor. The insured made a
								claim against the
								bookkeeper for reputational damages, and argued
								that the
								insured's breach
								of duty caused
								the issue to go
								undetected.
							</fo:block>
							<fo:block space-after="5mm" font-size="16px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								<fo:inline font-weight="bold">
									ACCOUNTS RECEIVABLE
								</fo:inline>
							</fo:block>
							<fo:block space-after="0.5in" font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								The insured was retained to reconcile deposits of income
								from the
								client's customers,
								with the client's account receivable.
								One of the client's largest
								customers had a large
								outstanding
								receivable that was mistakenly overlooked by the insured for a
								period of 16
								months. By the time the issue came to light, the
								customer had filed for
								bankruptcy, and
								the client was ultimately
								unable to collect the amount owed. The
								client brought a claim
								for
								recovery of the amount against the insured.
							</fo:block>
							<fo:block space-after="5mm" font-size="16px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								<fo:inline font-weight="bold">
									PAYROLL SERVICES
								</fo:inline>
							</fo:block>
							<fo:block space-after="0.6in" font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								The client hired the insured to handle its payroll, on an
								outsourced
								basis. The insured
								mistakenly overpaid a sales rep of
								the client by a significant amount, and
								the sales rep
								terminated
								her employment with the client before the client detected the
								error. The client
								sought to recover the overpayment from the
								insureds.
							</fo:block>
							<fo:block space-after="5mm" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									DISCUSSION
								</fo:inline>
							</fo:block>
							<fo:block font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								Claims against Accountants for bookkeeping services are
								less frequent
								and may involve a
								wide variety of circumstances.
								Often, claims may arise when a client
								has expectations that
								are
								not
								justified, or when an Accountant fails to perform his work
								on
								a
								timely basis. These
								three claim scenarios illustrate typical
								claims against bookkeepers,
								and represent the
								types of claims
								covered under an accountant’s professional liability
								policy.
							</fo:block>
						</fo:block>
						<fo:block page-break-before="always" />
					</xsl:if>
					<xsl:if
						test="(response/AOP_Percentage_8 != '' and response/AOP_Percentage_8 != '0')">
						<fo:block margin-top="8mm">
							<fo:block space-after="0.3in" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									COMPILATION CLAIM SCENARIO
								</fo:inline>
							</fo:block>
							<fo:block space-after="0.4in" font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								An accounting firm was retained to review and compile
								financial
								reports at the request of a
								Texas law firm.  Relying
								upon
								the belief that the engagement was “low
								risk”, the accounting
								firm
								did not staff the engagement with a partner experienced in
								the
								accounting issues
								faced by law firms.  The accounting manager
								at
								the law firm,
								therefore, was able to cover
								up the fact that
								certain
								significant accounts payable
								payees/amounts were
								irregular, and
								suspicious.  The accounting manager was thereafter
								found to have
								embezzled
								from the law
								firm.  The law firm in turn
								sought to
								recover all amounts embezzled
								from the firm after the
								accounting
								firm was retained, upon the theory that it should have
								been
								apparent to the
								accounting firm that the embezzlement was
								occurring, simply because of
								standard debt to
								equity ratios common
								to law firms.  Although the accounting firm relied
								upon the
								defense
								that the scope of its representation did not include the
								level of
								review necessary to support
								a claim against it, the
								client
								argued that the accounting firm was
								negligent in improperly
								staffing the engagement.  This argument allowed the claim to
								proceed past
								the pleading
								stage in a lawsuit, and the claim was
								accordingly thereafter settled. 
							</fo:block>
							<fo:block space-after="5mm" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									DISCUSSION
								</fo:inline>
							</fo:block>
							<fo:block space-after="5mm" font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								Claims that arise from reviews or compilations generally
								have lower
								damage awards than
								audit claims. Clients often have
								expectations that are inaccurate. In
								a review or compilation,
								the
								client may not understand that an Accountant does not verify the
								information in a
								financial statement.
							</fo:block>
							<fo:block margin-top="1in">
								<xsl:choose>
									<xsl:when test="response/environmentproduction = 'Y'">
										<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/3.PNG"
											content-height="11in" content-width="7.5in" />
									</xsl:when>
									<xsl:otherwise>
										<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/3.PNG"
											content-height="11in" content-width="7.5in" />
									</xsl:otherwise>
								</xsl:choose>
							</fo:block>
						</fo:block>
						<fo:block page-break-before="always" />
					</xsl:if>
					<xsl:if
						test="(response/AOP_Percentage_6 != '' and response/AOP_Percentage_6 != '0')">
						<fo:block margin-top="8mm">
							<fo:block space-after="5mm" font-size="25px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="maroon">
								<fo:inline font-weight="bold">
									AUDIT CLAIM SCENARIO
								</fo:inline>
							</fo:block>
							<fo:block space-after="0.5in" font-size="12px"
								font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
								color="black">
								An accounting firm was engaged to audit the financial
								statements of
								a small independent
								school in Colorado, during a
								year
								when the school was also kicking off
								a major renovation.
								 The
								renovation was to be funded through a capital campaign and a $4
								million loan from a
								local bank.  The engagement was difficult
								from
								the start, because the
								school’s CFO was
								uncooperative.
								 However,
								the accounting firm partner did not bring the
								difficulties she
								encountered to the attention of her superiors at
								her firm, or to
								the
								attention of the school’s
								board of trustees.
								 The accounting
								firm issued an unqualified audit
								opinion, and the
								capital
								campaign
								was a quick success.  In addition, the local bank
								issued the
								loan
								based upon the
								school’s audited financials.
								However, 8 months
								later the CFO was found to
								have embezzled
								a
								significant amount of
								money from the loan proceeds, and to have
								misrepresented the
								financial health of the school to the
								accounting firm in order
								for the
								unqualified audit
								opinion to be
								issued.  Shortly
								thereafter, a claim was made against the
								accounting firm and
								the
								individual audit partner by the bank and
								the capital campaign
								donors for failure to
								detect the fraud.
							</fo:block>
							<fo:table>
								<fo:table-column column-width="105mm" />
								<fo:table-column column-width="80mm" />
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<fo:block space-after="5mm" font-size="25px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="maroon">
												<fo:inline font-weight="bold">
													DISCUSSION
												</fo:inline>
											</fo:block>
											<fo:block space-after="4mm" font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												Clients typically file claims alleging breach of
												contract,
												negligence, or breach of fiduciary duty, based on
												the
												Accountant's failure to:
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;Discover an employee's embezzlement
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;Disclose fraud discovered during an audit
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;Disclose weaknesses in the Client's internal
												controls
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												•&#160;Detect and disclose material misstatements in
												the
											</fo:block>
											<fo:block space-after="6mm" font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												Client's financial statements
											</fo:block>
											<fo:block font-size="12px"
												font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
												color="black">
												Claims by non-Clients, or third parties, are usually
												similar to those filed by clients. In this case, the third
												party included the local bank and the capital campaign
												donors
												who relied on audit opinions in extending a loan
												to the
												client. This claim was difficult to defend because
												of the
												inability to demonstrate that the audit partner
												followed best
												practices by raising the difficulties
												encountered, and lack
												of
												cooperation, to her
												management and the management of the
												client.
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block margin-left="6mm">
											<xsl:choose>
												<xsl:when test="response/environmentproduction = 'Y'">
													<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/4.PNG"
														content-height="7in" content-width="3in" />
												</xsl:when>
												<xsl:otherwise>
													<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/4.PNG"
														content-height="7in" content-width="3in" />
												</xsl:otherwise>
											</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:block>
						<fo:block page-break-before="always" />
					</xsl:if>
					<xsl:choose>
						<xsl:when
							test="((response/AOP_Percentage_7 = '' or response/AOP_Percentage_7 = '0') and (response/AOP_Percentage_6 = '' or response/AOP_Percentage_6 = '0') and (response/AOP_Percentage_8 = '' or response/AOP_Percentage_8 = '0'))">
							<fo:block margin-top="8mm">
								<fo:block space-after="5mm" font-size="25px"
									font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
									color="maroon">
									<fo:inline font-weight="bold">
										BEST PRACTICES
									</fo:inline>
								</fo:block>
								<fo:block space-after="0.5in" font-size="12px"
									font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
									color="black">
									To avoid a malpractice claim, accountants in your area(s)
									of
									practice should implement the
									following best practices.
								</fo:block>
								<fo:table>
									<fo:table-column column-width="100mm" />
									<fo:table-column column-width="80mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="23mm" />
													<fo:table-column column-width="80mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																<xsl:choose>
																	<xsl:when test="response/environmentproduction = 'Y'">
																		<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n1.PNG" />
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n1.PNG" />
																	</xsl:otherwise>
																</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Avoid situations that could
																	present conflicts of
																	interest
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n2.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n2.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use detailed engagement letters
																	for all engagements
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n3.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n3.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Keep existing clients informed of
																	the status of
																	their engagements
																	at all times.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n4.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n4.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Avoid giving specific warranties
																	or performance
																	guarantees
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n5.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n5.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Maintain detailed, written
																	documentation of all
																	activity with
																	clients, including telephone calls
																	and
																	billing calculations.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n6.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n6.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Closely supervise junior
																	Accountants to ensure the
																	accuracy of their work. Extensive
																	reviews by the
																	manager/partner
																	should be completed before
																	finalizing any
																	work, since the
																	partner is responsible for work
																	performed
																	by staff Accountants.
																	Train and orient new Accountants
																	with their duties and
																	responsibilities, and provide
																	checklists for them to follow.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:table-cell>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="23mm" />
													<fo:table-column column-width="70mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n7.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n7.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use effective billing procedures.
																	Often, claims
																	arise from billing
																	and fee disputes. Generally, it is
																	important to first determine why
																	a bill was unpaid by a
																	client and
																	then try to negotiate a payment
																	arrangement.
																	This gives the
																	Accountant an opportunity to·
																	learn if the
																	client was not pleased
																	with his services, and may also
																	present a chance to correct
																	problems before a dispute
																	escalates. Open and effective
																	communication is important
																	in
																	maintaining a productive
																	Accountant-Client relationship
																	so that claims do not arise from
																	escalated billing
																	issues.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n8.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n8.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Maintain adequate APL insurance
																	coverage.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block></fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.7in" text-align="right"
																	space-after="7mm">
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/line2.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/line2.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
																<fo:block space-after="5mm" font-size="12px"
																	font-family="Segoe Script,serif;" color="black"
																	text-align="right" font-style="italic">
																	If you have any questions about professional
																	liability
																	insurance or your policy you can
																	contact us call us at
																	(888) 803-9898. Visit
																	<fo:inline color="maroon">www.protexureaccountants.com
																	</fo:inline>
																	to learn more
																	about our program and how Protexure
																	Accountants can help your CPA firm.
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</xsl:when>
						<xsl:when
							test="((response/AOP_Percentage_6 = '' or response/AOP_Percentage_6 = '0') and ((response/AOP_Percentage_8 != '' and response/AOP_Percentage_8 != '0') or (response/AOP_Percentage_7 != '' and response/AOP_Percentage_7 != '0')))">
							<fo:block margin-top="8mm">
								<fo:block space-after="5mm" font-size="25px"
									font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
									color="maroon">
									<fo:inline font-weight="bold">
										BEST PRACTICES
									</fo:inline>
								</fo:block>
								<fo:block space-after="7mm" font-size="12px"
									font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
									color="black">
									To avoid a malpractice claim, accountants in your area(s)
									of
									practice should implement the
									following best practices.
								</fo:block>
								<fo:table>
									<fo:table-column column-width="90mm" />
									<fo:table-column column-width="80mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="22mm" />
													<fo:table-column column-width="70mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n1.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n1.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.6in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Avoid situations that could present conflicts of
																	interest
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n2.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n2.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use caution when selecting
																	engagements. Carefully
																	screening clients may reveal
																	potential risks. Financially
																	distressed organizations tend
																	to have many claims made
																	against them and, therefore,
																	have a greater potential for
																	future claims. Creditors,
																	regulators and stockholders are
																	more apt to sue Accountants
																	for allegedly failing to find
																	or
																	disclose problems with these
																	companies' financial
																	records.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n3.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n3.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.6in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use detailed engagement
																	letters for all engagements
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:table-cell>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="22mm" />
													<fo:table-column column-width="70mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n4.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n4.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.6in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Avoid giving specific warranties
																	or performance
																	guarantees
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n5.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n5.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.3in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use effective billing procedures.
																	Often, claims
																	arise from billing and
																	fee disputes. Generally, it is
																	important to first determine why a
																	bill was unpaid by a
																	client and
																	then try to negotiate a payment
																	arrangement.
																	This gives the
																	Accountant an opportunity to·
																	learn if the
																	client was not pleased
																	with his services, and may also
																	present a chance to correct
																	problems before a dispute
																	escalates. Open and effective
																	communication is important
																	in
																	maintaining a productive
																	Accountant-Client relationship
																	so
																	that claims do not arise from
																	escalated billing issues.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
								<fo:block margin-top="0.3in">
									<xsl:choose>
										<xsl:when test="response/environmentproduction = 'Y'">
											<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/6.PNG"
										content-height="9.5in" content-width="7.5in" />
										</xsl:when>
										<xsl:otherwise>
											<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/6.PNG"
										content-height="9.5in" content-width="7.5in" />
										</xsl:otherwise>
									</xsl:choose>
								</fo:block>
							</fo:block>
							<fo:block page-break-before="always" />
							<fo:block margin-top="8mm">
								<fo:table>
									<fo:table-column column-width="80mm" />
									<fo:table-column column-width="100mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													<xsl:choose>
														<xsl:when test="response/environmentproduction = 'Y'">
															<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/7.PNG"
																content-height="9in" content-width="3in" />
														</xsl:when>
														<xsl:otherwise>
															<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/7.PNG"
																content-height="9in" content-width="3in" />
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="22mm" />
													<fo:table-column column-width="80mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n6.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n6.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="1in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Closely supervise junior
																	Accountants to ensure the
																	accuracy
																	of their work. Extensive reviews by
																	the
																	manager/partner should be
																	completed before finalizing any
																	work, since the partner is
																	responsible for work performed
																	by
																	staff Accountants. Train and orient
																	new Accountants
																	with
																	their duties
																	and responsibilities, and provide
																	checklists
																	for them to follow.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n7.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n7.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.5in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Maintain detailed, written
																	documentation of all
																	activity with
																	clients, including telephone calls
																	and
																	billing calculations.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n8.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n8.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.5in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Keep existing clients informed of
																	the status of
																	their engagements
																	at all times.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n9.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n9.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.5in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Maintain adequate APL insurance
																	coverage.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:block margin-top="0.6in" margin-left="0.8in"
													space-after="4mm" text-align="center" font-size="12px"
													font-family="Segoe Script,serif;" color="black" font-style="italic">
													If you have any questions about professional
													liability
													insurance or your policy you can
													contact us call us at (888)
													803-9898. Visit
													<fo:inline color="maroon">www.protexureaccountants.com
													</fo:inline>
													to learn
													more about our program and how Protexure
													Accountants
													can help your CPA firm.
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</xsl:when>
						<xsl:otherwise>
							<fo:block margin-top="8mm">
								<fo:block space-after="6mm" font-size="25px"
									font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
									color="maroon">
									<fo:inline font-weight="bold">
										BEST PRACTICES
									</fo:inline>
								</fo:block>
								<fo:block space-after="0.5in" font-size="12px"
									font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
									color="black">
									To avoid a malpractice claim, accountants in your area(s)
									of
									practice should implement the
									following best practices.
								</fo:block>
								<fo:table>
									<fo:table-column column-width="100mm" />
									<fo:table-column column-width="80mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="22mm" />
													<fo:table-column column-width="70mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n1.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n1.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Avoid situations that could present conflicts
																	of
																	interest
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n2.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n2.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Reveal adverse information
																	and issue disclaimers.
																	Any
																	adverse information must be
																	completely revealed when
																	preparing financial reports. An
																	Accountant must be
																	straightforward in revealing
																	adverse information to help
																	minimize the possibility of
																	claims filed by a third
																	party.
																	If
																	any doubt exists about the
																	accuracy of the
																	information,
																	a
																	disclaimer may provide a
																	strong defense to
																	allegations
																	of
																	negligence
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n3.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n3.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Follow GAAP and GAAS
																	guidelines, and document that
																	the guidelines were followed.
																	This is one of the
																	strongest
																	defenses to allegations of
																	negligence. Any
																	engagement
																	should include documentation
																	of the
																	Accountant's
																	compliance to these
																	standards. Ensure
																	compliance
																	with GAAS
																	and the Code of
																	Professional Conduct.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:table-cell>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="22mm" />
													<fo:table-column column-width="70mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n4.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n4.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use detailed engagement letters for all
																	engagements
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n5.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n5.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Closely supervise junior
																	Accountants to ensure the
																	accuracy of their work.
																	Extensive reviews by the
																	manager/partner should be
																	completed before finalizing
																	any
																	work, since the partner is
																	responsible for work
																	performed
																	by staff
																	Accountants. Train and orient
																	new Accountants
																	with
																	their
																	duties and responsibilities,
																	and provide
																	checklists
																	for
																	them to follow.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:block margin-left="0.3in">
													<xsl:choose>
														<xsl:when test="response/environmentproduction = 'Y'">
															<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/5.PNG"
																content-height="4in" content-width="3.5in" />
														</xsl:when>
														<xsl:otherwise>
															<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/5.PNG"
																content-height="4in" content-width="3.5in" />
														</xsl:otherwise>
													</xsl:choose>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
							<fo:block page-break-before="always" />
							<fo:block margin-top="8mm">
								<fo:table>
									<fo:table-column column-width="100mm" />
									<fo:table-column column-width="80mm" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="22mm" />
													<fo:table-column column-width="70mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n6.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n6.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use effective billing procedures.
																	Often, claims
																	arise from billing
																	and fee disputes. Generally, it is
																	important to first determine
																	why a bill was unpaid by a
																	client
																	and then try to negotiate a
																	payment arrangement.
																	This
																	gives the Accountant an
																	opportunity to· learn if the
																	client was not pleased with his
																	services, and may also
																	present a
																	chance to correct problems
																	before a dispute
																	escalates. Open
																	and effective communication is
																	important
																	in
																	maintaining a
																	productive Accountant-Client
																	relationship
																	so
																	that claims do
																	not arise from escalated billing
																	issues.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n7.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n7.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Ensure that Auditors prepare
																	a plan and follow
																	certain
																	procedures that will increase
																	their ability to
																	detect fraud
																	and the misappropriation of
																	assets.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n8.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n8.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Avoid giving specific
																	warranties or performance
																	guarantees
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:table-cell>
											<fo:table-cell>
												<fo:table>
													<fo:table-column column-width="22mm" />
													<fo:table-column column-width="70mm" />
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n9.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n9.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.1in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Use caution when selecting
																	engagements. Carefully
																	screening
																	clients may reveal potential
																	risks. Financially
																	distressed
																	organizations tend to have many
																	claims made
																	against them and,
																	therefore, have a greater potential
																	for
																	future claims. Creditors,
																	regulators and stockholders are
																	more apt to sue Accountants for
																	allegedly failing to find
																	or disclose
																	problems with these companies'
																	financial
																	records.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n10.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n10.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Keep existing clients informed of
																	the status of
																	their engagements at
																	all times.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n11.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n11.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Maintain detailed, written
																	documentation of all
																	activity
																	with clients, including telephone
																	calls and
																	billing calculations.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>
																	<xsl:choose>
																		<xsl:when test="response/environmentproduction = 'Y'">
																			<fo:external-graphic src="C:/Tomcat/lawyers_webapps/LawyersIns/image/n12.PNG" />
																		</xsl:when>
																		<xsl:otherwise>
																			<fo:external-graphic src="C:/Tomcat/webapps/LawyersIns/image/n12.PNG" />
																		</xsl:otherwise>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block margin-top="0.2in"></fo:block>
																<fo:block space-after="0.4in" font-size="12px"
																	font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;"
																	color="black">
																	Maintain adequate APL insurance
																	coverage.
																</fo:block>
																<fo:block></fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
								<fo:block margin-top="0.8in" text-align="center"
									font-size="12px" font-family="Segoe Script,serif;" color="black"
									font-style="italic">
									If you have any questions about professional liability
									insurance
									or your policy you can contact
									us call us at (888) 803-9898.
									Visit
									<fo:inline color="maroon">www.protexureaccountants.com
									</fo:inline>
									to learn more about our
									program and how Protexure Accountants can
									help your CPA firm.
								</fo:block>
							</fo:block>
						</xsl:otherwise>
					</xsl:choose>
					<fo:block id="TheVeryLastPage">
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>

	</xsl:template>
</xsl:stylesheet>
          
          