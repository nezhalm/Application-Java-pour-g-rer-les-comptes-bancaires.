package Service;

import Dao.DaoImplementation.EmployeImp;

import java.time.LocalDate;
import java.util.*;

public class Employe {


    public static void ajouterEmploye() throws Exception {
        Scanner scanner2 = new Scanner(System.in);
        Model.Employe employe = new Model.Employe();
        EmployeImp employeImp = new EmployeImp();
        System.out.println("Veuillez saisir les informations de l'employé :");
        System.out.print("Code : ");
        String code = scanner2.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner2.nextLine();
        System.out.print("Nom : ");
        String nom = scanner2.nextLine();
        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
        String dateNaissanceStr = scanner2.nextLine();
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);
        System.out.print("Numéro de téléphone : ");
        String numeroTelephone = scanner2.nextLine();
        System.out.print("Email : ");
        String email = scanner2.nextLine();
        System.out.print("Date de recrutement (AAAA-MM-JJ) : ");
        String dateRecrutementStr = scanner2.nextLine();
        LocalDate dateRecrutement = LocalDate.parse(dateRecrutementStr);
        employe.setMatricule(code);
        employe.setPrenom(prenom);
        employe.setNom(nom);
        employe.setDateNaissance(dateNaissance);
        employe.setTelephone(numeroTelephone);
        employe.setDateRecrutement(dateRecrutement);
        employe.setEmail(email);
        Optional<Model.Employe> employe1 = employeImp.ajouter(employe);
        employe1.ifPresent(e -> {
            System.out.println("L'employé a été ajouté avec succès !");
        });
        if (!employe1.isPresent()) {
            System.out.println("Échec de l'ajout de l'employé.");
        }

    }


    public static List<Model.Employe> AllEmployes() throws Exception {
        EmployeImp employeImp = new EmployeImp();
        Optional<List<Model.Employe>> clientListOptional = employeImp.afficheList();
        if (clientListOptional.isPresent()) {
            List<Model.Employe> employeLists = clientListOptional.get();
            List<Model.Employe> processedEmployes = new ArrayList<>();
            if (!employeLists.isEmpty()) {
                for (Model.Employe employe : employeLists) {
                    System.out.println("code : " + employe.getMatricule() + "---------nom : " + employe.getNom() + "---------prenom : " + employe.getPrenom() + "----------telephone : " + employe.getTelephone());
                    processedEmployes.add(employe);
                }
                return processedEmployes;
            } else {
                System.out.println("La liste de clients est vide.");
                return processedEmployes;
            }
        } else {
            System.out.println("La liste de clients est indisponible.");
            return Collections.emptyList();
        }
    }
        public static void chercherPourSupprimer() throws Exception {
            EmployeImp employeImp1 = new EmployeImp();
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter le matricule");
            String code = scanner.nextLine();
            Optional<Model.Employe> employe = employeImp1.chercher(code);
            if (employe.isPresent()) {
                Model.Employe employe1 = employe.get();
                employeImp1.supprimer(employe1);
                System.out.println("employe supprimer avec success");
            } else {
                System.out.println("employe non trouvé pour le le matricule : " + code);
            }
        }


        public static void chercher() throws Exception {
            EmployeImp employe = new EmployeImp();
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter le matricule");
            String code = scanner.nextLine();
            Optional<Model.Employe> employeTrouve = employe.chercher(code);
            if (employeTrouve.isPresent()) {
                Model.Employe employe1 = employeTrouve.get();
                System.out.println("Employe trouvé : " + employe1.getNom() + " " + employe1.getPrenom());
            } else {
                System.out.println("Employe non trouvé pour le matricule : " + code);
            }


        }


    }

