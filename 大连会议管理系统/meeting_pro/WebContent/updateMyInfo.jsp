<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
    </head>
    <body>
        <%@include file="include/page-header.jsp" %>
        
        
        <div class="page-body">
           
            <%@include file="include/userPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                   	个人中心 > 修改信息
                </div>
				<form action="UpdateMyInfoServlet" method="post" id="form1">
					<input type="hidden" value="${emp.empId }" name="empId" />
					<input type="hidden" value="${emp.username }" name="oldUsername" />
                    <fieldset>
                        <legend>我的信息</legend>
                        <table class="formtable" style="width:50%">
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
                                <td>账号名:</td>
                                <td>
                                    <input id="username" type="text" name="username"
                                    value="${emp.username }"
                                    onblur="checkName('${emp.username }')"/>&nbsp;&nbsp;&nbsp;
                                    <span id="userMsg"></span>
                                </td>
                            </tr>
                            <tr>
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