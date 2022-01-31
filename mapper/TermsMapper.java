package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.TermsDTO;

@Mapper
public interface TermsMapper {
	
	public int maxNum() throws Exception;
	
	public void insertData(TermsDTO dto) throws Exception;
	
	public List<TermsDTO> getLists(TermsDTO termsDTO) throws Exception;
	
	public TermsDTO getReadData(int t_num) throws Exception;

}
