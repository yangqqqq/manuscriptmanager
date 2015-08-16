﻿<%@page import="com.yang.software.mm.data.Constants"%>
<%@page import="com.yang.software.mm.enums.PublishTimeEnum"%>
<%@page import="com.yang.software.mm.enums.MmOpTypeEnum"%>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/table.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/table.js"></script>
<style type="text/css">
table {  
    border: 1px solid #B1CDE3;  
    padding:0;   
    margin:0 auto;  
    border-collapse: collapse;  
}  
  
td {  
    border: 1px solid #B1CDE3;  
    font-size:15px;  
    padding: 3px 3px 3px 8px;  
    color: #000000;
    font-family:"宋体";
    word-break:keep-all;/* 不换行 */
    white-space:nowrap;/* 不换行 */
    overflow:hidden;
    text-overflow:ellipsis;
}
th {
    word-break:keep-all;/* 不换行 */
    white-space:nowrap;/* 不换行 */
    overflow:hidden;
    text-overflow:ellipsis;
}
.selected
{
    background-color:#d1e8ff;
}

</style>
<script type="text/javascript">
function manuscriptPreview(manuscriptId, trObj, remark)
{
	$(".manuTr").removeClass("selected");
	$(trObj).addClass("selected");
	form.manuscriptId.value = manuscriptId;
	form.remark.value = remark;
	$("#remark").attr("readonly","readonly");
	window.document.getElementById("manuscriptPreviewFrame").src = "./manuscriptMainPreview?manuscriptId="+manuscriptId;
	window.document.getElementById("manuscriptRecordListFrame").src="./manuscriptRecordList?manuscriptId="+manuscriptId;
}
function manuscriptEditInit(manuscriptId)
{
	window.location.href="./manuscriptEditInit?manuscriptId="+manuscriptId;
}
function manuscriptAdviceInit(manuscriptId)
{
    window.location.href="./manuscriptAdviceInit?manuscriptId="+manuscriptId;
}
function mutiSelectChange(checkboxObj)
{
	if (checkboxObj.checked)
	{
		var checkBoxList = $(".checkbox_selectId");
		for (var i = 0 ; i < checkBoxList.length; i++)
		{
			var checkBoxTr = $(checkBoxList[i]).parent().parent();
			if (checkBoxTr.css("display") == "none")
			{
			    continue;	
			}
			$(checkBoxList[i]).prop("checked",true);
		}
	}
	else
	{
		$(".checkbox_selectId").prop("checked",false);
	}
}
function opTypeChange(opTypeId)
{
	var selectCheckbox = $(".checkbox_selectId");
	if (selectCheckbox.length == 0)
	{
		alert("请选择一条记录");
		form.opType.value="0";
		return false;
	}
	for (var i = 0 ; i < selectCheckbox.length; i++)
	{
		if (selectCheckbox[i].checked)
		{
		    var r=confirm("确定吗？")
            if (r==true)
            {
            	form.opType.value = opTypeId;
                document.form.submit();
				return true;
			}
			else
			{
				form.opType.value="0";
			    return false;
		    }
		}
	}
	alert("请选择一条记录");
	form.opType.value="0";
    return false;
}
function search1()
{
    document.form.action="./manuscriptList";
    document.form.submit();
}
function search2()
{
	search1();
}
function isMatch(searchStr, contentStr)
{
	if (searchStr == "")
	{
		return true;
	}
	return (contentStr.indexOf(searchStr) != -1);
}
window.onload = pageInit;
function pageInit()
{
    getManuscriptResult(0);
}
function getManuscriptResult(page)
{
    $.ajax({
        url: "./manuscriptListJson?currentPage=" + page,
        context: document.body,
        cache:false,
        success: function(data){
            if (data.length > 0)
            {
                setTableValue(data);
            }
            if (page < 1)
            {
                getManuscriptResult(page + 1);
            }
        }});
}
function setTableValue(data)
{
    for (var i = 0;i < data.length; i ++)
    {
        var manuscript = data[i];
        var trString = "<tr class='manuTr' onclick=\"manuscriptPreview("+ manuscript.manuscriptId +",this,'"+ manuscript.remark+"')\" ondblclick='manuscriptEditInit("+ manuscript.manuscriptId +")'>";
        trString += "<td width=\"10px\"><input type=\"checkbox\" name=\"selectId\" class=\"checkbox_selectId\" value=\""+ manuscript.manuscriptId +"\"/></td>";
        trString += "<td width=\"*%\">";
        if (manuscript.remark != '')
        {
            trString += "<label style=\"color: red\">["+manuscript.remark+"]</label>";
        }
        trString += manuscript.summary+"</td>";
        trString += "<td width=\"150px\">"+manuscript.sectionName+"</td>";
        trString += "<td width=\"120px\">"+manuscript.countDisplay+"</td>";
        trString += "<td width=\"70px\">"+manuscript.contentLength+"</td>";
        trString += "<td width=\"60px\">"+manuscript.ownerName+"</td>";
        trString += "<td width=\"60px\">"+manuscript.publishYear+"年</td>";
        trString += "<td width=\"60px\">"+manuscript.publishTimeDescription+"</td>";
        trString += "<td width=\"60px\">"+manuscript.autherName+"</td>";
        trString += "<td width=\"60px\">"+manuscript.factoryDescription+"</td>";
        trString += "<td width=\"170px\">"+manuscript.createDateStr+"</td>";
        trString += "<td width=\"170px\">"+manuscript.opDateStr+"</td></tr>";
        $("#tableSort").append(trString);
    }
    $("#manuCount").html(parseInt($("#manuCount").html(), 10) + data.length);

}
function submitAdvice()
{
    if (form.manuscriptId.value == '')
   	{
    	alert("请选择一条记录!!");
    	return false;
   	}
	document.form.action = "./manuscriptAdvice?";
	document.form.submit();
}
function reset()
{
    document.form.content.value="";
    document.form.publishTime.value="-1";
    document.form.publishYear.value="-1";
    document.form.section.value="-1";
	search2();
}
function setWirtable(inputObj)
{
	$(inputObj).removeAttr("readonly");
}
function realDelete()
{
    var selectCheckbox = $(".checkbox_selectId");
    if (selectCheckbox.length == 0)
    {
        alert("请选择一条记录");
        return false;
    }
    for (var i = 0 ; i < selectCheckbox.length; i++)
    {
        if (selectCheckbox[i].checked)
        {
            var r=confirm("确定吗？")
            if (r==true)
            {
                document.form.action = "./manuscriptDelete?";
                document.form.submit();
                return true;
            }
        }
    }
}
</script>
</head>
<body>
<form name="form" action="./manuscriptOpInit" onsubmit="return false;">
操作类型:
<input type="hidden" name="opType">
<input type="hidden" name="manuscriptId" id="manuscriptId">

