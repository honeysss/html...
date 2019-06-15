package com.icss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icss.vo.Article;


public interface ArticleMapper {
	//��������
	public void insertArticle(Article art);
	//�鿴ĳ��������Ϣ
	public Article selectArticleById(int id);
	//�޸�����
	public void updateArticleById(Article art);
	//ɾ������
	public void deleteArticleById(int id);
	//ͨ����ʾ�����ͷ��ϵ����±����ѯ����
	public List<Article> selectArticles(String title, int pageNum, int showNum);
	//ͨ����������ģ����ѯһ���ж���������
	public int selectAllNums(String title);
	//��ѯ��������
	public List<Article> selectAllArticles();
	//ͨ���������Ʋ�ѯ�Ƿ��Ѿ����ڸ�����
	public Article selectArticleByName(String title);
}
