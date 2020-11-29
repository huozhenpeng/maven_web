<%--
  Created by IntelliJ IDEA.
  User: huozhenpeng
  Date: 2020/11/29
  Time: 4:18 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div><%= request.getSession().getAttribute("username") %>,欢迎您！</div>
</body>
</html>
