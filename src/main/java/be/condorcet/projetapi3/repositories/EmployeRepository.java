package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.modele.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Integer>{
    List<Employe> findByIdEmployeAndNomAndPrenomAndMailEmp(Integer id_emp,String nom,String prenom,String mail);
    List<Employe>findByNom(String nom);
    List<Employe> findByNomLike(String nom);
    Employe findByNomAndPrenomAndMailEmp(String nom, String prenom,String mail);
    Employe findByMailEmp(String mail);

}
