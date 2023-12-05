package com.e_Commerce.e_Commerce.controller;


import com.e_Commerce.e_Commerce.model.entity.*;
import com.e_Commerce.e_Commerce.service.ProduitsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling product-related operations.
 */
@Controller
public class ProduitsController {
    private Client client;  // Client entity
    private Utilisateur user; // User entity
    private Produit produit; // Product entity
    private Moderateur moderateur; // Moderator entity

    @Autowired
    private ProduitsService produitsService;
    /**
     * Displays the product details page.
     */
    @GetMapping("/Produit/{id}")
    public String produit(ModelMap model, @PathVariable Integer id, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user != null) {
            // Additional logic for handling user and moderator information
            System.out.println("Utilisateur connecté : " + user.getMail());
            model.addAttribute("user", user);
            Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
            if (moderateur != null) {
                model.addAttribute("moderateur", moderateur);
                System.out.println("Produit test modo droits : " + moderateur.getDroits());
            }
        }
        Produit product = produitsService.getProductById(id);
        model.addAttribute("produit", product);
        return "produit";
    }
    /**
     * Processes actions related to products, such as adding or removing items from the cart.
     */

    @PostMapping("/Produit/{id}")
    public String ajouterProduit(ModelMap model, HttpSession session,
                                 @RequestParam int produitId, @RequestParam(required = false) Integer produitQuantite, @RequestParam String action) {
        if (action.equals("ajouter")) {
            Produit produit = produitsService.getProductById(produitId);
            Commande panier = (Commande) session.getAttribute("panier");
            boolean produitExisteDeja = false;
            for (Produit p : panier.getPanier()) {
                if (p.getIdProduit() == produitId) { // le produit existe deja dans le panier
                    produitExisteDeja = true;
                    if (p.getStock() + produitQuantite <= produit.getStock()) { // le produit est disponible dans le bon nombre d'exemplaire
                        panier.setPrix(panier.getPrix() + (p.getPrix() * produitQuantite));
                        panier.ajouterProduit(p, produitQuantite);
                    }
                    break;
                }
            }
            if (!produitExisteDeja) {
                panier.setPrix(panier.getPrix() + (produit.getPrix() * produitQuantite));
                panier.ajouterProduit(produit, produitQuantite);
            }
            session.setAttribute("panier", panier);
            System.out.println("Prix panier : " + panier.getPrix());
        }
        if (action.equals("supprimer")) {
            Produit produit = produitsService.getProductById(produitId);
            produitsService.deleteProduct(produit);
        }
        return "redirect:/Produits";
    }
    /**
     * Displays the products page.
     */

    @GetMapping("/Produits")
    public String produits(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        // Additional logic for handling user information
        System.out.println("UTILISATEUR EXISTE : " + user);
        if (user != null) {
            System.out.println("Utilisateur connecté : " + user.getMail());
            model.addAttribute("user", user);
        }
        Iterable<Produit> products = produitsService.getProduct();
        model.addAttribute("produits", products);
        return "produits";
    }
        /**
     * Displays the page for adding a new product.
     */
    @GetMapping("/ajouterProduit")
    public String afficherAjouterProduit(ModelMap model, HttpSession session) {
        return "ajouterProduit";
    }
     /**
     * Processes the form submission for adding a new product.
     */

    @PostMapping("/ajouterProduit")
    public String ajouterProduit(ModelMap model, @RequestParam("nom") String nom, @RequestParam("description") String description,
                                 @RequestParam("prix") float prix, @RequestParam("stock") int stock) {
        Produit produit = new Produit(nom, prix, description, stock);
        Produit nouveauProduit = produitsService.saveProduct(produit);
        return "redirect:/Produits";
    }
      /**
     * Displays the page for modifying an existing product.
     */

    @GetMapping("/Modifier_Produit/{id}")
    public String modifierProduit(ModelMap model, HttpSession session, @PathVariable Integer id) {
        Produit product = produitsService.getProductById(id);
        model.addAttribute("produit", product);
        return "modifierProduit";
    }
    /**
     * Processes the form submission for modifying an existing product.
     */
    @PostMapping("/Modifier_Produit/{id}")
    public String modifierProduitPost(ModelMap model,
            @RequestParam String nom,
            @RequestParam String description,
            @RequestParam Float prix,
            @RequestParam int stock
            /*@RequestParam("image") MultipartFile image,
            Model model) {*/){
        Produit produit = produitsService.getProductByNom(nom);
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrix(prix);
        produit.setStock(stock);
        produitsService.saveProduct(produit);
        return "redirect:/Produits";
    }
}
