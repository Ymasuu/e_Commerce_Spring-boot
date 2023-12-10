package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.Client;
import com.e_Commerce.e_Commerce.model.entity.Moderateur;
import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import com.e_Commerce.e_Commerce.service.ProduitsService;
import com.e_Commerce.e_Commerce.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

// This is the main controller handling redirection and processing for various pages in the e-Commerce application.
@Controller
public class RedirectController {
    //Attributes for different entities
    private Client client;

    // Autowired services for interacting with the database
    @Autowired
    private ProduitsService produitsService;
    @Autowired
    private UtilisateurService utilisateurService;

    // Mapping for the home page
    @GetMapping("/")
    public String index(ModelMap model) {
        return "index";
    }

    // Mapping for adding a payment method page
    @GetMapping("/Ajouter_Moyen_De_Paiement")
    public String ajouterMoyenPaiement(ModelMap model, HttpSession session) {
        // Logic for handling the addition of a payment method
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        return "ajouterMoyenPaiement";
    }

    // Post mapping for handling the form submission for adding a payment method
    @PostMapping("/Ajouter_Moyen_De_Paiement")
    public String ajouterMoyenPaiementPost(ModelMap model, HttpSession session, @RequestParam String carteBancaire) {
        // Logic for processing the form submission for adding a payment method
        Client client = (Client) session.getAttribute("client");
        if (carteBancaire.equals("0000 0000 0000 0000") || carteBancaire.equals(client.getCompteBancaireNum())) {
            Utilisateur user = (Utilisateur) session.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("client", client);
            String errorMessage = "Veuillez mettre un numéro de carte valide et différent de l'ancien";
            model.addAttribute("errorMessage", errorMessage);
            return "ajouterMoyenPaiement";
        }
        client.setCompteBancaireNum(carteBancaire);
        utilisateurService.saveClient(client);
        session.setAttribute("client", client);
        return "redirect:/Profil";
    }

    // Mapping for adding a product page
    @GetMapping("/Ajouter_Produit")
    public String ajouterProduit(ModelMap model) {
        return "ajouterProduit";
    }

    // Mapping for adding balance page
    @GetMapping("/AjouterSolde")
    public String ajouterSolde(ModelMap model, HttpSession session) {
        // Logic for displaying the add balance page
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        return "ajouterSolde";
    }
    // Post mapping for handling the form submission for adding balance

    @PostMapping("/AjouterSolde")
    public String ajouterSoldePost(ModelMap model, HttpSession session, @RequestParam String numeroCarte,
                                   @RequestParam Float montant) {
        // Logic for processing the form submission for adding balance

        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        if (client.getCompteBancaireNum() == null) {
            String errorMessage = "Vous devez ajouter votre carte bleue sur votre profil avant de pouvoir ajouter de " +
                    "l'argent sur votre compte";
            model.addAttribute("errorMessage", errorMessage);
            return "ajouterSolde";
        }

        if (client.getCompteBancaireNum().equals("0000 0000 0000 0000") || numeroCarte.isEmpty()) {
            String errorMessage = "Vous devez ajouter votre carte bleue sur votre profil avant de pouvoir ajouter de " +
                    "l'argent sur votre compte";
            model.addAttribute("errorMessage", errorMessage);
            return "ajouterSolde";
        } else if (!numeroCarte.equals(client.getCompteBancaireNum())) {
            String errorMessage = "Numéro de carte bleue incorrect";
            model.addAttribute("errorMessage", errorMessage);
            return "ajouterSolde";
        } else {
            float soldeActuel = client.getCompteBancaireSolde();
            float soldeApresModif = soldeActuel + montant;
            client.setCompteBancaireSolde(soldeApresModif);
            //TODO update la bdd avec le nouveau solde pour le client
            utilisateurService.saveClient(client);
            session.setAttribute("client", client);
            return "profil";
        }
    }

    /////////////////////////////////////////////////////////////////////


