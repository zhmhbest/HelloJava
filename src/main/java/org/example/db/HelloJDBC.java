package org.example.db;

import java.sql.*;

interface SqlBatchAddCallBack {
    void run(PreparedStatement ps) throws SQLException;
}

public class HelloJDBC {
    static {
        try {
            // 注册JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            // Class.forName("com.mysql.jdbc.Driver");
            // Class.forName("com.github.housepower.jdbc.ClickHouseDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection(
            String host, int port, String dbname,
            String user, String password
    ) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s:%d/%s", host, port, dbname),
                    user, password
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }

    public static Connection getConnection(String dbname, String user, String password) {
        return getConnection("localhost", 3306, dbname, user, password);
    }

    public static ResultSet executeQuery(Connection conn, String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }


    public static int executeUpdate(Connection conn, String sql, SqlBatchAddCallBack callback) {
        PreparedStatement ps = null;
        int size = 0;
        try {
            ps = conn.prepareStatement(sql);
            callback.run(ps);
            size = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return size;
    }

    public static void main(String[] args) throws SQLException {
        // 查询
        Connection conn1 = getConnection("mysql", "root", "");
        if (null != conn1) {
            ResultSet rs = executeQuery(conn1, "SELECT Host,User,Password FROM user WHERE User='root';");
            ResultSetMetaData meta = rs.getMetaData();
            for (int columnIndex = 1; columnIndex <= meta.getColumnCount(); columnIndex++) {
                System.out.printf(
                        "Name:%s, TypeName:%s\n",
                        meta.getColumnName(columnIndex),
                        meta.getColumnTypeName(columnIndex)
                );
            }
            while (rs.next()) {
                System.out.printf("%s,%s,%s\n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
            rs.close();
            conn1.close();
        }

        // 修改
        Connection conn2 = getConnection("nutzbook", "root", "");
        if (null != conn2) {
            int size = executeUpdate(conn2, "INSERT INTO tbl_user VALUES(?,?,?);", ps -> {
                ps.clearBatch();
                // 1
                ps.setInt(1, 21);
                ps.setString(2, "qwe");
                ps.setString(3, "123");
                ps.addBatch();
                ps.executeBatch();
                ps.clearBatch();
                // 2
                ps.setInt(1, 22);
                ps.setString(2, "asd");
                ps.setString(3, "456");
                ps.addBatch();
                // ps.executeBatch();
            });
            System.out.println(size);
            conn2.close();
        }
    }
}
