<%@page import="app.model.*"%>
<%@page import="java.util.*"%>
<jsp:include page="header.jsp" />

<div class="container padding">
	<div class="jumbotron" id="">
		<h1 class="display-4">Reservation Information</h1>
		<hr class="my-4">
		<div class="product">
			<img src="data:image/png;base64,${vehicle.base64Img}" alt="">
			<h6>Vehicle make and model: ${vehicle.make} ${vehicle.model}</h6>
			<h6>Cost of this vehicle per day: ${vehicle.costPerDay}</h6>
			<h6>Vehicle Code: ${vehicle.id}</h6>
			<form action="confirm" method="Post">
				<input name="modify" value="modifyCar" type="hidden" /> <input
					type="submit" value="Select New Vehicle" class="btn btn-success">
			</form>
		</div>
		<h4>Location: ${location}</h4>
		<h4>Pickup Time: ${pickupTime}</h4>
		<h4>Drop off Time: ${dropoffTime}</h4>
		<form action="confirm" method="Post">
			<input name="modify" value="modifyItinerary" type="hidden" /> <input
				type="submit" value="Select New Itinerary" class="btn btn-success">
		</form>
	</div>

	<div class="jumbotron" id="">
		<h1 class="display-4">Payment Information</h1>
		<hr class="my-4">
		<form action="confirm" method="Post">
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="state">Card Type</label> <select class="form-control"
						id="cardType" name="cardType" required>
						<option>Discover</option>
						<option>American Express</option>
						<option>Mastercard</option>
						<option>Visa</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="lastName">Card Number</label> <input type="text"
						class="form-control" id="cardNumber" placeholder=""
						name="cardNumber" pattern="[0-9\s]{13,19}" required>
				</div>
				<div class="form-group col-md-2">
					<label for="email">CVV</label> <input type="number"
						class="form-control" id="cvv" placeholder="123" name="cvv"
						min="100" max="9999" required>
				</div>
			</div>
			<input name="modify" value="" type="hidden" /> 
			<input name="carToSelect" value=${vehicle.id } type="hidden" /> 
			<input type="submit" value="Confirm Reservation" class="btn btn-success">
		</form>
	</div>
</div>

</body>
</html>