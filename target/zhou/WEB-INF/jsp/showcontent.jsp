<%--
  Created by IntelliJ IDEA.
  User: Zhou
  Date: 2017/6/19
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">


    <title>学生选课管理系统</title>

    <link href="<c:url value="/resources/favicon.ico" />">

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="<c:url value="/resources/assets/css/ie10-viewport-bug-workaround.css"/>" rel="stylesheet">


    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js" type="text/javascript"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<c:url value="/resources/jquery.watable.js" />" type="text/javascript" charset="utf-8"></script>
    <link rel='stylesheet' href="http://netdna.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">

    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.css">

    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/footable/css/footable.bootstrap.css"/>" />
    <script type="text/javascript" src="<c:url value="/resources/footable/js/footable.js"/>"></script>
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
                    <li><a href="/singleselect">单表查询</a></li>
                    <li><a href="/multiselect">多表查询</a></li>
                    <li><a href="/update">更改数据</a></li>
                </ul>

            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>

    <!-- Main component for a primary marketing message or call to action -->
    <div>
        <table class="table" data-sorting="true">
            <thead>
                <c:forEach var="s" items="${sessionScope.namelist}">
                    <th data-breakpoints="xs" >${s}</th>
                </c:forEach>
            </thead>
            <tbody>
                <c:forEach var="ss" items="${sessionScope.data}">
                    <tr>
                        <c:forEach var="s" items="${ss}">
                            <td>${s}</td>
                        </c:forEach>
                    </tr>

                </c:forEach>

            </tbody>
        </table>
    </div>

</div> <!-- /container -->
<script>
    jQuery(function($){
        $('.table').footable();
    });
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="<c:url value="/resources/assets/js/vendor/jquery.min.js"/>""<\/script>')</script>
<script src="<c:url value="/resources/dist/js/bootstrap.min.js"/>"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/resources/assets/js/ie10-viewport-bug-workaround.js"/>"></script>
</body>
</html>
