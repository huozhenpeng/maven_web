package filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class ShopCarFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        if (uri.contains("/login.jsp")
                || uri.contains("/loginServlet")
                || uri.contains("/index.html")
                || uri.contains("/index.jsp")
                || uri.contains("/css/")
                || uri.contains("/js/")
                || uri.contains("/fonts/")
                || uri.contains("/checkCodeServlet")) {
            //放行
            chain.doFilter(request, response);
        } else {
            Object user = httpServletRequest.getSession().getAttribute("user");
            if (user == null) {
                //转发到登陆页面进行登录操作
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }

        }


    }

    @Override
    public void destroy() {

    }
}
