<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="login.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: huozhenpeng
  Date: 2020/11/30
  Time: 6:59 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${3 > 4} <br/>
    <%-- 可以忽略当前el表达式 --%>
    \${3 > 4}

    <h3> 算术运算符 </h3>
    ${3 + 4} <br/>
    ${3 - 4} <br/>
    ${3 / 4} <br/>
    ${3 div 4} <br/>
    ${3 % 4} <br/>
    ${3 mod 4} <br/>

    <h3> 比较运算符 </h3>
    ${3 == 4} <br/>


    <h3>逻辑运算符</h3>
    ${3 > 4  && 3 < 4} <br/>
    ${3 > 4  and 3 < 4} <br/>

    <h4>empty运算符,判断字符串、集合、数组是否为空</h4>

    <%

        String str = "";
        request.setAttribute("str", str);

        List list = new ArrayList<>();
        request.setAttribute("list", list);

        session.setAttribute("name", "张三");
        request.setAttribute("name", "李四");

    %>

    ${not empty list}
    ${not empty str}

    ${requestScope.name}
    ${sessionScope.name}
    <%--默认从小范围到大范围搜索值 pageScope requestScope sessionScope applicationScope--%>
    ${name}


    <h4>域对象中存储对象、List、Map</h4>

    <%
        User user = new User();
        user.setUserName("david");
        user.setAddress("北京");
        request.setAttribute("u", user);

        List<String> lists = new ArrayList();
        lists.add("111");
        lists.add("222");
        lists.add("333");
        request.setAttribute("lists", lists);

        Map<String, User> map = new HashMap<>();
        map.put("user", user);
        request.setAttribute("map", map);



    %>


    ${requestScope.u.userName} <br/>
    ${requestScope.lists} <br/>
    ${requestScope.lists.get(0)} <br/>
    ${requestScope.map.user} <br/>
    ${requestScope.map.user.userName} <br/>


    <%--el隐式对象--%>
    <%--el有11个隐式对象，pageContext是其中的一个--%>
    <%--利用pageContext可以获取jsp其他8个内置对象--%>
    ${pageContext.request.contextPath} <br/>



</body>
</html>
