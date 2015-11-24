<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="header.jsp" flush="true" />
<section id="accountNav">
	<!--user account nav-->
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<div class="mainmenu pull-left">
					<ul class="nav navbar-nav collapse navbar-collapse">
						<li><a href="${pageContext.request.contextPath}/Profile">Profile</a></li>
						<li class="dropdown"><a href="#">Transaction History<i
								class="fa fa-angle-down"></i></a>
							<ul role="menu" class="sub-menu">
								<li><a href="${pageContext.request.contextPath}/bookspurchased">Books Purchased</a></li>
								<li><a href="${pageContext.request.contextPath}/booksrented">Books Rented</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!--/user account nav-->
<section id="books_purchased">
	<div class="container">
		<div class="table-responsive books_purchased_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">Name & Author</td>
						<td class="description">ISBN Number</td>
						<td class="price">Rent Amount</td>
						<td class="date">Actual Return Date</td>
						<td class="total">Expected Return Date</td>
						<td class="total">Fine Amount</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${rented}">
						<tr id="${current.categoryName}">
							<td class="cart_product"><img src="${item.image}" />${item.itemName},
								${item.author}</td>
							<td class="cart_description">
								<p>${item.isbn}</p>
							</td>
							<td class="cart_description">
								<p>$ ${item.purchasePrice/10}</p>
							</td>
							<c:set var="string1" value="${item.actualReturnDate}" />
							<c:set var="string2" value="${fn:substring(string1,0,10)}" />
							<td class="cart_description">
								<p>${string2}</p>
							</td>
							<c:set var="string3" value="${item.expectedReturnDate}" />
							<c:set var="string4" value="${fn:substring(string3,0,10)}" />
							<td class="cart_description">
								<p>${string4}</p>
							</td>
							<td>
							$ ${item.lateFee}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>
<!--/#cart_items-->

<jsp:include page="footer.jsp" flush="true" />