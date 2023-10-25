package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.modele.Employe;

import java.util.List;

public interface InterfEmployeService extends InterfService<Employe>{
    public List<Employe> read(String mail);

    Employe read(String nom, String prenom, String mail);

}
