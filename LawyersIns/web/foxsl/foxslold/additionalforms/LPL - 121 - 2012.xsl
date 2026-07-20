<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="LPL1212012">
		
		    	
				    <fo:block text-align="center" font-size="12px">THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1210312" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    					<fo:leader leader-pattern="rule"/>
  					 </fo:block>
				     <fo:block margin-top="2mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">PREVIOUS FIRM ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				    <fo:block  font-size="10px">In consideration of the premium charged it is agreed that the coverage afforded by this policy shall apply to a <fo:inline font-weight="bold" font-style="italic">Claim</fo:inline> first made and reported to the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> during the <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> against:</fo:block>
				 	 <fo:block margin-top="5mm"/>
				 	<!--  <fo:block  font-size="10px" text-align="center" font-weight="bold">(Attorney)</fo:block> -->
				 	 
				 	 <!-- <fo:block margin-top="4mm"/>
				 	 <fo:block  font-size="10px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_1 != ''"> <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_1"></xsl:value-of> </xsl:if> </fo:block>
				 	  <fo:block margin-top="2mm"/>
				 	 <fo:block  font-size="10px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_2 != ''"> <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_2"></xsl:value-of> </xsl:if> </fo:block>
				 	  <fo:block margin-top="2mm"/>
				 	 <fo:block  font-size="10px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PersonOrEntity_3 != ''"> <xsl:value-of select="response/additionaldata_freeform_01/data/PersonOrEntity_3"></xsl:value-of> </xsl:if> </fo:block>
				 	  -->
				 	 <fo:block margin-top="4mm"/>
				 	 <fo:block  font-size="10px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PreviousAttorneyName_1 != ''"> <xsl:value-of select="response/additionaldata_freeform_01/data/PreviousAttorneyName_1"></xsl:value-of> </xsl:if> </fo:block>
				 	  <fo:block margin-top="2mm"/>
				 	 <fo:block  font-size="10px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PreviousAttorneyName_2 != ''"> <xsl:value-of select="response/additionaldata_freeform_01/data/PreviousAttorneyName_2"></xsl:value-of> </xsl:if> </fo:block>
				 	  <fo:block margin-top="2mm"/>
				 	 <fo:block  font-size="10px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PreviousAttorneyName_3 != ''"> <xsl:value-of select="response/additionaldata_freeform_01/data/PreviousAttorneyName_3"></xsl:value-of> </xsl:if> </fo:block>
				 	  
				 	 
				 	 
				 	 
					<fo:block margin-top="5mm"/>
					<fo:block  font-size="10px">as respects Professional Services provided on behalf of:   </fo:block>
				 	 <fo:block margin-top="5mm"/>
				 	 <!-- <fo:block  font-size="10px" text-align="center" font-weight="bold">(Previous Firm Name)</fo:block>-->
						 <fo:block margin-top="2mm"/>
				 	 <fo:block  font-size="10px" text-align="center" font-weight="bold"><xsl:if test="response/additionaldata_freeform_01/data/PreviousFirmName != ''"> <xsl:value-of select="response/additionaldata_freeform_01/data/PreviousFirmName"></xsl:value-of> </xsl:if> </fo:block>
				 	
				 	<!--  
					<fo:block margin-top="5mm"/>
					<fo:block  font-size="10px">(Optional language: and performed between _____________________ and [Date of Merger] and not otherwise excluded under the agreements, conditions and exclusions of this policy.)</fo:block>
						 -->
					
				 <!-- 
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
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center"><fo:inline font-weight="bold" font-style="italic"> Predecessor Firm</fo:inline> Name </fo:block></fo:table-cell>
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
				     <fo:block text-align="left" start-indent="4cm" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="8mm"/>
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
				</fo:block>				       
	         	<fo:block margin-top="1mm"/>
	         	<fo:block  font-size="10px" text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1210312"/></fo:block>
				 
     </xsl:template>
</xsl:stylesheet>




					
				    	