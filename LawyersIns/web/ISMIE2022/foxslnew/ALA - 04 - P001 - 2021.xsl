<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template name="Policycoverletter1" match="/">
	
		 
           <fo:block  color="grey" text-align="left">
			<fo:external-graphic
			src="../LawyersIns/image/ISMIE_logo.png" content-width="200px" />
			 </fo:block>
          
          	<fo:block margin-top="3mm" />
		<fo:block font-weight="bold" font-size="12px" text-align="center">
			LAWYERS PROFESSIONAL LIABILITY POLICY</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" text-align="left">
			In consideration of the payment of premium, the undertaking of the
			<fo:inline font-weight="bold">Insured</fo:inline> to pay the Deductible herein, and in reliance upon the
			application and supplements and all the information provided to the
			<fo:inline font-weight="bold">Insurer</fo:inline>, and subject to the Declarations, definitions, terms,
			conditions, limitations, representations, exclusions and endorsements
			herein or attached hereto, the <fo:inline font-weight="bold">Insurer</fo:inline> and the <fo:inline font-weight="bold">Insured</fo:inline> agree as
			follows:
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" 
			font-size="12px" text-align="left">
			
			I. INSURING AGREEMENT
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">A. Coverage</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			Subject to the Limit of Liability shown in Item 3. of the Declarations
			and as limited in Section II., the <fo:inline font-weight="bold">Insurer</fo:inline> shall pay on behalf of the
			<fo:inline font-weight="bold">Insured</fo:inline> all <fo:inline font-weight="bold">Damages</fo:inline> and <fo:inline font-weight="bold">Defense Expenses</fo:inline> in excess of the Deductible
			shown in Item 4. of the Declarations and as limited in Section II.,
			that the <fo:inline font-weight="bold">Insured</fo:inline> becomes legally obligated to pay as a result of a
			<fo:inline font-weight="bold">Claim</fo:inline> first made against the <fo:inline font-weight="bold">Insured</fo:inline> and reported in writing to the
			<fo:inline font-weight="bold">Insurer</fo:inline> during:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	the <fo:inline font-weight="bold">Policy Period</fo:inline>; or
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	any applicable Extended Reporting Period,
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			by reason of a negligent act, error or omission in the performance of
			<fo:inline font-weight="bold">Professional Services</fo:inline> by the <fo:inline font-weight="bold">Insured</fo:inline> or by someone for whom the <fo:inline font-weight="bold">Insured</fo:inline>
			is legally responsible, provided that such negligent act, error or
			omission began on or after the <fo:inline font-weight="bold">Prior Acts</fo:inline> Date shown in Item 6. of the
			Declarations.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">B. Defense</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			Subject to the terms, conditions and exclusions appearing in other
			Sections of this policy, the <fo:inline font-weight="bold">Insurer</fo:inline> has the right and duty to defend
			any covered <fo:inline font-weight="bold">Claim</fo:inline> including, but not limited to, the appointment of
			legal counsel, even if any of the allegations of the <fo:inline font-weight="bold">Claim</fo:inline> are
			groundless, false or fraudulent.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" 
			font-size="12px" text-align="left">
			
			II. LIMITS OF LIABILITY AND DEDUCTIBLE
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">A. Limits of Liability</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">
			1. Subject to 2. that follows, the <fo:inline font-weight="bold">Insurer’s</fo:inline> Limit of Liability for
			<fo:inline font-weight="bold">Damages</fo:inline> and <fo:inline font-weight="bold">Defense Expenses</fo:inline> combined, for 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">
			&#160;&#160;&#160;  each <fo:inline font-weight="bold">Claim</fo:inline> first made and
			reported to the <fo:inline font-weight="bold">Insurer</fo:inline> during the <fo:inline font-weight="bold">Policy Period</fo:inline>, including any
			applicable  
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">	
			&#160;&#160;&#160;  Extended Reporting Period, shall not exceed the amount shown
			in Item 3. of the Declarations as applicable 
			</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160;   to “each <fo:inline font-weight="bold">Claim</fo:inline>”.
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">
			2. Subject to 1. above, the <fo:inline font-weight="bold">Insurer’s</fo:inline> Limit of Liability for <fo:inline font-weight="bold">Damages</fo:inline>
			and <fo:inline font-weight="bold">Defense Expenses</fo:inline> combined, for all 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160;  <fo:inline font-weight="bold">Claims</fo:inline> first made and reported
			to the <fo:inline font-weight="bold">Insurer</fo:inline> during the <fo:inline font-weight="bold">Policy Period</fo:inline>, including any applicable
			Extended 
			</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160;  	Reporting Period, shall not exceed the amount shown in Item 3.
			of the Declarations as applicable to 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160; 	 “<fo:inline font-weight="bold">Policy Aggregate</fo:inline>”.
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">
			3.	<fo:inline font-weight="bold">Defense Expenses</fo:inline> are included within, and shall reduce, the applicable Limit of Liability available to pay 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160; 	 <fo:inline font-weight="bold">Damages</fo:inline>.
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">
			4.	The Limit of Liability shall apply excess of the Deductible amount.
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">
			5. The Limit of Liability available for <fo:inline font-weight="bold">Claims</fo:inline> first made against the
			<fo:inline font-weight="bold">Insured</fo:inline> and reported to the <fo:inline font-weight="bold">Insurer</fo:inline> 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160; 	 during any applicable Extended
			Reporting Period, is part of, and not in addition to the Limit of
			Liability   
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160; 	 shown in Item 3. of the Declarations. Applicable Extended
			Reporting Periods shall not provide a new,  
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160; 	 additional or renewed Limit of Liability.
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">
			6. If the <fo:inline font-weight="bold">Insurer</fo:inline> has exhausted the applicable Limit of Liability by
			payment of <fo:inline font-weight="bold">Damages</fo:inline> or <fo:inline font-weight="bold">Defense Expenses</fo:inline> 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160;  	or by tender of the remaining
			Limit of Liability into court, it shall have no further duties to the
			<fo:inline font-weight="bold">Insured</fo:inline> under  
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160;  	this policy.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">B. Deductible</fo:block>
		<fo:block margin-top="2mm" />

		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			The <fo:inline font-weight="bold">Insurer</fo:inline> shall only be liable for amounts payable under this policy
			for <fo:inline font-weight="bold">Damages</fo:inline> or <fo:inline font-weight="bold">Defense Expenses</fo:inline> which are in excess of the Deductible
			amount shown in Item 4. of the Declarations. This Deductible amount
			shall: 
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	apply separately to each and every <fo:inline font-weight="bold">Claim</fo:inline>;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	be borne by the <fo:inline font-weight="bold">Insured</fo:inline>; and
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			3.	remain uninsured.
		</fo:block>
		<fo:block margin-top="1.5cm"></fo:block>
		 		
			 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 12</fo:block>
        	 </fo:block>
			 

		<fo:block break-after="page" />
		
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			The Deductible amount applies to the payment of <fo:inline font-weight="bold">Damages</fo:inline> and <fo:inline font-weight="bold">Defense</fo:inline>
			<fo:inline font-weight="bold">&#160;Expenses</fo:inline>. If the <fo:inline font-weight="bold">Insurer</fo:inline> advances any amounts within the Deductible,
			the <fo:inline font-weight="bold">Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold">Insurer</fo:inline> within 30 days of the
			<fo:inline font-weight="bold">Insurer’s</fo:inline> request to do so. In the event of <fo:inline font-weight="bold">Related Claims</fo:inline>, a single
			Deductible amount will apply.
		</fo:block>
		
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">C. Multiple Insureds, Claims and Claimants
		</fo:block>
		<fo:block margin-top="2mm" />

		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			Regardless of the number of <fo:inline font-weight="bold">Claims</fo:inline>, <fo:inline font-weight="bold">Insureds</fo:inline> or claimants, the Limit of
			Liability shown in Item 3. of the Declarations as applicable to “each
			<fo:inline font-weight="bold">Claim</fo:inline>” and to “<fo:inline font-weight="bold">Policy Aggregate</fo:inline>” shall be subject to paragraph A. of
			this Section II. If <fo:inline font-weight="bold">Related Claims</fo:inline> are subsequently made against the
			<fo:inline font-weight="bold">Insured</fo:inline> and reported to the <fo:inline font-weight="bold">Insurer</fo:inline>, all such <fo:inline font-weight="bold">Related Claims</fo:inline>, whenever
			made, shall be considered a single <fo:inline font-weight="bold">Claim</fo:inline> first made and reported to the
			<fo:inline font-weight="bold">Insurer</fo:inline> within the policy period in which the earliest of the <fo:inline font-weight="bold">Related</fo:inline>
			<fo:inline font-weight="bold">&#160;Claims</fo:inline> was first made and reported to the <fo:inline font-weight="bold">Insurer</fo:inline>.
		</fo:block>
		
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold"
			font-size="12px" text-align="left">
			
			III. EXTENSIONS OF COVERAGE
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">A. Estates, Heirs, Bankruptcy Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			In the event of the death or incapacity of the <fo:inline font-weight="bold">Insured</fo:inline>, or the
			bankruptcy of the <fo:inline font-weight="bold">Insured</fo:inline>, any <fo:inline font-weight="bold">Claim</fo:inline> made against any heir, executor,
			administrator, assignee or legal representative of the <fo:inline font-weight="bold">Insured</fo:inline> or
			against any trustee in bankruptcy of the <fo:inline font-weight="bold">Insured</fo:inline>, which arises from any
			negligent act, error or omission of such <fo:inline font-weight="bold">Insured</fo:inline> rendering <fo:inline font-weight="bold">Professional</fo:inline>
			<fo:inline font-weight="bold">&#160;Services</fo:inline> shall be deemed to be a <fo:inline font-weight="bold">Claim</fo:inline> made against such <fo:inline font-weight="bold">Insured</fo:inline> for
			the purposes of this policy. Bankruptcy or insolvency of the <fo:inline font-weight="bold">Insured</fo:inline> or
			of the <fo:inline font-weight="bold">Insured’s</fo:inline> estate will not relieve the <fo:inline font-weight="bold">Insurer</fo:inline> of any of its
			obligations hereunder.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">B. Spousal and Domestic Partner Extension
		</fo:block>
		<fo:block margin-top="2mm" />

		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			If a <fo:inline font-weight="bold">Claim</fo:inline> is made against the lawful spouse or lawful domestic partner
			of an <fo:inline font-weight="bold">Insured</fo:inline> which includes a <fo:inline font-weight="bold">Claim</fo:inline> for a negligent act, error or
			omission made against an <fo:inline font-weight="bold">Insured</fo:inline> rendering <fo:inline font-weight="bold">Professional Services</fo:inline> then
			such <fo:inline font-weight="bold">Claim</fo:inline> shall be deemed a <fo:inline font-weight="bold">Claim</fo:inline> made against such <fo:inline font-weight="bold">Insured</fo:inline>, provided:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	his or her lawful spouse or lawful domestic partner accepts the same legal counsel as the <fo:inline font-weight="bold">Insured</fo:inline>; and 
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	such <fo:inline font-weight="bold">Claim</fo:inline> is made solely by reason of such lawful spouse’s or lawful domestic partner’s status as such.  
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			This extension, however, shall not apply to any <fo:inline font-weight="bold">Claim</fo:inline> alleging any
			negligent act, error or omission committed by the lawful spouse or
			lawful domestic partner of an <fo:inline font-weight="bold">Insured</fo:inline>.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">C. Personal Injury and Advertising Liability
			Extensions</fo:block>
		<fo:block margin-top="2mm" />

		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			Subject to all other terms, conditions and exclusions, this policy
			covers
			<fo:inline font-weight="bold">Damages</fo:inline>
			and
			<fo:inline font-weight="bold">Defense Expenses</fo:inline>
			the
			<fo:inline font-weight="bold">Insured</fo:inline>
			becomes legally obligated to pay resulting from
			<fo:inline font-weight="bold">Claims</fo:inline>
			arising out of
			<fo:inline font-weight="bold">Personal Injury</fo:inline>
			and
			<fo:inline font-weight="bold">Advertising Liability</fo:inline>.
			</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">D. Extended Reporting Period Extensions</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1.5cm" font-size="12px"
			text-align="left" space-after="3mm">1. Automatic Extended Reporting Period</fo:block>

		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			Upon the expiration of this policy for any reason other than for
			cancellation for nonpayment of premium or for nonpayment of Deductible
			due hereunder, the <fo:inline font-weight="bold">Named Insured</fo:inline> shall be provided with an automatic
			and non-cancelable period of 60 days, commencing on the policy
			expiration date, to report <fo:inline font-weight="bold">Claims</fo:inline> to the <fo:inline font-weight="bold">Insurer</fo:inline> pursuant to Section
			VI., General Condition A.1., Notice of <fo:inline font-weight="bold">Claim</fo:inline>. Coverage under this extension of time to report a <fo:inline font-weight="bold">Claim</fo:inline> (referred to
			below as the Automatic Extended Reporting Period) shall apply solely to
			negligent acts, errors or omissions in rendering <fo:inline font-weight="bold">Professional Services</fo:inline>:
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(a) committed or attempted prior to the effective date of nonrenewal or
			cancellation, whichever occurs first; 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2cm">		
			&#160;&#160; &#160; 	and
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(b)	which are not otherwise excluded by any terms, conditions or exclusions of this policy.
			This Automatic 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2cm">		
			&#160;&#160; &#160; 	Extended Reporting Period shall not be applicable, however, in the event the <fo:inline font-weight="bold">Named Insured</fo:inline> has  
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2cm">		
			&#160;&#160; &#160; 	obtained another policy of Lawyers Professional Liability insurance with an inception date as  
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2cm">		
			&#160;&#160; &#160; 	 of the termination date of this policy.
		</fo:block>
			 
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1.5cm" font-size="12px"
			text-align="left">2. Optional Extended Reporting Period</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			Upon the expiration or cancellation of this policy for any reason other
			than for nonpayment of premium or for the nonpayment of Deductible due
			hereunder, the <fo:inline font-weight="bold">Named Insured</fo:inline> shall have the right, upon payment of the
			additional premium shown in one of the options below for the length of
			time shown, to report <fo:inline font-weight="bold">Claims</fo:inline> pursuant to Section VI., General Condition
			A.1., Notice of <fo:inline font-weight="bold">Claim</fo:inline>.
		</fo:block>
		<fo:block margin-top="2cm"></fo:block>
		 	
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 2 of 12</fo:block>
        	 </fo:block>
		
		<fo:block break-after="page"></fo:block>
		
		
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			The Optional Extended Reporting Periods are:
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(a)	12 months for a premium charge of 100% of the annual policy premium;
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(b)	36 months for a premium charge of 185% of the annual policy premium;
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(c)	60 months for a premium charge of 225% of the annual policy premium;
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(d)	72 months for a premium charge of 250% of the annual policy premium;
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(e)	An unlimited period for a premium charge of 300% of the annual policy premium.
		</fo:block>
		<fo:block margin-top="2mm"></fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			Coverage under such extension of time to report a <fo:inline font-weight="bold">Claim</fo:inline> (referred to
			below as the Optional Extended Reporting Period) shall apply solely to
			negligent acts, errors or omissions in rendering <fo:inline font-weight="bold">Professional Services</fo:inline>
			committed or attempted prior to the effective date of nonrenewal or
			cancellation, whichever occurs first, and which are not otherwise
			excluded by any terms, conditions or exclusions of this policy.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			If the Optional Extended Reporting Period requested by the <fo:inline font-weight="bold">Named Insured</fo:inline> is issued by the <fo:inline font-weight="bold">Insurer</fo:inline>:
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(1)	the additional premium shall be fully earned by the <fo:inline font-weight="bold">Insurer</fo:inline>; and
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(2)	the Optional Extended Reporting Period cannot be cancelled by the <fo:inline font-weight="bold">Insureds</fo:inline> or the <fo:inline font-weight="bold">Insurer</fo:inline>.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			When the Optional Extended Reporting Period applies, it replaces the Automatic Extended Reporting Period.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1.5cm" font-size="12px"
			text-align="left">3. Non-Practicing Extended Reporting
			Period</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="2cm" font-size="12px"
			text-align="left">(a)	Eligibility</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			If an <fo:inline font-weight="bold">Insured</fo:inline> completely retires from or otherwise ceases the private
			practice of law during the <fo:inline font-weight="bold">Policy Period</fo:inline>, such <fo:inline font-weight="bold">Insured</fo:inline> shall have the
			option to purchase an unlimited period of time during which <fo:inline font-weight="bold">Claims</fo:inline> may
			be reported to the <fo:inline font-weight="bold">Insurer</fo:inline> pursuant to Section VI., General Condition
			A.1., Notice of <fo:inline font-weight="bold">Claim</fo:inline>. Coverage under any
			such extension of time to report a <fo:inline font-weight="bold">Claim</fo:inline> (referred to below as the
			Non-Practicing Extended Reporting Period) shall apply solely to
			negligent acts, errors or omissions in rendering <fo:inline font-weight="bold">Professional Services</fo:inline>:
		</fo:block>
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			(1)	committed or attempted prior to the date of such <fo:inline font-weight="bold">Insured’s</fo:inline> retirement or termination of private 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2.5cm">		
			&#160;&#160; &#160; 	practice; and
		</fo:block>
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			(2)	which are not otherwise excluded by any terms, conditions or exclusions of this policy.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			The option to purchase a Non-Practicing Extended Reporting Period will
			apply only to such <fo:inline font-weight="bold">Insured</fo:inline> as an individual lawyer and will not apply
			to any other lawyer, including but not limited to any lawyer acting as
			an independent contractor or contracting on a per diem basis.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="2cm" font-size="12px"
			text-align="left">(b)	Non-Practicing Extended Reporting Period Premium and Deductible</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			Any specific Non-Practicing Extended Reporting Period must be elected
			by the <fo:inline font-weight="bold">Named Insured</fo:inline>. For each Non-Practicing Extended Reporting Period
			elected, the additional premium charged will be 300% of the individual
			lawyer <fo:inline font-weight="bold">Insured’s</fo:inline> proportionate share of the annual policy premium.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			The <fo:inline font-weight="bold">Insurer</fo:inline> will waive the premium for a respective Non-Practicing
			Extended Reporting Period if such individual lawyer <fo:inline font-weight="bold">Insured</fo:inline>:
		</fo:block>
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			(1)	dies, except by suicide;
		</fo:block>
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			(2)	becomes <fo:inline font-weight="bold">Totally and Permanently Disabled</fo:inline>; or
		</fo:block>
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			(3) retires or otherwise ceases the private practice of law during the
			<fo:inline font-weight="bold">Policy Period</fo:inline> and has been insured 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2.5cm">		
			&#160;&#160; &#160; 	by the <fo:inline font-weight="bold">Insurer</fo:inline> under a primary
			Lawyers Professional Liability Policy continuously for the last 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2.5cm">		
			&#160;&#160; &#160; three years.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			The Deductible amount and Deductible provisions of this policy do not
			apply to <fo:inline font-weight="bold">Claims</fo:inline> first made against such individual lawyer <fo:inline font-weight="bold">Insured</fo:inline>
			during any Non-Practicing Extended Reporting Period.
		</fo:block>
			 

		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			If the Non-Practicing Extended Reporting Period requested by the <fo:inline font-weight="bold">Named</fo:inline>
			<fo:inline font-weight="bold">&#160;Insured</fo:inline> is issued by the <fo:inline font-weight="bold">Insurer</fo:inline>, the additional premium, if any, shall
			be fully earned by the <fo:inline font-weight="bold">Insurer</fo:inline> and the Non-Practicing Extended
			Reporting Period cannot be cancelled by the <fo:inline font-weight="bold">Insureds</fo:inline> or the <fo:inline font-weight="bold">Insurer</fo:inline>.
		</fo:block>
		<fo:block margin-top="2mm" />
		
		<fo:block font-weight="bold" text-indent="2cm" font-size="12px"
			text-align="left">(c)	Non-Practicing Extended Reporting Period Limits of Liability</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			The <fo:inline font-weight="bold">Insurer’s</fo:inline> Limit of Liability for all <fo:inline font-weight="bold">Claims</fo:inline> first made against an
			<fo:inline font-weight="bold">Insured</fo:inline> during any Non-Practicing Extended Reporting Period will be
			part of, and not in addition to, the Limit of Liability shown in Item
			3. of the Declarations, regardless of the number of Non-Practicing
			Extended Reporting Periods purchased.
		</fo:block>
		<fo:block margin-top="2mm" />
		
		<fo:block margin-top="2cm"></fo:block>
		 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 3 of 12</fo:block>
        	 </fo:block>
		<fo:block break-after="page"></fo:block>
		<fo:block font-size="10px" start-indent="2.5cm" text-align="left">
			If the Non-Practicing Extended Reporting Period herein applies to a
			<fo:inline font-weight="bold">Claim</fo:inline>, Section VI.H., Other Insurance, shall not apply to such <fo:inline font-weight="bold">Claim</fo:inline>.
			If any other policy of insurance in effect would apply to any <fo:inline font-weight="bold">Claim</fo:inline>
			first made against an <fo:inline font-weight="bold">Insured</fo:inline> during the Non-Practicing Extended
			Reporting Period, then coverage provided under this policy during any
			Non-Practicing Extended Reporting Period shall not apply. Such other
			insurance shall render this Non-Practicing Extended Reporting Period
			inapplicable, even though the limit of liability of such other
			insurance may be inadequate to pay all <fo:inline font-weight="bold">Damages</fo:inline> and <fo:inline font-weight="bold">Defense Expenses</fo:inline> or
			the Deductible amount and retention provisions of such other insurance
			may be different from those of this policy.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1.5cm" font-size="12px"
			text-align="left">4.	Conditions for Extended Reporting Periods</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			As a condition precedent to the right to elect an Extended Reporting Period:
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(a)	all premium and Deductible amounts due under this policy must have been paid;
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(b)	all <fo:inline font-weight="bold">Insureds</fo:inline> must be in compliance with the terms and conditions of the policy;
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(c) the <fo:inline font-weight="bold">Named Insured’s</fo:inline> right to practice law has not been revoked,
			suspended or surrendered at the 
		</fo:block>	
		<fo:block font-size="10px" text-align="left" start-indent="2cm">		
			&#160; &#160;&#160; request of any regulatory authority for
			reasons other than death, disability or retirement; and
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(d) the <fo:inline font-weight="bold">Named Insured</fo:inline> provides the <fo:inline font-weight="bold">Insurer</fo:inline> with written notice of its
			selection and pays the premium 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2cm">		
			&#160; &#160;&#160; charge, if applicable, for the selected
			Extended Reporting Period in full within 60 days of the 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="2cm">		
			&#160; &#160;&#160; expiration date
			of the <fo:inline font-weight="bold">Policy Period</fo:inline>.
		</fo:block>
		<fo:block margin-top="2mm"></fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			This right to elect an Extended Reporting Period shall lapse unless the
			provisions of (a) through (d) in the preceding paragraph are fully met.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">E.	Deductible Credit for Mediation Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			If: 
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1. mediation of a <fo:inline font-weight="bold">Claim</fo:inline> takes place either without institution of an
			arbitration proceeding or service of suit within 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160; 60 days of the
			institution of such proceedings or service of suit; and
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2. such <fo:inline font-weight="bold">Claim</fo:inline> is ultimately resolved for an amount acceptable to the
			<fo:inline font-weight="bold">Insured</fo:inline> and the <fo:inline font-weight="bold">Insurer</fo:inline> by the process of 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160;&#160;&#160; mediation,
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			the <fo:inline font-weight="bold">Insured’s</fo:inline> Deductible applying to the <fo:inline font-weight="bold">Claim</fo:inline> will be reduced by the lesser of 50% or $25,000.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">F.	Defendant Reimbursement Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			The <fo:inline font-weight="bold">Insurer</fo:inline> will reimburse an <fo:inline font-weight="bold">Insured</fo:inline> $100 per hour for the time such <fo:inline font-weight="bold">Insured</fo:inline> attends a: 
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	trial;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	court hearing;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			3.	mediation; or 
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			4.	arbitration proceeding, 
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			in connection with a <fo:inline font-weight="bold">Claim</fo:inline>, when such attendance is at the request of
			the <fo:inline font-weight="bold">Insurer</fo:inline>. Any payments made regarding such attendance will be in
			addition to the Limit of Liability and are not subject to the
			Deductible.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">G.	Regulatory Inquiry Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			If a: 
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	regulatory body;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	state licensing board;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			3.	public oversight board; or
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			4.	government agency,
		</fo:block>

		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			having regulatory authority over the <fo:inline font-weight="bold">Insured’s Professional Services</fo:inline>,
			first initiates an investigation of any <fo:inline font-weight="bold">Insured</fo:inline> during the <fo:inline font-weight="bold">Policy Period</fo:inline>
			which arises from <fo:inline font-weight="bold">Professional Services</fo:inline> rendered on or after the <fo:inline font-weight="bold">Prior
			Acts Date</fo:inline> and, such regulatory inquiry is reported to the <fo:inline font-weight="bold">Insurer</fo:inline>
			during the Policy Period, the <fo:inline font-weight="bold">Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold">Named Insured</fo:inline>
			for attorney fees, court and regulatory body costs incurred in
			responding to such inquiry, up to $25,000. This is the maximum amount
			the <fo:inline font-weight="bold">Insurer</fo:inline> will reimburse, regardless of the number of such inquiries
			or of the number of <fo:inline font-weight="bold">Insureds</fo:inline> involved in such inquiries, for all
			inquiries first initiated against the <fo:inline font-weight="bold">Insureds</fo:inline> and reported to the
			<fo:inline font-weight="bold">Insurer</fo:inline> during the <fo:inline font-weight="bold">Policy Period</fo:inline>. Any notice the <fo:inline font-weight="bold">Insured</fo:inline> gives the
			<fo:inline font-weight="bold">Insurer</fo:inline> of such inquiry pursuant to Section VI., General Condition
			A.2., shall be deemed notice of a potential <fo:inline font-weight="bold">Claim</fo:inline>. Any payments made
			regarding such inquiry will be in addition to the Limit of Liability
			and are not subject to the Deductible.
		</fo:block>
		
		<fo:block margin-top="3cm"></fo:block>
		 		
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 4 of 12</fo:block>
        	 </fo:block>

		<fo:block break-after="page"></fo:block>
		
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">H.	Subpoena Assistance Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			If during the <fo:inline font-weight="bold">Policy Period</fo:inline>, an <fo:inline font-weight="bold">Insured</fo:inline> first receives a subpoena for
			documents or testimony as a fact witness arising from <fo:inline font-weight="bold">Professional
			Services</fo:inline> rendered by the <fo:inline font-weight="bold">Insured</fo:inline> in whole on or after the <fo:inline font-weight="bold">Prior Acts
			Date</fo:inline> and such receipt of a subpoena is reported to the <fo:inline font-weight="bold">Insurer</fo:inline> during
			the <fo:inline font-weight="bold">Policy Period</fo:inline>, then if:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1. said subpoena arises out of a matter or lawsuit to which an <fo:inline font-weight="bold">Insured</fo:inline> is not a party; and
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2. no <fo:inline font-weight="bold">Insured</fo:inline> has been engaged to provide professional advice or testimony
			in connection with the matter or 
			</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">	
		lawsuit at any previous time,
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
		then the <fo:inline font-weight="bold">Insurer</fo:inline> will retain an attorney to provide advice to the
		<fo:inline font-weight="bold">Insured</fo:inline> regarding the production of documents, to prepare the <fo:inline font-weight="bold">Insured</fo:inline>
		for sworn testimony and to represent the <fo:inline font-weight="bold">Insured</fo:inline> at depositions. Any
		notice the <fo:inline font-weight="bold">Insured</fo:inline> gives the <fo:inline font-weight="bold">Insurer</fo:inline> of such subpoena pursuant to
		Section VI., General Condition A.2., shall be deemed notice of a
		potential <fo:inline font-weight="bold">Claim</fo:inline>. Any payments made regarding such subpoena will be in
		addition to the Limit of Liability and are not subject to the
		Deductible.
	</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">I.	Client Discrimination Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			If during the <fo:inline font-weight="bold">Policy Period</fo:inline>:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1. allegations are made against an <fo:inline font-weight="bold">Insured</fo:inline> by a client, or potential
			client, that any <fo:inline font-weight="bold">Insured</fo:inline> refused to perform 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160;&#160;<fo:inline font-weight="bold">Professional Services</fo:inline> for
			said client or potential client due to discrimination; and
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2. such allegations are reported to the <fo:inline font-weight="bold">Insurer</fo:inline> during the <fo:inline font-weight="bold">Policy Period</fo:inline>
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			then, provided the allegations did not arise out of such <fo:inline font-weight="bold">Insured’s</fo:inline>
			intentional disregard or willful failure to comply with any state or
			federal laws or regulations governing discriminatory practices, the
			<fo:inline font-weight="bold">Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold">Named Insured</fo:inline> solely for <fo:inline font-weight="bold">Defense Expenses</fo:inline>
			incurred by the <fo:inline font-weight="bold">Insured</fo:inline> up to $15,000 for the entire <fo:inline font-weight="bold">Policy Period</fo:inline> for
			all such allegations, regardless of the number of clients or potential
			clients making such allegations. The <fo:inline font-weight="bold">Insurer</fo:inline>
			shall provide its consent, not to be unreasonably withheld, to the
			<fo:inline font-weight="bold">Named Insured’s</fo:inline>
			choice of counsel in connection with defending such allegations. This
			policy shall not, however, provide coverage for <fo:inline font-weight="bold">Damages</fo:inline> resulting from
			such allegations. Any notice the <fo:inline font-weight="bold">Insured</fo:inline> gives the <fo:inline font-weight="bold">Insurer</fo:inline> of such
			allegations pursuant to Section VI., General Condition A.2., shall be
			deemed notice of a potential <fo:inline font-weight="bold">Claim</fo:inline>. Notwithstanding anything in this
			policy to the contrary, any <fo:inline font-weight="bold">Defense Expenses</fo:inline> incurred regarding such
			allegations will be in addition to the Limit of Liability and are not
			subject to the Deductible.


		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">J.	Disciplinary Proceedings Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			The <fo:inline font-weight="bold">Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold">Named Insured</fo:inline> up to $25,000 for each <fo:inline font-weight="bold">Insured</fo:inline> and all
			<fo:inline font-weight="bold">Insureds</fo:inline> in the aggregate, for attorney fees and other reasonable
			costs, expenses or fees paid to third parties (other than an <fo:inline font-weight="bold">Insured</fo:inline>)
			resulting from any one <fo:inline font-weight="bold">Disciplinary Proceeding</fo:inline> first received by the <fo:inline font-weight="bold">Insured</fo:inline>
			during the <fo:inline font-weight="bold">Policy Period</fo:inline> and reported to the Insurer during the Policy
			Period, and arising out of an act, error or omission in the rendering
			of <fo:inline font-weight="bold">Professional Services</fo:inline> by such <fo:inline font-weight="bold">Insured</fo:inline> in whole on or after the <fo:inline font-weight="bold">Prior
			Acts Date</fo:inline>. The amount payable hereunder shall not exceed $25,000
			despite the number of such proceedings reported to the <fo:inline font-weight="bold">Insurer</fo:inline> during
			the <fo:inline font-weight="bold">Policy Period</fo:inline>. Any notice the <fo:inline font-weight="bold">Insured</fo:inline> gives the <fo:inline font-weight="bold">Insurer</fo:inline> of such
			proceedings pursuant to Section VI., General Condition A.2., shall be
			deemed notice of a potential <fo:inline font-weight="bold">Claim</fo:inline>. Any payments made regarding such
			proceedings will be in addition to the Limit of Liability and are not
			subject to the Deductible.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" text-indent="1cm" font-size="12px"
			text-align="left">K.	Crisis Event Extension</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			The <fo:inline font-weight="bold">Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold">Named Insured</fo:inline> up to $20,000 for <fo:inline font-weight="bold">
			Crisis Event Expenses</fo:inline> that result from a <fo:inline font-weight="bold">Crisis Event</fo:inline>
			first occurring during the  <fo:inline font-weight="bold">Policy Period</fo:inline> and reported to the  <fo:inline font-weight="bold">Insurer</fo:inline>
			during the  <fo:inline font-weight="bold">Policy Period</fo:inline>. Any such notice the  <fo:inline font-weight="bold">Insured</fo:inline> gives the  <fo:inline font-weight="bold">Insurer</fo:inline>
			pursuant to Section VI., General Condition A.2., shall be deemed notice
			of a potential  <fo:inline font-weight="bold">Claim</fo:inline>. Any payments made regarding such event will be in
			addition to the Limit of Liability and are not subject to the
			Deductible.
		</fo:block>

		<fo:block margin-top="2mm" />
		<fo:block font-weight="bold" 
			font-size="12px" text-align="left">
			
			IV.	DEFINITIONS
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1cm" text-align="left">
			<fo:inline font-weight="bold">A. Advertising Liability</fo:inline> means legal obligations the <fo:inline font-weight="bold">Insured</fo:inline> incurs
			arising out of the marketing and promotion of 
		</fo:block>
		<fo:block font-size="10px" start-indent="1cm" text-align="left">
		&#160; &#160; &#160;the <fo:inline font-weight="bold">Insured’s
			Professional Services</fo:inline> by reason of:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1. oral or written publication of material which slanders or libels an
			individual or entity, or which disparages its 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; goods, services or
			products;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	misappropriation of marketing or promotion ideas or styles of business of others; or
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			3.	infringement of titles or slogans of others.
		</fo:block>
		
		<fo:block margin-top="2cm"></fo:block>
		
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 5 of 12</fo:block>
        	 </fo:block>

		<fo:block break-after="page"></fo:block>
		
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1cm" text-align="left">
			<fo:inline font-weight="bold">B.	Claim</fo:inline> means:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	a demand or civil proceeding seeking <fo:inline font-weight="bold">Damages</fo:inline>;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	service of suit seeking <fo:inline font-weight="bold">Damages</fo:inline>;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			3.	institution of alternative dispute proceedings seeking <fo:inline font-weight="bold">Damages</fo:inline>; or
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			4.	a demand for services.
		</fo:block>
		
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1cm" text-align="left">
			<fo:inline font-weight="bold">C.	Crisis Event</fo:inline> means:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	death, departure or debilitating illness of a principal <fo:inline font-weight="bold">Insured</fo:inline>;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	dissolution of the <fo:inline font-weight="bold">Named Insured</fo:inline>; or
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			3.	incident of workplace violence;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			that the <fo:inline font-weight="bold">Named Insured</fo:inline> reasonably believes will have a material adverse effect upon the <fo:inline font-weight="bold">Named Insured’s</fo:inline> reputation.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1cm" text-align="left">
			<fo:inline font-weight="bold">D. Crisis Event Expenses</fo:inline>
			mean reasonable fees, costs and expenses incurred by the <fo:inline font-weight="bold">Named Insured</fo:inline>
			for 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			consulting services provided by a public relations firm to the
			<fo:inline font-weight="bold">Named Insured</fo:inline> in response to a 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			<fo:inline font-weight="bold">Crisis Event</fo:inline>.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1cm" text-align="left">
			<fo:inline font-weight="bold">E. Damages</fo:inline> means a monetary judgment (including pre- and post-judgment
			interest awarded against the 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			<fo:inline font-weight="bold">Insured</fo:inline>), monetary award or monetary
			settlement negotiated with the <fo:inline font-weight="bold">Insurer’s</fo:inline> written consent. 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			If the
			<fo:inline font-weight="bold">Insurer</fo:inline> makes an offer to pay the applicable Limit of Liability, it
			will not pay any prejudgment interest 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			based on the period of time after
			such offer is made. Notwithstanding anything to the contrary contained
			herein,  
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			<fo:inline font-weight="bold">Damages</fo:inline> also include those amounts the court is permitted to
			impose on a debt collector as set forth 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			in 15 USC 1692k(a).
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			<fo:inline font-weight="bold">Damages</fo:inline> shall not include:
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			1.	any restitution, disgorgement, unjust enrichment or illegal profits by an <fo:inline font-weight="bold">Insured</fo:inline>;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			2.	return or offset of fees or overcharges or amounts which are the subject of fee disputes;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			3. punitive or exemplary damages, awards or judgments or any amounts
			which are a multiple of compensatory 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; damages, awards or judgments,
			except to the extent insurance for such damages, awards or 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; judgments is
			insurable under applicable law and is not otherwise excluded by the
			provisions of this policy. 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; For the purposes of determining whether such
			damages are insurable: 
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(a) the law of the state of incorporation or principal place of
			business of the <fo:inline font-weight="bold">Insured</fo:inline> or the <fo:inline font-weight="bold">Insurer</fo:inline>; 
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(b)	the state where the negligent act, error or omission took place; or 
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			(c)	the state where the damages are awarded or imposed,
		</fo:block>
		<fo:block font-size="10px" start-indent="2cm" text-align="left">
			whichever is most favorable to the <fo:inline font-weight="bold">Insured</fo:inline>, shall be deemed applicable law;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			4.	civil or criminal fines, sanctions or penalties;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			5. any amounts for which the <fo:inline font-weight="bold">Insured</fo:inline> is not financially liable or for
			which there is no legal recourse against the 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; <fo:inline font-weight="bold">Insured</fo:inline>;
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			6.	subject to 3. above, amounts deemed uninsurable under the law pursuant to which this policy shall be 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; construed; or
		</fo:block>
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			7. amounts paid to comply with any injunctive order or other
			non-monetary or declaratory relief or award, 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; including amounts ordered
			to be paid to comply with specific performance or any agreement to
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160; provide such relief.
		</fo:block>
		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" start-indent="1.5cm" text-align="left">
			<fo:inline font-weight="bold">F. Defense Expenses</fo:inline> mean reasonable and necessary fees charged by
			attorneys designated or approved by 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160;&#160; the <fo:inline font-weight="bold">Insurer</fo:inline> and all other
			reasonable and necessary fees, costs and expenses resulting 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160;&#160; from the
			adjustment, negotiation, arbitration, mediation, defense or appeal of a
			<fo:inline font-weight="bold">Claim</fo:inline>, including premiums 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160;&#160; on appeal, attachment or similar bonds. This
			provision does not obligate the <fo:inline font-weight="bold">Insurer</fo:inline> to apply for or furnish 
		</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">		
			&#160; &#160;&#160; any such bond.  
		</fo:block>

		<fo:block margin-top="2mm" />
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold">Defense Expenses</fo:inline> do not include:</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;salaries;</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;charges;</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;&#160;&#160;&#160;wages;</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">4.&#160;&#160;&#160;&#160;&#160;loss of wages; or</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">5.&#160;&#160;&#160;&#160;&#160;expenses,</fo:block>
		<fo:block font-size="10px" text-align="left" start-indent="1.5cm">of any partner, principal, director, officer, member or employee of the <fo:inline font-weight="bold">Insured </fo:inline>or the <fo:inline font-weight="bold">Insurer</fo:inline>.</fo:block>
		
		<fo:block padding-left="5pt" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="10px" text-align="justify">

                 <fo:block margin-top="3mm"></fo:block>
		<fo:block>
		 <fo:list-block>
	   <fo:list-item>
               <fo:list-item-label>
                 <fo:block start-indent="1cm">
                   <fo:inline font-weight="bold" >G.</fo:inline>
                 </fo:block>
               </fo:list-item-label>
               <fo:list-item-body>
                 <fo:block start-indent="1.5cm">
                   <fo:block space-after="3mm"><fo:inline font-weight="bold">Disciplinary Proceeding</fo:inline> means a forum in which a complaint alleging violation of any professional rule or professional misconduct is brought before a tribunal of competent jurisdiction which shall make a determination subject to appeal or other review or a final and enforceable determination as to whether such alleged rules or misconduct are to be the subject of discipline.
                    
                   </fo:block>
                
                </fo:block>
                </fo:list-item-body>
                </fo:list-item>
		</fo:list-block>
		</fo:block>
		<fo:block margin-top="2cm"></fo:block>
			 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 6 of 12</fo:block>
        	 </fo:block>

		<fo:block break-after="page"></fo:block>
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >H.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Immediate Family Member</fo:inline> means a:
                      <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;parent;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;child;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;&#160;&#160;&#160;grandchild;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">4.&#160;&#160;&#160;&#160;&#160;brother;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">5.&#160;&#160;&#160;&#160;&#160;sister; or</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">6.&#160;&#160;&#160;&#160;&#160;past or present spouse,</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">of any past or present <fo:inline font-weight="bold">Insured</fo:inline>.</fo:block>
					
                      </fo:block>
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >I.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Insured </fo:inline>means the <fo:inline font-weight="bold">Named Insured, Predecessor Firm,</fo:inline> and:
                      <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;any present or future principal, partner, director, officer, member or employee of the <fo:inline font-weight="bold">Named Insured</fo:inline>;</fo:block>
                      
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;any former principal, partner, director, officer, member or employee of the <fo:inline font-weight="bold">Named Insured</fo:inline> or of a </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160;&#160;&#160;<fo:inline font-weight="bold">Predecessor Firm</fo:inline>;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;&#160;&#160;&#160;the estate, heirs, executors, administrators, assigns and legal representatives of an <fo:inline font-weight="bold">Insured</fo:inline> but only in the </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160;&#160;&#160;event of such <fo:inline font-weight="bold">Insured’s</fo:inline> death, incapacity, insolvency or bankruptcy, and only to the extent</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160;&#160;&#160;that such <fo:inline font-weight="bold">Insured</fo:inline> would otherwise have been provided coverage under the terms, conditions and </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160;&#160;&#160;exclusions of this policy;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">4.&#160;&#160;&#160;&#160;&#160;any contract or temporary employee of a <fo:inline font-weight="bold">Named Insured</fo:inline> under the direct supervision of an <fo:inline font-weight="bold">Insured</fo:inline>; and</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">5.&#160;&#160;&#160;&#160;&#160;any lawyer acting as "of counsel";</fo:block>
					
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">but only with respect to <fo:inline font-weight="bold">Professional Services</fo:inline> performed within the scope of their duties on </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">behalf of the <fo:inline font-weight="bold">Named Insured</fo:inline> or <fo:inline font-weight="bold">Predecessor Firm.</fo:inline></fo:block>
					
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >J.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Insurer </fo:inline>means the insurance company named in the Declarations.
                     
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >K.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Named Insured</fo:inline> means the entity, individual, partnership or corporation shown in Item 1. of the Declarations.
                     
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >L.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Personal Injury</fo:inline> means any of the following which arise from <fo:inline font-weight="bold">the Insured’s Professional Services</fo:inline>:
                     <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;false arrest, detention or imprisonment; malicious prosecution;</fo:block>
                      
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;the publication or utterance of a libel or slander or other defamatory or disparaging statement or </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;disparaging material;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;&#160;&#160;&#160;a publication or utterance in violation of a person’s right of privacy;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">4.&#160;&#160;&#160;&#160;&#160;the wrongful eviction of a person from a residence; or</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">5.&#160;&#160;&#160;&#160;&#160;wrongful entry into, or invasion of the right of private occupancy.</fo:block>
                     
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
						<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >M.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Policy Aggregate</fo:inline> means the amount shown in Item 3. of the Declarations which represents the maximum amount of the <fo:inline font-weight="bold">Insurer’s</fo:inline> liability for all:
                     <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;<fo:inline font-weight="bold">Claims</fo:inline>;</fo:block>
                      
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;<fo:inline font-weight="bold">Damages;</fo:inline> and</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;&#160;&#160;&#160;<fo:inline font-weight="bold">Defense Expenses,</fo:inline></fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">combined under this policy, inclusive of any applicable Extended Reporting Period.</fo:block>
                     
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
						<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >N.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Policy Period</fo:inline> means the length of time between the effective date shown in Item 2. of the Declarations and the earlier of:
                     <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;the expiration date shown in Item 2. of the Declarations; or</fo:block>
                      
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;the cancellation date of this policy.</fo:block>
					  
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					
						<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >O.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Predecessor Firm</fo:inline> means an individual, partnership, professional corporation, professional association, limited liability corporation or partnership which was at all times engaged in <fo:inline font-weight="bold">Professional Services</fo:inline> and to whose financial assets and liabilities the <fo:inline font-weight="bold">Named Insured</fo:inline> is the majority successor in interest prior to the effective date shown in Item 2. of the Declarations.
                      </fo:block>
                     <fo:block margin-top="3mm"></fo:block>
                     
                     <fo:block font-size="10px" text-align="left" start-indent="1.5cm"><fo:inline font-weight="bold">Predecessor Firm</fo:inline> does not include any individual, partnership, professional corporation, professional association, limited liability corporation or partnership which was at all times engaged in <fo:inline font-weight="bold">Professional Services</fo:inline> and to whose financial assets and liabilities the <fo:inline font-weight="bold">Named Insured</fo:inline> becomes the majority successor in interest after the effective date of this policy shown in Item 2. of the Declarations unless the <fo:inline font-weight="bold">Insurer</fo:inline> at its sole discretion agrees to include such entity.  Should the <fo:inline font-weight="bold">Insurer</fo:inline> agree to include such entity it may do so for an additional premium or with amended policy terms and conditions.</fo:block>
                     
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
                      
                      <fo:block  margin-top="3mm"></fo:block>
					
						<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >P.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Prior Acts Date</fo:inline> means the date shown in Item 6. of the Declarations.
                      </fo:block>
                      
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
		<fo:block margin-top="2cm"></fo:block>
			 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 7 of 12</fo:block>
        	 </fo:block>
			
		<fo:block break-after="page"></fo:block>
					
					
                      <fo:block  margin-top="3mm"></fo:block>
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >Q.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Professional Services </fo:inline>means:
                       <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;those services performed for a client in the <fo:inline font-weight="bold">Insured’s</fo:inline> capacity as a lawyer for a monetary fee;</fo:block>
                      
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;those services performed as an arbitrator, mediator or notary public for a monetary fee;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;&#160;&#160;&#160;those services performed as a title agent for a client which are incidental to services performed as a lawyer </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160;&#160; &#160;for the client for a monetary fee;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">4.&#160;&#160;&#160;&#160;&#160;pro bono services performed by an <fo:inline font-weight="bold" >Insured</fo:inline> if at the time such services were rendered, they were </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;approved by a partner, director or officer of the <fo:inline font-weight="bold" >Named Insured</fo:inline> to be performed </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;without compensation;</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">5.&#160;&#160;&#160;&#160;&#160;those services performed as a member of a formal accreditation, standards review or similar professional </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;board or committee solely related to the profession of the practice of law, but only when such formal</fo:block> 
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;accreditation, standards review or similar professional board or committee solely related to the </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;profession of the practice of law does not indemnify the <fo:inline font-weight="bold" >Insured</fo:inline> or have insurance coverage applicable </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;to the <fo:inline font-weight="bold" >Insured</fo:inline> in respect of such services; and</fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">6.&#160;&#160;&#160;&#160;&#160;those services performed as an administrator, conservator, receiver, executor, trustee, guardian, or any </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;similar fiduciary capacity, or court-appointed trustee.  However, no coverage shall apply to any loss </fo:block>
					<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160;&#160;sustained by any <fo:inline font-weight="bold" >Insured</fo:inline> as the beneficiary or distributee of any trust or estate.</fo:block>
					
					
					 
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                  </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >R.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Related Claims</fo:inline> means all <fo:inline font-weight="bold">Claims</fo:inline> arising from negligent acts, errors or omissions that have as a common nexus any fact, circumstance, situation, transaction, event or cause or series of causally connected facts, circumstances, situations, transactions, events or causes.
                       
					
					 
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >S.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">Totally and Permanently Disabled</fo:inline> means that the <fo:inline font-weight="bold">Insured</fo:inline> is so disabled as to be wholly prevented from rendering <fo:inline font-weight="bold">Professional Services</fo:inline> provided that such disability is reasonably expected to be continuous and permanent, and:
                       <fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;has existed continuously for not less than six months; or</fo:block>
                        <fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;the <fo:inline font-weight="bold" >Insurer</fo:inline> is provided with written proof of the <fo:inline font-weight="bold" >Insured’s</fo:inline> total and permanent disability, including the date the disability commenced, certified by a physician acceptable to the <fo:inline font-weight="bold" >Insurer</fo:inline>.</fo:block>
                      </fo:block>
                      
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent=".5cm">
                      <fo:inline font-weight="bold" >V.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1cm">
                      <fo:block space-after="3mm"><fo:inline font-weight="bold">EXCLUSIONS</fo:inline>
                      
					  </fo:block>
					  <fo:block  margin-top="3mm"></fo:block>
					  <fo:block start-indent="1cm">This policy does not apply to any <fo:inline font-weight="bold" >Claim</fo:inline> based upon or arising out of:</fo:block>
                      <fo:block  margin-top="3mm"></fo:block>
                   
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >A.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block space-after="3mm">a dishonest, intentional, fraudulent, criminal or malicious act or omission or any intentional or knowing violation of law by any <fo:inline font-weight="bold" >Insured</fo:inline>.  However:
                      
					  </fo:block>
					  <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					
					 <fo:block>
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="1.5cm">1.</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="2cm" >the <fo:inline font-weight="bold">Insurer</fo:inline> will provide the <fo:inline font-weight="bold">Insured</fo:inline> with a defense of such <fo:inline font-weight="bold">Claim</fo:inline> and pay <fo:inline font-weight="bold">Defense Expenses</fo:inline> for any such <fo:inline font-weight="bold">Claim</fo:inline> until there is a judgment, final adjudication or adverse admission by an <fo:inline font-weight="bold">Insured</fo:inline> or a finding of fact against an <fo:inline font-weight="bold">Insured</fo:inline> as to such conduct; and</fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
                   
                   
					
					<fo:block>
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="1.5cm">2.</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="2cm" >this exclusion shall not apply to an <fo:inline font-weight="bold">Insured</fo:inline> who, in fact, did not personally commit, direct or participate in committing a dishonest, intentional, fraudulent, criminal or malicious act or omission or any intentional or knowing violation of law.</fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
                   
					
					
					
					
					<fo:block margin-top="2mm">
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="1cm">B.</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="1.5cm" >1.&#160;&#160;physical injury, sickness, disease or the death of any person including mental anguish or emotional</fo:block>
					  <fo:block start-indent="2cm" >distress resulting therefrom; or</fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
					 
					 
                   
                   <fo:block>
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="1.5cm">2.</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="2cm" >physical injury to, or destruction of any tangible property, including any resulting loss of use thereof. However, this exclusion shall not apply to accounting records of clients of the <fo:inline font-weight="bold">Named Insured</fo:inline>.</fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
                   
					
				
                   <fo:block margin-top="2mm">
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="1cm">C.</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="1.5cm" >1.&#160;&#160;an <fo:inline font-weight="bold">Insured’s</fo:inline> capacity as a fiduciary under the Employee Retirement Income Security Act of 1974 and </fo:block>
					 <fo:block start-indent="2cm" >any amendments thereto.  However, this exclusion shall not apply if:</fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
                   
					
                   <fo:block>
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="2cm">(a)</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="2.5cm" >a court of competent jurisdiction deems an <fo:inline font-weight="bold">Insured</fo:inline> to be a fiduciary under such Act solely by reason of <fo:inline font-weight="bold">Professional Services</fo:inline> an <fo:inline font-weight="bold">Insured</fo:inline> rendered to any employee benefit plan; or</fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
               
                <fo:block>
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="2cm">(b)</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="2.5cm" >an <fo:inline font-weight="bold">Insured</fo:inline> is appointed as a Receiver, Trustee or Custodian of an employee benefit plan by a court of law; or</fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
               
               
               
               <fo:block>
					 <fo:list-block>
					 <fo:list-item>
					 <fo:list-item-label>
					 <fo:block start-indent="1.5cm">2.</fo:block>
					 </fo:list-item-label>
					 <fo:list-item-body>
					 <fo:block start-indent="2cm" >any actual or alleged violation of the Racketeer Influenced and Corrupt Organizations Act.</fo:block>
					 
				<fo:block  margin-top="3mm"></fo:block>
					 </fo:list-item-body>
					 </fo:list-item>
					 
					 </fo:list-block>
					 </fo:block>
           <fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >D.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block ><fo:inline font-weight="bold">Professional Services </fo:inline>performed for any person by an <fo:inline font-weight="bold">Insured</fo:inline>, if at the time of any negligent act, error or omission giving rise to the <fo:inline font-weight="bold">Claim</fo:inline>, such <fo:inline font-weight="bold">Professional Services</fo:inline> were rendered to an <fo:inline font-weight="bold">Immediate Family Member</fo:inline>.
                      
					  </fo:block>
					 </fo:block>
				
                   
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
		<fo:block margin-top="2cm"></fo:block>
		 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 8 of 12</fo:block>
        	 </fo:block>

		<fo:block break-after="page"></fo:block>
				
				
				 <fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >E.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                    
                    <fo:block start-indent="1.5cm" ><fo:inline font-weight="bold">Professional Services</fo:inline> performed by an <fo:inline font-weight="bold" >Insured</fo:inline> for any entity, or any individual partner, member, director or employee  of such entity, if at the time of any negligent act, error or omission giving rise to the <fo:inline font-weight="bold" >Claim</fo:inline>:</fo:block>
                    
					   <fo:block start-indent="1.5cm" >1.&#160;&#160;&#160;&#160;&#160;such <fo:inline font-weight="bold">Insured</fo:inline> or his/her <fo:inline font-weight="bold">Immediate Family Member</fo:inline> controlled or owned more than 10% equity interest, operated or managed such entity; or</fo:block>
					  
					  <fo:block start-indent="1.5cm" >2.&#160;&#160;&#160;&#160;&#160;such <fo:inline font-weight="bold">Insured</fo:inline> or his/her <fo:inline font-weight="bold">Immediate Family Member</fo:inline> was a partner, member, director, officer or employee of such entity. </fo:block>
					    
					  </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
					<fo:block  margin-top="3mm"></fo:block>
					
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >F.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                    
                      <fo:block start-indent="1.5cm" >1.&#160;&#160;&#160;&#160;&#160;liability of others assumed by an <fo:inline font-weight="bold">Insured</fo:inline> under any contract or agreement; or</fo:block>
					  
					  <fo:block start-indent="1.5cm" >2.&#160;&#160;&#160;&#160;&#160;the breach of any express warranty of any contract unless such liability would have attached to the <fo:inline font-weight="bold">Insured</fo:inline> even in the absence of such contract or agreement.</fo:block>
					    <fo:block  margin-top="3mm"></fo:block>
					  </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
				 <fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >G.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                    
					   <fo:block start-indent="1.5cm" >actual or alleged negligent acts, errors or omissions asserted by or on behalf of one or more Insureds against any other <fo:inline font-weight="bold" >Insured</fo:inline>.</fo:block>
					  
					  <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
					<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >H.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                      <fo:block >an <fo:inline font-weight="bold">Insured</fo:inline> acting in the capacity as:
                      
					  </fo:block>
					   <fo:block start-indent="1.5cm" >1.&#160;&#160;&#160;&#160;&#160;an executor, administrator or personal representative of an estate or as a trustee if the <fo:inline font-weight="bold">Insured</fo:inline> or their </fo:block>
					   <fo:block start-indent="1.5cm" >&#160; &#160; &#160; &#160; <fo:inline font-weight="bold">Immediate Family Member</fo:inline> is or was a beneficiary or distributee of said estate or trust;</fo:block>
					  
					  <fo:block start-indent="1.5cm" >2.&#160;&#160;&#160;&#160;&#160;an officer, director, trustee, partner or other member of a governing body of any entity other than the </fo:block>
					  <fo:block start-indent="1.5cm" >&#160; &#160; &#160; &#160; <fo:inline font-weight="bold">Named Insured</fo:inline> and other than an accreditation or standards entity within the scope of Section IV., </fo:block>
					  <fo:block start-indent="1.5cm" >&#160; &#160; &#160; &#160; Definition Q., <fo:inline font-weight="bold">Professional Services</fo:inline>, paragraph 5.; or</fo:block>
					  <fo:block start-indent="1.5cm" >3.&#160;&#160;&#160;&#160;&#160;a public official or employee of a governmental body, agency or subdivision thereof, unless such capacity </fo:block>
					  <fo:block start-indent="1.5cm" >&#160; &#160; &#160; &#160; is deemed as a matter of law to be a public official, employee or representative of such entity </fo:block>
					  <fo:block start-indent="1.5cm" >&#160; &#160; &#160; &#160; solely by virtue of an <fo:inline font-weight="bold">Insured</fo:inline> rendering <fo:inline font-weight="bold">Professional Services</fo:inline>.</fo:block>
					  <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
				<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >I.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                     
					   <fo:block start-indent="1.5cm" >1.&#160;&#160;&#160;&#160;&#160;defects in title of which the <fo:inline font-weight="bold" >Insured</fo:inline> had knowledge as of the date of issuance of any title policy; or</fo:block>
					  
					  <fo:block start-indent="1.5cm" >2.&#160;&#160;&#160;&#160;&#160;breach of underwriting authority granted an <fo:inline font-weight="bold" >Insured</fo:inline> by a title insurance company or its delegate.</fo:block>
					   <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
				
				<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >J.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                     
					   <fo:block start-indent="1.5cm" ><fo:inline font-weight="bold" >Professional Services</fo:inline> performed prior to the <fo:inline font-weight="bold" >Prior Acts Date</fo:inline> shown in Item 6. of the Declarations.</fo:block>
					  
					 <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >K.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                     
					   <fo:block start-indent="1.5cm" >negligent acts, errors or omissions or <fo:inline font-weight="bold" >Related Claims</fo:inline> which have been the subject of any notice given under any prior policy of which this policy is a renewal or replacement.</fo:block>
					  
					 <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
				
				<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >L.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                     
					   <fo:block start-indent="1.5cm" >any facts or circumstances of which any <fo:inline font-weight="bold" >Insured</fo:inline> had knowledge as of the effective date of this policy and which could reasonably have been expected to give rise to a <fo:inline font-weight="bold" >Claim</fo:inline>.
				</fo:block>
					  
					 <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
				<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >M.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                     
					   <fo:block start-indent="1.5cm" ><fo:inline font-weight="bold" >Professional Services</fo:inline> rendered by an <fo:inline font-weight="bold" >Insured</fo:inline> while the <fo:inline font-weight="bold" >Insured’s</fo:inline> license to practice law was:
				    </fo:block>
				    <fo:block start-indent="1.5cm" >1.&#160;&#160;&#160;&#160;&#160;suspended;</fo:block>
					  
					  <fo:block start-indent="1.5cm" >2.&#160;&#160;&#160;&#160;&#160;revoked;</fo:block>
					  
					   <fo:block start-indent="1.5cm" >3.&#160;&#160;&#160;&#160;&#160;surrendered;</fo:block>
					    <fo:block start-indent="1.5cm" >4.&#160;&#160;&#160;&#160;&#160;lapsed; or</fo:block>
					  
					  <fo:block start-indent="1.5cm" >5.&#160;&#160;&#160;&#160;&#160;otherwise not recognized as a bona fide license,</fo:block>
					  
					   <fo:block start-indent="1.5cm" >in the state where such services were rendered.</fo:block>
					 
				
					  
					 <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
				
				
				
				
				<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >N.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                     
					   <fo:block start-indent="1.5cm" >any loss sustained by any <fo:inline font-weight="bold" >Insured</fo:inline> as a beneficiary or distributee of any estate or trust.</fo:block>
					  
					 <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
				
				
				
				<fo:block>
					 <fo:list-block>
				   <fo:list-item>
                  <fo:list-item-label>
                    <fo:block start-indent="1cm">
                      <fo:inline font-weight="bold" >O.</fo:inline>
                    </fo:block>
                  </fo:list-item-label>
                  <fo:list-item-body>
                    <fo:block start-indent="1.5cm">
                     
					   <fo:block start-indent="1.5cm" >a notarized certification or acknowledgment of signature without an identity check and physical appearance before the <fo:inline font-weight="bold">Insured</fo:inline> performing such notarization of the person whose signature was notarized or acknowledged.</fo:block>
					  
					 <fo:block  margin-top="3mm"></fo:block>
					 
					 
				
                   </fo:block>
                   </fo:list-item-body>
                   </fo:list-item>
					</fo:list-block>
					</fo:block>
				
			<fo:block font-weight="bold" 
                 text-align="left">
                 VI. GENERAL CONDITIONS
            </fo:block>
            <fo:block margin-top="4mm" />
            <fo:block font-weight="bold"  text-indent="1cm"
                  text-align="left">A. Notice</fo:block>
            <fo:block margin-top="3mm"></fo:block>

           <fo:block>
           <fo:list-block>
           <fo:list-item>
           <fo:list-item-label>
           <fo:block text-indent="1.5cm">&#160;</fo:block>
           </fo:list-item-label>
           
           <fo:list-item-body>
           <fo:block font-size="10px" text-align="left" start-indent="1.5cm" font-weight="bold" space-after="3mm">
           1.	Notice of Claim
           </fo:block>
          <fo:block font-size="10px" text-align="left" start-indent="2cm">
                  The
                  <fo:inline font-weight="bold">Insureds</fo:inline>
                  shall, as a condition precedent to the obligations of the
                  <fo:inline font-weight="bold">Insurer</fo:inline>
                  under this policy, give written notice of a
                  <fo:inline font-weight="bold">Claim</fo:inline>
                  as soon as practicable, but in no event later than 60 days after
                  the expiration of the
                  <fo:inline font-weight="bold">Policy Period.</fo:inline>
                  In the event a
                  <fo:inline font-weight="bold"> Claim </fo:inline>
                  is made during the Extended Reporting Period, if applicable, the
                  <fo:inline font-weight="bold">Insureds </fo:inline>
                  shall, as a condition precedent to the obligations of the
                  <fo:inline font-weight="bold"> Insurer</fo:inline>
                  under this policy, give written notice of such
                  <fo:inline font-weight="bold">Claim </fo:inline>
                  as soon as practicable, but in no event later than the expiration of
                  the Extended Reporting Period. Such written notice shall include:
            </fo:block>
            <fo:block font-size="10px" text-align="left" start-indent="2cm">
                  (a) all written correspondence between the claiming party and any
                  <fo:inline font-weight="bold">Insured</fo:inline>;
                  
            </fo:block>
            <fo:block font-size="10px" text-align="left" start-indent="2cm">(b)
                  a written summary of the facts and circumstances of the allegation of
                  the negligent act, error,</fo:block>
            <fo:block font-size="10px" text-align="left" start-indent="3cm">
                  omission,
                  <fo:inline font-weight="bold">Personal Injury</fo:inline> or <fo:inline font-weight="bold">Advertising Liability</fo:inline>;
            </fo:block>

            <fo:block font-size="10px" text-align="left" start-indent="2cm">(c)
                  dates and details of the parties involved; and</fo:block>

            <fo:block font-size="10px" text-align="left" start-indent="2cm">
                  (d) possible
                  <fo:inline font-weight="bold"> Damages.</fo:inline>
            </fo:block>

           </fo:list-item-body>
           </fo:list-item>
           
           </fo:list-block>
           
           </fo:block>


            <fo:block margin-top="1cm" />
			 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 9 of 12</fo:block>
        	 </fo:block>
			 
            
             <fo:block page-break-before="always"/>
            
            <fo:block>
            <fo:list-block>
            <fo:list-item>
            <fo:list-item-label>
              <fo:block> &#160;</fo:block>
            </fo:list-item-label>
            
            <fo:list-item-body>
            <fo:block font-size="10px" text-align="left" start-indent="1.5cm" font-weight="bold" space-after="3mm">
         		2.	Notice of Potential Claim</fo:block>
           <fo:block font-size="10px" text-align="left" start-indent="1.5cm">
				If during the <fo:inline font-weight="bold">Policy Period</fo:inline>, the <fo:inline font-weight="bold">Insured</fo:inline> becomes aware of any facts or
				circumstances that may reasonably be expected to give rise to a <fo:inline font-weight="bold">Claim</fo:inline>,
				and written notice is given to the <fo:inline font-weight="bold">Insurer</fo:inline> as soon as practicable but
				in no event later than the last day of the <fo:inline font-weight="bold">Policy Period</fo:inline> of:
            </fo:block>
    
            <fo:block font-size="10px" text-align="left" start-indent="2cm">
                 (a) such facts or circumstances, as well as the reasons for anticipating such a <fo:inline font-weight="bold">Claim</fo:inline>;
             
            </fo:block>
            <fo:block font-size="10px" text-align="left" start-indent="2cm">
                  (b) specific information as to the expected negligent act, error, omission, <fo:inline font-weight="bold">Personal Injury or Advertising Liability</fo:inline>;
            </fo:block>
            <fo:block font-size="10px" text-align="left" start-indent="2cm">
                  (c) dates and details of the parties involved; and</fo:block>

            <fo:block font-size="10px" text-align="left" start-indent="2cm">
                  (d) the possible Damages;
                </fo:block>
                
                <fo:block font-size="10px" text-align="left" start-indent="2cm">then any <fo:inline font-weight="bold">Claim</fo:inline> or coverage under this policy arising out of such specific facts or circumstances that is subsequently made against the <fo:inline font-weight="bold">Insureds</fo:inline> and reported to the <fo:inline font-weight="bold">Insurer</fo:inline> shall be deemed first made during the <fo:inline font-weight="bold">Policy Period</fo:inline> as of the date of such notice.</fo:block>

            </fo:list-item-body>
            </fo:list-item>
            
            </fo:list-block>
            
            </fo:block>
            
			
			
            <!-- <fo:block font-size="10px" text-align="left" start-indent="2cm">
                  then any
                  <fo:inline font-weight="bold">Claim</fo:inline> or coverage provided pursuant to Section III., Extensions of Coverage:
                 <fo:block start-indent="2cm" >(1)&#160;&#160;&#160;&#160;&#160;paragraph G., Regulatory Inquiry Extension;</fo:block>
                 <fo:block start-indent="2cm" >(2)&#160;&#160;&#160;&#160;&#160;paragraph H., Subpoena Assistance Extension;</fo:block>
                 <fo:block start-indent="2cm" >(3)&#160;&#160;&#160;&#160;&#160;paragraph I., Client Discrimination Extension;</fo:block>
                 <fo:block start-indent="2cm" >(4)&#160;&#160;&#160;&#160;&#160;paragraph J., Disciplinary Proceedings Extension; or</fo:block>
                 <fo:block start-indent="2cm" >(5)&#160;&#160;&#160;&#160;&#160;paragraph K., Crisis Event Extension,</fo:block>
                 <fo:block start-indent="2cm" >arising out of such specific facts or circumstances that is subsequently made against the Insureds and reported to the Insurer shall be deemed first made during the Policy Period as of the date of such notice.</fo:block>
            </fo:block>

 -->


            <fo:block margin-top="3mm" />
            <fo:block font-size="10px" text-align="left" start-indent="1cm">
                  3.&#160;&#160;&#160;&#160;&#160;Notice of a <fo:inline font-weight="bold">Claim</fo:inline> or potential <fo:inline font-weight="bold" >Claim</fo:inline> to the <fo:inline font-weight="bold">Insurer</fo:inline> shall be given in writing to:
            </fo:block>
            <fo:block margin-top="2mm" />

            <fo:block font-size="10px" text-align="left" text-indent="2cm">Attention: InsClaim Solutions, LLC</fo:block>
            <fo:block font-size="10px" text-align="left" text-indent="2cm">300 S. Wacker Dr., 32nd FL</fo:block>
            <fo:block font-size="10px" text-align="left" text-indent="2cm">Chicago, Illinois 60606</fo:block>
            <fo:block font-size="10px" text-align="left" text-indent="2cm">Email: claims@ins-claim.com</fo:block>

            <fo:block margin-top="2mm" />
			
				
				<fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >B.	Defense and Settlement  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.	The <fo:inline font-weight="bold" >Insurer</fo:inline> shall have the right and the duty to defend any<fo:inline font-weight="bold" > Claim </fo:inline>regardless of whether the allegations </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">are groundless, false, or fraudulent. In undertaking this right and duty, the<fo:inline font-weight="bold" > Insurer</fo:inline> expressly retains the right to select defense counsel even when the<fo:inline font-weight="bold" > Insurer</fo:inline> reserves its rights on issues concerning the applicability of coverage under this policy. The<fo:inline font-weight="bold" > Insureds </fo:inline>shall pay any <fo:inline font-weight="bold" >Defense Expenses</fo:inline> within the applicable Deductible shown in Item 4. of the Declarations. The<fo:inline font-weight="bold" > Insurer’s</fo:inline> right and duty to defend any <fo:inline font-weight="bold" >Claim</fo:inline> and pay <fo:inline font-weight="bold" >Defense Expenses</fo:inline> shall terminate upon the exhaustion of the Limit of Liability, whereupon the <fo:inline font-weight="bold" >Insurer</fo:inline> shall have no further obligation or liability to defend the<fo:inline font-weight="bold" > Insured</fo:inline> or to pay <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, judgments or settlements.  The <fo:inline font-weight="bold" >Insurer </fo:inline>may make any investigation it deems necessary and may, with the <fo:inline font-weight="bold" >Insured’s</fo:inline> consent, such consent not to be unreasonably withheld, make any settlement of any <fo:inline font-weight="bold" >Claim</fo:inline> it deems expedient. If the <fo:inline font-weight="bold" >Insured</fo:inline> withholds consent of such settlement, the <fo:inline font-weight="bold" >Insurer’s </fo:inline>liability for all <fo:inline font-weight="bold" >Damages</fo:inline> on account of such <fo:inline font-weight="bold" >Claim</fo:inline> shall not exceed the amount for which the<fo:inline font-weight="bold" > Insurer</fo:inline> could have settled such<fo:inline font-weight="bold" > Claim</fo:inline>, inclusive of <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, incurred as of the date such settlement was proposed to the<fo:inline font-weight="bold" > Insured.</fo:inline></fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.	The <fo:inline font-weight="bold" >Insureds </fo:inline>and those acting on their behalf shall not admit liability, consent to any judgment, incur any </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm"><fo:inline font-weight="bold" >Defense Expenses</fo:inline> or agree to any settlement without the<fo:inline font-weight="bold" > Insurer’s</fo:inline> written consent, such consent not to be unreasonably withheld.  The <fo:inline font-weight="bold" >Insureds</fo:inline> agree that they shall not knowingly take any action that in any way increases<fo:inline font-weight="bold" > Damages</fo:inline> or<fo:inline font-weight="bold" > Defense Expenses</fo:inline> under this policy.  Coverage afforded by this policy shall not apply to any <fo:inline font-weight="bold" >Damages</fo:inline> sustained as a result of any admission of liability or consent to any judgment or agreement to settle, without or prior to the<fo:inline font-weight="bold" > Insurer’s</fo:inline> written consent.</fo:block>
				
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.	The<fo:inline font-weight="bold" > Insureds</fo:inline> shall provide the<fo:inline font-weight="bold" > Insurer</fo:inline> with such information, assistance, and cooperation as the <fo:inline font-weight="bold" >Insurer</fo:inline></fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">and its counsel may reasonably request with respect to the defense and settlement of any<fo:inline font-weight="bold" > Claim.</fo:inline> </fo:block>
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" text-indent="1cm" font-size="12px" text-align="left" >C.	Action Against Insurer </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;	No action shall be taken against the <fo:inline font-weight="bold" >Insurer</fo:inline> unless, as a condition precedent thereto, the <fo:inline font-weight="bold" >Insureds</fo:inline> shall </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">have fully complied with all of the terms and conditions of this policy, nor until the amount of the <fo:inline font-weight="bold" >Insured’s</fo:inline> obligation to pay <fo:inline font-weight="bold" >Damages</fo:inline> for any <fo:inline font-weight="bold" >Claim</fo:inline> shall have been fully and finally determined either by judgment against the <fo:inline font-weight="bold" >Insured</fo:inline> or by written agreement between the <fo:inline font-weight="bold" >Insureds</fo:inline>, the claimant, and the <fo:inline font-weight="bold" >Insurer</fo:inline>.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;	Nothing contained herein shall give any person or entity any right to join the <fo:inline font-weight="bold" >Insurer</fo:inline> as a party to any <fo:inline font-weight="bold" >Claim</fo:inline> </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">against the <fo:inline font-weight="bold" >Insureds</fo:inline> to determine their liability. Nor shall the <fo:inline font-weight="bold" >Insurer</fo:inline> be impleaded by the <fo:inline font-weight="bold" >Insureds</fo:inline> or their legal representative in any <fo:inline font-weight="bold" >Claim</fo:inline>.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >D.	Cancellation and Nonrenewal </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;The <fo:inline font-weight="bold" >Named Insured</fo:inline> may cancel this policy at any time prior to the expiration date of the <fo:inline font-weight="bold" >Policy Period</fo:inline> by </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">providing prior written notice to the <fo:inline font-weight="bold" >Insurer</fo:inline> or by surrender of this policy to the <fo:inline font-weight="bold" >Insurer</fo:inline> or its authorized agent. If the <fo:inline font-weight="bold" >Named Insured</fo:inline> cancels this policy, the <fo:inline font-weight="bold" >Insurer</fo:inline> shall return 90% of the unearned portion of the premium.</fo:block>
				<fo:block margin-top="2cm"/>
				 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 10 of 12</fo:block>
        	 </fo:block>
			 
				 <fo:block page-break-before="always"/>
            
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;The <fo:inline font-weight="bold" >Insurer</fo:inline> may only cancel this policy for nonpayment of premium or Deductible. This policy may be </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">canceled by the <fo:inline font-weight="bold" >Insurer</fo:inline> by mailing or delivering to the <fo:inline font-weight="bold" >Named Insured</fo:inline>, at the address shown in Item 1. of the Declarations, written notice of cancellation at least 10 days before the effective date of cancellation. The mailing of such notice shall be sufficient proof of notice and the effective date of cancellation stated in such notice shall become the expiration date of the <fo:inline font-weight="bold" >Policy Period</fo:inline>. If the <fo:inline font-weight="bold" >Insurer</fo:inline> cancels this policy, the <fo:inline font-weight="bold" >Insurer</fo:inline> shall credit the <fo:inline font-weight="bold" >Named Insured</fo:inline> the pro rata unearned portion of the premium. Payment or tender of any unearned premium by the <fo:inline font-weight="bold" >Insurer</fo:inline> shall not be a condition precedent to the effectiveness of cancellation, but such payment shall be made as soon as practicable.</fo:block>
				<fo:block margin-top="2mm"/>
			
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;This policy may be nonrenewed by the <fo:inline font-weight="bold" >Insurer</fo:inline> by mailing or delivering to the <fo:inline font-weight="bold" >Named Insured</fo:inline>, </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm"> at the address shown in Item 1. of the Declarations, written notice of nonrenewal at least 30 days prior to the expiration date of the <fo:inline font-weight="bold" >Policy Period</fo:inline>.  The mailing of such notice shall be sufficient proof of notice.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >E.&#160;&#160;Changes in Exposures </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;If the number of attorneys employed by the <fo:inline font-weight="bold" >Named Insured</fo:inline> increases more than 25% from the amount of </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">attorneys shown in the application attached to this policy at its inception date, the <fo:inline font-weight="bold" >Named Insured </fo:inline>shall give the <fo:inline font-weight="bold" >Insurer </fo:inline>notice of such hiring, merger or acquisition as soon as practicable, but in no event more than 30 days after the effective date of hiring, merger or acquisition. The<fo:inline font-weight="bold" > Insurer </fo:inline>shall then have the right to amend any terms of this policy. There shall be no coverage under this policy for any <fo:inline font-weight="bold" >Professional Services</fo:inline> after the date of hiring, merger or acquisition .  This paragraph shall not be applicable if the original   number of attorneys insured on the effective date of this policy was less than six attorneys. </fo:block>
				<fo:block margin-top="1mm"/>
				
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;If the <fo:inline font-weight="bold" >Named Insured</fo:inline> during the <fo:inline font-weight="bold" >Policy Period</fo:inline> merges or consolidates with another entity in a manner </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="2cm">such that the <fo:inline font-weight="bold" >Named Insured</fo:inline> is not the surviving entity, coverage under this policy for<fo:inline font-weight="bold" > Professional Services</fo:inline> rendered, or which should have been rendered after the effective date of such transaction shall be excluded for the remainder of the <fo:inline font-weight="bold" >Policy Period</fo:inline>. Coverage shall then solely apply to <fo:inline font-weight="bold" >Professional Services </fo:inline>rendered, or which should have been rendered, between the <fo:inline font-weight="bold" >Prior Acts Date</fo:inline> shown in Item 6. of the Declarations and the effective date of the transaction whereby the <fo:inline font-weight="bold" >Named Insured</fo:inline> is not the surviving entity, subject to all the  other terms, conditions and exclusions of this policy.</fo:block>
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >F. 	Subrogation </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">In the event of any payment under this policy, the <fo:inline font-weight="bold" >Insurer</fo:inline> shall be subrogated to the extent of such payment to all <fo:inline font-weight="bold" >Insureds'</fo:inline> rights of recovery therefrom against any person or entity. The <fo:inline font-weight="bold" >Insureds</fo:inline> shall execute all papers required and shall do everything that may be necessary to secure and preserve such rights to enable the <fo:inline font-weight="bold" >Insurer</fo:inline> to effectively bring suit in their name, and shall provide all other assistance and cooperation which the <fo:inline font-weight="bold" >Insurer</fo:inline> may reasonably require.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-indent="1cm" text-align="left" >G. 	Representations  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">In granting coverage to the <fo:inline font-weight="bold" >Insureds</fo:inline>, it is agreed that the <fo:inline font-weight="bold" >Insurer</fo:inline> has relied upon the representations and statements contained in the application for this policy (and all such previous applications submitted, or made part of any previous policy which this policy may succeed in time) including materials submitted therewith, as being accurate and complete. They shall be the basis of the contract and shall become part of such policy as if physically attached. Such representations and statements are deemed to be material to the risk assumed by the <fo:inline font-weight="bold" >Insurer</fo:inline>.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">H.	Other Insurance  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">All <fo:inline font-weight="bold" >Damages</fo:inline> and <fo:inline font-weight="bold" >Defense Expenses</fo:inline> payable under this policy shall be in excess of and shall not contribute with other existing insurance including, but not limited to, any insurance under which there is a duty to defend, regardless of whether any <fo:inline font-weight="bold" >Damages</fo:inline> or <fo:inline font-weight="bold" >Defense Expenses</fo:inline> are collectible or recoverable under such other insurance, unless such other insurance is written specifically excess of this policy.  This policy shall not be subject to the terms or conditions of any other insurance.</fo:block>
				
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">I.	Headings and Titles  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">The headings, sub-headings, and titles of this policy are for descriptive and reference purposes only. They are not to be deemed in any way to limit, modify, or affect the terms and conditions of this policy.</fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">J.	Assignment of Interest  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" start-indent="1.5cm" text-align="left" >
					This policy and any and all rights hereunder are not assignable without the written consent of the <fo:inline font-weight="bold" >Insurer</fo:inline>.
				</fo:block>
				<!-- <fo:block margin-top="10mm"  font-size="10px"  color="grey" text-align="left">
				 --><!-- <xsl:choose>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'OK'">
						LPL – 100 – OK (09/10)
					</xsl:when>
					<xsl:when test="response/policy_freeform_01/data/StateCode= 'ND'">
							ND LPL-100 (06/15)
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1000910" />
					</xsl:otherwise>
				</xsl:choose> -->
				
				<!-- </fo:block>	 -->
				<!-- <fo:block margin-top="2.5cm"/> -->
				<fo:block margin-top="2cm" />
				 
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 11 of 12</fo:block>
        	 </fo:block>
			 
				
				 <fo:block page-break-before="always"/>
				<fo:block margin-top="2mm"/>
				
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">K.	Changes  </fo:block>
				<fo:block margin-top="2mm"/>
			 	<fo:block font-size="10px" text-align="left" start-indent="1.5cm">Notice to any agent or knowledge possessed by any agent or other person acting on behalf of the <fo:inline font-weight="bold" >Insurer</fo:inline> shall not effect a waiver or a change in any part of this policy or estop the <fo:inline font-weight="bold" >Insurer</fo:inline> from asserting any right under the terms and conditions of this policy. Nor shall any terms or conditions be waived or changed except by written endorsement issued to form a part of this policy.</fo:block>
				
            
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">L.	Territory  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">This policy applies to <fo:inline font-weight="bold" >Professional Services</fo:inline> taking place anywhere in the world provided that suit is brought and maintained against the <fo:inline font-weight="bold" >Insured</fo:inline>  within:</fo:block>
				
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1. the United States of America, its territories or possessions;</fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2. Puerto Rico; or</fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.	Canada.</fo:block>
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">M.	Named Insured Sole Agent  </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">The <fo:inline font-weight="bold" >Named Insured</fo:inline> will be the sole agent and will be authorized to act on behalf of all<fo:inline font-weight="bold" > Insureds</fo:inline> </fo:block>
				
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">1.&#160;&#160;&#160;&#160;&#160;for the purpose of giving or   receiving any notices, any amendments to or cancellation of this policy;</fo:block> 
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">2.&#160;&#160;&#160;&#160;&#160;for the completing of any applications and the making of any statements, representations and warranties </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160; for the policy; and </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">3.&#160;&#160;&#160;&#160;&#160;for the payment of the Deductible and the exercising or declining to exercise any right under this policy, </fo:block>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">&#160; &#160; &#160; &#160; including the purchase of any Extended Reporting Period.</fo:block>
				
				
				<fo:block margin-top="2mm"/>
				<fo:block font-weight="bold" font-size="12px" text-align="left" text-indent="1cm">N.	Liberalization    </fo:block>
				<fo:block margin-top="2mm"/>
				<fo:block font-size="10px" text-align="left" start-indent="1.5cm">If during the <fo:inline font-weight="bold" >Policy Period</fo:inline>,   the <fo:inline font-weight="bold" >Insurer </fo:inline>adopts any provision that would broaden the coverage under this policy without an additional premium charge, the broadened coverage shall automatically apply to this policy.</fo:block>
				
				<fo:block margin-top="16cm"/>
					
			  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-P001 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 12 of 12</fo:block>
        	 </fo:block>
			
								
				  </fo:block>
			 
     </xsl:template>
</xsl:stylesheet>

