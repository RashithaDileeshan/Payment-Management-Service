<%@page import="com.PaymentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="Component/jquery-3.5.0.min.js"></script>
<link href="Veiws/css/bootstrap.min.css" rel="stylesheet">
<link href="Veiws/css/style.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Online Payments</h1>
				<form id="formPayment" name="formPayment">
					Payment ID: <input id="paymentId" name="paymentId" type="text"
						class="form-control form-control-sm"> <br>    
						
						Patient Name: <input id="patientName" name="patientName" type="text"
						class="form-control form-control-sm"> <br> 
						
						Amount: <input id="amount" name="amount" type="text"
						class="form-control form-control-sm"> <br> 
						
						Payment Date: <input id="paymentdate" name="paymentdate" type="date"
						class="form-control form-control-sm"> <br> 
						
						Address: <input id="address" name="address" type="text"
						class="form-control form-control-sm"> <br> 
						
						Contact Number: <input id="contactNo" name="contactNo" type="text"
						class="form-control form-control-sm"> <br> 
						
						Email: <input id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> 
						
						<input id="btnSave" name="btnSave" type="button" value="Submit"
						class="btn btn-primary"> <input type="hidden"
						id="hidPaymentIdSave" name="hidPaymentIdSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>

				<div id="divPaymentsGrid">
					<% 
						PaymentService paymentService = new PaymentService();
						out.print(paymentService.readPayments());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>