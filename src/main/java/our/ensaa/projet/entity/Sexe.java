package our.ensaa.projet.entity;

public enum Sexe {
    HOMME("Homme"),
    FEMME("Femme");

    private final String libelle;

    Sexe(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
