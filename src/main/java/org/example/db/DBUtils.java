package org.example.db;

import java.sql.*;


public class DBUtils {
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
            String jdbcUrl, String jdbcUser, String jdbcPassword
    ) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }

    interface SqlQueryCallBack {
        void run(ResultSet rs) throws SQLException;
    }

    interface SqlUpdateCallBack {
        void run(PreparedStatement ps) throws SQLException;
    }

    protected Connection conn;

    public DBUtils(Connection conn) {
        this.conn = conn;
    }

    public DBUtils(String url, String user, String password) {
        this.conn = getConnection(url, user, password);
    }

    public DBUtils(String host, int port, String user, String password) {
        this.conn = getConnection(String.format("jdbc:mysql://%s:%d", host, port), user, password);
    }

    public DBUtils(String host, int port, String dbname, String user, String password) {
        this.conn = getConnection(String.format("jdbc:mysql://%s:%d/%s", host, port, dbname), user, password);
    }

    public void disconnect() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean connected() {
        return null != this.conn;
    }

    /**
     * 查询数据
     *
     * @param sql      SQL语句
     * @param callback 回调方法
     */
    public void query(String sql, SqlQueryCallBack callback) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(sql);
            callback.run(rs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 执行SQL，不考虑响应结果及影响范围
     *
     * @param sql SQL语句
     */
    public void execute(String sql) {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 插入/更新 数据
     *
     * @param sql      SQL语句
     * @param callback 回调方法
     */
    public void update(String sql, SqlUpdateCallBack callback) {
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement(sql);
            callback.run(ps);
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
    }
}
