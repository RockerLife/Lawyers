<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
	<title>Index</title>
    	<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" id="hs-script-loader" async defer src="//js.hs-scripts.com/3334505.js"></script> </head>

<BODY>


    <form name ="test" action="manageindex.do" method="post">
            
    <input type="hidden" name="inet_method" value="INET_SHOW_TESTHARNESS_PAGE"/>
    <input type="hidden" name="inet_page" value="MANAGE_TESTHARNESS"/>
    <input type="hidden" name="dynaKeys" value="u|r|u_n|e|roles"/>
    <input type="hidden" name="dynaValues" value="<c:if test="${empty param.u}">null</c:if><c:if test="${!empty param.u}"><c:out value="${param.u}"/></c:if>|<c:if test="${empty param.r}">-1</c:if><c:if test="${!empty param.r}"><c:out value="${param.r}"/></c:if>|<c:if test="${empty param.u_n}">null</c:if><c:if test="${!empty param.u_n}"><c:out value="${param.u_n}"/></c:if>|<c:if test="${empty param.e}">null</c:if><c:if test="${!empty param.e}"><c:out value="${param.e}"/></c:if>|-1"/>
    <input type="hidden" name="sessionInit" value="<%= (request.getParameter("sessionInit") != null && !request.getParameter("sessionInit").equals("")) ? request.getParameter("sessionInit") : null %>"/>
    <input type="hidden" name="rescr_id" value="<%= (request.getParameter("rescr_id") != null && !request.getParameter("rescr_id").equals("")) ? request.getParameter("rescr_id") : null %>"/>
    
    <%-- <input type="hidden" name="dynaValues" value="<c:if test="${empty param.user_id}">null</c:if><c:if test="${!empty param.user_id}"><c:out value="${param.user_id}"/></c:if>|<c:if test="${empty param.roles}">null</c:if><c:if test="${!empty param.roles}"><c:out value="${param.roles}"/></c:if>|<c:if test="${empty param.user_name}">null</c:if><c:if test="${!empty param.user_name}"><c:out value="${param.user_name}"/></c:if>"/> --%>
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

