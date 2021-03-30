package tests;

import entities.Abonnement;
import entities.Reclamation;
import services.AbonnementCRUD;
import services.ReclamationCRUD;
import java.util.Date;

public class ClassMain {
    public static void main(String[] args) {
        Abonnement abonnement = new Abonnement(26,"Annuelle", new Date(),new Date(),1);
        //System.out.println(abonnement.toString());
        AbonnementCRUD abonnementCRUD = new AbonnementCRUD();
        abonnementCRUD.ajouterAbonnement(abonnement);
        //abonnement.setValidite(500);
        //abonnementCRUD.updateAbonnement(abonnement);
        //abonnementCRUD.supprimerAbonnement(26);
        //System.out.println(abonnementCRUD.displayAbonnements());
        //System.out.println(abonnementCRUD.TrierParValidite());
         //abonnement = abonnementCRUD.FindAbonnementById(100);
        //System.out.println(abonnement);
        Reclamation reclamation = new Reclamation(4,"Reclamation Test up to date","Test");
        ReclamationCRUD reclamationCRUD = new ReclamationCRUD();
        //reclamationCRUD.ajouterReclamation(reclamation);
        //reclamationCRUD.updateReclamation(reclamation);
        //reclamationCRUD.supprimerReclamation(reclamation.getId());
        //System.out.println(reclamationCRUD.displayReclamations());
        //System.out.println(reclamationCRUD.TrierParTitre());
        //reclamation = reclamationCRUD.FindReclamationById(3);
        //System.out.println("//////////"+ reclamation);

    }
}
