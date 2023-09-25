package Service;

import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.CompteImp;
import Dao.DaoImplementation.EmployeImp;
import Dao.DaoImplementation.OperationImp;
import Model.Client;
import Model.CompteCourant;
import Model.CompteEpargne;
import Model.Employe;
import Model.Etat;

import java.time.LocalDate;
import java.util.*;

import static Service.Operation.ajouterOperation;

public class Compte {

    public static void ajouter() throws Exception {
        Scanner scanner2 = new Scanner(System.in);
        OperationImp operationImp = new OperationImp();
        CompteImp compteImp = new CompteImp();
        System.out.println("Veuillez saisir les informations de l'operation :");
        System.out.print("entrer le matricule de l'employe ");
        String matricule = scanner2.nextLine();
        Optional<Employe> ifexiste = operationImp.employeExiste(matricule);
        if (ifexiste.isPresent()) {
            System.out.println("L'employé existe et valide");
            System.out.println("choisir le type de compte");
            System.out.println("1 -compte courant");
            System.out.println("2 -compte epargne");
            int choix = scanner2.nextInt();
            switch (choix) {
                case 1:
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.print("Entrez le code de client : ");
                    String codeClient = scanner4.next();
                    ClientImp clientImp1 = new ClientImp();
                    Optional<Client> clientt = clientImp1.chercher(codeClient);
                    clientt.ifPresent(e -> {
                        System.out.println("le client existe!");
                    });
                    if (!clientt.isPresent()) {
                        System.out.println("client n'existe pas");
                        System.exit(0);
                    }
                    String codeUnique =   CompteImp.genererCodeUnique(5);
                    System.out.print("Entrez le solde : ");
                    Double soldeCourant = scanner4.nextDouble();
                    LocalDate dateActuelle = LocalDate.now();
                    System.out.print("Entrez le découvert : ");
                    int decouvertCourant = scanner4.nextInt();
                    //    System.out.print("Choisir l'etat : ");
                    //      System.out.println("Choisir l'état :");
                    //     System.out.println("1 - ACTIVE");
                    //    System.out.println("2 - FROZEN");
                    //     System.out.println("3 - CLOSED");
                    //   Scanner scanner = new Scanner(System.in);
                    //   int choixx = scanner.nextInt();

                   Etat etat = Etat.ACTIVE;

                //    switch (choixx) {
                    //        case 1:
                    //      etat = Etat.ACTIVE;
                    //      break;
                    //  case 2:
                    //      etat = Etat.FROZEN;
                    //      break;
                    //  case 3:
                    //      etat = Etat.CLOSED;
                    //      break;
                    //  default:
                    //      System.out.println("Choix invalide.");
                    //  //      break;
                    // }
                   CompteCourant compte = new CompteCourant(soldeCourant, codeUnique, dateActuelle, etat, ifexiste.get(), clientt.get(), decouvertCourant);
                   Optional<Model.Compte> compte1 = compteImp.ajouterCourant(compte);
                    compte1.ifPresent(e -> {
                        System.out.println("Le compte a été ajouté avec succès !");
                    });
                    if (!compte1.isPresent()) {
                        System.out.println("Échec de l'ajout de le compte.");
                    }
                    break;
                case 2:
                    Scanner scanner5 = new Scanner(System.in);
                    System.out.print("Entrez le code de client : ");
                    String codeClient2 = scanner5.next();
                    ClientImp clientImp2 = new ClientImp();
                    Optional<Client> client2 = clientImp2.chercher(codeClient2);
                    client2.ifPresent(e -> {
                        System.out.println("le client existe!");
                    });
                    if (!client2.isPresent()) {
                        System.out.println("client n'existe pas");
                        System.exit(0);
                    }
                    String codeUnique2 =   CompteImp.genererCodeUnique(5);
                    System.out.print("Entrez le solde : ");
                    Double soldeepargne = scanner5.nextDouble();
                    LocalDate dateActuellee = LocalDate.now();
                    System.out.print("Entrez le taux d'interet : ");
                    int taux = scanner5.nextInt();
                    Etat etat2 = Etat.ACTIVE;
                    CompteEpargne compte2 = new CompteEpargne(soldeepargne, codeUnique2, dateActuellee, etat2, ifexiste.get(), client2.get(), taux);
                    Optional<Model.Compte> compteRes = compteImp.ajouterEpargne(compte2);
                    compteRes.ifPresent(e -> {
                        System.out.println("Le compte a été ajouté avec succès !"+compteRes.get().getNumero());
                    });
                    if (!compteRes.isPresent()) {
                        System.out.println("Échec de l'ajout de le compte.");
                    }
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        } else {
            System.out.println("L'employé n'existe pas dans le systeme !!!! ");
            ajouterOperation();
        }
    }

    public static List<Model.CompteCourant>  afficheListCourant() throws Exception {
        CompteImp compteCourant = new CompteImp();
        Optional<List<CompteCourant>> clientListOptional = compteCourant.afficheListCourant() ;
        if (clientListOptional.isPresent()) {
            List<Model.CompteCourant> comptesList = clientListOptional.get();
            List<Model.CompteCourant> processedCourants = new ArrayList<>();
            if (!comptesList.isEmpty()) {
                for (Model.CompteCourant compteCourant1 : comptesList) {
                    System.out.println("numero : " + compteCourant1.getNumero() + "---------client : " + compteCourant1.getClient().getNom()+compteCourant1.getClient().getPrenom() + "---------employe matricule : " + compteCourant1.getEmploye().getMatricule() + "----------decouvert : " + compteCourant1.getDecouvert());
                    processedCourants.add(compteCourant1);
                }
                return processedCourants;
            } else {
                System.out.println("La liste de comptes est vide.");
                return processedCourants;
            }
        } else {
            System.out.println("La liste de comptes est indisponible.");
            return Collections.emptyList();
        }


    }

}
