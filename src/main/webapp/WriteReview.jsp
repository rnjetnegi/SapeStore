<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Give Your Ratings</title> -->
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
	
	rate = 0;
	
	$(function() {
	
		$("#demo-1").jRate({
			rating : rate,
			strokeColor : 'blue',
			startColor: "cyan",
			endColor: "blue",
			width : 15,
			height : 15,
			backgroundColor: '#C0C0C0',
			onSet : function(rating) {
				rate = rating;
				//console.log("OnSet: Rating: " + rating);
			}
		});
	});
	
	function checkValidation(){
		return (document.getElementById("userComment").value == "" && rate == 0);
	}
	
	function addcomment(){
		
		if(checkValidation()){
			alert("Please fill either the Review or the Rating");
			return;
		}
		
		$.ajax({
	 	    url: '/SapeStore/comments',
	 	    type: 'POST',
	 	    data: {isbn : '${isbn}', comment : document.getElementById("userComment").value , rating: rate},
	 	    success: function(response){
	 	    	//alert("COMMENT ADDED"+document.getElementById("userComment").value + rate );
	 	    	//$('#userComment').val("");
				$('#cboxClose').click();
	 	    }
	 	});
	}
	
	$(document).ready(function() {
	    var text_max = 150;
	    $('#MaxNumberOfChar').html(text_max + ' Char\'s remaining');

	    $('#userComment').keyup(function() {
	        var text_length = $('#userComment').val().length;
	        var text_remaining = text_max - text_length;

	        $('#MaxNumberOfChar').html(text_remaining + ' Char\'s remaining');
	    });
	}); 
	
</script>
	<h2
		style="font-family: SapientSansRegular; color: #000000; font-size: 22px; padding-bottom: 10px">Write
		a Review</h2>
	<div class="BookDetails" style="display: block; float: left; padding-top: 10px">
		<img alt="Book Image" src="${bookModel.fullPath}" style="width: 152px; height: 222px" onerror="if (this.src != 'error.jpg') this.src = 'img/products/error.jpg';">
		<h5
			style="font-size: 18px; font-family: SapientCentroSlabBold; color: #000000; margin: 0px">${bookModel.bookTitle}</h5>
		<h5
			style="font-size: 13px; font-family: SapientSansRegular; color: #000000; margin: 0px;">${bookModel.bookAuthor}</h5>
	</div>

	<div style="float: left; padding-left: 20px">
		<h4
			style="margin: 0px; font-family: SapientSansRegular; color: #000000; font-size: 14px;">Your
			Comment</h4>
		<form id="writeReview" action="">
			<textarea id="userComment" rows="13" cols="80" maxlength="150"></textarea>
			<h4
				style="margin: 0px; font-family: SapientSansRegular; color: #000000; font-size: 14px;">Your
				Rating</h4>
				<span id="demo-1" style="height:8px;width: 10px; margin:0px auto; "></span>
				<span id="MaxNumberOfChar" style="margin-left: 20px;font-family: SapientSansRegular;font-size:14px;"></span>
			<span style="float: right;"> 
				<input id="PostComment" onclick="addcomment(); return true;" type="button" value="Post Comment"
				style="background-color: #21addd; color: #ffffff; font-size: 18px; font-family: SapientSansRegular">
				<!-- <a href="" id="cancelPost" class="close" style="font-size: 14px; color: #de2728;">Cancel Post</a>-->
			</span> 
		</form>
	</div>

<script src="js/jRate.min.js"></script>

<!-- </body>
</html> -->