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
<script type="text/javascript">
	function submitForm(isbn) {

		var id = document.getElementById(isbn);

		var title = id.getElementsByClassName("bookTitle_td")[0].innerHTML;
		var author = id.getElementsByClassName("bookAuthor_td")[0].innerHTML;
		var publisher = id.getElementsByClassName("publisherName_td")[0].innerHTML;
		var rentAvailable = id.getElementsByClassName("rentAvailable_td")[0].innerHTML;
		var quantity = id.getElementsByClassName("quantity_td")[0].innerHTML;

		var bookPrice = id.getElementsByClassName("bookPrice_td")[0].innerHTML;
		var rentPrice = id.getElementsByClassName("rentPrice_td")[0].innerHTML;
		var isbn = id.getElementsByClassName("isbn_td")[0].innerHTML;
		var bookShortDesc = id.getElementsByClassName("bookShortDesc_td")[0].innerHTML;
		var bookDetailDesc = id.getElementsByClassName("bookDetailDesc_td")[0].innerHTML;
		var thumbPath = id.getElementsByClassName("thumbPath_td")[0].innerHTML;
		var categoryName = id.getElementsByClassName("categoryName_td")[0].innerHTML;
		var fullPath = id.getElementsByClassName("fullPath_td")[0].innerHTML;

		document.getElementById("name_to_submit").value = name;
		document.getElementById("title_to_submit").value = title;
		document.getElementById("author_to_submit").value = author;
		document.getElementById("publisher_to_submit").value = publisher;
		document.getElementById("rent_to_submit").value = rentAvailable;
		document.getElementById("quantity_to_submit").value = quantity;

		document.getElementById("bookPrice_to_submit").value = bookPrice;
		document.getElementById("rentPrice_to_submit").value = rentPrice;
		document.getElementById("isbn_to_submit").value = isbn;
		document.getElementById("bookShortDesc_to_submit").value = bookShortDesc;
		document.getElementById("bookDetailDesc_to_submit").value = bookDetailDesc;
		document.getElementById("thumbPath_to_submit").value = thumbPath;
		document.getElementById("fullPath_to_submit").value = fullPath;
		document.getElementById("categoryName_to_submit").value = categoryName;
		document.editForm.submit();

	}

	function testFunction(isbn) {
		var id = document.getElementById(isbn);
		var rentAvailable = id.getElementsByClassName("rentAvailable_td")[0].innerHTML;
		var quantity = id.getElementsByClassName("quantity_td")[0].innerHTML;
		var author = id.getElementsByClassName("bookAuthor_td")[0].innerHTML;
		var x = rentAvailable;
		var del;
		if (x.trim() == "N") {
			if (quantity > 1) {
				var result = confirm("More than 1 book is available in the inventory. Click Yes, if you want to delete all records.");
				if (result == true) {
					del = "yes";
				}
			} else {
				var result = confirm("Only  1 book is available in the inventory. Click Yes, if you want to delete the records.");
				if (result == true) {
					del = "yes";
				}
			}
			// here
			if (del == "yes") {
				var isbn = id.getElementsByClassName("isbn_td")[0].innerHTML;
				document.getElementById("isbn_to_submit").value = isbn;
				document.editForm.submit();
			}
		} else {
			alert("This book is rented by a customer and cannot be deleted from records");
		}

	}

	function handleSubmit(isbn) {
		var delSubmit = document.getElementById("delSubmit");

		var id = document.getElementById(isbn);
		var rentAvailable = id.getElementsByClassName("rentAvailable_td")[0].innerHTML;
		var quantity = id.getElementsByClassName("quantity_td")[0].innerHTML;
		var author = id.getElementsByClassName("bookAuthor_td")[0].innerHTML;
		var x = rentAvailable;
		var del;
		if (x.trim() == "N") {
			if (quantity > 1) {
				var result = confirm("More than 1 book is available in the inventory. Click Yes, if you want to delete all records.");
				if (result == true) {
					del = "yes";
				}
			} else {
				var result = confirm("Only  1 book is available in the inventory. Click Yes, if you want to delete the records.");
				if (result == true) {
					del = "yes";
				}
			}
			// here
			if (del == "yes") {
				var isbn = id.getElementsByClassName("isbn_td")[0].innerHTML;
				document.getElementById("isbn_to_submit").value = isbn;
				delSubmit.click();
			}
		} else {
			alert("This book is rented by a customer and cannot be deleted from records");
		}

	}
