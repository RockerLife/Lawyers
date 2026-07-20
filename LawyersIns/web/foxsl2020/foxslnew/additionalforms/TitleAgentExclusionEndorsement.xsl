<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="TitleAgentExclusionEndorsement">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
					
  					 
				     <fo:block margin-top="3mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="12px">TITLE AGENT EXCLUSION ENDORSEMENT</fo:block>
				     
				     <fo:block margin-top="10mm"/>
				     <fo:block space-after="3mm" font-size="10px">In consideration of the premium charged, it is agreed that:</fo:block>
				 	 
				 	
				 	<fo:block text-align="left" space-after="3mm"  font-size="10px">1.	Section IV., Definition Q., Professional Services, is amended by the deletion of paragraph 3.</fo:block>
					
					<fo:block text-align="left" space-after="3mm"  font-size="10px"><fo:block text-align="left" space-after="3mm"  font-size="10px">2.	Section V., Exclusion I., is deleted and replaced with the following:</fo:block>
	</fo:block>
	    		<fo:block>
                  <fo:list-block>
                    <fo:list-item>
                      <fo:list-item-label start-indent="4mm">
                        <fo:block>
                          <fo:inline font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;">1.</fo:inline>
                        </fo:block>
                      </fo:list-item-label>
                      <fo:list-item-body start-indent="10mm">
                        <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" text-align="justify">
                       	any services performed as a title agent, whether or not such services are incidental to those performed as a lawyer.
                         </fo:block>
                      </fo:list-item-body>
                      
                      </fo:list-item>
                      </fo:list-block>
                   </fo:block>

	
					<fo:block margin-top="1cm"/>
				     <fo:block text-align="left" font-size="10px">All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				     <fo:block margin-top="16.5cm"/>
				    	       
	         	
	         	<!--  <xsl:if test="response/policy_freeform_05/data/CompanyKey= 1"> 
	         	<fo:block  font-size="10px" color="grey" text-align="left">LPL - 108 (04/17)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	</xsl:if> -->
	         	  <xsl:if test="response/policy_freeform_05/data/CompanyKey= 3">
	         	<fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F035 (09/01/2021)
	         	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
	         	</fo:block>
	         	</xsl:if>
	         	
     </xsl:template>
</xsl:stylesheet>




					
				    	