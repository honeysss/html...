<%@ page language="java"
	import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <%@include file="include/head.jsp" %>
    <body>
        <%@include file="include/page-header.jsp" %>
         <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
         </div>
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > 我的会议
                </div>
                
                <table class="listtable">
                    <caption>我将参加的会议：</caption>
                    <tr class="listheader">
                       	<th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>预定者</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="item" items="${requestScope.meetings}">
                    <tr>
                        <td>${item.mName}</td>
                        <td>
                        	<c:forEach var="mr" items="${requestScope.allMrs }">
                        		<c:if test="${mr.mrId == item.mrId }">
                        			${mr.mrName }
                        		</c:if>
                        	</c:forEach>
                        </td>
                        <td>${item.startTime }</td>
                        <td>${item.endTime }</td>
                        <td>${item.reTime }</td>
                        <td>
							<c:forEach var="emp" items="${requestScope.emps }">
                        		<c:if test="${item.reEmpId == emp.empId }">
                        			${emp.empName }
                        		</c:if>
                        	</c:forEach>
						</td>
                        <td>
                            <button>
                            <a class="clickbutton" href="ViewMyMeetingDetailServlet?mId=${item.mId}&oper=myFutureMeetings">查看</a>
                            </button>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                
            </div>
        <%@include file="include/page-footer.jsp" %>
    </body>
</html>