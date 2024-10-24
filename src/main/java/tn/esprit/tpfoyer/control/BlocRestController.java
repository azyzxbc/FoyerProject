package tn.esprit.tpfoyer.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.List;

@Tag(name = "Gestion Bloc pour l'équipe 4DS9")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocRestController {

    private final IBlocService blocService;

    // Retrieve all blocs
    @GetMapping("/retrieve-all-blocs")
    @Operation(description = "WS de récupération de tous les Blocs")
    public List<Bloc> getBlocs() {
        return blocService.retrieveAllBlocs();
    }

    // Retrieve a specific bloc by ID
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long bId) {
        return blocService.retrieveBloc(bId);
    }

    // Add a new bloc
    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc c) {
        return blocService.addBloc(c);
    }

    // Remove a bloc by ID
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long chId) {
        blocService.removeBloc(chId);
    }

    // Modify an existing bloc
    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc c) {
        return blocService.modifyBloc(c);
    }

    // Retrieve blocs without foyer
    @GetMapping("/trouver-blocs-sans-foyer")
    public List<Bloc> getBlocsWithoutFoyer() {
        return blocService.trouverBlocsSansFoyer();
    }

    // Retrieve blocs by name and capacity
    @GetMapping("/get-bloc-nb-c/{nb}/{c}")
    public List<Bloc> recuperBlocsParNomEtCap(
            @PathVariable("nb") String nb,
            @PathVariable("c") long c) {
        return blocService.trouverBlocsParNomEtCap(nb, c);
    }
}
