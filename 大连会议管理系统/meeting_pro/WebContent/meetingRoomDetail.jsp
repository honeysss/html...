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
                    会议预定 > 修改会议室信息
                </div>
                <form action="UpdateMeetingRoomServlet" method="post" id="form1">
                    <fieldset>
                        <legend>会议室信息</legend>
                        <table class="formtable">
                        
                            <tr>
                                <td>门牌号:</td>
                                <td>
                                    <input type="hidden" name="mrId" value='${requestScope.mr.mrId}'>
                                    <input id="roomnumber" name="mrNum" type="text"  maxlength="10" value='${requestScope.mr.mrNum}'/>
                                </td>
                            </tr>
                            <tr>
                                <td>会议室名称:</td>
                                <td>
                                    <input id="mrName" name="mrName" type="text" maxlength="20" value="${requestScope.mr.mrName}"
                                    onblur="checkName('${requestScope.mr.mrName}')"/>
                                    <span id='mrNameMsg'></span>
                                </td>
                            </tr>
                            <tr>
                                <td>最多容纳人数：</td>
                                <td>
                                    <input id="roomcapacity" name="mrCapacity" type="text" value='${requestScope.mr.mrCapacity}'/>
                                </td>
                            </tr>
                            <tr>
                                <td>当前状态：</td>
                                <td>
                                	<input type="hidden" name="mrStatus" value="${requestScope.mr.mrStatus }" />
                                <c:if test="${requestScope.mr.mrStatus eq 0 }">
                               		     可用
                     			</c:if>
                     			
                     			  <c:if test="${requestScope.mr.mrStatus eq 1 }">
                                    	不可用
                     			</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td>
                                    <textarea id="description"  name="mrRemark" maxlength="200" rows="5" cols="60" >${requestScope.mr.mrRemark}</textarea>
                                </td>
                            </tr>
                            <tr>
                            	<td></td>
                                <td colspan="2" class="command">
                                    <input type="button" value="确认修改" class="clickbutton" onclick="check('${requestScope.mr.mrName}')"/>
                                    <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
       <%@include file="include/page-footer.jsp" %>
       
       <script>
	       function checkName(oldMrName) {
	    		var mrName = $('#mrName').val();
	    		if (mrName === null || mrName === '') {
	    			$('#mrNameMsg').html('不能为空');
	    		} else if (mrName === oldMrName) {
	    			$('#mrNameMsg').html('');
	    		} else {
	    			$.post('IfExistMrNameServlet', {
	    				'mrName': mrName
	    			}, function (data) {
	    				if (data.trim() === 'true') {
	    					$('#mrNameMsg').html('该会议室名称已存在');
	    				} else {
	    					$('#mrNameMsg').html('');
	    				}
	    			})
	    		}
	    	}
	
	    	function check(oldMrName) {
	    		var mrName = $('#mrName').val();
	    		var flag = true;
	    		if (mrName === null || mrName === '' ) {
	    			$('#mrNameMsg').html('不能为空');
	    			flag = false;
	    		} else if (mrName === oldMrName) {
	    			$('#mrNameMsg').html('');
	    			$('#form1').submit();
	    			flag = false;
	    		}
	    		if (flag) {
	    			$.post('IfExistMrNameServlet', {
	    				'mrName': mrName
	    			}, function (data) {
	    				if (data.trim() === 'true') {
	    					$('#mrNameMsg').html('该会议室名称已存在');
	    				} else {
	    					$('#form1').submit();
	    				}
	    			})
	    		}
	    		
	    	}
       	
       </script>
    </body>
</html>