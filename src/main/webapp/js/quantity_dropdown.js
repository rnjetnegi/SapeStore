$(function(){
    var $select = $(".1-20");
    for (i=1;i<=20;i++){
        $select.append($('<option></option>').val(i).html(i));
    }
});

function check(isbn, type ,quant, select1, bookPrice, cost){
	$.ajax({
 	    url: '/SapeStore/varyQuantity',
 	    type: 'POST',
 	    data: {isbn : isbn, type : type, quantity : quant},
 	    success: function(response){
 	    	if(response != "true"){
 	    		$(select1).val(response);
 	    		$(cost).text('$ '.concat(parseInt(response) * parseInt($(bookPrice).text().substring(1))));
 	    		alert('Max Quantity that can be selected is '.concat(response));
 	    	}
 	    	
 	    }
 	});
}

function removeItem(isbn, type){
	$.ajax({
 	    url: '/SapeStore/removeFromCart',
 	    type: 'POST',
 	    data: {isbn : isbn, type : type},
 	    success: function(response){
 	    
 	    	//removelocal(isbn);
 	  	
 	    }
 	    
 	});
	
}



//function removelocal(isbn){
//
//	var  addToCart = '#addToCartLink-'.concat(isbn);
//	  
//	  var	storedisbn = JSON.parse(localStorage["isbnvalues"]);
//	  storedisbn.pop(addToCart);
//	  localStorage["isbnvalues"] = JSON.stringify(storedisbn);
//}
