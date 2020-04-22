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
					<a class="nav-link text-dark  rounded" href="/J2EE-Design/index.jsp">Home</a>
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
		 		 <li class="nav-item pad" style="padding-left:10px;">
					<form class="form-inline" action="account" method="POST">
						<input name="createAccount" value="true" type="hidden"/>
			    		<button class="btn btn-success" type="submit">Create Account</button>
			 		 </form>
		 		 </li>
	 		 </ul>
 		 </div> 
	</nav> 