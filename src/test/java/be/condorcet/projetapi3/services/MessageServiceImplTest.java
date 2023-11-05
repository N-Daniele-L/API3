package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MessageServiceImplTest {
    @Autowired
    private EmployeServiceImpl employeServiceImpl;
    @Autowired
    private  MessageServiceImpl messageServiceImpl;
    Employe emp;
    Message mess;

    @BeforeEach
    void setUp() {
        try {
            emp = new Employe(null, "Nom.Prenom@Test.com", "NomTest", "PrenomTest", 1);
            employeServiceImpl.create(emp);
            mess = new Message(null,"testobj","testcontenu",Date.valueOf(LocalDate.now()),emp);
            messageServiceImpl.create(mess);
            System.out.println("création de message : " + mess);
            System.out.println("création de employé : " + emp);
        } catch (Exception e) {
            System.out.println("erreur de création de message "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try{
            messageServiceImpl.delete(mess);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement du message "+e);
        }
        try{
            employeServiceImpl.delete(emp);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement de l'employe "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(null,mess.getIdMess(),"numéro de commande non incrémenté");
    }

    @Test
    void read() {
        try {
            int IdMess = mess.getIdMess();
            Message mess2 = messageServiceImpl.read(IdMess);
            assertEquals("testobj",mess2.getObjet(),"objet differents");
            assertEquals("testcontenu",mess2.getContenu(),"objet differents");
            assertEquals(Date.valueOf(LocalDate.now()),mess2.getDateenvoi(),"date differents");
            assertEquals(emp,mess2.getEmploye(),"emp differents");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        mess.setObjet("newObjetTest");
        mess.setContenu("newContenuTest");
        try{
            mess = messageServiceImpl.update(mess);
            assertEquals(mess.getObjet(),"newObjetTest","Objet différents" + mess.getObjet()+ " - newObjetTest" );
            assertEquals(mess.getContenu(),"newContenuTest","Contenu différents" + mess.getContenu()+ " - newContenuTest" );
        } catch (Exception e) {
            fail("erreur de mise à jour "+e);
        }

    }

    @Test
    void delete() {
        try{
            messageServiceImpl.delete(mess);
            Assertions.assertThrows(Exception.class,() ->{
                messageServiceImpl.read(mess.getIdMess());
            },"message nn éffacé");
        } catch (Exception e) {
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Message> messages = messageServiceImpl.all();
            assertNotEquals(0,messages.size(),"la liste ne contient aucun element");
        } catch (Exception e) {
            fail("erreur de recherche de tous les messages "+e);;
        }
    }

    @Test
    void getMessageByEmp() {
        try {
            List<Message> lme = messageServiceImpl.getMessageByEmp(emp);
            boolean trouve = false;
            for(Message m:lme){
                // Integer => object
                if((int)m.getEmploye().getIdEmploye() == emp.getIdEmploye()){
                    trouve=true;
                    break;
                }
            }
            assertTrue(trouve,"message absent de la liste de l'employé");
        }
        catch(Exception e){
            fail("Erreur de recherche "+e);
        }
    }

    @Test
    void getMessageByObjet() {
        try {
            Message mess2 = new Message(null,"testobj","testcontenu",Date.valueOf(LocalDate.now()),emp);
            messageServiceImpl.create(mess2);
            List<Message> lme = messageServiceImpl.getMessageByObject("testobj");
            boolean trouve = true;
            for(Message m:lme){
                if(!m.getObjet().equals("testobj")){
                    trouve=false;
                    break;
                }
            }
            assertTrue(trouve,"message absent de la liste de l'employé");
            messageServiceImpl.delete(mess2);
        }
        catch(Exception e){
            fail("Erreur de recherche "+e);
        }
    }
}