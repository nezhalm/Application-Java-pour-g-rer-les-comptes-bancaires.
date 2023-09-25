package Model;
import java.time.LocalDate;

public class CompteEpargne extends Compte{
    private int tauxInterer;
    public CompteEpargne(Double solde, String numero, LocalDate dateCreation, Etat etat, Employe employe, Client client, int tauxInterer) {
        super(solde, numero, dateCreation, etat, employe, client);
        this.tauxInterer = tauxInterer;
    }
    public int getTauxInterer() {
        return tauxInterer;
    }
    public void setTauxInterer(int tauxInterer) {
        this.tauxInterer = tauxInterer;
    }
}
