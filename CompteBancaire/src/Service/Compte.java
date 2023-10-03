package Service;

import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.CompteCourantImp;
import Dao.DaoImplementation.CompteEpargneImp;
import Dao.DaoImplementation.OperationImp;
import Model.Client;
import Model.CompteCourant;
import Model.CompteEpargne;
import Model.Employe;
import Model.Etat;

import java.time.LocalDate;
import java.util.*;


public class Compte {

    public static void ajouter() throws Exception {
        Scanner scanner2 = new Scanner(System.in);
        OperationImp operationImp = new OperationImp();
        CompteCourantImp compteImp = new CompteCourantImp();
        CompteEpargneImp compteep = new CompteEpargneImp();

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
                    String codeUnique =   CompteCourantImp.genererCodeUnique(5);
                    System.out.print("Entrez le solde : ");
                    Double soldeCourant = scanner4.nextDouble();
                    LocalDate dateActuelle = LocalDate.now();
                    System.out.print("Entrez le découvert : ");
                    double decouvertCourant = scanner4.nextInt();

                   Etat etat = Etat.ACTIVE;


                   CompteCourant compte = new CompteCourant(soldeCourant, codeUnique, dateActuelle, etat, ifexiste.get(), clientt.get(), decouvertCourant);
                   Optional<Model.CompteCourant> compte1 = compteImp.ajouter(compte);
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
                    String codeUnique2 =   CompteCourantImp.genererCodeUnique(5);
                    System.out.print("Entrez le solde : ");
                    Double soldeepargne = scanner5.nextDouble();
                    LocalDate dateActuellee = LocalDate.now();
                    System.out.print("Entrez le taux d'interet : ");
                    double taux = scanner5.nextInt();
                    Etat etat2 = Etat.ACTIVE;
                    CompteEpargne compte2 = new CompteEpargne(soldeepargne, codeUnique2, dateActuellee, etat2, ifexiste.get(), client2.get(), taux);
                    Optional<Model.CompteEpargne> compteRes = compteep.ajouter(compte2);
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
        }
    }

    public static List<Model.CompteCourant>  afficheListCourant() throws Exception {
        CompteCourantImp compteCourant = new CompteCourantImp();

        Optional<List<Model.CompteCourant>> clientListOptional = compteCourant.afficheList() ;

        if (clientListOptional.isPresent()  )  {
            List<Model.CompteCourant> comptesList = clientListOptional.get();
            List<Model.CompteCourant> processedCourants = new ArrayList<>();

            if (!comptesList.isEmpty() ) {
                System.out.println("La liste de comptes courants.");
                for (Model.CompteCourant compteCourant1 : comptesList) {
                    System.out.println("numero : " + compteCourant1.getNumero() + "---------client : " + compteCourant1.getClient().getNom()+compteCourant1.getClient().getPrenom() + "---------employe matricule : " + compteCourant1.getEmploye().getMatricule() + "----------decouvert : " + compteCourant1.getDecouvert() + "----------status : " + compteCourant1.getEtat());
                    processedCourants.add((CompteCourant) compteCourant1);
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
    public static List<Model.CompteEpargne>  afficheListEpagne() throws Exception {
        CompteEpargneImp compteEgarne = new CompteEpargneImp();

        Optional<List<Model.CompteEpargne>> clientListOptional = compteEgarne.afficheList() ;

        if (clientListOptional.isPresent()  )  {
            List<Model.CompteEpargne> comptesList = clientListOptional.get();
            List<Model.CompteEpargne> processedCourants = new ArrayList<>();

            if (!comptesList.isEmpty() ) {
                System.out.println("La liste de comptes Epagrnes.");
                for (Model.CompteEpargne compteCourant1 : comptesList) {
                    System.out.println("numero : " + compteCourant1.getNumero() + "---------client : " + compteCourant1.getClient().getNom()+compteCourant1.getClient().getPrenom() + "---------employe matricule : " + compteCourant1.getEmploye().getMatricule() + "----------taux interet : " + compteCourant1.getTauxInterer());
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
    public static void chercherCompteParClient() throws Exception {
        CompteCourantImp compte = new CompteCourantImp();
        ClientImp client = new ClientImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter le code du client");
        String code = scanner.nextLine();
        Optional<Model.Client> clientTrouve = client.chercher(code);

        if (clientTrouve.isPresent()) {
            Model.Client clients = clientTrouve.get();
            System.out.println("Client trouvé : " + clients.getNom() + " " + clients.getPrenom());
        } else {
            System.out.println("Client non trouvé pour le code : " + code);
        }

        Optional<List<Model.Compte>> comptes = compte.chercherParClient(clientTrouve);

        if (comptes.isPresent()) {
            System.out.println("les comptes de ce client : ");

            List<Model.Compte> comptesList = comptes.get();
            for (Model.Compte compte1 : comptesList) {
                if (compte1 instanceof CompteCourant) {
                    CompteCourant compteCourant = (CompteCourant) compte1;
                    System.out.println("numero : " + compteCourant.getNumero() + ", solde en DH: "+ compteCourant.getSolde()+" DH , type: "+compteCourant.getAccountType1()+" account");
                } else if (compte1 instanceof CompteEpargne) {
                    CompteEpargne compteEpargne = (CompteEpargne) compte1;
                    System.out.println("numero : " + compteEpargne.getNumero() + ", solde en DH: "+ compteEpargne.getSolde()+" DH , type: "+compteEpargne.getAccountType1()+" account");
                }else {
            System.out.println("Aucun compte trouvé pour le code du client : " + code);
        }
     }



     }
    }


    public static void chercherPourSupprimer() throws Exception {
        CompteCourantImp compteC = new CompteCourantImp();
        CompteEpargneImp compteE = new CompteEpargneImp();

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter le numero du compte que vous voulez supprimer");
        String code = scanner.nextLine();
        Optional<Model.CompteCourant> compteCourant = compteC.chercher(code);
        Optional<Model.CompteEpargne> compteEpargne = compteE.chercher(code);
        if (compteCourant.isPresent() || compteEpargne.isPresent()) {
            if (compteCourant.isPresent()) {
                Model.CompteCourant compteCourant1 = compteCourant.get();
                compteC.supprimer(compteCourant1);
                System.out.println("Compte courant supprimé avec succès.");
            }
            if (compteEpargne.isPresent()) {
                Model.CompteEpargne compteEpargne1 = compteEpargne.get();
                compteE.supprimer(compteEpargne1);
                System.out.println("Compte épargne supprimé avec succès.");
            }
        } else {
            System.out.println("Compte non trouvé pour le code : " + code);
        }
    }
    public static void changeStatus() throws Exception {
        Scanner scanner = new Scanner(System.in);

        CompteCourantImp compteC = new CompteCourantImp();
        CompteEpargneImp compteE = new CompteEpargneImp();

        Scanner scanner4 = new Scanner(System.in);
        System.out.print("Entrez le code de compte que vous voulez changer leur status : ");
        String code = scanner4.next();

        ClientImp clientImp1 = new ClientImp();

        Optional<Model.CompteCourant> compteCourant = compteC.chercher(code);
        Optional<Model.CompteEpargne> compteEpargne = compteE.chercher(code);

        if (compteCourant.isPresent() || compteEpargne.isPresent()) {
            String nouveauStatut;
            if (compteCourant.isPresent()) {
                Model.CompteCourant compteCourant1 = compteCourant.get();

                // Afficher le statut actuel
                System.out.println("Statut actuel du compte courant : " + compteCourant1.getEtat());

                    System.out.print("Choisir l'etat : ");
                      System.out.println("Choisir l'état :");
                     System.out.println("1 - ACTIVE");
                    System.out.println("2 - FROZEN");
                     System.out.println("3 - CLOSED");
                   int choixx = scanner.nextInt();
                Etat etat = null;
                switch (choixx) {
                        case 1:
                      etat = Etat.ACTIVE;
                      break;
                  case 2:
                      etat = Etat.FROZEN;
                      break;
                  case 3:
                      etat = Etat.CLOSED;
                      break;
                  default:
                      System.out.println("Choix invalide.");
                        break;
                 }

                // Mettre à jour le statut du compte courant
                Optional<CompteCourant> test1 = compteC.changerStatus(compteCourant1, etat);
                if (test1.isPresent()) {
                    System.out.println("Statut du compte courant mis à jour avec succès.");
                } else {
                    System.out.println("Statut du compte courant non mis à jour.");
                }

            }

            if (compteEpargne.isPresent()) {
                Model.CompteEpargne compteEpargne1 = compteEpargne.get();

                // Afficher le statut actuel
                System.out.println("Statut actuel du compte épargne : " + compteEpargne1.getEtat());

                // Demander à l'utilisateur de saisir le nouveau statut
                System.out.print("Choisir l'etat : ");
                System.out.println("Choisir l'état :");
                System.out.println("1 - ACTIVE");
                System.out.println("2 - FROZEN");
                System.out.println("3 - CLOSED");
                int choixx = scanner.nextInt();
                Etat etat = null;
                switch (choixx) {
                    case 1:
                        etat = Etat.ACTIVE;
                        break;
                    case 2:
                        etat = Etat.FROZEN;
                        break;
                    case 3:
                        etat = Etat.CLOSED;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                             break;
                }


                // Mettre à jour le statut du compte épargne
            Optional<CompteEpargne>  test2 =  compteE.changerStatus(compteEpargne1, etat);
                if (test2.isPresent()) {
                    System.out.println("Statut du compte courant mis à jour avec succès.");
                } else {
                    System.out.println("Statut du compte courant non mis à jour.");
                }            }
        } else {
            System.out.println("Compte non trouvé pour le code : " + code);
        }
    }


    public static List<Model.CompteCourant> afficheListCourantParStatut(Etat statut) throws Exception {
        CompteCourantImp compteCourant = new CompteCourantImp();
        Optional<List<Model.CompteCourant>> clientListOptional = compteCourant.afficheList();

        if (clientListOptional.isPresent()) {
            List<Model.CompteCourant> comptesList = clientListOptional.get();
            List<Model.CompteCourant> processedCourants = new ArrayList<>();

            if (!comptesList.isEmpty()) {
                System.out.println("La liste de comptes courants pour le statut : " + statut);
                for (Model.CompteCourant compteCourant1 : comptesList) {
                    if (compteCourant1.getEtat() == statut) {
                        System.out.println("numero : " + compteCourant1.getNumero() + "---------client : " + compteCourant1.getClient().getNom() + compteCourant1.getClient().getPrenom() + "---------employe matricule : " + compteCourant1.getEmploye().getMatricule() + "----------decouvert : " + compteCourant1.getDecouvert() + "----------status : " + compteCourant1.getEtat());
                        processedCourants.add(compteCourant1);
                    }
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

    // Exemple d'utilisation

    public static List<CompteEpargne> afficheListEpargneParStatut(Etat statut) throws Exception {
        CompteEpargneImp compteEpargneImp = new CompteEpargneImp();
        Optional<List<Model.CompteEpargne>> clientListOptional = compteEpargneImp.afficheList();

        if (clientListOptional.isPresent()) {
            List<Model.CompteEpargne> comptesList = clientListOptional.get();
            List<Model.CompteEpargne> processedCourants = new ArrayList<>();

            if (!comptesList.isEmpty()) {
                System.out.println("La liste de comptes epargnes pour le statut : " + statut);
                for (Model.CompteEpargne compteCourant1 : comptesList) {
                    if (compteCourant1.getEtat() == statut) {
                        System.out.println("numero : " + compteCourant1.getNumero() + "---------client : " + compteCourant1.getClient().getNom() + compteCourant1.getClient().getPrenom() + "---------employe matricule : " + compteCourant1.getEmploye().getMatricule() + "----------taux d'interet : " + compteCourant1.getTauxInterer() + "----------status : " + compteCourant1.getEtat());
                        processedCourants.add(compteCourant1);
                    }
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