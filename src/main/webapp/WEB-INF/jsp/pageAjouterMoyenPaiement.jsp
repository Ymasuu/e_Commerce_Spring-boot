<%@ page import="entity.Utilisateur" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter Moyen de Paiement</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        h1 {
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input {
            padding: 8px;
            margin-bottom: 10px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function validerCarteBancaire() {
        var carteBancaire = document.getElementById("carteBancaire").value;
        var regex = /^[0-9]{16}$/;

        // Retirer les espaces de la saisie de l'utilisateur
        var carteBancaireSansEspaces = carteBancaire.replace(/ /g, '');

        if (!regex.test(carteBancaireSansEspaces)) {
            alert("Veuillez entrer un numéro de carte bancaire valide (16 chiffres).");
            return false;
        }

        // Formater le numéro de carte bancaire avec des espaces tous les 4 caractères
        var carteBancaireFormatee = carteBancaireSansEspaces.replace(/(.{4})/g, '$1 ');

        // Mettre à jour la valeur dans le champ
        document.getElementById("carteBancaire").value = carteBancaireFormatee.trim();

        return true;
    }
    </script>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<div class="container">
    <h1>Ajouter Moyen de Paiement</h1>
    <form action="ServletAjouterMoyenPaiement" method="post" onsubmit="return validerCarteBancaire();">
        <label for="carteBancaire">Code de la Carte Bancaire :</label>
        <input type="text" id="carteBancaire" name="carteBancaire" required><br>

        <input type="submit" value="Valider">
    </form>
</div>
</body>
</html>