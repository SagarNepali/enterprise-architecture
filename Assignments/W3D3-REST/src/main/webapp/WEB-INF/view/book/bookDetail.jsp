<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${msg} a Book</title>
</head>

<body>

	<c:choose>
		<c:when test="${msg == 'Update'}">
			<c:set var="formAction" value="../books/${book.id}" />
		</c:when>
		<c:otherwise>
			<c:set var="formAction" value="../books" />
		</c:otherwise>
	</c:choose>

	<form:form modelAttribute="book" name="bookForm" action="${formAction}" method="post">
		<form:errors path="*" cssClass="errorblock" element="div" />

		<table>
			<tr>
				<td>Title:</td>
				<td><form:input path="title" /> </td>
				<td><form:errors path="title" class="form-control height30px" cssErrorClass="form-control height30px error"/></td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td><form:input path="ISBN" /> </td>
				<td><form:errors path="ISBN" cssClass="ui-state-error-text"/> </td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><form:input path="author" /> </td>
				<td><form:errors path="author" cssClass="ui-state-error-text"/> </td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price" /> </td>
				<td><form:errors path="price" cssClass="ui-state-error-text"/> </td>
			</tr>
		</table>
		<input type="submit" value="${msg}"/>
	</form:form>
	<c:if test="${msg == 'Update'}">
		<form:form action="delete?bookId=${book.id}" method="post">
			<button type="submit">Delete</button>
		</form:form>
	</c:if>
</body>

</html>