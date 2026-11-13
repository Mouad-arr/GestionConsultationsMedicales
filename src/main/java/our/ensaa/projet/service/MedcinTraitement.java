package our.ensaa.projet.service;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.StatusConsultation;

import java.util.List;

public interface MedcinTraitement {

    public List<Consultation> getHistoriqueConsultationsByMedcinId(Long medcinId);
    public List<Consultation> getUpcomingConsultationsByMedcinId(Long medcinId);
    public Medecin getInfoMedicinById(Long medecinId);
    public List<Consultation> getLesTroisPremiersUpcomingConsultationsByMedecinId(Long medecinId);
    public List<Consultation> getLesTroisDerniersConsultationsTerminerByMedecinId(Long medecinId);
    public Consultation getConsultationById(Long consultationId,Long medcinId);
    public void updateDiagnostic(Long consultationId, String diagnostic);
    public void updateConsultation(Long consultationId, StatusConsultation status);
}
