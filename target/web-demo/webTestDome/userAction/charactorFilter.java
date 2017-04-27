package main.webapp.webTestDome.userAction;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Susie on 2017/4/6.
 */
public class charactorFilter implements Filter{
    String encoding=null;
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException{
        System.out.println("Coding change1");
        if(encoding!=null)
        {
            //设置字符编码
            System.out.println("Coding change2");
            request.setCharacterEncoding(encoding);
            response.setContentType("text/html;charset="+encoding);
        }
        chain.doFilter(request,response);
    }
    public void init(FilterConfig filterConfig) throws ServletException{
        encoding=filterConfig.getInitParameter("encoding");
        System.out.println("Coding WayInit");
    }
    public void destory(){
        encoding=null;
        System.out.println("Coding destory");
    }
}
