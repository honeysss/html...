<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
    <!-- 新的噢21-->
    <%
    	String username = null;
    	String pwd = null;
    	Cookie[] cks = request.getCookies();
    	if (cks != null) {
    		for (Cookie ck: cks) {
    			if ("username".equals(ck.getName())) {
    				username = ck.getValue();
    			}
    			if ("pwd".equals(ck.getName())) {
    				 pwd = ck.getValue();
    			}
    		}
    		
    		if (username != null && pwd != null) {
    			request.getRequestDispatcher("LoginServlet?username=" + username + "&pwd=" + pwd).forward(request, response);
    		}
    	}
    
    %>
    
    
    <body>
        <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                	欢迎访问会议管理系统
            </div>
            <div class="header-quicklink">
              <strong></strong>
                <a href="changepassword.html"></a>
            </div>
        </div>
        <div class="page-body">
           
            <div class="page-content">
                <div class="content-nav">
                  	  登录
                </div>
                <form action="LoginServlet" id="form1" method="post">
                    <fieldset>
                        <legend>登录信息</legend>
                        <span style="color:red;">${msg }</span>
                        
                       <%	
                       		request.setCharacterEncoding("utf-8");
                       %>
                        
                        <span style="color:red;">${param.msg }</span>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="username" type="text" name="username"
                                    placeholder="请输入您的姓名" value="${requestScope.username }"
                                    onblur="checkName()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="userMsg"></span>
                                </td>
                            </tr>
                            <tr>	
                                <td>密码:</td>
                                <td>
                                    <input id="pwd" type="password" name="pwd"
                                     placeholder="请输入您的密码"
                                     onblur="checkPwd()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="pwdMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <select name="maxAge" 
                                     style="padding: 2px;">
                                    	<option value="0">每次都需要输入用户名和密码</option>
                                    	<option value="1">一分钟内不需要</option>
                                    	<option value="30">三十分钟内不需要</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="button" value="登录" class="clickbutton" onclick="check()" />
                                    <input type="button" value="注册" class="clickbutton" onclick="window.location.href='ToUserRegisterJspServlet';"/>
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
	        function checkName() {
				var name = document.getElementById('username').value;
				var userMsg = document.getElementById('userMsg');
				if (name === '' || name === null) {
					userMsg.innerHTML = '用户名不能为空';
				} else {
					$.post('IsAlreadyExistTheUsernameServlet', {
						'username': name
					}, function(data) {
						if (data === 'true') {
							userMsg.innerHTML = '';
						} else {
							userMsg.innerHTML = '该用户不存在';
						}
					})
				}
			}
	
			function checkPwd() {
				var pwd = document.getElementById('pwd').value;
				var pwdMsg = document.getElementById('pwdMsg');
				if (pwd === '' || pwd === null) {
					pwdMsg.innerHTML = '密码不能为空';
				} else {
					pwdMsg.innerHTML = '';
				}
			}
			
			function check() {
				var name = document.getElementById('username').value;
				var pwd = document.getElementById('pwd').value;
				var pwdMsg = document.getElementById('pwdMsg');
				var userMsg = document.getElementById('userMsg');
				var form1 = document.getElementById('form1');
				$.post('IsAlreadyExistTheUsernameServlet', {
					'username': name
				}, function(data) {
					if(data === 'false') {
						userMsg.innerHTML = '该用户不存在';
					} else if (pwd === '' || pwd === null) {
						pwdMsg.innerHTML = '密码不能为空';
					} else if (name === '' || name === null) {
						userMsg.innerHTML = '用户名不能为空';
					} else {
						form1.submit();
					}
				})
				
			}
        </script>
    </body>
</html>