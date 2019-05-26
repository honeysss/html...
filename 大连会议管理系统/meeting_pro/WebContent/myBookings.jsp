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
                    个人中心 > 我的预定
                </div>
                <table class="listtable">
                    <caption>我预定的会议：</caption>
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="item" items="${requestScope.meetings}">
                    <tr class="trs">
                        <td>${item.mName}</td>
                        <td>
                        	<c:forEach var="mr" items="${requestScope.allMrs }">
                        		<c:if test="${mr.mrId == item.mrId }">
                        			${mr.mrName }
                        		</c:if>
                        	</c:forEach>
                        </td>
                        <td class="startTimeStamp">${item.startTime }</td>
                        <td class="endTimeStamp">${item.endTime }</td>
                        <td>${item.reTime }</td>
                        <td class="manager">
                            <input type="hidden" value="${item.mId}" class="usemId" />
                            <c:if test="${item.mStatus == 0}">
	                            <button><a class="clickbutton" href="cancleMeeting.jsp?mId=${item.mId}" style="color: red;">取消</a></button>
								<button><a class="clickbutton updateA" href="BeforeUpdateMyBookingServlet?mId=${item.mId}" >修改</a></button>
	                       </c:if>
	                      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
							
	                     
	                       
	                       <c:if test="${item.mStatus == 1 }">
								<button><a class="clickbutton" href="ViewMyMeetingDetailServlet?mId=${item.mId}&oper=viewMyBookingMeeting">查看</a></button>
	                       </c:if>
                            
                        </td>
                    </tr>
                    </c:forEach>
                    
                    
					<!-- 当前时间的时间戳 -->
                      <% Date date = new Date(); request.setAttribute("date", date); %>
	                   	<span id="nowTime" style="display: none">
	                   		<fmt:formatDate value="${date}" pattern="yyyy-MM-dd hh:mm:ss"/>
	                   	</span>
                </table>
            </div>
     
       
         <%@include file="include/page-footer.jsp" %>
         
         <script>
	       //将字符串的事件转换为年月日时分类型
	         function convertDateFromString(dateString) { 
	         	if (dateString) { 
	         		var arr1 = dateString.split(" "); 
	         		var sdate = arr1[0].split('-'); 
	         		var sdate2 = arr1[1].split(':'); 
	         		var date = new Date(sdate[0], sdate[1]-1, sdate[2], sdate2[0], sdate2[1]); 
	         		return date;
	         	} 
	         }
	
	         //获取系统当前时间
	         var nowTime = $('#nowTime').html();
	         var nowTimeStamp = convertDateFromString(nowTime.trim()).getTime();
	         var i = 0;
	         $('.startTimeStamp').each(function() {
	        	 
	        	 var startTimeStamp = convertDateFromString($(this).html().trim()).getTime();
	        	 //我预定的会议显示的是所有会议
	        	 //如果会议未取消 显示取消 和 修改按钮
	        	 //如果会议已经取消 显示查看按钮
	        	 //如果会议在已经开始的情况下  只显示查看按钮
	        	 if (nowTimeStamp > startTimeStamp) {
	        		 var ele = $('.trs').eq(i).find('.manager');
	        		 var mId = ele.find('.usemId').val();
	        		ele.find('button').remove();
	        		ele.append('<button><a class="clickbutton" href="ViewMyMeetingDetailServlet?mId=' + mId +'&oper=viewMyBookingMeeting">查看</a></button>');
	        	 }
	        	 i++;
	         })
	         
	         
         </script>
    </body>
</html>