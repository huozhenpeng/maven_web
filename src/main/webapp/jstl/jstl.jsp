<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.Student" %>

<%--
  Created by IntelliJ IDEA.
  User: huozhenpeng
  Date: 2020/11/30
  Time: 9:48 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3> if标签</h3>

<c:if test="true">
    <h3>是真</h3>
</c:if>

<%
    request.setAttribute("number", 4);
%>

<c:if test="${requestScope.number % 2 == 0}">
    <h3>是偶数</h3>
</c:if>

<h3> choose标签</h3>

<c:choose>
    <c:when test="%{number == 1}">
        <h5>星期一</h5>
    </c:when>
    <c:when test="%{number == 2}">
        <h5>星期二</h5>
    </c:when>
    <c:when test="%{number == 3}">
        <h5>星期三</h5>
    </c:when>
    <c:otherwise>
        <h5>数字输入有误</h5>
    </c:otherwise>
</c:choose>

<h3> choose标签</h3>

<%--i值：3 index:3 count:1--%>
<%--i值：4 index:4 count:2--%>
<%--i值：5 index:5 count:3--%>
<%--i值：6 index:6 count:4--%>
<%--i值：7 index:7 count:5--%>
<%--i值：8 index:8 count:6--%>
<%--i值：9 index:9 count:7--%>
<%--i值：10 index:10 count:8--%>
<c:forEach begin="3" end="10" step="1" var="i" varStatus="status">
    i值：${i}         index:${status.index}       count:${status.count} <br/>
</c:forEach>

<%
    List<String> list = new ArrayList<>();
    list.add("111");
    list.add("222");
    list.add("333");
    request.setAttribute("list", list);
%>
<%--index:0 count:1 str:111--%>
<%--index:1 count:2 str:222--%>
<%--index:2 count:3 str:333--%>
<c:forEach var="str" varStatus="status" items="${requestScope.list}">
    index:${status.index}   count:${status.count}  str:${str} <br/>

</c:forEach>

<%
    List<Student> users = new ArrayList();
    users.add(new Student("111", "123", "cn"));
    users.add(new Student("222", "456", "es"));
    users.add(new Student("333", "789", "th"));
    request.setAttribute("sts", users);
%>

<%--做个表格--%>
<table border="1" width="500" align="center" >
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>密码</th>
        <th>地址</th>
    </tr>

    <c:forEach var="u" varStatus="status" items="${sts}">
        <c:if test="${status.index % 2 == 0}">
            <tr bgcolor="#faebd7">
                <td>${status.count}</td>
                <td>${u.username}</td>
                <td>${u.address}</td>
                <td>${u.password}</td>
            </tr>
        </c:if>
        <c:if test="${status.index % 2 != 0}">
            <tr bgcolor="#7fffd4">
                <td>${status.count}</td>
                <td>${u.username}</td>
                <td>${u.address}</td>
                <td>${u.password}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>






</body>
</html>
