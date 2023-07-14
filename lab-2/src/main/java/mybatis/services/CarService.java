package mybatis.services;

import mybatis.MyBatisUtil;
import mybatis.entities.Car;
import mybatis.mappers.CarMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private static final CarService Instance = new CarService();
    private CarService() {}
    public static CarService GetInstance() { return Instance; }
    public Car save(Car entity) {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(CarMapper.class).insert(entity);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return entity;
    }

    public void deleteById(long id) {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(CarMapper.class).deleteById(id);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public void deleteByEntity(Car entity) { deleteById(entity.getId()); }
    public void deleteAll() {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(CarMapper.class).deleteAll();
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public Car update(Car entity) {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(CarMapper.class).update(entity);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return entity;
    }
    public Car getById(long id) {
        Car result = new Car();

        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            result = session.getMapper(CarMapper.class).getById(id);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }

        return result;
    }
    public List<Car> getAll() {
        List<Car> result = new ArrayList<>();

        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            result = session.getMapper(CarMapper.class).getAll();
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return result;
    }

    public List<Car> getAllByBrandId(long id) {
        List<Car> result = new ArrayList<>();

        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            result = session.getMapper(CarMapper.class).getAllByBrandId(id);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return result;
    }
}
