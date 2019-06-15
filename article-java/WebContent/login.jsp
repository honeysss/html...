<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/common.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
    <style>
    	span{
    		color: red;
    		font-size:11px;
    	}
    </style>
<title>login</title>
</head>
<body>
<!-- 好像是 如果是ajax异步校验传过去的值不会乱码 不需要强转  如果是表单传过去的值会乱码 需要强转 -->
	<div class="page-body">
           
            <div class="page-content">
                <form action="search.action?pageNum=1&title=" id="form1" method="post">
                    <fieldset>
                        <legend>登录</legend>
                        <table class="formtable" style="width:56%">
                            <tr>
                                <td><label class="modify-h2 control-label">用户名：</label></td>
                                <td>
	                                <div class="modify1 form-group">
							                <input id="username" type="text" name="username"
		                                    placeholder="请输入您的姓名" value="${requestScope.username }"
		                                    onblur="checkName()" class="modify-title form-control"  style="display:inline-block;">
		                                    &nbsp;&nbsp;&nbsp;<span id="userMsg"></span>
							        </div>
                                </td>
                            </tr>
                            <tr>	
                                <td><label class="modify-h2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label></td>
                                <td>
                                	<div class="modify1 form-group" >
							                <input id="pwd" type="password" name="pwd"
		                                     placeholder="请输入您的密码"
		                                     onblur="checkPwd()" class="modify-title form-control"  style="display:inline-block;">
		                                    &nbsp;&nbsp;&nbsp;<span id="pwdMsg"></span>
							        </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="button" value="登录" class="clickbutton btn btn-default" onclick="check()" />
                                    <input type="button" value="注册" class="clickbutton btn btn-default" onclick="window.location.href='regist.jsp';"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        
        <script>
	        function checkName() {
				var name = document.getElementById('username').value;
				var userMsg = document.getElementById('userMsg');
				if (name === '' || name === null) {
					userMsg.innerHTML = '用户名不能为空';
				} else {
					$.post('ifExistUsername.action', {
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
				if (name === '' || name === null) {
					userMsg.innerHTML = '用户名不能为空';
				} else if (pwd === '' || pwd === null) {
					pwdMsg.innerHTML = '密码不能为空';
				} else {
					var flag = true;
					$.when(
						$.post('ifExistUsername.action', {
							username: name
						}, function(data) {
							if(data === 'false') {
								userMsg.innerHTML = '该用户不存在';
								flag = false;
							}
						}),
						$.post('ifThePwdIsRight.action', {
							username: name,
							password: pwd
						}, function(data) {
							if (flag) {
								if(data === 'false') {
									pwdMsg.innerHTML = '密码有误';
									flag = false;
								}
							}
							
						})
					).done(function() {
						console.log(flag)
						if(flag) {
							form1.submit();
						}
					})
				}
			}
        </script>
        
</body>
</html>