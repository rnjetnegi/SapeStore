<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />
<section id="cart_items">
	<div class="container">
		<h2>Confirm your Shopping Cart</h2>
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">Item</td>
						<td class="description">ISBN</td>
						<td class="quantity">Quantity</td>
						<td class="description">Type</td>
						<td class="price">Price</td>
					</tr>
				</thead>
				<tbody>
					<!-- Printing All Books  -->
					<c:forEach var="Booklist" items="${ShoppingCart}">
						<tr>
							<td><c:out value="${Booklist.title}" /></td>
							<!-- Book Title -->
							<td><c:out value="${Booklist.isbn}" /></td>
							<!-- Book ISBN -->
							<td><c:out value="${Booklist.quantity}" /></td>
							<!-- Book Quantity -->
							<td><c:set value="${Booklist.type}" var="Booktype" /> <c:out
									value="${Booktype}" /></td>
							<!-- Book type Purchased or Rented -->
							<td><c:choose>
									<c:when test="${Booktype eq 'purchase'}">
										<!-- When Book is purchased -->
										<c:out value="${Booklist.bookPrice}" />
									</c:when>
									<c:when test="${Booktype eq 'rent'}">
										<c:out value="${Booklist.rentPrice}" />
										<!-- when Book is rented -->
									</c:when>
								</c:choose> $<c:out value="${Booklist.total}" /></td>
						</tr>

					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>Sub-Total: $<c:out value="${total}"></c:out>
						</td>
					</tr>
					<tr>
						<td>
							<form action="/SapeStore/shipAddress" method="GET">
								<button class="btn btn-primary">Confirm</button>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</section>
<script type="text/javascript" src="js/registerValidation.js">
<!--
	
//-->
</script>
<jsp:include page="footer.jsp" flush="true" />