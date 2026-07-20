<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="NMAmendatory">
	
				    <fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLNMAmendatory0313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="1mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">DEFENSE EXPENSES IN ADDITION TO THE LIMIT OF LIABILITY</fo:block>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">DEDUCTIBLE DOES NOT APPLY TO DEFENSE EXPENSES</fo:block>
				     <fo:block margin-top="3mm"/>
				    
				     <fo:block text-align="left"  font-size="10px">This endorsement amends the Limits of Liability so that the <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> are paid in addition to the Limit of Liability available to pay <fo:inline font-style="italic" font-weight="bold">Damages</fo:inline>; and amends the Deductible so as not to apply to <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline>.</fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">In consideration of the premium charged it is agreed that:</fo:block>
				     
				     <fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px">1. The last sentence of the preamble appearing above Item 1 of the Declarations is deleted.</fo:block>
			    
			   		 <fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px">2. Item 3 of the Declarations, Limits of Liability, is amended as follows:</fo:block>
			    
			    	 <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">"each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>" is amended to read "<fo:inline font-style="italic" font-weight="bold">Damages</fo:inline> each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>"</fo:block>
			    
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">"<fo:inline font-style="italic" font-weight="bold">Policy Aggregate</fo:inline>" is amended to read "<fo:inline font-style="italic" font-weight="bold">Damages Policy Aggregate</fo:inline>"</fo:block>
			    		
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">3. Section IV. DEFINITIONS, <fo:inline font-style="italic" font-weight="bold">Policy Aggregate</fo:inline> is deleted and replaced, wherever such phrase is found in the policy, with the following:</fo:block>
			    
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm"><fo:inline font-style="italic" font-weight="bold">Damages Policy Aggregate</fo:inline> means the amount identified in Item 3. of the Declarations which represents the maximum amount of the <fo:inline font-style="italic" font-weight="bold">Insurer's</fo:inline> liability for all <fo:inline font-style="italic" font-weight="bold">Claims</fo:inline> under this policy, inclusive of any applicable Extended Reported Period, if purchased.</fo:block>
			    	
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">4. Section II of the Policy, Limits of Liability and Deductible, Paragraph A. Limits of Liability, is amended as follows:</fo:block>
			    	
			    	
			    	<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="3cm">1.	Subject to 2 that follows, the <fo:inline font-style="italic" font-weight="bold">Insurer's</fo:inline> Limit of Liability for "<fo:inline font-style="italic" font-weight="bold">Damages</fo:inline> each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>" first made and reported to the <fo:inline font-style="italic" font-weight="bold">Insurer</fo:inline> during the <fo:inline font-style="italic" font-weight="bold">Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amount shown in Item 3. of the Declarations as applicable to ""<fo:inline font-style="italic" font-weight="bold">Damages</fo:inline> each <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline>"".</fo:block>
			    	
			    	<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="3cm">2.	Subject to 1 above, the <fo:inline font-style="italic" font-weight="bold">Insurer's</fo:inline> Limit of Liability for <fo:inline font-style="italic" font-weight="bold">Damages</fo:inline> for all <fo:inline font-style="italic" font-weight="bold">Claims</fo:inline> first made and reported to the <fo:inline font-style="italic" font-weight="bold">Insurer</fo:inline> during the <fo:inline font-style="italic" font-weight="bold">Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amount shown in Item 3. of the Declarations as applicable to "<fo:inline font-style="italic" font-weight="bold">Damages Policy Aggregate</fo:inline>".</fo:block>
			    			    	
			    	<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="3cm">3.	<fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> are paid in addition to the Limit of Liability</fo:block>
			    
			    	<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="3cm">7.     If the <fo:inline font-style="italic" font-weight="bold">Insurer</fo:inline> has exhausted the applicable Limit of Liability by payment of <fo:inline font-style="italic" font-weight="bold">Damages</fo:inline> or by tender of the remaining Limit of Liability into court, it shall have no further duties to the <fo:inline font-style="italic" font-weight="bold">Insured</fo:inline> under this policy.</fo:block>
			    	
			    		<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">It is further agreed that:</fo:block>
			    	
			    		<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">1.  The sentence appearing in Item 4 of the Declarations, Deductible,  "This amount applies to <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> unless this Section is  amended by specific endorsement of this policy. " is deleted and replaced with the following sentence:</fo:block>
			    
			    		
			    		<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm"><fo:inline font-style="italic" font-weight="bold">This amount does not apply to Defense Expenses</fo:inline>.</fo:block>
			    	
			    		
			    		<fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px">2.  Section II of the Policy, Limits of Liability and Deductible, Paragraph B. Deductible, is deleted and replaced with the following paragraph B:</fo:block>
						
							<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">B.  The <fo:inline font-style="italic" font-weight="bold">Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-style="italic" font-weight="bold">Damages</fo:inline> or <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4 of the Declarations. The Deductible amount shall apply separately to each and every <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline> unless amended by specific endorsement of this policy, and shall be borne by the <fo:inline font-style="italic" font-weight="bold">Insured</fo:inline> and shall remain uninsured. The Deductible amount shall not apply to <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline>. In the event of <fo:inline font-style="italic" font-weight="bold">Related Claims</fo:inline> that are deemed a single <fo:inline font-style="italic" font-weight="bold">Claim</fo:inline> pursuant to Section II C. below, a single Deductible amount will apply.</fo:block>
		
			    		<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px">3. Section VI. General Conditions, Paragraph B. Defense and Settlement, sub-paragraph 1. is amended by deletion of this, the third sentence: </fo:block>
						
						<fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">"The <fo:inline font-style="italic" font-weight="bold">Insureds</fo:inline> shall pay any <fo:inline font-style="italic" font-weight="bold">Defense Expenses</fo:inline> within the applicable Deductible shown in Item 4 of the Declarations."</fo:block>
		
			    
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" start-indent="4cm" font-size="10px">All other terms, conditions and limitations of the policy remain unchanged.</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block>
				       <fo:table  text-align="center">
					   <fo:table-column column-width = "80mm"/>
				       <fo:table-column column-width = "80mm"/>
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/authorized_signature"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/date"/></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 <fo:table-row>
					    		  		<fo:table-cell padding-left="4pt" padding-top="4pt">
					               			<fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					             </fo:table-row>
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center">Authorized Representative</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">Date</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>				       
	         	<fo:block margin-top="1mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLNMAmendatory0313"/></fo:block>
				
     </xsl:template>
</xsl:stylesheet>




					
				    	