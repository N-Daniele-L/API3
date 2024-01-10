package be.condorcet.projetapi3.services.Message;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;
import be.condorcet.projetapi3.repositories.EmployeRepository;
import be.condorcet.projetapi3.repositories.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public List<Message> getMessageByEmpandObjet(String mail, String objet){
        List<Message> lme = messageRepository.findMessageByEmploye_MailEmpAndObjet(mail,objet);
        return lme;
    }

    public List<Message> getMessageBySender(String email){
        List<Message> lme = messageRepository.findMessageByEmploye_MailEmp(email);
        return lme;
    }

    public List<Message> getMessageByObject(String obj){
        List<Message> lme = messageRepository.findMessageByObjet(obj);
        return lme;
    }
    public List<Message>getMessageBetweenDate(Employe emp,Date before, Date after){
        List<Message> lme = messageRepository.findMessageByEmployeAndDateenvoiBeforeAndDateenvoiAfter(emp,before,after);
        return lme;
    }
}
