package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.modele.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeRepository extends JpaRepository<Employe,Integer>{
    List<Employe> findByIdEmployeAndNomAndPrenomAndMailEmp(Integer id_emp,String nom,String prenom,String mail);
    public List<Employe> findByNomLike(String nom);
    Employe findByNomAndPrenomAndMailEmp(String nom, String prenom,String mail);
}
