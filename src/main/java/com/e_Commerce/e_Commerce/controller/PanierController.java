package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import com.e_Commerce.e_Commerce.service.ProduitsService;
import com.e_Commerce.e_Commerce.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class PanierController {
    private Client client;
    private Utilisateur user;
    private Commande panier;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProduitsService produitsService;


    @GetMapping("/Panier")
    public String panier(ModelMap model) {
        model.addAttribute("produits", produitsService.getProduct());
        return "pagePanier";
    }

    @PostMapping("/Panier")
    public String panierPost(ModelMap model, HttpSession session,
         @RequestParam String action, @RequestParam (required = false) Integer produitId, @RequestParam (required = false) Integer produitQuantite) {
        if (action.equals("vider")){
            Client client = (Client) session.getAttribute("client");
            Commande panierVide = new Commande(client.getIdClient(), 0);
            session.setAttribute("panier", panierVide);
        }else {
            Commande newPanier = (Commande) session.getAttribute("panier");
            if (action.equals("supprimer")){
                for (Produit p : newPanier.getPanier()){
                    if (p.getIdProduit() == produitId){
                        newPanier.setPrix(newPanier.getPrix() - (p.getPrix() * p.getStock()));
                        newPanier.getPanier().remove(p);
                        newPanier.majNbrProduit();
                        session.setAttribute("panier", newPanier);
                        break;
                    }
                }
            }else { // action.equals("modifier")
                for (Produit p : newPanier.getPanier()){
                    if (p.getIdProduit() == produitId){
                        newPanier.setPrix(newPanier.getPrix() - (p.getPrix() * p.getStock()));
                        p.setStock(produitQuantite);
                        newPanier.setPrix(newPanier.getPrix() + (p.getPrix() * p.getStock()));
                        session.setAttribute("panier", newPanier);
                        break;
                    }
                }
            }
        }
        return "redirect:/Panier";
    }

/*    @ModelAttribute("stockProduit")
    public Produit getProduitById(@RequestParam int idProduit) {
        for (Produit produit : produitsService.getProduct()) {
            if (produit.getIdProduit() == idProduit) {
                return produit;
            }
        }
        return null;
    }*/
}
