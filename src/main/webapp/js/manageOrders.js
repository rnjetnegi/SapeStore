	/* $(function() {
		$("#tabs").tabs();
	}); */

//	$(document).ready(function() {
//
//		$("#payment").attr('disabled', 'disabled');
//
//	});
//	function makeCall() {
//
//		$("#dispatch").attr('disabled', 'disabled');
//		$("#payment").removeAttr('disabled');//enable
//
//	}
//
//	function cancelIt() {
//		location.reload();
//	}

	function getEmail() {
		var Values = "";
		$('input:button').each(function() {
			if (this.name != null) {
				Values = Values + this.name;
			}
			Values = Values + ":";
		});

		$.ajax({
			type : "GET",
			url : "/SapeStore/admin/sendRentedEmail",
			dataType : 'text/javascript',
			data : {
				'emailIds' : Values
			},
			success : function(result) {
				alert("success");
			}
		});
		document.getElementById("message").innerHTML = "Email sent successfully.";

	}