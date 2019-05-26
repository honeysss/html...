<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-header">
	 <div class="header-banner">
	     <img src="images/header.png" alt="CoolMeeting"/>
	 </div>
	 <div class="header-title">
	     	欢迎访问会议管理系统
	 </div>
	 <div class="header-quicklink" style="margin-top: 30px;">
        欢迎您, <strong>${sessionScope.empName }</strong>
     <form action="changePwd.jsp" style="display:inline-block;">
	     <input class="update" type="submit" value="修改密码" />
	     <input class="update" type="button" value="退出" 
	     onclick="quit()" />
	     <script>
	     	function quit() {
	             var con = confirm('您确定要退出吗?');
	             if (con) {
	             	$.post('LogOutServlet', function(data) {
	             		setTimeout(function() {//过多长时间执行一个函数
	             			changeToPostMethod('login.jsp', {
	             				'msg': '您已退出',
	             				'username': data
	             			})
	             		}, 30)
	             	})
	             }
	     	}
	     </script>
     </form>
    </div>
    <p style="margin-top: 75px;float: right; margin-right: -175px;">您是第<strong style="color: red; font-size: 20px; padding: 4px;">${count }</strong>次访问该网站</p>
</div>