/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.models;

/**
 *
 * @author infoevo
 */
public class rating {
     private String nom;
      private String rating;

    public rating(String nom, String rating) {
        this.nom = nom;
        this.rating = rating;
    }

    public rating() {
    }

    public String getNom() {
        return nom;
    }

    public String getRating() {
        return rating;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "rating{" + "nom=" + nom + ", rating=" + rating + '}';
    }
      
      
    
}

