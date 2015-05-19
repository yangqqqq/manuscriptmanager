<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<frameset rows="500,*" cols="*" frameborder="no" border="0" framespacing="0" border="1">
  <frame src="./${url}" name="manuscriptListFrame" scrolling="No" noresize="noresize" id="manuscriptListFrame"/>
  <frame src="./manuscriptMainPreview" name="manuscriptPreviewFrame" id="manuscriptPreviewFrame"/>
</frameset>
</html>