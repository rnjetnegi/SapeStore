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
.less_quantity{
color:red;
}
#tablepaging tr td {
	width: 500px;
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
									src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
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
						<section style="height: 1550px; margin-bottom: 20px;">

								<ul class="nav nav-tabs">
									<li class="active"><a
										href="/SapeStore/admin/purchasedRentedReport">Purchased/Rented</a></li>
									<li><a href="/SapeStore/admin/defaultersReport">Defaulters</a></li>
								</ul>

<!-- 								<div class="tab-content "> -->
<!-- 									<div class="tab-pane active" id="1"> -->
										<table id="tablepaging">
																			<thead
									style="text-align: left; font-size: 14px; font-family: SapientSansMedium; font-weight: regular; color: #000000">
									<tr style="border-bottom: 2px solid black;">
													<th style="width: 5%; word-break: break-word;">Book Name</th>
													<th style="width: 5%; word-break: break-word;">Book Author</th>
													<th style="width: 5%; word-break: break-word;">Order Type</th>
													<th style="width: 5%; word-break: break-word;">Total Order</th>
													<th style="width: 5%; word-break: break-word;">Quantity Left</th>
												</tr>
											</thead>

											<c:forEach items="${adminReportsList}" var="current">
												<tbody>
													<tr
														<c:if test="${current.QUANTITY lt 6}"> class="less_quantity" </c:if>>


														<td class="bookTitle_td" style="width: 5%; word-break: break-word;">${current.BOOK_TITLE}</td>
														<td class="bookAuthor_td" style="width: 5%; word-break: break-word;">${current.BOOK_AUTHOR}</td>
														<td class="purchasetype_td" style="width: 5%">${current.PURCHASE_TYPE}</td>
														<td class="quantity_td" style="text-align: center; width: 5%; word-break: break-word;">${current.CAL}</td>
														<td class="quantity_td" style="text-align: center; width: 5%; word-break: break-word;">${current.QUANTITY}</td>



													</tr>
												</tbody>
											</c:forEach>
										</table>
										<div id="pageNavPosition" align="center"></div>
										</section>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script type="text/javascript">
						var pager = new Pager('tablepaging', 10);
						pager.init();
						pager.showPageNav('pager', 'pageNavPosition1');
						pager.showPage(1);
					</script>
	<script src="/SapeStore/js/managePages.js"></script>
	<script src="/SapeStore/js/manageOrdersLinks.js"></script>
	<script src="/SapeStore/js/DefaulterReport.js"></script>
</body>
</html>