<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function logoff()
{
	window.parent.location.href = "./logoff";
}
function keepLive()
{
    var ss = $.ajax({url:"./keepLive",async:false});
	setTimeout("keepLive()",30000);
}
function pageInit()
{
	keepLive();
}
window.onload = pageInit;
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<p style="" align="right">
${command}
<a href="javascript:void(0)" onclick="logoff();">注销</a>
</p>
</body>
</html>