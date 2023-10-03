package Service;

import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.CompteCourantImp;
import Dao.DaoImplementation.CompteEpargneImp;
import Dao.DaoImplementation.OperationImp;
import Model.Compte;
import Model.CompteCourant;
import Model.CompteEpargne;
import Model.Employe;

import java.util.Optional;
import java.util.Scanner;

public class Operation {

    public static void Operation() throws Exception {
        Scanner scanner2 = new Scanner(System.in);
        CompteCourantImp compteCourantImp = new CompteCourantImp();
        CompteEpargneImp compteEpargneImp = new CompteEpargneImp();
        OperationImp operationImp = new OperationImp();
        System.out.println("Veuillez saisir les informations de l'operation :");
        System.out.print("entrer le matricule de l'employe ");
        String matricule = scanner2.nextLine();
        Optional<Employe> ifexiste = operationImp.employeExiste(matricule);
        if (ifexiste.isPresent()) {
                System.out.println("L'employé existe et valide");
                System.out.println("choisir le type d'operation:");
                System.out.println("1 - retrait:");
                System.out.println("2 - versement:");
                String typeOperation = scanner2.nextLine();
            System.out.println("entrer le code du compte");
            String codeCompte = scanner2.nextLine();
            Optional<CompteCourant> compteCourant =compteCourantImp.chercher(codeCompte);
            Optional<CompteEpargne> compteEpargne =compteEpargneImp.chercher(codeCompte);

            switch (typeOperation) {
                    case "1":
                        if (compteCourant.isPresent() || compteEpargne.isPresent()) {
                            System.out.println("entrer le montant que vous voulez retirer");
                            Double montant = Double.valueOf((scanner2.nextLine()));


                            if (compteCourant.isPresent()) {
                                Model.CompteCourant compteCourant1 = compteCourant.get();
                             Optional<CompteCourant> courant =   compteCourantImp.retrait(compteCourant1,ifexiste.get(),montant,typeOperation);
                                System.out.println("retrait effectue avec succès");
                            }

                            if (compteEpargne.isPresent()) {
                                Model.CompteEpargne compteEpargne1 = compteEpargne.get();
                                Optional<CompteEpargne> epargne =   CompteEpargneImp.retrait(compteEpargne1,ifexiste.get(),montant,typeOperation);
                                System.out.println("retrait effectue avec succès");
                            }
                        } else {
                            System.out.println("Compte non trouvé " );
                        }
                        break;
                    case "2":
                        if (compteCourant.isPresent() || compteEpargne.isPresent()) {
                            System.out.println("entrer le montant que vous voulez verser");
                            Double montant = Double.valueOf((scanner2.nextLine()));


                            if (compteCourant.isPresent()) {
                                Model.CompteCourant compteCourant1 = compteCourant.get();
                                Optional<CompteCourant> courant =   compteCourantImp.versement(compteCourant1,ifexiste.get(),montant,typeOperation);
                                System.out.println("versement effectue avec succès.");
                            }

                            if (compteEpargne.isPresent()) {
                                Model.CompteEpargne compteEpargne1 = compteEpargne.get();
                                Optional<CompteEpargne> epargne =   compteEpargneImp.versement(compteEpargne1,ifexiste.get(),montant,typeOperation);
                                System.out.println("versement effectue avec succès.");
                            }
                        } else {
                            System.out.println("Compte non trouvé " );
                        }
                        break;
                    default:
                        System.out.println("");
                        break;
                }
        } else {
                System.out.println("L'employé n'existe pas dans le systeme !!!! ");
            }
    }

    public static void chercherPourSupprimer() throws Exception {
        OperationImp operation = new OperationImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter le code d'operation");
        String code = scanner.nextLine();
        Optional<Model.Operation> optionalOperation = operation.chercher(code);
        if (optionalOperation.isPresent()) {
            Model.Operation operation1 = optionalOperation.get();
            operation.supprimer(operation1);
            System.out.println("operation supprimer avec success");
        } else {
            System.out.println("operation non trouvé pour le code : " + code);
        }
    }

    public static void chercher() throws Exception {
        OperationImp operationImp = new OperationImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter le code d'operation");
        String code = scanner.nextLine();
        Optional<Model.Operation> optionalOperation = operationImp.chercher(code);
        if (optionalOperation.isPresent()) {
            Model.Operation operation = optionalOperation.get();
            System.out.println("numero de d'operation : " + operation.getNumero() + " numero de compte" + operation.getComptes().getNumero()+ "type :" + operation.getType());
        } else {
            System.out.println("Client non trouvé pour le code : " + code);
        }


    }

}
