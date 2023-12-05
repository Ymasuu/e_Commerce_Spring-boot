package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
/**
 * Represents the association between a Commande (Order) and a Produit (Product) in the e-Commerce application.
 * Each CommandeProduit entity has a unique identifier, the IDs of the associated order and product, and a quantity.
 */

@Entity
@Table(name = "Commande_Produit")
public class CommandeProduit implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_commande_prod")
    private int idCommandeProd;
    @Basic
    @Column(name = "id_commande")
    private Integer idCommande;
    @Basic
    @Column(name = "id_produit")
    private Integer idProduit;
    @Basic
    @Column(name = "quantite")
    private Integer quantite;
    /**
     * Constructs a CommandeProduit object with the given order ID, product ID, and quantity.
     *
     * @param idCommande The unique identifier of the associated order.
     * @param idProduit  The unique identifier of the associated product.
     * @param quantite   The quantity of the associated product in the order.
     */

    public CommandeProduit(Integer idCommande, Integer idProduit, Integer quantite){
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    } // Default constructor required by JPA

    public CommandeProduit(){

    }
    // Getters and setters for CommandeProduit attributes

    /**
     * Retrieves the unique identifier of the CommandeProduit association.
     *
     * @return The unique identifier of the CommandeProduit association.
     */
    public int getIdCommandeProd() {
        return idCommandeProd;
    }

 /**
     * Sets the unique identifier for the CommandeProduit association.
     *
     * @param idCommandeProd The new unique identifier for the CommandeProduit association.
     */
    public void setIdCommandeProd(int idCommandeProd) {
        this.idCommandeProd = idCommandeProd;
    }
    /**
     * Retrieves the unique identifier of the associated order.
     *
     * @return The unique identifier of the associated order.
     */
    public Integer getIdCommande() {
        return idCommande;
    }
    /**
     * Sets the unique identifier of the associated order.
     *
     * @param idCommande The new unique identifier of the associated order.
     */

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }
    /**
     * Retrieves the unique identifier of the associated product.
     *
     * @return The unique identifier of the associated product.
     */
    public Integer getIdProduit() {
        return idProduit;
    }
     /**
     * Sets the unique identifier of the associated product.
     *
     * @param idProduit The new unique identifier of the associated product.
     */
    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }
      /**
     * Retrieves the quantity of the associated product in the order.
     *
     * @return The quantity of the associated product in the order.
     */
    public Integer getQuantite() {
        return quantite;
    }
    /**
     * Sets the quantity of the associated product in the order.
     *
     * @param quantite The new quantity of the associated product in the order.
     */
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    /**
     * Checks if this CommandeProduit object is equal to another object.
     *
     * @param o The object to compare with this CommandeProduit.
     * @return True if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandeProduit that = (CommandeProduit) o;

        if (idCommandeProd != that.idCommandeProd) return false;
        if (idCommande != null ? !idCommande.equals(that.idCommande) : that.idCommande != null) return false;
        if (idProduit != null ? !idProduit.equals(that.idProduit) : that.idProduit != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;

        return true;
    }
     /**
     * Generates a hash code for this CommandeProduit object.
     *
     * @return The hash code value for this CommandeProduit.
     */
    @Override
    public int hashCode() {
        int result = idCommandeProd;
        result = 31 * result + (idCommande != null ? idCommande.hashCode() : 0);
        result = 31 * result + (idProduit != null ? idProduit.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        return result;
    }
}
