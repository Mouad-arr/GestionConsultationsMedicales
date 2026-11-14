package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    List<Patient> findAll();

    /** Search by nom, prenom or CIN (case-insensitive). */
    List<Patient> search(String search);

    Optional<Patient> findById(Long id);

    void save(Patient patient);

    void update(Patient patient);

    void delete(Long id);

}
