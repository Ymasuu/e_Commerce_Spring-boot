package com.e_Commerce.e_Commerce.repository;

import com.e_Commerce.e_Commerce.model.entity.Produit;
import org.springframework.data.repository.CrudRepository;
import  org.springframework.stereotype.Repository;

@Repository
public interface ProduitsRepository extends CrudRepository<Produit, Integer> {

}
