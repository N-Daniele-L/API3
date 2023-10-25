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
class EmployeServiceImplTest {
    //@Autowired
    //private EmployeServiceImpl employeServiceImpl;
    //@Autowired
    //private MessageServiceImpl messageServiceImpl;
    Employe emp;

    @BeforeEach
    void setUp() {
        /*try{
            emp = new Employe(null,"Nom.Prenom@Test.com","NomTest","PrenomTest",null);
            employeServiceImpl.create(emp);
            System.out.println("création de l'employé : " + emp);
        } catch (Exception e) {
            System.out.println("erreur de création du client : "+emp +" erreur : "+e);
        }*/
    }

    @AfterEach
    void tearDown() {
        /*try {
            employeServiceImpl.delete(emp);
            System.out.println("effacement de l'employé : "+emp);
        } catch (Exception e) {
            System.out.println("erreur d'effacement du client :"+emp+" erreur : "+e);
        }*/
    }

    @Test
    void read() {
        /*try {
            int numEmp = emp.getIdEmploye();
            Employe emp2 = employeServiceImpl.read(numEmp);
            assertEquals("Nom.Prenom@Test.com",emp2.getNom(),"mails différents"+"Nom.Prenom@Test.com"+"-"+emp2.getMailEmp());
        } catch (Exception e) {
            fail("recherche infructueuse" +e);
        }*/
    }

    @Test
    void testRead() {
    }

    @Test
    void create() {
    }

    @Test
    void testRead1() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void all() {
    }
}