package our.ensaa.projet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

/**
 * Base class shared by every user of the application (Medecin, Patient, Admin).
 * Annotated with @MappedSuperclass: the columns are stored in the table of
 * each concrete subclass, Person itself has no table.
 */
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(nullable = false, length = 30)
    private Role role;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telephone;

    private LocalDate dateNaissance;

    /** Hashed password used by the future login feature. */
//    @Column(nullable = false)
//    private String motDePasse;

    protected Person() {
    }

    protected Person(String nom, String prenom, String email, String telephone,
                     LocalDate dateNaissance, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.role=role;
//        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

//    public String getMotDePasse() {
//        return motDePasse;
//    }
//
//    public void setMotDePasse(String motDePasse) {
//        this.motDePasse = motDePasse;
//    }
}
