package com.e_Commerce.e_Commerce.service;

import com.e_Commerce.e_Commerce.model.entity.CommandeProduit;
import com.e_Commerce.e_Commerce.repository.CommandeProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeProduitService {

    private final CommandeProduitRepository commandeProduitRepository;

    @Autowired
    public CommandeProduitService(CommandeProduitRepository commandeProduitRepository) {
        this.commandeProduitRepository = commandeProduitRepository;
    }

    public Iterable<CommandeProduit> getAllCommandeProduits() {
        return commandeProduitRepository.findAll();
    }

    /*
    public Optional<CommandeProduit> getCommandeProduitById(int id) {
        return commandeProduitRepository.findById(id);
    }

     */

    public CommandeProduit saveCommandeProduit(CommandeProduit commandeProduit) {
        return commandeProduitRepository.save(commandeProduit);
    }

    public void deleteCommandeProduit(CommandeProduit commandeProduit) {
        commandeProduitRepository.delete(commandeProduit);
    }

    public List<CommandeProduit> getCommandeProduitsByProduitId(Integer idProduit) {
        return commandeProduitRepository.findByIdProduit(idProduit);
    }
}
