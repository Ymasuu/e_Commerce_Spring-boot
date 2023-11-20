package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Commande {
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
