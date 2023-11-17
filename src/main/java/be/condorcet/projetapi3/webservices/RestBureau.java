package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.modele.Bureau;
import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.services.InterfBureauService;
import be.condorcet.projetapi3.services.InterfEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/bureaux")
public class RestBureau {
    @Autowired
    private InterfBureauService bureauService;
    private InterfEmployeService employeService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bureau> getBureau(@PathVariable(value = "id") int id) throws  Exception{
        System.out.println("recherche du bureau avec id + id");
        Bureau bureau = bureauService.read(id);
        return new ResponseEntity<>(bureau, HttpStatus.OK);
    }
    @RequestMapping(value = "/sigle={sigle}",method = RequestMethod.GET)
    public ResponseEntity<Bureau> BureauSigle(@PathVariable(value="sigle") String sigle) throws  Exception{
        System.out.println("recherche du bureau avec sigle : " +sigle);
        Bureau bureau = bureauService.read(sigle);
        return new ResponseEntity<>(bureau, HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Bureau> createBureau(@RequestBody Bureau bureau) throws Exception{
        System.out.println("Création du bureau : " + bureau);
        bureauService.create(bureau);
        return new ResponseEntity<>(bureau,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Bureau> updateBureau(@PathVariable(value="id")int id,@RequestBody Bureau bureau) throws Exception{
        System.out.println("Update du bureau avec l'id : ");
        bureau.setIdBureau(id);
        Bureau bur = bureauService.update(bureau);
        return new ResponseEntity<>(bur,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBureau(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du bureau avec cet id : " + id);
        Bureau bur = bureauService.read(id);
        bureauService.delete(bur);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Bureau>> listBureau() throws Exception{
        System.out.println("recherche de tous les employés");
        return new ResponseEntity<>(bureauService.all(), HttpStatus.OK);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}