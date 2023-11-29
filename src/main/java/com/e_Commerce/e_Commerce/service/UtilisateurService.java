package com.e_Commerce.e_Commerce.service;

import com.e_Commerce.e_Commerce.model.entity.Client;
import com.e_Commerce.e_Commerce.model.entity.Moderateur;
import com.e_Commerce.e_Commerce.model.entity.Produit;
import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import com.e_Commerce.e_Commerce.repository.ClientRepository;
import com.e_Commerce.e_Commerce.repository.ModerateurRepository;
import com.e_Commerce.e_Commerce.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ModerateurRepository moderateurRepository;
    @Autowired
    private ClientRepository clientRepository;

    /* ------------------------ User -----------------------------*/
    public Utilisateur getUserById(Integer id){
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur saveUser(Utilisateur user){
        return utilisateurRepository.save(user);
    }

    public Utilisateur verifierUtilisateur(String email, String motDePasse) {
        return utilisateurRepository.findByMailAndMotDePasse(email, motDePasse).orElse(null);
    }
    public Iterable<Utilisateur> getUsers() {
        return utilisateurRepository.findAll();
    }
    public Utilisateur verifierUtilisateur(String email) {
        return utilisateurRepository.findByMail(email).orElse(null);
    }

    /*---------------------Moderator-----------------------*/
    public Moderateur saveModerateur(Moderateur moderateur){
        return moderateurRepository.save(moderateur);
    }
    public Iterable<Moderateur> getModerators() {
        return moderateurRepository.findAll();
    }

    public Moderateur getModerateurByIdUtilisateur(int id){
        return  moderateurRepository.findById(id).orElse(null);
    }


    /* --------------------------- Client ------------------------ */

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }
    public Client getClientByIdUtilisateur(int id){
        return  clientRepository.findById(id).orElse(null);
    }





/*    public List<Utilisateur> getAllModerateurs() {
        return utilisateurRepository.findByTypeDeCompte("Moderateur");
    }*/

}
