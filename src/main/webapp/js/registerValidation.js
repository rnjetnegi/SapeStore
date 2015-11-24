$(document).ready(function(){
$("#register_state").change(function() {
 
       var $dropdown = $(this);  
       $.getJSON("js/states.json", function(states) {
      
              var key = $dropdown.val();
              var vals = [];
                                               
              switch(key) {
                     case 'Florida':
                           vals = states.Florida.split(",");
                           break;
                     case 'California':
                           vals = states.California.split(",");
                           break;
                     case 'Alabama':
                           vals = states.Alabama.split(",");
                           break;
                     case 'Texas':
                           vals = states.Texas.split(",");
                           break;
                     case 'New Jersey':
                           vals = states.NewJersey.split(",");
                           break;
                     case 'Alaska':
                           vals = states.Alaska.split(",");
                           break;
                     case 'Georgia':
                           vals = states.Georgia.split(",");
                           break;
                     case 'Indiana':
                           vals = states.Indiana.split(",");
                           break;
                     case 'Ohio':
                           vals = states.Ohio.split(",");
                           break;
                     case 'Michigan':
                           vals = states.Michigan.split(",");
                           break;
                          
                     /* case 'base':
                           vals = ['Please choose from above']; */
              }
             
              var $secondChoice = $("#register_city");
              $secondChoice.empty();
              $.each(vals, function(index, value) {
                     $secondChoice.append("<option>" + value + "</option>");
              });
 
       });
});
});


function matchPassword() {
	var upassword = document.forms["registration"]["password"].value;
	var cnfPassword = document.forms["registration"]["cnfPassword"].value;

	if (upassword == cnfPassword) {
		document.getElementById("cnfPasswordErrorMessage").innerHTML = "";
		return true;
	}
	document.getElementById("cnfPasswordErrorMessage").innerHTML = "<span class="
			+ "'cnfPasswordErrorMessage'"
			+ ">Password and Confirm password does not match.</span>";
	return false;
}

