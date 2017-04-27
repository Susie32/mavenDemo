package main.webapp.webTestDome.userAction;

import main.webapp.webTestDome.dbcpConn.DBCPAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Susie on 2017/4/2.
 */

@WebServlet("/listTopic")
public class listTopic extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public listTopic(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型，中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sql=new String();
        String text=new String();

        try {

            String userName=new String(request.getParameter("userName"));
            sql="select id,title,content,status,date from topic where userName='"+userName+"'";
            System.out.println("will execute sql:"+sql);
            //向数据库查询信息
            String result=null;
            result=new DBCPAction().queryString(sql);
            if(result!=null){
                /* //返回一个table
                   text="<tr><th>ID</th>" +
                        "<th>DATE</th>" +
                        "<th>TITLE</th>" +
                        "<th>STATUS</th>"+
                        "<th>ACTION</th>"  ;
                for (HashMap oneLine:result) {
                    text=text+"<tr><td>"+oneLine.get("id").toString()+"</td>"+
                            "<td>"+oneLine.get("date").toString()+"</td>"+
                            "<td>"+oneLine.get("title").toString()+"</td>"+
                            "<td>"+oneLine.get("status").toString()+"</td>" +
                            "<td><input type='button' class='smallButton' value='edit' onclick=edit("+oneLine.get("id").toString()+")></td>" +
                            "</tr>";
                }*/
                text=result.toString();
            }
            else if(result==null)
            {
                text=null;
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
