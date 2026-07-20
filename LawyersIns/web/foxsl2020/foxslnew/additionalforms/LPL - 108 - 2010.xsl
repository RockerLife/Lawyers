<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="LPL1082010">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					<!-- 
				    <fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1080910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block> -->
  					 
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
					              
					              
					              
					              			    		  
					    		  <!-- <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" ><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_2 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_2"></xsl:value-of></xsl:if></fo:block></fo:table-cell>
					              </fo:table-row>					    		  
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center" ><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_3 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_3"></xsl:value-of></xsl:if></fo:block></fo:table-cell>
					              </fo:table-row> -->					    		  
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
					
					 <!-- 
					<fo:block  font-size="10px" text-align="center">Person(s) or Entity (ies)</fo:block>
					<fo:block margin-top="15mm"/>
			 	
			 		<fo:block  font-size="12px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_1 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_1"></xsl:value-of></xsl:if></fo:block>
					<fo:block margin-top="5mm"/>
					<fo:block  font-size="12px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_2 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_2"></xsl:value-of></xsl:if></fo:block>
					<fo:block margin-top="5mm"/>
					<fo:block  font-size="12px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_3 != '' ">  <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_3"></xsl:value-of></xsl:if></fo:block>
					<fo:block margin-top="5mm"/>
				
				 <fo:block>
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"   text-align="center"> (A) </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"> (B) </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center"><fo:inline font-weight="bold" > Predecessor Firm</fo:inline> Name </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"> Prior Acts Date: </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"> </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"> </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center" margin-top="1cm"> __________________________  </fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center" margin-top="1cm"> __________________________ </fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				     -->
				     
				     <fo:block margin-top="5cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="9cm"/>
				     <!-- 
				     <fo:block>
				       <fo:table  text-align="center">
					   <fo:table-column column-width = "80mm"/>
				       <fo:table-column column-width = "80mm"/>
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/authorized_signature"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   font-size="10px"  text-align="center"><xsl:value-of select="response/freeform/data/date"/></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 <fo:table-row>
					    		  		<fo:table-cell padding-left="4pt" padding-top="4pt">
					               			<fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">________________________</fo:block>
					               		</fo:table-cell>
					             </fo:table-row>
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="center">Authorized Representative</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block  font-size="10px"  text-align="center">Date</fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block> -->			       
	         	
	         	  <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1">
	         	<fo:block  font-size="10px" color="grey" text-align="left">LPL - 108 (04/17)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	</xsl:if>
	         	  <xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F010 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	</xsl:if>
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	