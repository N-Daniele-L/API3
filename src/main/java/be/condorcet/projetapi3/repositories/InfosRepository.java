package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Infos;
import be.condorcet.projetapi3.modele.InfosKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfosRepository extends JpaRepository<Infos,Integer> {
    Optional<Infos> findById(InfosKey id);
    List<Infos> findInfosByEmploye(Employe emp);
    void deleteById(InfosKey id);
}
