package com.icss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icss.dao.UserMapper;
import com.icss.vo.User;

@Service("userService")
public class UserService {
	
	//��װDAO
	@Resource(name="userMapper")
	private UserMapper um;
	public UserMapper getUm() {
		return um;
	}
	public void setUm(UserMapper um) {
		this.um = um;
	}

	//�ж��û����Ƿ��Ѿ�����
	public boolean ifExistUsernameService(String username) {
		User user = um.ifExistUsername(username);
		boolean flag = true;
		if ("null".equals(user) || null == user) {
			flag = false;
		}
		return flag;
	}

	//�ж��Ƿ���ڸ��û�
	public boolean selectOneUserService(User user) {
		boolean flag = true;
		User userF = um.selectOneUser(user);
		if ("null".equals(userF) || null == userF) {
			flag = false;
		}
		return flag;
	}
	
	//�����û�
	public void insertUserService(User user) {
		um.insertUser(user);
	}

}
