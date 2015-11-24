<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />
<section id="cart_items">
	<div class="container">
		<h2>Your Shopping Cart</h2>
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">Item</td>
						<td class="description">Description</td>
						<td class="description">Type</td>
						<td class="price">Price</td>
						<td class="quantity">Quantity</td>
						<td class="total">Sub Total</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${cartSize != 0}">
						<c:forEach items="${ShoppingCart.cartItems}" var="current">
							<tr id="row-${current.isbn}-${current.type}">
								<td class="cart_product"><a
									href="/SapeStore/bookDetails?isbn=${current.isbn}"><img
										src="${current.image}" alt=""
										onerror="this.src='img/products/Thumbnails/error.jpg'"></a></td>
								<td class="cart_description">
									<h4>
										<a href="/SapeStore/bookDetails?isbn=${current.isbn}">${current.title}</a>
									</h4>
									<p>ISBN: ${current.isbn }</p>
								</td>
								<td class="cart_description">
									<h4>${current.type}</h4>
								</td>
								<td id="bookprice-${current.isbn}-${current.type}"
									class="cart_price">$${current.bookPrice}</td>
								<td class="cart_quantity">
									<div class="cart_quantity_button">
										<select class="1-20"
											id="quantityValue-${current.isbn}-${current.type}"></select>
									</div>
								</td>
								<td id="subtotal-${current.isbn}-${current.type}"
									class="cart_total cart_total_price">$${current.bookPrice}</td>
								<td class="cart_delete"><a
									id="remove-${current.isbn}-${current.type}"
									class="cart_quantity_delete" href="#"><i
										class="fa fa-times"></i></a></td>
							</tr>
							<script>
								var select = '#quantityValue-'.concat(
										'${current.isbn}').concat('-').concat(
										'${current.type}');
								$(select)
										.change(

												function() {
													var subtotal = '#subtotal-'
															.concat(
																	'${current.isbn}')
															.concat("-")
															.concat(
																	'${current.type}');
													var bookprice = '#bookprice-'
															.concat(
																	'${current.isbn}')
															.concat("-")
															.concat(
																	'${current.type}');
													//alert($(bookprice).text().substring(1).concat(' ').concat(this.value));
													$(subtotal)
															.text(
																	'$ '
																			.concat(parseInt($(
																					bookprice)
																					.text()
																					.substring(
																							1))
																					* parseInt(this.value)));
													// alert(this.value);
													//alert($(subtotal).text());
													var x = check(
															'${current.isbn}',
															'${current.type}',
															this.value,
															'#quantityValue-'
																	.concat(
																			'${current.isbn}')
																	.concat('-')
																	.concat(
																			'${current.type}'),
															'#bookprice-'
																	.concat(
																			'${current.isbn}')
																	.concat("-")
																	.concat(
																			'${current.type}'),
															'#subtotal-'
																	.concat(
																			'${current.isbn}')
																	.concat("-")
																	.concat(
																			'${current.type}')); // check( isbn, price, quantity) 
												});
								
								var removeId = '#remove-'.concat(
								'${current.isbn}').concat('-').concat(
								'${current.type}');
						$(removeId).click(
								function() {
									var rowId = '#row-'.concat(
											'${current.isbn}').concat(
											'-').concat(
											'${current.type}');

										// FADE ROW ON REMOVE
										$(rowId).fadeOut('fast');
										removeItem('${current.isbn}',
												'${current.type}'); // remove(isbn, type)
										
									});
							
						</script>
					</c:forEach>
					<tr><td><a class="btn btn-default check_out" id="checkout" href="/SapeStore/checkout">Check Out</a></td></tr>

					</c:if>
					<c:if test="${cartSize == 0}">
						<tr>
							<td>You have no item in your cart.</td>
						</tr>
					</c:if>
				</tbody>
			</table>


		</div>
	</div>
</section>
<!--/#cart_items-->
<script src="js/quantity_dropdown.js"></script>
<jsp:include page="footer.jsp" flush="true" />
