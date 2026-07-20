<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String encryptedPolicyKey = request.getParameter("PolicyKey");
String policyKey = "";
try {
	com.userbo.LawyersValidationUtils validationUtils = new com.userbo.LawyersValidationUtils();
	try {
		Integer.parseInt(encryptedPolicyKey);
		policyKey = encryptedPolicyKey;
		encryptedPolicyKey = validationUtils.encrypt(policyKey);
	} catch (NumberFormatException e) {
		policyKey = validationUtils.decrypt(encryptedPolicyKey);
		Integer.parseInt(policyKey);
	}
} catch (Exception e) {
	response.sendError(400, "Invalid renewal link.");
	return;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<style type="text/css">
  		body
  		{
  			font-family:arial;
  			font-size:12px;
  			line-height:18px;
  			text-aling:center;
  		}
  	</style>
<script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>
  
  <BODY>
  <table width="99%" height="600px" >
  	<tr>
  		<td>
  			
  		
<%
	session.setAttribute("alsid",request.getParameter("alsid"));
%>
    <form name ="test" action="renew.do" method="post">
    <input type="hidden" name="inet_method" value="insuredRenewedByMail"/>
    <input type="hidden" name="inet_page" value="indexrenew"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|CreatedType|IsInsuredViewed|EncryptedPolicyKey|SubProducerLogoDisplay|"/>
    <input type="hidden" name="dynaValues" value="|Y|2|3|1|insured|N|<%=encryptedPolicyKey %>|Y|"/>
    <input type="hidden" name="inet_context" value=""/>
    
    
    </form>
     
    </td>
  	</tr>
  	
	<% 
	String updatedRenewalLink  = "";
	if(request.getParameter("UpdatedRenewalLink") != null){
		updatedRenewalLink  = String.valueOf(request.getParameter("UpdatedRenewalLink"));
	}
	
	String policyKeyList = "68941~68768~66424~68668~68156~70138~70127~69100~68900~68777~67915~67648~67409~70178~68059~65767~68888~70427~68216~70225~68500~"
						+ "67687~67642~67294~68808~70511~70337~70300~68835~70799~68804~67817~70366~67766~70821~68506~67683~66212~68603~67692~70196~68118~"
						+ "70915~70639~70588~70497~66897~71401~70251~68990~71230~70899~70880~70452~70258~71238~66335~70450~68464~71374~68408~70433~71742~"
						+ "67735~71639~68639~71839~72010~67670";
	
	if(!(policyKeyList.contains(policyKey)) || "Y".equals(updatedRenewalLink)){ 
	%>
		<tr>
		  	<td align="center">
		  	<img src="image/loading_gif.gif" /><br />
		    Please wait while we create you renewal documents.....
		  	</td>
		 </tr>
		<Script language="javascript">
		
		setTimeout(function(){ forward_page(); }, 1000);
			
	
		function forward_page() {
			document.forms[0].submit();		
		}
	
		</Script>
		
	<% } else {%> 
	  	<tr>
	  		<td align="center">
	  			This Link has been disabled. Soon you will get another Renewal Solicitation Link.
	  		</td>
		</tr>
	<%}%>

  </table>
</body>       <script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script>
</html>
