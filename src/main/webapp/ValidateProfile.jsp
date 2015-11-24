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
			<div class="col-sm-4 col-sm-offset-4">
				<div class="validate-profile-form">
					<!--validate-profile-form-->
					<h2>Confirm User Details!</h2>
					<form name="validateProfile" id="validateProfile"
						action="${pageContext.request.contextPath}/register" method="post">
						<table>
							<tr>
								<td><label>Full Name </label></td>
								<td><span>${confirmUser.fullName}</span></td>
							</tr>
							<tr>
								<td><label>Preferred Login Name </label></td>
								<td><span>${confirmUser.loginName}</span></td>
							</tr>

							<tr>
								<td><label>Email </label></td>
								<td><span>${confirmUser.email}</span></td>
							</tr>
							<tr>
								<td><label>Address Line 1 </label></td>
								<td><span>${confirmUser.addressLine1}</span></td>
							</tr>
							<tr>
								<td><label>Address Line 2 </label></td>
								<td><span>${confirmUser.addressLine2}</span></td>
							<tr>
								<td><label>City </label></td>
								<td><span>${confirmUser.city}</span></td>
							</tr>
							<tr>
								<td><label>State </label></td>
								<td><span>${confirmUser.state}</span></td>
							</tr>
							<tr>
								<td><label>Zip Code </label></td>
								<td><span>${confirmUser.postalCode}</span></td>
							</tr>
							<tr>
								<td><label>Phone Number </label></td>
								<td><span>${confirmUser.phone}</span></td>
							</tr>
							<tr>
								<td><label>Mobile Number</label></td>
								<td><span>${confirmUser.mobileNumber}</span></td>
							</tr>
							<tr>
								<td><button type="submit" class="btn btn-default">Confirm Registration</button></td>
											
								<td><button id="btnBack" class="btn btn-default">Back</button></td>
													
							</tr>
						</table>
					</form>
				</div>
				<!--/validate-profile-form-->
			</div>
		</div>
	</div>
</section>
			<script>
	$('#btnBack').click(
			function() {
				
				$('#validateProfile').attr('action',
						'${pageContext.request.contextPath}/preRegister')
						.submit();
			});
</script>			

<jsp:include page="footer.jsp" flush="true" />