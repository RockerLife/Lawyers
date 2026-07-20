<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="Policycoverletter4">
					<fo:block>				  	
						<xsl:call-template name="CommonHeader" />						           	
					</fo:block>
				    <!--<fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block >
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1040313" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				    <fo:block space-after=".1in" text-align-last="justify">
    <fo:leader leader-pattern="rule" />
   </fo:block>  -->
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >AGGREGATE DEDUCTIBLE FOR ALL CLAIMS</fo:block>
				     <fo:block text-align="center" font-weight="bold" font-size="10px"  >APPLIES TO DEFENSE EXPENSES</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				     <fo:block text-align="left" font-size="10px" >In consideration of: (check one box only)</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of $__________</fo:block>
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >
				     <fo:external-graphic src="../LawyersIns/image/check-btn6.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
				    
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >it is agreed that:</fo:block> 
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >1.  Item 4. of the Declarations, Deductible, is deleted and replaced with the following:</fo:block>
				     
				     <fo:block margin-top="6mm"/>
				     <fo:table border="2pt solid black">
						<fo:table-column column-width="20mm" />
						<fo:table-column column-width="150mm" />				
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left">Item 4.</fo:block>
								</fo:table-cell>
								<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block text-align="left" font-size="10px" ><fo:inline font-weight="bold" >Deductible:</fo:inline></fo:block>
								   
									<fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/> Aggregate Deductible for all <fo:inline font-weight="bold" >Claims</fo:inline></fo:block>
								    <fo:block text-align="left" font-size="10px" >This amount applies to <fo:inline font-weight="bold" >Defense Expenses</fo:inline>.</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
				     
				      <!-- 
				      <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >Item 4. Deductible</fo:block>
				      <fo:block text-align="left" text-indent="2cm" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/DeductibleAmount"/>, Aggregate Deductible for all <fo:inline font-weight="bold" >Claims</fo:inline></fo:block>
				      <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >This amount includes <fo:inline font-weight="bold" >Defense Expenses</fo:inline> unless this Section is amended by specific endorsement of this</fo:block>
				      <fo:block text-align="left" text-indent="1.5cm" font-size="10px" >policy.</fo:block>
				       -->
				      <fo:block margin-top="4mm"/>
				      <fo:block text-align="left" font-size="10px" >2. Section II. of the policy, Limits of Liability and Deductible, subsection B., Deductible, is deleted and replaced with the</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="0.4cm">following: </fo:block>
				      <fo:block margin-top="5mm"/>
				      <fo:block text-align="left" font-size="10px" text-indent="0.5cm" font-weight="bold" >B. Deductible</fo:block>
				       <fo:block margin-top="2mm"/>
				        <fo:block text-align="left" font-size="10px" start-indent="1cm" >The <fo:inline font-weight="bold" >Insurer</fo:inline> shall only be liable for amounts payable under this policy for <fo:inline font-weight="bold" >Damages</fo:inline> or <fo:inline font-weight="bold" >Defense Expenses</fo:inline> which are in excess of the Deductible amount shown in Item 4. of the Declarations. This Deductible amount shall:   
				        </fo:block>
				        <fo:block text-align="left" font-size="10px" start-indent="1cm" >1.&#160;&#160; be borne by the <fo:inline font-weight="bold" >Insured</fo:inline>;</fo:block>
				        
				        <fo:block text-align="left" font-size="10px" start-indent="1cm" >2. &#160;&#160;remain uninsured; and</fo:block>
				       <fo:block text-align="left" font-size="10px" start-indent="1cm" >3.&#160;&#160; be the maximum amount borne by the <fo:inline font-weight="bold" >Insured</fo:inline> for all <fo:inline font-weight="bold" >Claims </fo:inline>under this policy.</fo:block>   
				        <fo:block text-align="left" font-size="10px" start-indent="1cm" >If the <fo:inline font-weight="bold" >Insured</fo:inline> has paid this amount, no further Deductibles shall apply to <fo:inline font-weight="bold" >Claims.</fo:inline>      
				        The Deductible amount applies to the payment of <fo:inline font-weight="bold" >Damages</fo:inline>
				          and <fo:inline font-weight="bold" >Defense Expenses</fo:inline>.  If the <fo:inline font-weight="bold" >Insurer</fo:inline> 
				          advances any amounts within the Deductible, the <fo:inline font-weight="bold" >Named Insured</fo:inline> shall reimburse the 
				          <fo:inline font-weight="bold" >Insurer</fo:inline> within 30 days of the <fo:inline font-weight="bold" >Insurer's</fo:inline>
				           request to do so.  In the event of <fo:inline font-weight="bold" >Related Claims</fo:inline>, a single Deductible amount will apply.
				           </fo:block> 
				       
				         
				         
				        <fo:block margin-top="10mm"/>
				        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				        <!-- 
				        <fo:block margin-top="15mm"/>
				        <fo:block>
				        <fo:table  text-align="center">
					    <fo:table-column column-width = "80mm" />
				        <fo:table-column column-width = "80mm" />
				       
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
				<fo:block margin-top="4mm"/>
				<fo:block text-align="left" font-size="10px" >unaltered.</fo:block> -->
				        
		       <fo:block margin-top="5.5cm"/>
		       <fo:block>
				      <fo:table>
				      <fo:table-column column-width="70mm"/>
				      <fo:table-column column-width="50mm"/>
				      <fo:table-column column-width="70mm"/>
				      <fo:table-body>
				      <fo:table-row>
				      <fo:table-cell>
				      <fo:block font-size="9px" color="grey">
				      ______________________________________
				      </fo:block>
				      <fo:block font-size="9px" color="grey" margin-left="1.5cm">
				      Authorized Representative
				      </fo:block>
				      </fo:table-cell>
				      <fo:table-cell>
				      <fo:block>
				      &#160;&#160;&#160;&#160;
				      </fo:block>
				      </fo:table-cell>
				      <fo:table-cell>
				      <fo:block font-size="9px" color="grey">
				      ______________________________________
				      </fo:block>
				      <fo:block font-size="9px" color="grey" margin-left="3.4cm">
				      Date
				      </fo:block>
				      </fo:table-cell>
				      </fo:table-row>
				      </fo:table-body>
				      </fo:table>
				      </fo:block>  
				      <fo:block margin-top="4mm"/>
				        
				      
		                 
		                  <fo:block  font-size="10px" color="grey" text-align="left">ISMIE ALA-04-F006 (09/01/2021)
        	 <fo:block margin-top="-0.5cm" font-size="10px" color="grey" text-align="right">Page 1 of 1</fo:block>
        	 </fo:block>
			     
     </xsl:template>
</xsl:stylesheet>




					
				    	