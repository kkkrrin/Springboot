package com.spring.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.MypageDTO;


@Mapper
public interface UserMapper {

	public  int maxNum() throws Exception;
	
	public void insertData(MypageDTO dto) throws Exception;
	
	public int loginId(String userId, String userPwd) throws Exception;
	
	public int findChk(String userMail) throws Exception;
	
	public String findId(String userMail) throws Exception;
	
	public String pwdChk(String userId) throws Exception;
	
	
	public int findPwd(String userId,String userMail) throws Exception;

	public MypageDTO getReadData(String userId) throws Exception;
	
	public int idCheck(String userId) throws Exception;
	
	public int nickCheck(String userNick) throws Exception;

	public void updateTag(String userTag, String userId);
	
	public void changePwd(String userPwd, String userId);
	
}
