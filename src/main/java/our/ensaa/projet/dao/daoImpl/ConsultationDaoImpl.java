package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import our.ensaa.projet.dao.ConsultationRepository;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.StatusConsultation;
import our.ensaa.projet.util.JpaUtil;

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
}
