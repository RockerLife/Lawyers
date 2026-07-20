
<html>
<head>
	<title>Index</title>
		<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>
<%
	session.setAttribute("alsid",request.getParameter("alsid"));
%>
    <form name ="test" action="quickquote.do" method="post">
    <input type="hidden" name="inet_method" value="showqq"/>
    <input type="hidden" name="inet_page" value="qqinsured"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|StatusKey|IsQuickQuote|IsFullQuote|User|IsQuickQuotePage|role_id|isOtherStateAgent|ProducerCode|MarketingId|QQSource|"/>
    <input type="hidden" name="dynaValues" value="|Y|2|3|1|1|Y|N|insured|N|1|N|<%=request.getParameter("ProducerCode")==null?"SPACE":request.getParameter("ProducerCode")%>|<%=request.getParameter("MarketingId")==null?"MSPACE":request.getParameter("MarketingId")%>|<%=request.getParameter("QQSource")==null?"Portal":request.getParameter("QQSource")%>|"/>
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

