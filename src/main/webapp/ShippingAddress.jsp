<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<jsp:include page="header.jsp" flush="true" />

<style> .error{color:red;} </style>
<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="signup-form">
					<!--sign up form-->
					<c:if test="${not empty addressList}">
					<h2>Existing Shipping Address</h2>
					<c:forEach var="item" items="${addressList}">
						<div class="col-sm-3" style="height: 200px; border: 2px;">
							<p>${item.name }</p>
							<p>${item.addressLine1 }</p>
							<p>${item.addressLine2 }</p>
							<p>${item.cityName }, ${item.stateName }</p>
							<a
								href="/SapeStore/confirmexistingaddress?addressId=${item.addressId}"
								class="btn btn-default">Use this</a>
						</div>
					</c:forEach>
					</c:if>
					<!--/sign up form-->
				</div>
			</div>
			<div class="col-sm-8">
				<div class="signup-form">
					<form:form onSubmit="return validateForm()" id="newAddr"
						name="newAddr" action="/SapeStore/addaddress" method="POST"
						commandName="newAddress">
						<legend>Create New Shipping Address</legend>
						<table>
							<tr>
								<td><label class="field">Name* </label></td>
								<td><form:input path="name" id="newAddr_name" type="text" value=""
										name="name" cssClass="formtext" /></td>
								<td class="error" id="nameErrorMessage"></td>
							</tr>
							<tr>
								<td><label class="field">Address Line 1* </label></td>
								<td><form:input path="addressLine1" id="newAddr_addressLine1" type="text" value=""
										name="addressLine1" cssClass="formtext" /></td>
								<td class="error" id="addr1ErrorMessage"></td>
							</tr>
							<tr>
								<td><label class="field">Address Line 2 </label></td>
								<td><form:input path="addressLine2" type="text" id="newAddr_addressLine2"
										name="addressLine2" value="" cssClass="formtext" /></td>
								<td class="error" id="addr2ErrorMessage"></td>
							</tr>
							<tr>
								<td><label class="field">State* </label></td>
								<td><form:select path="stateId" id="newAddr_stateId" name="stateId"
										cssClass="formtext">
										<form:option value="no" label="--- Select ---" />
										<c:forEach items="${states}" var="stateName">
											<form:option value="${stateName.stateId }"
												label="${stateName.stateName }" />
										</c:forEach>
									</form:select></td>
								<td class="error" id="stateErrorMessage"></td>
							</tr>
							<%-- <tr>
								<td><label class="field">City* </label></td>
								<td><form:select path="cityId" id="newAddr_cityId" name="cityId"
										cssClass="formtext">
										<form:option value="no" label="--- Select ---" />
										<c:forEach items="${cities}" var="cityName">
											<form:option value="${cityName.cityId }"
												label="${cityName.cityName }" />
										</c:forEach>
									</form:select></td>
								<td class="error" id="stateErrorMessage"></td>
							</tr> --%>
							 <tr>
								<td><label class="field">City* </label></td>
								<td><form:select path="cityId" id="newAddr_cityId" name="cityId" onchange="selectOption()"
										cssClass="formtext">
										<form:option value="0" selected="true">Select a city</form:option>  
										<form:option value="NONE" label="--- Select ---" />
									</form:select></td>
								<td class="error" id="cityErrorMessage"></td>
							</tr>
							<tr>
								<td><label class="field">Zip Code* </label></td>
								<td><form:input path="postalCode" type="text" id="newAddr_postalCode"
										name="postalCode" placeholder="XXXXX or XXXXX-XXXX "
										cssClass="formtext" /></td>
								<td class="error" id="zipErrorMessage"></td>
							</tr>
							<tr>
								<td><label class="field">Phone Number* </label></td>
								<td><form:input path="phone" name="phone" type="text" id="newAddr_phone"
										placeholder="XXX-XXX-XXXX" cssClass="formtext" /></td>
								<td class="error" id="phoneErrorMessage"></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<button class="btn btn-primary" onclick="validateForm()">Use this address</button>
								</td>
							</tr>
						</table>
					</form:form>
					<script>
					function selectOption() {
						var strUser = $("#newAddr_cityId");
						document.cookie = "cityId = ".concat(strUser.val());
						//alert(strUser.val());
					};
					</script>
				</div>
			</div>
		</div>
	</div>
</section>
<!--/form-->
<script type="text/javascript" src="js/shipAddress.js">
<!--
	
//-->
</script>
<jsp:include page="footer.jsp" flush="true" />