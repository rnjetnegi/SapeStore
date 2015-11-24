<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!--header--><jsp:include page="header.jsp" /><!--/header-->
<c:if test="${not empty userLoginName }">
	<jsp:forward page="/welcome"></jsp:forward>
</c:if>
<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-1">
				<div class="login-form">
					<!--login form-->
					<h2>Login to your account</h2>
					<form:form id="login" name="loginform"
						action="${pageContext.request.contextPath}/login" method="POST"
						commandName="userlogin">
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
			<div class="col-sm-1">
				<h2 class="or">OR</h2>
			</div>
			<div class="col-sm-4">
				<div class="signup-form">
					<!--sign up form-->
					<h2>New User!</h2>
					<form action="${pageContext.request.contextPath}/preRegister"
						method="POST">
						<p>Its free and easy!</p>
						<button type="submit" class="btn btn-default">Signup Here</button>
					</form>
				</div>
				<!--/sign up form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->
<script type="text/javascript" src="js/loginValidation.js"></script>


<!--footer--><jsp:include page="footer.jsp" flush="true" /><!--/footer-->
