package com.e_Commerce.e_Commerce.repository;

import com.e_Commerce.e_Commerce.model.entity.CommandeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeProduitRepository extends JpaRepository<CommandeProduit, Long> {
    List<CommandeProduit> findByIdProduit(Integer idProduit);
}
