var priceCheck = 1;

function validateForm() {

	var count = 0;
	var bookTitle = document.forms["addBooksForm"]["addAddress_bookTitle"].value;
	var bookAuthor = document.forms["addBooksForm"]["addAddress_bookAuthor"].value;
	var bookIsbn = document.forms["addBooksForm"]["addAddress_isbn"].value;
	var publisherName = document.forms["addBooksForm"]["addAddress_publisherName"].value;
	var bookCategory = document.forms["addBooksForm"]["addAddress_categoryName"].value;
	var bookPrice = document.forms["addBooksForm"]["addAddress_bookPrice"].value;
	var rentPrice = document.forms["addBooksForm"]["rentPrice_id"].value;
	var bookQuantity = document.forms["addBooksForm"]["addAddress_quantity"].value;
	var shortDesc = document.forms["addBooksForm"]["addAddress_bookShortDesc"].value;
	var isLetters = /[\w\s]+$/;
	var isAlphabets = /^[a-zA-Z ]{2,30}$/;
	var isNumbers = /^[0-9]+$/;
	var isDecimal = /^\d+(\.\d{1,3})?$/i;
	var isPublisher = /^[a-zA-Z\s&.]/;

	if (bookTitle == null || bookTitle.trim() == "") {
		document.getElementById("bookTitleErrorMessage").innerHTML = "<span class="
				+ "\'bookTitleErrorMessage\'"
				+ ">Book Title is required.</span>";
		count++;
	} else {
		document.getElementById("bookTitleErrorMessage").innerHTML = "";
	}

	if (bookAuthor == null || bookAuthor == "") {
		document.getElementById("bookAuthorErrorMessage").innerHTML = "<span class="
				+ "'bookAuthorErrorMessage'"
				+ ">Author Name is required.</span>";
		count++;
	} else if (isAlphabets.test(bookAuthor.trim()) == false) {
		document.getElementById("bookAuthorErrorMessage").innerHTML = "<span class="
				+ "'bookAuthorErrorMessage'"
				+ ">Please provide a valid Author Name</span>";
		count++;
	} else {
		document.getElementById("bookAuthorErrorMessage").innerHTML = "";
	}

	if (bookIsbn == null || bookIsbn.trim() == "") {
		document.getElementById("bookIsbnErrorMessage").innerHTML = "<span class="
				+ "'bookIsbnErrorMessage'" + ">ISBN is required.</span>";
		count++;
	} else if (isNumbers.test(bookIsbn) == true
			&& (((bookIsbn.length) == 10) || ((bookIsbn.length) == 13))) {
		document.getElementById("bookIsbnErrorMessage").innerHTML = "";
	} else {
		document.getElementById("bookIsbnErrorMessage").innerHTML = "<span class="
				+ "'bookIsbnErrorMessage'"
				+ ">Please provide a valid ISBN(Either 10 or 13 digits)</span>";
		count++;
	}

	if (publisherName == null || publisherName.trim() == "") {
		document.getElementById("publisherNameErrorMessage").innerHTML = "<span class="
				+ "'publisherNameErrorMessage'"
				+ ">Publisher Name is required.</span>";
		count++;
	} else if (isPublisher.test(publisherName) == false) {
		document.getElementById("publisherNameErrorMessage").innerHTML = "<span class="
				+ "'publisherNameErrorMessage'"
				+ ">Please provide a valid Publisher Name.</span>";
		count++;
	} else {
		document.getElementById("publisherNameErrorMessage").innerHTML = "";
	}

	if (bookCategory == null || bookCategory == "" || bookCategory == "NONE") {
		document.getElementById("categoryErrorMessage").innerHTML = "<span class="
				+ "'categoryErrorMessage'"
				+ ">Book Category is required. </span>";
		count++;
	} else {
		document.getElementById("categoryErrorMessage").innerHTML = "";
	}

	if (bookPrice == null || bookPrice.trim == "" || bookPrice.length == 0) {
		document.getElementById("bookPriceErrorMessage").innerHTML = "<span class="
				+ "'bookPriceErrorMessage'" + ">Book price is required.</span>";
		count++;
	} else if (isNumbers.test(bookPrice) == false) {
		document.getElementById("bookPriceErrorMessage").innerHTML = "<span class="
				+ "'bookPriceErrorMessage'"
				+ ">Please provide a valid price for the book.</span>";
		count++;
	} else if (bookPrice.length > 4 && priceCheck) {
		priceCheck = 0;
		var flag = confirm("Book Price is too high ! Are you Sure ?");
		if (flag) {
			document.getElementById("bookPriceErrorMessage").innerHTML = "";
		} else {
			priceCheck = 1;
			document.getElementById("bookPriceErrorMessage").innerHTML = "<span class="
					+ "'bookPriceErrorMessage'"
					+ ">Book Price should not be greater than $10000.</span>";
			count++;
		}
	} else {
		document.getElementById("bookPriceErrorMessage").innerHTML = "";
	}
	if (isNumbers.test(rentPrice) == true || isDecimal.test(rentPrice) == true) {
		document.getElementById("rentPriceErrorMessage").innerHTML = "";
	} else {
		document.getElementById("rentPriceErrorMessage").innerHTML = "<span class="
				+ "'rentPriceErrorMessage'"
				+ ">Please provide a valid Rent price for the book.</span>";
		count++;
	}

	if (bookQuantity == null || isNumbers.test(bookQuantity) == false) {
		document.getElementById("quantityErrorMessage").innerHTML = "<span class="
				+ "'quantityErrorMessage'"
				+ ">Book quantity is invalid.</span>";
		count++;
	} else if (bookQuantity > 20) {
		document.getElementById("quantityErrorMessage").innerHTML = "<span class="
				+ "'quantityErrorMessage'"
				+ ">Book quantity should not be greater than 20.</span>";
		count++;
	} else {
		document.getElementById("quantityErrorMessage").innerHTML = "";
	}

	if (shortDesc == null || shortDesc.trim() == "") {
		document.getElementById("shortDescErrorMessage").innerHTML = "<span class="
				+ "'shortDescErrorMessage'"
				+ ">Description is required.</span>";
		count++;
	} else if (isLetters.test(shortDesc) == false || shortDesc.length > 500) {
		document.getElementById("shortDescErrorMessage").innerHTML = "<span class="
				+ "'shortDescErrorMessage'"
				+ ">Description is too long (Max 500 Characters).</span>";
		count++;
	}

	else {
		document.getElementById("shortDescErrorMessage").innerHTML = "";
	}

	if (count != 0) {
		return false;
	} else {
		document.forms["addBooksForm"].submit();
	}
}

