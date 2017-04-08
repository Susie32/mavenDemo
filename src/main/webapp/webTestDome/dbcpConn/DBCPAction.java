package main.webapp.webTestDome.dbcpConn;

import jdk.nashorn.api.scripting.JSObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Susie on 2017/3/27.
 */

public class DBCPAction {
    //通过DBCP连接池写数据库
    public static int update(String sql){
        //String sql = "insert into dbcp values ('101')";
        int updateItemNo=0;

        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行："+sql+"========");
            Statement stat = conn.createStatement();
            updateItemNo=stat.executeUpdate(sql);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
        return updateItemNo;
    }
    public static List<HashMap> queryList(String sql){
        //String sql = "insert into dbcp values ('101')";
        ResultSet rs=null;
        List<HashMap> list=new ArrayList<HashMap>();
        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行："+sql+"========");
            //createStatement 对ResultSet的影响：http://blog.csdn.net/garfielder007/article/details/52053960
            Statement stat = conn.createStatement();
            rs=stat.executeQuery(sql);
            list=convertList(rs);
            //String s=resultSetToJson(rs);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
            return list;
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
        return list;
    }

    public static String queryString(String sql){
        //String sql = "insert into dbcp values ('101')";
        ResultSet rs=null;
        String str=new String();
        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行："+sql+"========");
            //createStatement 对ResultSet的影响：http://blog.csdn.net/garfielder007/article/details/52053960
            Statement stat = conn.createStatement();
            rs=stat.executeQuery(sql);
            //list=convertList(rs);
            str=resultSetToJson(rs);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
            return str;
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
        System.out.println("数据库查询结果为："+str);
        return str;
    }

    private static List<HashMap> convertList(ResultSet rs) throws SQLException{
        List<HashMap> list=new ArrayList<HashMap>();
        ResultSetMetaData md=rs.getMetaData();
        int columnCount=md.getColumnCount();
        while (rs.next()){
            HashMap rowData=new HashMap();
            for(int i=1;i<=columnCount;i++){
                rowData.put(md.getColumnName(i),rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    private static String resultSetToJson(ResultSet rs) throws SQLException,JSONException
    {
        JSONArray array = new JSONArray();
        // json数组
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
            //array.put(jsonObj);
        }

        return array.toString();
    }
}
