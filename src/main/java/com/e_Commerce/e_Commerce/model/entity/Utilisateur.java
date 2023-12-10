package com.e_Commerce.e_Commerce.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a User entity in the e-Commerce application.
 * Each User has a unique identifier, name, surname, email, password, and account type.
 */
@Entity
@Table(name = "Utilisateur")
public class Utilisateur implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_utilisateur")
    private int idUtilisateur;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prenom")
    private String prenom;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "mot_de_passe")
    private String motDePasse;
    @Basic
    @Column(name = "type_de_compte")
    private String typeDeCompte;

    /**
     * Default constructor for the User class.
     */
    public Utilisateur() {
    }

    /**
     * Constructs a User object with specified values for name, surname, email, password, and account type.
     *
     * @param nom          The name of the user.
     * @param prenom       The surname of the user.
     * @param mail         The email of the user.
     * @param motDePasse   The password of the user.
     * @param typeDeCompte The account type of the user.
     */
    public Utilisateur(String nom, String prenom, String mail, String motDePasse, String typeDeCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.typeDeCompte = typeDeCompte;
    }

    /**
     * Retrieves the unique identifier of the User.
     *
     * @return The unique identifier of the User.
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Sets the unique identifier for the User.
     *
     * @param idUtilisateur The new unique identifier for the User.
     */

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Retrieves the name of the User.
     *
     * @return The name of the User.
     */

    public String getNom() {
        return nom;
    }

    /**
     * Sets the name for the User.
     *
     * @param nom The new name for the User.
     */

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retrieves the surname of the User.
     *
     * @return The surname of the User.
     */

    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets the surname for the User.
     *
     * @param prenom The new surname for the User.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retrieves the email of the User.
     *
     * @return The email of the User.
     */

    public String getMail() {
        return mail;
    }

    /**
     * Sets the email for the User.
     *
     * @param mail The new email for the User.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Retrieves the password of the User.
     *
     * @return The password of the User.
     */

    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Sets the password for the User.
     *
     * @param motDePasse The new password for the User.
     */

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Retrieves the account type of the User.
     *
     * @return The account type of the User.
     */

    public String getTypeDeCompte() {
        return typeDeCompte;
    }

    /**
     * Sets the account type for the User.
     *
     * @param typeDeCompte The new account type for the User.
     */

    public void setTypeDeCompte(String typeDeCompte) {
        this.typeDeCompte = typeDeCompte;
    }

    /**
     * Checks if this User object is equal to another object.
     *
     * @param o The object to compare with this User.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        if (idUtilisateur != that.idUtilisateur) return false;
        if (!Objects.equals(nom, that.nom)) return false;
        if (!Objects.equals(prenom, that.prenom)) return false;
        if (!Objects.equals(mail, that.mail)) return false;
        if (!Objects.equals(motDePasse, that.motDePasse)) return false;
        return Objects.equals(typeDeCompte, that.typeDeCompte);
    }

    /**
     * Generates a hash code for this User object.
     *
     * @return The hash code value for this User.
     */

    @Override
    public int hashCode() {
        int result = idUtilisateur;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        result = 31 * result + (typeDeCompte != null ? typeDeCompte.hashCode() : 0);
        return result;
    }
}
