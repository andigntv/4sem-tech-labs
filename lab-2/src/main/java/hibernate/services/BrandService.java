package hibernate.services;

import hibernate.HibernateUtil;
import hibernate.entities.Brand;
import hibernate.entities.Car;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BrandService {
    private static final BrandService Instance = new BrandService();
    private BrandService() {}
    public static BrandService GetInstance() { return Instance; }

    public Brand save(Brand entity) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return entity;
    }

    public void deleteById(long id) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            List<Car> cars = CarService.GetInstance().getAllByBrandId(id);

            for (var car : cars)
                CarService.GetInstance().deleteByEntity(car);

            session.beginTransaction();
            session.remove(getById(id));
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public void deleteByEntity(Brand entity) { deleteById(entity.getId()); }
    public void deleteAll() {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            CarService.GetInstance().deleteAll();

            session.beginTransaction();
            session.createQuery("DELETE FROM Brand").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public Brand update(Brand entity) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return entity;
    }
    public Brand getById(long id) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            session.beginTransaction();
            Brand result = session.get(Brand.class, id);
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Throwable exception) {
            System.err.println(exception);
            return new Brand();
        }
    }

    public List<Brand> getAll() {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
            criteria.from(Brand.class);
            return session.createQuery(criteria).getResultList();
        } catch (Throwable exception) {
            System.err.println(exception);
            return new ArrayList<>();
        }
    }
}
