package com.e_Commerce.e_Commerce.service;

import com.e_Commerce.e_Commerce.model.entity.Commande;
import com.e_Commerce.e_Commerce.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public Iterable<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Integer id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Commande commande) {
        commandeRepository.delete(commande);
    }

    // Add additional methods or custom queries as needed
}
