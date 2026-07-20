<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="ALA04F009">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					 <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">PREDECESSOR FIRM PRIOR ACTS ENDORSEMENT</fo:block>
				     <fo:block margin-top="10mm"/>
				     <fo:block text-align="left" font-size="10px" >In consideration of: (check one box only)</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of $__________</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
				     
				     <fo:block margin-top="4mm"/>
				     <fo:block  font-size="10px"> it is agreed that Item 6. of the Declarations, <fo:inline font-weight="bold" >Prior Acts Date</fo:inline>, is amended to include the date shown in Column B below with respect to the <fo:inline font-weight="bold" >Predecessor Firm</fo:inline> shown in Column A below:</fo:block>
				 	 <fo:block margin-top="10mm"/>
				 	 
				 <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"> A </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"> B </fo:block></fo:table-cell>
					    		  </fo:table-row>					    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center"><fo:inline font-weight="bold"> Predecessor Firm  Name</fo:inline></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"> <fo:inline font-weight="bold">Prior Acts Date</fo:inline> </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block   font-size="10px"  text-align="left" >  </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center" >  </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="70pt">
					                    <fo:block   font-size="10px"  text-align="left" >
					                    <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/PredecessorFirmName_1 != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/PredecessorFirmName_1"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
									  
					                     </fo:block>
					                     </fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center" >
					                    
								     <xsl:choose>
						      <xsl:when test="response/additionaldata_freeform_01/data/PriorActsDate_1 != '' "> 
									                    <xsl:value-of select="response/additionaldata_freeform_01/data/PriorActsDate_1"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
								  </fo:block>
								  </fo:table-cell>
					    		  </fo:table-row>	
					    		  
					    		  
					    		    
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="70pt">
					                    <fo:block   font-size="10px"  text-align="left" >
					                    <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/PredecessorFirmName_2 != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/PredecessorFirmName_2"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
									  
					                     </fo:block>
					                     </fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center" >
					                    
								     <xsl:choose>
						      <xsl:when test="response/additionaldata_freeform_01/data/PriorActsDate_2 != '' "> 
									                    <xsl:value-of select="response/additionaldata_freeform_01/data/PriorActsDate_2"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
								  </fo:block>
								  </fo:table-cell>
					    		  </fo:table-row>	
					    		  
					    		  
					    		  			 <fo:table-row>
					                    <fo:table-cell padding-left="70pt">
					                    <fo:block   font-size="10px"  text-align="left" >
					                    <xsl:choose>
						         <xsl:when test="response/additionaldata_freeform_01/data/PredecessorFirmName_3 != '' ">
						           <xsl:value-of select="response/additionaldata_freeform_01/data/PredecessorFirmName_3"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
									  
					                     </fo:block>
					                     </fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center" >
					                    
								     <xsl:choose>
						      <xsl:when test="response/additionaldata_freeform_01/data/PriorActsDate_3 != '' "> 
									                    <xsl:value-of select="response/additionaldata_freeform_01/data/PriorActsDate_3"></xsl:value-of>
						         </xsl:when>
						         <xsl:otherwise>
						      <fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:block>  
						         </xsl:otherwise>
						       </xsl:choose>
								  </fo:block>
								  </fo:table-cell>
					    		  </fo:table-row>		    		  
					    		  
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				     
				     
				     <fo:block margin-top="5cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="8mm"/>
				     
	         	<fo:block margin-top="7cm"/>
	         	
				 
         
        
				 <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F009 (09/01/2021)
				  <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
				  </fo:block>
				 
      
     </xsl:template>
</xsl:stylesheet>




					
				    	