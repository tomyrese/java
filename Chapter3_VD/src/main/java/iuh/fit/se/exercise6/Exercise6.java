package iuh.fit.se.exercise6;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Exercise6 {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            List<String> tables = new ArrayList<>();
            DatabaseMetaData metaData = conn.getMetaData();

            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());
            System.out.println("JDBC Major Version: " + metaData.getJDBCMajorVersion());
            System.out.println("JDBC Minor Version: " + metaData.getJDBCMinorVersion());

            // Chỉ lấy TABLE, bỏ VIEW
            try (ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
                while (rs.next()) {
                    String tableName = rs.getString("TABLE_NAME");
                    tables.add(tableName);
                }
            }

            System.out.println(tables);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
