package cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/setCookieServlet")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("name1", "张三");
        //设置cooke存活时间
//        cookie1.setMaxAge(30);//将cookie持久化到硬盘，30秒后会自动删除cookie文件
//        cookie1.setMaxAge(-1);//默认值 存在于浏览器内存中，浏览器关闭后会清除
//        cookie1.setMaxAge(0);//删除Cookie
        Cookie cookie2 = new Cookie("name2", "david");
        //当前服务器下部署的所有项目共享cookie
//        cookie2.setPath("/");
        //当前项目共享cookie，也就是设置为当前项目的虚拟目录
        cookie2.setPath(req.getContextPath());
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}
