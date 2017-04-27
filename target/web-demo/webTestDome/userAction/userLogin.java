package main.webapp.webTestDome.userAction;

import main.webapp.webTestDome.dbcpConn.DBCPAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import java.util.HashMap;

/**
 * Created by Susie on 2017/3/30.
 */

@WebServlet("/userLogin")
public class userLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public userLogin(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sql=new String();
        String text=null;
        try {
            //Handle Chinese words
            String userName =new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8");
            String password=request.getParameter("password");
            sql="select password from userBaseInfo where userName='"+userName+"'";
            System.out.println("will execute sql:"+sql);
            //向数据库查询用户信息
            DBCPAction db=new DBCPAction();
            List<HashMap> result=db.queryList(sql);
            if(result.size()!=0)
            {
                String sp=result.get(0).get("password").toString();
                System.out.println("The password of current user is:"+sp);
                System.out.println("The password of login user is:"+password);
                //==(object) and equal(value) are not same!
                if(password.equals(sp)) {
                    text="登录成功！";
                }
                else {
                    text="密码错误！";
                }
            }
            else
            {
                text="用户不存在！";
            }
        }
        catch (Exception e)
        {
            text="登陆异常!";
            System.out.println(sql);
        }
        System.out.print(text);
        out.write(text);
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
