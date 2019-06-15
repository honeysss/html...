package com.icss.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icss.dao.ArticleMapper;
import com.icss.vo.Article;

@Service("articleService")
public class ArticleService {
	//��װDAO
	@Resource(name="articleMapper")
	private ArticleMapper am;
	public ArticleMapper getAm() {
		return am;
	}
	public void setAm(ArticleMapper am) {
		this.am = am;
	}

	//��������
	public void insertArticleService(Article art) throws IOException {
		//�������µ�ʱ����articles�ļ����´���һ������������������.txt�ļ�
		String title = art.getTitle();
		String path = "E://Program Files/eclipse/workspace/article-java/WebContent/articles/" + title + ".txt";
//		System.out.println("========path=======" + path);
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		//��content�е�����д������ļ�
		String content = art.getContent();
//		System.out.println("content===========" + content);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < content.length(); i ++) {
			bw.write(content.charAt(i));
		}
		bw.close();
		
		//�����ʱ������ݿ��е�content��Ϊһ��·��
		art.setContent(path);
		am.insertArticle(art);
	}

	//�鿴ĳ��������Ϣ
	public Article selectArticleByIdService(int id) {
		return am.selectArticleById(id);
	}

	//�޸�����
	public void updateArticleService(Article art) throws IOException {
		//�޸����µ�ʱ���Ȼ�ȡ�����������µ��ı�·��
		String title = art.getTitle();
		String path = "E://Program Files/eclipse/workspace/article-java/WebContent/articles/" + title + ".txt";
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		//��content�е�����д������ļ�
		String content = art.getContent();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < content.length(); i ++) {
			bw.write(content.charAt(i));
		}
		bw.close();
		//�����ʱ������ݿ��е�content��Ϊһ��·��
		art.setContent(path);
		am.updateArticleById(art);
	}

	//ɾ������
	public void deleteArticleService(int id, String content) {
		//ɾ�����µ�ʱ������µ�·��Ҳɾ����
		//����ļ����ڵĻ�ɾ��
		File file = new File(content);
		if (file.exists()) {
			System.out.println(file.delete());;
		}
		am.deleteArticleById(id);
	}

	//ͨ����ʾ�����ͷ��ϵ����±����ѯ����
	public List<Article> selectArticlesService(String title, int pageNum, int showNum) {
		return am.selectArticles(title, pageNum, showNum);
	}

	//ͨ���������ƺ���ʾ�����ͷ��ϵ����±����ѯһ���ж���ҳ
	public int selectAllPageNumService(String title, int showNum) {
		int length = am.selectAllNums(title);
		int allPageNum = length % showNum == 0 ? length / showNum : length / showNum + 1;
		return allPageNum;
	}
	
	//ͨ����������ģ����ѯһ���ж���������
	public int selectAllNumService(String title) {
		int allNum = am.selectAllNums(title);
		return allNum;
	}
	
	//��ѯ����
	public List<Article> selectAllArticlesService() {
		return am.selectAllArticles();
	}
	
	//ͨ���������Ʋ�ѯ�Ƿ��Ѿ����ڸ�����
	public Boolean selectArticleByNameService(String title) {
		Article article = am.selectArticleByName(title);
		boolean flag = true;
		if ("null".equals(article) || null == article) {
			flag = false;
		}
		return flag;
	}
}
