<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="ALA04F010">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					
  					 
				     <fo:block margin-top="3mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">SPECIFIED ATTORNEY OR ENTITY EXCLUSION ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block  font-size="10px">In consideration of the premium charged, it is agreed that Section IV., Definition I.,<fo:inline font-weight="bold" >Insured,</fo:inline> is amended by addition of the following:</fo:block>
				 	 <fo:block margin-top="5mm"/>
				 	 <fo:block  font-size="10px" >However, <fo:inline font-weight="bold" >Insured</fo:inline> shall not include:</fo:block>
					<!--  <fo:block margin-top="10mm"/> -->
					<fo:block text-align="left" font-size="10px">(a) the following person or entity; </fo:block>
					
					<fo:block text-align="left" font-size="10px">(b) any entity that is operated, managed or owned by such person or entity; or </fo:block>
					
					<fo:block text-align="left" font-size="10px">(c) any affiliate, subsidiary or parent of such person or entity:</fo:block>
					<fo:block>
					 <fo:block margin-top="10mm"/>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "200mm" />
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-weight="bold" font-size="10px"   text-align="center"> Person or Entity </fo:block></fo:table-cell>
					              </fo:table-row>
					             	    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                    </fo:block></fo:table-cell>
					              </fo:table-row>				    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                    
					                     <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/PersonOrEntity_1 != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_1"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
					                    
					                
					                    
					                    </fo:block></fo:table-cell>
					              </fo:table-row>		
					              
					                <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                    
					                     <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/PersonOrEntity_2 != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_2"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
					                    
					                
					                    
					                    </fo:block></fo:table-cell>
					              </fo:table-row>
					              
					              
					              
					                <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                    
					                     <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/PersonOrEntity_3 != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_3"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
					                    
					                
					                    
					                    </fo:block></fo:table-cell>
					              </fo:table-row>
					              
					              
					              
					              			    		  
					    		  			    		  
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
					
					 
				     
				     <fo:block margin-top="5cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="9cm"/>
				     
	         	 
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F010 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	