<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <a href="userAddInit" target="mainFrame">增加用户</a>
    <table border="1" width="99%"> 
       <tr> 
        <td width="40%">用户名
        </td> 
        <td width="30%">密码 
        </td>
        <td width="20%">权限
        </td>
        <td>
        op
        </td> 
       </tr> 
       <!--  loop begin --> 
       <c:forEach var="user" items="${command}" > 
          <tr> 
          <td> <c:out value="${user.name}"/> 
          </td> 
          <td> 
           <c:out value="${user.password}"/> 
          </td>
          <td> 
           <c:out value="${user.roleDescription}"/> 
          </td>
          <td>
          <a href="userEditInit?id=${user.id}">edit</a>
          </td> 
          </tr> 
       </c:forEach> 
       <!-- loop end  --> 
    </table>
</body>
</html>