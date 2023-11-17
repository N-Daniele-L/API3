package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.modele.Bureau;
import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.services.Bureau.BureauServiceImpl;
import be.condorcet.projetapi3.services.Employe.EmployeServiceImpl;
import be.condorcet.projetapi3.services.Message.MessageServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeServiceImplTest {
    @Autowired
    private EmployeServiceImpl employeServiceImpl;
    @Autowired
    private MessageServiceImpl messageServiceImpl;
    @Autowired
    private BureauServiceImpl bureauServiceImpl;
    Employe emp;
    Bureau bur;

    @BeforeEach
    void setUp() {
        try{
            bur = new Bureau("TEST","0000000");
            bureauServiceImpl.create(bur);
            emp = new Employe("Nom.Prenom@Test.com","NomTest","PrenomTest",bur);
            employeServiceImpl.create(emp);
            System.out.println("création de l'employé : " + emp);
        } catch (Exception e) {
            System.out.println("erreur de création de l'employé : "+emp +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {

            employeServiceImpl.delete(emp);
            System.out.println("effacement de l'employé : "+emp);
            bureauServiceImpl.delete(bur);
            System.out.println("effacement du bureau : "+bur);
        } catch (Exception e) {
            System.out.println("erreur d'effacement du client :"+emp+" erreur : "+e);
        }
    }

    @Test
    void read() {
        try {
            int numEmp = emp.getIdEmploye();
            Employe emp2 = employeServiceImpl.read(numEmp);
            assertEquals("Nom.Prenom@Test.com",emp2.getMailEmp(),"mails différents"+"Nom.Prenom@Test.com"+"-"+emp2.getMailEmp());
            assertEquals("NomTest",emp2.getNom(),"noms différents"+"NomTest"+"-"+emp2.getNom());
            assertEquals("PrenomTest",emp2.getPrenom(),"prenoms différents"+"PrenomTest"+"-"+emp2.getPrenom());
        } catch (Exception e) {
            fail("recherche infructueuse" +e);
        }
    }

    @Test
    void create() {
        assertNotEquals(null,emp.getIdEmploye(),"id employe non incrémenté");
        assertEquals("NomTest",emp.getNom(),"nom employe non enregistré : "+emp.getNom()+ " au lieu de NomTest");
        assertEquals("PrenomTest",emp.getPrenom(),"prénom client non enregistré : "+emp.getPrenom()+" au lieu de PrenomTest");
        assertEquals("Nom.Prenom@Test.com",emp.getMailEmp(),"mail employe non enregistré : "+emp.getMailEmp()+ " au lieu de Nom.Prenom@Test.com");
    }

    @Test
    void update(){
        try {
            emp.setNom("TestNom");
            emp.setPrenom("TestPrenom");
            emp.setMailEmp("Prenom.Nom@Test.com");
            emp = employeServiceImpl.update(emp);
            assertEquals("TestNom",emp.getNom(),"noms différents " + "TestNom -" + emp.getNom());
            assertEquals("TestPrenom",emp.getPrenom(),"prenoms différents " + "TestPrenom -" + emp.getPrenom());
            assertEquals("Prenom.Nom@Test.com",emp.getMailEmp(),"mails différents " + "Prenom.Nom@Test.com -" + emp.getMailEmp());
        } catch (Exception e) {
            fail("erreur de mise à jour " + e);
        }


    }

    @Test
    void delete() {
        try{
            employeServiceImpl.delete(emp);
            Assertions.assertThrows(Exception.class, () -> {
                employeServiceImpl.read(emp.getIdEmploye());
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Employe> employes = employeServiceImpl.all();
            assertNotEquals(0,employes.size(),"la liste ne contient aucun element");
        } catch (Exception e) {
            fail("erreur de recherche de tous les clients "+e);;
        }
    }
}