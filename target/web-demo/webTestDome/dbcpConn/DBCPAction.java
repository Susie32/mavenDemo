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

public class DBCPAction implements DBCAActionI {
    //通过DBCP连接池写数据库
    //update返回值为更新条数updateItemNo
    public int update(String sql) {
        //String sql = "insert into dbcp values ('101')";
        int updateItemNo = 0;

        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行：" + sql + "========");
            Statement stat = conn.createStatement();
            updateItemNo = stat.executeUpdate(sql);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
        return updateItemNo;
    }

    //查询，返回值的格式为ResultSet()
    public ResultSet query(String sql) {
        //String sql = "insert into dbcp values ('101')";
        ResultSet rs = null;
        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行：" + sql + "========");
            //createStatement 对ResultSet的影响：http://blog.csdn.net/garfielder007/article/details/52053960
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            //String s=resultSetToJson(rs);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
            return rs;
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
        return rs;
    }

    public List<HashMap> queryList(String sql){
        List<HashMap> list=new ArrayList<HashMap>();
        ResultSet rs = null;
        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行：" + sql + "========");
            //createStatement 对ResultSet的影响：http://blog.csdn.net/garfielder007/article/details/52053960
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            list=convertList(rs);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
        return list;
    }
    //返回值为Json
    public String queryString(String sql){
        String str=new String();
        ResultSet rs = null;
        try {
            System.out.println("========尝试数据库连接========");
            Connection conn = KCYDBCPUtil.getConnection();
            System.out.println("========数据库连接成功========");
            System.out.println("========数据库即将执行：" + sql + "========");
            //createStatement 对ResultSet的影响：http://blog.csdn.net/garfielder007/article/details/52053960
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            str=resultSetToJson(rs);
            conn.commit();
            conn.close();
            System.out.println("========数据库执行成功========");
        } catch (SQLException e) {
            System.out.println("========数据库执行失败========");
            e.printStackTrace();
        }
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
