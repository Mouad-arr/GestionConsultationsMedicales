package our.ensaa.projet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
public class Patient extends Person {

    /** Medical record number, generated when the patient is registered. */
    @Column(unique = true, length = 20)
    private String numeroDossier;

    @Column(length = 5)
    private String groupeSanguin;



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
}
