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
	var status = validatePaymentForm();
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
			onPatientSaveComplete(response.responseText, status);
		}
	});
});

function onPaymentSaveComplete(response, status) {
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPaymentsGrid").html(resultSet.data);
		
		} 
		else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	
	} 
	else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	
	}
	
	$("#hidPaymentIdSave").val("");
	$("#formPayment")[0].reset();

}

// CLIENT-MODEL================================================================
function validatePaymentForm() {
	//Patient Name 
	if ($("#patientName").val().trim() == "") {
		return "Insert Patient Name";
	}
	
	// Amount
	if ($("#amount").val().trim() == "") {
		return "Insert Amount.";
	}
	// Payment date
	if ($("#paymentdate").val().trim() == "") {
		return "Insert Payment Date.";
	}
	// Address
	if ($("#address").val().trim() == "") {
		return "Insert Address.";
	}
	
	// Contact Number
	if ($("#contactNo").val().trim() == "") {
		return "Insert Contact Number.";
	}
	
	// email
	if ($("#email").val().trim() == "") {
		return "Insert Email.";
	}
	

	return true;
}

//Update
$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#hidPaymentIdSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
		 $("#patientName").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#amount").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#paymentdate").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#address").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#contactNo").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#email").val($(this).closest("tr").find('td:eq(5)').text());
	});


//Remove
$(document).on("click", ".btnRemove", function(event)
		{
		 	$.ajax(
		{
			url : "PaymentsAPI",
			type : "DELETE",
			data : "paymentId=" + $(this).data("itemid"),
			dataType : "text",
			complete : function(response, status)
			{
				onPaymentDeleteComplete(response.responseText, status);
			}
		 });
		});

	function onPaymentDeleteComplete(response, status)
	{
	if (status == "success")
	{
			var resultSet = JSON.parse(response);
	
	if (resultSet.status.trim() == "success")
	{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divPaymentsGrid").html(resultSet.data);
	} 
	
	else if (resultSet.status.trim() == "error")
	{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
	}
	
	}
	
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}




	
	