    // Mapping to change password page
    @GetMapping("/Changer_Mot_De_Passe")
    public String changerMotDePasse(ModelMap model, HttpSession session) {
        return "changerMotDePasse";
    }

    // Post mapping to handle the form submission to change password
    @PostMapping("/Changer_Mot_De_Passe")
    public String changerMotDePassePost(RedirectAttributes redirectAttributes,
                                        ModelMap model,
                                        HttpSession session,
                                        @RequestParam("oldPassword") String oldPassword,
                                        @RequestParam("newPassword") String newPassword,
                                        @RequestParam("confirmPassword") String confirmPassword) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        if (user.getMotDePasse().equals(oldPassword) && newPassword.trim().equals(confirmPassword.trim())) {
            user.setMotDePasse(newPassword);
            utilisateurService.saveUser(user);
            session.setAttribute("user", user);

            return "redirect:/Profil";
        } else {
            // incorrect password, so error message
            String errorMessage = "Mot de passe incorrect";
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/Changer_Mot_De_Passe";
        }
    }
    // Mapping for converting points page

    @GetMapping("/Convertir_Points")
    public String convertirPoints(ModelMap model, HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("client", client);
        return "convertirPoints";
    }

    // Post mapping for handling the form submission for converting points
    @PostMapping("/Convertir_Points")
    public String convertPoints(ModelMap model, RedirectAttributes redirectAttributes, @RequestParam int quantite, @RequestParam String action, HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        System.out.println(client.getPoints());
        if ("convertir".equals(action)) {
            // user chose to convert his points
            if (quantite > client.getPoints()) {
                String errorMessage = "Vous n'avez pas assez de points pour convertir " + quantite + " points.";
                model.addAttribute("errorMessage", errorMessage);
                return "redirect:/Convertir_Points";
            } else if (quantite < 1) {
                String errorMessage = "Vous devez convertir au moins 1 point";
                model.addAttribute("errorMessage", errorMessage);
                return "redirect:/Convertir_Points";
            } else {
                // example conversion point : 1 point = 1 euro)
                float soldeActuel = client.getCompteBancaireSolde();
                float soldeApresModif = soldeActuel + quantite;
                client.setCompteBancaireSolde(soldeApresModif);

                int pointsApresModif = client.getPoints() - quantite;
                client.setPoints(pointsApresModif);

                client = utilisateurService.saveClient(client);
                session.setAttribute("client", client);

                redirectAttributes.addFlashAttribute("successMessage", "Conversion réussie. Montant ajouté au solde : " + quantite);
                return "redirect:/Profil";
            }
        } else if ("annuler".equals(action)) {
            // L'utilisateur a choisi de ne pas convertir les points
            return "redirect:/Profil";
        }
        return "convertirPoints";
    }

    // Mapping for the list of moderators page
    /* ---------------------------Modérateurs---------------------------*/
    @GetMapping("/Liste_Moderateurs")
    public String listModerateurs(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
        Iterable<Moderateur> moderateurs = utilisateurService.getModerators();
        List<Integer> IdModos = new ArrayList<>();
        List<Utilisateur> listeModUtilisateur = new ArrayList<>();
        for (Moderateur mod : moderateurs) {
            listeModUtilisateur.add(utilisateurService.getUserById(mod.getIdModerateur()));
            IdModos.add(mod.getIdModerateur());
        }
        System.out.println("\n\nListe des id modérateurs : " + IdModos);
        model.addAttribute("user", user);
        model.addAttribute("moderateur", moderateur);
        model.addAttribute("listeModerateurs", moderateurs);
        model.addAttribute("listeModUtilisateur", listeModUtilisateur);
        return "listeModerateurs";
    }

    // Mapping for modifying moderator rights page
    @GetMapping("/modifierDroits")
    public String afficherPageModifierDroits(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");

        Iterable<Utilisateur> listUtilisateur = utilisateurService.getUsers();
        model.addAttribute("listUtilisateur", listUtilisateur);
        return "modifierDroitsModerateur";
    }

    // Post mapping for handling the form submission for modifying moderator rights
    @PostMapping("/modifierDroits")
    public String RecupererModifDroits(ModelMap model, HttpSession session,
                                       @RequestParam("email") String email, @RequestParam("droits") String droits) {
        Utilisateur utilisateur = utilisateurService.verifierUtilisateur(email);
        if (utilisateur != null) {
            Moderateur moderateur = utilisateurService.getModerateurByIdUtilisateur(utilisateur.getIdUtilisateur());
            moderateur.setDroits(droits);
            Moderateur nouveauModerateur = utilisateurService.saveModerateur(moderateur);
            model.addAttribute("modification", true);
        } else {
            return "redirect:/error";
        }
        return "redirect:/modifierDroits";
    }

    // Mapping for the page to delete a moderator

    @GetMapping("/supprimerModerateur")
    public String supprimerModerateur(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");

        Iterable<Utilisateur> listUtilisateur = utilisateurService.getUsers();
        model.addAttribute("listUtilisateur", listUtilisateur);
        return "supprimerModerateur";
    }

    // Post mapping for handling the form submission for deleting a moderator
    @PostMapping("/supprimerModerateur")
    public String supprimerModerateur(ModelMap model, HttpSession session, @RequestParam("email") String email) {
        Utilisateur utilisateur = utilisateurService.verifierUtilisateur(email);
        if (utilisateur != null) {
            Moderateur moderateur = utilisateurService.getModerateurByIdUtilisateur(utilisateur.getIdUtilisateur());
            utilisateurService.deleteModerateur(moderateur);
            utilisateurService.deleteUser(utilisateur);
            model.addAttribute("suppression", true);
        } else {
            return "redirect:/error";
        }
        return "redirect:/supprimerModerateur";
    }

    // Mapping for the page to modify user profile
    @GetMapping("/Modifier_Profil")
    public String modifierProfil(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Client client = (Client) session.getAttribute("client");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        System.out.println("Modifier_Profil -> ERROR MESSAGE : " + model.getAttribute("errorMessage"));
        return "modifierProfil";
    }
    // Post mapping for handling the form submission for modifying user profile

    @PostMapping("/Modifier_Profil")
    public String modifierProfilPost(RedirectAttributes redirectAttributes,
                                     ModelMap model, HttpSession session, @RequestParam("nom") String nom,
                                     @RequestParam("prenom") String prenom, @RequestParam("mail") String email,
                                     @RequestParam("mdp") String mdp) {

        // Il est verifiée si le mot de passe est correct
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user.getMotDePasse().equals(mdp)) {
            // Le mot de passe est correct, on modifie les données de l'utilisateur
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setMail(email);
            user.setMotDePasse(mdp);
            //TODO update d'utilisateur dans la bdd
            utilisateurService.saveUser(user);
            session.setAttribute("user", user);
            session.setAttribute("client", client);
            model.addAttribute("user", user);
            model.addAttribute("client", client);
            return "redirect:/Profil";
        } else {
            // Le mot de passe est incorrect, on affiche un message d'erreur
            String errorMessage = "Mot de passe incorrect";
            //Permet de redirectionner le message sans avoir besoin du model
            //vers un autre mapping de type Get.
            //Cela permet aussi de ne pas avoir le besoin de retourner une page.
            //Il est possible donc de retourner un GetMapping, ce qu'est sembable
            //à la logique des servlettes
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/Modifier_Profil";
        }


    }
    // Mapping for the user profile page

    @GetMapping("/Profil")
    public String utilisateur(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Moderateur moderateur = (Moderateur) session.getAttribute("moderateur");
        Client client = (Client) session.getAttribute("client");

        model.addAttribute("user", user);
        model.addAttribute("moderateur", moderateur);
        model.addAttribute("client", client);
        return "profil";
    }

    @GetMapping("/error")
    public String erreur() {
        return "erreur";
    }

}


