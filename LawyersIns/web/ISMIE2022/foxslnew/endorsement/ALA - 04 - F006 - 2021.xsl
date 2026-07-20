<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="Policycoverletter4">
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
										<fo:block font-size="10px" text-align="left">Endorsement Number: ISMIE ALA-04-F006 (09/01/2021)</fo:block>
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
										<fo:block font-size="10px" text-align="left">Insurer: ISMIE Mutual Insurance Company</fo:block>
									</fo:table-cell>					
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="7px" padding-left="20pt" text-indent="0.5cm">Insert the policy number. The remainder of the information is to be completed only when this endorsement is issued subsequent to the preparation of the policy.</fo:block>
					<fo:block margin-top="2mm">
					</fo:block>
						
					
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >AGGREGATE DEDUCTIBLE FOR ALL CLAIMS</fo:block>
				     
				     <fo:block margin-top="8mm"/>				    
				     <fo:block text-align="left" font-size="10px" >In consideration of premium charged it is agreed that:</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >(1)  Item 4 of the Declarations, Deductible, is deleted and replaced with the following amended Item 4:</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >Item 4. Deductible</fo:block>
				     <fo:block text-align="left" text-indent="2cm" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/>, Aggregate Deductible for all <fo:inline font-weight="bold" >Claims</fo:inline></fo:block>
				     <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >This amount includes <fo:inline font-weight="bold" >Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
				     <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >policy.</fo:block>
				      
				      <fo:block margin-top="4mm"/>
				      <fo:block text-align="left" font-size="10px" >(2) Section II of the policy, Limits of Liability and Deductible, Paragraph B, Deductible, is deleted and replaced with the</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="0.5cm">following Paragraph B. </fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >B. Deductible</fo:block>
				      <fo:block margin-top="2mm"/>
				      <fo:block text-align="left" font-size="10px" start-indent="1.5cm" >The <fo:inline font-weight="bold" >Insurer</fo:inline> shall be liable for amounts payable under this policy for <fo:inline font-weight="bold" >Damages</fo:inline> or <fo:inline font-weight="bold" >Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4 in the Declarations. The Deductible amount shall be borne by the <fo:inline font-weight="bold" >Insured</fo:inline> and shall remain uninsured .  The Deductible amount applies to the payment of <fo:inline font-weight="bold" >Damages</fo:inline> and <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, unless amended by specific endorsement of this policy.  If the <fo:inline font-weight="bold" >Insurer</fo:inline> advances any amounts within the Deductible, the <fo:inline font-weight="bold" >Named Insured</fo:inline> shall reimburse the <fo:inline font-weight="bold" >Insurer</fo:inline> within thirty days of the <fo:inline font-weight="bold" >Insurer's</fo:inline> request to do so.  In the event of <fo:inline font-weight="bold" >Related Claims</fo:inline>, a single Deductible amount will apply.</fo:block> 
				       
				         
				         
				      <fo:block margin-top="15mm"/>
				      <fo:block text-align="left" font-size="10px"  >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				       <fo:block margin-top="85mm"/>
			           <fo:block>
				       <fo:table>
					<fo:table-column column-width="80mm"/>
					<fo:table-column column-width="40mm"/>
					<fo:table-column column-width="60mm"/>
					<fo:table-body>
						<fo:table-row>
						<fo:table-cell>
						
						 <xsl:choose>
	                    	<xsl:when test="response/isShowSignature='Y'">
		                    	<fo:block font-size="9px" color="black" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black" text-align="center">
									<fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="10em" content-width="8em"/>
								</fo:block> 
						  	</xsl:when>
		                  	<xsl:otherwise>
		                        <fo:block font-size="9px" color="black" border-bottom-width="0.9pt" border-bottom-style="solid" border-bottom-color="black" text-align="center">
									<fo:external-graphic src="../LawyersIns/image/BlankSingnature.png" content-height="10em" content-width="8em"/>
								</fo:block>
		                    </xsl:otherwise>
                   		  </xsl:choose>
						<fo:block font-size="9px" color="black" text-align="center">
						&#160;&#160;Authorized Representative
						</fo:block>
						</fo:table-cell>
						<fo:table-cell>
						<fo:block> &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center">
						<fo:block>
						&#160;
						</fo:block>
						<fo:block>
						</fo:block>
						<fo:block margin-top="3mm" font-size="9px" color="black"  border-bottom-style="solid" border-bottom-color="black" text-align="center">
						<xsl:value-of select="response/general_freeform_01/data/CurrentDate"/>
						</fo:block>
						<fo:block font-size="9px" color="black">
						Date
						</fo:block>
						</fo:table-cell>
						</fo:table-row>
						</fo:table-body>
					</fo:table>	 	   
				</fo:block>	
				<fo:block margin-top="1mm"/>
		        <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F006 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
        	 </fo:block>
		           </xsl:template>
</xsl:stylesheet>




					
				    	