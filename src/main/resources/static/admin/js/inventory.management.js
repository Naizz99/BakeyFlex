var inventoryId = 0;
$(document).ready(function(){
	//alert();
	if(document.getElementById("inventoryId")){
		inventoryId = document.getElementById("inventoryId").value;	
	}
});

$("#saveInventory").click(function () {
	disableButton(document.getElementById("saveInventory"));
	if(document.getElementById("inventorySerial").value != '' && $("#inventoryType").val() !='' && document.getElementById("inventoryName").value != '' && $("#unit").val() !='' && $("#availableAmount").val() !='' && $("#unitPrice").val() !='' && $("#firstExDate").val() !=''){
		
		var selectedStats = '';
		document.querySelectorAll('input[name="status"]').forEach(function(radioButton) {
			if(radioButton.checked){
				selectedStats = radioButton.value;	
			}
		});		
		
		var inventory = {
			inventoryId: document.getElementById("inventoryId").value,
	        inventorySerial: document.getElementById("inventorySerial").value,
	        inventoryType: document.getElementById("inventoryType").value,
	        inventoryName: document.getElementById("inventoryName").value,
	        description: document.getElementById("description").value,
	        specialNote: document.getElementById("specialNote").value,
	        image: document.getElementById("image").value,
	        unitId: document.getElementById("unitId").value,
	        availableAmount: document.getElementById("availableAmount").value,
	        unitPrice: document.getElementById("unitPrice").value,
	        firstExDate: document.getElementById("firstExDate").value,
	        createBy: document.getElementById("createBy").value,
	        updateBy: document.getElementById("updateBy").value,
	        createDate: document.getElementById("createDate").value,
	        updateDate: document.getElementById("updateDate").value,
	        status: selectedStats
		}
				
		$.ajax({
			url: '/secure/saveInventory',
			type: 'POST',
			data: inventory,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("saveInventory"),"Save");
				    window.location = "/inventoryDetails?inventoryId="+res;
				 });	     			
			},
			error:function(status, error){
				Swal.fire(
			      'Can not save!',
			      'Something went wrong',
			      'error'
			    );
			    enableButton(document.getElementById("saveInventory"),"Save");
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
		enableButton(document.getElementById("saveInventory"),"Save");
	}
});

function viewInventoryDetails(inventoryId, button){
	disableButton(button);
	button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
	window.location = "/inventoryDetails?inventoryId="+inventoryId;
}

function deleteInventory(inventoryId, button){
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
			url: '/deleteInventory',
			type: 'GET',
			traditional: true,
			data: { inventoryId: inventoryId},
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

$("#linkSupplier").click(function () {
	var selectedRadio = document.querySelector('input[name="status"]:checked');
	var selectedStatus = '';
    if (selectedRadio) {
        var label = document.querySelector('label[for="' + selectedRadio.id + '"]');
        if (label ) {
            selectedStatus = label .textContent;
        }
    } else {
        selectedStatus = '';
    }

	disableButton(document.getElementById("linkSupplier"));
	if(document.getElementById("supplierSerial").value != '' && ($("#supplierName").text) && document.getElementById("availableAmount").value != '' && $("#unitPrice").val() !='' && $("#firstExDate").val() !='' && $("#specialNote").val() !='' && selectedStatus !=''){
		
		var supplierLink = {
			islId		: 0,
			inventoryId	: inventoryId,
			supplierId	: document.getElementById("supplierSerial").value,
			specialNote	: $("#specialNote").val(),
			image		: '',
			availableAmount	: document.getElementById("availableAmount").value,
			unitPrice	: $("#unitPrice").val(),
			firstExDate	: $("#firstExDate").val(),
			createBy	: '',
			updateBy	: '',
			createDate	: '',
			updateDate	: '',
			status		: selectedStatus
		}
						
		$.ajax({
			url: '/secure/linkSupplier',
			type: 'POST',
			data: supplierLink,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("linkSupplier"),"Save");
				    window.location = "/viewInventorySuppliers?inventoryId="+inventoryId;
				 });	     			
			},
			error:function(status, error){
				 Swal.fire(
			      "Can't Process!",
			      status.responseText != null ? status.responseText : 'Something Went Wrong!',
			      'error'
			    )
			    enableButton(document.getElementById("linkSupplier"),"Save");	
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
		enableButton(document.getElementById("linkSupplier"),"Save");
	}
});

