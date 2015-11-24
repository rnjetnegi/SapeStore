<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />
<section id="accountNav">
	<!--user account nav-->
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<div class="mainmenu pull-left">
					<ul class="nav navbar-nav collapse navbar-collapse">
						<li><a href="${pageContext.request.contextPath}/Profile">Profile</a></li>
						<li class="dropdown"><a href="#">Transaction History<i
								class="fa fa-angle-down"></i></a>
							<ul role="menu" class="sub-menu">
								<li><a href="${pageContext.request.contextPath}/bookspurchased">Books Purchased</a></li>
								<li><a href="${pageContext.request.contextPath}/booksrented">Books Rented</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!--/user account nav-->
<section id="accountDesc">
	<!--user account description-->
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="userAccountDesc pull-left">
					<h2>Welcome ${userLoginName }</h2>
				</div>
			</div>
		</div>
	</div>
</section>
<!--/user account description-->
<jsp:include page="footer.jsp" flush="true" />