<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.MenuItem"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cardápio</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script>
        function confirmDelete(event) {
            var userConfirmed = confirm("Tem certeza que deseja excluir esse prato?");
            
            if (!userConfirmed) {
                alert("Exclusão cancelada.");
                event.preventDefault(); // Prevent form submission if the user cancels
            }

            return userConfirmed;
        }

        function goBack() {
            window.location.href = "villaHome.jsp";
        }
    </script>
</head>
<body>
    <header id="container-menu">
        <p class="menu-title">Villa Eleganza</p>
    </header>
    <main>
        <div id="box-main">
            <h2 class="menu-heading">Menu</h2>
            <table border="1" class="menu-table">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Ingredientes</th>
                    <th>Tipo</th>
                    <th>Ação</th>
                </tr>
                <% List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
                for (MenuItem menuItem : menuItems) { %>
                    <tr>
                        <td><%=menuItem.getId()%></td>
                        <td><%=menuItem.getName()%></td>
                        <td><%=menuItem.getIngredients()%></td>
                        <td><%=menuItem.getType()%></td>
                        <td>
                            <form action="Controller" method="post" onsubmit="return confirmDelete(event)">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="idToDelete" value="<%=menuItem.getId()%>">
                                <!-- Use a Font Awesome trash bin icon -->
                                <button type="submit" class="delete-button"><i class="fas fa-trash-alt"></i></button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </table>
            <button onclick="goBack()" class="back-button">Página Principal</button>
        </div>
    </main>
</body>
</html>
