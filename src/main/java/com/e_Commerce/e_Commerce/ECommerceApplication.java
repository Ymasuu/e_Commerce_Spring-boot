package com.e_Commerce.e_Commerce;

import com.e_Commerce.e_Commerce.model.entity.Produit;
import com.e_Commerce.e_Commerce.service.ProduitsService;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {

	@Autowired
	private ProduitsService produitsService;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		Iterable<Produit> produits = produitsService.getProduct();
		produits.forEach(produit -> System.out.println("Test list : " + produit.getNom()));

		Optional<Produit> optProduct = produitsService.getProductById(1);
		Produit productId1 = optProduct.get();
		System.out.println("Test nom prod by id : " + productId1.getNom());
	}

}
