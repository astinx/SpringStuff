var authUrl = "rest/auth/";
var signinUrl = "rest/user/";

$(document).ready(function(){
	$("#signinSubmit").click(signin.submit);
	$("#loginSubmit").click(login.submit);
});

var login = {
		submit: function() {
			var user = {};
			user.email = $("#emailLogin").val();
			user.password = $("#passwordLogin").val();
			$.ajax(
					authUrl, {
					type: 'POST',
					contentType: 'application/json',
					data:JSON.stringify(user),
					processData: false,
					success: function(data, textStatus, jqXHR) {
					},
					error: function(data, textStatus, jqXHR) {
						
					},
				}	
			);
		}
};

var signin = {
		submit: function() {
			var user = {};
			user.email = $("#emailSignin").val();
			user.password = $("#passwordSignin").val();
			$.ajax(
					signinUrl, {
					type: 'POST',
					contentType: 'application/json',
					data:JSON.stringify(user),
					processData: false,
					success: function(data, textStatus, jqXHR) {
					},
					error: function(data, textStatus, jqXHR) {
						
					},
					
				}	
			);
		}
};