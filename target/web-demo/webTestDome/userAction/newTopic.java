package main.webapp.webTestDome.userAction;

/**
 * Created by Susie on 2017/3/31.
 */

import main.webapp.webTestDome.dbcpConn.DBCPAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet("/newTopic")
public class newTopic extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public newTopic(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sql=new String();
        String text=new String();
        try {
            //Handle Chinese words
            request.setCharacterEncoding("utf-8");
            String title =new String(request.getParameter("topicTitle"));
            String topicContent =new String(request.getParameter("topicContent"));
            String userName=new String(request.getParameter("userName").getBytes());
            String kinds=new String(request.getParameter("kinds"));
            String topicID=new String(request.getParameter("topicID"));
            if(kinds.equals("Submit"))
            {
                text = "发布";
            }
            else if(kinds.equals("Save"))
            {
                text="保存";
            }
            else
            {
                text="更新";
            }
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String date = dateFormat.format(now);
            if(topicID.equals("-1")){
                sql="insert topic(title,content,userName,status,date) values('"+title+"','"+topicContent+"','"+userName+"','"+kinds+"','"+date+"');";
            }
            else
            {
                sql="update topic set title='"+title+"',content='"+topicContent+"',userName='"+userName+"',status='"+kinds+"',date='"+date+"' where id='"+topicID+"'";
            }
            System.out.println("will execute sql:"+sql);
            //向数据库写信息
            if(new DBCPAction().update(sql)!=0){
                text=text+"成功！";
            }
            else
            {
                text=text+"失败！";
            }
        }
        catch (Exception e)
        {
            text=text+"异常!";
            System.out.println(sql);
        }
        out.write(text);
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


