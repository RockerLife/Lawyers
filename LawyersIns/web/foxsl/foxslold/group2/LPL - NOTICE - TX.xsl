<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">

	<xsl:template match="/" name="LPL-State-TX-Notice" >
	
				    <fo:block>
					<fo:table  text-align="left">
					    <fo:table-column column-width = "80mm" />
					     <fo:table-column column-width = "20mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"><fo:inline font-weight="bold" >TEXAS - IMPORTANT NOTICE</fo:inline></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					                    <fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"><fo:inline font-weight="bold" >AVISO IMPORTANTE</fo:inline></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		   <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="3mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		  
					    					    		
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="left">To obtain information or make a complaint:</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left">Para obtener informacion o para someter una queja:</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		    <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		 
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left">You may call the company’s telephone number for information or to make a complaint at</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-size="10px"  text-align="left">Usted puede llamar al numero de telefono de la compania para informacion o para someter una queja al</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		  <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		  
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent="2cm">1-973-490-6600</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent="2cm">1-973-490-6600</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		    <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" >You may write to either Company at</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-size="10px"  text-align="left">Puede escribir</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		    
					    		    <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		    
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="8px" font-style="italic"  text-align="left" >UNITED STATES FIRE INSURANCE COMPANY</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="8px" font-style="italic"  text-align="left" >UNITED STATES FIRE INSURANCE COMPANY O</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		    <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="8px" font-style="italic"  text-align="left" >THE NORTH RIVER INSURANCE COMPANY</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="8px" font-style="italic"  text-align="left" >THE NORTH RIVER INSURANCE COMPANY</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  					    		  
					    		  
					    		  	<fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="8px"  text-align="left" ><fo:inline font-weight="bold" font-style="italic">305 Madison Avenue</fo:inline></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block ></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-weight="bold" font-size="8px" font-style="italic"  text-align="left">305 Madison Avenue</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="8px" font-style="italic"  text-align="left" >Morristown, NJ 07907</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-style="italic" font-size="8px"  text-align="left">Morristown, NJ 07907</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>					    		  
					    		  
					    		   <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" >You may contact the Texas Department of Insurance to obtain information on companies, coverages, rights or complaints at</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block ></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left">Puede communicarse con el Departamento de Seguros de Texas para obtener informacion acerca de companias, coberturas, derechos o quejas al</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		    <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		  
					    		<fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent="2cm"> 1-800-252-3439</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent="2cm">1-800-252-3439</fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		  
					    		   <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		  
					    		  
					    		 <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" >You may write the Texas Department of Insurance</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-size="10px"  text-align="left" >Puede escribir al Departamento de Seguros de Texas</fo:block></fo:table-cell>
					    		 </fo:table-row> 
					    		  
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" >PO Box 149104</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-size="10px"  text-align="left">PO Box 149104</fo:block></fo:table-cell>
					    		 </fo:table-row> 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" >Austin, TX  78714-9104</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-size="10px"  text-align="left" >Austin, TX  78714-9104</fo:block></fo:table-cell>
					    		 </fo:table-row> 
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" >FAX# (512) 475-1771</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-size="10px"  text-align="left" >FAX# (512) 475-1771</fo:block></fo:table-cell>
					    		 </fo:table-row> 
					    		 
					    		  <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		    
					    		 <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" font-weight="bold" text-align="left" >Web:<fo:inline font-weight="bold" font-style="italic" color="blue" >http://www.tdi.state.tx.us</fo:inline></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" font-weight="bold" text-align="left">Web:<fo:inline font-weight="bold" font-style="italic" color="blue" >http://www.tdi.state.tx.us</fo:inline></fo:block></fo:table-cell>
					    		 </fo:table-row> 
					    		 
					    		  <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		    
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" font-weight="bold" text-align="left" >E-mail:</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" font-weight="bold" text-align="left" >E-mail:</fo:block></fo:table-cell>
					    		 </fo:table-row> 					    		 
					    		 
					    		  <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" font-weight="bold" text-align="left"  color="blue">ConsumerProtection@tdi.state.tx.us</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" font-weight="bold" text-align= "left" color="blue">ConsumerProtection@tdi.state.tx.us</fo:block></fo:table-cell>
					    		 </fo:table-row> 
					    		 <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		 <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" ><fo:inline font-weight="bold"  >PREMIUM OR CLAIM DISPUTES: </fo:inline>Should you have a dispute concerning your premium or about a claim you should contact the company first.  If the dispute is not resolved, you may contact the Texas Department of Insurance.</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block  font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" text-align="left" ><fo:inline font-weight="bold"  >DISPUTAS SOBRE PRIMAS O RECLAMOS: </fo:inline>Si tiene una disputa concerniente a su prima o a un reclamo, debe comunicarse con la compania primero.  Si no se resuelve la disputa, puede entonces comunicarse con el departamento (TDI).</fo:block></fo:table-cell>
					    		 </fo:table-row> 
					    		 <fo:table-row>
					    		  		<fo:table-cell><fo:block margin-top="2mm"/></fo:table-cell>
					    		    </fo:table-row>
					    		 <fo:table-row>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" text-align="left" ><fo:inline font-weight="bold" >ATTACH THIS NOTICE TO YOUR POLICY: </fo:inline>This notice is for information only and does not become a part or condition of the attached document.</fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"><fo:block font-size="10px"  text-align="center"></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px" text-align="left" ><fo:inline font-weight="bold"  >UNA ESTE AVISO A SU POLIZA: </fo:inline>:  Este aviso es solo para proposito de informacion y no se convierte en parte o condicion del documento adjunto.</fo:block></fo:table-cell>
					    		 </fo:table-row>
					    		 
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
				     <fo:block margin-top="85mm"/>
			  <fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPLNOTICEFORTEXAS" /></fo:block>
   
		
			
     </xsl:template>
</xsl:stylesheet>
