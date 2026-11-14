package our.ensaa.projet.service;

import our.ensaa.projet.entity.Medecin;

import java.util.List;

public interface AdminInterface {
    public List<Medecin> getAllMedecins() ;
    public List<Medecin> getMedecinsFiltered(String search, String specialite) ;

}
