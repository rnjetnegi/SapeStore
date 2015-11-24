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
<!-- 												class="panel-title"> -->
<!-- 												<a href="/SapeStore/admin/purchasedRentedReport">Manage Reports</a> -->
<!-- 											</h4> -->
<!-- 											<h4 name="link" id="pag" onclick="changLink(pag)" -->
<!-- 												class="panel-title-highlighted"> -->
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
											<h4 class="panel-title">
												<a href="/SapeStore/admin/purchasedRentedReport">Manage
													Reports</a>
											</h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title-highlighted">
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
						<h2 class="title text-center">Pages</h2>
						<section>

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
							<div id="tabbed">
								<ul class="nav nav-tabs">
									<li class="active"><a href="/SapeStore/admin/policyEdit">Privacy
											Policy</a></li>
									<li><a href="/SapeStore/admin/contactUsEdit">Contact Us Page</a></li>
								</ul>

								<div class="tab-content ">
									<div class="tab-pane active" id="1">
										<div id="div1"
											style="/*border: 1px solid #AAAAAA; margin-left: 15px; background-color: #F0F7F8; height: 920px;*/">
											<div
												style="margin-right: 50px; float: left; margin-top: 10px">
												<div id="message1" style="margin-left: 23px;"></div>

												<p
													style="font-size: 13px; font-weight: bold; margin-left: 24px;">Enter
													your privacy policy details here</p>
											</div>
											<div
												style="/*float: right; margin-left: 100px; margin-right: 15px; margin-top: 15px; margin-bottom: 15px*/">

											</div>
											<form:form action="/SapeStore/admin/policy">
												<input type="hidden" name="hiddenText" id="hiddenText"
													value="${policyText}" />
												<textarea id="policyText" name="policyText" cols="40"
													rows="10"
													style="/*width: 925px; margin-left: 20px; height: 755px*/">${policyText}</textarea>
											</form:form>
											<div id="message1"></div>
											<div align="left"
												style="margin-top: 10px; margin-left: 7px; margin-bottom: 10px">
												<button id="editPolicy" onclick="editCall()"
													style="/*margin-right: 8px; outline: none; font-family: Georgia;*/">Edit
												</button>
												<button id="savePolicy" onclick="makeCallPolicy()"
													style="/*margin-left: 16px; outline: none*/">Save</button>
												<button style="/*margin-left: 30px; outline: none*/"
													id="cancelPolicy" onclick="cancelIt()">Cancel</button>
											</div>
										</div>
									</div>
									<div class="tab-pane" id="2">
										<div id="div1"
											style="/*border: 1px solid #AAAAAA; margin-left: 15px; background-color: #F0F7F8; height: 920px;*/">
											<div
												style="margin-right: 50px; float: left; margin-top: 10px">
												<div id="message" style="margin-left: 23px;"></div>
												<p
													style="font-size: 13px; font-weight: bold; margin-left: 24px;">Enter
													your contact details here</p>
											</div>
											<div
												style="/*float: right; margin-left: 100px; margin-right: 15px; margin-top: 15px; margin-bottom: 15px*/">

											</div>
											<form:form action="/SapeStore/admin/contactUs">
												<input type="hidden" name="hiddenText" id="hiddenText"
													value="${contactText}" />
												<textarea id="contactText" name="contactText" cols="40"
													rows="10"
													style="/*width: 925px; margin-left: 20px; height: 755px*/">${contactText}</textarea>
											</form:form>
											<div id="message"></div>
											<div align="left"
												style="margin-top: 10px; margin-left: 7px; margin-bottom: 10px">
												<button id="edit" onclick="editCall1()"
													style="/*margin-right: 8px; outline: none; font-family: Georgia;*/">Edit
												</button>
												<button id="save" onclick="makeCall()"
													style="/*margin-left: 16px; outline: none*/">Save</button>
												<button id="cancel" style="/*margin-left: 30px; outline: none*/"
													onclick="cancelIt()">Cancel</button>
											</div>
										</div>
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
	<script src="/SapeStore/js/policy.js"></script>
	<script src="/SapeStore/js/manageOrdersLinks.js"></script>
</body>
</html>