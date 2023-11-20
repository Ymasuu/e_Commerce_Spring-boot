<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <%
        Boolean modification = (Boolean) request.getAttribute("modification");
    %>
    <meta charset="UTF-8">
    <title>Modifier droit modérateur</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin : 0;
        }

        h1 {
            color: #333;
        }

        form {
            background-color: #fff;
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            text-align: left;
            margin: 10px 0;
        }

        input[type="email"],
        select {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        p {
            margin-top: 15px;
        }

        .success {
            color: green;
        }
        .tooltip-container label {
            position: relative;
            font-size: 12px; /* Réduisez la police d'écriture selon vos besoins */
        }

        .tooltip-container .tooltip {
            position: absolute;
            display: none;
            background-color: #f9f9f9;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            color: #333;
            z-index: 1;
            font-weight : normal;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Modification des droits</h1>
<form action="ServletModifyRights" method="post">
    <label for="email">Adresse email :</label>
    <select id="email" name="email" required>
        <option value="" selected disabled hidden>Veuillez choisir un modérateur</option>
<%
    List<Utilisateur> listUtilisateur = UtilisateurDAO.getListUtilisateurs();
    for (Utilisateur user : listUtilisateur){
        if (user.getTypeDeCompte().equals("Moderateur")){
%>
        <option value="<%= user.getMail() %>"><%= user.getMail()%></option>
<%
        }
    }
%>
    </select>
    <label for="droits" id="label_droits">Droits (voir détail) :</label>
    <div class="tooltip-container" id="tooltip_droits">
        <div class="tooltip" id="tooltip_droits_content"></div>
    </div>
    <select id="droits" name="droits">
        <option value="000">000</option>
        <option value="001">001</option>
        <option value="010">010</option>
        <option value="100">100</option>
        <option value="110">110</option>
        <option value="101">101</option>
        <option value="011">011</option>
        <option value="111">111</option>
    </select><br><br>

    <input type="submit" value="Modifier">

    <%
        if (modification != null && modification) {
    %>
    <p class="success">Le modérateur a été mis à jour</p>
    <%
        }
    %>
</form>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Fonction pour afficher la boîte de dialogue
        function afficherBoiteDialogue() {
            var tooltipBox = document.getElementById('tooltip_droits_content');
            tooltipBox.innerHTML = '1er droit : Ajouter un produit<br>2ème droit : Supprimer un produit<br>' +
                '3ème droit : Ajouter un Modérateur<br><br>1 : Possède le droit <br>0 : Ne possède pas le droit';
            tooltipBox.style.display = 'block';
        }

        // Attachez un gestionnaire d'événements au label "Droits"
        var labelDroits = document.querySelector('label[for="droits"]');

        labelDroits.addEventListener('mouseover', function () {
            // Affichez la boîte de dialogue lorsque la souris survole le label "Droits"
            afficherBoiteDialogue();
        });

        labelDroits.addEventListener('mouseout', function () {
            // Cachez la boîte de dialogue lorsque la souris quitte le label "Droits"
            var tooltipBox = document.getElementById('tooltip_droits_content');
            tooltipBox.style.display = 'none';
        });
    });
</script>

</body>
</html>
