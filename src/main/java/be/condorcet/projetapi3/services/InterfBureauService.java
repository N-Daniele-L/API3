package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.modele.Bureau;
import be.condorcet.projetapi3.modele.Employe;

import java.util.List;

public interface InterfBureauService extends InterfService<Bureau>{
    public Bureau read(String sigle);
}
