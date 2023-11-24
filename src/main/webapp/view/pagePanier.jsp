<%@ page import="com.e_Commerce.e_Commerce.model.entity.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%Boolean panierVide = (Boolean) request.getAttribute("panierVide");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
    <link rel="icon" href="../logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="../logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        }
        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
        }
        h1 {
            text-align: center;
            color: #333;
            padding: 20px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #333;
            color: #fff;
        }
        form {
            display: inline-block;
        }
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
            margin-right: 10px;
        }
        button:hover {
            background-color: #0056b3;
        }
        p {
            margin-top: 15px;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
        }
        .payment-form {
            display: none;
        }
        .hidden {
            display: none;
        }
        .button-container {
            text-align: center;
            margin-top: 10px;
        }
        .button-container button {
            background-color: #333;
        }
        .button-container button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Panier</h1>
<table border="1">

    <tr>
        <th>Produit</th>
        <th>Prix unitaire</th>
        <th>Quantité</th>
        <th>Prix total</th>
        <th>Action </th>
    </tr>

    <%
        List<Produit> panier = Controller.getInstanceController().requestGetPanier();
        int stockAvailable = 1;
        float montantTotal = Controller.getInstanceController().requestGetCommande().getPrix();
        if (panier != null) {
            for (Produit produit : panier) {
                Produit produitInStock = ProduitDAO.getProduitById(produit.getIdProduit());
                assert produitInStock != null;
                stockAvailable = produitInStock.getStock();
    %>

    <tr>
        <form action="ServletPanier" method="get">
            <td><%=produit.getNom()%> <br> <img src="imagesProduct/<%=produit.getIdProduit()%>.jpeg" alt="
                            <%=produit.getNom()%>" width="100"></td>
            <td><%=produit.getPrix()%> €</td>
            <td><input type="number" name="produitQuantite" min="1" max="<%= stockAvailable %>" value="<%=produit.getStock()%>"></td>
            <td><%=produit.getPrix() * produit.getStock()%> €</td>
            <td>
                <input type="hidden" name="produitId" value="<%=produit.getIdProduit()%>">
                <button type="submit" name="action" value="supprimer">Supprimer</button>
                <button type="submit" name="action" value="modifier">Modifier</button>
            </td>
        </form>
    </tr>

    <%
            }
        }
    %>
</table>

<p>Montant total : <%=montantTotal%>  €</p>
<div class="button-container">
    <form action="ServletPanier" method="post">
        <button type="submit" name="action" value="vider">Vider le panier</button>
        <button type="submit" name="action" value="payer">Payer</button>
    </form>

    <% if (panierVide != null && panierVide) { %>
    <h3 style="color: red;">Ajoutez des produits pour les acheter!</h3>
    <% } %>

</div>
</body>
</html>