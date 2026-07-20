<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-AZ" >
		
		    
				    <fo:block text-align="center" font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPLAMENDAZ0910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    
   <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				   
				      <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">ARIZONA AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="10mm"/>
				    
				    
				   
				
				    <fo:block text-align="left" font-size="10px" >This policy may not be canceled by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> for any reason at any time except for non payment of premium or deductible by the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>.  However, VI., General Conditions, D. Cancellation and Nonrenewal, paragraph #3 is deleted and replaced with the following:</fo:block>
					<fo:block margin-top="4mm"/>
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">3. This policy may be non-renewed by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> by delivering to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or by mailing to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at its last known address written notice of such nonrenewal at least sixty days prior to the expiration of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>. The proof of mailing of such notice shall be sufficient proof of notice.</fo:block>
				 	
				 	<fo:block margin-top="2mm"/>
				 	<fo:block text-align="left" start-indent="1cm" font-size="10px">The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> is not required to provide such notice of non renewal if:</fo:block>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">- the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> or an insurance company with in the same group as the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> offers a renewal policy;</fo:block>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">- the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> has replaced coverage, or has agreed in writing to do so</fo:block>
					
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" start-indent="1cm" font-size="10px">If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> offers a renewal policy which contains:</fo:block>
					<fo:block margin-top="2mm"/>
					
					<fo:block text-align="left" start-indent="2cm" font-size="10px">- an increase in premium, or</fo:block>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">- an increase in <fo:inline font-weight="bold" font-style="italic">Deductible</fo:inline>, or</fo:block>
					<fo:block text-align="left" start-indent="2cm" font-size="10px">- a reduction in the <fo:inline font-weight="bold" font-style="italic">Limit of Liability</fo:inline>, or</fo:block>
				 	<fo:block text-align="left" start-indent="2cm" font-size="10px">- a substantial reduction in coverage provided</fo:block>
					
					
					<fo:block margin-top="2mm"/>
					<fo:block text-align="left" font-size="10px" start-indent="1cm">then the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall mail or deliver written notice of such of these changes to the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> at the last
                     mailing address of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> known by the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> at least sixty days prior to the end of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.  If the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> shall fail to comply with this notice, the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> will be extended for sixty days, or for a 
                     lesser period of time if the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> replaces coverage within the sixty day extension, at pro rata additional
                     premium of the rate applicable to the non renewed policy or, the rate presently in effect,  whichever is lower.
                     If the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> accepts the renewal coverage, the premium increase, if any, and any other changes
                     will be effective the day following the end of the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>.</fo:block>
					
				    <fo:block margin-top="70mm"/>
				    
				      <fo:block text-align="center" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
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
			  <fo:block margin-top="10mm"/>
			  <fo:block  font-size="10px"  start-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLAMENDAZ0910" /></fo:block>
		  
     </xsl:template>
</xsl:stylesheet>
