$(document).ready(function(){
	//alert();
});

$("#saveRecipe").click(function () {
	disableButton(document.getElementById("saveRecipe"));
	if(document.getElementById("description").value){
		
		var selectedStats = '';
		document.querySelectorAll('input[name="status"]').forEach(function(radioButton) {
			if(radioButton.checked){
				selectedStats = radioButton.value;	
			}
		});
						
		var recipe = {
	        recipeId : document.getElementById("recipeId").value,
			description : document.getElementById("description").value,
			specialNote : document.getElementById("specialNote").value,
			image : document.getElementById("image").value,  
			createBy : document.getElementById("createBy").value,
			updateBy : document.getElementById("updateBy").value,
			createDate : document.getElementById("createDate").value,
			updateDate : document.getElementById("updateDate").value,
			status : selectedStats
		}
						
		$.ajax({
			url: '/secure/saveRecipe',
			type: 'POST',
			data: recipe,
			cache: false,
			success:function (res) {
				Swal.fire(
			      'Successful!',
			      'Your record has been saved',
			      'success'
			     ).then(function() {
					enableButton(document.getElementById("saveRecipe"),"Save");
				    window.location = "/recipeDetails?recipeId="+res;
				 });	     			
			},
			error:function(status, error){
				Swal.fire(
			      'Can not save!',
			      'Something went wrong',
			      'error'
			    );
			    enableButton(document.getElementById("saveRecipe"),"Save");
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
		enableButton(document.getElementById("saveRecipe"),"Save");
	}
});

function viewRecipeDetails(recipeId, button){
	disableButton(button);
	button.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
	window.location = "/recipeDetails?recipeId=" + recipeId;
	enableButton(button,"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>");
}

function deleteRecipe(recipeId, button){
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
			url: '/deleteRecipe',
			type: 'GET',
			traditional: true,
			data: { recipeId: recipeId},
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
