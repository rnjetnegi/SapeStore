<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>SapeStore-Home</title>
<jsp:include page="adminHeader.jsp" flush="true" />
<link rel="stylesheet" href="/SapeStore/css/normalize.css">
<link rel="stylesheet" href="/SapeStore/css/main.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="/SapeStore/css/override.css">
<style>
.flaged{
color:red;
}
</style>
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
								value="include_books" checked> <a
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
<!-- 						<div class="col-sm-3"> -->
<!-- 							<div class="left-sidebar"> -->
<!-- 								<h2>Links</h2> -->
<!-- 								<div class="panel-group category-products" id="accordian"> -->
<!-- 									<div class="panel panel-default"> -->
<!-- 										<div class="panel-heading"> -->
<!-- 											<h4 name="link" id="inv" onclick="changLink(inv)" -->
<!-- 												class="panel-title"> -->
<!-- 												<a href="/SapeStore/admin/manageInventory">Manage Inventory</a> -->
<!-- 											</h4> -->
<!-- 											<h4 name="link" id="ord" onclick="changLink(ord)" -->
<!-- 												class="panel-title"> -->
<!-- 												<a href="/SapeStore/admin/manageOrders">Manage Orders</a> -->
<!-- 											</h4> -->
<!-- 											<h4 name="link" id="rep" onclick="changLink(rep)" -->
<!-- 												class="panel-title-highlighted"> -->
<!-- 												<a href="/SapeStore/admin/purchasedRentedReport">Manage Reports</a> -->
<!-- 											</h4> -->
<!-- 											<h4 name="link" id="pag" onclick="changLink(pag)" -->
<!-- 												class="panel-title"> -->
<!-- 												<a href="/SapeStore/admin/policyEdit">Manage Pages</a> -->
<!-- 											</h4> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
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
											<h4 class="panel-title">
												<a href="/SapeStore/admin/manageOrders">Manage Orders</a>

											</h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title-highlighted">
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
						<h2 class="title text-center">Reports</h2>
						<section style="height: 1050px; width: 600px; margin-bottom: 20px;">

							<!-- 							<div id="tabs" -->
							<!-- 								style="height: 1000px; margin-left: -12px; border: none;"> -->
							<!-- 								<ul -->
							<!-- 									style="padding-top: 20px; padding-left: 22px; padding-right: 3px;"> -->
							<!-- 									  	<li><a href="#div1">Contact Us</a> </li> -->
							<!-- 									<li><a href="/SapeStore/policyEdit" style="outline: none">Privacy -->
							<!-- 											Policy Page</a></li> -->
							<!-- 									<li><a href="/SapeStore/contactUsEdit" -->
							<!-- 										style="outline: none">Contact Us</a></li> -->

							<!-- 								</ul> -->


							<!-- 							</div> -->
							<div id="" class="container">
								<ul class="nav nav-tabs">
									<li class=""><a
										href="/SapeStore/admin/purchasedRentedReport">Purchased/Rented</a></li>
									<li class="active"><a href="/SapeStore/admin/defaultersReport">Defaulters</a></li>
								</ul>

								<div class="tab-content ">
									<div class="tab-pane active" id="2">
										<table id="tablepage" style="width: 850px;">
											<thead
									style="text-align: left; font-size: 14px; font-family: SapientSansMedium; font-weight: regular; color: #000000">
									<tr style="border-bottom: 2px solid black;">
													<th style="width: .5%">Customer Id</th>
<!-- 													<th style="width: .5%">Book Category</th> -->
													<th style="width: .5%">Book Name</th>
													<th style="width: .5%">Due Date</th>
													<th style="width: .5%">Return Date</th>
													<th style="width: .5%">Return Status</th>
													<th style="width: .5%">Rent Price</th>
													<th style="width: .5%">Late Fee</th>
													<th style="width: .5%">email</th>
												</tr>
											</thead>
											<div id="message" style="color: red;"></div>
											<div id="sendMail">
												<button hidden="true" id="btn" onclick="getEmail()"
													style="outline: none">Send Email</button>
											</div>

											<c:forEach items="${defaultersReportsList}" var="current">

												<tbody>
													<tr
														<c:if test="${current.LATE_FEE >= current.RENT_PRICE}"> class="flaged" </c:if>>
														<td style="width: .5%">${current.USER_ID}</td>
<%-- 														<td style="width: .5%">${current.CATEGORY_ID}</td> --%>
														<td style="width: .5%">${current.BOOK_TITLE}</td>
														<td style="width: .5%">${current.EXPECTED_RETURN_DATE}</td>
														<c:choose>
															<c:when test="${empty current.ACTUAL_RETURN_DATE}">
																<td style="width: .5%">--/--/--</td>
															</c:when>
															<c:otherwise>
																<td style="width: .5%">${current.ACTUAL_RETURN_DATE}</td>
															</c:otherwise>
														</c:choose>
														<td style="width: .5%">${current.RETURN_STATUS}</td>
														<td style="width: .5%">$${current.RENT_PRICE}</td>
														<td style="width: .5%">$${current.LATE_FEE}</td>
														<td style="width: .5%">
															<div id="sendMail">
																<input id="btn" type="button" onclick="getEmail()"
																	style="outline: none" value="Send Email"
																	name='${current.EMAIL_ADDRESS}#${current.CUSTOMER_ID}#${current.BOOK_TITLE}#${current.EXPECTED_RETURN_DATE}#${current.LATE_FEE}#${current.RETURN_STATUS}'>
																<!-- <button id="btn" onclick="getEmail()" style="outline: none">
							Send Email</button> -->
															</div>
														</td>
													</tr>
												</tbody>
											</c:forEach>
										</table>
										<div id="hello" align="center"></div>
									</div>
								</div>
							</div>

						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script src="/SapeStore/js/managePages.js"></script>
	<script src="/SapeStore/js/manageOrdersLinks.js"></script>
	<script src="/SapeStore/js/DefaulterReport.js"></script>
</body>
</html>