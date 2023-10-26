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
import java.util.List;


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
            System.out.println("création de message + ploy : " + mess);
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
    }

    @Test
    void getMessage() {
    }
}