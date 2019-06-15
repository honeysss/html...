<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, com.icss.vo.*, java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	 <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>文章修改</title>
</head>
<body>
	<!--    顶部内容-->
	<div class="top-content">
	    <h1 class="top-h1">文章修改</h1>
	</div>

	<div class="modify-article-content">
	    <form method="post" action="confirmUpdate.action" class="form-horizontal" role="form" id="form">
	        <input type="hidden" name="id" value="${article.id }">
	        <input type="hidden" name="pubDate" value="${article.pubDate }">
	        <input type="hidden" name="pageNum" value="${pageNum }">
	        <input type="hidden" name="title2" value="${title2 }">
	        <div class="modify1 form-group">
	            <label class="modify-h2 col-sm-2 control-label">文章名称：</label>
	            <div class="col-sm-5">
	                <input type="text" value="${article.title }" class="modify-title form-control" name="title" id="title">
	            </div>
	        </div>
	        <div class="modify2 form-group">
	            <label class="modify-h2 col-sm-2 control-label">文章作者：</label><div class="col-sm-5">
	                <input type="text" value="${article.author }" class="modify-title form-control" name="author" id="author">
	            </div>
	        </div>
	        <div class="modify3 form-group">
	            <label class="modify-h2 col-sm-2 control-label">文章内容：</label>
	            <div class="col-sm-7">
	            <%
		        	Article article = (Article)request.getAttribute("article");
		       		String path = article.getContent();
		       		File file = new File(path);
		       		BufferedReader br = new BufferedReader(new FileReader(file));
		       		String str = null;
		       		String content = "";
		       		while((str = br.readLine()) != null) {
		       			content += str;
		       			content += "\n";
		       		}
		       		br.close();
		       		%>
	                <textarea class="form-control" name="content" rows="15" id="content"><%=content %></textarea>
	            </div>
	        </div>
	        <button type="button" id="modifyBtn" class="btn btn-default change-over1">提交</button>
	        <button type="button" id="modifyBtn" class="btn btn-default change-over2">
	        	<a href="search.action?pageNum=1&title=" style="color: black; text-decoration: none;">返回</a>
	        </button>
	    </form>
	    <div class="foot"></div>
	    
	    <script>
		$(document).ready(function() {
			$('#modifyBtn').click(function() {
				var title = $('#title').val().trim();
				var author = $('#author').val().trim();
				var content = $('#content').val().trim();
				if (title != null && title != '' && author != null && author != '' && content != null && content != '') {
					$('#form').submit();
				} else {
					alert('不能为空');
				}
			})
		})
	</script>
	    
	</div>
</body>
</html>