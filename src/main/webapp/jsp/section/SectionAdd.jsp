<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="sectionAdd" method="post">
<table>
    <tr><td>栏目名称:<input name="sectionName"></td></tr>
    <tr><td>是否隐藏:<select name="hidden">
    <option value = "0">否</option>
    <option value = "1">是</option>
    </select></td></tr>
</table>
<input type="submit"/>
</form>
</body>
</html>