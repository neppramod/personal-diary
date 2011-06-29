<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript" src="js/jquery-1.4.1.min.js"></script>
<script type="text/javascript">
    	$(document).ready(function() {    	
    		$("#title").attr("disabled", "disabled");
    		$("#description").attr("disabled", "disabled");
    		
    		$("#editBtn").click(function(){    		 	
    			$("#title").attr("disabled", "");
    			$("#description").attr("disabled", "");
    		});
        });
    </script>
</head>
<body>

	<form:form method="post" modelAttribute="message"
		action="saveEntry.htm">
		<form:errors path="*" cssClass="error" />
		<form:hidden path="id" />
		<c:set var="postId" value="${id}" />
		<table>
			<tbody>
				<tr>
					<td>Title</td>
					<td><form:input path="title" />
					</td>
					<td><fmt:formatDate value='${message.dateCreated}' type='DATE'
							pattern='yyyy-MM-dd' /> <form:hidden path="dateCreated" />
					</td>
					<td><form:errors path="title" cssClass="error" /> <form:errors
							path="dateCreated" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3"><form:textarea path="description" rows="12"
							cols="50" />
					</td>
					<td><form:errors path="description" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>
			</tbody>
		</table>
		<input type="button" id="editBtn" name="editBtn" value="Edit" />
		<!--  We could also use history.back() -->
		<input type="button" name="cancelBtn" value="Cancel"
			onclick="history.go(-1)" />
		<input type="submit" value="Save" />
		<input type="button" value="Delete" name="btnDelete"
			onclick='location.href="deleteEntry.htm?id=${message.id}"' />
	</form:form>

</body>
</html>