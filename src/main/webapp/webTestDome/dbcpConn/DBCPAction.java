package main.webapp.webTestDome.dbcpConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Susie on 2017/3/27.
 */
public class DBCPAction {
    //通过DBCP连接池写数据库
    public static void update(String sql){
        //String sql = "insert into dbcp values ('101')";
        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行："+sql+"========");
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
    }
    public static void query(String sql){
        //String sql = "insert into dbcp values ('101')";
        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行："+sql+"========");
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
    }
}
