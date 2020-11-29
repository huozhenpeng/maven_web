package session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/sessionServlet02")
public class SessionServlet02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止获取请求参数乱码
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("username");
        String passWord = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String value = (String) session.getAttribute("checkCode");
        if (value != null && value.equalsIgnoreCase(checkCode)) {
            //成功
            if ("张三".equals(userName) && "123456".equals(passWord)) {
                session.setAttribute("username", userName);
                //重定向到success.jsp
                resp.sendRedirect(req.getContextPath() + "/login/success.jsp");
            } else {
                req.setAttribute("error", "用户名或密码错误");
                req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("codeError", "验证码错误");
            req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
        }
    }
}
