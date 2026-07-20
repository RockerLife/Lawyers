<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="Ofac">
		<fo:block text-align="left">				  	
		 	<fo:external-graphic src="../LawyersIns/image/CFLogo.png"/> 		          	
		 </fo:block>
		 <fo:block margin-top="10mm" />
		<fo:block font-weight="bold" text-align="center" font-size="15px">
			U.S. TREASURY DEPARTMENT'S OFFICE OF FOREIGN ASSETS CONTROL</fo:block>
		<fo:block font-weight="bold" text-align="center" font-size="15px">
			 ("OFAC")</fo:block>
		<fo:block font-weight="bold" text-align="center" font-size="15px">
			ADVISORY NOTICE TO POLICYHOLDERS</fo:block>
		<fo:block margin-top="5mm" />
		<fo:block font-weight="bold" text-align="center" font-size="15px">
			________________________________________________________________
		</fo:block>

		<fo:block margin-top="5mm" />
		<fo:block text-align="left" font-size="10px">No coverage is
			provided by this Policyholder Notice nor can it be construed to
			replace any provisions of your
			policy. You should read your policy and review your Declarations page for
			complete information on the coverages
			you are provided.
		</fo:block>
		<fo:block margin-top="3mm" />
		<fo:block text-align="left" font-size="10px">
			This Notice provides information concerning possible impact on your
			insurance coverage due to directives issued
			by OFAC.
			<fo:inline font-weight="bold">Please read this Notice carefully.
			</fo:inline>
		</fo:block>
		<fo:block margin-top="3mm" />
		<fo:block text-align="left" font-size="10px">The Office of
			Foreign Assets Control (OFAC) administers and enforces sanctions
			policy, based on Presidential
			declarations of "national emergency". OFAC has identified and listed numerous:
		</fo:block>

		<fo:block margin-top="3mm" />
		<fo:block >
			<fo:table  text-align="center">
			    <fo:table-column column-width = "10mm" />
		        <fo:table-column column-width = "50mm" />
		        <fo:table-body>
			    		  <fo:table-row>
			                    <fo:table-cell ><fo:block><fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/></fo:block></fo:table-cell>
			                    <fo:table-cell ><fo:block font-size="10px" text-align="left">Foreign agents;</fo:block></fo:table-cell>
			    		  </fo:table-row>
			    		  <fo:table-row>
			                    <fo:table-cell ><fo:block><fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/></fo:block></fo:table-cell>
			                    <fo:table-cell ><fo:block font-size="10px" text-align="left">Front organizations;</fo:block></fo:table-cell>
			    		  </fo:table-row>
			    		  <fo:table-row>
			                    <fo:table-cell ><fo:block><fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/></fo:block></fo:table-cell>
			                    <fo:table-cell ><fo:block font-size="10px" text-align="left">Terrorists;</fo:block></fo:table-cell>
			    		  </fo:table-row>
			    		  <fo:table-row>
			                    <fo:table-cell ><fo:block><fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/></fo:block></fo:table-cell>
			                    <fo:table-cell ><fo:block font-size="10px" text-align="left"> Terrorist organizations; and</fo:block></fo:table-cell>
			    		  </fo:table-row>
			    		  <fo:table-row>
			                    <fo:table-cell ><fo:block><fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/></fo:block></fo:table-cell>
			                    <fo:table-cell ><fo:block font-size="10px" text-align="left">Narcotics traffickers;</fo:block></fo:table-cell>
			    		  </fo:table-row>
			     </fo:table-body>
		     </fo:table>	   
		</fo:block>
		<!-- 		
		<fo:block text-align="left" font-size="10px">
			<fo:external-graphic src="../LawyersIns/image/spacer.png" />
			<fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/>
			 Foreign agents;
		</fo:block>
		<fo:block text-align="left" font-size="10px">
			<fo:external-graphic src="../LawyersIns/image/spacer.png" />
			<fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/>
			 Front organizations;
		</fo:block>
		<fo:block text-align="left" font-size="10px">
			<fo:external-graphic src="../LawyersIns/image/spacer.png" />
			<fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/>
			 Terrorists;
		</fo:block>
		<fo:block text-align="left" font-size="10px">
			<fo:external-graphic src="../LawyersIns/image/spacer.png" />
			<fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/>
			 Terrorist organizations; and
		</fo:block>
		<fo:block text-align="left" font-size="10px">
			<fo:external-graphic src="../LawyersIns/image/spacer.png" />
			<fo:external-graphic src="../LawyersIns/image/list_item_circle.png" content-height="0.3em" content-width="0.3em"/>
			 Narcotics traffickers;
		</fo:block>-->
		
		<fo:block margin-top="3mm" />
		<fo:block text-align="left" font-size="10px">as "Specially
			Designated Nationals and Blocked Persons". This list can be located
			on the United States Treasury's
			web site – http//www.treas.gov/ofac.
		</fo:block>


		<fo:block margin-top="5mm" />
		<fo:block font-size="10px" text-align="left">In accordance with OFAC
			regulations, if it is determined that you or any other insured, or
			any person or entity
			claiming the benefits of this insurance has violated U.S. sanctions law or is
			a Specially Designated National and
			Blocked Person, as identified by OFAC, this insurance will be considered a
			blocked or frozen contract and all
			provisions of this insurance are immediately subject to OFAC. When an insurance
			policy is considered to be such
			a blocked or frozen contract, no payments nor premium refunds may be
			made without authorization from OFAC.			
		</fo:block>
		
		<fo:block margin-top="5mm" />
		<fo:block font-size="10px" text-align="left">
			Other limitations on the premiums and payments also apply.
		</fo:block>
		
		<fo:block margin-top="100mm" />
		<fo:block font-size="10px"  color="grey" text-align="left">
			<!--<xsl:value-of select="response/policyformfooter_freeform_01/data/ILP0010104" /> --> IL P001 01 04 &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;  &#160;&#160;&#160;&#160;&#160;&#160; ISO Properties Inc., 2004
		</fo:block>
	</xsl:template>
</xsl:stylesheet>




					
				    	