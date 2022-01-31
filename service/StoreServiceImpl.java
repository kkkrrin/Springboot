package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.StoreDTO;
import com.spring.boot.mapper.StoreMapper;
import com.spring.boot.mapper.StoreService;



@Service
public class StoreServiceImpl implements StoreService{
	@Autowired
	private StoreMapper storeMapper;

	@Override
	public int maxNum() throws Exception {
		return storeMapper.maxNum();
	}
	
	@Override
	public void insertData(StoreDTO dto) throws Exception {
		storeMapper.insertData(dto);	
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return storeMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<StoreDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception {
		return storeMapper.getLists(start, end, searchKey, searchValue);
	}

	@Override
	public StoreDTO getReadData(int num) throws Exception {
		return storeMapper.getReadData(num);
	}

	@Override
	public void updateHitCount(int num) throws Exception {
		storeMapper.updateHitCount(num);	
	}

	@Override
	public void updateData(StoreDTO dto) throws Exception {
		storeMapper.updateData(dto);	
	}

	@Override
	public void deleteData(int num) throws Exception {
		storeMapper.deleteData(num);	
	}


	@Override
	public int getDataCount1(String tags1) throws Exception {
		return storeMapper.getDataCount1(tags1);
	}


	@Override
	public List<StoreDTO> getLists1(int start, int end, String tags1) throws Exception {
		return storeMapper.getLists1(start, end, tags1);
	}

//	@Override
//	public void insertReply(ReplyDTO replyDto) throws Exception {
//		storeMapper.insertReply(replyDto);
//	}
	
	@Override
	public int maxProductNum() throws Exception {
		return storeMapper.maxProductNum();
	}

		
	
	//reply
	@Override
	public int maxNum11() throws Exception {
		return storeMapper.maxNum11();
	}
	@Override
	public int maxProductNum11() throws Exception {
		return storeMapper.maxProductNum11();
	}
//	@Override
//	public void insertData11(StorejjimDTO jjimDTO) throws Exception {
//		storeMapper.insertData11(jjimDTO);		
//	}
	@Override
	public int getDataCount11(String searchKey, String searchValue) throws Exception {
		return storeMapper.getDataCount11(searchKey, searchValue);
	}
//	@Override
//	public List<StorejjimDTO> getLists11(int start, int end, String searchKey, String searchValue) throws Exception {
//		return storeMapper.getLists11(start, end, searchKey, searchValue);
//	}
//	@Override
//	public StorejjimDTO getReadData11(int num) throws Exception {
//		return storeMapper.getReadData11(num);
//	}
	@Override
	public void updateHitCount11(int num) throws Exception {
		storeMapper.updateHitCount11(num);	
	}
//	@Override
//	public void updateData11(StorejjimDTO jjimDTO) throws Exception {
//		storeMapper.updateData11(jjimDTO);	
//	}
	@Override
	public void deleteData11(int num) throws Exception {
		storeMapper.deleteData11(num);
	}


}
