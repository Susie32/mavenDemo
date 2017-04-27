package main.webapp.webTestDome.userAction;

import main.webapp.webTestDome.dbcpConn.DBCPAction;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.Mock;
import org.easymock.internal.MocksControl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import sun.security.util.Password;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.*;

import static com.sun.javafx.fxml.expression.Expression.add;
import static com.sun.tools.doclint.Entity.or;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


/**
 * Created by Susie on 2017/4/13.
 */
public class userRegistTest {

    private userRegist servlet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private ServletContext mockServerletContext;
    private RequestDispatcher mockDispatcher;
    //private DBCPAction mockDBCPAction;
    private PrintWriter mockPrintWriter;
    private IMocksControl control=EasyMock.createControl();
    //List<HashMap> result=main.webapp.webTestDome.dbcpConn.DBCPAction.queryList(sql);

    @Before
    public void setUp() throws Exception {
        //http://www.cnblogs.com/soundcode/p/6441736.html

        mockRequest= control.createMock(HttpServletRequest.class);
        mockResponse=control.createMock(HttpServletResponse.class);
        mockServerletContext=control.createMock(ServletContext.class);
        mockDispatcher=control.createMock(RequestDispatcher.class);
        //mockDBCPAction=control.createMock(DBCPAction.class);
        servlet =new userRegist(){
            private static final long serialVersionUID=1L;
            public ServletContext getServletContext()
            {
                return mockServerletContext;
            }
        };
    }

    @Test
    public void registFailWithDueplicateUserName() throws Exception {
        EasyMock.expect(mockRequest.getParameter("userName")).andReturn("susie").times(2);
        mockResponse.setContentType((String)EasyMock.anyObject());
        EasyMock.expectLastCall().anyTimes();
        EasyMock.expect(mockResponse.getWriter()).andReturn(new PrintWriter(System.out,true)).anyTimes();
        EasyMock.expect(mockRequest.getParameter("password")).andReturn("111111").times(1);
        //String sql="select password from userBaseInfo where userName='susie'";
        //EasyMock.expect(mockDBCPAction.queryString(sql)).andReturn("111");
        //EasyMock.expect(mockDBCPAction.queryString(isA(String.class))).andReturn("111").anyTimes();
        //如果mock对象的方法是void，则需要使用expectLastCall()：
        //expectLastCall().andReturn("111");
        //EasyMock.replay(mockRequest,mockResponse);
        //如果Mock对象是通过IMocksControl接口提供的createMock方法生成的
        control.replay();
        servlet.doGet(mockRequest,mockResponse);

        //assertEquals
        System.out.println("------------------");
        //control.verify();
        //assertEquals("0用户已存在！",mockResponse.getWriter().toString());
        //EasyMock.verify(mockResponse)
        //EasyMock.verify(control);
    }

    @Test
    public void regFailWithDueUserName() throws  Exception
    {

    }


}