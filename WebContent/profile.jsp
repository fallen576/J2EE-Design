<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null || !user.isAdmin()) {
		response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
	}
%>
<jsp:include page="/header.jsp" />
<div class="container padding">
		<div class="jumbotron" id="closeMe">
			<h1 class="display-4">Account Information</h1>
			<p class="lead">You will need to logout in order for your changes to take affect</p>
			<hr class="my-4">
			<form action="account" method="Post">
			 	<div class="form-row">
    				<div class="form-group col-md-2">
      					<label for="firstName">First Name</label>
      					<input type="text" class="form-control" id="firstName" value="${user.firstName}" name="firstName" required>
    				</div>
    				<div class="form-group col-md-4">
      					<label for="lastName">Last Name</label>
      					<input type="text" class="form-control" id="lastName" value="${user.lastName}" name="lastName" required>
    				</div>
    				<div class="form-group col-md-6">
      					<label for="email">Email</label>
      					<input type="email" class="form-control" id="email" value="${user.emailAddress}" name="email" required>
    				</div>
    			</div>
    			<div class="button padT">
		    			<button class="btn btn-success btn-lg rounded">Update Account</button>
		    			<input type="hidden" name="createAccount" value="update"/>
		    			<input type="hidden" name="id" value="${user.id}"> 
		    		</div>
			</form>
		</div>
	</div>
</body>
</html>