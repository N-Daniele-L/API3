package be.condorcet.projetapi3.services.Employe;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.services.InterfService;

import java.util.List;

public interface InterfEmployeService extends InterfService<Employe> {
    public List<Employe> read(String nom);
    Employe read(String nom, String prenom, String mail);
}
