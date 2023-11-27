package com.e_Commerce.e_Commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.e_Commerce.e_Commerce.model.entity.Client;
import com.e_Commerce.e_Commerce.model.entity.Moderateur;
import com.e_Commerce.e_Commerce.model.entity.Produit;
import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import com.e_Commerce.e_Commerce.service.UtilisateurService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.e_Commerce.e_Commerce.service.ProduitsService;

import java.util.List;
import java.util.Optional;

@Controller
public class InscriptionController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/Inscription")
    public String afficherFormulaireInscription(Model model) {
        return "pageInscription";
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

            // Rediriger vers la page des produits
            return "redirect:/Produits";
        } else {
            // Il y a des erreurs, les afficher dans le modèle
            model.addAttribute("erreurs", "<style> font-color:red  </style>Cet utilisateur existe dejà");
            return "pageInscription";
        }
    }
}