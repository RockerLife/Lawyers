<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	
	<xsl:template match="/" name="Policycoverletter36103">
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
										<fo:block font-size="10px" text-align="left">Endorsement Number: LPL-136 (04/17)</fo:block>
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
				     <fo:block text-align="center" font-weight="bold"  font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="12px"  >ENDORSEMENT DELETION</fo:block>
				      		    
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px"  > In consideration of: (check one box only)</fo:block>				     
				    
				     <xsl:choose>
                    	<xsl:when test="response/PremiumCharged= 'N'">
                         <fo:block margin-top="6mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  
					     return premium of <xsl:value-of select="response/PremiumValue"/></fo:block>
					     <fo:block margin-top="6mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     premium charged </fo:block>
                      </xsl:when>
                      <xsl:when test="response/PremiumCharged= 'S'">
                        <fo:block margin-top="6mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     a return premium of $</fo:block>
					     <fo:block margin-top="6mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     the premium charged </fo:block>
                      </xsl:when>                      
                      <xsl:otherwise>
                         <fo:block margin-top="6mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     a return premium of $</fo:block>
					     <fo:block margin-top="6mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  
					     the premium charged </fo:block>
                      </xsl:otherwise>
                     </xsl:choose>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block text-align="left" font-size="10px" >it is agreed that Endorsement #LPL-103 (04/17) of this policy hereby deleted.</fo:block>
                    
                    <fo:block margin-top="10mm"/>
			        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				    <fo:block margin-top="120mm"/>
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
				
				  			       
	         	<fo:block margin-top="15mm"/>
	         	<fo:block  font-size="10px" color="grey" text-align="left">LPL-136 (04/17)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<fo:inline text-align="right" width="50%">Page 1 of 1</fo:inline></fo:block>

				  
     </xsl:template>
</xsl:stylesheet>




					
				    	