<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" flush="true" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.0.1/jquery.rateyo.min.css">
<script src="js/jRate.min.js"></script>
<script src="js/jquery.rateyo.js"></script>
<script>
	
	function loadPage(list) {

		location.href = list.options[list.selectedIndex].value;
		
	}
	rate = 0;

	$(function() {

		$("#demo-1").jRate({
			rating : rate,
			strokeColor : 'blue',
			startColor : "cyan",
			endColor : "blue",
			width : 15,
			height : 15,
			backgroundColor : '#C0C0C0',
			onSet : function(rating) {
				rate = rating;
				//console.log("OnSet: Rating: " + rating);
			}
		});
	});

	function checkValidation() {
		return (rate != 0);
	}

	function addcomment() {
		if (!checkValidation()) {
			//prompt("Are you sure you don't want to add any comment?");
			alert("Please enter a rating");
			return;
		}

		$.ajax({
			url : '/SapeStore/comments',
			type : 'GET',
			data : {
				isbn : '${isbn}',
				comment : document.getElementById("userComment").value,
				rating : rate
			},
			success : function(response) {
				window.location.href = "/SapeStore/bookDetails?isbn=${isbn}&tab=review";
			},
			error : function(x, e) {
				//alert(x.e);
			}
		});
	}
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
</script>


