package com.icss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icss.service.UserService;
import com.icss.vo.User;

@Controller("userController")
public class UserController {
	//��װService
	@Resource(name="userService")
	private UserService us;
	public UserService getUs() {
		return us;
	}
	public void setUs(UserService us) {
		this.us = us;
	}

	//�ж��û����Ƿ����
	@RequestMapping("ifExistUsername.action")
	public void ifExastUsername(HttpServletResponse response, String username) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		boolean flag = us.ifExistUsernameService(username);
		out.print(flag);
		out.close();
	}
	
	//�ж��Ƿ���ڸ��û�
	@RequestMapping("ifThePwdIsRight.action")
	public void ifThePwdIsRight(String username, String password, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		boolean flag = us.selectOneUserService(new User(0, username, password));
		out.print(flag);
		out.close();
	}
	
	//�����û���ע��ɹ���
	@RequestMapping("regist.action")
	public String regist(HttpServletRequest request) throws UnsupportedEncodingException {
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8");
		String password = request.getParameter("password");
		us.insertUserService(new User(0, username, password));
		return "forward:search.action?pageNum=1";
	}
	
}
