<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	
	<xsl:template match="/" name="Policycoverletter2">
					<fo:block text-align="center" font-weight="bold" font-size="11px" >
						THIS ENDORSEMENT CHANGES THE POLICY. PLEASE READ IT CAREFULLY.
					</fo:block>
					
					<fo:block margin-top="3mm">
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="120mm" />
							<fo:table-column column-width="70mm" />				
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Named Insured: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Endorsement Number: LPL-102 (04/17)</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>	
						</fo:table>
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="60mm" />
							<fo:table-column column-width="60mm" />
							<fo:table-column column-width="70mm" />				
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Policy Number: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Policy Period:</fo:block>
										<fo:block font-size="10px" padding-left="8pt"><xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /> to <xsl:value-of select="response/policycoverage_freeform_01/data/PolicyExpirationDate" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="2pt solid black" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policycoverage_freeform_01/data/TransactionEffectiveDate" /></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
								
						<fo:table border="2pt solid black" text-align="center">
							<fo:table-column column-width="190mm" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="2pt solid black"	padding-bottom="4mm" padding-left="4pt">
										<fo:block font-size="10px" text-align="left">Insurer: United States Fire Insurance Company</fo:block>
									</fo:table-cell>					
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block font-size="7px" padding-left="20pt" text-indent="0.5cm">Insert the policy number. The remainder of the information is to be completed only when this endorsement is issued subsequent to the preparation of the policy.</fo:block>
					<fo:block margin-top="2mm">
					</fo:block>
				   <!-- <fo:block text-align="center" font-size="12px"  >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
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
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1020910" /></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block> 
					
				    <fo:block space-after=".1in" text-align-last="justify">
				    	<fo:leader leader-pattern="rule" />
				   	</fo:block>-->
				   	
				     <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" font-size="12px" >DEFENSE EXPENSES IN ADDITION TO THE LIMIT OF LIABILITY</fo:block>
				     <fo:block margin-top="8mm"/>
				    
				    <!--  <fo:block text-align="left"  font-size="10px" >This endorsement amends the Limit of Liability so that <fo:inline font-weight="bold" >Defense Expenses</fo:inline> are paid in addition to and equal to the Limit of Liability available to pay <fo:inline font-weight="bold" >Damages</fo:inline>.  Thereafter, <fo:inline font-weight="bold" >Defense Expenses</fo:inline> are part of and reduce the Limit of Liability to pay <fo:inline font-weight="bold" >Damages</fo:inline>.</fo:block>
				     <fo:block margin-top="4mm"/> -->
				     
				     <fo:block text-align="left" font-size="10px" >In consideration of: (check one box only)</fo:block>
				     <fo:block margin-top="4mm"/>
				     
				     <xsl:choose>
				     	<xsl:when test="response/PremiumCharged= 'Y'">
                    	 <fo:block start-indent="7mm">
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of <xsl:value-of select="response/PremiumValue"/></fo:block>
					     <fo:block margin-top="4mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
					     </fo:block>
                      	</xsl:when>
                      	<xsl:when test="response/PremiumCharged= 'N'">
                         <fo:block start-indent="7mm">					     
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  a return premium of <xsl:value-of select="response/PremiumValue"/></fo:block>
					     <fo:block margin-top="4mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
					     </fo:block>
                      </xsl:when>
                      <xsl:when test="response/PremiumCharged= 'S'">
                        <fo:block start-indent="7mm">
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of $_____</fo:block>
					     <fo:block margin-top="4mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
					     </fo:block>
                      </xsl:when>     
                      	<xsl:otherwise>
                         <fo:block start-indent="7mm">
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  an additional premium of $_____</fo:block>
					     <fo:block margin-top="4mm"/>
					     <fo:block text-align="left" font-size="10px" >
					     <fo:external-graphic src="../LawyersIns/image/check-btn4.png" content-height="1em" content-width="1em"/>  the premium charged</fo:block>
					     </fo:block>
                      </xsl:otherwise>
                     </xsl:choose>
                     
				     
				    
				     <fo:block margin-top="4mm"/>
				     <fo:block text-align="left" font-size="10px" >it is agreed that:</fo:block>
				     <fo:block margin-top="4mm"/>
				     
				    <!-- <fo:block text-align="left" font-size="10px" >1. The last sentence of the Preamble appearing above Item 1 of the Declarations is deleted.</fo:block> -->
				     
				     <fo:block text-align="left" font-size="10px" >1. Item 3. of the Declarations, Limit of Liability,  is deleted and replaced with</fo:block>
				     
				     
				        <fo:block text-align="left" font-size="10px" >&#160;&#160;&#160;&#160;the following:</fo:block>
                     <fo:block margin-top="5mm"/>
                     
                     <fo:table padding-left="5pt" border="1pt black">
						<fo:table-column column-width="20mm" />
						<fo:table-column column-width="150mm" />				
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border="1pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block font-size="10px" text-align="left">Item3.</fo:block>
								</fo:table-cell>
								<fo:table-cell border="1pt solid black"	padding-bottom="4mm" padding-left="4pt">
									<fo:block text-align="left" font-size="10px" ><fo:inline font-weight="bold" >Limits of Liability:</fo:inline></fo:block>
								   
									<fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Damages</fo:inline>, each <fo:inline font-weight="bold">Claim</fo:inline></fo:block>
								    <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/AggregateLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, each <fo:inline font-weight="bold">Claim</fo:inline></fo:block>
								    <fo:block margin-top="4mm"/>
								    <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Damages Aggregate</fo:inline></fo:block>
								    <fo:block text-align="left" font-size="10px" ><xsl:value-of select="response/policycoverage_freeform_01/data/OccuranceLimitText"/>     &#160;&#160;&#160;&#160;&#160;&#160;      <fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline></fo:block>
				      		</fo:table-cell>
							</fo:table-row>
						</fo:table-body>	
					</fo:table>
					
				        <fo:block margin-top="4mm"/>
				        <fo:block text-align="left" font-size="10px" >2. Section II. of the policy, Limits of Liability and Deductible,  subsection A., Limits of Liability, is deleted and replaced with </fo:block> 
				        <fo:block text-align="left" font-size="10px" >&#160;&#160;&#160;&#160;the following:</fo:block>
				        <fo:block margin-top="4mm"/>
				        
				      <fo:block text-align="left" font-size="10px" text-indent="0.4cm" font-weight="bold" >A. Limits of Liability</fo:block>
				       <fo:block margin-top="2mm"/>
				      
				        
				            <fo:block text-align="justify" font-size="10px" start-indent="1cm">1.	 Subject to 2. that follows, the <fo:inline font-weight="bold" >Insurer's</fo:inline> Limits of Liability for <fo:inline font-weight="bold" >Damages</fo:inline> and <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, for each</fo:block>
				     		<fo:block text-align="justify" font-size="10px" start-indent="1.4cm"> <fo:inline font-weight="bold" >Claim</fo:inline> first made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> during the <fo:inline font-weight="bold" >Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amounts shown in Item 3. of the Declarations as applicable to <fo:inline font-weight="bold" >"Damages</fo:inline>, each<fo:inline font-weight="bold" > Claim"</fo:inline> and <fo:inline font-weight="bold" >"Defense Expenses</fo:inline>, each <fo:inline font-weight="bold" >Claim"</fo:inline>, respectively.</fo:block>
				        
				           <fo:block text-align="justify" font-size="10px" start-indent="1cm" >2.	Subject to 1. above, the <fo:inline font-weight="bold" >Insurer"s</fo:inline>  Limits of Liability for <fo:inline font-weight="bold" >Damages</fo:inline> and <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, for all <fo:inline font-weight="bold" >Claims</fo:inline>  first </fo:block>
				     
				          <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> during the <fo:inline font-weight="bold" >Policy Period</fo:inline>, including any applicable Extended Reporting Period, shall not exceed the amounts shown in Item 3. of the Declarations as applicable to <fo:inline font-weight="bold" >"Damages Aggregate"</fo:inline> and <fo:inline font-weight="bold" >"Defense Expenses Aggregate"</fo:inline>, respectively.</fo:block>
				        
				           <fo:block text-align="justify" font-size="10px" start-indent="1cm" >3.	The Limits of Liability shall apply excess of the Deductible amount.</fo:block>
				     
				        
				           <fo:block text-align="justify" font-size="10px" start-indent="1cm" >4.	The Limits of Liability available for <fo:inline font-weight="bold" >Claims</fo:inline> first made against the <fo:inline font-weight="bold" >Insured</fo:inline> and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> during </fo:block>
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">any applicable Extended Reporting Period, are part of, and not in addition to the Limits of Liability shown in </fo:block>
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">Item 3. of, the Declarations. Applicable Extended Reporting Periods shall not provide any new, additional or renewed Limit of Liability.</fo:block>
				     
				           <fo:block text-align="justify" font-size="10px" start-indent="1cm" >5.	If the <fo:inline font-weight="bold" >Insurer</fo:inline> has exhausted the <fo:inline font-weight="bold" >"Damages</fo:inline>, each <fo:inline font-weight="bold" >Claim"</fo:inline> Limit of Liability by payment of <fo:inline font-weight="bold" >Damages</fo:inline> or by </fo:block>
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">tender of the remaining <fo:inline font-weight="bold" >"Damages</fo:inline>, each <fo:inline font-weight="bold" >Claim"</fo:inline> Limit of Liability into court, it shall have no further duties to the <fo:inline font-weight="bold" >Insured</fo:inline> under this policy with respect to the same <fo:inline font-weight="bold" >Claim</fo:inline>.</fo:block>
				     
				           <fo:block text-align="justify" font-size="10px" start-indent="1cm" >6.	If the <fo:inline font-weight="bold" >Insurer</fo:inline> has exhausted the <fo:inline font-weight="bold" >"Damages Aggregate"</fo:inline> Limit of Liability by payment of <fo:inline font-weight="bold" >Damages</fo:inline> or by </fo:block>
				           <fo:block text-align="justify" font-size="10px" start-indent="1.4cm">tender of the remaining <fo:inline font-weight="bold" >"Damages Aggregate"</fo:inline> Limit of Liability into court, it shall have no further duties to the <fo:inline font-weight="bold" >Insured</fo:inline> under this policy with respect to all <fo:inline font-weight="bold" >Claims</fo:inline>.</fo:block>
				     
	         
	         <fo:block space-after="4mm"/>
				     
				  <!--    <fo:block>
				       <fo:table  text-align="center">
					    <fo:table-column column-width = "60mm" />
				        <fo:table-column column-width = "120mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block    text-align="center"><fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="2em" content-width="5em"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   text-align="center"><xsl:value-of select="response/general_freeform_01/data/CurrentDate"/></fo:block></fo:table-cell>
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
				</fo:block>	 -->
				
				  			       
	         	<fo:block margin-top="30mm"/>
	         	
	       	 <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="gray">
	       	 
	       	  <fo:inline text-align="left" width="50%">LPL-102 (04/17)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:inline><fo:inline text-align="right" width="50%">Page 1 of 2</fo:inline>  
	       	 
             <!--  <fo:table font-size="9px" font-family="Arial" color="gray">
              <fo:table-column column-width="50%"/>
              <fo:table-column column-width="50%"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                  <fo:block text-align="left">
                       LPL - 102 (04/17)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="right">
                      Page 1 of 2
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
              </fo:table-body>
            </fo:table>  -->
          </fo:block>  
 <!--  <fo:block font-size="10px" color="grey" text-align="right">page 1 of 2</fo:block>
 -->		  <fo:block break-after="page"/>
				     
				      <fo:block margin-top="4mm"/>
				     
				        <fo:block text-align="justify" font-size="10px" start-indent="1cm" >In the event the amount shown in this endorsement for <fo:inline font-weight="bold" >Defense Expenses</fo:inline>, each <fo:inline font-weight="bold" >Claim</fo:inline> is exhausted, then 
						<fo:inline font-weight="bold" >Defense Expenses</fo:inline> incurred thereafter, shall be part of and reduce the Limit of Liability as shown for <fo:inline font-weight="bold" >Damages</fo:inline>, 
						each <fo:inline font-weight="bold" >Claim</fo:inline>. In the event the amount shown above for <fo:inline font-weight="bold" >Defense Expenses Aggregate</fo:inline> is exhausted, then <fo:inline font-weight="bold" >Defense Expenses</fo:inline> incurred thereafter, shall be part of and reduce the Limit of Liability as shown for <fo:inline font-weight="bold" >Damages Aggregate</fo:inline>.
				       </fo:block>
				      <fo:block margin-top="10mm"/>
				        
				        <fo:block text-align="left" font-size="10px">3.	Section II. of the policy, Limits of Liability and Deductible, subsection C., Multiple <fo:inline font-weight="bold" >Insureds, Claims </fo:inline>and Claimants, is </fo:block> 
				        <fo:block text-align="left" font-size="10px" start-indent="0.4cm" >deleted and replaced with the following;</fo:block>
				        <fo:block margin-top="4mm"/>
				        
				        
				        <fo:block text-align="left" font-size="10px" text-indent="0.4cm" ><fo:inline font-weight="bold" >C. Multiple Insureds, Claims and Claimants</fo:inline></fo:block>
				       <fo:block margin-top="4mm"/>
				      
				        <fo:block text-align="justify" font-size="10px" start-indent="1cm">Regardless of the number of <fo:inline font-weight="bold" >Claims , Insureds</fo:inline> or  claimants, the  Limit of Liability shown in item 3. of the </fo:block>
				     		
				      <fo:block text-align="justify" font-size="10px" start-indent="1cm">Declarations as applicable to "each <fo:inline font-weight="bold" >Claim"</fo:inline>, to <fo:inline font-weight="bold" >"Damages Aggregate"</fo:inline> and to <fo:inline font-weight="bold" >"Defense Expenses Aggregate"</fo:inline> shall be subject to paragraph A. of this Section II. If <fo:inline font-weight="bold" >Related Claims</fo:inline> are subsequently made against the <fo:inline font-weight="bold" >Insured</fo:inline> and reported to the <fo:inline font-weight="bold" >Insurer,</fo:inline> all such Related <fo:inline font-weight="bold" >Claims</fo:inline>, whenever made, shall be considered a single <fo:inline font-weight="bold" >Claim</fo:inline> first made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline> within the policy period in which the earliest of the <fo:inline font-weight="bold" >Related Claims</fo:inline> was first made and reported to the <fo:inline font-weight="bold" >Insurer</fo:inline>.</fo:block>
				        
				        <fo:block margin-top="4mm"/>
				        
				        
				         <fo:block text-align="left" font-size="10px" >4.	Section IV. of the policy, Definitions, is amended as follows:</fo:block> 
				        <fo:block margin-top="4mm"/>
				       
				       <fo:block text-align="left" font-size="10px" text-indent="1cm" >a.	Definition M., <fo:inline font-weight="bold" >Policy Aggregate</fo:inline>, is deleted.</fo:block>
				       <fo:block margin-top="4mm"/>
				     
				        
				        <fo:block text-align="left" font-size="10px" text-indent="1cm" >b.	The following definitions are added:</fo:block>
				       <fo:block margin-top="4mm"/>
				        
				        <fo:block text-align="justify" font-size="10px" start-indent="1.5cm"><fo:inline font-weight="bold" >• &#160;&#160;&#160;Damages Aggregate</fo:inline> means the applicable amount shown in Item 3. of, the Declarations which represents </fo:block>
				        <fo:block text-align="justify" font-size="10px" start-indent="2.1cm">the maximum amount of the <fo:inline font-weight="bold" >Insurer's</fo:inline> liability for all <fo:inline font-weight="bold" >Damages</fo:inline> for all <fo:inline font-weight="bold" >Claims</fo:inline> under this policy, inclusive of any applicable Extended Reporting Period.</fo:block>
				       <fo:block margin-top="4mm"/>
				        <fo:block text-align="justify" font-size="10px" start-indent="1.5cm"><fo:inline font-weight="bold" >• &#160;&#160;&#160;Defense Expenses Aggregate</fo:inline> means the applicable amount shown in Item 3. of the Declarations which </fo:block>
				        <fo:block text-align="justify" font-size="10px" start-indent="2.1cm">represents the maximum amount of the <fo:inline font-weight="bold" >Insurer's</fo:inline> liability for all <fo:inline font-weight="bold" >Defense Expenses</fo:inline> for all <fo:inline font-weight="bold" >Claims</fo:inline> under this policy, inclusive of any applicable Extended Reporting Period.</fo:block>
				      
				      
				        
				        
				        
				       
				         
				       <fo:block margin-top="8mm"/>
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
				 <fo:block text-align="left" font-size="10px" >unaltered.</fo:block>
				 -->    
				 
				  <fo:block space-after="8cm"/>
				 
				<!--  <fo:block>
				       <fo:table  text-align="center">
					    <fo:table-column column-width = "60mm" />
				        <fo:table-column column-width = "120mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block    text-align="center"><fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="2em" content-width="5em"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   text-align="center"><xsl:value-of select="response/general_freeform_01/data/CurrentDate"/></fo:block></fo:table-cell>
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
				</fo:block>	 -->
				
				
				
				         <fo:block>
			       	<fo:table  text-align="center">
				   	<fo:table-column column-width = "60mm" />
			       	<fo:table-column column-width = "120mm" />				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block    text-align="center"><fo:external-graphic src="../LawyersIns/image/kyle.png" content-height="2em" content-width="5em"/></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block   text-align="center"><xsl:value-of select="response/general_freeform_01/data/CurrentDate"/></fo:block></fo:table-cell>
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
	         	<fo:block space-after="18mm"/>	
	         	
	       	 <fo:block font-size="10px" font-family="Arial, 'Helvetica Neue', Helvetica, sans-serif;" color="gray">
	       	 
	       	  <fo:inline text-align="left" width="50%">LPL-102 (04/17)&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;
		        &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</fo:inline><fo:inline text-align="right" width="50%">Page 2 of 2</fo:inline> 
	       	 
             <!--  <fo:table font-size="9px" font-family="Arial" color="gray">
              <fo:table-column column-width="50%"/>
              <fo:table-column column-width="50%"/>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell>
                  <fo:block text-align="left">
                       LPL - 102 (04/17)
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="right">
                      Page 1 of 2
                    </fo:block>
                  </fo:table-cell>

                </fo:table-row>
              </fo:table-body>
            </fo:table>  -->
          </fo:block>  
            	  <!-- <fo:block font-size="10px" color="grey" text-align="right">page 2 of 2</fo:block> -->	       
	         	<fo:block  font-size="10px" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1020417"/></fo:block>
				 
     </xsl:template>
</xsl:stylesheet>




					
				    	