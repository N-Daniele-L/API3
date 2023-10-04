package be.condorcet.projetapi3;

import be.condorcet.projetapi3.modele.Bureau;
import be.condorcet.projetapi3.modele.BureauDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bureaux")
public class GestBureau {
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    BureauDAO bureauDAO;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche bureaux");
        List<Bureau> liste;
        try {
            liste= bureauDAO.readall();
            model.put("mesBureaux", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "afficheagetousBureau";
    }
}