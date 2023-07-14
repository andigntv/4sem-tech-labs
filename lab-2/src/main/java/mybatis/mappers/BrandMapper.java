package mybatis.mappers;

import mybatis.entities.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    final String insert = "INSERT INTO Brands (name, date) VALUES (#{name}, #{date})";
    final String deleteById = "DELETE FROM Brands WHERE id = #{id}";
    final String deleteAll = "DELETE FROM Brands";
    final String update = "UPDATE Brands SET name = #{name}, date = #{date} WHERE id = #{id}";
    final String getById = "SELECT * FROM Brands WHERE id = #{id}";
    final String getAll = "SELECT * FROM Brands";

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Brand entity);

    @Delete(deleteById)
    void deleteById(long id);

    @Delete(deleteAll)
    void deleteAll();

    @Update(update)
    void update(Brand entity);

    @Select(getById)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "date", column = "date")
    })
    Brand getById(long id);

    @Select(getAll)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "date", column = "date")
    })
    List<Brand> getAll();
}
