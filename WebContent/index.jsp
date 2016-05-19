<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
form { 
	margin: 0 auto; 
	width:250px;
}
</style>
</head>
<body>
<h1>YouTube-DBPedia based music recommender</h1><br><br>

<form action="process" method="post">
	<h4>Please insert a valid YouTube link:</h4><br>
  	<input type="text" name="youtube_link" style="width: 300px;"><br><br>
	<input type="submit" value="submit">
</form>
</body>
</html>
