package Service;

import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.MissionImp;

import java.util.*;

public class Mission {

    public static void ajouter() throws Exception {
        Scanner scanner2 = new Scanner(System.in);
        Model.Mission mission = new Model.Mission();
        MissionImp missionimp = new MissionImp();
        System.out.println("Veuillez saisir les informations du mission :");
        System.out.print("Code : ");
        String code = scanner2.nextLine();
        System.out.print("nom : ");
        String nom = scanner2.nextLine();
        System.out.print("description : ");
        String description = scanner2.nextLine();
        mission.setCode(code);
        mission.setNom(nom);
        mission.setDescription(description);
        Optional<Model.Mission> mission1 = missionimp.ajouter(mission);
        mission1.ifPresent(e -> {
            System.out.println("la mission a été ajouté avec succès !");
        });
        if (!mission1.isPresent()) {
            System.out.println("Échec de l'ajout du mission.");
        }
    }
    public static List<Model.Mission> afficher() throws Exception {
            MissionImp missionImp = new MissionImp();
            Optional<List<Model.Mission>> missions = missionImp.afficheList();
            if (missions.isPresent()) {
                List<Model.Mission> missions1 = missions.get();
                List<Model.Mission> list = new ArrayList<>();
                if (!missions1.isEmpty()) {
                    for (Model.Mission mission : missions1) {
                        System.out.println("code : " + mission.getCode() + "---------nom : " + mission.getNom() + "---------description : " + mission.getDescription() );
                        list.add(mission);
                    }
                    return list;
                } else {
                    System.out.println("La liste des missions est vide.");
                    return list;
                }
            } else {
                System.out.println("La liste des missions est indisponible.");
                return Collections.emptyList();
            }


}
    public static void chercherPourSupprimer() throws Exception {
        MissionImp missionImp = new MissionImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter le code");
        String code = scanner.nextLine();
        Optional<Model.Mission> optionalMission = missionImp.chercher(code);
        if (optionalMission.isPresent()) {
            Model.Mission mission = optionalMission.get();
            missionImp.supprimer(mission);
            System.out.println("Mission supprimer avec success");
        } else {
            System.out.println("Mission non trouvé pour le code : " + code);
        }
    }
}