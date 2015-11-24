<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="header.jsp" flush="true" />
<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<div class="col-sm-7 col-sm-offset-1">
				<div class="user-registered-form">
					<!--user registered form-->
					<h2>You have been registered successfully</h2>
					<h3>Please check Your Email to activate the account!</h3>
<%-- 					<form action="${pageContext.request.contextPath}/welcome"> --%>
					<form action="${pageContext.request.contextPath}/afterRegistration" method="post">
						<button class="btn btn-default">OK</button>
					</form>
				</div>
				<!--/user registered form-->
			</div>
		</div>
	</div>
</section>
<jsp:include page="footer.jsp" flush="true" />