package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {

    private final IChambreService chambreService;

    // Retrieve all chambres
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        return chambreService.retrieveAllChambres();
    }

    // Retrieve a specific chambre by ID
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        return chambreService.retrieveChambre(chId);
    }

    // Add a new chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        return chambreService.addChambre(c);
    }

    // Remove a chambre by ID
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }

    // Modify an existing chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        return chambreService.modifyChambre(c);
    }

    // Find chambres based on type
    @GetMapping("/trouver-chambres-selon-typ/{tc}")
    public List<Chambre> trouverChSelonTC(@PathVariable("tc") TypeChambre tc) {
        return chambreService.recupererChambresSelonTyp(tc);
    }

    // Find a chambre based on student's CIN
    @GetMapping("/trouver-chambre-selon-etudiant/{cin}")
    public Chambre trouverChSelonEt(@PathVariable("cin") long cin) {
        return chambreService.trouverchambreSelonEtudiant(cin);
    }
}
