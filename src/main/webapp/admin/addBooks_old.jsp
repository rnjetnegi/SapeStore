<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,java.io.*,com.sapestore.hibernate.entity.BookCategory,com.sapestore.controller.ProductController"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="adminHeader.jsp" flush="true" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
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
				<div class="signup-form features_items">
										<h2 class="title text-center">Add Book to Store</h2>
					<form:form method="POST" name="addBooksForm"
						action="/SapeStore/addBooks" id="addAddress"
						enctype="multipart/form-data" commandName="addBooks">
						<table class="wwFormTable">
							<tr>
								<td class="tdLabel">Book
										Thumbnail Image<span class="required">*</span>
								</td>
								<td><input type="button" value="Browse Image"
									id="fakeBrowse" name="fakeBrowse"
									onclick="HandleBrowseClick();" />  <input type="file"
									id="thumbImage" name="thumbImage"
									accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp"
									id="thumbImage" placeholder="Book Thumbnail Image"
									required="required" style="opacity: 0"
									onChange="Handlechange();" value="${addBooks.thumbImage}" /></td>
							</tr>

							<tr>
								<td class="tdLabel">Book
										Detail Image<span class="required">*</span>
								</td>
								<td><input type="button" value="Browse Image"
									id="fakeBrowsefullImage" name="fakeBrowsefullImage"
									onclick="HandleBrowseClickFullImage();" />  <input
									type="file" id="fullImage" name="fullImage" value=""
									accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp"
									id="fullImage" placeholder="Book Detail Image"
									required="required" style="opacity: 0"
									onChange="HandlechangeFullImage();"
									value="${addBooks.fullImage}" /></td>
							</tr>
							<tr>
								<td class="tdLabel">Book Title<span class="required">*</span></td>
								<td><input type="text" name="bookTitle"
									value="${addBooks.bookTitle}" id="addAddress_bookTitle"
									placeholder="Book Title" /></td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="bookTitleErrorMessage"></td>
							</tr>
							<tr>
								<td class="tdLabel">Book Author<span class="required">*</span></td>
								<td><input type="text" name="bookAuthor"
									value="${addBooks.bookAuthor}" id="addAddress_bookAuthor"
									placeholder="Book Author" /></td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="bookAuthorErrorMessage"></td>
							</tr>
							<tr>
								<td class="tdLabel">ISBN<span class="required">*</span></td>
								<td><input type="text" name="isbn" value="${addBooks.isbn}"
									id="addAddress_isbn" placeholder="ISBN" /></td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="bookIsbnErrorMessage"></td>
							</tr>
							<tr>
								<td class="tdLabel">Publisher Name<span class="required">*</span></td>
								<td><input type="text" name="publisherName"
									value="${addBooks.publisherName}" id="addAddress_publisherName"
									placeholder="Publisher Name" /></td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="publisherNameErrorMessage"></td>
							</tr>
							<tr>
								<td class="tdLabel">Book Category<span class="required">*</span></td>
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
								<td class="tdLabel">Selling Price (In $)<span
										class="required">*</span></td>
								<td><input type="text" name="bookPrice"
									id="addAddress_bookPrice" value="${addBooks.bookPrice}"
									placeholder="Book Price (In $)" /></td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="bookPriceErrorMessage"></td>
							</tr>

							<tr>
								<td class="tdLabel">Number of Books<span class="required">*</span></td>
								<td><input type="text" name="quantity"
									id="addAddress_quantity" value="${addBooks.quantity}"
									placeholder="Quantity" /></td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="quantityErrorMessage"></td>
							</tr>
							<tr>
								<td class="tdLabel">Available for Rent<span class="required">*</span></td>
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
								<td class="tdLabel">Rent
										Price (In $)<span class="required">*</span>
								</td>
								<td><input type="text" name="rentPrice"
									value="${addBooks.rentPrice}" id="rentPrice_id"
									placeholder="Rent Price" /></td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="rentPriceErrorMessage"></td>
							<tr>
								<td class="tdLabel">Short Description<span class="required">*</span></td>
								<td><textarea name="bookShortDesc" cols="25" rows="3"
										id="addAddress_bookShortDesc" placeholder="Short Description">${addBooks.bookShortDesc}</textarea>
								</td>

								<td class="tdLabel"></td>
								<td class="errMsg" id="shortDescErrorMessage"></td>
							</tr>
							<tr>
								<td><input type="hidden" name="bookDetailDesc"
									id="bookDetailDesc_id" value="${addBooks.bookDetailDesc}"></td>
							</tr>
							<tr>
								<td></td>
								<td><a onclick="validateForm()" class="btn btn-default">Add
										to Store</a> <a type="button" id=addAddress__cancel
									onclick="goBack()" class="btn btn-default"
									class="lightButton addtoStore">Cancel</a></td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script src="/SapeStore/js/addBooksValidation.js"></script>
<jsp:include page="footer.jsp" flush="true" />