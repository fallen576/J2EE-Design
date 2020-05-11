<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null || !user.isAdmin()) {
		response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
	}
	List<Vehicle> vehicles = (List<Vehicle>) session.getAttribute("allVehicles");
%>
<script type="text/javascript">
	function encodeBase64Image(vehicleId) {
		var fileInput = document.getElementById('image-' + vehicleId);
		var value = fileInput.value;
		if (!isAcceptedFileExtension(value)) {
			document.getElementById('image-' + vehicleId).value = null;
			alert('Only jpg, jpeg, or png file types permited');
		} else if (value !== "") {
			var reader = new FileReader();
			reader.onload = function(event) {
			    var base64String = reader.result.split(',')[1];
			    document.getElementById('image-hidden-' + vehicleId).value = base64String;
			};
			reader.readAsDataURL(fileInput.files[0]);
		}		
	}
	function encodeNewVehicleImage() {
		var fileInput = document.getElementById('new-vehicle-img');
		var value = fileInput.value;
		if (!isAcceptedFileExtension(value)) {
			document.getElementById('new-vehicle-img').value = null;
			alert('Only jpg, jpeg, or png file types permited');
		} else if (value !== "") {
			var reader = new FileReader();
			reader.onload = function(event) {
			    var base64String = reader.result.split(',')[1];
			    document.getElementById('new-vehicle-img-hidden').value = base64String;
			};
			reader.readAsDataURL(fileInput.files[0]);
		}		
	}
	function isAcceptedFileExtension(value) {
		var extension = value.match(/\.([^\.]+)$/)[1];
		return extension === 'jpg' || extension === 'jpeg' || extension === 'png';
	}
</script>
<jsp:include page="header.jsp" />
	<div class="admin row border-secondary rounded bg-light">
		<div class="container">
			 <div class="new-vehicle-btn">
			 	<button type="button" class="btn btn-success" data-toggle="modal" data-target="#newVehicleModal">Create Vehicle</button>
			 </div>
		     <div class="vehicles">
     			<%
     			if (vehicles != null) {
     				for (int i = 0; i < vehicles.size(); i++) {
     					Vehicle v = vehicles.get(i);
     					String base64Img = v.getBase64Img();
     					String make = v.getMake();
     					String model = v.getModel();
     					String color = v.getColor();
     					VehicleCategory category = v.getCategory();
     					long vehicleId = v.getId();
     					%>
				        <div class="vehicle">
				           <div class="header-img">
					       	   <div class="header">
				       	   		 	<span>
						             	<form action="admin" method="POST">
						             		<input type="hidden" name="method" value="DELETE" />
						             		<input type="hidden" name="vehicleDeleteId" value="<%= vehicleId %>">
						             		<input type="submit" value="Delete" class="btn btn-sm btn-danger"> 
						             	</form>
						            </span>
					       	   </div>
					       	   <img src="data:image/png;base64,<%= base64Img %>" alt="">
				       	   </div>
					       <form action="admin" method="POST">
					       		<div>
					       			<div class="table">
						       			<div class="row">
					       					<input type="hidden" name="method" value="PUT" />
					       					<input type="hidden" name="id" value="<%= vehicleId %>" />
							       			<label for="id">Id:</label>
					      					<input type="text" class="form-control" name="id" value="<%= vehicleId %>" required disabled>
							       		</div>
									 	<div class="row">
					      					<label for="make">Make:</label>
					      					<input type="text" class="form-control" name="make" value="<%= make %>" required>
					    				</div>
									 	<div class="row">
					      					<label for="model">Model:</label>
					      					<input type="text" class="form-control" name="model" value="<%= model %>" required>
					    				</div>
					    				<div class="row">
					    					<label for="costPerDay">Cost Per Day:</label>
					    					<input type="number" class="form-control" name="costPerDay" step="0.01"
					    						min="0" max="999.99" value="<%= v.getCostPerDay() %>" required />
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
					    				<div class="row">
					    					<label for="image">Image (png, jpeg, jpg):</label>
					      					<input type="file" accept="jpg, jpeg, png" class="form-control" id="image-<%= vehicleId %>" onchange="encodeBase64Image(<%= vehicleId %>)">
					      					<input type="hidden" name="base64Img" id="image-hidden-<%= vehicleId %>" value="<%= base64Img %>" />
					    				</div>
							       		<div class="row save-btn">
							       			<label></label>
							       			<button class="btn btn-success btn-sm rounded">Save Changes</button>
							       		</div>
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
	  	<div id="newVehicleModal" class="modal fade" role="dialog" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">Create Vehicle</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		      <div class="modal-body">
		        <form action="admin" method="POST">
		       		<div>
		       			<div class="table">
						 	<div class="row">
		      					<label for="make">Make:</label>
		      					<input type="text" class="form-control" name="make" required>
		    				</div>
						 	<div class="row">
		      					<label for="model">Model:</label>
		      					<input type="text" class="form-control" name="model" required>
		    				</div>
		    				<div class="row">
		    					<label for="costPerDay">Cost Per Day:</label>
		    					<input type="number" class="form-control" name="costPerDay" step="0.01"
		    						min="0" max="999.99" required />
		    				</div>
			       			<div class="row">
			       				<label for="category">Category:</label>
			       				<select class="form-control" name="category">
			       					<option value="ECONOMY" selected>Economy</option>
			       					<option value="COMPACT">Compact</option>
			       					<option value="INTERMEDIATE">Intermediate</option>
			       					<option value="STANDARD">Standard</option>
			       					<option value="FULL_SIZE">Full Size</option>
			       					<option value="PREMIUM">Premium</option>
			       					<option value="LUXURY">Luxury</option>
			       				</select>
			       			</div>
			       			<div class="row">
		      					<label for="color">Color:</label>
		      					<input type="text" class="form-control" name="color" required>
		    				</div>
		    				<div class="row">
		    					<label for="image">Image:</label>
		      					<input type="file" class="form-control" id="new-vehicle-img" onchange="encodeNewVehicleImage()">
		      					<input type="hidden" name="base64Img" id="new-vehicle-img-hidden" />
		    				</div>
				       		<div class="row save-btn">
				       			<button class="btn btn-success btn-sm rounded">Create Vehicle</button>
				       		</div>
			       		</div>
		       		</div>
				 </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
	  </div>
	</body>
</html>