<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="/J2EE-Design/styles/main.css" type="text/css">
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Car Rental</title>
</head>

<body> 
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="navbar-collapse collapse w-100 order-3 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<a class="nav-link" href="">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="">Cars</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="">Contact Us</a> 
				</li>
			</ul>
		</div>
		<div class="navbar-collapse  collapse w-100 order-3 dual-collapse2">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<form class="form-inline" action="/action_page.php" method="POST">
			    		<input class="form-control mr-sm-3 navBar" type="text" placeholder="username">
			    		<input class="form-control mr-sm-3" type="password" placeholder="password" data-toggle="password">
			    		<button class="btn btn-success" type="submit">Login</button>
			 		 </form>
		 		 </li>
	 		 </ul>
 		 </div>
	</nav> 
	<!-- <div class="background">-->
		<div class="container bootstrap snipets">
		   <h1 class="text-center color">Our Small Boi Collection</h1>
		   <div class="row flow-offset-1">
		     <div class="col-xs-6 col-md-4">
		       <div class="product tumbnail thumbnail-3"><a href="#"><img src="/J2EE-Design/images/car0.jpeg" alt=""></a>
		           <h6><a href="#">Small Boi</a></h6>
		             <span>
		             	<form method="POST">
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Remove" class="btn btn-success">
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product tumbnail thumbnail-3"><a href="#"><img src="/J2EE-Design/images/car0.jpeg" alt=""></a>
		           <h6><a href="#">Small Boi</a></h6>
		             <span>
		             	<form method="POST">
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Remove" class="btn btn-success">
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product tumbnail thumbnail-3"><a href="#"><img src="/J2EE-Design/images/car0.jpeg" alt=""></a>
		           <h6><a href="#">Small Boi</a></h6>
		             <span>
		             	<form method="POST">
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Remove" class="btn btn-success">
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product tumbnail thumbnail-3"><a href="#"><img src="/J2EE-Design/images/car0.jpeg" alt=""></a>
		           <h6><a href="#">Small Boi</a></h6>
		             <span>
		             	<form method="POST">
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Remove" class="btn btn-success">
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product tumbnail thumbnail-3"><a href="#"><img src="/J2EE-Design/images/car0.jpeg" alt=""></a>
		           <h6><a href="#">Small Boi</a></h6>
		             <span>
		             	<form method="POST">
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Remove" class="btn btn-success">
		             	</form>
		             </span>
		       </div>
		     </div>
		     <div class="col-xs-6 col-md-4">
		       <div class="product tumbnail thumbnail-3"><a href="#"><img src="/J2EE-Design/images/car0.jpeg" alt=""></a>
		           <h6><a href="#">Small Boi</a></h6>
		             <span>
		             	<form method="POST">
		             		<input type="hidden" name="carToSelect" value="car0>">
		             		<input type="submit" value="Remove" class="btn btn-success">
		             	</form>
		             </span>
		       </div>
		     </div>
		   </div>
		 </div>
	<!-- </div>--> 
	
</body>

</html>     