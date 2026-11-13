package our.ensaa.projet.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Map;

public class JpaUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "jeePU",
            Map.of(
                    "jakarta.persistence.jdbc.url",      System.getenv("DB_URL"),
                    "jakarta.persistence.jdbc.user",     System.getenv("DB_USERNAME"),
                    "jakarta.persistence.jdbc.password", System.getenv("DB_PASSWORD")
            )
    );

    public static EntityManagerFactory getEmf() {
        return emf;
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}