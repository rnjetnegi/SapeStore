<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />
<section id="slider">
	<!--slider-->
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div id="slider-carousel" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#slider-carousel" data-slide-to="0"
							class="active"></li>
						<li data-target="#slider-carousel" data-slide-to="1"></li>
						<li data-target="#slider-carousel" data-slide-to="2"></li>
					</ol>

					<div class="carousel-inner">
						<div class="item active">
							<div class="col-sm-6">
								<h1>THE ART OF THINKING CLEARLY</h1>
								<h2>ROLF DOBELI</h2>
								<p>The Art of Thinking Clearly by world-class thinker and entrepreneur Rolf Dobelli is an eye-opening look at human psychology and reasoning.</p>
								
							</div>
							<div class="col-sm-6">
								<img src="images/products/Large/1.jpg" onerror="this.src='img/products/Thumbnails/error.jpg'"
									class="girl img-responsive" alt="" /> 
<!-- 									<img -->
<!-- 									src="images/home/pricing.png" class="pricing" alt="" /> -->
							</div> 
						</div>
						<div class="item">
							<div class="col-sm-6">
								<h1>ANGELS & DEMONS       </h1>
								<h2>DAN BROWN</h2>
								<p>An ancient secret brotherhood. A devastating new weapon of destruction. An unthinkable target. </p>
								
							</div>
							<div class="col-sm-6">
								<img src="images/products/Large/angelsanddemons.jpg" onerror="this.src='img/products/Thumbnails/error.jpg'"
									class="girl img-responsive" alt="" />
									<!-- <img src="images/home/pricing.png" class="pricing" alt="" /> -->
							</div>
						</div>
						<div class="item">
							<div class="col-sm-6">
								<h1>STEVE JOBS           </h1>
								<h2>WALTER ISAACSON</h2>
								<p>Based on more than forty interviews with Jobs conducted over two years?as well as interviews with more than a hundred family members, friends,</p>
								
							</div>
							<div class="col-sm-6">
								<img src="images/products/Large/2.jpg" onerror="this.src='img/products/Thumbnails/error.jpg'"
									class="girl img-responsive" alt="" />
<!-- 									 <img src="images/home/pricing.png" class="pricing" alt="" /> -->
							</div>
						</div>


					</div>

					<a href="#slider-carousel" class="left control-carousel hidden-xs"
						data-slide="prev"> <i class="fa fa-angle-left"></i>
					</a> <a href="#slider-carousel"
						class="right control-carousel hidden-xs" data-slide="next"> <i
						class="fa fa-angle-right"></i>
					</a>
				</div>

			</div>
		</div>
	</div>
</section>
<!--/slider-->

<section>
	<div class="container">
		<div class="row">
			<jsp:include page="sidePane.jsp" flush="true" />

			<div class="col-sm-9 padding-right">
				<div class="features_items">
					<!--features_items-->
					<h2 class="title text-center">${categoryName}</h2>
					<c:forEach items="${bookList}" var="current">
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<a href="/SapeStore/bookDetails?isbn=${current.isbn}"
											title="${current.bookTitle}">
											 <img src="${current.bookThumbImage}" alt="${current.bookTitle}" onerror="this.src='img/products/Thumbnails/error.jpg'" />
										</a>
										<h2>$${current.bookPrice}</h2>
										<p>${current.bookTitle}</p>
										<c:set var="text" value="Buy" />
										<c:forEach var="item" items="${booksInCart}">
										  <c:if test="${item eq current.isbn}">
										    <c:set var="text" value="Already In Cart" />
										  </c:if>
										</c:forEach>
										<c:choose>
											<c:when test="${text eq 'Already In Cart'}">
												<button id="addToCartLink-${current.isbn}" onclick="purchaseFunc('${current.isbn}')" class="btn btn-default add-to-cart" disabled><i
													class="fa fa-shopping-cart"></i>	
													Already In Cart	
													</button>										
											</c:when>
											<c:otherwise>
													<button id="addToCartLink-${current.isbn}" onclick="purchaseFunc('${current.isbn}')" class="btn btn-default add-to-cart" ><i
													class="fa fa-shopping-cart"></i>	
													Buy
													</button>										
											</c:otherwise>
										</c:choose>

									</div>
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href="#/" onclick="addToWishList('${current.isbn}')" ><i class="fa fa-plus-square"></i>Add
												to wishlist</a></li>
									</ul>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!--features_items-->
			</div>
		</div>
	</div>
</section>
<script src="js/cartcounter.js"></script>
<script src="js/Wish_to_cart.js"></script>
<jsp:include page="footer.jsp" flush="true" />