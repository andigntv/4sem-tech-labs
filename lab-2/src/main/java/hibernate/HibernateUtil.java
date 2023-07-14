package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final HibernateUtil Instance = new HibernateUtil();
    private final SessionFactory sessionFactory;
    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable exception) {
            System.err.println("Initial SessionFactory creation failed." + exception);
            throw new ExceptionInInitializerError(exception);
        }
    }
    public static HibernateUtil GetInstance() { return Instance; }

    public Session createSession() {
        return sessionFactory.openSession();
    }
}
