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
     					Reservation r = reservation.get(i);
     					Vehicle v = r.getVehicle();
     					VehicleCategory category = v.getCategory();
     					String confirmationNumber = r.getConfirmationNumber();
     					String pickupLocation = r.getPickupLocation();
     					String dropoffLocation = r.getDropoffLocation();
     					Date pickupDate = r.getPickupDate();
     					Date dropoffDate = r.getDropoffDate();
     					%>
				        <div class="vehicle">
				           <div class="header-img">
					       	   <img src="data:image/png;base64,<%= v.getBase64Img() %>" alt="">
				       	   </div>
					       <form action="admin" method="POST">
					       		<div>
					       			<div class="table">
						       			<div class="row">
							       			<label for="confirmationNumber">Confirmation Number:</label>
					      					<input type="text" class="form-control" name="confirmationNumber" value="<%= confirmationNumber %>" disabled>
							       		</div>
									 	<div class="row">
					      					<label for="pickupLocation">Pickup Location:</label>
					      					<input type="text" class="form-control" name="pickupLocation" value="<%= pickupLocation %>" disabled>
					    				</div>
									 	<div class="row">
					      					<label for="dropoffLocation">Drop off Location:</label>
					      					<input type="text" class="form-control" name="model" value="<%= dropoffLocation %>" disabled>
					    				</div>
						       		</div>
						       		<div class="table">
						       			<div class="row">
						       				<label for="category">Category:</label>
						       				<select class="form-control" name="category">
						       					<option value="ECONOMY" disabled <%= category.name() == "ECONOMY" ? "selected" : "" %>>Economy</option>
						       					<option value="COMPACT" disabled <%= category.name() == "COMPACT" ? "selected" : "" %>>Compact</option>
						       					<option value="INTERMEDIATE" disabled <%= category.name() == "INTERMEDIATE" ? "selected" : "" %>>Intermediate</option>
						       					<option value="STANDARD" disabled <%= category.name() == "STANDARD" ? "selected" : "" %>>Standard</option>
						       					<option value="FULL_SIZE" disabled <%= category.name() == "FULL_SIZE" ? "selected" : "" %>>Full Size</option>
						       					<option value="PREMIUM" disabled <%= category.name() == "PREMIUM" ? "selected" : "" %>>Premium</option>
						       					<option value="LUXURY" disabled <%= category.name() == "LUXURY" ? "selected" : "" %>>Luxury</option>
						       				</select>
						       			</div>
						       			<div class="row">
					      					<label for="pickupDate">Pickup Date:</label>
					      					<input type="text" class="form-control" name="pickupDate" value="<%= pickupDate %>" disabled>
					    				</div>
					    				<div class="row">
					      					<label for="dropoffDate">Drop off Date:</label>
					      					<input type="text" class="form-control" name="dropoffDate" value="<%= dropoffDate %>" disabled>
					    				</div>
					    				<!-- 
							       		<div class="row save-btn">
							       			<label></label>
							       			<button class="btn btn-success btn-sm rounded">Save Changes</button>
							       		</div>
							       		 -->
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