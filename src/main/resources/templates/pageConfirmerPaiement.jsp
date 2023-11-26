<%@ page import="javax.naming.ldap.Control" %>
<%@ page import="entity.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Confirmer le Paiement</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <!-- Bootstrap CSS CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
        .title {
            color: #333;
            text-align: center;
            padding: 20px;
        }
        .content {
            max-width: 400px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            letter-spacing: 1em;
            -webkit-text-security: disc;
        }
        input[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }
    </style>
    <script>
        function formatCarteBancaire() {
            var numeroCarte = document.getElementById("numeroCarte");
            var regex = /^[0-9 ]+$/;

            // Retirer les espaces de la saisie de l'utilisateur
            var numeroCarteSansEspaces = numeroCarte.value.replace(/ /g, '');

            // Formater le numéro de carte bancaire avec des espaces tous les 4 caractères
            var numeroCarteFormatee = numeroCarteSansEspaces.replace(/(.{4})/g, '$1 ');

            // Mettre à jour la valeur dans le champ
            document.getElementById("numeroCarte").value = numeroCarteFormatee.trim();

            return true;
        }
    </script>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String carteBancaireNum = (String) Controller.getInstanceController().requestGetClient().getCompteBancaireNum();
%>
<div class="header">
<%--    <%@ include file="header.html" %>--%>
</div>
<div class="title">
    <h1>Confirmer le Paiement</h1>
</div>
<% if(errorMessage != null){ %>
<p class="error" style="color : red;text-align : center"><%= errorMessage %></p>
<% } %>
<div class="container">
    <div class="content">
        <%
            if (carteBancaireNum != null && !carteBancaireNum.equals("0000 0000 0000 0000")) {
                String lastFourDigits = carteBancaireNum.substring(carteBancaireNum.length() - 4);
        %>
        <p class="text-center"><strong>Carte Bancaire :</strong> **** **** **** <%= lastFourDigits %> </p>
        <%
            }
        %>
        <form action="ServletPayerCommande" method="post" onsubmit="return formatCarteBancaire()">
            <div class="form-group">
                <label for="numeroCarte">Confirmez votre carte bancaire :</label>
                <input type="text" class="form-control" id="numeroCarte" name="numeroCarte" required pattern="[0-9 ]+">
            </div>
            <button type="submit" class="btn btn-dark">Payer</button>
        </form>
    </div>
</div>
<!-- Bootstrap JS and Popper.js CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
