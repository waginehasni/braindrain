/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.services;

import com.pidev.models.fos_user;
import com.pidev.tests.main;
import com.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WSI
 */
public class ServiceUser {
    private Statement st;
    private ResultSet rs;
    Connection cnx = DataSource.getInstance().getCnx();
    
    public ServiceUser()
    {
           DataSource cs= DataSource.getInstance();
        try {
            st= cs.getCnx().createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
public void AjouterUser(fos_user u) {
        try {
            String req = "INSERT INTO fos_user (username,email,password,roles,telephone) VALUES (?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            //st.setString(6, u.getSalt());
            st.setString(3, md5(u.getPassword()));
            //st.setDate(8, u.getLast_login());
            //st.setDate(10, u.getPassword_requested_at());
            st.setString(4, u.getRoles());
            st.setString(5, u.getTelephone());
            st.executeUpdate();
            System.out.println("user ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModiferUser(int id, fos_user u) {
        try {
            System.out.println("-------"+u.getUsername());
            String req = "UPDATE fos_user SET username = ?, email= ?, password=?, roles=? , telephone=? " 
                    + " WHERE id = " + id + ";";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setString(3, md5(u.getPassword())+"{"+u.getUsername()+"}");
            st.setString(4, u.getRoles());
            st.setString(5, u.getTelephone());
           

            st.executeUpdate();
            System.out.println("user modifié !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    

 public void SupprimerUser(int id) {
        try {
            String req = "DELETE FROM fos_user WHERE fos_user.`id` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("user supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public String  validerLogin(String username, String password  ) throws SQLException{
      String k= null;
               String requete = "select count(*) as total from fos_user where username=? and password=? ";
           PreparedStatement st = cnx.prepareStatement(requete);
             st.setString(1,username );
              st.setString(2,md5(password ));
                  
             ResultSet rs=st.executeQuery();
             rs.next();
             int l1=rs.getInt("total");
             System.out.println("nbr" +l1);
             rs.close();
      
          if (l1!=1){
              k= "utilisateur n'existe pas ";
          }
            else  k=  "utilisateur correct ";
         
         return k;
       
  }

//  public Boolean Login(String username, String password) {
//
//        try {
//            st = cnx.createStatement();
//            ResultSet rs = st.executeQuery("Select * from fos_user WHERE fos_user.`username` = '" + username + "'and  fos_user.`password` like '"+md5(password)+"%'");
//            
//            if (rs.next()) {
//                System.out.println("login success");
//                return true;
//            }
//            
//
//            st.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return false;
//    }

  
   public void registerUser(fos_user u) {
        try {
            String req = "INSERT INTO fos_user (username,email,password,roles,telephone) VALUES (?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setString(3, md5(u.getPassword()));
            st.setString(4, u.getRoles());
            st.setString(5, u.getTelephone());
            st.executeUpdate();
            System.out.println("User Ajouté !!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
  
  
   
//   public fos_user AfficherUserId(int id) {
// ArrayList<fos_user> listN = new ArrayList<fos_user>();
// fos_user u = new fos_user();
//        try {
//            st = cnx.createStatement();
//            ResultSet rs = st.executeQuery("Select * from fos_user where fos_user.`id`='"+id+"'");
//            while (rs.next()) {
//               
//                        u.setId(rs.getInt("id"));
//                        u.setUsername(rs.getString("username"));
//                        u.setEmail(rs.getString("email"));
//                        u.setPassword(rs.getString("password"));
//                     
//                        u.setRoles(rs.getString("roles"));
//            }
//            st.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(fos_user.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return u;
//    }
   public List<fos_user> listuserid() {
        List<fos_user> userList = new ArrayList<>();
        try {
            String requete = "select * from fos_user";
             st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                fos_user a = new fos_user();
                a.setId(rs.getInt(1));
                a.setUsername(rs.getString(2));
                a.setEmail(rs.getString(3));
                a.setPassword(rs.getString(4));
                
                a.setRoles(rs.getString(5));
                 a.setTelephone(rs.getString(6));
                userList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }
   
   
   public List<fos_user> getAllUser() {
        ArrayList<fos_user> listN = new ArrayList<fos_user>();
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from fos_user");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                listN.add(new fos_user(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                  
                        rs.getString("roles"),
                        rs.getString("telephone")
                ));
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(fos_user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listN;
    }
   
    
 

    public List<fos_user> AfficherUser(String username) {
        List<fos_user> listu = new ArrayList<>();
 
        try {
            String requete = "Select * from fos_user where fos_user.`username`='"+username+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) 
            {
                 fos_user u = new fos_user(); 
                        u.setUsername(rs.getString("username"));
                        u.setEmail(rs.getString("email"));
                        u.setPassword(rs.getString("password"));
                     
                        u.setRoles(rs.getString("roles"));
                        u.setTelephone(rs.getString("telephone"));
                        listu.add(u);
            }
        } catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
        }

        return listu;
    }
    public List<fos_user> TrierParId() {
        List<fos_user> list = new ArrayList<>();
        try {
           String requete ="select * from fos_user ORDER BY id DESC";
        PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet res = pst.executeQuery();
        fos_user com = null;
        while (res.next()) {
            com = new fos_user(res.getInt(1),res.getString(2),res.getString(3),  res.getString(4), res.getString(5),res.getString(6));
            list.add(com);
            
        }} catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        return list;
    }
    public String md5(String md5) {
   try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(md5.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
}

    public void Login(fos_user fos_user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


   
   

