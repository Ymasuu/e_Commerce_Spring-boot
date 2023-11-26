<%@ page import="entity.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="entity.Moderateur" %>
<%@ page import="ecommerce.ecommerce.model.DAO.UtilisateurDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Modérateurs</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        /* Styles CSS pour la mise en page */
        body {
            font-family: Arial, sans-serif;
            margin : 0;
        }
        .moderators {
            width: 80%;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px 12px;
            text-align: left;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        /* Styles pour les boutons */
        .button-container {
            display: flex; /* Utilisation de flexbox */
            justify-content: center; /* Alignement horizontal au centre */
            margin-top: 20px;
        }
        .button-container button {
            margin: 10px;
            padding: 10px 20px; /* Taille réduite */
            background-color: #4CAF50; /* Couleur verte */
            color: #fff;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.5s; /* Animation de transition de la couleur de fond */
        }

        .button-container button span {
            display: inline-block;
            position: relative;
            transition: 0.5s;
        }

        .button-container button:hover {
            background-color: #45a049; /* Couleur verte plus foncée au survol */
        }

        .tooltip-container th {
            position: relative;
            font-size: 12px; /* Réduire la police d'écriture selon vos besoins */
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
            font-weight: normal;
        }
        h1 {
            text-align : center;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="header">
<%--    <%@ include file="header.html" %>--%>
</div>
<div class="moderators">
    <h1>Liste des Modérateurs</h1>
    <table>
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Mail</th>
            <th id="tooltip_header"><div class="tooltip-container">Droits (voir détail) <div class="tooltip" id="tooltip_header_content"></div></th>
        </tr>
        <%
            Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
            List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("listModerateur");
            for (Utilisateur utilisateur : utilisateurs) {
                Moderateur moderateur = UtilisateurDAO.findModByUtilisateur(utilisateur);
        %>
        <tr>
            <td><%= utilisateur.getPrenom() %></td>
            <td><%= utilisateur.getNom() %></td>
            <td><%= utilisateur.getMail() %></td>
            <td><%= moderateur.getDroits()%></td>
        </tr>
        <%
            }
        %>
    </table>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var tooltipTh = document.getElementById('tooltip_header');

            tooltipTh.addEventListener('mouseover', function () {
                var tooltipBox = document.getElementById('tooltip_header_content');
                tooltipBox.innerHTML = '1 er droit : Ajouter un produit<br>2 ème droit : Supprimer un produit<br> ' +
                    '3 ème droit : Ajouter un Modérateur<br><br>1 : Possède le droit <br> 0 : Ne possède pas le droit';
                tooltipBox.style.display = 'block';
            });

            tooltipTh.addEventListener('mouseout', function () {
                var tooltipBox = document.getElementById('tooltip_header_content');
                tooltipBox.style.display = 'none';
            });
        });
    </script>

    <div class="button-container">
        <%
            if (u.getTypeDeCompte().equals("Admin")) {
        %>
                <form action="ServletAddModerateur" method ="get">
                    <button type="submit">Ajouter Modérateur</button>
                </form>
        <%
            }
            if (u.getTypeDeCompte().equals("Moderateur")) {
                Moderateur m = UtilisateurDAO.findModByUtilisateur(u);
                if (m.getDroits().charAt(2) == '1'){
        %>
                    <form action="ServletAddModerateur" method ="get">
                        <button type="submit"><span>Ajouter Modérateur</span></button>
                    </form>
        <%
                }
            }
        %>
            <%
                if (u.getTypeDeCompte().equals("Admin")) {
            %>
                    <form action="ServletDeleteModerateur" method ="get">
                        <button type="submit"><span>Supprimer Modérateur</span></button>
                    </form>
                    <form action="ServletModifyRights" method ="get">
                        <button type="submit"><span>Modifier les droits</span></button>
                    </form>
            <%
                }
            %>
    </div>
</div>
<!-- Ajoutez ce code JavaScript à votre page -->
<script>
    // Fonction pour afficher la boîte de dialogue
    function afficherBoiteDialogue(droits) {
        // Définissez ici la logique pour afficher la boîte de dialogue
        alert("Premier chiffre : Ajouter un produit\nDeuxième chiffre : Supprimer un produit\nTroisième chiffre : Supprimer un produit");
    }

    // Attachez un gestionnaire d'événements à toutes les cellules des droits
    document.querySelectorAll('.droits-cell').forEach(function(cell) {
        cell.addEventListener('mouseover', function() {
            // Récupérez les droits de l'attribut de données
            var droits = cell.getAttribute('data-droits');
            // Affichez la boîte de dialogue avec les droits correspondants
            afficherBoiteDialogue(droits);
        });
    });
</script>

</body>
</html>

