package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import our.ensaa.projet.dao.ConsultationRepository;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.StatusConsultation;
import our.ensaa.projet.util.JpaUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ConsultationDaoImpl implements ConsultationRepository {

    private final EntityManager entityManager;

    public ConsultationDaoImpl() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public List<Consultation> findHistoryByMedecinId(Long medecinId) {
        String jpql = "SELECT c FROM Consultation c WHERE c.medecin.id = :medecinId AND c.status != 'Planifie' ORDER BY c.dateConsultation DESC";

        TypedQuery<Consultation> query = entityManager.createQuery(jpql, Consultation.class);

        query.setParameter("medecinId", medecinId);

        return query.getResultList();
    }

    @Override
    public List<Consultation> findUpcomingByMedecinId(Long medecinId) {
        String jpql = "SELECT c FROM Consultation c WHERE c.medecin.id = :medecinId AND c.status = 'Planifie' ORDER BY c.dateConsultation ASC";

        TypedQuery<Consultation> query = entityManager.createQuery(jpql, Consultation.class);

        query.setParameter("medecinId", medecinId);

        return query.getResultList();
    }

    @Override
    public Optional<Consultation> getConsultationByIdAndMedecinId(Long consultationId, Long medecinId) {
        String jpql = "SELECT c FROM Consultation c WHERE c.id = :consultationId AND c.medecin.id = :medecinId";

        TypedQuery<Consultation> query = entityManager.createQuery(jpql, Consultation.class);

        query.setParameter("consultationId", consultationId);
        query.setParameter("medecinId", medecinId);


        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Consultation> getConsultationById(Long consultationId) {
        String jpql = "SELECT c FROM Consultation c WHERE c.id = :consultationId";

        TypedQuery<Consultation> query = entityManager.createQuery(jpql, Consultation.class);

        query.setParameter("consultationId", consultationId);

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public void updateConsultation(Long consultationId, StatusConsultation status) {
        entityManager.getTransaction().begin();
        String jpql = "UPDATE Consultation c SET  c.status = :status WHERE c.id = :consultationId";
        entityManager.createQuery(jpql)
                .setParameter("consultationId", consultationId)
                .setParameter("status", status)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    // ---------------------------------------------------------------------
    // Admin : global views
    // ---------------------------------------------------------------------

    @Override
    public List<Consultation> findAll() {
        return entityManager
                .createQuery("SELECT c FROM Consultation c ORDER BY c.dateConsultation DESC", Consultation.class)
                .getResultList();
    }

    @Override
    public List<Consultation> findConsultationsDuJour() {
        LocalDate today = LocalDate.now();
        String jpql = """
                SELECT c FROM Consultation c
                WHERE c.dateConsultation >= :start AND c.dateConsultation < :end
                ORDER BY c.dateConsultation ASC
                """;
        return entityManager.createQuery(jpql, Consultation.class)
                .setParameter("start", today.atStartOfDay())
                .setParameter("end", today.plusDays(1).atStartOfDay())
                .getResultList();
    }

    @Override
    public List<Consultation> search(StatusConsultation statut, LocalDate date, String medecin) {
        StringBuilder jpql = new StringBuilder("SELECT c FROM Consultation c WHERE 1 = 1");

        if (statut != null) {
            jpql.append(" AND c.status = :statut");
        }
        if (date != null) {
            jpql.append(" AND c.dateConsultation >= :start AND c.dateConsultation < :end");
        }
        if (medecin != null && !medecin.isBlank()) {
            jpql.append(" AND (LOWER(c.medecin.nom) LIKE LOWER(:medecin) OR LOWER(c.medecin.prenom) LIKE LOWER(:medecin))");
        }
        jpql.append(" ORDER BY c.dateConsultation DESC");

        TypedQuery<Consultation> query = entityManager.createQuery(jpql.toString(), Consultation.class);

        if (statut != null) {
            query.setParameter("statut", statut);
        }
        if (date != null) {
            query.setParameter("start", date.atStartOfDay());
            query.setParameter("end", date.plusDays(1).atStartOfDay());
        }
        if (medecin != null && !medecin.isBlank()) {
            query.setParameter("medecin", "%" + medecin.trim() + "%");
        }

        return query.getResultList();
    }

    @Override
    public void save(Consultation consultation) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(consultation);
            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

}
