package com.spring.boot.mapper;

import java.util.List;

import com.spring.boot.dto.NoticeDTO;

public interface NoticeService {
	public int maxNum() throws Exception;
	
	public void insertData(NoticeDTO dto) throws Exception;
	
	public List<NoticeDTO> getLists(NoticeDTO noticeDTO) throws Exception;
	
	public NoticeDTO getReadDate(int n_num) throws Exception;

}
