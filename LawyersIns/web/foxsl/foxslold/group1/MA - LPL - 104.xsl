<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="MAAggregateDeductible">
	
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/MALPL1040313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">AGGREGATE DEDUCTIBLE FOR ALL CLAIMS</fo:block>
				     
				     <fo:block margin-top="5mm"/>
				    
				     <fo:block text-align="left"  font-size="10px">In consideration of the premium charged it is agreed that:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >(1) Item 4 of the Declarations, Deductible, is deleted and replaced with the following amended Item 4:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">Item 4. Deductible.</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="4cm"><fo:inline text-decoration="underline" font-weight="bold" > <xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/></fo:inline> Aggregate Deductible for all Claims </fo:block>
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">This amount includes <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this policy.</fo:block>
			    
			   		 <fo:block margin-top="8mm"/>
				     <fo:block text-align="left" font-size="10px" >(2)  Section II of the policy, Limits of Liability and Deductible, Paragraph B. Deductible, is deleted and replaced with the following Paragraph B.</fo:block>
			    
			    	 <fo:block margin-top="8mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">B. Deductible</fo:block>
			    
			    	 <fo:block margin-top="4mm"/>
				     
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall be liable for amounts payable under this policy for <fo:inline font-weight="bold" font-style="italic">Damages or Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4 in the Declarations. The Deductible amount shall be borne by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, shall remain uninsured and shall be the maximum amount borne by the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> for all <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline> under this policy.  If the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> has paid this amount, no further Deductible shall apply to <fo:inline font-weight="bold" font-style="italic">Claims</fo:inline>.  The Deductible amount applies to the payment of <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline> and to <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>, unless amended by specific endorsement of this policy.  If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> advances any amounts within the Deductible, the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> within thirty days of the <fo:inline font-weight="bold" font-style="italic">Insurer's</fo:inline> request to do so.  In the event of <fo:inline font-weight="bold" font-style="italic">Related Claims</fo:inline>, a single Deductible amount will apply.</fo:block>
				 
				 
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
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/MALPL1040313"/></fo:block>
				  
     </xsl:template>
</xsl:stylesheet>




					
				    	