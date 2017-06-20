<%--
  Created by IntelliJ IDEA.
  User: Zhou
  Date: 2017/6/17
  Time: 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>学生选课管理系统</title>

    <link href="<c:url value="/resources/assets/css/ie10-viewport-bug-workaround.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/signin.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/favicon.ico" />" >

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">



</head>
<body>

<div class="container">

    <form class="form-signin" action="/login" method="post">
        <h2 class="form-signin-heading">请登陆</h2>
        <label for="inputAccount" class="sr-only">用户名</label>
        <input type="text" id="inputAccount" name="inputAccount" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="密码" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
    </form>
</div>
</body>
</html>
