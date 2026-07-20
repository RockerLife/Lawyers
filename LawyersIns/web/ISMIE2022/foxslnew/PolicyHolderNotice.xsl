<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	<xsl:template name="PolicyHolderNotice"  match="/">
		<fo:block margin-top="2mm">
			<fo:block font-size="25px" text-align="center">
				<fo:external-graphic src="../LawyersIns/image/PolicyHolderNotice_logo.png" content-height="10em" content-width="20em"/>
			</fo:block>
			<fo:block margin-top="8mm" font-size="27px" text-align="center">
				<fo:block color="#104B82">
					<fo:inline font-weight="bold">
						IMPORTANT POLICYHOLDER NOTICE
					</fo:inline>
				</fo:block>
			</fo:block>
			<fo:block margin-top="8mm" margin-left="2mm">
				<fo:table>
					<fo:table-column column-width="100mm"/>
					<fo:table-column column-width="80mm"/>
					<fo:table-body>
						<fo:table-row>
							<fo:table-cell>
								<fo:block margin-top="0.2in"/>
								<fo:block font-size="12px">
									As part of our value-added services to our 
									Lawyer's Professional Liability clients, 
									Protexure Insurance Agency, Inc. and 
									ISMIE Mutual Insurance Company offer 
									you a pre-claim and risk mitigation 
									consultation “helpline” staffed by 
									independent attorneys from the law firm 
									of Marshall Dennehy, who specialize in 
									the defense of lawyer professional liability 
									claims.
								</fo:block>
								<fo:block margin-top="0.2in"/>
								<fo:block font-size="12px">
									This helpline provides you access to seek 
									advice prior to submitting a claim or 
									reporting a circumstance that could give 
									rise to a claim. 
								</fo:block>
							</fo:table-cell>
							<fo:table-cell>
								<fo:block margin-top="0.2in"/>
								<fo:block font-size="12px">
									It will also serve as a resource to reduce 
									the professional liability risks inherent in 
									your day-to-day practice of law. 
									Consultation with a helpline attorney is on 
									a confidential basis and can serve to 
									properly manage any circumstance that 
									may lead to a potential claim.
								</fo:block>
								<fo:block margin-top="0.2in"/>
								<fo:block font-size="12px">
									Any advice provided will not be considered 
									the reporting of a claim, or potential claim. 
									Please refer to the policy for requirements 
									in reporting claims and potential claims
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
			<fo:block margin-top="8mm" margin-left="2mm">
				<fo:block font-size="25px" text-align="center">
					<fo:inline font-weight="bold" color="#104B82">
						1-888-509-1786
					</fo:inline>
				</fo:block>
				<fo:block font-size="13px">
					Please call our dedicated toll-free number, shown above, Monday – Friday between 8:00 
					AM – 5:00 PM EST. Please note, calls received after business hours will be responded to 
					the following business day.
				</fo:block>
			</fo:block>
			<fo:block font-size="25px" text-align="center">
				<fo:external-graphic src="../LawyersIns/image/PolicyHolderNotice.png" content-height="30em" content-width="23.5em"/>
			</fo:block>
			<fo:block font-size="17px" text-align="center" margin-top="8mm" >
				<fo:inline font-weight="bold" color="#104B82">
					Thank you for choosing Protexure Lawyers
				</fo:inline>
			</fo:block>
		</fo:block>
	</xsl:template>
</xsl:stylesheet>
          
          