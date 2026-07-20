<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="ALA04F014_NJ">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >AGGREGATE DEDUCTIBLE FOR ALL CLAIMS</fo:block>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >DOES NOT APPLY TO DEFENSE EXPENSES</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     <fo:block text-align="left" font-size="10px" >In consideration of: (check one box only)</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of $__________</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
				    
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >and notwithstanding anything to the contrary in the New Jersey Amendatory Endorsement, ISMIE ALA-04-F014, it is agreed that:</fo:block> 
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >1.  Item 4. of the Declarations, Deductible, is deleted and replaced with the following:</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:table border="2pt solid black">
						<fo:table-column column-width="20mm" />
						<fo:table-column column-width="150mm" />				
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left">Item 4.</fo:block>
								</fo:table-cell>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block text-align="left" font-size="10px" ><fo:inline font-weight="bold" >Deductible</fo:inline></fo:block>
								   
									<fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/> Aggregate Deductible for all <fo:inline font-weight="bold" >Claims</fo:inline></fo:block>
								    <fo:block text-align="left" font-size="10px" >This amount does not apply to <fo:inline font-weight="bold" >Defense Expenses.</fo:inline></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
				     
				       <fo:block margin-top="3mm"/>
				        <fo:block text-align="left" font-size="10px" >2. Section II. of the policy, Limits of Liability and Deductible, subsection B., Deductible, is deleted and replaced with the</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="0.4cm">following: </fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" text-indent="0.5cm" font-weight="bold" >B. Deductible</fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm" >The <fo:inline font-weight="bold" >Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-weight="bold" >Damages</fo:inline> which are in excess of the Deductible amount shown in Item 4. of the Declarations. This Deductible amount shall: </fo:block> 
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">1.&#160;&#160;&#160;&#160;be borne by the <fo:inline font-weight="bold" >Insured</fo:inline>; </fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">2.&#160;&#160;&#160;&#160;remain uninsured; and</fo:block>
				      
				      <fo:block text-align="left" font-size="10px" start-indent="1cm">3.&#160;&#160;&#160;&#160;be the maximum amount borne by the <fo:inline font-weight="bold" >Insured</fo:inline> for all <fo:inline font-weight="bold" >Claims</fo:inline> under this policy.</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="1cm"> If the <fo:inline font-weight="bold" >Insured</fo:inline> has paid this amount, no further Deductibles shall apply to <fo:inline font-weight="bold" >Claims</fo:inline>. The Deductible amount applies to the payment of <fo:inline font-weight="bold" >Damages</fo:inline> only and shall not apply to <fo:inline font-weight="bold" >Defense Expenses</fo:inline>.  If the <fo:inline font-weight="bold" >Insurer</fo:inline> advances any amounts within the Deductible, the <fo:inline font-weight="bold" >Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold" >Insurer</fo:inline> within 30 days of the <fo:inline font-weight="bold" >Insurer's</fo:inline> request to do so.  In the event of <fo:inline font-weight="bold" >Related Claims</fo:inline>, a single Deductible amount will apply.</fo:block>
				      
			         <fo:block margin-top="5mm"/>
			        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				        <fo:block margin-top="20mm"/>
				 
					<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F014 NJ (09/01/2021)
		         	</fo:block>
				
				
     </xsl:template>
</xsl:stylesheet>




					
				    	