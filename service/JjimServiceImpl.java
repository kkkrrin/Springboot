package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.JjimDTO;
import com.spring.boot.mapper.JjimMapper;
import com.spring.boot.mapper.JjimService;


@Service
public class JjimServiceImpl implements JjimService {
	
	@Autowired
	private JjimMapper jjimMapper;

	@Override
	public int maxNum_jjim() throws Exception {
		
		return jjimMapper.maxNum_jjim();
	}

	@Override
	public void insertData_jjim(JjimDTO dto) throws Exception {
		jjimMapper.insertData_jjim(dto);
		
	}

	@Override
	public int getDataCount_jjim() throws Exception {
		
		return jjimMapper.getDataCount_jjim();
	}

	@Override
	public List<JjimDTO> getLists_jjim(int start, int end) throws Exception {
		
		return jjimMapper.getLists_jjim(start, end);
	}

	@Override
	public JjimDTO getReadData_jjim(int productNum) throws Exception {
		// TODO Auto-generated method stub
		return jjimMapper.getReadData_jjim(productNum);
	}
	

}
