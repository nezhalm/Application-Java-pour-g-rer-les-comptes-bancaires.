package Model;

import java.time.LocalDate;
import java.util.List;

public class  Compte {
    private Double solde;
    private String numero;
    private LocalDate dateCreation;
    private Etat etat;
    private Employe employe;
    private Client client;

    public Compte(Double solde, String numero, LocalDate dateCreation, Etat etat, Employe employe, Client client) {
        this.solde = solde;
        this.numero = numero;
        this.dateCreation = dateCreation;
        this.etat = etat;
        this.employe = employe;
        this.client = client;
    }
    private List<Operation> operations;

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void  setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
