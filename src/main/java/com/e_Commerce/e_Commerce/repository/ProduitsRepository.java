package com.e_Commerce.e_Commerce.repository;

import com.e_Commerce.e_Commerce.model.entity.Produit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitsRepository extends CrudRepository<Produit, Integer> {

    Optional<Produit> findByNom(String nom);
}
