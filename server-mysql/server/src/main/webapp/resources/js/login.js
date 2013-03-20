//var authUrl = "rest/auth/";

$(document).ready(function(){
	$("#loginSubmit").click(login.submit);
});

var login = {
		submit: function() {
//			var user = {};
//			user.email = $("#emailSignin").val();
//			user.password = $("#passwordSignin").val();
//			$.ajax(
//					signinUrl, {
//					type: 'POST',
//					contentType: 'application/json',
//					data:JSON.stringify(user),
//					processData: false,
//					success: function(data, textStatus, jqXHR) {
//					},
//					error: function(data, textStatus, jqXHR) {
//						
//					},
//					
//				}	
//			);
			window.location.href = "/server/home/main/";
		}
};