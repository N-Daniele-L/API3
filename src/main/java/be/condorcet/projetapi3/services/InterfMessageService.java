package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;

import java.util.List;

public interface InterfMessageService extends InterfService<Message>{
    public List<Message> getMessageByEmp(Employe employe);
    public List<Message> getMessageByObject(String obj);
}
