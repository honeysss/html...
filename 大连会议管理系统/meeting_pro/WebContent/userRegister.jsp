<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
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
                  	  注册
                </div>
                <form action="UserRegisterServlet" method="post" id="form1">
                    <fieldset>
                        <legend>注册信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="username" type="text" name="username"
                                    placeholder="请输入您的姓名"
                                    onblur="checkName()"/>&nbsp;&nbsp;&nbsp;
                                    <span id="userMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="pwd" type="password" name="userPwd"
                                     placeholder="请输入6-12位密码"
                                     onblur="checkPwd()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="pwdMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>确认密码:</td>
                                <td>
                                    <input id="pwd2" type="password" name="pwd2"
                                     placeholder="请再次输入您的密码"
                                     onblur="checkPwd2()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="pwdMsg2"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>真实姓名:</td>
                                <td>
                                    <input id="empName" type="text" name="empName"
                                     placeholder="请输入您的真实姓名"
                                     onblur="checkEmpName()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="empNameMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>电话号码:</td>
                                <td>
                                    <input id="tel" type="text" name="empTel"
                                     placeholder="请输入您的电话号码"
                                     onblur="checkTel()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="telMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>邮箱:</td>
                                <td>
                                    <input id="email" type="text" name="empEmail"
                                     placeholder="请输入您的邮箱"
                                     onblur="checkEmail()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="emailMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>部门:</td>
                                <td>
                                    <select name="empDeptId">
                                    	<c:forEach var="dept" items="${depts }">
                                    		<option value="${dept.deptId }">${dept.deptName }</option>
                                    	</c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="button" value="注册" class="clickbutton" onclick="check()" />
                                    <input type="reset" value="清空" class="clickbutton" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <%@include file="include/page-footer.jsp" %>
        
        
        <script src="js/jquery-1.8.3.min.js"></script>
        <script src="js/userRegister.js"></script>
        
        
    </body>
</html>