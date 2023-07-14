package mybatis;

import mybatis.mappers.BrandMapper;
import mybatis.mappers.CarMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtil {
    private static final MyBatisUtil Instance = new MyBatisUtil();
    final private SqlSessionFactory sessionFactory;
    private MyBatisUtil() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            sessionFactory.getConfiguration().addMapper(BrandMapper.class);
            sessionFactory.getConfiguration().addMapper(CarMapper.class);
        } catch (Throwable e) {
            System.err.println("Initial SqlSessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static MyBatisUtil GetInstance() { return Instance; }

    public SqlSession createSession() {
        return sessionFactory.openSession();
    }
}
