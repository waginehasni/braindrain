/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.Date;

/**
 *
 * @author hedii
 */
public class Reclamation {
    
 private int identifiant;
 private float identifiantSource;
 private int identifiantDestinataire;
 private String text;
 private String subject;
 private String dateReclamation;

    public Reclamation() {
    }
         Reclamation(float identifiantSource,String text, String subject, String dateReclamation) {
        
        this.identifiantSource = identifiantSource;
        this.text = text;
        this.subject = subject;
        this.dateReclamation = dateReclamation;
    }
    public Reclamation(float identifiantSource, int identifiantDestinataire, String text, String subject, String dateReclamation) {
        
        this.identifiantSource = identifiantSource;
        this.identifiantDestinataire = identifiantDestinataire;
        this.text = text;
        this.subject = subject;
        this.dateReclamation = dateReclamation;
    }

    public Reclamation(int identifiant, float identifiantSource, int identifiantDestinataire, String text, String subject, String dateReclamation) {
        this.identifiant = identifiant;
        this.identifiantSource = identifiantSource;
        this.identifiantDestinataire = identifiantDestinataire;
        this.text = text;
        this.subject = subject;
        this.dateReclamation = dateReclamation;
    }

    public String getDateReclamation() {
        return dateReclamation;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public int getIdentifiantDestinataire() {
        return identifiantDestinataire;
    }

    public float getIdentifiantSource() {
        return identifiantSource;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }

    public void setDateReclamation(String dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public void setIdentifiantDestinataire(int identifiantDestinataire) {
        this.identifiantDestinataire = identifiantDestinataire;
    }

    public void setIdentifiantSource(float identifiantSource) {
        this.identifiantSource = identifiantSource;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }
    
 
}
