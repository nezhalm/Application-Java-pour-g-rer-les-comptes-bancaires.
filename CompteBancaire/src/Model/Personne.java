package Model;

import java.time.LocalDate;
import java.util.Date;

public class Personne {
    protected String nom;
    protected String prenom;
    protected LocalDate dateNaissance;
    protected String telephone;
    public Personne(String nom, String prenom, LocalDate dateNaissance, String telephone) {
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
        setTelephone(telephone);
     }

    public Personne() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
