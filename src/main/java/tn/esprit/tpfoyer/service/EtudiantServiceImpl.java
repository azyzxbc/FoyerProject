package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;

    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant retrieveEtudiant(Long etudiantId) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);
        if (optionalEtudiant.isPresent()) {
            return optionalEtudiant.get();
        } else {
            // Handle the case when the Etudiant is not found
            // You might want to throw an exception or return null based on your error handling strategy
            return null; // or throw new EntityNotFoundException("Etudiant not found");
        }
    }

    public Etudiant addEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }

    public Etudiant modifyEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }

    public void removeEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);
    }

    public Etudiant recupererEtudiantParCin(long cin) {
        return etudiantRepository.findEtudiantByCinEtudiant(cin);
    }
}
