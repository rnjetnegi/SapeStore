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
	function dis_able() {
		var r = $("#rentAvailable_id").val().trim();
		var p = $("#addAddress_bookPrice").val().trim();
		if (r == "N") {
			document.getElementById("rentPrice_id").value = 0;
			document.getElementById("rentPrice_id").disabled = true;
		} else {
			document.getElementById("rentPrice_id").value = (p/10).toFixed(3);
			document.getElementById("rentPrice_id").disabled = false;
		}
	}
	function HandleBrowseClick() {
		var fileinput = document.getElementById("thumbImage");
		fileinput.click();
	}
	function Handlechange() {
		var fileinput = document.getElementById("thumbImage");
		if (fileinput) {
			var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput
					.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
			var filename = fileinput.substring(startIndex);
			if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
				filename = filename.substring(1);
			}
			var textinput = document.getElementById("filename");
			textinput.value = filename.value;
		}

	}
	function HandleBrowseClickFullImage() {
		var fileinput = document.getElementById("fullImage");
		fileinput.click();
	}
	function HandlechangeFullImage() {
		var fileinput = document.getElementById("fullImage");
		if (fileinput) {
			var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput
					.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
			var filename = fileinput.substring(startIndex);
			if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
				filename = filename.substring(1);
			}
			var textinput = document.getElementById("filenameFullImage");
			textinput.value = filename.value;
		}

	}

	/* function submitTheForm() {
		document.addBooksForm.submit();
	} */

	function goBack() {
		window.history.back();
	}
</script>
<style>
.errMsg {
	color: red;
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
									src="/SapeStore/img/icon_cart.jpg" width="15" height="12"
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
					<div class="signup-form">
						<h2 class="title text-center">Add Book to Store</h2>
						<form:form method="POST" name="addBooksForm"
							action="/SapeStore/admin/addBooks" id="addAddress"
							enctype="multipart/form-data" commandName="addBooks">
							<table>
								<tbody>
									<tr>
										<td><label>Book Thumbnail Image</label></td>
										<td><input type="button" value="Browse Image"
											id="fakeBrowse" name="fakeBrowse"
											onclick="HandleBrowseClick();" /> <input type="file"
											id="thumbImage" name="thumbImage"
											accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp"
											id="thumbImage" placeholder="Book Thumbnail Image"
											required="required" style="opacity: 0"
											onChange="Handlechange();" value="${addBooks.thumbImage}" /></td>
									</tr>
									<tr>
										<td><label>Book Detail Image</label></td>
										<td><input type="button" value="Browse Image"
											id="fakeBrowsefullImage" name="fakeBrowsefullImage"
											onclick="HandleBrowseClickFullImage();" /> <input
											type="file" id="fullImage" name="fullImage" value=""
											accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp"
											id="fullImage" placeholder="Book Detail Image"
											required="required" style="opacity: 0"
											onChange="HandlechangeFullImage();"
											value="${addBooks.fullImage}" /></td>
									</tr>
									<tr>
										<td><label>Book Title</label></td>
										<td><input type="text" name="bookTitle"
											 value="${addBooks.bookTitle}"
											id="addAddress_bookTitle" placeholder="Book Title" /></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="bookTitleErrorMessage"></td>
									</tr>
									<tr>
										<td><label>Book Author</label></td>
										<td><input type="text" name="bookAuthor" 
											value="${addBooks.bookAuthor}" id="addAddress_bookAuthor"
											placeholder="Book Author" /></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="bookAuthorErrorMessage"></td>
									</tr>
									<tr>
										<td><label>ISBN</label></td>
										<td><input type="text" name="isbn" 
											value="${addBooks.isbn}" id="addAddress_isbn"
											placeholder="ISBN" /></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="bookIsbnErrorMessage"></td>
									</tr>
									<tr>
										<td><label>Publisher Name</label></td>
										<td><input type="text" name="publisherName" 
											value="${addBooks.publisherName}"
											id="addAddress_publisherName" placeholder="Publisher Name" /></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="publisherNameErrorMessage"></td>
									</tr>
									<tr>
										<td><label>Book Category</label></td>
										<td><form:select path="categoryId"
												id="addAddress_categoryName">
												<form:option value="" label="Select a category" />
												<c:forEach items="${categoryList}" var="current">
													<c:choose>
														<c:when
															test="${current.categoryName==addBooks.categoryName}">
															<form:option value="${current.categoryId}"
																label="${current.categoryName}" selected="selected" />
														</c:when>
														<c:otherwise>
															<form:option value="${current.categoryId}"
																label="${current.categoryName}" />
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</form:select></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="categoryErrorMessage"></td>
									</tr>
									<tr>
										<td><label>Selling Price (In $)</label></td>
										<td><input type="text" name="bookPrice" 
											id="addAddress_bookPrice" oninput="dis_able()" value="${addBooks.bookPrice}"
											placeholder="Book Price (In $)" /></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="bookPriceErrorMessage"></td>
									</tr>
									<tr>
										<td><label>Number of Books</label></td>
										<td><input type="text" name="quantity" 
											id="addAddress_quantity" value="${addBooks.quantity}"
											placeholder="Quantity" /></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="quantityErrorMessage"></td>
									</tr>
									<tr>
										<td><label>Available for Rent</label></td>
										<td><select name="rentAvailable" id="rentAvailable_id"
											onchange="dis_able()">
												<option value="Y">Yes</option>
												<c:choose>
													<c:when test="${addBooks.rentAvailable=='N'}">
														<option value="N" selected="selected">No</option>
													</c:when>
													<c:otherwise>
														<option value="N">No</option>
													</c:otherwise>
												</c:choose>

										</select></td>
									</tr>
									<tr>
										<td><label>Rent Price (In $)</label></td>
										<td><input type="text" name="rentPrice" 
											value="${addBooks.rentPrice}" id="rentPrice_id"
											placeholder="Rent Price" /></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="rentPriceErrorMessage"></td>
									</tr>
									<tr>
										<td><label>Short Description</label></td>
										<td><textarea name="bookShortDesc" cols="25" rows="3" 
												id="addAddress_bookShortDesc"
												placeholder="Short Description">${addBooks.bookShortDesc}</textarea></td>
										<td class="tdLabel"></td>
										<td class="errMsg" id="shortDescErrorMessage"></td>
									</tr>
									<tr>
										<td><input type="hidden" name="bookDetailDesc"
											id="bookDetailDesc_id" value="${addBooks.bookDetailDesc}"></td>
									</tr>
									<tr>
										<td></td>
										<td class="pull-right"><a onclick="validateForm()"
											class="btn btn-default">Add to Store</a> <a type="button"
											id=addAddress__cancel onclick="goBack()"
											class="btn btn-default" class="lightButton addtoStore">Cancel</a>
										</td>
									</tr>
								</tbody>
							</table>
							</form:form>
					</div>

					</section>
				</div>
			</div>
		</div>
	</div>
	<script src="/SapeStore/js/plugins.js"></script>
	<script src="/SapeStore/js/main.js"></script>
	<script src="/SapeStore/js/addBooksValidation.js"></script>
	<jsp:include page="footer.jsp" flush="true" />
	<!-- 	<script src="js/manageOrdersLinks.js"></script> -->
</body>
</html>
