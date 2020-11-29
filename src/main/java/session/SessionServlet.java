package session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/sessionServlet")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("name", "张三");
        //在Cookie学习中已经看到，会默认保存一条Cookie记录
        //name-->JSESSIONID   value--->06E4EB20014C0FAD4E00B0819D9BB8CB
        //但是这个在浏览器关闭后就销毁了
        //如果期望浏览器关闭，再打开后也能获取到之前的session,可以将cookie序列化到本地
        Cookie cookie = new Cookie("JSESSIONID", httpSession.getId());
        cookie.setMaxAge(60 * 60);//1小时
        resp.addCookie(cookie);
    }
}
