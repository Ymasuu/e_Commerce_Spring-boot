package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import com.e_Commerce.e_Commerce.service.CommandeProduitService;
import com.e_Commerce.e_Commerce.service.CommandeService;
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

/**
 * Controller for handling shopping cart-related operations.
 */
@Controller
public class PanierController {
    //private Client client;
    //private Utilisateur user;
    //private Commande panier;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProduitsService produitsService;

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private CommandeProduitService commandeProduitService;

      /**
     * Displays the shopping cart page.
     */
    @GetMapping("/Panier")
    public String panier(ModelMap model) {
        model.addAttribute("produits", produitsService.getProduct());
        return "panier";
    }
     /**
     * Processes actions related to the shopping cart, such as adding, updating, or clearing items.
     */
    @PostMapping("/Panier")
    public String panierPost(ModelMap model, HttpSession session,
         @RequestParam String action, @RequestParam (required = false) Integer produitId, @RequestParam (required = false) Integer produitQuantite) {
        Commande commande = (Commande) session.getAttribute("panier");
        Client client = (Client) session.getAttribute("client");
        if (action.equals("vider")){
            //Client client = (Client) session.getAttribute("client");
            Commande panierVide = new Commande(client.getIdClient(), 0);
            session.setAttribute("panier", panierVide);
        }else {
            Commande newPanier = (Commande) session.getAttribute("panier");
            if (action.equals("supprimer")){
                // Remove the selected product from the cart
                for (Produit p : newPanier.getPanier()){
                    if (p.getIdProduit() == produitId){
                        newPanier.setPrix(newPanier.getPrix() - (p.getPrix() * p.getStock()));
                        newPanier.getPanier().remove(p);
                        newPanier.majNbrProduit();
                        session.setAttribute("panier", newPanier);
                        break;
                    }
                }
            }else if(action.equals("modifier")){
                // Update the quantity of the selected product in the cart
                for (Produit p : newPanier.getPanier()) {
                    if (p.getIdProduit() == produitId) {
                        newPanier.setPrix(newPanier.getPrix() - (p.getPrix() * p.getStock()));
                        p.setStock(produitQuantite);
                        newPanier.setPrix(newPanier.getPrix() + (p.getPrix() * p.getStock()));
                        newPanier.majNbrProduit();
                        session.setAttribute("panier", newPanier);
                        break;
                    }
                }
            }else{ // action.equals("payer")
            // Check if the client has sufficient funds to pay for the cart
                if (commande.getPrix() <= client.getCompteBancaireSolde()) { // Process the payment
                    System.out.println("test panierController");
/*                    commandeService.saveCommande(commande);
                    session.setAttribute("panier",new Commande(client.getIdClient(), 0));*/
                    return "redirect:/Confirmer_Paiement";
                }else {
                    model.addAttribute("errorMessage", "Desolé mais vous devez ajouter de l'argent sur votre solde pour procédé au paiement");
                    return "panier";
                }

            }
        }
        return "redirect:/Panier";
    }
     /**
     * Displays the payment confirmation page.
     */
    @GetMapping("/Confirmer_Paiement")
    public String confirmerPaiement(ModelMap model) {
        return "confirmerPaiement";
    }
     /**
     * Processes the submission of the payment confirmation form.
     */

    @PostMapping("/Confirmer_Paiement")
    public String confirmerPaiementPost(ModelMap model, HttpSession session, @RequestParam String numeroCarte){
        // Process payment confirmation
        System.out.println("test confirmer paiement");
        Client client = (Client) session.getAttribute("client");
        if (numeroCarte.equals(client.getCompteBancaireNum())){
            Commande commande = (Commande) session.getAttribute("panier");
            // TODO: Add the 'Commande_Produit' tables for each product in the cart.
            commande.setStatus("Payé");
            commande = commandeService.saveCommande(commande);
            for (Produit p : commande.getPanier()){
                CommandeProduit commandeProduit = new CommandeProduit(commande.getIdCommande(), p.getIdProduit(), p.getStock());
                commandeProduitService.saveCommandeProduit(commandeProduit);
            }
            session.setAttribute("panier",new Commande(client.getIdClient(), 0));
            client.setCompteBancaireSolde(client.getCompteBancaireSolde() - commande.getPrix());
            client.setPoints((int) (commande.getPrix() / 10));
            utilisateurService.saveClient(client);
            session.setAttribute("client", client);
            return "redirect:/Produits";
        }
        model.addAttribute("errorMessage", "Le numero de carte est incorrect");
        return "confirmerPaiement";

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