<c:forEach items="${opTypes }" var="opType">
<a href="javascript:void(0)" onclick="opTypeChange(${opType.id})">${opType.opString}</a>
</c:forEach>
<%if ("true".equals(request.getParameter("isRecyle"))){%>
<a href="javascript:void(0)" onclick="realDelete()">从数据库删除</a>
    <%}%>
共<label id="manuCount">0</label>篇
&nbsp;&nbsp;
按内容查询<input type="text" name="content" value="${condition.content}"/>
按栏目查询<select name="section">
        <option value="-1">请选择</option>
        <c:forEach var="section" items="${sections}">
            <c:if test="${section.hidden == 0}"><option value="${section.id}" <c:if test="${section.id == condition.section}">selected="selected" </c:if> >${section.sectionName}</option></c:if>
        </c:forEach>
    </select>
按期数查询<select name="publishTime">
<option value="-1">请选择</option>
    <c:forEach items="<%=PublishTimeEnum.values() %>" var="period">
        <option value="${period.id}"  <c:if test="${period.id == condition.publishTime}">selected="selected" </c:if>>${period.description}</option>
    </c:forEach>
    </select>
    按年份查询<select name="publishYear">
<option value="-1">请选择</option>
    <c:forEach items="${publishYear}" var="year">
        <option value="${year}"  <c:if test="${year == condition.publishYear}">selected="selected" </c:if>>${year}</option>
    </c:forEach>
    </select>
<a href="javascript:void(0)" onclick="search2();" onkeyup="">搜索</a>
<a href="javascript:void(0)" onclick="reset();" onkeyup="">重置</a>
<div style="max-height:300px;OVERFLOW-Y: auto;overflow-x:auto;" >
 <table summary="user infomation table" id="tableSort" border="1" width="99%" style="margin-left: 0">
  <thead>
   <tr>
   <th align="left"><input type="checkbox" onclick="mutiSelectChange(this)"></th> 
    <th onclick="sortTable('tableSort', 1)" style="cursor: pointer;">摘要</th>
    <th onclick="sortTable('tableSort', 2)" style="cursor: pointer;">栏目</th>
    <th onclick="sortTable('tableSort', 3)" style="cursor: pointer;">备注</th>
    <th onclick="sortTable('tableSort', 4, 'int')" style="cursor: pointer;">字数</th>
    <th onclick="sortTable('tableSort', 5)" style="cursor: pointer;">操作人</th>
    <th onclick="sortTable('tableSort', 6)" style="cursor: pointer;">年份</th>
    <th onclick="sortTable('tableSort', 7)" style="cursor: pointer;">期数</th>
    <th onclick="sortTable('tableSort', 8)" style="cursor: pointer;">作者</th>
    <th onclick="sortTable('tableSort', 9)" style="cursor: pointer;">所在库</th>
    <th onclick="sortTable('tableSort', 10)" style="cursor: pointer;">上传时间</th>
    <th onclick="sortTable('tableSort', 11)" style="cursor: pointer;">最后操作时间</th>
   </tr>
  </thead>
 </table>
</div>
<table style="width: 90%;margin-top: 10px;margin-left: 0px"><tr><td>
建议：<input readonly="readonly" type="text" id="remark" name="remark" style="width: 50%;background-color: #cccccc;height: 20px" ondblclick="setWirtable(this);"><a href="javascript:void(0)" onclick="submitAdvice();">确认修改</a>
</td></tr></table>
</form>
<iframe src="" name="manuscriptPreviewFrame" width="95%" height="500" id="manuscriptPreviewFrame" style="margin-top:10px"></iframe>
<iframe src="" name="manuscriptRecordListFrame" width="95%" height="200" id="manuscriptRecordListFrame" style="margin-top:10px"></iframe>
</body>
</html>