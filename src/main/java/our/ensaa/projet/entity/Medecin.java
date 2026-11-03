package our.ensaa.projet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "medecin")
public class Medecin extends Person {

    /** Speciality, e.g. "Cardiologie", "Pediatrie"... */
    @Column(length = 80)
    private String specialite;

    /** Official order number (numero ordre des medecins). */
    @Column(unique = true, length = 20)
    private String numeroOrdre;

    @Column(nullable = false)
    private String motDePasse;

    public Medecin() {
        super();
    }


    public Medecin(String nom, String prenom, String email, String telephone,
                   LocalDate dateNaissance, String motDePasse,
                   String specialite, String numeroOrdre) {
        super(nom, prenom, email, telephone, dateNaissance);
        this.specialite = specialite;
        this.numeroOrdre = numeroOrdre;
        this.motDePasse = motDePasse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(String numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
