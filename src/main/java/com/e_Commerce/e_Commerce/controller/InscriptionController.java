package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import services.Courier;


import com.e_Commerce.e_Commerce.service.UtilisateurService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class InscriptionController {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Displays the user registration form.
     */

    @GetMapping("/Inscription")
    public String afficherFormulaireInscription(Model model) {
        return "inscription";
    }
     /**
     * Processes the user registration form submission.
     * If the user doesn't already exist, registers the user, logs them in, and redirects to the products page.
     * If the user already exists, displays an error message.
     */

    @PostMapping("/Inscription")
    public String soumettreFormulaireInscription(@RequestParam String email, @RequestParam String nom,
     @RequestParam String prenom, @RequestParam String motDePasse, Model model, HttpSession session) {
        if (utilisateurService.verifierUtilisateur(email) == null) {
            Utilisateur utilisateur = new Utilisateur(prenom, nom, email, motDePasse, "Client");
            // Aucune erreur, enregistrer l'utilisateur et le connecter
            Utilisateur nouvelUtilisateur = utilisateurService.saveUser(utilisateur);
            session.setAttribute("user", nouvelUtilisateur);

            Client client = new Client(nouvelUtilisateur.getIdUtilisateur());
            Client nouveauClient = utilisateurService.saveClient(client);
            session.setAttribute("client", nouveauClient);

            Commande panier = new Commande(client.getIdClient(), 0);
            session.setAttribute("panier", panier);

            // Rediriger vers la page des produits
            return "redirect:/Produits";
        } else {
            // Display errors in the model if the user already exists
            String errorMessage = "Cet utilisateur existe déjà";
            model.addAttribute("errorMessage", errorMessage);
            return "inscription";
        }
    }
     /**
     * Displays the page for adding a moderator.
     */

    @GetMapping("/ajouterModerateur")
    public String ajouterModerateur(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
        return "ajouterModerateur";}

         /**
     * Processes the form submission for adding a moderator.
     * If the email is not already in use, adds a new moderator and redirects to the products page.
     * If the email is already in use, displays an error message.
     */

    @PostMapping("/ajouterModerateur")
    public String ajouterModerateur(ModelMap model, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
                                    @RequestParam("motDePasse") String motDePasse, @RequestParam("email") String email) {
        if (utilisateurService.verifierUtilisateur(email) == null) {
            Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse, "Moderateur");
            Utilisateur nouvelUtilisateur = utilisateurService.saveUser(utilisateur);

            Moderateur moderateur = new Moderateur();
            moderateur.setIdModerateur(nouvelUtilisateur.getIdUtilisateur());
            Moderateur nouveauModerateur = utilisateurService.saveModerateur(moderateur);

            // Redirect to the products page
            return "redirect:/Produits";

        } else {
            // Display errors in the model if the email is already in use
            String errorMessage = "Cette adresse mail est déjà utilisée";
            model.addAttribute("errorMessage", errorMessage);
            return "ajouterModerateur";
        }
    }
}