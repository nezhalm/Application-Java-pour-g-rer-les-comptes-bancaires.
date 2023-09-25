package Model;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Employe extends Personne {
    private String matricule;
    private LocalDate dateRecrutement;
    private String email;
 //   private List<Compte> comptes;
    private Mission mission;
   // private List<Operation> operations;

    public Employe(String matricule, String prenom, String nom, LocalDate dateNaissance, String telephone, String email, LocalDate dateRecrutement) {

        super(nom, prenom, dateNaissance, telephone);
        this.matricule = matricule;
        this.dateRecrutement = dateRecrutement;
        this.email = email;


    }

    public Employe() {

    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(LocalDate dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  //  public List<Compte> getComptes() {
  //      return comptes;
 //   }

   // public void setComptes(List<Compte> comptes) {
   //     this.comptes = comptes;
   // }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

   // public List<Operation> getOperations() {
   //     return operations;
  //  }

    //public void setOperations(List<Operation> operations) {
     //   this.operations = operations;
  //  }
}
