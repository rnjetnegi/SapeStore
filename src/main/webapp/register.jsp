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
			<div class="col-sm-8 col-sm-offset-2">
				<div class="signup-form">
					<!--sign up form-->
					<h2>New User Signup</h2>
					<form:form id="registration" name="registration"
						action="/SapeStore/confirmRegistrationDetails" method="POST"
						commandName="registerUser">
						<table>
						<tr><td><label>Full Name : </label></td>
						<td><form:input type="text" path="fullName" placeholder="Full Name" /></td>
						<td><span class="errMsg" id="nameErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Login Name : </label></td>
						<td><form:input type="text" path="loginName"
							placeholder="Preferred Login Name" /></td>
						<td><span class="errMsg" id="loginNameErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Password : </label></td>
						<td><form:input type="password" path="password" placeholder="Password" /></td>
						<td><span class="errMsg" id="passwordErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Confirm Password: </label></td>
						<td><input type="password" name="cnfPassword" onblur="matchPassword()"
							placeholder="Confirm Password" /></td>
						<td><span class="errMsg" id="cnfPasswordErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Email : </label></td>
						<td><form:input type="email" path="email" placeholder="Email Address" /></td>
						<td><span class="errMsg" id="emailErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Address Line 1 : </label></td>
						<td><form:input type="text" path="addressLine1"
							placeholder="Address Line 1" /></td>
						<td><span class="errMsg" id="addr1ErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Address Line 2 : </label></td>
						<td><form:input type="text" path="addressLine2"
							placeholder="Address Line 2" /></td>
						<td><span class="errMsg" id="addr2ErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>State : </label></td>
						<td><form:select path="state" id="register_state">
							<form:option value="NONE" label="--- State ---" />
							<c:forEach items="${states}" var="stateName">
								<form:option value="${stateName.stateName }"
									label="${stateName.stateName }" />
							</c:forEach>
						</form:select></td>
						<td><span class="errMsg" id="stateErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>City : </label></td>
						<td><form:select path="city" id="register_city">
							<form:option value="NONE" label="--- City ---" />
						</form:select></td>
						<td><span class="errMsg" id="cityErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Zip Code : </label></td>
						<td><form:input type="text" path="postalCode" placeholder="Zip Code" /></td>
						<td><span class="errMsg" id="zipErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Mobile Number : </label></td>
						<td><form:input type="text" path="mobileNumber"
							placeholder="Mobile Number" /></td>
						<td><span class="errMsg" id="mobileErrorMessage"></span></td>
						</tr>
						<tr>
						<td><label>Phone Number : </label></td>
						<td><form:input type="text" path="phone" placeholder="Phone Number" /></td>
						<td><span class="errMsg" id="phoneErrorMessage"></span></td>
						</tr>
						<tr>
						<td></td>
						<td class="pull-right">
						<button type="button" class="btn btn-default"
							onclick="validateForm()">Create Profile</button>
							</td>
							</tr>
							</table>
					</form:form>
				</div>
				<!--/sign up form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->
<script type="text/javascript" src="js/registerValidation.js">
<!--
	
//-->
</script>
<jsp:include page="footer.jsp" flush="true" />