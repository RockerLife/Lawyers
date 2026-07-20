
<html>
<head>
	<title>Index</title>
		<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>
<%
	session.setAttribute("alsid",request.getParameter("alsid"));
%>
    <form name ="test" action="completeapplink.do" method="post">
    <input type="hidden" name="inet_method" value="showApp"/>
    <input type="hidden" name="inet_page" value="IPFSThankYouPage"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|User|role_id|isOtherStateAgent|isNewApp|x_response_code|PolicyKey|FromManualCheck|"/>
    <input type="hidden" name="dynaValues" value="|Y|2|3|1|insured|1|N|Y|1|<%=request.getParameter("PolicyKey")==null?"SPACE":request.getParameter("PolicyKey")%>|Y|"/>
    <input type="hidden" name="inet_context" value=""/>
    </form>
</body>       <script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script>
<Script language="javascript">

forward_page();	

	function forward_page()
	{
					
		document.forms[0].submit();		
	}
	

	</Script>

</html>

