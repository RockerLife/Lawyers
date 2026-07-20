
<%@page import="java.util.Enumeration"%>
<html>
<head>
	<title>Index</title>
		<script type="text/javascript" src="js/jquery.js"></script>
		
	
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>

    <form name ="test" action="payment.do;jsessionid=<%= request.getParameter("jsessionid") %>" method="post">
    <input type="hidden" name="inet_method" value="showpayment"/>
    <input type="hidden" name="inet_page" value="showpayment"/>
    <input type="hidden" name="dynaKeys" value="|inet_skip_validation|MarketableProductKey|CoverageKey|AddressTypeKey|SignedDocumentID|SignedDocumentToken|SignatoryID|SignedAuthToken|OrderNumber|DocumentStatus|"/>
    <input type="hidden" name="dynaValues" value="|Y|2|3|1|<%=request.getParameter("i")%>|<%=request.getParameter("a")%>|<%=request.getParameter("s")%>|<%=request.getParameter("sa")%>|<%=request.getParameter("o")%>|<%=request.getParameter("status")%>|"/>
    <input type="hidden" name="inet_context" value=""/>
    
    
    </form>
    
</body>       <script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script>
<Script language="javascript">

forward_page();	
	
	
	function forward_page()
	{
		//alert(document.forms[0].action);
		//alert(document.forms[0].action);
		document.forms[0].submit();		
	}
	

	</Script>

</html>

