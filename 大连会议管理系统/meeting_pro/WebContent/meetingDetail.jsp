<%@ page language="java"
	import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    	<%@include file="include/head.jsp" %>
    <body>
    	<%@include file="include/page-header.jsp" %>
            <%@include file="include/adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                  ${requestScope.title }
                </div>
                <form >
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                           <tr>
                                <td>会议名称：</td>
                                <td>${requestScope.meeting.mName}</td>
                            </tr>
                            <tr>
                                <td>预计参加人数：</td>
                                <td>${requestScope.meeting.mNum}</td>
                            </tr>
                            <tr>
                                <td>会议室名称：</td>
                                <td>
									<c:forEach var="mr" items="${requestScope.allMrs }">
		                        		<c:if test="${mr.mrId == requestScope.meeting.mrId }">
		                        			${mr.mrName }
		                        		</c:if>
		                        	</c:forEach>
								</td>
                            </tr>
                            <tr>
                                <td>预定者：</td>
                                <td>
									<c:forEach var="emp" items="${requestScope.emps }">
		                        		<c:if test="${emp.empId == requestScope.meeting.reEmpId }">
		                        			${emp.empName }
		                        		</c:if>
		                        	</c:forEach>
								</td>
                            </tr>
                            <tr>
                                <td>预定时间：</td>
                                <td>${requestScope.meeting.reTime}</td>
                            </tr>
                            <tr>
                                <td>预计开始时间：</td>
                                <td>${requestScope.meeting.startTime}</td>
                            </tr>
                            <tr>
                                <td>预计结束时间：</td>
                                <td>${requestScope.meeting.endTime}
                                </td>
                            </tr>
                            <tr>
                                <td>会议描述：</td>
                                <td>${requestScope.meeting.mDescribe}
                                </td>
                            </tr>
                            <c:if test="${requestScope.meeting.mStatus == 1}">
                            <tr>
                                <td>取消时间：</td>
                                <td>
                                   ${requestScope.meeting.cancleTime}
                                </td>
                            </tr>
                            <tr>
                                <td>取消原因：</td>
                                <td>
                                   ${requestScope.meeting.cancleReason}
                                </td>
                            </tr>
                            </c:if>
                            <tr>
                                <td>参会人员：</td>
                                <td>
                                    <table class="listtable">
                                        <tr class="listheader">
                                            <th>姓名</th>
                                            <th>联系电话</th>
                                            <td>电子邮件</td>
                                        </tr>
                                        <c:forEach var="emp" items="${requestScope.empList}">
                                        <tr>
                                            <td>${emp.empName}</td>
                                            <td>${emp.empTel}</td>
                                            <td>${emp.empEmail}</td>
                                        </tr>
                                        </c:forEach>
                                        
                                      
                                    </table>
                                </td>
                            </tr>
                            <tr>
                            	<td></td>
                                <td class="command" colspan="2">
                                    <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
   	 <%@include file="include/page-footer.jsp" %>
    </body>
</html>