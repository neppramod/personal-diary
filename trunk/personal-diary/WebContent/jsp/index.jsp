<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/jquery-1.4.1.min.js"></script>
<script type="text/javascript">
        $(document).ready(function() {
            var date = new Date();
            var m = date.getMonth();
            var y = date.getFullYear();
            var cal;

            /* Normal Load */
            cal = new CALENDAR(y, m + 1);
            cal.offset = 1;
            cal.weekNumbers = true;
            cal.tFontSize = 24;
            cal.hFontSize = 18;
            cal.dFontSize = 24;
            cal.wFontSize = 18;
            cal.link = 'entry_list.htm?page';
            $('#calendar').html(cal.create() + '</td><td>');


            /* Prev Clicked */
            $("#prev").click(function() {
                if (m != 0) {
                    m = m - 1;
                } else {
                    m = 11;
                    y = y - 1;
                }


                cal = new CALENDAR(y, m + 1);
                cal.offset = 1;
                cal.weekNumbers = true;
                cal.tFontSize = 24;
                cal.hFontSize = 18;
                cal.dFontSize = 24;
                cal.wFontSize = 18;
                cal.link = 'entry_list.htm?page';
                $('#calendar').html(cal.create() + '</td><td>');
            });


            /* Next Clicked */

            $("#next").click(function() {
                if (m != 11) {
                    m = m + 1;
                } else {
                    m = 0;
                    y = y + 1;
                }


                cal = new CALENDAR(y, m + 1);
                cal.offset = 1;
                cal.weekNumbers = true;
                cal.tFontSize = 24;
                cal.hFontSize = 18;
                cal.dFontSize = 24;
                cal.wFontSize = 18;
                cal.link = 'entry_list.htm?page';
                $('#calendar').html(cal.create() + '</td><td>');
            });
        });
    </script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal Diary</title>
</head>
<body>
	<h1>Personal Blog</h1>
	<DIV align="left" style="float: left; width: 200px;">
		<h2>Recent</h2>
		<ol>
			<c:forEach items="${messageList}" var="message">
				<li><fmt:formatDate value='${message.dateCreated}' type='DATE'
						pattern='MM-dd' />:&nbsp;<a
					href="edit_entry.htm?id=${message.id}">${message.title}</a>
				</li>
			</c:forEach>
		</ol>
	</DIV>
	<DIV align="center" style="float: left;">
		<FORM action="search.htm">
			<INPUT type="text" name="query" size="45">&nbsp;&nbsp;&nbsp;
			<INPUT type="hidden" name="page" /> <input type="submit"
				value="Search" />
		</FORM>
		<br />

		<div align="center">
			<a
				href="entry.htm?date=<fmt:formatDate value='<%=new Date() %>' type='DATE' pattern='yyyy-MM-dd'/>">New</a>
			<a href="entry_list.htm?date&page">List</a>
		</div>
		<br /> <a href="#" id="prev" style="float: left;"><img
			src="images/prev.png" alt="Previous" width="32" height="32"
			title="Previous" /> </a>

		<div id="calendar" style="float: left;"></div>
		<a href="#" id="next"><img style="float: left;"
			src="images/next.png" alt="Next" width="32" height="32" title="Next" />
		</a>
	</DIV>


</body>
</html>