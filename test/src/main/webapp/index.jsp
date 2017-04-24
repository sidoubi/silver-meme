<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2016/12/23
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%--<script type="text/javascript">--%>
        <%--function change()--%>
        <%--{--%>
            <%--//alert("1");--%>
            <%--//重新加载生成图片--%>
            <%--document.getElementById("myimg").src="/VerificationCode?"+new Date().getTime();--%>
            <%--//alert(document.getElementById("myimg").src);--%>
        <%--}--%>
    <%--</script>--%>
    <title>Insert title here</title>
</head>
<body>
<form action="/HelloWorld" method="get">
    输入用户名<input type="text" name="name"/><br/>
    请输入密码<input type="password" name="password"/><br/>
    请输入验证码<input type="text" name ="checkcode">
    <img src="/VerificationCode" onclick="change()" id="myimg" style="cursor:pointer"/> <br/>
    <input type="submit" value="注册"/>

    <input type="checkbox" name="baidu" checked="checked"/>百度
    <input type="checkbox" name="google"/>谷歌
    <input type="checkbox" name="xinlang"/>新浪

</form>

</body>
</html>
