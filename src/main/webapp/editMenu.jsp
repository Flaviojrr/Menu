<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.DAOMenuItem" %>
<%@ page import="model.MenuItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Editar Prato</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header id="container-menu">
        <p class="menu-title">Villa Eleganza</p>
    </header>
    <main>
        <div id="box-main">
            <h2 class="menu-heading">Editar Prato</h2>
            <%
                String itemId = request.getParameter("id");
                DAOMenuItem daoMenuItem = new DAOMenuItem();
                MenuItem menuItem = daoMenuItem.getMenuItemById(itemId);
            %>
            <form action="Controller" method="post" accept-charset="UTF-8">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="idToUpdate" value="<%=itemId%>">
                <table>
                    <tr>
                        <td><p>Nome: </p><input type="text" name="name" value="<%=menuItem.getName()%>" class="input-field"></td>
                    </tr>
                    <tr>
                        <td><p>Ingredientes: </p><input type="text" name="ingredients" value="<%=menuItem.getIngredients()%>" class="input-field"></td>
                    </tr>
                    <tr>
                        <td>
                            <p>Tipo: </p>
                            <select name="type" class="input-field">
                                <option value="entrada" <%=selected("entrada", menuItem.getType())%>>Entrada</option>
                                <option value="principal" <%=selected("principal", menuItem.getType())%>>Principal</option>
                                <option value="sobremesa" <%=selected("sobremesa", menuItem.getType())%>>Sobremesa</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Atualizar" class="botao">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </main>
</body>
</html>

<%!
    // Helper method to simplify selected attribute in the dropdown
    private String selected(String expected, String actual) {
        return expected.equals(actual) ? "selected" : "";
    }
%>
