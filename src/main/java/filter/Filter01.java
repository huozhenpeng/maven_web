package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/no/*", dispatcherTypes = DispatcherType.REQUEST)//拦截所有资源
public class Filter01 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter01开始执行了");

        //放行
        chain.doFilter(request, response);

        System.out.println("filter01执行完了");
    }

    @Override
    public void destroy() {

    }
}
