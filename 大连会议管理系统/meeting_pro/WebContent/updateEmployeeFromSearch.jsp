<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
    </head>
    <body>
        <%@include file="include/page-header.jsp" %>
        
        
        <div class="page-body">
           
            <%@include file="include/adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                   	人员管理 > 搜索员工  >修改
                </div>
				<form action="UpdateEmpFromSearchServlet" method="post" id="form1">
					<input type="hidden" value="${emp.empId }" name="empId" />
					<input type="hidden" value="${emp.username }" name="oldUsername" />
					<input type="hidden" value="${emp.userPwd }" name="odlPwd" />
                    <fieldset>
                        <legend>员工信息</legend>
                        <table class="formtable" style="width:50%">
                        	<tr>
                        		<td>员工id</td>
                        		<td>${emp.empId }</td>
                        	</tr>
                            <tr>
                                <td>真实姓名:</td>
                                <td>
                                    <input id="empName" type="text" name="empName"
                                     value="${emp.empName }"/>&nbsp;&nbsp;&nbsp;
                                     <span id="empNameMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>电话号码:</td>
                                <td>
                                    <input id="tel" type="text" name="empTel"
                                    value="${emp.empTel }"/>&nbsp;&nbsp;&nbsp;
                                     <span id="telMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>邮箱:</td>
                                <td>
                                    <input id="email" type="text" name="empEmail"
                                    value="${emp.empEmail }"/>&nbsp;&nbsp;&nbsp;
                                     <span id="emailMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>部门:</td>
                                <td>
                                    <select name="empDeptId">
                                    	<c:forEach var="dept" items="${depts }">
                                    		<option value="${dept.deptId }"
                                    		<c:if test="${emp.empDeptId eq dept.deptId }">
                                    		selected
                                    		</c:if>
                                    		>
                                    			${dept.deptName }
                                    		</option>
                                    	</c:forEach>
                                    </select>
                                </td>
                            </tr>
                             <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="username" type="text" name="username"
                                    value="${emp.username }"
                                    onblur="checkName('${emp.username }')"/>&nbsp;&nbsp;&nbsp;
                                    <span id="userMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="pwd" type="text" name="userPwd"
                                    value="${emp.userPwd }"/>&nbsp;&nbsp;&nbsp;
                                     <span id="pwdMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>账号状态:</td>
                                <td>
                                	<select name="userStatus">
                                		<option value="0" 
                                    		<c:if test="${emp.userStatus == 0  }">
                                    		selected
                                    		</c:if>
                                    		>正在审核</option>
                                		<option value="1" 
                                    		<c:if test="${emp.userStatus == 1  }">
                                    		selected
                                    		</c:if>
                                    		>审核通过</option>
                                		<option value="2" 
                                    		<c:if test="${emp.userStatus == 2  }">
                                    		selected
                                    		</c:if>
                                    		>审核未通过</option>
                                	</select>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>身份:</td>
                                <td>
                                	<select name="userRole">
                                		<option value="0" <c:if test="${emp.userRole == 0  }">
                                    		selected
                                    		</c:if>>普通用户</option>
                                		<option value="1" <c:if test="${emp.userRole == 1  }">
                                    		selected
                                    		</c:if>>管理员</option>
                                	</select>
                                </td>
                            </tr>
                            <tr>
                            	<input type="hidden" value="${newPageNum}" id="newPageNum" name="newPageNum"/>
								<input type="hidden" value="${requestScope.newUsername}" id="newUsername" name="newUsername"/>
								<input type="hidden" value="${requestScope.newEmpName}" id="newEmpName" name="newEmpName"/>
								<input type="hidden" value="${requestScope.newEmpStatus}" id="newEmpStatus" name="newEmpStatus"/>
                                <td colspan="2" class="command">
                                    <input type="button" value="确定" class="clickbutton" onclick="check('${emp.username }')" />
                                    <input type="button" value="返回" class="clickbutton" onclick="window.history.back()"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <%@include file="include/page-footer.jsp" %>
        <script src="js/jquery-1.8.3.min.js"></script>
        <script>
	        function checkName(username) {
	        	var name = document.getElementById('username').value;
	        	var userMsg = document.getElementById('userMsg');
	        	if (name === '' || name === null) {
	        		userMsg.innerHTML = '用户名不能为空';
	        	} else {
	        		//如果用户名不为空判断是否已经存在这个用户名
	        		if (username != name) {
	        			$.post('IsAlreadyExistTheUsernameServlet', {
		        			'username': name
		        		}, function(data) {
		        			if (data === 'true') {
		        				userMsg.innerHTML = '该用户名已被注册';
		        			} else {
		        				userMsg.innerHTML = '';
		        			}
		        		})
	        		} else {
        				userMsg.innerHTML = '';
        			}
	        		
	        	}
	        }
	        
	    	
	        function check(username) {
	        	var name = document.getElementById('username').value;
	        	var userMsg = document.getElementById('userMsg');
	        	var form1 = document.getElementById('form1');
	        	var flag = true;
	        	
	        	if (username === name) {
	        		form1.submit();
	        		flag = false;
	        	}
	        	
	        	if (flag) {
	        		$.post('IsAlreadyExistTheUsernameServlet', {
		        		'username': name
		        	}, function(data) {
		        		if (data.trim() === 'true') {
		        			userMsg.innerHTML = '该用户名已被注册';
		        		} else {
		        			form1.submit();
		        		}
		        	})
	        	}
	        }
	        
        </script>
    </body>
</html>