<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>文章管理系统</title>
</head>
<body>
<!--    顶部内容-->
<div class="top-content">
    <h1 class="top-h1">文章管理系统</h1>
    <div class="public-article">
        <a href="article-public.php" title="发布文章"  class="public">发布文章</a>
    </div>
</div>

<!--    中间内容-->
<div class="middle-content">
    <!--        文章信息-->
    <div class="middle-left-content">
        <!--        搜索框-->
        <div class="middle-right-content">
            <div class="search-content">
                <form method="post" action="article-search.php">
                    <div class="input-group">
                        <input type="text" placeholder="请在这里输入..." class="search form-control" name="keywords">
                        <span class="input-group-btn">
                                <button type="submit button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search"></span> Search
                                </button>
                    </div>
                    </span>
                </form>
            </div>
            <hr class="cut0">
        </div>
        <?php
        if(empty($data)) {
            echo "<script>alert('当前没有文章，请您先发布文章！');
                    window.location.href='mainPage.php';</script>";
        }else{
            foreach ($data as $value) {
                ?>
                <div class="article-list">
                    <h1 class="article-title"><?php echo $value['title'];?></h1>
                    <hr class="cut1">
                    <p class="article-author">&nbsp;&nbsp;&nbsp;&nbsp;作者：<?php echo $value['author'];?></p>
                    <p class="article-content"><?php echo $value['content'];?></p>
                    <div class="article-foot">
                        <a href="article-show.php?id=<?php echo $value['id'];?>" title="详情" class="detail"><h4>详情=>></h4></a>
                        <a href="article-modify.php?id=<?php echo $value['id'];?>" title="修改" class="modify"><h4>修改</h4></a>
                        <a href="article-delete.php?id=<?php echo $value['id'];?>" title="删除" class="delete"><h4>删除</h4></a>
                    </div>
                    <hr class="cut2">
                </div>
                <?php
            }
        }
        ?>
    </div>
</div>
</body>
</html>