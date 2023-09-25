package Model;

import java.time.LocalDate;
import java.util.Optional;

public class CompteCourant extends Compte{
    private int decouvert;
    public CompteCourant(Double solde, String numero, LocalDate dateCreation, Etat etat, Employe employe, Client client, int decouvert) {
        super(solde, numero, dateCreation, etat, employe, client); // Appel au constructeur de la classe parente Compte
        this.decouvert = decouvert;
    }

    public CompteCourant() {
        super();
    }

    public int getDecouvert() {
        return decouvert;
    }
    public void setDecouvert(int decouvert) {
        this.decouvert = decouvert;
    }
}
