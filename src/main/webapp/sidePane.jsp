<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div class="col-sm-3">
	<div class="left-sidebar">
		<h2>Category</h2>
		<div class="panel-group category-products" id="accordian">
			<c:forEach items="${catList}" var="current">
				<div class="panel panel-default">
					<div class="panel-heading">
						<c:choose>
							<c:when test="${categoryName==current.categoryName}">
								<h4 class="panel-title-highlighted">
									<a
										href="/SapeStore/bookListByCat?categoryId=${current.categoryId}&categoryName=${current.categoryName}&checkMe=${checkMe}"
										title="${current.categoryName}"> ${current.categoryName }</a>
								</h4>
							</c:when>
							<c:otherwise>
								<h4 class="panel-title">
									<a
										href="/SapeStore/bookListByCat?categoryId=${current.categoryId}&categoryName=${current.categoryName}&checkMe=${checkMe}"
										title="${current.categoryName}"> ${current.categoryName }</a>
								</h4>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>