package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

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

    public CommandeProduit(Integer idCommande, Integer idProduit, Integer quantite){
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public CommandeProduit(){

    }
    public int getIdCommandeProd() {
        return idCommandeProd;
    }

    public void setIdCommandeProd(int idCommandeProd) {
        this.idCommandeProd = idCommandeProd;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

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

    @Override
    public int hashCode() {
        int result = idCommandeProd;
        result = 31 * result + (idCommande != null ? idCommande.hashCode() : 0);
        result = 31 * result + (idProduit != null ? idProduit.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        return result;
    }
}
