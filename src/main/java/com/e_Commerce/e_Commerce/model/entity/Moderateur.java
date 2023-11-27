package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Moderateur")
public class Moderateur implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_moderateur")
    private int idModerateur;
    @Basic
    @Column(name = "droits")
    private String droits;

    public int getIdModerateur() {
        return idModerateur;
    }

    public void setIdModerateur(int idModerateur) {
        this.idModerateur = idModerateur;
    }

    public String getDroits() {
        return droits;
    }

    public void setDroits(String droits) {
        this.droits = droits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moderateur that = (Moderateur) o;

        if (idModerateur != that.idModerateur) return false;
        if (droits != null ? !droits.equals(that.droits) : that.droits != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idModerateur;
        result = 31 * result + (droits != null ? droits.hashCode() : 0);
        return result;
    }
}
