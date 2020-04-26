<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<%
	//get session variables here
	List<Vehicle> vehicles = (List<Vehicle>) session.getAttribute("vehicles");
	String[] vehicleCategory = (String[]) session.getAttribute("vehicleCategory");
	String[] carColor = (String[]) session.getAttribute("carColor");
	if (carColor == null) {
		carColor = new String[1];
	}
%>
<jsp:include page="header.jsp"/>
	<div class="row border-secondary rounded bg-light">
		<div class="container">
		     <div class="row">
		     	<div class="col-xs-1">
		     		<div class="card">
		     		<form action="rental" method="Get">
				<article class="card-group-item">
					<header class="card-header">
						<h6>Vehicle Type</h6>
					</header>
					<div class="filter-content">
						<div class="card-body">
							<label class="form-check">
							<% if (vehicleCategory != null && Arrays.asList(vehicleCategory).contains("COMPACT")) {%>
							  <input class="form-check-input" name="vehicleCategory" type="checkbox" value="COMPACT" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="vehicleCategory" type="checkbox" value="COMPACT">
						    	<%} %>
							  <span class="form-check-label">
							   Compact
							  </span>
							</label> <!-- form-check.// -->
							<label class="form-check">
							  <% if (vehicleCategory != null && Arrays.asList(vehicleCategory).contains("ECONOMY")) {%>
							  <input class="form-check-input" name="vehicleCategory"  type="checkbox" value="ECONOMY" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="vehicleCategory" type="checkbox" value="ECONOMY">
						    	<%} %>
							  <span class="form-check-label">
							    Economy
							  </span>
							</label>  <!-- form-check.// -->
							<label class="form-check">
							  <% if (vehicleCategory != null && Arrays.asList(vehicleCategory).contains("INTERMEDIATE")) {%>
							  <input class="form-check-input" name="vehicleCategory" type="checkbox" value="INTERMEDIATE" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="vehicleCategory" type="checkbox" value="INTERMEDIATE">
						    	<%} %>
							  <span class="form-check-label">
							    Intermediate
							  </span>
							</label>  <!-- form-check.// -->
							<label class="form-check">
							  <% if (vehicleCategory != null && Arrays.asList(vehicleCategory).contains("LUXURY")) {%>
							  <input class="form-check-input" name="vehicleCategory" type="checkbox" value="LUXURY" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="vehicleCategory" type="checkbox" value="LUXURY">
						    	<%} %>
							  <span class="form-check-label">
							   Luxury
							  </span>
							</label> <!-- form-check.// -->
							<label class="form-check">
							 <% if (vehicleCategory != null && Arrays.asList(vehicleCategory).contains("STANDARD")) {%>
							  <input class="form-check-input" name="vehicleCategory" type="checkbox" value="STANDARD" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="vehicleCategory" type="checkbox" value="STANDARD">
						    	<%} %>
							  <span class="form-check-label">
							    Standard
							  </span> 
							</label>  <!-- form-check.// -->		
							<label class="form-check">
							 <% if (vehicleCategory != null && Arrays.asList(vehicleCategory).contains("FULL_SIZE")) {%>
							  <input class="form-check-input" name="vehicleCategory" type="checkbox" value="FULL_SIZE" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="vehicleCategory" type="checkbox" value="FULL_SIZE">
						    	<%} %>
							  <span class="form-check-label">
							    Full Size
							  </span>
							</label>  <!-- form-check.// -->	
							<label class="form-check">
							 <% if (vehicleCategory != null && Arrays.asList(vehicleCategory).contains("PREMIUM")) {%>
							  <input class="form-check-input" name="vehicleCategory" type="checkbox" value="PREMIUM" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="vehicleCategory" type="checkbox" value="PREMIUM">
						    	<%} %>
							  <span class="form-check-label">
							    Premium
							  </span>
							</label>  <!-- form-check.// -->		
						</div> <!-- card-body.// -->
					</div>
				</article> <!-- card-group-item.// -->
				<article class="card-group-item">
					<header class="card-header">
						<h6>Vehicle Color</h6>
					</header>
					<div class="filter-content">
						<div class="card-body">
							<label class="form-check">
							<% if (carColor != null && Arrays.asList(carColor).contains("Blue")) {%>
							  <input class="form-check-input" name="carColor" type="checkbox" value="Blue" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="carColor" type="checkbox" value="Blue">
						    	<%} %>
							  <span class="form-check-label">
							   Blue
							  </span>
							</label> <!-- form-check.// -->
							<label class="form-check">
							  <% if (carColor != null && Arrays.asList(carColor).contains("Silver")) {%>
							  <input class="form-check-input" name="carColor"  type="checkbox" value="Silver" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="carColor" type="checkbox" value="Silver">
						    	<%} %>
							  <span class="form-check-label">
							    Silver
							  </span>
							</label>  <!-- form-check.// -->
							<label class="form-check">
							  <% if (carColor != null && Arrays.asList(carColor).contains("Green")) {%>
							  <input class="form-check-input" name="carColor" type="checkbox" value="Green" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="carColor" type="checkbox" value="Green">
						    	<%} %>
							  <span class="form-check-label">
							    Green
							  </span>
							</label>  <!-- form-check.// -->
							<label class="form-check">
							  <% if (carColor != null && Arrays.asList(carColor).contains("Red")) {%>
							  <input class="form-check-input" name="carColor" type="checkbox" value="Red" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="carColor" type="checkbox" value="Red">
						    	<%} %>
							  <span class="form-check-label">
							   Red
							  </span>
							</label> <!-- form-check.// -->
							<label class="form-check">
							 <% if (carColor != null && Arrays.asList(carColor).contains("Black")) {%>
							  <input class="form-check-input" name="carColor" type="checkbox" value="Black" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="carColor" type="checkbox" value="Black">
						    	<%} %>
							  <span class="form-check-label">
							    Black
							  </span>
							</label>  <!-- form-check.// -->
				<header class="card">
								<button class="btn btn-success">Search</button>
								<input type="hidden" name="filter" value="panel"/>
							</header>
							</div>
							</div>
							</article>
				</form>
			</div> <!-- card.// -->   
		     	</div>
		     	<div class="col-lg">
		     		<div class="row">
		     		
		     		<%
		     			if (vehicles != null) {
		     				for (int i = 0; i < vehicles.size(); i++) {
		     					Vehicle v = vehicles.get(i);
		     					String path = v.getImg();
		     					String make = v.getMake();
		     					String model = v.getModel();
		     					long id = v.getId();
		     		%>
		     				<div class="col-md-2 col-md-4">
						       <div class="product"><img src="<%= path %>" alt="">
						           <h6><%= make + " " + model%></h6>  
						             <span>
						             	<form method="POST">  
						             		<input type="hidden" name="carToSelect" value="<%= id%>">
						             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
						             	</form>
						             </span>
						       </div>
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