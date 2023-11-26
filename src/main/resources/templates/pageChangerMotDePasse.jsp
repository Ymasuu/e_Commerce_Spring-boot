<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Utilisateur" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="ecommerce.ecommerce.model.DAO.UtilisateurDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Changer le Mot de Passe</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {font-family: Arial, sans-serif; background-color: #f0f0f0; margin: 0; padding: 0;}
        .container { max-width: 800px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);}
        h1 {color: #333;}
        form {margin-top: 20px;}
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
    <h1>Changer le Mot de Passe</h1>
    <form action="ServletChangerMotDePasse" method="post">
        <label for="oldPassword">Ancien Mot de Passe :</label>
        <input type="password" id="oldPassword" name="oldPassword" required><br>

        <label for="newPassword">Nouveau Mot de Passe :</label>
        <input type="password" id="newPassword" name="newPassword" required><br>

        <label for="confirmPassword">Confirmer le Nouveau Mot de Passe :</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br>

        <input type="submit" value="Valider">
    </form>
</div>
</body>
</html>