function regFormValidation() {
	var count = 0;
	var fname = document.forms["registration"]["fullName"].value;
	alert(fname);
	var uid = document.getElementById("register_loginName");
	var passid = document.getElementById("register_password");
	var cnfpassid = document.getElementById("register_cnfPassword");
	// var uname = document.registration.username;
	var uemail = document.getElementById("register_email");
	var uadd = document.getElementById("register_addressLine1");
	var uadd2 = document.getElementById("register_addressLine2");
	var ucity = document.getElementById("register_city");
	var ustate = document.getElementById("register_state");
	var uzip = document.getElementById("register_postalCode");
	//var ucountry = document.registration.country;
	var uphone = document.getElementById("register_phoneNumber");
	var umobile = document.getElementById("register_mobileNumber");
	//var umsex = document.registration.msex;
	//var ufsex = document.registration.fsex;
	
	if (!userid_validation(uid, 6, 12)) {
		alert("User Id should not be empty / length be between " + mx + " to "
				+ my);
		count++;
	}
	if (!passid_validation(passid, 6, 12)) {
		alert("Password should not be empty / length be between " + mx + " to "
			+ my);
	passid.focus();
		count++;	
	}
	if (!allLetter(fname) || fname == "hello") {
		alert('Username must have alphabet characters only');
		uname.focus();
		count++;
	}
	if (!validateEmail(uemail)) {
		alert("You have entered an invalid email address!");
		uemail.focus();
		count++;
	}
	if (!alphanumeric(uadd)) {
		alert('User address must have alphanumeric characters only');
		uadd.focus();
		count++;
	}
	if(!cityselect(ustate)){
		alert('Select your city from the list');
		ucity.focus();
		count++;
	}
	if (!stateselect(ucity)) {
		alert('Select your state from the list');
		ustate.focus();
		count++;
	}
	if (!allnumeric(uzip)) {
		alert('ZIP code must have numeric characters only');
		uzip.focus();
		count++;
	}
	if (!validphone(uphone)) {
		alert("You have entered an invalid phone number!");
		uphone.focus();
		count++;
	}
	if(!validmobile(umobile)){
		alert("You have entered an invalid mobile number!");
		umobile.focus();
		count++;
	}
	if (count != 0){
		return false;
	}
}
function userid_validation(uid, mx, my) {
	var uid_len = uid.value.length;
	if (uid_len == 0 || uid_len >= my || uid_len < mx) {
		uid.focus();
		return false;
	}
	return true;
}
function passid_validation(passid, mx, my) {
	var passid_len = passid.value.length;
	if (passid_len == 0 || passid_len >= my || passid_len < mx) {
		
		return false;
	}
	return true;
}
function allLetter(fname) {
	var letters = /^[A-Za-z]+$/;
	if (uname.value.match(letters)) {
		return true;
	} else {
		
		return false;
	}
}
function alphanumeric(uadd) {
	var letters = /^[0-9a-zA-Z]+$/;
	if (uadd.value.match(letters)) {
		return true;
	} else {
		
		return false;
	}
}
function stateselect(ustate) {
	if (ustate.value == "Default") {
		
		return false;
	} else {
		return true;
	}
}
function cityselect(ucity) {
	if (ucity.value == "Default") {
		
		return false;
	} else {
		return true;
	}
}
function allnumeric(uzip) {
	var numbers = /^[0-9]+$/;
	if ((uzip.value.match(numbers)) && (uzip.value > 9999  && uzip.value < 100000)) {
		return true;
	} else {
		
		return false;
	}
}
function validateEmail(uemail) {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (uemail.value.match(mailformat)) {
		return true;
	} else {
		
		return false;
	}
}

function validphone(uphone) {
	var numbers = /^[0-9]+$/;
	var phone_len = phone.value.length;
	if ((uphone.value.match(numbers)) && (phone_len == 10)) {
		return true;
	} else {
		
		return false;
	}
}

function validmobile(umobile) {
	var numbers = /^[0-9]+$/;
	var mobile_len = phone.value.length;
	if ((umobile.value.match(numbers)) && (mobile_len == 10)) {
		return true;
	} else {
		
		return false;
	}
}
/*function validsex(umsex, ufsex) {
	x = 0;

	if (umsex.checked) {
		x++;
	}
	if (ufsex.checked) {
		x++;
	}
	if (x == 0) {
		alert('Select Male/Female');
		umsex.focus();
		return false;
	} else {
		alert('Form Succesfully Submitted');
		window.location.reload();
		return true;
	}
}*/



