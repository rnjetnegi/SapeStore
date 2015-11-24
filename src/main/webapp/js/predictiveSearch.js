/*Author : Siddhant and Aditi
The code is for predictive search.
It loads up when the page is loaded and then on each key pressed,
it goes to the database and finds by category,author and title.*/

$(document).ready(function() {
	$(function() {
		$("#searchByText").autocomplete({

			source : function(request, response) {
				$.ajax({
					url : "/SapeStore/predictiveSearch",
					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(data) {
						response(data.slice(0,10));
					}
				});
			},
			
			select: function (a, b) {
		        $(this).val(b.item.value);
		        $('.searchBtn').attr('disabled', false);
		    }
		});
	});
});
