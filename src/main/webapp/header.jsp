<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | SapeStore</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link href="/SapeStore/css/bootstrap.min.css" rel="stylesheet">
<link href="/SapeStore/css/font-awesome.min.css" rel="stylesheet">
<link href="/SapeStore/css/prettyPhoto.css" rel="stylesheet">
<link href="/SapeStore/css/price-range.css" rel="stylesheet">
<link href="/SapeStore/css/animate.css" rel="stylesheet">
<link href="/SapeStore/css/main.css" rel="stylesheet">
<link href="/SapeStore/css/responsive.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">

<!-- added by Alex to make search Auto complete work -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

</head>
<!--/head-->

<body>
	<header id="header">
		<!--header-->
		<div class="header_top">
			<!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><span><i class="fa fa-phone"></i> +1 244 444 8080</span></li>
								<li><span><i class="fa fa-envelope"></i>
										contact@sapestore.com</span></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<c:choose>
							<c:when test="${userLoginName != null }">
								<div class="btn-group pull-right">
									<button type="button"
										class="btn btn-default dropdown-toggle usa"
										data-toggle="dropdown">
										${userLoginName} <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/Profile">Profile</a></li>
										<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
									</ul>
								</div>
							</c:when>
							<c:otherwise>
								<div class="contactinfo pull-right">
									<ul class="nav nav-pills">
										<li><a
											href="${pageContext.request.contextPath}/login.jsp"><i
												class="fa fa-lock"></i> Login</a></li>
									</ul>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<!--/header_top-->

		<div class="header-middle">
			<!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="${pageContext.request.contextPath}/welcome"><img src="images/home/logo.png" alt="" /></a>
						</div>
					</div>
					<div class="col-sm-4">
<!-- 						<div class="pull-right"> -->
							<form name="searchForm" class="form-inline"
								action="/SapeStore/searchBook"
								method="post">
								<div class="form-group" style="width: 69%;">
									<label for="searchByText" class="sr-only"></label>
									<input type="text" id="searchByText" name="search"
										class="form-control" placeholder="Search" maxlength="20" /> 
								</div>
								<input type="submit" id="searchBtn" value="Search" style="width: 29%"
									class="searchBtn btn btn-default btn-primary-search" disabled="disabled" />
							</form>
<!-- 						</div> -->
					</div>
					<div class="col-sm-4">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="${pageContext.request.contextPath}/viewWishlist"><i class="fa fa-star"></i> Wishlist</a></li>
								<li><a href="${pageContext.request.contextPath}/viewCart"><i class="fa fa-shopping-cart"></i>
										Cart</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-middle-->
	</header>
	<!--/header-->