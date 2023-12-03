package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Client")
public class Client implements Serializable {
    @Id
    @Column(name = "id_client")
    private int idClient;
    @Basic
    @Column(name = "compte_bancaire_num")
    private String compteBancaireNum;
    @Basic
    @Column(name = "compte_bancaire_solde")
    private Float compteBancaireSolde;
    @Basic
    @Column(name = "points")
    private Integer points;

    public Client(int idClient) {
        this.idClient = idClient;
        this.compteBancaireNum = "0000 0000 0000 0000";
        this.compteBancaireSolde = (float) 0;
        this.points = 0;
    }

    public Client() {

    }

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

    public Float getCompteBancaireSolde() {
        return compteBancaireSolde;
    }

    public void setCompteBancaireSolde(float compteBancaireSolde) {
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
        if (!Objects.equals(compteBancaireNum, client.compteBancaireNum))
            return false;
        if (!Objects.equals(compteBancaireSolde, client.compteBancaireSolde))
            return false;
        return Objects.equals(points, client.points);
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + (compteBancaireNum != null ? compteBancaireNum.hashCode() : 0);
        //result = 31 * result + (compteBancaireSolde != null ? compteBancaireSolde : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
