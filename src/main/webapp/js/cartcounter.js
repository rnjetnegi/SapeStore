
$(document).ready(function() {
	
//var	storedisbn = JSON.parse(localStorage["isbnvalues"]);

//for	(index = 0; index < storedisbn.length; index++) {
//	$(storedisbn[index])
//	.text("added to cart");
//	
//}


});


function purchaseFunc(isbn){
    //var $purchase = $("#purchaselink");
    var $welcomeBookLink = $('#addToCartLink-' + isbn);
    addToCart = '#addToCartLink-'.concat(isbn);
    $welcomeBookLink.html("Added In Cart");
    $welcomeBookLink.attr("disabled", "disabled");
    //$purchase.disabled = true;
    /*if($purchase.data('clicked')) {
    	
    }
    else {
        $purchase.data('clicked', true);*/
        $.ajax({
	 	    url: '/SapeStore/addToCart',
	 	    type: 'GET',
	 	    data: {isbn : isbn, type : "PURCHASED"},
	 	    success: function(response){
	 	    	counterIncrement();
	 	    	/*$("#purchaseDescription").hide();
	 	    	$("#purchaseDescriptionHidden").show();
	 	    	$purchase.hide();
	 	    	$("#purchaseHiddenImage").show();*/
	 	    	$("#purchaseBtn").attr('disabled','disabled');
	 	    }
	 	});
       
    
      //addlocal(addToCart);
        
    //}
}


function rentFunc(isbn){
    //var $rent = $("rentlink");
    //$rent.disabled = true;
    /*if($rent.data('clicked')) {
    	
    }
    else {
        $rent.data('clicked', true);*/
        $.ajax({
	 	    url: '/SapeStore/addToCart',
	 	    type: 'GET',
	 	    data: {isbn : isbn, type : "RENTED"},
	 	    success: function(response){
	 	    	counterIncrement();
		 	    /*$("#rentDescription").hide();
		 	    $("#rentDescriptionHidden").show();
	 	    	$rent.hide();
	 	    	$("#rentHiddenImage").show();*/
	 	    	$("#rentBtn").attr('disabled','disabled');
	 	    }
	 	});
    //}
}

function counterIncrement() {
    $('#cartCounter').html(function(i, val) { return val*1+1 });

}
