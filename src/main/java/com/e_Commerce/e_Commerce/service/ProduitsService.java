package com.e_Commerce.e_Commerce.service;

import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_Commerce.e_Commerce.model.entity.Produit;
import com.e_Commerce.e_Commerce.repository.ProduitsRepository;

import java.util.Optional;

@Service
public class ProduitsService {
    @Autowired
    private ProduitsRepository productsRepository;

    public Iterable<Produit> getProduct() {
        return productsRepository.findAll();
    }
    public Produit getProductById(Integer id){
        return productsRepository.findById(id).orElse(null);
    }

    public Produit saveProduct(Produit product){
        return productsRepository.save(product);
    }

    public void deleteProduct(Produit product){
        productsRepository.delete(product);
    }

    public Produit getProductByNom(String nom) {return productsRepository.findByNom(nom).orElse(null);
    }
}
