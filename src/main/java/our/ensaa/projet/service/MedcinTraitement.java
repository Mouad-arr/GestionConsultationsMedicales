package our.ensaa.projet.service;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Diagnostic;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.StatusConsultation;

import java.util.List;

public interface MedcinTraitement {

    List<Consultation> getHistoriqueConsultationsByMedcinId(Long medcinId);
    List<Consultation> getUpcomingConsultationsByMedcinId(Long medcinId);
    Medecin getInfoMedicinById(Long medecinId);
    List<Consultation> getLesTroisPremiersUpcomingConsultationsByMedecinId(Long medecinId);
    List<Consultation> getLesTroisDerniersConsultationsTerminerByMedecinId(Long medecinId);
    Consultation getConsultationById(Long consultationId,Long medcinId);
    void updateDiagnostic(Long consultationId, Diagnostic diagnostic);
    void updateConsultation(Long consultationId, StatusConsultation status);
    Medecin getMedecinByEmail(String email);
}
