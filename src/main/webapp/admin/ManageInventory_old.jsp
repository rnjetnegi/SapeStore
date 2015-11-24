<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="adminHeader.jsp" flush="true" />
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
#tablepaging tr td{
width: 500px;}
</style>
<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
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
									<a href="/SapeStore/admin/manageInventory">Manage Inventory</a>
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
				<div class="signup-form">
					<h2 class="title text-center">Inventory Summary</h2>
					<form:form name="addBooks" action="/SapeStore/admin/addBooksAdmin"
						method="POST">
						<input type="submit" value="Add a book"
							style="height: 25px; font-weight: bold; font-size: initial; width: 106px; background-color: #21addd; color: white; height: 30px">
					</form:form>

					<form:form action="/SapeStore/admin/editBooks" method="POST"
						name="editForm" commandName="updateBooks">
						<input type="hidden" name="name" id="name_to_submit" />
						<input type="hidden" name="bookTitle" id="title_to_submit" />
						<input type="hidden" name="bookAuthor" id="author_to_submit" />
						<input type="hidden" name="publisherName" id="publisher_to_submit" />
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
									<th>Category</th>
									<th>Book Name</th>
									<th>Author</th>
									<th>Publisher</th>
									<th>For Rent</th>
									<th>Quantity</th>
									<th>Edit Book</th>
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
										<td style="width: 50px">
											<button type="submit" name="editBook" title="Edit"
												onclick="submitForm('${current.isbn}')">
												<img src="/SapeStore/img/edit11.png" width="15" height="13"
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
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	$("html, body").animate({
		scrollTop : 100
	}, 1000);
</script>
<jsp:include page="footer.jsp" flush="true" />