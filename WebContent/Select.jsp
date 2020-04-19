<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<%
	//get session variables here
	List<Vehicle> vehicles = (List<Vehicle>) session.getAttribute("vehicles");
	String[] carType = (String[]) session.getAttribute("type");
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
		     		<form action="Rental" method="Get">
				<article class="card-group-item">
					<header class="card-header">
						<h6>Vehicle Type</h6>
					</header>
					<div class="filter-content">
						<div class="card-body">
							<label class="form-check">
							<% if (carType != null && Arrays.asList(carType).contains("Compact")) {%>
							  <input class="form-check-input" name="type" type="checkbox" value="Compact" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="type" type="checkbox" value="Compact">
						    	<%} %>
							  <span class="form-check-label">
							   Compact
							  </span>
							</label> <!-- form-check.// -->
							<label class="form-check">
							  <% if (carType != null && Arrays.asList(carType).contains("SUV")) {%>
							  <input class="form-check-input" name="type"  type="checkbox" value="SUV" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="type" type="checkbox" value="SUV">
						    	<%} %>
							  <span class="form-check-label">
							    SUV
							  </span>
							</label>  <!-- form-check.// -->
							<label class="form-check">
							  <% if (carType != null && Arrays.asList(carType).contains("Sedan")) {%>
							  <input class="form-check-input" name="type" type="checkbox" value="Sedan" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="type" type="checkbox" value="Sedan">
						    	<%} %>
							  <span class="form-check-label">
							    Sedan
							  </span>
							</label>  <!-- form-check.// -->
							<label class="form-check">
							  <% if (carType != null && Arrays.asList(carType).contains("Luxury")) {%>
							  <input class="form-check-input" name="type" type="checkbox" value="Luxury" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="type" type="checkbox" value="Luxury">
						    	<%} %>
							  <span class="form-check-label">
							   Luxury
							  </span>
							</label> <!-- form-check.// -->
							<label class="form-check">
							 <% if (carType != null && Arrays.asList(carType).contains("Sports")) {%>
							  <input class="form-check-input" name="type" type="checkbox" value="Sports" checked>
						    <% } else { %>
						    	<input class="form-check-input" name="type" type="checkbox" value="Sports">
						    	<%} %>
							  <span class="form-check-label">
							    Sports
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
		     		<!-- 
		     			<div class="col-md-2 col-md-4">
					       <div class="product"><img src="/J2EE-Design/images/blueBMW.png" alt="">
					           <h6>Small Boi</h6>  
					             <span>
					             	<form method="POST">  
					             		<input type="hidden" name="carToSelect" value="car0>">
					             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
					             	</form>
					             </span>
					       </div>
		     			</div>
		     			<div class="col-md-2 col-md-4">
					       <div class="product"><img src="/J2EE-Design/images/blueWRX.png" alt="">
					           <h6>Small Boi</h6>  
					             <span>
					             	<form method="POST">  
					             		<input type="hidden" name="carToSelect" value="car0>">
					             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
					             	</form>
					             </span>
					       </div>
		     			</div>
		     			<div class="col-md-2 col-md-4">
					       <div class="product"><img src="/J2EE-Design/images/greenCivic.png" alt="">
					           <h6>Small Boi</h6>  
					             <span>
					             	<form method="POST">  
					             		<input type="hidden" name="carToSelect" value="car0>">
					             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
					             	</form>
					             </span>
					       </div>
		     			</div>
		     			<div class="col-md-2 col-md-4">
					       <div class="product"><img src="/J2EE-Design/images/silverCamry.png" alt="">
					           <h6>Small Boi</h6>  
					             <span>
					             	<form method="POST">  
					             		<input type="hidden" name="carToSelect" value="car0>">
					             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
					             	</form>
					             </span>
					       </div>
		     			</div>
		     			<div class="col-md-2 col-md-4">
					       <div class="product"><img src="/J2EE-Design/images/silverYukon.png" alt="">
					           <h6>Small Boi</h6>  
					             <span>
					             	<form method="POST">  
					             		<input type="hidden" name="carToSelect" value="car0>">
					             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
					             	</form>
					             </span>
					       </div>
		     			</div>
		     		</div>
		     		 -->
		     </div>
		   </div>
		 </div>
</body>
</html>