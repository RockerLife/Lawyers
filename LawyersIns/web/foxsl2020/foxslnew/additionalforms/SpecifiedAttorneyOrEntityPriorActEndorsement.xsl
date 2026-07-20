<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="SpecifiedAttorneyOrEntityPriorActEndorsement">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					
				     <fo:block margin-top="3mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">SPECIFIED ATTORNEY OR ENTITY PRIOR ACTS DATE AMENDATORY ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block  font-size="10px">In consideration of the premium charged, it is agreed that Item 6. of the Declarations, <fo:inline font-weight="bold" >Prior Acts Date</fo:inline>, is amended to include the date shown in Column B below with respect to the attorney or entity shown in Column A below:</fo:block>
				 	 
					<fo:block>
					 <fo:block margin-top="10mm"/>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "100mm" />
					     <fo:table-column column-width = "100mm" />
				        <fo:table-body>
				         <fo:table-row>
					                    <fo:table-cell padding-left="4pt">
					                    <fo:block   font-size="10px"   text-align="center"> A </fo:block>
					                    </fo:table-cell>
					               <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"   text-align="center"> B</fo:block></fo:table-cell>
					              
					              </fo:table-row>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt">
					                    <fo:block text-decoration="underline" font-size="10px"   text-align="center"> Attorney or Entity </fo:block>
					                    </fo:table-cell>
					               <fo:table-cell padding-left="4pt"><fo:block  text-decoration="underline" font-weight="bold" font-size="10px"   text-align="center"> Prior Acts Date</fo:block></fo:table-cell>
					              
					              </fo:table-row>
					             	    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                   &#160; </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" >
					                    &#160;</fo:block></fo:table-cell>
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
				     <fo:block margin-top="10cm"/>
				    	       
	         	
	         	 
	         	  <xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F013 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	</xsl:if>
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	