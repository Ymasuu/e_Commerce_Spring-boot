package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Commande (Order) entity in the e-Commerce application.
 * Each order has a unique identifier, a client ID, a total price, a status, a list of products (cart), and the number of products.
 */
@Entity
@Table(name = "Commande")
public class Commande implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_commande")
    private int idCommande;
    @Basic
    @Column(name = "id_client")
    private Integer idClient;
    @Basic
    @Column(name = "prix")
    private Float prix;
    @Basic
    @Column(name = "status")
    private String status;

    @Transient
    private List<Produit> panier;

    @Transient
    private int nbrProduit;
    
    /**
     * Default constructor required by JPA.
     */
    public Commande(){

    }
     /**
     * Constructs a Commande object with the given client ID and total price.
     * Initializes other attributes with default values.
     *
     * @param idClient The unique identifier of the client associated with the order.
     * @param prix     The total price of the order.
     */
    public Commande(Integer idClient, float prix){
        this.idClient = idClient;
        this.prix = prix;
        this.status= "non paye";
        this.panier = new ArrayList<>();
        this.nbrProduit = 0;
    }
    // Getters and setters for the Commande attributes

    /**
     * Retrieves the unique identifier of the order.
     *
     * @return The order's identifier.
     */
    public int getIdCommande() {
        return idCommande;
    }
    /**
     * Sets the unique identifier for the order.
     *
     * @param idCommande The new identifier for the order.
     */
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }
    /**
     * Retrieves the client's identifier associated with the order.
     *
     * @return The client's identifier.
     */
    public Integer getIdClient() {
        return idClient;
    }
    /**
     * Sets the client's identifier associated with the order.
     *
     * @param idClient The new client identifier for the order.
     */

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
    /**
     * Retrieves the total price of the order.
     *
     * @return The total price of the order.
     */

    public float getPrix() {
        return prix;
    }
     /**
     * Sets the total price for the order.
     *
     * @param prix The new total price for the order.
     */
    public void setPrix(float prix) { this.prix = prix; }
    /**
     * Retrieves the status of the order (e.g., "non paye").
     *
     * @return The status of the order.
     */

    public String getStatus() {
        return status;
    }
    /**
     * Sets the status for the order.
     *
     * @param status The new status for the order.
     */
    public void setStatus(String status) { this.status = status; }
    /**
     * Retrieves the list of products in the order (cart).
     *
     * @return The list of products in the order.
     */

    public List<Produit> getPanier() { return panier; }
    /**
     * Sets the list of products in the order (cart) and updates the number of products.
     *
     * @param panier The new list of products in the order.
     */

    public void setPanier(List<Produit> panier) {
        this.panier = panier;
        this.nbrProduit = panier.size();
    }
    /**
     * Retrieves the number of products in the order.
     *
     * @return The number of products in the order.
     */

    public int getNbrProduit() {
        return nbrProduit;
    }
    /**
     * Sets the number of products in the order.
     *
     * @param nbrProduit The new number of products in the order.
     */
    public void setNbrProduit(int nbrProduit) {
        this.nbrProduit = nbrProduit;
    }
    /**
     * Adds a product to the order's cart with the specified quantity.
     * If the product is already in the cart, updates the quantity.
     *
     * @param produit  The product to be added to the cart.
     * @param quantite The quantity of the product to add.
     */

    public void ajouterProduit(Produit produit, int quantite) {
        // Implementation logic to add or update a product in the cart
        boolean produitIsPresent = false;
        for (Produit p : panier) {
            if (p.getIdProduit() == produit.getIdProduit()) {
                produitIsPresent = true;
                p.setStock(p.getStock() + quantite);
                majNbrProduit();
                break;
            }
        }
        if (!produitIsPresent){
            produit.setStock(quantite);
            panier.add(produit);
            majNbrProduit();
        }
    }
     /**
     * Removes a specified quantity of a product from the order's cart.
     * If the remaining quantity is zero, removes the product from the cart.
     *
     * @param produit The product to be removed from the cart.
     */
    public void supprimerProduit(Produit produit) {
        // Implementation logic to remove a product from the cart
        for (Produit produitDansPanier : panier) {
            if (produitDansPanier.getIdProduit() == produit.getIdProduit()) {
                if (produitDansPanier.getStock() > produit.getStock()) {
                    produitDansPanier.setStock(produitDansPanier.getStock() - produit.getStock());
                } else {
                    panier.remove(produitDansPanier);
                }
                majNbrProduit();
                break;
            }
        }
    }
    /**
     * Updates the total quantity of products in the order based on the contents of the cart.
     */
    public void majNbrProduit() {
        // Implementation logic to update the total quantity of products in the order
        int totalQuantite = 0;

        for (Produit produit : panier) {
            totalQuantite += produit.getStock();
        }

        nbrProduit = totalQuantite;
    }
     
     /**
     * Checks if this Commande object is equal to another object.
     *
     * @param o The object to compare with this Commande.
     * @return True if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        // Implementation of equals method
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commande commande = (Commande) o;

        if (idCommande != commande.idCommande) return false;
        if (idClient != null ? !idClient.equals(commande.idClient) : commande.idClient != null) return false;
        if (prix != null ? !prix.equals(commande.prix) : commande.prix != null) return false;
        if (status != null ? !status.equals(commande.status) : commande.status != null) return false;

        return true;
    }
    /**
     * Generates a hash code for this Commande object.
     *
     * @return The hash code value for this Commande.
     */

    @Override
    public int hashCode() {
        // Implementation of hashCode method
        int result = idCommande;
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
