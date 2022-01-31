package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.AboutPatTvDTO;

import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.VideoCommDTO;
import com.spring.boot.dto.ZzimListDTO;


@Mapper
public interface AboutPatTvMapper {
	
	public int maxNum() throws Exception;
	
	public void insertData(AboutPatTvDTO dto) throws Exception;
	
	public int getDataCount(String searchValue) throws Exception;
	
	public List<AboutPatTvDTO> getLists(String searchKey, String searchValue, int start, int end) throws Exception;
	
	public List<AboutPatTvDTO> getLists2() throws Exception; 
	
	public AboutPatTvDTO getReadData(int num) throws Exception;
	
	public void updateHitCount(int num) throws Exception;
	
	public void updateData(AboutPatTvDTO dto) throws Exception;
	
	public void deleteData(int num) throws Exception;
	
	public List<AboutPatTvDTO> getAllList(int start, int end) throws Exception;
	
	 
	
	
	//유저
	public MypageDTO getReadUserData(String userId) throws Exception;
	
	public List<MypageDTO> getUserLists() throws Exception;
	
	
	
	//댓글
	
	public int video_commentMaxNum() throws Exception;
	
	public void video_comment_insert(VideoCommDTO dto) throws Exception;
	
	public void video_comment_delete(int communityNum, String userId) throws Exception;
	
	public List<VideoCommDTO> video_comment_Lists(int communityNum) throws Exception;
	
	public int video_comment_count(int communityNum) throws Exception;
	
	public void video_comment_update(String contents) throws Exception;
	
	public void updateHitCount_video(int num) throws Exception;
	//
	//찜
	public int tv_zzimMaxNum() throws Exception;
	
	public void tv_zzim_insert(ZzimListDTO dto) throws Exception;
	
	public void tv_zzim_delete(int num, String userId) throws Exception;
	
	public List<ZzimListDTO> getTvZzimLists(String userId) throws Exception;

	
	
	//
	
	

}
