package our.ensaa.projet.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.HashMap;
import java.util.Map;

@WebListener
public class JpaListener implements ServletContextListener {

    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Map<String, String> properties = new HashMap<>();

        // Read directly from the Environment Variables passed by IntelliJ
        properties.put("jakarta.persistence.jdbc.url", System.getenv("DB_URL"));
        properties.put("jakarta.persistence.jdbc.user", System.getenv("DB_USERNAME"));
        properties.put("jakarta.persistence.jdbc.password", System.getenv("DB_PASSWORD"));

        // 3. Tell Hibernate to use the connection pool
        properties.put("hibernate.connection.provider_class",
                "org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl");

        // 4. Start Hibernate
        try {
            emf = Persistence.createEntityManagerFactory("jeePU", properties);
            System.out.println("✅ Database connected successfully!");
        } catch (Exception e) {
            System.err.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
}