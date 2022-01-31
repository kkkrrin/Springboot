package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.TermsDTO;
import com.spring.boot.mapper.TermsMapper;
import com.spring.boot.mapper.TermsService;

@Service
public class TermsServiceImpl implements TermsService{
	
	@Autowired
	private TermsMapper termsMapper;

	@Override
	public int maxNum() throws Exception {
		return  termsMapper.maxNum();
	}

	@Override
	public void insertData(TermsDTO dto) throws Exception {
		termsMapper.insertData(dto);
	}

	@Override
	public List<TermsDTO> getLists(TermsDTO termsDTO) throws Exception {
		return termsMapper.getLists(termsDTO);
	}

	@Override
	public TermsDTO getReadData(int t_num) throws Exception {
		return termsMapper.getReadData(t_num);
	}

}
