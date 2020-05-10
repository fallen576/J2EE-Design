<%
	String message = (String) session.getAttribute("errorMessage");
%>
<jsp:include page="/header.jsp"/>
	<div class="container padding">  
		<div class="jumbotron" id="closeMe"> 
			<p class="display-4">Sorry about that</p>
			<p class="lead"><%= message == null ? "" : message%></p>
		</div>
	</div>
</body>
</html>