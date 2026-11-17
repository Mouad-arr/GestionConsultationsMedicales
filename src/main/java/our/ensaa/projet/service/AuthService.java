package our.ensaa.projet.service;

import our.ensaa.projet.entity.Admin;
import our.ensaa.projet.entity.Medecin;

public interface AuthService {
    Medecin getMedecin(String email , String password);
    Admin getAdmin(String email , String password);
}
