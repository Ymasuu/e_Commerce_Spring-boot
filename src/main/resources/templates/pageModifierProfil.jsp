<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Utilisateur" %>
<%@ page import="entity.Client" %>
<%@ page import="entity.Moderateur" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="ecommerce.ecommerce.model.DAO.UtilisateurDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier le Profil</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {font-family: Arial, sans-serif; background-color: #f0f0f0; margin: 0; padding: 0;}
        .container { max-width: 800px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);}
        h1 {color: #333;}
        h2 {color: #555;}
        ul {list-style-type: none; padding: 0;}
        li {margin: 10px 0;}
        strong {color: #333;}
        a {text-decoration: none; color: #0077cc;}
        label {display: block; margin-bottom: 5px;}
        input {padding: 8px; margin-bottom: 10px;}
        input[type="submit"] {background-color: #007bff; color: #fff; border: none; border-radius: 3px; padding: 10px 20px; cursor: pointer; margin-top: 20px;}
        input[type="submit"]:hover {background-color: #0056b3;}
    </style>
</head>
<body>
<div class="header">
<%--    <%@ include file="header.html" %>--%>
</div>
<div class="container">
    <% Utilisateur u = Controller.getInstanceController().requestGetUtilisateur(); %>
    <h1>Modifier le Profil</h1>
    <form action="ServletModifierProfil" method="post">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom"  pattern="[A-Za-zÀ-ÿ\s\-]+" value="<%= u.getNom() %>"><br>

        <label for="prenom">Prenom :</label>
        <input type="text" id="prenom" name="prenom"  pattern="[A-Za-zÀ-ÿ\s\-]+" value="<%= u.getPrenom() %>"><br>

        <label for="email">Email :</label>
        <input type="text" id="email" name="email" value="<%= u.getMail() %>"><br>

        <label for="mdp">Mot de passe :</label>
        <input type="password" id="mdp" name="mdp" required><br>

        <input type="submit" value="Valider">
    </form>
</div>
</body>
</html>