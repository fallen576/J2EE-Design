<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null || !user.isAdmin()) {
		response.sendRedirect(request.getContextPath() + "/authorization.jsp");
	}
	List<Reservation> reservation = (List<Reservation>) session.getAttribute("reservations");
%>
<jsp:include page="/header.jsp" />
	<div class="admin row border-secondary rounded bg-light">
		<div class="container">
		     <div class="vehicles">
     			<%
     			if (reservation != null) {
     				for (int i = 0; i < reservation.size(); i++) {
     					boolean readOnly = false;
     					Reservation r = reservation.get(i);
     					Vehicle v = r.getVehicle();
     					VehicleCategory category = v.getCategory();
     					String confirmationNumber = r.getConfirmationNumber();
     					String pickupLocation = r.getPickupLocation();
     					String dropoffLocation = r.getDropoffLocation();
     					Date pickupDate = r.getPickupDate();
     					Date dropoffDate = r.getDropoffDate();
     					
     					if (pickupDate.after(new Date())) {
     						readOnly = true;
     					}
     					%>
				        <div class="vehicle">
				           <div class="header-img">
					       	   <img src="data:image/png;base64,<%= v.getBase64Img() %>" alt="">
				       	   </div>
					       <form action="reservation" method="POST">
					       		<div>
					       			<div class="table">
						       			<div class="row">
							       			<label for="confirmationNumber">Confirmation Number:</label>
					      					<input type="text" class="form-control" name="confirmationNumber" value="<%= confirmationNumber %>" disabled>
							       		</div>
									 	<div class="row">
					      					<label for="pickupLocation">Pickup Location:</label>
					      					<input type="text" class="form-control" name="pickupLocation" value="<%= pickupLocation %>" <%= !readOnly ? "disabled" : "" %>>
					    				</div>
									 	<div class="row">
					      					<label for="dropoffLocation">Drop off Location:</label>
					      					<input type="text" class="form-control" name="model" value="<%= dropoffLocation %>" <%= !readOnly ? "disabled" : "" %>>
					    				</div>
						       		</div>
						       		<div class="table">
						       			<div class="row">
						       				<label for="category">Category:</label>
						       				<select class="form-control" name="category" disabled>
						       					<option value="ECONOMY" <%= category.name() == "ECONOMY" ? "selected" : "" %>>Economy</option>
						       					<option value="COMPACT" <%= category.name() == "COMPACT" ? "selected" : "" %>>Compact</option>
						       					<option value="INTERMEDIATE"  <%= category.name() == "INTERMEDIATE" ? "selected" : "" %>>Intermediate</option>
						       					<option value="STANDARD"  <%= category.name() == "STANDARD" ? "selected" : "" %>>Standard</option>
						       					<option value="FULL_SIZE"  <%= category.name() == "FULL_SIZE" ? "selected" : "" %>>Full Size</option>
						       					<option value="PREMIUM"  <%= category.name() == "PREMIUM" ? "selected" : "" %>>Premium</option>
						       					<option value="LUXURY"  <%= category.name() == "LUXURY" ? "selected" : "" %>>Luxury</option>
						       				</select>
						       			</div>
						       			<div class="row">
					      					<label for="pickupDate">Pickup Date:</label>
					      					<input type="text" class="form-control" name="pickupDate" value="<%= pickupDate %>" <%= !readOnly ? "disabled" : "" %>>
					    				</div>
					    				<div class="row">
					      					<label for="dropoffDate">Drop off Date:</label>
					      					<input type="text" class="form-control" name="dropoffDate" value="<%= dropoffDate %>" <%= !readOnly ? "disabled" : "" %>>
					    				</div>
					    				<% if (readOnly) { %>
							       		<div class="row save-btn">
							       			<input type="hidden" name="reservations" value="update">
							       			<button class="btn btn-success btn-sm rounded">Update Changes</button>
							       		</div>
							       		<%} %>
						       		</div>
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
</html>