package testing;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {
        jdbc.entities.Brand jdbcBrand = new jdbc.entities.Brand();
        jdbcBrand.setName("BMW");
        jdbcBrand.setDate(new Date(0));

        long start = System.nanoTime();

        for (int i = 0; i < 100; ++i)
            jdbc.services.BrandService.GetInstance().save(jdbcBrand);

        long finish = System.nanoTime();

        long jdbcInsert = finish - start;

        start = System.nanoTime();

        jdbc.services.BrandService.GetInstance().getAll();

        finish = System.nanoTime();

        long jdbcGetAll = finish - start;

        jdbc.services.BrandService.GetInstance().deleteAll();

        // end of jdbc testing

        start = System.nanoTime();

        for (int i = 0; i < 100; ++i) {
            hibernate.entities.Brand hibernateBrand = new hibernate.entities.Brand();
            hibernateBrand.setName("Mercedes");
            hibernateBrand.setDate(new Date(0));

            hibernate.services.BrandService.GetInstance().save(hibernateBrand);
        }
        finish = System.nanoTime();

        long hibernateInsert = finish - start;

        start = System.nanoTime();

        hibernate.services.BrandService.GetInstance().getAll();

        finish = System.nanoTime();

        long hibernateGetAll = finish - start;

        hibernate.services.BrandService.GetInstance().deleteAll();

        // end of hibernate testing

        mybatis.entities.Brand mybatisBrand = new mybatis.entities.Brand();
        mybatisBrand.setName("Audi");
        mybatisBrand.setDate(new Date(0));

        start = System.nanoTime();

        for (int i = 0; i < 100; ++i)
            mybatis.services.BrandService.GetInstance().save(mybatisBrand);

        finish = System.nanoTime();

        long mybatisInsert = finish - start;

        start = System.nanoTime();

        mybatis.services.BrandService.GetInstance().getAll();

        finish = System.nanoTime();

        long mybatisGetAll = finish - start;

        mybatis.services.BrandService.GetInstance().deleteAll();

        // end of mybatis testing

        System.out.println("JDBC insert time: " + (float)jdbcInsert / 1000000000 + "s");
        System.out.println("JDBC get time: " + (float)jdbcGetAll / 1000000000 + "s");
        System.out.println("Hibernate insert time: " + (float)hibernateInsert / 1000000000 + "s");
        System.out.println("Hibernate get time: " + (float)hibernateGetAll / 1000000000 + "s");
        System.out.println("MyBatis insert time: " + (float)mybatisInsert / 1000000000 + "s");
        System.out.println("MyBatis " + (float)mybatisGetAll / 1000000000 + "s");

    }
}