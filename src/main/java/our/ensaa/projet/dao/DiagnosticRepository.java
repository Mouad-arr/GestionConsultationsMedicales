package our.ensaa.projet.dao;

public interface DiagnosticRepository {

    public void saveDiagnostic(Long consultationId, String description);
}
