	 <jsp:include page="/header.jsp"/>
 <%
 	session.setAttribute("carColor", null);
 %>
	<div class="container padding">  
		<!-- <h1 class="text-center color padding">Our Wheels</h1>-->
		
		<div class="jumbotron" id="closeMe"> 
			<h1 class="display-4">Welcome to Deals on Wheels</h1>
			<p class="lead">Let us know a little about your trip</p>
			<hr class="my-4">
			<form action="rental" method="Get">
				<div class="container">
					<div class="form-group padT">
					    <label for="locations"><h5>Locations</h5></label>
					    <select class="form-control" id="locations" name="location" required>
					      <option>Maryland</option>
					      <option>Virginia</option>
					      <option>West Virginia</option>
					      <option>Pennsylvania</option>
					      <option>Delaware</option>
					    </select>
					</div>
					<div class="form-group padT">
						<label for="dateTime"><h5>When would you like to pick up your car?</h5></label>
					    <input class="form-control" type="datetime-local" value="2020-01-01T09:00:01" id="dateTime" name="pickup">
					</div>
					<div class="form-group padT">
						<label for="dateTime"><h5>When would you like to return your car?</h5></label>
					    <input class="form-control" type="datetime-local" value="2020-01-02T09:00:01" id="dateTime" name="dropoff">
					</div>
					<div class="form-group padT">
					    <label for="numDrivers"><h5>How many people will be driving?</h5></label>
					    <select class="form-control" id="numDrivers" required>
					      <option>1</option>
					      <option>2</option>
					      <option>3+</option>
					    </select>
					</div>
					<div class="form-group padT">
					    <label for="numDrivers"><h5>What type of car would you like?</h5></label>
					    <select class="form-control" id="vehicleCategory" name="vehicleCategory" required>
					      <option value="ECONOMY">Economy</option>
					      <option value="COMPACT">Compact</option>
					      <option value="INTERMEDIATE">Intermediate</option>
					      <option value="STANDARD">Standard</option>
					      <option value="FULL_SIZE">Full Size</option>
					      <option value="PREMIUM">Premium</option>
					      <option value="LUXURY">Luxury</option>
					    </select>
					</div>
					<div class="button padT">
		    			<button class="btn btn-success btn-lg rounded">Find Cars</button>
		    			<input type="hidden" name="filter" value="index"/>
		    		</div>
				</div>
			</form>
			<p class="lead padT">Feel free to check out our frequently asked questions over at our Contact Us page.</p>
			<p class="lead">
				<a class="btn btn-warning rounded btn-med" href="" role="button">Learn more</a>
			</p>
		</div>
	</div>
</body>

</html>   