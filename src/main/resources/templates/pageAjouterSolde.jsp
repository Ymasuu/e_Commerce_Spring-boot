<%@ page import="javax.naming.ldap.Control" %>
<%@ page import="entity.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Solde</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
</head>
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

    select, button {
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
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Ajouter un Solde</h1>
<%
    Utilisateur utilisateur = Controller.getInstanceController().requestGetUtilisateur();
    Client client = UtilisateurDAO.findClientByUtilisateur(utilisateur);
    assert client != null;%>


<form action="ServletAjouterSolde" method="post">
    <% if(errorMessage != null){ %>
    <p class="error" style="color : red;text-align : center"><%= errorMessage %></p>
    <% } %>
    <p>Votre solde actuel est de : <%= client.getCompteBancaireSolde() %></p>
    <label for="numeroCarte">Numéro de Carte Bleue:</label>
    <input type="text" id="numeroCarte" name="numeroCarte" required placeholder="Exemple : **** **** **** ****"><br><br>

    <label for="montant">Montant</label>
    <input type="text" id="montant" name="montant" pattern="[0-9]+" required placeholder="Veuillez entrer le montant"><br><br>


    <!-- Autres champs du formulaire si nécessaire -->

    <!-- Bouton de soumission du formulaire -->
    <button type="submit">Ajouter Solde</button>
</form>
</body>
</html>
