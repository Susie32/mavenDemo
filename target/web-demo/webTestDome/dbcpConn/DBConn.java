package main.webapp.webTestDome.dbcpConn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
    private static Connection conn = null;

    //获取一个数据库连接
    public  Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/test";
            System.out.println("dbUrl:"+dbUrl);
            conn = DriverManager.getConnection(dbUrl, "root", "123456");
            System.out.println("========数据库连接成功========");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========数据库连接失败========");
            return null;
        }
        return conn;
    }
}