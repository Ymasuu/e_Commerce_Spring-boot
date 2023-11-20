package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Produit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_produit")
    private int idProduit;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prix")
    private BigDecimal prix;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "stock")
    private Integer stock;

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produit produit = (Produit) o;

        if (idProduit != produit.idProduit) return false;
        if (nom != null ? !nom.equals(produit.nom) : produit.nom != null) return false;
        if (prix != null ? !prix.equals(produit.prix) : produit.prix != null) return false;
        if (description != null ? !description.equals(produit.description) : produit.description != null) return false;
        if (stock != null ? !stock.equals(produit.stock) : produit.stock != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProduit;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }
}
