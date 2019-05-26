<%@ page language="java"
	import="java.util.*, com.icss.service.*, com.icss.vo.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
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
                padding:94px 5px;
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
    <body>
        <%@include file="include/page-header.jsp" %>
         <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
            
         </div>
            <div class="page-content">
                <div class="content-nav">
                  个人中心  > 会议预定 > 修改会议预定
                </div>
                <form method="post" action="ConfirmUpdateMeetingServlet" id="form1">
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                             <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" id="mName" maxlength="20" name="mName"
									 placeholder="请输入会议名称" 
									 <c:if test="${requestScope.mName != null}">
									 	value="${requestScope.mName }"
									 </c:if>
									 <c:if test="${requestScope.meeting.mName != null}">
									 	value="${requestScope.meeting.mName }"
									 </c:if>
									 
									 />
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
                                
                                <!-- 获取原先的会议室id 如果会议室id等于该会议的会议室id-->
                                	<c:forEach var="mr" items="${requestScope.mrs }">
                                     	<c:if test="${mr.mrId == requestScope.meeting.mrId}">
                                     		<c:set var="theMrId" value="${mr.mrId }"></c:set>
                                     	</c:if>
									</c:forEach>
									
	                                <c:if test="${requestScope.mrId != 0 }">
	                                	<c:set var="theMrId" value="${requestScope.mrId }"></c:set>
	                                </c:if>
                                <td>开始时间：</td>
                                <td>
                                	<span id="time1" style="display:none;">
                                	
                                		<%
                                			if (request.getAttribute("startTime") != null) {
                                				
                                		%>
                                			<%=request.getAttribute("startTime")%>
                                		<%
                                		} else {
                                		%>
                                			<%=((Meeting)request.getAttribute("meeting")).getStartTime() %>
                                		
                                		<% } %>
	                                </span>
                                    <input type="datetime-local" id="startTime" name="startTime"
                                    placeholder="请输入会议开始时间" onblur="ifCanUse('${theMrId }')"/>
                                    <span id="startTimeMsg"></span>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>结束时间：</td>
                                <td>
                                    <span id="time2" style="display:none;">
                                    	<%
                                			if (request.getAttribute("endTime") != null) {
                                				
                                		%>
                                			<%=request.getAttribute("endTime")%>
                                		<%
                                		} else {
                                		%>
                                			<%=((Meeting)request.getAttribute("meeting")).getEndTime() %>
                                		
                                		<% } %>
                                    
                                    	
                                    </span>
                                    <input type="datetime-local" id="endTime" name="endTime" 
                                    placeholder="请输入会议结束时间" onblur="ifCanUse('${theMrId }')"/>
                                    <span id="endTimeMsg"></span>
                                </td>
                            </tr>
							<tr>
                            <tr>
                                <td>会议室名称：</td>
                                <td>
                                
                                    <select name="mrId" id="mrId" onchange="ifCanUse('${theMrId }')"> 
                                    	<option value="">请选择会议室</option> 
                                        <c:forEach var="mr" items="${requestScope.mrs }">
                                        	<c:if test="${mr.mrStatus == 0}">
		                                     	<option value="${mr.mrId }"
		                                     	<c:if test="${mr.mrId == theMrId}">
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
                                <c:if test="${requestScope.mDescribe != null}">
                                 	<c:set var="requestScopemDescribe" value="true"></c:set>
                                 </c:if>
                                 <c:if test="${requestScope.mDescribe == null}">
                                 	<c:set var="requestScopemDescribe" value="false"></c:set>
                                 </c:if>
                                <td> <textarea rows="5" name="mRemark"
                                     placeholder="请输入会议说明"  id="mDescribe"
                                     ><c:if test="${requestScopemDescribe == true }">${requestScope.mDescribe}</c:if><c:if test="${requestScopemDescribe != true }">${requestScope.meeting.mDescribe}</c:if></textarea>
                                </td>
                               
                            </tr>
                            <tr>
                                <td>参会人员：</td>
                                <td>
                                    <table class="listtable">
                                        <tr class="listheader">
                                            <th>姓名</th>
                                            <th>联系电话</th>
                                            <td>电子邮件</td>
                                            <td>操作</td>
                                        </tr>
                                        <c:forEach var="emp" items="${requestScope.empList}">
                                        <span class="joinedEmpId" style="display: none;">${emp.empId }</span>&nbsp;
                                        <tr>
                                            <td>${emp.empName}</td>
                                            <td>${emp.empTel}</td>
                                            <td>${emp.empEmail}</td>
                                            <td>
                                            	<button>
                                            		<a href="javascript:;" onclick="deleteEmp(${emp.empId }, ${requestScope.meeting.mId})">删除</a>
                                            	</button>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>增加参会人员：</td>
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
                                        <select id="selEmployees" multiple="multiple">
	                                        <c:forEach var="emp" items="${requestScope.allEmps}">
	                                        	<option value="${emp.empId }" class="notJoinIdList" >${emp.empName }</option>
	                                        </c:forEach>
                                       </select>
                                       
                                        <c:forEach var="emp" items="${requestScope.allEmps}">
                                        	<input type="hidden" value="${emp.empId }" class="allEmpId" />
                                        </c:forEach>
                                        
                                        <input type="hidden" id="empIdList" name="empIdList"/>
                                        
                                    </div>
                                    
                                    <div id="divoperator">
                                    	<input type="button" class="clickbutton" value="添加" 
                                    	onclick="selectEmployees(${requestScope.meeting.mId})"/>
                               	</div>
                                </td>
                            </tr>
                            
                            
                            <tr>
                            	<td></td>
                                <td class="command" colspan="2">
                                	<input type="hidden" name="mId" value="${requestScope.meeting.mId }" id="mId">
                                	<input type="button" class="clickbutton" value="修改" onclick="confirmUpdate('${theMrId }')"/>
                                    <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                                </td>
                            </tr>
                        </table>
                        
                        
                        
                    </fieldset>
                </form>
            </div>
    
        <%@include file="include/page-footer.jsp" %>
        
        <script src="js/updateMeeting.js"></script>
    </body>
</html>