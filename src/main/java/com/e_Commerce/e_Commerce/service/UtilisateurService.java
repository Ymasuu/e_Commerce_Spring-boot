package com.e_Commerce.e_Commerce.service;

import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import com.e_Commerce.e_Commerce.repository.UtilisateurRepository;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> getUserById(Integer id){
        return utilisateurRepository.findById(id);
    }


/*    public List<Utilisateur> getAllModerateurs() {
        return utilisateurRepository.findByTypeDeCompte("Moderateur");
    }*/

    public Utilisateur saveUser(Utilisateur user){
        return utilisateurRepository.save(user);
    }

    public Utilisateur verifierUtilisateur(String email, String motDePasse) {
        return utilisateurRepository.findByMailAndMotDePasse(email, motDePasse).orElse(null);
    }

    public Utilisateur verifierUtilisateur(String email) {
        return utilisateurRepository.findByMail(email).orElse(null);
    }
}

