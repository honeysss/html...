package com.icss.dao;


import com.icss.vo.User;


public interface UserMapper {
	//�ж��û����Ƿ��Ѿ�����
	public User ifExistUsername(String username);
	//�ж��Ƿ���ڸ��û�
	public User selectOneUser(User user);
	//�����û���ע��ɹ���
	public void insertUser(User user);
}