function submitForm() {

	var count = 0;
	var bookTitle = document.forms["updateForm"]["bookTitle_id"].value;
	var bookAuthor = document.forms["updateForm"]["bookAuthor_id"].value;
	var publisherName = document.forms["updateForm"]["publisherName_id"].value;
	var bookPrice = document.forms["updateForm"]["bookPrice_id"].value;
	var rentPrice = document.forms["updateForm"]["rentPrice_id"].value;
	var bookQuantity = document.forms["updateForm"]["quantity_id"].value;
	var shortDesc = document.forms["updateForm"]["bookShortDesc_id"].value;
	var isLetters = /[\w\s]+$/;
	var isAlphabets = /^[a-zA-Z ]{2,30}$/;
	var isNumbers = /^[0-9]+$/;
	var isDecimal = /^\d+(\.\d{1,3})?$/i;
	var isPublisher = /^[a-zA-Z\s&.]/;
	// var isDisabled =
	// document.forms["updateForm"]["rentPrice_id"].getAttribute('disabled');

	if (bookTitle == null || bookTitle.trim() == "") {
		document.getElementById("bookTitleErrorMessage").innerHTML = "<span class="
				+ "\'bookTitleErrorMessage\'"
				+ ">Book Title is required.</span>";
		count++;
	} else {
		document.getElementById("bookTitleErrorMessage").innerHTML = "";
	}

	if (bookAuthor == null || bookAuthor == "") {
		document.getElementById("bookAuthorErrorMessage").innerHTML = "<span class="
				+ "'bookAuthorErrorMessage'"
				+ ">Author Name is required.</span>";
		count++;
	} else if (isAlphabets.test(bookAuthor.trim()) == false) {
		document.getElementById("bookAuthorErrorMessage").innerHTML = "<span class="
				+ "'bookAuthorErrorMessage'"
				+ ">Please provide a valid Author Name</span>";
		count++;
	} else {
		document.getElementById("bookAuthorErrorMessage").innerHTML = "";
	}

	if (publisherName == null || publisherName.trim() == "") {
		document.getElementById("publisherNameErrorMessage").innerHTML = "<span class="
				+ "'publisherNameErrorMessage'"
				+ ">Publisher Name is required.</span>";
		count++;
	} else if (isPublisher.test(publisherName) == false) {
		document.getElementById("publisherNameErrorMessage").innerHTML = "<span class="
				+ "'publisherNameErrorMessage'"
				+ ">Please provide a valid Publisher Name.</span>";
		count++;
	} else {
		document.getElementById("publisherNameErrorMessage").innerHTML = "";
	}

	if (bookPrice == null || bookPrice.trim == "" || bookPrice.length == 0) {
		document.getElementById("bookPriceErrorMessage").innerHTML = "<span class="
				+ "'bookPriceErrorMessage'" + ">Book price is required.</span>";
		count++;
	} else if (isNumbers.test(bookPrice) == false) {
		document.getElementById("bookPriceErrorMessage").innerHTML = "<span class="
				+ "'bookPriceErrorMessage'"
				+ ">Please provide a valid price for the book.</span>";
		count++;
	} else if (bookPrice.length > 4 && priceCheck) {
		priceCheck = 0;
		var flag = confirm("Book Price is too high ! Are you Sure ?");
		if (flag) {
			document.getElementById("bookPriceErrorMessage").innerHTML = "";
		} else {
			priceCheck = 1;
			document.getElementById("bookPriceErrorMessage").innerHTML = "<span class="
					+ "'bookPriceErrorMessage'"
					+ ">Book Price should not be greater than $10000.</span>";
			count++;
		}
	} else {
		document.getElementById("bookPriceErrorMessage").innerHTML = "";
	}

	if (isNumbers.test(rentPrice) == true || isDecimal.test(rentPrice) == true) {
		document.getElementById("rentPriceErrorMessage").innerHTML = "";
	} else {
		document.getElementById("rentPriceErrorMessage").innerHTML = "<span	class="
				+ "'rentPriceErrorMessage'"
				+ ">Please provide a valid Rent price for the book.</span>";
		count++;
	}

	if (bookQuantity == null || isNumbers.test(bookQuantity) == false) {
		document.getElementById("quantityErrorMessage").innerHTML = "<span class="
				+ "'quantityErrorMessage'"
				+ ">Book quantity is invalid.</span>";
		count++;
	} else if (bookQuantity > 20) {
		document.getElementById("quantityErrorMessage").innerHTML = "<span class="
				+ "'quantityErrorMessage'"
				+ ">Book quantity should not be greater than 20.</span>";
		count++;
	} else {
		document.getElementById("quantityErrorMessage").innerHTML = "";
	}

	if (shortDesc == null || shortDesc.trim() == "") {
		document.getElementById("shortDescErrorMessage").innerHTML = "<span class="
				+ "'shortDescErrorMessage'"
				+ ">Description is required.</span>";
		count++;
	} else if (isLetters.test(shortDesc) == false || shortDesc.length > 500) {
		document.getElementById("shortDescErrorMessage").innerHTML = "<span class="
				+ "'shortDescErrorMessage'"
				+ ">Description is too long (Max 500 Characters).</span>";
		count++;
	}

	else {
		document.getElementById("shortDescErrorMessage").innerHTML = "";
	}

	if (count != 0) {
		return false;
	} else {
		document.forms["updateForm"].submit();
	}

}

