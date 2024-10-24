package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    private final FoyerRepository foyerRepository;

    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    public Foyer retrieveFoyer(Long foyerId) {
        Optional<Foyer> optionalFoyer = foyerRepository.findById(foyerId);
        if (optionalFoyer.isPresent()) {
            return optionalFoyer.get();
        } else {
            // Handle the case when the Foyer is not found
            // You might want to throw an exception or return null based on your error handling strategy
            return null; // or throw new EntityNotFoundException("Foyer not found");
        }
    }

    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }
}
