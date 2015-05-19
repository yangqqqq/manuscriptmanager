<%@page import="com.yang.software.mm.enums.FactoryTypeEnum"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function factorySelect(factoryId, aObj)
{
	window.parent.document.getElementById("mainFrame").src ="./manuscriptMainOfFactory?factoryId="+factoryId;
	factoryTypeChange(aObj);
}
function listTypeChange(aObj)
{
	$(".listType").removeClass("selected");
	$(".factoryType").removeClass("selected");
	$(".recyclerType").removeClass("selected");
	$(aObj).addClass("selected");
}
function factoryTypeChange(aObj)
{
	$(".factoryType").removeClass("selected");
	$(".recyclerType").removeClass("selected");
	$(aObj).addClass("selected");
}
function recyclerSelected(aObj)
{
	$(".listType").removeClass("selected");
    $(".factoryType").removeClass("selected");
	$(aObj).addClass("selected");
}
function pageInit()
{
	listTypeChange(document.getElementById("myFrameTitle"));
}

window.onload = pageInit;
</script>
<style type="text/css">
table.leftTable{ font-size:14px; font-family:Verdana, Geneva, sans-serif, "微软雅黑";}
table.leftTable tr td a{ display:block; height:30px; line-height:30px; text-indent:10px; text-decoration:none; width:100%;color:#333; border-bottom:1px solid #fff;}
table.leftTable tr td a:hover{ background-color:#357CBF;}
.selected
{
    background-color:#357CBF;
}
</style>
</head>
<body bgcolor="#c7edcb">
<table class="leftTable" width="200" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="34"><a class="listType" target="mainFrame" href="./manuscriptAddInit" onclick="listTypeChange(this)">编写新稿件</a></td>
  </tr>
  <tr>
    <td height="34"><a class="listType" target="mainFrame" href="./manuscriptMainMy" onclick="listTypeChange(this)" id="myFrameTitle">我的稿件</a></td>
  </tr>
  <tr>
    <td height="34"><a class="listType" target="mainFrame" href="./manuscriptMainAll" onclick="listTypeChange(this)">所有稿件</a></td>
  </tr>
   <tr>
    <td height="34">
                           请选择所在库
        <c:forEach var="factory" items="<%=FactoryTypeEnum.values() %>">
             <a href="javascript:void(0)" onclick="factorySelect(${factory.id },this)" class="factoryType">${factory.description }</a>
        </c:forEach>
     </td>
  </tr>
  <tr>
    <td><a target="mainFrame" href="manuscriptRecyclerList" class="recyclerType" onclick="recyclerSelected(this)">回收站</a></td>
  </tr>
</table>
</body>
</html>