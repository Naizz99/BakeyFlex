$(document).ready(function(){
	//alert();
});

$("#saveSupplier").click(function () {
	disableButton(document.getElementById("saveSupplier"));
	if(document.getElementById("supplierSerial").value != '' && document.getElementById("supplierName").value != '' 
	&& document.getElementById("contactPerson").value != '' && document.getElementById("supplierName").value != ''
	&& document.getElementById("mobile1").value != ''
	&& document.getElementById("email").value != '' && document.getElementById("address").value != ''){
		
		var selectedStats = '';
		document.querySelectorAll('input[name="status"]').forEach(function(radioButton) {
			if(radioButton.checked){
				selectedStats = radioButton.value;	
			}
		});
						
		var supplier = {
			supplierId : document.getElementById("supplierId").value,
			supplierSerial : document.getElementById("supplierSerial").value,  
			supplierName : document.getElementById("supplierName").value, 
			contactPerson : document.getElementById("contactPerson").value, 
			mobile1 : document.getElementById("mobile1").value,
			mobile2 : document.getElementById("mobile2").value,
			address : document.getElementById("address").value,
			email :  document.getElementById("email").value,
			image : document.getElementById("image").value,
			specialNote : document.getElementById("specialNote").value,
			createBy : document.getElementById("createBy").value,
			updateBy : document.getElementById("updateBy").value,
			createDate : document.getElementById("createDate").value,
			updateDate : document.getElementById("updateDate").value,
	        status: selectedStats
		}
						
		$.ajax({
			url: '/secure/saveSupplier',
			type: 'POST',
			data: supplier,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("saveSupplier"),"Save");
				    window.location = "/supplierDetails?supplierId="+res;
				 });	     			
			},
			error:function(status, error){
				Swal.fire(
			      'Can not save!',
			      'Something went wrong',
			      'error'
			    );
			    enableButton(document.getElementById("saveSupplier"),"Save");
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
		enableButton(document.getElementById("saveSupplier"),"Save");
	}
});

function viewSupplierDetails(supplierId, button){
	disableButton(button);
	button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
	window.location = "/supplierDetails?supplierId=" + supplierId;
	enableButton(button,"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>");
}

function deleteSupplier(supplierId, button){
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
			url: '/deleteSupplier',
			type: 'GET',
			traditional: true,
			data: { supplierId: supplierId},
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
