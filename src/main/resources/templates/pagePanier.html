<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
    <link rel="icon" href="/logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/logo/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/css/header.css">
    <style>
        /*Your CSS styles remain the same*/
    </style>
</head>
<body>
<header th:insert="~{header.html :: header}"></header>
<h1>Panier</h1>
<table border="1">
    <thead>
    <tr>
        <th>Produit</th>
        <th>Prix unitaire</th>
        <th>Quantité</th>
        <th>Prix total</th>
        <th>Action </th>
    </tr>
    </thead>
    <tbody>
    <!-- Thymeleaf iteration over the panier -->
    <tr th:each="produit : ${panier}">
        <form action="ServletPanier" method="get">
            <td>
                <span th:text="${produit.nom}"></span><br>
                <img th:src="'imagesProduct/' + ${produit.idProduit} + '.jpeg'" alt="" th:alt="${produit.nom}" width="100">
            </td>
            <td th:text="${produit.prix} + ' €'"></td>
            <td>
                <input type="number" name="produitQuantite" min="1" th:max="${produit.stock}" th:value="${produit.stock}">
            </td>
            <td th:text="${produit.prix * produit.stock} + ' €'"></td>
            <td>
                <input type="hidden" name="produitId" th:value="${produit.idProduit}">
                <button type="submit" name="action" value="supprimer">Supprimer</button>
                <button type="submit" name="action" value="modifier">Modifier</button>
            </td>
        </form>
    </tr>
    </tbody>
</table>

<p>Montant total : <span th:text="${montantTotal} + ' €'"></span></p>
<div class="button-container">
    <form action="ServletPanier" method="post">
        <button type="submit" name="action" value="vider">Vider le panier</button>
        <button type="submit" name="action" value="payer">Payer</button>
    </form>

    <!-- Thymeleaf conditional check -->
    <h3 th:if="${panierVide} and ${panierVide == true}" style="color: red;">Ajoutez des produits pour les acheter!</h3>
</div>
</body>
</html>
