<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,java.io.*,com.sapestore.hibernate.entity.BookCategory,com.sapestore.controller.ProductController"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="adminHeader.jsp" flush="true" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<<style>
.lable {color: black }
</style>
<script type="text/javascript">
	function dis_able() {
		var r = $("#rentAvailable_id").val().trim();
		if (r == "N") {
			document.getElementById("rentPrice_id").value = "";
			document.getElementById("rentPrice_id").disabled = true;
		} else {
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
	function validateInput() {
		if (document.getElementById("addAddress_bookPrice").value == null) {
			document.getElementById("errorPrice").innerHTML = "Please provide book price.";
		} else if (document.getElementById("addAddress_bookPrice").value == "") {
			document.getElementById("errorPrice").innerHTML = "Please provide book price.";
		} else if (document.getElementById("addAddress_quantity").value == "") {
			document.getElementById("errorQuantity").innerHTML = "Please provide book quantity.";
		} else if (document.getElementById("addAddress_quantity").value == "") {
			document.getElementById("errorQuantity").innerHTML = "Please provide book quantity.";
		} else {
			document.addBooksForm.submit();
		}
	}

	function submitTheForm() {
		document.addBooksForm.submit();
	}

	function goBack() {
		window.history.back();
	}
</script>
<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
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
			<div class="col-sm-9 padding-right">
				<div class="signup-form  features_items">
										<h2 class="title text-center">Update Book Details</h2>
					<form:form id="fid" name="updateForm"
						action="/SapeStore/admin/updateInventory" enctype="multipart/form-data"
						method="post" commandName="updateInv">
						<table>
							<fieldset>

								<div>
									<tr>
										<td class="tdLabel">Book Thumbnail Image<span
												class="required">*</span></td>
										<td><img alt="Book Thumbnail" width="81" height="112"
											src="SapeStore/../../${updateBooks.thumbPath}"> <input type="button"
											value="Browse Image" id="fakeBrowse" name="fakeBrowse"
											onclick="HandleBrowseClick();" /> <input type="file"
											id="thumbImage" name="thumbImage"
											value="${updateBooks.thumbImage}"
											accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp"
											id="thumbImage_id" placeholder="Book Thumbnail Image"
											style="opacity: 0" onChange="Handlechange();" /></td>
									</tr>
								</div>
								<td class="tdLabel">Book
										Detail Image<span class="required">*</span>
								</td>
								<td><img alt="Book Detail Image" width="81" height="112"
									src="SapeStore/../../${updateBooks.fullPath}"> <input type="button"
									value="Browse Image" id="fakeBrowsefullImage"
									name="fakeBrowsefullImage"
									onclick="HandleBrowseClickFullImage();" />  <input
									type="file" id="fullImage" name="fullImage" value=""
									accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp"
									id="fullImage" placeholder="Book Detail Image"
									required="required" style="opacity: 0"
									onChange="HandlechangeFullImage();" /></td>
								</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Book Title<span class="required">*</span></td>
										<td><input type="text" name="bookTitle"
											value="${updateBooks.bookTitle}" id="bookTitle_id" /></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="bookTitleErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Book Author<span class="required">*</span></td>
										<td><input type="text" name="bookAuthor"
											value="${updateBooks.bookAuthor}" id="bookAuthor_id"
											placeholder="Book Author" /></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="bookAuthorErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">ISBN<span
												class="required">*</span></td>
										<td><input type="text" name="isbn"
											value="${updateBooks.isbn}" id="isbn_id" placeholder="ISBN"
											disabled="disabled" /> <input type="hidden" id="oldIsbn"
											name="oldIsbn" value="${updateBooks.isbn}" /></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="bookIsbnErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Book Category<span class="required">*</span></td>
										<td><form:select path="categoryId">
												<form:option value="${updateBooks.categoryId}"
													label="${updateBooks.categoryName}" />
												<form:options items="${categoryList}" itemValue="categoryId"
													itemLabel="categoryName" />
											</form:select></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="categoryErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Selling Price (In $)<span
												class="required">*</span></td>
										<td><input type="text" name="bookPrice"
											value="${updateBooks.bookPrice}" id="bookPrice_id"
											placeholder="Book Price (In $)" /></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="bookPriceErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Available for Rent</td>
										<td><select name="rentAvailable" id="rentAvailable_id"
											onchange="dis_able()">
												<option value="Y">Yes</option>
												<option value="N">No</option>
										</select></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Rent Price (In $)<span class="required">*</span></td>
										<td><input type="text" name="rentPrice"
											value="${updateBooks.rentPrice}" id="rentPrice_id"
											placeholder="Rent Price" /></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="rentPriceErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Books Quantity<span class="required">*</span></td>
										<td><input type="text" name="quantity"
											value="${updateBooks.quantity}" id="quantity_id"
											placeholder="11" /></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="quantityErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td class="tdLabel">Publisher Name<span class="required">*</span></td>
										<td><input type="text" name="publisherName"
											value="${updateBooks.publisherName}" id="publisherName_id"
											placeholder="Publisher Name" /></td>
											<td class="tdLabel"></td>
								<td class="errMsg" id="publisherNameErrorMessage"></td>
									</tr>
								</div>

								<div>
									<tr>
										<td class="tdLabel">Short Description<span class="required">*</span></td>
										<td><textarea name="bookShortDesc" cols="25" rows="3"
												id="bookShortDesc_id" placeholder="Short Description">${updateBooks.bookShortDesc}
								</textarea></td>
								<td class="tdLabel"></td>
								<td class="errMsg" id="shortDescErrorMessage"></td>
									</tr>
								</div>
								<div>
									<tr>
										<td><input type="hidden" name="bookDetailDesc" 
												id="bookDetailDesc_id" value="${updateBooks.bookDetailDesc}">
									</td>
									</tr>
								</div>
							</fieldset>
							<br>

							<div id="cancel" style="width: 42em;">
								<tr>
									<td colspan="3">
										<div align="right">
											<a class="btn btn-default"	onclick="submitForm()">Update Store</a>
											<a type="button" id="manageInv" name="manageInv"
												value="Submit" onclick="goBack()" class="btn btn-default">Cancel</a>
										</div>
									</td>
								</tr>
							</div>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script src="/SapeStore/js/addBooksValidation.js"></script>
<jsp:include page="footer.jsp" flush="true" />