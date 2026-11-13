package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import java.util.Optional;

public interface MedecinRepository{

    public Optional<Medecin> getMedecinById(Long medecinId);

}
