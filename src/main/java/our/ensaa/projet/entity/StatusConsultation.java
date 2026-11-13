package our.ensaa.projet.entity;

/**
 * Lifecycle state of a {@link Consultation}.
 * <p>
 * Values can be adjusted to match the business workflow;
 * the class diagram only defines the enum itself.
 */
public enum StatusConsultation {
    Planifie("Planifiée"),
    TERMINEE("Terminée"),
    ANNULEE("Annulée");

    private final String libelle;

    StatusConsultation(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
