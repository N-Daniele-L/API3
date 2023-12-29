package be.condorcet.projetapi3.services.Bureau;

import be.condorcet.projetapi3.modele.Bureau;
import be.condorcet.projetapi3.repositories.BureauRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class BureauServiceImpl implements InterfBureauService{
    @Autowired
    private BureauRepository bureauRepository;

    @Override
    public List<Bureau> read(String sigle) {
        return bureauRepository.findBySigleLike(sigle);
    }


    @Override
    public Bureau create(Bureau bureau) throws Exception {
        bureauRepository.save(bureau);
        return bureau;
    }

    @Override
    public Bureau read(Integer id) throws Exception {
        Optional<Bureau> obr= bureauRepository.findById(id);
        return obr.get();
    }

    @Override
    public Bureau update(Bureau bureau) throws Exception {
        read(bureau.getIdBureau());
        bureauRepository.save(bureau);
        return bureau;
    }

    @Override
    public void delete(Bureau bureau) throws Exception {
        bureauRepository.deleteById(bureau.getIdBureau());
    }

    @Override
    public List<Bureau> all() throws Exception {
        return bureauRepository.findAll();
    }

}
