package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private BigDecimal prix;
    @Basic
    @Column(name = "status")
    private String status;

    @OneToMany
    private List<Produit> panier;

    @Basic
    private int nbrProduit;

    public Commande(){

    }
    public Commande(Integer idClient, BigDecimal prix){
        this.idClient = idClient;
        this.prix = prix;
        this.status= "non paye";
        this.panier = new ArrayList<>();
        this.nbrProduit = 0;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Produit> getPanier() { return panier; }

    public void setPanier(List<Produit> panier) {
        this.panier = panier;
        this.nbrProduit = panier.size();
    }

    public int getNbrProduit() {
        return nbrProduit;
    }

    public void setNbrProduit(int nbrProduit) {
        this.nbrProduit = nbrProduit;
    }

    public void ajouterProduit(Produit produit) {
        boolean produitIsPresent = false;
        for (Produit p : panier) {
            if (p.getIdProduit() == produit.getIdProduit()) {
                produitIsPresent = true;
                p.setStock(p.getStock() + produit.getStock());
                majNbrProduit();
                break;
            }
        }
        if (!produitIsPresent){
            panier.add(produit);
        }
    }

    public void supprimerProduit(Produit produit) {
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

    private void majNbrProduit() {
        int totalQuantite = 0;

        for (Produit produit : panier) {
            totalQuantite += produit.getStock();
        }

        nbrProduit = totalQuantite;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commande commande = (Commande) o;

        if (idCommande != commande.idCommande) return false;
        if (idClient != null ? !idClient.equals(commande.idClient) : commande.idClient != null) return false;
        if (prix != null ? !prix.equals(commande.prix) : commande.prix != null) return false;
        if (status != null ? !status.equals(commande.status) : commande.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCommande;
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
