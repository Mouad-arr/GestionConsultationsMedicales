package our.ensaa.projet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String medicament;

    /** Dosage instructions, e.g. "1 comprime matin et soir". */
    @Column(nullable = false, length = 100)
    private String posologie;

    /** Treatment duration in days. */
    private Integer dureeJours;

    @Column(length = 300)
    private String instructions;

    /** Owning side of the many-to-one relation with Diagnostic. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "diagnostic_id", nullable = false)
    private Diagnostic diagnostic;

    public Prescription() {
    }

    public Prescription(String medicament, String posologie, Integer dureeJours, String instructions) {
        this.medicament = medicament;
        this.posologie = posologie;
        this.dureeJours = dureeJours;
        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public Integer getDureeJours() {
        return dureeJours;
    }

    public void setDureeJours(Integer dureeJours) {
        this.dureeJours = dureeJours;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }
}
