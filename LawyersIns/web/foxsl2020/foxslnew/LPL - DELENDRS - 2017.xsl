<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	
	<xsl:template match="/" name="DeleteEndorsement">
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
									<fo:block font-size="10px" text-align="left">To be attached legal part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
								</fo:table-cell>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/endorsment_freeform_01/data/LastUpdateTimeStamp" /></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
					<fo:table border="2pt solid black" text-align="center">
						<fo:table-column column-width="120mm" />
						<fo:table-column column-width="70mm" />				
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block>
								</fo:table-cell>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left">Endorsement No: LPL - 136 - 2010 /></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
				</fo:block>
				   				   	
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >AMENDATORY ENDORSEMENT</fo:block>
				     <fo:block margin-top="8mm"/>			    
				     
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block text-align="left" font-size="10px" >In consideration of premium charged, it is agreed that Endorsement#_________ of this policy hereby deleted, null and void.</fo:block>
                    
                    <fo:block margin-top="170mm"/>
			        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				         
			          <fo:block margin-top="10mm"/>
			          <fo:block>
				       <fo:table  text-align="center">
					    <fo:table-column column-width = "60mm" />
				        <fo:table-column column-width = "120mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/authorized_signature"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/date"/></fo:block></fo:table-cell>
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
				
				  			       
	         	<fo:block margin-top="10mm"/>
	         	<fo:block  font-size="10px" color="grey" text-align="left">LPL - 114 - 2017</fo:block>
				  
     </xsl:template>
</xsl:stylesheet>




					
				    	