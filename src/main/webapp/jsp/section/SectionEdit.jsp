<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="sectionEdit" method="post">
<input name="id" type="hidden" value="${command.id}"/>
<table>

    <tr><td>栏目名称:<input name="sectionName" value="${command.sectionName}"></td></tr>
        <tr><td>是否隐藏:<select name="hidden">
    <option value = "0" <c:if test="${command.hidden == 0}">selected </c:if> >否</option>
    <option value = "1" <c:if test="${command.hidden == 1}">selected </c:if>>是</option>
    </select></td></tr>
</table>
<input type="submit"/>
</form>
</body>
</html>