/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pi.test;

import esprit.pi.entities.Offres;
import esprit.pi.entities.Sponsors;
import esprit.pi.services.OffresCRUD;
import esprit.pi.services.SponsorsCRUD;

/**
 *
 * @author asus
 */
public class MainClass {
     public static void main(String[] args) {
        OffresCRUD ocd = new OffresCRUD();
//        Offres of1 = new Offres (12,"haja hbel","produit alimentaire","ijaa nefrah bik");
//        ocd.ajouterOffres(of1);
        //ocd.updateOffres(of1);
         //System.out.println(ocd.displayOffres());
        // ocd.supprimerOffres("ah");
//        ocd.listOffresid(of1);
//         
//          Offres of2 = new Offres ("dawakh"," vetement","ijaa nefrah bik");
//          Offres of3 = new Offres ("wow","produit alimentaire","ijaa nefrah bik");
//          Offres of4 = new Offres ("ah","alimentation","ijaa nefrah bik");
//          Offres of5 = new Offres ("babab hbel","","ijaa nefrah bik");
//        Offres of6 = new Offres (26,"tttttt","dcfcc","kkkkkk");
//        ocd.updateOffres(of6, 26);
////        System.out.println(ocd.nbOffresTotal());
//        
//          ocd.ajouterOffres(of2);
//          ocd.ajouterOffres(of3);
//          ocd.ajouterOffres(of4);
//          ocd.ajouterOffres(of5);

//ocd.TrierParId().forEach(e->{System.out.println(e);});
         //  ocd.RechercherParType("alimentation").forEach(e->{System.out.println(e);});

     
          
        
//       SponsorsCRUD scd = new SponsorsCRUD();
//       Sponsors sss = new Sponsors (1," delice",12,29654321);
//      // scd.ajouterSponsors(sss);
//      //scd.supprimerSponsors(sss);
//     
//       Sponsors ss2 = new Sponsors (2," vitalait",5,5668461);
//        Sponsors ss3 = new Sponsors (3," vitalait",4,58164610);
//         Sponsors ss4 = new Sponsors (4," vitalait",15,55164645);
//      scd.ajouterSponsors(sss);
//       scd.ajouterSponsors(ss2);
//       scd.ajouterSponsors(ss3);
//       scd.ajouterSponsors(ss4);
//     
//Sponsors ss5 = new Sponsors (5," vitalait",15,55164645);
//scd.ajouterSponsors(ss5);
////       
     // scd.TrierParBudget().forEach(e->{System.out.println(e);});
System.out.println(ocd.RechercherParType("bbb"));
    }
    
}
