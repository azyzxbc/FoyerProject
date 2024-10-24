package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {

    private final IEtudiantService etudiantService;

    // Retrieve all students
    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    // Retrieve a student by CIN
    @GetMapping("/retrieve-etudiant-cin/{cin}")
    public Etudiant retrieveEtudiantParCin(@PathVariable("cin") Long cin) {
        return etudiantService.recupererEtudiantParCin(cin);
    }

    // Retrieve a student by ID
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long chId) {
        return etudiantService.retrieveEtudiant(chId);
    }

    // Add a new student
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant c) {
        return etudiantService.addEtudiant(c);
    }

    // Remove a student by ID
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long chId) {
        etudiantService.removeEtudiant(chId);
    }

    // Modify an existing student
    @PutMapping("/modify-etudiant")
    public Etudiant modifyEtudiant(@RequestBody Etudiant c) {
        return etudiantService.modifyEtudiant(c);
    }
}
