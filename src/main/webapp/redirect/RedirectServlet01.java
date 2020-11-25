package main.webapp.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/redirect01")
public class RedirectServlet01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("请求01Servlet");
        //重定向
//        resp.setStatus(302);
//        resp.setHeader("location", "/Web_Maven_war_exploded/redirect02");

        //简单写法
//        resp.sendRedirect("/Web_Maven_war_exploded/redirect02");
        //虚拟目录建议动态获取
        String path = req.getContextPath();
        System.out.println("虚拟目录："+path);
        resp.sendRedirect(path + "/redirect02");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
