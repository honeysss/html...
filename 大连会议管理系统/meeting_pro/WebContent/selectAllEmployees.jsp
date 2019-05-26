<%@page import="javax.script.ScriptContext"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.icss.service.*" %>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
    <body>
        <%@include file="include/page-header.jsp" %>
        
        
        <div class="page-body">
           
            <%@include file="include/adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                 	   人员管理   >  注册审批
                </div>
				<table class="listtable">
				<caption>所有员工:</caption>
                    <tr class="listheader">
                        <th>员工ID</th>       
                        <th>姓名</th>                     
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>部门名称</th>
                        <th>账号名</th>
                        <th>密码</th>
                        <th>账号状态</th>
                        <th>角色</th>
                        <th colspan="2">操作</th>
                    </tr>
                    
                    <c:forEach var="emp" items="${emps }">
	                    <tr>
	                        <td>${emp.empId }</td>   
	                        <td>${emp.empName }</td>                 
	                        <td>${emp.empTel }</td>
	                        <td>${emp.empEmail }</td>
	                        <td class="deptName">
	                        	<c:forEach var="dept" items="${depts }">
	                        		<c:if test="${emp.empDeptId == dept.deptId }">
	                        			${dept.deptName }
	                        		</c:if>
	                        	</c:forEach>
	                        </td>
	                        <td>${emp.username }</td>
	                        <td>${emp.userPwd }</td>
	                        <td>
	                        	<c:if test="${emp.userStatus == 0 }">
	                        		<c:set var="status" value="正在审核中"></c:set>
	                        	</c:if>
	                        	<c:if test="${emp.userStatus == 1 }">
	                        		<c:set var="status" value="审核通过"></c:set>
	                        	</c:if>
	                        	<c:if test="${emp.userStatus == 2 }">
	                        		<c:set var="status" value="审核失败"></c:set>
	                        	</c:if>
	                        	${status }
	                        </td>
	                        <td>
	                        	<c:if test="${emp.userRole == 0 }">
	                        		<c:set var="role" value="普通用户"></c:set>
	                        	</c:if>
	                        	<c:if test="${emp.userRole == 1 }">
	                        		<c:set var="role" value="管理员"></c:set>
	                        	</c:if>
	                        	${role }
	                        </td>
							  <td>
							  	<c:if test="${emp.userStatus == 0 }">
							  		<input type="button" class="clickbutton" value="通过" onclick="window.location.href='ApproveServlet?empId=${emp.empId}&oper=yes'"/>
	                                <input type="button" class="clickbutton" value="不通过" onclick="window.location.href='ApproveServlet?empId=${emp.empId}&oper=no'"/>
	                       		 </c:if>
	                        </td>
							
	                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <%@include file="include/page-footer.jsp" %>
        <script src="js/common.js"></script>
        <script>
        	window.onload = function() {
        		var deptName = document.getElementsByClassName('deptName');
        		console.log(deptName)
        		for (var i = 0; i < deptName.length; i ++) {
        			if(deptName[i].innerHTML.trim() === '' || deptName[i].innerHTML.trim() === null) {
            			deptName[i].innerHTML = '无';
            		}
        		}
        	}
        </script>
    </body>
</html>