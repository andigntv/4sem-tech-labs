package mybatis.mappers;

import mybatis.entities.Brand;
import mybatis.entities.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CarMapper {
    final String insert = "INSERT INTO Cars (name, length, width, body, brandId) " +
            "VALUES (#{name}, #{length}, #{width}, #{body}, #{brandId})";
    final String deleteById = "DELETE FROM Cars WHERE id = #{id}";
    final String deleteAll = "DELETE FROM Cars";
    final String update = "UPDATE Cars SET name = #{name}, length = #{length}, width = #{width}, body = #{body}, brandId = #{brandId}" +
            " WHERE id = #{id}";
    final String getById = "SELECT * FROM Cars WHERE id = #{id}";
    final String getAll = "SELECT * FROM Cars";
    final String getAllByBrandId = "SELECT * FROM Cars WHERE brandId = #{id}";

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Car entity);

    @Delete(deleteById)
    void deleteById(long id);

    @Delete(deleteAll)
    void deleteAll();

    @Update(update)
    void update(Car entity);

    @Select(getById)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "length", column = "length"),
            @Result(property = "width", column = "width"),
            @Result(property = "body", column = "body"),
            @Result(property = "brandId", column = "brandId"),
    })
    Car getById(long id);

    @Select(getAll)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "length", column = "length"),
            @Result(property = "width", column = "width"),
            @Result(property = "body", column = "body"),
            @Result(property = "brandId", column = "brandId"),
    })
    List<Car> getAll();

    @Select(getAllByBrandId)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "length", column = "length"),
            @Result(property = "width", column = "width"),
            @Result(property = "body", column = "body"),
            @Result(property = "brandId", column = "brandId"),
    })
    List<Car> getAllByBrandId(long id);
}
