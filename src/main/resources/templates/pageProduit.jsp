<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entity.Produit" %>

<%
    Produit produit = (Produit) request.getAttribute("produit");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page Produit</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            margin: 10px 0;
        }

        h1 {
            text-align: center;
            background-color: #333;
            color: #fff;
            padding: 10px;
        }

        .container {
            display: flex;
            justify-content: center;
        }

        .product-details {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
            text-align: center;
        }

        .product-image {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Détails du Produit</h1>
<div class="container">
    <div class="product-details">
        <img src="imagesProduct/<%= produit.getIdProduit() %>.jpeg" class="product-image" alt="<%= produit.getNom() %>">
        <ul>
            <li><strong>Nom du Produit:</strong> <%= produit.getNom() %></li>
            <li><strong>Description:</strong> <%= produit.getDescription() %></li>
            <li><strong>Prix:</strong> <%= produit.getPrix() %> €</li>
            <li><strong>Stock Disponible:</strong> <%= produit.getStock() %> unités</li>
            <%
                if(Controller.getInstanceController().requestGetUtilisateur()!=null){
                    Utilisateur user = Controller.getInstanceController().requestGetUtilisateur();
                    if(user.getTypeDeCompte().equals("Admin")){
                        %>
                        <form action="ServletModifierProduit" method="get">
                            <!-- Autres champs du formulaire pour la modification si nécessaire -->
                            <input type="hidden" name="idProduit" value="<%= produit.getIdProduit() %>">
                            <input type="submit" value="Modifier le produit">
                        </form>
                        <form action="ServletDeleteProduct" method="get">
                            <!-- Autres champs du formulaire si nécessaire -->
                            <input type="hidden" name="idProduit" value="<%= produit.getIdProduit() %>">
                            <input type="submit" value="Supprimer le produit">
                        </form>
                    <%}
                    if(user.getTypeDeCompte().equals("Moderateur")){
                        Moderateur moderateur = UtilisateurDAO.findModByUtilisateur(user);
                        if(moderateur.getDroits().charAt(1) == '1' ){
                            %>
                                <form action="ServletModifierProduit" method="get">
                                    <!-- Autres champs du formulaire pour la modification si nécessaire -->
                                    <input type="hidden" name="idProduit" value="<%= produit.getIdProduit() %>">
                                    <input type="submit" value="Modifier le produit">
                                </form>
                            <form action="ServletDeleteProduct" method="get">
                                <!-- Autres champs du formulaire si nécessaire -->
                                <input type="hidden" name="idProduit" value="<%= produit.getIdProduit() %>">
                                <input type="submit" value="Supprimer le produit">
                            </form>
            <%          }
                    }else if(user.getTypeDeCompte().equals("Client")){
            %>
                        <form action="ServletPanier" method="get">
                        <input type="hidden" name="produitId" value="<%= produit.getIdProduit() %>">
                        <input type="number" name="produitQuantite" min="1" max="<%= produit.getStock() %>" value="1"><br>
                        <input type="submit" name="action" value="ajouter">
                        </form>
            <%
                    }
                }
            %>
        </ul>
    </div>
</div>
<%
    Boolean suppression = (Boolean) request.getAttribute("suppression");
    if (suppression != null && suppression) {
%>
<p class="success">Le moderateur a ete supprime</p>
<%
    }
%>
</body>
</html>
