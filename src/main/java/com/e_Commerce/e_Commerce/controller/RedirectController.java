package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.e_Commerce.e_Commerce.service.ProduitsService;


@Controller
public class RedirectController {
    @Autowired
    private ProduitsService produitsService;

    @GetMapping("/")
    public String index(ModelMap model) { return "index";}
    @GetMapping("/Ajouter_Moderateur")
    public String ajouterModerateur(ModelMap model) { return "pageAjouterModerateur";}
    @GetMapping("/Ajouter_Moyen_De_Paiement")
    public String ajouterMoyenPaiement(ModelMap model) { return "pageAjouterMoyenPaiement";}
    @GetMapping("/Ajouter_Produit")
    public String ajouterProduit(ModelMap model) { return "pageAjouterProduit";}
    @GetMapping("/Ajouter_Solde")
    public String ajouterSolde(ModelMap model) { return "pageAjouterSolde";}
    @GetMapping("/Changer_Mot_De_Passe")
    public String changerMotDePasse(ModelMap model) { return "pageChangerMotDePasse";}
    @GetMapping("/Confirmer_Paiement")
    public String confirmerPaiement(ModelMap model) { return "pageConfirmerPaiement";}
    @GetMapping("/Connexion")
    public String connexion(ModelMap model) { return "pageConnexion";}
    @GetMapping("/Convertir_Points")
    public String convertirPoints(ModelMap model) { return "pageConvertPoints";}
    @GetMapping("/Inscription")
    public String inscription(ModelMap model) { return "pageInscription";}
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
    @GetMapping("/Produit")
    public String produit(ModelMap model) { return "pageProduit";}
    @GetMapping("/Produits")
    public String produits(ModelMap model) {
        Iterable<Produit> products = produitsService.getProduct();
        model.addAttribute("products", products);
        return "pageProduits";
    }
    @GetMapping("/Profil")
    public String profil(ModelMap model) { return "pageProfil";}
    @GetMapping("/Supprimer_Moderateur")
    public String supprimerModerateur(ModelMap model) { return "pageSupprimerModerateur";}
}
