<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	
	<xsl:template match="/" name="Policycoverletter32">
					<fo:block>				  	
						<xsl:call-template name="CommonHeaderFilled" />						           	
					</fo:block>
				   				   	
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="12px"  >REINSTATEMENT ENDORSEMENT</fo:block>
				     <fo:block margin-top="8mm"/>			    
				     
				     <fo:block text-align="left" font-size="10px" >In consideration of the premium charged, it is agreed that Endorsement # LPL-114 (04/17) effective <xsl:value-of select="response/endorsement_freeform_01/data/TransactionEffectiveDate" /> of this</fo:block>
                     <fo:block text-align="left" font-size="10px" >policy is hereby deleted, null and void.</fo:block>
                    
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >It is further agreed that the policy is reinstated without lapse in coverage.</fo:block>
                                               
			        <fo:block margin-top="8mm"/>
			        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				          <fo:block margin-top="8mm"/>
			       <!--  <fo:block text-align="left" font-weight="bold" color="red" font-size="10px" >[Note: The Effective Date of this Endorsement would be the same as the Effective Date of the Cancellation Endorsement]</fo:block>
			        -->   <fo:block margin-top="135mm"/>
			          <fo:block>
				       <fo:table  text-align="center">
					    <fo:table-column column-width = "60mm" />
				        <fo:table-column column-width = "120mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					    		  <fo:table-cell>
					                    <xsl:choose>
					                    	<xsl:when test="response/isShowSignature='Y'">
						                    	<fo:block font-size="9px" text-align="center">
													<fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="10em" content-width="8em"/>
												</fo:block> 
										  	</xsl:when>
						                  	<xsl:otherwise>
						                        <fo:block font-size="9px"  text-align="center">
													<fo:external-graphic src="../LawyersIns/image/BlankSingnature.png" content-height="10em" content-width="8em"/>
												</fo:block>
						                    </xsl:otherwise>
				                   		  </xsl:choose> 
				                   		  </fo:table-cell>
				                   		  <fo:table-cell  padding-left="4pt" padding-top="4pt"><fo:block   text-align="center"><xsl:value-of select="response/general_freeform_01/data/CurrentDate"/></fo:block></fo:table-cell>
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
	         	<xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
	         	<fo:block  font-size="10px" color="grey" text-align="left">LPL-132 (04/17)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<fo:inline text-align="right" width="50%">Page 1 of 1</fo:inline></fo:block>
              </xsl:if>
              
              <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
				  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F015 (09/01/2021)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;<fo:inline text-align="right" width="50%">Page 1 of 1</fo:inline></fo:block>
				  </xsl:if>
     </xsl:template>
</xsl:stylesheet>




					
				    	