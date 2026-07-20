<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="PredecessorFirmDefinition">
	
				    <fo:block text-align="center" font-size="12px">THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1260113" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">PREDECESSOR FIRM DEFINITION AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="15mm"/>
				    
				     <fo:block text-align="left"  font-size="10px">It is agreed that section IV. DEFINITIONS, Predecessor Firm is deleted in its entirety and replaced with the following:</fo:block>
				     <fo:block margin-top="10mm"/>
				     <fo:block text-align="left" font-size="10px"><fo:inline font-style="italic" font-weight="bold">Predecessor Firm</fo:inline> means an individual, partnership, professional corporation, professional association, limited liability corporation or partnership which was at all times engaged in <fo:inline font-style="italic" font-weight="bold">Professional Services</fo:inline> and to whose financial assets and liabilities the <fo:inline font-style="italic" font-weight="bold">Named Insured</fo:inline>  is  the majority successor in interest prior to the effective date as stated in Item 2. of the Declarations.  <fo:inline font-style="italic" font-weight="bold">Predecessor Firm</fo:inline> does not include any individual, partnership, professional corporation, professional association, limited liability corporation or partnership which was at all times engaged in <fo:inline font-style="italic" font-weight="bold">Professional Services</fo:inline> and to whose financial assets and liabilities the <fo:inline font-style="italic" font-weight="bold">Named Insured</fo:inline> becomes the majority successor in interest subsequent to the effective date of this policy as stated in Item 2. of the Declarations unless the <fo:inline font-style="italic" font-weight="bold">Insurer</fo:inline> at its sole discretion agrees to include such entity.  Should the <fo:inline font-style="italic" font-weight="bold">Insurer</fo:inline> agree to include such entity it may do so for an additional premium and/or with amended policy terms and conditions.</fo:block>
				     
			    
				     <fo:block margin-top="50mm"/>
				     <fo:block text-align="left" start-indent="4cm" font-size="10px">All other terms, conditions and limitations of the policy remain unchanged.</fo:block>
				     <fo:block margin-top="15mm"/>
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
	         	<fo:block margin-top="50mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1260113"/></fo:block>
				
     </xsl:template>
</xsl:stylesheet>




					
				    	