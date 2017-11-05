(function($) {

	$(document).ready(function() {
		
		var cardInfoTable = $('table#creditCardInfo');
		var createCardForm = $('form#createCard');
		var creditCardSearchForm = $('form#creditCardSearch');
		var displayStatus = $('div#displayStatus');
		
		function performRequest(url, data, callback) {

			$.ajax(url, {
				data : data,
				method : 'POST',
				success : function(e) {
					
					callback(e);
				}
			});
		}
		
		function handleResponse(cssClass, message) {
			displayStatus.removeClass();
			displayStatus.addClass('alert ' + cssClass);
			displayStatus.text(message);
			displayStatus.show();
		}
		
		function handleResponseSuccess(message) {
			
			handleResponse('alert-success', message);
		}
		
		function handleResponseFailure(message) {
			
			handleResponse('alert-danger', message);
		}
		
		function formatDate(strDate) {
			
			var date = new Date(strDate);
			return (date.getMonth() +1) + "/" + date.getFullYear();
		}
		
		createCardForm.submit(function(e) {

			e.preventDefault();

			var data = {
				username : $('input#username').val(),
				cardNumber : $('input#cardNumber').val(),
				cardOwner : $('input#cardOwner').val(),
				expiryMonth : $('input#expiryMonth').val(),
				expiryYear : $('input#expiryYear').val(),
				_csrf : $('input#csrf').val()
			};
			
			var callback = function(response) {
				switch(response.entity) {
					case "SUCCESS" : 
						handleResponseSuccess(response.message);
						break;
				
					case "FAILURE" :
						handleResponseFailure(response.message);
						break;
					
					case "FAILURE_ALREADY_EXISTS" :
						handleResponseFailure(response.message);
						break;
						
					case "FAILURE_VALIDATION" :
						handleResponseFailure(response.message);
						break;
				}
			}
			
			performRequest('http://localhost:8080/saveCard', data, callback);
		});

		creditCardSearchForm.submit(function(e) {

			e.preventDefault();

			var data = {
				username : $('input#usernameSearch').val(),
				cardNumber : $('input#cardNumberSearch').val(),
				_csrf : $('input#csrfSearch').val()
			};
			
			var callback = function(response) {
				var entity = response.entity;
				$('tr.infoCard').remove();
			
				cardInfoTable.append('<tr class="infoCard"><td>' 
									+ entity.id + '</td><td>' 
									+ entity.userId + '</td><td>' 
									+ entity.number + '</td><td>' 
									+ formatDate(entity.expiryDate) + '</td><td>' 
									+ entity.name + '</td></tr>');
			}
			
			performRequest('http://localhost:8080/getCreditCard', data, callback);
		});
	});

})(jQuery);