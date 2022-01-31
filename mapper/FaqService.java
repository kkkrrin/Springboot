package com.spring.boot.mapper;

import java.util.List;

import com.spring.boot.dto.FaqDTO;

public interface FaqService {
	
	public int maxNum() throws Exception;
	
	public void insertData(FaqDTO dto) throws Exception;
	
	public List<FaqDTO> getLists(FaqDTO faqDTO) throws Exception;
	
	public FaqDTO getReadData(int f_num) throws Exception;


}
