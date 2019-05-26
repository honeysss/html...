<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-sidebar">
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">个人中心</div>
        <ul class="sidebar-menu">
            <li class="sidebar-menuitem"><a href="MyNotificationServlet">最新通知</a></li>
            <li class="sidebar-menuitem active"><a href="ViewMyBookingServlet">我的预定</a></li>
            <li class="sidebar-menuitem"><a href="ViewMyMeetingsServlet">我的会议</a></li>
        </ul>
    </div>
    
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">人员管理</div>
        <ul class="sidebar-menu">
            <li class="sidebar-menuitem"><a href="ViewAllDepartmentsServlet">部门管理</a></li>
            <li class="sidebar-menuitem"><a href="SelectAllEmployeesServlet">注册审批</a></li>
            <li class="sidebar-menuitem"><a href="ToAddEmployeeJspServlet">增加员工</a></li>
            <li class="sidebar-menuitem"><a href="SearchEmployeesServlet">搜索员工</a></li>
        </ul>
    </div>
      
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">会议预定</div>
        <ul class="sidebar-menu">
         	<li class="sidebar-menuitem"><a href="addMeetingRoom.jsp">添加会议室</a></li>
            <li class="sidebar-menuitem"><a href="ViewAllMeetingRoomsServlet">查看会议室</a></li>
            <li class="sidebar-menuitem"><a href="BookingMeetingServlet?code=prepare">预定会议</a></li>
            <li class="sidebar-menuitem"><a href="SelectAllMeetingsServlet">查看所有会议</a></li>
            
        </ul>
    </div>
</div>


