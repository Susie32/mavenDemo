package main.webapp.webTestDome.userAction;

/**
 * Created by Susie on 2017/3/28.
 */

import main.webapp.webTestDome.dbcpConn.DBCPAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userRegist")
public class userRegist extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public userRegist(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        String sql=new String();
        String text;
        int status=0;
        String userName =new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8");
        String password=new String(request.getParameter("password"));
        try{
            sql="select password from userBaseInfo where userName='"+userName+"'";
            System.out.println("will execute sql:"+sql);
            //向数据库查询是否有已注册用户名相关信息
            String result=null;
            DBCPAction db=new DBCPAction();
            result=db.queryString(sql);
            //List<HashMap> result=main.webapp.webTestDome.dbcpConn.DBCPAction.queryList(sql);
            System.out.println("String result="+result.toString());
            if(result.equals("[]")!=true)
            {
                text="用户已存在！";
                status=0;
            }
            else
            {
                sql="insert userBaseInfo(userName,password) values('"+userName+"','"+password+"');";
                System.out.println("will execute sql:"+sql);
                //向数据库写入用户注册信息
                if(new DBCPAction().update(sql)!=0) {
                    text = "注册成功！";
                    status = 1;
                    //out.println(sql);
                }
                else {
                    text="注册失败:数据库用户信息写入失败！";
                }
            }
        }
        catch (Exception e)
        {
            text="注册失败!";
            status=0;
            System.out.println(sql);
        }
        response.getWriter().write(status+text);
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
