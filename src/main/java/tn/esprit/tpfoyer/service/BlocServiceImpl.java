package tn.esprit.tpfoyer.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j  // Simple Logging Facade For Java
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;

    @Scheduled(fixedRate = 30000) // milliseconds
    public List<Bloc> retrieveAllBlocs() {
        List<Bloc> listB = blocRepository.findAll();
        log.info("Total blocs: {}", listB.size());
        listB.forEach(b -> log.info("Bloc: {}", b));
        return listB;
    }

    @Transactional
    public List<Bloc> retrieveBlocsSelonCapacite(long c) {
        List<Bloc> listB = blocRepository.findAll();
        List<Bloc> listBselonC = new ArrayList<>();

        for (Bloc b : listB) {
            if (b.getCapaciteBloc() >= c) {
                listBselonC.add(b);
            }
        }
        return listBselonC;
    }

    @Transactional
    public Bloc retrieveBloc(Long blocId) {
        Optional<Bloc> optionalBloc = blocRepository.findById(blocId);
        if (optionalBloc.isPresent()) {
            return optionalBloc.get();
        } else {
            log.warn("Bloc with ID {} not found", blocId);
            return null; // or throw an exception, depending on your error handling strategy
        }
    }

    public Bloc addBloc(Bloc c) {
        return blocRepository.save(c);
    }

    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    public List<Bloc> trouverBlocsSansFoyer() {
        return blocRepository.findAllByFoyerIsNull();
    }

    public List<Bloc> trouverBlocsParNomEtCap(String nb, long c) {
        return blocRepository.findAllByNomBlocAndCapaciteBloc(nb, c);
    }
}
