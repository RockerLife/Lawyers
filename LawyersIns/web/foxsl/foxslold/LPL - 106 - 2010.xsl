<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="Policycoverletter6">
		
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1060910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>
				      <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >OFFICE SHARING EXCLUSION ENDORSEMENT</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     <fo:block text-align="left"  font-size="10px" >It is hereby agreed that Section IV, DEFINITIONS, definition of <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline>, is amended by the addition of the following, paragraph:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >However, the term <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> shall not include any  person(s) or entity (ies) that have no oral or written partnership, shareholder or employment agreement with the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> regardless of whether such person(s) or entity (ies):</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >a.	share office space, office facilities, administrative or clerical personnel or letterhead stationery with the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>; and/or</fo:block>
				      
				      
				      <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >b.	are deemed by a court of competent jurisdiction to be partners, officers, directors, employees, associates, managers or members of the <fo:inline font-weight="bold" font-style="italic">Named Insured.</fo:inline></fo:block>
				      
				     
				         
				       
				         
				         
				        <fo:block margin-top="20mm"/>
				        <fo:block text-align="center" font-size="10px" >All other terms, conditions and limitations of the policy remain unchanged.</fo:block>
				         
				         
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
				       
		       <fo:block margin-top="80mm"/>
		       <fo:block  font-size="10px" text-indent="2cm"  color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1060910" /></fo:block>
				  
     </xsl:template>
</xsl:stylesheet>




					
				    	