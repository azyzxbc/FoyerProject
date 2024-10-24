package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface IChambreService {

    List<Chambre> retrieveAllChambres();
    Chambre retrieveChambre(Long chambreId);
    Chambre addChambre(Chambre chambre); // Renamed 'c' to 'chambre' for consistency
    void removeChambre(Long chambreId);
    Chambre modifyChambre(Chambre chambre);

    // Here we will add later methods calling keywords and methods calling JPQL
    Chambre trouverChambreSelonEtudiant(long cin); // Renamed 'Cin' to 'cin' to match naming convention

    List<Chambre> recupererChambresSelonTyp(TypeChambre typeChambre); // Consider renaming for clarity
}
