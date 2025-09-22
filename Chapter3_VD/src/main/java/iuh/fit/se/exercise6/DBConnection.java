package iuh.fit.se.exercise6;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // MSSQL
    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=employees;encrypt=false;";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "reallyStrongPwd123";
    static {
        try {
            // Load driver chỉ một lần duy nhất
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
