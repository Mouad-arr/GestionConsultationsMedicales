package our.ensaa.projet.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diagnostic")
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_diagnostic")
    private LocalDate dateDiagnostic;

    @Column(nullable = false, length = 1000)
    private String description;

    /** Owning side of the one-to-one relation with Consultation. */
    @OneToOne(optional = false)
    @JoinColumn(name = "consultation_id", nullable = false, unique = true)
    private Consultation consultation;

    @OneToMany(mappedBy = "diagnostic",
               cascade = CascadeType.ALL, orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Prescription> prescriptions = new ArrayList<>();

    public Diagnostic() {
    }

    public Diagnostic(LocalDate dateDiagnostic, String description, Consultation consultation) {
        this.dateDiagnostic = dateDiagnostic;
        this.description = description;
        this.consultation = consultation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDiagnostic() {
        return dateDiagnostic;
    }

    public void setDateDiagnostic(LocalDate dateDiagnostic) {
        this.dateDiagnostic = dateDiagnostic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

//    /** Keeps both sides of the relationship consistent. */
//    public void addPrescription(Prescription prescription) {
//        prescriptions.add(prescription);
//        prescription.setDiagnostic(this);
//    }
//
//    public void removePrescription(Prescription prescription) {
//        prescriptions.remove(prescription);
//        prescription.setDiagnostic(null);
//    }
}
