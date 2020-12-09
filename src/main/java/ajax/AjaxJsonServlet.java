package ajax;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/ajaxJsonServlet")
public class AjaxJsonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("username");

        //服务器响应的数据，在客户端使用时，要想当做json数据格式使用。有两种解决方案：
        //1. $.get(type):将最后一个参数type指定为"json"
        //2. 在服务器端设置MIME类型
        //response.setContentType("application/json;charset=utf-8");
        resp.setContentType("application/json;charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        if ("张三".equals(userName)) {
            map.put("userExist", true);
            map.put("msg", "用户名已经被使用，请更换一个");
        } else {
            map.put("userExist", false);
            map.put("msg", "用户名可用");
        }
        ObjectMapper mapper = new ObjectMapper();
        //将map转换为json并写会到客户端
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
