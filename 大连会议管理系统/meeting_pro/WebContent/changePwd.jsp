<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
    <body>
        <%@include file="include/page-header.jsp" %>
        
        <c:if test="${sessionScope.role == 0 }">
        	 <%@include file="include/userPageBar.jsp" %>
        </c:if>
         <c:if test="${sessionScope.role == 1 }">
        	 <%@include file="include/adminPageBar.jsp" %>
        </c:if> 
            <div class="page-content">
                <div class="content-nav">
                	    修改密码
                </div>
                <form action="ChangePwdServlet" method="post" id="form1">
                
                <input type="text" style="display:none;" name="empId" value="${empId }"/>
                    <fieldset>
                        <legend>修改密码信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>原密码:</td>
                                <td>
                                    <input id="oldPwd" type="password" name="oldPwd"
                                    onblur="checkOldPwd('${pwd}')"/>
                                    <span id="oldPwdMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>新密码:</td>
                                <td>
                                    <input id="newPwd" type="password" name="newPwd" 
                                    onblur="checkNewPwd()" placeholder="请输入6-12位密码"/>
                                    <span id="newPwdMsg" ></span>
                                </td>
                            </tr>
                            <tr>
                                <td>确认密码:</td>
                                <td>
                                    <input id="pwdAgain" type="password" name="pwdAgain"
                                    onblur="checkPwdAgain()"  placeholder="请输入6-12位密码"/>
                                    <span id="pwdAgainMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="button" value="确认修改" class="clickbutton" onclick="check('${pwd}')"/>
                                    <input type="button" value="返回" class="clickbutton" onclick="window.history.back()"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        
        <%@include file="include/page-footer.jsp" %>
       
       <script>
	       function checkOldPwd(pwd) {
		   		var oldPwd = document.getElementById('oldPwd').value;
		   		var oldPwdMsg = document.getElementById('oldPwdMsg');
		   		if (oldPwd === '' || oldPwd === null) {
		   			oldPwdMsg.innerHTML = '不能为空';
		   		}else if(pwd != oldPwd) {
		   			oldPwdMsg.innerHTML = '密码输入有误';
		   		} else {
		   			oldPwdMsg.innerHTML = '';
		   		}
		   	}
	       
	       function checkNewPwd() {
	    		var newPwd = document.getElementById('newPwd').value;
	    		var newPwdMsg = document.getElementById('newPwdMsg');
	    		if (newPwd === '' || newPwd === null) {
	    			newPwdMsg.innerHTML = '不能为空';
	    		} else if(!(newPwd.length >= 6 && newPwd.length <= 12)) {
	    			newPwdMsg.innerHTML = '密码长度有误';
		   		} else {
	    			newPwdMsg.innerHTML = '';
	    		}
	    	}
	    		
	    	function checkPwdAgain() {
	    		var newPwd = document.getElementById('newPwd').value;
	    		var pwdAgain = document.getElementById('pwdAgain').value;
	    		var pwdAgainMsg = document.getElementById('pwdAgainMsg');
	    		if (pwdAgain === '' || pwdAgain === null) {
	    			pwdAgainMsg.innerHTML = '不能为空';
	    		} else if(pwdAgain != newPwd) {
	    			pwdAgainMsg.innerHTML = '两次密码不一致';
	    		} else{
	    			pwdAgainMsg.innerHTML = '';
	    		}
	    	}
	    	function check(pwd) {
		   		var oldPwd = document.getElementById('oldPwd').value;
		   		var oldPwdMsg = document.getElementById('oldPwdMsg');
	    		var newPwd = document.getElementById('newPwd').value;
	    		var newPwdMsg = document.getElementById('newPwdMsg');
	    		var pwdAgain = document.getElementById('pwdAgain').value;
	    		var pwdAgainMsg = document.getElementById('pwdAgainMsg');
	    		var form1 = document.getElementById('form1');
	    		if (oldPwd === '' || oldPwd === null) {
		   			oldPwdMsg.innerHTML = '不能为空';
		   		} else if(pwd != oldPwd) {
		   			oldPwdMsg.innerHTML = '密码输入有误';
		   		} else if (newPwd === '' || newPwd === null) {
	    			newPwdMsg.innerHTML = '不能为空';
	    		} else if(!(newPwd.length >= 6 && newPwd.length <= 12)) {
	    			newPwdMsg.innerHTML = '密码长度有误';
		   		} else if (pwdAgain === '' || pwdAgain === null) {
	    			pwdAgainMsg.innerHTML = '不能为空';
	    		} else if(pwdAgain != newPwd) {
	    			pwdAgainMsg.innerHTML = '两次密码不一致';
	    		} else{
	    			form1.submit();
	    		}
	    	}	
       </script>
       
       
    </body>
</html>