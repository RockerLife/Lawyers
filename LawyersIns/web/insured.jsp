
<html>
<head>
	<title>Index</title>
		<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>
<%
	session.setAttribute("alsid",request.getParameter("alsid"));
	

%>
    <form name ="test" action="insured.do" method="post">
    <input type="hidden" name="inet_method" value="newApp"/>
    <input type="hidden" name="inet_page" value="indexinsured"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|MarketingId|role_id|comingFrom|SubProducer|SubProducerLogoDisplay|"/>
    <input type="hidden" name="dynaValues" value="|Y|2|3|1|<%=request.getParameter("MarketingId")==null?"MSPACE":request.getParameter("MarketingId")%>|1|indexinsuredNewApp|<%=request.getParameter("SubProducer")==null?"SPACE":request.getParameter("SubProducer")%>|Y|"/>
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

