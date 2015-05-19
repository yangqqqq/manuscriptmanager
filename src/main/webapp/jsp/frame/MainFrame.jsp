<%@page import="com.yang.software.mm.enums.FactoryTypeEnum"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<title>《政治指导员》采编系统</title>
<frameset rows="59,*" cols="*" frameborder="no" border="0" framespacing="0" border="1">
  <frame src="./topFrame" name="topFrame" scrolling="No" noresize="noresize" id="topFrame"/>
  <frameset cols="150,*" frameborder="no" border="0" framespacing="0">
    <frame src="./leftFrame" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" bordercolor="#CC0000"/>
    <frame src="./manuscriptMainOfFactory?factoryId=<%=FactoryTypeEnum.EDIT_ID %>" name="mainFrame" id="mainFrame"/>
    </frameset>
    </frameset>
</html>