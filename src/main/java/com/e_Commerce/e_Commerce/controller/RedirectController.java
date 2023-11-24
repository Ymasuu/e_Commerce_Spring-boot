package com.e_Commerce.e_Commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    @GetMapping("/")
    public String index(ModelMap model) {
        return "index";
    }
    @GetMapping("/Ajouter_Moderateur")
    public String ajouterModerateur(ModelMap model) { return "pageAjouterModerateur";}
    @GetMapping("/connexion")
    public String connexion(ModelMap model) {
        return "pageConnexion";
    }
}
