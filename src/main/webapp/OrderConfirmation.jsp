<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />
	<style type="text/css">
		.address{	    
			padding-left: 20px;
    		padding-top: 20px;
    		padding-right: 0px;
    		padding-bottom: 3px;
		}
		.cart_menu{
			background: #6697C9 none repeat scroll 0% 0%;
			color: #FFF;
			font-size: 22px;
			font-family: "Roboto",sans-serif;
			font-weight: normal;	
		}
	</style>
	<div class="container">
		<div class="col-sm-11 col-sm-offset-1">
			<h2>Confirm your order</h2>
			<div class="jumbotron Address col-sm-5">
						<h4><c:out value="${user}"/> </h4>
						<p><c:out value="${addressLine1}"/></p>
						<p><c:out value="${cityName}"/>, <c:out value="${stateName}"/></p>
					</div>
		</div>
		
		<div class="table-responsive cart_info col-lg-12">
		<table class="table table-condensed table-striped">
			<thead>
				<tr class="cart_menu">
					<td class="col-lg-3">Book Title</td>
					<td class="col-lg-2">ISBN</td>
					<td class="col-lg-2">Type</td>
					<td class="col-lg-2">Quantity</td>
					<td class="col-lg-2">Price</td>
					<td class="col-lg-1">Sub-Total</td>
				</tr>
				
			</thead>
		
		
		
			<tbody>
		<!-- Printing items -->
		<c:forEach items="${cart}" var="current"> 
				<tr style="font-size: 20px;">
					<td style="font-size: 16px">${current.title}</td>
					<td> ${current.isbn }</td><!-- Book ISBN -->
					<td > ${current.type} </td>	<!-- BOOK TYPE(RENT OR BUY) -->
					<td> ${current.quantity} </td>	<!-- BOOK QUANTITY -->
					<td > ${current.bookPrice} </td>	<!-- BOOK PRICE-->
					<td >${current.quantity*current.bookPrice} </td> <!-- SUB TOTAL -->
				</tr>
				
			</tbody>
		</c:forEach>
				<tr style="font-size: 20px;">
					<td style="font-size: 16px"></td>
					<td></td><!-- Book ISBN -->
					<td ></td>	<!-- BOOK TYPE(RENT OR BUY) -->
					<td></td>	<!-- BOOK QUANTITY -->
					<td ></td>	<!-- BOOK PRICE-->
					<td >Total:  ${total}</td> <!-- SUB TOTAL -->
				</tr> 
	</table>
	<div class="col-xs-offset-11">	
		<form action="/SapeStore/confirmOrder" method="GET"> <!-- action="${pageContext.request.contextPath}/afterRegistration" -->
			<button class="btn btn-lg btn-warning">Confirm</button>
		</form>
	</div>
	</div>
</div>


<jsp:include page="footer.jsp" flush="true" />