import java.sql.*;

interface SQLCallBack {
    void onBatchAdd(PreparedStatement ps) throws SQLException;
}

public class demoJDBC {

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

    public static int executeUpdate(String sql, SQLCallBack callback) throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?characterEncoding=utf8",
                "root",
                "");
        PreparedStatement ps = conn.prepareStatement(sql);
        callback.onBatchAdd(ps);
        // ps.addBatch();
        // ps.executeBatch();
        // ps.clearBatch();
        int size = ps.executeUpdate();
        ps.close();
        conn.close();
        return size;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ResultSet rs = executeQuery("SELECT Host,User,Password FROM mysql.user WHERE User='root';");
        while (rs.next()) {
            System.out.printf("%s,%s,%s\n",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
            );
        }
        // rs.close();

        int size = executeUpdate("INSERT INTO nutzbook.tbl_user VALUES(?,?,?);", ps -> {
            ps.setInt(1, 13);
            ps.setString(2, "ewq");
            ps.setString(3, "123");
            ps.addBatch();
            ps.executeBatch();
        });
        System.out.println(size);
    }
}
