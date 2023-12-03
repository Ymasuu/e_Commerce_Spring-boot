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
import services.SendService;

import java.io.IOException;
import java.util.HashMap;

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

            // L'utilisateur à été créer, on envoie un mail de confirmation
            Courier.init("pk_prod_RW21FPAESN4DD3N8YK0RWH3YEC0E");

            SendEnhancedRequestBody request = new SendEnhancedRequestBody();
            SendRequestMessage message = new SendRequestMessage();

            HashMap<String, String> to = new HashMap<String, String>();
            to.put("email", email);
            message.setTo(to);
            message.setTemplate("ZHASTSX98ZM89ZGHHKTNQ8RN4P3V");
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("user", prenom+" "+nom);
            message.setData(data);
            request.setMessage(message);
            try {
                SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
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