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
    <meta charset="UTF-8" />
    <title>DashBoard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
  </head>

  <body>
    <div class="container mt-5">
      <h1>Welcome,  ${thisUser.userName} !</h1>
      <a href="/logout" class="btn btn-link"> Logout</a>
    </div>
  <table class="table container mx-auto mt-5 w-50" border = "1">
		<thead>
			<tr>
				<th scope="col"> Name</th>
				<th scope="col">Genre </th>
				<th scope="col">Info</th>
			</tr>
		</thead>
		<tbody>
      <c:forEach var="game" items="${games}">
        <tr>
          <td><a href= "/view/${game.id}/game">${game.name}</a></td>
          <td>${game.genre}</td>
          <td>${game.info}</td> 
    </tr>
      </c:forEach>
   


		</tbody>
	</table>
	<div class="container mt-5">
  	<a href="/game/form" class="btn btn-link"> Create New Game </a>
	</div>



    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>