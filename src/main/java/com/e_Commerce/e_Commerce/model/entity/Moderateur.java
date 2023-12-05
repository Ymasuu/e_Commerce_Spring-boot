package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
/**
 * Represents a Moderateur (Moderator) entity in the e-Commerce application.
 * Each Moderateur has a unique identifier and a set of rights assigned.
 */
@Entity
@Table(name = "Moderateur")
public class Moderateur implements Serializable {
    @Id
    @Column(name = "id_moderateur")
    private int idModerateur;
    @Basic
    @Column(name = "droits")
    private String droits;
    /**
     * Constructs a Moderateur object with default values.
     * Initializes the rights to "000" by default.
     */

    public Moderateur(){
        this.droits="000";
    }
    /**
     * Retrieves the unique identifier of the Moderateur.
     *
     * @return The unique identifier of the Moderateur.
     */
    public int getIdModerateur() {
        return idModerateur;
    }
    /**
     * Sets the unique identifier for the Moderateur.
     *
     * @param idModerateur The new unique identifier for the Moderateur.
     */
    public void setIdModerateur(int idModerateur) {
        this.idModerateur = idModerateur;
    }
     /**
     * Retrieves the set of rights assigned to the Moderateur.
     *
     * @return The set of rights assigned to the Moderateur.
     */

    public String getDroits() {
        return droits;
    }
     /**
     * Sets the set of rights for the Moderateur.
     *
     * @param droits The new set of rights for the Moderateur.
     */
    public void setDroits(String droits) {
        this.droits = droits;
    }
    /**
     * Checks if this Moderateur object is equal to another object.
     *
     * @param o The object to compare with this Moderateur.
     * @return True if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        // Implementation of equals method
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moderateur that = (Moderateur) o;

        if (idModerateur != that.idModerateur) return false;
        if (droits != null ? !droits.equals(that.droits) : that.droits != null) return false;

        return true;
    }
    /**
     * Generates a hash code for this Moderateur object.
     *
     * @return The hash code value for this Moderateur.
     */
    @Override
    public int hashCode() {
        int result = idModerateur;
        result = 31 * result + (droits != null ? droits.hashCode() : 0);
        return result;
    }
}
