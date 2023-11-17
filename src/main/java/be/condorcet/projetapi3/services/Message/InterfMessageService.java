package be.condorcet.projetapi3.services.Message;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;
import be.condorcet.projetapi3.services.InterfService;

import java.util.List;

public interface InterfMessageService extends InterfService<Message> {
    public List<Message> getMessageByEmp(Employe employe);
    public List<Message> getMessageByObject(String obj);
}
