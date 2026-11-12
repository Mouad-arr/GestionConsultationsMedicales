package our.ensaa.projet.service;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;

import java.util.List;

public class AdminService implements MedcinTraitement {

    @Override
    public List<Consultation> getHistoriqueConsultationsByMedcinId(Long medcinId) {
        return null; // Implementation
    }
    @Override
    public List<Consultation> getUpcomingConsultationsByMedcinId(Long medcinId) {
        return null; // Implementation
    }
    @Override
    public Medecin getInfoMedicinById(Long medecinId) {
        // Implementation
        return null;
    }
    @Override
    public List<Consultation> getLesTroisPremiersUpcomingConsultationsByMedecinId(Long medecinId) {
        // Implementation
        return null;
    }
    @Override
    public List<Consultation> getLesTroisDerniersConsultationsTerminerByMedecinId(Long medecinId) {
        // Implementation
        return null;
    }
    @Override
    public Consultation getConsultationById(Long consultationId, Long medcinId) {
        // Implementation
        return null;
    }
}
