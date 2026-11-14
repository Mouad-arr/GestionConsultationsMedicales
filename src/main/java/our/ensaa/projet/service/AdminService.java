package our.ensaa.projet.service;

import our.ensaa.projet.dao.AdminRepository;
import our.ensaa.projet.dao.ConsultationRepository;
import our.ensaa.projet.dao.MedecinRepository;
import our.ensaa.projet.dao.PatientRepository;
import our.ensaa.projet.dao.daoImpl.AdminDaoImpl;
import our.ensaa.projet.dao.daoImpl.ConsultationDaoImpl;
import our.ensaa.projet.dao.daoImpl.MedecinDaoImpl;
import our.ensaa.projet.dao.daoImpl.PatientDaoImpl;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.Patient;
import our.ensaa.projet.entity.Role;
import our.ensaa.projet.entity.StatusConsultation;

import java.time.LocalDate;
import java.util.List;

/**
 * Business logic for the administration console: managing medecins, patients
 * and the global consultation views. Decoupled from {@link MedcinTraitement},
 * which is the medecin-facing contract.
 */
public class AdminService implements AdminInterface{

    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final AdminRepository adminRepository;

    public AdminService() {
        this.medecinRepository = new MedecinDaoImpl();
        this.patientRepository = new PatientDaoImpl();
        this.consultationRepository = new ConsultationDaoImpl();
        this.adminRepository = new AdminDaoImpl();
    }

    // ---------------------------------------------------------------------
    //  Médecins
    // ---------------------------------------------------------------------
    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public List<Medecin> getMedecinsFiltered(String search, String specialite) {
        return medecinRepository.search(search, specialite);
    }

    @Override
    public Medecin getMedecinById(Long id) {
        return medecinRepository.getMedecinById(id).orElse(null);
    }

    @Override
    public List<String> getAllSpecialites() {
        return medecinRepository.findAllSpecialites();
    }

    @Override
    public void saveMedecin(Medecin medecin) {
        medecin.setRole(Role.MEDECIN);
        medecinRepository.save(medecin);
    }

    @Override
    public void updateMedecin(Medecin medecin) {
        medecinRepository.update(medecin);
    }

    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.delete(id);
    }

    // ---------------------------------------------------------------------
    //  Patients
    // ---------------------------------------------------------------------

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsFiltered(String search) {
        return patientRepository.search(search);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public void savePatient(Patient patient) {
        patient.setRole(Role.PATIENT);
        patientRepository.save(patient);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientRepository.update(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.delete(id);
    }

    // ---------------------------------------------------------------------
    //  Consultations (global, read-only from the admin console)
    // ---------------------------------------------------------------------

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public List<Consultation> getConsultationsDuJour() {
        return consultationRepository.findConsultationsDuJour();
    }

    @Override
    public List<Consultation> getConsultationsFiltered(String statut, String date, String medecin) {
        return consultationRepository.search(parseStatus(statut), parseDate(date), medecin);
    }

    private StatusConsultation parseStatus(String statut) {
        if (statut == null || statut.isBlank()) {
            return null;
        }
        try {
            return StatusConsultation.valueOf(statut);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private LocalDate parseDate(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

}
