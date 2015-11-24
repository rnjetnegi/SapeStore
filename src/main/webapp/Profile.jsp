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
								<li><a
									href="${pageContext.request.contextPath}/bookspurchased">Books
										Purchased</a></li>
								<li><a
									href="${pageContext.request.contextPath}/booksrented">Books
										Rented</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!--/user account nav-->
<style>
td{
line-height: 200%;
}
</style>
<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="signup-form">
					<!--sign up form-->
					<h2 style="font-size: 25px"><strong>User Profile</strong></h2>
					<form name="updateProfile"
						action="${pageContext.request.contextPath}/preEditProfile"
						method="post">
						<table>
							<tr>
								<td><label>Full Name :</label></td>
								<td><span>${user.fullName} </span></td>
							</tr>
						
							<tr>
								<td><label>Login Name :</label></td>
								<td><span>${user.loginName}</span></td>
							</tr>
				
							<tr>
								<td><label>Email :</label></td>
								<td><span>${user.email}</span></td>
							</tr>
						
							<tr>
								<td><label>Address Line 1 :</label></td>
								<td><span>${user.addressLine1}</span></td>
							</tr>
						
							<tr>
								<td><label>Address Line 2 :</label></td>
								<td><span>${user.addressLine2}</span></td>
							</tr>
							
							<tr>
								<td><label>State :</label></td>
								<td><span>${user.state}</span></td>
							</tr>
							
							<tr>
								<td><label>City :</label></td>
								<td><span>${user.city}</span></td>
							</tr>
							
							
							<tr>
								<td><label>Zip Code :</label></td>
								<td><span>${user.postalCode}</span></td>
							</tr>
							
							<tr>
								<td><label>Phone Number :</label></td>
								<td><span>${user.phone}</span></td>
							</tr>
					
							<tr>
								<td><label>Mobile Number :</label></td>
								<td><span>${user.mobileNumber}</span></td>
							</tr>
						
							<tr>
								<td style="text-align: center;"><button class="btn btn-default">Update Profile</button></td>
							</tr>
						</table>
					</form>
				</div>
				<!--/sign up form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->

<jsp:include page="footer.jsp" flush="true" />