package com.e_Commerce.e_Commerce.controller;

import com.e_Commerce.e_Commerce.model.entity.Client;
import com.e_Commerce.e_Commerce.model.entity.Moderateur;
import com.e_Commerce.e_Commerce.model.entity.Produit;
import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import com.e_Commerce.e_Commerce.service.UtilisateurService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.e_Commerce.e_Commerce.service.ProduitsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Controller
public class RedirectController {
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
    @GetMapping("/Ajouter_Solde")
    public String ajouterSolde(ModelMap model) { return "pageAjouterSolde";}
    @GetMapping("/Changer_Mot_De_Passe")
    public String changerMotDePasse(ModelMap model) { return "pageChangerMotDePasse";}
    @GetMapping("/Confirmer_Paiement")
    public String confirmerPaiement(ModelMap model) { return "pageConfirmerPaiement";}
    @GetMapping("/Connexion")
    public String connexion(ModelMap model) { return "pageConnexion";}

    @PostMapping("/Connexion")
    public String processLogin(@RequestParam String email, @RequestParam String motDePasse, ModelMap model, HttpSession session) {
        Utilisateur user = utilisateurService.verifierUtilisateur(email, motDePasse);
        if (user != null) {
            session.setAttribute("user", user);
            // envoyer 'user' dans la session
            return "redirect:/Produits";
        } else {
            // Échec de la connexion, renvoyez l'utilisateur à la page de connexion avec un message d'erreur
            model.addAttribute("errorMessage", "Identifiants incorrects");
            return "pageConnexion";
        }
    }

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
    @GetMapping("/Produit/{id}")
    public String produit(ModelMap model, @PathVariable Integer id,HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user != null){
            System.out.println("Utilisateur connecté : " + user.getMail());
            model.addAttribute("user", user);
        }
        Produit product = produitsService.getProductById(id);
        model.addAttribute("produit", product);
        return "pageProduit";
    }
    @GetMapping("/Produits")
    public String produits(ModelMap model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user != null){
            System.out.println("Utilisateur connecté : " + user.getMail());
            model.addAttribute("user", user);
        }
        Iterable<Produit> products = produitsService.getProduct();
        model.addAttribute("produits", products);
        return "pageProduits";
    }

    @GetMapping("/Profil")
    public String utilisateur(ModelMap model, @RequestParam Integer id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurService.getUserById(id);

        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            model.addAttribute("utilisateur", utilisateur);
            return "pageProfil";
        } else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            // Vous pouvez rediriger vers une page d'erreur par exemple
            return "redirect:/erreur";
        }
    }

    @GetMapping("/Supprimer_Moderateur")
    public String supprimerModerateur(ModelMap model) { return "pageSupprimerModerateur";}
}
