<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null || !user.isAdmin()) {
		response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
	}
	List<Vehicle> vehicles = (List<Vehicle>) session.getAttribute("allVehicles");
%>
<jsp:include page="header.jsp" />
	<div class="admin row border-secondary rounded bg-light">
		<div class="container">
		     <div class="vehicles">
     			<%
     			if (vehicles != null) {
     				for (int i = 0; i < vehicles.size(); i++) {
     					Vehicle v = vehicles.get(i);
     					String path = v.getImg();
     					String make = v.getMake();
     					String model = v.getModel();
     					String color = v.getColor();
     					VehicleCategory category = v.getCategory();
     					long id = v.getId();
     					%>
				        <div class="vehicle">
				           <div class="header-img">
					       	   <div class="header">
				       	   		 	<span>
						             	<form action="/admin" method="DELETE">
						             		<input type="hidden" name="vehicleDeleteId" value="${id}">
						             		<input type="submit" value="Delete" class="btn btn-sm btn-danger"> 
						             	</form>
						            </span>
					       	   </div>
					       	   <img src="<%= path %>" alt="">
				       	   </div>
					       <form action="admin" method="PUT">
					       		<div>
					       			<div class="table">
						       			<div class="row">
							       			<label for="id">Id:</label>
					      					<input type="text" class="form-control" name="id" value="<%= id %>" required disabled>
							       		</div>
									 	<div class="row">
					      					<label for="make">Make:</label>
					      					<input type="text" class="form-control" name="make" value="<%= make %>" required>
					    				</div>
									 	<div class="row">
					      					<label for="model">Model:</label>
					      					<input type="text" class="form-control" name="model" value="<%= model %>" required>
					    				</div>
						       		</div>
						       		<div class="table">
						       			<div class="row">
						       				<label for="category">Category:</label>
						       				<select class="form-control" name="category">
						       					<option value="ECONOMY" <%= category.name() == "ECONOMY" ? "selected" : "" %>>Economy</option>
						       					<option value="COMPACT" <%= category.name() == "COMPACT" ? "selected" : "" %>>Compact</option>
						       					<option value="INTERMEDIATE" <%= category.name() == "INTERMEDIATE" ? "selected" : "" %>>Intermediate</option>
						       					<option value="STANDARD" <%= category.name() == "STANDARD" ? "selected" : "" %>>Standard</option>
						       					<option value="FULL_SIZE" <%= category.name() == "FULL_SIZE" ? "selected" : "" %>>Full Size</option>
						       					<option value="PREMIUM" <%= category.name() == "PREMIUM" ? "selected" : "" %>>Premium</option>
						       					<option value="LUXURY" <%= category.name() == "LUXURY" ? "selected" : "" %>>Luxury</option>
						       				</select>
						       			</div>
						       			<div class="row">
					      					<label for="color">Color:</label>
					      					<input type="text" class="form-control" name="color" value="<%= color %>" required>
					    				</div>
						       		</div>
					       		</div>
					       		<div class="save-btn">
					       			<button class="btn btn-success btn-sm rounded">Save Changes</button>
					       		</div>
						   </form>
		     			</div>
    				<%
     				}
     			}
     			%>
     		</div>
 		  </div>
	  </div>
	</body>
</html>