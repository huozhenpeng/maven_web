<%--
  Created by IntelliJ IDEA.
  User: huozhenpeng
  Date: 2020/11/25
  Time: 6:55 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<h5>这种方式是直接下载的，或者直接可以打开（浏览器可以解析），不满足需求，需求是要弹框</h5>
<a href="/Web_Maven_war_exploded/img/image1.jpeg">浏览图片</a>

<br/>

<a href="/Web_Maven_war_exploded/downloadServlet?filename=image1.jpeg">弹出下载框</a>

<br/>

<a href="/Web_Maven_war_exploded/downloadServlet?filename=图片.jpeg">弹出下载框(中文)</a>
</body>
</html>
