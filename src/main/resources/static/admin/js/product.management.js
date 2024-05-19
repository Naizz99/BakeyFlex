$(document).ready(function(){
	//alert();
});

$("#saveProduct").click(function () {
	disableButton(document.getElementById("saveProduct"));
	if(document.getElementById("productSerial").value != '' && $("#productType").val() !='' && 
	document.getElementById("productName").value != '' && $("#shortDescription").val() !='' && 
	document.getElementById("longDescription").value != '' && $("#unitPrice").val() !='' ){
		
		var selectedStats = '';
		document.querySelectorAll('input[name="status"]').forEach(function(radioButton) {
			if(radioButton.checked){
				selectedStats = radioButton.value;	
			}
		});
						
		var product = {
			productId : document.getElementById("productId").value,
			productSerial : document.getElementById("productSerial").value, 
			productType :  document.getElementById("productType").value,
			productName : document.getElementById("productName").value, 
			shortDescription : document.getElementById("shortDescription").value, 
			longDescription : document.getElementById("longDescription").value,
			specialNote : document.getElementById("specialNote").value,
			recipeId : document.getElementById("recipeId").value,  
			unitPrice : document.getElementById("unitPrice").value,  
			discountRate : document.getElementById("discountRate").value, 
			image : document.getElementById("image").value,
			specialNote : document.getElementById("specialNote").value,
			createBy : document.getElementById("createBy").value,
			updateBy : document.getElementById("updateBy").value,
			createDate : document.getElementById("createDate").value,
			updateDate : document.getElementById("updateDate").value,
	        status: selectedStats
		}
						
		$.ajax({
			url: '/secure/saveProduct',
			type: 'POST',
			data: product,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("saveProduct"),"Save");
				    window.location = "/productDetails?productId="+res;
				 });	     			
			},
			error:function(status, error){
				Swal.fire(
			      'Can not save!',
			      'Something went wrong',
			      'error'
			    );
			    enableButton(document.getElementById("saveProduct"),"Save");
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
		enableButton(document.getElementById("saveProduct"),"Save");
	}
});

function viewProductDetails(productId, button){
	disableButton(button);
	button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
	window.location = "/productDetails?productId=" + productId;
	enableButton(button,"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>");
}

function deleteProduct(productId, button){
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
			url: '/deleteProduct',
			type: 'GET',
			traditional: true,
			data: { productId: productId},
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

$("#saveProductType").click(function () {
	disableButton(document.getElementById("saveProductType"));
	if(document.getElementById("productType").value != '' && $("#productTypeName").val() !='' && document.getElementById("description").value != ''){
		
		var selectedStats = '';
		document.querySelectorAll('input[name="status"]').forEach(function(radioButton) {
			if(radioButton.checked){
				selectedStats = radioButton.value;	
			}
		});
						
		var productType = {
	        ptId : document.getElementById("ptId").value,
			productType : document.getElementById("productType").value, 
			productTypeName : document.getElementById("productTypeName").value, 
			description : document.getElementById("description").value,  
			createBy : document.getElementById("createBy").value,
			updateBy : document.getElementById("updateBy").value,
			createDate : document.getElementById("createDate").value,
			updateDate : document.getElementById("updateDate").value,
			status : selectedStats
		}
						
		$.ajax({
			url: '/secure/saveProductType',
			type: 'POST',
			data: productType,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("saveProductType"),"Save");
				    window.location = "/productTypeDetails?ptId="+res;
				 });	     			
			},
			error:function(status, error){
				Swal.fire(
			      'Can not save!',
			      'Something went wrong',
			      'error'
			    );
			    enableButton(document.getElementById("saveProductType"),"Save");
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
		enableButton(document.getElementById("saveProduct"),"Save");
	}
});

function deleteProductType(ptId, button){
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
			url: '/deleteProductType',
			type: 'GET',
			traditional: true,
			data: { ptId: ptId},
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

function productTypeStatusUpdate(object, ptId, curentStatus, newStatus){
	
	var productType = {
        ptId : ptId,
		productType : '', 
		productTypeName : '', 
		description : '',  
		createBy : '',
		updateBy : '',
		createDate : '',
		updateDate : '',
		status : newStatus
	}	
		
	Swal.fire({
	  title: 'Are you sure?',
	  text: "Do you want to update status?",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Yes'
	}).then((result) => {
	  if (result.isConfirmed) {
		$.ajax({
			url: '/secure/productTypeStatusUpdate',
			type: 'POST',
			traditional: true,
			data: productType,
			success: function (response) {
				window.location.reload();	
			},
			error:function(status, error){
				 Swal.fire(
			      "Can't Process!",
			      status.responseText != null ? status.responseText : 'Something Went Wrong!',
			      'error'
			    )
			    object.value = curentStatus;	
			}
		});
	  }else{	
			window.location.reload();	
	  }
	})	
}
