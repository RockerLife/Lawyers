<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-AMEND-WY2">
		
					<fo:block font-weight="bold" text-align="center"
						font-size="12px">
						ATTENTION: WYOMING POLICYHOLDERS
					</fo:block>

					<fo:block margin-top="10mm" />

					<fo:block font-weight="bold" start-indent="3cm" margin-top="10mm">
						THE POLICY YOU ARE PURCHASING PROVIDES THAT DEFENSE EXPENSES
						INCURRED BY THE INSURER ON YOUR BEHALF FOR A CLAIM COVERED BY THIS
						POLICY ARE PART OF AND REDUCE THE LIMIT OF LIABILITY AVAILABLE TO
						PAY JUDGEMENTS AND SETTLEMENTS AND MAY EXHAUST THE LIMIT OF
						LIABILITY ENTIRELY.
					</fo:block>

					<fo:block font-weight="bold" start-indent="3cm" margin-top="10mm">
						WYOMING INSURANCE REGULATIONS REQUIRE THAT YOU ACKNOWLEDGE YOU
						HAVE READ THE PARAGRAPH ABOVE AS EVIDENCED BY YOUR SIGNATURE ON
						THIS NOTICE AND RETURNING IT TO THE INSURER.
					</fo:block>

					<fo:block font-weight="bold" start-indent="3cm" margin-top="10mm">
						NAME ON POLICY ___________________________________
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm" margin-top="5mm">
						YOUR NAME _________________________________________
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm" margin-top="5mm">
						YOUR TITLE ________________________________________
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm" margin-top="5mm">
						YOUR SIGNATURE ___________________________________
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm" margin-top="5mm">
						DATE SIGNED _________________________MM/DD/YYYY)
					</fo:block>

					<fo:block font-weight="bold" start-indent="3cm" margin-top="5mm">
						*******************************************************************
					</fo:block>

					<fo:block font-weight="bold" start-indent="3cm" margin-top="5mm">
						UNITED STATES FIRE INSURANCE COMPANY
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm">
						THE NORTH RIVER INSURANCE COMPANY
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm">
						305 MADISON AVE; P.O BOX 1973
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm">
						MORRISTOWN, NJ 07962-1973
					</fo:block>
					<fo:block font-weight="bold" start-indent="3cm">
						ATTN: PROFESSIONAL LIABILITY DEPARTMENT
					</fo:block>







					

					
					<fo:block margin-top="10mm" />
					<fo:block font-size="10px" start-indent="2cm" color="grey"
						text-align="left">
						<xsl:value-of
							select="response/policyformfooter_freeform_01/data/LPLAMENDWY20910" />
						
					</fo:block>


	</xsl:template>
</xsl:stylesheet>
