$(document).ready(function(){
$("#newAddr_stateId").change(function() {
 
       var $dropdown = $(this);  
       $.getJSON("js/address.json", function(states) {
      
              var key = $dropdown.val();
              var vals = [];                         
              switch(key) {
                     case '1':
                           vals = states.Florida.split(",");
                           break;
                     case '2':
                           vals = states.California.split(",");
                           break;
                     case '3':
                           vals = states.Alabama.split(",");
                           break;
                     case '4':
                           vals = states.Texas.split(",");
                           break;
                     case '5':
                           vals = states.NewJersey.split(",");
                           break;
                     case '6':
                           vals = states.Alaska.split(",");
                           break;
                     case '7':
                           vals = states.Georgia.split(",");
                           break;
                     case '8':
                           vals = states.Indiana.split(",");
                           break;
                     case '9':
                           vals = states.Ohio.split(",");
                           break;
                     case '10':
                           vals = states.Michigan.split(",");
                           break;
                          
                     /* case 'base':
                           vals = ['Please choose from above']; */
              }
             
              var $secondChoice = $("#newAddr_cityId");
              $secondChoice.empty();
              $.each(vals, function(index, value) {
                     $secondChoice.append("<option value='"+value+"'>" + value + "</option>");
              });
 
       });
});
});



function validateForm() {
	var count = 0;
	var name = document.forms["newAddr"]["newAddr_name"].value;
	var aline1 = document.forms["newAddr"]["newAddr_addressLine1"].value;
	var aline2 = document.forms["newAddr"]["newAddr_addressLine2"].value;
	var city = document.forms["newAddr"]["newAddr_cityId"].value;
	var state = document.forms["newAddr"]["newAddr_stateId"].value;
	var zip = document.forms["newAddr"]["newAddr_postalCode"].value;
	var phone = document.forms["newAddr"]["newAddr_phone"].value;
	
	var isValidPhone = /^\(?([0-9]{3})\)?[- ]?([0-9]{3})[- ]?([0-9]{4})$/;
	var isValidZip = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
	var isValidName = /^[a-z ,.'-]+$/i;
	var isAddress = /^[0-9a-zA-Z-/,\s]+$/;

	if (name == null || name == "" || isValidName.test(name) == false) {
		document.getElementById("nameErrorMessage").innerHTML = "<span class="
				+ "'error'" + ">Please provide a valid username.</span>";
		count++;
	} else {
		document.getElementById("nameErrorMessage").innerHTML = "";
	}

	if (aline1 == null || aline1 == "") {
		document.getElementById("addr1ErrorMessage").innerHTML = "<span class="
				+ "'addr1ErrorMessage'"
				+ ">Address cannot be empty.</span>";
		count++;
	} else if (aline1.length > 22) {
		document.getElementById("addr1ErrorMessage").innerHTML = "<span class="
				+ "'addr1ErrorMessage'" + ">Address too long.Only 22 characters allowed.</span>";
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

	if (city == null || city == "" || city == "NONE") {
		document.getElementById("cityErrorMessage").innerHTML = "<span class="
				+ "'error'" + ">Please select a city.</span>";
		count++;
	} else {
		document.getElementById("cityErrorMessage").innerHTML = "";
	}

	if (state == null || state == "" || state == "no") {
		document.getElementById("stateErrorMessage").innerHTML = "<span class="
				+ "'error'" + ">Please select a state.</span>";
		count++;
	} else {
		document.getElementById("stateErrorMessage").innerHTML = "";
	}

	if (zip == null || zip == "" || isValidZip.test(zip) == false) {
		document.getElementById("zipErrorMessage").innerHTML = "<span class="
				+ "'error'" + ">Please provide a valid zip code in XXXXX or XXXXX-XXXX format.</span>";
		count++;
	} else {
		document.getElementById("zipErrorMessage").innerHTML = "";
	}

	if (phone == null || phone == "" || isValidPhone.test(phone) == false) {
		document.getElementById("phoneErrorMessage").innerHTML = "<span class="
				+ "'error'"
				+ ">Please provide a 10 digit valid phone number.</span>";
		count++;
	} else {
		document.getElementById("phoneErrorMessage").innerHTML = "";
	}

	if (count != 0) {
		return false;
	}
	return true;
}

//$(document).ready(function() {
//	$(".inline").colorbox({
//		inline : true,
//		width : "auto",
//		height : "auto"
//	});
//	$(".callbacks").colorbox({
//		onOpen : function() {
//			alert('onOpen: colorbox is about to open');
//		},
//		onLoad : function() {
//			alert('onLoad: colorbox has started to load the targeted content');
//		},
//		onComplete : function() {
//			alert('onComplete: colorbox has displayed the loaded content');
//		},
//		onCleanup : function() {
//			alert('onCleanup: colorbox has begun the close process');
//		},
//		onClosed : function() {
//			alert('onClosed: colorbox has completely closed');
//		}
//	});
//});

