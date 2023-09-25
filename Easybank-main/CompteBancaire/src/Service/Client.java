package Service;
import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.EmployeImp;
import Dao.DaoImplementation.OperationImp;
import Model.Employe;
import Model.Operation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static Service.Operation.ajouterOperation;

public class Client {
    public static List<Model.Client> AllClients() throws SQLException {
        ClientImp clients = new ClientImp();
        Optional<List<Model.Client>> clientListOptional = clients.afficheList();
        if (clientListOptional.isPresent()) {
            List<Model.Client> clientsList = clientListOptional.get();
            List<Model.Client> processedClients = new ArrayList<>();
            if (!clientsList.isEmpty()) {
                for (Model.Client client : clientsList) {
                    System.out.println("code : " + client.getCode() + "---------nom : " + client.getNom() + "---------prenom : " + client.getPrenom() + "----------telephone : " + client.getTelephone());
                    processedClients.add(client);
                }
                return processedClients;
            } else {
                System.out.println("La liste de clients est vide.");
                return processedClients;
            }
        } else {
            System.out.println("La liste de clients est indisponible.");
            return Collections.emptyList();
        }

    }





    public static void chercher() throws Exception {
        ClientImp client = new ClientImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter le code");
        String code = scanner.nextLine();
        Optional<Model.Client> clientTrouve = client.chercher(code);
        if (clientTrouve.isPresent()) {
            Model.Client clients = clientTrouve.get();
            System.out.println("Client trouvé : " + clients.getNom() + " " + clients.getPrenom());
        } else {
            System.out.println("Client non trouvé pour le code : " + code);
        }


    }



    public static void ajouter() throws Exception {
        Scanner scanner2 = new Scanner(System.in);
        Model.Client client = new Model.Client();
        OperationImp operationImp = new OperationImp();
        ClientImp clientImp = new ClientImp();
        System.out.println("Veuillez saisir les informations de l'operation :");
        System.out.print("entrer le matricule de l'employe ");
        String matricule = scanner2.nextLine();
        Optional<Employe> ifexiste = operationImp.employeExiste(matricule);
        if (ifexiste.isPresent()) {
                Scanner scanner3 = new Scanner(System.in);
                System.out.println("L'employé existe et valide");
                System.out.println("Veuillez saisir les informations du compte :");
                System.out.print("code : ");
                String code = scanner3.nextLine();
                System.out.print("nom : ");
                String nom = scanner3.nextLine();
                System.out.print("prénom  : ");
                String prenom = scanner3.nextLine();
                System.out.print("téléphone : ");
                String numeroTelephone = scanner3.nextLine();
                System.out.print("adresse : ");
                String adresse = scanner3.nextLine();
                System.out.print(" date de naissance  : ");
                String date = scanner3.nextLine();
                LocalDate dateNaissance = LocalDate.parse(date);

                client.setCode(code);
                client.setPrenom(prenom);
                client.setAdresse(adresse);
                client.setNom(nom);
                client.setDateNaissance(dateNaissance);
                client.setTelephone(numeroTelephone);

               Optional<Model.Client> client1 =clientImp.ajouterClient(client,ifexiste) ;
                client1.ifPresent(e -> {
                    System.out.println("L'employé a été ajouté avec succès !");
                });
                if (!client1.isPresent()) {
                    System.out.println("Échec de l'ajout de l'employé.");
                }

            } else {
                System.out.println("L'employé n'existe pas dans le systeme !!!! ");
                ajouterOperation();

            }





    }

    public static void chercherPourSupprimer() throws Exception {
        ClientImp client = new ClientImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter le code");
        String code = scanner.nextLine();
        Optional<Model.Client> clientTrouve = client.chercher(code);
        if (clientTrouve.isPresent()) {
            Model.Client clients = clientTrouve.get();
           client.supprimer(clients);
           System.out.println("client supprimer avec success");
    } else {
            System.out.println("Client non trouvé pour le code : " + code);
        }
    }
    }

