package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Diagnostic;

public interface DiagnosticRepository {

    void saveDiagnostic(Long consultationId, Diagnostic diagnostic);
}
