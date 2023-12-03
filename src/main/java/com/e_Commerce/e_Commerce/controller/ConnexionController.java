package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import com.e_Commerce.e_Commerce.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class ConnexionController {
    private Client client;
    private Utilisateur user;
    private Moderateur moderateur;
    private Commande panier;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/Connexion")
    public String connexion(ModelMap model) { return "connexion";}

    @PostMapping("/Connexion")
    public String processLogin(@RequestParam String email, @RequestParam String motDePasse, ModelMap model, HttpSession session) {
        Utilisateur user = utilisateurService.verifierUtilisateur(email, motDePasse);
        if (user != null) {
            //On passe au controlleur l'utilisateur
            this.user = user;
            // envoyer 'user' dans la session
            session.setAttribute("user", user);

            // on fait la même chose pour l'entité moderateur
            this.moderateur = utilisateurService.getModerateurByIdUtilisateur(user.getIdUtilisateur());
            if (moderateur != null){
                System.out.println("id modo : " + moderateur.getIdModerateur());
                session.setAttribute("moderateur", moderateur);
            }

            // on fait la même chose pour l'entité client
            this.client = utilisateurService.getClientByIdUtilisateur(user.getIdUtilisateur());
            if (client != null){
                System.out.println("id client : " + client.getIdClient());
                session.setAttribute("client", client);

                // Creation du panier dans la session et pas dans la bdd
                this.panier = new Commande(client.getIdClient(), 0);
                session.setAttribute("panier", panier);

            }
            return "redirect:/Produits";
        } else {
            // Échec de la connexion, renvoyez l'utilisateur à la page de connexion avec un message d'erreur
            model.addAttribute("errorMessage", "Identifiants incorrects");
            return "connexion";
        }
    }

    @GetMapping("/Deconnexion")
    public String Deconnexion(ModelMap model, HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("moderateur");
        session.removeAttribute("client");
        session.removeAttribute("panier");
        this.user = null;
        this.moderateur = null;
        this.client = null;
        this.panier = null;
        return "redirect:/Produits";
    }
}
