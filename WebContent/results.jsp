<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My query results</title>
</head>
<body>

<h1>List of related artists for: <c:out value="${artist}"/></h1>

<c:forEach items="${results}" var="item">
	<a href="${item}"><c:out value="${item}"/></a> --- <a href="http://localhost:8080/youtube_recommender/music_recommendation?music_of=${item}">Show artist's tracks</a><br>
</c:forEach>

<br> 
<a href="index.jsp">Go back to the index page </a>

</body>
</html>