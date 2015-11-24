<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="adminHeader.jsp" flush="true" />
<link rel="stylesheet" href="/SapeStore/css/normalize.css">
<link rel="stylesheet" href="/SapeStore/css/main.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="/SapeStore/css/override.css">
<script src="/SapeStore/js/manageOrders.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- Add your site or application content here -->
			<div id="wrapper" class="homeAdmin">
				<!-- header starts-->
				<header>
					<div id="header">
						<ul class="topLinks hide">
							<li><input name="include_books" type="checkbox"
								value="include_books"> <a
								title="Add books from Partner Store" href="javacript:void(0)">Include
									books from Partner Store</a></li>
							<li><a class='inline' href="#shoppingCart"><img
									src="/SapeStore/img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
							<li class="cartNum">0</li>
						</ul>
						<!-- in case of admin hide this and display another one -->
						<ul class="topLinks">
							<li><c:choose>
									<c:when test="${not empty userId}">
						Welcome ${username}
					</c:when>
								</c:choose></li>
						</ul>
							<div class="col-sm-3">
							<div class="left-sidebar">
								<h2>Links</h2>
								<div class="panel-group category-products" id="accordian">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="/SapeStore/admin/manageInventory">Manage
													Inventory</a>
											</h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title-highlighted">
												<a href="/SapeStore/admin/manageOrders">Manage Orders</a>

											</h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="/SapeStore/admin/purchasedRentedReport">Manage
													Reports</a>
											</h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="/SapeStore/admin/policyEdit">Manage Pages</a>
											</h4>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</header>

				<!-- header ends -->
				<div class="col-sm-9 padding-right">
					<div class="features_items">
						<!--features_items-->
						<h2 class="title text-center">Orders</h2>
						<div id="message" style="color: red;"></div>
						<section style="height: 650px; margin-bottom: 20px;">

							<%-- <form:form method="POST" 				name="updateForm"
				style="height: 25px; font-size: initial; width: 1000px"
				commandName="rentedUpdateForm"> --%>
							<table>
								<thead
									style="text-align: left; font-size: 14px; font-family: SapientSansMedium; font-weight: regular; color: #000000">
									<tr style="border-bottom: 2px solid black;">
										<th style="width: 10%">Customer Id</th>
										<th style="width: 10%">Order Number</th>
										<th style="width: 10%">Item</th>
										<th style="width: 15%">Rented/ Purchased</th>
										<th style="width: 10%">Price</th>
										<th style="width: 15%">Return Date</th>
										<th style="width: 10%">Dispatch</th>
										<th style="width: 10%">Payment</th>
										<th style="width: 10%">Return</th>
									</tr>

								</thead>



								<%-- <c:forEach items="${adminReportsList}" var="current"> --%>
								<c:forEach items="${toDispatchOrdersList}" var="entry">
									<tr>
										<c:set var="counter" value="1" scope="page" />
										<c:forEach items="${entry.value}" var="current">

											<c:choose>
												<c:when test="${counter == '1'}">
													<td class="bookTitle_td" style="width: 10%">
														${current.customerId}</td>
													<c:set var="counter" value="${counter + 1}" scope="page" />
												</c:when>
												<c:otherwise>
													<td class="bookTitle_td" style="width: 10%"></td>

												</c:otherwise>

											</c:choose>

											<td class="bookTitle_td" style="width: 10%">
												${current.orderNumber}</td>
											<td class="bookAuthor_td" style="width: 10%">${current.itemName}</td>

											<c:set var="string" value="${current.type}">


											</c:set>
											<c:choose>
												<c:when test="${ string == 'PURCHASED' }">
													<td class="publisherName_td" style="width: 15%">P</td>
												</c:when>
												<c:otherwise>
													<td class="publisherName_td" style="width: 15%">R</td>

												</c:otherwise>
											</c:choose>
											<%-- <td class="publisherName_td" style="width: 20%">${current.type}</td>  --%>
											<c:choose>
												<c:when test="${ string == 'PURCHASED' }">
													<td class="publisherName_td" style="width: 10%">$ ${current.purchasePrice}</td>
												</c:when>
												<c:otherwise>
													<td class="publisherName_td" style="width: 10%">$ ${current.purchasePrice/10}</td>

												</c:otherwise>
											</c:choose>
											<%-- <td class="bookTitle_td" style="width: 20%">  ${current.purchasePrice}</td> --%>


											<c:set var="string1" value="${current.expectedReturnDate}" />
											<c:set var="string2" value="${fn:substring(string1,0,10)}" />


											<td class="bookAuthor_td" style="width: 15%">${string2}</td>
											<c:set var="orderDispatched" value="${current.dispatched}"></c:set>
											<c:set var="orderPaid" value="${current.paid}"></c:set>
											<form:form
												action="dispatchSlip?dispatchList=${current.orderItemNumber}"
												method="POST">
												<c:choose>
													<c:when test="${orderDispatched}">
														<td><input type="submit" value="Dispatch" style="color:red;"
															id="dispatch" disabled="disabled"></td>
													</c:when>
													<c:otherwise>
														<td><input type="submit" value="Dispatch"
															id="dispatch"></td>
													</c:otherwise>
												</c:choose>
											</form:form>

											<form:form
												action="paymentSlip?paymentId=${current.orderItemNumber}"
												method="POST">

												<c:choose>

													<c:when test="${orderDispatched}">
														<c:choose>
															<c:when test="${orderPaid}">
																<td><input type="submit" value="Payment" style="color: red;"
																	id="payment" class="paymentBTN" disabled="disabled"></td>
															</c:when>
															<c:otherwise>
																<td><input type="submit" value="Payment"
																	id="payment" class="paymentBTN"></td>
															</c:otherwise>
														</c:choose>
													</c:when>

													<c:otherwise>

														<td><input type="submit" value="Payment" id="payment"
															class="paymentBTN" disabled="disabled"></td>

													</c:otherwise>
												</c:choose>
											</form:form>



											<c:choose>
												<c:when test="${ string == 'PURCHASED' }">
													<td class="publisherName_td" style="width: 10%"></td>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${orderPaid}">
															<td><input id="btn" type="button"
																onclick="getEmail()" style="outline: none"
																value="Follow Up"
																name='${current.emailId}#${current.customerId}#${current.itemName}#${current.expectedReturnDate}'>
															</td>
														</c:when>
														<c:otherwise>
															<td><input id="btn" type="button"
																onclick="getEmail()" style="outline: none"
																value="Follow Up" disabled="disabled"
																name='${current.emailId}#${current.customerId}#${current.itemName}#${current.expectedReturnDate}'>
															</td>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
									</tr>

								</c:forEach>
								<br>


								</c:forEach>

							</table>
					</div>


					<div
						style="margin-left: 0em; float: left; margin-bottom: 1em; margin-top: 1em; height: 30px;">
						<!-- <input type="button" name="dispatch" style="font-family: Georgia;"
						id="dispatch" value="DISPATCH ORDER(S)" /> -->
					</div>
					<div
						style="float: left; margin-bottom: 1em; margin-top: 1em; margin-left: 1em; height: 30px;">
						<!-- <input type="submit" name="return" style="font-family: Georgia;"
						id="return" name="method:returnOrder" value="RETURN RECEIVED"
						onclick="beforeReturn()" /> -->
					</div>
					<div id="pageNavPosition1" align="center"></div>
					`
					<script type="text/javascript">
						var pager = new Pager('tablepaging', 10);
						pager.init();
						pager.showPageNav('pager', 'pageNavPosition1');
						pager.showPage(1);
					</script>
					<script src="/SapeStore/js/plugins.js"></script>
					<script src="/SapeStore/js/main.js"></script>


					</section>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="true" />
<!-- 	<script src="js/manageOrdersLinks.js"></script> -->
<!-- 	<script src="js/manageOrders.js"></script> -->
</body>
</html>