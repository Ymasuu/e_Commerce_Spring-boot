<%@ page import="com.e_Commerce.e_Commerce.model.entity.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Convertir Points en Solde</title>
    <link rel="icon" href="../logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="../logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
        }

        input, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: #f8f8f8;
            color: #333;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
            color: #fff;
        }
    </style>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Convertir Points en Solde</h1>
    <%
    Utilisateur utilisateur = Controller.getInstanceController().requestGetUtilisateur();
    Client client = UtilisateurDAO.findClientByUtilisateur(utilisateur);
    assert client != null;%>
<% if(errorMessage != null){ %>
<p class="error" style="color : red;text-align : center"><%= errorMessage %></p>
<% } %>
<form action="ServletConvertPoints" method="post">
    <p>Votre nombre de points de fidélité est de : <%= client.getPoints()%></p>
    <label for="quantite">Choisissez la quantité à convertir :</label>
    <input type="number" id="quantite" name="quantite" value ="0" min="0" required>

    <button type="submit" name="action" value="convertir">Convertir</button>
    <button type="submit" name="action" value="annuler">Retour</button>
</form>
</body>
</html>
