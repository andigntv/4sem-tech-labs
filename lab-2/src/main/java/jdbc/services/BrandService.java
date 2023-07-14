package jdbc.services;

import jdbc.JDBCUtil;
import jdbc.entities.Brand;
import jdbc.entities.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandService {
    private static final BrandService Instance = new BrandService();
    private BrandService() {}
    public static BrandService GetInstance() { return Instance; }
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab-2", "postgres", "password");
    }
    public Brand save(Brand entity) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String insert = "INSERT INTO Brands (name, date) values (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getDate());

            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
        return entity;
    }
    public void deleteById(long id) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            List<Car> cars = CarService.GetInstance().getAllByBrandId(id);

            for (var car : cars)
                CarService.GetInstance().deleteByEntity(car);

            String delete = "DELETE FROM Brands WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
    }
    public void deleteByEntity(Brand entity) { deleteById(entity.getId()); }
    public void deleteAll() {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            CarService.GetInstance().deleteAll();

            String delete = "DELETE FROM Brands";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
    }
    public Brand update(Brand entity) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String update = "UPDATE Brands SET name = ?, date = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getDate());
            preparedStatement.setLong(3, entity.getId());

            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
        return entity;
    }
    public Brand getById(long id) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String select = "SELECT name, date FROM Brands WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            return new Brand(id, result.getString("name"), result.getDate("date"));
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
            return new Brand(-1, "zaporojec", new Date(0));
        }
    }

    public List<Brand> getAll() {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String select = "SELECT * FROM Brands";

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Brand> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(new Brand(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date")));
            }
            return result;
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
            return new ArrayList<>();
        }
    }

}
