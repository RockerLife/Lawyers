<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	 

	<xsl:template match="/">
		
		<fo:root>
		
		<fo:layout-master-set>
				<fo:simple-page-master master-name="sample" page-width="325mm"   
					page-height="270mm" margin-bottom="1.0in" margin-left="2.0in" margin-top="17mm"
					margin-right="2.0in">
					<fo:region-body margin-top="2.0mm"/>
					<fo:region-before extent="0.1in"/> 
					<fo:region-after /> 					
				</fo:simple-page-master>
			</fo:layout-master-set>	

			<fo:page-sequence master-reference="sample">			
				
				<fo:static-content flow-name="xsl-region-after">
				  <fo:block text-align="left">Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page"/></fo:block>
				</fo:static-content>
				
				<fo:flow flow-name="xsl-region-body">
					
					<fo:block text-align="Left" margin-top="1mm" font-size="18pt" font-family="Arial" >
						<fo:inline font-weight="bold">ENDORSEMENT PREMIUM CALCULATION FOR LAWYERS</fo:inline>
					</fo:block>
					
					<fo:block margin-top="8mm">
						<fo:table>
					         <fo:table-column column-width = "110mm" />
					         <fo:table-column column-width = "110mm" />
					       <fo:table-body>
					         <fo:table-row>
					         	<fo:table-cell ><fo:block font-weight="bold" font-size="15pt" font-family="Arial" >Firm Name : <xsl:value-of select="response/AccountName" /></fo:block></fo:table-cell>
						      	<fo:table-cell ><fo:block font-weight="bold" font-size="15pt" font-family="Arial" start-indent= "4cm">QuoteNumber : <xsl:value-of select="response/QuoteNumber" /></fo:block></fo:table-cell>
						      </fo:table-row>					      
					      </fo:table-body>
					  	</fo:table>
					 </fo:block> 
					 <fo:block margin-top="2mm"/>
									
					<fo:block text-align="center" margin-top="2mm" font-size="15pt" font-family="Arial" >
						<fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/>
					</fo:block>	
					 
					<fo:block margin-top="2mm" font-size="17pt" font-family="Arial" >
						<fo:inline font-weight="bold">Endorsement premium calculation has following steps:</fo:inline>
					</fo:block>					
				<!-- 	<fo:block font-size="15pt" font-family="Arial">
						after step seven where the resultant premium is rounded up to the nearest whole dollar for amounts 
					</fo:block>					
					<fo:block font-size="15pt" font-family="Arial">
						above 49 cents. 
					</fo:block>	 --> -->		
					
					<fo:block text-align="left" margin-top="5mm">
					 <xsl:choose>
        				 <xsl:when test="response/PremiumInfo/data/isPremiumChange = 'Y' ">
        				 	 <xsl:choose>
        						 <xsl:when test="response/PremiumInfo/data/EndorsementType = '9' or response/PremiumInfo/data/EndorsementType ='10' or response/PremiumInfo/data/EndorsementType ='12' or response/PremiumInfo/data/EndorsementType ='14' or response/PremiumInfo/data/EndorsementType = '15'">
										<fo:table >
								         <fo:table-column column-width = "500mm" />
								         <fo:table-column column-width = "" />
								         <fo:table-body>
								         		<fo:table-row>
									 				 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Endorsement Premium: </fo:inline>$<xsl:value-of select="response/PremiumInfo/data/EndorsementPremium" />  <fo:inline font-weight="bold"> &#160; and Endorsement Tax :</fo:inline>$<xsl:value-of select="response/PremiumInfo/data/EndorsementTax"/>  </fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                    <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Issued Policy Premium :</fo:inline> $<xsl:value-of select="response/PremiumInfo/data/PreviousPremium"/>  <fo:inline font-weight="bold"> &#160;and  Tax :</fo:inline> $<xsl:value-of select="response/PremiumInfo/data/PreviousTax"/>  . </fo:block></fo:table-cell>
									           </fo:table-row>
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           <fo:table-row>
									           
									             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Premium Difference = Endorsement Premium - Issued Policy Premium</fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/> </fo:block></fo:table-cell>
									           </fo:table-row>
									           <fo:table-row>
									            
									             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" >  <xsl:value-of select="response/PremiumInfo/data/PremiumDifference"/>  = <xsl:value-of select="response/PremiumInfo/data/EndorsementPremium"/> - <xsl:value-of select="response/PremiumInfo/data/PreviousPremium"/></fo:block></fo:table-cell>
									           </fo:table-row>
									             <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           <fo:table-row>
									            
									             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Tax Difference = Endorsement Tax - Issued Policy Tax</fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									            <!--  <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell> -->
									             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/TaxDifference"/> = <xsl:value-of select="response/PremiumInfo/data/EndorsementTax"/> - <xsl:value-of select="response/PremiumInfo/data/PreviousTax"/>  </fo:block></fo:table-cell>
									           </fo:table-row>
									         
									          <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Earned Premium = (Premium Difference / 365) * Days;</fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/EarnedPremium"/> = (<xsl:value-of select="response/PremiumInfo/data/PremiumDifference"/> / 365) * <xsl:value-of select="response/PremiumInfo/data/Days"/>  </fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Earned Tax = (Tax Difference / 365) * Days;</fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/EarnedTax"/> = (<xsl:value-of select="response/PremiumInfo/data/TaxDifference"/> / 365) * <xsl:value-of select="response/PremiumInfo/data/Days"/>  </fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Unearned Premium = Premium Difference - Earned Premium </fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/UnearnedPremium"/> = <xsl:value-of select="response/PremiumInfo/data/PremiumDifference"/>  - ( <xsl:value-of select="response/PremiumInfo/data/EarnedPremium"/> )</fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Unearned Tax = Tax Difference - Earned Tax </fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/UnearnedTax"/> = <xsl:value-of select="response/PremiumInfo/data/TaxDifference"/>  - ( <xsl:value-of select="response/PremiumInfo/data/EarnedTax"/> )  </fo:block></fo:table-cell>
									           </fo:table-row>
										</fo:table-body>
									</fo:table>
									 </xsl:when>
								 <xsl:when test="response/PremiumInfo/data/CancellationType ='2' or response/PremiumInfo/data/CancellationType ='3'">
					     			  <fo:table >
								         <fo:table-column column-width = "500mm" />
								         <fo:table-column column-width = "" />
								         <fo:table-body>
								         		
									           <fo:table-row>
									                    <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Annual Policy Premium :</fo:inline> $<xsl:value-of select="response/PremiumInfo/data/Premium"/>  <fo:inline font-weight="bold"> &#160;and  Tax :</fo:inline> $<xsl:value-of select="response/PremiumInfo/data/Tax"/>  . </fo:block></fo:table-cell>
									           </fo:table-row>
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									         
									          <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Earned Premium = (Premium / 365) * Days </fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/EarnedPremium"/> = (<xsl:value-of select="response/PremiumInfo/data/Premium"/> / 365) * <xsl:value-of select="response/PremiumInfo/data/Days"/>  </fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Earned Tax = (Tax / 365) * Days;</fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/EarnedTax"/> = (<xsl:value-of select="response/PremiumInfo/data/Tax"/> / 365) * <xsl:value-of select="response/PremiumInfo/data/Days"/>  </fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Unearned Premium = Premium - Earned Premium </fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/UnearnedPremium"/> = <xsl:value-of select="response/PremiumInfo/data/Premium"/>  - ( <xsl:value-of select="response/PremiumInfo/data/EarnedPremium"/> )</fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									            
									            <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Unearned Tax = Tax Difference - Earned Tax </fo:inline></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.1em" content-width="10em"/></fo:block></fo:table-cell>
									           </fo:table-row>
									           
									           <fo:table-row>
									                 <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" > <xsl:value-of select="response/PremiumInfo/data/UnearnedTax"/> = <xsl:value-of select="response/PremiumInfo/data/Tax"/>  - ( <xsl:value-of select="response/PremiumInfo/data/EarnedTax"/> )  </fo:block></fo:table-cell>
									           </fo:table-row>
										</fo:table-body>
									</fo:table>
					  </xsl:when>
         							<xsl:otherwise>
         							<fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/></fo:block>
          								<fo:block text-align="center" margin-top="2mm" font-size="17pt" font-family="Arial" >
											Other Endorsements
								</fo:block>		
			        			 </xsl:otherwise>
      					 </xsl:choose>
					 </xsl:when>
					 
         			<xsl:otherwise>
         			<fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/></fo:block>
          				<fo:block text-align="center" margin-top="2mm" font-size="17pt" font-family="Arial" >
							There is no premium change.
					</fo:block>		
        			 </xsl:otherwise>
      			 </xsl:choose>
					</fo:block>
					
					<fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/></fo:block>
					<fo:block id="last-page"/>
											
				</fo:flow>
			</fo:page-sequence>

		</fo:root>

	</xsl:template>

</xsl:stylesheet>
