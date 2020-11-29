<%--
  Created by IntelliJ IDEA.
  User: huozhenpeng
  Date: 2020/11/26
  Time: 10:38 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src = "/Web_Maven_war_exploded/checkCodeServlet?time=" + new Date().getTime();
            }
        }
    </script>
</head>
<body>

    <form action="/Web_Maven_war_exploded/sessionServlet02" method="post">

        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <img id="img" src="/Web_Maven_war_exploded/checkCodeServlet">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="登陆">
                </td>
            </tr>
        </table>

    </form>


    <div><%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %></div>
    <div><%= request.getAttribute("codeError") == null ? "" : request.getAttribute("codeError") %></div>


</body>
</html>
