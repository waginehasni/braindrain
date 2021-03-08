/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.entities;

/**
 *
 * @author dell
 */
public class Cours {
    
    private int numCours;
    private int numReservation;
    private String  nomCours;
    private String  nomCoach;
    private String  type;

    public Cours() {
    }



    public Cours(int numCours, int numReservation, String nomCours, String nomCoach, String type ) {
        this.numCours = numCours;
        this.numReservation = numReservation;
        this.nomCours = nomCours;
        this.nomCoach = nomCoach;
        this.type = type;
         
    }

    public Cours(int numReservation, String nomCours, String nomCoach, String type) {
        this.numReservation = numReservation;
        this.nomCours = nomCours;
        this.nomCoach = nomCoach;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cours{" + "numCours=" + numCours + ", numReservation=" + numReservation + ", nomCours=" + nomCours + ", nomCoach=" + nomCoach + ", type=" + type + '}';
    }

    public void setNumCours(int numCours) {
        this.numCours = numCours;
    }

    public void setNumReservation(int numReservation) {
        this.numReservation = numReservation;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumCours() {
        return numCours;
    }

    public int getNumReservation() {
        return numReservation;
    }

    public String getNomCours() {
        return nomCours;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public String getType() {
        return type;
    }

}
