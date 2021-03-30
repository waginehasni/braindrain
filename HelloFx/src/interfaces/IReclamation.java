package interfaces;

import entities.Reclamation;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

public interface IReclamation <T>{
    public void ajouterReclamation(T t);
    public void supprimerReclamation(int t);
    public void updateReclamation(T t);
    public List<T> displayReclamations();
    public  ObservableList<T> chercherReclamationActif(String titre);
    public  ObservableList<T> chercherReclamationcloture(String titre);
    public T FindReclamationById(int id);
    public ArrayList<Reclamation> TrierParTitre();
}
