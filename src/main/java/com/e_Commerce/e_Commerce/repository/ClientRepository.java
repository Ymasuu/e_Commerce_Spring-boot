package com.e_Commerce.e_Commerce.repository;

import com.e_Commerce.e_Commerce.model.entity.Client;
import org.springframework.data.repository.CrudRepository;
import  org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {



}
