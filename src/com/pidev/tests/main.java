/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.tests;
import com.pidev.services.ServiceUser;
import com.pidev.models.*;
import com.pidev.services.*;
import com.pidev.models.fos_user;
import java.sql.Date;
import java.time.LocalDateTime;
import com.pidev.services.Service_Evenement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author benha
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   
    
    //ServiceUser suser = new ServiceUser();
   //suser.AjouterUser(new fos_user( "firas", "wagine.hasni@esprit.tn", "1233", "admin"));
        //System.out.println(suser.listuserid());
   
// suser.SupprimerUser(16);
        
             // suser.ModiferUser(17, new fos_user("wagine", "wagine@esprit.tn", "abc", "coach"));
           // suser.getAllUser().forEach(System.out::println);
            //System.out.println("user id "+ suser.AfficherUserId(17));
            
            //suser.Login("wajine", "125");
            
            //  suser.registerUser(new fos_user("wajjsin", "llslsl@lld.com", "1233", "123"));
            //suser.TrierParId().forEach(e->{System.out.println(e);});
//  String str = "2020-03-30";
//  Date d1 = Date.valueOf(str);
//   String str1 = "2020-04-30";
//  Date d2 = Date.valueOf(str1);
  Service_Evenement evenement1 = new Service_Evenement();
//
// 
//    evenement e1  = new evenement (33,43,"football",d1,d2,"1h");

//evenement1.Ajouterevent(e1);
// event.ModiferEvenement(14, new evenement(1,58, "wagine@esprit.tn", d1,d2, "coach"));
//evenement1.getAllEvenement().forEach(e->System.out.println(e));

//evenement1.SupprimerEvenement(33);
evenement1.trierevenementDateDebut().forEach(e->System.out.println(e));

        
           
        

    
}}
