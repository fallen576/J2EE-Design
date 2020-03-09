<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="/J2EE-Design/styles/main.css" type="text/css">
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Deals On Wheels</title>
</head>

<body>
	<nav class="navbar navbar-expand-sm bg-light navbar-dark fixed-top">
		<div class="navbar-collapse collapse w-100 order-3 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<a class="nav-link text-dark  rounded" href="">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-dark  rounded" href="">Reviews</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-dark  rounded" href="">Profile</a> 
				</li>
			</ul> 
		</div>
		<div class="navbar-collapse collapse w-100 order-3 order-md-0 dual-collapse2">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item pad">
					<form class="form-inline" action="" method="POST">
			    		<input class="form-control mr-sm-3 navBar" type="text" placeholder="username">
			    		<input class="form-control mr-sm-3" type="password" placeholder="password" data-toggle="password">
			    		<button class="btn btn-success" type="submit">Login</button>
			 		 </form>
		 		 </li>
	 		 </ul>
 		 </div> 
	</nav> 
	<div class="container padding">  
		<!-- <h1 class="text-center color padding">Our Wheels</h1>-->
		
		<div class="jumbotron" id="closeMe"> 
			<h1 class="display-4">Welcome to Deals on Wheels</h1>
			<p class="lead">Let us know a little about your trip</p>
			<hr class="my-4">
			<form action="" method="POST">
				<div class="container">
					<h5>Contact Information</h5>
			    	<div class="input-group">
						<div class="input-group-prepend">
					    	<span class="input-group-text" id="">First and last name</span>
						</div>
					  	<input type="text" class="form-control" required>
					  	<input type="text" class="form-control" required>
					</div>
					<div class="form-group padT">
					    <label for="locations"><h5>Locations</h5></label>
					    <select class="form-control" id="locations" required>
					      <option>Maryland</option>
					      <option>Virginia</option>
					      <option>West Virginia</option>
					      <option>Pennsylvania</option>
					      <option>Delaware</option>
					    </select>
					</div>
					<div class="form-group padT">
						<label for="dateTime"><h5>When would you like to pick up your car?</h5></label>
					    <input class="form-control" type="datetime-local" value="2020-01-01T09:00:00" id="dateTime">
					</div>
					<div class="form-group padT">
						<label for="dateTime"><h5>When would you like to return your car?</h5></label>
					    <input class="form-control" type="datetime-local" value="2020-01-01T09:00:00" id="dateTime">
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
					    <select class="form-control" id="numDrivers" required>
					      <option>Compact</option>
					      <option>SUV</option>
					      <option>Sedan</option>
					      <option>Luxury</option>
					      <option>Sports</option>
					    </select>
					</div>
					<div class="button padT">
		    			<button class="btn btn-success btn-lg rounded">Find Cars</button>
		    		</div>
				</div>
			</form>
			<p class="lead padT">Feel free to check out our frequently asked questions over at our Contact Us page.</p>
			<p class="lead">
				<a class="btn btn-warning rounded btn-med" href="" role="button">Learn more</a>
			</p>
		</div>
		<!--
		<div class="row border-secondary rounded bg-light">
		<div class="container">
		   <div class="row">
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
		           <h6>Small Boi</h6>  
		             <span>
		             	<form method="POST">  
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Rent Me!" class="btn btn-success"> 
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product"><img src="/J2EE-Design/images/car0.jpeg" alt="">
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
		 </div>
		 </div>
		 -->
	</div>
</body>

</html>     