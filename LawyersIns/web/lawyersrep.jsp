
<html>
<head>
	<title>Index</title>
<script language="javascript" src="js/jquery.js"/>
	
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>
<%
	session.setAttribute("alsid",request.getParameter("alsid"));
	

%>
    <form name ="test" action="lawyersrep.do;jsessionid=<%= request.getParameter("jsessionid") %>" method="post">
    <input type="hidden" name="inet_method" value="show"/>
    <input type="hidden" name="inet_page" value="lawyersrep"/>
    <input type="hidden" name="dynaKeys" value="|AgentEmail|User|role_id|UserLoginKey|AccountEmail|"/>
    <input type="hidden" name="dynaValues" value="|<%=request.getParameter("AccountEmail")==null?"SPACE":request.getParameter("AccountEmail")%>|<%=request.getParameter("User")==null?"SPACE":request.getParameter("User")%>|<%=request.getParameter("role_id")==null?"SPACE":request.getParameter("role_id")%>|<%=request.getParameter("UserLoginKey")==null?"SPACE":request.getParameter("UserLoginKey")%>|<%=request.getParameter("AccountEmail")==null?"SPACE":request.getParameter("AccountEmail")%>|"/>
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

