<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />

<section>
	<div class="container">
		<div class="row">
			<jsp:include page="sidePane.jsp" flush="true" />

			<div class="col-sm-9 padding-right">
				<div class="features_items">
					<!--features_items-->
					<form name="addtoshoppingcartForm" action="addToShoppingCart"
						method="GET">
						<ul>

							<c:if test="${emptyListInfo == 'Result not found' }">
								<p>
									<div class="text-center">
										<strong>Sorry! <br> Results Not Found</strong>
									</div>
								</p>
							</c:if>
							<c:forEach items="${resultBookList}" var="current">
								<div class="col-sm-4">
									<div class="product-image-wrapper">
										<div class="single-products">
											<div class="productinfo text-center">
												<a href="/SapeStore/bookDetails?isbn=${current.isbn}"
													title="${current.bookTitle}"> <img
													src="${current.bookThumbImage}" alt="${current.bookTitle}" onerror="this.src='img/products/Thumbnails/error.jpg'" />
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
												<button href="#/" id="addToCartLink-${current.isbn}" onclick="purchaseFunc('${current.isbn}')" class="btn btn-default add-to-cart" disabled><i
													class="fa fa-shopping-cart"></i>	
													Already In Cart	
													</button>										
											</c:when>
											<c:otherwise>
													<button href="#/" id="addToCartLink-${current.isbn}" onclick="purchaseFunc('${current.isbn}')" class="btn btn-default add-to-cart" ><i
													class="fa fa-shopping-cart"></i>	
													Buy
													</button>										
											</c:otherwise>
										</c:choose>
											</div>
										</div>
										<div class="choose">
											<ul class="nav nav-pills nav-justified">
												<li><a href="#" onclick="addToWishList('${current.isbn}')"><i class="fa fa-plus-square"></i>Add
														to wishlist</a></li>
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>
						</ul>
					</form>

				</div>
				<!--features_items-->
			</div>
		</div>
	</div>
</section>
<script src="js/cartcounter.js"></script>
<script src="js/Wish_to_cart.js"></script>
<jsp:include page="footer.jsp" flush="true" />