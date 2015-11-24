	$(function() {
		$("#tabs").tabs();
	});
	function clearContents() {
		document.getElementById("contactText").disabled = true;
	}
	function clearContentsPolicy() {
		document.getElementById("policyText").disabled = true;
	}
	function makeCall() {
		if (($("#contactText").val().trim()) != "") {
			$.ajax({
				type : "GET",
				url : "/SapeStore/admin/contactUs",
				dataType : 'text/javascript',
				data : {
					'contactText' : $("#contactText").val()
				},
				success : function(result) {
					alert("success");
				}
			});
			document.getElementById("message").innerHTML = "<p style="+"'color: red'"+">Contact Us page content has been saved successfully.</p>";
		} else {
			document.getElementById("message").innerHTML = "<p style="+"'color: red'"+">Text box empty.</p>";

		}
		document.getElementById("edit").disabled = false;
		document.getElementById("contactText").disabled = true;
	}
	function makeCallPolicy() {
		if (($("#policyText").val().trim()) != "") {
			$.ajax({
				type : "GET",
				url : "/SapeStore/admin/policy",
				dataType : 'text/javascript',
				data : {
					'policyText' : $("#policyText").val()
				},
				success : function(result) {
					alert("success");
				}
			});
			document.getElementById("message1").innerHTML = "<p style="+"'color: red'"+">Privacy Policy page content has been saved successfully.</p>";
		} else {
			document.getElementById("message1").innerHTML = "<p style="+"'color: red'"+">Text box empty.</p>";

		}
		document.getElementById("editPolicy").disabled = false;
		document.getElementById("policyText").disabled = true;
	}