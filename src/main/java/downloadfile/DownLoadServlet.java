package downloadfile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/downloadServlet")
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要下载的文件名称
        String fileName = req.getParameter("filename");
        ServletContext servletContext = req.getServletContext();

        String mime = servletContext.getMimeType(fileName);
        resp.setHeader("content-type", mime);

        //乱码问题
        String agent = req.getHeader("user-agent");
        String resultName = DownLoadUtils.getFileName(agent, fileName);
        resp.setHeader("content-disposition", "attachment;filename=" + resultName);



        String realPath = servletContext.getRealPath("/img/" + fileName);
        FileInputStream fileInputStream = new FileInputStream(realPath);
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = fileInputStream.read(buff)) != -1) {
            servletOutputStream.write(buff, 0, len);
        }
        fileInputStream.close();
    }
}
