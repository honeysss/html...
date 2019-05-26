<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
    
    <%@include file="include/head.jsp" %>
    <body>
         <%@include file="include/page-header.jsp" %>
        <div class="page-body">
            <%@include file="include/adminPageBar.jsp" %>
         </div>
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 添加会议室
                </div>
                <form method="post" action="AddMeetingRoomServlet" id="form1">
                    <fieldset>
                        <legend>会议室信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>门牌号:</td>
                                <td>
                                    <input id="mrNum" name="mrNum" type="number" maxlength="5" placeholder="例如：201" maxlength="10"/>
                                </td>
                            </tr>
                            <tr>
                                <td>会议室名称:</td>
                                <td>
                                    <input id="mrName"  name="mrName" type="text" min="2" max="20" placeholder="例如：第一会议室" maxlength="20"
                                    onblur="checkName()"/>
                                    <span id='mrNameMsg'></span>
                                </td>
                            </tr>
                            <tr>
                                <td>最多容纳人数：</td>
                                <td>
                                    <input id="mrCapacity" name="mrCapacity" type="text" type="number" min="1" max="5" placeholder="填写一个正整数"/>
                                </td>
                            </tr>
                            <tr>
                                <td>当前状态：</td>
                                <td>
                                    <input type="radio" id="mrStatus" name="mrStatus" checked="checked" value="0"/><label for="status">可用</label>
                                    <input type="radio" id="mrStatus" name="mrStatus" value="1"/><label for="status" >不可用</label>
                                   
                                </td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td>
                                    <textarea id="mrRemark" name="mrRemark" maxlength="200" rows="5" cols="60" placeholder="200字以内的文字描述"></textarea>
                                </td>
                            </tr>
                            <tr>
                            <td></td>
                                <td colspan="2" class="command">
                                    <input type="button" value="添加" class="clickbutton" onclick="check2()"/>
                                    <input type="reset" value="重置" class="clickbutton"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
    
         <%@include file="include/page-footer.jsp" %>
         <script src="js/addMR.js"></script>
    </body>
</html>