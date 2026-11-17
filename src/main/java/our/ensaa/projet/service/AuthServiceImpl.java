package our.ensaa.projet.service;

import our.ensaa.projet.entity.Admin;
import our.ensaa.projet.entity.Medecin;

public class AuthServiceImpl implements AuthService {

    private final MedcinTraitement medecinService;
    private final AdminInterface adminService;

    public AuthServiceImpl() {
        this.medecinService = new MedecinService();
        this.adminService = new AdminService();
    }

    @Override
    public Medecin getMedecin(String email, String password) {
        Medecin medecin= medecinService.getMedecinByEmail(email);
        if(medecin != null && medecin.getMotDePasse().equals(password)){
            return medecin;
        }
        return null;
    }

    @Override
    public Admin getAdmin(String email, String password) {
        Admin admin = adminService.getAdminByEmail(email);
        if(admin != null && admin.getMotDePasse().equals(password)) {
            return admin;
        }
        return null;
    }
}
