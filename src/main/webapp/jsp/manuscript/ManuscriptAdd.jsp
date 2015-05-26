<%@page import="com.yang.software.mm.enums.PublishTimeEnum"%>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function addManuscript()
{
	window.parent.document.getElementById("leftFrame").src="./leftFrame";
	document.form.submit();
	return true;
}
function showTextLength()
{
	$("#textLengthLabel").html(form.content.value.length);
}
</script>
</head>
<body>
<form name="form" method="post" action="manuscriptAdd" onsubmit="return false;">
    栏目<select name="sectionId">
        <c:forEach var="section" items="${sections}">
            <c:if test="${section.hidden == 0}"><option value="${section.id}">${section.sectionName}</option></c:if>
        </c:forEach>
    </select>
    
   年份<select name="publishYear">
    <%
        Date now = new Date();
        int realYear = 1900 + now.getYear();
        for (int i = 2014; i <= realYear + 1; i ++) {%>
    <option value="<%=i%>" <%=i == realYear? "selected='selected'" : ""%>><%=i%></option>
    <%}%>
   </select>
    期数<select name="publishTime">
    <c:forEach var="publishTime" items="<%=PublishTimeEnum.values() %>">
        <option value="${publishTime.id }">${publishTime.description}</option>
    </c:forEach>
    </select>
备注<input name="count"/>
<input type="hidden" name="remark">
    <br/>
    当前字数：<label id="textLengthLabel"></label>
    <textarea name="content" style="width:100%;word-break:break-all;height:500px;background-color: #c7edcc;" onkeyup="showTextLength()"></textarea>
</form>
    <button onclick="addManuscript()">添加稿件</button>
</body>
</html>