package com.icss.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

public class Article {
	private int id;
	private Date pubDate;
	private String title;
	private String author;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Article(int id, Date pubDate, String title, String author, String content) {
		super();
		this.id = id;
		this.pubDate = pubDate;
		this.title = title;
		this.author = author;
		this.content = content;
	}
	public Article() {
		super();
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", pubDate=" + pubDate + ", title=" + title + ", author=" + author + ", content="
				+ content + "]";
	}
	
	
	
	
}
