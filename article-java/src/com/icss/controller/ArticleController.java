package com.icss.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icss.service.ArticleService;
import com.icss.vo.Article;

@Controller("articleController")
public class ArticleController {
	//��װService
	@Resource(name="articleService")
	private ArticleService as;
	public ArticleService getAs() {
		return as;
	}
	public void setAs(ArticleService as) {
		this.as = as;
	}
	private int showNum = 5;
	
	//ģ����ѯ����
	@RequestMapping("search.action")
	public String selectArticles(Model model,
			int pageNum, 
//			@RequestParam(value="pageNum", required=true, defaultValue="1") int pageNum,
//			@RequestParam(value="title", required=false, defaultValue="") String title,
//			@RequestParam(value="showNum", required=false, defaultValue="5") int showNum,
			HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String title = "";
		if (request.getParameter("title") != null && !"".equals(request.getParameter("title"))) {
			title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "utf-8");
		}
		int allPageNum = as.selectAllPageNumService(title, showNum);
		int allNum = as.selectAllNumService(title);
		//�����ǰҳ������ҳ�� ��ǰҳ��һ
		if (pageNum > allPageNum) {
			pageNum--;
		}
		model.addAttribute("articles", as.selectArticlesService(title, pageNum, showNum));
		model.addAttribute("allPageNum", allPageNum);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("title", title);
		model.addAttribute("allNum", allNum);
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		return "index";
	}
	
	//�鿴��������
	@RequestMapping("detail.action")
	public String detail(Model model, int id) {
		model.addAttribute("article", as.selectArticleByIdService(id));
		return "article-detail";
	}
	
	//��ʾ�޸�����
	@RequestMapping("update-show.action")
	public String updateShow(Model model, int id, int pageNum, String title) throws UnsupportedEncodingException {
		model.addAttribute("article", as.selectArticleByIdService(id));
		model.addAttribute("pageNum", pageNum);
		String title2 = new String(title.getBytes("ISO-8859-1"), "utf-8");
		model.addAttribute("title2", title2);
		return "article-modify";
	}
	
	//ȷ���޸�����
	@RequestMapping("confirmUpdate.action")
	public String confirmUpdate(Integer id, Date pubDate, int pageNum, String title2, HttpServletRequest request) throws IOException {
		String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "utf-8");
		String author = new String(request.getParameter("author").getBytes("ISO-8859-1"), "utf-8");
		String content = new String(request.getParameter("content").getBytes("ISO-8859-1"), "utf-8");
		Article article = new Article(id, pubDate, title, author, content);
		as.updateArticleService(article);
		return "forward:search.action?pageNum=" + pageNum + "&title=" + title2 + "&type=update";
	}
	
	//ɾ������
	@RequestMapping("delete.action")
	public String delete(int id, int pageNum, String title, String content) throws UnsupportedEncodingException {
		as.deleteArticleService(id, content);
		return "forward:search.action?pageNum=" + pageNum + "&title=" + title + "&type=delete";
	}
	
	
	//��������
	@RequestMapping("publish.action")
	public String publash(HttpServletRequest request) throws IOException {
		String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "utf-8");
		String author = new String(request.getParameter("author").getBytes("ISO-8859-1"), "utf-8");
		String content = new String(request.getParameter("content").getBytes("ISO-8859-1"), "utf-8");
		Article article = new Article(0, null, title, author, content);
		as.insertArticleService(article);
		return "forward:search.action?pageNum=1&title=&type=publish";
	}
	
	//ͨ���������Ʋ�ѯ�Ƿ��Ѿ����ڸ�����
	@RequestMapping("ifExistTheArticle.action")
	public void ifExistTheArticle(String title, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		boolean flag = as.selectArticleByNameService(title);
		out.print(flag);
		out.close();
	}
	
	//��������
	@RequestMapping("download.action")
	public ResponseEntity<byte[]> download(String content, String title
			) throws IOException {
		String path = new String(content.getBytes("iso-8859-1"), "UTF-8");
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		// Ϊ�˽������������������
		String titleF = new String(title.getBytes("iso-8859-1"), "UTF-8");
		String path2 = "D:\\" + titleF + ".txt";
//		String fileName = new String(path2.getBytes("iso-8859-1"), "UTF-8");
		headers.setContentDispositionFormData("attachement", path2);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}
	
	
}
