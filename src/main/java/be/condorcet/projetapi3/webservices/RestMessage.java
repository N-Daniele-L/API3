package be.condorcet.projetapi3.webservices;
import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;
import be.condorcet.projetapi3.services.Employe.EmployeServiceImpl;
import be.condorcet.projetapi3.services.Message.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/messages")
public class RestMessage {
    @Autowired
    private MessageServiceImpl messageService;
    @Autowired
    private EmployeServiceImpl employeService;

    @RequestMapping(value = "/idEmploye={id}/before={before}/after={after}",method = RequestMethod.GET)
    public ResponseEntity<List<Message>> betweendate(@PathVariable(value = "id")int id,
                                                     @PathVariable("before")@DateTimeFormat(pattern = "yyyy-MM-dd")Date before,
                                                     @PathVariable("after")@DateTimeFormat(pattern = "yyyy-MM-dd")Date after) throws Exception{
        System.out.println("recherche message entre deux dates");
        Employe emp = employeService.read(id);
        List<Message> message = messageService.getMessageBetweenDate(emp,before,after);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Message> getMessage(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("recherche du message avec Id : " + id);
        Message message = messageService.read(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/objet={objet}",method = RequestMethod.GET)
    public ResponseEntity <List<Message>> getMessageByObjet(@PathVariable(value = "objet")String objet) throws Exception{
        System.out.println("recherche des messages avec l'objet suivant : "+ objet);
        List<Message> lme = messageService.getMessageByObject(objet);
        return new ResponseEntity<>(lme,HttpStatus.OK);
    }

    @RequestMapping(value = "/idEmploye={id}",method = RequestMethod.GET)
    public ResponseEntity <List<Message>> getMessageByEmp(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("recherche des messages envoyé par l'employé avec cet id : "+ id);
        Employe emp = employeService.read(id);
        List<Message> lme = messageService.getMessageByEmp(emp);
        return new ResponseEntity<>(lme,HttpStatus.OK);
    }
    @RequestMapping(value = "/mail={mail}/objet={objet}",method = RequestMethod.GET)
    public ResponseEntity <List<Message>> getMessageBySenderAndObjet(@PathVariable(value = "mail")String mail,
                                                                     @PathVariable(value = "objet")String objet) throws Exception{
        System.out.println("recherche des messages envoyé par l'employé avec ce mail : "+ mail + "et cet objet" + objet);
        List<Message> lme = messageService.getMessageByEmpandObjet(mail,objet);
        return new ResponseEntity<>(lme,HttpStatus.OK);
    }

    @RequestMapping(value = "/mail={mail}",method = RequestMethod.GET)
    public ResponseEntity <List<Message>> getMessageBySender(@PathVariable(value = "mail")String mail) throws Exception{
        System.out.println("recherche des messages envoyé par l'employé avec cet id : "+ mail);
        Employe emp = employeService.readByMail(mail);
        List<Message> lme = messageService.getMessageByEmp(emp);
        return new ResponseEntity<>(lme,HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Message> createMessage(@RequestBody Message message) throws Exception{
        System.out.println("Création du message : " + message);
        messageService.create(message);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Message> updateMessage(@PathVariable(value="id")int id,@RequestBody Message message) throws Exception{
        System.out.println("Update du message avec l'id : " + id);
        message.setIdMess(id);
        Message mess= messageService.update(message);
        return new ResponseEntity<>(mess,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMessage(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du message avec cet id : " + id);
        Message mess = messageService.read(id);
        messageService.delete(mess);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Message>> listMessage() throws Exception{
        System.out.println("recherche de tous les messages");
        return new ResponseEntity<>(messageService.all(), HttpStatus.OK);
    }



    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
