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
function editManuscript()
{
	document.form.submit();
	return true;
}
function showTextLength()
{
    $("#textLengthLabel").html(form.content.value.length);
}
function pageInit()
{
	showTextLength();
}
window.onload=pageInit;
</script>
</head>
<body>
<form name="form" method="post" action="manuscriptEdit" onsubmit="return false;">
    栏目<select name="sectionId">
        <c:forEach var="section" items="${sections}">
            <c:if test="${section.hidden == 0}"><option value="${section.id}"  <c:if test="${command.sectionId == section.id}">selected="selected"</c:if>>${section.sectionName}</option></c:if>
        </c:forEach>
    </select>
    
       年份<select name="publishYear">
    <option value="2013" <c:if test="${command.publishYear == 2013}">selected="selected"</c:if>>2013</option>
    <option value="2014" <c:if test="${command.publishYear == 2014}">selected="selected"</c:if>>2014</option>
    <option value="2015" <c:if test="${command.publishYear == 2015}">selected="selected"</c:if>>2015</option>
    <option value="2016" <c:if test="${command.publishYear == 2016}">selected="selected"</c:if>>2016</option>
   </select>
   <p style="display:none">
    版本号<select name="publishTime">
    <c:forEach var="publishTime" items="<%=PublishTimeEnum.values() %>">
        <option value="${publishTime.id }" <c:if test="${command.publishTime == publishTime.id}">selected="selected"</c:if>>${publishTime.description}</option>
    </c:forEach>
    </select>
    </p>
备注<input type="text" name="count" value="${command.count}"/><input type="hidden" name="remark">
 <br/>
      当前字数：<label id="textLengthLabel"></label>
<input type="hidden" value="${command.manuscriptId}" name="manuscriptId">
    <textarea name="content" style="width:95%;word-break:break-all;height:600px;font-size:18px;font-family:'宋体';background-color: #c7edcc;"  onkeyup="showTextLength()">${command.content}</textarea>
</form>
</body>
  <button onclick="editManuscript()">提交</button>
</html>