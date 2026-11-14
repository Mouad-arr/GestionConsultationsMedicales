package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import our.ensaa.projet.dao.PatientRepository;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Patient;
import our.ensaa.projet.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientRepository {

    private final EntityManager entityManager;

    public PatientDaoImpl() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public List<Patient> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Patient p ORDER BY p.nom ASC", Patient.class)
                .getResultList();
    }

    @Override
    public List<Patient> search(String search) {
        String jpql = """
                SELECT p FROM Patient p
                WHERE LOWER(p.nom) LIKE LOWER(:search)
                   OR LOWER(p.prenom) LIKE LOWER(:search)
                   OR LOWER(p.cin) LIKE LOWER(:search)
                ORDER BY p.nom ASC
                """;
        return entityManager.createQuery(jpql, Patient.class)
                .setParameter("search", "%" + search.trim() + "%")
                .getResultList();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Patient.class, id));
    }

    @Override
    public void save(Patient patient) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(patient);
            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public void update(Patient patient) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.merge(patient);
            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Patient patient = entityManager.find(Patient.class, id);
            if (patient != null) {
                // Delete dependent consultations first (cascades to diagnostic + prescriptions).
                List<Consultation> consultations = entityManager
                        .createQuery("SELECT c FROM Consultation c WHERE c.patient.id = :id", Consultation.class)
                        .setParameter("id", id)
                        .getResultList();
                for (Consultation consultation : consultations) {
                    entityManager.remove(consultation);
                }
                entityManager.remove(patient);
            }
            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

}
