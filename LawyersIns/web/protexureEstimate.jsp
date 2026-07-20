
<html>
<head>
	<title>protexureEstimate</title>	
		<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>
<%
	session.setAttribute("alsid",request.getParameter("alsid"));
%>
    <form name ="test" action="estimateForm.do" method="post">
    <input type="hidden" name="inet_method" value="showApp"/>
    <input type="hidden" name="inet_page" value="protexureEstimate"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|User|role_id|"/>
    <input type="hidden" name="dynaValues" value="|Y|2|3|1|insured|1|"/>
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

