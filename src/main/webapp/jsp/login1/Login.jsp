<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!-- Meta -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- Libraries -->
<link type="text/css" href="css/login.css" rel="stylesheet" />
<script type="text/javascript">
function login()
{
	document.form.submit();
	return true;
}
</script>
</head>
<title>《政治指导员》采编系统</title>
    <body style="background-color: #ffffcb">
    <div style="background-color: #ffffcb;width:1000px; margin:0 auto;padding:0">
    <div style="float:left;background-color: #ffffcb;width:600px;"><img src="image/frontcover.jpg" alt="" width="600px"></div>
    <div style="float:left;background-color: #ffffcb; width:400px;margin-top:100px" id="container">
        <div style="background-color: #ffffcb;" id="box">
            <form action="login" method="post" name="form">
            <p>
                <label>用户名:</label>
                <input name="username" value="" />
                <br/> 
                <label>密&nbsp;&nbsp;&nbsp;码:</label>
                <input type="password" name="password" />    
            </p>
            ${errorInfo }
            </form>
            <p class="space">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button onclick="login()">登录</button>
            </p>
        </div>
    </div>
    </div>
    </body>
</html>