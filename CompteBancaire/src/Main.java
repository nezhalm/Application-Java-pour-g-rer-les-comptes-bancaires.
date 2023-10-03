import ConnexionBaseDonnes.Connexion;
import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.EmployeImp;
import Model.Client;
import Model.CompteCourant;
import Model.Employe;
import Model.Etat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static  void main(String[] args) throws Exception {
   //     Service.Client.chercher();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:

                    System.out.println("1 - ajouter client");
                    System.out.println("2 - supprimer client");
                    System.out.println("3 - chercher client par code");
                    System.out.println("4 - afficher tous les clients");
                    int sousChoix = scanner.nextInt();

                    switch (sousChoix) {
                        case 1:
                            Service.Client.ajouter();

                            break;
                        case 2:
                            Service.Client.chercherPourSupprimer();
                            break;
                        case 3:
                           Service.Client.chercher();
                            break;
                        case 4:
                            Service.Client.AllClients();
                            break;
                        default:
                            System.out.println("Option non valide");
                    }

                    break;
                case 2:
                    System.out.println("1 - ajouter employe");
                    System.out.println("2 - supprimer employe");
                    System.out.println("3 - chercher employe par matricule");
                    System.out.println("4 - afficher tous les employes");
                    int sousChoix2 = scanner.nextInt();

                    switch (sousChoix2) {
                        case 1:
                            Service.Employe.ajouterEmploye();
                            break;
                        case 2:
                            Service.Employe.chercherPourSupprimer();
                            break;
                        case 3:
                            Service.Employe.chercher();
                            break;
                        case 4:
                            Service.Employe.AllEmployes();
                            break;
                        default:
                            System.out.println("Option non valide");
                    }


                    break;
                case 3:
                    System.out.println("1 - ajouter compte");
                    System.out.println("2 - supprimer compte");
                    System.out.println("3 - chercher compte par client");
                    System.out.println("4 - afficher tous les comptes");
                    System.out.println("5 - changer le status d'un compte ");
                    System.out.println("6 - afficher la liste des comptes par status ");

                    int sousChoix3 = scanner.nextInt();

                    switch (sousChoix3) {
                        case 1:
                            Service.Compte.ajouter();
                            break;
                        case 2:
                            Service.Compte.chercherPourSupprimer();
                            break;
                        case 3:
                            Service.Compte.chercherCompteParClient();
                            break;
                        case 4:
                            Service.Compte.afficheListCourant();
                            Service.Compte.afficheListEpagne();
                            break;
                        case 5:
                            Service.Compte.changeStatus();
                            break;
                        case 6:
                            System.out.println("-----------------les comptes courants---------------------");

                            Service.Compte.afficheListCourantParStatut(Etat.ACTIVE);
                            Service.Compte.afficheListCourantParStatut(Etat.FROZEN);
                            Service.Compte.afficheListCourantParStatut(Etat.CLOSED);
                            System.out.println("-----------------les comptes epargnes---------------------");
                            Service.Compte.afficheListEpargneParStatut(Etat.ACTIVE);
                            Service.Compte.afficheListEpargneParStatut(Etat.FROZEN);
                            Service.Compte.afficheListEpargneParStatut(Etat.CLOSED);
                            break;
                        default:
                            System.out.println("Option non valide");
                    }

                    break;
                case 4:
                    System.out.println("mission");
                    System.out.println("1 - ajouter mission");
                    System.out.println("2 - supprimer mission");
                    System.out.println("3 - afficher la liste des missions");
                    int sousChoix4 = scanner.nextInt();

                    switch (sousChoix4) {
                        case 1:
                            Service.Mission.ajouter();

                            break;
                        case 2:
                            Service.Mission.chercherPourSupprimer();


                            break;
                        case 3:
                            Service.Mission.afficher();

                            break;

                        default:
                            System.out.println("Option non valide");
                    }

                    break;


                case 5:
                    System.out.println("operations");
                    System.out.println("1 - ajouter operation");
                    System.out.println("2 - supprimer opereration par numero");
                    System.out.println("3 - chercher operation par numero");
                    int sousChoix5 = scanner.nextInt();

                    switch (sousChoix5) {
                        case 1:
                            Service.Operation.Operation();

                            break;
                        case 2:
                            Service.Operation.chercherPourSupprimer();
                            break;
                        case 3:
                            Service.Operation.chercher();

                            break;

                        default:
                            System.out.println("Option non valide");
                            break;
                    }
                default:
                    System.out.println("Choix invalide, veuillez entrer un numéro valide.");
                    break;
            }
        } while (choix != 4);

       scanner.close();
    }

    public static void afficherMenu() {
        System.out.println("Menu Principal :");
        System.out.println("1. Administrer les clients");
        System.out.println("2. Administrer les employés");
        System.out.println("3. Administrer les comptes");
        System.out.println("4. Administrer les missions");
        System.out.println("5. Administrer les operations");
        System.out.println("6. Quitter");
        System.out.print("Choisissez une option : ");
    }


    }


