<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


<link rel="stylesheet" href="/SapeStore/css/normalize.css" type="text/css">
<link rel="stylesheet" href="/SapeStore/css/main.css" type="text/css">


<style type="text/css">
.pg-normal {
	color: #de2728;
	font-size: 14px;
	cursor: pointer;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

.pg-selected {
	color: #fff;
	font-size: 14px;
	background: #de2728;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

table.yui {
	border-collapse: collapse;
	font-size: small;
}

table.yui td {
	padding: 5px;
}

table.yui .even {
	background-color: #EEE8AC;
}

table.yui .odd {
	background-color: #F9FAD0;
}

table.yui th {
	padding-top: 13px;
	height: auto;
}

table.yui th a {
	text-decoration: none;
	text-align: center;
	padding-right: 20px;
	font-weight: bold;
	white-space: nowrap;
}

table.yui tfoot td {
	background-color: #E1ECF9;
}

table.yui thead td {
	vertical-align: middle;
	background-color: #E1ECF9;
	border: none;
}

table.yui thead .tableHeader {
	font-size: larger;
	font-weight: bold;
}

table.yui thead .filter {
	text-align: right;
}

table.yui tfoot {
	background-color: #E1ECF9;
	text-align: center;
}

table.yui .tablesorterPager {
	padding: 10px 0 10px 0;
}

table.yui .tablesorterPager span {
	padding: 0 5px 0 5px;
}

table.yui .tablesorterPager input.prev {
	width: auto;
	margin-right: 10px;
}

table.yui .tablesorterPager input.next {
	width: auto;
	margin-left: 10px;
}

table.yui .pagedisplay {
	font-size: 10pt;
	width: 30px;
	border: 0px;
	background-color: #E1ECF9;
	text-align: center;
	vertical-align: top;
}

.homeAdmin #mainContent table {
	width: 99%;
}

#pageNavPosition {
	float: right;
	background: #f0f7f8;
	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
	border-bottom: 1px solid #AAAAAA;
	padding-left: 774px;
	margin-right: 15px;
	padding-bottom: 0.5em;
	padding-top: 0.5em;
	padding-right: 2px;
}

#searchByText {
	width: 350px;
	height: 25px;
}

#mainContent {
	padding-top: 200px;
	text-align: center;
}

#searchBtn {
	background-color: #63c6e8;
}
</style>


<script>

$(document).ready(function(){
	$("#dispatch").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	$("#return").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	
});
</script>
<script>
	function beforeDispatch() {
		document.updateForm.submit();
	}
	function beforeReturn() {
		document.updateForm.submit();
	}

	function dispatchClick(control) {
		var cid=control.id;
		var substr="dispatchCheckIndex";
		if(cid.lastIndexOf(substr, 0) == 0)
			{
			var str2=cid.substring(18);
			var str1 = "dispatchTextIndex";
			var textI = str1.concat(str2);
			document.getElementById(textI).childNodes[0].nextSibling.value = document.getElementById(cid).checked;
			}
	}
	
	function returnClick(control) {
		var cid=control.id;
		var substr="returnCheckIndex";
		if(cid.lastIndexOf(substr, 0) == 0)
			{
			var str2=cid.substring(16);
			var str1 = "returnTextIndex";
			var textI = str1.concat(str2);
			document.getElementById(textI).childNodes[0].nextSibling.value = document.getElementById(cid).checked;
			}
	}
</script>
</head>

<body>

	<script type="text/javascript">
