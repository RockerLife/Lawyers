<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="LPL-AMEND-NM2">
	<fo:block>				  	
			<xsl:call-template name="CommonHeader" />						           	
		  </fo:block>
	
	<fo:block margin-top="1cm"></fo:block>
				    <fo:block font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center">
            NEW MEXICO AMENDATORY ENDORSEMENT
          </fo:block>
          <fo:block font-size="12px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold" text-align="center" space-after="8mm">
            (DEFENSE EXPENSES WITHIN THE LIMIT AND DEDUCTIBLE)
          </fo:block>

          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm">
            In consideration of the premium charged, it is agreed that:
          </fo:block>


          <fo:block>
            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                    Section II., Limits of Liability and Deductible, is amended as follows:
                  </fo:block>
                  <fo:block>
                    <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block>
                            <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">a.</fo:inline>
                          </fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            Subsection A., Limits of Liability, paragraphs 1., 2. and 3., are deleted and replaced with the following:
                          </fo:block>
                          <fo:block>            
                            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                              <fo:list-item>
                                <fo:list-item-label start-indent="14mm">
                                  <fo:block>
                                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                                  </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body  start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    Subject to 2. that follows, the <fo:inline font-weight="bold">Insurer’s</fo:inline> Limit of Liability for <fo:inline font-weight="bold">Damages</fo:inline> and <fo:inline font-weight="bold">Defense Expenses</fo:inline>, for each <fo:inline font-weight="bold">Claim</fo:inline> first made and reported to the <fo:inline font-weight="bold">Insurer</fo:inline> during the <fo:inline font-weight="bold">Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amount shown in Item 3. of the Declarations as applicable to "each <fo:inline font-weight="bold">Claim</fo:inline>".
                                  </fo:block>
                                </fo:list-item-body>
                              </fo:list-item>
                              
                              <fo:list-item>
                                <fo:list-item-label start-indent="14mm">
                                  <fo:block>
                                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                                  </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body  start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    Subject to 1. above, the <fo:inline font-weight="bold">Insurer's</fo:inline> Limit of Liability for <fo:inline font-weight="bold">Damages</fo:inline> and <fo:inline font-weight="bold">Defense Expenses</fo:inline>, for all <fo:inline font-weight="bold">Claims</fo:inline> first made and reported to the <fo:inline font-weight="bold">Insurer</fo:inline> during the <fo:inline font-weight="bold">Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amount shown in Item 3.  of the Declarations as applicable to "<fo:inline font-weight="bold">Policy Aggregate</fo:inline>".
                                  </fo:block>
                                </fo:list-item-body>
                              </fo:list-item>

                              <fo:list-item>
                                <fo:list-item-label start-indent="14mm">
                                  <fo:block>
                                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                                  </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body  start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                                    Subject to 1. and 2. above, <fo:inline font-weight="bold">Defense Expenses</fo:inline> charged against the Limit of Liability shall not exceed 50% of such Limit of Liability and the Insurer shall assume any <fo:inline font-weight="bold">Defense Expenses</fo:inline> over that percentage, except for those due to any offset against the Deductible.
                                  </fo:block>
                                </fo:list-item-body>
                              </fo:list-item>
                              
                            </fo:list-block>
                        </fo:block>
                        <fo:block margin-top="3mm"></fo:block>
                        </fo:list-item-body>
                      </fo:list-item>

                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block>
                            <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">b.</fo:inline>
                          </fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            Subsection B., Deductible, is deleted and replaced with the following:
                          </fo:block>
                          <fo:block>
                            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                              <fo:list-item>
                                <fo:list-item-label start-indent="14mm">
                                  <fo:block>
                                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold">B.</fo:inline>
                                  </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body  start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" font-weight="bold" text-align="justify">
                                    Deductible
                                  </fo:block>
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    The <fo:inline font-weight="bold">Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-weight="bold">Damages</fo:inline> or <fo:inline font-weight="bold">Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4. of the Declarations. This Deductible amount shall:
                                  </fo:block>
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    1. &#160; &#160; apply separately to each and every <fo:inline font-weight="bold">Claim</fo:inline>;
                                  </fo:block>
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    2. &#160; &#160; be borne by the <fo:inline font-weight="bold">Insured</fo:inline>; and
                                  </fo:block>
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    3. &#160; &#160; remain uninsured.
                                  </fo:block>
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                                    <fo:inline font-weight="bold">Defense Expenses</fo:inline> charged against the Deductible shall not exceed 50% of such Deductible and the <fo:inline font-weight="bold">Insurer</fo:inline> shall assume any <fo:inline font-weight="bold">Defense Expenses</fo:inline> over that percentage, except for those due to any offset against the Limit of Liability. If the <fo:inline font-weight="bold">Insurer</fo:inline> advances any amounts within the Deductible, the <fo:inline font-weight="bold">Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold">Insurer</fo:inline> within 30 days of the <fo:inline font-weight="bold">Insurer's</fo:inline> request to do so. In the event of <fo:inline font-weight="bold">Related Claims</fo:inline>, a single Deductible amount will apply.
                                  </fo:block>
                                </fo:list-item-body>
                              </fo:list-item>
                              
                              
                            </fo:list-block>
                          </fo:block>
                          <fo:block margin-top="3mm"></fo:block>
                        </fo:list-item-body>
                      </fo:list-item>

                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block>
                            <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">c.</fo:inline>
                          </fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            The following subsection is added:
                          </fo:block>
                          <fo:block>
                            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                              <fo:list-item>
                                <fo:list-item-label start-indent="14mm">
                                  <fo:block>
                                    &#x2022;
                                  </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body  start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" font-weight="bold" text-align="justify">
                                    Accounting
                                  </fo:block>
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                                    Where the Limit of Liability of the policy is reduced by <fo:inline font-weight="bold">Defense Expenses</fo:inline> or where <fo:inline font-weight="bold">Defense Expenses</fo:inline> are applied against the Deductible, the Insurer shall notify the <fo:inline font-weight="bold">Named Insured</fo:inline> of its right, upon written request, to an accounting of <fo:inline font-weight="bold">Defense Expenses</fo:inline> actually expended.
                                  </fo:block>
                                  <fo:block break-after="page"/>
                                </fo:list-item-body>
                              </fo:list-item>
                            </fo:list-block>
                          </fo:block>
                        </fo:list-item-body>
                      </fo:list-item>
                      
                    </fo:list-block>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                    Subsection IV., Definition M., <fo:inline font-weight="bold">Policy Aggregate</fo:inline>, is deleted and replaced with the following:
                  </fo:block>
                  <fo:block>
                    <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                      <fo:list-item>
                        <fo:list-item-label  start-indent="7mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-weight="bold">
                            M.
                          </fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                            <fo:inline font-weight="bold">Policy Aggregate</fo:inline> means the amount shown in Item 3. of the Declarations which represents the maximum amount of the <fo:inline font-weight="bold">Insurer's</fo:inline> liability for:
                          </fo:block>
                          <fo:block>
                            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                              <fo:list-item>
                                <fo:list-item-label  start-indent="14mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">
                                    1.
                                  </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    all <fo:inline font-weight="bold">Damages</fo:inline>; and
                                  </fo:block>
                                </fo:list-item-body>
                              </fo:list-item>

                              <fo:list-item>
                                <fo:list-item-label  start-indent="14mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" >
                                    2.
                                  </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                                    <fo:inline font-weight="bold">Defense Expenses</fo:inline> up to 50% of the Limit of Liability,
                                  </fo:block>                                  
                                </fo:list-item-body>
                              </fo:list-item>                              
                            </fo:list-block>
                            <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                              for all <fo:inline font-weight="bold">Claims</fo:inline> under this policy, inclusive of any applicable Extended Reporting Period.
                            </fo:block>
                          </fo:block>                                                   
                        </fo:list-item-body>
                      </fo:list-item>
                    </fo:list-block>
                  </fo:block>
                  <fo:block margin-top="7mm"></fo:block>
                  
                </fo:list-item-body>
              </fo:list-item>
               <fo:list-item>
              
              <fo:list-item-label>
              <fo:block></fo:block></fo:list-item-label>
              <fo:list-item-body>
              <fo:block margin-top="3mm"></fo:block>
              <fo:block  font-size="10px" color="grey" text-align="left">LPL-AMEND-NM2 (04/19)
              <fo:block margin-top="-0.4cm" font-size="10px" color="grey" text-align="right">Page 1 of 2</fo:block>
              </fo:block>
			 </fo:list-item-body>
              </fo:list-item>
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">3.</fo:inline>
                  </fo:block>
                  <fo:block margin-top="3mm"></fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                    Section VI., General Condition B., Defense and Settlement, paragraph 1., is deleted and replaced with the following:
                  </fo:block>
                    <fo:block>            
                    <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            The <fo:inline font-weight="bold">Insurer</fo:inline> shall have the right and the duty to defend any <fo:inline font-weight="bold">Claim</fo:inline> regardless of whether the allegations are groundless, false, or fraudulent. In undertaking this right and duty, the <fo:inline font-weight="bold">Insurer</fo:inline> expressly retains the right to select defense counsel even when the <fo:inline font-weight="bold">Insurer</fo:inline> reserves its rights on issues concerning the applicability of coverage under this policy. The <fo:inline font-weight="bold">Insurer's</fo:inline> right and duty to defend any <fo:inline font-weight="bold">Claim</fo:inline> and pay <fo:inline font-weight="bold">Defense Expenses</fo:inline> shall terminate upon the exhaustion of the Limit of Liability, whereupon the <fo:inline font-weight="bold">Insurer</fo:inline> shall have no further obligation or liability to defend the Insured or to pay <fo:inline font-weight="bold">Defense Expenses</fo:inline>, judgments or settlements. The <fo:inline font-weight="bold">Insurer</fo:inline> may make any investigation it deems necessary and may, with the <fo:inline font-weight="bold">Insured's</fo:inline> consent, such consent not to be unreasonably withheld, make any settlement of any <fo:inline font-weight="bold">Claim</fo:inline> it deems expedient. If the <fo:inline font-weight="bold">Insured</fo:inline> withholds consent of such settlement, the <fo:inline font-weight="bold">Insurer’s</fo:inline> liability for all <fo:inline font-weight="bold">Damages</fo:inline> on account of such <fo:inline font-weight="bold">Claim</fo:inline> shall not exceed the amount for which the Insurer could have settled such <fo:inline font-weight="bold">Claim</fo:inline>, inclusive of <fo:inline font-weight="bold">Defense Expenses,</fo:inline> incurred as of the date such settlement was proposed to the <fo:inline font-weight="bold">Insured</fo:inline>.
                          </fo:block>
                        </fo:list-item-body>
                      </fo:list-item>
                    </fo:list-block>
                    </fo:block>
                    <fo:block margin-top="3mm"></fo:block>
                </fo:list-item-body>
              </fo:list-item>
              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">4.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                    Section VI., General Condition D., Cancellation and Nonrenewal, is amended as follows:
                  </fo:block>
                  <fo:block>
                    <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">a.</fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            Paragraph 2. is deleted and replaced with the following:
                          </fo:block>

                          <fo:block>
                            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                              <fo:list-item>
                                <fo:list-item-label start-indent="14mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                                    The <fo:inline font-weight="bold">Insurer</fo:inline> may only cancel this policy for nonpayment of premium. This policy may be canceled by the <fo:inline font-weight="bold">Insurer</fo:inline> by mailing postage-paid or personally delivering to the <fo:inline font-weight="bold">Named Insured</fo:inline>, at its address last of record with the <fo:inline font-weight="bold">Insurer</fo:inline>, written notice of cancellation at least 10 days before the effective date of cancellation. Notice so mailed shall be deemed given when deposited in a mail depository of the U.S. Post Office, and the effective date of cancellation stated in such notice shall become the expiration date of the <fo:inline font-weight="bold">Policy Period</fo:inline>. If the <fo:inline font-weight="bold">Insurer</fo:inline> cancels this policy, the <fo:inline font-weight="bold">Insurer</fo:inline> shall credit the <fo:inline font-weight="bold">Named Insured</fo:inline> the pro rata unearned portion of the premium. Payment or tender of any unearned premium by the <fo:inline font-weight="bold">Insurer</fo:inline> shall not be a condition precedent to the effectiveness of cancellation, but such payment shall be made as soon as practicable.
                                  </fo:block>
                                </fo:list-item-body>
                              </fo:list-item>
                            </fo:list-block>
                          </fo:block>
                          <fo:block margin-top="3mm"></fo:block>
                          
                        </fo:list-item-body>
                      </fo:list-item>
                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">b.</fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            Paragraph 3. is amended by addition of the following:
                          </fo:block>
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            This paragraph shall not apply to the transfer of a policy upon its expiration to an affiliated insurer. If the <fo:inline font-weight="bold">Insurer</fo:inline>, upon expiration of the policy, transfers the policy to an affiliated insurer, the transferring <fo:inline font-weight="bold">Insurer</fo:inline> shall provide written notice of change of insurer to the agent or the <fo:inline font-weight="bold">Named Insured</fo:inline> prior to the expiration date of the policy and shall include the name and contact information of the insurer accepting the transferred policy and the reason for the transfer.
                          </fo:block>
                          <fo:block margin-top="3mm"></fo:block>
                        </fo:list-item-body>
                      </fo:list-item>

                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">c.</fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            The following paragraph is added:
                          </fo:block>

                          <fo:block>
                            <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                              <fo:list-item>
                                <fo:list-item-label start-indent="14mm">
                                  <fo:block>&#x2022;</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="21mm">
                                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                                    The <fo:inline font-weight="bold">Insurer</fo:inline> shall provide written notice to the agent or the <fo:inline font-weight="bold">Named Insured</fo:inline> of any change in policy form not less than 30 days prior to the effective date of the change. Change in policy form means any change of limitation, restriction in coverage, or change in the Deductible.
                                  </fo:block>
                                </fo:list-item-body>
                              </fo:list-item>
                            </fo:list-block>
                          </fo:block>

                        </fo:list-item-body>
                      </fo:list-item>
                      
                    </fo:list-block>
                  </fo:block>
                  <fo:block margin-top="3mm"></fo:block>
                </fo:list-item-body>
              </fo:list-item>


              <fo:list-item>
                <fo:list-item-label>
                  <fo:block>
                    <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">5.</fo:inline>
                  </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="7mm">
                  <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                    Section VI., General Condition M., <fo:inline font-weight="bold">Named Insured</fo:inline> Sole Agent, paragraph 2., is deleted and replaced with the following:
                  </fo:block>
                  <fo:block>
                    <fo:list-block provisional-distance-between-starts="0.8cm" provisional-label-separation="0.15cm">
                      <fo:list-item>
                        <fo:list-item-label start-indent="7mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">2.</fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="14mm">
                          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
                            for the completing of any applications and the making of any statements and representations for the policy; and
                          </fo:block>
                        </fo:list-item-body>
                      </fo:list-item>
                    </fo:list-block>
                  </fo:block>
                </fo:list-item-body>
              </fo:list-item>              
            </fo:list-block>
          </fo:block>
          <fo:block margin-top="1cm"></fo:block>
          <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" space-after="4mm" text-align="justify">
            All other terms, conditions and limitations of the policy remain unaltered.
          </fo:block>			       
	       	<fo:block margin-top="7.5cm"></fo:block>
                   <fo:block  font-size="10px" color="grey" text-align="left">LPL-AMEND-NM2 (04/19)
                   <fo:block margin-top="-0.4cm" font-size="10px" color="grey" text-align="right">Page 2 of 2</fo:block>
                   </fo:block>
				
     </xsl:template>
</xsl:stylesheet>




					
				    	