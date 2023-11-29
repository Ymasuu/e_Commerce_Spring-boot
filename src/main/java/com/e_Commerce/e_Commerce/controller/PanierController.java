package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import com.e_Commerce.e_Commerce.service.ProduitsService;
import com.e_Commerce.e_Commerce.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String panier(ModelMap model) { return "pagePanier.html";}

    @PostMapping("/Panier")
    public String panierPost(ModelMap model, HttpSession session,
         @RequestParam String action, @RequestParam (required = false) Integer produitId) {
        if (action.equals("vider")){
            Client client = (Client) session.getAttribute("client");
            Commande panierVide = new Commande(client.getIdClient(), 0);
            session.setAttribute("panier", panierVide);
        }
        if (action.equals("supprimer")){
            Commande newPanier = (Commande) session.getAttribute("panier");
            for (Produit p : newPanier.getPanier()){
                if (p.getIdProduit() == produitId){
                    newPanier.setPrix(newPanier.getPrix() - (p.getPrix() * p.getStock()));
                    newPanier.getPanier().remove(p);
                    newPanier.majNbrProduit();
                    session.setAttribute("panier", newPanier);
                    break;
                }
            }
        }
        return "redirect:/Panier";
    }
}
