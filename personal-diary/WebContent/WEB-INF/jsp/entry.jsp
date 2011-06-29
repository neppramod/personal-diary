<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entry</title>
<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>
</head>
<body>
	<form:form method="post" modelAttribute="message"
		action="saveEntry.htm">
		<table>
			<tbody>
				<tr>
					<td>Title</td>
					<td><form:input path="title" /></td>
					<td><form:input path="dateCreated"
							title="Input date in yyyy-MM-dd format. E.g 2005-06-07" />(yyyy-MM-dd)</td>
					<td><form:errors path="title" cssClass="error" /> <form:errors
							path="dateCreated" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td colspan="3"><form:textarea path="description" rows="12"
							cols="50" /></td>
					<td><form:errors path="description" cssClass="error" /></td>
				</tr>
			</tbody>
		</table>
		<input type="button" name="editBtn" value="Edit" disabled="disabled" />
		<input type="button" name="deleteBtn" value="Delete"
			disabled="disabled" />
		<input type="button" name="cancelBtn" value="Cancel"
			onclick="history.go(-1)" />
		<input type="submit" value="Save" />
	</form:form>
</body>
</html>