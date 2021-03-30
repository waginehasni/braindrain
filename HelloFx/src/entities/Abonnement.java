package entities;

import java.util.Date;

public class Abonnement {
    private int id ;
    private String type;
    private Date dateCreation;
    private Date dateExpiration ;
    private int validite ;

    public Abonnement() {
    }

    public Abonnement(String type, Date dateCreation, Date dateExpiration, int validite) {
        this.type = type;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.validite = validite;
    }

    public Abonnement(int id, String type, Date dateCreation, Date dateExpiration, int validite) {
        this.id = id;
        this.type = type;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.validite = validite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getValidite() {
        return validite;
    }

    public void setValidite(int validite) {
        this.validite = validite;
    }

    @Override
    public String toString() {
        return "\n Abonnement{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateExpiration=" + dateExpiration +
                ", validite=" + validite +
                '}';
    }
}
