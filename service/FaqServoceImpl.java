package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.FaqDTO;
import com.spring.boot.mapper.FaqMapper;
import com.spring.boot.mapper.FaqService;

@Service
public class FaqServoceImpl implements FaqService{
	
	@Autowired
	private FaqMapper faqMapper;
	
	@Override
	public int maxNum() throws Exception{
		return faqMapper.maxNum();
	}

	@Override
	public void insertData(FaqDTO dto) throws Exception {
		faqMapper.insertData(dto);
		
	}

	@Override
	public List<FaqDTO> getLists(FaqDTO faqDTO) throws Exception {
		return faqMapper.getLists(faqDTO);
	}

	@Override
	public FaqDTO getReadData(int f_num) throws Exception {
		return faqMapper.getReadData(f_num);
	}

}
