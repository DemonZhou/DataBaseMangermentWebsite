<%--
  Created by IntelliJ IDEA.
  User: Zhou
  Date: 2017/6/17
  Time: 3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:out value="${sessionScope.tablename}" />
</div>
<div>
    <c:out value="${sessionScope.data}" />
</div>
</body>
</html>
