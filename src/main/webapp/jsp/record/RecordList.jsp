<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">  
table {  
    border: 1px solid #B1CDE3;  
    padding:0;   
    margin:0 auto;  
    border-collapse: collapse;  
}  
  
td {  
    border: 1px solid #B1CDE3;  
    background: #fff;  
    font-size:15px;  
    padding: 3px 3px 3px 8px;  
    color: #4f6b72;
    font-family:"宋体";
    overflow:hidden; /*不显示超过对象宽度的内容*/ 
    text-overflow:ellipsis; /*当对象内文本溢出时显示省略标记（...）*/ 
    white-space:nowrap; /*限制在一行内显示所有文本*/  
}  
</style>
<script type="text/javascript">
function manuscriptPreview(recordId)
{
	window.parent.document.getElementById("manuscriptPreviewFrame").src ="./manuscriptRecordPreview?recordId="+recordId;
}
</script>
</head>
<body>
    <table border="1" width="99%"> 
       <tr> 
        <td>操作类型 
        </td> 
        <td>操作人
        </td>
        <td>操作时间
        </td>
        <td>所在库
        </td> 
       <td>摘要 
        </td>
        <td>备注 
        </td>
        <td>年份 
        </td>
        <td>期数 
        </td>
        <td>栏目
        </td>
        <td>字数 
        </td>
       </tr> 
       <!--  loop begin --> 
       <c:forEach var="manuscriptRecord" items="${command}" > 
          <tr onclick="manuscriptPreview(${manuscriptRecord.recordId})"> 
          <td> <c:out value="${manuscriptRecord.opTypeDescription}" /> 
          </td> 
          <td> <c:out value="${manuscriptRecord.opErName}" /> 
          </td>     
          <td> <c:out value="${manuscriptRecord.opDate}" /> 
          </td>
          <td> <c:out value="${manuscriptRecord.factoryDescription}" /> 
          </td>
          <td><c:if test="${manuscriptRecord.remark != ''}"><label style="color: red">[${manuscriptRecord.remark}]</label></c:if><c:out value="${manuscriptRecord.summary}" /> 
          </td>
          <td> <c:out value="${manuscriptRecord.count}" /> 
          </td>
          <td> <c:out value="${manuscriptRecord.publishYear}" /> 
          </td>
          <td> <c:out value="${manuscriptRecord.publishTimeDescription}" /> 
          </td>
          <td> <c:out value="${manuscriptRecord.sectionName}" /> 
          </td>
          <td> <c:out value="${manuscriptRecord.contentLength}" /> 
          </td>
          </tr> 
       </c:forEach> 
       <!-- loop end  --> 
    </table>
</body>
</html>