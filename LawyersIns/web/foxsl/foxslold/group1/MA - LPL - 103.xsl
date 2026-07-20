<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="MADefenceExpenses">
	
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/MALPL1030313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">DEDUCTIBLE DOES NOT APPLY TO DEFENSE EXPENSES</fo:block>
				     
				     <fo:block margin-top="5mm"/>
				    
				     <fo:block text-align="left"  font-size="10px">In consideration of the premium charged it is agreed that:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >(1)	The sentence appearing in Item 4 of the Declarations, Deductible, is deleted and replaced with the following sentence:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">This amount does not apply to <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>.</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >(2). Section II of the Policy, Limits of Liability and Deductible, paragraph B. Deductible, is deleted and replaced with the following:</fo:block>
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px">B. Deductible</fo:block>
			    
			   		 <fo:block margin-top="8mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be liable for amounts payable under this policy for <fo:inline font-weight="bold" font-style="italic">Damages or Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4 of the Declarations. The Deductible amount shall apply separately to each and every <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>, unless amended by specific endorsement of this policy, and shall be borne by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> and shall remain uninsured. The Deductible amount shall not apply to <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>. In the event of <fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline> that are deemed a single <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> pursuant to Section II C. below, a single Deductible amount will apply.</fo:block>
			    
			    	 <fo:block margin-top="8mm"/>
				     <fo:block text-align="left" font-size="10px">(3) Section VI. General Conditions, Paragraph B. Defense and Settlement, sub-paragraph 1. is amended by deletion of this the third sentence:   </fo:block>
			    
			    	 <fo:block margin-top="4mm"/>
				     
				     <fo:block text-align="left" font-size="10px">"The <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> shall pay any <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> within the applicable Deductible shown in Item 4 of the Declarations."</fo:block>
				 
				 
				     <fo:block margin-top="5cm"/>
				     <fo:block text-align="left" start-indent="4cm" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="8mm"/>
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
	         	<fo:block margin-top="10mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/MALPL1030313"/></fo:block>
				   
     </xsl:template>
</xsl:stylesheet>




					
				    	