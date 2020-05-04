$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

});

var status = validatePaymentForm();
if (status != true) 
{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	
	$("#formPayment").submit();
	
	// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide(); 
	 
	// Form validation-------------------
	 var status = validatePaymentForm();
	 if (status != true)
	  {
	  $("#alertError").text(status);
	  $("#alertError").show();
	  return;
	  }
	 // If valid-------------------------
	  $("#formPayment").submit();
});

	$(document).on("click", ".btnUpdate", function(event)
	{
			 
	});


	 
	 
}
