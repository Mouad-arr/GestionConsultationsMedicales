package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import our.ensaa.projet.dao.DiagnosticRepository;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Diagnostic;
import our.ensaa.projet.entity.Prescription;
import our.ensaa.projet.util.JpaUtil;

import java.time.LocalDate;

public class DiagnosticDaoImpl implements DiagnosticRepository {

    private final EntityManager entityManager;

    public DiagnosticDaoImpl(){
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public void saveDiagnostic(Long consultationId, Diagnostic diagnostic) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Consultation consultation =
                    entityManager.find(Consultation.class, consultationId);

            if (consultation == null) {
                throw new IllegalArgumentException("Consultation not found");
            }

            diagnostic.setDateDiagnostic(LocalDate.now());
            diagnostic.setConsultation(consultation);

            if (diagnostic.getPrescriptions() != null) {
                for (Prescription prescription : diagnostic.getPrescriptions()) {
                    prescription.setDiagnostic(diagnostic);
                }
            }

            entityManager.persist(diagnostic);

            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

}
