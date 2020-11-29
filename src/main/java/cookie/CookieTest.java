package cookie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/cookieTest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果有上次访问的cookie信息，那么显示 "欢迎您！您上次的登录时间是。。。"
        //如果没有，显示"这是您第一次登陆"
        resp.setCharacterEncoding("utf-8");
        Cookie[] cookies = req.getCookies();
        String lastTime = null;
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if ("LastTime".equals(cookie.getName())) {
                    lastTime = cookie.getValue();
                    break;
                }
            }

            if (lastTime != null) {
                resp.getWriter().write("欢迎您回来，您上次的访问日期是"+lastTime);
            } else {
                resp.getWriter().write("这是您第一次访问");
            }
        }
        //cookie同名会被覆盖
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String value = simpleDateFormat.format(new Date());
        Cookie cookie = new Cookie("LastTime", value);
        //cookie中如果涉及到中文乱码问题可以使用URLEncoder进行编解码再使用
        resp.addCookie(cookie);

        for (int i = 0; i < cookies.length; i++) {
            System.out.println("name-->"+cookies[i].getName()+"   value--->"+cookies[i].getValue());
        }
        //name-->JSESSIONID   value--->06E4EB20014C0FAD4E00B0819D9BB8CB
        //name-->LastTime   value--->2020年11月26日22:20:05

    }
}
