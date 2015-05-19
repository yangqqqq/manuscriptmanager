<%@page import="com.yang.software.mm.enums.RoleEnum"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="userEdit" method="post">
<input name="id" type="hidden" value="${user.id}"/>
<table>

    <tr><td>name:<input name="name" value="${user.name}"></td></tr>
    <tr><td>password:<input name="password" type="password"></td></tr>
     <tr><td>权限：<select name="roleId">
    <c:forEach items="<%=RoleEnum.values() %>" var="role">
        <option value="${role.id}" <c:if test="${user.roleId == role.id}"> selected="selected"</c:if>>${role.description}</option>
    </c:forEach>
    </select> </td></tr>
   
</table>
<input type="submit"/>
</form>
</body>
</html>