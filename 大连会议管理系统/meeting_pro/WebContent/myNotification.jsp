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
                    个人中心 > 最新通知
                </div>
                <table class="listtable">
                    <caption>
                        未来7天我要参加的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <c:forEach var="item" items="${requestScope.futureMeetings}">
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
                        <td>
                            <button>
                            <a class="clickbutton" href="ViewMyMeetingDetailServlet?mId=${item.mId}">
                            	查看详情
                            </a>
                            </button>
                        </td>
                    </tr>
                    </c:forEach>
                    
                </table>
                <table class="listtable">
                    <caption>
                        已取消的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                   <c:forEach var="item" items="${requestScope.cancleMeetings}">
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
                        <td>
                            <button>
                            <a class="clickbutton" href="ViewMyMeetingDetailServlet?mId=${item.mId}">
                            	查看详情
                            </a>
                            </button>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
      
      <%@include file="include/page-footer.jsp" %>
    </body>
</html>