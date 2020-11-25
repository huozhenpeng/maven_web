<%--
  Created by IntelliJ IDEA.
  User: huozhenpeng
  Date: 2020/11/24
  Time: 10:51 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>超链接属于get请求</h1>
<h5>相对路径：找到当前资源和目标资源的相对位置关系</h5>
</br>
当前资源：http://localhost:8181/Web_Maven_war_exploded/redirect.jsp
</br>
目标资源：http://localhost:8181/Web_Maven_war_exploded/redirect01
</br>
<a href="./redirect01">重定向(相对路径1)</a>
</br>
<a href="redirect01">重定向(相对路径2)</a>

</br>
<h5>绝对路径：可以确定唯一资源</h5>
</br>
<a href="/Web_Maven_war_exploded/redirect01">重定向(绝对路径)</a>


</body>
</html>
