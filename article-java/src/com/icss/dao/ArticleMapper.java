package com.icss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icss.vo.Article;


public interface ArticleMapper {
	//增加文章
	public void insertArticle(Article art);
	//查看某个文章信息
	public Article selectArticleById(int id);
	//修改文章
	public void updateArticleById(Article art);
	//删除文章
	public void deleteArticleById(int id);
	//通过显示条数和符合的文章标题查询文章
	public List<Article> selectArticles(String title, int pageNum, int showNum);
	//通过文章名称模糊查询一共有多少条数据
	public int selectAllNums(String title);
	//查询所有文章
	public List<Article> selectAllArticles();
	//通过文章名称查询是否已经存在该文章
	public Article selectArticleByName(String title);
}
