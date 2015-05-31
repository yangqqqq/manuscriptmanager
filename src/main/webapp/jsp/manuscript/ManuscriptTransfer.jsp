<%@page import="com.yang.software.mm.enums.RoleEnum"%>
<%@page import="com.yang.software.mm.enums.FactoryTypeEnum"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function dataCheck()
{
    document.form.submit();
    return true;
}

function doCancle()
{
    document.location="./manuscriptList";
}

</script>
</head>
<body>
<form name="form" method="post" action="manuscriptTransfer">
    <select name="factoryId">
    <c:forEach items="${command }" var="factory">
        <option value="${factory.id }">${factory.description}</option>
    </c:forEach>
    </select>
    <br>
</form>
    <button onclick="dataCheck();">确定</button><button onclick="doCancle()">取消</button>
</body>
</html>