package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.*;
import com.e_Commerce.e_Commerce.service.CommandeProduitService;
import com.e_Commerce.e_Commerce.service.CommandeService;
import com.e_Commerce.e_Commerce.service.ProduitsService;
import com.e_Commerce.e_Commerce.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

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


    @GetMapping("/Panier")
    public String panier(ModelMap model) {
        model.addAttribute("produits", produitsService.getProduct());
        return "panier";
    }

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
                if (commande.getPrix() <= client.getCompteBancaireSolde()) { // le client a les moyens de payer
                    System.out.println("test panierController");
/*                    commandeService.saveCommande(commande);
                    session.setAttribute("panier",new Commande(client.getIdClient(), 0));*/
                    mailDuPayment(commande, session);
                    return "redirect:/Confirmer_Paiement";
                }else {
                    model.addAttribute("errorMessage", "Desolé mais vous devez ajouter de l'argent sur votre solde pour procédé au paiement");
                    return "panier";
                }

            }
        }
        return "redirect:/Panier";
    }

    @GetMapping("/Confirmer_Paiement")
    public String confirmerPaiement(ModelMap model) {
        return "confirmerPaiement";
    }

    @PostMapping("/Confirmer_Paiement")
    public String confirmerPaiementPost(ModelMap model, HttpSession session, @RequestParam String numeroCarte){
        System.out.println("test confirmer paiement");
        Client client = (Client) session.getAttribute("client");
        if (numeroCarte.equals(client.getCompteBancaireNum())){
            Commande commande = (Commande) session.getAttribute("panier");
            // TODO ajouter les tables Commande_Produit pour chaque produit du panier
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

    public void mailDuPayment(Commande commande, HttpSession session){
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Courier.init("pk_prod_RW21FPAESN4DD3N8YK0RWH3YEC0E");
        String COMPANY_NAME = "Azur Shop";

        // Récupération des informations nécessaires pour construire l'email
        String userName = user.getPrenom() + " " + user.getNom(); // Fonction pour obtenir le nom du client
        // Génération de la date et de l'heure actuelles
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        // Formatage de la date et de l'heure
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String paymentDate = currentDate.format(dateFormatter);
        String paymentTime = currentTime.format(timeFormatter);
        String currency = "EUR"; // Changez en la devise appropriée si nécessaire

        // Préparation du contenu de l'email
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Cher ").append(userName).append(",\n\n");
        emailContent.append("Nous vous confirmons le paiement effectué le ").append(paymentDate);
        emailContent.append(" à ").append(paymentTime).append(". Votre achat comprend les articles suivants :\n\n");

        // Boucle pour ajouter les détails de chaque produit dans l'email
        for (Produit produit : commande.getPanier()){
            String productName = produit.getNom();
            int productQuantity = produit.getStock();
            float productPrice = produit.getPrix();
            float productGroupPrice = productQuantity > 1 ? (productQuantity * productPrice) : productPrice;

            emailContent.append(productName).append(", x").append(productQuantity);
            emailContent.append(" - ").append(productPrice).append(" ").append(currency).append(" l'unité");
            emailContent.append(" - ").append(productGroupPrice).append(" ").append(currency).append(" le groupe\n");
        }
        emailContent.append("\nPrix total : ").append(commande.getPrix()).append(" ").append(currency).append(".\n\n");
        emailContent.append("Merci pour votre achat chez nous.\n\n");
        emailContent.append("Cordialement,\nL'équipe de ").append(COMPANY_NAME);
        // Envoi de l'email via Courier
        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<>();
        to.put("email", user.getMail()); // Utilisation de l'adresse e-mail du destinataire
        message.setTo(to);

        HashMap<String, Object> content = new HashMap<>();
        content.put("title", "Confirmation de paiement");
        content.put("body", emailContent.toString()); // Utilisation du contenu préparé pour l'email
        message.setContent(content);

        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs liées à l'envoi de l'email
        }
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