function validateForm() {
	var count = 0;
	var fname = document.forms["registration"]["fullName"].value;
	var username = document.forms["registration"]["loginName"].value;
	var upassword = document.forms["registration"]["password"].value;
	var cnfPassword = document.forms["registration"]["cnfPassword"].value;
	var uemail = document.forms["registration"]["email"].value;
	var aline1 = document.forms["registration"]["addressLine1"].value;
	var aline2 = document.forms["registration"]["addressLine2"].value;
	var ucity = document.forms["registration"]["city"].value;
	var ustate = document.forms["registration"]["state"].value;
	var uzip = document.forms["registration"]["postalCode"].value;
	var uphone = document.forms["registration"]["phone"].value;
	var umobile = document.forms["registration"]["mobileNumber"].value;
	
	var isUsername = /^[0-9a-zA-Z_]+$/; //Login name accepts Alphabets, numbers and underscore(_)
	var isValidPhone = /^\(?([0-9]{3})\)?[- ]?([0-9]{3})[- ]?([0-9]{4})$/;
	var isValidZip = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
	var isAddress = /^[0-9a-zA-Z-/,\s]+$/;
	var isLetters = /^[a-zA-Z\s]+$/;
	var isAlphanumeric = /^[0-9a-zA-Z]+$/;
	var isPassword = /^[0-9a-zA-Z!@#$~]+$/;
	var isVPassword = /^((?=.*\d)(?=.*[A-Z])(?=.*\W).{8,16})/;
	var isEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

	if (fname == null || fname == "" || isLetters.test(fname) == false) {
		document.getElementById("nameErrorMessage").innerHTML = "<span class="
				+ "\'nameErrorMessage\'"
				+ ">Please provide a valid Full Name.</span>";
		count++;
	} else {
		document.getElementById("nameErrorMessage").innerHTML = "";
	}
	if (username == null || username == ""  || (username.length<8) || isUsername.test(username) == false) {
		document.getElementById("loginNameErrorMessage").innerHTML = "<span class="
				+ "'loginNameErrorMessage'"
				+ ">Please provide a valid Login Name of minimum 8 characters.</span>";
		count++;
	} else {
		document.getElementById("loginNameErrorMessage").innerHTML = "";
	}

	if (upassword == null || upassword == ""){
		document.getElementById("passwordErrorMessage").innerHTML = "<span class="
			+ "'passwordErrorMessage'"
			+ ">Please provide a valid password.</span>";
	count++;
	} else if((upassword.length)<8 || (upassword.length)>16){
		document.getElementById("passwordErrorMessage").innerHTML = "<span class="
			+ "'passwordErrorMessage'"
			+ ">Size Matters! Your password should be between 8 and 16 characters.</span>";
	count++;
	}else if(isVPassword.test(upassword) == false) {
		document.getElementById("passwordErrorMessage").innerHTML = "<span class="
				+ "'passwordErrorMessage'"
				+ ">Password must be between 8 to 16 characters and must have an Upper case alphabet, number and special character.</span>";
		count++;
	} else {
		document.getElementById("passwordErrorMessage").innerHTML = "";
	}

	if (cnfPassword == null || cnfPassword == ""
			|| isVPassword.test(cnfPassword) == false) {
		document.getElementById("cnfPasswordErrorMessage").innerHTML = "<span class="
				+ "'cnfPasswordErrorMessage'"
				+ ">Please provide a valid Password.</span>";
		count++;
	} else {
		document.getElementById("cnfPasswordErrorMessage").innerHTML = "";
	}
	
	if (upassword == cnfPassword) {
		document.getElementById("cnfPasswordErrorMessage").innerHTML = "";
	}
	else{
	document.getElementById("cnfPasswordErrorMessage").innerHTML = "<span class="
			+ "'cnfPasswordErrorMessage'"
			+ ">Password and Confirm password do not match.</span>";
	count++;
}

	if (uemail == null || uemail == "" || isEmail.test(uemail) == false) {
		document.getElementById("emailErrorMessage").innerHTML = "<span class="
				+ "'emailErrorMessage'"
				+ ">Please provide a valid Email.</span>";
		count++;
	} else {
		document.getElementById("emailErrorMessage").innerHTML = "";
	}

	if (aline1 == null || aline1 == "") {
		document.getElementById("addr1ErrorMessage").innerHTML = "<span class="
				+ "'addr1ErrorMessage'"
				+ ">Address cannot be empty.</span>";
		count++;
	} else if (aline1.length > 22) {
		document.getElementById("addr1ErrorMessage").innerHTML = "<span class="
				+ "'addr1ErrorMessage'" + ">Address too long. Only 22 characters allowed.</span>";
		count++;
	} else if(isAddress.test(aline1) == false){
		document.getElementById("addr1ErrorMessage").innerHTML = "<span class="
			+ "'addr1ErrorMessage'" + ">Only these special characters allowed -/,. </span>";
	count++;
	}
	else {
		document.getElementById("addr1ErrorMessage").innerHTML = "";
	}

	if(aline2 != null && aline2 != ""){
		if(isAddress.test(aline2) == false){
		document.getElementById("addr2ErrorMessage").innerHTML = "<span class="
			+ "'addr2ErrorMessage'" + ">Only these special characters allowed -/,. </span>";
	count++;
	} else if (aline2.length > 22) {
		document.getElementById("addr2ErrorMessage").innerHTML = "<span class="
				+ "'addr2ErrorMessage'" + ">Address too long.Only 22 characters allowed.</span>";
		count++;
	} else if(isAddress.test(aline2) == false){
		document.getElementById("addr1ErrorMessage").innerHTML = "<span class="
			+ "'addr2ErrorMessage'" + ">Only these special characters allowed -/,. </span>";
	count++;
	} else {
		document.getElementById("addr2ErrorMessage").innerHTML = "";
	}}

	if (ucity == null || ucity == "" || ucity == "NONE") {
		document.getElementById("cityErrorMessage").innerHTML = "<span class="
				+ "'cityErrorMessage'" + ">Please select a city.</span>";
		count++;
	} else {
		document.getElementById("cityErrorMessage").innerHTML = "";
	}

	if (ustate == null || ustate == "" || ustate == "NONE") {
		document.getElementById("stateErrorMessage").innerHTML = "<span class="
				+ "'stateErrorMessage'" + ">Please select a state.</span>";
		count++;
	} else {
		document.getElementById("stateErrorMessage").innerHTML = "";
	}

	if (uzip == null || uzip == "" || isValidZip.test(uzip) == false) {
		document.getElementById("zipErrorMessage").innerHTML = "<span class="
				+ "'zipErrorMessage'"
				+ ">Please provide a valid zip code in format XXXXX or XXXXX-XXXX.</span>";
		count++;
	} else {
		document.getElementById("zipErrorMessage").innerHTML = "";
	}

	if (uphone != null && uphone != "") {
		if(isValidPhone.test(uphone) == false)
		document.getElementById("phoneErrorMessage").innerHTML = "<span class="
				+ "'phoneErrorMessage'"
				+ ">Please provide a valid 10 digit phone number.</span>";
	} else {
		document.getElementById("phoneErrorMessage").innerHTML = "";
	}

	if (umobile == null || umobile == "" || isValidPhone.test(umobile) == false) {
		document.getElementById("mobileErrorMessage").innerHTML = "<span class="
				+ "'mobileErrorMessage'"
				+ ">Please provide a valid 10 digit mobile number.</span>";
		count++;
	} else {
		document.getElementById("mobileErrorMessage").innerHTML = "";
	}

	if (count != 0) {
		return false;
	}
	document.forms["registration"].submit();
}