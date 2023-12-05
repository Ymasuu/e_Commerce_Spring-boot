package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * Represents a Product entity in the e-Commerce application.
 * Each Product has a unique identifier, a name, price, description, and available stock.
 */

@Entity
@Table(name = "Produit")
public class Produit implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_produit")
    private int idProduit;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prix")
    private Float prix;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "stock")
    private Integer stock;

     /**
     * Constructs a Product object with specified values for name, price, description, and stock.
     *
     * @param nom         The name of the product.
     * @param prix        The price of the product.
     * @param description The description of the product.
     * @param stock       The available stock of the product.
     */

    public Produit(String nom, float prix, String description, Integer stock){
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.stock = stock;
    }
    
    /**
     * Default constructor for the Product class.
     */
    public Produit(){

    }
    /**
     * Retrieves the unique identifier of the Product.
     *
     * @return The unique identifier of the Product.
     */
    public int getIdProduit() {
        return idProduit;
    }
    /**
     * Sets the unique identifier for the Product.
     *
     * @param idProduit The new unique identifier for the Product.
     */

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    /**
     * Retrieves the name of the Product.
     *
     * @return The name of the Product.
     */

    public String getNom() {
        return nom;
    }
    /**
     * Sets the name for the Product.
     *
     * @param nom The new name for the Product.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
     /**
     * Retrieves the price of the Product.
     *
     * @return The price of the Product.
     */
    public float getPrix() {
        return prix;
    }
     /**
     * Sets the price for the Product.
     *
     * @param prix The new price for the Product.
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }
     /**
     * Retrieves the description of the Product.
     *
     * @return The description of the Product.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description for the Product.
     *
     * @param description The new description for the Product.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Retrieves the available stock of the Product.
     *
     * @return The available stock of the Product.
     */

    public Integer getStock() {
        return stock;
    }
    /**
     * Sets the available stock for the Product.
     *
     * @param stock The new available stock for the Product.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    /**
     * Checks if this Product object is equal to another object.
     *
     * @param o The object to compare with this Product.
     * @return True if the objects are equal, false otherwise.
     */
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
    /**
     * Generates a hash code for this Product object.
     *
     * @return The hash code value for this Product.
     */

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
