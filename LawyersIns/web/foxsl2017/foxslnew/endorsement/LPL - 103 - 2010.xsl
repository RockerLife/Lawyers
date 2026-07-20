<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="Policycoverletter3">
					<fo:block text-align="center" font-weight="bold" font-size="11px">
						THIS ENDORSEMENT CHANGES THE POLICY. PLEASE READ IT CAREFULLY.
					</fo:block>
					
					<fo:block margin-top="3mm">
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="120mm" />
							<fo:table-column column-width="70mm" />				
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Named Insured: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Endorsement Number: LPL - 103 (04/17)</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>	
						</fo:table>
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="60mm" />
							<fo:table-column column-width="60mm" />
							<fo:table-column column-width="70mm" />				
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Policy Number: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Policy Period:</fo:block>
										<fo:block font-size="10px" padding-left="8pt"><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /> to <xsl:value-of select="response/policycoverage_freeform_01/data/PolicyExpirationDate" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policycoverage_freeform_01/data/TransactionEffectiveDate" /></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
								
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="190mm" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Insurer: United States Fire Insurance Company</fo:block>
									</fo:table-cell>					
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="7px" padding-left="20pt" text-indent="0.5cm">Insert the policy number. The remainder of the information is to be completed only when this endorsement is issued subsequent to the preparation of the policy.</fo:block>
					<fo:block margin-top="2mm">
					</fo:block>
					
				   
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >DEDUCTIBLE DOES NOT APPLY TO <fo:inline font-weight="bold" font-style="italic" >DEFENSE EXPENSES</fo:inline> </fo:block>
				    
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="left" font-size="10px" >In consideration of premium charged it is agreed that:</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="1cm" >(1) The sentence appearing in Item 4 of the Declarations, Deductible, are deleted and replaced with the following</fo:block>
				     <fo:block text-align="left" font-size="10px" start-indent="1.5cm" >sentence:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="1.5cm">This amount does not apply to <fo:inline font-weight="bold" font-style="italic" >Defense Expenses</fo:inline>.</fo:block>
				      
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >(2). Section II of the policy, Limits of Liability and Deductible, paragraph B. Deductible, is deleted and replaced with the</fo:block>
  					 <fo:block text-align="left" font-size="10px" start-indent="0.7cm">following: </fo:block>
  					 
    				 <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px">B.   Deductible</fo:block>
                     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" start-indent="0.5cm" >The <fo:inline font-weight="bold" font-style="italic" >Insurer</fo:inline> shall be liable for amounts payable under this policy for <fo:inline font-weight="bold" font-style="italic" >Damages or Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4 of the Declarations. The Deductible amount shall apply separately to each and every <fo:inline font-weight="bold" font-style="italic" >Claim</fo:inline>, unless amended by specific endorsement of this policy, and shall be borne by the <fo:inline font-weight="bold" font-style="italic" >Insured</fo:inline> and shall remain uninsured. The Deductible amount shall not apply to <fo:inline font-weight="bold" font-style="italic" >Defense Expenses</fo:inline>. In the event of <fo:inline font-weight="bold" font-style="italic" >Related Claims</fo:inline> that are deemed a single <fo:inline font-weight="bold" font-style="italic" >Claim</fo:inline> pursuant to section II C. below, a single Deductible amount will apply.</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px">(3).  Section VI. General Conditions, Paragraph B. Defense and Settlement, sub-paragraph 1. is amended by deletion of</fo:block>
				     <fo:block text-align="left" font-size="10px" start-indent="0.7cm">this the third sentence:</fo:block>
				     
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-size="10px" >"The <fo:inline font-weight="bold" font-style="italic" >Insureds</fo:inline> shall pay any <fo:inline font-weight="bold" font-style="italic" >Defense Expenses</fo:inline> within the applicable Deductible shown in Item 4 of Declarations".</fo:block>
				      
				      <fo:block margin-top="15mm"/>
				      <fo:block text-align="center" font-size="10px"  >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				      <fo:block margin-top="75mm"/>
			          <fo:block>
				       <fo:table  text-align="center">
					    <fo:table-column column-width = "60mm" />
				        <fo:table-column column-width = "120mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block    text-align="center"><fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="2em" content-width="5em"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   text-align="center"><xsl:value-of select="response/general_freeform_01/data/CurrentDate"/></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 <fo:table-row>
					    		  		<fo:table-cell padding-left="4pt" padding-top="4pt">
					               			<fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					               		<fo:table-cell padding-left="10pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					             </fo:table-row>
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center">Authorized Representative</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="10pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">Date</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row> 					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>	
				<fo:block margin-top="5mm"/>
		        <fo:block  font-size="10px" color="grey" text-align="left">LPL- 103 (04/17)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<fo:inline text-align="right" width="50%">Page 1 of 1</fo:inline></fo:block>
		          
     </xsl:template>
</xsl:stylesheet>




					
				    	