$(document).ready(function(){
	$("#loginPop").click();
});
function changeVal()
{
	document.getElementById('account').value = '-1';
}
</script>
	<div id="wrapper">
		<div id="shoppingCartContainer" style="display: none">
			<div id="shoppingCart" class="popup">
				
			</div>
		</div>
		<form:form name="form"
			action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false"
			method="GET" commandName="welcome">
			<header>
			<div id="header">
				<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore"
					class="logo"><img src="/SapeStore/img/logo_option 01.png" width="231"
					height="109" alt="SapeStore"></a>

				<ul class="topLinks">
					<li>
						<!--form:checkbox id="checkMe" path="checkMe" value="Include books from Partner Store" onchange="form.submit();"/-->
						<c:choose>
							<c:when test="${welcome.checkMe==false}">
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();"
									checked="checked">
							</c:otherwise>
						</c:choose> <label for="checkMe" style="font-size: 13px;">Include
							books from Partner Store</label> <input type="hidden" name="categoryId"
						value="${categoryId}" /> <input type="hidden" name="categoryName"
						value="${categoryName}" />
					</li>
					<li><a class='inline' href="#shoppingCart"><img
							src="/SapeStore/img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
					<li class="cartNum">${ShoppingCart.totalQuantity}</li>
					<c:choose>
						<c:when test="${not empty userId}">
							<select id="account" name="account" style="font-size: 12px;"
								onchange="changeVal()">
								<option value="-1" style="font-size: 10px;">Welcome
									${username}</option>
								<option value="1" style="font-size: 10px;">Edit Profile</option>
								<option value="2" style="font-size: 10px;">Order Status
									Tracking</option>
								<option value="3" style="font-size: 10px;">Transaction
									History</option>
									
							</select>
						</c:when>
					</c:choose>
				</ul>
				<nav>
				<ul class="nav">
					<li class="active"><a
						href="/SapeStore/welcome?checkMe=${checkMe}">HOME</a></li>
					<c:choose>
						<c:when test="${not empty userId}">
							<li><a href="/SapeStore/welcome?checkMe=${checkMe}">Account</a></li>
						</c:when>

					</c:choose>

					<li><a href="/SapeStore/welcome?checkMe=${checkMe}">Wishlist</a></li>
				
					<li><a href="/SapeStore/welcome?checkMe=${checkMe}"><img
							alt="searchImage" src="/SapeStore/img/magnifier-icon.png"></a></li>

				</ul>
				</nav>
			</div>
			</header>
			<!-- header ends -->
			<section> <section>
			<div class="leftCol">
				<h2>Book Categories</h2>
				<nav> <!-- left navigation -->
				<ul>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=1&categoryName=Academic and Professional&checkMe=false"
						title="Academic and Professional"> Academic and Professional</a>
					</li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=2&categoryName=Business and Management&checkMe=false"
						title="Business and Management"> Business and Management</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=3&categoryName=Comics and Graphic Novels&checkMe=false"
						title="Comics and Graphic Novels"> Comics and Graphic Novels</a>
					</li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=4&categoryName=Computers and Internet&checkMe=false"
						title="Computers and Internet"> Computers and Internet</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=5&categoryName=Food and Wine&checkMe=false"
						title="Food and Wine"> Food and Wine</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=6&categoryName=Health&checkMe=false"
						title="Health"> Health</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=7&categoryName=History and Politics&checkMe=false"
						title="History and Politics"> History and Politics</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=8&categoryName=Literature and Fiction&checkMe=false"
						title="Literature and Fiction"> Literature and Fiction</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=9&categoryName=Music Films and Entertainment&checkMe=false"
						title="Music Films and Entertainment"> Music Films and
							Entertainment</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=10&categoryName=Religion and Spirituality&checkMe=false"
						title="Religion and Spirituality"> Religion and Spirituality</a>
					</li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=11&categoryName=Science and Technology&checkMe=false"
						title="Science and Technology"> Science and Technology</a></li>

					<li><a
						href="/SapeStore/bookListByCat?categoryId=12&categoryName=Sports and Games&checkMe=false"
						title="Sports and Games"> Sports and Games</a></li>


				</ul>
				</nav>
			</div>
		</form:form>


		<div id="mainContent">
			<%--   <h2>
      	${categoryName}
      </h2> --%>
			<div class="clearfix"></div>

			<form name="searchForm" action="search/searchBook" method="post">
				<input type="text" id="searchByText" name="search" onkeyup="checkForm()" /> 
				<input type="submit" id="searchBtn"	value="Search" disabled="disabled" />
			</form>
		</div>
		</section>
		<div id="pageNavPosition1" align="center"></div>
		<div class="clearfix"></div>

		<footer>

		<div id="footer">
			<div style="float: left; margin-left: 386px;">
				<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact
					Us</a>
			</div>
			<div style="float: left; margin-left: 6px; color: #21addd">|</div>

			<div style="float: left; margin-left: 7px;">
				<a href="/SapeStore//policyCustomer" style="color: #21addd;">Privacy
					Policy</a>
			</div>
			<div>
				Powered by <img src="/SapeStore/img/sapient_nitro.jpg" width="78" height="14"
					alt="sapient nitro">
			</div>
		</div>
		</footer>
	</div>

	<!-- This contains the hidden content for shopping cart popup -->

	<!-- This contains the hidden content for login popup -->
	<div style="display: none">
		<div id="login" class="popup">


		
		</div>
	</div>

	<!-- Forgot password-->
	<div style="display: none">
		<form method="post" action="" id="forgotPassword"
			onsubmit="return ValidateForm();">
			<fieldset>
				<label for="email">Enter your email id<span class="required">*</span></label>
				<input type="submit" value="Submit" class="lightButton">
				<div id="someHiddenDiv" style="display: none">Your password
					has been sent to your registered mail.</div>
			</fieldset>
		</form>
	</div>
</body>
<script src="js/predictiveSearch.js"></script>
<script src="js/emptySearchDisabled.js"></script>
</html>