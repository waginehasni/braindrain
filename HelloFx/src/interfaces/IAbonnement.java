package interfaces;


import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

public interface IAbonnement <T>{
    public void ajouterAbonnement(T t);
    public void supprimerAbonnement(int t);
    public void updateAbonnement(T t);
    public List<T> displayAbonnements();
    public T FindAbonnementById(int id);
    public ArrayList<T> TrierParValidite();    
    public  ObservableList<T> chercherAbonnement(java.util.Date date);
}
