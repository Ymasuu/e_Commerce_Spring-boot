<jsp:useBean id="produit" scope="request" type="com.e_Commerce.e_Commerce.model.entity.Produit"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.e_Commerce.e_Commerce.model.entity.*" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Produits</title>
    <link rel="icon" href="../logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="../logo/logo.png" type="image/x-icon">
    <!-- Bootstrap CSS CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            padding: 10px;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        .produit {
            border: 1px solid #ddd;
            margin: 10px;
            padding: 10px;
            width: 250px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s; /* Ajout de l'animation de transition */
        }

        .produit:hover {
            transform: scale(1.05); /* Animation de zoom au survol */
        }

        .produit img {
            max-width: 100%;
            height: auto;
            cursor: pointer;
        }

        .produit h2 {
            font-size: 20px;
            margin: 10px 0;
        }

        .produit p {
            font-size: 16px;
            color: #777;
        }

        .footer {
            text-align: center;
            background-color: #333;
            color: #fff;
            padding: 10px 0;
        }
    </style>
</head>
<body>
<div class="header" th:replace="header :: header"></div>
<h1 class="display-4">Liste des Produits</h1>
<div class="container">
    <div th:each="produit : ${listeProduits}" class="produit">
        <a th:href="@{/produit/{id}(id=${produit.idProduit})}">
            <img th:src="@{/imagesProduct/{id}.jpeg(id=${produit.idProduit})}" alt="Nom du produit" width="100" class="img-fluid">
        </a>
        <h2 th:text="${produit.nom}"></h2>
        <p th:text="${produit.prix + ' €'}"></p>
        <!-- Ajoutez des fonctionnalités Thymeleaf pour l'ajout au panier -->
    </div>
</div>
<div class="footer">
    <p>&copy; 2023 AZUR. Tous droits réservés.</p>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