</script>
<style>
.errMsg {
	color: red;
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
								value="include_books"> <a
								title="Add books from Partner Store" href="javacript:void(0)">Include
									books from Partner Store</a></li>
							<li><a class='inline' href="#shoppingCart"><img
									src="/SapeStore/img/icon_cart.jpg" width="17" height="23"
									alt="cart"></a></li>
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
											<h4 class="panel-title-highlighted">
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
						<h2 class="title text-center">Inventory Summary</h2>
						<form:form name="addBooks" action="/SapeStore/admin/addBooksAdmin"
							method="POST">
							<input type="submit" value="Add a book"
								class="btn btn-default btn-primary center-block" style="">
						</form:form>

						<form:form action="/SapeStore/admin/editBooks" method="POST"
							name="editForm" commandName="updateBooks">
							<input type="hidden" name="name" id="name_to_submit" />
							<input type="hidden" name="bookTitle" id="title_to_submit" />
							<input type="hidden" name="bookAuthor" id="author_to_submit" />
							<input type="hidden" name="publisherName"
								id="publisher_to_submit" />
							<input type="hidden" name="rentAvailable" id="rent_to_submit" />
							<input type="hidden" name="quantity" id="quantity_to_submit" />
							<input type="hidden" name="bookPrice" id="bookPrice_to_submit" />
							<input type="hidden" name="rentPrice" id="rentPrice_to_submit" />
							<input type="hidden" name="isbn" id="isbn_to_submit" />
							<input type="hidden" name="bookShortDesc"
								id="bookShortDesc_to_submit" />
							<input type="hidden" name="bookDetailDesc"
								id="bookDetailDesc_to_submit" />
							<input type="hidden" name="thumbPath" id="thumbPath_to_submit" />
							<input type="hidden" name="categoryName"
								id="categoryName_to_submit" />
							<input type="hidden" name="fullPath" id="fullPath_to_submit" />
							<table id="tablepaging" class="yui">
								<thead
									style="text-align: left; font-size: 14px; font-family: SapientSansMedium; font-weight: regular; color: #000000">
									<tr style="border-bottom: 2px solid black;">
										<th style="width: 120px; word-break: break-word">Category</th>
										<th style="width: 110px; word-break: break-word">Book
											Name</th>
										<th style="width: 110px; word-break: break-word">Author</th>
										<th style="width: 110px; word-break: break-word">Publisher</th>
										<th style="text-align: center; width: 190px">For Rent</th>
										<th
											style="text-align: center; width: 95px; word-break: break-word">Quantity</th>
										<th style="width: 50px">Edit Book</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${adminInventoryList}" var="current">

										<tr id="${current.isbn}" style="height: 60px">

											<td class="categoryName_td"
												style="width: 120px; word-break: break-word">${current.categoryName}
											</td>
											<td class="bookTitle_td"
												style="width: 110px; word-break: break-word">${current.bookTitle}
											</td>
											<td class="bookAuthor_td"
												style="width: 110px; word-break: break-word">${current.bookAuthor}
											</td>
											<td class="publisherName_td"
												style="width: 110px; word-break: break-word">${current.publisherName}
											</td>
											<td class="rentAvailable_td"
												style="text-align: center; width: 190px">${current.rentAvailability}
											</td>
											<td class="quantity_td"
												style="text-align: center; width: 95px; word-break: break-word">${current.quantity}
											</td>
											<td style="display: none" class="bookPrice_td">${current.bookPrice}</td>

											<td style="display: none" class="rentPrice_td">${current.rentPrice}
											</td>
											<td style="display: none" class="isbn_td">${current.isbn}
											</td>
											<td style="display: none" class="bookShortDesc_td">${current.bookShortDescription}
											</td>
											<td style="display: none" class="bookDetailDesc_td">${current.bookDetailDescription}
											</td>
											<td style="display: none" class="thumbPath_td">${current.bookThumbImage}
											</td>
											<td style="display: none" class="fullPath_td">${current.bookFullImage}
											</td>
											<td style="width: 50px">
												<button type="submit" name="editBook" title="Edit"
													onclick="submitForm('${current.isbn}')">
													<img src="/SapeStore/img/edit11.png" width="17" height="23"
														align="right">
												</button>
											</td>
											<%-- <td style="float: inherit;"><button type="button" name="deleteBook" title="Delete" onclick="handleSubmit('${current.isbn}');"><img src="img/delete.png" width="15" height="13" align="right"></button>
									
								</td> --%>

										</tr>

									</c:forEach>
								</tbody>
							</table>
						</form:form>
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
</body>
</html>
