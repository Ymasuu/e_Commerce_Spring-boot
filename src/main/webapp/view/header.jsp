<!--<%@ page import="com.e_Commerce.e_Commerce.model.entity.Utilisateur" %>
<%@ page import="com.e_Commerce.e_Commerce.model.entity.Moderateur" %>
<%@ page import="com.e_Commerce.e_Commerce.model.entity.Commande" %>
<%@ page import="com.e_Commerce.e_Commerce.model.entity.Produit" %> -->
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        header{
            width:100%;
            height: 85px;
            background-color: #333;
        }
        .header-nav{
            padding: 0;
            height: 5px;
            position: relative;
            bottom: 0;
            left: 0;
            z-index: 999;
        }
        .header-nav ul{
            list-style: none;
            display: flex;
            justify-content: space-between;
            margin: 0;
            padding: 0;
            padding-left: 10px;
            padding-right: 10px;
        }
        .header-nav li{
            display: flex;
            justify-content: center;
            align-items: center;
            height:55px;
            text-align: center;
            margin-top : 15px;
        }
        .style {
            display: inline-block;
            font-size: 20px;
            font-weight: bold;
            text-decoration: none;
            transition: font-size 0.2s ease-in-out;
        }
        .style:hover {
            font-size: 12px;
            border: 2px solid #3498db;
            border-radius: 5px;
            padding: 3px 4px;
        }
        .lien {
            color: #fff;
            padding: 10px;
            text-decoration: none;
            font-size: 18px;
            border-radius: 5px;
        }
        .lien:hover {
            background-color: #3498db;
            font-size: 20px;
        }
        .test svg {
            vertical-align: middle;
            margin-right: 10px;
        }
        .search-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80px;
        }
        .search-form {
            display: flex;
            align-items: center;
            position: relative;
        }
        .search-form input[type="text"] {
            font-size: 16px;
            padding: 10px 16px;
            border: none;
            border-radius: 30px;
            width: 300px;
            background-color: #C0C0C0;
            transition: all 0.3s ease-in-out;
        }
        .search-form button[type="submit"] {
            position: absolute;
            top: 50%;
            right: 16px;
            transform: translateY(-50%);
            background-color: transparent;
            border: none;
            font-size: 20px;
            color: #333;
            transition: all 0.3s ease-in-out;
            cursor: pointer;
        }
        .search-form button[type="submit"]:hover {
            color: #555;
        }
        .search-form input[type="text"]:focus {
            width: 400px;
            background-color: #fff;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.2);
            outline: none;
        }
        .search-form input[type="text"]::placeholder {
            color: #555;
        }
        .se-connecter {
            margin-left: auto;
        }
    </style>
    <title> header </title>
</head>
<body>
<header>
    <nav class="header-nav">
        <ul>
            <%--<li class="current"><a href="index.php"><img src="../../img/logo.png" alt="logo_du_site" width="175px"></a></li>--%>
            <li><a href='' style='display: flex; align-items: center; text-decoration: none;'>
                <img src="../logo/logo.png" alt="Logo Azur Shop" width="50px" style='margin-right: 10px;'>
                <span style='color: #fff; font-size: 24px; font-weight: bold;'>Azur Shop</span></a></li>
            <%/*
                Utilisateur header = Controller.getInstanceController().requestGetUtilisateur();
                Commande headerCommande = Controller.getInstanceController().requestGetCommande();
                int nbProduct = 0;
                if (headerCommande != null){
                    List<Produit> headerPanier = headerCommande.getPanier();
                    for(Produit p : headerPanier){
                        nbProduct += p.getStock();
                    }
                }



                if (header == null) {*/
            %>
                <li class='style se-connecter'><a href='' style='display: flex; align-items: center; text-decoration: none;'>
                    <img src="../logo/logIn.png" alt="Logo Log In" width="35px" style='margin-right: 10px;'>
                    <span style='color: #fff; font-size: 16px; font-weight: bold;'>Se Connecter</span></a></li>
            <%/*
                } else {
                    Moderateur headerMod = null;
                    if (header.getTypeDeCompte().equals("Moderateur")){
                        headerMod = UtilisateurDAO.findModByUtilisateur(header);
                    }*/
            %>
                <li class='style'><a href='' style='display: flex; align-items: center; text-decoration: none;'>
                    <img src="../logo/profil.png" alt="Logo Profil" width="35px" style='margin-right: 10px;'>
                    <span style='color: #fff; font-size: 16px; font-weight: bold;'>Mon Compte</span></a></li>
            <%/*
                    if (header.getTypeDeCompte().equals("Client")) {*/

            %>
                    <%--<li class='style'><a href='ServletPanier' class='lien'>Panier : <%= headerCommande.getPrix() %> euros</a></li>--%>
                    <li class='style'><a href='' style='display: flex; align-items: center; text-decoration: none;'>
                    <img src="../logo/panier.png" alt="Logo Panier" width="40px" style='margin-right: 10px;'>
                    <span style='color: #fff; font-size: 16px; font-weight: bold;'> [todo] </span></a></li>
            <%/*
                    } else {*/
            %>
                    <li class='style'><a href='' style='display: flex; align-items: center; text-decoration: none;'>
                        <img src="../logo/moderator.png" alt="Logo Moderator" width="35px" style='margin-right: 10px;'>
                        <span style='color: #fff; font-size: 16px; font-weight: bold;'>Gerer Moderateur</span></a></li>
            <%/*
                        if (header.getTypeDeCompte().equals("Admin") || (headerMod != null && headerMod.getDroits().charAt(0) == '1')){*/
            %>
                    <li class='style'><a href='' style='display: flex; align-items: center; text-decoration: none;'>
                        <img src="../logo/addProduct.png" alt="Logo Add Product" width="35px" style='margin-right: 10px;'>
                        <span style='color: #fff; font-size: 16px; font-weight: bold;'>Ajouter Produit</span></a></li>
            <%/*
                        }
                    }*/
            %>
                    <li class='style'><a href='' style='display: flex; align-items: center; text-decoration: none;'>
                        <img src="../logo/logOut.png" alt="Logo Log Out" width="35px" style='margin-right: 10px;'>
                        <span style='color: #fff; font-size: 16px; font-weight: bold;'>Se Deconnecter</span></a></li>

            <%/*
                }*/
            %>
        </ul>
        <br>
    </nav>
</header>
</body>
</html>