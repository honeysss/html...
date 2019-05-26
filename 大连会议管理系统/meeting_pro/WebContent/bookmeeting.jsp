<%@ page language="java"
	import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>会议管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
#divfrom {
	float: left;
	width: 150px;
}

#divto {
	float: left;
	width: 150px;
}

#divoperator {
	float: left;
	width: 50px;
	padding: 60px 5px;
}

#divoperator input[type="button"] {
	margin: 10px 0;
}

#selDepartments {
	display: block;
	width: 100%;
}

#selEmployees {
	display: block;
	width: 100%;
	height: 200px;
}

#selSelectedEmployees {
	display: block;
	width: 100%;
	height: 225px;
}
</style>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	    
	    var xmlHttp;

        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();                
            }
        }

        function showEmployees() {
            createXMLHttpRequest();
       		var deptid=document.getElementById("selDepartments").value;   	
       		var url = "SelectEmployeesOfDeptServlet?departmentid=" + escape(deptid);           
            xmlHttp.open("GET", url, true);     
            xmlHttp.onreadystatechange = callback;
            xmlHttp.send(null);
        }

        function callback() {
           clearEmployees();
           var selEmployees=document.getElementById("selEmployees");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
	                    selEmployees.options.add(new Option(text,value));
                    }       
                }
            }
              
        }
        
        function clearEmployees(){
         	document.getElementById("selEmployees").options.length=0;
        }
        
         function selectEmployees(){
         		var selEmployees=document.getElementById("selEmployees");
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selEmployees.options.length;i++){
                    if (selEmployees.options[i].selected){
                        var opt=new Option(selEmployees.options[i].text,selEmployees.options[i].value);
                        opt.selected=true;
                        selSelectedEmployees.options.add(opt);
                       
                    }
                }
                setTimeout(function(){
                	for(var i=0;i<selEmployees.options.length;i++){
                        if (selEmployees.options[i].selected){
                        	selEmployees.options.remove(i);
                            i--;
                        }
                    }
                },30)
            }
        
        function deSelectEmployees(){
        		var selEmployees=document.getElementById("selEmployees");
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selSelectedEmployees.options.length;i++){
                    if (selSelectedEmployees.options[i].selected){
                        selEmployees.options.add(new Option(selSelectedEmployees.options[i].text,selSelectedEmployees.options[i].value));
                    }
                }
                setTimeout(function(){
                	for(var i=0;i<selSelectedEmployees.options.length;i++){
                        if (selSelectedEmployees.options[i].selected){
                            selSelectedEmployees.options.remove(i);
                            i--;
                        }
                    }
                },30)
                
                setSelected();
            }     
             
        function setSelected(){
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selSelectedEmployees.options.length;i++){
                    selSelectedEmployees.options[i].selected=true;
                }
        }
        
        function refreshRooms(){
            createXMLHttpRequest();   
	        var starttime=document.getElementById("starttime").value;   	
	        var endtime=document.getElementById("endtime").value;  
	        
	       	var url = "RefreshRoomsServlet?starttime=" + escape(starttime)+"&endtime="+escape(endtime);           
	        xmlHttp.open("GET", url, true);     
	        xmlHttp.onreadystatechange = refresh;
	        xmlHttp.send(null);
        }
        
         function refresh() {
  		   clearMeetingRooms(); 
           var roomid=document.getElementById("roomid");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                	
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
	                    roomid.options.add(new Option(text,value),i+1);
                    }       
                }
            }
        
        }
        
        function clearMeetingRooms(){
         	document.getElementById("roomid").options.length=1;
        }
        
       
    	   
    	      	   

        
        
        
            
