<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="adminHeader.jsp" flush="true" />
<!-- <div id="wrapper" class="homeAdmin"> -->
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
<!-- 												class="panel-title-highlighted"> -->
<!-- 												<a href="/SapeStore/admin/manageOrders">Manage Orders</a> -->
<!-- 											</h4> -->
<!-- 											<h4 name="link" id="rep" onclick="changLink(rep)" -->
<!-- 												class="panel-title"> -->
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
<!-- 				<h2 class="title text-center">Payment Slip</h2> -->
				<section style="height: 650px; margin-bottom: 20px;">
								<div id="mainContent">
				<script>
			function printDiv(divName) {
				 //document.getElementById('Print').setAttribute("style","display:none;");
				 //document.getElementById('Back').setAttribute("style","display:none;");
			     var prtContent = document.getElementById(divName);
			     var WinPrint = window.open();
			     WinPrint.document.write(prtContent.innerHTML);
			     WinPrint.document.close();
			     WinPrint.focus();
			     WinPrint.print();
			     WinPrint.close();
			     //document.getElementById('Print').setAttribute("style","display:;");
			     //document.getElementById('Back').setAttribute("style","display:;");
			}
			</script>
								
				<div id = "pdf"  align="center">
				<h2 class="title text-center" style="text-align: center;">Payment Slip</h2>
				<table class="tableT" style="width: 100%;">
					<thead
						style="text-align: left; font-size: 14px; font-family: SapientSansMedium; font-weight: regular; color: #000000">
						<tr style="border-bottom: 2px solid black;">
							<th style="width: 20%;">Customer Name</th>
							<th style="width: 20%;">Order Number</th>
							<th style="width: 20%;">Order Item Number</th>
							<th style="width: 20%;">Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${paidOrder}" var="current">
							<tr>
								<td style="width: 20%;">${current.customerName}</td>
								<td style="width: 20%;">${current.orderNumber}</td>
								<td style="width: 20%;">${current.orderItemNumber}</td>
								<c:set var="string" value="${current.type}"/>
								<c:choose>
												<c:when test="${ string == 'PURCHASED' }">
													<td class="publisherName_td" style="width: 10%">$ ${current.purchasePrice}</td>
												</c:when>
												<c:otherwise>
													<td class="publisherName_td" style="width: 10%">$ ${current.purchasePrice/10}</td>

												</c:otherwise>
											</c:choose>
								<!-- <td style="width: 20%;">$${current.purchasePrice/10}</td> -->
							</tr>


						</c:forEach>
					</tbody>
				</table>
				</div>




				<div class="text-center">
					<input type="button" id="Print" value="Print" name="Print"
						style="height: 25px; font-size: initial" onclick="printDiv('pdf');" />
				</div>



			</div>
			</section>
			</div>
		</div>
		</div>
		</div>
		</div>
<!-- 	<div class="clearfix"></div> -->
	<jsp:include page="footer.jsp" flush="true" />
	</body>
	</html>