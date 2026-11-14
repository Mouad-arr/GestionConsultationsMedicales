package our.ensaa.projet.service;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.Patient;
import our.ensaa.projet.entity.StatusConsultation;

import java.time.LocalDate;
import java.util.List;

public interface AdminInterface {
    public List<Medecin> getAllMedecins() ;
    public List<Medecin> getMedecinsFiltered(String search, String specialite) ;
    public Medecin getMedecinById(Long id) ;
    public List<String> getAllSpecialites() ;
    public void saveMedecin(Medecin medecin) ;
    public void updateMedecin(Medecin medecin) ;
    public void deleteMedecin(Long id) ;

    public List<Patient> getAllPatients() ;
    public List<Patient> getPatientsFiltered(String search) ;
    public Patient getPatientById(Long id) ;
    public void savePatient(Patient patient) ;
    public void updatePatient(Patient patient) ;
    public void deletePatient(Long id) ;

    public List<Consultation> getAllConsultations() ;
    public List<Consultation> getConsultationsDuJour() ;
    public List<Consultation> getConsultationsFiltered(StatusConsultation statut, LocalDate date, String medecin) ;
}
