<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.MenuItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Menu</title>
<link rel="stylesheet" href="style.css">
<!-- Add your stylesheet link here if needed -->
</head>
<body>
	<header id="container-menu">
		<p>Villa Eleganza</p>
	</header>
	<main>
		<div id="box-main">
			<h2>Menu</h2>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Ingredientes</th>
					<th>Tipo</th>
				</tr>
				<%
    List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
    for (MenuItem menuItem : menuItems) {
%>
        <tr>
            <td><%= menuItem.getId() %></td>
            <td><%= menuItem.getName() %></td>
            <td><%= menuItem.getIngredients() %></td>
            <td><%= menuItem.getType() %></td>
        </tr>
<%
    }
%>
			</table>
			<button onclick="goBack()">Página Principal</button>

			<script>
    function goBack() {
        window.location.href = "villaHome.jsp";
    }
</script>
		</div>
	</main>
</body>
</html>
