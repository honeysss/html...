<%@ page language="java"
	import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
     <%@include file="include/head.jsp" %>
    <body>
         <%@include file="include/page-header.jsp" %>
        <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
         </div>
            <div class="page-content">
                <div class="content-nav">
                    	会议预定 > 查看会议室
                </div>
                <table class="listtable">
                    <caption>所有会议室:</caption>
                    <tr class="listheader">
                        <th>门牌编号</th>
                        <th>会议室名称</th>
                        <th>容纳人数</th>
                        <th>当前状态</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="room" items='${requestScope.mrs}'>
                    <tr>
                        <td>${room.mrNum}</td>
                        <td>${room.mrName}</td>
                        <td>${room.mrCapacity }</td>
                        <c:if test='${room.mrStatus == 0}'>
                        <td>可用</td>
                        </c:if>
                        <c:if test='${room.mrStatus == 1}'>
                        <td>不可用</td>
                        </c:if>
                        <td>
                        	<!-- 如果是可用状态 添加一个停用按钮 -->
                        	<c:if test='${room.mrStatus == 0}'>
                        	<button>
                            <a class="clickbutton" href="CloseMeetingRoomServlet?mrId=${room.mrId}" style="color: red;">停用</a>
                            </button>
	                        </c:if>
	                        
                        	<!-- 如果是不可用状态 添加一个开启按钮 -->
                        	<c:if test='${room.mrStatus == 1}'>
                        	<button>
                            <a class="clickbutton" href="OpenMeetingRoomServlet?mrId=${room.mrId}">开启</a>
                            </button>
	                        </c:if>
                            <button>
                            <a class="clickbutton" href="ViewOneMeetingRoomServlet?mrId=${room.mrId}">修改</a>
                            </button>
                        </td>
                    </tr>
                    </c:forEach>
                   
                </table>
            </div>
 		<%@include file="include/page-footer.jsp" %>
 		<c:if test="${requestScope.mrMsg != null }">
 			<script>
 				alert('${requestScope.mrMsg}');
 			</script>
 		</c:if>
 		
 		
    </body>
</html>