package our.ensaa.projet.service;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;

import java.util.List;

public interface MedcinTraitement {

    public List<Consultation> getHistoriqueConsultationsByMedcinId(Long medcinId);
    public List<Consultation> getUpcomingConsultationsByMedcinId(Long medcinId);
    public Medecin getInfoMedicinById(Long medecinId);
    public List<Consultation> getLesTroisPremiersUpcomingConsultationsByMedecinId(Long medecinId);
    public List<Consultation> getLesTroisDerniersConsultationsTerminerByMedecinId(Long medecinId);
    public Consultation getConsultationById(Long consultationId,Long medcinId);
}
