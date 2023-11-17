package be.condorcet.projetapi3.services.Infos;

import be.condorcet.projetapi3.modele.Employe;
import be.condorcet.projetapi3.modele.Infos;
import be.condorcet.projetapi3.modele.InfosKey;
import be.condorcet.projetapi3.repositories.InfosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class InfosServiceImpl implements InterfInfosService{
    @Autowired
    private InfosRepository infosRepository;
    @Override
    public Infos read(Integer id) throws Exception {
        return null;
    }
    @Override
    public Infos create(Infos infos) throws Exception {
        infosRepository.save(infos);
        return infos;
    }
    @Override
    public List<Infos> getInfosByEmploye(Employe emp) throws Exception {
        List<Infos> infos = infosRepository.findInfosByEmploye(emp);
        return infos;
    }
    @Override
    public Infos read(InfosKey id) throws Exception {
        Optional<Infos> oin = infosRepository.findById(id);
        return oin.get();
    }
    @Override
    public Infos update(Infos infos) throws Exception {
        read(infos.getId());
        infosRepository.save(infos);
        return infos;
    }


    @Override
    public void delete(Infos infos) throws Exception {
        infosRepository.deleteById(infos.getId());
    }

    @Override
    public List<Infos> all() throws Exception {
        return infosRepository.findAll();
    }
}