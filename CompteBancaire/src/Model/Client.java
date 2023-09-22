package Model;
import java.util.Date;
import java.util.List;

public class Client extends Personne {
    private String code ;
    private String adresse ;

    public Client(String nom, String prenom,String telephone , Date dateNaissance, String code, String adresse) {
        super(nom, prenom, dateNaissance, telephone);
        this.code = code;
        this.adresse = adresse;
    }


    private List<Compte> comptes;

    public Client() {
        super();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
