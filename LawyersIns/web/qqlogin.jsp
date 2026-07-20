
<html>
<head>
	<title>Index</title>
		<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>

    <form name ="test" action="login.do" method="post">
    <input type="hidden" name="inet_method" value="showlogin"/>
    <input type="hidden" name="inet_page" value="qqlogin"/>
    <input type="hidden" name="dynaKeys" value="|User|role_id|isOtherStateAgent|IsQuickQuotePage|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|fromRegistrationLink|AccountEmail|PolicyKey|AccountKey|TransactionSequence|VersionSequence|CoverageSequence"/>
    <input type="hidden" name="dynaValues" value="|insured|1|N|Y|Y|2|3|1|Y|<%=request.getParameter("AccountEmail")%>|<%=request.getParameter("PolicyKey")%>|<%=request.getParameter("AccountKey")%>|<%=request.getParameter("TransactionSequence")%>|<%=request.getParameter("VersionSequence")%>|<%=request.getParameter("CoverageSequence")%>"/>
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

