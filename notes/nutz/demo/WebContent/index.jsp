<%@ page import="example.nutzbook.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- 使用<%=...%>可以从Java语句返回一个值到Html --%>
    <title><%="Nutz" + "Book"%> </title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<%
    String show_login;
    String show_info;
    if (null==session.getAttribute("id")) {
        show_login = "block";
        show_info = "none";
    } else {
        show_login = "none";
        show_info = "block";
    }
%>
<body>
    <div style="margin: 10px 10% 10px 10%;">
        <form id="loginForm" action="#" method="post" style="display: <%=show_login%>;">
            <div class="form-group">
                <label for="account">Account</label>
                <input class="form-control" id="account" name="username" type="text" value="admin">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input class="form-control" id="password" name="password" type="password" value="admin">
            </div>
            <button id="login_button" type="submit" class="btn btn-default">Submit</button>
        </form>

        <div id="infoTable" style="display: <%=show_info%>;">
            <p>ID:<%=session.getAttribute("id")%></p>
            <p>Name:<%=session.getAttribute("name")%></p>
            <a class="btn btn-default" href="${base}/user/logout">Logout</a>
        </div>
    </div>
</body>
<script>
    const url_base = '${base}';
    const url_login = "/user/login";
    $(function () {
        $("#login_button").click(function () {
            $.ajax({
                url: url_base + url_login,
                type: "POST",
                data: $('#loginForm').serialize(),
                error: function (request) {
                    alert("Connection error");
                },
                dataType: "json",
                success: function (data) {
                    if (data === true) {
                        location.reload();
                    } else {
                        alert("登陆失败，请检查账号密码！")
                    }
                }
            });
            return false;
        });
    });
</script>
</html>