function Wishlist_to_cart(isbn){
    
   
    $.ajax({
 	    url: '/SapeStore/moveToCartFromWishlist',
 	    type: 'GET',
 	    data: {isbn : isbn},
 	    success: function(response){
 	    	//alert(response);
 	    	//location.href = "/SapeStore/viewCart";
 	    }
 	});
    
}

function removefromWishList(isbn){
    $.ajax({
 	    url: '/SapeStore/removeFromWishlist',
 	    type: 'GET',
 	    data: {isbn : isbn},
 	    success: function(response){
 	    	//alert(response);
 	    	//location.href = "/SapeStore/removeFromWishlist";
 	    }
 	});
    
}

function addToWishList(isbn){
	
	 $.ajax({
	 	    url: '/SapeStore/addToWishlist',
	 	    type: 'GET',
	 	    data: {isbn : isbn},
	 	    success: function(response){
	 	    	
	 	if(response!="false"){
	 		//alert(response);	
	 	}else{
	 		location.href = "/SapeStore/login.jsp";
	 	}
	 	    }
	 	});
}
