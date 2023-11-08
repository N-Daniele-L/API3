package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.services.InterfEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/clients")
public class RestEmploye {
    @Autowired
    private InterfEmployeService employeService;
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
}
