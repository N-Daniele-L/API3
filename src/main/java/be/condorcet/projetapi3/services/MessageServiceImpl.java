package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;
import be.condorcet.projetapi3.repositories.EmployeRepository;
import be.condorcet.projetapi3.repositories.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class MessageServiceImpl implements InterfMessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Message create(Message message) throws Exception {
        messageRepository.save(message);
        return message;
    }

    @Override
    public Message read(Integer id) throws Exception {
        return messageRepository.findById(id).get();
    }

    @Override
    public Message update(Message message) throws Exception {
        read(message.getIdMess());
        messageRepository.save(message);
        return message;
    }

    @Override
    public void delete(Message message) throws Exception {
        messageRepository.deleteById(message.getIdMess());
    }

    @Override
    public List<Message> all() throws Exception {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getMessageByEmp(Employe emp) {
        List<Message> lme = messageRepository.findMessageByEmploye(emp);
        return lme;
    }

    public List<Message> getMessageByObject(String obj){
        List<Message> lme = messageRepository.findMessageByObjet(obj);
        return lme;
    }
}
