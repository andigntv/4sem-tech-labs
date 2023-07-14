package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final JDBCUtil Instance = new JDBCUtil();
    private JDBCUtil() {}
    public static JDBCUtil GetInstance() {return Instance;}
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab-2", "postgres", "password");
    }
}
