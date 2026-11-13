package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.StatusConsultation;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {
    public List<Consultation> findHistoryByMedecinId(Long medecinId);
    public List<Consultation> findUpcomingByMedecinId(Long medecinId);
    public Optional<Consultation> getConsultationByIdAndMedecinId(Long consultationId, Long medecinId);
    public Optional<Consultation> getConsultationById(Long consultationId);
    public void updateConsultation(Long consultationId, StatusConsultation statusConsultation);
}
