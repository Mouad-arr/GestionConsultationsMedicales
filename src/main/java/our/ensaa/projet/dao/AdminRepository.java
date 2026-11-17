package our.ensaa.projet.dao;

import our.ensaa.projet.entity.Admin;

import java.util.Optional;

public interface AdminRepository {

    /**
     * Looks up an admin by email + password (for the future login feature).
     * Password comparison is done in plain text for now; replace with a hash
     * check once authentication is implemented properly.
     */
    Optional<Admin> findByEmailAndPassword(String email, String motDePasse);
    Admin getAdminByEmail(String email);

}
