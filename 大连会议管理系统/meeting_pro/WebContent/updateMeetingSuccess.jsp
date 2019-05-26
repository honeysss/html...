
<!DOCTYPE html>
<html>
   <%@include file="include/head.jsp" %>
    <body>
       <%@include file="include/page-header.jsp" %>
        
        
        <div class="page-body">
           
            <%@include file="include/adminPageBar.jsp" %>
           
            
            <div class="page-content">
                <div class="content-nav">
                    个人中心  > 会议预定 > 修改会议预定  > 会议修改成功
                </div>
                <form action="ViewMyBookingServlet">
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>原会议名称：</td>
                                <td>${sessionScope.oldMeeting.mName }</td>   
                                <td>修改后会议名称：</td>
                                <td><span style="color:red">${requestScope.meeting.mName }</span></td>
                            
                            </tr>
                            <tr>
                                <td>原会议人数：</td>
                                <td>${sessionScope.oldMeeting.mNum }</td>   
                                <td>修改后会议人数：</td>
                                <td><span style="color:red">${requestScope.meeting.mNum }</span></td>
                            
                            </tr>
                            <tr>
                                <td>原会议开始时间：</td>
                                <td>${sessionScope.oldMeeting.startTime }</td>
                                <td>变更后会议开始时间：</td>
                                <td><span style="color:red">${requestScope.meeting.startTime }</span></td>
                            </tr>
                            <tr>
                                <td>原会议结束时间：</td>
                                <td>${sessionScope.oldMeeting.endTime }</td>
                                <td>变更后会议结束时间：</td>
                                <td><span style="color:red">${requestScope.meeting.endTime }</span></td>
                            </tr>
                            <tr>
                                <td>原定会议室:</td>
                                <td>
                                	<c:forEach var="mr" items="${requestScope.mrs }">
                                		<c:if test="${mr.mrId == sessionScope.oldMeeting.mrId }">
                                			${mr.mrName }
                                		</c:if>
                                	</c:forEach>
                                </td>
                                <td>变更后会议室:</td>
                                <td>
                                <span style="color:red">
                                	<c:forEach var="mr" items="${requestScope.mrs }">
                                		<c:if test="${mr.mrId == requestScope.meeting.mrId }">
                                			${mr.mrName }
                                		</c:if>
                                	</c:forEach>
                                </span>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>原会议描述：</td>
                                <td>${sessionScope.oldMeeting.mDescribe }</td>
                                <td>变更后会议描述：</td>
                                <td><span style="color:red">${requestScope.meeting.mDescribe }</span></td>
                            </tr>
                            
                      



    						 <tr>
                                <td style="line-height:170px;">原参与人员：</td>
                                
                                <td style="padding-right:100px;">
                                     
                                    <table class="listtable" id="before">
                                        <c:forEach var="emp" items="${sessionScope.oldEmpList }">
	                                		<tr>
	                                        	<td>${emp.empName }</td>
	                                        </tr>	
	                                	</c:forEach>
                                    </table>
                                </td>
                                
                                
                                
                                <td>变更后参与人员：</td>
                                <td>

                                    <table class="listtable" style="width:29%;" id="after">
	                                      	 <c:forEach var="emp" items="${requestScope.empList }">
		                                		<tr>
		                                        	<td>${emp.empName }</td>
		                                        </tr>	
		                                	</c:forEach>
                                    </table>
									
								</td>
                            </tr>
                              <tr>
                              <td></td>
                                <td colspan="4" class="command">
                                    <input type="submit" class="clickbutton" value="确认"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
         <%@include file="include/page-footer.jsp" %>
         
         <script>
         	$(document).ready(function() {
         		if ($('#after').find('tr').length === 0) {
         			$('#after').css('border', 'none');
         			$('#after').append('<span>无</span>');
         		}
				if ($('#before').find('tr').length === 0) {
         			$('#before').css('border', 'none');
         			$('#before').append('<span>无</span>');
         		}
         	})
         </script>
    </body>
</html>