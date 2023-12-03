package com.e_Commerce.e_Commerce.repository;

import com.e_Commerce.e_Commerce.model.entity.Moderateur;
import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    // List<Utilisateur> findByTypeDeCompte(String typeDeCompte);

    Optional<Utilisateur> findByMail(String email);
    Optional<Utilisateur> findByMailAndMotDePasse(String email, String motDePasse);


}
