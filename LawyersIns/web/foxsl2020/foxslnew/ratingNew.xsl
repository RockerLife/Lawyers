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
					
					<fo:block text-align="center" margin-top="1mm" font-size="15pt" font-family="Arial" >
						<fo:inline font-weight="bold">RATING OF LAWYERS PROFESSIONAL LIABILITY</fo:inline>
					</fo:block>
					
					<fo:block margin-top="8mm">
						<fo:table>
					         <fo:table-column column-width = "110mm" />
					         <fo:table-column column-width = "110mm" />
					       <fo:table-body>
					         <fo:table-row>
					         	<fo:table-cell ><fo:block font-weight="bold" font-size="15pt" font-family="Arial" >Firm Name : <xsl:value-of select="response/RatingData/data/AccountName" /></fo:block></fo:table-cell>
						      	<fo:table-cell ><fo:block font-weight="bold" font-size="15pt" font-family="Arial" start-indent= "4cm">QuoteNumber : <xsl:value-of select="response/RatingData/data/QuoteNumber" /></fo:block></fo:table-cell>
						      </fo:table-row>					      
					      </fo:table-body>
					  	</fo:table>
					 </fo:block> 
					 <fo:block margin-top="4mm"/>
					 <!-- 					
					<fo:block text-align="center" margin-top="2mm" font-size="15pt" font-family="Arial" >
						<fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/>
					</fo:block>	
					 --> 
					<fo:block margin-top="2mm" font-size="15pt" font-family="Arial" >
						Rating of an applicant for coverage is a seven step process, as follows herein. Rounding is performed 
					</fo:block>					
					<fo:block font-size="15pt" font-family="Arial">
						after step seven where the resultant premium is rounded up to the nearest whole dollar for amounts 
					</fo:block>					
					<fo:block font-size="15pt" font-family="Arial">
						above 49 cents. 
					</fo:block>				
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 1:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Base Rate and Interim Base Premium</fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" >The absolute Base Rate is $<xsl:value-of select="response/PremiumInfo/data/BaseRate" /> per lawyer applicant . </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" >(The number of lawyers to be insured will be stated on the application).</fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Interim Base Premium = (Absolute Base Rate * FullTimeEquivalent * StateFactor ) </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/InterimBaseRate" /></fo:inline>= (<xsl:value-of select="response/PremiumInfo/data/BaseRate" /> * <xsl:value-of select="response/PremiumInfo/data/FullTimeEquivalent" /> * <xsl:value-of select="response/PremiumInfo/data/StateFactor" /> )</fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 2:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Interim Modification Factor </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Interim Modification Factor </fo:inline>= Size_Factor * AOP_Factor * CM_Factor * CountyFactor</fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/InterimModificationFactor" /></fo:inline>= <xsl:value-of select="response/PremiumInfo/data/SizeFactor" /> * <xsl:value-of select="response/PremiumInfo/data/AOPFactor" /> * <xsl:value-of select="response/PremiumInfo/data/ClaimAgeFactor" /> * <xsl:value-of select="response/PremiumInfo/data/CountyFactor" /></fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 3:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Calculate the Interim Modified Factor </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Interim Modified Factor </fo:inline>=   ERP_Factor * County_Factor </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/ModificationFactorERPCOUNTY" /></fo:inline>= <xsl:value-of select="response/PremiumInfo/data/ERPFactor" /> * <xsl:value-of select="response/PremiumInfo/data/CountyFactor" /></fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					
					
					
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 4:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Calculate the Interim Modified Base Limit Premium </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Interim Modified Base Premium </fo:inline>= Round(Interim Base Premium * Interim  Modification Factor * ModificationFactor) till 2 decimal points. </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/BasicLimitPremium" /></fo:inline>= <xsl:value-of select="response/PremiumInfo/data/InterimBaseRate" /> * <xsl:value-of select="response/PremiumInfo/data/InterimModificationFactor" /> * <xsl:value-of select="response/PremiumInfo/data/ModificationFactorERPCOUNTY" /></fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 5:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Calculate Premium Without Modifiers </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Interim Modified Base Premium(Without Modifiers) </fo:inline>= TLF(Increased Limit Factor/Deductible Factor) * First dollar Defense Option * interim_modified_base_premium</fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/PolicyPremiumWithoutSM" /></fo:inline>= <xsl:value-of select="response/PremiumInfo/data/TLFFactor" /> * <xsl:value-of select="response/PremiumInfo/data/FirstDollarFactor" /> * <xsl:value-of select="response/PremiumInfo/data/BasicLimitPremium" /></fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<!--  
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 5:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Interim Modified Base Premium </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Interim Modified Base Premium </fo:inline>= Base Limit Premium * Modification Factor </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/BaseLimitPremiumAfterDeductibles" /></fo:inline>= <xsl:value-of select="response/PremiumInfo/data/ModifiedBaseLimitPremium" /> * <xsl:value-of select="response/PremiumInfo/data/Modification_Factor" /> </fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					-->
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 6:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Determine Schedule Rating Modifiers </fo:inline>(Manual Capture of fields)</fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">(a) Schedule Rating Modifier 1 (value between -25% to 25%) </fo:inline>= 
						             	<xsl:if test="response/PremiumInfo/data/SchduleRatingModifier1 != ''">
						             		<xsl:value-of select="response/PremiumInfo/data/SchduleRatingModifier1" />
						             	</xsl:if>
						             	<xsl:if test="response/PremiumInfo/data/SchduleRatingModifier1 = ''">
						             		0
						             	</xsl:if>
						             	</fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">(b) Schedule Rating Modifier 2 (value between -25% to 25%) </fo:inline>= 
									 	<xsl:if test="response/PremiumInfo/data/SchduleRatingModifier2 != ''">
						             		<xsl:value-of select="response/PremiumInfo/data/SchduleRatingModifier2" />
						             	</xsl:if>
						             	<xsl:if test="response/PremiumInfo/data/SchduleRatingModifier2 = ''">
						             		0
						             	</xsl:if>
									 </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">(c) Schedule Rating Modifier 3 (value between -25% to 25%) </fo:inline>= 
						             	<xsl:if test="response/PremiumInfo/data/SchduleRatingModifier3 != ''">
						             		<xsl:value-of select="response/PremiumInfo/data/SchduleRatingModifier3" />
						             	</xsl:if>
						             	<xsl:if test="response/PremiumInfo/data/SchduleRatingModifier3 = ''">
						             		0
						             	</xsl:if>
						             </fo:block></fo:table-cell>
						           </fo:table-row>
						           
						           
						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Total Schedule Rating Modifier </fo:inline> = a+b+c </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell >
						             	<fo:block font-size="15pt" font-family="Arial" >
						             		<xsl:if test="response/PremiumInfo/data/TotalSM != '0'">
						             			<fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/TotalSM" /> = <xsl:value-of select="response/PremiumInfo/data/SchduleRatingModifier1" /> + <xsl:value-of select="response/PremiumInfo/data/SchduleRatingModifier2" /> + <xsl:value-of select="response/PremiumInfo/data/SchduleRatingModifier3" /></fo:inline>
						             		</xsl:if>
						             		<xsl:if test="response/PremiumInfo/data/TotalSM = '0'">
						             			<fo:inline font-weight="bold">Total Schedule Rating Modifier = <xsl:value-of select="response/PremiumInfo/data/TotalSM" /></fo:inline>
						             		</xsl:if>						             		
						             	</fo:block>
						             </fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 7:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Final Premium </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Final Premium  = Interim Modified Base Premium  * (100+ Total Schedule Rating Modifier)/ 100 </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell >
						             	<fo:block font-size="15pt" font-family="Arial" >
						             		<xsl:if test="response/PremiumInfo/data/IsMinimumPremium= 'Y'">
						             			<fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/ActualFinalBaseLimitPremium" /></fo:inline> = <xsl:value-of select="response/PremiumInfo/data/PolicyPremiumWithoutSM" /> * (100 + <xsl:value-of select="response/PremiumInfo/data/TotalSM" />) / 100
						             		</xsl:if>
						             		<xsl:if test="response/PremiumInfo/data/IsMinimumPremium= 'N'">
						             			<fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/ActualFinalBaseLimitPremium" /></fo:inline> = <xsl:value-of select="response/PremiumInfo/data/PolicyPremiumWithoutSM" /> * (100 + <xsl:value-of select="response/PremiumInfo/data/TotalSM" />) / 100
						             		</xsl:if>
						             		<xsl:if test="response/PremiumInfo/data/IsMinimumPremium= ''">
						             			<fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/ActualFinalBaseLimitPremium" /></fo:inline> = <xsl:value-of select="response/PremiumInfo/data/PolicyPremiumWithoutSM" /> * (100 + <xsl:value-of select="response/PremiumInfo/data/TotalSM" />) / 100
						             		</xsl:if>
						             		
						             	</fo:block>
						             </fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell >
						             	<fo:block font-size="15pt" font-family="Arial" >
						             		<xsl:if test="response/PremiumInfo/data/IsMinimumPremium= 'Y'">
					             				<fo:inline font-weight="bold">Final Premium  = <xsl:value-of select="response/PremiumInfo/data/ActualFinalBaseLimitPremium" /> (Minimum)</fo:inline>
					             			</xsl:if>						             		
						             	</fo:block>
						             </fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 8:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Tax Calculation </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">City Tax = </fo:inline><xsl:value-of select="response/PremiumInfo/data/MTTaxAmmount" />	<!--  (<xsl:value-of select="response/PremiumInfo/data/MTTaxPercentage" /> %)--> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">County Tax = </fo:inline><xsl:value-of select="response/PremiumInfo/data/CountyTaxAmmount" /> <!-- (<xsl:value-of select="response/PremiumInfo/data/CountyTaxPercentage" /> %)-->  </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">State Tax 1 = </fo:inline><xsl:value-of select="response/PremiumInfo/data/State1TaxAmmount" /> <!-- (<xsl:value-of select="response/PremiumInfo/data/State1Percentage" /> %) --></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">State Tax 2 = </fo:inline><xsl:value-of select="response/PremiumInfo/data/State2TaxAmmount" /><!-- (<xsl:value-of select="response/PremiumInfo/data/State2Percentage" /> %) --></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Total Tax = City Tax + County Tax + State 1 Tax + State 2 Tax</fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/Total_tax" /> </fo:inline>= 
						             
						             <xsl:value-of select="response/PremiumInfo/data/MTTaxAmmount" /> +
						             
						             <xsl:value-of select="response/PremiumInfo/data/CountyTaxAmmount" /> + 
						             
						             <xsl:value-of select="response/PremiumInfo/data/State1TaxAmmount" /> + 
						             
						             <xsl:value-of select="response/PremiumInfo/data/State2TaxAmmount" />
						            
						             </fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block margin-top="5mm">
						<fo:table >
					         <fo:table-column column-width = "20mm" />
					         <fo:table-column column-width = "200mm" />
					         <fo:table-body>
					         		<fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">STEP 9:</fo:inline></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"> Final Premium with all taxes </fo:inline></fo:block></fo:table-cell>
						           </fo:table-row>						           
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold">Final Premium with all Taxes </fo:inline>= Final Premium + Total Tax</fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell number-columns-spanned="2" empty-cells="show"><fo:block font-size="15pt" font-family="Arial" ><fo:external-graphic src="../LawyersIns/img/space.gif" content-height="0.3em" content-width="10em"/> </fo:block></fo:table-cell>
						           </fo:table-row>
						           <fo:table-row>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ></fo:block></fo:table-cell>
						             <fo:table-cell ><fo:block font-size="15pt" font-family="Arial" ><fo:inline font-weight="bold"><xsl:value-of select="response/PremiumInfo/data/FinalBaseLimitPremiumWithTax" /></fo:inline>= <xsl:value-of select="response/PremiumInfo/data/ActualFinalBaseLimitPremium" /> + <xsl:value-of select="response/PremiumInfo/data/Total_tax" /></fo:block></fo:table-cell>
						           </fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block id="last-page"/>
											
				</fo:flow>
			</fo:page-sequence>

		</fo:root>

	</xsl:template>

</xsl:stylesheet>
