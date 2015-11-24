function validateForm() {
	var count = 0;
	var username = document.forms["loginform"]["userId"].value;
	var upassword = document.forms["loginform"]["password"].value;

	var isUsername = /^[0-9a-zA-Z_]+$/;
	var isVPassword = /^((?=.*\d)(?=.*[A-Z])(?=.*\W).{8,16})/;

	if (username == null || username == "" || (username.length < 5)
			|| isUsername.test(username) == false) {
		document.getElementById("loginNameErrorMessage").innerHTML = "<span class="
				+ "'loginNameErrorMessage'"
				+ ">Please provide a valid Login Name of minimum 5 characters and can have only special"
				+ "character is underscore(_).</span>";
		count++;
	} else {
		document.getElementById("loginNameErrorMessage").innerHTML = "";
	}

	if (upassword == null || upassword == "") {
		document.getElementById("passwordErrorMessage").innerHTML = "<span class="
				+ "'passwordErrorMessage'"
				+ ">Please provide a valid password.</span>";
		count++;
	} else {
		document.getElementById("passwordErrorMessage").innerHTML = "";
	}

	if (count != 0) {
		return false;
	}

	$
			.ajax({
				url : '/SapeStore/loginCheck',
				type : 'POST',
				data : {
					userId : username,
					password : upassword
				},
				success : function(response) {
					if (response == "true") {
						document.getElementById("loginErrorMessage").innerHTML = "";
						document.forms["loginform"].submit();
					} else {
						document.getElementById("loginErrorMessage").innerHTML = "Invalid Username or Password";
					}
				}
			});

}
