﻿<%@page import="com.yang.software.mm.data.Constants"%>
<%@page import="com.yang.software.mm.enums.PublishTimeEnum"%>
<%@page import="com.yang.software.mm.enums.MmOpTypeEnum"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/table.jsp" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/table.jsp"></script>
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
	  var table = document.getElementById("tableSort");
	  var tbody = table.tBodies[0];
	  var tr = tbody.rows; 
	  var matchCount = 0;
	  for (var i = 0 ; i < tr.length; i++)
	  {
		  if (isMatch(form.searchContext.value,tr[i].cells[1].innerHTML)
				  &&isMatch(form.searchSection.value,tr[i].cells[2].innerHTML)
				  && isMatch(form.searchPublishTime.value,tr[i].cells[7].innerHTML)
                  && isMatch(form.searchPublishYear.value,tr[i].cells[6].innerHTML))
		  {
			  $(tr[i]).css("display","");
			  matchCount ++;
		  }
		  else
		  {
			  $(tr[i]).css("display","none");
		  }
	  }
	  $("#manuCount").html(matchCount);
}
function search2()
{
	search1();
    setSearchCondtion();
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
	var sc = $.ajax({url:"./getSearchCondition",async:false});
	var searchCondition = sc.responseText.split("<%=Constants.SEARCH_CONDITION_SPLIT%>");
	form.searchContext.value = searchCondition[0];
	form.searchPublishTime.value = searchCondition[1];
	form.searchSection.value = searchCondition[2];
	search1();
	sortTable('tableSort', 10);
	sortTable('tableSort', 10);
}
function setSearchCondtion()
{
	$.ajax({url:"./setSearchCondition?searchContext="+form.searchContext.value+"&searchPublishTime="+form.searchPublishTime.value+"&searchSection="+form.searchSection.value,async:true});
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
	form.searchContext.value="";
	form.searchSection.value="";
	form.searchPublishTime.value="";
	form.searchPublishYear.value="";
	search2();
}
function setWirtable(inputObj)
{
	$(inputObj).removeAttr("readonly");
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
共<label id="manuCount">${fn:length(command)}</label>篇
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
按内容查询<input type="text" name="searchContext"/>
按栏目查询<select name="searchSection">
        <option value="">请选择</option>
        <c:forEach var="section" items="${sections}">
            <c:if test="${section.hidden == 0}"><option value="${section.sectionName}">${section.sectionName}</option></c:if>
        </c:forEach>
    </select>
按期数查询<select name="searchPublishTime">
<option value="">请选择</option>
    <c:forEach items="<%=PublishTimeEnum.values() %>" var="period">
        <option value="${period.description}">${period.description}</option>
    </c:forEach>
    </select>
    按年份查询<select name="searchPublishYear">
<option value="">请选择</option>
        <option value="2014年">2014年</option>
        <option value="2015年">2015年</option>
        <option value="2015年">2016年</option>
        <option value="2015年">2017年</option>
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
    <th onclick="sortTable('tableSort', 10)" style="cursor: pointer;">最后操作时间</th>
   </tr>
  </thead>
  <tbody>
       <c:forEach var="manuscript" items="${command}" > 
          <tr class="manuTr" onclick="manuscriptPreview(${manuscript.manuscriptId},this,'${manuscript.remark}')" <c:if test="${listType == 'myList'}">ondblclick="manuscriptEditInit(${manuscript.manuscriptId})"</c:if>> 
          <td width="10px"><input type="checkbox" name="selectId" class="checkbox_selectId" value="${manuscript.manuscriptId}"/>
          </td>
          <td width="*%"><c:if test="${manuscript.remark != ''}"><label style="color: red">[${manuscript.remark}]</label></c:if><c:out value="${manuscript.summary}" default="" /><input type="hidden" value="${manuscript.content}"/>
          </td> 
          <td width="150px"> <c:out value="${manuscript.sectionName}" default=""/> 
          </td>
          <td width="120px"> <c:out value="${manuscript.countDisplay}" default=""/> 
          </td> 
          <td width="70px"> <c:out value="${manuscript.contentLength}" default=""/> 
          </td>
          <td width="60px"> <c:out value="${manuscript.ownerName}" default=""/> 
          </td>
         <td width="60px"> <c:out value="${manuscript.publishYear}年" default=""/> 
          </td>
          <td width="60px"> <c:out value="${manuscript.publishTimeDescription}" default=""/> 
          </td>
          <td width="60px"> <c:out value="${manuscript.autherName}" default=""/> 
          </td>     
          <td width="60px"> <c:out value="${manuscript.factoryDescription}" default=""/> 
          </td>
          <td width="170px"> <c:out value="${manuscript.opDate}" default=""/> 
          </td>
          </tr> 
       </c:forEach>
  </tbody>
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