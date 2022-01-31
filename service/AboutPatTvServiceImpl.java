package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.AboutPatTvDTO;
import com.spring.boot.dto.MypageDTO;
import com.spring.boot.dto.VideoCommDTO;
import com.spring.boot.dto.ZzimListDTO;
import com.spring.boot.mapper.AboutPatTvMapper;
import com.spring.boot.mapper.AboutPatTvService;

@Service
public class AboutPatTvServiceImpl implements AboutPatTvService{
	
	@Autowired
	private AboutPatTvMapper aboutPatTvMapper;
	

	@Override
	public int maxNum() throws Exception {
		
		return aboutPatTvMapper.maxNum();
	}

	@Override
	public void insertData(AboutPatTvDTO dto) throws Exception {
		aboutPatTvMapper.insertData(dto);
		
	}

	@Override
	public int getDataCount(String searchValue) throws Exception {
		
		return aboutPatTvMapper.getDataCount(searchValue);
	}

	@Override
	public List<AboutPatTvDTO> getLists(String searchKey, String searchValue, int start, int end) throws Exception {
		
		return aboutPatTvMapper.getLists(searchKey, searchValue, start, end);
	}

	@Override
	public AboutPatTvDTO getReadData(int num) throws Exception {
		
		return aboutPatTvMapper.getReadData(num);
	}

	@Override
	public void updateData(AboutPatTvDTO dto) throws Exception {
		aboutPatTvMapper.updateData(dto);
		
	}

	@Override
	public void deleteData(int num) throws Exception {
		aboutPatTvMapper.deleteData(num);
		
	}

	@Override
	public void updateHitCount(int num) throws Exception {
		aboutPatTvMapper.updateHitCount(num);
		
	}
	//
	
	// 비디오 댓글

	@Override
	public int video_commentMaxNum() throws Exception {
		// TODO Auto-generated method stub
		return aboutPatTvMapper.video_commentMaxNum();
	}

	@Override
	public void video_comment_insert(VideoCommDTO dto) throws Exception {
		aboutPatTvMapper.video_comment_insert(dto); 
		
	}

	@Override
	public void video_comment_delete(int communityNum, String userId) throws Exception {
		aboutPatTvMapper.video_comment_delete(communityNum,userId);
		
	}

	@Override
	public List<VideoCommDTO> video_comment_Lists(int communityNum) throws Exception {
		// TODO Auto-generated method stub
		return aboutPatTvMapper.video_comment_Lists(communityNum);
	}

	@Override
	public int video_comment_count(int communityNum) throws Exception {
		// TODO Auto-generated method stub
		return aboutPatTvMapper.video_comment_count(communityNum);
	}

	@Override
	public void video_comment_update(String contents) throws Exception {
		aboutPatTvMapper.video_comment_update(contents);
		
	}
	// 비디오 댓글

	@Override
	public List<AboutPatTvDTO> getAllList(int start, int end) throws Exception {
		
		return aboutPatTvMapper.getAllList(start, end);
	}

	@Override
	public MypageDTO getReadUserData(String userId) throws Exception {
		// TODO Auto-generated method stub
		return aboutPatTvMapper.getReadUserData(userId);
	}

	@Override
	public List<MypageDTO> getUserLists() throws Exception {
		// TODO Auto-generated method stub
		return aboutPatTvMapper.getUserLists();
	}

	@Override
	public void updateHitCount_video(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int tv_zzimMaxNum() throws Exception {
		
		return aboutPatTvMapper.tv_zzimMaxNum();
	}

	@Override
	public void tv_zzim_insert(ZzimListDTO dto) throws Exception {
		aboutPatTvMapper.tv_zzim_insert(dto);
		
	}

	@Override
	public void tv_zzim_delete(int num, String userId) throws Exception {
		aboutPatTvMapper.tv_zzim_delete(num, userId);
		
	}

	@Override
	public List<ZzimListDTO> getTvZzimLists(String userId) throws Exception {
		
		return aboutPatTvMapper.getTvZzimLists(userId);
	}

	@Override
	public List<AboutPatTvDTO> getLists2() throws Exception {
		// TODO Auto-generated method stub
		return aboutPatTvMapper.getLists2();
	}

	

}
