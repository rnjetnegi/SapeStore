	$(document).ready(function() {
		if (($("#policyText").val()) == "") {

			document.getElementById("editPolicy").disabled = true;

		} else {
			document.getElementById("policyText").disabled = true;
			document.getElementById("savePolicy").disabled = true;
			document.getElementById("cancelPolicy").disabled = true;

		}

	})

	function editCall() {
		document.getElementById("policyText").disabled = false;
		document.getElementById("editPolicy").disabled = true;
		document.getElementById("savePolicy").disabled = false;
		document.getElementById("cancelPolicy").disabled = false;
		return false
	}

	function cancelIt() {
		location.reload();
	}