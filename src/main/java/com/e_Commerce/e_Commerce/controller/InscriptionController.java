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
        model.addAttribute("utilisateur", new Utilisateur());
        return "pageInscription";
    }

    @PostMapping("/Inscription")
    public String soumettreFormulaireInscription(@ModelAttribute Utilisateur utilisateur, Model model, HttpSession session) {
        // Valider les données du formulaire
        List<String> erreurs = utilisateurService.validerUtilisateur(utilisateur);

        if (erreurs.isEmpty()) {
            // Aucune erreur, enregistrer l'utilisateur et le connecter
            utilisateurService.enregistrerUtilisateur(utilisateur);
            session.setAttribute("user", utilisateur);

            // Rediriger vers la page des produits
            return "redirect:/Produits";
        } else {
            // Il y a des erreurs, les afficher dans le modèle
            model.addAttribute("erreurs", erreurs);
            return "pageInscription";
        }
    }
}