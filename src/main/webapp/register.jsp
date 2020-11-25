<%--
  Created by IntelliJ IDEA.
  User: huozhenpeng
  Date: 2020/11/25
  Time: 10:00 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        window.onload = function () {
            const img = document.getElementById("checkCode");
            img.onclick = function () {
                //防止浏览器取缓存
                const date = new Date().getTime();
                img.src = "/Web_Maven_war_exploded/checkCodeServlet?"+date;
            }
        }
    </script>
    <title>Title</title>
</head>
<body>

<img id="checkCode" src="/Web_Maven_war_exploded/checkCodeServlet">
<a id="change" href="">看不清，换一张</a>

</body>
</html>
