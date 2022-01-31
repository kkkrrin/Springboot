package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.NoticeDTO;
import com.spring.boot.mapper.NoticeMapper;
import com.spring.boot.mapper.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired 
	private NoticeMapper noticeMapper;

	@Override
	public int maxNum() throws Exception {
		return noticeMapper.maxNum();
	}

	@Override
	public void insertData(NoticeDTO dto) throws Exception {
		noticeMapper.insertData(dto);	
	}

	@Override
	public List<NoticeDTO> getLists(NoticeDTO noticeDTO) throws Exception {
		return noticeMapper.getLists(noticeDTO);
	}

	@Override
	public NoticeDTO getReadDate(int n_num) throws Exception {
		return noticeMapper.getReadDate(n_num);
	} 

}
