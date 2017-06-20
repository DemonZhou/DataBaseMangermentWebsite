<%--
  Created by IntelliJ IDEA.
  User: Zhou
  Date: 2017/6/21
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="<c:url value="/resources/footable/css/footable.bootstrap.min.css"/>" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
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
        <form action="/modifyselect/" method="post">
            <div class="input-group">
                <label>请选择要更改的表：</label>
                <select name="table" class="form-control">
                    <option>课程</option>
                    <option>学生</option>
                    <option>教师</option>
                    <option>选课表</option>
                    <option>授课表</option>
                    <option>院系表</option>
                    <option>专业表</option>
                </select>
                <input type="submit" class="btn btn-default" value="提交"/>
            </div>

        </form>
    </div>
    <div class="example">
        <table id="editing-example" class="table" data-paging="true" data-filtering="true" data-sorting="true">
            <thead>
            <tr>
                <th data-name="Ctid" data-breakpoints="xs" data-type="number">id</th>
                <th data-name="Cid">Cid</th>
                <th data-name="Tid">Tid</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ss" items="${sessionScope.data}">
                <tr data-expanded="true">
                    <c:forEach var="s" items="${ss}">
                        <td>${s}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Modal -->
        <div class="modal fade" id="editor-modal" tabindex="-1" role="dialog" aria-labelledby="editor-title">
            <style scoped>
                /* provides a red astrix to denote required fields */
                .form-group.required .control-label:after {
                    content:"*";
                    color:red;
                    margin-left: 4px;
                }
            </style>
            <div class="modal-dialog" role="document">
                <form class="modal-content form-horizontal" id="editor">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editor-title">增加列</h4>
                    </div>
                    <div class="modal-body">
                        <input type="number" id="id" name="id" class="hidden"/>
                        <div class="form-group required">
                            <label for="Ctid" class="col-sm-3 control-label">id</label>
                            <div class="col-sm-9">
                                <input type="number" class="form-control" id="Ctid" name="Ctid" placeholder="id">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label for="Cid" class="col-sm-3 control-label">Cid</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="Cid" name="Cid" placeholder="Cid" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Tid" class="col-sm-3 control-label">Tid</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="Tid" name="Tid" placeholder="Tid" required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div> <!-- /container -->

<<!-- Placed at the end of the document so the pages load faster -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- Add in any FooTable dependencies we may need -->
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
<!-- Add in FooTable itself -->
<script src="<c:url value="/resources/footable/js/footable.js"/>"></script>
<!-- Initialize FooTable -->
<script>
    jQuery(function($){
        var $modal = $('#editor-modal'),
            $editor = $('#editor'),
            $editorTitle = $('#editor-title'),
            ft = FooTable.init('#editing-example', {
                editing: {
                    enabled: true,
                    addRow: function(){
                        $modal.removeData('row');
                        $editor[0].reset();
                        $editorTitle.text('Add a new row');
                        $modal.modal('show');
                    },
                    editRow: function(row){
                        var values = row.val();
                        $editor.find('#Ctid').val(values.Ctid);
                        $editor.find('#Cid').val(values.Cid);
                        $editor.find('#Tid').val(values.Tid);
                        $modal.data('row', row);
                        $editorTitle.text('Edit row #' + values.Ctid);
                        $modal.modal('show');
                    },
                    deleteRow: function(row){
                        if (confirm('确定删除吗?')){
                            row.delete();
                        }
                        var values = row.val();
                        console.log(values.Ctid);
                        $.ajax({
                            type:'POST',
                            url: '/CT/delete',
                            data : {"table":"CT","id":values.Ctid},
                            dataType:'json',
                            success:function (data) {
                                var s = new String(data);
                                if(s == "true"){
                                    alert("修改成功");
                                }else{
                                    alert('更改失败，请检查条件是否正确（外键值是否存在，条件是否为空）');
                                    location.reload(true);
                                }
                            }
                        })
                    }
                }
            }),
            uid = 10;

        $editor.on('submit', function(e){
            if (this.checkValidity && !this.checkValidity()) return;
            e.preventDefault();
            var row = $modal.data('row'),
                values = {
                    Ctid: $editor.find('#Ctid').val(),
                    Cid: $editor.find('#Cid').val(),
                    Tid: $editor.find('#Tid').val()
                };

            if (row instanceof FooTable.Row){
                row.val(values);
            } else {
                values.id = uid++;
                ft.rows.add(values);
            }
            $.ajax({
                type:'POST',
                url: '/CT/updateoradd',
                data : {"table":"Students","id":values.Ctid,"Cid":values.Cid,"Tid":values.Tid},
                dataType:'json',
                success:function (data) {
                    var s = new String(data);
                    if(s == "true"){
                        alert("修改成功");
                    }else{
                        alert('更改失败，请检查条件是否正确（外键值是否存在，条件是否为空）');
                    }
                    location.reload(true);
                }
            });
            $modal.modal('hide');
        });
    });
</script>
</body>
</html>
