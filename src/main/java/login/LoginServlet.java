package login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这个要删除，要不然请求转发会失败
        //java.lang.IllegalStateException: 提交响应后无法转发
//        super.doPost(req, resp);
        //1、设置编码
        req.setCharacterEncoding("utf-8");
        //2、获取请求参数
        String userName = req.getParameter("username");
        String passWord = req.getParameter("password");
        User user = new User();
        user.setUsername(userName);
        user.setPassword(passWord);

        UserDao userDao = new UserDao();
        User result = userDao.login(user);
        if (result == null) {
            //登陆失败
            req.getRequestDispatcher("/failedServlet").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", result);
            String referer = req.getHeader("Referer");
            System.out.println("referer:"+referer);
            if (referer != null) {
                String [] arrays = referer.split(req.getContextPath());
                referer = arrays[1];
            }
            if (referer != null) {
//                req.getRequestDispatcher(referer).forward(req,resp);
                resp.sendRedirect(req.getContextPath() + referer);
            } else {
                req.getRequestDispatcher("/successServlet").forward(req,resp);
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
