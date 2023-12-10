package com.e_Commerce.e_Commerce.service;

import com.e_Commerce.e_Commerce.model.entity.Produit;
import com.e_Commerce.e_Commerce.repository.ProduitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitsService {
    @Autowired
    private ProduitsRepository productsRepository;

    public Iterable<Produit> getProduct() {
        return productsRepository.findAll();
    }

    public Produit getProductById(Integer id) {
        return productsRepository.findById(id).orElse(null);
    }

    public Produit saveProduct(Produit product) {
        return productsRepository.save(product);
    }

    public void deleteProduct(Produit product) {
        productsRepository.delete(product);
    }

}
