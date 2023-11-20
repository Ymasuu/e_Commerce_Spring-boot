package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_client")
    private int idClient;
    @Basic
    @Column(name = "compte_bancaire_num")
    private String compteBancaireNum;
    @Basic
    @Column(name = "compte_bancaire_solde")
    private BigDecimal compteBancaireSolde;
    @Basic
    @Column(name = "points")
    private Integer points;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getCompteBancaireNum() {
        return compteBancaireNum;
    }

    public void setCompteBancaireNum(String compteBancaireNum) {
        this.compteBancaireNum = compteBancaireNum;
    }

    public BigDecimal getCompteBancaireSolde() {
        return compteBancaireSolde;
    }

    public void setCompteBancaireSolde(BigDecimal compteBancaireSolde) {
        this.compteBancaireSolde = compteBancaireSolde;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (idClient != client.idClient) return false;
        if (compteBancaireNum != null ? !compteBancaireNum.equals(client.compteBancaireNum) : client.compteBancaireNum != null)
            return false;
        if (compteBancaireSolde != null ? !compteBancaireSolde.equals(client.compteBancaireSolde) : client.compteBancaireSolde != null)
            return false;
        if (points != null ? !points.equals(client.points) : client.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + (compteBancaireNum != null ? compteBancaireNum.hashCode() : 0);
        result = 31 * result + (compteBancaireSolde != null ? compteBancaireSolde.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