/*
 * $(document).ready(function(){
 * 
 * 
 * var flag=true; $(".error").remove(); var characterReg = /^[1-9]\d*$/; var
 * nameReg = /^[a-zA-Z0-9 ]{1,100}$/; var isbnNameReg = /^[a-zA-Z0-9]{1,100}$/;
 * var alphaReg=/^[.a-zA-Z ]{1,100}$/;
 * 
 * var quantityVal= $("#quantity_id").val(); var
 * quantityValT=quantityVal.trim(); if(!characterReg.test(quantityValT)) {
 * $("#quantity_id").after('<span class="error"> Enter only numbers between
 * 1-9(max 3 digits)</span>'); flag=false; }
 * 
 * var bookPrice= $("#bookPrice_id").val(); var bookPriceT=bookPrice.trim();
 * if(!characterReg.test(bookPriceT)) { $("#bookPrice_id").after('<span
 * class="error"> Enter only numbers between 1-9(max 3 digits) </span>');
 * flag=false; }
 * 
 * 
 * var bookTitle= $("#bookTitle_id").val(); var bookTitleT=bookTitle.trim();
 * if(!nameReg.test(bookTitleT)) { $("#bookTitle_id").after('<span
 * class="error"> Enter only alphanumerics(max 100 chars) </span>'); flag=false; }
 * 
 * var bookAuthor= $("#bookAuthor_id").val(); var bookAuthorT=bookAuthor.trim();
 * if(!alphaReg.test(bookAuthorT)) { $("#bookAuthor_id").after('<span
 * class="error"> Enter only alphabets(max 100 chars)</span>'); flag=false; }
 * 
 * var isbn= $("#isbn_id").val(); var isbnT=isbn.trim();
 * if(!isbnNameReg.test(isbnT)) { $("#isbn_id").after('<span class="error">
 * Enter only alphanumerics(max 100 chars)</span>'); flag=false; }
 * 
 * var bookShortDesc= $("#bookShortDesc_id").val(); var
 * bookShortDescT=bookShortDesc.trim(); if(bookShortDescT.length==0) {
 * $("#bookShortDesc_id").after('<span class="error"> Description Cannot be
 * blank"</span>'); flag=false; } if(bookShortDescT.length>150) {
 * $("#bookShortDesc_id").after('<span class="error"> Maximum 150 characters."</span>');
 * flag=false; }
 * 
 * var bookDetailDesc= $("#bookDetailDesc_id").val(); var
 * bookDetailDescT=bookDetailDesc.trim(); if(bookDetailDescT.length==0) {
 * $("#bookDetailDesc_id").after('<span class="error"> Description Cannot be
 * blank"</span>'); flag=false; } if(bookDetailDescT.length>500) {
 * $("#bookDetailDesc_id").after('<span class="error"> Maximum 500 characters."</span>');
 * flag=false; }
 * 
 * 
 * if(flag==true){ document.updateForm.submit(); } });
 */
