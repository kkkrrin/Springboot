package com.spring.boot.mapper;

import java.util.List;

import com.spring.boot.dto.JjimDTO;


public interface JjimService {
	
	public int maxNum_jjim() throws Exception;
	
	public void insertData_jjim(JjimDTO dto) throws Exception;
	 
    public int getDataCount_jjim() throws Exception;
    
    public List<JjimDTO> getLists_jjim(int start, int end) throws Exception;

    public JjimDTO getReadData_jjim(int productNum) throws Exception;

}
