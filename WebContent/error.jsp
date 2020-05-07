<%
	//get session variables here
	String errorMessage = (String) session.getAttribute("errorMessage");
	boolean hasErrorMessage = errorMessage != null && errorMessage.trim().length() > 0;
%>
<jsp:include page="/header.jsp"/>
	<div class="container padding">  
		<div class="jumbotron" id="closeMe"> 
			<h1 class="display-4">Whoops! Sorry about that</h1>
			<% if (hasErrorMessage) {%>
			  	<p class="lead"><%= errorMessage %></p>
		    <% } else { %>
		    	<p class="lead">We ran into a small issue. Please give that another go</p>
	    	<%} %>
			
		</div>
	</div>
</body>
</html>