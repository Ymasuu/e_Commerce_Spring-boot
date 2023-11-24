package com.e_Commerce.e_Commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_Commerce.e_Commerce.model.entity.Produit;
import com.e_Commerce.e_Commerce.repository.ProduitsRepository;

@Service
public class ProduitsService {
    @Autowired
    private ProduitsRepository produitsRepository;

    public Iterable<Produit> getProduits() {
        return produitsRepository.findAll();
    }
}
