package hibernate.services;


import hibernate.HibernateUtil;
import hibernate.entities.Brand;
import hibernate.entities.Car;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    private static final CarService Instance = new CarService();
    private CarService() {}
    public static CarService GetInstance() { return Instance; }

    public Car save(Car entity) {
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
            session.beginTransaction();
            session.remove(getById(id));
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public void deleteByEntity(Car entity) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public void deleteAll() {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public Car update(Car entity) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return entity;
    }
    public Car getById(long id) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            session.beginTransaction();
            Car result = session.get(Car.class, id);
            session.getTransaction().commit();
            return result;
        } catch (Throwable exception) {
            System.err.println(exception);
            return new Car();
        }
    }

    public List<Car> getAll() {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Car> criteria = builder.createQuery(Car.class);
            criteria.from(Car.class);
            return session.createQuery(criteria).getResultList();
        } catch (Throwable exception) {
            System.err.println(exception);
            return new ArrayList<>();
        }
    }
    public List<Car> getAllByBrandId(long id) {
        try (Session session = HibernateUtil.GetInstance().createSession()) {
            Brand entity = BrandService.GetInstance().getById(id);
            session.beginTransaction();
            //Query query = session.createQuery("FROM Car WHERE brand = :Param").setParameter("Param", entity);
            List<Car> result = session.createQuery("FROM Car WHERE brand = :Param").setParameter("Param", entity).getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Throwable exception) {
            System.err.println(exception);
            return new ArrayList<>();
        }
    }
}
