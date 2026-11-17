package our.ensaa.projet.service;

import our.ensaa.projet.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AdminInterface {
    List<Medecin> getAllMedecins() ;
    List<Medecin> getMedecinsFiltered(String search, String specialite) ;
    Medecin getMedecinById(Long id) ;
    List<String> getAllSpecialites() ;
    void saveMedecin(Medecin medecin) ;
    void updateMedecin(Medecin medecin) ;
    void deleteMedecin(Long id) ;

    List<Patient> getAllPatients() ;
    List<Patient> getPatientsFiltered(String search) ;
    Patient getPatientById(Long id) ;
    void savePatient(Patient patient) ;
    void updatePatient(Patient patient) ;
    void deletePatient(Long id) ;

    List<Consultation> getAllConsultations() ;
    List<Consultation> getConsultationsDuJour() ;
    List<Consultation> getConsultationsFiltered(StatusConsultation statut, LocalDate date, String medecin) ;
    void createConsultation(Long patientId, Long medecinId, LocalDateTime dateConsultation, String motif) ;

    Admin getAdminByEmail(String email);
}
