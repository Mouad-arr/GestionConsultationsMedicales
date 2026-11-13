package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import our.ensaa.projet.dao.DiagnosticRepository;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Diagnostic;
import our.ensaa.projet.util.JpaUtil;

import java.time.LocalDate;

public class DiagnosticDaoImpl implements DiagnosticRepository {

    private final EntityManager entityManager;

    public DiagnosticDaoImpl(){
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public void saveDiagnostic(Long consultationId, String description) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Consultation consultation = entityManager.find( Consultation.class, consultationId );

            if (consultation == null) {
                throw new IllegalArgumentException("Consultation not found");
            }

            Diagnostic diagnostic = new Diagnostic();
            diagnostic.setDescription(description);
            diagnostic.setDateDiagnostic(LocalDate.now());
            diagnostic.setConsultation(consultation);

            entityManager.persist(diagnostic);

            entityManager.flush();

            transaction.commit();


        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

}
