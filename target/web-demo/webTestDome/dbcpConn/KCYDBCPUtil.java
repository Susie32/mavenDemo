package main.webapp.webTestDome.dbcpConn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.lang.Class;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * DBCP配置类
 * @author SUN
 */
public class KCYDBCPUtil {

    private static Properties properties = new Properties();
    private static DataSource dataSource;
    //加载DBCP配置文件
    static{
        try{
            //Static方法中获取properties文件方法 http://blog.csdn.net/chengly0129/article/details/49493297
            InputStream is=KCYDBCPUtil.class.getResourceAsStream("dbcp.properties");
            //FileInputStream is = new FileInputStream(new File("dbcp.properties"));
            properties.load(is);
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //从连接池中获取一个连接
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }
}