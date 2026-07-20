<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template name="Policycoverletter"  match="/">
		
				  <fo:block margin-top="2mm">				  
				  <fo:table>
				         <fo:table-column column-width = "45mm" />
				         <fo:table-column column-width = "150mm" />
				         <fo:table-body>
				         	<fo:table-row>
					             <fo:table-cell >
					             	<fo:block text-align="left">			  	
									  	<fo:external-graphic src="../LawyersIns/image/logo_protexureaccountants1.png" content-height="6em" content-width="20em"/>           	
									</fo:block>
								 </fo:table-cell>
					             <fo:table-cell>
					             	<fo:block>
					             		<fo:table>
						             		<fo:table-body>
									         	<fo:table-row>
										             <fo:table-cell ><fo:block font-size="9px" font-weight="bold" text-align="right">Protexure Insurance/MPA.</fo:block></fo:table-cell>
										        </fo:table-row>
										        <fo:table-row>
										             <fo:table-cell ><fo:block font-size="9px" text-align="right">PO Box 773197</fo:block></fo:table-cell>
										        </fo:table-row>
										        <fo:table-row>
										             <fo:table-cell ><fo:block font-size="9px" text-align="right">Detroit, Michigan 48277-3197</fo:block></fo:table-cell>
										        </fo:table-row>
										        <fo:table-row>
													<fo:table-cell ><fo:block font-size="9px" text-align="right">Phone: 1-877-569-4111 </fo:block></fo:table-cell>
										        </fo:table-row>										        
										         									        
										        <fo:table-row>
										             <fo:table-cell ><fo:block font-size="9px" text-align="right">www.protexurelawyers.com</fo:block></fo:table-cell>
										        </fo:table-row>
										    </fo:table-body>
									    </fo:table>					             		
					             	</fo:block>
					             </fo:table-cell>
				           	</fo:table-row>      	
				           	
				         </fo:table-body>
				  </fo:table>
				  </fo:block>
				  
			    <fo:block margin-top="15mm"/>
			    <fo:table text-align="center">
					    <fo:table-column column-width = "50mm" />
				        <fo:table-column column-width = "50mm" />					       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell><fo:block  text-align="left" font-size="9px" ><xsl:value-of select="response/policy_freeform_01/data/CurrentDate"/></fo:block></fo:table-cell>
					                    <fo:table-cell><fo:block  text-align="left" font-size="9px" ></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  <fo:table-row>
					                    <fo:table-cell number-columns-spanned="2"><fo:block  text-align="left" font-size="9px" ><fo:external-graphic src="../LawyersIns/image/spacer.png"/></fo:block></fo:table-cell>
					              </fo:table-row>
					    		  <fo:table-row>
					                    <fo:table-cell number-columns-spanned="2"><fo:block  text-align="left" font-size="9px" ><xsl:value-of select="response/policy_freeform_01/data/AccountName"/></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  <!--  <fo:table-row>
					                    <fo:table-cell number-columns-spanned="2"><fo:block  text-align="left" font-size="9px" ><xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/></fo:block></fo:table-cell>
					    		  </fo:table-row> -->
					    		   <fo:table-row>
					                    <fo:table-cell number-columns-spanned="2"><fo:block  text-align="left" font-size="9px" ><xsl:value-of select="response/address_freeform_01/data/Address1"/></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  <fo:table-row>
					                    <fo:table-cell number-columns-spanned="2"><fo:block  text-align="left" font-size="9px" ><xsl:value-of select="response/address_freeform_01/data/Address2"/></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		    <fo:table-row>
					                    <fo:table-cell number-columns-spanned="2">
						                    <fo:block  text-align="left" font-size="9px" >
							                    <xsl:if test="response/address_freeform_01/data/City!= ''"> 
											        <xsl:value-of select="response/address_freeform_01/data/City" />
											        <xsl:if test="response/address_freeform_01/data/StateDesc!= ''">, 
											        </xsl:if>
											    </xsl:if>
											    <xsl:if test="response/address_freeform_01/data/StateDesc!= ''"> 					    	
											        <xsl:value-of select="response/address_freeform_01/data/StateDesc" /> 
											        <xsl:if test="response/address_freeform_01/data/Zip!= ''"><fo:external-graphic src="../LawyersIns/image/spacer.png"/>					        	
											    	</xsl:if>
											    </xsl:if>	
											    <xsl:if test="response/address_freeform_01/data/Zip!= ''"> 
											        <xsl:value-of select="response/address_freeform_01/data/Zip" /> 
											    </xsl:if>						    
							                </fo:block>
					                    </fo:table-cell>
					    		  </fo:table-row>
					    </fo:table-body>
				    </fo:table>
				    		 
                  
                  
                   <fo:block margin-top="2mm"/>
			    <fo:block  text-align="left" font-size="9px"> Re:   &#160;&#160;
			    &#160;&#160;
			    &#160;&#160;
			    &#160;
			               Lawyers Professional Liability</fo:block> 
                   <fo:block  text-align="left" font-size="9px" text-indent="1.5cm">Policy Number:&#160;	<xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /> </fo:block>
                   <fo:block  text-align="left" font-size="9px" text-indent="1.5cm">Policy term:&#160;&#160;&#160;&#160;&#160;&#160; 	<xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /> to <xsl:value-of select="response/address_freeform_01/data/PolicyExpirationDate" /></fo:block>
			    <fo:block  text-align="left" font-size="9px" text-indent="1.5cm">Carrier:&#160;&#160;&#160;&#160;&#160;&#160;	&#160;&#160;&#160;&#160;&#160;&#160;	ISMIE Mutual Insurance Company</fo:block> 
			    <fo:block margin-top="2mm"/>
			    <fo:block  text-align="left" font-size="9px">Dear <xsl:value-of select="response/policy_freeform_01/data/ContactPerson"/>,</fo:block>
			    <fo:block margin-top="2mm"/>
		       	<fo:block  text-align="left" font-size="9px">Thank you for choosing Protexure Lawyers for your insurance needs.  I am pleased to attach your Lawyers Professional Liability Policy, underwritten by ISMIE Mutual Insurance Company.  </fo:block>
			    <fo:block margin-top="2mm"/>
			    <fo:block  text-align="left" font-size="9px">Please review the attached policy carefully and advise us immediately if there are any conflicts between the actual policy and the information provided by us throughout the process.</fo:block>
			    <!-- <fo:block margin-top="2mm"/>
			    <fo:block  text-align="left" font-size="9px">As a policyholder, you have access to our Lawyers' Risk Management Hotline - at no charge.  We provide confidential advice on questions about malpractice risks and other day-to-day legal issues that may affect your business.  Call 1-888-959-2786 and have your policy number available.</fo:block> -->			    
			    <fo:block margin-top="2mm"/>
			    <fo:block  text-align="left" font-size="9px">If you have any additional questions, please contact us at 1-877-569-4111 and one of our experienced Underwriting Specialists will be happy to assist you.</fo:block>
			    <fo:block margin-top="2mm"/>
			    <fo:block  text-align="left" font-size="9px">We appreciate your business very much and look forward to renewing your policy for many years to come.</fo:block>
			    <fo:block margin-top="4mm"/>
			    <fo:block  text-align="left" font-size="9px">Best Regards,</fo:block>
			    <fo:block>
					<fo:external-graphic src="../LawyersIns/image/kyle.png"/>
 				</fo:block>
 				<fo:block  text-align="left" font-size="9px">Kyle Nieman</fo:block>
			    <fo:block  text-align="left" font-size="9px">President/CEO</fo:block>
			    <fo:block  text-align="left" font-size="9px">Protexure</fo:block> 
				 
     </xsl:template>
</xsl:stylesheet>




					
				    	