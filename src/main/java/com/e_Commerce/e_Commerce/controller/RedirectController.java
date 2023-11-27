package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import com.e_Commerce.e_Commerce.service.ProduitsService;
import com.e_Commerce.e_Commerce.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;


@Controller
public class RedirectController {
    //Attributs des diverses entités
    private Client client;
    private Utilisateur user;
    private Produit produit;
    private Moderateur moderateur;

    private Commande commande;


    @Autowired
    private ProduitsService produitsService;
    @Autowired
    private UtilisateurService utilisateurService;


    @GetMapping("/")
    public String index(ModelMap model) { return "index";}
    @GetMapping("/Ajouter_Moderateur")
    public String ajouterModerateur(ModelMap model) { return "pageAjouterModerateur";}
    @GetMapping("/Ajouter_Moyen_De_Paiement")
    public String ajouterMoyenPaiement(ModelMap model) { return "pageAjouterMoyenPaiement";}
    @GetMapping("/Ajouter_Produit")
    public String ajouterProduit(ModelMap model) { return "pageAjouterProduit";}

    ///////AJOUTER SOLDE//////////////////////////////////////////////////
    @GetMapping("/AjouterSolde")
    public String ajouterSolde(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        return "pageAjouterSolde";}

    @PostMapping("/AjouterSolde")
    public String ajouterSoldePost(ModelMap model, HttpSession session,@RequestParam("numeroCarte") String numeroCarte,
                                   @RequestParam("montant") Double montant) {

        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        if (client.getCompteBancaireNum() == null) {
            String errorMessage = "Vous devez ajouter votre carte bleue sur votre profil avant de pouvoir ajouter de " +
                    "l'argent sur votre compte";
            model.addAttribute("errorMessage", errorMessage);
            return "pageAjouterSolde";
        }

        if(client.getCompteBancaireNum().equals("0000 0000 0000 0000") || numeroCarte.isEmpty()){
            String errorMessage = "Vous devez ajouter votre carte bleue sur votre profil avant de pouvoir ajouter de " +
                    "l'argent sur votre compte";
            model.addAttribute("errorMessage", errorMessage);
            return "pageAjouterSolde";
        }
        else if(!numeroCarte.equals(client.getCompteBancaireNum())){
            String errorMessage = "Numéro de carte bleue incorrect";
            return "pageAjouterSolde";
        }
        else {
            BigDecimal soldeAjoute = BigDecimal.valueOf(montant);
            BigDecimal soldeActuel = client.getCompteBancaireSolde();
            BigDecimal soldeApresModif = soldeActuel.add(soldeAjoute);
            client.setCompteBancaireSolde(soldeApresModif);
            //TODO update la bdd avec le nouveau solde pour le client
            utilisateurService.saveClient(client);
            return "pageProfil";
        }
    }

    /////////////////////////////////////////////////////////////////////
    @GetMapping("/Changer_Mot_De_Passe")
    public String changerMotDePasse(ModelMap model) { return "pageChangerMotDePasse";}
    @GetMapping("/Confirmer_Paiement")
    public String confirmerPaiement(ModelMap model) { return "pageConfirmerPaiement";}

    @GetMapping("/Convertir_Points")
    public String convertirPoints(ModelMap model) { return "pageConvertPoints";}
    /*@GetMapping("/Inscription")
    public String inscription(ModelMap model) { return "pageInscription";}*/
    @GetMapping("/Liste_Moderateurs")
    public String listeModerateurs(ModelMap model) { return "pageListeModerateurs";}
    @GetMapping("/Modifier_Droits_Moderateur")
    public String modifierDroitsModerateur(ModelMap model) { return "pageModifierDroitsModerateur";}
    @GetMapping("/Modifier_Produit")
    public String modifierProduit(ModelMap model) { return "pageModifierProduit";}
    @GetMapping("/Modifier_Profil")
    public String modifierProfil(ModelMap model) { return "pageModifierProfil";}
    @GetMapping("/Panier")
    public String panier(ModelMap model) { return "pagePanier";}

    @GetMapping("/Profil")
    public String utilisateur(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
        Client client = (Client) session.getAttribute("client");

        model.addAttribute("user", user);
        model.addAttribute("moderateur", moderateur);
        model.addAttribute("client", client);
        return "pageProfil";
    }

    @GetMapping("/Supprimer_Moderateur")
    public String supprimerModerateur(ModelMap model) { return "pageSupprimerModerateur";}

}


