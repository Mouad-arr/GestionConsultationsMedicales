package our.ensaa.projet.entity;

/**
 * Lifecycle state of a {@link Consultation}.
 * <p>
 * Values can be adjusted to match the business workflow;
 * the class diagram only defines the enum itself.
 */
public enum StatusConsultation {
    Planifie,
    TERMINEE,
    ANNULEE
}
