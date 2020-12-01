package login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        if (user != null) {
            //可以往页面写一些东西
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("来自"+user.getAddress()+"的"+user.getUsername()+"欢迎您!");
        }
    }
}
