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

import java.util.Optional;


@Controller
public class ProduitsController {
    private Client client;
    private Utilisateur user;
    private Produit produit;
    private Moderateur moderateur;

    @Autowired
    private ProduitsService produitsService;

    @GetMapping("/Produit/{id}")
    public String produit(ModelMap model, @PathVariable Integer id,HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user != null){
            System.out.println("Utilisateur connecté : " + user.getMail());
            model.addAttribute("user", user);
            Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
            if (moderateur != null){
                model.addAttribute("moderateur", moderateur);
                System.out.println("Produit test modo droits : " + moderateur.getDroits());
            }
        }
        Produit product = produitsService.getProductById(id);
        model.addAttribute("produit", product);
        return "pageProduit";
    }

    @PostMapping("/Produit/{id}")
    public String ajouterProduit(ModelMap model, HttpSession session,
         @RequestParam int produitId, @RequestParam int produitQuantite){
        return "redirect:/Produits";
    }




    @GetMapping("/Produits")
    public String produits(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        System.out.println("UTILISATAUR EXISTE? : " + user);
        if (user != null){
            System.out.println("Utilisateur connecté : " + user.getMail());
            model.addAttribute("user", user);
        }
        Iterable<Produit> products = produitsService.getProduct();
        model.addAttribute("produits", products);
        return "pageProduits";
    }



}
