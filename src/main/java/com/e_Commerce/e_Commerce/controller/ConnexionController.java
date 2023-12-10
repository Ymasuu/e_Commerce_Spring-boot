package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.Client;
import com.e_Commerce.e_Commerce.model.entity.Commande;
import com.e_Commerce.e_Commerce.model.entity.Moderateur;
import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import com.e_Commerce.e_Commerce.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling user login.
 */

@Controller
public class ConnexionController {
    private Client client;    // Client entity
    private Utilisateur user;  // User entity
    private Moderateur moderateur; // Moderator entity
    private Commande panier;  // Cart entity

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Displays the login page.
     */
    @GetMapping("/Connexion")
    public String connexion(ModelMap model) {
        return "connexion";
    }

    @PostMapping("/Connexion")
    public String processLogin(@RequestParam String email, @RequestParam String motDePasse, ModelMap model, HttpSession session) {
        Utilisateur user = utilisateurService.verifierUtilisateur(email, motDePasse);
        if (user != null) {
            // We pass the user to the controller
            this.user = user;
            // Send 'user' to the session
            session.setAttribute("user", user);

            // We do the same thing for the 'moderator' entity
            this.moderateur = utilisateurService.getModerateurByIdUtilisateur(user.getIdUtilisateur());
            if (moderateur != null) {
                System.out.println("id modo : " + moderateur.getIdModerateur());
                session.setAttribute("moderateur", moderateur);
            }

            // We do the same thing for the 'client' entity
            this.client = utilisateurService.getClientByIdUtilisateur(user.getIdUtilisateur());
            if (client != null) {
                System.out.println("id client : " + client.getIdClient());
                session.setAttribute("client", client);

                // Creation of the shopping cart in the session, not in the database
                this.panier = new Commande(client.getIdClient(), 0);
                session.setAttribute("panier", panier);

            }
            return "redirect:/Produits";
        } else {
            // Login failed, redirect the user to the login page with an error message
            model.addAttribute("errorMessage", "Identifiants incorrects");
            return "connexion";
        }
    }

    /**
     *
     */

    @GetMapping("/Deconnexion")
    public String Deconnexion(ModelMap model, HttpSession session) {
        // Removes session attributes related to the user, moderator, client, and cart
        session.removeAttribute("user");
        session.removeAttribute("moderateur");
        session.removeAttribute("client");
        session.removeAttribute("panier");
        // Resets the corresponding member variables in the controller
        this.user = null;
        this.moderateur = null;
        this.client = null;
        this.panier = null;
        return "redirect:/Produits"; // Redirects to the products page after logout
    }
}
