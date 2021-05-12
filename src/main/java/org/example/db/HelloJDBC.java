package org.example.db;


import java.sql.ResultSetMetaData;

public class HelloJDBC {
    public static void main(String[] args) {
        DBUtils db = new DBUtils("localhost", 3306, "root", "root");
        if (db.connected()) {
            System.out.println("已连接");
            // 创建库表
            db.execute("CREATE DATABASE IF NOT EXISTS `test_db`;");
            System.out.println("建库");
            db.execute("CREATE TABLE IF NOT EXISTS `test_db`.`test_tbl` ( id BIGINT, name VARCHAR(32), PRIMARY KEY(id) );");
            System.out.println("建表");

            // 查询表结构
            DBUtils.MetaInfo metaInfo = db.getTableMetaData("`test_db`.`test_tbl`");
            final String placeHolders = DBUtils.placeHolders(metaInfo.getColumnCount());

            // 插入
            db.update("INSERT INTO `test_db`.`test_tbl` VALUES(" + placeHolders + ");", ps -> {
                ps.clearBatch();
                // 1
                ps.setInt(1, 1);
                ps.setString(2, "qwe");
                ps.addBatch();
                // 2
                ps.setInt(1, 2);
                ps.setString(2, "asd");
                ps.addBatch();
                // 3
                ps.setInt(1, 3);
                ps.setString(2, "zxc");
                ps.addBatch();
                //
                ps.executeBatch();
            });
            System.out.println("插入");

            // 查询
            db.query("SELECT * FROM `test_db`.`test_tbl`;", rs -> {
                ResultSetMetaData meta = rs.getMetaData();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.println(meta.getColumnTypeName(i));
                }
                //
                while (rs.next()) {
                    System.out.printf("%d,%s\n",
                            rs.getInt(1),
                            rs.getString(2)
                    );
                }
            });

            // 删除库表
            db.execute("DROP TABLE IF EXISTS `test_db`.`test_tbl`;");
            System.out.println("删表");
            db.execute("DROP DATABASE IF EXISTS `test_db`;");
            System.out.println("删库");

            // 断开连接
            db.disconnect();
        } else {
            System.out.println("连接失败");
        }
    }
}
