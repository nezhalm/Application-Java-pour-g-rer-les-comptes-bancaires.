package Dao;

import Model.Client;
import Model.Compte;
import Model.Employe;
import Model.CompteCourant;
import Model.Etat;

import java.util.List;
import java.util.Optional;

public interface CompteCourantDao extends GlobaleDao<CompteCourant> {
    public Optional<CompteCourant> changerStatus(CompteCourant compteCourant, Etat etat) throws Exception ;
    public Optional<List<Compte>> chercherParClient(Optional<Client> client) throws Exception;

    public  Optional<CompteCourant> retrait(CompteCourant compteCourant, Employe employe, Double montant, String typeOperation) throws Exception ;


    public  Optional<CompteCourant> versement(CompteCourant compteCourant, Employe employe , Double montant, String typeOperation) throws Exception ;
}