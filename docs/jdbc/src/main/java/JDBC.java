import java.sql.*;

public class JDBC {
    public static ResultSet executeQuery(String sql) throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/",
                "root",
                "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        // rs.close();
        stmt.close();
        conn.close();
        return rs;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ResultSet rs = executeQuery("SHOW DATABASES;");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
    }
}
