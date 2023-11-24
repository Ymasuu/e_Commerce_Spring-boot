package com.e_Commerce.e_Commerce.service;

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
    public Optional<Produit> getProductById(Integer id){
        return productsRepository.findById(id);
    }

    public Produit saveProduct(Produit product){
        return productsRepository.save(product);
    }

}
