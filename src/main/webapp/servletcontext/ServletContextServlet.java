package main.webapp.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servletContext")
public class ServletContextServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Servlet
        //两种方式：
        //1
        ServletContext servletContext1 = req.getServletContext();
        //2
        ServletContext servletContext2 = getServletContext();


        //主要功能
        //1、获取文件的MIME类型 text/html
        System.out.println(servletContext1.getMimeType("./index.html"));

        //2、共享数据 ServletContext对象范围：所有用户所有请求的数据

        //3、获取文件的真实路径（在服务器中的路径）
        System.out.println(servletContext1.getRealPath("/WEB-INF/a.txt"));
        //  /Users/huozhenpeng/Documents/web/Web_Maven/target/Web_Maven/WEB-INF/a.txt

        System.out.println(servletContext1.getRealPath("/index.jsp"));
        //  /Users/huozhenpeng/Documents/web/Web_Maven/target/Web_Maven/index.jsp


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
