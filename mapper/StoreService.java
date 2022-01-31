package com.spring.boot.mapper;

import java.util.List;

import com.spring.boot.dto.StoreDTO;

public interface StoreService {
public int maxNum() throws Exception;
public int maxProductNum() throws Exception;


	
	public void insertData(StoreDTO dto) throws Exception;
	//public void insertReply(ReplyDTO replyDto) throws Exception;




	public int getDataCount(String searchKey, String searchValue) throws Exception;
	public List<StoreDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception;
	
	public int getDataCount1(String tags1) throws Exception;
	public List<StoreDTO> getLists1(int start, int end, String tags1) throws Exception;
	
	
	public StoreDTO getReadData(int num) throws Exception;
	public void updateHitCount(int num) throws Exception;

	public void updateData(StoreDTO dto) throws Exception;
	public void deleteData(int num) throws Exception;
	
	
	
	public int maxNum11() throws Exception;
	public int maxProductNum11() throws Exception;	
	//public void insertData11(StorejjimDTO jjimDTO) throws Exception;
	public int getDataCount11(String searchKey, String searchValue) throws Exception;
	//public List<StorejjimDTO> getLists11(int start, int end, String searchKey, String searchValue) throws Exception;
	//public StorejjimDTO getReadData11(int num) throws Exception;
	public void updateHitCount11(int num) throws Exception;
	//public void updateData11(StorejjimDTO jjimDTO) throws Exception;
	public void deleteData11(int num) throws Exception;


	

}
