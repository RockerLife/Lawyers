
<%@page import="com.util.SystemProperties"%>
<html>
<head>
	<title>Index</title>	
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
<%
	String url = SystemProperties.getInstance().getString("appl.LawyersIns.url");
%>
<BODY>

<table width="99%" height="600px" >
  	<tr>
  		<td>
    <form name ="test" action="<%=url %>/confirmation.do;jsessionid=<%= request.getParameter("jsessionid") %>" method="post">
    <input type="hidden" name="inet_method" value="showpaypalthanks"/>
    <input type="hidden" name="inet_page" value="showpaypalthanks"/>
    <input type="hidden" name="dynaKeys" id="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|x_response_code|x_trans_id|x_response_reason_code|x_response_reason_text|x_auth_code|x_amount|x_response_reason_code|"/>
    <input type="hidden" name="dynaValues" id="dynaValues" value="|Y|2|3|1|<%=request.getParameter("x_response_code") != null && !request.getParameter("x_response_code").equals("") ? request.getParameter("x_response_code") : "$" %>|<%=request.getParameter("x_trans_id") != null && !request.getParameter("x_trans_id").equals("") ? request.getParameter("x_trans_id") : "$"%>|<%=request.getParameter("x_response_reason_code") != null && !request.getParameter("x_response_reason_code").equals("") ? request.getParameter("x_response_reason_code") : "$"%>|<%=request.getParameter("x_response_reason_text") != null && !request.getParameter("x_response_reason_text").equals("") ? request.getParameter("x_response_reason_text") : "$"%>|<%=request.getParameter("x_auth_code") != null && !request.getParameter("x_auth_code").equals("") ? request.getParameter("x_auth_code") : "$"%>|<%=request.getParameter("x_amount") != null && !request.getParameter("x_amount").equals("") ? request.getParameter("x_amount") : "$"%>|<%=request.getParameter("x_response_reason_code") != null && !request.getParameter("x_response_reason_code").equals("") ? request.getParameter("x_response_reason_code") : "$"%>|"/>
 	<input type="hidden" name="inet_context" value=""/>
    
    
    </form>
    </td>
  	</tr>
  	<tr>
  	<td align="center">
  	<img src="image/loadingp.gif" /><br />
    Please wait while you payment is been processed.....<br />
    Do not refresh or press back button
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

