
<html>
<head>
	<title>Index</title>	
		<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>
<%
	session.setAttribute("alsid",request.getParameter("alsid"));
%>
    <form name ="test" action="indicationInsured.do" method="post">
    <input type="hidden" name="inet_method" value="showindication"/>
    <input type="hidden" name="inet_page" value="indicationInsured"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|StatusKey|User|role_id|CreatedType|IsInsuredViewed|ProducerCode|PolicyKey|TransactionSequence|CoverageSequence|RatingTraceSequence|AccountID|AccountKey|AddressKey|VersionSequence|"/>
    <input type="hidden" name="dynaValues" value="|Y|1|2|1|1|insured|1|insured|N|<%=request.getParameter("ProducerCode")==null?"SPACE":request.getParameter("ProducerCode")%>|<%=request.getParameter("PolicyKey")==null?"SPACE":request.getParameter("PolicyKey")%>|<%=request.getParameter("TransactionSequence")==null?"SPACE":request.getParameter("TransactionSequence")%>|<%=request.getParameter("CoverageSequence")==null?"SPACE":request.getParameter("CoverageSequence")%>|<%=request.getParameter("RatingTraceSequence")==null?"SPACE":request.getParameter("RatingTraceSequence")%>|<%=request.getParameter("AccountID")==null?"SPACE":request.getParameter("AccountID")%>|<%=request.getParameter("AccountKey")==null?"SPACE":request.getParameter("AccountKey")%>|<%=request.getParameter("AddressKey")==null?"SPACE":request.getParameter("AddressKey")%>|<%=request.getParameter("VersionSequence")==null?"SPACE":request.getParameter("VersionSequence")%>|"/>
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

