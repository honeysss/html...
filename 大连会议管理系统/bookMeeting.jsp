<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.icss.service.*,  com.icss.vo.*"%>
<!DOCTYPE html>
<html>
	<head>
    	<%@include file="include/head.jsp" %>	
        <style type="text/css">
            #divfrom{
                float:left;
                width:150px;
            }
            #divto{
                float:left;
                width:150px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 7px;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #selEmployees{
                display:block;
                width:100%;
                height:200px;
            }
            #selSelectedEmployees{
                display:block;
                width:100%;
                height:225px;
            }
        </style>
    </head>
    
    <body>
    	 <%@include file="include/page-header.jsp" %>
        <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    会议管理 > 增加会议
                </div>
                <form action="AddMeetingServlet" method="post">
                <input type="hidden" name="mId" value="${requestScope.mId }" />
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" id="mName" maxlength="20" name="mName"
									 placeholder="请输入会议名称" value="${requestScope.mName }"/>
                                </td>
                            </tr>
                            <tr>
                                <td>会议人数：</td>
                                <td>
                                    <%-- <input type="text" id="mNum" maxlength="20" name="mNum"
									 placeholder="请输入会议预计人数" value="${requestScope.mNum }"/> --%>
									 <span id="showmNum">0</span>
									 <input type="hidden" name="mNum" id="mNum"/>
                                </td>
                            </tr>
                            <tr>
                                <td>开始时间：</td>
                                <td>
                                    <input type="datetime-local" id="startTime" name="startTime" onblur="ifCanUse()"
                                    placeholder="请输入会议开始时间"  value="${requestScope.startTime }"/>
                                    <span id="startTimeMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>结束时间：</td>
                                <td>
                                    <input type="datetime-local" id="endTime" name="endTime"  onblur="ifCanUse()"
                                    placeholder="请输入会议结束时间" value="${requestScope.endTime }" />
                                    <span id="endTimeMsg"></span>
                                </td>
                            </tr>
							<tr>
                                <td>会议室名称：</td>
                                <td>
                                    <select name="mrId" id="mrId" onchange="ifCanUse()"> 
                                    	<option value="">请选择会议室</option> 
                                        <c:forEach var="mr" items="${requestScope.mrs }">
                                        	<c:if test="${mr.mrStatus == 0}">
		                                     	<option value="${mr.mrId }"
		                                     	<c:if test="${mr.mrId == requestScope.mrId}">
		                                     	selected
		                                     	</c:if>
												>
		                                     		${mr.mrName }
		                                     	</option>
	                                     	</c:if>
										</c:forEach>
                                     </select>
                                    <span id="mrIdMsg"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="mRemark" rows="5" name="mRemark"
                                     placeholder="请输入会议说明" >${requestScope.mRemark }</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>选择参会人员：</td>
                                <td>
                                    <div id="divfrom">
                                    
                                     <select name="deptId" id="deptId" onchange="changeDeptId()">
	                                     
	                                     <option value="0">请选择部门</option>
	                                     <c:forEach var="dept" items="${requestScope.depts}">
		                                     <option value="${dept.deptId }" 
		                                     <c:if test="${dept.deptId == requestScope.deptId }">
		                                     	selected
		                                     </c:if>
		                                     >
		                                     	${dept.deptName }
		                                     </option>
	                                     </c:forEach>
                                     </select>
                                        <select id="selEmployees" multiple="true">
                                        
                                        
                                      <%

                                    	ArrayList<Integer> empIdList = new ArrayList<Integer>();
                                    	Object empIdList3 = request.getAttribute("empIdList");
                                    	
                                    	
                                    	if (empIdList3 != null) {
                                        	String[] empIdList2 = ((((String)empIdList3).replace("[", "")).replace("]", "")).split(",");
                                        	for (int i = 0; i < empIdList2.length; i ++) {
                                        		if (!empIdList2[i].equals("")) {
                                        			empIdList.add(Integer.parseInt(empIdList2[i].trim()));
                                        		}
                                        	}
                                    	}
                                   	

                  	
                                   	%>
                                        		<!-- 如果已选中的员工和这里的员工id不相同 显示 -->
			                                    <%

		                                    	  EmployeeService es = new EmployeeService();
				                                    int deptId = 0;
				                                 	if (request.getAttribute("deptId") != null) {
				                                 		deptId = Integer.parseInt(request.getAttribute("deptId").toString());
				                                 	} 
				                                 	ArrayList<Employee> employeeList = new ArrayList<Employee>();
				                                 	if (deptId == 0) {
				                                 		employeeList = es.selectAllUserService();
				                                 	} else {
				                                 		employeeList = es.selectUserByDeptIdService(deptId);
				                                 	}
		                                        	if (employeeList.size() != 0) {
		                                        		for (int i = 0; i <employeeList.size(); i ++) {
		                                        			if (employeeList.get(i).getEmpName() != null && !employeeList.get(i).getEmpName().equals("")
		                                        					&& empIdList.indexOf(employeeList.get(i).getEmpId()) == -1) {
		                                        	%>		
		                                        	
		                                        		<option value="<%= employeeList.get(i).getEmpId() %>"><%= employeeList.get(i).getEmpName() %></option>
		                                        	<%
		                                            		}
		                                        		}
	                                        	} else {
	                                        		
	                                        	}
	                                        	%>
                                        	
                                        </select>
                                        
                                        
                                    </div>
                                    
                                    <div id="divoperator">
                                    	<input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                                              
      
                                    </div>
                                    	
                 			
                                    
                                    <div id="divto">
                                        <select id="selSelectedEmployees" multiple="multiple" name="empId">
                                    	  
                                    	   <!-- 如果已经选过该员工 一直显示在这里 -->
                                    	   <%
	                                        for (int i = 0; i < empIdList.size(); i ++) {
	                                        	
	                                        
	                                        %>
	                                        <option value="<%= es.selectUserByIdService(empIdList.get(i)).getEmpId() %>">
	                                        <%= es.selectUserByIdService(empIdList.get(i)).getEmpName() %>
	                                        </option>
	                                        
	                                        <% } %>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td></td>
                                <td class="command" colspan="2">
                                
                                    	
                                    <input type="hidden" name="empIdList" id="empIdList" <% if (empIdList.size() != 0) { %> value="<%= empIdList %>" <% } %>/>
                                    	
                                    <input type="submit" class="clickbutton" value="添加会议"/>
                                    <input type="reset" class="clickbutton" value="重置" onclick="clearSelectedEmployees()"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <%@include file="include/page-footer.jsp" %>
        <script src="js/bookMeeting.js"></script>
        
        <script type="application/javascript">
	        
            
            
        </script>
        
        
</html>