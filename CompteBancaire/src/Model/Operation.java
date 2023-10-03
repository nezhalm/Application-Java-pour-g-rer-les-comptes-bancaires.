package Model;
import java.time.LocalDate;

public class Operation<C extends Compte> {
    private Employe employes;
    private Compte comptes;
    private String numero;
    private LocalDate dateCreation;
    private int montant;
    private   TypeOperation  type;

    public Operation(Employe employes, Compte comptes, String numero, LocalDate dateCreation, int montant) {
        setEmployes(employes);
        setComptes(comptes);
        setNumero(numero);
        setDateCreation(dateCreation);
        setMontant(montant);
    }

    public Operation(Employe employes, Compte comptes, String numero, LocalDate dateCreation, int montant,TypeOperation type) {
        setEmployes(employes);
        setComptes(comptes);
        setNumero(numero);
        setDateCreation(dateCreation);
        setMontant(montant);
        setType(type);
    }

    public Operation() {

    }

    public Employe getEmployes() {
        return employes;
    }

    public void setEmployes(Employe employes) {
        this.employes = employes;
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

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Compte getComptes() {
        return comptes;
    }

    public void setComptes(Compte comptes) {
        this.comptes = comptes;
    }

    public TypeOperation getType() {
        return type;
    }

    public void setType(TypeOperation type) {
        this.type = type;
    }
}
