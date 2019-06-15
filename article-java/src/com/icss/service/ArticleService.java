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
	//封装DAO
	@Resource(name="articleMapper")
	private ArticleMapper am;
	public ArticleMapper getAm() {
		return am;
	}
	public void setAm(ArticleMapper am) {
		this.am = am;
	}

	//增加文章
	public void insertArticleService(Article art) throws IOException {
		//增加文章的时候在articles文件夹下创建一个以文章名称命名的.txt文件
		String title = art.getTitle();
		String path = "E://Program Files/eclipse/workspace/article-java/WebContent/articles/" + title + ".txt";
//		System.out.println("========path=======" + path);
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		//把content中的内容写进这个文件
		String content = art.getContent();
//		System.out.println("content===========" + content);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < content.length(); i ++) {
			bw.write(content.charAt(i));
		}
		bw.close();
		
		//插入的时候把数据库中的content作为一个路径
		art.setContent(path);
		am.insertArticle(art);
	}

	//查看某个文章信息
	public Article selectArticleByIdService(int id) {
		return am.selectArticleById(id);
	}

	//修改文章
	public void updateArticleService(Article art) throws IOException {
		//修改文章的时候先获取到存放这个文章的文本路径
		String title = art.getTitle();
		String path = "E://Program Files/eclipse/workspace/article-java/WebContent/articles/" + title + ".txt";
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		//把content中的内容写进这个文件
		String content = art.getContent();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < content.length(); i ++) {
			bw.write(content.charAt(i));
		}
		bw.close();
		//插入的时候把数据库中的content作为一个路径
		art.setContent(path);
		am.updateArticleById(art);
	}

	//删除文章
	public void deleteArticleService(int id, String content) {
		//删除文章的时候把文章的路径也删除掉
		//如果文件存在的话删除
		File file = new File(content);
		if (file.exists()) {
			System.out.println(file.delete());;
		}
		am.deleteArticleById(id);
	}

	//通过显示条数和符合的文章标题查询文章
	public List<Article> selectArticlesService(String title, int pageNum, int showNum) {
		return am.selectArticles(title, pageNum, showNum);
	}

	//通过文章名称和显示条数和符合的文章标题查询一共有多少页
	public int selectAllPageNumService(String title, int showNum) {
		int length = am.selectAllNums(title);
		int allPageNum = length % showNum == 0 ? length / showNum : length / showNum + 1;
		return allPageNum;
	}
	
	//通过文章名称模糊查询一共有多少条数据
	public int selectAllNumService(String title) {
		int allNum = am.selectAllNums(title);
		return allNum;
	}
	
	//查询所有
	public List<Article> selectAllArticlesService() {
		return am.selectAllArticles();
	}
	
	//通过文章名称查询是否已经存在该文章
	public Boolean selectArticleByNameService(String title) {
		Article article = am.selectArticleByName(title);
		boolean flag = true;
		if ("null".equals(article) || null == article) {
			flag = false;
		}
		return flag;
	}
}
