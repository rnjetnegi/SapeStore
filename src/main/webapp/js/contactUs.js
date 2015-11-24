	$(document).ready(function() {
		if (($("#contactText").val()) == "") {

			document.getElementById("edit").disabled = true;

		} else {
			document.getElementById("contactText").disabled = true;
			document.getElementById("save").disabled = true;
			document.getElementById("cancel").disabled = true;

		}

	})

	function editCall1() {
		document.getElementById("edit").disabled = true;
		document.getElementById("contactText").disabled = false;
		document.getElementById("save").disabled = false;
		document.getElementById("cancel").disabled = false;
		return false;
	}

	function cancelIt() {
		location.reload();
	}
