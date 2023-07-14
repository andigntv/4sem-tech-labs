package jdbc.services;
import jdbc.JDBCUtil;
import jdbc.entities.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    private static final CarService Instance = new CarService();
    private CarService() {}
    public static CarService GetInstance() { return Instance; }
    private Connection connect() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/lab-2\", \"postgres\", \"password\"");
        } catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
            throw exception;
        }
    }
    public Car save(Car entity) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String insert = "INSERT INTO Cars (name, length, width, body, brandId) values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getLength());
            preparedStatement.setInt(3, entity.getWidth());
            preparedStatement.setString(4, entity.getBody());
            preparedStatement.setLong(5, entity.getBrandId());

            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
        return entity;
    }
    public void deleteById(long id) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String delete = "DELETE FROM Cars WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
    }
    public void deleteByEntity(Car entity) { deleteById(entity.getId()); }
    public void deleteAll() {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String delete = "DELETE FROM Cars";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
    }
    public Car update(Car entity) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String update = "UPDATE Cars SET name = ?, length = ?, width = ?, body = ?, brandId = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getLength());
            preparedStatement.setInt(3, entity.getWidth());
            preparedStatement.setString(4, entity.getBody());
            preparedStatement.setLong(5, entity.getBrandId());
            preparedStatement.setLong(6, entity.getId());

            preparedStatement.execute();
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
        }
        return entity;
    }
    public Car getById(long id) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String select = "SELECT name, date FROM Cars WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            return new Car(id,
                    result.getString("name"),
                    result.getInt("length"),
                    result.getInt("width"),
                    result.getString("body"),
                    result.getLong("brandId"));
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
            return new Car(-1, "zaporojec", 0, 0, "kvadrat", -1);
        }
    }
    public List<Car> getAll() {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String select = "SELECT * FROM Cars";

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Car> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(new Car(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("length"),
                        resultSet.getInt("width"),
                        resultSet.getString("body"),
                        resultSet.getLong("brandId")));
            }
            return result;
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
            List<Car> result = new ArrayList<>();
            result.add(new Car(-1, "zaporojec", 0, 0, "kvadrat", -1));
            return result;
        }
    }

    public List<Car> getAllByBrandId(long id) {
        try (Connection connection = JDBCUtil.GetInstance().connect()) {
            String select = "SELECT * FROM Brands WHERE BrandId = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Car> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(new Car(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("length"),
                        resultSet.getInt("width"),
                        resultSet.getString("body"),
                        resultSet.getLong("brandId")));
            }
            return result;
        }
        catch (SQLException exception) {
            System.err.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage());
            List<Car> result = new ArrayList<>();
            result.add(new Car(-1, "zaporojec", 0, 0, "kvadrat", -1));
            return result;
        }
    }
}
