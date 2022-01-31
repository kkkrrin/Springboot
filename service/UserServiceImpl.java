package com.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.MypageDTO;
import com.spring.boot.mapper.UserMapper;
import com.spring.boot.mapper.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int maxNum() throws Exception {
		
		return userMapper.maxNum();
	}

	@Override
	public void insertData(MypageDTO dto) throws Exception {
		
		userMapper.insertData(dto);
		
	}

	@Override
	public int nickCheck(String userNick) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.nickCheck(userNick);
	}

	@Override
	public int loginId(String userId, String userPwd) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.loginId(userId, userPwd);
	}

	@Override
	public void updateTag(String userTag, String userId) throws Exception {
		
		userMapper.updateTag(userTag, userId);
		
	}

	@Override
	public MypageDTO getReadData(String userId) throws Exception {
 
		return userMapper.getReadData(userId);
	}

	@Override
	public int idCheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.idCheck(userId);
	}

	@Override
	public String findId(String userMail) throws Exception {
		
		return userMapper.findId(userMail);
	}

	@Override
	public int findChk(String userMail) throws Exception {
		
		return userMapper.findChk(userMail);
	}

	@Override
	public int findPwd(String userId, String userMail) throws Exception {
		
		return userMapper.findPwd(userId, userMail);
	}

	@Override
	public String pwdChk(String userId) throws Exception {
		
		return userMapper.pwdChk(userId);
	}

	@Override
	public void changePwd(String userPwd, String userId) {
		
		userMapper.changePwd(userPwd, userId);
		
	}
	

}
