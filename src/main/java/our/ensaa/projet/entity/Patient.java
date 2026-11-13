package our.ensaa.projet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
public class Patient extends Person {

    /** Medical record number, generated when the patient is registered. */
    @Column(unique = true, length = 20)
    private String numeroDossier;

    @Column(length = 5)
    private String groupeSanguin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Sexe sexe;

    /** Carte d'identité nationale (unique). */
    @Column(nullable = false, unique = true, length = 20)
    private String cin;

    @Column(length = 255)
    private String adresse;



    public Patient() {
        super();
    }

    public Patient(String nom, String prenom, String email, String telephone,
                   LocalDate dateNaissance,
                   String numeroDossier, String groupeSanguin, Role role) {
        super(nom, prenom, email, telephone, dateNaissance, role);
        this.numeroDossier = numeroDossier;
        this.groupeSanguin = groupeSanguin;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
