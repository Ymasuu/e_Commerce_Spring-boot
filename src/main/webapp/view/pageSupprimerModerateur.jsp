<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <link rel="icon" href="../logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="../logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
        }

        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
        }

        h1 {
            color: #333;
        }

        form {
            background-color: #fff;
            display: inline-block;
            text-align: left;
            margin: 20px auto;
            padding: 30px;
            width: 60%;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        select,
        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 12px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 14px 20px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        p.success {
            margin-top: 20px;
            color: #4caf50;
            font-size: 18px;
            font-weight: bold;
        }
    </style>
    <script>
        function validerMail(champ) {
            var value = champ.value;
            // Utiliser une expression régulière pour vérifier le format de l'adresse e-mail
            var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (!emailPattern.test(value)) {
                alert("Le champ " + champ.name + " ne contient pas une adresse e-mail valide.");
                // Réinitialiser la valeur du champ à une chaîne vide
                champ.value = '';
            }
        }
    </script>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Suppression moderateurs</h1>
<form action="ServletDeleteModerateur" method="post" class="supp">
    <label for="email" style="text-align: center">Veuillez renseigner l'Adresse email du moderateur que vous voulez supprimer :</label>
    <select id="email" name="email" required style="text-align: center">
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
    </select><br><br>
    <input type="submit" value="Supprimer" style="justify-content: center">
</form>
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
