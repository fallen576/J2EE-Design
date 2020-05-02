<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null || !user.isAdmin()) {
		response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
	}
%>
<jsp:include page="/header.jsp" />

</body>
</html>