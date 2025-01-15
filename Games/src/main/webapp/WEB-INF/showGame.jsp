<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game Details</title>
</head>
<body>
	<div class= "container">
		<h2>${team.name}</h2>
		<a href="/home" class="btn btn-link"> Dashboard</a>
	</div>
	
	<div class="container mx-auto mt-5" border = "2">
      <h4>
        Game name :
        <span>${game.name} </span>
      </h4>
      <h4>Added By: <span> ${game.creator.userName}</span></h4>
      <h4>Genre : <span>${game.genre}</span></h4>
      <h4>Info: <span>${game.info} </span></h4>
    </div>
    
    <div>
    <c:if test="${game.creator.id == newUser }">
    	<button type="button" class="btn btn-primary">
    	<a class="btn btn-danger" href="/edit/${game.id}/game" class="btn btn-link"> Edit</a>
    	</button>
    	<form action="/delete/${game.id}/game" method="post">
    	<input type="hidden" name= "_method" value= "delete"> <input class="btn btn-danger" type="submit" value="Delete">
    	</form>
    	</c:if>
    </div>

    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>