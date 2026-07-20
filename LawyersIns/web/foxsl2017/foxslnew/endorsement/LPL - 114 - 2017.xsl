<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	
	<xsl:template match="/" name="Policycoverletter14">
					<fo:block>				  	
						<xsl:call-template name="CommonHeaderFilled" />						           	
					</fo:block>
				   				   	
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="center" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="12px"  >CANCELLATION OF THE POLICY ENDORSEMENT</fo:block>
				     <fo:block text-align="center" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="12px"  >(CHECK ONE BOX ONLY)</fo:block>
				     <fo:block margin-top="6mm"/>			    
				     
				     
				     <fo:block text-align="left" font-size="10px" >
				     <xsl:choose>
				         <xsl:when test="response/endorsement_freeform_01/data/CancellationReasonID = '1' or response/endorsement_freeform_01/data/CancellationReasonID = '2' or response/endorsement_freeform_01/data/CancellationReasonID = '3'">
				           <fo:external-graphic src="../LawyersIns/image//check-btn6.png" content-height="1em" content-width="1em"/>
				         </xsl:when>
				         <xsl:otherwise>
				          <fo:external-graphic src="../LawyersIns/image//check-btn4.png" content-height="1em" content-width="1em"/>
				         </xsl:otherwise>
				     </xsl:choose>				     
				       Cancellation by the <fo:inline font-weight="bold" >Named Insured</fo:inline>:
				     </fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" text-indent="0.5cm" font-size="10px" >In consideration of a return premium of
					     <xsl:choose>
					         <xsl:when test="response/endorsement_freeform_01/data/CancellationReasonID = '1' or response/endorsement_freeform_01/data/CancellationReasonID = '2' or response/endorsement_freeform_01/data/CancellationReasonID = '3'">
					           <xsl:value-of select="response/endorsement_freeform_01/data/InvoicedPremium" /> 				     
					         </xsl:when>
					         <xsl:otherwise>
					          $______
					         </xsl:otherwise>
					     </xsl:choose>   
				     and in accordance with the <fo:inline font-weight="bold" >Named Insured's</fo:inline> request,</fo:block>
				     
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px" >
					     <xsl:choose>
					         <xsl:when test="response/endorsement_freeform_01/data/CancellationReasonID = '4'">
					           <fo:external-graphic src="../LawyersIns/image//check-btn6.png" content-height="1em" content-width="1em"/>
					         </xsl:when>
					         <xsl:otherwise>
					          <fo:external-graphic src="../LawyersIns/image//check-btn4.png" content-height="1em" content-width="1em"/>
					         </xsl:otherwise>
					     </xsl:choose>
				       Cancellation by the <fo:inline font-weight="bold" >Insurer</fo:inline>:</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" text-indent="0.5cm" font-size="10px" >In consideration of a return premium of
					     <xsl:choose>
					         <xsl:when test="response/endorsement_freeform_01/data/CancellationReasonID = '4'">
					           <xsl:value-of select="response/endorsement_freeform_01/data/InvoicedPremium" /> 				     
					         </xsl:when>
					         <xsl:otherwise>
					         $______,
					         </xsl:otherwise>
					     </xsl:choose> 
				     </fo:block>
				    
				     
				     <fo:block margin-top="6mm"/>
				     <fo:block text-align="left" font-size="10px" >it is agreed that this policy is cancelled effective <xsl:value-of select="response/endorsement_freeform_01/data/TransactionEffectiveDate" />; thus, Item 2 of the Declarations is amended to read:</fo:block>
                     
                     <fo:block margin-top="4mm"/>
                     <fo:table border="2pt solid black" text-align="center">
					    <fo:table-column column-width = "20mm" />
				        <fo:table-column column-width = "170mm" />				
						<fo:table-body>
							<fo:table-row>
			               		<fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt"><fo:block font-size="10px"   text-align="left">Item 2.</fo:block></fo:table-cell>
			               		<fo:table-cell border="2pt solid black" padding-bottom="4mm" padding-left="4pt">
			               			 <fo:block font-size="10px"   text-align="left"><fo:inline font-weight="bold" >Policy Period:</fo:inline>  From  <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" />  (Effective)  &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160; To  <xsl:value-of select="response/endorsement_freeform_01/data/TransactionEffectiveDate" />  (Expiration)  </fo:block>
			               			 <fo:block font-size="10px"   text-align="left" text-indent="2.4cm">(12:01 a.m. local time at the address shown in Item 1.)</fo:block>
			               		</fo:table-cell>
			    		  	</fo:table-row>
						</fo:table-body>	
					</fo:table>     
			        <fo:block margin-top="6mm"/>
			        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				         
			          <fo:block margin-top="100mm"/>
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
				                   		  <fo:table-cell padding-top="2pt" padding-left="4pt"><fo:block   text-align="center"><xsl:value-of select="response/general_freeform_01/data/CurrentDate"/></fo:block></fo:table-cell>
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
	         	<fo:block  font-size="10px" color="grey" text-align="left">LPL-114 (04/17)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<fo:inline text-align="right" width="50%">Page 1 of 1</fo:inline></fo:block>
	         	  
     </xsl:template>
</xsl:stylesheet>




					
				    	