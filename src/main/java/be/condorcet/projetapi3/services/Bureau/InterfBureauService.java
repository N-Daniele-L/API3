package be.condorcet.projetapi3.services.Bureau;

import be.condorcet.projetapi3.modele.Bureau;
import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.services.InterfService;

import java.util.List;

public interface InterfBureauService extends InterfService<Bureau> {
    public List<Bureau> read(String sigle);
}
