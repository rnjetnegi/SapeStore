<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!--header--><jsp:include page="adminHeader.jsp" flush="true" /><!--/header-->

<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-1">
				<div class="login-form">
					<!--login form-->
					<h2>Administrator Login</h2>
					<form:form id="login" name="loginform" action="${pageContext.request.contextPath}/adminLogin"
						method="POST" commandName="userlogin">
						<input type="text" name="userId" placeholder="User Name" />
						<span class="errMsg" id="loginNameErrorMessage"></span>
						<input type="password" name="password" placeholder="Password" />
						<span class="errMsg" id="passwordErrorMessage"></span>
						<button type="button" class="btn btn-default"
							onclick="validateForm()">Login</button>
					</form:form>
					<span class="errMsg" id="loginErrorMessage"></span>
				</div>
				<!--/login form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->
<script type="text/javascript" src="/SapeStore/js/loginValidation.js"></script>


<!--footer--><jsp:include page="footer.jsp" flush="true" /><!--/footer-->
