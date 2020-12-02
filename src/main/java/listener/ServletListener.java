package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 监听ServletContext对象的创建和销毁，通常用作缓存
 * https://www.cnblogs.com/jpfss/p/9435789.html
 *
 * ServletContext 被 Servlet 程序用来与 Web 容器通信。例如写日志，转发请求。每一个 Web 应用程序含有一个Context，被Web应用内的各个程序共享。因为Context可以用来保存资源并且共享，所以我所知道的 ServletContext 的最大应用是Web缓存----把不经常更改的内容读入内存，所以服务器响应请求的时候就不需要进行慢速的磁盘I/O了。
 *
 * ServletContextListener 是 ServletContext 的监听者，如果 ServletContext 发生变化，如服务器启动时 ServletContext 被创建，服务器关闭时 ServletContext 将要被销毁。
 *
 * 在JSP文件中，application 是 ServletContext 的实例，由JSP容器默认创建。Servlet 中调用 getServletContext()方法得到 ServletContext 的实例。
 *
 * 我们使用缓存的思路大概是：
 *
 * 服务器启动时，ServletContextListener 的 contextInitialized()方法被调用，所以在里面创建好缓存。可以从文件中或者从数据库中读取取缓存内容生成类，用 ervletContext.setAttribute()方法将缓存类保存在 ServletContext 的实例中。
 *
 * 程序使用 ServletContext.getAttribute()读取缓存。如果是 JSP，使用a pplication.getAttribute()。如果是 Servlet，使用 getServletContext().getAttribute()。如果缓存发生变化(如访问计数)，你可以同时更改缓存和文件/数据库。或者你等 变化积累到一定程序再保存，也可以在下一步保存。
 *
 * 服务器将要关闭时，ServletContextListener 的 contextDestroyed()方法被调用，所以在里面保存缓存的更改。将更改后的缓存保存回文件或者数据库，更新原来的内容。
 */
@WebListener
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext创建了");
        ServletContext servletContext = sce.getServletContext();
        //初始化参数在web.xml中配置的，没法通过注解设置
        String value1 = servletContext.getInitParameter("param1");
        String value2 = servletContext.getInitParameter("param2");

        System.out.println("value1:"+value1);
        System.out.println("value2:"+value2);

        String cache = servletContext.getInitParameter("cache");
        String path = ServletListener.class.getResource(cache).getPath();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(path));
            byte[] buf = new byte[3];
//            nam
//            e
//            v
//            alu
//            e
            int length = 0;
            while((length = fileInputStream.read(buf)) != -1){
                System.out.println(new String(buf,0,length));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream !=  null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext销毁了");
    }
}
