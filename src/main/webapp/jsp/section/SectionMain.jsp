<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <a href="sectionAddInit" target="mainFrame">增加栏目</a>
    <table border="1" width="99%"> 
       <tr> 
        <td width="80%">name 
        </td> 
        <td>
        op
        </td> 
       </tr> 
       <!--  loop begin --> 
       <c:forEach var="section" items="${command}" > 
          <tr> 
          <td> <c:out value="${section.sectionName}"/> 
          </td> 
          <td>
          <a href="sectionEditInit?id=${section.id}">edit</a>
          </td> 
          </tr> 
       </c:forEach> 
       <!-- loop end  --> 
    </table>
</body>
</html>