package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.CommunityCommentDTO;
import com.spring.boot.dto.CommunityDTO;
import com.spring.boot.dto.CommunityLikesDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.ZzimListDTO;
import com.spring.boot.mapper.CommunityMapper;
import com.spring.boot.mapper.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityMapper communityMapper;
	
	@Override
	public int maxNum_Commu() throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.maxNum_Commu();
	}

	@Override
	public void insertData_Commu(CommunityDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
		communityMapper.insertData_Commu(dto);
		
	}

	@Override
	public int getDataCount_Commu(String searchValue) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.getDataCount_Commu(searchValue);
	}

	@Override
	public List<CommunityDTO> getLists_Commu(String searchValue, int start, int end) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.getLists_Commu(searchValue, start, end);
	}

	@Override
	public CommunityDTO getReadData_Commu(int num) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.getReadData_Commu(num);
	}

	@Override
	public void updateHitCount_Commu(int num) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.updateHitCount_Commu(num);
	}

	@Override
	public void uplikes(int num) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.uplikes(num);
	}

	@Override
	public void downlikes(int num) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.downlikes(num);
	}

	@Override
	public void updateData_Commu(CommunityDTO dto) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.updateData_Commu(dto);
	}

	@Override
	public void deleteData_Commu(int num) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.deleteData_Commu(num);
	}

	@Override
	public MypageDTO getReadUserData(String userId) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.getReadUserData(userId);
	}

	@Override
	public List<MypageDTO> getUserLists() throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.getUserLists();
	}

	@Override
	public void communityLikes_insert(CommunityLikesDTO dto) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.communityLikes_insert(dto);
	}

	@Override
	public void communityLikes_delete(int cno, String userId) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.communityLikes_delete(cno, userId);
	}

	@Override
	public int likesmaxNum() throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.likesmaxNum();
		
	}

	@Override
	public int checkLike(int cno, String userId) throws Exception {
		// TODO Auto-generated method stub
		
		return communityMapper.checkLike(cno, userId);
		
	}

	@Override
	public List<CommunityLikesDTO> getLikeLists(String userId) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.getLikeLists(userId);
	}

	@Override
	public int com_commentMaxNum() throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.com_commentMaxNum();
	}

	@Override
	public void com_comment_insert(CommunityCommentDTO dto) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.com_comment_insert(dto);
	}

	@Override
	public void com_comment_delete(int communityNum, String userId) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.com_comment_delete(communityNum, userId);
	}

	@Override
	public List<CommunityCommentDTO> getcom_Comment_Lists(int communityNum) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.getcom_Comment_Lists(communityNum);
	}

	@Override
	public int com_comment_count(int communityNum) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.com_comment_count(communityNum);
		
	}

	@Override
	public void com_comment_change(String contents) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.com_comment_change(contents);
		
	}

	@Override
	public int com_userCount(String userId) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.com_userCount(userId);
	}

	@Override
	public List<CommunityDTO> com_MyUserListData(String userName, int start, int end) {
		// TODO Auto-generated method stub
		return communityMapper.com_MyUserListData(userName, start, end);
	}

	@Override
	public int com_zzimMaxNum() throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.com_zzimMaxNum();
	}

	@Override
	public void com_zzim_insert(ZzimListDTO dto) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.com_zzim_insert(dto);
	}

	@Override
	public void com_zzim_delete(int num, String userId) throws Exception {
		// TODO Auto-generated method stub
		communityMapper.com_zzim_delete(num, userId);
	}

	@Override
	public List<ZzimListDTO> getZzimLists(String userId) throws Exception {
		// TODO Auto-generated method stub
		
		return communityMapper.getZzimLists(userId);
		
	}
	
	
	
	
	
}
