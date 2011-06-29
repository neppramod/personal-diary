<%-- 
    Document   : entry_list
    Created on : Jun 1, 2011, 7:40:06 AM
    Author     : pramod
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entry List</title>
</head>
<body>
	<div style="width: 600px;" align="center">
		<c:if test="${empty dateString}">
			<fmt:formatDate value='<%=new Date() %>' type='DATE'
				pattern='yyyy-MM-dd' />
		</c:if>
		<c:if test="${! empty dateString }">
    ${dateString}    
</c:if>
		<table cellspacing="1" cellpadding="5">
			<tr style="color: #FFFFFF; background-color: #6633CC">
				<th>S.N</th>
				<th>Title</th>
				<th>Date</th>
				<th>Description</th>
			</tr>
			<%
		// No logic should have come here,
		// but bringing this value from controller is not an intelligent idea.		
		int i = 1 +(Integer)request.getAttribute("start");
		String[] color = {"#330099", "#6699CC"};
	%>
			<c:forEach items="${messageList}" var="message">
				<tr style="color: #FFFFFF">
					<td style="text-align: center; background-color: <%=color[i % 2]%>"><%=i%></td>
					<td
						style="text-align: left; background-color: <%=color[(i + 1) % 2]%>"><a
						style="color: #CCFF33;" href="edit_entry.htm?id=${message.id}">${message.title}</a>
					</td>
					<td style="text-align: center; background-color: <%=color[i % 2]%>"><fmt:formatDate
							value='${message.dateCreated}' type='DATE' pattern='yyyy-MM-dd' />
					</td>
					<td
						style="text-align: left; background-color: <%=color[(i + 1) % 2]%>">${message.description}</td>
				</tr>
				<%
       		// Increment the counter so that we don't have to depend upon id.
       		i++;
       %>
			</c:forEach>
			<tr>
				<td colspan="3"><c:forEach var="j" begin="1" end="${pages + 1}">
						<c:if test="${! empty dateString }">
							<a href="entry_list.htm?date=${dateString}&page=${j}">${j}</a>&nbsp;
    			</c:if>
						<c:if test="${ empty dateString }">
							<a href="entry_list.htm?date&page=${j}">${j}</a>&nbsp;
    			</c:if>
					</c:forEach>
				</td>
			</tr>
		</table>
		<a href="entry.htm?date=${dateString}">Create an entry</a> <a
			href="index.htm">Home</a>
	</div>
</body>
</html>
