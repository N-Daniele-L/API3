package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Infos;
import be.condorcet.projetapi3.modele.InfosKey;
import be.condorcet.projetapi3.services.Employe.InterfEmployeService;
import be.condorcet.projetapi3.services.Infos.InterfInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/infos")
public class RestInfos {
    @Autowired
    private InterfInfosService infosService;
    @Autowired
    private InterfEmployeService employeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Infos> createInfos(@RequestBody Infos infos) throws Exception {
        infos.setId(new InfosKey(infos.getEmploye().getIdEmploye(), infos.getMessage().getIdMess()));
        infosService.create(infos);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }
    @RequestMapping(value = "/idEmp",method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> listMessagesReceiveByEmp(@PathVariable(value="idEmp") int idEmp) throws  Exception{
        System.out.println("recherche des messages reçu par l'employé : " + idEmp);
        Employe emp = employeService.read(idEmp);
        List<Infos> infos = infosService.getInfosByEmploye(emp);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }
    @RequestMapping(value = "/{idEmp}/{idMess}",method = RequestMethod.PUT)
    public ResponseEntity<Infos> updateInfos(@PathVariable(value="idEmp")int idEmp,
                                             @PathVariable(value = "idMess") int idMess,
                                             @RequestBody Infos infos) throws Exception{
        infos.setId(new InfosKey(idEmp,idMess));
        Infos inf = infosService.update(infos);
        return new ResponseEntity<>(inf,HttpStatus.OK);
    }
    @RequestMapping(value = "/{idEmp}/{idMess}", method = RequestMethod.DELETE)
    public ResponseEntity<Infos> deleteInfos(@PathVariable(value="idEmp")int idEmp,
                                             @PathVariable(value = "idMess") int idMess) throws Exception{
        Infos inf = infosService.read(new InfosKey(idEmp,idMess));
        infosService.delete(inf);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
