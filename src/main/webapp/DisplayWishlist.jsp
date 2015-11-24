<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />
<style type="text/css">
.cart_menu {
	background: #6697C9;
	color: #fff;
	font-size: 26px;
	font-family: 'Roboto', sans-serif;
	font-weight: normal;
}
</style>
<section id="cart_items">
	<div class="container">
		<h2>Your Wishlist</h2>
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">Item</td>
						<td class="description">Book Title</td>
						<td class="price">Price</td>
						<td class="quantity"></td>
						<td class="total"></td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty Wishlist.wishListItems}">
							<c:forEach items="${Wishlist.wishListItems}" var="current">
								<tr id="row-${current.isbn}">
									<td class="cart_product"><a href=""><img
											src="${current.bookThumbImage}" alt=""></a></td>
									<td class="cart_description">
										<h4>
											<a href="">${current.bookTitle}</a>
										</h4>
										<p>ISBN : ${current.isbn }</p>
									</td>

									<td class="cart_price">$${current.bookPrice}</td>

									<td id="add-${current.isbn}"
										class="cart_total cart_total_price"><a href="#"
										onclick="Wishlist_to_cart('${current.isbn}')"
										class="btn btn-default add-to-cart"><i
											class="fa fa-shopping-cart"></i>Buy</a></td>
									<td class="cart_delete"><a id="remove-${current.isbn}"
										class="cart_quantity_delete" href=""
										onclick="removefromWishList('${current.isbn}')"><i
											class="fa fa-times"></i></a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td>
									Your have no item in your wishlist.
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</section>

<script>
	var addId = '#add-'.concat('${current.isbn}');
	$(addId).click(
			function() {
				var rowId = '#row-'.concat('${current.isbn}').concat('-')
						.concat('${current.type}');

				// FADE ROW ON REMOVE
				$(rowId).fadeOut('fast');
				Wishlist_to_cart('${current.isbn}'); // add(isbn)
			});
</script>
<script src="js/Wish_to_cart.js"></script>
<script type="text/javascript">
	var pager = new Pager('tablepaging', 10);
	pager.init();
	pager.showPageNav('pager', 'pageNavPosition');
	pager.showPage(1);
</script>
<script src="js/Cart.js"></script>
<jsp:include page="footer.jsp" flush="true" />

