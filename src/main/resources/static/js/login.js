(function($) {
	
	$(document).ready(function() {

		var displayStatus = $('div#displayStatus');
		var createUserForm = $('form#createUserForm');
		
		function handleLogin(cssClass, message) {
			displayStatus.removeClass();
			displayStatus.addClass('alert ' + cssClass);
			displayStatus.text(message);
			displayStatus.show();
		}
		
		function handleLoginSuccess(message) {
			
			handleLogin('alert-success', message);
		}
		
		function handleLoginFailure(message) {
			
			handleLogin('alert-danger', message);
		}
		
		createUserForm.submit(function(e) {

			e.preventDefault();
			
			var username = $('input#createUsername').val();
			var password = $('input#createPassword').val();
			var admin = $('input#isAdmin').val();
			var csrf = $('input#csrf').val();
			
			var data = {
				username : username,
				password : password,
				admin : admin,
				_csrf : csrf
			};

			$.ajax('http://localhost:8080/saveUser', {
				data : data,
				method : 'POST',
				success : function(response) {
					
					switch (response.entity) {
						case "SUCCESS" : 
							handleLoginSuccess(response.message);
							break;
						case "FAILURE" :
							handleLoginFailure(response.message);
							break;
					}
				}
			});
		});
	});

})(jQuery);