</script>
</head>
<body >
 <%@include file="include/page-header.jsp" %>
  <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
         </div>
	<div class="page-content">
		<div class="content-nav">会议预定 > 预定会议</div>
		<form method="post" action="BookingMeetingServlet" id="form1">
			<fieldset>
				<legend>会议信息</legend>
				<table class="formtable">
					<tr>
						<td>会议名称：</td>
						<td>
						<input type="text" id="meetingname"  name="meetingname" maxlength="20" onblur="check()" />
						<span id="meetingnamemsg"></span>
						</td>
					</tr>
					<tr>
						<td>预计参加人数：</td>
						<td><input type="number" id="numofattendents" name="numofparticipants" onblur="check()"/><span id="numofattendentsmsg"></span></td>
					</tr>
					<tr>
						<td>预计开始时间：</td>
						<td><input class="Wdate" type="text" id="starttime" name="starttime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onblur="check()"><span id="starttimemsg"></span></td>
					</tr>
					<tr>
						<td>预计结束时间：</td>
						<td><input class="Wdate" type="text" id="endtime" name="endtime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onblur="check()"><span id="endtimemsg"></span>
						</td>
					</tr>

					<tr>
						<td>选择会议室：</td>
						
						<td><select id="roomid" name="roomid" onfocus="refreshRooms()" onblur="check()">
						<option value="0" >请选择会议室</option>
								<c:forEach var="room" items="${requestScope.roomsList}">
									<c:if test="${room.mrStatus==0 }">
									<option value="${room.mrId}">${room.mrName}</option>
									</c:if>
								</c:forEach> 
						</select><span id="roomidmsg"></span></td>
					</tr>
					<tr>
						<td>会议说明：</td>
						<td><textarea id="description" name="description" rows="5"></textarea></td>
					</tr>
					<tr>
						<td>选择参会人员：<span id="selDepartmentsmsg"></span></td>
						<td>
							<div id="divfrom">
								<select id="selDepartments" onchange="showEmployees()">
								    <option value="0">请选择部门</option>
									<c:forEach var="dept" items="${requestScope.deptsList}">
										<option value="${dept.deptId}">${dept.deptName}</option>
									</c:forEach>
								</select> <select id="selEmployees"  multiple="multiple">

								</select>
							</div>
							<div id="divoperator">
								<input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()" /> 
								<input type="button"
									class="clickbutton" value="&lt;" onclick="deSelectEmployees()" />
							</div>
							<div id="divto">
								<select id="selSelectedEmployees"  name="selSelectedEmployees" multiple="multiple" >
								</select>
							</div></td>
					</tr>
					<tr>
						<td></td>
						<td class="command" colspan="2">
						<input type="hidden" name="code" value="book">
						<input type="button" class="clickbutton" value="预定会议" onclick="checkAll()" /> 
						<input type="reset"	class="clickbutton" value="重置" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>

	 <%@include file="include/page-footer.jsp" %>
	 <script type="text/javascript">
	 
	 function check(){
	   var flag=1;
  	   var form1=document.getElementById("form1");
  	   var meetingname=document.getElementById("meetingname").value;
  	   var numofparticipants=document.getElementById("numofattendents").value;
  	   var starttime=document.getElementById("starttime").value;
  	   var endtime=document.getElementById("endtime").value;
  	   var roomid=document.getElementById("roomid").value;
  	   var meetingnamemsg=document.getElementById("meetingnamemsg");
  	   var numofparticipantsmsg=document.getElementById("numofattendentsmsg");
  	   var starttimemsg=document.getElementById("starttimemsg");
  	   var endtimemsg=document.getElementById("endtimemsg");
  	   var roomidmsg=document.getElementById("roomidmsg");
  	   if(meetingname==null||meetingname==""){
  		   meetingnamemsg.innerHTML="会议名称不能为空";
  		   flag=0;   		      		   
  	   }else{
  		 meetingnamemsg.innerHTML="";
  		   
  	   }
  	   if(numofparticipants==null||numofparticipants==""){
  		 numofattendentsmsg.innerHTML="人数不能为空";
  		   flag=0;   		      		   
  	   }else{
  		 numofattendentsmsg.innerHTML="";
  	   }
  	   if(starttime==null||starttime==""){
  		   starttimemsg.innerHTML="开始时间不能为空";
  		   flag=0;   		      		   
  	   }else{
  		 starttimemsg.innerHTML="";
  	   }
  	   if(endtime==null||endtime==""){
  		   endtimemsg.innerHTML="结束时间不能为空";
  		   flag=0;   		      		   
  	   }else{
  		   
  		 endtimemsg.innerHTML="";
  	   }
  	   if(roomid=="0"){
  		 roomidmsg.innerHTML="请选择一个会议室";
  		 flag=0;
  		 
  	   }else{
  		 roomidmsg.innerHTML="";
  		   
  	   }
  	
	 }
	 
	 
	 
	 function checkAll(){
		   var flag=1;
	  	   var form1=document.getElementById("form1");
	  	   var meetingname=document.getElementById("meetingname").value;
	  	   var numofparticipants=document.getElementById("numofattendents").value;
	  	   var starttime=document.getElementById("starttime").value;
	  	   var endtime=document.getElementById("endtime").value;
	  	   var roomid=document.getElementById("roomid").value;
	  	   var meetingnamemsg=document.getElementById("meetingnamemsg");
	  	   var numofparticipantsmsg=document.getElementById("numofattendentsmsg");
	  	   var starttimemsg=document.getElementById("starttimemsg");
	  	   var endtimemsg=document.getElementById("endtimemsg");
	  	   var roomidmsg=document.getElementById("roomidmsg");
	  	   if(meetingname==null||meetingname==""){
	  		   meetingnamemsg.innerHTML="会议名称不能为空";
	  		   flag=0;   		      		   
	  	   }
	  	   if(numofparticipants==null||numofparticipants==""){
	  		 numofattendentsmsg.innerHTML="人数不能为空";
	  		   flag=0;   		      		   
	  	   }
	  	   if(starttime==null||starttime==""){
	  		   starttimemsg.innerHTML="开始时间不能为空";
	  		   flag=0;   		      		   
	  	   }
	  	   if(endtime==null||endtime==""){
	  		   endtimemsg.innerHTML="结束时间不能为空";
	  		   flag=0;   		      		   
	  	   }
	  	 if(roomid=="0"){
	  		 roomidmsg.innerHTML="请选择一个会议室";
	  		 flag=0;
	  	   }
	  	   
	  	   if(flag==1){
	  		   form1.action="BookingMeetingServlet";
	  		   form1.submit();
	  		   
	  	   }
		 }
	 
	 
	 </script>
</body>
</html>