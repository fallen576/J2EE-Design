
<jsp:include page="/header.jsp" />


	<div class="container padding">
		<div class="jumbotron" id="closeMe">
			<h1 class="display-4">Account Information</h1>
			<hr class="my-4">
			<form action="account" method="Post">
			 	<div class="form-row">
    				<div class="form-group col-md-6">
      					<label for="name">Name</label>
      					<input type="text" class="form-control" id="name" placeholder="John Doe" required>
    				</div>
    				<div class="form-group col-md-6">
      					<label for="email">Email</label>
      					<input type="email" class="form-control" id="email" placeholder="JohnDoe@gmail.com" required>
    				</div>
    			</div>
    			<div class="form-group">
    				<label for="password">Password</label>
      				<input type="password" class="form-control" id="password" placeholder="" required>
    			</div>
    			<div class="form-group">
    				<label for="address">Address</label>
      				<input type="text" class="form-control" id="address" placeholder="123 L St" required>
    			</div>
    			<div class="form-group">
    				<label for="address2">Address 2</label>
      				<input type="text" class="form-control" id="address2" placeholder="Apartment, studio, suite, floor" required>
    			</div>
				<div class="form-row">
    				<div class="form-group col-md-6">
      					<label for="city">City</label>
      					<input type="text" class="form-control" id="city" placeholder="" required>
					</div>
					<div class="form-group col-md-4">
					 <label for="state">State</label>
					    <select class="form-control" id="state" name="state" required>
					      <option>Maryland</option>
					      <option>Virginia</option>
					      <option>West Virginia</option>
					      <option>Pennsylvania</option>
					      <option>Delaware</option>
					    </select>
					</div>
    				<div class="form-group col-md-2">
      					<label for="zip">ZIP</label>
      					<input type="number" class="form-control" id="zip" placeholder="" required max="99999">
    				</div>
    			</div>
    			<div class="button padT">
		    			<button class="btn btn-success btn-lg rounded">Create Account</button>
		    			<input type="hidden" name="createAccount" value="done"/>
		    		</div>
			</form>
		</div>
	</div>
</body>
</html>