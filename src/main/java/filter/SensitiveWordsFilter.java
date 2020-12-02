package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class SensitiveWordsFilter implements Filter {
    private List<String> words = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //加载敏感词汇文件
        String filePath = SensitiveWordsFilter.class.getResource("/sensitive.txt").getPath();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void doFilter(final ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //动态代理技术，对request进行代理，过滤request中的敏感词
        ServletRequest proxy = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(request, args);
                    if (value != null) {
                        for (String str : words) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                } else {
                    return method.invoke(request, args);
                }
            }
        });

        //放行
        chain.doFilter(proxy, response);
    }

    @Override
    public void destroy() {

    }
}
