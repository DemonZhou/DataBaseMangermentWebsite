<%--
  Created by IntelliJ IDEA.
  User: Zhou
  Date: 2017/6/17
  Time: 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="<c:url value="/resources/favicon.ico" />" >

    <title>学生选课管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/dist/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="<c:url value="/resources/assets/css/ie10-viewport-bug-workaround.css"/>" rel="stylesheet">

    <script src="<c:url value="/resources/assets/js/ie-emulation-modes-warning.js"/>"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

    <script>
        function changeselected(){
            $("#attrs").remove();
            var sel = $("#table").find("option:selected").text();
            var s = "";
            var attrs = new Array();
            if (sel == "课程"){
                attrs = new Array("课程id(Cid)","课程名(Cname)","学分(Credit)","开课人数(Cnum)","院系id(Did)","开课周(startweek)","结课周(endweek)","备注(Info");
            }else if(sel == "学生"){
                attrs = new Array("学生id(Sid)","学生名(Sname)","性别(Sex)","入学日期(recruitdate)");
            }else if(sel == "教师"){
                attrs = new Array("教师id(Tid)","教师名(Tname)","性别(Sex)","院系id（Did)");
            }else if(sel == "选课表"){
                attrs = new Array("学生id(Sid)","课程id(Cid)");
            }else if(sel == "授课表"){
                attrs = new Array("课程id(Cid)","教师id(Tid)");
            }else if(sel == "院系表") {
                attrs = new Array("院系id(Did)","院系名(Dname)");
            }else if(sel == "专业表"){
                attrs = new Array("专业id(Mid)","专业名(Mname)","院系id(Did)");
            }
            s += " <div id=\"attrs\" class=\"btn-group\" style=\"margin-bottom: 4px\">";
            for(var item in attrs)
            {
                s += "<button class=\"btn btn-default\" disabled=\"disabled\">";
                s += attrs[item];
                s += "</button>"
            }
            s += "</div>";
            $("#pre").append(s);
        }
        function addand() {
            var operators = new Array("=","<>",">","<",">=","<=","in","not in","is");
            var s1 = "<div class=\"input-group and\"><label class='form-control'>与</label><span class=\"input-group-addon\"></span>";
            var s2 = "<input type=\"text\" class=\"form-control\" width=\"30\"/>";
            var s3 = "<span class=\"input-group-addon\"></span>";
            var s4 = "<select class=\"form-control\">";
            for(var item in operators){
                s4 += "<option>";
                s4 += operators[item];
                s4 += "</option>";
            }
            s4 += "</select>";
            var s5 = "<span class=\"input-group-addon\"></span>";
            var s6 = "<input type=\"text\" class=\"form-control\"  width=\"30\"/>";
            var s7 = "</div>";
            $("form").append(s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6 + "\n" + s7)
        }
        function addor(){
            var operators = new Array("=","<>",">","<",">=","<=","in","not in","is");
            var s1 = "<div class=\"input-group or\" style=\"margin-bottom: 2px\"><label class='form-control'>或</label><span class=\"input-group-addon\"></span>";
            var s2 = "<input type=\"text\" class=\"form-control\" width=\"30\"/>";
            var s3 = "<span class=\"input-group-addon\"></span>";
            var s4 = "<select class=\"form-control\">";
            for(var item in operators){
                s4 += "<option>";
                s4 += operators[item];
                s4 += "</option>";
            }
            s4 += "</select>";
            var s5 = "<span class=\"input-group-addon\"></span>";
            var s6 = "<input type=\"text\" class=\"form-control\"  width=\"30\"/>";
            var s7 = "</div>";
            $("form").append(s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6 + "\n" + s7)
        }
        function removeand(){
            $(".and:last").remove();
        }
        function removeor(){
            $(".or:last").remove();
        }
        function tell(){
            //window.open("http://www.baidu.com");
            var sel = $("#table").find("option:selected").text();
            var tablename = "";
            if (sel == "课程"){
                tablename = "courses";
            }else if(sel == "学生"){
                tablename = "students";
            }else if(sel == "教师"){
                tablename = "teachers";
            }else if(sel == "选课表"){
                tablename = "SC";
            }else if(sel == "授课表"){
                tablename = "ST";
            }else if(sel == "院系表") {
                tablename = "Departments";
            }else if(sel == "专业表"){
                tablename = "Majors";
            }
            var postdata = "";
            $(".and").each(function (index,element) {
                var s1 = "";
                var s3 = "";
                $(this).children("input:first").each(function () {
                    s1 = $(this).val();
                });
                $(this).children("input:last").each(function () {
                    s3 = $(this).val();
                });
                var s2 = "";
                $(this).children("select").each(function(){
                    s2 += $(this).find("option:selected").text();
                });
                postdata += "and:"+ s1 + "&" + s2 + "&" + s3 + "\n";
            });
            $(".or").each(function (index,element) {
                var s1 = "";
                var s3 = "";
                $(this).children("input:first").each(function () {
                    s1 = $(this).val();
                });
                $(this).children("input:last").each(function () {
                    s3 = $(this).val();
                });
                var s2 = "";
                $(this).children("select").each(function(){
                    s2 += $(this).find("option:selected").text();
                });
                postdata += "or:"+ s1 + "&" + s2 + "&" + s3 + "\n";
            });
            console.log(postdata);
            $.ajax({
                type: 'POST',
                url: '/singleselect/',
                data: {"tablename":tablename,"data":postdata},
                dataType: 'json',
                success:function (data) {
                    var s = new String(data);
                    if(s == "true"){
                        window.open("/showcontent/")
                    }else{
                        alert('查询失败，请检查条件是否正确（外键值是否存在，条件是否为空）');
                    }

                }
            });
        }
    </script>
    <style>
        .and{
            margin-bottom: 2px;
        }
        .or{
            border: #b1e7ff;
            margin-bottom: 2px;
        }
    </style>
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
    <div class="jumbotron">
        <div class="btn-group" style="margin-bottom: 4px">
            <button class="btn btn-default" onclick="addand()">增加and</button>
            <button class="btn btn-default" onclick="addor()">增加or</button>
            <button class="btn btn-default" onclick="tell()">提交</button>
            <button class='btn btn-default' onclick='removeor()'>删除或</button>
            <button class='btn btn-default' onclick='removeand()'>删除与</button>
        </div>
        <form name="fo">
            <div id="pre" class="input-group" style="margin-bottom: 4px">
                <label>选择查找表：</label>
                <select id="table" name="table" class="form-control" onchange="changeselected()">
                    <option>课程</option>
                    <option>学生</option>
                    <option>教师</option>
                    <option>选课表</option>
                    <option>授课表</option>
                    <option>院系表</option>
                    <option>专业表</option>
                </select>
                <div id="attrs" class="btn-group" style="margin-bottom: 4px">
                    <button class="btn btn-default" disabled="disabled">课程id(Cid)</button>
                    <button class="btn btn-default" disabled="disabled">课程名(Cname)</button>
                    <button class="btn btn-default" disabled="disabled">学分(Credit)</button>
                    <button class='btn btn-default'disabled="disabled">开课人数(Cnum)</button>
                    <button class="btn btn-default"disabled="disabled">院系id(Did)</button>
                    <button class='btn btn-default'disabled="disabled">开课周(startweek)</button>
                    <button class='btn btn-default'disabled="disabled">结课周(endweek)</button>
                    <button class='btn btn-default'disabled="disabled">备注(Info)</button>
                </div>

            </div>
        </form>
    </div>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/resources/dist/js/bootstrap.min.js"/>"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/resources/assets/js/ie10-viewport-bug-workaround.js"/>"></script>
</body>
</html>
