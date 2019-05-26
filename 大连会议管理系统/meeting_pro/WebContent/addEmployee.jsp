<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
    <body>
        <%@include file="include/page-header.jsp" %>
        
        
        <div class="page-body">
          
            <%@include file="include/adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                  	 人员管理  > 增加员工
                </div>
                <form action="AddEmpServlet" method="post" id="form1">
                    <fieldset>
                        <legend>员工信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="username" type="text" name="username"
                                    placeholder="请输入员工的姓名"
                                    onblur="checkName()"/>&nbsp;&nbsp;&nbsp;
                                    <span id="userMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="pwd" type="password" name="userPwd"
                                     placeholder="请输入员工的密码"
                                     onblur="checkPwd()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="pwdMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>确认密码:</td>
                                <td>
                                    <input id="pwd2" type="password" name="pwd2"
                                     placeholder="请再次输入密码"
                                     onblur="checkPwd2()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="pwdMsg2"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>真实姓名:</td>
                                <td>
                                    <input id="empName" type="text" name="empName"
                                     placeholder="请输入员工的真实姓名"
                                     onblur="checkEmpName()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="empNameMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>电话号码:</td>
                                <td>
                                    <input id="tel" type="text" name="empTel"
                                     placeholder="请输入员工的电话号码"
                                     onblur="checkTel()"/>&nbsp;&nbsp;&nbsp;
                                     <span id="telMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>邮箱:</td>
                                <td>
                                    <input id="email" type="text" name="empEmail"
                                     placeholder="请输入员工的邮箱"
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
                                <td>账号状态:</td>
                                <td>
                                	<select name="userStatus">
                                		<option value="0">正在审核</option>
                                		<option value="1">审核通过</option>
                                		<option value="2">审核未通过</option>
                                	</select>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>身份:</td>
                                <td>
                                	<select name="userRole">
                                		<option value="0">普通用户</option>
                                		<option value="1">管理员</option>
                                	</select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="button" value="添加" class="clickbutton" onclick="check()" />
                                    <input type="button" value="返回" class="clickbutton" onclick="window.histroy.back()"/>
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