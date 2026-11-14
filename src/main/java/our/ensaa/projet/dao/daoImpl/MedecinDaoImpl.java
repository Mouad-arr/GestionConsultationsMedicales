package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import our.ensaa.projet.dao.MedecinRepository;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class MedecinDaoImpl implements MedecinRepository {

    private final EntityManager entityManager;

    public MedecinDaoImpl() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Optional<Medecin> getMedecinById(Long medecinId) {
        return Optional.ofNullable(entityManager.find(Medecin.class, medecinId));
    }

    @Override
    public List<Medecin> findAll() {
        return entityManager
                .createQuery("SELECT m FROM Medecin m ORDER BY m.nom ASC", Medecin.class)
                .getResultList();
    }

    @Override
    public List<Medecin> search(String search, String specialite) {
        StringBuilder jpql = new StringBuilder("SELECT m FROM Medecin m WHERE 1 = 1");

        if (search != null && !search.isBlank()) {
            jpql.append(" AND (LOWER(m.nom) LIKE LOWER(:search) OR LOWER(m.prenom) LIKE LOWER(:search))");
        }
        if (specialite != null && !specialite.isBlank()) {
            jpql.append(" AND m.specialite = :specialite");
        }
        jpql.append(" ORDER BY m.nom ASC");

        TypedQuery<Medecin> query = entityManager.createQuery(jpql.toString(), Medecin.class);

        if (search != null && !search.isBlank()) {
            query.setParameter("search", "%" + search.trim() + "%");
        }
        if (specialite != null && !specialite.isBlank()) {
            query.setParameter("specialite", specialite.trim());
        }

        return query.getResultList();
    }

    @Override
    public List<String> findAllSpecialites() {
        String jpql = """
                SELECT DISTINCT m.specialite FROM Medecin m
                WHERE m.specialite IS NOT NULL
                ORDER BY m.specialite ASC
                """;
        return entityManager.createQuery(jpql, String.class).getResultList();
    }

    @Override
    public void save(Medecin medecin) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(medecin);
            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public void update(Medecin medecin) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.merge(medecin);
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
            Medecin medecin = entityManager.find(Medecin.class, id);
            if (medecin != null) {
                // Delete dependent consultations first (cascades to diagnostic + prescriptions).
                List<Consultation> consultations = entityManager
                        .createQuery("SELECT c FROM Consultation c WHERE c.medecin.id = :id", Consultation.class)
                        .setParameter("id", id)
                        .getResultList();
                for (Consultation consultation : consultations) {
                    entityManager.remove(consultation);
                }
                entityManager.remove(medecin);
            }
            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

}
