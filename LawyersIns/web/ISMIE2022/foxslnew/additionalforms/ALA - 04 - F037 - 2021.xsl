<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="ALA04F037">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
		    	   
  					 
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">SPECIFIC CLAIMANT EXCLUSION ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block  font-size="10px">In consideration of the premium charged, it is agreed that Section V., Exclusions, is amended by addition of the following exclusion:</fo:block>
				 	 <fo:block margin-top="5mm"/>
				 	 <fo:block font-size="10px" ><fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.4em" content-width="0.4em"/>&#160;&#160;&#160;&#160;&#160;&#160;<fo:inline font-weight="bold" >Professional Services</fo:inline> rendered or which should have been rendered by any <fo:inline font-weight="bold" >Insured</fo:inline> to or on behalf of 
				 	<!--  <xsl:if test="response/additionaldata_freeform_01/data/DisputeRenderedOnBehalfOf != '' ">
				 	 <xsl:value-of select="response/additionaldata_freeform_01/data/DisputeRenderedOnBehalfOf"/></xsl:if> -->
				 	 
				 	<fo:inline>  <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/DisputeRenderedOnBehalfOf != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/DisputeRenderedOnBehalfOf"></xsl:value-of>
						          </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose></fo:inline></fo:block>
					 <fo:block font-size="10px" text-indent="0.3cm">&#160;, or any <fo:inline font-weight="bold" >Claim</fo:inline> or cause of action brought by or on behalf of 
					
					 <fo:inline>
					 <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/ClaimRenderedOnBehalfOf != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/ClaimRenderedOnBehalfOf"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
					 </fo:inline>
					 
					 </fo:block>
					 
					
					 
				     <fo:block margin-top="2cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				
				    			       
	         	<fo:block margin-top="15cm"/>
	         	
	         	  
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F037 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>  
	         	
	         	
	         	
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	