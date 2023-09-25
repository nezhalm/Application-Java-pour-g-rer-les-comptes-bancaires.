package Service;

import Dao.DaoImplementation.EmployeImp;
import Dao.DaoImplementation.OperationImp;
import Model.Employe;
import com.sun.jdi.Value;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class Operation {

    public static void ajouterOperation() throws Exception {
        Scanner scanner2 = new Scanner(System.in);
        Model.Operation operation = new Model.Operation();
        OperationImp operationImp = new OperationImp();
        System.out.println("Veuillez saisir les informations de l'operation :");
        System.out.print("entrer le matricule de l'employe ");
        String matricule = scanner2.nextLine();
        Optional<Employe> ifexiste = operationImp.employeExiste(matricule);
        if (ifexiste.isPresent()) {
                System.out.println("L'employé existe et valide");
                System.out.println("choisir le type d'operation:");
                System.out.println("1 - versement:");
                System.out.println("2 - retrait:");
                String typeOperation = scanner2.nextLine();
                switch (typeOperation) {
                    case "1":
                        System.out.println("Vous avez choisi un versement.");
                        System.out.println("entrer le co");

                        break;
                    case "2":
                        System.out.println("Vous avez choisi un retrait.");
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez saisir 1 ou 2.");
                        break;
                }


        } else {
                System.out.println("L'employé n'existe pas dans le systeme !!!! ");
                ajouterOperation();

            }

//        String date = scanner2.nextLine();
//        System.out.print("montant : ");
//        String montant = scanner2.nextLine();
//
//        LocalDate date2 = LocalDate.parse(date);
//        operation.setNumero(numero);
//        operation.setDateCreation(date2);
//        operation.setNumero(numero);
//
//        Optional<Operation> operation1 = operationImp.ajouter(operation);
//        employe1.ifPresent(e -> {
//            System.out.println("L'employé a été ajouté avec succès !");
//        });
//        if (!employe1.isPresent()) {
//            System.out.println("Échec de l'ajout de l'employé.");
//        }

    }
}
