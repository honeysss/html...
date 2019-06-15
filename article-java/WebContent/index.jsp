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
    <script src="js/common.js"></script>
    <title>文章管理系统</title>
    
    <script>
 
    </script>
    
</head>
<body>
<!-- new -->
	<c:if test="${type == 'delete' }">
		<div class="alert alert-success alert-dismissable">
		    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
		        &times;
			</button>
			文章删除成功！
		</div>
	</c:if>
   
	<c:if test="${type == 'publish' }">
		<div class="alert alert-success alert-dismissable">
		    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
		        &times;
			</button>
			文章发布成功！
		</div>
	</c:if>
	
	
	<c:if test="${type == 'update'}">
		<div class="alert alert-success alert-dismissable">
		    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
		        &times;
			</button>
			文章修改成功！
		</div>
	</c:if>
	
	
	
<!--    顶部内容-->
    <div class="top-content">
        <h1 class="top-h1">文章管理系统</h1>
        <div class="public-article">
            <a href="article-publish.jsp" title="发布文章"  class="public">发布文章</a>
        </div>
    </div>

<!--    中间内容-->
    <div class="middle-content">
    
    
      <div id="changePage">
      <a href="javascript:;"
		 onclick="changeToPostMethod('search.action', {
			 pageNum: 1,
			 title: '${title }'
		 })">首页</a>
		<a href="javascript:;" id="backPage" >上一页</a>
		<a href="javascript:;" id="forwardPage">下一页</a>
		<a href="javascript:;"
		onclick="changeToPostMethod('search.action', {
			 pageNum: ${allPageNum },
			 title: '${title }'
		 })"
		>尾页</a><br/>
		<span>跳到第</span>
			<input type="text" id="toPage"/>
		<span>页</span>
		<a href="javascript:;" id="toThePage">确认跳转</a>
		<br/><span>共<b>${allNum }</b>条数据</span>
		<span>分<b>${allPageNum }</b>页显示</span>
		<span>当前是第<b>${pageNum }</b>页</span>
	</div>
	<input type="hidden" value="${pageNum}" id="pageNum" />
	<input type="hidden" value="${allPageNum}" id="allPageNum" />
	<input type="hidden" value="${title}" id="oldTitle" />
    
    
<!--        文章信息-->
        <div class="middle-left-content">
            <!--        搜索框-->
            <div class="middle-right-content">
                <div class="search-content">
                    <form method="post" action="search.action" id="form">
                            <div class="input-group">
								<input type="hidden" value="1" name="pageNum" />
                                <input type="text" placeholder="请在这里输入文章标题..." class="search form-control" name="title" id="title">
                                <span class="input-group-btn">
	                                <button type="submit" class="btn btn-default" id="publishBtn">
	                                    <span class="glyphicon glyphicon-search"></span> Search
	                                </button>
	                       		 </span>
                           </div>
                    </form>
                </div>
                <hr class="cut0">
            </div>
             <div class="article-list">
             
             	<%
             		if (request.getAttribute("articles") != null) {
             			List<Article> articles = ((List<Article>)request.getAttribute("articles"));
             			for (Article article: articles) {
             				%>
             				<h1 class="article-title"><%=article.getTitle() %></h1>
                            <!-- <hr class="cut1"> -->
                            <p class="article-author">&nbsp;&nbsp;&nbsp;&nbsp;作者：<%=article.getAuthor() %></p>
                            <p class="article-content">
                            	<%
                            		String path = article.getContent();
                            		File file = new File(path);
                            		BufferedReader br = new BufferedReader(new FileReader(file));
                            		String str = null;
                            		while((str = br.readLine()) != null) {
                            			%><%=str%><br/><%
                            		}
                		       		br.close();
                            	%>
                            </p>
                            <div class="article-foot">
                                <a href="javascript:;" onclick="changeToPostMethod('detail.action', {
                                	id: <%=article.getId() %>
                                })" title="详情" class="detail"><h4>详情=>></h4></a>
                                <a  href="javascript:;" onclick="changeToPostMethod('update-show.action', {
                                	id: <%=article.getId() %>,
                                	pageNum: <%=request.getAttribute("pageNum") %>,
        			 				title: '<%=request.getAttribute("title") %>'
                                })" title="修改" class="modify"><h4>修改</h4></a>
                                <a  href="javascript:;" onclick="changeToPostMethod('delete.action', {
                                	id: <%=article.getId() %>,
                                	pageNum: <%=request.getAttribute("pageNum") %>,
        							 title: '<%=request.getAttribute("title") %>',
        							 content: '<%=path %>',
                                })" title="删除" class="delete" id="delete"><h4>删除</h4></a>
                                <!-- <img src="img/下载.png"/> -->
                                <a href="javascript:;" class="download"
                                onclick="changeToPostMethod('download.action', {
        							 content: '<%=path %>',
        							 title: '<%=article.getTitle() %>',
                                })" title="下载"
                                ><h4>下载</h4></a>
                            </div>
                            <hr class="cut2">
             			<%
             			}
             		}
             	%>
             
             
             
            	<%-- <c:forEach var="article" items="${articles }">
                    <h1 class="article-title">${article.title }</h1>
                    <hr class="cut1">
                    <p class="article-author">&nbsp;&nbsp;&nbsp;&nbsp;作者：${article.author }</p>
                    <p class="article-content">${article.content }</p>
                    <div class="article-foot">
                        <a href="javascript:;" onclick="changeToPostMethod('detail.action', {
                        	id: ${article.id }
                        })" title="详情" class="detail"><h4>详情=>></h4></a>
                        <a  href="javascript:;" onclick="changeToPostMethod('update-show.action', {
                        	id: ${article.id },
                        	pageNum: ${pageNum },
			 				title: '${title }'
                        })" title="修改" class="modify"><h4>修改</h4></a>
                        <a  href="javascript:;" onclick="changeToPostMethod('delete.action', {
                        	id: ${article.id },
                        	pageNum: ${pageNum },
							 title: '${title }'
                        })" title="删除" class="delete" id="delete"><h4>删除</h4></a>
                    </div>
                    <hr class="cut2">
           		 </c:forEach> --%>
                </div>
        </div>
    </div>


	<script>
		$(document).ready(function() {
			$('#publishBtn').click(function() {
				var title = $('#title').trim();
				if (title != null && title != '') {
					$('#form').submit();
				} else {
					alert('不能为空');
				}
			})
			
			$('#backPage').click(function() {
				var pageNum = $('#pageNum').val();
				var oldTitle = $('#oldTitle').val();
				if (pageNum > 1) {
					pageNum--;
					changeToPostMethod('search.action', {
						 pageNum: pageNum,
						 title: oldTitle
					});
				}
			})
			
			$('#forwardPage').click(function() {
				var pageNum = $('#pageNum').val();
				var allPageNum = $('#allPageNum').val();
				var oldTitle = $('#oldTitle').val();
				if (pageNum < allPageNum) {
					pageNum++;
					changeToPostMethod('search.action', {
						 pageNum: pageNum,
						 title: oldTitle
					});
				}
			})
			
			$('#toThePage').click(function() {
				var pageNum = $('#toPage').val();
				var allPageNum = $('#allPageNum').val();
				var oldTitle = $('#oldTitle').val();
				if (!(pageNum >=1 && pageNum <= allPageNum)) {
					alert('页数有误');
					document.getElementById('toPage').value = '';
				} else {
					changeToPostMethod('search.action', {
						 pageNum: pageNum,
						 title: oldTitle
					});
				}
			})
		})
	</script>
</body>
</html>