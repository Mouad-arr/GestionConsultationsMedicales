package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.StatusConsultation;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    void save(Consultation consultation);

    /**
     * Indique si un médecin est libre à une date/heure donnée, c'est-à-dire
     * s'il n'a pas déjà une consultation planifiée au même créneau.
     */
    boolean isMedecinDisponible(Long medecinId, LocalDateTime dateConsultation);

}
