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
                    会议预定 > 修改会议预定 > 取消会议
                </div>
                <form method="post" action="CancleMeetingServlet">
                    <fieldset>
                   	 <table class="formtable">
                   	 		<tr>	
                   	 			<td>取消原因:</td>
                   	 			<td>
                   	 				<textarea rows="10" cols="40" name="cancleReason"
                   	 				placeholder="请输入取消会议的原因.."></textarea>
                   	 			</td>
                   	 		</tr>
                       		<tr>
                            	<td></td>
                                <td class="command" colspan="2">
                                	<input type="hidden" name="mId" value="${param.mId }">
                                	<!-- 如果会议的状态是0显示取消会议 -->
                                    <input type="submit" class="clickbutton" value="确定" />
                                </td>
                            </tr>
                        </table>
                        
                        
                        
                    </fieldset>
                </form>
            </div>
    
        <%@include file="include/page-footer.jsp" %>
    </body>
</html>