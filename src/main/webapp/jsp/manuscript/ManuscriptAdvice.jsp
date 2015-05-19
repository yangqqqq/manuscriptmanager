<%@page import="com.yang.software.mm.enums.PublishTimeEnum"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function adviceManuscript()
{
	document.form.submit();
	return true;
}
function pageInit()
{
	showTextLength();
}
window.onload=pageInit;
</script>
</head>
<body>
<form name="form" method="post" action="manuscriptAdvice">
<input type="hidden" name="manuscriptId" value="${command.manuscriptId}">
建议：
<br>
    <input type="text" name="remark" value="${command.remark}" maxlength="20">
</form>
</body>
  <button onclick="adviceManuscript()">提交</button>
</html>