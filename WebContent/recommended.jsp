<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recommended tracks</title>
</head>
<body>

<h1>Recommended tracks</h1>

<!--  what is missing here is to enter the single track value into the youtube search link and review the query for tracks (more cases) -->
<c:forEach items="${tracks}" var="item">
	<c:set var="dateParts" value="${fn:split(item, '/')}" />
	
	<!--<c:forEach var="i" items="${dateParts}">
		<c:out value="${i}" />
	</c:forEach> -->
	
	<a href="${item}"><c:out value="${item}"/></a> --- <a href="https://www.youtube.com/results?search_query=${dateParts[3]}" target="_blank">Search on YouTube</a><br>
</c:forEach>

<br>
<a href="index.jsp">Go back to the index page </a>



</body>
</html>