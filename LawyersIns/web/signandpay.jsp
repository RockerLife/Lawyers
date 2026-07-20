<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script src="js/jquery.js"></script>
  <script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>
  
  <BODY>
  <table width="99%" height="600px" >
  	<tr>
  		<td>
    <form name ="test" action="signandpay.do" method="post">
    <input type="hidden" name="inet_method" value="signandpay"/>
    <input type="hidden" name="inet_page" value="indexsignandpay"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|fromRegistrationLink|EncryptedPolicyKey|SubProducerLogoDisplay|"/>
    <input type="hidden" name="dynaValues" value="|Y|2|2|1|Y|<%=request.getParameter("PolicyKey") %>|Y|"/>
    <input type="hidden" name="inet_context" value=""/>
 	</form>
     
    </td>
  	</tr>
  	<tr>
  	<td align="center">
  	<img src="image/loading_gif.gif" /><br />
    	Please wait .....
  	</td>
  	</tr>
  </table>
</body>       <script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script>
<Script language="javascript">

forward_page();	

	function forward_page()
	{
					
		document.forms[0].submit();		
	}
	

	</Script>
</html>
