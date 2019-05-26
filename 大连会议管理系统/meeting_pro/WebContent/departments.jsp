<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   
    <%@include file="include/head.jsp" %>
    <body>
    	<%@include file="include/page-header.jsp" %>
        <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
         </div>
           <div class="page-content">
                <div class="content-nav">
                   	 人员管理 > 部门管理
                </div>
                <form action="AddDepartmentServlet" method="post" id="form1">
                    <fieldset>
                        <strong><span id="deptNameMsg"></span></strong>
                        <legend>添加部门</legend>
                     		   部门名称:
                        <input type="text" name="deptName" maxlength="20"
                        placeholder="请输入部门名称" id="deptName"/>
                        <input type="button" class="clickbutton" value="添加" id="confirmAddDept"/>
                    </fieldset>
                </form>
                <c:if test="${requestScope.depts!=null}">
      
                <table class="listtable">
                    <caption>所有部门:</caption>
                    <tr class="listheader">
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>操作</th>
                    </tr>
	
					<c:forEach var="dept" items="${requestScope.depts}">
                    <tr>
                        <td>${dept.deptId}</td>
                        <td>${dept.deptName}</td>
                        <td>
                           <button>
                           	<a class="clickbutton" href="javascript:;"
                           	onclick="changeToPostMethod('DeleteDepartmentServlet', {
                           		'deptId': ${dept.deptId }
                           	})"
                           	>删除</a>
                           </button>
                        </td>
                    </tr>
               	</c:forEach>
                </table>
                </c:if>
            </div>
             <%@include file="include/page-footer.jsp" %>
             
             <script>
             	$(document).ready(function() {
             		$('#confirmAddDept').click(function() {
             			var deptName = $('#deptName').val().trim();
             			if (deptName === '' || deptName === null) {
             				$('#deptNameMsg').html('不能为空');
             			} else if(deptName.length > 6) {
             				$('#deptNameMsg').html('长度过长');
             			} else {
                 			//从数据库中查询该部门是否已经存在 如果已经存在 提示已有该部门 否则提交表单
                 			$.post('IfExistDeptNameServlet', {
                 				'deptName': deptName
                 			}, function(data) {
                 				if (data.trim() === 'true') {
                 					$('#deptNameMsg').html('该部门已存在');
                 					$('#deptName').val('');
                 					$('#deptName').focus();
                 				} else {
                 					$('#form1').submit();
                 				}
                 			})
             			}
             		})
             	}) 
             </script>
             
    </body>
</html>