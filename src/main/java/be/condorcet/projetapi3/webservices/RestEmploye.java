package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.services.Bureau.BureauServiceImpl;
import be.condorcet.projetapi3.services.Employe.EmployeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/employes")
public class RestEmploye {
    @Autowired
    private EmployeServiceImpl employeService;
    private BureauServiceImpl bureauService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmploye(@PathVariable(value = "id") int id) throws  Exception{
        System.out.println("recherche de l'employe avec id + id");
        Employe employe = employeService.read(id);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }
    @RequestMapping(value = "/nom={nom}",method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listEmployeNom(@PathVariable(value="nom") String nom) throws  Exception{
        System.out.println("recherche de " +nom);
        List<Employe> employes;
        employes = employeService.read(nom);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }
    @RequestMapping(value = "/{nom}/{prenom}/{mail}",method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmployeUnique(@PathVariable(value="nom")String nom,
                                                    @PathVariable(value = "prenom")String prenom,
                                                    @PathVariable(value = "mail")String mail) throws Exception {
        System.out.println("Recherche d'un employé unique");
        System.out.println("nom : " + nom + "prenom : " + prenom + "adresse mail : " + mail);
        Employe emp;
        emp=employeService.read(nom,prenom,mail);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) throws Exception{
        System.out.println("Création du l'employé : " + employe);
        employeService.create(employe);
        return new ResponseEntity<>(employe,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Employe> updateEmploye(@PathVariable(value="id")int id,@RequestBody Employe employe) throws Exception{
        System.out.println("Update de l'employé avec l'id : ");
        employe.setIdEmploye(id);
        Employe emp = employeService.update(employe);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmploye(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de l'employé avec cet id : " + id);
        Employe emp = employeService.read(id);
        employeService.delete(emp);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listClient() throws Exception{
        System.out.println("recherche de tous les employés");
        return new ResponseEntity<>(employeService.all(), HttpStatus.OK);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
