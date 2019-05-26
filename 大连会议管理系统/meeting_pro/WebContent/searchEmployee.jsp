<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
     <%@include file="include/head.jsp" %>
    <body>
        <%@include file="include/page-header.jsp" %>
        
        
        <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    员工管理  > 搜索员工
                </div>
                
                <form action="SearchEmployeesServlet" method="post">
                    <fieldset style="position: relative">
                        <legend>查找员工</legend>
                        <table class="formtable"style="margin-left:40px;">
	                            <tr>
								<span style="position:absolute; top:36px;
								 display: inline-block;">姓名：</span>
								<td><input type="text" id="empName" name="empName"
									maxlength="20"
									placeholder="请输入员工的真实姓名" value="${requestScope.empName }"/></td>
								<td>账号名：</td>
								<td><input type="text" id="username" name="username"
									maxlength="20" 
									placeholder="请输入员工的用户名" value="${requestScope.username }"/></td>
								<td>状态：</td>
								<td>
									<input type="radio" id="status" name="empStatus" value="1" 
									<c:if test="${requestScope.empStatus == 1 }">
										checked
									</c:if>
									/>
									<label>已批准</label>
									<input type="radio" id="status" name="empStatus" value="0" 
									<c:if test="${requestScope.empStatus == 0 }">
										checked
									</c:if>
									/>
									<label>待审批</label>
									<input type="radio" id="status" name="empStatus" value="2"
									<c:if test="${requestScope.empStatus == 2 }">
										checked
									</c:if>
									 />
									<label>已关闭</label>
									<input type="radio" id="status" name="empStatus" value="3" 
									<c:if test="${requestScope.empStatus == 3 }">
										checked
									</c:if>
									 />
								<label>所有</label>
								</td>
							</tr>
							<tr>
								<td colspan="6" class="command">
								<input type="hidden" class="clickbutton" name="pageNum" value="1" /> 
								<input type="submit" class="clickbutton" value="查询" /> 
								<input type="reset" class="clickbutton" value="重置" /></td>
								</tr>
                        </table>
                    </fieldset>
                    
                    
               <table class="listtable">
               <div>
				<h3 style="text-align:center;color:black">查询结果</h3>
				<div class="pager-header">
					<div class="header-info">
						共<span class="info-number">${empCount}</span>条结果，
						分成<span class="info-number">${allPageNum}</span>页显示，
						当前第<span class="info-number">${pageNum}</span>页
					</div>
					<div class="header-nav">
						<input type="button" class="clickbutton" value="首页"
						onclick="changeToPostMethod('SearchEmployeesServlet', {
									'pageNum': 1,
									'username': '${requestScope.username}',
									'empName': '${requestScope.empName }',
									'empStatus': ${requestScope.empStatus }
								})"/>
						<c:if test="${pageNum ne '1'}">
							<input type="button" class="clickbutton" value="上页"
								onclick="changeToPostMethod('SearchEmployeesServlet', {
									'pageNum': ${requestScope.pageNum-1},
									'username': '${requestScope.username}',
									'empName': '${requestScope.empName }',
									'empStatus': ${requestScope.empStatus }
								})"/>
						</c:if>
						<c:if test="${pageNum ne allPageNum}">
							<input type="button" class="clickbutton" value="下页"
								onclick="changeToPostMethod('SearchEmployeesServlet', {
									'pageNum': ${requestScope.pageNum+1},
									'username': '${requestScope.username}',
									'empName': '${requestScope.empName }',
									'empStatus': ${requestScope.empStatus }
								})"/>
						</c:if>
						<input type="button" class="clickbutton" value="末页"
							onclick="changeToPostMethod('SearchEmployeesServlet', {
									'pageNum': ${requestScope.allPageNum},
									'username': '${requestScope.username}',
									'empName': '${requestScope.empName }',
									'empStatus': ${requestScope.empStatus }
								})"/>
						跳到第<input type="text" id="toPage" name="pageNum"
							class="nav-number"  value="${requestScope.pageNum}">页
							 <a href="javascript:;" id="toThePage"><input type="button" class="clickbutton"
							value="跳转"  /></a>
						
							<input type="hidden" value="${allPageNum}" id="allPageNum" />
							<input type="hidden" value="${requestScope.username}" id="newUsername" />
							<input type="hidden" value="${requestScope.empName}" id="newEmpName" />
							<input type="hidden" value="${requestScope.empStatus}" id="newEmpStatus" />
							
					</div>
				</div>
			</div>
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
                    
                    <c:forEach var="emp" items="${requestScope.emps }">
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
								<button>
								<a href="javascript:;"
								onclick="changeToPostMethod('UpdateSelectUserFromSearchServlet', {
									'empId': '${emp.empId }',
									'newPageNum': ${requestScope.pageNum},
									'newUsername': '${requestScope.username}',
									'newEmpName': '${requestScope.empName }',
									'newEmpStatus': ${requestScope.empStatus }
								})">修改</a>
								</button>
							</td>
							<td>
								<button>
								<a type="button" href="javascript:;"
								onclick="changeToPostMethod('DeleteUserFromSearchServlet', {
									'empId': '${emp.empId }',
									'pageNum': ${requestScope.pageNum},
									'username': '${requestScope.username}',
									'empName': '${requestScope.empName }',
									'empStatus': ${requestScope.empStatus }
								})">删除</a>
								</button>
							</td>
	                    </tr>
                    </c:forEach>
                </table>
                    
                </form>
                
                <table class="listtable">
                   
                </table>
            </div>
        </div>
         <%@include file="include/page-footer.jsp" %>
         
         
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

        		
	   	         $('#toThePage').click(function() {
	   	 			var pageNum = $('#toPage').val();
	   	 			var allPageNum = $('#allPageNum').val();
	   	 			var username = $('#newUsername').val();
	   	 			var empName = $('#newEmpName').val();
	   	 			var empStatus = $('#newEmpStatus').val();
	   	 			if (!(pageNum >=1 && pageNum <= allPageNum)) {
	   	 				alert('页数有误');
	   	 				document.getElementById('toPage').value = '';
	   	 				document.getElementById('toPage').focus();
	   	 			} else {
		   	 			changeToPostMethod('SearchEmployeesServlet', {
							'pageNum': pageNum,
							'username': username,
							'empName': empName,
							'empStatus': empStatus
						})
	   	 			}
	   	 		})
	 		
         </script>
         
    </body>
</html>