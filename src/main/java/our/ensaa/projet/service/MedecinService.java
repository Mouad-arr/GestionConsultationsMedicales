package our.ensaa.projet.service;

import our.ensaa.projet.dao.ConsultationRepository;
import our.ensaa.projet.dao.DiagnosticRepository;
import our.ensaa.projet.dao.MedecinRepository;
import our.ensaa.projet.dao.daoImpl.ConsultationDaoImpl;
import our.ensaa.projet.dao.daoImpl.DiagnosticDaoImpl;
import our.ensaa.projet.dao.daoImpl.MedecinDaoImpl;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Diagnostic;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.StatusConsultation;

import java.util.List;

public class MedecinService implements MedcinTraitement {

    private final MedecinRepository medecinRepository;
    private final ConsultationRepository consultationRepository;
    private final DiagnosticRepository diagnosticRepository;



    public MedecinService() {
        this.consultationRepository = new ConsultationDaoImpl();
        this.medecinRepository = new MedecinDaoImpl();
        this.diagnosticRepository = new DiagnosticDaoImpl();
    }

    @Override
    public List<Consultation> getHistoriqueConsultationsByMedcinId(Long medcinId) {
        if(medcinId == null) {
            throw new IllegalArgumentException("medcinId cannot be null");
        }
        return consultationRepository.findHistoryByMedecinId(medcinId);
    }

    @Override
    public List<Consultation> getUpcomingConsultationsByMedcinId(Long medcinId) {
        if(medcinId == null) {
            throw new IllegalArgumentException("medcinId cannot be null");
        }
        return consultationRepository.findUpcomingByMedecinId(medcinId);
    }

    @Override
    public Medecin getInfoMedicinById(Long medecinId) {
        if(medecinId == null) {
            throw new IllegalArgumentException("medecinId cannot be null");
        }
        return medecinRepository.getMedecinById(medecinId).orElse(null);
    }

    @Override
    public List<Consultation> getLesTroisPremiersUpcomingConsultationsByMedecinId(Long medecinId) {
        if(medecinId == null) {
            throw new IllegalArgumentException("medecinId cannot be null");
        }
        return this.getUpcomingConsultationsByMedcinId(medecinId).stream().limit(3).toList();
    }
    @Override
    public List<Consultation> getLesTroisDerniersConsultationsTerminerByMedecinId(Long medecinId) {
        if(medecinId == null) {
            throw new IllegalArgumentException("medecinId cannot be null");
        }
        return this.getHistoriqueConsultationsByMedcinId(medecinId).stream().limit(3).toList();
    }
    @Override
    public Consultation getConsultationById(Long consultationId, Long medcinId) {
        if(consultationId == null || medcinId == null) {
            throw new IllegalArgumentException("consultationId and medcinId cannot be null");
        }
        return consultationRepository.getConsultationByIdAndMedecinId(consultationId, medcinId).orElse(null);
    }

    @Override
    public void updateDiagnostic(Long consultationId, Diagnostic diagnostic) {
        if(consultationId == null || diagnostic == null) {
            throw new IllegalArgumentException("consultationId and diagnostic cannot be null");
        }
        Consultation consultation = consultationRepository.getConsultationById(consultationId).orElse(null);
        diagnosticRepository.saveDiagnostic(consultationId, diagnostic);
        if(consultation == null) {
            throw new IllegalArgumentException("Consultation with id " + consultationId + " not found");
        } else {
            consultationRepository.updateConsultation(consultationId, StatusConsultation.TERMINEE);
        }
    }

    @Override
    public void updateConsultation(Long id,StatusConsultation statusConsultation) {
        if(id==null || statusConsultation == null) {
            throw new IllegalArgumentException("consultationId and status cannot be null");
        }
        consultationRepository.updateConsultation(id, statusConsultation);
    }

    @Override
    public Medecin getMedecinByEmail(String email) {
        if(email == null || email.isEmpty()) {
            return null;
        }
        return medecinRepository.getMedecinByEmail(email);
    }
}
