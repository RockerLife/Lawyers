<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="Policycoverletter2">
	
				    <fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block >
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1020910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				      <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  ><fo:inline font-style="italic">DEFENSE EXPENSES </fo:inline>IN ADDITION TO THE LIMIT OF LIABILITY</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     <fo:block text-align="left"  font-size="10px" >This endorsement amends the limit of liability so that <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> are paid in addition to and equal to the limit of liability available to pay <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>.  Thereafter, <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline> are part of and reduce the limit of liability to pay <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>.</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >In consideration of the premium charged it is agreed that:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >1- The last sentence of the Preamble appearing above Item 1 of the Declarations is deleted.</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >2. Item 3 of the Declarations, Limit of Liability, is deleted and amended to read:</fo:block>
                     <fo:block margin-top="4mm"/>
				      <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>, Each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>;</fo:block>
				      <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>, Each <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>;</fo:block>
				      <fo:block margin-top="4mm"/>
				       <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" font-style="italic">Damages Policy Aggregate</fo:inline>;</fo:block>
				      <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" font-style="italic">Defense Expenses Policy Aggregate</fo:inline>.</fo:block>
				      
				        <fo:block margin-top="4mm"/>
				        <fo:block text-align="left" font-size="10px" >3. Section II of the Policy, Limits of Liability and Deductible, Paragraph A, Limits of Liability, is amended as follows:</fo:block> 
				        <fo:block margin-top="4mm"/>
				        <fo:block text-align="left" text-indent="2cm" font-size="10px" >-  the word “combined” is deleted where it appears in 1. and 2.;</fo:block>
				        <fo:block text-align="left" text-indent="2cm" font-size="10px" >-  3. is deleted in its entirety;</fo:block> 
				        <fo:block margin-top="4mm"/>
				        <fo:block text-align="left" font-size="10px" >4. The following is added to Section II of the policy, Limits of Liability and Deductible, paragraph A. Limits of Liability:</fo:block>
				        <fo:block margin-top="4mm"/>
				        <fo:block text-align="left" start-indent="2cm" font-size="10px" >In the event the amount shown above for  <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>, each  <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> is exhausted, then  <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>
               incurred thereafter, shall be part of and reduce the Limit of Liability as shown for  <fo:inline font-weight="bold" font-style="italic">Damages</fo:inline>, each  <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline>.  In the 
               event the amount shown above for  <fo:inline font-weight="bold" font-style="italic">Defense Expenses Policy Aggregate</fo:inline> is exhausted, then  <fo:inline font-weight="bold" font-style="italic">Defense Expenses</fo:inline>
               incurred thereafter, shall be part of and reduce the Limit of Liability as shown for  <fo:inline font-weight="bold" font-style="italic">Damages Policy Aggregate</fo:inline>.
				        </fo:block>
				         
				         
				         
				       <fo:block margin-top="20mm"/>
				        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unchanged.</fo:block>
				         
				         
				          <fo:block margin-top="15mm"/>
				          <fo:block>
				       <fo:table  text-align="center">
					    <fo:table-column column-width = "80mm" />
				        <fo:table-column column-width = "80mm" />
				       
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
	         	<fo:block margin-top="50mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1020910"/></fo:block>
				  
     </xsl:template>
</xsl:stylesheet>




					
				    	