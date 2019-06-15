package com.icss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icss.dao.UserMapper;
import com.icss.vo.User;

@Service("userService")
public class UserService {
	
	//封装DAO
	@Resource(name="userMapper")
	private UserMapper um;
	public UserMapper getUm() {
		return um;
	}
	public void setUm(UserMapper um) {
		this.um = um;
	}

	//判断用户名是否已经存在
	public boolean ifExistUsernameService(String username) {
		User user = um.ifExistUsername(username);
		boolean flag = true;
		if ("null".equals(user) || null == user) {
			flag = false;
		}
		return flag;
	}

	//判断是否存在该用户
	public boolean selectOneUserService(User user) {
		boolean flag = true;
		User userF = um.selectOneUser(user);
		if ("null".equals(userF) || null == userF) {
			flag = false;
		}
		return flag;
	}
	
	//增加用户
	public void insertUserService(User user) {
		um.insertUser(user);
	}

}
