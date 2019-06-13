package com.icss.dao;

import java.util.List;

import com.icss.vo.Article;

public interface IArticleDAO {
	//��������
	public void insertArticleDAO(Article art);
	//�鿴ĳ��������Ϣ
	public Article selectOneArticleDAO(int id);
	//�޸�����
	public void updateArticleDAO(Article art);
	//ɾ������
	public void deleteArticleDAO(int id);
	//ģ����ѯ��������
	public List<Article> selectArticlesByTitleDAO(String title);
	//��ѯ��������
	public List<Article> selectAllArticlesDAO();
}
