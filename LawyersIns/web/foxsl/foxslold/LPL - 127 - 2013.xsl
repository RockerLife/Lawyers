<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="SecurityIncident">
	
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1270313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule" />
  					 </fo:block>
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">SECURITY INCIDENT AND IDENTIFICATION THEFT EXTENSION ENDORSEMENT</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     <fo:block text-align="left"  font-size="10px" >In consideration of premium charged, it is understood and agreed that Section III. EXTENSIONS OF COVERAGE is amended to include the following as paragraph J.:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" ><fo:inline font-weight="bold" font-style="italic">K. 	Security Incident Extension</fo:inline></fo:block>
				     <fo:block text-align="left" font-size="10px" >The <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, for each <fo:inline font-weight="bold" font-style="italic">Security Incident</fo:inline>, for expenses incurred to: </fo:block>

				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">(1) hire cyber forensic analysts to determine the extent of an actual security breach that has occurred; or </fo:block>
				     <fo:block margin-top="1mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="2cm">(2) comply with state or local privacy laws requiring that notification and credit monitoring services are to be provided to individuals when the security, confidentiality, or integrity of their personal information has been compromised, </fo:block>
				     <fo:block margin-top="5mm"/>
				     <fo:block text-align="left" font-size="10px">up to a maximum reimbursable amount of $25,000.  This is the maximum amount the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> will reimburse, regardless of the number of such <fo:inline font-weight="bold" font-style="italic">Security Incidents</fo:inline> or of the number of <fo:inline font-weight="bold" font-style="italic">Insureds</fo:inline> involved in such <fo:inline font-weight="bold" font-style="italic">Security Incidents</fo:inline>, for all <fo:inline font-weight="bold" font-style="italic">Security Incidents</fo:inline> reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline>, during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline>, including any applicable Extended Reporting Period.</fo:block>
				     <fo:block margin-top="5mm"/>
				    
				     <fo:block text-align="left" font-size="10px"><fo:inline font-weight="bold" font-style="italic">Security Incident</fo:inline> means the unauthorized access to, use, misuse or unauthorized use or disclosure of confidential or proprietary data or of personally identifiable information or data containing private or confidential information in connection with the performance of <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> which results in the violation of any Privacy Regulation.</fo:block>
				         
				     <fo:block margin-top="50mm"/>
				     <fo:block text-align="left" start-indent="4cm" font-size="10px" >All other terms, conditions and limitations of the policy remain unchanged.</fo:block>
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
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1270313"/></fo:block>
				 
     </xsl:template>
</xsl:stylesheet>




					
				    	