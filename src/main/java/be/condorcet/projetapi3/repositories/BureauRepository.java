package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.modele.Bureau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BureauRepository extends JpaRepository<Bureau,Integer> {
    List<Bureau> findByIdBureauAndSigleAndTel(Integer id_bur, String sigle, String tel);
    Bureau findBySigle(String sigle);
}
