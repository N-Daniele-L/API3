package be.condorcet.projetapi3;

import be.condorcet.projetapi3.modele.Bureau;
import be.condorcet.projetapi3.repositories.BureauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/bureaux")
public class GestBureau {
    @Autowired
    BureauRepository bureauRepository;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche bureaux");
        List<Bureau> liste;
        try {
            liste= bureauRepository.findAll();
            model.put("mesBureaux", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "afficheagetousBureau";
    }
    @RequestMapping("/selection")
    String selection(@RequestParam("id_bureau") int idbur, Map<String, Object> model) {
        Bureau bur = null;
        Optional<Bureau> obur;
        try {
            obur = bureauRepository.findById(idbur);// à développer
            if(obur.isPresent()) bur = obur.get();
            else throw new Exception("bureau inconnu");
            model.put("monBureau", bur);


        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error", e);
            return "error";
        }
        return "affBur";
    }
}