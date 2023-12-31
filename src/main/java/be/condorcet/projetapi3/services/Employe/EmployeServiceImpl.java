package be.condorcet.projetapi3.services.Employe;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.repositories.EmployeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeServiceImpl implements InterfEmployeService{

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public List<Employe> read(String nom) {
        return employeRepository.findByNomLike(nom + "%");
    }
    @Override
        public Employe read(String nom, String prenom, String mail) {
            return employeRepository.findByNomAndPrenomAndMailEmp(nom,prenom,mail);
        }

        @Override
        public Employe create(Employe employe) throws Exception {
            employeRepository.save(employe);
            return employe;
        }

        @Override
        public Employe read(Integer id) throws Exception {
            Optional<Employe> oem= employeRepository.findById(id);
            return oem.get();
        }

    public Employe readByMail(String mail) throws Exception {
        return employeRepository.findByMailEmp(mail);
    }

        @Override
        public Employe update(Employe employe) throws Exception {
            read(employe.getIdEmploye());
            employeRepository.save(employe);
            return employe;
        }

        @Override
        public void delete(Employe employe) throws Exception {
            employeRepository.deleteById(employe.getIdEmploye());
        }

        @Override
        public List<Employe> all() throws Exception {
            return employeRepository.findAll();
        }

    }

