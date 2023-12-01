package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @GetMapping("/Inscription")
    public String afficherFormulaireInscription(Model model) {
        return "inscription";
    }

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
            // Il y a des erreurs, les afficher dans le modèle
            String errorMessage = "Cet utilisateur existe déjà";
            model.addAttribute("errorMessage", errorMessage);
            return "inscription";
        }
    }

    @GetMapping("/ajouterModerateur")
    public String ajouterModerateur(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
        return "ajouterModerateur";}

    @PostMapping("/ajouterModerateur")
    public String ajouterModerateur(ModelMap model, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
                                    @RequestParam("motDePasse") String motDePasse, @RequestParam("email") String email) {
        if (utilisateurService.verifierUtilisateur(email) == null) {
            Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse, "Moderateur");
            Utilisateur nouvelUtilisateur = utilisateurService.saveUser(utilisateur);

            Moderateur moderateur = new Moderateur();
            moderateur.setIdModerateur(nouvelUtilisateur.getIdUtilisateur());
            Moderateur nouveauModerateur = utilisateurService.saveModerateur(moderateur);
            return "redirect:/Produits";

        } else {
            String errorMessage = "Cette adresse mail est déjà utilisée";
            model.addAttribute("errorMessage", errorMessage);
            return "ajouterModerateur";
        }
    }
}