package be.condorcet.projetapi3.services.Infos;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Infos;
import be.condorcet.projetapi3.modele.InfosKey;
import be.condorcet.projetapi3.services.InterfService;

import java.util.List;

public interface InterfInfosService extends InterfService<Infos> {
    List<Infos> getInfosByEmploye(Employe emp) throws Exception;
    public Infos read(InfosKey id) throws Exception;
}
