package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Medecin;

import java.util.List;
import java.util.Optional;

public interface MedecinRepository {

    Optional<Medecin> getMedecinById(Long medecinId);

    List<Medecin> findAll();

    /** Search by nom/prenom, optionally filtered by specialite. */
    List<Medecin> search(String search, String specialite);

    /** Distinct list of specialites, used to populate the filter dropdown. */
    List<String> findAllSpecialites();

    void save(Medecin medecin);

    void update(Medecin medecin);

    void delete(Long id);

}