<section>
	<div class="container">
		<div class="row">
			<jsp:include page="sidePane.jsp" flush="true" />

			<div class="col-sm-9 padding-right">
				<div class="product-details">
					<!--product-details-->
					<div class="col-sm-5">
						<div class="view-product">
							<c:choose>
								<c:when test="${bookModel.fullPath != null}">
									<img src="${bookModel.fullPath}" alt="${bookModel.bookTitle}"
										onerror="this.src='img/products/Large/error.jpg'" />
								</c:when>
								<c:otherwise>
									<img src="img/products/Large/error.jpg"
										alt="${bookModel.bookTitle}" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-sm-7">
						<div class="product-information">
							<!--/product-information-->
							<h2>${bookModel.bookTitle}</h2>
							<p>
								<b>${bookModel.bookAuthor}</b>
							</p>
							<p>
								<b>ISBN: </b>${bookModel.isbn}</p>
							<p>
								<b>Publisher:</b> ${bookModel.publisherName}
							</p>
							<p>
								<span><span style="display: inline" id="book-${isbn}"></span>
									<span style="display: inline;">${rating }</span></span>
								<script>
									$(function() {
										var id = "#book-".concat('${isbn}');
										$(id).rateYo({
											rating : eval('${rating}'),
											ratedFill : "#6697C9",
											starWidth : "30px",
											readOnly : true
										});
									});
								</script>
							</p>
							<p>
								<span> <span>US $${bookModel.bookPrice}</span> <b>Purchase:</b>
									<c:choose>
										<c:when test="${buyAddCartDisabled == true}">
											<button id="purchaseBtn" type="button"
												class="btn btn-default cart"
												onclick="purchaseFunc('${isbn}')" disabled="disabled">
												<i class="fa fa-shopping-cart"></i> Add to cart
											</button>
										</c:when>
										<c:otherwise>
											<button id="purchaseBtn" type="button"
												class="btn btn-default cart"
												onclick="purchaseFunc('${isbn}')">
												<i class="fa fa-shopping-cart"></i> Add to cart
											</button>
										</c:otherwise>
									</c:choose>
								</span>
							</p>
							<p>
								<span> <span>US $${bookModel.rentPrice}</span> <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rent:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;</b> <c:choose>
										<c:when test="${rentAddCartDisabled == true}">
											<button id="rentBtn" type="button"
												class="btn btn-default cart" onclick="rentFunc('${isbn}')"
												disabled="disabled">
												<i class="fa fa-shopping-cart"></i> Add to cart
											</button>
										</c:when>
										<c:otherwise>
											<button id="rentBtn" type="button"
												class="btn btn-default cart" onclick="rentFunc('${isbn}')">
												<i class="fa fa-shopping-cart"></i> Add to cart
											</button>
										</c:otherwise>
									</c:choose>
								</span>
							</p>

							<p>
								<a href="#" onclick="addToWishList('${current.isbn}')"><i
									class="fa fa-plus-square"></i> Add to wishlist </a>
								&nbsp;&nbsp;&nbsp; <a href="#" onclick="showForm()"><i
									class="fa fa-share"></i> Share product</a>
							</p>
							<p>
								<span id="sharedBook" style="color: #6697C9"></span>
							<table>
								<tr id="shareForm" name="shareForm" style="display: none;">
									<td><input id="shareName" type="text" name="name" placeholder="FUll Name" /></td>
									<td><input id="shareEmail" type="text" name="email" placeholder="Email" /></td>
									<td><button onClick="sendData()" id="submitbtn"
											style="display: none;" class="btn btn-default cart">Share</button></td>
								</tr>
							</table>
							</p>
							<script>
								function showForm() {
									$("#shareForm").show();
									$('#submitbtn').show();
									$('#sharedBook').hide();
								}

								function sendData() {
									var name = document.getElementById("shareName").value;
									var isbn = '${isbn}';
									var email = document.getElementById("shareEmail").value;
									if(!checkData(name, email)){
										return;
									}
									$
											.ajax({
												url : '/SapeStore/shareEmail',
												type : 'POST',
												data : {
													isbn : isbn,
													name : name,
													emailId : email
												},
												success : function(response) {
													if (response == "true") {
														$('#sharedBook').show();
														document
																.getElementById("sharedBook").innerHTML = "Book has been shared";
														$("#shareForm").hide();
														$('#submitbtn').hide();
													} else {
														document
																.getElementById("sharedBook").innerHTML = "Invalid Email";
													}

												},
												error : function(x, e) {
													alert(x.e);
												}
											});
								}
							</script>
						</div>
						<!--/product-information-->
					</div>
				</div>
				<!--/product-details-->

				<div class="category-tab shop-details-tab">
					<!--category-tab-->
					<div class="col-sm-12">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#details" data-toggle="tab">Details</a></li>
							<li><a href="#reviews" id = "reviewTab" data-toggle="tab">Reviews
									(${fn:length(list)})</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<div class="tab-pane fade active in" id="details">
							<div class="col-sm-12">
								<div class="single-products">
									<c:if test="${empty bookModel.bookShortDesc}">
										<p id="descriptionParagraph">Description Not Available.</p>
									</c:if>
									<c:if test="${not empty bookModel.bookShortDesc}">
										<p id="descriptionParagraph">${bookModel.bookShortDesc}</p>
									</c:if>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="reviews">
							<c:choose>
								<c:when test="${userLoginName != null }">
									<div class="col-sm-12">
										<p>
											<b>Write Your Review</b>
										</p>
										<textarea id="userComment"
											placeholder="Write your comments about the product. Max 150 characters."></textarea>
										<!-- 	<b>Rating: </b> <img src="images/product-details/rating.png"
										alt="" /> -->
										<h4
											style="margin: 0px; font-family: SapientSansRegular; color: #000000; font-size: 14px;">Your
											Rating</h4>
										<span id="demo-1"
											style="height: 8px; width: 10px; margin: 0px auto;"></span>
										<button type="submit" onclick=addcomment()
											class="btn btn-default pull-right">Submit</button>

									</div>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${empty list}">
									<div class="single-products">
										<p>No reviews available!</p>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-sm-3">
										<form>
											<select id="sort" onchange="loadPage(this.form.elements[0])">
												<option value="" disabled selected style="display: none;">Sort</option>
												<option value="/SapeStore/bookReviewsForDate?isbn=${isbn}">Date</option>

												<option
													value="/SapeStore/bookReviewsForRatings?isbn=${isbn}">Ratings</option>
											</select>
										</form>
									</div>
								</c:otherwise>
							</c:choose>
							<c:forEach var="list" items="${list}">
								<div class="col-sm-12">
									<ul>
										<li><span><i class="fa fa-user"></i>${list.userId}</span></li>
										<li>${list.bookRating}<span><i id="rateYo-${list.userId}"></i></span></li>
										<li><span><i class="fa fa-calendar-o"></i>${list.date}</span></li>
									</ul>
									<p>${list.bookComments}</p>
								</div>
								<script>
									$(function() {
										var id = "#rateYo-"
												.concat('${list.userId}');
										$(id)
												.rateYo(
														{
															rating : eval('${list.bookRating}'),
															ratedFill : "#6697C9",
															starWidth : "20px",
															readOnly : true
														});
									});
								</script>

							</c:forEach>


						</div>
					</div>
				</div>
				<!--/category-tab-->
			</div>
		</div>
	</div>
</section>
<script>
$(document).ready(function(){
	var tab = getParameterByName('tab');
	if(tab=='review')
		$("#reviewTab").click();
});
</script>
<script src="js/cartcounter.js"></script>
<script src="js/Wish_to_cart.js"></script>
<jsp:include page="footer.jsp" flush="true" />