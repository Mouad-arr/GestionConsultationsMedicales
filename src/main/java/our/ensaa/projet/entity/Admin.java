package our.ensaa.projet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "admin")
public class Admin extends Person {

    @Column(nullable = false)
    private String motDePasse;

    /** Date the administrator account was created. */
    private LocalDate dateCreation;

    public Admin() {
        super();
        this.dateCreation = LocalDate.now();
    }

    public Admin(String nom, String prenom, String email, String telephone,
                 LocalDate dateNaissance, String motDePasse, Role role) {
        super(nom, prenom, email, telephone, dateNaissance, role );
        this.dateCreation = LocalDate.now();
        this.motDePasse = motDePasse;
    }


    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
