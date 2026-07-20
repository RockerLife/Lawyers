<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo"
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:java="java">
	
	<xsl:template match="/" name="Policycoverletter8">			
		
				
				    <fo:block text-align="center"  font-size="12px">THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				    <fo:block margin-top="10mm"/>
				    
				    <fo:block >
					<fo:table  text-align="center">
					    <fo:table-column column-width = "90mm" />
				        <fo:table-column column-width = "90mm" />
				       
				        <fo:table-body>
					    		  <fo:table-row>
					                    <fo:table-cell padding-left="4pt"><fo:block font-weight="bold"  font-size="10px"  text-align="left">To be attached to and form part of Policy No: <xsl:value-of select="response/policy_freeform_01/data/PolicyNumber" /></fo:block></fo:table-cell>
					                    <fo:table-cell  padding-left="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Effective Date of Endorsement: <xsl:value-of select="response/policy_freeform_01/data/PolicyEffectiveDate" /></fo:block></fo:table-cell>
					    		  </fo:table-row>
					    		 
					    		  <fo:table-row> 
					               		<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left">Issued to: <xsl:value-of select="response/policy_freeform_01/data/AccountName" /></fo:block></fo:table-cell>
					               		<fo:table-cell padding-left="4pt"  padding-top="4pt">
					               			 <fo:block font-weight="bold" font-size="10px"  text-align="left">Endorsement No: <xsl:value-of select="response/endorsements_freeform_01/data/LPL1082010"/></fo:block>
					               		</fo:table-cell>
					    		  </fo:table-row>
					    		 
					     </fo:table-body>
				     </fo:table>	   
				</fo:block>
							
			   <fo:block space-after=".1in" text-align-last="justify">
    				<fo:leader leader-pattern="rule" />
   				</fo:block>
			
				      <fo:block margin-top="12mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">LAWYERS PROFESSIONAL LIABILITY </fo:block>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">BROAD ADVANTAGE ENDORSEMENT</fo:block>
				    
				     <fo:block margin-top="10mm"/>
				     <fo:block text-align="left"  font-size="10px" >In consideration of the premium charged it is hereby agreed that:</fo:block>
				     <fo:block margin-top="5mm"/>
				     <fo:block text-align="left" font-size="10px" >(1) Section VI, General Conditions, is amended by the addition of the following paragraph M. Liberalization Clause:</fo:block>
				     <fo:block margin-top="5mm"/>
				     <fo:block text-align="left" text-indent="1.5cm" font-size="10px"  font-weight="bold">M. Liberalization Clause</fo:block>
				     <fo:block margin-top="5mm"/>	      
				     
				      <fo:block text-align="left" text-indent="2cm" font-size="10px" >If during the  <fo:inline font-weight="bold" font-style="italic">Policy Period</fo:inline> , the <fo:inline font-weight="bold" font-style="italic"> Insurer </fo:inline>adopts any provision that would broaden the coverage under this</fo:block>
				      <fo:block text-align="left" text-indent="2cm" font-size="10px" >policy without an additional premium charge, the broadened coverage shall automatically apply to this policy.</fo:block>
				      <fo:block margin-top="5mm"/>
				      
				      <fo:block text-align="left" font-size="10px" >(2) Section IV., Definitions, definition of <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> is deleted and replaced with the following definition:</fo:block>
				      <fo:block margin-top="5mm"/>
				      <fo:block text-align="left" font-size="10px" text-indent="1.5cm" ><fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> means the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline>, <fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline>, and:</fo:block>
				      <fo:block margin-top="5mm"/>
				      <fo:block text-align="left" font-size="10px" start-indent="2.5cm" >(a)  any present or future principal, partner, director, officer, member or employee of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> ;</fo:block> 
				      <fo:block text-align="left" font-size="10px" start-indent="2.5cm" >(b)  any former principal, partner, director, officer, member or employee of the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or of a </fo:block> 
				      <fo:block text-align="left" font-size="10px" start-indent="3cm" ><fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline>;</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="2.5cm" >(c)  the estate, heirs, executors, administrators, assigns and legal representatives of an <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> but only </fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="3cm" >in the  event of such <fo:inline font-weight="bold" font-style="italic">Insured's</fo:inline> death, incapacity, insolvency or bankruptcy, and only to the extent that</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="3cm" > such <fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline> would otherwise have been provided coverage under the terms, conditions and exclusions of this policy;</fo:block>  
				      <fo:block text-align="left" font-size="10px" start-indent="2.5cm" >(d)  any contract or temporary employee of a <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> under the direct supervision of an <fo:inline font-weight="bold" font-style="italic"> Insured</fo:inline>;</fo:block>  
				         
				       <fo:block margin-top="5mm"/>
				       <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >but only with respect to Professional Services performed within the scope of their duties on behalf of the </fo:block>
				        <fo:block text-align="left" font-size="10px" text-indent="1.5cm" ><fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> or <fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline>. </fo:block>
				        <fo:block margin-top="5mm"/> 
				         
				        <fo:block text-align="left" font-size="10px" >(3) Section IV.,  Definitions, definition of <fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline> is deleted and replaced with the following definition:</fo:block> 
				         <fo:block margin-top="5mm"/> 
				         <fo:block text-align="left" font-size="10px" text-indent="1.5cm" ><fo:inline font-weight="bold" font-style="italic">Predecessor Firm</fo:inline> means an individual, partnership, professional corporation, professional association, limited </fo:block>
				         <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >liability corporation or partnership which was at all times engaged in <fo:inline font-weight="bold" font-style="italic">Professional Services</fo:inline> and to whose </fo:block>
				         <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >financial assets and liabilities the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> became the majority successor in interest prior to the</fo:block>
				         <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >effective date as stated in Item 2. of the Declarations and which is named as such by specific endorsement to</fo:block>
				         <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >this policy. Predecessor Firm does not include any individual, partnership, professional corporation,</fo:block>
				          <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >professional liability corporation or partnership which was at all times engaged in Professional Services and to</fo:block>
				           <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >whose financial assets and  liabilities the <fo:inline font-weight="bold" font-style="italic">Named Insured</fo:inline> becomes the majority successor in interest</fo:block>
				           <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >subsequent to the effective date of this  policy as stated in  Item 2. of the Declarations unless the <fo:inline font-weight="bold" font-style="italic"> Insurer</fo:inline> at</fo:block>
				           <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >its sole discretion agrees to include such entity.Should the <fo:inline font-weight="bold" font-style="italic">Insurer</fo:inline> agree to include such entity it may do so for</fo:block>
				         	<fo:block text-align="left" font-size="10px" text-indent="1.5cm" >an additional premium and/or with amended policy terms terms and conditions.</fo:block>
				         
				          <fo:block margin-top="15mm"/>	
				          <!-- 		     
				     <fo:block text-align="right" font-size="12px" ><fo:inline font-weight="bold" font-style="italic"> Continued, on Page Two</fo:inline></fo:block>
				          -->	
				          <fo:table text-align="center">
				         	 <fo:table-column column-width = "110mm" />
				        	 <fo:table-column column-width = "110mm" />
				        	 
				        	 <fo:table-body>
				        		 <fo:table-row >
									<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent= "1cm">PAGE ONE OF TWO</fo:block></fo:table-cell>
									<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block font-weight="bold" font-size="10px"  text-align="left" start-indent= "2cm">CONTINUED ON PAGE TWO </fo:block></fo:table-cell>
							 	</fo:table-row>	 	
				        	 </fo:table-body>				          
				          </fo:table>
				          <fo:block margin-top="10mm"/>	
				       
			  			<fo:block  font-size="10px"  text-indent="2cm" color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1082010" /></fo:block>	
				      	 
				 	 <fo:block break-after="page" /> 
				  
				  	<fo:block text-align="center"  font-size="12px" >THIS ENDORSEMENT CHANGES THE POLICY, READ IT CAREFULLY.</fo:block>
				  	
				  	 <fo:block margin-top="10mm"/>
				  	 <fo:block text-align="center" font-weight="bold" font-size="10px">LAWYERS PROFESSIONAL LIABILITY </fo:block>
				     <fo:block text-align="center" font-weight="bold" font-size="10px">BROAD ADVANTAGE ENDORSEMENT</fo:block>
				     
				      <fo:block margin-top="8mm"/>
				     <fo:block text-align="center" font-weight="bold" font-size="10px" >(Page 2 of 2)</fo:block>
				      <fo:block margin-top="8mm"/>
				  
				   <fo:block text-align="left" font-size="10px" >(4) Section III., Paragraph D., Extended Reporting Period Extensions, sub- paragraph 3.<fo:inline font-weight="bold" font-style="italic">Retired  Insured </fo:inline> Extended </fo:block> 
				   <fo:block text-align="left" font-size="10px" text-indent="0.5cm" >Reporting Period, is amended by the addition of the following:</fo:block>
				  
				   <fo:block margin-top="4mm"/> 
				   <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >The additional premium charges stated herein shall be waived for a <fo:inline font-weight="bold" font-style="italic">Retired  Insured </fo:inline> who has been insured for</fo:block>
				    <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >his/her <fo:inline font-weight="bold" font-style="italic">Professional Services </fo:inline> by the <fo:inline font-weight="bold" font-style="italic">Insurer </fo:inline> for three consecutive <fo:inline font-weight="bold" font-style="italic">Policy Periods </fo:inline>  prior to the date the </fo:block>
				    <fo:block text-align="left" font-size="10px" text-indent="1.5cm" ><fo:inline font-weight="bold" font-style="italic"> Insured </fo:inline>becomes a <fo:inline font-weight="bold" font-style="italic">Retired  Insured </fo:inline>  and, such <fo:inline font-weight="bold" font-style="italic">Retired  Insured </fo:inline>:</fo:block>
				  
				       <fo:block margin-top="4mm"/>
				      <fo:block text-align="left" font-size="10px" start-indent="2.5cm" >(a) was, and is, the sole practitioner lawyer insured during said three consecutive <fo:inline font-weight="bold" font-style="italic">Policy Periods</fo:inline></fo:block> 
				      <fo:block text-align="left" font-size="10px" start-indent="2.9cm" > and;</fo:block>
				      <fo:block text-align="left" font-size="10px" start-indent="2.5cm" > (b) is the sole practitioner lawyers during the <fo:inline font-weight="bold" font-style="italic">Policy Periods </fo:inline>. stated in Item 2. of the Declarations.</fo:block> 
				      
				          <fo:block margin-top="4mm"/>
				       	 <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >For the purpose of the applicability of this waiver of additional premium “sole practitioner lawyer” means </fo:block>
				       	  <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >one who was engaged in performing <fo:inline font-weight="bold" font-style="italic">Professional Services </fo:inline> alone,  with no other partners, lawyers,</fo:block>
				       	   <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >consultants, bookkeepers or other professionals working on client engagements. </fo:block> 
				    
				    <fo:block margin-top="4mm"/>
				    
				    <fo:block text-align="left" font-size="10px" >(5) Section V of this policy, Exclusions, is amended by deletion of Exclusion 1 and replacing it with the following</fo:block>    
				     <fo:block text-align="left" font-size="10px" text-indent="0.2cm">Exclusion1.:</fo:block>
				     <fo:block margin-top="4mm"/> 
				   <fo:block text-align="left" font-size="10px" text-indent="0.5cm" >This policy does not apply to any Claim based upon or arising out of:</fo:block>
				   
				        
				         <fo:block margin-top="4mm"/> 
				   <fo:block text-align="left" font-size="10px" text-indent="1.5cm" >1. a dishonest, fraudulent, criminal or malicious act or omission or any intentional or knowing violation of law by</fo:block>
                   <fo:block text-align="left" font-size="10px" text-indent="2cm" >any <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>. However, the <fo:inline font-weight="bold" font-style="italic">Insured</fo:inline> will provide the <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline> with a defense of such Claim and pay </fo:block>
                   <fo:block text-align="left" font-size="10px" text-indent="2cm" ><fo:inline font-weight="bold" font-style="italic">Defense Expenses </fo:inline> for any such <fo:inline font-weight="bold" font-style="italic">Claim </fo:inline>until there is a judgment, final adjudication or adverse admission</fo:block>
                   <fo:block text-align="left" font-size="10px" text-indent="2cm" >by an <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline>or a finding of  fact against an <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline> as to such conduct. However, this exclusion shall not</fo:block>
                   <fo:block text-align="left" font-size="10px" text-indent="2cm" >apply to any <fo:inline font-weight="bold" font-style="italic">Insured </fo:inline> who, in fact, did not  personally commit, direct, participate in committing a dishonest,</fo:block>
                   <fo:block text-align="left" font-size="10px" text-indent="2cm" >intentional, fraudulent, criminal or malicious act or omission or any intentional or knowing violation of law.</fo:block>
				         
				    <fo:block margin-top="4mm"/> 
				   <fo:block text-align="left" font-size="10px" >(6) Item 7. of  the Declarations, Optional Extended Reporting Period is amended to provide these optional lengths:</fo:block>      
				         
				   <fo:block margin-top="4mm"/> 
				      <fo:table text-align="center">
				         	 <fo:table-column column-width = "50mm" />
				        	 <fo:table-column column-width = "100mm" />
				        	 
				        	 <fo:table-body>
				        		 <fo:table-row >
									<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "2cm">(a) length</fo:block></fo:table-cell>
									<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= ".5cm" >(b) Additional Premium as a percent of the annual premium: </fo:block></fo:table-cell>
							 	</fo:table-row>	
							 	 <fo:table-row >
									<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "2.3cm">(i)  12 months</fo:block></fo:table-cell>
									<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "1cm"> (i) 100% </fo:block></fo:table-cell>
							 	</fo:table-row>	
							 	 <fo:table-row >
									<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "2.3cm">(ii)  36 months </fo:block></fo:table-cell>
									<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "1cm"> (ii) 185%</fo:block></fo:table-cell>
							 	</fo:table-row>	
							 	
							 	 <fo:table-row >
									<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block font-size="10px"  text-align="left" start-indent= "2.3cm">(iii) 60 months </fo:block></fo:table-cell>
									<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "1cm">(iii)  225% </fo:block></fo:table-cell>
							 	</fo:table-row>	
							 	
							 	 <fo:table-row >
									<fo:table-cell padding-left="4pt" padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "2.3cm">(iv) 72 months </fo:block></fo:table-cell>
									<fo:table-cell padding-left="4pt"  padding-top="4pt"><fo:block  font-size="10px"  text-align="left" start-indent= "1cm">(iv) 250% </fo:block></fo:table-cell>
							 	</fo:table-row>	
				        	 </fo:table-body>				          
				         </fo:table>
				         
				         
				         
				          <fo:block margin-top="4mm"/>
				   <fo:block text-align="left" font-size="10px" >  If any of the above lengths or premium charges conflict with a state amendatory endorsement attached to this policy, or with insurance regulations in the policyholder’s state, such endorsement or regulation shall supersede this item (6) of this Broad  Advantage Endorsement.</fo:block>      
				         
				         
				         
				        <fo:block margin-top="20mm"/>
				        <fo:block text-align="left" font-size="10px" >All other terms, conditions and limitations of the policy remain unaltered.</fo:block>
				         
				         
				           <fo:block margin-top="10mm"/>
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
				       
				       <fo:block margin-top="6mm"/>
				       <fo:block  font-size="10px" text-indent="2cm"  color="grey" text-align="left"><xsl:value-of select="response/policyformfooter_freeform_01/data/LPL1082010" /></fo:block>
				  	  
			
     </xsl:template>
</xsl:stylesheet>




					
				    	