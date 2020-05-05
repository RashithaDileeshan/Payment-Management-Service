$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validatePatientForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidPaymentIdSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "PaymentsAPI",
		type : type,
		data : $("#formPayment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onPaymentSaveComplete(response.responseText, status);
		}
	});
});

function onPaymentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPaymentsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidPaymentIdSave").val("");
	$("#formPayment")[0].reset();
}

// CLIENT-MODEL================================================================
function validatePatientForm() {
	// type
	if ($("#patientName").val().trim() == "") {
		return "Insert Patient Name";
	}
	
	// amount
	if ($("#amount").val().trim() == "") {
		return "Insert Amount.";
	}
	// paymentId
	if ($("#paymentdate").val().trim() == "") {
		return "Insert Payment Date.";
	}
	// Address
	if ($("#address").val().trim() == "") {
		return "Insert Address.";
	}
	
	// ContactNo
	if ($("#contactNo").val().trim() == "") {
		return "Insert Contact Number.";
	}
	// email
	if ($("#email").val().trim() == "") {
		return "Insert Email.";
	}	
	// is numerical value
	var tmpamount = $("#amount").val().trim();
	if (!$.isNumeric(tmpamount)) {
		return "Insert a numerical value for Item Price.";
	}
	// convert to decimal price
	$("#amount").val(parseFloat(tmpamount).toFixed(2));
	
	return true;
}

