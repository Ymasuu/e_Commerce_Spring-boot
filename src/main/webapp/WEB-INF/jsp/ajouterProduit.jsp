<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Produit</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
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
            max-width: 600px;
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

        input[type="text"],
        input[type="file"],
        textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function validerStock() {
            var stockInput = document.getElementById('stock');
            var stockValue = stockInput.value.trim();

            if (!stockValue.match(/^\d+$/)) {
                alert('Veuillez entrer une valeur entière pour le stock.');
                stockInput.focus();
                return false;
            }

            return true;
        }

        function validerPrix() {
            var prixInput = document.getElementById('prix');
            var prixValue = prixInput.value.trim();

            if (!prixValue.match(/^\d+(\.\d+)?$/)) {
                alert('Veuillez entrer une valeur numérique pour le prix.');
                prixInput.focus();
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Ajouter un Produit</h1>
<p class="error" style="color : red;text-align : center"><%= errorMessage %></p>
<form action="ServletAjouterProduit" method="post" onsubmit="return validerPrix() && validerStock()" enctype="multipart/form-data">
    <label for="nom">Nom du Produit:</label>
    <input type="text" id="nom" name="nom" required placeholder="Nom du produit"><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" cols="50" required placeholder="Ce produit sert à..."></textarea><br><br>

    <label for="prix">Prix:</label>
    <input type="text" id="prix" name="prix" required placeholder="Veuillez entrer une valeur numérique"><br><br>

    <label for="stock">Stock:</label>
    <input type="text" id="stock" name="stock" pattern="[0-9]+" required placeholder="Veuillez entrer une valeur entière"><br><br>

    <!-- Vous pouvez ajouter un champ pour télécharger une image ici si nécessaire -->
    <label for="image">Image du Produit:</label>
    <input type="file" id="image" name="image" accept="image/*" required>
    <br>

    <input type="submit" value="Ajouter">
</form>
</body>
</html>
