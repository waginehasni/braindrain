/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pi.entities;

/**
 *
 * @author asus
 */
public class Sponsors {

    private int idSponsor;
    private String societe;
    private float budget;
    private int numtelephone;

    public Sponsors() {
    }

    public Sponsors(String societe, float budget, int numtelephone) {
        this.societe = societe;
        this.budget = budget;
        this.numtelephone = numtelephone;
    }

    public Sponsors(int idSponsor,String societe,float budget,int numtelephone) {
        this.idSponsor = idSponsor;
        this.societe = societe;
        this.budget = budget;
        this.numtelephone = numtelephone;
    }

    public Sponsors(String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdSponsor() {
        return idSponsor;
    }

    public String getSociete() {
        return societe;
    }

    public float getBudget() {
        return budget;
    }

    public int getNumtelephone() {
        return numtelephone;
    }

    public void setIdSponsor(int idSponsor) {
        this.idSponsor = idSponsor;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void setNumtelephone(int numtelephone) {
        this.numtelephone = numtelephone;
    }


    @Override
    public String toString() {
        return "Sponsors{" + "idSponsor=" + idSponsor + ", societe=" + societe + ", budget=" + budget + ", numtelephone=" + numtelephone + '}';
    }








}
