package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;

import java.util.List;

public interface InterfMessageRepository extends InterfService<Message>{
    public List<Message> getMessage(Employe employe);
}
