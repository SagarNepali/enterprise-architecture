<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cars currently in the shop</title>
</head>

<body>
	<h1>Cars currently in the shop</h1>
	<table>
		<c:forEach var="car" items="${cars}">
			<tr>
				<td>${car.make}</td>
				<td>${car.model}</td>
				<td>${car.year}</td>
				<td>${car.color}</td>
				<sec:authorize access="!hasRole('USER')">
					<td><a href="cars/${car.id}">edit</a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>

	<sec:authorize access="!hasRole('USER')">
		<a href="cars/add"> Add a Car</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a href="${pageContext.request.contextPath}/logout">Logout</a>
	</sec:authorize>
</body>

</html>