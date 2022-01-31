package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.CommunityCommentDTO;
import com.spring.boot.dto.CommunityDTO;
import com.spring.boot.dto.CommunityLikesDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.ZzimListDTO;

@Mapper
public interface CommunityMapper {

	public int maxNum_Commu() throws Exception;
	
	public void insertData_Commu(CommunityDTO dto) throws Exception;
	
	public int getDataCount_Commu(String searchValue) throws Exception;
	
	public List<CommunityDTO> getLists_Commu(String searchValue, int start, int end) throws Exception;
	
	public CommunityDTO getReadData_Commu(int num) throws Exception;
	
	public void updateHitCount_Commu(int num) throws Exception;
	
	public void uplikes(int num) throws Exception;
	
	public void downlikes(int num) throws Exception;
	
	public void updateData_Commu(CommunityDTO dto) throws Exception;
	
	public void deleteData_Commu(int num) throws Exception;
	
	public MypageDTO getReadUserData(String userId) throws Exception;
	
	public List<MypageDTO> getUserLists() throws Exception;
	
	public void communityLikes_insert(CommunityLikesDTO dto) throws Exception;
	
	public void communityLikes_delete(int cno, String userId) throws Exception;	
	
	public int likesmaxNum() throws Exception;
	
	public int checkLike(int cno, String userId) throws Exception;
	
	public List<CommunityLikesDTO> getLikeLists(String userId) throws Exception;
	
	public int com_commentMaxNum() throws Exception;
	
	public void com_comment_insert(CommunityCommentDTO dto) throws Exception;
	
	public void com_comment_delete(int communityNum, String userId) throws Exception;
	
	public List<CommunityCommentDTO> getcom_Comment_Lists(int communityNum) throws Exception;
	
	public int com_comment_count(int communityNum) throws Exception;
	
	public void com_comment_change(String contents) throws Exception;
	
	public int com_userCount(String userId) throws Exception;
	
	public List<CommunityDTO> com_MyUserListData(String userName, int start, int end);
			
	public int com_zzimMaxNum() throws Exception;
	
	public void com_zzim_insert(ZzimListDTO dto) throws Exception;
	
	public void com_zzim_delete(int num, String userId) throws Exception;
	
	public List<ZzimListDTO> getZzimLists(String userId) throws Exception;
	
}
