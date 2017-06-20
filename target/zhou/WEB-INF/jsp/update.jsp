<%--
  Created by IntelliJ IDEA.
  User: Zhou
  Date: 2017/6/20
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<c:url value="/resources/favicon.ico"/>">

    <title>学生选课管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- FooTable Bootstrap CSS -->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container">

    <!-- Static navbar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">学生选课管理系统</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/singselesct">单表查询</a></li>
                    <li><a href="/multiselect">多表查询</a></li>
                    <li><a href="/update">更改数据</a></li>
                </ul>

            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <form action="/modifyselect" method="post">
            <div class="input-group">
                <label>请选择要更改的表：</label>
                <br>
                <input type="radio" name="table" value="课程">课程<br>
                <input type="radio" name="table" value="学生">学生<br>
                <input type="radio" name="table" value="教师">教师<br>
                <input type="radio" name="table" value="选课表">选课表<br>
                <input type="radio" name="table" value="授课表">授课表<br>
                <input type="radio" name="table" value="院系表">院系表<br>
                <input type="radio" name="table" value="专业表">专业表<br>

                <!--
                <select name="table" class="form-control">
                    <option value="课程" selected>课程</option>
                    <option value="学生">学生</option>
                    <option value="教师">教师</option>
                    <option value="选课表">选课表</option>
                    <option value="授课表">授课表</option>
                    <option value="院系表">院系表</option>
                    <option value="专业表">专业表</option>
                </select>-->
                <input type="submit" class=" form-control btn btn-default" value="提交"/>
            </div>

        </form>
    </div>
</div> <!-- /container -->

<!-- Placed at the end of the document so the pages load faster -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- Add in any FooTable dependencies we may need -->
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
<!-- Add in FooTable itself -->
<!-- Initialize FooTable -->
</body>
</html>
