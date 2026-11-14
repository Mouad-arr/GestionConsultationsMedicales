package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.StatusConsultation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {

    List<Consultation> findHistoryByMedecinId(Long medecinId);

    List<Consultation> findUpcomingByMedecinId(Long medecinId);

    Optional<Consultation> getConsultationByIdAndMedecinId(Long consultationId, Long medecinId);

    Optional<Consultation> getConsultationById(Long consultationId);

    void updateConsultation(Long consultationId, StatusConsultation statusConsultation);

    // ---------------------------------------------------------------------
    // Admin : global views (all consultations, today, filtered)
    // ---------------------------------------------------------------------

    List<Consultation> findAll();

    List<Consultation> findConsultationsDuJour();

    List<Consultation> search(StatusConsultation statut, LocalDate date, String medecin);

}
