package com.icss.dao;

import java.util.List;

import com.icss.vo.Article;

public interface IArticleDAO {
	//增加文章
	public void insertArticleDAO(Article art);
	//查看某个文章信息
	public Article selectOneArticleDAO(int id);
	//修改文章
	public void updateArticleDAO(Article art);
	//删除文章
	public void deleteArticleDAO(int id);
	//模糊查询查找文章
	public List<Article> selectArticlesByTitleDAO(String title);
	//查询所有文章
	public List<Article> selectAllArticlesDAO();
}
