package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the entity class for a Client in the e-Commerce application.
 */
@Entity
@Table(name = "Client")
public class Client implements Serializable {
    // Client properties
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

    /**
     * Constructs a Client object with the given client identifier.
     * Initializes other attributes with default values.
     *
     * @param idClient The unique identifier for the client.
     */

    public Client(int idClient) {
        this.idClient = idClient;
        this.compteBancaireNum = "0000 0000 0000 0000";
        this.compteBancaireSolde = (float) 0;
        this.points = 0;
    }

    /**
     * Default constructor for the Client class.
     */
    public Client() {

    }
    // Getters and setters for Client properties

    /**
     * Retrieves the client's unique identifier.
     *
     * @return The client's identifier.
     */

    public int getIdClient() {
        return idClient;
    }

    /**
     * Sets the client's unique identifier.
     *
     * @param idClient The new identifier for the client.
     */

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Retrieves the client's bank account number.
     *
     * @return The client's bank account number.
     */
    public String getCompteBancaireNum() {
        return compteBancaireNum;
    }

    /**
     * Sets the client's bank account number.
     *
     * @param compteBancaireNum The new bank account number for the client.
     */

    public void setCompteBancaireNum(String compteBancaireNum) {
        this.compteBancaireNum = compteBancaireNum;
    }

    /**
     * Retrieves the client's bank account balance.
     *
     * @return The client's bank account balance.
     */

    public Float getCompteBancaireSolde() {
        return compteBancaireSolde;
    }

    /**
     * Sets the client's bank account balance.
     *
     * @param compteBancaireSolde The new bank account balance for the client.
     */

    public void setCompteBancaireSolde(float compteBancaireSolde) {
        this.compteBancaireSolde = compteBancaireSolde;
    }

    /**
     * Retrieves the loyalty points associated with the client.
     *
     * @return The loyalty points of the client.
     */

    public Integer getPoints() {
        return points;
    }

    /**
     * Sets the loyalty points for the client.
     *
     * @param points The new loyalty points for the client.
     */

    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * Checks if this Client object is equal to another object.
     *
     * @param o The object to compare with this Client.
     * @return True if the objects are equal, false otherwise.
     */

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

    /**
     * Generates a hash code for this Client object.
     *
     * @return The hash code value for this Client.
     */
    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + (compteBancaireNum != null ? compteBancaireNum.hashCode() : 0);
        //result = 31 * result + (compteBancaireSolde != null ? compteBancaireSolde : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
