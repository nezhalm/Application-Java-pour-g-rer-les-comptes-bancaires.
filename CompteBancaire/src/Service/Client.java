package Service;
import Dao.DaoImplementation.ClientImp;

import java.util.*;

public class Client {
    public static List<Model.Client> AllClients() {
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
}

