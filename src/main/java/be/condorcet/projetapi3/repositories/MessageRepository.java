package be.condorcet.projetapi3.repositories;
import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Message;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer>{
    List<Message> findByIdMessAndObjetAndContenu(Integer id,String objet,String contenu);
    List<Message> findMessageByEmploye(Employe emp);
}
