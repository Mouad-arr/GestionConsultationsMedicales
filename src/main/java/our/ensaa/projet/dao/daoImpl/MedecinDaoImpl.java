package our.ensaa.projet.dao.daoImpl;

import jakarta.persistence.EntityManager;
import our.ensaa.projet.dao.MedecinRepository;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.util.JpaUtil;

import java.util.Optional;

public class MedecinDaoImpl implements MedecinRepository {

    private final EntityManager entityManager;

    public MedecinDaoImpl() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Optional<Medecin> getMedecinById(Long medecinId) {
        String jpql = """
                SELECT m FROM Medecin m WHERE m.id = :medecinId
                """;
        return Optional.ofNullable(entityManager.createQuery(jpql, Medecin.class)
                .setParameter("medecinId", medecinId)
                .getSingleResult());
    }

}