function deleteSupplier(islId, button){
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
			url: '/unLinkSupplier',
			type: 'GET',
			traditional: true,
			data: { islId: islId},
			success: function (response) {
				window.location.reload();	
			},
			error:function(status, error){
				 Swal.fire(
			      "Can't Process!",
			      status.responseText != null ? status.responseText : 'Something Went Wrong!',
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

function availableAmountUpdate(object, islId, curentAmount, newAmount){
	
	var supplierLink = {
		islId		: islId,
		inventoryId	: inventoryId,
		supplierId	: '',
		specialNote	: '',
		image		: '',
		availableAmount	: newAmount,
		unitPrice	: 0,
		firstExDate	: '',
		createBy	: '',
		updateBy	: '',
		createDate	: '',
		updateDate	: '',
		status		: ''
	}
		
	Swal.fire({
	  title: 'Are you sure?',
	  text: "Do you want to update available inventry amount?",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Yes'
	}).then((result) => {
	  if (result.isConfirmed) {
		$.ajax({
			url: '/secure/availableAmountUpdate',
			type: 'POST',
			traditional: true,
			data: supplierLink,
			success: function (response) {
				window.location.reload();	
			},
			error:function(status, error){
				 Swal.fire(
			      "Can't Process!",
			      status.responseText != null ? status.responseText : 'Something Went Wrong!',
			      'error'
			    )
			    object.value = curentAmount;	
			}
		});
	  }else{	
			window.location.reload();	
	  }
	})	
}

function supplierInventoryStatusUpdate(object, islId, curentStatus, newStatus){
	
	var supplierLink = {
		islId		: islId,
		inventoryId	: inventoryId,
		supplierId	: '',
		specialNote	: '',
		image		: '',
		availableAmount	: 0,
		unitPrice	: 0,
		firstExDate	: '',
		createBy	: '',
		updateBy	: '',
		createDate	: '',
		updateDate	: '',
		status		: newStatus
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
			url: '/secure/supplierInventoryStatusUpdate',
			type: 'POST',
			traditional: true,
			data: supplierLink,
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

$("#newRequest").click(function () {
	disableButton(document.getElementById("newRequest"));
	if(document.getElementById("supplierSerial").value != '' && ($("#supplierName").text) && document.getElementById("requestingAmount").value != '' && $("#specialNote").val() !=''){
		
		var inventoryRequest = {
			irId: 0,
			requestCode: document.getElementById("requestCode").value,
			inventoryId: inventoryId,
			supplierId:	document.getElementById("supplierSerial").value,
			specialNote: $("#specialNote").val(),	
			requestedAmount: document.getElementById("requestingAmount").value,
			createBy: '',
			updateBy: '',
			createDate: '',
			updateDate: '',
			status: ''
		}
						
		$.ajax({
			url: '/secure/requestInventory',
			type: 'POST',
			data: inventoryRequest,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("newRequest"),"Save");
				    window.location = "/viewInventoryRequests?inventoryId="+inventoryId;
				 });	     			
			},
			error:function(status, error){
				 Swal.fire(
			      "Can't Process!",
			      status.responseText != null ? status.responseText : 'Something Went Wrong!',
			      'error'
			    )
			    enableButton(document.getElementById("newRequest"),"Save");	
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
		enableButton(document.getElementById("newRequest"),"Save");
	}
});

function deleteInventoryRequest(irId, button){
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
			url: '/deleteInventoryRequest',
			type: 'GET',
			traditional: true,
			data: { irId: irId},
			success: function (response) {
				window.location.reload();	
			},
			error:function(status, error){
				 Swal.fire(
			      "Can't Process!",
			      status.responseText != null ? status.responseText : 'Something Went Wrong!',
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

function inventoryRequestStatusUpdate(object, irId, currentStatus, newStatus){
	
	var inventoryRequest = {
		irId: irId,
		requestCode: '',
		inventoryId: inventoryId,
		supplierId:	'',
		specialNote: '',	
		requestedAmount: 0,
		createBy: '',
		updateBy: '',
		createDate: '',
		updateDate: '',
		status: newStatus
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
			url: '/secure/inventoryRequestStatusUpdate',
			type: 'POST',
			traditional: true,
			data: inventoryRequest,
			success: function (response) {
				window.location.reload();	
			},
			error:function(status, error){
				 Swal.fire(
			      "Can't Process!",
			      status.responseText != null ? status.responseText : 'Something Went Wrong!',
			      'error'
			    )
			    object.value = currentStatus;
			}
		});
	  }else{	
			window.location.reload();	
	  }
	})	
}

