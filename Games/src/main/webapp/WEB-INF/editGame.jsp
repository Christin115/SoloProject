<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Game</title>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="container mx-auto w-50">
	
  <h1>Edit Game</h1>
  <a href="/home" class="btn btn-link"> Dashboard</a>
	
	</div>
	<form:form action="/update/${game.id}/game" method="post" modelAttribute="game"
		class="container mx-auto w-50">
	<input type="hidden" name= "_method" value= "put">
	<input type="hidden" name= "creator" value= "${newUser}">

		<p>
			<form:label path="name" class="form-label"> Name</form:label>
			<form:errors path="name" class="text-danger" />
			<form:input type="text" path="name" class="form-control" />
		</p>

		<p>
			<form:label path="genre" class="form-label">Genre</form:label>
			<form:errors path="genre" class="text-danger" />
			<form:input type="text" path="genre" class="form-control" />
		</p>

		<p>
			<form:label path="info" class="form-label">Info</form:label>
			<form:errors path="info" class="text-danger" />
			<form:input type="text" path="info" class="form-control" />
		</p>


		<input type="submit" value="Submit" class="btn btn-primary" />
		
	</form:form>
	
	<form action="/delete/${game.id}/game" method="post" class="container mx-auto w-50"> 
    	<input type="hidden" name= "_method" value= "delete"> <input class="btn btn-danger" type="submit" value="Delete">
	</form>

<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>