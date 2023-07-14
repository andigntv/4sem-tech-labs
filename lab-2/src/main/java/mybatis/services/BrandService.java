package mybatis.services;

import mybatis.MyBatisUtil;
import mybatis.entities.Brand;
import mybatis.mappers.BrandMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class BrandService {
    private static final BrandService Instance = new BrandService();
    private BrandService() {}
    public static BrandService GetInstance() { return Instance; }
    public Brand save(Brand entity) {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(BrandMapper.class).insert(entity);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return entity;
    }

    public void deleteById(long id) {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(BrandMapper.class).deleteById(id);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public void deleteByEntity(Brand entity) { deleteById(entity.getId()); }
    public void deleteAll() {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(BrandMapper.class).deleteAll();
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
    }
    public Brand update(Brand entity) {
        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            session.getMapper(BrandMapper.class).update(entity);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return entity;
    }
    public Brand getById(long id) {
        Brand result = new Brand();

        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            result = session.getMapper(BrandMapper.class).getById(id);
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }

        return result;
    }
    public List<Brand> getAll() {
        List<Brand> result = new ArrayList<>();

        try (SqlSession session = MyBatisUtil.GetInstance().createSession()) {
            result = session.getMapper(BrandMapper.class).getAll();
            session.commit();
        } catch (Throwable exception) {
            System.err.println(exception);
        }
        return result;
    }
}
