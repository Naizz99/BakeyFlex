$(document).ready(function(){
	//alert();
});

$("#saveCustomer").click(function () {
	disableButton(document.getElementById("saveCustomer"));
	if(document.getElementById("customerSerial").value != '' && document.getElementById("customerName").value != '' 
	&& document.getElementById("contactPerson").value != '' && document.getElementById("customerName").value != ''
	&& document.getElementById("mobile1").value != ''
	&& document.getElementById("email").value != '' && document.getElementById("address").value != ''){
		
		var selectedStats = '';
		document.querySelectorAll('input[name="status"]').forEach(function(radioButton) {
			if(radioButton.checked){
				selectedStats = radioButton.value;	
			}
		});
						
		var customer = {
			customerId : document.getElementById("customerId").value,
			customerCode : document.getElementById("customerCode").value,  
			firstName : document.getElementById("firstName").value, 
			lastName : document.getElementById("lastName").value, 
			mobile : document.getElementById("mobile").value,
			email :  document.getElementById("email").value,
			addressLine1 : document.getElementById("addressLine1").value,
			addressLine2 : document.getElementById("addressLine2").value,
			addressLine3 : document.getElementById("addressLine3").value,
			addressLine4 : document.getElementById("addressLine4").value,
			gender : document.getElementById("gender").value,
			birthDay : document.getElementById("birthDay").value,
			image : document.getElementById("image").value,
			username : document.getElementById("username").value,
			password : document.getElementById("password").value,
			logged : '',
			createBy : document.getElementById("createBy").value,
			updateBy : document.getElementById("updateBy").value,
			createDate : document.getElementById("createDate").value,
			updateDate : document.getElementById("updateDate").value,
	        status: selectedStats
		}
						
		$.ajax({
			url: '/secure/saveCustomer',
			type: 'POST',
			data: customer,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("saveCustomer"),"Save");
				    window.location = "/customerDetails?customerId="+res;
				 });	     			
			},
			error:function(status, error){
				Swal.fire(
			      'Can not save!',
			      'Something went wrong',
			      'error'
			    );
			    enableButton(document.getElementById("saveCustomer"),"Save");
			}
		});
	}
	else{
		Swal.fire({
		  icon: 'error',
		  title: 'Something went wrong!',
		  text: 'Enter Required Details',
		  footer: ''
		});
		enableButton(document.getElementById("saveCustomer"),"Save");
	}
});

function viewCustomerDetails(customerId, button){
	disableButton(button);
	button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
	window.location = "/customerDetails?customerId=" + customerId;
	enableButton(button,"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>");
}

function deleteCustomer(customerId, button){
	disableButton(button);
	button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
	Swal.fire({
	  title: 'Are you sure?',
	  text: "You won't be able to revert this!",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Yes'
	}).then((result) => {
	  if (result.isConfirmed) {
		$.ajax({
			url: '/deleteCustomer',
			type: 'GET',
			traditional: true,
			data: { customerId: customerId},
			success: function (response) {
				window.location.reload();	
			},
			error:function(status, error){
				 Swal.fire(
			      'Can not process!',
			      'Something went wrong',
			      'error'
			    )
			    enableButton(button,"<i class='fa fa-trash-o' aria-hidden='true'></i>");
			}
		});
	  }else{	
			enableButton(button,"<i class='fa fa-trash-o' aria-hidden='true'></i>");
	  }
	})	
}
