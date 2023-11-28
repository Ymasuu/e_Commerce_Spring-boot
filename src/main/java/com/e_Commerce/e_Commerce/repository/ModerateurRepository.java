package com.e_Commerce.e_Commerce.repository;

import com.e_Commerce.e_Commerce.model.entity.Moderateur;
import com.e_Commerce.e_Commerce.model.entity.Utilisateur;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import  org.springframework.stereotype.Repository;



@Repository
public interface ModerateurRepository extends CrudRepository<Moderateur, Integer> {

}
