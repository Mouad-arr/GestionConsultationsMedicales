package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import our.ensaa.projet.dao.AdminRepository;
import our.ensaa.projet.entity.Admin;
import our.ensaa.projet.util.JpaUtil;

import java.util.Optional;

public class AdminDaoImpl implements AdminRepository {

    private final EntityManager entityManager;

    public AdminDaoImpl() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Optional<Admin> findByEmailAndPassword(String email, String motDePasse) {
        String jpql = """
                SELECT a FROM Admin a
                WHERE a.email = :email AND a.motDePasse = :motDePasse
                """;
        try {
            Admin admin = entityManager.createQuery(jpql, Admin.class)
                    .setParameter("email", email)
                    .setParameter("motDePasse", motDePasse)
                    .getSingleResult();
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
